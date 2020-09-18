package com.IpManage.common.exception;

/**
 * 单次请求错误返回，非批量<br>
 * US-HTTPAPI-V2.1 HTTP API开发规范<br>
 */
public class RESTError {
    private Error error;

    public RESTError(Integer code, String message) {
        this.error = new Error(code, message);
    }

    public RESTError() {

    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public static class Error {
        private Integer code;
        private String message;

        Error(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


}
