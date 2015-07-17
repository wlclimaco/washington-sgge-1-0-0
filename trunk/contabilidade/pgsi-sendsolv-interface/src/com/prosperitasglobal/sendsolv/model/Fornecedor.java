package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Fornecedor extends Pessoa
{

	private List<CfopPessoa> listCfops;

	private List<ProdutoPessoa> listProdutos;

	/**
	 * Default constructor.
	 */
	public Fornecedor()
	{
		super();
	}

	public Fornecedor(Integer id)
	{
		setId(id);
	}

	/**
	 * @return the listCfops
	 */
	public List<CfopPessoa> getListCfops()
	{
		return listCfops;
	}

	/**
	 * @param listCfops the listCfops to set
	 */
	public void setListCfops(List<CfopPessoa> listCfops)
	{
		this.listCfops = listCfops;
	}

	/**
	 * @return the listProdutos
	 */
	public List<ProdutoPessoa> getListProdutos()
	{
		return listProdutos;
	}

	/**
	 * @param listProdutos the listProdutos to set
	 */
	public void setListProdutos(List<ProdutoPessoa> listProdutos)
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
		return "Fornecedor [getListCfops()=" + getListCfops() + ", getListProdutos()=" + getListProdutos()
				+ ", getPessoaTypeEnumValue()=" + getPessoaTypeEnumValue() + ", getId()=" + getId() + ", getNome()="
				+ getNome() + ", getNomePai()=" + getNomePai() + ", getNomeMae()=" + getNomeMae()
				+ ", getNomeConjugue()=" + getNomeConjugue() + ", getEstadoCivil()=" + getEstadoCivil()
				+ ", getDatanasc()=" + getDatanasc() + ", getFoto()=" + getFoto() + ", getPessoaTypeEnum()="
				+ getPessoaTypeEnum() + ", getFormaPagamentoList()=" + getFormaPagamentoList() + ", getCondPagList()="
				+ getCondPagList() + ", getContatoList()=" + getContatoList() + ", getSexo()=" + getSexo()
				+ ", getEnderecos()=" + getEnderecos() + ", getDocumentos()=" + getDocumentos() + ", getEmails()="
				+ getEmails() + ", getTelefones()=" + getTelefones() + ", getNotes()=" + getNotes() + ", getBancos()="
				+ getBancos() + ", toString()=" + super.toString() + ", getTabelaEnumValue()=" + getTabelaEnumValue()
				+ ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue()
				+ ", getParentId()=" + getParentId() + ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType()
				+ ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()="
				+ getEmprId() + ", getSite()=" + getSite() + ", getProcessId()=" + getProcessId() + ", getUserId()="
				+ getUserId() + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
