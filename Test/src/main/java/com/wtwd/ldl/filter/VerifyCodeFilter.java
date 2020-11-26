package com.wtwd.ldl.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Constants;
import com.wtwd.ldl.common.RespCode;
import com.wtwd.ldl.common.RespCodeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author ldaoliang
 * @Date 2019/11/22 0022 上午 11:06
 * @Description TODO
 **/
public class VerifyCodeFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(VerifyCodeFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if("/login".equals(uri) && "post".equalsIgnoreCase(request.getMethod())){//如果是登录请求，需要验证；如果不是，继续执行后面的过滤器
			//从session中获取验证码
			HttpSession session = request.getSession();
			String sessionCode = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
			logger.info("code in session：" + sessionCode);
			//获取请求参数中的verifyCode
			String webCode = request.getParameter("verifyCode");
			logger.info("code in request：" + webCode);
			if(webCode.equals(sessionCode)){
				filterChain.doFilter(request,response);
			}else{
				response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
				PrintWriter out = response.getWriter();
				RespCodeEntity respCodeEntity = new RespCodeEntity(RespCode.VERIFY_CODE_MISTAKE);
				ObjectMapper objectMapper = new ObjectMapper();
				out.write(objectMapper.writeValueAsString(respCodeEntity));
			}
		}else{
			filterChain.doFilter(request,response);
		}
	}
}
