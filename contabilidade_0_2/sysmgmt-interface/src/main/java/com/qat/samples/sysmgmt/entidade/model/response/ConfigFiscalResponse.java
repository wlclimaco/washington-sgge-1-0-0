package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.ConfigFiscal;

public class ConfigFiscalResponse extends InquiryResponse
{

	/** Attributes */
	private List<ConfigFiscal> configFiscalList;

	/**
	 * The Constructor.
	 */
	public ConfigFiscalResponse()
	{

	}

	/**
	 * @return the configFiscalList
	 */
	public List<ConfigFiscal> getConfigFiscalList()
	{
		return configFiscalList;
	}

	/**
	 * @param configFiscalList the configFiscalList to set
	 */
	public void setConfigFiscalList(List<ConfigFiscal> configFiscalList)
	{
		this.configFiscalList = configFiscalList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setConfigFiscalList((List<ConfigFiscal>)coll);
	}

	@Override
	public String toString()
	{
		return "ConfigFiscalResponse [getConfigFiscalList()=" + getConfigFiscalList() + ", toString()=" + super.toString() + "]";
	}

}