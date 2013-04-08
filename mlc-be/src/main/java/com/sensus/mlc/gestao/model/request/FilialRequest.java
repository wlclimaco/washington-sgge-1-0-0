package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.filial.model.Filial;


public class FilialRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Filial  filial;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	@Override
	public String toString() {
		return "FilialRequest [parentRetry=" + parentRetry + ", filial="
				+ filial + ", getParentRetry()=" + getParentRetry()
				+ ", getFilial()=" + getFilial() + "]";
	}


}
