package com.qat.samples.sysmgmt.endereco.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.endereco.model.Endereco;

/**
 * The Model Object EnderecoResponse.
 */
public class EnderecoResponse extends InquiryResponse
{

	/** The enderecos. */
	@XmlElement(nillable = true)
	private List<Endereco> enderecos;

	/**
	 * Gets the enderecos.
	 * 
	 * @return the enderecos
	 */
	public List<Endereco> getEnderecos()
	{
		return enderecos;
	}

	/**
	 * Sets the enderecos.
	 * 
	 * @param enderecos the new enderecos
	 */
	public void setEnderecos(List<Endereco> enderecos)
	{
		this.enderecos = enderecos;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setEnderecos((List<Endereco>)coll);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "EnderecoResponse [getEnderecos()=" + getEnderecos() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
