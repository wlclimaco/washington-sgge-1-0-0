package com.qat.samples.sysmgmt.financeiro.model;

import java.util.List;

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

	private Double Valor;

	private Double juros;

	private Double multa;

	private Double desconto;

	private List<TipoBaixa> tipoBaixaList;

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

	public Double getValor()
	{
		return Valor;
	}

	public void setValor(Double valor)
	{
		Valor = valor;
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

	public List<TipoBaixa> getTipoBaixaList() {
		return tipoBaixaList;
	}

	public void setTipoBaixaList(List<TipoBaixa> tipoBaixaList) {
		this.tipoBaixaList = tipoBaixaList;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	@Override
	public String toString() {
		return "BaixaTitulo [getId()=" + getId() + ", getFinanId()=" + getFinanId() + ", getDataBaixa()="
				+ getDataBaixa() + ", getObservacao()=" + getObservacao() + ", getValor()=" + getValor()
				+ ", getJuros()=" + getJuros() + ", getMulta()=" + getMulta() + ", getTipoBaixaList()="
				+ getTipoBaixaList() + ", getDesconto()=" + getDesconto() + ", toString()=" + super.toString() + "]";
	}



}
