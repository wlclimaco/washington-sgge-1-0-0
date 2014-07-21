package com.qat.samples.sysmgmt.bas.ws;

import javax.jws.WebService;

import com.qat.samples.sysmgmt.bai.ICountyBAI;
import com.qat.samples.sysmgmt.bas.ICountyBAS;
import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
@WebService(targetNamespace = "http://www.supermercado.kinghost.net/sysmgmt")
public class CountyBAS implements ICountyBAS
{

	/** The county bai. */
	private ICountyBAI countyBAI; // injected by Spring through setter

	/**
	 * Sets the county bai.
	 * 
	 * @param countyBAI the new county bai
	 */
	public void setCountyBAI(ICountyBAI countyBAI)
	{
		this.countyBAI = countyBAI;
	}

	/**
	 * Gets the county bai.
	 * 
	 * @return the county bai
	 */
	public ICountyBAI getCountyBAI()
	{
		return countyBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ICountyBAS#insertCounty(com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest
	 * )
	 */
	@Override
	public CountyResponse insertCounty(CountyMaintenanceRequest request)
	{
		return getCountyBAI().insertCounty(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ICountyBAS#updateCounty(com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest
	 * )
	 */
	@Override
	public CountyResponse updateCounty(CountyMaintenanceRequest request)
	{
		return getCountyBAI().updateCounty(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ICountyBAS#deleteCounty(com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest
	 * )
	 */
	@Override
	public CountyResponse deleteCounty(CountyMaintenanceRequest request)
	{
		return getCountyBAI().deleteCounty(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.ICountyBAS#refreshCounties(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public CountyResponse refreshCounties(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		return getCountyBAI().refreshCounties(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ICountyBAS#fetchAllCounties(com.qat.samples.sysmgmt.model.request.FetchAllRequest)
	 */
	@Override
	public CountyResponse fetchAllCounties(FetchAllRequest request)
	{
		return getCountyBAI().fetchAllCounties(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ICountyBAS#fetchCountyById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public CountyResponse fetchCountyById(FetchByIdRequest request)
	{
		return getCountyBAI().fetchCountyById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.ICountyBAS#fetchCountiesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public CountyResponse fetchCountiesByRequest(PagedInquiryRequest request)
	{
		return getCountyBAI().fetchCountiesByRequest(request);
	}
}
