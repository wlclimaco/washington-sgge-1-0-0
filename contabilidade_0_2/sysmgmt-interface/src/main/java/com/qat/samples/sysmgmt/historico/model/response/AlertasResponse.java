package com.qat.samples.sysmgmt.historico.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.alerta.model.Alertas;

public class AlertasResponse extends InquiryResponse
{

	/** Attributes */
	private List<Alertas> alertasList;

	/**
	 * The Constructor.
	 */
	public AlertasResponse()
	{

	}

	/**
	 * @return the alertasList
	 */
	public List<Alertas> getAlertasList()
	{
		return alertasList;
	}

	/**
	 * @param alertasList the alertasList to set
	 */
	public void setAlertasList(List<Alertas> alertasList)
	{
		this.alertasList = alertasList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setAlertasList((List<Alertas>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getAlertasList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}