package com.wtwd.ldl.service;

import com.wtwd.ldl.bean.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SysUserService extends UserDetailsService {
	//根据ID获取用户信息
	SysUser getSysUserById(int userId);
	//根据用户名获取用户信息
	SysUser getSysUserByName(String userName);
	//获取用户登录信息(userDetails的方法  security用的)
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	//更新最近登录时间
	int updateLastLoginTime(int userId);
}
