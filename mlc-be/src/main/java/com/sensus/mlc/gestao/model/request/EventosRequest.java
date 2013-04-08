package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Eventos;


public class EventosRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Eventos  eventos;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Eventos getEventos() {
		return eventos;
	}

	public void setEventos(Eventos eventos) {
		this.eventos = eventos;
	}

	@Override
	public String toString() {
		return "EventosRequest [parentRetry=" + parentRetry + ", eventos="
				+ eventos + ", getParentRetry()=" + getParentRetry()
				+ ", getEventos()=" + getEventos() + "]";
	}


}
