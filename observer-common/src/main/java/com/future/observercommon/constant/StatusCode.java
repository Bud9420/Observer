package com.future.observercommon.constant;

public enum StatusCode {

    OK(200), // 请求成功，服务器响应正常
    FOUND(302), // 资源被重定向
    BAD_REQUEST(400), // 客户端请求的语法错误，服务器无法理解
    UNAUTHORIZED(401), // 请求要求用户的身份认证
    FORBIDDEN(403), // 服务器拒绝执行此请求，如权限不足
    NOT_FOUNT(404), // 请求的资源不存在
    METHOD_NOT_ALLOWED(405), // 请求的方式不对
    INTERNAL_SERVER_ERROR(500), // 服务器内部异常
    BAD_GATEWAY(502), // 作为网关或代理工作的服务器尝试执行请求时，从远程服务器接收到了一个无效的响应
    SERVICE_UNAVAILABLE(503); // 由于超载或系统维护，服务器暂时无法处理客户端的请求

    private final Integer code; // 状态码

    StatusCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
