package com.qat.samples.sysmgmt.bac.impl;

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
import com.qat.samples.sysmgmt.bac.ICountyBAC;
import com.qat.samples.sysmgmt.bar.ICountyBAR;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for County leveraging the injected ICountyBAR.
 */
@Component
public class CountyBACImpl implements ICountyBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_COUNTY_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_COUNTY_BAC_EXCEPTION_MSG = "sysmgmt.base.countybacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CountyBACImpl.class);

	/** The county BAR. */
	private ICountyBAR countyBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the county BAR.
	 *
	 * @param countyBAR the new county BAR
	 */
	public void setCountyBAR(ICountyBAR countyBAR)
	{
		this.countyBAR = countyBAR;
	}

	/**
	 * Gets the county BAR.
	 *
	 * @return the county BAR
	 */
	public ICountyBAR getCountyBAR()
	{
		return countyBAR;
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCounty(com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<County> insertCounty(CountyMaintenanceRequest request)
	{
		InternalResultsResponse<County> response =
				process(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#updateCounty(com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<County> updateCounty(CountyMaintenanceRequest request)
	{
		InternalResultsResponse<County> response =
				process(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#deleteCounty(com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<County> deleteCounty(CountyMaintenanceRequest request)
	{
		InternalResultsResponse<County> response =
				process(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.ICountyBAC#refreshCountys(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<County> refreshCounties(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getCountyBAR().deleteAllCounties();
		int refreshNumber = request.getRefreshInt();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
			getCountyBAR().insertCounty(new County(i, "CountyDesc" + i));
		}

		// Call maintain to see if we need to return the county list and if so whether it should be paged or not
		return maintainReturnList(request.getReturnList(), request.getReturnListPaged());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.ICountyBAC#fetchAllCountys()
	 */
	@Override
	public InternalResultsResponse<County> fetchAllCounties()
	{
		InternalResultsResponse<County> response = new InternalResultsResponse<County>();
		response.getResultsList().addAll(getCountyBAR().fetchAllCounties().getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#fetchCountyById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<County> fetchCountyById(FetchByIdRequest request)
	{
		InternalResultsResponse<County> response = new InternalResultsResponse<County>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getCountyBAR().fetchCountyById(request).getFirstResult());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.ICountyBAC#fetchCountiesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<County> fetchCountiesByRequest(PagedInquiryRequest request)
	{
		return getCountyBAR().fetchCountiesByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the county response
	 */
	private InternalResultsResponse<County> process(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			CountyMaintenanceRequest request)
			{
		InternalResultsResponse<County> response = null;

		// Validate
		ValidationContext context = new ValidationContext(County.class.getSimpleName(), request.getCounty(), indicator);
		if (!getValidationController().validate(context))
		{
			response = new InternalResultsResponse<County>();
			response.setStatus(SystemErrorCategory.SystemValidation);
			response.addMessages(context.getMessages());
			return response;
		}

		// Persist
		InternalResponse internalResponse = doPersistence(request.getCounty(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<County>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_COUNTY_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the county list and if so whether it should be paged or
		// not
		response = maintainReturnList(request.getReturnList(), request.getReturnListPaged());

		return response;
			}

	/**
	 * Do persistence.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistence(County county, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCountyBAR().insertCounty(county);

			case UPDATE:
				return getCountyBAR().updateCounty(county);

			case DELETE:
				return getCountyBAR().deleteCountyById(county);
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
	private InternalResultsResponse<County> maintainReturnList(Boolean listIndicator, Boolean pageListIndicator)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchCountiesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllCounties();
			}
		}
		else
		{
			return new InternalResultsResponse<County>();
		}
	}
}
