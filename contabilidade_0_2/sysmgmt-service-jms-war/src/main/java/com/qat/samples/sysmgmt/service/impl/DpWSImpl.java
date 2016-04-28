/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IDpBAC;
import com.qat.samples.sysmgmt.model.Dp;
import com.qat.samples.sysmgmt.model.response.DpResponse;
import com.qat.samples.sysmgmt.service.IDpWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
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

//===================================### FUNCIONARIO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IFuncionarioBAC}.
	 *
	 * @param funcionarioBAC the funcionarioBAC to set.
	 */
	public void setFuncionarioBAC(IFuncionarioBAC funcionarioBAC)
	{
		this.funcionarioBAC = funcionarioBAC;
	}
	
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
			InternalResultsResponse<Funcionario> internalResponse = getFuncionarioBAC().insertFuncionario(request);
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
			InternalResultsResponse<Funcionario> internalResponse = getFuncionarioBAC().updateFuncionario(request);
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
			InternalResultsResponse<Funcionario> internalResponse = getFuncionarioBAC().deleteFuncionario(request);
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
			InternalResultsResponse<Funcionario> internalResponse = getFuncionarioBAC().fetchFuncionarioById(request);
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
			InternalResultsResponse<Funcionario> internalResponse = getFuncionarioBAC().fetchFuncionariosByRequest(request);
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
			InternalResultsResponse<Funcionario> internalResponse = getFuncionarioBAC().refreshFuncionarios(request);
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
			InternalResultsResponse<Funcionario> internalResponse = getFuncionarioBAC().fetchAllFuncionarios();
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
	 * Spring injection uses this method to inject an implementation of {@link IEventosBAC}.
	 *
	 * @param eventosBAC the eventosBAC to set.
	 */
	public void setEventosBAC(IEventosBAC eventosBAC)
	{
		this.eventosBAC = eventosBAC;
	}
	
	/**
	 * Delegates call to {@link IEventosBAC}
	 *
	 * @param request a EventosRequest
	 * @return EventosResponse
	 */
	@Override
	public EventosResponse insertEventos(EventosMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventosResponse response = new EventosResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getEventosBAC().insertEventos(request);
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
	 * @return EventosResponse
	 */
	@Override
	public EventosResponse updateEventos(EventosMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventosResponse response = new EventosResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getEventosBAC().updateEventos(request);
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
	 * @return EventosResponse
	 */
	@Override
	public EventosResponse deleteEventos(EventosMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventosResponse response = new EventosResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getEventosBAC().deleteEventos(request);
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
	 * @return EventosResponse
	 */
	@Override
	public EventosResponse fetchEventosById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventosResponse response = new EventosResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getEventosBAC().fetchEventosById(request);
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
	 * @return EventosResponse
	 */
	@Override
	public EventosResponse fetchEventossByRequest(EventosInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventosResponse response = new EventosResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getEventosBAC().fetchEventossByRequest(request);
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
	 * @return EventosResponse
	 */
	@Override
	public EventosResponse refreshEventoss(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EventosResponse response = new EventosResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getEventosBAC().refreshEventoss(request);
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
	 * @return EventosResponse
	 */
	@Override
	public EventosResponse fetchAllEventoss(FetchAllRequest request)
	{
		EventosResponse response = new EventosResponse();
		try
		{
			InternalResultsResponse<Eventos> internalResponse = getEventosBAC().fetchAllEventoss();
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
	 * Spring injection uses this method to inject an implementation of {@link IBeneficiosBAC}.
	 *
	 * @param beneficiosBAC the beneficiosBAC to set.
	 */
	public void setBeneficiosBAC(IBeneficiosBAC beneficiosBAC)
	{
		this.beneficiosBAC = beneficiosBAC;
	}
	
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
			InternalResultsResponse<Beneficios> internalResponse = getBeneficiosBAC().insertBeneficios(request);
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
			InternalResultsResponse<Beneficios> internalResponse = getBeneficiosBAC().updateBeneficios(request);
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
			InternalResultsResponse<Beneficios> internalResponse = getBeneficiosBAC().deleteBeneficios(request);
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
			InternalResultsResponse<Beneficios> internalResponse = getBeneficiosBAC().fetchBeneficiosById(request);
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
			InternalResultsResponse<Beneficios> internalResponse = getBeneficiosBAC().fetchBeneficiossByRequest(request);
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
			InternalResultsResponse<Beneficios> internalResponse = getBeneficiosBAC().refreshBeneficioss(request);
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
			InternalResultsResponse<Beneficios> internalResponse = getBeneficiosBAC().fetchAllBeneficioss();
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
	 * Spring injection uses this method to inject an implementation of {@link IHoraFuncBAC}.
	 *
	 * @param horafuncBAC the horafuncBAC to set.
	 */
	public void setHoraFuncBAC(IHoraFuncBAC horafuncBAC)
	{
		this.horafuncBAC = horafuncBAC;
	}
	
	/**
	 * Delegates call to {@link IHoraFuncBAC}
	 *
	 * @param request a HoraFuncRequest
	 * @return HoraFuncResponse
	 */
	@Override
	public HoraFuncResponse insertHoraFunc(HoraFuncMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HoraFuncResponse response = new HoraFuncResponse();
		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getHoraFuncBAC().insertHoraFunc(request);
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
	 * @return HoraFuncResponse
	 */
	@Override
	public HoraFuncResponse updateHoraFunc(HoraFuncMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HoraFuncResponse response = new HoraFuncResponse();
		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getHoraFuncBAC().updateHoraFunc(request);
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
	 * @return HoraFuncResponse
	 */
	@Override
	public HoraFuncResponse deleteHoraFunc(HoraFuncMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HoraFuncResponse response = new HoraFuncResponse();
		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getHoraFuncBAC().deleteHoraFunc(request);
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
	 * @return HoraFuncResponse
	 */
	@Override
	public HoraFuncResponse fetchHoraFuncById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HoraFuncResponse response = new HoraFuncResponse();
		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getHoraFuncBAC().fetchHoraFuncById(request);
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
	 * @return HoraFuncResponse
	 */
	@Override
	public HoraFuncResponse fetchHoraFuncsByRequest(HoraFuncInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HoraFuncResponse response = new HoraFuncResponse();
		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getHoraFuncBAC().fetchHoraFuncsByRequest(request);
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
	 * @return HoraFuncResponse
	 */
	@Override
	public HoraFuncResponse refreshHoraFuncs(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HoraFuncResponse response = new HoraFuncResponse();
		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getHoraFuncBAC().refreshHoraFuncs(request);
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
	 * @return HoraFuncResponse
	 */
	@Override
	public HoraFuncResponse fetchAllHoraFuncs(FetchAllRequest request)
	{
		HoraFuncResponse response = new HoraFuncResponse();
		try
		{
			InternalResultsResponse<HoraFunc> internalResponse = getHoraFuncBAC().fetchAllHoraFuncs();
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
