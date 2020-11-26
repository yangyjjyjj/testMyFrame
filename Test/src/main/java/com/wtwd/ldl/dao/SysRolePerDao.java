package com.wtwd.ldl.dao;

import com.wtwd.ldl.bean.SysPer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRolePerDao {

	//根据url获取该url需要的权限信息
	SysPer getUrlPerRoleListByUrl(String url);
}
