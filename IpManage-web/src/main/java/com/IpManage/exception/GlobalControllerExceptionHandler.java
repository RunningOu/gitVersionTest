package com.IpManage.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.IpManage.dataobject.*;
import com.IpManage.dataobject.Error;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局异常处理类（非常重要的类）
 */
@EnableWebMvc
@ControllerAdvice
class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    ResponseEntity<Object> handleControllerException(HttpServletRequest req, Throwable ex) {
        try {
            logger.debug("Handle controller exception", ex);
            return new ResponseEntity<Object>(createResponseFromException(req.getRequestURI(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            logger.warn("Handle controller exception failed", ex);
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        try {
            logger.debug("Handle servlet exception", ex);
            String path = null;
            if (request instanceof ServletWebRequest)
                path = ((ServletWebRequest) request).getNativeRequest(HttpServletRequest.class).getRequestURI();
            return new ResponseEntity<Object>(createResponseFromException(path, ex), HttpStatus.NOT_FOUND);
        } catch (Throwable e) {
            logger.warn("Handle servlet exception failed", ex);
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ErrorResponse createResponseFromException(String path, Throwable exception) {
        ErrorResponse response = new ErrorResponse();
        response.setError(new Error());
        response.getError().setCode(exception.getClass().getSimpleName());
        response.getError().setMessage(exception.getMessage());
        response.getError().setPath(path);
        response.getError().setTime(format.format(new Date()));
        return response;
    }
}
