/** create by system gera-java version 1.0.0 15/09/2017 16:12 : 30*/
package com.qat.samples.sysmgmt.bac.Advogado;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.MessageLevel;
import com.qat.framework.model.MessageSeverity;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.SystemErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.advocacia.Advogados;
import com.qat.samples.sysmgmt.advocacia.Envolvidos;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.ProcessoCliente;
import com.qat.samples.sysmgmt.advocacia.ProcessoStatus;
import com.qat.samples.sysmgmt.advocacia.request.CompromissoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.EspecialidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoMaintenanceRequest;
import com.qat.samples.sysmgmt.arquivo.model.Arquivo;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoMaintenanceRequest;
import com.qat.samples.sysmgmt.bar.Advogado.IAdvocaciaBAR;
import com.qat.samples.sysmgmt.clinica.model.Especialidade;
import com.qat.samples.sysmgmt.util.model.Compromisso;
import com.qat.samples.sysmgmt.util.model.DiasHoras;
import com.qat.samples.sysmgmt.util.model.ParticipanteExterno;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Advogado leveraging the injected IAdvogadoBAR.
 */
@Component
public class AdvocaciaBACImpl implements IAdvocaciaBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG = "sysmgmt.base.Advogadobacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AdvocaciaBACImpl.class);

	/** The Advogado BAR. */
	private IAdvocaciaBAR advocaciaBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter


	public IAdvocaciaBAR getAdvocaciaBAR() {
		return advocaciaBAR;
	}

	public void setAdvocaciaBAR(IAdvocaciaBAR advocaciaBAR) {
		this.advocaciaBAR = advocaciaBAR;
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

//===================================### PROCESSOSTATUS ####======================================


/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProcessoStatusBAC#refreshProcessoStatuss(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ProcessoStatus> refreshProcessoStatuss(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getAdvocaciaBAR().deleteAllProcessoStatuss();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	//getAdvocaciaBAR().insertProcessoStatus(new ProcessoStatus(i, "ProcessoStatusDesc" + i));
	}

	// Call maintain to see if we need to return the processostatus list and if so whether it should be paged or not
	return maintainReturnListProcessoStatus(request.getReturnList(), request.getReturnListPaged(),new ProcessoStatus());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProcessoStatusBAC#fetchAllProcessoStatuss(ProcessoStatus processostatus)
 */
@Override
public InternalResultsResponse<ProcessoStatus> fetchAllProcessoStatuss(ProcessoStatus processostatus)
{
	InternalResultsResponse<ProcessoStatus> response = new InternalResultsResponse<ProcessoStatus>();
	response.getResultsList().addAll(getAdvocaciaBAR().fetchAllProcessoStatuss(processostatus).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProcessoStatusBAC#fetchProcessoStatusById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ProcessoStatus> fetchProcessoStatusById(FetchByIdRequest request)
{
	InternalResultsResponse<ProcessoStatus> response = new InternalResultsResponse<ProcessoStatus>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getAdvocaciaBAR().fetchProcessoStatusById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProcessoStatusBAC#fetchProcessoStatussByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ProcessoStatus> fetchProcessoStatussByRequest(PagedInquiryRequest request)
{
	return getAdvocaciaBAR().fetchProcessoStatussByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the processostatus response
 */


	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<ProcessoStatus> maintainReturnListProcessoStatus(Boolean listIndicator, Boolean pageListIndicator,ProcessoStatus processostatus)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchProcessoStatussByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllProcessoStatuss(processostatus);
			}
		}
		else
		{
			return new InternalResultsResponse<ProcessoStatus>();
		}
	}

//===================================### DIASHORAS ####======================================


/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IDiasHorasBAC#refreshDiasHorass(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<DiasHoras> refreshDiasHorass(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getAdvocaciaBAR().deleteAllDiasHorass();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getAdvocaciaBAR().insertDiasHoras(new DiasHoras(i, "DiasHorasDesc" + i));
	}

	// Call maintain to see if we need to return the diashoras list and if so whether it should be paged or not
	return maintainReturnListDiasHoras(request.getReturnList(), request.getReturnListPaged(),new DiasHoras());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IDiasHorasBAC#fetchAllDiasHorass(DiasHoras diashoras)
 */
