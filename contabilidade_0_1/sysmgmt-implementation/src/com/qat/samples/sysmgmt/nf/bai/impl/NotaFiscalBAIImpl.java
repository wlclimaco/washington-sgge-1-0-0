package com.qat.samples.sysmgmt.nf.bai.impl;

import java.util.List;

import javax.xml.ws.Response;

import org.slf4j.LoggerFactory;

import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.condpag.model.request.CondPgInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.Caixa;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.nf.bac.INotaFiscalBAC;
import com.qat.samples.sysmgmt.nf.bai.INotaFiscalBAI;
import com.qat.samples.sysmgmt.nf.model.Contas;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;
import com.qat.samples.sysmgmt.nf.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.ContasInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.ContasMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.response.CaixaResponse;
import com.qat.samples.sysmgmt.nf.model.response.CondPgResponse;
import com.qat.samples.sysmgmt.nf.model.response.ContasResponse;
import com.qat.samples.sysmgmt.nf.model.response.NotaFiscalEntradaResponse;
import com.qat.samples.sysmgmt.nf.model.response.NotaFiscalSaidaResponse;
import com.qat.samples.sysmgmt.nf.model.response.OrcamentoResponse;
import com.qat.samples.sysmgmt.nf.model.response.PedidoComprasResponse;

/**
 * The Class NotaFiscalBAIImpl.
 */
public class NotaFiscalBAIImpl implements INotaFiscalBAI
{
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = NotaFiscalBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NotaFiscalBAIImpl.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED =
			"sendsolv.base.notaFiscalvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The notaFiscal bac. */
	private INotaFiscalBAC notaFiscalBAC; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController validationController;

	/**
	 * Get notaFiscal validation controller.
	 * 
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Spring sets the notaFiscal validation controller.
	 * 
	 * @param notaFiscalValidationController the new validation controller
	 */
	public void setValidationController(ValidationController notaFiscalValidationController)
	{
		validationController = notaFiscalValidationController;
	}

	/**
	 * Spring Sets the notaFiscal bac.
	 * 
	 * @param notaFiscalBAC the new notaFiscal bac
	 */
	public void setNotaFiscalBAC(INotaFiscalBAC notaFiscalBAC)
	{
		this.notaFiscalBAC = notaFiscalBAC;
	}

	/**
	 * Gets the notaFiscal bac.
	 * 
	 * @return the notaFiscal bac
	 */
	public INotaFiscalBAC getNotaFiscalBAC()
	{
		return notaFiscalBAC;
	}

