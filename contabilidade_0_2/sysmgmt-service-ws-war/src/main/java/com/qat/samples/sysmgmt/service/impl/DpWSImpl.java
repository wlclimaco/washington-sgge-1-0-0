/** create by system gera-java version 1.0.0 28/04/2016 14:31 : 5*/

package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IDpBAC;
import com.qat.samples.sysmgmt.model.Dp;
import com.qat.samples.sysmgmt.model.request.DpMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.DpResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.DpInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class DpWSImpl implements com.qat.samples.sysmgmt.service.IDpWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.dpwsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.dpwsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = DpWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DpWSImpl.class);

	/** The dp BAC. */
	private IDpBAC dpBAC; // injected by Spring through setter

	/**
	 * Spring Sets the dp BAC.
	 *
	 * @param dpBAC the new dp BAC
	 */
	public void setDpBAC(IDpBAC dpBAC)
	{
		this.dpBAC = dpBAC;
	}

	/**
	 * Gets the dp bac.
	 *
	 * @return the dp bac
	 */
	public IDpBAC getDpBAC()
	{
		return dpBAC;
	}


//===================================### FUNCIONARIO ####======================================
	@Override
	public FuncionarioResponse insertFuncionario(FuncionarioMaintenanceRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();

		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().insertFuncionario(request);
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
	public FuncionarioResponse updateFuncionario(FuncionarioMaintenanceRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();

		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().updateFuncionario(request);
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
	public FuncionarioResponse deleteFuncionario(FuncionarioMaintenanceRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();

		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().deleteFuncionario(request);
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
	public FuncionarioResponse refreshFuncionarios(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FuncionarioResponse response = new FuncionarioResponse();

		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().refreshFuncionarios(request);
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
	public FuncionarioResponse fetchAllFuncionarios(FetchAllRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();

		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().fetchAllFuncionarios(new Funcionario());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IFuncionarioWS#fetchFuncionarioById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public FuncionarioResponse fetchFuncionarioById(FetchByIdRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();

		try
		{
			InternalResultsResponse<Funcionario> internalResponse = new InternalResultsResponse<Funcionario>();

			internalResponse = getDpBAC().fetchFuncionarioById(request);
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
	public FuncionarioResponse fetchFuncionariosByRequest(FuncionarioInquiryRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();

		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().fetchFuncionariosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### EVENTOS ####======================================
	@Override
	public EventosResponse insertEventos(EventosMaintenanceRequest request)
	{
		EventosResponse response = new EventosResponse();

		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().insertEventos(request);
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
	public EventosResponse updateEventos(EventosMaintenanceRequest request)
	{
		EventosResponse response = new EventosResponse();

		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().updateEventos(request);
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
	public EventosResponse deleteEventos(EventosMaintenanceRequest request)
	{
		EventosResponse response = new EventosResponse();

		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().deleteEventos(request);
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
	public EventosResponse refreshEventoss(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventosResponse response = new EventosResponse();

		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().refreshEventoss(request);
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
	public EventosResponse fetchAllEventoss(FetchAllRequest request)
	{
		EventosResponse response = new EventosResponse();

		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().fetchAllEventoss(new Eventos());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IEventosWS#fetchEventosById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public EventosResponse fetchEventosById(FetchByIdRequest request)
	{
		EventosResponse response = new EventosResponse();

		try
		{
			InternalResultsResponse<Eventos> internalResponse = new InternalResultsResponse<Eventos>();

			internalResponse = getDpBAC().fetchEventosById(request);
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
	public EventosResponse fetchEventossByRequest(EventosInquiryRequest request)
	{
		EventosResponse response = new EventosResponse();

		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().fetchEventossByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### BENEFICIOS ####======================================
	@Override
	public BeneficiosResponse insertBeneficios(BeneficiosMaintenanceRequest request)
	{
		BeneficiosResponse response = new BeneficiosResponse();

		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().insertBeneficios(request);
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
	public BeneficiosResponse updateBeneficios(BeneficiosMaintenanceRequest request)
	{
		BeneficiosResponse response = new BeneficiosResponse();

		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().updateBeneficios(request);
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
	public BeneficiosResponse deleteBeneficios(BeneficiosMaintenanceRequest request)
	{
		BeneficiosResponse response = new BeneficiosResponse();

		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().deleteBeneficios(request);
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
	public BeneficiosResponse refreshBeneficioss(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BeneficiosResponse response = new BeneficiosResponse();

		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().refreshBeneficioss(request);
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
	public BeneficiosResponse fetchAllBeneficioss(FetchAllRequest request)
	{
		BeneficiosResponse response = new BeneficiosResponse();

		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().fetchAllBeneficioss(new Beneficios());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IBeneficiosWS#fetchBeneficiosById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public BeneficiosResponse fetchBeneficiosById(FetchByIdRequest request)
	{
		BeneficiosResponse response = new BeneficiosResponse();

		try
		{
			InternalResultsResponse<Beneficios> internalResponse = new InternalResultsResponse<Beneficios>();

			internalResponse = getDpBAC().fetchBeneficiosById(request);
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
	public BeneficiosResponse fetchBeneficiossByRequest(BeneficiosInquiryRequest request)
	{
		BeneficiosResponse response = new BeneficiosResponse();

		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().fetchBeneficiossByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### HORAFUNC ####======================================
	@Override
	public HoraFuncResponse insertHoraFunc(HoraFuncMaintenanceRequest request)
	{
		HoraFuncResponse response = new HoraFuncResponse();

		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getDpBAC().insertHoraFunc(request);
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
	public HoraFuncResponse updateHoraFunc(HoraFuncMaintenanceRequest request)
	{
		HoraFuncResponse response = new HoraFuncResponse();

		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getDpBAC().updateHoraFunc(request);
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
	public HoraFuncResponse deleteHoraFunc(HoraFuncMaintenanceRequest request)
	{
		HoraFuncResponse response = new HoraFuncResponse();

		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getDpBAC().deleteHoraFunc(request);
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
	public HoraFuncResponse refreshHoraFuncs(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HoraFuncResponse response = new HoraFuncResponse();

		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getDpBAC().refreshHoraFuncs(request);
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
	public HoraFuncResponse fetchAllHoraFuncs(FetchAllRequest request)
	{
		HoraFuncResponse response = new HoraFuncResponse();

		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getDpBAC().fetchAllHoraFuncs(new HoraFunc());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IHoraFuncWS#fetchHoraFuncById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public HoraFuncResponse fetchHoraFuncById(FetchByIdRequest request)
	{
		HoraFuncResponse response = new HoraFuncResponse();

		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = new InternalResultsResponse<HoraFunc>();

			internalResponse = getDpBAC().fetchHoraFuncById(request);
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
	public HoraFuncResponse fetchHoraFuncsByRequest(HoraFuncInquiryRequest request)
	{
		HoraFuncResponse response = new HoraFuncResponse();

		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getDpBAC().fetchHoraFuncsByRequest(request);
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
