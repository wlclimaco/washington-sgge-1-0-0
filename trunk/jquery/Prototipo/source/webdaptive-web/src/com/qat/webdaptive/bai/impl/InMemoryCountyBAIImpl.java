package com.qat.webdaptive.bai.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.util.QATInterfaceUtil;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;
import com.qat.webdaptive.bai.ICountyBAI;

/**
 * The Class InMemoryCountyBAIImpl.
 */
public class InMemoryCountyBAIImpl implements ICountyBAI
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(InMemoryCountyBAIImpl.class);

	/*
	 * (non-Javadoc)
	 * @see com.qat.webdaptive.bai.ICountyBAI#refreshCounties(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public CountyResponse refreshCounties(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		CountyResponse response = new CountyResponse();
		try
		{
			List<County> counties = new ArrayList<County>();
			int z = 15;
			if ((request.getRefreshInt() != null) && (request.getRefreshInt() > 0))
			{
				z = request.getRefreshInt();
			}

			for (int c = 0; c < z; c++)
			{
				County county = new County();
				county.setId(c);
				county.setDescription("refresh description " + c);
				counties.add(county);
			}
			response.setCounties(counties);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.webdaptive.bai.ICountyBAI#fetchAllCounties(com.qat.samples.sysmgmt.model.request.FetchAllRequest)
	 */
	@Override
	public CountyResponse fetchAllCounties(FetchAllRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CountyResponse response = new CountyResponse();
		try
		{
			List<County> counties = new ArrayList<County>();
			for (int c = 0; c < 15; c++)
			{
				County county = new County();
				county.setId(c);
				county.setDescription("fetch description " + c);
				counties.add(county);
			}
			response.setCounties(counties);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex);
		}
		return response;
	}
}
