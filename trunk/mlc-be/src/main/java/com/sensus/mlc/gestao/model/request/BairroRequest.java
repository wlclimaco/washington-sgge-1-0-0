package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Bairro;


public class BairroRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Bairro  bairro;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	@Override
	public String toString() {
		return "BairroRequest [parentRetry=" + parentRetry + ", bairro="
				+ bairro + ", getParentRetry()=" + getParentRetry()
				+ ", getBairro()=" + getBairro() + "]";
	}


}
