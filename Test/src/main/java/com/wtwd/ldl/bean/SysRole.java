package com.wtwd.ldl.bean;

/**
 * @Author ldaoliang
 * @Date 2019/10/24 0024 上午 11:00
 * @Description TODO
 **/
public class SysRole {
	private Integer role_id;
	private String role_name;
	private String addTime;
	private int editPermission;

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public int getEditPermission() {
		return editPermission;
	}

	public void setEditPermission(int editPermission) {
		this.editPermission = editPermission;
	}
}
