package com.qat.samples.sysmgmt.bac.Vendas;


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
import com.qat.samples.sysmgmt.bar.Vendas.IVendasBAR;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Vendas leveraging the injected IVendasBAR.
 */
@Component
public class VendasBACImpl implements IVendasBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_VENDAS_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_VENDAS_BAC_EXCEPTION_MSG = "sysmgmt.base.Vendasbacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(VendasBACImpl.class);

	/** The Vendas BAR. */
	private IVendasBAR vendasBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Vendas BAR.
	 *
	 * @param VendasBAR the new Vendas BAR
	 */
	public void setVendasBAR(IVendasBAR vendasBAR)
	{
		this.vendasBAR = vendasBAR;
	}

	/**
	 * Gets the Vendas BAR.
	 *
	 * @return the Vendas BAR
	 */
	public IVendasBAR getVendasBAR()
	{
		return vendasBAR;
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

//===================================### NOTAFISCALSAIDA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertNotaFiscalSaida(com.qat.samples.sysmgmt.model.request.NotaFiscalSaidaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<NotaFiscalSaida> insertNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
{
	InternalResultsResponse<NotaFiscalSaida> response =
			processNotaFiscalSaida(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.INotaFiscalSaidaBAC#updateNotaFiscalSaida(com.qat.samples.sysmgmt.model.request.NotaFiscalSaidaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<NotaFiscalSaida> updateNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
{
	InternalResultsResponse<NotaFiscalSaida> response =
			processNotaFiscalSaida(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.INotaFiscalSaidaBAC#deleteNotaFiscalSaida(com.qat.samples.sysmgmt.model.request.NotaFiscalSaidaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<NotaFiscalSaida> deleteNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
{
	InternalResultsResponse<NotaFiscalSaida> response =
			processNotaFiscalSaida(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.INotaFiscalSaidaBAC#refreshNotaFiscalSaidas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<NotaFiscalSaida> refreshNotaFiscalSaidas(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getVendasBAR().deleteAllNotaFiscalSaidas();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getVendasBAR().insertNotaFiscalSaida(new NotaFiscalSaida(i, "NotaFiscalSaidaDesc" + i));
	}

	// Call maintain to see if we need to return the notafiscalsaida list and if so whether it should be paged or not
	return maintainReturnListNotaFiscalSaida(request.getReturnList(), request.getReturnListPaged(),new NotaFiscalSaida());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.INotaFiscalSaidaBAC#fetchAllNotaFiscalSaidas(NotaFiscalSaida notafiscalsaida)
 */
@Override
public InternalResultsResponse<NotaFiscalSaida> fetchAllNotaFiscalSaidas(NotaFiscalSaida notafiscalsaida)
{
	InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();
	response.getResultsList().addAll(getVendasBAR().fetchAllNotaFiscalSaidas(notafiscalsaida).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.INotaFiscalSaidaBAC#fetchNotaFiscalSaidaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaById(FetchByIdRequest request)
{
	InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getVendasBAR().fetchNotaFiscalSaidaById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.INotaFiscalSaidaBAC#fetchNotaFiscalSaidasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidasByRequest(NotaFiscalInquiryRequest request)
{
	return getVendasBAR().fetchNotaFiscalSaidasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the notafiscalsaida response
 */
private InternalResultsResponse<NotaFiscalSaida> processNotaFiscalSaida(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		NotaFiscalSaidaMaintenanceRequest request)
		{
	InternalResultsResponse<NotaFiscalSaida> response = null;

	// Validate
	ValidationContext context = new ValidationContext(NotaFiscalSaida.class.getSimpleName(), request.getNotafiscal(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<NotaFiscalSaida>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceNotaFiscalSaida(request.getNotafiscal(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<NotaFiscalSaida>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_VENDAS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the notafiscalsaida list and if so whether it should be paged or
		// not
		response = maintainReturnListNotaFiscalSaida(request.getReturnList(), request.getReturnListPaged(),new NotaFiscalSaida());

		return response;
			}

	/**
	 * Do persistenceNotaFiscalSaida.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceNotaFiscalSaida(NotaFiscalSaida notafiscalsaida, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getVendasBAR().insertNotaFiscalSaida(notafiscalsaida);

			case UPDATE:
				return getVendasBAR().updateNotaFiscalSaida(notafiscalsaida);

			case DELETE:
				return getVendasBAR().deleteNotaFiscalSaidaById(notafiscalsaida);
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
	private InternalResultsResponse<NotaFiscalSaida> maintainReturnListNotaFiscalSaida(Boolean listIndicator, Boolean pageListIndicator,NotaFiscalSaida notafiscalsaida)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				NotaFiscalInquiryRequest request = new NotaFiscalInquiryRequest();
				request.setPreQueryCount(true);
				return fetchNotaFiscalSaidasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllNotaFiscalSaidas(notafiscalsaida);
			}
		}
		else
		{
			return new InternalResultsResponse<NotaFiscalSaida>();
		}
	}

//===================================### ORCAMENTO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertOrcamento(com.qat.samples.sysmgmt.model.request.OrcamentoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Orcamento> insertOrcamento(OrcamentoMaintenanceRequest request)
{
	InternalResultsResponse<Orcamento> response =
			processOrcamento(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IOrcamentoBAC#updateOrcamento(com.qat.samples.sysmgmt.model.request.OrcamentoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Orcamento> updateOrcamento(OrcamentoMaintenanceRequest request)
{
	InternalResultsResponse<Orcamento> response =
			processOrcamento(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IOrcamentoBAC#deleteOrcamento(com.qat.samples.sysmgmt.model.request.OrcamentoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Orcamento> deleteOrcamento(OrcamentoMaintenanceRequest request)
{
	InternalResultsResponse<Orcamento> response =
			processOrcamento(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IOrcamentoBAC#refreshOrcamentos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Orcamento> refreshOrcamentos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getVendasBAR().deleteAllOrcamentos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getVendasBAR().insertOrcamento(new Orcamento(i, "OrcamentoDesc" + i));
	}

	// Call maintain to see if we need to return the orcamento list and if so whether it should be paged or not
	return maintainReturnListOrcamento(request.getReturnList(), request.getReturnListPaged(),new Orcamento());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IOrcamentoBAC#fetchAllOrcamentos(Orcamento orcamento)
 */
@Override
public InternalResultsResponse<Orcamento> fetchAllOrcamentos(Orcamento orcamento)
{
	InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();
	response.getResultsList().addAll(getVendasBAR().fetchAllOrcamentos(orcamento).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IOrcamentoBAC#fetchOrcamentoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request)
{
	InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getVendasBAR().fetchOrcamentoById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IOrcamentoBAC#fetchOrcamentosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Orcamento> fetchOrcamentosByRequest(OrcamentoInquiryRequest request)
{
	return getVendasBAR().fetchOrcamentosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the orcamento response
 */
private InternalResultsResponse<Orcamento> processOrcamento(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		OrcamentoMaintenanceRequest request)
		{
	InternalResultsResponse<Orcamento> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Orcamento.class.getSimpleName(), request.getOrcamento(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Orcamento>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceOrcamento(request.getOrcamento(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Orcamento>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_VENDAS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the orcamento list and if so whether it should be paged or
		// not
		response = maintainReturnListOrcamento(request.getReturnList(), request.getReturnListPaged(),new Orcamento());

		return response;
			}

	/**
	 * Do persistenceOrcamento.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceOrcamento(Orcamento orcamento, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getVendasBAR().insertOrcamento(orcamento);

			case UPDATE:
				return getVendasBAR().updateOrcamento(orcamento);

			case DELETE:
				return getVendasBAR().deleteOrcamentoById(orcamento);
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
	private InternalResultsResponse<Orcamento> maintainReturnListOrcamento(Boolean listIndicator, Boolean pageListIndicator,Orcamento orcamento)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				OrcamentoInquiryRequest request = new OrcamentoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchOrcamentosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllOrcamentos(orcamento);
			}
		}
		else
		{
			return new InternalResultsResponse<Orcamento>();
		}
	}

//===================================### ORDEMSERVICO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertOrdemServico(com.qat.samples.sysmgmt.model.request.OrdemServicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<OrdemServico> insertOrdemServico(OrdemServicoMaintenanceRequest request)
{
	InternalResultsResponse<OrdemServico> response =
			processOrdemServico(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IOrdemServicoBAC#updateOrdemServico(com.qat.samples.sysmgmt.model.request.OrdemServicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<OrdemServico> updateOrdemServico(OrdemServicoMaintenanceRequest request)
{
	InternalResultsResponse<OrdemServico> response =
			processOrdemServico(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IOrdemServicoBAC#deleteOrdemServico(com.qat.samples.sysmgmt.model.request.OrdemServicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<OrdemServico> deleteOrdemServico(OrdemServicoMaintenanceRequest request)
{
	InternalResultsResponse<OrdemServico> response =
			processOrdemServico(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IOrdemServicoBAC#refreshOrdemServicos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<OrdemServico> refreshOrdemServicos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getVendasBAR().deleteAllOrdemServicos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getVendasBAR().insertOrdemServico(new OrdemServico(i, "OrdemServicoDesc" + i));
	}

	// Call maintain to see if we need to return the ordemservico list and if so whether it should be paged or not
	return maintainReturnListOrdemServico(request.getReturnList(), request.getReturnListPaged(),new OrdemServico());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IOrdemServicoBAC#fetchAllOrdemServicos(OrdemServico ordemservico)
 */
@Override
public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos(OrdemServico ordemservico)
{
	InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
	response.getResultsList().addAll(getVendasBAR().fetchAllOrdemServicos(ordemservico).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IOrdemServicoBAC#fetchOrdemServicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request)
{
	InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getVendasBAR().fetchOrdemServicoById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IOrdemServicoBAC#fetchOrdemServicosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request)
{
	return getVendasBAR().fetchOrdemServicosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the ordemservico response
 */
private InternalResultsResponse<OrdemServico> processOrdemServico(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		OrdemServicoMaintenanceRequest request)
		{
	InternalResultsResponse<OrdemServico> response = null;

	// Validate
	ValidationContext context = new ValidationContext(OrdemServico.class.getSimpleName(), request.getOrdemServico(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<OrdemServico>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceOrdemServico(request.getOrdemServico(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<OrdemServico>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_VENDAS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the ordemservico list and if so whether it should be paged or
		// not
		response = maintainReturnListOrdemServico(request.getReturnList(), request.getReturnListPaged(),new OrdemServico());

		return response;
			}

	/**
	 * Do persistenceOrdemServico.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceOrdemServico(OrdemServico ordemservico, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getVendasBAR().insertOrdemServico(ordemservico);

			case UPDATE:
				return getVendasBAR().updateOrdemServico(ordemservico);

			case DELETE:
				return getVendasBAR().deleteOrdemServicoById(ordemservico);
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
	private InternalResultsResponse<OrdemServico> maintainReturnListOrdemServico(Boolean listIndicator, Boolean pageListIndicator,OrdemServico ordemservico)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				OrdemServicoInquiryRequest request = new OrdemServicoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchOrdemServicosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllOrdemServicos(ordemservico);
			}
		}
		else
		{
			return new InternalResultsResponse<OrdemServico>();
		}
	}
}
