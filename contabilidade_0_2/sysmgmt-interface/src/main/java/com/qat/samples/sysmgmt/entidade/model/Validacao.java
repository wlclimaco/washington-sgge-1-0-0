package com.qat.samples.sysmgmt.entidade.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Validacao extends ModelCosmeDamiao
{

	private Integer id;

	private String error;

	private DoisValores tipo;

	public Validacao() {
		// TODO Auto-generated constructor stub
	}

	public Validacao(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public DoisValores getTipo() {
		return tipo;
	}

	public void setTipo(DoisValores tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Validacao [getId()=" + getId() + ", getError()=" + getError() + ", getTipo()=" + getTipo()
				+ ", toString()=" + super.toString() + "]";
	}


}
