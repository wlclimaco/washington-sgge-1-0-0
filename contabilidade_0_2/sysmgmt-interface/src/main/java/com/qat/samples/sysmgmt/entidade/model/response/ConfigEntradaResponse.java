package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.ConfigEntrada;

public class ConfigEntradaResponse extends InquiryResponse
{

	/** Attributes */
	private List<ConfigEntrada> configEntradaList;

	/**
	 * The Constructor.
	 */
	public ConfigEntradaResponse()
	{

	}

	/**
	 * @return the configEntradaList
	 */
	public List<ConfigEntrada> getConfigEntradaList()
	{
		return configEntradaList;
	}

	/**
	 * @param configEntradaList the configEntradaList to set
	 */
	public void setConfigEntradaList(List<ConfigEntrada> configEntradaList)
	{
		this.configEntradaList = configEntradaList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setConfigEntradaList((List<ConfigEntrada>)coll);
	}

	@Override
	public String toString()
	{
		return "ConfigEntradaResponse [getConfigEntradaList()=" + getConfigEntradaList() + ", toString()=" + super.toString() + "]";
	}

}