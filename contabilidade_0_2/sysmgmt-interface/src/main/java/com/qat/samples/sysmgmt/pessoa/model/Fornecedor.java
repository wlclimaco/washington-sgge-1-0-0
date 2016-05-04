package com.qat.samples.sysmgmt.pessoa.model;

import java.util.Date;
import java.util.List;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
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
		setId(i);
		setNome(string);
		setModelAction(PersistenceActionEnum.INSERT);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
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
