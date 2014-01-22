package com.sensus.lc.pessoa.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.lc.pessoa.model.Pessoa;

/**
 * The Class PessoaResponse.
 * 
 * @author - Washington
 */
public class PessoaResponse extends Response
{

	/** The pessoas. */
	@XmlElement(nillable = true)
	private List<Pessoa> pessoas;

	/** The is pessoa name unique. */
	private Boolean isPessoaNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the pessoas.
	 * 
	 * @return the pessoas
	 */
	public List<Pessoa> getPessoas()
	{
		return pessoas;
	}

	/**
	 * Sets the pessoas.
	 * 
	 * @param pessoaObjects the new pessoas
	 */
	public void setPessoas(List<Pessoa> pessoaObjects)
	{
		pessoas = pessoaObjects;
	}

	/**
	 * Gets the checks if is pessoa name unique.
	 * 
	 * @return the checks if is pessoa name unique
	 */
	public Boolean getIsPessoaNameUnique()
	{
		return isPessoaNameUnique;
	}

	/**
	 * Sets the checks if is pessoa name unique.
	 * 
	 * @param isPessoaNameUnique the new checks if is pessoa name unique
	 */
	public void setIsPessoaNameUnique(Boolean isPessoaNameUnique)
	{
		this.isPessoaNameUnique = isPessoaNameUnique;
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
	public String toString()
	{
		return "PessoaResponse [getPessoas()=" + getPessoas() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsPessoaNameUnique()="
				+ getIsPessoaNameUnique() + "]";
	}

}
