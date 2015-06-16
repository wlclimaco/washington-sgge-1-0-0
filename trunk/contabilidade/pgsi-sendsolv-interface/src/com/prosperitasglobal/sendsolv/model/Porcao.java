package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Porcao extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer idproduto;

	/** The type of an account. */
	private PorcaoItem porcaoItens;

	private Double valor;

	/**
	 * Default constructor.
	 */
	public Porcao()
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the idproduto
	 */
	public Integer getIdproduto()
	{
		return idproduto;
	}

	/**
	 * @param idproduto the idproduto to set
	 */
	public void setIdproduto(Integer idproduto)
	{
		this.idproduto = idproduto;
	}

	/**
	 * @return the porcaoItens
	 */
	public PorcaoItem getPorcaoItens()
	{
		return porcaoItens;
	}

	/**
	 * @param porcaoItens the porcaoItens to set
	 */
	public void setPorcaoItens(PorcaoItem porcaoItens)
	{
		this.porcaoItens = porcaoItens;
	}

	/**
	 * @return the valor
	 */
	public Double getValor()
	{
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor)
	{
		this.valor = valor;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Porcao [getId()=" + getId() + ", getIdproduto()=" + getIdproduto() + ", getPorcaoItens()="
				+ getPorcaoItens() + ", getValor()=" + getValor() + ", getTabelaEnumValue()=" + getTabelaEnumValue()
				+ ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue()
				+ ", getParentId()=" + getParentId() + ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType()
				+ ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()="
				+ getEmprId() + ", getSite()=" + getSite() + ", toString()=" + super.toString() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
