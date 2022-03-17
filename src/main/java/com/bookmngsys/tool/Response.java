package com.bookmngsys.tool;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Newway
 */
@Getter
@Setter
public class Response {
    private static final String OK = "OK";
    private static final String ERROR = "ERROR";
    /**
     * 元数据
     */
    private Meta meta;
    /**
     * 响应内容
     */
    private Object data;

    public Response success() {
        this.meta = new Meta(true, OK);
        return this;
    }

    public Response success(Object data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }

    public Response failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }

    public Response failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }
}
