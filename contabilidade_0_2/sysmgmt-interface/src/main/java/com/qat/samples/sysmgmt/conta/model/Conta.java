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

	/** The descricao. */
	private String descricao;

	/** The numero conta. */
	private String numeroConta;

	/** The nosso numero. */
	private String nossoNumero;

	/** The status conta. */
	private Integer statusConta;

	/** The emite boleto. */
	private Integer emiteBoleto;

	/** The saldo. */
	private double saldo;

	/** The data ult lanc. */
	private Long dataUltLanc;

	/** The list baixa. */
	private List<BaixaDetalhe> listBaixa;

	/** The tipo conta. */
	private DoisValores tipoConta;

	/** The observacao. */
	private String observacao;

	/**
	 * Instantiates a new conta.
	 */
	public Conta()
	{

	}

	/**
	 * Instantiates a new conta.
	 *
	 * @param id the id
	 */
	public Conta(Integer id) {
		super();
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Sets the descricao.
	 *
	 * @param descricao the new descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Gets the numero conta.
	 *
	 * @return the numero conta
	 */
	public String getNumeroConta() {
		return numeroConta;
	}

	/**
	 * Sets the numero conta.
	 *
	 * @param numeroConta the new numero conta
	 */
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo the new saldo
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the data ult lanc.
	 *
	 * @return the data ult lanc
	 */
	public Long getDataUltLanc() {
		return dataUltLanc;
	}

	/**
	 * Sets the data ult lanc.
	 *
	 * @param dataUltLanc the new data ult lanc
	 */
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

	/**
 * Gets the tipo conta.
 *
 * @return the tipo conta
 */
public DoisValores getTipoConta() {
		return tipoConta;
	}

	/**
	 * Sets the tipo conta.
	 *
	 * @param tipoConta the new tipo conta
	 */
	public void setTipoConta(DoisValores tipoConta) {
		this.tipoConta = tipoConta;
	}

	/**
	 * Gets the observacao.
	 *
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * Sets the observacao.
	 *
	 * @param observacao the new observacao
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * Gets the list baixa.
	 *
	 * @return the list baixa
	 */
	public List<BaixaDetalhe> getListBaixa() {
		return listBaixa;
	}

	/**
	 * Sets the list baixa.
	 *
	 * @param listBaixa the new list baixa
	 */
	public void setListBaixa(List<BaixaDetalhe> listBaixa) {
		this.listBaixa = listBaixa;
	}



	/**
	 * Gets the nosso numero.
	 *
	 * @return the nosso numero
	 */
	public String getNossoNumero() {
		return nossoNumero;
	}

	/**
	 * Sets the nosso numero.
	 *
	 * @param nossoNumero the new nosso numero
	 */
	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	/**
	 * Gets the status conta.
	 *
	 * @return the status conta
	 */
	public Integer getStatusConta() {
		return statusConta;
	}

	/**
	 * Sets the status conta.
	 *
	 * @param statusConta the new status conta
	 */
	public void setStatusConta(Integer statusConta) {
		this.statusConta = statusConta;
	}

	/**
	 * Gets the emite boleto.
	 *
	 * @return the emite boleto
	 */
	public Integer getEmiteBoleto() {
		return emiteBoleto;
	}

	/**
	 * Sets the emite boleto.
	 *
	 * @param emiteBoleto the new emite boleto
	 */
	public void setEmiteBoleto(Integer emiteBoleto) {
		this.emiteBoleto = emiteBoleto;
	}

	/* (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao#toString()
	 */
	@Override
	public String toString() {
		return "Conta [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", getNumeroConta()="
				+ getNumeroConta() + ", getSaldo()=" + getSaldo() + ", getDataUltLanc()=" + getDataUltLanc()
				+ ", getTipoConta()=" + getTipoConta() + ", getObservacao()=" + getObservacao() + ", getListBaixa()="
				+ getListBaixa() + ", getNossoNumero()=" + getNossoNumero() + ", getStatusConta()=" + getStatusConta()
				+ ", getEmiteBoleto()=" + getEmiteBoleto() + ", toString()=" + super.toString() + "]";
	}

}
