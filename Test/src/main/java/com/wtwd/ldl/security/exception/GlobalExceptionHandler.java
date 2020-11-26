package com.wtwd.ldl.security.exception;


import com.wtwd.ldl.common.RespCode;
import com.wtwd.ldl.common.RespEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lixiangyi
 * @date 2019年3月26日
 * @description
 */


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理，比如：404,500
     * @param req   req
     * @param e     Exception
     * @return  ResponseData
     * @throws Exception e
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RespEntity defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        RespEntity resp;
        logger.error("defaultErrorHandler",e);
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            resp = new RespEntity(RespCode.NOT_FOUND);
        } else {
            resp = new RespEntity(RespCode.EXCEPTION_500);
        }
        return resp;
    }

}
