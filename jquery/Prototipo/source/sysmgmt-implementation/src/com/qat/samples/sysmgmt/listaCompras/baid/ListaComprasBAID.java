package com.qat.samples.sysmgmt.listaCompras.baid;

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
import com.qat.samples.sysmgmt.listaCompras.bac.IListaComprasBAC;
import com.qat.samples.sysmgmt.listaCompras.model.ListaCompras;
import com.qat.samples.sysmgmt.listaCompras.model.request.ListaComprasMaintenanceRequest;
import com.qat.samples.sysmgmt.listaCompras.model.response.ListaComprasResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * Delegate class for ListaCompras BAI. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ListaComprasBAID
{

	/** The Constant DEFAULT_BUNDLE_BAID_EXCEPTION_MSG. */
	private static final String DEFAULT_BUNDLE_BAID_EXCEPTION_MSG = "sysmgmt.base.cidadebaidimpl.defaultexception";

	/** The Constant SYSMGMT_BASE_ID_REQUIRED. */
	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ListaComprasBAID.class);

	/**
	 * Required for final classes to insure no one tries to instantiate it.
	 */
	private ListaComprasBAID()
	{
	}

	/**
	 * Maintain cidade.
	 * 
	 * @param cidadeBAC the cidade bac
	 * @param validationIndicator the validation indicator
	 * @param controller the controller
	 * @param persistType the persist type
	 * @param request the request
	 * @param response the response
	 */
	public static void maintainListaCompras(IListaComprasBAC cidadeBAC, ValidationContextIndicator validationIndicator,
			ValidationController controller,
			PersistanceActionEnum persistType, ListaComprasMaintenanceRequest request, ListaComprasResponse response)
	{
		ValidationContext context =
				new ValidationContext(ListaCompras.class.getSimpleName(), request.getListaCompras(),
						validationIndicator);

		InternalResponse internalResponse = new InternalResponse();
		if (controller.validate(context))
		{
			// perform persistence
			switch (persistType)
			{
				case INSERT:
					internalResponse = cidadeBAC.insertListaCompras(request.getListaCompras());
					break;
				case UPDATE:
					internalResponse = cidadeBAC.updateListaCompras(request.getListaCompras());
					break;
				case DELETE:
					internalResponse = cidadeBAC.deleteListaCompras(request.getListaCompras());
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
				maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response, cidadeBAC);
			}
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
	}

	/**
	 * Refresh cidades.
	 * 
	 * @param cidadeBAC the cidade bac
	 * @param request the request
	 * @param response the response
	 */
	public static void refreshListaComprass(IListaComprasBAC cidadeBAC, RefreshRequest request,
			ListaComprasResponse response)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		cidadeBAC.refreshListaComprass(request.getRefreshInt());
		// Call maintain to see if we need to return the county list and if so whether it should be paged or not
		maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response, cidadeBAC);
	}

	/**
	 * Fetch all cidades.
	 * 
	 * @param cidadeBAC the cidade bac
	 * @param response the response
	 */
	public static void fetchAllListaComprass(IListaComprasBAC cidadeBAC, ListaComprasResponse response)
	{
		InternalResultsResponse<ListaCompras> internalResponse = cidadeBAC.fetchAllListaComprass();
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch cidades paged.
	 * 
	 * @param cidadeBAC the cidade bac
	 * @param request the request
	 * @param response the response
	 */
	public static void fetchListaComprassPaged(IListaComprasBAC cidadeBAC, PagedInquiryRequest request,
			ListaComprasResponse response)
	{
		InternalResultsResponse<ListaCompras> internalResponse = cidadeBAC.fetchListaComprassByRequest(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch cidade by id.
	 * 
	 * @param cidadeBAC the cidade bac
	 * @param request the request
	 * @param response the response
	 */
	public static void fetchListaComprasById(IListaComprasBAC cidadeBAC, FetchByIdRequest request,
			ListaComprasResponse response)
	{
		InternalResultsResponse<ListaCompras> internalResponse = new InternalResultsResponse<ListaCompras>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			internalResponse.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		}
		else
		{
			internalResponse = cidadeBAC.fetchListaComprasById(request);
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
	 * @param cidadeBAC the cidade bac
	 */
	private static void maintainReturnList(Boolean listIndicator, Boolean pageListIndicator,
			ListaComprasResponse response,
			IListaComprasBAC cidadeBAC)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				fetchListaComprassPaged(cidadeBAC, request, response);
			}
			else
			{
				// otherwise return all rows not paged
				fetchAllListaComprass(cidadeBAC, response);
			}
		}
	}
}
