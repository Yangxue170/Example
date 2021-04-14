package org.example.risk.safe;


import com.google.common.collect.ImmutableSet;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Set;

/**
 * @author panfudong
 * @date 2021/2/26 18:47
 * -
 * @description 黑名单设备/数据拦截
 */
@Component
public class BlackFilterChain extends BaseChain {

    private final Set<String> blackUcIdSet = ImmutableSet.of("1213123143");

    @Override
    public void doChain(ChainContext context) {
        String ucId = "123";
        for (String uc : blackUcIdSet) {
            if (Objects.equals(uc, ucId)) {
                context.setFlag(true);
                context.setHttpCode(HttpServletResponse.SC_FORBIDDEN);
                context.setReason("当前请求标识在黑名单中");
                return;
            }
        }
        //进行下一个节点处理？
        next(context);
    }

    @Override
    protected int rank() {
        return 0;
    }


}
