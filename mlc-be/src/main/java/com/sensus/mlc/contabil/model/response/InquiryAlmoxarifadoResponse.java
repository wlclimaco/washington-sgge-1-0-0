package com.sensus.mlc.contabil.model.response;

import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.almoxarifado.model.Almoxarifado;
import com.sensus.mlc.filial.model.Filial;


/**
 * The Class InquiryActionResponse.
 * 
 * @author - QAT Brazil.
 */
public class InquiryAlmoxarifadoResponse extends InquiryResponse
{

	/** The actions. */
	private List<Almoxarifado> almoxarifado;

	/** The process id. */
	private Integer processId;



	public List<Almoxarifado> getAlmoxarifado() {
		return almoxarifado;
	}

	public void setAlmoxarifado(List<Almoxarifado> almoxarifado) {
		this.almoxarifado = almoxarifado;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	@Override
	public String toString() {
		return "InquiryAlmoxarifadoResponse [almoxarifado=" + almoxarifado
				+ ", processId=" + processId + ", getAlmoxarifado()="
				+ getAlmoxarifado() + ", getProcessId()=" + getProcessId()
				+ "]";
	}



}
