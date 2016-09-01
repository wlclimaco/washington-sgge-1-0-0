package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.site.model.PlanoByEmpresa;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Transaction extends Entidade
{

	private Integer id;

	private String token;

	private Long inicioSession;

	private Long finalSession;



	public Transaction()
	{
		super();
	}



	public Transaction(String token, Long inicioSession,String userId) {
		super();
		this.token = token;
		this.inicioSession = inicioSession;
		setUserId(userId);
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Long getInicioSession() {
		return inicioSession;
	}


	public void setInicioSession(Long inicioSession) {
		this.inicioSession = inicioSession;
	}


	public Long getFinalSession() {
		return finalSession;
	}


	public void setFinalSession(Long finalSession) {
		this.finalSession = finalSession;
	}


	@Override
	public String toString() {
		return "Transaction [getId()=" + getId() + ", getToken()=" + getToken() + ", getInicioSession()="
				+ getInicioSession() + ", getFinalSession()=" + getFinalSession() + ", getUserId()=" + getUserId()
				+ ", toString()=" + super.toString() + "]";
	}


}
