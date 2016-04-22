package com.qat.samples.sysmgmt.cotacao.request;

import com.qat.samples.sysmgmt.cotacao.model.Cotacao;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class CotacaoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Cotacao Cotacao;

	/**
	 * The Constructor.
	 */
	public CotacaoMaintenanceRequest()
	{

	}

	/**
	 * @return the Cotacao
	 */
	public Cotacao getCotacao()
	{
		return Cotacao;
	}

	/**
	 * @param Cotacao the Cotacao to set
	 */
	public void setCotacao(Cotacao Cotacao)
	{
		this.Cotacao = Cotacao;
	}

	@Override
	public String toString() {
		return "CotacaoMaintenanceRequest [getCotacao()=" + getCotacao() + ", toString()=" + super.toString() + "]";
	}




}