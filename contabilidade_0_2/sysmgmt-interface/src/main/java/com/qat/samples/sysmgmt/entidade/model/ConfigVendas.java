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
	private Integer imprSegVia;
	private Integer imprAssinatura;
	private Integer imprResumoFinanc;
	private Integer atuaPrecoClonar;
	private Integer imprColUnidade;
	private Integer bloquearvendProdSemEstoq;
	private Integer addDespCalcImposto;
	private Integer retSubstTribICMS;

	public ConfigVendas()
	{
		super();
	}

	public ConfigVendas(Integer id)
	{
		setId(id);
	}

	public ConfigVendas(int i, String string) {
		// TODO Auto-generated constructor stub
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

	public Integer getImprSegVia() {
		return imprSegVia;
	}

	public void setImprSegVia(Integer imprSegVia) {
		this.imprSegVia = imprSegVia;
	}

	public Integer getImprAssinatura() {
		return imprAssinatura;
	}

	public void setImprAssinatura(Integer imprAssinatura) {
		this.imprAssinatura = imprAssinatura;
	}

	public Integer getImprResumoFinanc() {
		return imprResumoFinanc;
	}

	public void setImprResumoFinanc(Integer imprResumoFinanc) {
		this.imprResumoFinanc = imprResumoFinanc;
	}

	public Integer getAtuaPrecoClonar() {
		return atuaPrecoClonar;
	}

	public void setAtuaPrecoClonar(Integer atuaPrecoClonar) {
		this.atuaPrecoClonar = atuaPrecoClonar;
	}

	public Integer getImprColUnidade() {
		return imprColUnidade;
	}

	public void setImprColUnidade(Integer imprColUnidade) {
		this.imprColUnidade = imprColUnidade;
	}

	public Integer getBloquearvendProdSemEstoq() {
		return bloquearvendProdSemEstoq;
	}

	public void setBloquearvendProdSemEstoq(Integer bloquearvendProdSemEstoq) {
		this.bloquearvendProdSemEstoq = bloquearvendProdSemEstoq;
	}

	public Integer getAddDespCalcImposto() {
		return addDespCalcImposto;
	}

	public void setAddDespCalcImposto(Integer addDespCalcImposto) {
		this.addDespCalcImposto = addDespCalcImposto;
	}

	public Integer getRetSubstTribICMS() {
		return retSubstTribICMS;
	}

	public void setRetSubstTribICMS(Integer retSubstTribICMS) {
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
