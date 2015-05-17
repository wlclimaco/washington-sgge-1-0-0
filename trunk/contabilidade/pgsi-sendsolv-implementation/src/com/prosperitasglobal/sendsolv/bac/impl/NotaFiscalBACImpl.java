package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.INotaFiscalBAC;
import com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC;
import com.prosperitasglobal.sendsolv.model.NotaFiscal;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class NotaFiscalBACImpl.
 */
public class NotaFiscalBACImpl implements INotaFiscalBAC
{

	/** The person dac. */
	private INotaFiscalDAC NotaFiscalDAC;

	/**
	 * @return the NotaFiscalDAC
	 */
	public INotaFiscalDAC getNotaFiscalDAC()
	{
		return NotaFiscalDAC;
	}

	/**
	 * @param NotaFiscalDAC the NotaFiscalDAC to set
	 */
	public void setNotaFiscalDAC(INotaFiscalDAC NotaFiscalDAC)
	{
		this.NotaFiscalDAC = NotaFiscalDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.INotaFiscalBAC#insertNotaFiscal(NotaFiscalRequest)
	 */
	@Override
	public InternalResultsResponse<NotaFiscal> insertNotaFiscal(NotaFiscalMaintenanceRequest request)
	{
		InternalResultsResponse<NotaFiscal> response = new InternalResultsResponse<NotaFiscal>();

		response = getNotaFiscalDAC().insertNotaFiscal(request.getNotaFiscal());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.INotaFiscalBAC#updateNotaFiscal(NotaFiscalRequest)
	 */
	@Override
	public InternalResultsResponse<NotaFiscal> updateNotaFiscal(NotaFiscalMaintenanceRequest request)
	{
		InternalResultsResponse<NotaFiscal> response = new InternalResultsResponse<NotaFiscal>();

		response = getNotaFiscalDAC().updateNotaFiscal(request.getNotaFiscal());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.INotaFiscalBAC#deleteNotaFiscal
	 * (com.prosperitasglobal.sendsolv.model.request.NotaFiscalRequest
	 * )
	 */
	@Override
	public InternalResponse deleteNotaFiscal(NotaFiscalMaintenanceRequest request)
	{
		return getNotaFiscalDAC().deleteNotaFiscal(request.getNotaFiscal());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.INotaFiscalBAC#fetchNotaFiscalById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<NotaFiscal> fetchNotaFiscalById(FetchByIdRequest request)
	{
		return getNotaFiscalDAC().fetchNotaFiscalById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.INotaFiscalBAC#fetchNotaFiscalByRequest(com.prosperitasglobal.sendsolv.model.
	 * request
	 * .NotaFiscalInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NotaFiscal> fetchNotaFiscalByRequest(NotaFiscalInquiryRequest request)
	{
		return getNotaFiscalDAC().fetchNotaFiscalByRequest(request);
	}
}
