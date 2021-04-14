package org.example.risk.safe;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/14 17:48
 */
public class ChainContext implements Serializable {

    private HttpServletRequest request;

    /**
     * 是否跳过验签
     */
    private boolean flag;

    /**
     * 未通过情况下的httpCode
     * 先都按照200code，基于response body区分具体原因
     */
    private int httpCode = 200;

    /**
     * 具体不通过原因
     */
    private String reason;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ChainContext(HttpServletRequest request, boolean flag, int httpCode, String reason) {
        this.request = request;
        this.flag = flag;
        this.httpCode = httpCode;
        this.reason = reason;
    }

    public ChainContext() {
    }
}
