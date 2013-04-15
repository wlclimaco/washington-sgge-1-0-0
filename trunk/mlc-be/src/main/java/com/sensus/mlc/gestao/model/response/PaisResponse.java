package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Pais;


public class PaisResponse extends Response
{

    private Integer parentRetry;

    private List<Pais> pais ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Pais> getPais() {
		return pais;
	}

	public void setPais(List<Pais> pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "PaisResponse [parentRetry=" + parentRetry + ", pais=" + pais
				+ "]";
	}


}
