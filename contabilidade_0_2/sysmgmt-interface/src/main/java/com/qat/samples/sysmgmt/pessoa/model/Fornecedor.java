package com.qat.samples.sysmgmt.pessoa.model;

import java.util.List;

import com.qat.samples.sysmgmt.cfop.model.CfopPessoa;
import com.qat.samples.sysmgmt.produto.model.ProdutoPessoa;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Fornecedor extends Pessoa
{

	private Integer id;

	private List<CfopPessoa> listCfops;

	private List<ProdutoPessoa> listProdutos;

	public Fornecedor()
	{
		super();
	}

	public Fornecedor(int i, String string) {
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString()
	{
		return "Fornecedor [getListCfops()=" + getListCfops() + ", getListProdutos()=" + getListProdutos()
				+ ", getId()=" + getId() + ", toString()=" + super.toString() + "]";
	}

}
