package com.sensus.mlc.gestao.model.request;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.gestao.model.Embalagens;


public class InquiryEmbalagensRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;

    private String fileName;

    Integer processId;

    private List<Embalagens> embalagens;

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

	public List<Embalagens> getEmbalagens() {
		return embalagens;
	}

	public void setEmbalagens(List<Embalagens> embalagens) {
		this.embalagens = embalagens;
	}

	@Override
	public String toString() {
		return "InquiryEmbalagensRequest [baseSearch=" + baseSearch
				+ ", fileName=" + fileName + ", processId=" + processId
				+ ", embalagens=" + embalagens + ", getBaseSearch()="
				+ getBaseSearch() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", getEmbalagens()="
				+ getEmbalagens() + "]";
	}


}
