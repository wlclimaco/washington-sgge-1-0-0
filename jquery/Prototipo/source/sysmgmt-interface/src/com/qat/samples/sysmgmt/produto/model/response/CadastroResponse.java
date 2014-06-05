package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.Cadastro;

/**
 * The Model Object CadastroResponse.
 */
public class CadastroResponse extends InquiryResponse
{

	/** The cadastro. */
	@XmlElement(nillable = true)
	private List<Cadastro> cadastro;

	/**
	 * Gets the cadastro.
	 * 
	 * @return the cadastro
	 */
	public List<Cadastro> getCadastros()
	{
		return cadastro;
	}

	/**
	 * Sets the cadastro.
	 * 
	 * @param cadastro the new cadastro
	 */
	public void setCadastros(List<Cadastro> cadastro)
	{
		this.cadastro = cadastro;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setCadastros((List<Cadastro>)coll);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "CadastroResponse [getCadastros()=" + getCadastros() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
