/** create by system gera-java version 1.0.0 28/04/2016 14:29 : 20*/

package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Condominio.ICondominioBAC;
import com.qat.samples.sysmgmt.condominio.model.Avisos;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.condominio.model.request.AvisoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.AvisoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.response.AvisoResponse;
import com.qat.samples.sysmgmt.condominio.model.response.InquilinoResponse;
import com.qat.samples.sysmgmt.condominio.model.response.SindicoResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class CondominioWSImpl implements com.qat.samples.sysmgmt.service.ICondominioWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.condominiowsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.condominiowsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CondominioWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CondominioWSImpl.class);

	/** The condominio BAC. */
	private ICondominioBAC condominioBAC; // injected by Spring through setter

	/**
	 * Spring Sets the condominio BAC.
	 *
	 * @param condominioBAC the new condominio BAC
	 */
	public void setCondominioBAC(ICondominioBAC condominioBAC)
	{
		this.condominioBAC = condominioBAC;
	}

	/**
	 * Gets the condominio bac.
	 *
	 * @return the condominio bac
	 */
	public ICondominioBAC getCondominioBAC()
	{
		return condominioBAC;
	}


//===================================### SINDICO ####======================================
	@Override
	public SindicoResponse insertSindico(SindicoMaintenanceRequest request)
	{
		SindicoResponse response = new SindicoResponse();

		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().insertSindico(request);
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
	public SindicoResponse updateSindico(SindicoMaintenanceRequest request)
	{
		SindicoResponse response = new SindicoResponse();

		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().updateSindico(request);
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
	public SindicoResponse deleteSindico(SindicoMaintenanceRequest request)
	{
		SindicoResponse response = new SindicoResponse();

		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().deleteSindico(request);
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
	public SindicoResponse refreshSindicos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SindicoResponse response = new SindicoResponse();

		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().refreshSindicos(request);
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
	public SindicoResponse fetchAllSindicos(FetchAllRequest request)
	{
		SindicoResponse response = new SindicoResponse();

		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().fetchAllSindicos(new Sindico());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.ISindicoWS#fetchSindicoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public SindicoResponse fetchSindicoById(FetchByIdRequest request)
	{
		SindicoResponse response = new SindicoResponse();

		try
		{
			InternalResultsResponse<Sindico> internalResponse = new InternalResultsResponse<Sindico>();

			internalResponse = getCondominioBAC().fetchSindicoById(request);
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
	public SindicoResponse fetchSindicosByRequest(SindicoInquiryRequest request)
	{
		SindicoResponse response = new SindicoResponse();

		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().fetchSindicosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### INQUILINO ####======================================
	@Override
	public InquilinoResponse insertInquilino(InquilinoMaintenanceRequest request)
	{
		InquilinoResponse response = new InquilinoResponse();

		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().insertInquilino(request);
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
	public InquilinoResponse updateInquilino(InquilinoMaintenanceRequest request)
	{
		InquilinoResponse response = new InquilinoResponse();

		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().updateInquilino(request);
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
	public InquilinoResponse deleteInquilino(InquilinoMaintenanceRequest request)
	{
		InquilinoResponse response = new InquilinoResponse();

		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().deleteInquilino(request);
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
	public InquilinoResponse refreshInquilinos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InquilinoResponse response = new InquilinoResponse();

		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().refreshInquilinos(request);
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
	public InquilinoResponse fetchAllInquilinos(FetchAllRequest request)
	{
		InquilinoResponse response = new InquilinoResponse();

		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().fetchAllInquilinos(new Inquilino());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IInquilinoWS#fetchInquilinoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public InquilinoResponse fetchInquilinoById(FetchByIdRequest request)
	{
		InquilinoResponse response = new InquilinoResponse();

		try
		{
			InternalResultsResponse<Inquilino> internalResponse = new InternalResultsResponse<Inquilino>();

			internalResponse = getCondominioBAC().fetchInquilinoById(request);
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
	public InquilinoResponse fetchInquilinosByRequest(InquilinoInquiryRequest request)
	{
		InquilinoResponse response = new InquilinoResponse();

		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().fetchInquilinosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### AVISOS ####======================================
	@Override
	public AvisoResponse insertAvisos(AvisoMaintenanceRequest request)
	{
		AvisoResponse response = new AvisoResponse();

		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().insertAvisos(request);
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
	public AvisoResponse updateAvisos(AvisoMaintenanceRequest request)
	{
		AvisoResponse response = new AvisoResponse();

		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().updateAvisos(request);
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
	public AvisoResponse deleteAvisos(AvisoMaintenanceRequest request)
	{
		AvisoResponse response = new AvisoResponse();

		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().deleteAvisos(request);
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
	public AvisoResponse refreshAvisoss(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisoResponse response = new AvisoResponse();

		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().refreshAvisos(request);
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
	public AvisoResponse fetchAllAvisoss(FetchAllRequest request)
	{
		AvisoResponse response = new AvisoResponse();

		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().fetchAllAvisoss(new Avisos());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IAvisosWS#fetchAvisosById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public AvisoResponse fetchAvisosById(FetchByIdRequest request)
	{
		AvisoResponse response = new AvisoResponse();

		try
		{
			InternalResultsResponse<Avisos> internalResponse = new InternalResultsResponse<Avisos>();

			internalResponse = getCondominioBAC().fetchAvisosById(request);
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
	public AvisoResponse fetchAvisossByRequest(AvisoInquiryRequest request)
	{
		AvisoResponse response = new AvisoResponse();

		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().fetchAvisosByRequest(request);
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
