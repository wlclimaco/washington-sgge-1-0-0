package com.sensus.mlc.gestao.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.gestao.model.Municipio;


public class InquiryMunicipioRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;

    private String fileName;

    Integer processId;

    private List<Municipio> municipio;

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

	public List<Municipio> getMunicipio() {
		return municipio;
	}

	public void setMunicipio(List<Municipio> municipio) {
		this.municipio = municipio;
	}

	@Override
	public String toString() {
		return "InquiryMunicipioRequest [baseSearch=" + baseSearch
				+ ", fileName=" + fileName + ", processId=" + processId
				+ ", municipio=" + municipio + ", getBaseSearch()="
				+ getBaseSearch() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", getMunicipio()="
				+ getMunicipio() + "]";
	}


}
