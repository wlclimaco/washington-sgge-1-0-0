package com.qat.samples.sysmgmt.produto.model;

import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

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
	private List<RentabilidadeItens> rentabilidadeItensList;

	/**
	 * Default constructor.
	 */
	public Rentabilidade()
	{
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(Integer idproduto) {
		this.idproduto = idproduto;
	}

	public List<RentabilidadeItens> getRentabilidadeItensList() {
		return rentabilidadeItensList;
	}

	public void setRentabilidadeItensList(List<RentabilidadeItens> rentabilidadeItensList) {
		this.rentabilidadeItensList = rentabilidadeItensList;
	}

	@Override
	public String toString() {
		return "Rentabilidade [getId()=" + getId() + ", getIdproduto()=" + getIdproduto()
				+ ", getRentabilidadeItensList()=" + getRentabilidadeItensList() + ", toString()=" + super.toString()
				+ "]";
	}


}
