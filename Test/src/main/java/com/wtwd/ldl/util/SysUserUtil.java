package com.wtwd.ldl.util;

import com.wtwd.ldl.bean.SysUser;
import com.wtwd.ldl.service.Impl.SysUserServiceImpl;
import com.wtwd.ldl.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @Author ldaoliang
 * @Date 2019/10/26 0026 上午 10:14
 * @Description TODO
 **/
public class SysUserUtil {

	/**
	 * 获取当前登录用户；存在了Authentication的context中
	 * @return
	 */
	public static String getCurrentUserName(){
		if(SecurityContextHolder.getContext().getAuthentication()==null){
			return null;
		}
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		return username;
	}

	public static SysUser getCurrentUser(){
		if(SecurityContextHolder.getContext().getAuthentication()==null){
			return null;
		}
		SysUser user = (SysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
}
