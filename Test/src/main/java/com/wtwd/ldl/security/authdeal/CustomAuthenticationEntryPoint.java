package com.wtwd.ldl.security.authdeal;

/**
 * @author lixiangyi
 * @date 2019/7/2 17:40
 * @description     用来解决匿名用户访问无权限资源时的异常
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import com.wtwd.ldl.common.RespCode;
import com.wtwd.ldl.common.RespEntity;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 解决未登录的异常
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        if(authException instanceof BadCredentialsException){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            PrintWriter out = response.getWriter();
            RespEntity respEntity = new RespEntity(RespCode.PER_UN_LOGIN);
            try{
                out.write(new ObjectMapper().writeValueAsString(respEntity));
                out.flush();
            }finally {
                out.close();
            }
        }
    }
}
