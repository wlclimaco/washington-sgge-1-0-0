package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Tipcliente;


public class TipclienteRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Tipcliente  tipcliente;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Tipcliente getTipcliente() {
		return tipcliente;
	}

	public void setTipcliente(Tipcliente tipcliente) {
		this.tipcliente = tipcliente;
	}

	@Override
	public String toString() {
		return "TipclienteRequest [parentRetry=" + parentRetry
				+ ", tipcliente=" + tipcliente + ", getParentRetry()="
				+ getParentRetry() + ", getTipcliente()=" + getTipcliente()
				+ "]";
	}


}
