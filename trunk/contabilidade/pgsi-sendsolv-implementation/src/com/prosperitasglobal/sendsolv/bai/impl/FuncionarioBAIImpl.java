package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IFuncionarioBAC;
import com.prosperitasglobal.sendsolv.bac.IPessoaBAC;
import com.prosperitasglobal.sendsolv.bai.IFuncionarioBAI;
import com.prosperitasglobal.sendsolv.model.Beneficios;
import com.prosperitasglobal.sendsolv.model.Eventos;
import com.prosperitasglobal.sendsolv.model.Funcionario;
import com.prosperitasglobal.sendsolv.model.HorarioFunc;
import com.prosperitasglobal.sendsolv.model.request.BeneficiosInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EventoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.HoraFuncInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.BeneficiosResponse;
import com.prosperitasglobal.sendsolv.model.response.EventoResponse;
import com.prosperitasglobal.sendsolv.model.response.FuncionarioResponse;
import com.prosperitasglobal.sendsolv.model.response.HorarioFuncResponse;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class FuncionarioBAIImpl.
 */
public class FuncionarioBAIImpl implements IFuncionarioBAI
{
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = FuncionarioBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FuncionarioBAIImpl.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED =
			"sendsolv.base.empresavalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The funcionario bac. */
	private IFuncionarioBAC empresaBAC; // injected by Spring through setter

	private IPessoaBAC pessoaBAC;

	/** The validation controller. */
	private ValidationController validationController;

	/**
	 * Get funcionario validation controller.
	 *
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Spring sets the funcionario validation controller.
	 *
	 * @param funcionarioValidationController the new validation controller
	 */
	public void setValidationController(ValidationController funcionarioValidationController)
	{
		validationController = funcionarioValidationController;
	}

	/**
	 * Spring Sets the funcionario bac.
	 *
	 * @param empresaBAC the new empresa bac
	 */
	public void setFuncionarioBAC(IFuncionarioBAC empresaBAC)
	{
		this.empresaBAC = empresaBAC;
	}

	/**
	 * Gets the empresa bac.
	 *
	 * @return the empresa bac
	 */
	public IFuncionarioBAC getFuncionarioBAC()
	{
		return empresaBAC;
	}

	/**
	 * @return the empresaBAC
	 */
	public IFuncionarioBAC getEmpresaBAC()
	{
		return empresaBAC;
	}

	/**
	 * @param empresaBAC the empresaBAC to set
	 */
	public void setEmpresaBAC(IFuncionarioBAC empresaBAC)
	{
		this.empresaBAC = empresaBAC;
	}

	/**
	 * @return the pessoaBAC
	 */
	public IPessoaBAC getPessoaBAC()
	{
		return pessoaBAC;
	}

	/**
	 * @param pessoaBAC the pessoaBAC to set
	 */
	public void setPessoaBAC(IPessoaBAC pessoaBAC)
	{
		this.pessoaBAC = pessoaBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#insertLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public FuncionarioResponse insertFuncionario(FuncionarioMaintenanceRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			response = process(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#updateLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public FuncionarioResponse updateFuncionario(FuncionarioMaintenanceRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			response = process(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#deleteLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public FuncionarioResponse deleteFuncionario(FuncionarioMaintenanceRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			response = process(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#fetchLocaationById(com.prosperitasglobal.sendsolv.model.
	 * request
	 * .CountyRequest)
	 */
	@Override
	public FuncionarioResponse fetchFuncionarioById(FetchByIdRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getFuncionarioBAC().fetchFuncionarioById(request);
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.IFuncionarioBAI#fetchFuncionarioByRequest(com.prosperitasglobal.sendsolv.model
	 * .request.PagedInquiryRequest)
	 */
	@Override
	public FuncionarioResponse fetchFuncionarioByRequest(FuncionarioInquiryRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();
		try
		{
			fetchPaged(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.IFuncionarioBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPaged(FuncionarioInquiryRequest request, FuncionarioResponse response)
	{
		InternalResultsResponse<Funcionario> internalResponse = new InternalResultsResponse<Funcionario>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getFuncionarioBAC().fetchFuncionarioByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the funcionario response
	 */
	private FuncionarioResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			FuncionarioMaintenanceRequest request)
	{
		FuncionarioResponse response = new FuncionarioResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Funcionario.class.getSimpleName(), request.getFuncionario(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturn(response, internalResponse, context.getMessages(), true);
		// return (FuncionarioResponse)handleReturn((Response)response, internalResponse, null, true);
	}

	/**
	 * Handle return.
	 *
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the response
	 */
	private FuncionarioResponse handleReturn(FuncionarioResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{
		messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_OL_ERROR, MessageSeverity.Error,
				MessageLevel.Object));

		QATInterfaceUtil.handleOperationStatusAndMessages(response,
				internalResponse, messages, copyOver);
		return response;
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistance(FuncionarioMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getFuncionarioBAC().insertFuncionario(request);

			case UPDATE:
				return getFuncionarioBAC().updateFuncionario(request);

			case DELETE:
				return getFuncionarioBAC().deleteFuncionario(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	@Override
	public BeneficiosResponse fetchBeneficiosByRequest(BeneficiosInquiryRequest request)
	{
		BeneficiosResponse response = new BeneficiosResponse();
		try
		{
			fetchPagedBeneficios(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.IFuncionarioBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedBeneficios(BeneficiosInquiryRequest request, BeneficiosResponse response)
	{
		InternalResultsResponse<Beneficios> internalResponse = new InternalResultsResponse<Beneficios>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchBeneficiosRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public HorarioFuncResponse fetchHoraFuncByRequest(HoraFuncInquiryRequest request)
	{
		HorarioFuncResponse response = new HorarioFuncResponse();
		try
		{
			fetchPagedHorarioFunc(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.IFuncionarioBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedHorarioFunc(HoraFuncInquiryRequest request, HorarioFuncResponse response)
	{
		InternalResultsResponse<HorarioFunc> internalResponse = new InternalResultsResponse<HorarioFunc>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchHorarioFuncsRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public EventoResponse fetchEventoByRequest(EventoInquiryRequest request)
	{
		EventoResponse response = new EventoResponse();
		try
		{
			fetchPagedEvento(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.IFuncionarioBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedEvento(EventoInquiryRequest request, EventoResponse response)
	{
		InternalResultsResponse<Eventos> internalResponse = new InternalResultsResponse<Eventos>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchEventosRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

}
