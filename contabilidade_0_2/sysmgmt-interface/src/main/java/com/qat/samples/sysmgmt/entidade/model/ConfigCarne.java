package com.qat.samples.sysmgmt.entidade.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ConfigCarne extends ModelCosmeDamiao
{
	private Integer id;
	private Integer carneBotelo;
	private Integer carneNormal;


	public ConfigCarne()
	{

	}

	public ConfigCarne(Integer id)
	{
		setId(id);
	}

	public ConfigCarne(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getCarneBotelo() {
		return carneBotelo;
	}

	public void setCarneBotelo(Integer carneBotelo) {
		this.carneBotelo = carneBotelo;
	}

	public Integer getCarneNormal() {
		return carneNormal;
	}

	public void setCarneNormal(Integer carneNormal) {
		this.carneNormal = carneNormal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ConfigCarne [getId()=" + getId() + ", getCarneBotelo()=" + getCarneBotelo() + ", getCarneNormal()="
				+ getCarneNormal() + ", toString()=" + super.toString() + "]";
	}



}
