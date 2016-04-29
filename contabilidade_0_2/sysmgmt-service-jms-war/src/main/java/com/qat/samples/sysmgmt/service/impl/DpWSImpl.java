/** create by system gera-java version 1.0.0 28/04/2016 20:5 : 32*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Dp.IDpBAC;
import com.qat.samples.sysmgmt.beneficios.model.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosMaintenanceRequest;
import com.qat.samples.sysmgmt.beneficios.model.response.BeneficiosResponse;
import com.qat.samples.sysmgmt.dp.model.Eventos;
import com.qat.samples.sysmgmt.dp.model.HorarioFunc;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.EventosMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.response.EventoResponse;
import com.qat.samples.sysmgmt.dp.model.response.FuncionarioResponse;
import com.qat.samples.sysmgmt.dp.model.response.HorarioFuncResponse;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;
import com.qat.samples.sysmgmt.service.IDpWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * DpWS used to provide WS interface. Delegates all calls to the IDpBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class DpWSImpl implements IDpWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.dpjmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.dpjmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = DpWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DpWSImpl.class);
	private IDpBAC dpBAC;
	/**
	 * @return the dpBAC which is expected to provide the implementation.
	 */
	public IDpBAC getDpBAC()
	{
		return dpBAC;
	}
	/**
	 * Spring injection uses this method to inject an implementation of {@link IDpBAC}.
	 *
	 * @param dpBAC the dpBAC to set.
	 */
	public void setDpBAC(IDpBAC dpBAC)
	{
		this.dpBAC = dpBAC;
	}


