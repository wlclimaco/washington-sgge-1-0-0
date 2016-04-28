package com.qat.samples.sysmgmt.historico.model;

import java.util.Date;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class HistoricoItens extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The bairro. */
	private Integer idHist;

	/** The numero. */
	private Integer processId;




	public HistoricoItens() {
		super();
	}

	public HistoricoItens(Integer id, Integer idHist, Integer processId) {
		super();
		this.id = id;
		this.idHist = idHist;
		this.processId = processId;
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the idHist
	 */
	public Integer getIdHist()
	{
		return idHist;
	}

	/**
	 * @param idHist the idHist to set
	 */
	public void setIdHist(Integer idHist)
	{
		this.idHist = idHist;
	}

	/**
	 * @return the processId
	 */
	@Override
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	@Override
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	@Override
	public String toString()
	{
		return "HistoricoItens [getId()=" + getId() + ", getIdHist()=" + getIdHist() + ", getProcessId()="
				+ getProcessId() + ", toString()=" + super.toString() + "]";
	}

}
