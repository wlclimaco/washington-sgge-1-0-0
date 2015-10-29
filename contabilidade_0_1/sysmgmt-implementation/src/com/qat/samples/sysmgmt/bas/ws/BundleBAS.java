package com.qat.samples.sysmgmt.bas.ws;

import javax.jws.WebService;

import com.qat.samples.sysmgmt.bai.IBundleBAI;
import com.qat.samples.sysmgmt.bas.IBundleBAS;
import com.qat.samples.sysmgmt.model.request.BundleMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.BundleResponse;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
@WebService(targetNamespace = "http://www.supermercado.kinghost.net/sysmgmt")
public class BundleBAS implements IBundleBAS
{

	/** The bundle bai. */
	private IBundleBAI bundleBAI; // injected by Spring through setter

	/**
	 * Spring Sets the bundle bai.
	 * 
	 * @param bundleBAI the new bundle bai
	 */
	public void setBundleBAI(IBundleBAI bundleBAI)
	{
		this.bundleBAI = bundleBAI;
	}

	/**
	 * Gets the bundle bac.
	 * 
	 * @return the bundle bac
	 */
	public IBundleBAI getBundleBAI()
	{
		return bundleBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IBundleBAS#insertBundle(com.qat.samples.sysmgmt.model.request.BundleMaintenanceRequest
	 * )
	 */
	@Override
	public BundleResponse insertBundle(BundleMaintenanceRequest request)
	{
		return getBundleBAI().insertBundle(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IBundleBAS#updateBundle(com.qat.samples.sysmgmt.model.request.BundleMaintenanceRequest
	 * )
	 */
	@Override
	public BundleResponse updateBundle(BundleMaintenanceRequest request)
	{
		return getBundleBAI().updateBundle(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IBundleBAS#deleteBundle(com.qat.samples.sysmgmt.model.request.BundleMaintenanceRequest
	 * )
	 */
	@Override
	public BundleResponse deleteBundle(BundleMaintenanceRequest request)
	{
		return getBundleBAI().deleteBundle(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IBundleBAS#refreshBundles(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public BundleResponse refreshBundles(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		return getBundleBAI().refreshBundles(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IBundleBAS#fetchAllBundles(com.qat.samples.sysmgmt.model.request.FetchAllRequest)
	 */
	@Override
	public BundleResponse fetchAllBundles(FetchAllRequest request)
	{
		return getBundleBAI().fetchAllBundles(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IBundleBAS#fetchBundleById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public BundleResponse fetchBundleById(FetchByIdRequest request)
	{
		return getBundleBAI().fetchBundleById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IBundleBAS#fetchBundlesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public BundleResponse fetchBundlesByRequest(PagedInquiryRequest request)
	{
		return getBundleBAI().fetchBundlesByRequest(request);
	}

}
