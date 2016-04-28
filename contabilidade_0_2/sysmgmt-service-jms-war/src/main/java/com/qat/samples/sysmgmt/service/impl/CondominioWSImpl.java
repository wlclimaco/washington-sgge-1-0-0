/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.ICondominioBAC;
import com.qat.samples.sysmgmt.model.Condominio;
import com.qat.samples.sysmgmt.model.response.CondominioResponse;
import com.qat.samples.sysmgmt.service.ICondominioWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
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

//===================================### SINDICO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link ISindicoBAC}.
	 *
	 * @param sindicoBAC the sindicoBAC to set.
	 */
	public void setSindicoBAC(ISindicoBAC sindicoBAC)
	{
		this.sindicoBAC = sindicoBAC;
	}
	
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
			InternalResultsResponse<Sindico> internalResponse = getSindicoBAC().insertSindico(request);
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
			InternalResultsResponse<Sindico> internalResponse = getSindicoBAC().updateSindico(request);
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
			InternalResultsResponse<Sindico> internalResponse = getSindicoBAC().deleteSindico(request);
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
			InternalResultsResponse<Sindico> internalResponse = getSindicoBAC().fetchSindicoById(request);
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
			InternalResultsResponse<Sindico> internalResponse = getSindicoBAC().fetchSindicosByRequest(request);
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
			InternalResultsResponse<Sindico> internalResponse = getSindicoBAC().refreshSindicos(request);
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
			InternalResultsResponse<Sindico> internalResponse = getSindicoBAC().fetchAllSindicos();
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
	 * Spring injection uses this method to inject an implementation of {@link IInquilinoBAC}.
	 *
	 * @param inquilinoBAC the inquilinoBAC to set.
	 */
	public void setInquilinoBAC(IInquilinoBAC inquilinoBAC)
	{
		this.inquilinoBAC = inquilinoBAC;
	}
	
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
			InternalResultsResponse<Inquilino> internalResponse = getInquilinoBAC().insertInquilino(request);
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
			InternalResultsResponse<Inquilino> internalResponse = getInquilinoBAC().updateInquilino(request);
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
			InternalResultsResponse<Inquilino> internalResponse = getInquilinoBAC().deleteInquilino(request);
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
			InternalResultsResponse<Inquilino> internalResponse = getInquilinoBAC().fetchInquilinoById(request);
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
			InternalResultsResponse<Inquilino> internalResponse = getInquilinoBAC().fetchInquilinosByRequest(request);
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
			InternalResultsResponse<Inquilino> internalResponse = getInquilinoBAC().refreshInquilinos(request);
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
			InternalResultsResponse<Inquilino> internalResponse = getInquilinoBAC().fetchAllInquilinos();
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
	 * Spring injection uses this method to inject an implementation of {@link IAvisosBAC}.
	 *
	 * @param avisosBAC the avisosBAC to set.
	 */
	public void setAvisosBAC(IAvisosBAC avisosBAC)
	{
		this.avisosBAC = avisosBAC;
	}
	
	/**
	 * Delegates call to {@link IAvisosBAC}
	 *
	 * @param request a AvisosRequest
	 * @return AvisosResponse
	 */
	@Override
	public AvisosResponse insertAvisos(AvisosMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisosResponse response = new AvisosResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getAvisosBAC().insertAvisos(request);
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
	 * @return AvisosResponse
	 */
	@Override
	public AvisosResponse updateAvisos(AvisosMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisosResponse response = new AvisosResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getAvisosBAC().updateAvisos(request);
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
	 * @return AvisosResponse
	 */
	@Override
	public AvisosResponse deleteAvisos(AvisosMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisosResponse response = new AvisosResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getAvisosBAC().deleteAvisos(request);
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
	 * @return AvisosResponse
	 */
	@Override
	public AvisosResponse fetchAvisosById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisosResponse response = new AvisosResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getAvisosBAC().fetchAvisosById(request);
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
	 * @return AvisosResponse
	 */
	@Override
	public AvisosResponse fetchAvisossByRequest(AvisosInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisosResponse response = new AvisosResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getAvisosBAC().fetchAvisossByRequest(request);
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
	 * @return AvisosResponse
	 */
	@Override
	public AvisosResponse refreshAvisoss(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AvisosResponse response = new AvisosResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getAvisosBAC().refreshAvisoss(request);
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
	 * @return AvisosResponse
	 */
	@Override
	public AvisosResponse fetchAllAvisoss(FetchAllRequest request)
	{
		AvisosResponse response = new AvisosResponse();
		try
		{
			InternalResultsResponse<Avisos> internalResponse = getAvisosBAC().fetchAllAvisoss();
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
