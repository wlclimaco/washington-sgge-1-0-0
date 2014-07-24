package com.qat.samples.sysmgmt.controle.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.controle.model.Controle;

/**
 * The Model Object CidadeResponse.
 */
public class ControleResponse extends InquiryResponse
{

	/** The cidades. */
	@XmlElement(nillable = true)
	private List<Controle> controles;

	public List<Controle> getControles()
	{
		return controles;
	}

	public void setControles(List<Controle> controles)
	{
		this.controles = controles;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setControles((List<Controle>)coll);
	}

	public void addControle(Controle grantedAuthority)
	{
		if (getControles() == null)
		{
			setControles(new ArrayList<Controle>());
		}

		getControles().add(grantedAuthority);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "CidadeResponse [getControles()=" + getControles() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
