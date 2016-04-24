package com.qat.samples.sysmgmt.bar.mybatis.Status;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.PagedResultsBARD;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class StatusBARImpl extends SqlSessionDaoSupport implements IStatusBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### STATUS ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_STATUS = "StatusMap.";

/** The Constant STMT_INSERT_STATUS. */
private static final String STMT_INSERT_STATUS = NAMESPACE_STATUS + "insertStatus";

/** The Constant STMT_UPDATE_STATUS. */
private static final String STMT_UPDATE_STATUS = NAMESPACE_STATUS + "updateStatus";

/** The Constant STMT_DELETE_STATUS. */
private static final String STMT_DELETE_STATUS = NAMESPACE_STATUS + "deleteStatusById";

	/** The Constant STMT_DELETE_STATUS_ALL. */
	private static final String STMT_DELETE_STATUS_ALL = NAMESPACE_STATUS + "deleteAllStatus";

	/** The Constant STMT_FETCH_STATUS. */
	private static final String STMT_FETCH_STATUS = NAMESPACE_STATUS + "fetchStatusById";

	/** The Constant STMT_FETCH_STATUS_ALL. */
	private static final String STMT_FETCH_STATUS_ALL = NAMESPACE_STATUS + "fetchAllStatus";

	/** The Constant STMT_FETCH_STATUS_COUNT. */
	private static final String STMT_FETCH_STATUS_COUNT = NAMESPACE_STATUS + "fetchStatusRowCount";

	/** The Constant STMT_FETCH_STATUS_ALL_REQUEST. */
	private static final String STMT_FETCH_STATUS_ALL_REQUEST = NAMESPACE_STATUS + "fetchAllStatusRequest";

//===================================### STATUS ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IStatusBAR#insertStatus(com.qat.samples.sysmgmt.base.model.Status)
 */
@Override
public InternalResponse insertStatus(Status status)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_STATUS, status, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IStatusBAR#updateStatus(com.qat.samples.sysmgmt.base.model.Status)
 */
@Override
public InternalResponse updateStatus(Status status)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_STATUS, status, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IStatusBAR#deleteStatus(com.qat.samples.sysmgmt.base.model.Status)
 */
@Override
public InternalResponse deleteStatusById(Status status)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_STATUS, status, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IStatusBAR#deleteAllStatuss()
 */
@Override
public InternalResponse deleteAllStatus()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_STATUS_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IStatusBAR#fetchStatusById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Status fetchStatusById(FetchByIdRequest request)
{
	InternalResultsResponse<Status> response = new InternalResultsResponse<Status>();
	response.addResult((Status)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_STATUS,
			request.getFetchId()));
	return response.getFirstResult();
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IStatusBAR#fetchAllStatussCache()
 */
@Override
public InternalResultsResponse<Status> fetchAllStatus(Status status)
{
	InternalResultsResponse<Status> response = new InternalResultsResponse<Status>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_STATUS_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IStatusBAR#fetchStatussByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Status> fetchStatusByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<Status> response = new InternalResultsResponse<Status>();
	PagedResultsBARD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_STATUS_COUNT, STMT_FETCH_STATUS_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchStatussByRequest ####======================================

public static void fetchStatussByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

}
