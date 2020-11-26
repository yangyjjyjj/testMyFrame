package com.wtwd.ldl.service;

import com.wtwd.ldl.bean.SysRole;
import com.wtwd.ldl.bean.SysUser;

public interface SysRoleService {
	//根据角色ID获取角色信息
	SysRole getRoleById(int roleId);
}
