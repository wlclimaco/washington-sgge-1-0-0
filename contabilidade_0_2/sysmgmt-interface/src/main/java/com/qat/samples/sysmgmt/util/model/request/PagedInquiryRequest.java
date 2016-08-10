package com.qat.samples.sysmgmt.util.model.request;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.util.model.criteria.ComumCriteria;

/**
 * The Model Object PagedInquiryRequest.
 */
public class PagedInquiryRequest extends InquiryRequest {

	/** The business id. */
	private Integer emprId;

	/** The recipient id. */
	private Integer id;

	private Integer parentId;

	private String string;

	private String userId;

	public PagedInquiryRequest() {

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "PagedInquiryRequest [getEmprId()=" + getEmprId() + ", getParentId()=" + getParentId() + ", getId()="
				+ getId() + ", getString()=" + getString() + ", getUserId()=" + getUserId() + ", toString()="
				+ super.toString() + "]";
	}

}
