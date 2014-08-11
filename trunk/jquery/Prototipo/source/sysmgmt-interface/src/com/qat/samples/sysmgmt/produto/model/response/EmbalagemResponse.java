package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.Embalagem;
import com.qat.samples.sysmgmt.util.Criteria;

/**
 * The Model Object CadastroResponse.
 */
public class EmbalagemResponse extends InquiryResponse
{

	/** The cadastro. */
	@XmlElement(nillable = true)
	private List<Embalagem> embalagem;

	/** The criteria. */
	private List<Criteria> criteria;

	/**
	 * Gets the cadastro.
	 * 
	 * @param coll the coll
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

	/**
	 * Gets the cadastro.
	 * 
	 * @return the cadastro
	 */
	public List<Embalagem> getEmbalagem()
	{
		return embalagem;
	}

	/**
	 * Sets the cadastro.
	 * 
	 * @param embalagem the new cadastro
	 */
	public void setEmbalagem(List<Embalagem> embalagem)
	{
		this.embalagem = embalagem;
	}

	/**
	 * Gets the criteria.
	 * 
	 * @return the criteria
	 */
	public List<Criteria> getCriteria()
	{
		return criteria;
	}

	/**
	 * Sets the criteria.
	 * 
	 * @param criteria the new criteria
	 */
	public void setCriteria(List<Criteria> criteria)
	{
		this.criteria = criteria;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "EmbalagemResponse [getEmbalagem()=" + getEmbalagem() + ", getCriteria()=" + getCriteria()
				+ ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", toString()=" + super.toString() + "]";
	}

}
