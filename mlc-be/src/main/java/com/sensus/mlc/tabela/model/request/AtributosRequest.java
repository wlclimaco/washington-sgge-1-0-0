package com.sensus.mlc.tabela.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Atributos;


public class AtributosRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Atributos  atributos;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Atributos getAtributos() {
		return atributos;
	}

	public void setAtributos(Atributos atributos) {
		this.atributos = atributos;
	}

	@Override
	public String toString() {
		return "AtributosRequest [parentRetry=" + parentRetry + ", atributos="
				+ atributos + ", getParentRetry()=" + getParentRetry()
				+ ", getAtributos()=" + getAtributos() + "]";
	}


}
