package com.tanghuan.jwt.utils;

/**
 * Created by tanghuan on 2017/3/8.
 */

public enum RestRespStatus {

    SUCCESS(200, "SUCCESS", "成功"),
    NON_AUTHORITATIVE_INFORMATION(203, "NON_AUTHORITATIVE_INFORMATION", "缺少授权行为信息"),
    NO_CONTENT(204, "NO_CONTENT", "没有返回数据"),
    BAD_REQUEST(400, "BAD_REQUEST", "请求异常"),
    UNAUTHORIZED(401, "UNAUTHORIZED", "资源未授权"),
    FORBIDDEN(403, "FORBIDDEN", "没有权限访问"),
    NOT_FOUND(404, "NOT_FOUND", "找不到指定资源"),
    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED", "方法不允许"),
    NOT_ACCEPTABLE(406, "NOT_ACCEPTABLE", "请求不允许"),
    UNPROCESSABLE_ENTITY(422, "UNPROCESSABLE_ENTITY", "无法处理的Entity"),
    LOCKED(423, "LOCKED", "资源被锁定"),
    NOT_ALLOWED(424, "NOT_ALLOWED", "不允许的操作"),
    SYSTEM_FAIL(500, "SYSTEM_FAIL", "服务器内部错误"),
    SYSTEM_BUSY(501, "SYSTEM_BUSY", "服务器忙请稍后再试"),

    CREATED_SUCCESS(20001, "CREATED_SUCCESS", "创建成功"),
    DELETE_SUCCESS(20002, "DELETE_SUCCESS", "删除成功"),
    UPDATE_SUCCESS(20003, "UPDATE_SUCCESS", "更新成功"),
    TOP_SUCCESS(20004, "TOP_SUCCESS", "置顶成功"),
    LOCKED_SUCCESS(20005, "LOCKED_SUCCESS", "锁定成功"),
    UNLOCKED_SUCCESS(20006, "UNLOCKED_SUCCESS", "解锁成功"),

    PARAM_NOT_RIGHT(40000, "PARAM_NOT_RIGHT", "相关参数错误"),

    ACCOUNT_NULL(40100, "ACCOUNT_NULL", "账号为空"),
    PASSWORD_NULL(40101, "PASSWORD_NULL", "密码为空"),
    ACCOUNT_PASSWORD_ERROR(40102, "ACCOUNT_PASSWORD_ERROR", "账户名/密码错误"),
    ACCOUNT_LOCKED(40103, "ACCOUNT_LOCKED", "账号被锁定了"),
    UNAUTHENTICATION(40104, "UNAUTHENTICATION", "未登录"),

    AUTHENTICATED(20007, "AUTHENTICATED", "登录成功"),
    TOKEN_NOT_FIND(40105, "TOKEN_NOT_FIND", "Token未找到"),

    TOKEN_EXPIRED(40106, "TOKEN_EXPIRED", "Token已过期"),
    TOKEN_NOT_NEED_REFRESH(40107, "TOKEN_NOT_NEED_REFRESH", "Token不需要刷新"),
    TOKEN_REFRESHED(20008, "TOKEN_REFRESHED", "Token刷新成功");



    private int code;
    private String msg;
    private String info;

    RestRespStatus(int code, String msg, String info) {
        this.code = code;
        this.msg = msg;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static RestRespStatus getStatus(String msg) {
        for (RestRespStatus value : values()) {
            if (value.msg.equals(msg)) {
                return value;
            }
        }
        return SUCCESS;
    }
}
