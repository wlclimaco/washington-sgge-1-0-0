package com.sensus.mlc.gestao.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.gestao.model.Empresa;


public class InquiryEmpresaRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;

    private String fileName;

    Integer processId;

    private List<Empresa> empresa;

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

	public List<Empresa> getEmpresa() {
		return empresa;
	}

	public void setEmpresa(List<Empresa> empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "InquiryEmpresaRequest [baseSearch=" + baseSearch
				+ ", fileName=" + fileName + ", processId=" + processId
				+ ", empresa=" + empresa + ", getBaseSearch()="
				+ getBaseSearch() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", getEmpresa()="
				+ getEmpresa() + "]";
	}


}
