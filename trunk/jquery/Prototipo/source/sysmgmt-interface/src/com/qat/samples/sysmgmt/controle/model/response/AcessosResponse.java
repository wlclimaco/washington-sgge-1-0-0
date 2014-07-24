package com.qat.samples.sysmgmt.controle.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.util.ControleAcess;

/**
 * The Model Object CidadeResponse.
 */
public class AcessosResponse extends InquiryResponse
{

	/** The cidades. */
	@XmlElement(nillable = true)
	List<ControleAcess> acessos;

	public List<ControleAcess> getAcessos()
	{
		return acessos;
	}

	public void setAcessos(List<ControleAcess> acessos)
	{
		this.acessos = acessos;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setAcessos((List<ControleAcess>)coll);
	}

	public void addControle(ControleAcess grantedAuthority)
	{
		if (getAcessos() == null)
		{
			setAcessos(new ArrayList<ControleAcess>());
		}

		getAcessos().add(grantedAuthority);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "CidadeResponse [getAcessos()=" + getAcessos() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
