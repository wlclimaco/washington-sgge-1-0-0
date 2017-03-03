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

	/** The tipo endereco. */
	private String observacao;

	private Double valor;

	private Double juros;

	private Double multa;

	private Double desconto;

	private Conta conta;

	/**
	 * Default constructor.
	 */
	public BaixaTitulo()
	{
		super();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getFinanId()
	{
		return finanId;
	}

	public void setFinanId(Integer finanId)
	{
		this.finanId = finanId;
	}

	public Long getDataBaixa()
	{
		return dataBaixa;
	}

	public void setDataBaixa(Long dataBaixa)
	{
		this.dataBaixa = dataBaixa;
	}

	public String getObservacao()
	{
		return observacao;
	}

	public void setObservacao(String observacao)
	{
		this.observacao = observacao;
	}


	public Double getJuros()
	{
		return juros;
	}

	public void setJuros(Double juros)
	{
		this.juros = juros;
	}

	public Double getMulta()
	{
		return multa;
	}

	public void setMulta(Double multa)
	{
		this.multa = multa;
	}

	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "BaixaTitulo [getId()=" + getId() + ", getFinanId()=" + getFinanId() + ", getDataBaixa()="
				+ getDataBaixa() + ", getObservacao()=" + getObservacao() + ", getValor()=" + getValor()
				+ ", getJuros()=" + getJuros() + ", getMulta()=" + getMulta() + ", getConta()=" + getConta()
				+ ", getDesconto()=" + getDesconto() + ", toString()=" + super.toString() + "]";
	}



}
