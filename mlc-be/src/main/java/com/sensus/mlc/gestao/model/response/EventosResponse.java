package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Eventos;


// TODO: Auto-generated Javadoc
/**
 * The Class EventosResponse.
 */
public class EventosResponse extends Response
{

    /** The parent retry. */
    private Integer parentRetry;

    /** The eventos. */
    private List<Eventos> eventos ;

	/**
	 * Gets the parent retry.
	 *
	 * @return the parent retry
	 */
	public Integer getParentRetry() {
		return parentRetry;
	}

	/**
	 * Sets the parent retry.
	 *
	 * @param parentRetry the new parent retry
	 */
	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	/**
	 * Gets the eventos.
	 *
	 * @return the eventos
	 */
	public List<Eventos> getEventos() {
		return eventos;
	}

	/**
	 * Sets the eventos.
	 *
	 * @param eventos the new eventos
	 */
	public void setEventos(List<Eventos> eventos) {
		this.eventos = eventos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventosResponse [parentRetry=" + parentRetry + ", eventos="
				+ eventos + ", getParentRetry()=" + getParentRetry()
				+ ", getEventos()=" + getEventos() + "]";
	}




}
