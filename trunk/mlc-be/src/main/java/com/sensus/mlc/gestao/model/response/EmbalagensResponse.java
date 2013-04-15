package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Embalagens;


public class EmbalagensResponse extends Response
{

    private Integer parentRetry;

    private List<Embalagens> embalagens ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Embalagens> getEmbalagens() {
		return embalagens;
	}

	public void setEmbalagens(List<Embalagens> embalagens) {
		this.embalagens = embalagens;
	}

	@Override
	public String toString() {
		return "EmbalagensResponse [parentRetry=" + parentRetry
				+ ", embalagens=" + embalagens + ", getParentRetry()="
				+ getParentRetry() + ", getEmbalagens()=" + getEmbalagens()
				+ "]";
	}


}
