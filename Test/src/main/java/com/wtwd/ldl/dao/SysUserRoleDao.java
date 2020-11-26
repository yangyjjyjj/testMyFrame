package com.wtwd.ldl.dao;

import com.wtwd.ldl.bean.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysUserRoleDao {

	List<SysUserRole> getSysUserRoleListByUserId(int userId);
}