@Override
public InternalResultsResponse<DiasHoras> fetchAllDiasHorass(DiasHoras diashoras)
{
	InternalResultsResponse<DiasHoras> response = new InternalResultsResponse<DiasHoras>();
	response.getResultsList().addAll(getAdvocaciaBAR().fetchAllDiasHorass(diashoras).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IDiasHorasBAC#fetchDiasHorasById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<DiasHoras> fetchDiasHorasById(FetchByIdRequest request)
{
	InternalResultsResponse<DiasHoras> response = new InternalResultsResponse<DiasHoras>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getAdvocaciaBAR().fetchDiasHorasById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IDiasHorasBAC#fetchDiasHorassByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<DiasHoras> fetchDiasHorassByRequest(PagedInquiryRequest request)
{
	return getAdvocaciaBAR().fetchDiasHorassByRequest(request);
}



	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<DiasHoras> maintainReturnListDiasHoras(Boolean listIndicator, Boolean pageListIndicator,DiasHoras diashoras)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchDiasHorassByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllDiasHorass(diashoras);
			}
		}
		else
		{
			return new InternalResultsResponse<DiasHoras>();
		}
	}

//===================================### ESPECIALIDADE ####======================================


/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEspecialidadeBAC#refreshEspecialidades(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Especialidade> refreshEspecialidades(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getAdvocaciaBAR().deleteAllEspecialidades();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
//	getAdvocaciaBAR().insertEspecialidade(new Especialidade(i, "EspecialidadeDesc" + i));
	}

	// Call maintain to see if we need to return the especialidade list and if so whether it should be paged or not
	return maintainReturnListEspecialidade(request.getReturnList(), request.getReturnListPaged(),new Especialidade());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEspecialidadeBAC#fetchAllEspecialidades(Especialidade especialidade)
 */
