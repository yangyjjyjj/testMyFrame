package com.wtwd.ldl.dao;

import com.wtwd.ldl.bean.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleDao {

	SysRole getSysRoleById(int roleId);
}
