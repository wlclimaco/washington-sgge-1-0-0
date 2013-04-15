package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Bairro;


public class BairroResponse extends Response
{

    private Integer parentRetry;

    private List<Bairro> bairro ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Bairro> getBairro() {
		return bairro;
	}

	public void setBairro(List<Bairro> bairro) {
		this.bairro = bairro;
	}

	@Override
	public String toString() {
		return "BairroResponse [parentRetry=" + parentRetry + ", bairro="
				+ bairro + ", getParentRetry()=" + getParentRetry()
				+ ", getBairro()=" + getBairro() + "]";
	}


}
