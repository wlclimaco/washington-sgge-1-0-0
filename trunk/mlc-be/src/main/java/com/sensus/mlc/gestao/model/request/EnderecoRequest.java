package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Endereco;


public class EnderecoRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Endereco  endereco;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "EnderecoRequest [parentRetry=" + parentRetry + ", endereco="
				+ endereco + ", getParentRetry()=" + getParentRetry()
				+ ", getEndereco()=" + getEndereco() + "]";
	}


}
