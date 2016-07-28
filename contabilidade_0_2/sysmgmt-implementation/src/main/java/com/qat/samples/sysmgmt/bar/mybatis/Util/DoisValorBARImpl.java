/** create by system gera-java version 1.0.0 27/07/2016 17:37 : 11*/
package com.qat.samples.sysmgmt.bar.mybatis.Util;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Util.IDoisValorBAR;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class DoisValorBARImpl extends SqlSessionDaoSupport implements IDoisValorBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### DOISVALOR ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_DOISVALOR = "DoisValorMap.";

/** The Constant STMT_INSERT_DOISVALOR. */
private static final String STMT_INSERT_DOISVALOR = NAMESPACE_DOISVALOR + "insertDoisValor";

/** The Constant STMT_UPDATE_DOISVALOR. */
private static final String STMT_UPDATE_DOISVALOR = NAMESPACE_DOISVALOR + "updateDoisValor";

/** The Constant STMT_DELETE_DOISVALOR. */
private static final String STMT_DELETE_DOISVALOR = NAMESPACE_DOISVALOR + "deleteDoisValorById";

	/** The Constant STMT_DELETE_DOISVALOR_ALL. */
	private static final String STMT_DELETE_DOISVALOR_ALL = NAMESPACE_DOISVALOR + "deleteAllDoisValors";

	/** The Constant STMT_FETCH_DOISVALOR. */
	private static final String STMT_FETCH_DOISVALOR = NAMESPACE_DOISVALOR + "fetchDoisValorById";

	/** The Constant STMT_FETCH_DOISVALOR_ALL. */
	private static final String STMT_FETCH_DOISVALOR_ALL = NAMESPACE_DOISVALOR + "fetchAllDoisValors";

	/** The Constant STMT_FETCH_DOISVALOR_COUNT. */
	private static final String STMT_FETCH_DOISVALOR_COUNT = NAMESPACE_DOISVALOR + "fetchDoisValorRowCount";

	/** The Constant STMT_FETCH_DOISVALOR_ALL_REQUEST. */
	private static final String STMT_FETCH_DOISVALOR_ALL_REQUEST = NAMESPACE_DOISVALOR + "fetchAllDoisValorsRequest";

//===================================### DOISVALOR ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDoisValoresBAR#insertDoisValores(com.qat.samples.sysmgmt.base.model.DoisValores)
 */
@Override
public InternalResponse insertDoisValores(DoisValores doisvalor)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_DOISVALOR, doisvalor, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDoisValoresBAR#updateDoisValores(com.qat.samples.sysmgmt.base.model.DoisValores)
 */
@Override
public InternalResponse updateDoisValores(DoisValores doisvalor)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_DOISVALOR, doisvalor, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDoisValoresBAR#deleteDoisValores(com.qat.samples.sysmgmt.base.model.DoisValores)
 */
@Override
public InternalResponse deleteDoisValoresById(DoisValores doisvalor)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_DOISVALOR, doisvalor, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDoisValoresBAR#deleteAllDoisValoress()
 */
@Override
public InternalResponse deleteAllDoisValoress()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_DOISVALOR_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IDoisValoresBAR#fetchDoisValoresById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public DoisValores fetchDoisValoresById(FetchByIdRequest request)
{
return (DoisValores)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_DOISVALOR, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDoisValoresBAR#fetchAllDoisValoressCache()
 */
@Override
public InternalResultsResponse<DoisValores> fetchAllDoisValoress(DoisValores doisvalor)
{
	InternalResultsResponse<DoisValores> response = new InternalResultsResponse<DoisValores>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_DOISVALOR_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IDoisValoresBAR#fetchDoisValoressByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<DoisValores> fetchDoisValoressByRequest(DoisValoresInquiryRequest request)
{
	InternalResultsResponse<DoisValores> response = new InternalResultsResponse<DoisValores>();
	fetchDoisValoressByRequest(getSqlSession(), request, STMT_FETCH_DOISVALOR_COUNT, STMT_FETCH_DOISVALOR_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchDoisValoressByRequest ####======================================

public static void fetchDoisValoressByRequest(SqlSession sqlSession, DoisValoresInquiryRequest request, String countStatement,
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
