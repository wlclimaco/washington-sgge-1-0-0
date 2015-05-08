package com.prosperitasglobal.sendsolv.model.request;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.criteria.InquiryCriteria;
import com.qat.framework.model.request.InquiryRequest;

/**
 * The Class PagedInquiryRequest.
 */
public class PagedInquiryRequest extends InquiryRequest
{
	/** Attributes. */
	@XmlElement(nillable = true)
	private Integer parentId;

	/** The inquiry criteria. */
	private InquiryCriteria inquiryCriteria;

	/** The status list. */
	private List<StatusEnum> statusList = new ArrayList<StatusEnum>();

	/**
	 * The Constructor.
	 */
	public PagedInquiryRequest()
	{

	}

	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	public Integer getParentId()
	{
		return parentId;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentId the parent id
	 */
	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	/**
	 * Gets the inquiry criteria.
	 *
	 * @return the inquiry criteria
	 */
	public InquiryCriteria getInquiryCriteria()
	{
		return inquiryCriteria;
	}

	/**
	 * Sets the inquiry criteria.
	 *
	 * @param inquiryCriteria the inquiry criteria
	 */
	public void setInquiryCriteria(InquiryCriteria inquiryCriteria)
	{
		this.inquiryCriteria = inquiryCriteria;
	}

	/**
	 * Gets the status list.
	 *
	 * @return the status list
	 */
	public List<StatusEnum> getStatusList()
	{
		return statusList;
	}

	/**
	 * Sets the status list.
	 *
	 * @param statusList the status list
	 */
	public void setStatusList(List<StatusEnum> statusList)
	{
		this.statusList = statusList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PagedInquiryRequest [getParentId()=" + getParentId() + ", getInquiryCriteria()=" + getInquiryCriteria()
				+ ", getStatusList()=" + getStatusList() + ", getPageSize()=" + getPageSize() + ", getStartPage()="
				+ getStartPage() + ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()="
				+ getSortExpression() + ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()="
				+ getMaxPreQueryCount() + ", getUserContext()=" + getUserContext() + "]";
	}
}