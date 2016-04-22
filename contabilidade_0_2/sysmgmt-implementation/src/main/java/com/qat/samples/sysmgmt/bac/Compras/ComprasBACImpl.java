package com.qat.samples.sysmgmt.bac.Compras;


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
import com.qat.samples.sysmgmt.bar.Compras.IComprasBAR;
import com.qat.samples.sysmgmt.cotacao.model.Cotacao;
import com.qat.samples.sysmgmt.cotacao.request.CotacaoInquiryRequest;
import com.qat.samples.sysmgmt.cotacao.request.CotacaoMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Compras leveraging the injected IComprasBAR.
 */
@Component
public class ComprasBACImpl implements IComprasBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_COMPRAS_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_COMPRAS_BAC_EXCEPTION_MSG = "sysmgmt.base.Comprasbacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ComprasBACImpl.class);

	/** The Compras BAR. */
	private IComprasBAR comprasBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Compras BAR.
	 *
	 * @param ComprasBAR the new Compras BAR
	 */
	public void setComprasBAR(IComprasBAR comprasBAR)
	{
		this.comprasBAR = comprasBAR;
	}

	/**
	 * Gets the Compras BAR.
	 *
	 * @return the Compras BAR
	 */
	public IComprasBAR getComprasBAR()
	{
		return comprasBAR;
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

//===================================### NOTAFISCALENTRADA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertNotaFiscalEntrada(com.qat.samples.sysmgmt.model.request.NotaFiscalEntradaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<NotaFiscalEntrada> insertNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
{
	InternalResultsResponse<NotaFiscalEntrada> response =
			processNotaFiscalEntrada(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.INotaFiscalEntradaBAC#updateNotaFiscalEntrada(com.qat.samples.sysmgmt.model.request.NotaFiscalEntradaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<NotaFiscalEntrada> updateNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
{
	InternalResultsResponse<NotaFiscalEntrada> response =
			processNotaFiscalEntrada(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.INotaFiscalEntradaBAC#deleteNotaFiscalEntrada(com.qat.samples.sysmgmt.model.request.NotaFiscalEntradaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<NotaFiscalEntrada> deleteNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
{
	InternalResultsResponse<NotaFiscalEntrada> response =
			processNotaFiscalEntrada(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.INotaFiscalEntradaBAC#refreshNotaFiscalEntradas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<NotaFiscalEntrada> refreshNotaFiscalEntradas(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getComprasBAR().deleteAllNotaFiscalEntradas();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getComprasBAR().insertNotaFiscalEntrada(new NotaFiscalEntrada(i, "NotaFiscalEntradaDesc" + i));
	}

	// Call maintain to see if we need to return the notafiscalentrada list and if so whether it should be paged or not
	return maintainReturnListNotaFiscalEntrada(request.getReturnList(), request.getReturnListPaged(),new NotaFiscalEntrada());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.INotaFiscalEntradaBAC#fetchAllNotaFiscalEntradas(NotaFiscalEntrada notafiscalentrada)
 */
@Override
public InternalResultsResponse<NotaFiscalEntrada> fetchAllNotaFiscalEntradas(NotaFiscalEntrada notafiscalentrada)
{
	InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();
	response.getResultsList().addAll(getComprasBAR().fetchAllNotaFiscalEntradas(notafiscalentrada).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.INotaFiscalEntradaBAC#fetchNotaFiscalEntradaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaById(FetchByIdRequest request)
{
	InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getComprasBAR().fetchNotaFiscalEntradaById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.INotaFiscalEntradaBAC#fetchNotaFiscalEntradasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradasByRequest(NotaFiscalInquiryRequest request)
{
	return getComprasBAR().fetchNotaFiscalEntradasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the notafiscalentrada response
 */
private InternalResultsResponse<NotaFiscalEntrada> processNotaFiscalEntrada(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		NotaFiscalEntradaMaintenanceRequest request)
		{
	InternalResultsResponse<NotaFiscalEntrada> response = null;

	// Validate
	ValidationContext context = new ValidationContext(NotaFiscalEntrada.class.getSimpleName(), request.getNotafiscal(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<NotaFiscalEntrada>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceNotaFiscalEntrada(request.getNotafiscal(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<NotaFiscalEntrada>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_COMPRAS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the notafiscalentrada list and if so whether it should be paged or
		// not
		response = maintainReturnListNotaFiscalEntrada(request.getReturnList(), request.getReturnListPaged(),new NotaFiscalEntrada());

		return response;
			}

	/**
	 * Do persistenceNotaFiscalEntrada.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceNotaFiscalEntrada(NotaFiscalEntrada notafiscalentrada, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getComprasBAR().insertNotaFiscalEntrada(notafiscalentrada);

			case UPDATE:
				return getComprasBAR().updateNotaFiscalEntrada(notafiscalentrada);

			case DELETE:
				return getComprasBAR().deleteNotaFiscalEntradaById(notafiscalentrada);
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
	private InternalResultsResponse<NotaFiscalEntrada> maintainReturnListNotaFiscalEntrada(Boolean listIndicator, Boolean pageListIndicator,NotaFiscalEntrada notafiscalentrada)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				NotaFiscalInquiryRequest request = new NotaFiscalInquiryRequest();
				request.setPreQueryCount(true);
				return fetchNotaFiscalEntradasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllNotaFiscalEntradas(notafiscalentrada);
			}
		}
		else
		{
			return new InternalResultsResponse<NotaFiscalEntrada>();
		}
	}

//===================================### PEDIDOCOMPRAS ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertPedidoCompras(com.qat.samples.sysmgmt.model.request.PedidoComprasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<PedidoCompras> insertPedidoCompras(PedidoComprasMaintenanceRequest request)
{
	InternalResultsResponse<PedidoCompras> response =
			processPedidoCompras(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPedidoComprasBAC#updatePedidoCompras(com.qat.samples.sysmgmt.model.request.PedidoComprasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<PedidoCompras> updatePedidoCompras(PedidoComprasMaintenanceRequest request)
{
	InternalResultsResponse<PedidoCompras> response =
			processPedidoCompras(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPedidoComprasBAC#deletePedidoCompras(com.qat.samples.sysmgmt.model.request.PedidoComprasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<PedidoCompras> deletePedidoCompras(PedidoComprasMaintenanceRequest request)
{
	InternalResultsResponse<PedidoCompras> response =
			processPedidoCompras(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPedidoComprasBAC#refreshPedidoComprass(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<PedidoCompras> refreshPedidoComprass(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getComprasBAR().deleteAllPedidoComprass();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getComprasBAR().insertPedidoCompras(new PedidoCompras(i, "PedidoComprasDesc" + i));
	}

	// Call maintain to see if we need to return the pedidocompras list and if so whether it should be paged or not
	return maintainReturnListPedidoCompras(request.getReturnList(), request.getReturnListPaged(),new PedidoCompras());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPedidoComprasBAC#fetchAllPedidoComprass(PedidoCompras pedidocompras)
 */
@Override
public InternalResultsResponse<PedidoCompras> fetchAllPedidoComprass(PedidoCompras pedidocompras)
{
	InternalResultsResponse<PedidoCompras> response = new InternalResultsResponse<PedidoCompras>();
	response.getResultsList().addAll(getComprasBAR().fetchAllPedidoComprass(pedidocompras).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPedidoComprasBAC#fetchPedidoComprasById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request)
{
	InternalResultsResponse<PedidoCompras> response = new InternalResultsResponse<PedidoCompras>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getComprasBAR().fetchPedidoComprasById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPedidoComprasBAC#fetchPedidoComprassByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<PedidoCompras> fetchPedidoComprassByRequest(PedidoComprasInquiryRequest request)
{
	return getComprasBAR().fetchPedidoComprassByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the pedidocompras response
 */
private InternalResultsResponse<PedidoCompras> processPedidoCompras(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		PedidoComprasMaintenanceRequest request)
		{
	InternalResultsResponse<PedidoCompras> response = null;

	// Validate
	ValidationContext context = new ValidationContext(PedidoCompras.class.getSimpleName(), request.getPedidoCompras(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<PedidoCompras>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistencePedidoCompras(request.getPedidoCompras(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<PedidoCompras>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_COMPRAS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the pedidocompras list and if so whether it should be paged or
		// not
		response = maintainReturnListPedidoCompras(request.getReturnList(), request.getReturnListPaged(),new PedidoCompras());

		return response;
			}

	/**
	 * Do persistencePedidoCompras.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistencePedidoCompras(PedidoCompras pedidocompras, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getComprasBAR().insertPedidoCompras(pedidocompras);

			case UPDATE:
				return getComprasBAR().updatePedidoCompras(pedidocompras);

			case DELETE:
				return getComprasBAR().deletePedidoComprasById(pedidocompras);
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
	private InternalResultsResponse<PedidoCompras> maintainReturnListPedidoCompras(Boolean listIndicator, Boolean pageListIndicator,PedidoCompras pedidocompras)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PedidoComprasInquiryRequest request = new PedidoComprasInquiryRequest();
				request.setPreQueryCount(true);
				return fetchPedidoComprassByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllPedidoComprass(pedidocompras);
			}
		}
		else
		{
			return new InternalResultsResponse<PedidoCompras>();
		}
	}

//===================================### COTACAO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCotacao(com.qat.samples.sysmgmt.model.request.CotacaoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cotacao> insertCotacao(CotacaoMaintenanceRequest request)
{
	InternalResultsResponse<Cotacao> response =
			processCotacao(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICotacaoBAC#updateCotacao(com.qat.samples.sysmgmt.model.request.CotacaoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cotacao> updateCotacao(CotacaoMaintenanceRequest request)
{
	InternalResultsResponse<Cotacao> response =
			processCotacao(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICotacaoBAC#deleteCotacao(com.qat.samples.sysmgmt.model.request.CotacaoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cotacao> deleteCotacao(CotacaoMaintenanceRequest request)
{
	InternalResultsResponse<Cotacao> response =
			processCotacao(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICotacaoBAC#refreshCotacaos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Cotacao> refreshCotacaos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getComprasBAR().deleteAllCotacaos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getComprasBAR().insertCotacao(new Cotacao(i, "CotacaoDesc" + i));
	}

	// Call maintain to see if we need to return the cotacao list and if so whether it should be paged or not
	return maintainReturnListCotacao(request.getReturnList(), request.getReturnListPaged(),new Cotacao());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICotacaoBAC#fetchAllCotacaos(Cotacao cotacao)
 */
@Override
public InternalResultsResponse<Cotacao> fetchAllCotacaos(Cotacao cotacao)
{
	InternalResultsResponse<Cotacao> response = new InternalResultsResponse<Cotacao>();
	response.getResultsList().addAll(getComprasBAR().fetchAllCotacaos(cotacao).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICotacaoBAC#fetchCotacaoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Cotacao> fetchCotacaoById(FetchByIdRequest request)
{
	InternalResultsResponse<Cotacao> response = new InternalResultsResponse<Cotacao>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getComprasBAR().fetchCotacaoById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICotacaoBAC#fetchCotacaosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Cotacao> fetchCotacaosByRequest(CotacaoInquiryRequest request)
{
	return getComprasBAR().fetchCotacaosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the cotacao response
 */
private InternalResultsResponse<Cotacao> processCotacao(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		CotacaoMaintenanceRequest request)
		{
	InternalResultsResponse<Cotacao> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Cotacao.class.getSimpleName(), request.getCotacao(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Cotacao>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceCotacao(request.getCotacao(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Cotacao>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_COMPRAS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the cotacao list and if so whether it should be paged or
		// not
		response = maintainReturnListCotacao(request.getReturnList(), request.getReturnListPaged(),new Cotacao());

		return response;
			}

	/**
	 * Do persistenceCotacao.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceCotacao(Cotacao cotacao, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getComprasBAR().insertCotacao(cotacao);

			case UPDATE:
				return getComprasBAR().updateCotacao(cotacao);

			case DELETE:
				return getComprasBAR().deleteCotacaoById(cotacao);
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
	private InternalResultsResponse<Cotacao> maintainReturnListCotacao(Boolean listIndicator, Boolean pageListIndicator,Cotacao cotacao)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				CotacaoInquiryRequest request = new CotacaoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchCotacaosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllCotacaos(cotacao);
			}
		}
		else
		{
			return new InternalResultsResponse<Cotacao>();
		}
	}
}
