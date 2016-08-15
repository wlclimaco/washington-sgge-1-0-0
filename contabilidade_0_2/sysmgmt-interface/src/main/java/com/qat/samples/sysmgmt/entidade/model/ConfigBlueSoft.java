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
public class ConfigBlueSoft extends ModelCosmeDamiao
{
	private Integer id;


	private Integer ativBlue;
	private Integer url;
	private Integer token;

	public ConfigBlueSoft()
	{
		super();
	}

	public ConfigBlueSoft(Integer id)
	{
		setId(id);
	}

	public ConfigBlueSoft(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAtivBlue() {
		return ativBlue;
	}

	public void setAtivBlue(Integer ativBlue) {
		this.ativBlue = ativBlue;
	}

	public Integer getUrl() {
		return url;
	}

	public void setUrl(Integer url) {
		this.url = url;
	}

	public Integer getToken() {
		return token;
	}

	public void setToken(Integer token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "ConfigBlueSoft [getId()=" + getId() + ", getAtivBlue()=" + getAtivBlue() + ", getUrl()=" + getUrl()
				+ ", getToken()=" + getToken() + ", toString()=" + super.toString() + "]";
	}
	
	
}
