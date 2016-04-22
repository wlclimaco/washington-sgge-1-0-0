package com.qat.samples.sysmgmt.bar.mybatis.Classificacao;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Classificacao.IClassificacaoBAR;
import com.qat.samples.sysmgmt.fiscal.model.Classificacao;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class ClassificacaoBARImpl extends SqlSessionDaoSupport implements IClassificacaoBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### CLASSIFICACAO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CLASSIFICACAO = "ClassificacaoMap.";

/** The Constant STMT_INSERT_CLASSIFICACAO. */
private static final String STMT_INSERT_CLASSIFICACAO = NAMESPACE_CLASSIFICACAO + "insertClassificacao";

/** The Constant STMT_UPDATE_CLASSIFICACAO. */
private static final String STMT_UPDATE_CLASSIFICACAO = NAMESPACE_CLASSIFICACAO + "updateClassificacao";

/** The Constant STMT_DELETE_CLASSIFICACAO. */
private static final String STMT_DELETE_CLASSIFICACAO = NAMESPACE_CLASSIFICACAO + "deleteClassificacaoById";

	/** The Constant STMT_DELETE_CLASSIFICACAO_ALL. */
	private static final String STMT_DELETE_CLASSIFICACAO_ALL = NAMESPACE_CLASSIFICACAO + "deleteAllClassificacaos";

	/** The Constant STMT_FETCH_CLASSIFICACAO. */
	private static final String STMT_FETCH_CLASSIFICACAO = NAMESPACE_CLASSIFICACAO + "fetchClassificacaoById";

	/** The Constant STMT_FETCH_CLASSIFICACAO_ALL. */
	private static final String STMT_FETCH_CLASSIFICACAO_ALL = NAMESPACE_CLASSIFICACAO + "fetchAllClassificacaos";

	/** The Constant STMT_FETCH_CLASSIFICACAO_COUNT. */
	private static final String STMT_FETCH_CLASSIFICACAO_COUNT = NAMESPACE_CLASSIFICACAO + "fetchClassificacaoRowCount";

	/** The Constant STMT_FETCH_CLASSIFICACAO_ALL_REQUEST. */
	private static final String STMT_FETCH_CLASSIFICACAO_ALL_REQUEST = NAMESPACE_CLASSIFICACAO + "fetchAllClassificacaosRequest";

//===================================### CLASSIFICACAO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.IDocumentoBAR.bar.IClassificacaoBAR#insertClassificacao(com.qat.samples.sysmgmt.base.model.Classificacao)
 */
@Override
public InternalResponse insertClassificacao(Classificacao classificacao)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CLASSIFICACAO, classificacao, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClassificacaoBAR#updateClassificacao(com.qat.samples.sysmgmt.base.model.Classificacao)
 */
@Override
public InternalResponse updateClassificacao(Classificacao classificacao)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CLASSIFICACAO, classificacao, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClassificacaoBAR#deleteClassificacao(com.qat.samples.sysmgmt.base.model.Classificacao)
 */
@Override
public InternalResponse deleteClassificacaoById(Classificacao classificacao)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CLASSIFICACAO, classificacao, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClassificacaoBAR#deleteAllClassificacaos()
 */
@Override
public InternalResponse deleteAllClassificacaos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CLASSIFICACAO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IClassificacaoBAR#fetchClassificacaoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Classificacao fetchClassificacaoById(FetchByIdRequest request)
{
return (Classificacao)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CLASSIFICACAO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClassificacaoBAR#fetchAllClassificacaosCache()
 */
@Override
public InternalResultsResponse<Classificacao> fetchAllClassificacaos(Classificacao classificacao)
{
	InternalResultsResponse<Classificacao> response = new InternalResultsResponse<Classificacao>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CLASSIFICACAO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IClassificacaoBAR#fetchClassificacaosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Classificacao> fetchClassificacaosByRequest(ClassificacaoInquiryRequest request)
{
	InternalResultsResponse<Classificacao> response = new InternalResultsResponse<Classificacao>();
	fetchClassificacaosByRequest(getSqlSession(), request, STMT_FETCH_CLASSIFICACAO_COUNT, STMT_FETCH_CLASSIFICACAO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchClassificacaosByRequest ####======================================

public static void fetchClassificacaosByRequest(SqlSession sqlSession, ClassificacaoInquiryRequest request, String countStatement,
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
