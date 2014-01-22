package com.qat.webdaptive.bai;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;

/**
 * The Interface ICountyBAI.
 */
public interface ICountyBAI
{

	/**
	 * Refresh counties.
	 * 
	 * @param request the request
	 * @return the county response
	 */
	public CountyResponse refreshCounties(RefreshRequest request);

	/**
	 * Fetch all counties.
	 * 
	 * @param request the request
	 * @return the county response
	 */
	public CountyResponse fetchAllCounties(FetchAllRequest request);

}
