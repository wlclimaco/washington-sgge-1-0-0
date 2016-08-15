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


	private Integer estoqMin;
	private Integer estoqMax;
	private Integer erroNFe;
	private Integer pdCompra;
	private Integer nvCliente;
	private Integer retCaixa;

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

	public Integer getEstoqMin() {
		return estoqMin;
	}

	public void setEstoqMin(Integer estoqMin) {
		this.estoqMin = estoqMin;
	}

	public Integer getEstoqMax() {
		return estoqMax;
	}

	public void setEstoqMax(Integer estoqMax) {
		this.estoqMax = estoqMax;
	}

	public Integer getErroNFe() {
		return erroNFe;
	}

	public void setErroNFe(Integer erroNFe) {
		this.erroNFe = erroNFe;
	}

	public Integer getPdCompra() {
		return pdCompra;
	}

	public void setPdCompra(Integer pdCompra) {
		this.pdCompra = pdCompra;
	}


	public Integer getNvCliente() {
		return nvCliente;
	}

	public void setNvCliente(Integer nvCliente) {
		this.nvCliente = nvCliente;
	}

	public Integer getRetCaixa() {
		return retCaixa;
	}

	public void setRetCaixa(Integer retCaixa) {
		this.retCaixa = retCaixa;
	}

	@Override
	public String toString() {
		return "ConfigAlertas [getId()=" + getId() + ", getEstoqMin()=" + getEstoqMin() + ", getEstoqMax()="
				+ getEstoqMax() + ", getErroNFe()=" + getErroNFe() + ", getPdCompra()=" + getPdCompra()
				+ ", getNvCliente()=" + getNvCliente() + ", getRetCaixa()=" + getRetCaixa() + ", toString()="
				+ super.toString() + "]";
	}

	

}