	@Override
	public NotaFiscalEntradaResponse insertNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			response = processEntrada(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public NotaFiscalEntradaResponse updateNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			response = processEntrada(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public NotaFiscalEntradaResponse deleteNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			response = processEntrada(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradaById(FetchByIdRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getFetchId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getNotaFiscalBAC().fetchNotaFiscalEntradaById(request);
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

	@Override
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradaByRequest(NotaFiscalInquiryRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			fetchPagedEntrada(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public NotaFiscalSaidaResponse insertNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			response = processSaida(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public NotaFiscalSaidaResponse updateNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			response = processSaida(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public NotaFiscalSaidaResponse deleteNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			response = processSaida(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidaById(FetchByIdRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getFetchId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getNotaFiscalBAC().fetchNotaFiscalSaidaById(request);
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

	@Override
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidaByRequest(NotaFiscalInquiryRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			fetchPagedSaida(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public PedidoComprasResponse insertPedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();
		try
		{
			response = processPedidoCompras(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public PedidoComprasResponse updatePedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();
		try
		{
			response = processPedidoCompras(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public PedidoComprasResponse deletePedidoComprasl(PedidoComprasMaintenanceRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();
		try
		{
			response = processPedidoCompras(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public PedidoComprasResponse fetchPedidoComprasById(FetchByIdRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getFetchId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getNotaFiscalBAC().fetchPedidoComprasById(request);
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

	@Override
	public PedidoComprasResponse fetchPedidoComprasByRequest(PedidoComprasInquiryRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();
		try
		{
			fetchPagedPedidoCompras(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public OrcamentoResponse insertOrcamento(OrcamentoMaintenanceRequest request)
	{
		OrcamentoResponse response = new OrcamentoResponse();
		try
		{
			response = processOrcamento(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public OrcamentoResponse updateOrcamento(OrcamentoMaintenanceRequest request)
	{
		OrcamentoResponse response = new OrcamentoResponse();
		try
		{
			response = processOrcamento(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public OrcamentoResponse deleteOrcamento(OrcamentoMaintenanceRequest request)
	{
		OrcamentoResponse response = new OrcamentoResponse();
		try
		{
			response = processOrcamento(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public OrcamentoResponse fetchOrcamentoById(FetchByIdRequest request)
	{
		OrcamentoResponse response = new OrcamentoResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getFetchId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getNotaFiscalBAC().fetchOrcamentoById(request);
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

	@Override
	public OrcamentoResponse fetchOrcamentoByRequest(OrcamentoInquiryRequest request)
	{
		OrcamentoResponse response = new OrcamentoResponse();
		try
		{
			fetchPagedOrcamento(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public ContasResponse fetchContasByRequest(ContasInquiryRequest request)
	{
		ContasResponse response = new ContasResponse();
		try
		{
			fetchPagedContas(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public ContasResponse insertContas(ContasMaintenanceRequest request)
	{
		ContasResponse response = new ContasResponse();
		try
		{
			response = processContas(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public ContasResponse updateContas(ContasMaintenanceRequest request)
	{
		ContasResponse response = new ContasResponse();
		try
		{
			response = processContas(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public ContasResponse deleteContas(ContasMaintenanceRequest request)
	{
		ContasResponse response = new ContasResponse();
		try
		{
			response = processContas(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public ContasResponse fetchContasById(FetchByIdRequest request)
	{
		ContasResponse response = new ContasResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getFetchId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getNotaFiscalBAC().fetchContasById(request);
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

	/**
	 * Process.
	 * 
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the notaFiscal response
	 */
	private NotaFiscalEntradaResponse processEntrada(ValidationContextIndicator indicator,
			PersistanceActionEnum persistType,
			NotaFiscalEntradaMaintenanceRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistanceEntrada(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturnEntrada(response, internalResponse, null, true);
	}

	private NotaFiscalSaidaResponse processSaida(ValidationContextIndicator indicator,
			PersistanceActionEnum persistType,
			NotaFiscalSaidaMaintenanceRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistanceSaida(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (NotaFiscalSaidaResponse)handleReturn((Response)response, internalResponse, null, true);
	}

	/**
	 * Fetch paged.
	 * 
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedEntrada(NotaFiscalInquiryRequest request, NotaFiscalEntradaResponse response)
	{
		InternalResultsResponse<NotaFiscalEntrada> internalResponse = new InternalResultsResponse<NotaFiscalEntrada>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getNotaFiscalBAC().fetchNotaFiscalEntradaByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Do persistance.
	 * 
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceEntrada(NotaFiscalEntradaMaintenanceRequest request,
			PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getNotaFiscalBAC().insertNotaFiscalEntrada(request);

			case UPDATE:
				return getNotaFiscalBAC().updateNotaFiscalEntrada(request);

			case DELETE:
				return getNotaFiscalBAC().deleteNotaFiscalEntrada(request);

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
	 * Fetch paged.
	 * 
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedSaida(NotaFiscalInquiryRequest request, NotaFiscalSaidaResponse response)
	{
		InternalResultsResponse<NotaFiscalSaida> internalResponse = new InternalResultsResponse<NotaFiscalSaida>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getNotaFiscalBAC().fetchNotaFiscalSaidaByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
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
	private Response handleReturn(Response response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{
		// In the case there was an Optimistic Locking error, add the specific message
		if (!ValidationUtil.isNull(internalResponse) && !ValidationUtil.isNull(internalResponse.getStatus())
				&& Status.OptimisticLockingError.equals(internalResponse.getStatus()))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_OL_ERROR, MessageSeverity.Error,
					MessageLevel.Object));
		}

		QATInterfaceUtil.handleOperationStatusAndMessages((com.qat.framework.model.response.Response)response,
				internalResponse, messages, copyOver);
		return response;
	}

	private NotaFiscalEntradaResponse handleReturnEntrada(NotaFiscalEntradaResponse response,
			InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{
		// In the case there was an Optimistic Locking error, add the specific message
		if (!ValidationUtil.isNull(internalResponse) && !ValidationUtil.isNull(internalResponse.getStatus())
				&& Status.OptimisticLockingError.equals(internalResponse.getStatus()))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_OL_ERROR, MessageSeverity.Error,
					MessageLevel.Object));
		}

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
	private InternalResponse doPersistanceSaida(NotaFiscalSaidaMaintenanceRequest request,
			PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getNotaFiscalBAC().insertNotaFiscalSaida(request);

			case UPDATE:
				return getNotaFiscalBAC().updateNotaFiscalSaida(request);

			case DELETE:
				return getNotaFiscalBAC().deleteNotaFiscalSaida(request);

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
	 * Process.
	 * 
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the notaFiscal response
	 */
	private PedidoComprasResponse processPedidoCompras(ValidationContextIndicator indicator,
			PersistanceActionEnum persistType,
			PedidoComprasMaintenanceRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistancePedidoCompras(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (PedidoComprasResponse)handleReturn((Response)response, internalResponse, null, true);
	}

	/**
	 * Fetch paged.
	 * 
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedPedidoCompras(PedidoComprasInquiryRequest request, PedidoComprasResponse response)
	{
		InternalResultsResponse<PedidoCompras> internalResponse = new InternalResultsResponse<PedidoCompras>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getNotaFiscalBAC().fetchPedidoComprasByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	private InternalResponse doPersistancePedidoCompras(PedidoComprasMaintenanceRequest request,
			PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getNotaFiscalBAC().insertPedidoCompras(request);

			case UPDATE:
				return getNotaFiscalBAC().updatePedidoCompras(request);

			case DELETE:
				return getNotaFiscalBAC().deletePedidoCompras(request);

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
	 * Process.
	 * 
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the notaFiscal response
	 */
	private OrcamentoResponse processOrcamento(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			OrcamentoMaintenanceRequest request)
	{
		OrcamentoResponse response = new OrcamentoResponse();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistanceOrcamento(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (OrcamentoResponse)handleReturn((Response)response, internalResponse, null, true);
	}

	/**
	 * Fetch paged.
	 * 
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedOrcamento(OrcamentoInquiryRequest request, OrcamentoResponse response)
	{
		InternalResultsResponse<Orcamento> internalResponse = new InternalResultsResponse<Orcamento>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getNotaFiscalBAC().fetchOrcamentoByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	private InternalResponse doPersistanceOrcamento(OrcamentoMaintenanceRequest request,
			PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getNotaFiscalBAC().insertOrcamento(request);

			case UPDATE:
				return getNotaFiscalBAC().updateOrcamento(request);

			case DELETE:
				return getNotaFiscalBAC().deleteOrcamento(request);

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
	 * Process.
	 * 
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the notaFiscal response
	 */
	private ContasResponse processContas(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			ContasMaintenanceRequest request)
	{
		ContasResponse response = new ContasResponse();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistanceContas(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (ContasResponse)handleReturn((Response)response, internalResponse, null, true);
	}

	/**
	 * Fetch paged.
	 * 
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedContas(ContasInquiryRequest request, ContasResponse response)
	{
		InternalResultsResponse<Contas> internalResponse = new InternalResultsResponse<Contas>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getNotaFiscalBAC().fetchContasByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	private InternalResponse doPersistanceContas(ContasMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getNotaFiscalBAC().insertContas(request);

			case UPDATE:
				return getNotaFiscalBAC().updateContas(request);

			case DELETE:
				return getNotaFiscalBAC().deleteContas(request);

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
	public CaixaResponse fetchCaixaByRequest(CaixaInquiryRequest request)
	{
		CaixaResponse response = new CaixaResponse();
		try
		{
			fetchPagedCaixa(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchPagedCaixa(CaixaInquiryRequest request, CaixaResponse response)
	{
		InternalResultsResponse<Caixa> internalResponse = new InternalResultsResponse<Caixa>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getNotaFiscalBAC().fetchCaixaByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public CondPgResponse fetchCondPgByRequest(CondPgInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
