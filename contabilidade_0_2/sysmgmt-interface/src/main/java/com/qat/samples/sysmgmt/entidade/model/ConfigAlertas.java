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


	private Boolean estoqMin;
	private Boolean estoqMax;
	private Boolean erroNFe;
	private Boolean pdCompra;

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

	public Boolean getEstoqMin() {
		return estoqMin;
	}

	public void setEstoqMin(Boolean estoqMin) {
		this.estoqMin = estoqMin;
	}

	public Boolean getEstoqMax() {
		return estoqMax;
	}

	public void setEstoqMax(Boolean estoqMax) {
		this.estoqMax = estoqMax;
	}

	public Boolean getErroNFe() {
		return erroNFe;
	}

	public void setErroNFe(Boolean erroNFe) {
		this.erroNFe = erroNFe;
	}

	public Boolean getPdCompra() {
		return pdCompra;
	}

	public void setPdCompra(Boolean pdCompra) {
		this.pdCompra = pdCompra;
	}

	@Override
	public String toString() {
		return "ConfigAlertas [getId()=" + getId() + ", getEstoqMin()=" + getEstoqMin() + ", getEstoqMax()="
				+ getEstoqMax() + ", getErroNFe()=" + getErroNFe() + ", getPdCompra()=" + getPdCompra()
				+ ", toString()=" + super.toString() + "]";
	}

	

}
