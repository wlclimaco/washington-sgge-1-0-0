/** create by system gera-java version 1.0.0 07/05/2016 18:43 : 6*/
package com.qat.samples.sysmgmt.bar.mybatis.Produto;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Produto.IPrecoBAR;
import com.qat.samples.sysmgmt.produto.model.Preco;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class PrecoBARImpl extends SqlSessionDaoSupport implements IPrecoBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### PRECO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_PRECO = "PrecoMap.";

/** The Constant STMT_INSERT_PRECO. */
private static final String STMT_INSERT_PRECO = NAMESPACE_PRECO + "insertPreco";

/** The Constant STMT_UPDATE_PRECO. */
private static final String STMT_UPDATE_PRECO = NAMESPACE_PRECO + "updatePreco";

/** The Constant STMT_DELETE_PRECO. */
private static final String STMT_DELETE_PRECO = NAMESPACE_PRECO + "deletePrecoById";

	/** The Constant STMT_DELETE_PRECO_ALL. */
	private static final String STMT_DELETE_PRECO_ALL = NAMESPACE_PRECO + "deleteAllPrecos";

	/** The Constant STMT_FETCH_PRECO. */
	private static final String STMT_FETCH_PRECO = NAMESPACE_PRECO + "fetchPrecoById";

	/** The Constant STMT_FETCH_PRECO_ALL. */
	private static final String STMT_FETCH_PRECO_ALL = NAMESPACE_PRECO + "fetchAllPrecos";

	/** The Constant STMT_FETCH_PRECO_COUNT. */
	private static final String STMT_FETCH_PRECO_COUNT = NAMESPACE_PRECO + "fetchPrecoRowCount";

	/** The Constant STMT_FETCH_PRECO_ALL_REQUEST. */
	private static final String STMT_FETCH_PRECO_ALL_REQUEST = NAMESPACE_PRECO + "fetchAllPrecosRequest";

//===================================### PRECO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPrecoBAR#insertPreco(com.qat.samples.sysmgmt.base.model.Preco)
 */
@Override
public InternalResponse insertPreco(Preco preco)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PRECO, preco, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPrecoBAR#updatePreco(com.qat.samples.sysmgmt.base.model.Preco)
 */
@Override
public InternalResponse updatePreco(Preco preco)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PRECO, preco, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPrecoBAR#deletePreco(com.qat.samples.sysmgmt.base.model.Preco)
 */
@Override
public InternalResponse deletePrecoById(Preco preco)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PRECO, preco, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPrecoBAR#deleteAllPrecos()
 */
@Override
public InternalResponse deleteAllPrecos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PRECO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IPrecoBAR#fetchPrecoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Preco fetchPrecoById(FetchByIdRequest request)
{
return (Preco)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PRECO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPrecoBAR#fetchAllPrecosCache()
 */
@Override
public InternalResultsResponse<Preco> fetchAllPrecos(Preco preco)
{
	InternalResultsResponse<Preco> response = new InternalResultsResponse<Preco>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PRECO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IPrecoBAR#fetchPrecosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Preco> fetchPrecosByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<Preco> response = new InternalResultsResponse<Preco>();
	fetchPrecosByRequest(getSqlSession(), request, STMT_FETCH_PRECO_COUNT, STMT_FETCH_PRECO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchPrecosByRequest ####======================================

public static void fetchPrecosByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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