//===================================### FUNCIONARIO ####======================================

	/**
	 * Delegates call to {@link IFuncionarioBAC}
	 *
	 * @param request a FuncionarioRequest
	 * @return FuncionarioResponse
	 */
	@Override
	public FuncionarioResponse insertFuncionario(FuncionarioMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().insertFuncionario(request);
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
	 * Delegates call to {@link IFuncionarioBAC}
	 *
	 * @param request a FuncionarioRequest
	 * @return FuncionarioResponse
	 */
	@Override
	public FuncionarioResponse updateFuncionario(FuncionarioMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().updateFuncionario(request);
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
	 * Delegates call to {@link IFuncionarioBAC}
	 *
	 * @param request a FuncionarioRequest
	 * @return FuncionarioResponse
	 */
	@Override
	public FuncionarioResponse deleteFuncionario(FuncionarioMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().deleteFuncionario(request);
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
	 * Delegates call to {@link IFuncionarioBAC}
	 *
	 * @param request a FuncionarioRequest
	 * @return FuncionarioResponse
	 */
	@Override
	public FuncionarioResponse fetchFuncionarioById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().fetchFuncionarioById(request);
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
	 * Delegates call to {@link IFuncionarioBAC}
	 *
	 * @param request a FuncionarioRequest
	 * @return FuncionarioResponse
	 */
	@Override
	public FuncionarioResponse fetchFuncionariosByRequest(FuncionarioInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().fetchFuncionariosByRequest(request);
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
	 * Delegates call to {@link IFuncionarioBAC}
	 *
	 * @param request a FuncionarioRequest
	 * @return FuncionarioResponse
	 */
	@Override
	public FuncionarioResponse refreshFuncionarios(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().refreshFuncionarios(request);
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
	 * Delegates call to {@link IFuncionarioBAC}
	 *
	 * @param request a FuncionarioRequest
	 * @return FuncionarioResponse
	 */
	@Override
	public FuncionarioResponse fetchAllFuncionarios(FetchAllRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			InternalResultsResponse<Funcionario> internalResponse = getDpBAC().fetchAllFuncionarios(new Funcionario());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### EVENTOS ####======================================

	/**
	 * Delegates call to {@link IEventosBAC}
	 *
	 * @param request a EventosRequest
	 * @return EventoResponse
	 */
	@Override
	public EventoResponse insertEventos(EventosMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventoResponse response = new EventoResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().insertEventos(request);
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
	 * Delegates call to {@link IEventosBAC}
	 *
	 * @param request a EventosRequest
	 * @return EventoResponse
	 */
	@Override
	public EventoResponse updateEventos(EventosMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventoResponse response = new EventoResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().updateEventos(request);
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
	 * Delegates call to {@link IEventosBAC}
	 *
	 * @param request a EventosRequest
	 * @return EventoResponse
	 */
	@Override
	public EventoResponse deleteEventos(EventosMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventoResponse response = new EventoResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().deleteEventos(request);
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
	 * Delegates call to {@link IEventosBAC}
	 *
	 * @param request a EventosRequest
	 * @return EventoResponse
	 */
	@Override
	public EventoResponse fetchEventosById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventoResponse response = new EventoResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().fetchEventosById(request);
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
	 * Delegates call to {@link IEventosBAC}
	 *
	 * @param request a EventosRequest
	 * @return EventoResponse
	 */
	@Override
	public EventoResponse fetchEventossByRequest(EventoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventoResponse response = new EventoResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().fetchEventossByRequest(request);
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
	 * Delegates call to {@link IEventosBAC}
	 *
	 * @param request a EventosRequest
	 * @return EventoResponse
	 */
	@Override
	public EventoResponse refreshEventoss(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventoResponse response = new EventoResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().refreshEventoss(request);
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
	 * Delegates call to {@link IEventosBAC}
	 *
	 * @param request a EventosRequest
	 * @return EventoResponse
	 */
	@Override
	public EventoResponse fetchAllEventoss(FetchAllRequest request)
	{
		EventoResponse response = new EventoResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().fetchAllEventoss(new Eventos());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### BENEFICIOS ####======================================

	/**
	 * Delegates call to {@link IBeneficiosBAC}
	 *
	 * @param request a BeneficiosRequest
	 * @return BeneficiosResponse
	 */
	@Override
	public BeneficiosResponse insertBeneficios(BeneficiosMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BeneficiosResponse response = new BeneficiosResponse();
		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().insertBeneficios(request);
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
	 * Delegates call to {@link IBeneficiosBAC}
	 *
	 * @param request a BeneficiosRequest
	 * @return BeneficiosResponse
	 */
	@Override
	public BeneficiosResponse updateBeneficios(BeneficiosMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BeneficiosResponse response = new BeneficiosResponse();
		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().updateBeneficios(request);
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
	 * Delegates call to {@link IBeneficiosBAC}
	 *
	 * @param request a BeneficiosRequest
	 * @return BeneficiosResponse
	 */
	@Override
	public BeneficiosResponse deleteBeneficios(BeneficiosMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BeneficiosResponse response = new BeneficiosResponse();
		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().deleteBeneficios(request);
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
	 * Delegates call to {@link IBeneficiosBAC}
	 *
	 * @param request a BeneficiosRequest
	 * @return BeneficiosResponse
	 */
	@Override
	public BeneficiosResponse fetchBeneficiosById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BeneficiosResponse response = new BeneficiosResponse();
		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().fetchBeneficiosById(request);
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
	 * Delegates call to {@link IBeneficiosBAC}
	 *
	 * @param request a BeneficiosRequest
	 * @return BeneficiosResponse
	 */
	@Override
	public BeneficiosResponse fetchBeneficiossByRequest(BeneficiosInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BeneficiosResponse response = new BeneficiosResponse();
		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().fetchBeneficiossByRequest(request);
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
	 * Delegates call to {@link IBeneficiosBAC}
	 *
	 * @param request a BeneficiosRequest
	 * @return BeneficiosResponse
	 */
	@Override
	public BeneficiosResponse refreshBeneficioss(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BeneficiosResponse response = new BeneficiosResponse();
		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().refreshBeneficioss(request);
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
	 * Delegates call to {@link IBeneficiosBAC}
	 *
	 * @param request a BeneficiosRequest
	 * @return BeneficiosResponse
	 */
	@Override
	public BeneficiosResponse fetchAllBeneficioss(FetchAllRequest request)
	{
		BeneficiosResponse response = new BeneficiosResponse();
		try
		{
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().fetchAllBeneficioss(new Beneficios());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### HORAFUNC ####======================================

	/**
	 * Delegates call to {@link IHoraFuncBAC}
	 *
	 * @param request a HoraFuncRequest
	 * @return HorarioFuncResponse
	 */
	@Override
	public HorarioFuncResponse insertHoraFunc(HoraFuncMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HorarioFuncResponse response = new HorarioFuncResponse();
		try
		{
			InternalResultsResponse<HorarioFunc> internalResponse = getDpBAC().insertHoraFunc(request);
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
	 * Delegates call to {@link IHoraFuncBAC}
	 *
	 * @param request a HoraFuncRequest
	 * @return HorarioFuncResponse
	 */
	@Override
	public HorarioFuncResponse updateHoraFunc(HoraFuncMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HorarioFuncResponse response = new HorarioFuncResponse();
		try
		{
			InternalResultsResponse<HorarioFunc> internalResponse = getDpBAC().updateHoraFunc(request);
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
	 * Delegates call to {@link IHoraFuncBAC}
	 *
	 * @param request a HoraFuncRequest
	 * @return HorarioFuncResponse
	 */
	@Override
	public HorarioFuncResponse deleteHoraFunc(HoraFuncMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HorarioFuncResponse response = new HorarioFuncResponse();
		try
		{
			InternalResultsResponse<HorarioFunc> internalResponse = getDpBAC().deleteHoraFunc(request);
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
	 * Delegates call to {@link IHoraFuncBAC}
	 *
	 * @param request a HoraFuncRequest
	 * @return HorarioFuncResponse
	 */
	@Override
	public HorarioFuncResponse fetchHoraFuncById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HorarioFuncResponse response = new HorarioFuncResponse();
		try
		{
			InternalResultsResponse<HorarioFunc> internalResponse = getDpBAC().fetchHoraFuncById(request);
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
	 * Delegates call to {@link IHoraFuncBAC}
	 *
	 * @param request a HoraFuncRequest
	 * @return HorarioFuncResponse
	 */
	@Override
	public HorarioFuncResponse fetchHoraFuncsByRequest(HoraFuncInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HorarioFuncResponse response = new HorarioFuncResponse();
		try
		{
			InternalResultsResponse<HorarioFunc> internalResponse = getDpBAC().fetchHoraFuncsByRequest(request);
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
	 * Delegates call to {@link IHoraFuncBAC}
	 *
	 * @param request a HoraFuncRequest
	 * @return HorarioFuncResponse
	 */
	@Override
	public HorarioFuncResponse refreshHoraFuncs(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HorarioFuncResponse response = new HorarioFuncResponse();
		try
		{
			InternalResultsResponse<HorarioFunc> internalResponse = getDpBAC().refreshHoraFuncs(request);
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
	 * Delegates call to {@link IHoraFuncBAC}
	 *
	 * @param request a HoraFuncRequest
	 * @return HorarioFuncResponse
	 */
	@Override
	public HorarioFuncResponse fetchAllHoraFuncs(FetchAllRequest request)
	{
		HorarioFuncResponse response = new HorarioFuncResponse();
		try
		{
			InternalResultsResponse<HorarioFunc> internalResponse = getDpBAC().fetchAllHoraFuncs(new HorarioFunc());
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
