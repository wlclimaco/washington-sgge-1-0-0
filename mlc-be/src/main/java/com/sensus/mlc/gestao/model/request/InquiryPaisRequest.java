package com.sensus.mlc.gestao.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.gestao.model.Pais;


public class InquiryPaisRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;

    private String fileName;

    Integer processId;

    private List<Pais> pais;

	public BaseSearch getBaseSearch() {
		return baseSearch;
	}

	public void setBaseSearch(BaseSearch baseSearch) {
		this.baseSearch = baseSearch;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public List<Pais> getPais() {
		return pais;
	}

	public void setPais(List<Pais> pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "InquiryPaisRequest [baseSearch=" + baseSearch + ", fileName="
				+ fileName + ", processId=" + processId + ", pais=" + pais
				+ ", getBaseSearch()=" + getBaseSearch() + ", getFileName()="
				+ getFileName() + ", getProcessId()=" + getProcessId()
				+ ", getPais()=" + getPais() + "]";
	}


}
