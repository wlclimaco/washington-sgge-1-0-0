package com.qat.samples.sysmgmt.conta.model;

import java.util.List;

import com.qat.samples.sysmgmt.financeiro.model.BaixaDetalhe;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Conta extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private String descricao;

	private String numeroConta;

	private double saldo;

	private Long dataUltLanc;

	private List<BaixaDetalhe> listBaixa;

	private DoisValores tipoConta;

	private String observacao;

	public Conta()
	{

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Long getDataUltLanc() {
		return dataUltLanc;
	}

	public void setDataUltLanc(Long dataUltLanc) {
		this.dataUltLanc = dataUltLanc;
	}

//	public List<BaixaTitulo> getListBaixa() {
//		return listBaixa;
//	}
//
//	public void setListBaixa(List<BaixaTitulo> listBaixa) {
//		this.listBaixa = listBaixa;
//	}

	public DoisValores getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(DoisValores tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<BaixaDetalhe> getListBaixa() {
		return listBaixa;
	}

	public void setListBaixa(List<BaixaDetalhe> listBaixa) {
		this.listBaixa = listBaixa;
	}

	@Override
	public String toString() {
		return "Conta [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", getNumeroConta()="
				+ getNumeroConta() + ", getSaldo()=" + getSaldo() + ", getDataUltLanc()=" + getDataUltLanc()
				+ ", getTipoConta()=" + getTipoConta() + ", getObservacao()=" + getObservacao() + ", getListBaixa()="
				+ getListBaixa() + ", toString()=" + super.toString() + "]";
	}





}
