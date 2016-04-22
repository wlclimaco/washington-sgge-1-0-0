package com.qat.samples.sysmgmt.dp.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.dp.model.Eventos;

public class EventoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Eventos> eventosList;

	/**
	 * The Constructor.
	 */
	public EventoResponse()
	{

	}

	/**
	 * @return the eventosList
	 */
	public List<Eventos> getEventosList()
	{
		return eventosList;
	}

	/**
	 * @param eventosList the eventosList to set
	 */
	public void setEventosList(List<Eventos> eventosList)
	{
		this.eventosList = eventosList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setEventosList((List<Eventos>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "EventosResponse [getEventosList()=" + getEventosList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}