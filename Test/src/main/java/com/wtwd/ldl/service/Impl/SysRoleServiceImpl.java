package com.wtwd.ldl.service.Impl;

import com.wtwd.ldl.bean.SysRole;
import com.wtwd.ldl.dao.SysRoleDao;
import com.wtwd.ldl.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ldaoliang
 * @Date 2019/10/24 0024 下午 1:53
 * @Description TODO
 **/
@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao roleDao;

	public SysRole getRoleById(int roleId) {
		return roleDao.getSysRoleById(roleId);
	}
}
