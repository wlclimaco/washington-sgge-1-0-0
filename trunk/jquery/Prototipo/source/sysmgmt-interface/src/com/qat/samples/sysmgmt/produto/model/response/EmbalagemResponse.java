package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.Embalagem;

/**
 * The Model Object CadastroResponse.
 */
public class EmbalagemResponse extends InquiryResponse
{

	/** The cadastro. */
	@XmlElement(nillable = true)
	private List<Embalagem> embalagem;

	/**
	 * Gets the cadastro.
	 * 
	 * @return the cadastro
	 */

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setEmbalagem((List<Embalagem>)coll);
	}

	public List<Embalagem> getEmbalagem()
	{
		return embalagem;
	}

	public void setEmbalagem(List<Embalagem> embalagem)
	{
		this.embalagem = embalagem;
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "CadastroResponse [getEmbalagem()=" + getEmbalagem() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
