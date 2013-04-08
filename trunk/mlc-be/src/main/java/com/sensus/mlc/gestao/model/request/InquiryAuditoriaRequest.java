package com.sensus.mlc.gestao.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.gestao.model.Auditoria;


// TODO: Auto-generated Javadoc
/**
 * The Class InquiryAuditoriaRequest.
 */
public class InquiryAuditoriaRequest extends InquiryPaginationRequest
{

    /** The base search. */
    private BaseSearch baseSearch;

    /** The file name. */
    private String fileName;

    /** The process id. */
    Integer processId;

    /** The auditoria. */
    private List<Auditoria> auditoria;

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
	 * Gets the auditoria.
	 *
	 * @return the auditoria
	 */
	public List<Auditoria> getAuditoria() {
		return auditoria;
	}

	/**
	 * Sets the auditoria.
	 *
	 * @param auditoria the new auditoria
	 */
	public void setAuditoria(List<Auditoria> auditoria) {
		this.auditoria = auditoria;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString() {
		return "InquiryAuditoriaRequest [baseSearch=" + baseSearch
				+ ", fileName=" + fileName + ", processId=" + processId
				+ ", auditoria=" + auditoria + ", getBaseSearch()="
				+ getBaseSearch() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", getAuditoria()="
				+ getAuditoria() + "]";
	}


}
