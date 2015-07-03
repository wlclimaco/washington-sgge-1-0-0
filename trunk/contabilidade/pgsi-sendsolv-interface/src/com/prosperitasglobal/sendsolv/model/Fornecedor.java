package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Fornecedor extends Pessoa
{
	/** The SendSolv id for the account. */
	private Integer id;

	private List<Cfop> listCfops;

	private List<Produto> listProdutos;

	/**
	 * Default constructor.
	 */
	public Fornecedor()
	{
		super();
	}

	public Fornecedor(Integer id)
	{
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	@Override
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the listCfops
	 */
	public List<Cfop> getListCfops()
	{
		return listCfops;
	}

	/**
	 * @param listCfops the listCfops to set
	 */
	public void setListCfops(List<Cfop> listCfops)
	{
		this.listCfops = listCfops;
	}

	/**
	 * @return the listProdutos
	 */
	public List<Produto> getListProdutos()
	{
		return listProdutos;
	}

	/**
	 * @param listProdutos the listProdutos to set
	 */
	public void setListProdutos(List<Produto> listProdutos)
	{
		this.listProdutos = listProdutos;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Fornecedor [getId()=" + getId() + ", getListCfops()=" + getListCfops() + ", getListProdutos()="
				+ getListProdutos() + ", getNome()=" + getNome() + ", getNomePai()=" + getNomePai() + ", getNomeMae()="
				+ getNomeMae() + ", getNomeConjugue()=" + getNomeConjugue() + ", getEstadoCivil()=" + getEstadoCivil()
				+ ", getDatanasc()=" + getDatanasc() + ", getFoto()=" + getFoto() + ", getSexo()=" + getSexo()
				+ ", getEnderecos()=" + getEnderecos() + ", getDocumentos()=" + getDocumentos() + ", getEmails()="
				+ getEmails() + ", getTelefones()=" + getTelefones() + ", getNotes()=" + getNotes() + ", getBancos()="
				+ getBancos() + ", toString()=" + super.toString() + ", getTabelaEnumValue()=" + getTabelaEnumValue()
				+ ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue()
				+ ", getParentId()=" + getParentId() + ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType()
				+ ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()="
				+ getEmprId() + ", getSite()=" + getSite() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()=" + getCreateDateUTC()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()=" + getModifyDateUTC()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
