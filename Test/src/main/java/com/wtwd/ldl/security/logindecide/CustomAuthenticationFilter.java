package com.wtwd.ldl.security.logindecide;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ldaoliang
 * @Date 2019/10/26 0026 上午 9:41
 * @Description TODO
 **/
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		return super.attemptAuthentication(request, response);
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		return super.obtainPassword(request);
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		return super.obtainUsername(request);
	}

	@Override
	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		super.setDetails(request, authRequest);
	}

	@Override
	public void setUsernameParameter(String usernameParameter) {
		super.setUsernameParameter(usernameParameter);
	}

	@Override
	public void setPasswordParameter(String passwordParameter) {
		super.setPasswordParameter(passwordParameter);
	}

	@Override
	public void setPostOnly(boolean postOnly) {
		super.setPostOnly(postOnly);
	}
}
