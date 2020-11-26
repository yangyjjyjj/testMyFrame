package com.wtwd.ldl.service;

import com.wtwd.ldl.bean.SysPer;
import com.wtwd.ldl.bean.SysUser;

public interface SysRolePerService {

	//根据url获取该url所需要的roleList
	SysPer getUrlPerRoleListByUrl(String url);
}
