package com.sensus.mlc.gestao.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.gestao.model.Bairro;


public class InquiryBairroRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;

    private String fileName;

    Integer processId;

    private List<Bairro> bairro;

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

	public List<Bairro> getBairro() {
		return bairro;
	}

	public void setBairro(List<Bairro> bairro) {
		this.bairro = bairro;
	}

	@Override
	public String toString() {
		return "InquiryBairroRequest [baseSearch=" + baseSearch + ", fileName="
				+ fileName + ", processId=" + processId + ", bairro=" + bairro
				+ ", getBaseSearch()=" + getBaseSearch() + ", getFileName()="
				+ getFileName() + ", getProcessId()=" + getProcessId()
				+ ", getBairro()=" + getBairro() + "]";
	}


}
