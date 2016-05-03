package com.qat.samples.sysmgmt.clinica.model;

import java.util.Date;

import com.qat.samples.sysmgmt.entidade.model.Entidade;
import com.qat.samples.sysmgmt.entidade.model.EntidadeTypeEnum;

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

	public Clinica(Integer id,String nome) {
		super();
		setId(id);
		setNome(nome);
		setEntidadeEnum(EntidadeTypeEnum.CLINICA);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	@Override
	public String toString() {
		return "Clinica [getQntMedicos()=" + getQntMedicos() + ", toString()=" + super.toString() + "]";
	}

}
