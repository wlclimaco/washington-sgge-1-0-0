package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Titulares;


public class TitularesResponse extends Response
{

    private Integer parentRetry;

    private List<Titulares> titulares ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Titulares> getTitulares() {
		return titulares;
	}

	public void setTitulares(List<Titulares> titulares) {
		this.titulares = titulares;
	}

	@Override
	public String toString() {
		return "TitularesResponse [parentRetry=" + parentRetry + ", titulares="
				+ titulares + ", getParentRetry()=" + getParentRetry()
				+ ", getTitulares()=" + getTitulares() + "]";
	}


}
