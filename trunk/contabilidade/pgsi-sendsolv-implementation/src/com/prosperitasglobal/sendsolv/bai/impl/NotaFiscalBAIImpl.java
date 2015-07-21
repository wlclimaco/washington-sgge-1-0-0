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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#insertLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	// @Override
	// public NotaFiscalResponse insertNotaFiscal(NotaFiscalMaintenanceRequest request)
	// {
	// NotaFiscalResponse response = new NotaFiscalResponse();
	// try
	// {
	// response = process(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	//
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#updateLocaation(com.prosperitasglobal.sendsolv.model
	// * .request.LocaationRequest)
	// * Leveraging the common process method to perform the "real" work.
	// * Wrapped in a try-catch to ensure we never return an exception from this operation.
	// */
	// @Override
	// public NotaFiscalResponse updateNotaFiscal(NotaFiscalMaintenanceRequest request)
	// {
	// NotaFiscalResponse response = new NotaFiscalResponse();
	// try
	// {
	// response = process(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	//
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#deleteLocaation(com.prosperitasglobal.sendsolv.model
	// * .request.LocaationRequest)
	// * Leveraging the common process method to perform the "real" work.
	// * Wrapped in a try-catch to ensure we never return an exception from this operation.
	// */
	// @Override
	// public NotaFiscalResponse deleteNotaFiscal(NotaFiscalMaintenanceRequest request)
	// {
	// NotaFiscalResponse response = new NotaFiscalResponse();
	// try
	// {
	// response = process(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	//
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#fetchLocaationById(com.prosperitasglobal.sendsolv.model.
	// * request
	// * .CountyRequest)
	// */
	// @Override
	// public NotaFiscalResponse fetchNotaFiscalById(FetchByIdRequest request)
	// {
	// NotaFiscalResponse response = new NotaFiscalResponse();
	// try
	// {
	// InternalResponse internalResponse = new InternalResponse();
	// // validate fetchId field
	// if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
	// {
	// internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
	// }
	// else
	// {
	// internalResponse = getNotaFiscalBAC().fetchNotaFiscalById(request);
	// }
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	//
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.bai.INotaFiscalBAI#fetchNotaFiscalByRequest(com.prosperitasglobal.sendsolv.model
	// * .request.PagedInquiryRequest)
	// */
	// @Override
	// public NotaFiscalResponse fetchNotaFiscalByRequest(NotaFiscalInquiryRequest request)
	// {
	// NotaFiscalResponse response = new NotaFiscalResponse();
	// try
	// {
	// fetchPaged(request, response);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	// return response;
	// }

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.INotaFiscalBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPaged(NotaFiscalInquiryRequest request, NotaFiscalResponse response)
	{
		InternalResultsResponse<NotaFiscal> internalResponse = new InternalResultsResponse<NotaFiscal>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			// internalResponse = getNotaFiscalBAC().fetchNotaFiscalByRequest(request);
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
	 * @return the notaFiscal response
	 */
	private NotaFiscalResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			NotaFiscalMaintenanceRequest request)
	{
		NotaFiscalResponse response = new NotaFiscalResponse();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistance(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return (NotaFiscalResponse)handleReturn((Response)response, internalResponse, null, true);
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
	private InternalResponse doPersistance(NotaFiscalMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			// case INSERT:
			// return getNotaFiscalBAC().insertNotaFiscal(request);
			//
			// case UPDATE:
			// return getNotaFiscalBAC().updateNotaFiscal(request);
			//
			// case DELETE:
			// return getNotaFiscalBAC().deleteNotaFiscal(request);
			//
			// default:
			// if (LOG.isDebugEnabled())
			// {
			// LOG.debug("updateType missing!");
			// }
			// break;
		}
		return null;
	}

	@Override
	public NotaFiscalEntradaResponse insertNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotaFiscalEntradaResponse updateNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotaFiscalEntradaResponse deleteNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradaById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradaByRequest(NotaFiscalEntradaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotaFiscalSaidaResponse insertNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotaFiscalSaidaResponse updateNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotaFiscalSaidaResponse deleteNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidaById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidaByRequest(NotaFiscalSaidaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoComprasResponse insertPedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoComprasResponse updatePedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoComprasResponse deletePedidoComprasl(PedidoComprasMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoComprasResponse fetchPedidoComprasById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoComprasResponse fetchPedidoComprasByRequest(PedidoComprasMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrcamentoResponse insertOrcamento(OrcamentoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrcamentoResponse updateOrcamento(OrcamentoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrcamentoResponse deleteOrcamento(OrcamentoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrcamentoResponse fetchOrcamentoById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrcamentoResponse fetchOrcamentoByRequest(OrcamentoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContasResponse fetchContasByRequest(ContasMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContasResponse insertContas(NotaFiscalSaidaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContasResponse updateContas(NotaFiscalSaidaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContasResponse deleteContas(NotaFiscalSaidaMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContasResponse fetchContasById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
