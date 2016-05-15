package com.qat.samples.sysmgmt.cfop.model;

import java.util.Date;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class CfopParentId extends ModelCosmeDamiao
{
	private Integer id;

	private Cfop cfop;



	/**
	 * Default constructor.
	 */
	public CfopParentId()
	{
		super();
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Cfop getCfop() {
		return cfop;
	}



	public void setCfop(Cfop cfop) {
		this.cfop = cfop;
	}



	@Override
	public String toString() {
		return "CfopParentId [getId()=" + getId() + ", getCfop()=" + getCfop() + ", toString()=" + super.toString()
				+ "]";
	}

}
