package com.qat.samples.sysmgmt.site.baid;

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
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.site.Site;
import com.qat.samples.sysmgmt.site.bac.ISiteBAC;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.response.SiteResponse;

/**
 * Delegate class for Site BAI. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class SiteBAID
{

	/** The Constant DEFAULT_BUNDLE_BAID_EXCEPTION_MSG. */
	private static final String DEFAULT_BUNDLE_BAID_EXCEPTION_MSG =
			"sysmgmt.base.supermercadobaidimpl.defaultexception";

	/** The Constant SYSMGMT_BASE_ID_REQUIRED. */
	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SiteBAID.class);

	/**
	 * Required for final classes to insure no one tries to instantiate it.
	 */
	private SiteBAID()
	{
	}

	/**
	 * Maintain supermercado.
	 * 
	 * @param supermercadoBAC the supermercado bac
	 * @param validationIndicator the validation indicator
	 * @param controller the controller
	 * @param persistType the persist type
	 * @param request the request
	 * @param response the response
	 */
	public static void maintainSite(ISiteBAC supermercadoBAC,
			ValidationContextIndicator validationIndicator,
			ValidationController controller,
			PersistanceActionEnum persistType, SiteMaintenanceRequest request, SiteResponse response)
	{
		ValidationContext context =
				new ValidationContext(Site.class.getSimpleName(), request.getSite(),
						validationIndicator);

		InternalResponse internalResponse = new InternalResponse();
		if (controller.validate(context))
		{
			// perform persistence
			switch (persistType)
			{
				case INSERT:
					internalResponse = supermercadoBAC.insertSite(request.getSite());
					break;
				case UPDATE:
					internalResponse = supermercadoBAC.updateSite(request.getSite());
					break;
				case DELETE:
					internalResponse = supermercadoBAC.deleteSite(request.getSite());
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
				// maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response, supermercadoBAC);
			}
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
	}

	/**
	 * Refresh supermercados.
	 * 
	 * @param supermercadoBAC the supermercado bac
	 * @param request the request
	 * @param response the response
	 */
	public static void refreshSites(ISiteBAC supermercadoBAC, RefreshRequest request,
			SiteResponse response)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		supermercadoBAC.refreshSites(request.getRefreshInt());
		// Call maintain to see if we need to return the county list and if so whether it should be paged or not
		maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response, supermercadoBAC);
	}

	/**
	 * Fetch all supermercados.
	 * 
	 * @param supermercadoBAC the supermercado bac
	 * @param response the response
	 */
	public static void fetchAllSites(ISiteBAC supermercadoBAC, SiteResponse response)
	{
		InternalResultsResponse<Site> internalResponse = supermercadoBAC.fetchAllSites();
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch supermercados paged.
	 * 
	 * @param supermercadoBAC the supermercado bac
	 * @param request the request
	 * @param response the response
	 */
	public static void fetchSitesPaged(ISiteBAC supermercadoBAC, PagedInquiryRequest request,
			SiteResponse response)
	{
		InternalResultsResponse<Site> internalResponse = supermercadoBAC.fetchSitesByRequest(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch supermercado by id.
	 * 
	 * @param supermercadoBAC the supermercado bac
	 * @param request the request
	 * @param response the response
	 */
	public static void fetchSiteById(ISiteBAC supermercadoBAC, FetchByIdRequest request,
			SiteResponse response)
	{
		InternalResultsResponse<Site> internalResponse = new InternalResultsResponse<Site>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			internalResponse.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		}
		else
		{
			internalResponse = supermercadoBAC.fetchSiteById(request);
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
	 * @param supermercadoBAC the supermercado bac
	 */
	private static void maintainReturnList(Boolean listIndicator, Boolean pageListIndicator,
			SiteResponse response,
			ISiteBAC supermercadoBAC)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				fetchSitesPaged(supermercadoBAC, request, response);
			}
			else
			{
				// otherwise return all rows not paged
				fetchAllSites(supermercadoBAC, response);
			}
		}
	}
}
