package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.ConfigGeral;

public class ConfigGeralResponse extends InquiryResponse
{

	/** Attributes */
	private List<ConfigGeral> configGeralList;

	/**
	 * The Constructor.
	 */
	public ConfigGeralResponse()
	{

	}

	/**
	 * @return the configGeralList
	 */
	public List<ConfigGeral> getConfigGeralList()
	{
		return configGeralList;
	}

	/**
	 * @param configGeralList the configGeralList to set
	 */
	public void setConfigGeralList(List<ConfigGeral> configGeralList)
	{
		this.configGeralList = configGeralList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setConfigGeralList((List<ConfigGeral>)coll);
	}

	@Override
	public String toString()
	{
		return "ConfigGeralResponse [getConfigGeralList()=" + getConfigGeralList() + ", toString()=" + super.toString() + "]";
	}

}