package com.qat.samples.sysmgmt.produto.model.request;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.Embalagem;
import com.qat.samples.sysmgmt.util.Criteria;

/**
 * The Class EmbalagemMaintenanceRequest.
 */
public class EmbalagemMaintenanceRequest extends MaintenanceRequest
{

	/** The embalagem. */
	@XmlElement(nillable = true)
	private Embalagem embalagem;

	/** The criteria. */
	private List<Criteria> criteria;

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
	 * Instantiates a new embalagem maintenance request.
	 * 
	 * @param embalagem the embalagem
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public EmbalagemMaintenanceRequest(Embalagem embalagem, Boolean returnList, Boolean returnListPaged)
	{
		this.embalagem = embalagem;
		this.returnList = returnList;
		this.returnListPaged = returnListPaged;
	}

	/**
	 * Instantiates a new embalagem maintenance request.
	 */
	public EmbalagemMaintenanceRequest()
	{
	}

	/**
	 * Gets the embalagem.
	 * 
	 * @return the embalagem
	 */
	public Embalagem getEmbalagem()
	{
		return embalagem;
	}

	/**
	 * Sets the embalagem.
	 * 
	 * @param embalagem the new embalagem
	 */
	public void setEmbalagem(Embalagem embalagem)
	{
		this.embalagem = embalagem;
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
		return "EmbalagemMaintenanceRequest [getEmbalagem()=" + getEmbalagem() + ", getReturnList()=" + getReturnList()
				+ ", getReturnListPaged()=" + getReturnListPaged() + ", getCriteria()=" + getCriteria()
				+ ", toString()=" + super.toString() + "]";
	}

}
