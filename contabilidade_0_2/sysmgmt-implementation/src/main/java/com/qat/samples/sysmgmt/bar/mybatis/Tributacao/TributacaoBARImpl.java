package com.qat.samples.sysmgmt.bar.mybatis.Tributacao;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Tributacao.ITributacaoBAR;
import com.qat.samples.sysmgmt.fiscal.model.Tributacao;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class TributacaoBARImpl extends SqlSessionDaoSupport implements ITributacaoBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### TRIBUTACAO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_TRIBUTACAO = "TributacaoMap.";

/** The Constant STMT_INSERT_TRIBUTACAO. */
private static final String STMT_INSERT_TRIBUTACAO = NAMESPACE_TRIBUTACAO + "insertTributacao";

/** The Constant STMT_UPDATE_TRIBUTACAO. */
private static final String STMT_UPDATE_TRIBUTACAO = NAMESPACE_TRIBUTACAO + "updateTributacao";

/** The Constant STMT_DELETE_TRIBUTACAO. */
private static final String STMT_DELETE_TRIBUTACAO = NAMESPACE_TRIBUTACAO + "deleteTributacaoById";

	/** The Constant STMT_DELETE_TRIBUTACAO_ALL. */
	private static final String STMT_DELETE_TRIBUTACAO_ALL = NAMESPACE_TRIBUTACAO + "deleteAllTributacaos";

	/** The Constant STMT_FETCH_TRIBUTACAO. */
	private static final String STMT_FETCH_TRIBUTACAO = NAMESPACE_TRIBUTACAO + "fetchTributacaoById";

	/** The Constant STMT_FETCH_TRIBUTACAO_ALL. */
	private static final String STMT_FETCH_TRIBUTACAO_ALL = NAMESPACE_TRIBUTACAO + "fetchAllTributacaos";

	/** The Constant STMT_FETCH_TRIBUTACAO_COUNT. */
	private static final String STMT_FETCH_TRIBUTACAO_COUNT = NAMESPACE_TRIBUTACAO + "fetchTributacaoRowCount";

	/** The Constant STMT_FETCH_TRIBUTACAO_ALL_REQUEST. */
	private static final String STMT_FETCH_TRIBUTACAO_ALL_REQUEST = NAMESPACE_TRIBUTACAO + "fetchAllTributacaosRequest";

//===================================### TRIBUTACAO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#insertTributacao(com.qat.samples.sysmgmt.base.model.Tributacao)
 */
@Override
public InternalResponse insertTributacao(Tributacao tributacao)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_TRIBUTACAO, tributacao, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#updateTributacao(com.qat.samples.sysmgmt.base.model.Tributacao)
 */
@Override
public InternalResponse updateTributacao(Tributacao tributacao)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_TRIBUTACAO, tributacao, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#deleteTributacao(com.qat.samples.sysmgmt.base.model.Tributacao)
 */
@Override
public InternalResponse deleteTributacaoById(Tributacao tributacao)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_TRIBUTACAO, tributacao, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#deleteAllTributacaos()
 */
@Override
public InternalResponse deleteAllTributacaos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_TRIBUTACAO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ITributacaoBAR#fetchTributacaoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Tributacao fetchTributacaoById(FetchByIdRequest request)
{
return (Tributacao)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_TRIBUTACAO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#fetchAllTributacaosCache()
 */
@Override
public InternalResultsResponse<Tributacao> fetchAllTributacaos(Tributacao tributacao)
{
	InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_TRIBUTACAO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ITributacaoBAR#fetchTributacaosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Tributacao> fetchTributacaosByRequest(TributacaoInquiryRequest request)
{
	InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();
	fetchTributacaosByRequest(getSqlSession(), request, STMT_FETCH_TRIBUTACAO_COUNT, STMT_FETCH_TRIBUTACAO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchTributacaosByRequest ####======================================

public static void fetchTributacaosByRequest(SqlSession sqlSession, TributacaoInquiryRequest request, String countStatement,
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
