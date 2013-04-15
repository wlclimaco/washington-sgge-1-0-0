package com.sensus.mlc.gestao.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.gestao.model.Filial;


public class InquiryFilialRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;

    private String fileName;

    Integer processId;

    private List<Filial> filial;

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

	public List<Filial> getFilial() {
		return filial;
	}

	public void setFilial(List<Filial> filial) {
		this.filial = filial;
	}

	@Override
	public String toString() {
		return "InquiryFilialRequest [baseSearch=" + baseSearch + ", fileName="
				+ fileName + ", processId=" + processId + ", filial=" + filial
				+ ", getBaseSearch()=" + getBaseSearch() + ", getFileName()="
				+ getFileName() + ", getProcessId()=" + getProcessId()
				+ ", getFilial()=" + getFilial() + "]";
	}


}
