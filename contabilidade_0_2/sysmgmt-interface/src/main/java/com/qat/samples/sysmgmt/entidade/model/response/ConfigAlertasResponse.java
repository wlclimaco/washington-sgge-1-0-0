package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.ConfigAlertas;

public class ConfigAlertasResponse extends InquiryResponse
{

	/** Attributes */
	private List<ConfigAlertas> configAlertasList;

	/**
	 * The Constructor.
	 */
	public ConfigAlertasResponse()
	{

	}

	/**
	 * @return the configAlertasList
	 */
	public List<ConfigAlertas> getConfigAlertasList()
	{
		return configAlertasList;
	}

	/**
	 * @param configAlertasList the configAlertasList to set
	 */
	public void setConfigAlertasList(List<ConfigAlertas> configAlertasList)
	{
		this.configAlertasList = configAlertasList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setConfigAlertasList((List<ConfigAlertas>)coll);
	}

	@Override
	public String toString()
	{
		return "ConfigAlertasResponse [getConfigAlertasList()=" + getConfigAlertasList() + ", toString()=" + super.toString() + "]";
	}

}