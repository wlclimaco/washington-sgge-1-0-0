package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.ICountyBAC;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;
import com.qat.samples.sysmgmt.service.ICountyWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * CountyWS used to provide WS interface. Delegates all calls to the ICountyBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class CountyWSImpl implements ICountyWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.countyjmswsimpl.defaultexception";

	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.countyjmswsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CountyWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CountyWSImpl.class);

	private ICountyBAC countyBAC;

	/**
	 * @return the countyBAC which is expected to provide the implementation.
	 */
	public ICountyBAC getCountyBAC()
	{
		return countyBAC;
	}

	/**
	 * Spring injection uses this method to inject an implementation of {@link ICountyBAC}.
	 *
	 * @param countyBAC the countyBAC to set.
	 */
	public void setCountyBAC(ICountyBAC countyBAC)
	{
		this.countyBAC = countyBAC;
	}

	/**
	 * Delegates call to {@link ICountyBAC}
	 *
	 * @param request a CountyRequest
	 * @return CountyResponse
	 */
	@Override
	public CountyResponse insertCounties(CountyMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().insertCounty(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Delegates call to {@link ICountyBAC}
	 *
	 * @param request a CountyRequest
	 * @return CountyResponse
	 */
	@Override
	public CountyResponse updateCounties(CountyMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().updateCounty(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Delegates call to {@link ICountyBAC}
	 *
	 * @param request a CountyRequest
	 * @return CountyResponse
	 */
	@Override
	public CountyResponse deleteCounties(CountyMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().deleteCounty(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Delegates call to {@link ICountyBAC}
	 *
	 * @param request a CountyRequest
	 * @return CountyResponse
	 */
	@Override
	public CountyResponse fetchCountyById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().fetchCountyById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Delegates call to {@link ICountyBAC}
	 *
	 * @param request a CountyRequest
	 * @return CountyResponse
	 */
	@Override
	public CountyResponse fetchCountiesByRequest(PagedInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().fetchCountiesByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Delegates call to {@link ICountyBAC}
	 *
	 * @param request a CountyRequest
	 * @return CountyResponse
	 */
	@Override
	public CountyResponse refreshCounties(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().refreshCounties(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Delegates call to {@link ICountyBAC}
	 *
	 * @param request a CountyRequest
	 * @return CountyResponse
	 */
	@Override
	public CountyResponse fetchAllCounties(FetchAllRequest request)
	{
		CountyResponse response = new CountyResponse();

		try
		{
			InternalResultsResponse<County> internalResponse = getCountyBAC().fetchAllCounties();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}
}
