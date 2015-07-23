package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Conta extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Banco banco;

	/** The type of an account. */
	private String numeroConta;

	private double saldo;

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

	/**
	 * @return the banco
	 */
	public Banco getBanco()
	{
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(Banco banco)
	{
		this.banco = banco;
	}

	/**
	 * @return the numeroConta
	 */
	public String getNumeroConta()
	{
		return numeroConta;
	}

	/**
	 * @param numeroConta the numeroConta to set
	 */
	public void setNumeroConta(String numeroConta)
	{
		this.numeroConta = numeroConta;
	}

	/**
	 * @return the saldo
	 */
	public double getSaldo()
	{
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(double saldo)
	{
		this.saldo = saldo;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BancoCC [getId()=" + getId() + ", getBanco()=" + getBanco() + ", getNumeroConta()=" + getNumeroConta()
				+ ", getSaldo()=" + getSaldo() + ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()="
				+ getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId()
				+ ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()="
				+ getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId()
				+ ", getSite()=" + getSite() + ", toString()=" + super.toString() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
