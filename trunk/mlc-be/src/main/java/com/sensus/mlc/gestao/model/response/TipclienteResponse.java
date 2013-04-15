package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Tipcliente;


public class TipclienteResponse extends Response
{

    private Integer parentRetry;

    private List<Tipcliente> tipcliente ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Tipcliente> getTipcliente() {
		return tipcliente;
	}

	public void setTipcliente(List<Tipcliente> tipcliente) {
		this.tipcliente = tipcliente;
	}

	@Override
	public String toString() {
		return "TipclienteResponse [parentRetry=" + parentRetry
				+ ", tipcliente=" + tipcliente + ", getParentRetry()="
				+ getParentRetry() + ", getTipcliente()=" + getTipcliente()
				+ "]";
	}
    
}
