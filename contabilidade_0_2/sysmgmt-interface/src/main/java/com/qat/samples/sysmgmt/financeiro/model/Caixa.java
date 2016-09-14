package com.qat.samples.sysmgmt.financeiro.model;

import java.util.List;

import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Caixa extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;
	
	private String nome;

	private Double saldo;
	
	private Conta conta;
	
	private DoisValores caixaType;

	private List<BaixaTitulo> baixaTituloList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public List<BaixaTitulo> getBaixaTituloList() {
		return baixaTituloList;
	}

	public void setBaixaTituloList(List<BaixaTitulo> baixaTituloList) {
		this.baixaTituloList = baixaTituloList;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public DoisValores getCaixaType() {
		return caixaType;
	}

	public void setCaixaType(DoisValores caixaType) {
		this.caixaType = caixaType;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Caixa [getId()=" + getId() + ", getSaldo()=" + getSaldo() + ", getBaixaTituloList()="
				+ getBaixaTituloList() + ", getNome()=" + getNome() + ", getCaixaType()=" + getCaixaType()
				+ ", getConta()=" + getConta() + ", toString()=" + super.toString() + "]";
	}


}