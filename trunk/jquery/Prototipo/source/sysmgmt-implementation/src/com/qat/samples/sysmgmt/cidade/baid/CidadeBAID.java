package com.qat.samples.sysmgmt.cidade.baid;

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
import com.qat.samples.sysmgmt.cidade.bac.ICidadeBAC;
import com.qat.samples.sysmgmt.cidade.model.Cidade;
import com.qat.samples.sysmgmt.cidade.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.cidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.cidade.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * Delegate class for Cidade BAI. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class CidadeBAID
{

	/** The Constant DEFAULT_BUNDLE_BAID_EXCEPTION_MSG. */
	private static final String DEFAULT_BUNDLE_BAID_EXCEPTION_MSG = "sysmgmt.base.cidadebaidimpl.defaultexception";

	/** The Constant SYSMGMT_BASE_ID_REQUIRED. */
	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CidadeBAID.class);

	/**
	 * Required for final classes to insure no one tries to instantiate it.
	 */
	private CidadeBAID()
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
	public static void maintainCidade(ICidadeBAC cidadeBAC, ValidationContextIndicator validationIndicator,
			ValidationController controller,
			PersistanceActionEnum persistType, CidadeMaintenanceRequest request, CidadeResponse response)
	{
		ValidationContext context =
				new ValidationContext(Cidade.class.getSimpleName(), request.getCidade(), validationIndicator);

		InternalResponse internalResponse = new InternalResponse();
		if (controller.validate(context))
		{
			// perform persistence
			switch (persistType)
			{
				case INSERT:
					internalResponse = cidadeBAC.insertCidade(request.getCidade());
					break;
				case UPDATE:
					internalResponse = cidadeBAC.updateCidade(request.getCidade());
					break;
				case DELETE:
					internalResponse = cidadeBAC.deleteCidade(request.getCidade());
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
	public static void refreshCidades(ICidadeBAC cidadeBAC, RefreshRequest request, CidadeResponse response)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		cidadeBAC.refreshCidades(request.getRefreshInt());
		// Call maintain to see if we need to return the county list and if so whether it should be paged or not
		maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response, cidadeBAC);
	}

	/**
	 * Fetch all cidades.
	 * 
	 * @param cidadeBAC the cidade bac
	 * @param response the response
	 */
	public static void fetchAllCidades(ICidadeBAC cidadeBAC, CidadeResponse response)
	{
		InternalResultsResponse<Cidade> internalResponse = cidadeBAC.fetchAllCidades();
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
	public static void fetchCidadesPaged(ICidadeBAC cidadeBAC, CidadeInquiryRequest request, CidadeResponse response)
	{
		InternalResultsResponse<Cidade> internalResponse = cidadeBAC.fetchCidadesByRequest(request);
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
	public static void fetchCidadeById(ICidadeBAC cidadeBAC, FetchByIdRequest request, CidadeResponse response)
	{
		InternalResultsResponse<Cidade> internalResponse = new InternalResultsResponse<Cidade>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			internalResponse.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		}
		else
		{
			internalResponse = cidadeBAC.fetchCidadeById(request);
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
	private static void maintainReturnList(Boolean listIndicator, Boolean pageListIndicator, CidadeResponse response,
			ICidadeBAC cidadeBAC)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				CidadeInquiryRequest request = new CidadeInquiryRequest();
				request.setPreQueryCount(true);
				fetchCidadesPaged(cidadeBAC, request, response);
				// fetchAllCidades(cidadeBAC, response);
			}
			else
			{
				// otherwise return all rows not paged
				fetchAllCidades(cidadeBAC, response);
			}
		}
	}
}
