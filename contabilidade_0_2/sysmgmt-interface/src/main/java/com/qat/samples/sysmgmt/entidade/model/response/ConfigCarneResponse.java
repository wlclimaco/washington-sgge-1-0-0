package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.ConfigCarne;
import com.qat.samples.sysmgmt.entidade.model.ConfigCarne;

public class ConfigCarneResponse extends InquiryResponse
{

	/** Attributes */
	private List<ConfigCarne> configCarneList;

	/**
	 * The Constructor.
	 */
	public ConfigCarneResponse()
	{

	}

	/**
	 * @return the configCarneList
	 */
	public List<ConfigCarne> getConfigCarneList()
	{
		return configCarneList;
	}

	/**
	 * @param configCarneList the configCarneList to set
	 */
	public void setConfigCarneList(List<ConfigCarne> configCarneList)
	{
		this.configCarneList = configCarneList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setConfigCarneList((List<ConfigCarne>)coll);
	}

	@Override
	public String toString()
	{
		return "ConfigCarneResponse [getConfigCarneList()=" + getConfigCarneList() + ", toString()=" + super.toString() + "]";
	}

}