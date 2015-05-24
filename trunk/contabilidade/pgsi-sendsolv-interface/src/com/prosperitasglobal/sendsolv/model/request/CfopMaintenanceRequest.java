package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Arquivo;
import com.qat.framework.model.request.MaintenanceRequest;

public class CfopMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Arquivo arquivo;

	/**
	 * The Constructor.
	 */
	public CfopMaintenanceRequest()
	{

	}

	/**
	 * @return the arquivo
	 */
	public Arquivo getArquivo()
	{
		return arquivo;
	}

	/**
	 * @param arquivo the arquivo to set
	 */
	public void setArquivo(Arquivo arquivo)
	{
		this.arquivo = arquivo;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ArquivoMaintenanceRequest [getArquivo()=" + getArquivo() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}