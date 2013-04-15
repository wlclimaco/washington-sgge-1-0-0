package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Empresa;


public class EmpresaRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Empresa  empresa;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "EmpresaRequest [parentRetry=" + parentRetry + ", empresa="
				+ empresa + ", getParentRetry()=" + getParentRetry()
				+ ", getEmpresa()=" + getEmpresa() + "]";
	}


}
