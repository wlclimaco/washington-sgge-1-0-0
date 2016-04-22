package com.qat.samples.sysmgmt.bac.Dp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.MessageLevel;
import com.qat.framework.model.MessageSeverity;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.SystemErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Dp.IDpBAR;
import com.qat.samples.sysmgmt.beneficios.model.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.Eventos;
import com.qat.samples.sysmgmt.dp.model.HorarioFunc;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.EventosMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Dp leveraging the injected IDpBAR.
 */
@Component
public class DpBACImpl implements IDpBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_DP_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_DP_BAC_EXCEPTION_MSG = "sysmgmt.base.Dpbacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DpBACImpl.class);

	/** The Dp BAR. */
	private IDpBAR dpBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Dp BAR.
	 *
	 * @param DpBAR the new Dp BAR
	 */
	public void setDpBAR(IDpBAR dpBAR)
	{
		this.dpBAR = dpBAR;
	}

	/**
	 * Gets the Dp BAR.
	 *
	 * @return the Dp BAR
	 */
	public IDpBAR getDpBAR()
	{
		return dpBAR;
	}

	/**
	 * Gets the validation controller
	 *
	 * @return ValidationController
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Sets the validation Controller
	 *
	 * @param validationController
	 */
	public void setValidationController(ValidationController validationController)
	{
		this.validationController = validationController;
	}

