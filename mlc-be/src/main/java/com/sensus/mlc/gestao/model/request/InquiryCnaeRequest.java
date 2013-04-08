package com.sensus.mlc.gestao.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.gestao.model.Cnae;


public class InquiryCnaeRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;

    private String fileName;

    Integer processId;

    private List<Cnae> cnae;

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

	public List<Cnae> getCnae() {
		return cnae;
	}

	public void setCnae(List<Cnae> cnae) {
		this.cnae = cnae;
	}

	@Override
	public String toString() {
		return "InquiryCnaeRequest [baseSearch=" + baseSearch + ", fileName="
				+ fileName + ", processId=" + processId + ", cnae=" + cnae
				+ ", getBaseSearch()=" + getBaseSearch() + ", getFileName()="
				+ getFileName() + ", getProcessId()=" + getProcessId()
				+ ", getCnae()=" + getCnae() + "]";
	}


}
