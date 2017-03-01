package com.qat.samples.sysmgmt.conta.model;

import java.util.List;

import com.qat.samples.sysmgmt.financeiro.model.BaixaTitulo;
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

	private double saldo;
	
	private Long dataUltLanc;
	
	private List<BaixaTitulo> listBaixa;
	
	private DoisValores tipoConta;

	public Conta()
	{

	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public List<BaixaTitulo> getListBaixa() {
		return listBaixa;
	}

	public void setListBaixa(List<BaixaTitulo> listBaixa) {
		this.listBaixa = listBaixa;
	}

	public DoisValores getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(DoisValores tipoConta) {
		this.tipoConta = tipoConta;
	}

	@Override
	public String toString() {
		return "Conta [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", getSaldo()=" + getSaldo()
				+ ", getDataUltLanc()=" + getDataUltLanc() + ", getListBaixa()=" + getListBaixa() + ", getTipoConta()="
				+ getTipoConta() + ", toString()=" + super.toString() + "]";
	}

	
}
