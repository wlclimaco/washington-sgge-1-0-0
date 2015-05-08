package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IPayerBAC;
import com.prosperitasglobal.sendsolv.dac.IPayerDAC;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class PayerBACImpl.
 */
public class PayerBACImpl implements IPayerBAC
{

	/** The payer dac. */
	private IPayerDAC payerDAC;

	/**
	 * Gets the payer dac.
	 *
	 * @return the payer dac
	 */
	public IPayerDAC getPayerDAC()
	{
		return payerDAC;
	}

	/**
	 * Spring Sets the payer dac.
	 *
	 * @param payerDAC the new payer dac
	 */
	public void setPayerDAC(IPayerDAC payerDAC)
	{
		this.payerDAC = payerDAC;
	}

	/**
	 * Fetch payer by id.
	 *
	 * @param request the request
	 * @return the internal results response< payer>
	 */
	@Override
	public InternalResultsResponse<Payer> fetchPayerById(FetchByIdRequest request)
	{
		return getPayerDAC().fetchPayerById(request.getId());
	}

	/**
	 * Fetch payer by request in the SendSolv system.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all of the {@link Payer} that were found with the matching
	 *         criteria. Also contains information about whether the fetch was successful or not.
	 */
	@Override
	public InternalResultsResponse<Payer> fetchPayerByRequest(PayerInquiryRequest request)
	{
		return getPayerDAC().fetchPayerByRequest(request);
	}
}
