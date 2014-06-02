package com.qat.samples.sysmgmt.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.CachedResultsResponse;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dac.ICountyDAC;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Class CountyDACImpl. (Data Access Component - DAC)
 */
public class CountyDACImpl extends SqlSessionDaoSupport implements ICountyDAC
{

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "CountyMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertCounty";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updateCounty";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE = NAMESPACE + "deleteCountyById";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllCounties";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchCountyById";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllCounties";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchCountyRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllCountiesRequest";

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.ICountyDAC#insertCounty(com.qat.samples.sysmgmt.base.model.County)
	 */
	@Override
	public InternalResponse insertCounty(County county)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doInsert(getSqlSession(), STMT_INSERT, county, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.ICountyDAC#updateCounty(com.qat.samples.sysmgmt.base.model.County)
	 */
	@Override
	public InternalResponse updateCounty(County county)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), STMT_UPDATE, county, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.ICountyDAC#deleteCounty(com.qat.samples.sysmgmt.base.model.County)
	 */
	@Override
	public InternalResponse deleteCountyById(County county)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE, county, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.ICountyDAC#deleteAllCounties()
	 */
	@Override
	public InternalResponse deleteAllCounties()
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dac.ICountyDAC#fetchCountyById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public CachedResultsResponse<County> fetchCountyById(FetchByIdRequest request)
	{
		CachedResultsResponse<County> response = new CachedResultsResponse<County>();
		response.addResult((County)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId()));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.ICountyDAC#fetchAllCountiesCache()
	 */
	@Override
	public CachedResultsResponse<County> fetchAllCounties()
	{
		CachedResultsResponse<County> response = new CachedResultsResponse<County>();
		response.getResultsList().addAll(QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dac.ICountyDAC#fetchCountiesByRequest(com.qat.samples.sysmgmt.model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<County> fetchCountiesByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<County> response = new InternalResultsResponse<County>();
		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST, response);
		return response;
	}
}
