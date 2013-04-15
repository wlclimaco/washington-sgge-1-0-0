package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Endereco;


public class EnderecoResponse extends Response
{

    private Integer parentRetry;

    private List<Endereco> endereco ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "EnderecoResponse [parentRetry=" + parentRetry + ", endereco="
				+ endereco + ", getParentRetry()=" + getParentRetry()
				+ ", getEndereco()=" + getEndereco() + "]";
	}


}
