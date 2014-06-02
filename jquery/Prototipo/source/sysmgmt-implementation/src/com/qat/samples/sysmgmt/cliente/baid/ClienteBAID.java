package com.qat.samples.sysmgmt.cliente.baid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.cliente.bac.IClienteBAC;
import com.qat.samples.sysmgmt.cliente.model.Cliente;
import com.qat.samples.sysmgmt.cliente.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.cliente.model.response.ClienteResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * Delegate class for Cliente BAI. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ClienteBAID
{

	/** The Constant DEFAULT_BUNDLE_BAID_EXCEPTION_MSG. */
	private static final String DEFAULT_BUNDLE_BAID_EXCEPTION_MSG = "sysmgmt.base.clientebaidimpl.defaultexception";

	/** The Constant SYSMGMT_BASE_ID_REQUIRED. */
	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ClienteBAID.class);

	/**
	 * Required for final classes to insure no one tries to instantiate it.
	 */
	private ClienteBAID()
	{
	}

	/**
	 * Maintain cliente.
	 * 
	 * @param clienteBAC the cliente bac
	 * @param validationIndicator the validation indicator
	 * @param controller the controller
	 * @param persistType the persist type
	 * @param request the request
	 * @param response the response
	 */
	public static void maintainCliente(IClienteBAC clienteBAC, ValidationContextIndicator validationIndicator,
			ValidationController controller,
			PersistanceActionEnum persistType, ClienteMaintenanceRequest request, ClienteResponse response)
	{
		ValidationContext context =
				new ValidationContext(Cliente.class.getSimpleName(), request.getCliente(), validationIndicator);

		InternalResponse internalResponse = new InternalResponse();
		if (controller.validate(context))
		{
			// perform persistence
			switch (persistType)
			{
				case INSERT:
					internalResponse = clienteBAC.insertCliente(request.getCliente());
					break;
				case UPDATE:
					internalResponse = clienteBAC.updateCliente(request.getCliente());
					break;
				case DELETE:
					internalResponse = clienteBAC.deleteCliente(request.getCliente());
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("persistType missing! Setting Unspecified Error status.");
					}
					internalResponse.setStatus(InternalResponse.Status.UnspecifiedError);
					break;
			}

			// If the persistence worked
			if (internalResponse.getStatus() == Status.OperationSuccess)
			{
				// Call maintain to see if we need to return the county list and if so whether it should be paged or not
				maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response, clienteBAC);
			}
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
	}

	/**
	 * Refresh clientes.
	 * 
	 * @param clienteBAC the cliente bac
	 * @param request the request
	 * @param response the response
	 */
	public static void refreshClientes(IClienteBAC clienteBAC, RefreshRequest request, ClienteResponse response)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		clienteBAC.refreshClientes(request.getRefreshInt());
		// Call maintain to see if we need to return the county list and if so whether it should be paged or not
		maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response, clienteBAC);
	}

	/**
	 * Fetch all clientes.
	 * 
	 * @param clienteBAC the cliente bac
	 * @param response the response
	 */
	public static void fetchAllClientes(IClienteBAC clienteBAC, ClienteResponse response)
	{
		InternalResultsResponse<Cliente> internalResponse = clienteBAC.fetchAllClientes();
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch clientes paged.
	 * 
	 * @param clienteBAC the cliente bac
	 * @param request the request
	 * @param response the response
	 */
	public static void fetchClientesPaged(IClienteBAC clienteBAC, PagedInquiryRequest request, ClienteResponse response)
	{
		InternalResultsResponse<Cliente> internalResponse = clienteBAC.fetchClientesByRequest(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch cliente by id.
	 * 
	 * @param clienteBAC the cliente bac
	 * @param request the request
	 * @param response the response
	 */
	public static void fetchClienteById(IClienteBAC clienteBAC, FetchByIdRequest request, ClienteResponse response)
	{
		InternalResultsResponse<Cliente> internalResponse = new InternalResultsResponse<Cliente>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			internalResponse.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		}
		else
		{
			internalResponse = clienteBAC.fetchClienteById(request);
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Maintain return list.
	 * 
	 * @param listIndicator the list indicator
	 * @param pageListIndicator the page list indicator
	 * @param response the response
	 * @param clienteBAC the cliente bac
	 */
	private static void maintainReturnList(Boolean listIndicator, Boolean pageListIndicator, ClienteResponse response,
			IClienteBAC clienteBAC)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				fetchClientesPaged(clienteBAC, request, response);
			}
			else
			{
				// otherwise return all rows not paged
				fetchAllClientes(clienteBAC, response);
			}
		}
	}
}
