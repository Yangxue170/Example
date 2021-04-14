package org.example.risk;

import org.apache.logging.log4j.util.Strings;
import org.example.http.RestTemplateLogInterceptor;
import org.example.risk.safe.BaseChain;
import org.example.risk.safe.ChainContext;
import org.example.servlet.RepeatReadServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author Jdx
 * @version 1.0
 * "/*"拦截全部请求，但是想有些不进行拦截，则进行白名单设计
 * @description 安全与风控，验签+黑白名单
 * @date 2021/4/14 17:40
 */

@WebFilter(filterName = "riskSafeFilter",urlPatterns = "/*")
public class RiskSafeFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(RestTemplateLogInterceptor.class);

    private BaseChain chain;
    @Autowired
    private List<BaseChain> chainList;

    @PostConstruct
    private void init() {
        chainList.sort(chainList.get(0));
        chain = BaseChain.loadChain(chainList);
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //构建上下文对象
        ChainContext context = new ChainContext();
        try {
            HttpServletRequest request;
            if (servletRequest instanceof HttpServletRequest) {
                request = new RepeatReadServletRequest((HttpServletRequest) servletRequest);

                context.setRequest(request);
                //执行职责链
                chain.doChain(context);
                if (Strings.isNotBlank(context.getReason())) {
                    log.info("已经拦截{}", context.getReason());
                    return;
                }
                if (context.isFlag()) {
                    //跳过验签
                    request.setAttribute("__skipOpenIAMValidation__", true);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            log.error("do filter chain error", e);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void destroy() {

    }
}
