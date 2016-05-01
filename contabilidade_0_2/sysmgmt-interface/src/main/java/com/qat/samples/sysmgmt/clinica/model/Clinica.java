package com.qat.samples.sysmgmt.clinica.model;

import com.qat.samples.sysmgmt.entidade.model.Entidade;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Clinica extends Entidade
{
    private Integer qntMedicos;

	public Integer getQntMedicos() {
		return qntMedicos;
	}

	public void setQntMedicos(Integer qntMedicos) {
		this.qntMedicos = qntMedicos;
	}

	public Clinica() {
		super();
	}

	public Clinica(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Clinica [getQntMedicos()=" + getQntMedicos() + ", toString()=" + super.toString() + "]";
	}

}
