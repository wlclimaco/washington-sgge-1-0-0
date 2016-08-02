package com.qat.samples.sysmgmt.site.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServicoItens;

/**
 * The Model Object OrdemServicoItensResponse.
 */
public class OrdemServicoItensResponse extends InquiryResponse
{

	/** The OrdemServicoItensList. */
	@XmlElement(nillable = true)
	private List<OrdemServicoItens> OrdemServicoItensList;

	/**
	 * Gets the OrdemServicoItensList.
	 *
	 * @return the OrdemServicoItensList
	 */
	public List<OrdemServicoItens> getOrdemServicoItenss()
	{
		return OrdemServicoItensList;
	}

	/**
	 * Sets the OrdemServicoItensList.
	 *
	 * @param OrdemServicoItensList the new OrdemServicoItensList
	 */
	public void setOrdemServicoItenss(List<OrdemServicoItens> OrdemServicoItensList)
	{
		this.OrdemServicoItensList = OrdemServicoItensList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setOrdemServicoItenss((List<OrdemServicoItens>)coll);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "OrdemServicoItensResponse [getOrdemServicoItenss()=" + getOrdemServicoItenss() + ", getResultsSetInfo()="
				+ getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
