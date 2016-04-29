/** create by system gera-java version 1.0.0 28/04/2016 20:3 : 21*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.Audiencia;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.response.AdvogadoResponse;
import com.qat.samples.sysmgmt.advocacia.response.AudienciaResponse;
import com.qat.samples.sysmgmt.advocacia.response.ProcessoResponse;
import com.qat.samples.sysmgmt.bac.Advogado.IAdvocaciaBAC;
import com.qat.samples.sysmgmt.service.IAdvogadoWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * AdvogadoWS used to provide WS interface. Delegates all calls to the IAdvogadoBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class AdvogadoWSImpl implements IAdvogadoWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.advogadojmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.advogadojmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = AdvogadoWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AdvogadoWSImpl.class);
	private IAdvocaciaBAC advogadoBAC;
	/**
	 * @return the advogadoBAC which is expected to provide the implementation.
	 */
	public IAdvocaciaBAC getAdvogadoBAC()
	{
		return advogadoBAC;
	}
	/**
	 * Spring injection uses this method to inject an implementation of {@link IAdvogadoBAC}.
	 *
	 * @param advogadoBAC the advogadoBAC to set.
	 */
	public void setAdvogadoBAC(IAdvocaciaBAC advogadoBAC)
	{
		this.advogadoBAC = advogadoBAC;
	}


