package com.wtwd.ldl.bean;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Author ldaoliang
 * @Date 2019/10/24 0024 上午 10:59
 * @Description TODO
 **/
public class SysUser implements UserDetails {
	private Integer user_id;
	private String user_name;
	private String phone;
	private String password;
	private String registeTime;
	private String lastLoginTime;
	private List<SysRole> roleList;
	private Collection<GrantedAuthority> authorities;

	public SysUser(){

	}

	public SysUser(String user_name,String password,Collection<GrantedAuthority> authorities){
		this.user_name = user_name;
		this.password = password;
		this.authorities = authorities;
	}

	public SysUser(Integer user_id, String user_name, String phone, String password, String registeTime,
				   String lastLoginTime, List<SysRole> roleList, Collection<GrantedAuthority> authorities) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.phone = phone;
		this.password = password;
		this.registeTime = registeTime;
		this.lastLoginTime = lastLoginTime;
		this.roleList = roleList;
		this.authorities = authorities;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	@Override  //为了让princle对象拿到username
	public String getUsername() {
		return this.user_name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegisteTime() {
		return registeTime;
	}

	public void setRegisteTime(String registeTime) {
		this.registeTime = registeTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "SysUser{" +
				"user_id=" + user_id +
				", user_name='" + user_name + '\'' +
				", phone='" + phone + '\'' +
				", password='" + password + '\'' +
				", registeTime='" + registeTime + '\'' +
				", lastLoginTime='" + lastLoginTime + '\'' +
				", roleList=" + roleList +
				", authorities=" + authorities +
				'}';
	}
}

