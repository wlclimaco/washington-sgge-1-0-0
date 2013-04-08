package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Titulares;


public class TitularesRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Titulares  titulares;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Titulares getTitulares() {
		return titulares;
	}

	public void setTitulares(Titulares titulares) {
		this.titulares = titulares;
	}

	@Override
	public String toString() {
		return "TitularesRequest [parentRetry=" + parentRetry + ", titulares="
				+ titulares + ", getParentRetry()=" + getParentRetry()
				+ ", getTitulares()=" + getTitulares() + "]";
	}


}
