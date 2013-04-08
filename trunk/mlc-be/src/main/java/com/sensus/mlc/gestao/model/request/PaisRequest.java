package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Pais;


public class PaisRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Pais  pais;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "PaisRequest [parentRetry=" + parentRetry + ", pais=" + pais
				+ ", getParentRetry()=" + getParentRetry() + ", getPais()="
				+ getPais() + "]";
	}


}
