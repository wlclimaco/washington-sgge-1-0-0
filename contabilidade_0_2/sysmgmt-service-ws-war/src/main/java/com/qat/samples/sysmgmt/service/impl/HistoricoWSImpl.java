/** create by system gera-java version 1.0.0 28/04/2016 14:31 : 5*/

package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Historico.IHistoricoBAC;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoInquiryRequest;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoMaintenanceRequest;
import com.qat.samples.sysmgmt.historico.model.response.HistoricoResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class HistoricoWSImpl implements com.qat.samples.sysmgmt.service.IHistoricoWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.historicowsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.historicowsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = HistoricoWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(HistoricoWSImpl.class);

	/** The historico BAC. */
	private IHistoricoBAC historicoBAC; // injected by Spring through setter

	/**
	 * Spring Sets the historico BAC.
	 *
	 * @param historicoBAC the new historico BAC
	 */
	public void setHistoricoBAC(IHistoricoBAC historicoBAC)
	{
		this.historicoBAC = historicoBAC;
	}

	/**
	 * Gets the historico bac.
	 *
	 * @return the historico bac
	 */
	public IHistoricoBAC getHistoricoBAC()
	{
		return historicoBAC;
	}


//===================================### HISTORICO ####======================================
	@Override
	public HistoricoResponse insertHistorico(HistoricoMaintenanceRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();

		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().insertHistorico(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public HistoricoResponse updateHistorico(HistoricoMaintenanceRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();

		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().updateHistorico(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public HistoricoResponse deleteHistorico(HistoricoMaintenanceRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();

		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().deleteHistorico(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public HistoricoResponse refreshHistoricos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoResponse response = new HistoricoResponse();

		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().refreshHistoricos(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public HistoricoResponse fetchAllHistoricos(FetchAllRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();

		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().fetchAllHistoricos(new Historico());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IHistoricoWS#fetchHistoricoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public HistoricoResponse fetchHistoricoById(FetchByIdRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();

		try
		{
			InternalResultsResponse<Historico> internalResponse = new InternalResultsResponse<Historico>();

			internalResponse = getHistoricoBAC().fetchHistoricoById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public HistoricoResponse fetchHistoricosByRequest(HistoricoInquiryRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();

		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().fetchHistoricosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}
}
