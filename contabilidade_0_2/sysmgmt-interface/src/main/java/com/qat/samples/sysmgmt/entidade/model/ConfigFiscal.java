package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ConfigFiscal extends ModelCosmeDamiao
{
	private Integer id;
	private DoisValores princAtividade;
	private Regime regime;
	private Double aliqSimples;


	public ConfigFiscal()
	{
		super();
	}

	public ConfigFiscal(Integer id)
	{
		setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DoisValores getPrincAtividade() {
		return princAtividade;
	}

	public void setPrincAtividade(DoisValores princAtividade) {
		this.princAtividade = princAtividade;
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
		return "ConfigFiscal [getId()=" + getId() + ", getPrincAtividade()=" + getPrincAtividade() + ", getRegime()="
				+ getRegime() + ", getAliqSimples()=" + getAliqSimples() + ", toString()=" + super.toString() + "]";
	}

	

}
