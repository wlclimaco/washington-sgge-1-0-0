/** create by system gera-java version 1.0.0 29/09/2016 22:42 : 1*/
package com.qat.samples.sysmgmt.nfe.model;


import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoCana extends ModelCosmeDamiao
{

    /** The econtabil id for the NFNotaInfoCana. */
    private Integer id;

    /** The econtabil safra for the NFNotaInfoCana. */
    private String safra;

    /** The econtabil referencia for the NFNotaInfoCana. */
    private String referencia;

    /** The econtabil fornecimentosDiario for the NFNotaInfoCana. */
    private List<NFNotaInfoCanaFornecimentoDiario> fornecimentosDiario;

    /** The econtabil deducoes for the NFNotaInfoCana. */
    private List<NFNotaInfoCanaDeducao> deducoes;

    /** The econtabil quantidadeTotalMes for the NFNotaInfoCana. */
    private String quantidadeTotalMes;

    /** The econtabil quantidadeTotalAnterior for the NFNotaInfoCana. */
    private String quantidadeTotalAnterior;

    /** The econtabil quantidadeTotalGeral for the NFNotaInfoCana. */
    private String quantidadeTotalGeral;

    /** The econtabil valorFornecimento for the NFNotaInfoCana. */
    private String valorFornecimento;

    /** The econtabil valorTotalDeducao for the NFNotaInfoCana. */
    private String valorTotalDeducao;

    /** The econtabil valorLiquidoFornecimento for the NFNotaInfoCana. */
    private String valorLiquidoFornecimento;



    /**
     * Default constructor.
     */
    public NFNotaInfoCana()
    {
        super();
    }



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getSafra() {
		return safra;
	}



	public void setSafra(String safra) {
		this.safra = safra;
	}



	public String getReferencia() {
		return referencia;
	}



	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}



	public List<NFNotaInfoCanaFornecimentoDiario> getFornecimentosDiario() {
		return fornecimentosDiario;
	}



	public void setFornecimentosDiario(List<NFNotaInfoCanaFornecimentoDiario> fornecimentosDiario) {
		this.fornecimentosDiario = fornecimentosDiario;
	}



	public List<NFNotaInfoCanaDeducao> getDeducoes() {
		return deducoes;
	}



	public void setDeducoes(List<NFNotaInfoCanaDeducao> deducoes) {
		this.deducoes = deducoes;
	}



	public String getQuantidadeTotalMes() {
		return quantidadeTotalMes;
	}



	public void setQuantidadeTotalMes(String quantidadeTotalMes) {
		this.quantidadeTotalMes = quantidadeTotalMes;
	}



	public String getQuantidadeTotalAnterior() {
		return quantidadeTotalAnterior;
	}



	public void setQuantidadeTotalAnterior(String quantidadeTotalAnterior) {
		this.quantidadeTotalAnterior = quantidadeTotalAnterior;
	}



	public String getQuantidadeTotalGeral() {
		return quantidadeTotalGeral;
	}



	public void setQuantidadeTotalGeral(String quantidadeTotalGeral) {
		this.quantidadeTotalGeral = quantidadeTotalGeral;
	}



	public String getValorFornecimento() {
		return valorFornecimento;
	}



	public void setValorFornecimento(String valorFornecimento) {
		this.valorFornecimento = valorFornecimento;
	}



	public String getValorTotalDeducao() {
		return valorTotalDeducao;
	}



	public void setValorTotalDeducao(String valorTotalDeducao) {
		this.valorTotalDeducao = valorTotalDeducao;
	}



	public String getValorLiquidoFornecimento() {
		return valorLiquidoFornecimento;
	}



	public void setValorLiquidoFornecimento(String valorLiquidoFornecimento) {
		this.valorLiquidoFornecimento = valorLiquidoFornecimento;
	}



	@Override
	public String toString() {
		return "NFNotaInfoCana [getId()=" + getId() + ", getSafra()=" + getSafra() + ", getReferencia()="
				+ getReferencia() + ", getFornecimentosDiario()=" + getFornecimentosDiario() + ", getDeducoes()="
				+ getDeducoes() + ", getQuantidadeTotalMes()=" + getQuantidadeTotalMes()
				+ ", getQuantidadeTotalAnterior()=" + getQuantidadeTotalAnterior() + ", getQuantidadeTotalGeral()="
				+ getQuantidadeTotalGeral() + ", getValorFornecimento()=" + getValorFornecimento()
				+ ", getValorTotalDeducao()=" + getValorTotalDeducao() + ", getValorLiquidoFornecimento()="
				+ getValorLiquidoFornecimento() + ", toString()=" + super.toString() + "]";
	}

 }
