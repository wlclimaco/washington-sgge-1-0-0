package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IBeneficiosBAC;
import com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC;
import com.prosperitasglobal.sendsolv.model.Beneficios;
import com.prosperitasglobal.sendsolv.model.request.BeneficiosMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class BeneficiosBACImpl.
 */
public class BeneficiosBACImpl implements IBeneficiosBAC
{

	/** The person dac. */
	private IBeneficiosDAC beneficiosDAC;

	/**
	 * @return the beneficiosDAC
	 */
	public IBeneficiosDAC getBeneficiosDAC()
	{
		return beneficiosDAC;
	}

	/**
	 * @param beneficiosDAC the beneficiosDAC to set
	 */
	public void setBeneficiosDAC(IBeneficiosDAC beneficiosDAC)
	{
		this.beneficiosDAC = beneficiosDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IBeneficiosBAC#insertBeneficios(BeneficiosRequest)
	 */
	@Override
	public InternalResultsResponse<Beneficios> insertBeneficios(BeneficiosMaintenanceRequest request)
	{
		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();

		response = getBeneficiosDAC().insertBeneficios(request.getBeneficios());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IBeneficiosBAC#updateBeneficios(BeneficiosRequest)
	 */
	@Override
	public InternalResultsResponse<Beneficios> updateBeneficios(BeneficiosMaintenanceRequest request)
	{
		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();

		response = getBeneficiosDAC().updateBeneficios(request.getBeneficios());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IBeneficiosBAC#deleteBeneficios
	 * (com.prosperitasglobal.sendsolv.model.request.BeneficiosRequest
	 * )
	 */
	@Override
	public InternalResponse deleteBeneficios(BeneficiosMaintenanceRequest request)
	{
		return getBeneficiosDAC().deleteBeneficios(request.getBeneficios());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IBeneficiosBAC#fetchBeneficiosById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Beneficios> fetchBeneficiosById(FetchByIdRequest request)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IBeneficiosBAC#fetchBeneficiosByRequest(com.prosperitasglobal.sendsolv.model.
	 * request
	 * .BeneficiosInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Beneficios> fetchBeneficiosByRequest(PagedInquiryRequest request)
	{
		return getBeneficiosDAC().fetchBeneficiosByRequest(request);
	}
}
