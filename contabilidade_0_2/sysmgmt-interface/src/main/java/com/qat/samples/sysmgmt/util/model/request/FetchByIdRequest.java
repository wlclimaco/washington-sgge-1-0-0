package com.qat.samples.sysmgmt.util.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.entidade.model.Transaction;

/**
 * The Class FetchByIdRequest.
 */
public class FetchByIdRequest extends Request
{

	/** The fetch id. */
	@XmlElement(nillable = true)
	private Integer fetchId;


	@XmlElement(nillable = true)
	private Transaction transaction;

	@XmlElement(nillable = true)
	private Integer emprId;

	/**
	 * Instantiates a new fetch by id request.
	 *
	 * @param fetchId the fetch id
	 */
	public FetchByIdRequest(Integer fetchId)
	{
		this.fetchId = fetchId;
	}

	/**
	 * Instantiates a new fetch by id request.
	 */
	public FetchByIdRequest()
	{
	}

	/**
	 * Gets the fetch id.
	 *
	 * @return the fetch id
	 */
	public Integer getFetchId()
	{
		return fetchId;
	}

	/**
	 * Sets the fetch id.
	 *
	 * @param fetchId the new fetch id
	 */
	public void setFetchId(Integer fetchId)
	{
		this.fetchId = fetchId;
	}



	public Integer getEmprId() {
		return emprId;
	}

	public void setEmprId(Integer emprId) {
		this.emprId = emprId;
	}


	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "FetchByIdRequest [getFetchId()=" + getFetchId() + ", getEmprId()=" + getEmprId() + ", getTransaction()="
				+ getTransaction() + ", toString()=" + super.toString() + "]";
	}

}
