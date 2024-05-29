package com.ynjabo77.jabouser.common;

/**
 * 返回工具类
 */
public class ResultUtils {
    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code
     * @param msg
     * @return
     */
    public static BaseResponse error(int code, String msg) {
        return new BaseResponse(code, null, msg);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @param msg
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String msg) {
        return new BaseResponse(errorCode.getCode(), null, msg);
    }
}
