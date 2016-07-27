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
public class ConfigEntrada extends ModelCosmeDamiao
{
	private Integer id;

	private Boolean valorTotalFixo;
	private Boolean manterPrecoVendaProd;


	public ConfigEntrada()
	{
		super();
	}

	public ConfigEntrada(Integer id)
	{
		setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getValorTotalFixo() {
		return valorTotalFixo;
	}

	public void setValorTotalFixo(Boolean valorTotalFixo) {
		this.valorTotalFixo = valorTotalFixo;
	}

	public Boolean getManterPrecoVendaProd() {
		return manterPrecoVendaProd;
	}

	public void setManterPrecoVendaProd(Boolean manterPrecoVendaProd) {
		this.manterPrecoVendaProd = manterPrecoVendaProd;
	}

	@Override
	public String toString() {
		return "ConfigEntrada [getId()=" + getId() + ", getValorTotalFixo()=" + getValorTotalFixo()
				+ ", getManterPrecoVendaProd()=" + getManterPrecoVendaProd() + ", toString()=" + super.toString() + "]";
	}

	

}
