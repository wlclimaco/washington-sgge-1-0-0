package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.ConfigSMTP;

public class ConfigSMTPResponse extends InquiryResponse
{

	/** Attributes */
	private List<ConfigSMTP> configSMTPList;

	/**
	 * The Constructor.
	 */
	public ConfigSMTPResponse()
	{

	}

	/**
	 * @return the configSMTPList
	 */
	public List<ConfigSMTP> getConfigSMTPList()
	{
		return configSMTPList;
	}

	/**
	 * @param configSMTPList the configSMTPList to set
	 */
	public void setConfigSMTPList(List<ConfigSMTP> configSMTPList)
	{
		this.configSMTPList = configSMTPList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setConfigSMTPList((List<ConfigSMTP>)coll);
	}

	@Override
	public String toString()
	{
		return "ConfigSMTPResponse [getConfigSMTPList()=" + getConfigSMTPList() + ", toString()=" + super.toString() + "]";
	}

}