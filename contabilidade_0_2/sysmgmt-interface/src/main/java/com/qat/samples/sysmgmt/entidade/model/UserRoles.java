package com.qat.samples.sysmgmt.entidade.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class UserRoles extends ModelCosmeDamiao
{

	 private Integer user_role_id;
	 private String username;
	 private Role role;


	public UserRoles(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public UserRoles() {
		super();
	}



	public Integer getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(Integer user_role_id) {
		this.user_role_id = user_role_id;
	}

	public UserRoles(String username, Role role) {
		super();
		this.username = username;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRoles [getUser_role_id()=" + getUser_role_id() + ", getUsername()=" + getUsername() + ", getRole()="
				+ getRole() + ", toString()=" + super.toString() + "]";
	}



}