@Override
public InternalResultsResponse<Especialidade> fetchAllEspecialidades(Especialidade especialidade)
{
	InternalResultsResponse<Especialidade> response = new InternalResultsResponse<Especialidade>();
	response.getResultsList().addAll(getAdvocaciaBAR().fetchAllEspecialidades(especialidade).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEspecialidadeBAC#fetchEspecialidadeById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Especialidade> fetchEspecialidadeById(FetchByIdRequest request)
{
	InternalResultsResponse<Especialidade> response = new InternalResultsResponse<Especialidade>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getAdvocaciaBAR().fetchEspecialidadeById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEspecialidadeBAC#fetchEspecialidadesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Especialidade> fetchEspecialidadesByRequest(PagedInquiryRequest request)
{
	return getAdvocaciaBAR().fetchEspecialidadesByRequest(request);
}


	/**
	 * Do persistenceEspecialidade.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceEspecialidade(Especialidade especialidade, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getAdvocaciaBAR().insertEspecialidade(especialidade);

			case UPDATE:
				return getAdvocaciaBAR().updateEspecialidade(especialidade);

			case DELETE:
				return getAdvocaciaBAR().deleteEspecialidadeById(especialidade);
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
	private InternalResultsResponse<Especialidade> maintainReturnListEspecialidade(Boolean listIndicator, Boolean pageListIndicator,Especialidade especialidade)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchEspecialidadesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllEspecialidades(especialidade);
			}
		}
		else
		{
			return new InternalResultsResponse<Especialidade>();
		}
	}

//===================================### COMPROMISSO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCompromisso(com.qat.samples.sysmgmt.model.request.CompromissoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Compromisso> insertCompromisso(CompromissoMaintenanceRequest request)
{
	InternalResultsResponse<Compromisso> response =
			processCompromisso(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICompromissoBAC#updateCompromisso(com.qat.samples.sysmgmt.model.request.CompromissoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Compromisso> updateCompromisso(CompromissoMaintenanceRequest request)
{
	InternalResultsResponse<Compromisso> response =
			processCompromisso(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICompromissoBAC#deleteCompromisso(com.qat.samples.sysmgmt.model.request.CompromissoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Compromisso> deleteCompromisso(CompromissoMaintenanceRequest request)
{
	InternalResultsResponse<Compromisso> response =
			processCompromisso(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICompromissoBAC#refreshCompromissos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Compromisso> refreshCompromissos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getAdvocaciaBAR().deleteAllCompromissos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getAdvocaciaBAR().insertCompromisso(new Compromisso(i, "CompromissoDesc" + i));
	}

	// Call maintain to see if we need to return the compromisso list and if so whether it should be paged or not
	return maintainReturnListCompromisso(request.getReturnList(), request.getReturnListPaged(),new Compromisso());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICompromissoBAC#fetchAllCompromissos(Compromisso compromisso)
 */
@Override
public InternalResultsResponse<Compromisso> fetchAllCompromissos(Compromisso compromisso)
{
	InternalResultsResponse<Compromisso> response = new InternalResultsResponse<Compromisso>();
	response.getResultsList().addAll(getAdvocaciaBAR().fetchAllCompromissos(compromisso).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICompromissoBAC#fetchCompromissoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Compromisso> fetchCompromissoById(FetchByIdRequest request)
{
	InternalResultsResponse<Compromisso> response = new InternalResultsResponse<Compromisso>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getAdvocaciaBAR().fetchCompromissoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICompromissoBAC#fetchCompromissosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Compromisso> fetchCompromissosByRequest(PagedInquiryRequest request)
{
	return getAdvocaciaBAR().fetchCompromissosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the compromisso response
 */
private InternalResultsResponse<Compromisso> processCompromisso(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		CompromissoMaintenanceRequest request)
		{
	InternalResultsResponse<Compromisso> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(Compromisso.class.getSimpleName(), request.getCompromisso(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<Compromisso>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceCompromisso(request.getCompromisso(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Compromisso>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the compromisso list and if so whether it should be paged or
		// not
		response = maintainReturnListCompromisso(request.getReturnList(), request.getReturnListPaged(),new Compromisso());

		return response;
			}

	/**
	 * Do persistenceCompromisso.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceCompromisso(Compromisso compromisso, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getAdvocaciaBAR().insertCompromisso(compromisso);

			case UPDATE:
				return getAdvocaciaBAR().updateCompromisso(compromisso);

			case DELETE:
				return getAdvocaciaBAR().deleteCompromissoById(compromisso);
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
	private InternalResultsResponse<Compromisso> maintainReturnListCompromisso(Boolean listIndicator, Boolean pageListIndicator,Compromisso compromisso)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchCompromissosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllCompromissos(compromisso);
			}
		}
		else
		{
			return new InternalResultsResponse<Compromisso>();
		}
	}

//===================================### ADVOGADOS ####======================================

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAdvogadosBAC#refreshAdvogadoss(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Advogados> refreshAdvogadoss(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getAdvocaciaBAR().deleteAllAdvogadoss();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	//getAdvocaciaBAR().insertAdvogados(new Advogados(i, "AdvogadosDesc" + i));
	}

	// Call maintain to see if we need to return the advogados list and if so whether it should be paged or not
	return maintainReturnListAdvogados(request.getReturnList(), request.getReturnListPaged(),new Advogados());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAdvogadosBAC#fetchAllAdvogadoss(Advogados advogados)
 */
@Override
public InternalResultsResponse<Advogados> fetchAllAdvogadoss(Advogados advogados)
{
	InternalResultsResponse<Advogados> response = new InternalResultsResponse<Advogados>();
	response.getResultsList().addAll(getAdvocaciaBAR().fetchAllAdvogadoss(advogados).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAdvogadosBAC#fetchAdvogadosById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Advogados> fetchAdvogadosById(FetchByIdRequest request)
{
	InternalResultsResponse<Advogados> response = new InternalResultsResponse<Advogados>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getAdvocaciaBAR().fetchAdvogadosById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAdvogadosBAC#fetchAdvogadossByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Advogados> fetchAdvogadossByRequest(PagedInquiryRequest request)
{
	return getAdvocaciaBAR().fetchAdvogadossByRequest(request);
}




	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Advogados> maintainReturnListAdvogados(Boolean listIndicator, Boolean pageListIndicator,Advogados advogados)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchAdvogadossByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllAdvogadoss(advogados);
			}
		}
		else
		{
			return new InternalResultsResponse<Advogados>();
		}
	}

//===================================### ENVOLVIDOS ####======================================


/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEnvolvidosBAC#refreshEnvolvidoss(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Envolvidos> refreshEnvolvidoss(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getAdvocaciaBAR().deleteAllEnvolvidoss();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	//getAdvocaciaBAR().insertEnvolvidos(new Envolvidos(i, "EnvolvidosDesc" + i));
	}

	// Call maintain to see if we need to return the envolvidos list and if so whether it should be paged or not
	return maintainReturnListEnvolvidos(request.getReturnList(), request.getReturnListPaged(),new Envolvidos());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEnvolvidosBAC#fetchAllEnvolvidoss(Envolvidos envolvidos)
 */
@Override
public InternalResultsResponse<Envolvidos> fetchAllEnvolvidoss(Envolvidos envolvidos)
{
	InternalResultsResponse<Envolvidos> response = new InternalResultsResponse<Envolvidos>();
	response.getResultsList().addAll(getAdvocaciaBAR().fetchAllEnvolvidoss(envolvidos).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEnvolvidosBAC#fetchEnvolvidosById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Envolvidos> fetchEnvolvidosById(FetchByIdRequest request)
{
	InternalResultsResponse<Envolvidos> response = new InternalResultsResponse<Envolvidos>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getAdvocaciaBAR().fetchEnvolvidosById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEnvolvidosBAC#fetchEnvolvidossByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Envolvidos> fetchEnvolvidossByRequest(PagedInquiryRequest request)
{
	return getAdvocaciaBAR().fetchEnvolvidossByRequest(request);
}


	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Envolvidos> maintainReturnListEnvolvidos(Boolean listIndicator, Boolean pageListIndicator,Envolvidos envolvidos)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchEnvolvidossByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllEnvolvidoss(envolvidos);
			}
		}
		else
		{
			return new InternalResultsResponse<Envolvidos>();
		}
	}

//===================================### PARTICIPANTEEXTERNO ####======================================

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IParticipanteExternoBAC#refreshParticipanteExternos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ParticipanteExterno> refreshParticipanteExternos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getAdvocaciaBAR().deleteAllParticipanteExternos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
//	getAdvocaciaBAR().insertParticipanteExterno(new ParticipanteExterno(i, "ParticipanteExternoDesc" + i));
	}

	// Call maintain to see if we need to return the participanteexterno list and if so whether it should be paged or not
	return maintainReturnListParticipanteExterno(request.getReturnList(), request.getReturnListPaged(),new ParticipanteExterno());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IParticipanteExternoBAC#fetchAllParticipanteExternos(ParticipanteExterno participanteexterno)
 */
@Override
public InternalResultsResponse<ParticipanteExterno> fetchAllParticipanteExternos(ParticipanteExterno participanteexterno)
{
	InternalResultsResponse<ParticipanteExterno> response = new InternalResultsResponse<ParticipanteExterno>();
	response.getResultsList().addAll(getAdvocaciaBAR().fetchAllParticipanteExternos(participanteexterno).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IParticipanteExternoBAC#fetchParticipanteExternoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ParticipanteExterno> fetchParticipanteExternoById(FetchByIdRequest request)
{
	InternalResultsResponse<ParticipanteExterno> response = new InternalResultsResponse<ParticipanteExterno>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getAdvocaciaBAR().fetchParticipanteExternoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IParticipanteExternoBAC#fetchParticipanteExternosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ParticipanteExterno> fetchParticipanteExternosByRequest(PagedInquiryRequest request)
{
	return getAdvocaciaBAR().fetchParticipanteExternosByRequest(request);
}



	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<ParticipanteExterno> maintainReturnListParticipanteExterno(Boolean listIndicator, Boolean pageListIndicator,ParticipanteExterno participanteexterno)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchParticipanteExternosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllParticipanteExternos(participanteexterno);
			}
		}
		else
		{
			return new InternalResultsResponse<ParticipanteExterno>();
		}
	}

//===================================### PROCESSO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertProcesso(com.qat.samples.sysmgmt.model.request.ProcessoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Processo> insertProcesso(ProcessoMaintenanceRequest request)
{
	InternalResultsResponse<Processo> response =
			processProcesso(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProcessoBAC#updateProcesso(com.qat.samples.sysmgmt.model.request.ProcessoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Processo> updateProcesso(ProcessoMaintenanceRequest request)
{
	InternalResultsResponse<Processo> response =
			processProcesso(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProcessoBAC#deleteProcesso(com.qat.samples.sysmgmt.model.request.ProcessoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Processo> deleteProcesso(ProcessoMaintenanceRequest request)
{
	InternalResultsResponse<Processo> response =
			processProcesso(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProcessoBAC#refreshProcessos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Processo> refreshProcessos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getAdvocaciaBAR().deleteAllProcessos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getAdvocaciaBAR().insertProcesso(new Processo(i, "ProcessoDesc" + i));
	}

	// Call maintain to see if we need to return the processo list and if so whether it should be paged or not
	return maintainReturnListProcesso(request.getReturnList(), request.getReturnListPaged(),new Processo());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProcessoBAC#fetchAllProcessos(Processo processo)
 */
@Override
public InternalResultsResponse<Processo> fetchAllProcessos(Processo processo)
{
	InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();
	response.getResultsList().addAll(getAdvocaciaBAR().fetchAllProcessos(processo).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProcessoBAC#fetchProcessoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Processo> fetchProcessoById(FetchByIdRequest request)
{
	InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getAdvocaciaBAR().fetchProcessoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProcessoBAC#fetchProcessosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Processo> fetchProcessosByRequest(ProcessoInquiryRequest request)
{
	return getAdvocaciaBAR().fetchProcessosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the processo response
 */
private InternalResultsResponse<Processo> processProcesso(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ProcessoMaintenanceRequest request)
		{
	InternalResultsResponse<Processo> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(Processo.class.getSimpleName(), request.getProcesso(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<Processo>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceProcesso(request.getProcesso(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Processo>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the processo list and if so whether it should be paged or
		// not
		response = maintainReturnListProcesso(request.getReturnList(), request.getReturnListPaged(),new Processo());

		return response;
			}

	/**
	 * Do persistenceProcesso.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceProcesso(Processo processo, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getAdvocaciaBAR().insertProcesso(processo);

			case UPDATE:
				return getAdvocaciaBAR().updateProcesso(processo);

			case DELETE:
				return getAdvocaciaBAR().deleteProcessoById(processo);
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
	private InternalResultsResponse<Processo> maintainReturnListProcesso(Boolean listIndicator, Boolean pageListIndicator,Processo processo)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ProcessoInquiryRequest request = new ProcessoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchProcessosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllProcessos(processo);
			}
		}
		else
		{
			return new InternalResultsResponse<Processo>();
		}
	}

	//===================================### ESPECIALIDADE ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertEspecialidade(com.qat.samples.sysmgmt.model.request.EspecialidadeMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Especialidade> insertEspecialidade(EspecialidadeMaintenanceRequest request)
	{
		InternalResultsResponse<Especialidade> response =
				processEspecialidade(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IEspecialidadeBAC#updateEspecialidade(com.qat.samples.sysmgmt.model.request.EspecialidadeMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Especialidade> updateEspecialidade(EspecialidadeMaintenanceRequest request)
	{
		InternalResultsResponse<Especialidade> response =
				processEspecialidade(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IEspecialidadeBAC#deleteEspecialidade(com.qat.samples.sysmgmt.model.request.EspecialidadeMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Especialidade> deleteEspecialidade(EspecialidadeMaintenanceRequest request)
	{
		InternalResultsResponse<Especialidade> response =
				processEspecialidade(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}



	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the especialidade response
	 */
	private InternalResultsResponse<Especialidade> processEspecialidade(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			EspecialidadeMaintenanceRequest request)
			{
		InternalResultsResponse<Especialidade> response = null;

		// Validate
		//ValidationContext context = new ValidationContext(Especialidade.class.getSimpleName(), request.getEspecialidade(), indicator);
		//if (!getValidationController().validate(context))
		//{
		//	response = new InternalResultsResponse<Especialidade>();
		//	response.setStatus(SystemErrorCategory.SystemValidation);
		//	response.addMessages(context.getMessages());
		//	return response;
		//}

			// Persist
			InternalResponse internalResponse = doPersistenceEspecialidade(request.getEspecialidade(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<Especialidade>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the especialidade list and if so whether it should be paged or
			// not
			response = maintainReturnListEspecialidade(request.getReturnList(), request.getReturnListPaged(),new Especialidade());

			return response;
				}


	//===================================### ARQUIVO ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertArquivo(com.qat.samples.sysmgmt.model.request.ArquivoMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Arquivo> insertArquivo(ArquivoMaintenanceRequest request)
	{
		InternalResultsResponse<Arquivo> response =
				processArquivo(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IArquivoBAC#updateArquivo(com.qat.samples.sysmgmt.model.request.ArquivoMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Arquivo> updateArquivo(ArquivoMaintenanceRequest request)
	{
		InternalResultsResponse<Arquivo> response =
				processArquivo(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IArquivoBAC#deleteArquivo(com.qat.samples.sysmgmt.model.request.ArquivoMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Arquivo> deleteArquivo(ArquivoMaintenanceRequest request)
	{
		InternalResultsResponse<Arquivo> response =
				processArquivo(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IArquivoBAC#refreshArquivos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<Arquivo> refreshArquivos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getAdvocaciaBAR().deleteAllArquivos();
		int refreshNumber = request.getRefreshInt();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
		getAdvocaciaBAR().insertArquivo(new Arquivo(i, "ArquivoDesc" + i));
		}

		// Call maintain to see if we need to return the arquivo list and if so whether it should be paged or not
		return maintainReturnListArquivo(request.getReturnList(), request.getReturnListPaged(),new Arquivo());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IArquivoBAC#fetchAllArquivos(Arquivo arquivo)
	 */
	@Override
	public InternalResultsResponse<Arquivo> fetchAllArquivos(Arquivo arquivo)
	{
		InternalResultsResponse<Arquivo> response = new InternalResultsResponse<Arquivo>();
		response.getResultsList().addAll(getAdvocaciaBAR().fetchAllArquivos(arquivo).getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IArquivoBAC#fetchArquivoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Arquivo> fetchArquivoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Arquivo> response = new InternalResultsResponse<Arquivo>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getAdvocaciaBAR().fetchArquivoById(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IArquivoBAC#fetchArquivosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Arquivo> fetchArquivosByRequest(PagedInquiryRequest request)
	{
		return getAdvocaciaBAR().fetchArquivosByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the arquivo response
	 */
	private InternalResultsResponse<Arquivo> processArquivo(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			ArquivoMaintenanceRequest request)
			{
		InternalResultsResponse<Arquivo> response = null;

		// Validate
		//ValidationContext context = new ValidationContext(Arquivo.class.getSimpleName(), request.getArquivo(), indicator);
		//if (!getValidationController().validate(context))
		//{
		//	response = new InternalResultsResponse<Arquivo>();
		//	response.setStatus(SystemErrorCategory.SystemValidation);
		//	response.addMessages(context.getMessages());
		//	return response;
		//}

			// Persist
			InternalResponse internalResponse = doPersistenceArquivo(request.getArquivo(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<Arquivo>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the arquivo list and if so whether it should be paged or
			// not
			response = maintainReturnListArquivo(request.getReturnList(), request.getReturnListPaged(),new Arquivo());

			return response;
				}

		/**
		 * Do persistenceArquivo.
		 *
		 * @param request the request
		 * @param updateType the update type
		 * @return the internal response
		 */
		private InternalResponse doPersistenceArquivo(Arquivo arquivo, PersistenceActionEnum updateType)
		{
			switch (updateType)
			{
				case INSERT:
					return getAdvocaciaBAR().insertArquivo(arquivo);

				case UPDATE:
					return getAdvocaciaBAR().updateArquivo(arquivo);

				case DELETE:
					return getAdvocaciaBAR().deleteArquivoById(arquivo);
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
		private InternalResultsResponse<Arquivo> maintainReturnListArquivo(Boolean listIndicator, Boolean pageListIndicator,Arquivo arquivo)
		{
			// Fetch again if requested.
			if (listIndicator)
			{
				// Fetch Paged is requested.
				if (pageListIndicator)
				{
					PagedInquiryRequest request = new PagedInquiryRequest();
					request.setPreQueryCount(true);
					return fetchArquivosByRequest(request);
				}
				else
				{
					// otherwise return all rows not paged
					return fetchAllArquivos(arquivo);
				}
			}
			else
			{
				return new InternalResultsResponse<Arquivo>();
			}
		}
	@Override
	public InternalResultsResponse<ProcessoCliente> fetchProcessoClientesByRequest(PagedInquiryRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
