package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.OrdemServico;

/**
 * The Class OrdemServicoMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 2:15:09 PM
 */
public class OrdemServicoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private OrdemServico member;

	/**
	 * The Constructor.
	 */
	public OrdemServicoMaintenanceRequest()
	{

	}

	/**
	 * Gets the member.
	 *
	 * @return the member
	 */
	public OrdemServico getOrdemServico()
	{
		return member;
	}

	/**
	 * Sets the member.
	 *
	 * @param member the member
	 */
	public void setOrdemServico(OrdemServico member)
	{
		this.member = member;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OrdemServicoMaintenanceRequest [getOrdemServico()=" + getOrdemServico() + ", getUserContext()="
				+ getUserContext() + "]";
	}
}