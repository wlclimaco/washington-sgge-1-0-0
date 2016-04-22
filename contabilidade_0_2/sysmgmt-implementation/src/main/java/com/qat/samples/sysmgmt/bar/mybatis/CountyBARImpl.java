package com.qat.samples.sysmgmt.bar.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.ICountyBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.PagedResultsBARD;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class CountyBARImpl extends SqlSessionDaoSupport implements ICountyBAR
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
	 * @see com.qat.samples.sysmgmt.base.bar.ICountyBAR#insertCounty(com.qat.samples.sysmgmt.base.model.County)
	 */
	@Override
	public InternalResponse insertCounty(County county)
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT, county, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.ICountyBAR#updateCounty(com.qat.samples.sysmgmt.base.model.County)
	 */
	@Override
	public InternalResponse updateCounty(County county)
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE, county, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.ICountyBAR#deleteCounty(com.qat.samples.sysmgmt.base.model.County)
	 */
	@Override
	public InternalResponse deleteCountyById(County county)
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE, county, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.ICountyBAR#deleteAllCounties()
	 */
	@Override
	public InternalResponse deleteAllCounties()
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICountyBAR#fetchCountyById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<County> fetchCountyById(FetchByIdRequest request)
	{
		InternalResultsResponse<County> response = new InternalResultsResponse<County>();
		response.addResult((County)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH,
				request.getFetchId()));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.ICountyBAR#fetchAllCountiesCache()
	 */
	@Override
	public InternalResultsResponse<County> fetchAllCounties()
	{
		InternalResultsResponse<County> response = new InternalResultsResponse<County>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bar.ICountyBAR#fetchCountiesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<County> fetchCountiesByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<County> response = new InternalResultsResponse<County>();
		PagedResultsBARD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
				response);
		return response;
	}
}
