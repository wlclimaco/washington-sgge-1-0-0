package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.ConfigProduto;

public class ConfigProdutoResponse extends InquiryResponse
{

	/** Attributes */
	private List<ConfigProduto> configProdutoList;

	/**
	 * The Constructor.
	 */
	public ConfigProdutoResponse()
	{

	}

	/**
	 * @return the configProdutoList
	 */
	public List<ConfigProduto> getConfigProdutoList()
	{
		return configProdutoList;
	}

	/**
	 * @param configProdutoList the configProdutoList to set
	 */
	public void setConfigProdutoList(List<ConfigProduto> configProdutoList)
	{
		this.configProdutoList = configProdutoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setConfigProdutoList((List<ConfigProduto>)coll);
	}

	@Override
	public String toString()
	{
		return "ConfigProdutoResponse [getConfigProdutoList()=" + getConfigProdutoList() + ", toString()=" + super.toString() + "]";
	}

}