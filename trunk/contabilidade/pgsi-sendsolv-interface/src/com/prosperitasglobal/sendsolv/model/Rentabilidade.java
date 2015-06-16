package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Rentabilidade extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer idproduto;

	/** The type of an account. */
	private List<RentabilidadeItens> rentabilidadeList;

	/**
	 * Default constructor.
	 */
	public Rentabilidade()
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
	 * @return the rentabilidadeList
	 */
	public List<RentabilidadeItens> getRentabilidadeList()
	{
		return rentabilidadeList;
	}

	/**
	 * @param rentabilidadeList the rentabilidadeList to set
	 */
	public void setRentabilidadeList(List<RentabilidadeItens> rentabilidadeList)
	{
		this.rentabilidadeList = rentabilidadeList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Rentabilidade [getId()=" + getId() + ", getIdproduto()=" + getIdproduto() + ", getRentabilidadeList()="
				+ getRentabilidadeList() + ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()="
				+ getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId()
				+ ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()="
				+ getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId()
				+ ", getSite()=" + getSite() + ", toString()=" + super.toString() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
