package com.qat.samples.sysmgmt.entidade.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Role extends ModelCosmeDamiao
{


	private Integer id;

	private String role;
	private Integer status;
	private Pagina pagina;

	public Role(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Pagina getPagina() {
		return pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	@Override
	public String toString() {
		return "Role [getId()=" + getId() + ", getRole()=" + getRole() + ", getStatus()=" + getStatus()
				+ ", getPagina()=" + getPagina() + ", toString()=" + super.toString() + "]";
	}


}
