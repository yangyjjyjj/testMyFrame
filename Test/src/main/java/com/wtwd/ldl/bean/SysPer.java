package com.wtwd.ldl.bean;

import java.util.List;

/**
 * @Author ldaoliang
 * @Date 2019/10/28 0028 下午 3:52
 * @Description TODO
 **/
public class SysPer {
	private Integer per_id;
	private String per_url;
	private String per_des;
	private List<SysRole> roleList;

	public Integer getPer_id() {
		return per_id;
	}

	public void setPer_id(Integer per_id) {
		this.per_id = per_id;
	}

	public String getPer_url() {
		return per_url;
	}

	public void setPer_url(String per_url) {
		this.per_url = per_url;
	}

	public String getPer_des() {
		return per_des;
	}

	public void setPer_des(String per_des) {
		this.per_des = per_des;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}
}
