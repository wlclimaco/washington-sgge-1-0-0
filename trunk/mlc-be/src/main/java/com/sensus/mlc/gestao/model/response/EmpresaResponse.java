package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Empresa;


public class EmpresaResponse extends Response
{

    private Integer parentRetry;

    private List<Empresa> empresa ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Empresa> getEmpresa() {
		return empresa;
	}

	public void setEmpresa(List<Empresa> empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "EmpresaResponse [parentRetry=" + parentRetry + ", empresa="
				+ empresa + ", getParentRetry()=" + getParentRetry()
				+ ", getEmpresa()=" + getEmpresa() + "]";
	}


}
