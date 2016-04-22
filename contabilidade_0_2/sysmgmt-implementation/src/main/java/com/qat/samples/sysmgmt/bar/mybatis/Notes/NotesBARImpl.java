package com.qat.samples.sysmgmt.bar.mybatis.Notes;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Notes.INotesBAR;
import com.qat.samples.sysmgmt.util.model.Note;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class NotesBARImpl extends SqlSessionDaoSupport implements INotesBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### NOTES ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_NOTES = "NotesMap.";

/** The Constant STMT_INSERT_NOTES. */
private static final String STMT_INSERT_NOTES = NAMESPACE_NOTES + "insertNotes";

/** The Constant STMT_UPDATE_NOTES. */
private static final String STMT_UPDATE_NOTES = NAMESPACE_NOTES + "updateNotes";

/** The Constant STMT_DELETE_NOTES. */
private static final String STMT_DELETE_NOTES = NAMESPACE_NOTES + "deleteNotesById";

	/** The Constant STMT_DELETE_NOTES_ALL. */
	private static final String STMT_DELETE_NOTES_ALL = NAMESPACE_NOTES + "deleteAllNotess";

	/** The Constant STMT_FETCH_NOTES. */
	private static final String STMT_FETCH_NOTES = NAMESPACE_NOTES + "fetchNotesById";

	/** The Constant STMT_FETCH_NOTES_ALL. */
	private static final String STMT_FETCH_NOTES_ALL = NAMESPACE_NOTES + "fetchAllNotess";

	/** The Constant STMT_FETCH_NOTES_COUNT. */
	private static final String STMT_FETCH_NOTES_COUNT = NAMESPACE_NOTES + "fetchNotesRowCount";

	/** The Constant STMT_FETCH_NOTES_ALL_REQUEST. */
	private static final String STMT_FETCH_NOTES_ALL_REQUEST = NAMESPACE_NOTES + "fetchAllNotessRequest";

//===================================### NOTES ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotesBAR#insertNotes(com.qat.samples.sysmgmt.base.model.Notes)
 */
@Override
public InternalResponse insertNotes(Note notes)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NOTES, notes, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotesBAR#updateNotes(com.qat.samples.sysmgmt.base.model.Notes)
 */
@Override
public InternalResponse updateNotes(Note notes)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NOTES, notes, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotesBAR#deleteNotes(com.qat.samples.sysmgmt.base.model.Notes)
 */
@Override
public InternalResponse deleteNotesById(Note notes)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NOTES, notes, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotesBAR#deleteAllNotess()
 */
@Override
public InternalResponse deleteAllNotess()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NOTES_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.INotesBAR#fetchNotesById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Note fetchNotesById(FetchByIdRequest request)
{
return (Note)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NOTES, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotesBAR#fetchAllNotessCache()
 */
@Override
public InternalResultsResponse<Note> fetchAllNotess(Note notes)
{
	InternalResultsResponse<Note> response = new InternalResultsResponse<Note>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NOTES_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.INotesBAR#fetchNotessByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Note> fetchNotessByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<Note> response = new InternalResultsResponse<Note>();
	fetchNotessByRequest(getSqlSession(), request, STMT_FETCH_NOTES_COUNT, STMT_FETCH_NOTES_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchNotessByRequest ####======================================

public static void fetchNotessByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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
