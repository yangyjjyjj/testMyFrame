package com.wtwd.ldl.dao;

import com.wtwd.ldl.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserDao {
	//根据ID获取对象
	SysUser getSysUserById(int userId);
	//根据Name获取对象
	SysUser getSysUserByName(String userName);
	//更新最近登录时间
	int updateLoginTime(int userId);
}
