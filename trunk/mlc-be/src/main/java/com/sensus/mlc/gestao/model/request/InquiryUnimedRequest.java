package com.sensus.mlc.gestao.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.gestao.model.Unimed;


// TODO: Auto-generated Javadoc
/**
 * The Class InquiryUnimedRequest.
 */
public class InquiryUnimedRequest extends InquiryPaginationRequest
{

    /** The base search. */
    private BaseSearch baseSearch;

    /** The file name. */
    private String fileName;

    /** The process id. */
    Integer processId;

    /** The unimed. */
    private List<Unimed> unimed;

	/**
	 * Gets the base search.
	 *
	 * @return the base search
	 */
	public BaseSearch getBaseSearch() {
		return baseSearch;
	}

	/**
	 * Sets the base search.
	 *
	 * @param baseSearch the new base search
	 */
	public void setBaseSearch(BaseSearch baseSearch) {
		this.baseSearch = baseSearch;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#getFileName()
	 */
	public String getFileName() {
		return fileName;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#setFileName(java.lang.String)
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#getProcessId()
	 */
	public Integer getProcessId() {
		return processId;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#setProcessId(java.lang.Integer)
	 */
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	/**
	 * Gets the unimed.
	 *
	 * @return the unimed
	 */
	public List<Unimed> getUnimed() {
		return unimed;
	}

	/**
	 * Sets the unimed.
	 *
	 * @param unimed the new unimed
	 */
	public void setUnimed(List<Unimed> unimed) {
		this.unimed = unimed;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString() {
		return "InquiryUnimedRequest [baseSearch=" + baseSearch + ", fileName="
				+ fileName + ", processId=" + processId + ", unimed=" + unimed
				+ ", getBaseSearch()=" + getBaseSearch() + ", getFileName()="
				+ getFileName() + ", getProcessId()=" + getProcessId()
				+ ", getUnimed()=" + getUnimed() + "]";
	}


}
