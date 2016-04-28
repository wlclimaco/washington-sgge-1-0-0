/** create by system gera-java version 1.0.0 28/04/2016 13:48 : 18*/

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
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class AdvogadoWSImpl implements com.qat.samples.sysmgmt.service.IAdvogadoWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.advogadowsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.advogadowsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = AdvogadoWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AdvogadoWSImpl.class);

	/** The advogado BAC. */
	private IAdvocaciaBAC advogadoBAC; // injected by Spring through setter

	/**
	 * Spring Sets the advogado BAC.
	 *
	 * @param advogadoBAC the new advogado BAC
	 */
	public void setAdvogadoBAC(IAdvocaciaBAC advogadoBAC)
	{
		this.advogadoBAC = advogadoBAC;
	}

	/**
	 * Gets the advogado bac.
	 *
	 * @return the advogado bac
	 */
	public IAdvocaciaBAC getAdvogadoBAC()
	{
		return advogadoBAC;
	}


//===================================### ADVOGADO ####======================================
	@Override
	public AdvogadoResponse insertAdvogado(AdvogadoMaintenanceRequest request)
	{
		AdvogadoResponse response = new AdvogadoResponse();

		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().insertAdvogado(request);
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
	public AdvogadoResponse updateAdvogado(AdvogadoMaintenanceRequest request)
	{
		AdvogadoResponse response = new AdvogadoResponse();

		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().updateAdvogado(request);
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
	public AdvogadoResponse deleteAdvogado(AdvogadoMaintenanceRequest request)
	{
		AdvogadoResponse response = new AdvogadoResponse();

		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().deleteAdvogado(request);
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
	public AdvogadoResponse refreshAdvogados(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AdvogadoResponse response = new AdvogadoResponse();

		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().refreshAdvogados(request);
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
	public AdvogadoResponse fetchAllAdvogados(FetchAllRequest request)
	{
		AdvogadoResponse response = new AdvogadoResponse();

		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().fetchAllAdvogados(new Advogado());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IAdvogadoWS#fetchAdvogadoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public AdvogadoResponse fetchAdvogadoById(FetchByIdRequest request)
	{
		AdvogadoResponse response = new AdvogadoResponse();

		try
		{
			InternalResultsResponse<Advogado> internalResponse = new InternalResultsResponse<Advogado>();

			internalResponse = getAdvogadoBAC().fetchAdvogadoById(request);
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
	public AdvogadoResponse fetchAdvogadosByRequest(AdvogadoInquiryRequest request)
	{
		AdvogadoResponse response = new AdvogadoResponse();

		try
		{
			InternalResultsResponse<Advogado> internalResponse = getAdvogadoBAC().fetchAdvogadosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### AUDIENCIA ####======================================
	@Override
	public AudienciaResponse insertAudiencia(AudienciaMaintenanceRequest request)
	{
		AudienciaResponse response = new AudienciaResponse();

		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().insertAudiencia(request);
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
	public AudienciaResponse updateAudiencia(AudienciaMaintenanceRequest request)
	{
		AudienciaResponse response = new AudienciaResponse();

		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().updateAudiencia(request);
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
	public AudienciaResponse deleteAudiencia(AudienciaMaintenanceRequest request)
	{
		AudienciaResponse response = new AudienciaResponse();

		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().deleteAudiencia(request);
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
	public AudienciaResponse refreshAudiencias(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		AudienciaResponse response = new AudienciaResponse();

		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().refreshAudiencias(request);
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
	public AudienciaResponse fetchAllAudiencias(FetchAllRequest request)
	{
		AudienciaResponse response = new AudienciaResponse();

		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().fetchAllAudiencias(new Audiencia());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IAudienciaWS#fetchAudienciaById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public AudienciaResponse fetchAudienciaById(FetchByIdRequest request)
	{
		AudienciaResponse response = new AudienciaResponse();

		try
		{
			InternalResultsResponse<Audiencia> internalResponse = new InternalResultsResponse<Audiencia>();

			internalResponse = getAdvogadoBAC().fetchAudienciaById(request);
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
	public AudienciaResponse fetchAudienciasByRequest(AudienciaInquiryRequest request)
	{
		AudienciaResponse response = new AudienciaResponse();

		try
		{
			InternalResultsResponse<Audiencia> internalResponse = getAdvogadoBAC().fetchAudienciasByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### PROCESSO ####======================================
	@Override
	public ProcessoResponse insertProcesso(ProcessoMaintenanceRequest request)
	{
		ProcessoResponse response = new ProcessoResponse();

		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().insertProcesso(request);
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
	public ProcessoResponse updateProcesso(ProcessoMaintenanceRequest request)
	{
		ProcessoResponse response = new ProcessoResponse();

		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().updateProcesso(request);
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
	public ProcessoResponse deleteProcesso(ProcessoMaintenanceRequest request)
	{
		ProcessoResponse response = new ProcessoResponse();

		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().deleteProcesso(request);
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
	public ProcessoResponse refreshProcessos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProcessoResponse response = new ProcessoResponse();

		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().refreshProcessos(request);
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
	public ProcessoResponse fetchAllProcessos(FetchAllRequest request)
	{
		ProcessoResponse response = new ProcessoResponse();

		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().fetchAllProcessos(new Processo());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IProcessoWS#fetchProcessoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ProcessoResponse fetchProcessoById(FetchByIdRequest request)
	{
		ProcessoResponse response = new ProcessoResponse();

		try
		{
			InternalResultsResponse<Processo> internalResponse = new InternalResultsResponse<Processo>();

			internalResponse = getAdvogadoBAC().fetchProcessoById(request);
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
	public ProcessoResponse fetchProcessosByRequest(ProcessoInquiryRequest request)
	{
		ProcessoResponse response = new ProcessoResponse();

		try
		{
			InternalResultsResponse<Processo> internalResponse = getAdvogadoBAC().fetchProcessosByRequest(request);
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
