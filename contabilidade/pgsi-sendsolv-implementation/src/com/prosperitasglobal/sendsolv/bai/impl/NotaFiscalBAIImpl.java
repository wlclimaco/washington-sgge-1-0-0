package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import javax.xml.ws.Response;

import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.INotaFiscalBAC;
import com.prosperitasglobal.sendsolv.bai.INotaFiscalBAI;
import com.prosperitasglobal.sendsolv.model.NotaFiscal;
import com.prosperitasglobal.sendsolv.model.request.ContasMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PedidoComprasMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.ContasResponse;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalEntradaResponse;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalResponse;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalSaidaResponse;
import com.prosperitasglobal.sendsolv.model.response.OrcamentoResponse;
import com.prosperitasglobal.sendsolv.model.response.PedidoComprasResponse;
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
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
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
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradaByRequest(NotaFiscalEntradaMaintenanceRequest request)
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
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
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
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidaByRequest(NotaFiscalSaidaMaintenanceRequest request)
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
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
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
	public PedidoComprasResponse fetchPedidoComprasByRequest(PedidoComprasMaintenanceRequest request)
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
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
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
	public OrcamentoResponse fetchOrcamentoByRequest(OrcamentoMaintenanceRequest request)
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
	public ContasResponse fetchContasByRequest(ContasMaintenanceRequest request)
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
	public ContasResponse insertContas(NotaFiscalSaidaMaintenanceRequest request)
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
	}

	@Override
	public ContasResponse updateContas(NotaFiscalSaidaMaintenanceRequest request)
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
	public ContasResponse deleteContas(NotaFiscalSaidaMaintenanceRequest request)
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
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
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
	private NotaFiscalResponseEntrada processEntrada(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			NotaFiscalEntradaMaintenanceRequest request)
	{
		NotaFiscalResponseEntrada response = new NotaFiscalResponseEntrada();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistanceEntrada(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (NotaFiscalResponseEntrada)handleReturn((Response)response, internalResponse, null, true);
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

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistance(NotaFiscalEntradaMaintenanceRequest request, PersistanceActionEnum updateType)
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

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceSaida(NotaFiscalSaidaMaintenanceRequest request, PersistanceActionEnum updateType)
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
	private PedidoComprasResponse processPedidoCompras(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			PedidoComprasMaintenanceRequest request)
	{
		NotaFiscalResponseEntrada response = new NotaFiscalResponseEntrada();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistanceEntrada(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (NotaFiscalResponseEntrada)handleReturn((Response)response, internalResponse, null, true);
	}

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedPedidoCompras(NotaFiscalInquiryRequest request, PedidoComprasResponse response)
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

	private InternalResponse doPersistancePedidoCompras(PedidoComprasMaintenanceRequest request, PersistanceActionEnum updateType)
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
		NotaFiscalResponseEntrada response = new NotaFiscalResponseEntrada();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistanceEntrada(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (NotaFiscalResponseEntrada)handleReturn((Response)response, internalResponse, null, true);
	}

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedOrcamento(NotaFiscalInquiryRequest request, OrcamentoResponse response)
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

	private InternalResponse doPersistanceOrcamento(OrcamentoMaintenanceRequest request, PersistanceActionEnum updateType)
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
		NotaFiscalResponseEntrada response = new NotaFiscalResponseEntrada();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistanceEntrada(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (NotaFiscalResponseEntrada)handleReturn((Response)response, internalResponse, null, true);
	}

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedContas(NotaFiscalInquiryRequest request, ContasResponse response)
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


}
