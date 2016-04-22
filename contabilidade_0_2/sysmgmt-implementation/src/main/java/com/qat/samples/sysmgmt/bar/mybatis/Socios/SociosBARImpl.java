package com.qat.samples.sysmgmt.bar.mybatis.Socios;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Socios.ISociosBAR;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class SociosBARImpl extends SqlSessionDaoSupport implements ISociosBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### SOCIO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_SOCIO = "SocioMap.";

/** The Constant STMT_INSERT_SOCIO. */
private static final String STMT_INSERT_SOCIO = NAMESPACE_SOCIO + "insertSocio";

/** The Constant STMT_UPDATE_SOCIO. */
private static final String STMT_UPDATE_SOCIO = NAMESPACE_SOCIO + "updateSocio";

/** The Constant STMT_DELETE_SOCIO. */
private static final String STMT_DELETE_SOCIO = NAMESPACE_SOCIO + "deleteSocioById";

	/** The Constant STMT_DELETE_SOCIO_ALL. */
	private static final String STMT_DELETE_SOCIO_ALL = NAMESPACE_SOCIO + "deleteAllSocios";

	/** The Constant STMT_FETCH_SOCIO. */
	private static final String STMT_FETCH_SOCIO = NAMESPACE_SOCIO + "fetchSocioById";

	/** The Constant STMT_FETCH_SOCIO_ALL. */
	private static final String STMT_FETCH_SOCIO_ALL = NAMESPACE_SOCIO + "fetchAllSocios";

	/** The Constant STMT_FETCH_SOCIO_COUNT. */
	private static final String STMT_FETCH_SOCIO_COUNT = NAMESPACE_SOCIO + "fetchSocioRowCount";

	/** The Constant STMT_FETCH_SOCIO_ALL_REQUEST. */
	private static final String STMT_FETCH_SOCIO_ALL_REQUEST = NAMESPACE_SOCIO + "fetchAllSociosRequest";

//===================================### SOCIO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISocioBAR#insertSocio(com.qat.samples.sysmgmt.base.model.Socio)
 */
@Override
public InternalResponse insertSocio(Socio socio)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_SOCIO, socio, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISocioBAR#updateSocio(com.qat.samples.sysmgmt.base.model.Socio)
 */
@Override
public InternalResponse updateSocio(Socio socio)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_SOCIO, socio, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISocioBAR#deleteSocio(com.qat.samples.sysmgmt.base.model.Socio)
 */
@Override
public InternalResponse deleteSocioById(Socio socio)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_SOCIO, socio, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISocioBAR#deleteAllSocios()
 */
@Override
public InternalResponse deleteAllSocios()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_SOCIO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ISocioBAR#fetchSocioById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Socio fetchSocioById(FetchByIdRequest request)
{
return (Socio)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_SOCIO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISocioBAR#fetchAllSociosCache()
 */
@Override
public InternalResultsResponse<Socio> fetchAllSocios(Socio socio)
{
	InternalResultsResponse<Socio> response = new InternalResultsResponse<Socio>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_SOCIO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ISocioBAR#fetchSociosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Socio> fetchSociosByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<Socio> response = new InternalResultsResponse<Socio>();
	fetchSociosByRequest(getSqlSession(), request, STMT_FETCH_SOCIO_COUNT, STMT_FETCH_SOCIO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchSociosByRequest ####======================================

public static void fetchSociosByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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
