package com.sensus.mlc.endereco.model.response;

import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.endereco.model.Endereco;


// TODO: Auto-generated Javadoc
/**
 * The Class InquiryActionResponse.
 *
 * @author - QAT Brazil.
 */
public class InquiryEnderecoResponse extends InquiryResponse
{

	/** The actions. */
	private List<Endereco> enderecos;

	/** The process id. */
	private Integer processId;




	/**
	 * Gets the enderecos.
	 *
	 * @return the enderecos
	 */
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	/**
	 * Sets the enderecos.
	 *
	 * @param enderecos the new enderecos
	 */
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
		return "InquiryEnderecoResponse [enderecos=" + enderecos
				+ ", processId=" + processId + ", getEnderecos()="
				+ getEnderecos() + ", getProcessId()=" + getProcessId() + "]";
	}







}
