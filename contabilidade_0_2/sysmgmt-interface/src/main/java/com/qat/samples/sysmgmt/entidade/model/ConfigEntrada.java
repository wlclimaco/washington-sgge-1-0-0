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

	private Integer valorTotalFixo;
	private Integer manterPrecoVendaProd;


	public ConfigEntrada()
	{
		super();
	}

	public ConfigEntrada(Integer id)
	{
		setId(id);
	}

	public ConfigEntrada(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getValorTotalFixo() {
		return valorTotalFixo;
	}

	public void setValorTotalFixo(Integer valorTotalFixo) {
		this.valorTotalFixo = valorTotalFixo;
	}

	public Integer getManterPrecoVendaProd() {
		return manterPrecoVendaProd;
	}

	public void setManterPrecoVendaProd(Integer manterPrecoVendaProd) {
		this.manterPrecoVendaProd = manterPrecoVendaProd;
	}

	@Override
	public String toString() {
		return "ConfigEntrada [getId()=" + getId() + ", getValorTotalFixo()=" + getValorTotalFixo()
				+ ", getManterPrecoVendaProd()=" + getManterPrecoVendaProd() + ", toString()=" + super.toString() + "]";
	}



}
