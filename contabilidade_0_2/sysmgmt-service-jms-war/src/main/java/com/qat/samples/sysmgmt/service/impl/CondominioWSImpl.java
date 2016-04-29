/** create by system gera-java version 1.0.0 28/04/2016 20:5 : 32*/
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
import com.qat.samples.sysmgmt.service.ICondominioWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * CondominioWS used to provide WS interface. Delegates all calls to the ICondominioBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class CondominioWSImpl implements ICondominioWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.condominiojmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.condominiojmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CondominioWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CondominioWSImpl.class);
	private ICondominioBAC condominioBAC;
	/**
	 * @return the condominioBAC which is expected to provide the implementation.
	 */
	public ICondominioBAC getCondominioBAC()
	{
		return condominioBAC;
	}
	/**
	 * Spring injection uses this method to inject an implementation of {@link ICondominioBAC}.
	 *
	 * @param condominioBAC the condominioBAC to set.
	 */
	public void setCondominioBAC(ICondominioBAC condominioBAC)
	{
		this.condominioBAC = condominioBAC;
	}


//===================================### SINDICO ####======================================

	/**
	 * Delegates call to {@link ISindicoBAC}
	 *
	 * @param request a SindicoRequest
	 * @return SindicoResponse
	 */
	@Override
	public SindicoResponse insertSindico(SindicoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SindicoResponse response = new SindicoResponse();
		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().insertSindico(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link ISindicoBAC}
	 *
	 * @param request a SindicoRequest
	 * @return SindicoResponse
	 */
	@Override
	public SindicoResponse updateSindico(SindicoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SindicoResponse response = new SindicoResponse();
		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().updateSindico(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link ISindicoBAC}
	 *
	 * @param request a SindicoRequest
	 * @return SindicoResponse
	 */
	@Override
	public SindicoResponse deleteSindico(SindicoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SindicoResponse response = new SindicoResponse();
		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().deleteSindico(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link ISindicoBAC}
	 *
	 * @param request a SindicoRequest
	 * @return SindicoResponse
	 */
	@Override
	public SindicoResponse fetchSindicoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SindicoResponse response = new SindicoResponse();
		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().fetchSindicoById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link ISindicoBAC}
	 *
	 * @param request a SindicoRequest
	 * @return SindicoResponse
	 */
	@Override
	public SindicoResponse fetchSindicosByRequest(SindicoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SindicoResponse response = new SindicoResponse();
		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().fetchSindicosByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link ISindicoBAC}
	 *
	 * @param request a SindicoRequest
	 * @return SindicoResponse
	 */
	@Override
	public SindicoResponse refreshSindicos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SindicoResponse response = new SindicoResponse();
		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().refreshSindicos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link ISindicoBAC}
	 *
	 * @param request a SindicoRequest
	 * @return SindicoResponse
	 */
	@Override
	public SindicoResponse fetchAllSindicos(FetchAllRequest request)
	{
		SindicoResponse response = new SindicoResponse();
		try
		{
			InternalResultsResponse<Sindico> internalResponse = getCondominioBAC().fetchAllSindicos(new Sindico());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### INQUILINO ####======================================

	/**
	 * Delegates call to {@link IInquilinoBAC}
	 *
	 * @param request a InquilinoRequest
	 * @return InquilinoResponse
	 */
	@Override
	public InquilinoResponse insertInquilino(InquilinoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InquilinoResponse response = new InquilinoResponse();
		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().insertInquilino(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IInquilinoBAC}
	 *
	 * @param request a InquilinoRequest
	 * @return InquilinoResponse
	 */
	@Override
	public InquilinoResponse updateInquilino(InquilinoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InquilinoResponse response = new InquilinoResponse();
		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().updateInquilino(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IInquilinoBAC}
	 *
	 * @param request a InquilinoRequest
	 * @return InquilinoResponse
	 */
	@Override
	public InquilinoResponse deleteInquilino(InquilinoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InquilinoResponse response = new InquilinoResponse();
		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().deleteInquilino(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IInquilinoBAC}
	 *
	 * @param request a InquilinoRequest
	 * @return InquilinoResponse
	 */
	@Override
	public InquilinoResponse fetchInquilinoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InquilinoResponse response = new InquilinoResponse();
		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().fetchInquilinoById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IInquilinoBAC}
	 *
	 * @param request a InquilinoRequest
	 * @return InquilinoResponse
	 */
	@Override
	public InquilinoResponse fetchInquilinosByRequest(InquilinoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InquilinoResponse response = new InquilinoResponse();
		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().fetchInquilinosByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IInquilinoBAC}
	 *
	 * @param request a InquilinoRequest
	 * @return InquilinoResponse
	 */
	@Override
	public InquilinoResponse refreshInquilinos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InquilinoResponse response = new InquilinoResponse();
		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().refreshInquilinos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IInquilinoBAC}
	 *
	 * @param request a InquilinoRequest
	 * @return InquilinoResponse
	 */
	@Override
	public InquilinoResponse fetchAllInquilinos(FetchAllRequest request)
	{
		InquilinoResponse response = new InquilinoResponse();
		try
		{
			InternalResultsResponse<Inquilino> internalResponse = getCondominioBAC().fetchAllInquilinos(new Inquilino());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### AVISOS ####======================================

	/**
	 * Delegates call to {@link IAvisosBAC}
	 *
	 * @param request a AvisosRequest
	 * @return AvisoResponse
	 */
	@Override
	public AvisoResponse insertAvisos(AvisoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisoResponse response = new AvisoResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().insertAvisos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IAvisosBAC}
	 *
	 * @param request a AvisosRequest
	 * @return AvisoResponse
	 */
	@Override
	public AvisoResponse updateAvisos(AvisoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisoResponse response = new AvisoResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().updateAvisos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IAvisosBAC}
	 *
	 * @param request a AvisosRequest
	 * @return AvisoResponse
	 */
	@Override
	public AvisoResponse deleteAvisos(AvisoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisoResponse response = new AvisoResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().deleteAvisos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IAvisosBAC}
	 *
	 * @param request a AvisosRequest
	 * @return AvisoResponse
	 */
	@Override
	public AvisoResponse fetchAvisosById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisoResponse response = new AvisoResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().fetchAvisosById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IAvisosBAC}
	 *
	 * @param request a AvisosRequest
	 * @return AvisoResponse
	 */
	@Override
	public AvisoResponse fetchAvisossByRequest(AvisoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisoResponse response = new AvisoResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().fetchAvisosByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IAvisosBAC}
	 *
	 * @param request a AvisosRequest
	 * @return AvisoResponse
	 */
	@Override
	public AvisoResponse refreshAvisoss(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisoResponse response = new AvisoResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().refreshAvisos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IAvisosBAC}
	 *
	 * @param request a AvisosRequest
	 * @return AvisoResponse
	 */
	@Override
	public AvisoResponse fetchAllAvisoss(FetchAllRequest request)
	{
		AvisoResponse response = new AvisoResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getCondominioBAC().fetchAllAvisoss(new Avisos());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
}
