package com.qat.samples.sysmgmt.util.model.request;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.util.model.criteria.ComumCriteria;

/**
 * The Model Object PagedInquiryRequest.
 */
public class PagedInquiryRequest extends InquiryRequest
{

	/** The business id. */
	private Integer emprId;

	/** The recipient id. */
	private Integer id;

	private Integer parentId;

	private String string;

	public PagedInquiryRequest()
	{


	}






	public Integer getEmprId() {
		return emprId;
	}






	public void setEmprId(Integer emprId) {
		this.emprId = emprId;
	}

	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}






	public Integer getId() {
		return id;
	}






	public void setId(Integer id) {
		this.id = id;
	}






	public String getString() {
		return string;
	}






	public void setString(String string) {
		this.string = string;
	}






	@Override
	public String toString() {
		return "PagedInquiryRequest [getEmprId()=" + getEmprId() + ", getId()=" + getId() + ", getParentId()="
				+ getParentId() + ", getString()=" + getString() + ", toString()=" + super.toString() + "]";
	}

}
