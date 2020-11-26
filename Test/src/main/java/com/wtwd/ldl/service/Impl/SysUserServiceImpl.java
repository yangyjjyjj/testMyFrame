package com.wtwd.ldl.service.Impl;

import com.wtwd.ldl.bean.SysRole;
import com.wtwd.ldl.bean.SysUser;
import com.wtwd.ldl.dao.SysUserDao;
import com.wtwd.ldl.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author ldaoliang
 * @Date 2019/10/24 0024 下午 12:31
 * @Description TODO
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao userDao;

	public SysUser getSysUserById(int userId) {
		return userDao.getSysUserById(userId);
	}

	public SysUser getSysUserByName(String userName) {
		return userDao.getSysUserByName(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		//从数据库获取用户信息
		SysUser user = userDao.getSysUserByName(username);

		//判断用户是否存在
		if(user == null){
			throw new UsernameNotFoundException("用户不存在");
		}

		//授权
		List<SysRole> roleList = user.getRoleList();
		for(SysRole role : roleList){
			authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
		}
		user.setAuthorities(authorities);
		return user;
		//return new User(user.getUser_name(),user.getPassword(),authorities);
	}

	@Override
	public int updateLastLoginTime(int userId) {
		return userDao.updateLoginTime(userId);
	}
}
