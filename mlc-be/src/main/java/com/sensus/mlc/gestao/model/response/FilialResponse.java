package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Filial;


public class FilialResponse extends Response
{

    private Integer parentRetry;

    private List<Filial> filial ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Filial> getFilial() {
		return filial;
	}

	public void setFilial(List<Filial> filial) {
		this.filial = filial;
	}

	@Override
	public String toString() {
		return "FilialResponse [parentRetry=" + parentRetry + ", filial="
				+ filial + ", getParentRetry()=" + getParentRetry()
				+ ", getFilial()=" + getFilial() + "]";
	}


}
