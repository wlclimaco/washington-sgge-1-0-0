package com.qat.samples.sysmgmt.financeiro.model;

import java.util.List;

import com.qat.samples.sysmgmt.conta.model.ContasTypeEnum;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Caixa extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	private Double saldo;

	private List<BaixaTitulo> baixaTituloList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public List<BaixaTitulo> getBaixaTituloList() {
		return baixaTituloList;
	}

	public void setBaixaTituloList(List<BaixaTitulo> baixaTituloList) {
		this.baixaTituloList = baixaTituloList;
	}

	@Override
	public String toString() {
		return "Caixa [getId()=" + getId() + ", getSaldo()=" + getSaldo() + ", getBaixaTituloList()="
				+ getBaixaTituloList() + ", toString()=" + super.toString() + "]";
	}


}