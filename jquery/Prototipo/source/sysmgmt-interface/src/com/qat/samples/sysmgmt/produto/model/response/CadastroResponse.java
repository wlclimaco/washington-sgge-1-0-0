package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.util.Criteria;

/**
 * The Model Object CadastroResponse.
 */
public class CadastroResponse extends InquiryResponse
{

	/** The cadastro. */
	@XmlElement(nillable = true)
	private List<Cadastro> cadastro;

	private List<Criteria> criteria;

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

	public List<Cadastro> getCadastro()
	{
		return cadastro;
	}

	public void setCadastro(List<Cadastro> cadastro)
	{
		this.cadastro = cadastro;
	}

	public List<Criteria> getCriteria()
	{
		return criteria;
	}

	public void setCriteria(List<Criteria> criteria)
	{
		this.criteria = criteria;
	}

	@Override
	public String toString()
	{
		return "CadastroResponse [getCadastros()=" + getCadastros() + ", getCadastro()=" + getCadastro()
				+ ", getCriteria()=" + getCriteria() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ ", toString()=" + super.toString() + "]";
	}

}
