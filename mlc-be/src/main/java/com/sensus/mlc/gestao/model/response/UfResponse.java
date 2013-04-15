package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Uf;


public class UfResponse extends Response
{

    private Integer parentRetry;

    private List<Uf> uf ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Uf> getUf() {
		return uf;
	}

	public void setUf(List<Uf> uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "UfResponse [parentRetry=" + parentRetry + ", uf=" + uf
				+ ", getParentRetry()=" + getParentRetry() + ", getUf()="
				+ getUf() + "]";
	}


}
