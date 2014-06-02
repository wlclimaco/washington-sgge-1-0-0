package com.qat.samples.sysmgmt.cidade.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.cidade.model.Cidade;

/**
 * The Model Object CidadeResponse.
 */
public class CidadeResponse extends InquiryResponse
{

	/** The cidades. */
	@XmlElement(nillable = true)
	private List<Cidade> cidades;

	/**
	 * Gets the cidades.
	 * 
	 * @return the cidades
	 */
	public List<Cidade> getCidades()
	{
		return cidades;
	}

	/**
	 * Sets the cidades.
	 * 
	 * @param cidades the new cidades
	 */
	public void setCidades(List<Cidade> cidades)
	{
		this.cidades = cidades;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setCidades((List<Cidade>)coll);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "CidadeResponse [getCidades()=" + getCidades() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
