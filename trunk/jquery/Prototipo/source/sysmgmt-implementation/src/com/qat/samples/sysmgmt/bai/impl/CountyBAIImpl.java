package com.qat.samples.sysmgmt.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bac.ICountyBAC;
import com.qat.samples.sysmgmt.bai.ICountyBAI;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;

/**
 * Implementation of a BAI, well formed with lots of modularization.
 */
public class CountyBAIImpl implements ICountyBAI
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.countybaiimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CountyBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CountyBAIImpl.class);

	/** The county bac. */
	private ICountyBAC countyBAC; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController validationController;

	/**
	 * Get county validation controller.
	 * 
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Spring sets the county validation controller.
	 * 
	 * @param countyValidationController the new validation controller
	 */
	public void setValidationController(ValidationController countyValidationController)
	{
		validationController = countyValidationController;
	}

	/**
	 * Spring Sets the county bac.
	 * 
	 * @param countyBAC the new county bac
	 */
	public void setCountyBAC(ICountyBAC countyBAC)
	{
		this.countyBAC = countyBAC;
	}

	/**
	 * Gets the county bac.
	 * 
	 * @return the county bac
	 */
	public ICountyBAC getCountyBAC()
	{
		return countyBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.ICountyBAI#insertCounty(com.qat.samples.sysmgmt.model.request.CountyRequest)
	 * Leveraging the common process method to perform the "real"work.
	 * Wrapped in a try-catch to insure we never return an exception from this operation.
	 */
	@Override
	public CountyResponse insertCounty(CountyMaintenanceRequest request)
	{
		CountyResponse response = new CountyResponse();
		try
		{
			response = process(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.ICountyBAI#updateCounty(com.qat.samples.sysmgmt.model.request.CountyRequest)
	 * Leveraging the common process method to perform the "real"work.
	 * Wrapped in a try-catch to insure we never return an exception from this operation.
	 */
	@Override
	public CountyResponse updateCounty(CountyMaintenanceRequest request)
	{
		CountyResponse response = new CountyResponse();
		try
		{
			response = process(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.ICountyBAI#deleteCounty(com.qat.samples.sysmgmt.model.request.CountyRequest)
	 * Leveraging the common process method to perform the "real"work.
	 * Wrapped in a try-catch to insure we never return an exception from this operation.
	 */
	@Override
	public CountyResponse deleteCounty(CountyMaintenanceRequest request)
	{
		CountyResponse response = new CountyResponse();
		try
		{
			response = process(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.ICountyBAI#refreshCounties(com.qat.samples.sysmgmt.model.request.CountyRequest)
	 */
	@Override
	public CountyResponse refreshCounties(RefreshRequest request)
	{
		CountyResponse response = new CountyResponse();

		try
		{
			// This method is demo code only. Do not view this as a QAT Standard.
			getCountyBAC().refreshCounties(request.getRefreshInt());
			// Call maintain to see if we need to return the county list and if so whether it should be paged or not
			maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.ICountyBAI#fetchAllCounties(com.qat.samples.sysmgmt.model.request.CountyRequest)
	 */
	@Override
	public CountyResponse fetchAllCounties(FetchAllRequest request)
	{
		CountyResponse response = new CountyResponse();
		try
		{
			fetchAll(response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.ICountyBAI#fetchCountyById(com.qat.samples.sysmgmt.model.request.CountyRequest)
	 */
	@Override
	public CountyResponse fetchCountyById(FetchByIdRequest request)
	{
		CountyResponse response = new CountyResponse();
		try
		{
			InternalResultsResponse<County> internalResponse = new InternalResultsResponse<County>();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getFetchId()))
			{
				internalResponse.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			}
			else
			{
				internalResponse = getCountyBAC().fetchCountyById(request);
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

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.ICountyBAI#fetchCountiesByRequest(com.qat.samples.sysmgmt.model.request.PagedInquiryRequest)
	 */
	@Override
	public CountyResponse fetchCountiesByRequest(PagedInquiryRequest request)
	{
		CountyResponse response = new CountyResponse();
		try
		{
			fetchPaged(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Convenience method to get all the counties.
	 * 
	 * @param response the response
	 * @param request the request
	 */
	private void fetchAll(CountyResponse response)
	{
		InternalResultsResponse<County> internalResponse = getCountyBAC().fetchAllCounties();
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch paged.
	 * 
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPaged(PagedInquiryRequest request, CountyResponse response)
	{
		InternalResultsResponse<County> internalResponse = getCountyBAC().fetchCountiesByRequest(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
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
	 * @return the county response
	 */
	private CountyResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			CountyMaintenanceRequest request)
	{
		CountyResponse response = new CountyResponse();
		InternalResponse internalResponse = null;

		// Validate
		ValidationContext context = new ValidationContext(County.class.getSimpleName(), request.getCounty(), indicator);
		if (!getValidationController().validate(context))
		{
			return handleReturn(response, internalResponse, context.getMessages(), true);
		}

		// Persist
		internalResponse = doPersistance(request, persistType);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			return handleReturn(response, internalResponse, context.getMessages(), true);
		}

		// Call maintainReurnList to see if we need to return the county list and if so whether it should be paged or not
		maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturn(response, internalResponse, context.getMessages(), false);
	}

	/**
	 * Maintain return list.
	 * 
	 * @param request the request
	 * @param response the response
	 */
	private void maintainReturnList(Boolean listIndicator, Boolean pageListIndicator, CountyResponse response)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				fetchPaged(request, response);
			}
			else
			{
				// otherwise return all rows not paged
				fetchAll(response);
			}
		}
	}

	/**
	 * Handle return.
	 * 
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the county response
	 */
	private CountyResponse handleReturn(CountyResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

	/**
	 * Do persistance.
	 * 
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistance(CountyMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCountyBAC().insertCounty(request.getCounty());

			case UPDATE:
				return getCountyBAC().updateCounty(request.getCounty());

			case DELETE:
				return getCountyBAC().deleteCounty(request.getCounty());
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
