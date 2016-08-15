package com.qat.samples.sysmgmt.entidade.model;

import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ConfigFiscal extends ModelCosmeDamiao
{
	private Integer id;
	private Cnae princAtividade;
	private Regime regime;
	private Double aliqSimples;
	private Double aliqICMS;
	private Double aliqPIS;
	private Double aliqCONFINS;
	private Double aliqIRPJ;
	private Double aliqCLSS;


	public ConfigFiscal()
	{
		super();
	}

	public ConfigFiscal(Integer id)
	{
		setId(id);
	}

	public ConfigFiscal(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Cnae getPrincAtividade() {
		return princAtividade;
	}

	public void setPrincAtividade(Cnae princAtividade) {
		this.princAtividade = princAtividade;
	}

	public Double getAliqICMS() {
		return aliqICMS;
	}

	public void setAliqICMS(Double aliqICMS) {
		this.aliqICMS = aliqICMS;
	}

	public Double getAliqPIS() {
		return aliqPIS;
	}

	public void setAliqPIS(Double aliqPIS) {
		this.aliqPIS = aliqPIS;
	}

	public Double getAliqCONFINS() {
		return aliqCONFINS;
	}

	public void setAliqCONFINS(Double aliqCONFINS) {
		this.aliqCONFINS = aliqCONFINS;
	}

	public Double getAliqIRPJ() {
		return aliqIRPJ;
	}

	public void setAliqIRPJ(Double aliqIRPJ) {
		this.aliqIRPJ = aliqIRPJ;
	}

	public Double getAliqCLSS() {
		return aliqCLSS;
	}

	public void setAliqCLSS(Double aliqCLSS) {
		this.aliqCLSS = aliqCLSS;
	}

	public Regime getRegime() {
		return regime;
	}

	public void setRegime(Regime regime) {
		this.regime = regime;
	}

	public Double getAliqSimples() {
		return aliqSimples;
	}

	public void setAliqSimples(Double aliqSimples) {
		this.aliqSimples = aliqSimples;
	}

	@Override
	public String toString() {
		return "ConfigFiscal [getId()=" + getId() + ", getPrincAtividade()=" + getPrincAtividade() + ", getAliqICMS()="
				+ getAliqICMS() + ", getAliqPIS()=" + getAliqPIS() + ", getAliqCONFINS()=" + getAliqCONFINS()
				+ ", getAliqIRPJ()=" + getAliqIRPJ() + ", getAliqCLSS()=" + getAliqCLSS() + ", getRegime()="
				+ getRegime() + ", getAliqSimples()=" + getAliqSimples() + ", toString()=" + super.toString() + "]";
	}



}
