package com.qat.samples.sysmgmt.bac.impl;

import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bac.ICountyBAC;
import com.qat.samples.sysmgmt.dac.ICountyDAC;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

// TODO: Auto-generated Javadoc
/**
 * Standards based implementation of a BAC for County leveraging the injected ICountyDAC.
 */
public class CountyBACImpl implements ICountyBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_COUNTY_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_COUNTY_BAC_EXCEPTION_MSG = "sysmgmt.base.countybacimpl.defaultexception";

	/** The county dac. */
	private ICountyDAC countyDAC; // injected by Spring through setter

	/**
	 * Spring Sets the county dac.
	 * 
	 * @param countyDAC the new county dac
	 */
	public void setCountyDAC(ICountyDAC countyDAC)
	{
		this.countyDAC = countyDAC;
	}

	/**
	 * Gets the county dac.
	 * 
	 * @return the county dac
	 */
	public ICountyDAC getCountyDAC()
	{
		return countyDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.ICountyBAC#insertCounty(com.qat.samples.sysmgmt.base.model.County)
	 */
	@Override
	public InternalResponse insertCounty(County county)
	{
		return getCountyDAC().insertCounty(county);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.ICountyBAC#updateCounty(com.qat.samples.sysmgmt.base.model.County)
	 */
	@Override
	public InternalResponse updateCounty(County county)
	{
		InternalResponse internalResponse = getCountyDAC().updateCounty(county);
		// Check for error because in business case all non-success returns are failures (updating of zero rows) according to the business
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			internalResponse.addMessage(DEFAULT_COUNTY_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error, Message.MessageLevel.Object, new Object[] {internalResponse
					.getStatus().toString()});
		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.ICountyBAC#deleteCounty(com.qat.samples.sysmgmt.base.model.County)
	 */
	@Override
	public InternalResponse deleteCounty(County county)
	{
		InternalResponse internalResponse = getCountyDAC().deleteCountyById(county);
		// Check for error because in business case all non-success returns are failures (removal of zero rows) according to the business
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			internalResponse.addMessage(DEFAULT_COUNTY_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error, Message.MessageLevel.Object, new Object[] {internalResponse
					.getStatus().toString()});
		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.ICountyBAC#refreshCountys(int)
	 */
	@Override
	public void refreshCounties(Integer refreshNumber)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getCountyDAC().deleteAllCounties();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
			getCountyDAC().insertCounty(new County(i, "CountyDesc" + i));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.ICountyBAC#fetchAllCountys()
	 */
	@Override
	public InternalResultsResponse<County> fetchAllCounties()
	{
		InternalResultsResponse<County> response = new InternalResultsResponse<County>();
		response.getResultsList().addAll(getCountyDAC().fetchAllCounties().getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.ICountyBAC#fetchCountyById(com.qat.samples.sysmgmt.base.model.County
	 * )
	 */
	@Override
	public InternalResultsResponse<County> fetchCountyById(FetchByIdRequest request)
	{
		InternalResultsResponse<County> response = new InternalResultsResponse<County>();
		response.getResultsList().add(getCountyDAC().fetchCountyById(request).getFirstResult());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.ICountyBAC#fetchCountiesByRequest(com.qat.samples.sysmgmt.model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<County> fetchCountiesByRequest(PagedInquiryRequest request)
	{
		return getCountyDAC().fetchCountiesByRequest(request);
	}
}
