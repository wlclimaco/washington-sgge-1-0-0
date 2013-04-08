package com.sensus.mlc.tabela.model.request;

import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.tabela.model.Tabela;


// TODO: Auto-generated Javadoc
/**
 * The Class InquiryTabelaRequest.
 */
public class InquiryTabelaRequest extends InquiryPaginationRequest
{

    /** The base search. */
    private BaseSearch baseSearch;

    /** The file name. */
    private String fileName;

    /** The process id. */
    Integer processId;

    /** The tabela. */
    private List<Tabela> tabela;

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
	 * Gets the tabela.
	 *
	 * @return the tabela
	 */
	public List<Tabela> getTabela() {
		return tabela;
	}

	/**
	 * Sets the tabela.
	 *
	 * @param tabela the new tabela
	 */
	public void setTabela(List<Tabela> tabela) {
		this.tabela = tabela;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString() {
		return "InquiryTabelaRequest [baseSearch=" + baseSearch + ", fileName="
				+ fileName + ", processId=" + processId + ", tabela=" + tabela
				+ ", getBaseSearch()=" + getBaseSearch() + ", getFileName()="
				+ getFileName() + ", getProcessId()=" + getProcessId()
				+ ", getTabela()=" + getTabela() + "]";
	}

}