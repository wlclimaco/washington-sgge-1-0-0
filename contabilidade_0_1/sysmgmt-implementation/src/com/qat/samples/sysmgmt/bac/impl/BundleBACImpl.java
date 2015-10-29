package com.qat.samples.sysmgmt.bac.impl;

import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bac.IBundleBAC;
import com.qat.samples.sysmgmt.bad.BundleBAD;
import com.qat.samples.sysmgmt.dac.IBundleDAC;
import com.qat.samples.sysmgmt.model.Bundle;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * Implementation of the IBundleBAC leveraging a BAD, BundleBAD.
 */
public class BundleBACImpl implements IBundleBAC
{

	/** The Constant REFRESH_SEED. */
	private static final int REFRESH_SEED = 1050;

	/** The Constant UPDATE_SEED. */
	private static final int UPDATE_SEED = 3000;

	/** The Constant INSERT_SEED. */
	private static final int INSERT_SEED = 9000;

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_BUNDLE_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_BUNDLE_BAC_EXCEPTION_MSG = "sysmgmt.base.bundlebacimpl.defaultexception";

	/** The bundle dac. */
	private IBundleDAC bundleDAC; // injected by Spring through setter

	/**
	 * Spring Sets the bundle dac.
	 * 
	 * @param bundleDAC the new bundle dac
	 */
	public void setBundleDAC(IBundleDAC bundleDAC)
	{
		this.bundleDAC = bundleDAC;
	}

	/**
	 * Gets the bundle dac.
	 * 
	 * @return the bundle dac
	 */
	public IBundleDAC getBundleDAC()
	{
		return bundleDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IBundleBAC#insertBundle(com.qat.samples.sysmgmt.base.model.Bundle)
	 */
	@Override
	public InternalResponse insertBundle(Bundle bundle)
	{
		bundle.setPrice(BundleBAD.calculatePrice(INSERT_SEED));
		return getBundleDAC().insertBundle(bundle);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IBundleBAC#updateBundle(com.qat.samples.sysmgmt.base.model.Bundle)
	 */
	@Override
	public InternalResponse updateBundle(Bundle bundle)
	{
		bundle.setPrice(BundleBAD.calculatePrice(UPDATE_SEED));
		InternalResponse internalResponse = getBundleDAC().updateBundle(bundle);
		// Check for error because in business case all non-success returns are failures (updating of zero rows or
		// optimistic locking error) according to the business
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			internalResponse.addMessage(DEFAULT_BUNDLE_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error,
					Message.MessageLevel.Object, new Object[] {internalResponse
							.getStatus().toString()});
		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IBundleBAC#deleteBundle(com.qat.samples.sysmgmt.base.model.Bundle)
	 */
	@Override
	public InternalResponse deleteBundle(Bundle bundle)
	{
		InternalResponse internalResponse = getBundleDAC().deleteBundle(bundle);
		// Check for error because in business case all non-success returns are failures (removal of zero rows)
		// according to the business
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			internalResponse.addMessage(DEFAULT_BUNDLE_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error,
					Message.MessageLevel.Object, new Object[] {internalResponse
							.getStatus().toString()});
		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.IBundleBAC#refreshBundles(int)
	 */
	@Override
	public void refreshBundles(Integer refreshNumber)
	{

		if (refreshNumber < 1)
		{
			refreshNumber = MINIMUM_ENTRIES;
		}
		getBundleDAC().deleteAllBundles();
		for (int i = 1; i <= refreshNumber; i++)
		{
			getBundleDAC().insertBundle(
					new Bundle(1, "PC" + i, "BundleDesc" + i, BundleBAD.calculatePrice(REFRESH_SEED * i)));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.IBundleBAC#fetchAllBundles()
	 */
	@Override
	public InternalResultsResponse<Bundle> fetchAllBundles()
	{
		InternalResultsResponse<Bundle> response = new InternalResultsResponse<Bundle>();
		response.getResultsList().addAll(getBundleDAC().fetchAllBundles());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IBundleBAC#fetchBundleById(com.qat.samples.sysmgmt.base.model.Bundle
	 * )
	 */
	@Override
	public InternalResultsResponse<Bundle> fetchBundleById(FetchByIdRequest request)
	{
		InternalResultsResponse<Bundle> response = new InternalResultsResponse<Bundle>();
		response.getResultsList().add(getBundleDAC().fetchBundleById(request));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IBundleBAC#fetchBundlesByRequest(com.qat.samples.sysmgmt.model.request.
	 * BundleInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Bundle> fetchBundlesByRequest(PagedInquiryRequest request)
	{
		return getBundleDAC().fetchBundlesByRequest(request);
	}

}
