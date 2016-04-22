package com.qat.samples.sysmgmt.bar.mybatis.Historico;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class HistoricoBARImpl extends SqlSessionDaoSupport implements IHistoricoBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### HISTORICO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_HISTORICO = "HistoricoMap.";

/** The Constant STMT_INSERT_HISTORICO. */
private static final String STMT_INSERT_HISTORICO = NAMESPACE_HISTORICO + "insertHistorico";

/** The Constant STMT_UPDATE_HISTORICO. */
private static final String STMT_UPDATE_HISTORICO = NAMESPACE_HISTORICO + "updateHistorico";

/** The Constant STMT_DELETE_HISTORICO. */
private static final String STMT_DELETE_HISTORICO = NAMESPACE_HISTORICO + "deleteHistoricoById";

	/** The Constant STMT_DELETE_HISTORICO_ALL. */
	private static final String STMT_DELETE_HISTORICO_ALL = NAMESPACE_HISTORICO + "deleteAllHistoricos";

	/** The Constant STMT_FETCH_HISTORICO. */
	private static final String STMT_FETCH_HISTORICO = NAMESPACE_HISTORICO + "fetchHistoricoById";

	/** The Constant STMT_FETCH_HISTORICO_ALL. */
	private static final String STMT_FETCH_HISTORICO_ALL = NAMESPACE_HISTORICO + "fetchAllHistoricos";

	/** The Constant STMT_FETCH_HISTORICO_COUNT. */
	private static final String STMT_FETCH_HISTORICO_COUNT = NAMESPACE_HISTORICO + "fetchHistoricoRowCount";

	/** The Constant STMT_FETCH_HISTORICO_ALL_REQUEST. */
	private static final String STMT_FETCH_HISTORICO_ALL_REQUEST = NAMESPACE_HISTORICO + "fetchAllHistoricosRequest";

///===================================### HISTORICOITENS ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_HISTORICOITENS = "HistoricoitensMap.";

/** The Constant STMT_INSERT_HISTORICOITENS. */
private static final String STMT_INSERT_HISTORICOITENS = NAMESPACE_HISTORICOITENS + "insertHistoricoitens";

/** The Constant STMT_UPDATE_HISTORICOITENS. */
private static final String STMT_UPDATE_HISTORICOITENS = NAMESPACE_HISTORICOITENS + "updateHistoricoitens";

/** The Constant STMT_DELETE_HISTORICOITENS. */
private static final String STMT_DELETE_HISTORICOITENS = NAMESPACE_HISTORICOITENS + "deleteHistoricoitensById";

	/** The Constant STMT_DELETE_HISTORICOITENS_ALL. */
	private static final String STMT_DELETE_HISTORICOITENS_ALL = NAMESPACE_HISTORICOITENS + "deleteAllHistoricoitenss";

	/** The Constant STMT_FETCH_HISTORICOITENS. */
	private static final String STMT_FETCH_HISTORICOITENS = NAMESPACE_HISTORICOITENS + "fetchHistoricoitensById";

	/** The Constant STMT_FETCH_HISTORICOITENS_ALL. */
	private static final String STMT_FETCH_HISTORICOITENS_ALL = NAMESPACE_HISTORICOITENS + "fetchAllHistoricoitenss";

	/** The Constant STMT_FETCH_HISTORICOITENS_COUNT. */
	private static final String STMT_FETCH_HISTORICOITENS_COUNT = NAMESPACE_HISTORICOITENS + "fetchHistoricoitensRowCount";

	/** The Constant STMT_FETCH_HISTORICOITENS_ALL_REQUEST. */
	private static final String STMT_FETCH_HISTORICOITENS_ALL_REQUEST = NAMESPACE_HISTORICOITENS + "fetchAllHistoricoitenssRequest";

//===================================### HISTORICO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHistoricoBAR#insertHistorico(com.qat.samples.sysmgmt.base.model.Historico)
 */
@Override
public InternalResponse insertHistorico(Historico historico)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_HISTORICO, historico, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHistoricoBAR#updateHistorico(com.qat.samples.sysmgmt.base.model.Historico)
 */
@Override
public InternalResponse updateHistorico(Historico historico)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_HISTORICO, historico, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHistoricoBAR#deleteHistorico(com.qat.samples.sysmgmt.base.model.Historico)
 */
@Override
public InternalResponse deleteHistoricoById(Historico historico)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_HISTORICO, historico, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHistoricoBAR#deleteAllHistoricos()
 */
@Override
public InternalResponse deleteAllHistoricos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_HISTORICO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IHistoricoBAR#fetchHistoricoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Historico fetchHistoricoById(FetchByIdRequest request)
{
return (Historico)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_HISTORICO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHistoricoBAR#fetchAllHistoricosCache()
 */
@Override
public InternalResultsResponse<Historico> fetchAllHistoricos(Historico historico)
{
	InternalResultsResponse<Historico> response = new InternalResultsResponse<Historico>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_HISTORICO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IHistoricoBAR#fetchHistoricosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Historico> fetchHistoricosByRequest(HistoricoInquiryRequest request)
{
	InternalResultsResponse<Historico> response = new InternalResultsResponse<Historico>();
	fetchHistoricosByRequest(getSqlSession(), request, STMT_FETCH_HISTORICO_COUNT, STMT_FETCH_HISTORICO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchHistoricosByRequest ####======================================

public static void fetchHistoricosByRequest(SqlSession sqlSession, HistoricoInquiryRequest request, String countStatement,
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


//===================================### HISTORICOITENS ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHistoricoItensBAR#insertHistoricoItens(com.qat.samples.sysmgmt.base.model.HistoricoItens)
 */
@Override
public InternalResponse insertHistoricoItens(HistoricoItens historicoitens)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_HISTORICOITENS, historicoitens, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHistoricoItensBAR#updateHistoricoItens(com.qat.samples.sysmgmt.base.model.HistoricoItens)
 */
@Override
public InternalResponse updateHistoricoItens(HistoricoItens historicoitens)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_HISTORICOITENS, historicoitens, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHistoricoItensBAR#deleteHistoricoItens(com.qat.samples.sysmgmt.base.model.HistoricoItens)
 */
@Override
public InternalResponse deleteHistoricoItensById(HistoricoItens historicoitens)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_HISTORICOITENS, historicoitens, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHistoricoItensBAR#deleteAllHistoricoItenss()
 */
@Override
public InternalResponse deleteAllHistoricoItenss()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_HISTORICOITENS_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IHistoricoItensBAR#fetchHistoricoItensById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public HistoricoItens fetchHistoricoItensById(FetchByIdRequest request)
{
return (HistoricoItens)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_HISTORICOITENS, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IHistoricoItensBAR#fetchAllHistoricoItenssCache()
 */
@Override
public InternalResultsResponse<HistoricoItens> fetchAllHistoricoItenss(HistoricoItens historicoitens)
{
	InternalResultsResponse<HistoricoItens> response = new InternalResultsResponse<HistoricoItens>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_HISTORICOITENS_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IHistoricoItensBAR#fetchHistoricoItenssByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<HistoricoItens> fetchHistoricoItenssByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<HistoricoItens> response = new InternalResultsResponse<HistoricoItens>();
	fetchHistoricoItenssByRequest(getSqlSession(), request, STMT_FETCH_HISTORICOITENS_COUNT, STMT_FETCH_HISTORICOITENS_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchHistoricoItenssByRequest ####======================================

public static void fetchHistoricoItenssByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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
