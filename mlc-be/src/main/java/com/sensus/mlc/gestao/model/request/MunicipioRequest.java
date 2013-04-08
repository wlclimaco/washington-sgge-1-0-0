package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Municipio;


public class MunicipioRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Municipio  municipio;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Override
	public String toString() {
		return "MunicipioRequest [parentRetry=" + parentRetry + ", municipio="
				+ municipio + ", getParentRetry()=" + getParentRetry()
				+ ", getMunicipio()=" + getMunicipio() + "]";
	}


}
