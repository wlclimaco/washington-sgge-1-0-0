package com.qat.samples.sysmgmt.supermercado.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.supermercado.model.Supermercado;

/**
 * The Model Object SupermercadoResponse.
 */
public class SupermercadoResponse extends InquiryResponse
{

	/** The supermercados. */
	@XmlElement(nillable = true)
	private List<Supermercado> supermercados;

	/**
	 * Gets the supermercados.
	 * 
	 * @return the supermercados
	 */
	public List<Supermercado> getSupermercados()
	{
		return supermercados;
	}

	/**
	 * Sets the supermercados.
	 * 
	 * @param supermercados the new supermercados
	 */
	public void setSupermercados(List<Supermercado> supermercados)
	{
		this.supermercados = supermercados;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setSupermercados((List<Supermercado>)coll);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "SupermercadoResponse [getSupermercados()=" + getSupermercados() + ", getResultsSetInfo()="
				+ getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
