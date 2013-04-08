package com.sensus.mlc.gestao.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.gestao.model.Tipcliente;


public class InquiryTipclienteRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;

    private String fileName;

    Integer processId;

    private List<Tipcliente> tipcliente;

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

	public List<Tipcliente> getTipcliente() {
		return tipcliente;
	}

	public void setTipcliente(List<Tipcliente> tipcliente) {
		this.tipcliente = tipcliente;
	}

	@Override
	public String toString() {
		return "InquiryTipclienteRequest [baseSearch=" + baseSearch
				+ ", fileName=" + fileName + ", processId=" + processId
				+ ", tipcliente=" + tipcliente + ", getBaseSearch()="
				+ getBaseSearch() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", getTipcliente()="
				+ getTipcliente() + "]";
	}


}