//===================================### FUNCIONARIO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertFuncionario(com.qat.samples.sysmgmt.model.request.FuncionarioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Funcionario> insertFuncionario(FuncionarioMaintenanceRequest request)
{
	InternalResultsResponse<Funcionario> response =
			processFuncionario(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFuncionarioBAC#updateFuncionario(com.qat.samples.sysmgmt.model.request.FuncionarioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Funcionario> updateFuncionario(FuncionarioMaintenanceRequest request)
{
	InternalResultsResponse<Funcionario> response =
			processFuncionario(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFuncionarioBAC#deleteFuncionario(com.qat.samples.sysmgmt.model.request.FuncionarioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Funcionario> deleteFuncionario(FuncionarioMaintenanceRequest request)
{
	InternalResultsResponse<Funcionario> response =
			processFuncionario(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFuncionarioBAC#refreshFuncionarios(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Funcionario> refreshFuncionarios(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getDpBAR().deleteAllFuncionarios();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getDpBAR().insertFuncionario(new Funcionario(i, "FuncionarioDesc" + i));
	}

	// Call maintain to see if we need to return the funcionario list and if so whether it should be paged or not
	return maintainReturnListFuncionario(request.getReturnList(), request.getReturnListPaged(),new Funcionario());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFuncionarioBAC#fetchAllFuncionarios(Funcionario funcionario)
 */
@Override
public InternalResultsResponse<Funcionario> fetchAllFuncionarios(Funcionario funcionario)
{
	InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
	response.getResultsList().addAll(getDpBAR().fetchAllFuncionarios(funcionario).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFuncionarioBAC#fetchFuncionarioById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request)
{
	InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getDpBAR().fetchFuncionarioById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFuncionarioBAC#fetchFuncionariosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Funcionario> fetchFuncionariosByRequest(FuncionarioInquiryRequest request)
{
	return getDpBAR().fetchFuncionariosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the funcionario response
 */
private InternalResultsResponse<Funcionario> processFuncionario(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		FuncionarioMaintenanceRequest request)
		{
	InternalResultsResponse<Funcionario> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Funcionario.class.getSimpleName(), request.getFuncionario(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Funcionario>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceFuncionario(request.getFuncionario(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Funcionario>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_DP_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the funcionario list and if so whether it should be paged or
		// not
		response = maintainReturnListFuncionario(request.getReturnList(), request.getReturnListPaged(),new Funcionario());

		return response;
			}

	/**
	 * Do persistenceFuncionario.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceFuncionario(Funcionario funcionario, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getDpBAR().insertFuncionario(funcionario);

			case UPDATE:
				return getDpBAR().updateFuncionario(funcionario);

			case DELETE:
				return getDpBAR().deleteFuncionarioById(funcionario);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Funcionario> maintainReturnListFuncionario(Boolean listIndicator, Boolean pageListIndicator,Funcionario funcionario)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				FuncionarioInquiryRequest request = new FuncionarioInquiryRequest();
				request.setPreQueryCount(true);
				return fetchFuncionariosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllFuncionarios(funcionario);
			}
		}
		else
		{
			return new InternalResultsResponse<Funcionario>();
		}
	}

//===================================### EVENTOS ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertEventos(com.qat.samples.sysmgmt.model.request.EventosMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Eventos> insertEventos(EventosMaintenanceRequest request)
{
	InternalResultsResponse<Eventos> response =
			processEventos(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEventosBAC#updateEventos(com.qat.samples.sysmgmt.model.request.EventosMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Eventos> updateEventos(EventosMaintenanceRequest request)
{
	InternalResultsResponse<Eventos> response =
			processEventos(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEventosBAC#deleteEventos(com.qat.samples.sysmgmt.model.request.EventosMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Eventos> deleteEventos(EventosMaintenanceRequest request)
{
	InternalResultsResponse<Eventos> response =
			processEventos(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEventosBAC#refreshEventoss(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Eventos> refreshEventoss(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getDpBAR().deleteAllEventos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getDpBAR().insertEventos(new Eventos(i, "EventosDesc" + i));
	}

	// Call maintain to see if we need to return the eventos list and if so whether it should be paged or not
	return maintainReturnListEventos(request.getReturnList(), request.getReturnListPaged(),new Eventos());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEventosBAC#fetchAllEventoss(Eventos eventos)
 */
@Override
public InternalResultsResponse<Eventos> fetchAllEventoss(Eventos eventos)
{
	InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
	response.getResultsList().addAll(getDpBAR().fetchAllEventos(eventos).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEventosBAC#fetchEventosById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Eventos> fetchEventosById(FetchByIdRequest request)
{
	InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getDpBAR().fetchEventosById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEventosBAC#fetchEventossByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Eventos> fetchEventossByRequest(EventoInquiryRequest request)
{
	return getDpBAR().fetchEventosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the eventos response
 */
private InternalResultsResponse<Eventos> processEventos(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		EventosMaintenanceRequest request)
		{
	InternalResultsResponse<Eventos> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Eventos.class.getSimpleName(), request.getEventos(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Eventos>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceEventos(request.getEventos(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Eventos>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_DP_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the eventos list and if so whether it should be paged or
		// not
		response = maintainReturnListEventos(request.getReturnList(), request.getReturnListPaged(),new Eventos());

		return response;
			}

	/**
	 * Do persistenceEventos.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceEventos(Eventos eventos, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getDpBAR().insertEventos(eventos);

			case UPDATE:
				return getDpBAR().updateEventos(eventos);

			case DELETE:
				return getDpBAR().deleteEventosById(eventos);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Eventos> maintainReturnListEventos(Boolean listIndicator, Boolean pageListIndicator,Eventos eventos)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				EventoInquiryRequest request = new EventoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchEventossByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllEventoss(eventos);
			}
		}
		else
		{
			return new InternalResultsResponse<Eventos>();
		}
	}

//===================================### BENEFICIOS ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertBeneficios(com.qat.samples.sysmgmt.model.request.BeneficiosMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Beneficios> insertBeneficios(BeneficiosMaintenanceRequest request)
{
	InternalResultsResponse<Beneficios> response =
			processBeneficios(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IBeneficiosBAC#updateBeneficios(com.qat.samples.sysmgmt.model.request.BeneficiosMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Beneficios> updateBeneficios(BeneficiosMaintenanceRequest request)
{
	InternalResultsResponse<Beneficios> response =
			processBeneficios(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IBeneficiosBAC#deleteBeneficios(com.qat.samples.sysmgmt.model.request.BeneficiosMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Beneficios> deleteBeneficios(BeneficiosMaintenanceRequest request)
{
	InternalResultsResponse<Beneficios> response =
			processBeneficios(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IBeneficiosBAC#refreshBeneficioss(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Beneficios> refreshBeneficioss(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getDpBAR().deleteAllBeneficioss();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getDpBAR().insertBeneficios(new Beneficios(i, "BeneficiosDesc" + i));
	}

	// Call maintain to see if we need to return the beneficios list and if so whether it should be paged or not
	return maintainReturnListBeneficios(request.getReturnList(), request.getReturnListPaged(),new Beneficios());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IBeneficiosBAC#fetchAllBeneficioss(Beneficios beneficios)
 */
@Override
public InternalResultsResponse<Beneficios> fetchAllBeneficioss(Beneficios beneficios)
{
	InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();
	response.getResultsList().addAll(getDpBAR().fetchAllBeneficioss(beneficios).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IBeneficiosBAC#fetchBeneficiosById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Beneficios> fetchBeneficiosById(FetchByIdRequest request)
{
	InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getDpBAR().fetchBeneficiosById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IBeneficiosBAC#fetchBeneficiossByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Beneficios> fetchBeneficiossByRequest(BeneficiosInquiryRequest request)
{
	return getDpBAR().fetchBeneficiossByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the beneficios response
 */
private InternalResultsResponse<Beneficios> processBeneficios(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		BeneficiosMaintenanceRequest request)
		{
	InternalResultsResponse<Beneficios> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Beneficios.class.getSimpleName(), request.getBeneficios(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Beneficios>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceBeneficios(request.getBeneficios(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Beneficios>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_DP_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the beneficios list and if so whether it should be paged or
		// not
		response = maintainReturnListBeneficios(request.getReturnList(), request.getReturnListPaged(),new Beneficios());

		return response;
			}

	/**
	 * Do persistenceBeneficios.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceBeneficios(Beneficios beneficios, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getDpBAR().insertBeneficios(beneficios);

			case UPDATE:
				return getDpBAR().updateBeneficios(beneficios);

			case DELETE:
				return getDpBAR().deleteBeneficiosById(beneficios);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Beneficios> maintainReturnListBeneficios(Boolean listIndicator, Boolean pageListIndicator,Beneficios beneficios)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				BeneficiosInquiryRequest request = new BeneficiosInquiryRequest();
				request.setPreQueryCount(true);
				return fetchBeneficiossByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllBeneficioss(beneficios);
			}
		}
		else
		{
			return new InternalResultsResponse<Beneficios>();
		}
	}

//===================================### HORAFUNC ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertHorarioFunc(com.qat.samples.sysmgmt.model.request.HorarioFuncMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<HorarioFunc> insertHoraFunc(HoraFuncMaintenanceRequest request)
{
	InternalResultsResponse<HorarioFunc> response =
			processHorarioFunc(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IHorarioFuncBAC#updateHorarioFunc(com.qat.samples.sysmgmt.model.request.HorarioFuncMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<HorarioFunc> updateHoraFunc(HoraFuncMaintenanceRequest request)
{
	InternalResultsResponse<HorarioFunc> response =
			processHorarioFunc(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IHorarioFuncBAC#deleteHorarioFunc(com.qat.samples.sysmgmt.model.request.HorarioFuncMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<HorarioFunc> deleteHoraFunc(HoraFuncMaintenanceRequest request)
{
	InternalResultsResponse<HorarioFunc> response =
			processHorarioFunc(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IHorarioFuncBAC#refreshHorarioFuncs(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<HorarioFunc> refreshHoraFuncs(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getDpBAR().deleteAllHorafuncs();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getDpBAR().insertHorafunc(new HorarioFunc(i, "HorarioFuncDesc" + i));
	}

	// Call maintain to see if we need to return the horafunc list and if so whether it should be paged or not
	return maintainReturnListHorarioFunc(request.getReturnList(), request.getReturnListPaged(),new HorarioFunc());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IHorarioFuncBAC#fetchAllHorarioFuncs(HorarioFunc horafunc)
 */
@Override
public InternalResultsResponse<HorarioFunc> fetchAllHoraFuncs(HorarioFunc horafunc)
{
	InternalResultsResponse<HorarioFunc> response = new InternalResultsResponse<HorarioFunc>();
	response.getResultsList().addAll(getDpBAR().fetchAllHorafuncs(horafunc).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IHorarioFuncBAC#fetchHorarioFuncById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<HorarioFunc> fetchHoraFuncById(FetchByIdRequest request)
{
	InternalResultsResponse<HorarioFunc> response = new InternalResultsResponse<HorarioFunc>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getDpBAR().fetchHorafuncById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IHorarioFuncBAC#fetchHorarioFuncsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<HorarioFunc> fetchHoraFuncsByRequest(HoraFuncInquiryRequest request)
{
	return getDpBAR().fetchHorafuncsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the horafunc response
 */
private InternalResultsResponse<HorarioFunc> processHorarioFunc(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		HoraFuncMaintenanceRequest request)
		{
	InternalResultsResponse<HorarioFunc> response = null;

	// Validate
	ValidationContext context = new ValidationContext(HorarioFunc.class.getSimpleName(), request.getHorarioFunc(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<HorarioFunc>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceHorarioFunc(request.getHorarioFunc(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<HorarioFunc>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_DP_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the horafunc list and if so whether it should be paged or
		// not
		response = maintainReturnListHorarioFunc(request.getReturnList(), request.getReturnListPaged(),new HorarioFunc());

		return response;
			}

	/**
	 * Do persistenceHorarioFunc.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceHorarioFunc(HorarioFunc horafunc, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getDpBAR().insertHorafunc(horafunc);

			case UPDATE:
				return getDpBAR().updateHorafunc(horafunc);

			case DELETE:
				return getDpBAR().deleteHorafuncById(horafunc);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<HorarioFunc> maintainReturnListHorarioFunc(Boolean listIndicator, Boolean pageListIndicator,HorarioFunc horafunc)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				HoraFuncInquiryRequest request = new HoraFuncInquiryRequest();
				request.setPreQueryCount(true);
				return fetchHoraFuncsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllHoraFuncs(horafunc);
			}
		}
		else
		{
			return new InternalResultsResponse<HorarioFunc>();
		}
	}




}
