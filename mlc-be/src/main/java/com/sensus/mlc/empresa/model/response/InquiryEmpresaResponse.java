package com.sensus.mlc.empresa.model.response;

import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.empresa.model.Empresa;


// TODO: Auto-generated Javadoc
/**
 * The Class InquiryActionResponse.
 *
 * @author - QAT Brazil.
 */
public class InquiryEmpresaResponse extends InquiryResponse
{

	/** The actions. */
	private List<Empresa> empresa;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public List<Empresa> getEmpresa()
	{
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(List<Empresa> empresa)
	{
		this.empresa = empresa;
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
		return "InquiryEmpresaResponse [empresa=" + empresa + ", processId="
				+ processId + ", getEmpresa()=" + getEmpresa()
				+ ", getProcessId()=" + getProcessId() + "]";
	}









}
