package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.ConfiguracaoNFe;

public class ConfiguracaoNFeResponse extends InquiryResponse
{

	/** Attributes */
	private List<ConfiguracaoNFe> configuracaoNFeList;

	/**
	 * The Constructor.
	 */
	public ConfiguracaoNFeResponse()
	{

	}

	/**
	 * @return the configuracaoNFeList
	 */
	public List<ConfiguracaoNFe> getConfiguracaoNFeList()
	{
		return configuracaoNFeList;
	}

	/**
	 * @param configuracaoNFeList the configuracaoNFeList to set
	 */
	public void setConfiguracaoNFeList(List<ConfiguracaoNFe> configuracaoNFeList)
	{
		this.configuracaoNFeList = configuracaoNFeList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setConfiguracaoNFeList((List<ConfiguracaoNFe>)coll);
	}

	@Override
	public String toString()
	{
		return "ConfiguracaoNFeResponse [getConfiguracaoNFeList()=" + getConfiguracaoNFeList() + ", toString()=" + super.toString() + "]";
	}

}