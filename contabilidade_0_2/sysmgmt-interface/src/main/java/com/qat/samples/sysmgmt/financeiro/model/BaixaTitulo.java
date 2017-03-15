package com.qat.samples.sysmgmt.financeiro.model;

import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class BaixaTitulo extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;
	private Integer finanId;
	private Long dataBaixa;
	private Long dataVencimento;
	private String observacao;
	private Double valor;
	private Double juros;
	private Double multa;
	private Double seguro;
	private Double outros;
	private Double desconto;
	private Conta conta;

	/**
	 * Default constructor.
	 */
	public BaixaTitulo()
	{
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFinanId() {
		return finanId;
	}

	public void setFinanId(Integer finanId) {
		this.finanId = finanId;
	}

	public Long getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Long dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getJuros() {
		return juros;
	}

	public void setJuros(Double juros) {
		this.juros = juros;
	}

	public Double getMulta() {
		return multa;
	}

	public void setMulta(Double multa) {
		this.multa = multa;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Long getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Long dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Double getSeguro() {
		return seguro;
	}

	public void setSeguro(Double seguro) {
		this.seguro = seguro;
	}

	public Double getOutros() {
		return outros;
	}

	public void setOutros(Double outros) {
		this.outros = outros;
	}

	@Override
	public String toString() {
		return "BaixaTitulo [getId()=" + getId() + ", getFinanId()=" + getFinanId() + ", getDataBaixa()="
				+ getDataBaixa() + ", getObservacao()=" + getObservacao() + ", getValor()=" + getValor()
				+ ", getJuros()=" + getJuros() + ", getMulta()=" + getMulta() + ", getDesconto()=" + getDesconto()
				+ ", getConta()=" + getConta() + ", getDataVencimento()=" + getDataVencimento() + ", getSeguro()="
				+ getSeguro() + ", getOutros()=" + getOutros() + ", toString()=" + super.toString() + "]";
	}






}
