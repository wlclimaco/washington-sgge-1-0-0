package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Tabela;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:49 AM
 */
public class TabelaMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private Tabela tabela;

	/**
	 * The Constructor.
	 */
	public TabelaMaintenanceRequest()
	{

	}

	/**
	 * Gets the tabela.
	 *
	 * @return the tabela
	 */
	public Tabela getTabela()
	{
		return tabela;
	}

	/**
	 * Sets the tabela.
	 *
	 * @param tabela the tabela to set
	 */
	public void setTabela(Tabela tabela)
	{
		this.tabela = tabela;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TabelaMaintenanceRequest [getTabela()=" + getTabela() + ", getUserContext()="
				+ getUserContext() + "]";
	}

}