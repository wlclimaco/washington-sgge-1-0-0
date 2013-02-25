package com.sensus.mlc.almoxarifado.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.almoxarifado.model.Almoxarifado;
import com.sensus.mlc.filial.model.Filial;


/**
 * The Class ActionResponse.
 *
 * @author - QAT Brazil.
 */
public class AlmoxarifadoResponse extends Response
{

	/** The actions. */
	private List<Almoxarifado> almoxarifado;

	public List<Almoxarifado> getAlmoxarifado() {
		return almoxarifado;
	}

	public void setAlmoxarifado(List<Almoxarifado> almoxarifado) {
		this.almoxarifado = almoxarifado;
	}

	@Override
	public String toString() {
		return "AlmoxarifadoResponse [almoxarifado=" + almoxarifado
				+ ", getAlmoxarifado()=" + getAlmoxarifado() + "]";
	}

}
