package com.wtwd.ldl.service.Impl;

import com.wtwd.ldl.bean.SysPer;
import com.wtwd.ldl.bean.SysUser;
import com.wtwd.ldl.dao.SysRolePerDao;
import com.wtwd.ldl.service.SysRolePerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ldaoliang
 * @Date 2019/10/28 0028 下午 4:05
 * @Description TODO
 **/
@Service
public class SysRolePerServiceImpl implements SysRolePerService {

	@Autowired
	private SysRolePerDao rolePerDao;
	@Override
	public SysPer getUrlPerRoleListByUrl(String url) {
		return rolePerDao.getUrlPerRoleListByUrl(url);
	}
}
