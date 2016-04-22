package com.qat.samples.sysmgmt.bar.mybatis.Telefone;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Telefone.ITelefoneBAR;
import com.qat.samples.sysmgmt.util.model.Telefone;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class TelefoneBARImpl extends SqlSessionDaoSupport implements ITelefoneBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### TELEFONE ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_TELEFONE = "TelefoneMap.";

/** The Constant STMT_INSERT_TELEFONE. */
private static final String STMT_INSERT_TELEFONE = NAMESPACE_TELEFONE + "insertTelefone";

/** The Constant STMT_UPDATE_TELEFONE. */
private static final String STMT_UPDATE_TELEFONE = NAMESPACE_TELEFONE + "updateTelefone";

/** The Constant STMT_DELETE_TELEFONE. */
private static final String STMT_DELETE_TELEFONE = NAMESPACE_TELEFONE + "deleteTelefoneById";

	/** The Constant STMT_DELETE_TELEFONE_ALL. */
	private static final String STMT_DELETE_TELEFONE_ALL = NAMESPACE_TELEFONE + "deleteAllTelefones";

	/** The Constant STMT_FETCH_TELEFONE. */
	private static final String STMT_FETCH_TELEFONE = NAMESPACE_TELEFONE + "fetchTelefoneById";

	/** The Constant STMT_FETCH_TELEFONE_ALL. */
	private static final String STMT_FETCH_TELEFONE_ALL = NAMESPACE_TELEFONE + "fetchAllTelefones";

	/** The Constant STMT_FETCH_TELEFONE_COUNT. */
	private static final String STMT_FETCH_TELEFONE_COUNT = NAMESPACE_TELEFONE + "fetchTelefoneRowCount";

	/** The Constant STMT_FETCH_TELEFONE_ALL_REQUEST. */
	private static final String STMT_FETCH_TELEFONE_ALL_REQUEST = NAMESPACE_TELEFONE + "fetchAllTelefonesRequest";

//===================================### TELEFONE ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITelefoneBAR#insertTelefone(com.qat.samples.sysmgmt.base.model.Telefone)
 */
@Override
public InternalResponse insertTelefone(Telefone telefone)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_TELEFONE, telefone, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITelefoneBAR#updateTelefone(com.qat.samples.sysmgmt.base.model.Telefone)
 */
@Override
public InternalResponse updateTelefone(Telefone telefone)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_TELEFONE, telefone, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITelefoneBAR#deleteTelefone(com.qat.samples.sysmgmt.base.model.Telefone)
 */
@Override
public InternalResponse deleteTelefoneById(Telefone telefone)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_TELEFONE, telefone, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITelefoneBAR#deleteAllTelefones()
 */
@Override
public InternalResponse deleteAllTelefones()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_TELEFONE_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ITelefoneBAR#fetchTelefoneById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Telefone fetchTelefoneById(FetchByIdRequest request)
{
return (Telefone)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_TELEFONE, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITelefoneBAR#fetchAllTelefonesCache()
 */
@Override
public InternalResultsResponse<Telefone> fetchAllTelefones(Telefone telefone)
{
	InternalResultsResponse<Telefone> response = new InternalResultsResponse<Telefone>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_TELEFONE_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ITelefoneBAR#fetchTelefonesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Telefone> fetchTelefonesByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<Telefone> response = new InternalResultsResponse<Telefone>();
	fetchTelefonesByRequest(getSqlSession(), request, STMT_FETCH_TELEFONE_COUNT, STMT_FETCH_TELEFONE_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchTelefonesByRequest ####======================================

public static void fetchTelefonesByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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
