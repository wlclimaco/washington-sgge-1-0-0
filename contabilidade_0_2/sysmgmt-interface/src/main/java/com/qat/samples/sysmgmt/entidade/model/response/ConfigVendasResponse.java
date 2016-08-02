package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.ConfigVendas;

public class ConfigVendasResponse extends InquiryResponse
{

	/** Attributes */
	private List<ConfigVendas> configVendasList;

	/**
	 * The Constructor.
	 */
	public ConfigVendasResponse()
	{

	}

	/**
	 * @return the configVendasList
	 */
	public List<ConfigVendas> getConfigVendasList()
	{
		return configVendasList;
	}

	/**
	 * @param configVendasList the configVendasList to set
	 */
	public void setConfigVendasList(List<ConfigVendas> configVendasList)
	{
		this.configVendasList = configVendasList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setConfigVendasList((List<ConfigVendas>)coll);
	}

	@Override
	public String toString()
	{
		return "ConfigVendasResponse [getConfigVendasList()=" + getConfigVendasList() + ", toString()=" + super.toString() + "]";
	}

}