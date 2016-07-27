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
public class ConfigVendas extends ModelCosmeDamiao
{
	private Integer id;
	private Double  descontoMaxVenda;
	private String  observacao;
	private Boolean imprSegVia;
	private Boolean imprAssinatura;
	private Boolean imprResumoFinanc;
	private Boolean atuaPrecoClonar;
	private Boolean imprColUnidade;
	private Boolean bloquearvendProdSemEstoq;
	private Boolean addDespCalcImposto;
	private Boolean retSubstTribICMS;

	public ConfigVendas()
	{
		super();
	}

	public ConfigVendas(Integer id)
	{
		setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getDescontoMaxVenda() {
		return descontoMaxVenda;
	}

	public void setDescontoMaxVenda(Double descontoMaxVenda) {
		this.descontoMaxVenda = descontoMaxVenda;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getImprSegVia() {
		return imprSegVia;
	}

	public void setImprSegVia(Boolean imprSegVia) {
		this.imprSegVia = imprSegVia;
	}

	public Boolean getImprAssinatura() {
		return imprAssinatura;
	}

	public void setImprAssinatura(Boolean imprAssinatura) {
		this.imprAssinatura = imprAssinatura;
	}

	public Boolean getImprResumoFinanc() {
		return imprResumoFinanc;
	}

	public void setImprResumoFinanc(Boolean imprResumoFinanc) {
		this.imprResumoFinanc = imprResumoFinanc;
	}

	public Boolean getAtuaPrecoClonar() {
		return atuaPrecoClonar;
	}

	public void setAtuaPrecoClonar(Boolean atuaPrecoClonar) {
		this.atuaPrecoClonar = atuaPrecoClonar;
	}

	public Boolean getImprColUnidade() {
		return imprColUnidade;
	}

	public void setImprColUnidade(Boolean imprColUnidade) {
		this.imprColUnidade = imprColUnidade;
	}

	public Boolean getBloquearvendProdSemEstoq() {
		return bloquearvendProdSemEstoq;
	}

	public void setBloquearvendProdSemEstoq(Boolean bloquearvendProdSemEstoq) {
		this.bloquearvendProdSemEstoq = bloquearvendProdSemEstoq;
	}

	public Boolean getAddDespCalcImposto() {
		return addDespCalcImposto;
	}

	public void setAddDespCalcImposto(Boolean addDespCalcImposto) {
		this.addDespCalcImposto = addDespCalcImposto;
	}

	public Boolean getRetSubstTribICMS() {
		return retSubstTribICMS;
	}

	public void setRetSubstTribICMS(Boolean retSubstTribICMS) {
		this.retSubstTribICMS = retSubstTribICMS;
	}

	@Override
	public String toString() {
		return "ConfigVendas [getId()=" + getId() + ", getDescontoMaxVenda()=" + getDescontoMaxVenda()
				+ ", getObservacao()=" + getObservacao() + ", getImprSegVia()=" + getImprSegVia()
				+ ", getImprAssinatura()=" + getImprAssinatura() + ", getImprResumoFinanc()=" + getImprResumoFinanc()
				+ ", getAtuaPrecoClonar()=" + getAtuaPrecoClonar() + ", getImprColUnidade()=" + getImprColUnidade()
				+ ", getBloquearvendProdSemEstoq()=" + getBloquearvendProdSemEstoq() + ", getAddDespCalcImposto()="
				+ getAddDespCalcImposto() + ", getRetSubstTribICMS()=" + getRetSubstTribICMS() + ", toString()="
				+ super.toString() + "]";
	}

	
}
