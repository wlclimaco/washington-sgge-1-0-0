package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ConfigAlertas extends ModelCosmeDamiao
{
	private Integer id;

	private Boolean carneBotelo;
	private Boolean carneNormal;


	public ConfigAlertas()
	{
		super();
	}

	public ConfigAlertas(Integer id)
	{
		setId(id);
	}

	public ConfigAlertas(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getCarneBotelo() {
		return carneBotelo;
	}

	public void setCarneBotelo(Boolean carneBotelo) {
		this.carneBotelo = carneBotelo;
	}

	public Boolean getCarneNormal() {
		return carneNormal;
	}

	public void setCarneNormal(Boolean carneNormal) {
		this.carneNormal = carneNormal;
	}

	@Override
	public String toString() {
		return "ConfigCarne [getId()=" + getId() + ", getCarneBotelo()=" + getCarneBotelo() + ", getCarneNormal()="
				+ getCarneNormal() + ", toString()=" + super.toString() + "]";
	}



}
