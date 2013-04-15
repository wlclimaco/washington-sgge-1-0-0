package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Classcliente;


public class ClassclienteResponse extends Response
{

    private Integer parentRetry;

    private List<Classcliente> classcliente ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Classcliente> getClasscliente() {
		return classcliente;
	}

	public void setClasscliente(List<Classcliente> classcliente) {
		this.classcliente = classcliente;
	}

	@Override
	public String toString() {
		return "ClassclienteResponse [parentRetry=" + parentRetry
				+ ", classcliente=" + classcliente + ", getParentRetry()="
				+ getParentRetry() + ", getClasscliente()=" + getClasscliente()
				+ "]";
	}


}
