package com.sensus.mlc.tabela.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.tabela.model.Atributos;


// TODO: Auto-generated Javadoc
/**
 * The Class InquiryAtributosRequest.
 */
public class InquiryAtributosRequest extends InquiryPaginationRequest
{

    /** The base search. */
    private BaseSearch baseSearch;

    /** The file name. */
    private String fileName;

    /** The process id. */
    Integer processId;

    /** The atributos. */
    private List<Atributos> atributos;

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
	 * Gets the atributos.
	 *
	 * @return the atributos
	 */
	public List<Atributos> getAtributos() {
		return atributos;
	}

	/**
	 * Sets the atributos.
	 *
	 * @param atributos the new atributos
	 */
	public void setAtributos(List<Atributos> atributos) {
		this.atributos = atributos;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString() {
		return "InquiryAtributosRequest [baseSearch=" + baseSearch
				+ ", fileName=" + fileName + ", processId=" + processId
				+ ", atributos=" + atributos + ", getBaseSearch()="
				+ getBaseSearch() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", getAtributos()="
				+ getAtributos() + "]";
	}


}
