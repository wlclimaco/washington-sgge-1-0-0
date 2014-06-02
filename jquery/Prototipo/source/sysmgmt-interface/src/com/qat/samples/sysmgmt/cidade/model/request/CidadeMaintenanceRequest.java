package com.qat.samples.sysmgmt.cidade.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.cidade.model.Cidade;

/**
 * The Class CidadeMaintenanceRequest.
 */
public class CidadeMaintenanceRequest extends MaintenanceRequest
{

	/** The cidade. */
	@XmlElement(nillable = true)
	private Cidade cidade;

	/**
	 * The return list.
	 * Indicate true to return a list of objects or false not to return any objects
	 * */
	@XmlElement(nillable = true)
	private Boolean returnList;

	/**
	 * The return list paged.
	 * Indicate true to return the list of objects paged or false to return all the objects at once
	 * you must set returnList to true for this to work
	 * */
	@XmlElement(nillable = true)
	private Boolean returnListPaged;

	/**
	 * Instantiates a new cidade maintenance request.
	 * 
	 * @param cidade the cidade
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public CidadeMaintenanceRequest(Cidade cidade, Boolean returnList, Boolean returnListPaged)
	{
		this.cidade = cidade;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new cidade maintenance request.
	 */
	public CidadeMaintenanceRequest()
	{
	}

	/**
	 * Gets the cidade.
	 * 
	 * @return the cidade
	 */
	public Cidade getCidade()
	{
		return cidade;
	}

	/**
	 * Sets the cidade.
	 * 
	 * @param cidade the new cidade
	 */
	public void setCidade(Cidade cidade)
	{
		this.cidade = cidade;
	}

	/**
	 * Gets the return list.
	 * 
	 * @return the return list
	 */
	public Boolean getReturnList()
	{
		return (returnList == null) ? false : returnList;
	}

	/**
	 * Sets the return list.
	 * 
	 * @param returnList the new return list
	 */
	public void setReturnList(Boolean returnList)
	{
		this.returnList = returnList;
	}

	/**
	 * Gets the return list paged.
	 * 
	 * @return the return list paged
	 */
	public Boolean getReturnListPaged()
	{
		return (returnListPaged == null) ? false : returnListPaged;
	}

	/**
	 * Sets the return list paged.
	 * 
	 * @param returnListPaged the new return list paged
	 */
	public void setReturnListPaged(Boolean returnListPaged)
	{
		this.returnListPaged = returnListPaged;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CidadeMaintenanceRequest [getCidade()=" + getCidade() + ", getReturnList()=" + getReturnList()
				+ ", getReturnListPaged()=" + getReturnListPaged() + ", toString()=" + super.toString() + "]";
	}

}
