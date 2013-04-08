package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Cnae;


public class CnaeRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Cnae  cnae;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Cnae getCnae() {
		return cnae;
	}

	public void setCnae(Cnae cnae) {
		this.cnae = cnae;
	}

	@Override
	public String toString() {
		return "CnaeRequest [parentRetry=" + parentRetry + ", cnae=" + cnae
				+ ", getParentRetry()=" + getParentRetry() + ", getCnae()="
				+ getCnae() + "]";
	}


}
