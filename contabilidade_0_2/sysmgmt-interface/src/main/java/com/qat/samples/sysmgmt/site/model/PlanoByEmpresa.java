package com.qat.samples.sysmgmt.site.model;

import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class PlanoByEmpresa extends ModelCosmeDamiao {
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer numContrato;

	/** The type of an account. */
	private Double valor;

	private Long dataInicio;

	private Long dataFim;

	private Plano planoId;

	public PlanoByEmpresa() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumContrato() {
		return numContrato;
	}

	public void setNumContrato(Integer numContrato) {
		this.numContrato = numContrato;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Long dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Long getDataFim() {
		return dataFim;
	}

	public void setDataFim(Long dataFim) {
		this.dataFim = dataFim;
	}

	public Plano getPlanoId() {
		return planoId;
	}

	public void setPlanoId(Plano planoId) {
		this.planoId = planoId;
	}

	@Override
	public String toString() {
		return "PlanoByEmpresa [getId()=" + getId() + ", getNumContrato()=" + getNumContrato() + ", getValor()="
				+ getValor() + ", getDataInicio()=" + getDataInicio() + ", getDataFim()=" + getDataFim()
				+ ", getPlanoId()=" + getPlanoId() + ", toString()=" + super.toString() + "]";
	}

}
