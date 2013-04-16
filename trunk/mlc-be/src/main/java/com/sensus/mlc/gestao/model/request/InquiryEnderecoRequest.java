package com.sensus.mlc.gestao.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.gestao.model.Endereco;


public class InquiryEnderecoRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;

    private String fileName;

    Integer processId;

    private List<Endereco> endereco;

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

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "InquiryEnderecoRequest [baseSearch=" + baseSearch
				+ ", fileName=" + fileName + ", processId=" + processId
				+ ", endereco=" + endereco + ", getBaseSearch()="
				+ getBaseSearch() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", getEndereco()="
				+ getEndereco() + "]";
	}




}
