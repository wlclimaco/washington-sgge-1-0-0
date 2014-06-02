package com.qat.samples.sysmgmt.baid;

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
import com.qat.samples.sysmgmt.bac.IBundleBAC;
import com.qat.samples.sysmgmt.model.Bundle;
import com.qat.samples.sysmgmt.model.request.BundleMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.BundleResponse;

/**
 * Delegate class for Bundle BAI. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class BundleBAID
{

	/** The Constant DEFAULT_BUNDLE_BAID_EXCEPTION_MSG. */
	private static final String DEFAULT_BUNDLE_BAID_EXCEPTION_MSG = "sysmgmt.base.bundlebaidimpl.defaultexception";

	/** The Constant SYSMGMT_BASE_ID_REQUIRED. */
	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(BundleBAID.class);

	/**
	 * Required for final classes to insure no one tries to instantiate it.
	 */
	private BundleBAID()
	{
	}

	/**
	 * Maintain bundle.
	 * 
	 * @param bundleBAC the bundle bac
	 * @param validationIndicator the validation indicator
	 * @param controller the controller
	 * @param persistType the persist type
	 * @param request the request
	 * @param response the response
	 */
	public static void maintainBundle(IBundleBAC bundleBAC, ValidationContextIndicator validationIndicator,
			ValidationController controller,
			PersistanceActionEnum persistType, BundleMaintenanceRequest request, BundleResponse response)
	{
		ValidationContext context =
				new ValidationContext(Bundle.class.getSimpleName(), request.getBundle(), validationIndicator);

		InternalResponse internalResponse = new InternalResponse();
		if (controller.validate(context))
		{
			// perform persistence
			switch (persistType)
			{
				case INSERT:
					internalResponse = bundleBAC.insertBundle(request.getBundle());
					break;
				case UPDATE:
					internalResponse = bundleBAC.updateBundle(request.getBundle());
					break;
				case DELETE:
					internalResponse = bundleBAC.deleteBundle(request.getBundle());
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
				maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response, bundleBAC);
			}
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
	}

	/**
	 * Refresh bundles.
	 * 
	 * @param bundleBAC the bundle bac
	 * @param request the request
	 * @param response the response
	 */
	public static void refreshBundles(IBundleBAC bundleBAC, RefreshRequest request, BundleResponse response)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		bundleBAC.refreshBundles(request.getRefreshInt());
		// Call maintain to see if we need to return the county list and if so whether it should be paged or not
		maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response, bundleBAC);
	}

	/**
	 * Fetch all bundles.
	 * 
	 * @param bundleBAC the bundle bac
	 * @param response the response
	 */
	public static void fetchAllBundles(IBundleBAC bundleBAC, BundleResponse response)
	{
		InternalResultsResponse<Bundle> internalResponse = bundleBAC.fetchAllBundles();
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch bundles paged.
	 * 
	 * @param bundleBAC the bundle bac
	 * @param request the request
	 * @param response the response
	 */
	public static void fetchBundlesPaged(IBundleBAC bundleBAC, PagedInquiryRequest request, BundleResponse response)
	{
		InternalResultsResponse<Bundle> internalResponse = bundleBAC.fetchBundlesByRequest(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch bundle by id.
	 * 
	 * @param bundleBAC the bundle bac
	 * @param request the request
	 * @param response the response
	 */
	public static void fetchBundleById(IBundleBAC bundleBAC, FetchByIdRequest request, BundleResponse response)
	{
		InternalResultsResponse<Bundle> internalResponse = new InternalResultsResponse<Bundle>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			internalResponse.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		}
		else
		{
			internalResponse = bundleBAC.fetchBundleById(request);
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
	 * @param bundleBAC the bundle bac
	 */
	private static void maintainReturnList(Boolean listIndicator, Boolean pageListIndicator, BundleResponse response,
			IBundleBAC bundleBAC)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				fetchBundlesPaged(bundleBAC, request, response);
			}
			else
			{
				// otherwise return all rows not paged
				fetchAllBundles(bundleBAC, response);
			}
		}
	}
}
