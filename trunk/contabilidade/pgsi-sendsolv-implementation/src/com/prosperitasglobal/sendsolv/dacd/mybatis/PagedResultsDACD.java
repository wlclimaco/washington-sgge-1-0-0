package com.prosperitasglobal.sendsolv.dacd.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.model.request.EmpresaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class PagedResultsDACD extends SqlSessionDaoSupport
{

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/**
	 * Fetch objects by request.
	 *
	 * @param sqlSession the sql session
	 * @param request the request
	 * @param countStatement the count statement
	 * @param fetchPagedStatement the fetch paged statement
	 * @param response the response
	 */
	@SuppressWarnings("unchecked")
	public static void fetchObjectsByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response)
	{
		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)QATMyBatisDacHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(Status.NoRowsFoundError);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(QATMyBatisDacHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison,
		// since it is zero based, we have to offset by 1.
		if (request.getStartPage() == 0)
		{
			request.setStartPage(1);
		}
		else
		{
			request.setStartPage(request.getStartPage() + 1);
		}

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * request
				.getStartPage()))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}
	}

	@SuppressWarnings("unchecked")
	public static void fetchObjectsByRequestEmpresa(SqlSession sqlSession, EmpresaInquiryRequest request,
			String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response)
	{
		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)QATMyBatisDacHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(Status.NoRowsFoundError);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(QATMyBatisDacHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison,
		// since it is zero based, we have to offset by 1.
		if (request.getStartPage() == 0)
		{
			request.setStartPage(1);
		}
		else
		{
			request.setStartPage(request.getStartPage() + 1);
		}

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * request
				.getStartPage()))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}
	}
}
