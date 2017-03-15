package com.tanghuan.jwt.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tanghuan.jwt.utils.RestRespStatus;

import java.io.Serializable;

/**
 * Created by tanghuan on 2017/3/8.
 */

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class RestRespBody<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public RestRespBody() {
        this.code = RestRespStatus.SUCCESS.getCode();
        this.msg = RestRespStatus.SUCCESS.getMsg();
    }

    public RestRespBody(T data) {
        this.data = data;
    }

    public RestRespBody(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RestRespBody(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestRespBody changeStatus(RestRespStatus status) {
        this.code = status.getCode();
        this.msg = status.getMsg();
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
