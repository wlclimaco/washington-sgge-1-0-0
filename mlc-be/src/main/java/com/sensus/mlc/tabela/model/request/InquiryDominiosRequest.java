package com.sensus.mlc.tabela.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.tabela.model.Dominios;


// TODO: Auto-generated Javadoc
/**
 * The Class InquiryDominiosRequest.
 */
public class InquiryDominiosRequest extends InquiryPaginationRequest
{

    /** The base search. */
    private BaseSearch baseSearch;

    /** The file name. */
    private String fileName;

    /** The process id. */
    Integer processId;

    /** The dominios. */
    private List<Dominios> dominios;

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
	 * Gets the dominios.
	 *
	 * @return the dominios
	 */
	public List<Dominios> getDominios() {
		return dominios;
	}

	/**
	 * Sets the dominios.
	 *
	 * @param dominios the new dominios
	 */
	public void setDominios(List<Dominios> dominios) {
		this.dominios = dominios;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString() {
		return "InquiryDominiosRequest [baseSearch=" + baseSearch
				+ ", fileName=" + fileName + ", processId=" + processId
				+ ", dominios=" + dominios + ", getBaseSearch()="
				+ getBaseSearch() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", getDominios()="
				+ getDominios() + "]";
	}


}
