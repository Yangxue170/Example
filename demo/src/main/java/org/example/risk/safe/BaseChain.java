package org.example.risk.safe;

import java.util.Comparator;
import java.util.List;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/14 17:43
 */
public abstract class BaseChain implements Comparator<BaseChain> {

    /**
     * 下一个节点
     */
    private BaseChain nextChain;

    public BaseChain getNextChain() {
        return nextChain;
    }

    public void setNextChain(BaseChain nextChain) {
        this.nextChain = nextChain;
    }

    /**
     * 进行逻辑处理
     *
     * @param context 上下文数据
     */
    public abstract void doChain(ChainContext context);

    public void next(ChainContext context) {
        if (nextChain != null) {
            nextChain.doChain(context);
        }
    }


    /**
     * list转chain
     * @param chainList
     * @return
     */
    public static BaseChain loadChain(List<BaseChain> chainList) {
        if (chainList.size() > 0) {
            BaseChain preChain = chainList.get(0);
            for (int i = 1; i < chainList.size(); i++) {
                preChain.setNextChain(chainList.get(i));
                preChain = preChain.getNextChain();
            }
            return chainList.get(0);
        }
        return null;
    }

    protected abstract int rank();

    @Override
    public int compare(BaseChain o1, BaseChain o2) {
        return o1.rank() - o2.rank();
    }
}
