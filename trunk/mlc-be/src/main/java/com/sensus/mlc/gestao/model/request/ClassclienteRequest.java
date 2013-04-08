package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Classcliente;


public class ClassclienteRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Classcliente  classcliente;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Classcliente getClasscliente() {
		return classcliente;
	}

	public void setClasscliente(Classcliente classcliente) {
		this.classcliente = classcliente;
	}

	@Override
	public String toString() {
		return "ClassclienteRequest [parentRetry=" + parentRetry
				+ ", classcliente=" + classcliente + "]";
	}


}
