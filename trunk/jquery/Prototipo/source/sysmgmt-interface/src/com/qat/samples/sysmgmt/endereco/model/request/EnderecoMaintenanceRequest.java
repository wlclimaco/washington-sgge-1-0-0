package com.qat.samples.sysmgmt.endereco.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.endereco.model.Endereco;

/**
 * The Class EnderecoMaintenanceRequest.
 */
public class EnderecoMaintenanceRequest extends MaintenanceRequest
{

	/** The endereco. */
	@XmlElement(nillable = true)
	private Endereco endereco;

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
	 * Instantiates a new endereco maintenance request.
	 * 
	 * @param endereco the endereco
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public EnderecoMaintenanceRequest(Endereco endereco, Boolean returnList, Boolean returnListPaged)
	{
		this.endereco = endereco;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new endereco maintenance request.
	 */
	public EnderecoMaintenanceRequest()
	{
	}

	/**
	 * Gets the endereco.
	 * 
	 * @return the endereco
	 */
	public Endereco getEndereco()
	{
		return endereco;
	}

	/**
	 * Sets the endereco.
	 * 
	 * @param endereco the new endereco
	 */
	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
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
		return "EnderecoMaintenanceRequest [getEndereco()=" + getEndereco() + ", getReturnList()=" + getReturnList()
				+ ", getReturnListPaged()=" + getReturnListPaged()
				+ ", getUserContext()=" + getUserContext() + "]";
	}

}
