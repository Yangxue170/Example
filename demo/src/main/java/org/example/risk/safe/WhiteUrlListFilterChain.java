package org.example.risk.safe;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author Jdx
 * @version 1.0
 * @description 白名单
 * @date 2021/4/14 17:54
 */
@Component
public class WhiteUrlListFilterChain extends BaseChain {
    /**
     * 白名单列表,全路径
     */
    private static final Set<String> WHITE_URL_LIST = ImmutableSet.of();

    /**
     * 白名单列表,前缀
     */
    private static final Set<String> WHITE_URL_PREFIX_LIST = ImmutableSet.of(
            "/swagger-resources",
            "/swagger-ui.html"
    );


    @Override
    public void doChain(ChainContext context) {
        String url = context.getRequest().getRequestURI();
        if (StringUtils.isEmpty(url)) {
            context.setHttpCode(HttpServletResponse.SC_FORBIDDEN);
            context.setReason("请求的url是空");
            return;
        }
        //是否是指定白名单路径
        if (WHITE_URL_LIST.contains(url)) {
            context.setFlag(true);
            return;
        }
        //是否符合白名单路径前缀
        for (String s : WHITE_URL_PREFIX_LIST) {
            //前缀，以xx开始
            if (url.startsWith(s)) {
                context.setFlag(true);
                return;
            }
        }
        next(context);
    }

    @Override
    protected int rank() {
        return 1;
    }
}
