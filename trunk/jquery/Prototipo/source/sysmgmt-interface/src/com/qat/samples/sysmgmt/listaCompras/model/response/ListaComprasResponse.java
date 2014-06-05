package com.qat.samples.sysmgmt.listaCompras.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.listaCompras.model.ListaCompras;

/**
 * The Model Object ListaComprasResponse.
 */
public class ListaComprasResponse extends InquiryResponse
{

	/** The listaComprass. */
	@XmlElement(nillable = true)
	private List<ListaCompras> listaCompras;

	/**
	 * Gets the listaComprass.
	 * 
	 * @return the listaComprass
	 */
	public List<ListaCompras> getListaCompras()
	{
		return listaCompras;
	}

	/**
	 * Sets the listaComprass.
	 * 
	 * @param listaComprass the new listaComprass
	 */
	public void setListaCompras(List<ListaCompras> listaCompras)
	{
		this.listaCompras = listaCompras;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setListaCompras((List<ListaCompras>)coll);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "ListaComprasResponse [getListaComprass()=" + getListaCompras() + ", getResultsSetInfo()="
				+ getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
