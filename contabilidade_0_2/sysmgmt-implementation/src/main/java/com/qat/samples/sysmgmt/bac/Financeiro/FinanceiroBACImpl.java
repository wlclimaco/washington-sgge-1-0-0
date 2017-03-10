package com.qat.samples.sysmgmt.bac.Financeiro;


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
import com.qat.samples.sysmgmt.agencia.model.Agencia;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaMaintenanceRequest;
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.banco.model.request.BancoMaintenanceRequest;
import com.qat.samples.sysmgmt.bar.Financeiro.IFinanceiroBAR;
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.financeiro.model.BaixaTitulo;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.financeiro.model.request.BaixaTituloInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.BaixaTituloMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContaInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Financeiro leveraging the injected IFinanceiroBAR.
 */
@Component
public class FinanceiroBACImpl implements IFinanceiroBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_FINANCEIRO_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_FINANCEIRO_BAC_EXCEPTION_MSG = "sysmgmt.base.Financeirobacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FinanceiroBACImpl.class);

	/** The Financeiro BAR. */
	private IFinanceiroBAR financeiroBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Financeiro BAR.
	 *
	 * @param FinanceiroBAR the new Financeiro BAR
	 */
	public void setFinanceiroBAR(IFinanceiroBAR financeiroBAR)
	{
		this.financeiroBAR = financeiroBAR;
	}

	/**
	 * Gets the Financeiro BAR.
	 *
	 * @return the Financeiro BAR
	 */
	public IFinanceiroBAR getFinanceiroBAR()
	{
		return financeiroBAR;
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

//===================================### CONTASPAGAR ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertContasPagar(com.qat.samples.sysmgmt.model.request.ContasPagarMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ContasPagar> insertContasPagar(ContasPagarMaintenanceRequest request)
{
	InternalResultsResponse<ContasPagar> response =
			processContasPagar(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IContasPagarBAC#updateContasPagar(com.qat.samples.sysmgmt.model.request.ContasPagarMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ContasPagar> updateContasPagar(ContasPagarMaintenanceRequest request)
{
	InternalResultsResponse<ContasPagar> response =
			processContasPagar(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IContasPagarBAC#deleteContasPagar(com.qat.samples.sysmgmt.model.request.ContasPagarMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ContasPagar> deleteContasPagar(ContasPagarMaintenanceRequest request)
{
	InternalResultsResponse<ContasPagar> response =
			processContasPagar(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IContasPagarBAC#refreshContasPagars(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ContasPagar> refreshContasPagars(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getFinanceiroBAR().deleteAllContasPagars();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getFinanceiroBAR().insertContasPagar(new ContasPagar());
	}

	// Call maintain to see if we need to return the contaspagar list and if so whether it should be paged or not
	return maintainReturnListContasPagar(request.getReturnList(), request.getReturnListPaged(),new ContasPagar());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IContasPagarBAC#fetchAllContasPagars(ContasPagar contaspagar)
 */
@Override
public InternalResultsResponse<ContasPagar> fetchAllContasPagars(ContasPagar contaspagar)
{
	InternalResultsResponse<ContasPagar> response = new InternalResultsResponse<ContasPagar>();
	response.getResultsList().addAll(getFinanceiroBAR().fetchAllContasPagars(contaspagar).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IContasPagarBAC#fetchContasPagarById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ContasPagar> fetchContasPagarById(FetchByIdRequest request)
{
	InternalResultsResponse<ContasPagar> response = new InternalResultsResponse<ContasPagar>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getFinanceiroBAR().fetchContasPagarById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IContasPagarBAC#fetchContasPagarsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ContasPagar> fetchContasPagarsByRequest(ContasPagarInquiryRequest request)
{
	return getFinanceiroBAR().fetchContasPagarsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the contaspagar response
 */
private InternalResultsResponse<ContasPagar> processContasPagar(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ContasPagarMaintenanceRequest request)
		{
	InternalResultsResponse<ContasPagar> response = null;

		// Persist
		InternalResponse internalResponse = doPersistenceContasPagar(request.getContasPagar(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<ContasPagar>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_FINANCEIRO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the contaspagar list and if so whether it should be paged or
		// not
		response = maintainReturnListContasPagar(request.getReturnList(), request.getReturnListPaged(),new ContasPagar());

		return response;
			}

	/**
	 * Do persistenceContasPagar.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceContasPagar(ContasPagar contaspagar, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getFinanceiroBAR().insertContasPagar(contaspagar);

			case UPDATE:
				return getFinanceiroBAR().updateContasPagar(contaspagar);

			case DELETE:
				return getFinanceiroBAR().deleteContasPagarById(contaspagar);
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
	private InternalResultsResponse<ContasPagar> maintainReturnListContasPagar(Boolean listIndicator, Boolean pageListIndicator,ContasPagar contaspagar)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ContasPagarInquiryRequest request = new ContasPagarInquiryRequest();
				request.setPreQueryCount(true);
				return fetchContasPagarsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllContasPagars(contaspagar);
			}
		}
		else
		{
			return new InternalResultsResponse<ContasPagar>();
		}
	}

//===================================### CONTASRECEBER ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertContasReceber(com.qat.samples.sysmgmt.model.request.ContasReceberMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ContasReceber> insertContasReceber(ContasReceberMaintenanceRequest request)
{
	InternalResultsResponse<ContasReceber> response =
			processContasReceber(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IContasReceberBAC#updateContasReceber(com.qat.samples.sysmgmt.model.request.ContasReceberMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ContasReceber> updateContasReceber(ContasReceberMaintenanceRequest request)
{
	InternalResultsResponse<ContasReceber> response =
			processContasReceber(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IContasReceberBAC#deleteContasReceber(com.qat.samples.sysmgmt.model.request.ContasReceberMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ContasReceber> deleteContasReceber(ContasReceberMaintenanceRequest request)
{
	InternalResultsResponse<ContasReceber> response =
			processContasReceber(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IContasReceberBAC#refreshContasRecebers(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ContasReceber> refreshContasRecebers(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getFinanceiroBAR().deleteAllContasRecebers();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getFinanceiroBAR().insertContasReceber(new ContasReceber(i, "ContasReceberDesc" + i));
	}

	// Call maintain to see if we need to return the contasreceber list and if so whether it should be paged or not
	return maintainReturnListContasReceber(request.getReturnList(), request.getReturnListPaged(),new ContasReceber());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IContasReceberBAC#fetchAllContasRecebers(ContasReceber contasreceber)
 */
@Override
public InternalResultsResponse<ContasReceber> fetchAllContasRecebers(ContasReceber contasreceber)
{
	InternalResultsResponse<ContasReceber> response = new InternalResultsResponse<ContasReceber>();
	response.getResultsList().addAll(getFinanceiroBAR().fetchAllContasRecebers(contasreceber).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IContasReceberBAC#fetchContasReceberById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ContasReceber> fetchContasReceberById(FetchByIdRequest request)
{
	InternalResultsResponse<ContasReceber> response = new InternalResultsResponse<ContasReceber>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getFinanceiroBAR().fetchContasReceberById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IContasReceberBAC#fetchContasRecebersByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ContasReceber> fetchContasRecebersByRequest(ContasReceberInquiryRequest request)
{
	return getFinanceiroBAR().fetchContasRecebersByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the contasreceber response
 */
private InternalResultsResponse<ContasReceber> processContasReceber(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ContasReceberMaintenanceRequest request)
		{
	InternalResultsResponse<ContasReceber> response = null;

		// Persist
		InternalResponse internalResponse = doPersistenceContasReceber(request.getContasReceber(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<ContasReceber>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_FINANCEIRO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the contasreceber list and if so whether it should be paged or
		// not
		response = maintainReturnListContasReceber(request.getReturnList(), request.getReturnListPaged(),new ContasReceber());

		return response;
			}

	/**
	 * Do persistenceContasReceber.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceContasReceber(ContasReceber contasreceber, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getFinanceiroBAR().insertContasReceber(contasreceber);

			case UPDATE:
				return getFinanceiroBAR().updateContasReceber(contasreceber);

			case DELETE:
				return getFinanceiroBAR().deleteContasReceberById(contasreceber);
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
	private InternalResultsResponse<ContasReceber> maintainReturnListContasReceber(Boolean listIndicator, Boolean pageListIndicator,ContasReceber contasreceber)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ContasReceberInquiryRequest request = new ContasReceberInquiryRequest();
				request.setPreQueryCount(true);
				return fetchContasRecebersByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllContasRecebers(contasreceber);
			}
		}
		else
		{
			return new InternalResultsResponse<ContasReceber>();
		}
	}

//===================================### CONDPAG ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCondPag(com.qat.samples.sysmgmt.model.request.CondPagMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<CondPag> insertCondPag(CondPagMaintenanceRequest request)
{
	InternalResultsResponse<CondPag> response =
			processCondPag(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICondPagBAC#updateCondPag(com.qat.samples.sysmgmt.model.request.CondPagMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<CondPag> updateCondPag(CondPagMaintenanceRequest request)
{
	InternalResultsResponse<CondPag> response =
			processCondPag(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICondPagBAC#deleteCondPag(com.qat.samples.sysmgmt.model.request.CondPagMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<CondPag> deleteCondPag(CondPagMaintenanceRequest request)
{
	InternalResultsResponse<CondPag> response =
			processCondPag(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICondPagBAC#refreshCondPags(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<CondPag> refreshCondPags(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getFinanceiroBAR().deleteAllCondPags();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getFinanceiroBAR().insertCondPag(new CondPag(i, "CondPagDesc" + i));
	}

	// Call maintain to see if we need to return the condpag list and if so whether it should be paged or not
	return maintainReturnListCondPag(request.getReturnList(), request.getReturnListPaged(),new CondPag());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICondPagBAC#fetchAllCondPags(CondPag condpag)
 */
@Override
public InternalResultsResponse<CondPag> fetchAllCondPags(CondPag condpag)
{
	InternalResultsResponse<CondPag> response = new InternalResultsResponse<CondPag>();
	response.getResultsList().addAll(getFinanceiroBAR().fetchAllCondPags(condpag).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICondPagBAC#fetchCondPagById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<CondPag> fetchCondPagById(FetchByIdRequest request)
{
	InternalResultsResponse<CondPag> response = new InternalResultsResponse<CondPag>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getFinanceiroBAR().fetchCondPagById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICondPagBAC#fetchCondPagsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<CondPag> fetchCondPagsByRequest(CondPagInquiryRequest request)
{
	return getFinanceiroBAR().fetchCondPagsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the condpag response
 */
private InternalResultsResponse<CondPag> processCondPag(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		CondPagMaintenanceRequest request)
		{
	InternalResultsResponse<CondPag> response = null;

		// Persist
		InternalResponse internalResponse = doPersistenceCondPag(request.getCondPag(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<CondPag>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_FINANCEIRO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the condpag list and if so whether it should be paged or
		// not
		response = maintainReturnListCondPag(request.getReturnList(), request.getReturnListPaged(),new CondPag());

		return response;
			}

	/**
	 * Do persistenceCondPag.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceCondPag(CondPag condpag, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getFinanceiroBAR().insertCondPag(condpag);

			case UPDATE:
				return getFinanceiroBAR().updateCondPag(condpag);

			case DELETE:
				return getFinanceiroBAR().deleteCondPagById(condpag);
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
	private InternalResultsResponse<CondPag> maintainReturnListCondPag(Boolean listIndicator, Boolean pageListIndicator,CondPag condpag)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				CondPagInquiryRequest request = new CondPagInquiryRequest();
				request.setPreQueryCount(true);
				return fetchCondPagsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllCondPags(condpag);
			}
		}
		else
		{
			return new InternalResultsResponse<CondPag>();
		}
	}

//===================================### FORMAPG ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertFormaPg(com.qat.samples.sysmgmt.model.request.FormaPgMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<FormaPg> insertFormaPg(FormaPgMaintenanceRequest request)
{
	InternalResultsResponse<FormaPg> response =
			processFormaPg(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFormaPgBAC#updateFormaPg(com.qat.samples.sysmgmt.model.request.FormaPgMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<FormaPg> updateFormaPg(FormaPgMaintenanceRequest request)
{
	InternalResultsResponse<FormaPg> response =
			processFormaPg(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFormaPgBAC#deleteFormaPg(com.qat.samples.sysmgmt.model.request.FormaPgMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<FormaPg> deleteFormaPg(FormaPgMaintenanceRequest request)
{
	InternalResultsResponse<FormaPg> response =
			processFormaPg(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFormaPgBAC#refreshFormaPgs(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<FormaPg> refreshFormaPgs(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getFinanceiroBAR().deleteAllFormaPgs();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getFinanceiroBAR().insertFormaPg(new FormaPg(i, "FormaPgDesc" + i));
	}

	// Call maintain to see if we need to return the formapg list and if so whether it should be paged or not
	return maintainReturnListFormaPg(request.getReturnList(), request.getReturnListPaged(),new FormaPg());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFormaPgBAC#fetchAllFormaPgs(FormaPg formapg)
 */
@Override
public InternalResultsResponse<FormaPg> fetchAllFormaPgs(FormaPg formapg)
{
	InternalResultsResponse<FormaPg> response = new InternalResultsResponse<FormaPg>();
	response.getResultsList().addAll(getFinanceiroBAR().fetchAllFormaPgs(formapg).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFormaPgBAC#fetchFormaPgById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<FormaPg> fetchFormaPgById(FetchByIdRequest request)
{
	InternalResultsResponse<FormaPg> response = new InternalResultsResponse<FormaPg>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getFinanceiroBAR().fetchFormaPgById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFormaPgBAC#fetchFormaPgsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<FormaPg> fetchFormaPgsByRequest(FormaPgInquiryRequest request)
{
	return getFinanceiroBAR().fetchFormaPgsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the formapg response
 */
private InternalResultsResponse<FormaPg> processFormaPg(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		FormaPgMaintenanceRequest request)
		{
	InternalResultsResponse<FormaPg> response = null;

		// Persist
		InternalResponse internalResponse = doPersistenceFormaPg(request.getFormaPg(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<FormaPg>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_FINANCEIRO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the formapg list and if so whether it should be paged or
		// not
		response = maintainReturnListFormaPg(request.getReturnList(), request.getReturnListPaged(),new FormaPg());

		return response;
			}

	/**
	 * Do persistenceFormaPg.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceFormaPg(FormaPg formapg, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getFinanceiroBAR().insertFormaPg(formapg);

			case UPDATE:
				return getFinanceiroBAR().updateFormaPg(formapg);

			case DELETE:
				return getFinanceiroBAR().deleteFormaPgById(formapg);
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
	private InternalResultsResponse<FormaPg> maintainReturnListFormaPg(Boolean listIndicator, Boolean pageListIndicator,FormaPg formapg)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				FormaPgInquiryRequest request = new FormaPgInquiryRequest();
				request.setPreQueryCount(true);
				return fetchFormaPgsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllFormaPgs(formapg);
			}
		}
		else
		{
			return new InternalResultsResponse<FormaPg>();
		}
	}

//===================================### BANCO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertBanco(com.qat.samples.sysmgmt.model.request.BancoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Banco> insertBanco(BancoMaintenanceRequest request)
{
	InternalResultsResponse<Banco> response =
			processBanco(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IBancoBAC#updateBanco(com.qat.samples.sysmgmt.model.request.BancoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Banco> updateBanco(BancoMaintenanceRequest request)
{
	InternalResultsResponse<Banco> response =
			processBanco(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IBancoBAC#deleteBanco(com.qat.samples.sysmgmt.model.request.BancoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Banco> deleteBanco(BancoMaintenanceRequest request)
{
	InternalResultsResponse<Banco> response =
			processBanco(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IBancoBAC#refreshBancos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Banco> refreshBancos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getFinanceiroBAR().deleteAllBancos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getFinanceiroBAR().insertBanco(new Banco(i, "BancoDesc" + i));
	}

	// Call maintain to see if we need to return the banco list and if so whether it should be paged or not
	return maintainReturnListBanco(request.getReturnList(), request.getReturnListPaged(),new Banco());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IBancoBAC#fetchAllBancos(Banco banco)
 */
@Override
public InternalResultsResponse<Banco> fetchAllBancos(Banco banco)
{
	InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();
	response.getResultsList().addAll(getFinanceiroBAR().fetchAllBancos(banco).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IBancoBAC#fetchBancoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Banco> fetchBancoById(FetchByIdRequest request)
{
	InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getFinanceiroBAR().fetchBancoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IBancoBAC#fetchBancosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Banco> fetchBancosByRequest(BancoInquiryRequest request)
{
	return getFinanceiroBAR().fetchBancosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the banco response
 */
private InternalResultsResponse<Banco> processBanco(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		BancoMaintenanceRequest request)
		{
	InternalResultsResponse<Banco> response = null;


		// Persist
		InternalResponse internalResponse = doPersistenceBanco(request.getBanco(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Banco>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_FINANCEIRO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the banco list and if so whether it should be paged or
		// not
		response = maintainReturnListBanco(request.getReturnList(), request.getReturnListPaged(),new Banco());

		return response;
			}

	/**
	 * Do persistenceBanco.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceBanco(Banco banco, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getFinanceiroBAR().insertBanco(banco);

			case UPDATE:
				return getFinanceiroBAR().updateBanco(banco);

			case DELETE:
				return getFinanceiroBAR().deleteBancoById(banco);
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
	private InternalResultsResponse<Banco> maintainReturnListBanco(Boolean listIndicator, Boolean pageListIndicator,Banco banco)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				BancoInquiryRequest request = new BancoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchBancosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllBancos(banco);
			}
		}
		else
		{
			return new InternalResultsResponse<Banco>();
		}
	}

//===================================### CONTA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertContaCorrente(com.qat.samples.sysmgmt.model.request.ContaCorrenteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Conta> insertConta(ContaMaintenanceRequest request)
{
	InternalResultsResponse<Conta> response =
			processConta(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IContaBAC#updateConta(com.qat.samples.sysmgmt.model.request.ContaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Conta> updateConta(ContaMaintenanceRequest request)
{
	InternalResultsResponse<Conta> response =
			processConta(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IContaBAC#deleteConta(com.qat.samples.sysmgmt.model.request.ContaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Conta> deleteConta(ContaMaintenanceRequest request)
{
	InternalResultsResponse<Conta> response =
			processConta(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}


/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IContaBAC#fetchAllContas(Conta contacorrente)
 */
@Override
public InternalResultsResponse<Conta> fetchAllContas(Conta contacorrente)
{
	InternalResultsResponse<Conta> response = new InternalResultsResponse<Conta>();
	response.getResultsList().addAll(getFinanceiroBAR().fetchAllContas(contacorrente).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IContaBAC#fetchContaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Conta> fetchContaById(FetchByIdRequest request)
{
	InternalResultsResponse<Conta> response = new InternalResultsResponse<Conta>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getFinanceiroBAR().fetchContaById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IContaBAC#fetchContasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Conta> fetchContasByRequest(ContaInquiryRequest request)
{
	return getFinanceiroBAR().fetchContasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the contacorrente response
 */
private InternalResultsResponse<Conta> processConta(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ContaMaintenanceRequest request)
		{
	InternalResultsResponse<Conta> response = null;

		// Persist
		InternalResponse internalResponse = doPersistenceConta(request.getConta(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Conta>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_FINANCEIRO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the contacorrente list and if so whether it should be paged or
		// not
		response = maintainReturnListConta(request.getReturnList(), request.getReturnListPaged(),new Conta());

		return response;
			}

	/**
	 * Do persistenceConta.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConta(Conta contacorrente, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getFinanceiroBAR().insertConta(contacorrente);

			case UPDATE:
				return getFinanceiroBAR().updateConta(contacorrente);

			case DELETE:
				return getFinanceiroBAR().deleteContaById(contacorrente);
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
	private InternalResultsResponse<Conta> maintainReturnListConta(Boolean listIndicator, Boolean pageListIndicator,Conta contacorrente)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ContaInquiryRequest request = new ContaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchContasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllContas(contacorrente);
			}
		}
		else
		{
			return new InternalResultsResponse<Conta>();
		}
	}
	
	//===================================### CONTACORRENTE ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertContaCorrente(com.qat.samples.sysmgmt.model.request.ContaCorrenteMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ContaCorrente> insertContaCorrente(ContaCorrenteMaintenanceRequest request)
	{
		InternalResultsResponse<ContaCorrente> response =
				processContaCorrente(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IContaCorrenteBAC#updateContaCorrente(com.qat.samples.sysmgmt.model.request.ContaCorrenteMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ContaCorrente> updateContaCorrente(ContaCorrenteMaintenanceRequest request)
	{
		InternalResultsResponse<ContaCorrente> response =
				processContaCorrente(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IContaCorrenteBAC#deleteContaCorrente(com.qat.samples.sysmgmt.model.request.ContaCorrenteMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ContaCorrente> deleteContaCorrente(ContaCorrenteMaintenanceRequest request)
	{
		InternalResultsResponse<ContaCorrente> response =
				processContaCorrente(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IContaCorrenteBAC#refreshContaCorrentes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<ContaCorrente> refreshContaCorrentes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getFinanceiroBAR().deleteAllContaCorrentes();
		int refreshNumber = request.getRefreshInt();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
		getFinanceiroBAR().insertContaCorrente(new ContaCorrente(i, "ContaCorrenteDesc" + i));
		}

		// Call maintain to see if we need to return the contacorrente list and if so whether it should be paged or not
		return maintainReturnListContaCorrente(request.getReturnList(), request.getReturnListPaged(),new ContaCorrente());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IContaCorrenteBAC#fetchAllContaCorrentes(ContaCorrente contacorrente)
	 */
	@Override
	public InternalResultsResponse<ContaCorrente> fetchAllContaCorrentes(ContaCorrente contacorrente)
	{
		InternalResultsResponse<ContaCorrente> response = new InternalResultsResponse<ContaCorrente>();
		response.getResultsList().addAll(getFinanceiroBAR().fetchAllContaCorrentes(contacorrente).getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IContaCorrenteBAC#fetchContaCorrenteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ContaCorrente> fetchContaCorrenteById(FetchByIdRequest request)
	{
		InternalResultsResponse<ContaCorrente> response = new InternalResultsResponse<ContaCorrente>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getFinanceiroBAR().fetchContaCorrenteById(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IContaCorrenteBAC#fetchContaCorrentesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ContaCorrente> fetchContaCorrentesByRequest(ContaCorrenteInquiryRequest request)
	{
		return getFinanceiroBAR().fetchContaCorrentesByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the contacorrente response
	 */
	private InternalResultsResponse<ContaCorrente> processContaCorrente(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			ContaCorrenteMaintenanceRequest request)
			{
		InternalResultsResponse<ContaCorrente> response = null;

			// Persist
			InternalResponse internalResponse = doPersistenceContaCorrente(request.getContaCorrente(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<ContaCorrente>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_FINANCEIRO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the contacorrente list and if so whether it should be paged or
			// not
			response = maintainReturnListContaCorrente(request.getReturnList(), request.getReturnListPaged(),new ContaCorrente());

			return response;
				}

		/**
		 * Do persistenceContaCorrente.
		 *
		 * @param request the request
		 * @param updateType the update type
		 * @return the internal response
		 */
		private InternalResponse doPersistenceContaCorrente(ContaCorrente contacorrente, PersistenceActionEnum updateType)
		{
			switch (updateType)
			{
				case INSERT:
					return getFinanceiroBAR().insertContaCorrente(contacorrente);

				case UPDATE:
					return getFinanceiroBAR().updateContaCorrente(contacorrente);

				case DELETE:
					return getFinanceiroBAR().deleteContaCorrenteById(contacorrente);
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
		private InternalResultsResponse<ContaCorrente> maintainReturnListContaCorrente(Boolean listIndicator, Boolean pageListIndicator,ContaCorrente contacorrente)
		{
			// Fetch again if requested.
			if (listIndicator)
			{
				// Fetch Paged is requested.
				if (pageListIndicator)
				{
					ContaCorrenteInquiryRequest request = new ContaCorrenteInquiryRequest();
					request.setPreQueryCount(true);
					return fetchContaCorrentesByRequest(request);
				}
				else
				{
					// otherwise return all rows not paged
					return fetchAllContaCorrentes(contacorrente);
				}
			}
			else
			{
				return new InternalResultsResponse<ContaCorrente>();
			}
		}


//===================================### CAIXA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCaixa(com.qat.samples.sysmgmt.model.request.CaixaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Caixa> insertCaixa(CaixaMaintenanceRequest request)
{
	InternalResultsResponse<Caixa> response =
			processCaixa(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICaixaBAC#updateCaixa(com.qat.samples.sysmgmt.model.request.CaixaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Caixa> updateCaixa(CaixaMaintenanceRequest request)
{
	InternalResultsResponse<Caixa> response =
			processCaixa(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICaixaBAC#deleteCaixa(com.qat.samples.sysmgmt.model.request.CaixaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Caixa> deleteCaixa(CaixaMaintenanceRequest request)
{
	InternalResultsResponse<Caixa> response =
			processCaixa(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICaixaBAC#refreshCaixas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Caixa> refreshCaixas(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getFinanceiroBAR().deleteAllCaixas();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getFinanceiroBAR().insertCaixa(new Caixa());
	}

	// Call maintain to see if we need to return the caixa list and if so whether it should be paged or not
	return maintainReturnListCaixa(request.getReturnList(), request.getReturnListPaged(),new Caixa());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICaixaBAC#fetchAllCaixas(Caixa caixa)
 */
@Override
public InternalResultsResponse<Caixa> fetchAllCaixas(Caixa caixa)
{
	InternalResultsResponse<Caixa> response = new InternalResultsResponse<Caixa>();
	response.getResultsList().addAll(getFinanceiroBAR().fetchAllCaixas(caixa).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICaixaBAC#fetchCaixaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Caixa> fetchCaixaById(FetchByIdRequest request)
{
	InternalResultsResponse<Caixa> response = new InternalResultsResponse<Caixa>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getFinanceiroBAR().fetchCaixaById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICaixaBAC#fetchCaixasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Caixa> fetchCaixasByRequest(CaixaInquiryRequest request)
{
	return getFinanceiroBAR().fetchCaixasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the caixa response
 */
private InternalResultsResponse<Caixa> processCaixa(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		CaixaMaintenanceRequest request)
		{
	InternalResultsResponse<Caixa> response = null;

		// Persist
		InternalResponse internalResponse = doPersistenceCaixa(request.getCaixa(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Caixa>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_FINANCEIRO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the caixa list and if so whether it should be paged or
		// not
		response = maintainReturnListCaixa(request.getReturnList(), request.getReturnListPaged(),new Caixa());

		return response;
			}

	/**
	 * Do persistenceCaixa.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceCaixa(Caixa caixa, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getFinanceiroBAR().insertCaixa(caixa);

			case UPDATE:
				return getFinanceiroBAR().updateCaixa(caixa);

			case DELETE:
				return getFinanceiroBAR().deleteCaixaById(caixa);
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
	private InternalResultsResponse<Caixa> maintainReturnListCaixa(Boolean listIndicator, Boolean pageListIndicator,Caixa caixa)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				CaixaInquiryRequest request = new CaixaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchCaixasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllCaixas(caixa);
			}
		}
		else
		{
			return new InternalResultsResponse<Caixa>();
		}
	}
	
	//===================================### CAIXA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertAgencia(com.qat.samples.sysmgmt.model.request.AgenciaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Agencia> insertAgencia(AgenciaMaintenanceRequest request)
{
	InternalResultsResponse<Agencia> response =
			processAgencia(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAgenciaBAC#updateAgencia(com.qat.samples.sysmgmt.model.request.AgenciaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Agencia> updateAgencia(AgenciaMaintenanceRequest request)
{
	InternalResultsResponse<Agencia> response =
			processAgencia(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAgenciaBAC#deleteAgencia(com.qat.samples.sysmgmt.model.request.AgenciaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Agencia> deleteAgencia(AgenciaMaintenanceRequest request)
{
	InternalResultsResponse<Agencia> response =
			processAgencia(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAgenciaBAC#fetchAgenciaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Agencia> fetchAgenciaById(FetchByIdRequest request)
{
	InternalResultsResponse<Agencia> response = new InternalResultsResponse<Agencia>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getFinanceiroBAR().fetchAgenciaById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAgenciaBAC#fetchAgenciasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Agencia> fetchAgenciasByRequest(AgenciaInquiryRequest request)
{
	return getFinanceiroBAR().fetchAgenciasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the agencia response
 */
private InternalResultsResponse<Agencia> processAgencia(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		AgenciaMaintenanceRequest request)
		{
	InternalResultsResponse<Agencia> response = null;

		// Persist
		InternalResponse internalResponse = doPersistenceAgencia(request.getAgencia(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Agencia>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_FINANCEIRO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the agencia list and if so whether it should be paged or
		// not
		response = maintainReturnListAgencia(request.getReturnList(), request.getReturnListPaged(),new Agencia());

		return response;
			}

	/**
	 * Do persistenceAgencia.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceAgencia(Agencia agencia, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getFinanceiroBAR().insertAgencia(agencia);

			case UPDATE:
				return getFinanceiroBAR().updateAgencia(agencia);

			case DELETE:
				return getFinanceiroBAR().deleteAgenciaById(agencia);
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
	private InternalResultsResponse<Agencia> maintainReturnListAgencia(Boolean listIndicator, Boolean pageListIndicator,Agencia agencia)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				AgenciaInquiryRequest request = new AgenciaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchAgenciasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllAgencias(agencia);
			}
		}
		else
		{
			return new InternalResultsResponse<Agencia>();
		}
	}

	@Override
	public InternalResultsResponse<Agencia> refreshAgencias(RefreshRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Agencia> fetchAllAgencias(Agencia caixa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Conta> refreshContas(RefreshRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//===================================### CAIXA ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCaixa(com.qat.samples.sysmgmt.model.request.CaixaMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<BaixaTitulo> insertBaixaTitulo(BaixaTituloMaintenanceRequest request)
	{
		InternalResultsResponse<BaixaTitulo> response =
				processBaixaTitulo(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IBaixaTituloBAC#updateBaixaTitulo(com.qat.samples.sysmgmt.model.request.BaixaTituloMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<BaixaTitulo> updateBaixaTitulo(BaixaTituloMaintenanceRequest request)
	{
		InternalResultsResponse<BaixaTitulo> response =
				processBaixaTitulo(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IBaixaTituloBAC#deleteBaixaTitulo(com.qat.samples.sysmgmt.model.request.BaixaTituloMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<BaixaTitulo> deleteBaixaTitulo(BaixaTituloMaintenanceRequest request)
	{
		InternalResultsResponse<BaixaTitulo> response =
				processBaixaTitulo(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IBaixaTituloBAC#refreshBaixaTitulos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<BaixaTitulo> refreshBaixaTitulos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getFinanceiroBAR().deleteAllBaixaTitulos();
		int refreshNumber = request.getRefreshInt();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
		getFinanceiroBAR().insertBaixaTitulo(new BaixaTitulo());
		}

		// Call maintain to see if we need to return the caixa list and if so whether it should be paged or not
		return maintainReturnListBaixaTitulo(request.getReturnList(), request.getReturnListPaged(),new BaixaTitulo());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IBaixaTituloBAC#fetchAllBaixaTitulos(BaixaTitulo caixa)
	 */
	@Override
	public InternalResultsResponse<BaixaTitulo> fetchAllBaixaTitulos(BaixaTitulo caixa)
	{
		InternalResultsResponse<BaixaTitulo> response = new InternalResultsResponse<BaixaTitulo>();
		response.getResultsList().addAll(getFinanceiroBAR().fetchAllBaixaTitulos(caixa).getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IBaixaTituloBAC#fetchBaixaTituloById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<BaixaTitulo> fetchBaixaTituloById(FetchByIdRequest request)
	{
		InternalResultsResponse<BaixaTitulo> response = new InternalResultsResponse<BaixaTitulo>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getFinanceiroBAR().fetchBaixaTituloById(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IBaixaTituloBAC#fetchBaixaTitulosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<BaixaTitulo> fetchBaixaTitulosByRequest(BaixaTituloInquiryRequest request)
	{
		return getFinanceiroBAR().fetchBaixaTitulosByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the caixa response
	 */
	private InternalResultsResponse<BaixaTitulo> processBaixaTitulo(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			BaixaTituloMaintenanceRequest request)
			{
		InternalResultsResponse<BaixaTitulo> response = null;

			// Persist
			InternalResponse internalResponse = doPersistenceBaixaTitulo(request.getBaixaTitulo(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<BaixaTitulo>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_FINANCEIRO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the caixa list and if so whether it should be paged or
			// not
			response = maintainReturnListBaixaTitulo(request.getReturnList(), request.getReturnListPaged(),new BaixaTitulo());

			return response;
				}

		/**
		 * Do persistenceBaixaTitulo.
		 *
		 * @param request the request
		 * @param updateType the update type
		 * @return the internal response
		 */
		private InternalResponse doPersistenceBaixaTitulo(BaixaTitulo caixa, PersistenceActionEnum updateType)
		{
			switch (updateType)
			{
				case INSERT:
					return getFinanceiroBAR().insertBaixaTitulo(caixa);

				case UPDATE:
					return getFinanceiroBAR().updateBaixaTitulo(caixa);

				case DELETE:
					return getFinanceiroBAR().deleteBaixaTituloById(caixa);
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
		private InternalResultsResponse<BaixaTitulo> maintainReturnListBaixaTitulo(Boolean listIndicator, Boolean pageListIndicator,BaixaTitulo caixa)
		{
			// Fetch again if requested.
			if (listIndicator)
			{
				// Fetch Paged is requested.
				if (pageListIndicator)
				{
					BaixaTituloInquiryRequest request = new BaixaTituloInquiryRequest();
					request.setPreQueryCount(true);
					return fetchBaixaTitulosByRequest(request);
				}
				else
				{
					// otherwise return all rows not paged
					return fetchAllBaixaTitulos(caixa);
				}
			}
			else
			{
				return new InternalResultsResponse<BaixaTitulo>();
			}
		}
		
}
