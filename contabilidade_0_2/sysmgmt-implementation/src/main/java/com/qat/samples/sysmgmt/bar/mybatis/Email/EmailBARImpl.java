package com.qat.samples.sysmgmt.bar.mybatis.Email;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Email.IEmailBAR;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class EmailBARImpl extends SqlSessionDaoSupport implements IEmailBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### EMAIL ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_EMAIL = "EmailMap.";

/** The Constant STMT_INSERT_EMAIL. */
private static final String STMT_INSERT_EMAIL = NAMESPACE_EMAIL + "insertEmail";

/** The Constant STMT_UPDATE_EMAIL. */
private static final String STMT_UPDATE_EMAIL = NAMESPACE_EMAIL + "updateEmail";

/** The Constant STMT_DELETE_EMAIL. */
private static final String STMT_DELETE_EMAIL = NAMESPACE_EMAIL + "deleteEmailById";

	/** The Constant STMT_DELETE_EMAIL_ALL. */
	private static final String STMT_DELETE_EMAIL_ALL = NAMESPACE_EMAIL + "deleteAllEmails";

	/** The Constant STMT_FETCH_EMAIL. */
	private static final String STMT_FETCH_EMAIL = NAMESPACE_EMAIL + "fetchEmailById";

	/** The Constant STMT_FETCH_EMAIL_ALL. */
	private static final String STMT_FETCH_EMAIL_ALL = NAMESPACE_EMAIL + "fetchAllEmails";

	/** The Constant STMT_FETCH_EMAIL_COUNT. */
	private static final String STMT_FETCH_EMAIL_COUNT = NAMESPACE_EMAIL + "fetchEmailRowCount";

	/** The Constant STMT_FETCH_EMAIL_ALL_REQUEST. */
	private static final String STMT_FETCH_EMAIL_ALL_REQUEST = NAMESPACE_EMAIL + "fetchAllEmailsRequest";

//===================================### EMAIL ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEmailBAR#insertEmail(com.qat.samples.sysmgmt.base.model.Email)
 */
@Override
public InternalResponse insertEmail(Email email)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_EMAIL, email, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEmailBAR#updateEmail(com.qat.samples.sysmgmt.base.model.Email)
 */
@Override
public InternalResponse updateEmail(Email email)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_EMAIL, email, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEmailBAR#deleteEmail(com.qat.samples.sysmgmt.base.model.Email)
 */
@Override
public InternalResponse deleteEmailById(Email email)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_EMAIL, email, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEmailBAR#deleteAllEmails()
 */
@Override
public InternalResponse deleteAllEmails()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_EMAIL_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IEmailBAR#fetchEmailById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Email fetchEmailById(FetchByIdRequest request)
{
return (Email)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_EMAIL, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEmailBAR#fetchAllEmailsCache()
 */
@Override
public InternalResultsResponse<Email> fetchAllEmails(Email email)
{
	InternalResultsResponse<Email> response = new InternalResultsResponse<Email>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_EMAIL_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IEmailBAR#fetchEmailsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Email> fetchEmailsByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<Email> response = new InternalResultsResponse<Email>();
	fetchEmailsByRequest(getSqlSession(), request, STMT_FETCH_EMAIL_COUNT, STMT_FETCH_EMAIL_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchEmailsByRequest ####======================================

public static void fetchEmailsByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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
