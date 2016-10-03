package com.qat.samples.sysmgmt.site.model;

import java.util.List;

import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.produto.model.Servico;
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

	private List<ServicoAndPlano> planoServicoList;
	

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


	public List<ServicoAndPlano> getPlanoServicoList() {
		return planoServicoList;
	}


	public void setPlanoServicoList(List<ServicoAndPlano> planoServicoList) {
		this.planoServicoList = planoServicoList;
	}


	@Override
	public String toString() {
		return "PlanoByEmpresa [getId()=" + getId() + ", getNumContrato()=" + getNumContrato() + ", getValor()="
				+ getValor() + ", getDataInicio()=" + getDataInicio() + ", getDataFim()=" + getDataFim()
				+ ", getPlanoServicoList()=" + getPlanoServicoList() + ", toString()=" + super.toString() + "]";
	}
	
	

	

	
}