//===================================### ADVOGADO ####======================================

	/**
	 * Delegates call to {@link IAdvogadoBAC}
	 *
	 * @param request a AdvogadoRequest
	 * @return AdvogadoResponse
	 */
	@Override
	public AdvogadoResponse insertAdvogado(AdvogadoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AdvogadoResponse response = new AdvogadoResponse();
		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().insertAdvogado(request);
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
	 * Delegates call to {@link IAdvogadoBAC}
	 *
	 * @param request a AdvogadoRequest
	 * @return AdvogadoResponse
	 */
	@Override
	public AdvogadoResponse updateAdvogado(AdvogadoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AdvogadoResponse response = new AdvogadoResponse();
		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().updateAdvogado(request);
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
	 * Delegates call to {@link IAdvogadoBAC}
	 *
	 * @param request a AdvogadoRequest
	 * @return AdvogadoResponse
	 */
	@Override
	public AdvogadoResponse deleteAdvogado(AdvogadoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AdvogadoResponse response = new AdvogadoResponse();
		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().deleteAdvogado(request);
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
	 * Delegates call to {@link IAdvogadoBAC}
	 *
	 * @param request a AdvogadoRequest
	 * @return AdvogadoResponse
	 */
	@Override
	public AdvogadoResponse fetchAdvogadoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AdvogadoResponse response = new AdvogadoResponse();
		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().fetchAdvogadoById(request);
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
	 * Delegates call to {@link IAdvogadoBAC}
	 *
	 * @param request a AdvogadoRequest
	 * @return AdvogadoResponse
	 */
	@Override
	public AdvogadoResponse fetchAdvogadosByRequest(AdvogadoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AdvogadoResponse response = new AdvogadoResponse();
		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().fetchAdvogadosByRequest(request);
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
	 * Delegates call to {@link IAdvogadoBAC}
	 *
	 * @param request a AdvogadoRequest
	 * @return AdvogadoResponse
	 */
	@Override
	public AdvogadoResponse refreshAdvogados(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AdvogadoResponse response = new AdvogadoResponse();
		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().refreshAdvogados(request);
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
	 * Delegates call to {@link IAdvogadoBAC}
	 *
	 * @param request a AdvogadoRequest
	 * @return AdvogadoResponse
	 */
	@Override
	public AdvogadoResponse fetchAllAdvogados(FetchAllRequest request)
	{
		AdvogadoResponse response = new AdvogadoResponse();
		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().fetchAllAdvogados(new Advogado());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### AUDIENCIA ####======================================

	/**
	 * Delegates call to {@link IAudienciaBAC}
	 *
	 * @param request a AudienciaRequest
	 * @return AudienciaResponse
	 */
	@Override
	public AudienciaResponse insertAudiencia(AudienciaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AudienciaResponse response = new AudienciaResponse();
		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().insertAudiencia(request);
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
	 * Delegates call to {@link IAudienciaBAC}
	 *
	 * @param request a AudienciaRequest
	 * @return AudienciaResponse
	 */
	@Override
	public AudienciaResponse updateAudiencia(AudienciaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AudienciaResponse response = new AudienciaResponse();
		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().updateAudiencia(request);
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
	 * Delegates call to {@link IAudienciaBAC}
	 *
	 * @param request a AudienciaRequest
	 * @return AudienciaResponse
	 */
	@Override
	public AudienciaResponse deleteAudiencia(AudienciaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AudienciaResponse response = new AudienciaResponse();
		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().deleteAudiencia(request);
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
	 * Delegates call to {@link IAudienciaBAC}
	 *
	 * @param request a AudienciaRequest
	 * @return AudienciaResponse
	 */
	@Override
	public AudienciaResponse fetchAudienciaById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AudienciaResponse response = new AudienciaResponse();
		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().fetchAudienciaById(request);
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
	 * Delegates call to {@link IAudienciaBAC}
	 *
	 * @param request a AudienciaRequest
	 * @return AudienciaResponse
	 */
	@Override
	public AudienciaResponse fetchAudienciasByRequest(AudienciaInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AudienciaResponse response = new AudienciaResponse();
		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().fetchAudienciasByRequest(request);
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
	 * Delegates call to {@link IAudienciaBAC}
	 *
	 * @param request a AudienciaRequest
	 * @return AudienciaResponse
	 */
	@Override
	public AudienciaResponse refreshAudiencias(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AudienciaResponse response = new AudienciaResponse();
		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().refreshAudiencias(request);
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
	 * Delegates call to {@link IAudienciaBAC}
	 *
	 * @param request a AudienciaRequest
	 * @return AudienciaResponse
	 */
	@Override
	public AudienciaResponse fetchAllAudiencias(FetchAllRequest request)
	{
		AudienciaResponse response = new AudienciaResponse();
		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().fetchAllAudiencias(new Audiencia());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### PROCESSO ####======================================

	/**
	 * Delegates call to {@link IProcessoBAC}
	 *
	 * @param request a ProcessoRequest
	 * @return ProcessoResponse
	 */
	@Override
	public ProcessoResponse insertProcesso(ProcessoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProcessoResponse response = new ProcessoResponse();
		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().insertProcesso(request);
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
	 * Delegates call to {@link IProcessoBAC}
	 *
	 * @param request a ProcessoRequest
	 * @return ProcessoResponse
	 */
	@Override
	public ProcessoResponse updateProcesso(ProcessoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProcessoResponse response = new ProcessoResponse();
		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().updateProcesso(request);
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
	 * Delegates call to {@link IProcessoBAC}
	 *
	 * @param request a ProcessoRequest
	 * @return ProcessoResponse
	 */
	@Override
	public ProcessoResponse deleteProcesso(ProcessoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProcessoResponse response = new ProcessoResponse();
		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().deleteProcesso(request);
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
	 * Delegates call to {@link IProcessoBAC}
	 *
	 * @param request a ProcessoRequest
	 * @return ProcessoResponse
	 */
	@Override
	public ProcessoResponse fetchProcessoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProcessoResponse response = new ProcessoResponse();
		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().fetchProcessoById(request);
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
	 * Delegates call to {@link IProcessoBAC}
	 *
	 * @param request a ProcessoRequest
	 * @return ProcessoResponse
	 */
	@Override
	public ProcessoResponse fetchProcessosByRequest(ProcessoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProcessoResponse response = new ProcessoResponse();
		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().fetchProcessosByRequest(request);
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
	 * Delegates call to {@link IProcessoBAC}
	 *
	 * @param request a ProcessoRequest
	 * @return ProcessoResponse
	 */
	@Override
	public ProcessoResponse refreshProcessos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProcessoResponse response = new ProcessoResponse();
		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().refreshProcessos(request);
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
	 * Delegates call to {@link IProcessoBAC}
	 *
	 * @param request a ProcessoRequest
	 * @return ProcessoResponse
	 */
	@Override
	public ProcessoResponse fetchAllProcessos(FetchAllRequest request)
	{
		ProcessoResponse response = new ProcessoResponse();
		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().fetchAllProcessos(new Processo());
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
