package com.wtwd.ldl.security.logindecide;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wtwd.ldl.bean.SysUser;
import com.wtwd.ldl.common.RespCode;
import com.wtwd.ldl.common.RespEntity;
import com.wtwd.ldl.service.SysUserService;
import com.wtwd.ldl.util.SysUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author ldaoliang
 * @Date 2019/10/26 0026 上午 10:01
 * @Description 登录成功的处理
 **/
public class WebAuthenticationSuccessHander implements AuthenticationSuccessHandler {

	@Autowired
	private SysUserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {

		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		//
//		SysUser user =  SysUserUtil.getCurrentUser();
//		System.out.println(user.getRoleList());
//
//		//更新最近一次登录时间
//		userService.updateLastLoginTime(user.getUser_id());
//		//返回数据到接口 成功结果
//		RespEntity respEntity = new RespEntity(RespCode.SUCCESS,user);
		RespEntity respEntity = new RespEntity(RespCode.SUCCESS);

		ObjectMapper om = new ObjectMapper();
		PrintWriter out = response.getWriter();
		try {
			out.write(om.writeValueAsString(respEntity));
			out.flush();
		}finally {
			out.close();
		}






	}
}
