package com.wtwd.ldl.security.logindecide;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wtwd.ldl.common.RespCode;
import com.wtwd.ldl.common.RespEntity;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author ldaoliang
 * @Date 2019/10/26 0026 上午 9:47
 * @Description TODO   登录失败处理；以json格式返回
 **/
public class WebAuthenticationFailureHander implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response,
										AuthenticationException e) throws IOException, ServletException {

		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		//默认code为0
		int code = 0;
		//如果是 登录失败(坏的凭证是账号密码错误 ； 用户找到 )
		if(e instanceof BadCredentialsException
			|| e instanceof UsernameNotFoundException){
			code = 907;
		}
		RespEntity respEntity;
		if(code == 907){
			respEntity = new RespEntity(RespCode.PER_USERNAME_PASSWORD_ERR);
		}else{
			respEntity = new RespEntity(RespCode.FAILED);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		PrintWriter out = response.getWriter();

		try{
			out.write(objectMapper.writeValueAsString(respEntity));
			out.flush();
		}finally {
			out.close();
		}



	}
}
