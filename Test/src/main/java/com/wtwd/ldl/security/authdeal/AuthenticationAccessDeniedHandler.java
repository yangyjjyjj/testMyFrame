package com.wtwd.ldl.security.authdeal;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.wtwd.ldl.common.RespCode;
import com.wtwd.ldl.common.RespEntity;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lixiangyi
 * @date 2019年3月25日
 * @description 自定义403
 */
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp,
                       AccessDeniedException e) throws IOException {
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = resp.getWriter();
        RespEntity respEntity = new RespEntity(RespCode.PER_NO_PERMISSION);
        try{
            out.write(new ObjectMapper().writeValueAsString(respEntity));
            out.flush();
        }finally {
            out.close();
        }


    }
}
