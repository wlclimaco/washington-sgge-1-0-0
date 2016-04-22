package com.qat.samples.sysmgmt.bar.mybatis.Financeiro;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.bar.Financeiro.IFinanceiroBAR;
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class FinanceiroBARImpl extends SqlSessionDaoSupport implements IFinanceiroBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### CONTASPAGAR ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CONTASPAGAR = "ContasPagarMap.";

/** The Constant STMT_INSERT_CONTASPAGAR. */
private static final String STMT_INSERT_CONTASPAGAR = NAMESPACE_CONTASPAGAR + "insertContasPagar";

/** The Constant STMT_UPDATE_CONTASPAGAR. */
private static final String STMT_UPDATE_CONTASPAGAR = NAMESPACE_CONTASPAGAR + "updateContasPagar";

/** The Constant STMT_DELETE_CONTASPAGAR. */
private static final String STMT_DELETE_CONTASPAGAR = NAMESPACE_CONTASPAGAR + "deleteContasPagarById";

	/** The Constant STMT_DELETE_CONTASPAGAR_ALL. */
	private static final String STMT_DELETE_CONTASPAGAR_ALL = NAMESPACE_CONTASPAGAR + "deleteAllContasPagars";

	/** The Constant STMT_FETCH_CONTASPAGAR. */
	private static final String STMT_FETCH_CONTASPAGAR = NAMESPACE_CONTASPAGAR + "fetchContasPagarById";

	/** The Constant STMT_FETCH_CONTASPAGAR_ALL. */
	private static final String STMT_FETCH_CONTASPAGAR_ALL = NAMESPACE_CONTASPAGAR + "fetchAllContasPagars";

	/** The Constant STMT_FETCH_CONTASPAGAR_COUNT. */
	private static final String STMT_FETCH_CONTASPAGAR_COUNT = NAMESPACE_CONTASPAGAR + "fetchContasPagarRowCount";

	/** The Constant STMT_FETCH_CONTASPAGAR_ALL_REQUEST. */
	private static final String STMT_FETCH_CONTASPAGAR_ALL_REQUEST = NAMESPACE_CONTASPAGAR + "fetchAllContasPagarsRequest";

///===================================### CONTASRECEBER ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CONTASRECEBER = "ContasReceberMap.";

/** The Constant STMT_INSERT_CONTASRECEBER. */
private static final String STMT_INSERT_CONTASRECEBER = NAMESPACE_CONTASRECEBER + "insertContasReceber";

/** The Constant STMT_UPDATE_CONTASRECEBER. */
private static final String STMT_UPDATE_CONTASRECEBER = NAMESPACE_CONTASRECEBER + "updateContasReceber";

/** The Constant STMT_DELETE_CONTASRECEBER. */
private static final String STMT_DELETE_CONTASRECEBER = NAMESPACE_CONTASRECEBER + "deleteContasReceberById";

	/** The Constant STMT_DELETE_CONTASRECEBER_ALL. */
	private static final String STMT_DELETE_CONTASRECEBER_ALL = NAMESPACE_CONTASRECEBER + "deleteAllContasRecebers";

	/** The Constant STMT_FETCH_CONTASRECEBER. */
	private static final String STMT_FETCH_CONTASRECEBER = NAMESPACE_CONTASRECEBER + "fetchContasReceberById";

	/** The Constant STMT_FETCH_CONTASRECEBER_ALL. */
	private static final String STMT_FETCH_CONTASRECEBER_ALL = NAMESPACE_CONTASRECEBER + "fetchAllContasRecebers";

	/** The Constant STMT_FETCH_CONTASRECEBER_COUNT. */
	private static final String STMT_FETCH_CONTASRECEBER_COUNT = NAMESPACE_CONTASRECEBER + "fetchContasReceberRowCount";

	/** The Constant STMT_FETCH_CONTASRECEBER_ALL_REQUEST. */
	private static final String STMT_FETCH_CONTASRECEBER_ALL_REQUEST = NAMESPACE_CONTASRECEBER + "fetchAllContasRecebersRequest";

///===================================### CONDPAG ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CONDPAG = "CondPagMap.";

/** The Constant STMT_INSERT_CONDPAG. */
private static final String STMT_INSERT_CONDPAG = NAMESPACE_CONDPAG + "insertCondPag";

/** The Constant STMT_UPDATE_CONDPAG. */
private static final String STMT_UPDATE_CONDPAG = NAMESPACE_CONDPAG + "updateCondPag";

/** The Constant STMT_DELETE_CONDPAG. */
private static final String STMT_DELETE_CONDPAG = NAMESPACE_CONDPAG + "deleteCondPagById";

	/** The Constant STMT_DELETE_CONDPAG_ALL. */
	private static final String STMT_DELETE_CONDPAG_ALL = NAMESPACE_CONDPAG + "deleteAllCondPags";

	/** The Constant STMT_FETCH_CONDPAG. */
	private static final String STMT_FETCH_CONDPAG = NAMESPACE_CONDPAG + "fetchCondPagById";

	/** The Constant STMT_FETCH_CONDPAG_ALL. */
	private static final String STMT_FETCH_CONDPAG_ALL = NAMESPACE_CONDPAG + "fetchAllCondPags";

	/** The Constant STMT_FETCH_CONDPAG_COUNT. */
	private static final String STMT_FETCH_CONDPAG_COUNT = NAMESPACE_CONDPAG + "fetchCondPagRowCount";

	/** The Constant STMT_FETCH_CONDPAG_ALL_REQUEST. */
	private static final String STMT_FETCH_CONDPAG_ALL_REQUEST = NAMESPACE_CONDPAG + "fetchAllCondPagsRequest";

///===================================### FORMAPG ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_FORMAPG = "FormaPgMap.";

/** The Constant STMT_INSERT_FORMAPG. */
private static final String STMT_INSERT_FORMAPG = NAMESPACE_FORMAPG + "insertFormaPg";

/** The Constant STMT_UPDATE_FORMAPG. */
private static final String STMT_UPDATE_FORMAPG = NAMESPACE_FORMAPG + "updateFormaPg";

/** The Constant STMT_DELETE_FORMAPG. */
private static final String STMT_DELETE_FORMAPG = NAMESPACE_FORMAPG + "deleteFormaPgById";

	/** The Constant STMT_DELETE_FORMAPG_ALL. */
	private static final String STMT_DELETE_FORMAPG_ALL = NAMESPACE_FORMAPG + "deleteAllFormaPgs";

	/** The Constant STMT_FETCH_FORMAPG. */
	private static final String STMT_FETCH_FORMAPG = NAMESPACE_FORMAPG + "fetchFormaPgById";

	/** The Constant STMT_FETCH_FORMAPG_ALL. */
	private static final String STMT_FETCH_FORMAPG_ALL = NAMESPACE_FORMAPG + "fetchAllFormaPgs";

	/** The Constant STMT_FETCH_FORMAPG_COUNT. */
	private static final String STMT_FETCH_FORMAPG_COUNT = NAMESPACE_FORMAPG + "fetchFormaPgRowCount";

	/** The Constant STMT_FETCH_FORMAPG_ALL_REQUEST. */
	private static final String STMT_FETCH_FORMAPG_ALL_REQUEST = NAMESPACE_FORMAPG + "fetchAllFormaPgsRequest";

///===================================### BANCO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_BANCO = "BancoMap.";

/** The Constant STMT_INSERT_BANCO. */
private static final String STMT_INSERT_BANCO = NAMESPACE_BANCO + "insertBanco";

/** The Constant STMT_UPDATE_BANCO. */
private static final String STMT_UPDATE_BANCO = NAMESPACE_BANCO + "updateBanco";

/** The Constant STMT_DELETE_BANCO. */
private static final String STMT_DELETE_BANCO = NAMESPACE_BANCO + "deleteBancoById";

	/** The Constant STMT_DELETE_BANCO_ALL. */
	private static final String STMT_DELETE_BANCO_ALL = NAMESPACE_BANCO + "deleteAllBancos";

	/** The Constant STMT_FETCH_BANCO. */
	private static final String STMT_FETCH_BANCO = NAMESPACE_BANCO + "fetchBancoById";

	/** The Constant STMT_FETCH_BANCO_ALL. */
	private static final String STMT_FETCH_BANCO_ALL = NAMESPACE_BANCO + "fetchAllBancos";

	/** The Constant STMT_FETCH_BANCO_COUNT. */
	private static final String STMT_FETCH_BANCO_COUNT = NAMESPACE_BANCO + "fetchBancoRowCount";

	/** The Constant STMT_FETCH_BANCO_ALL_REQUEST. */
	private static final String STMT_FETCH_BANCO_ALL_REQUEST = NAMESPACE_BANCO + "fetchAllBancosRequest";

///===================================### CONTACORRENTE ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CONTACORRENTE = "ContaCorrenteMap.";

/** The Constant STMT_INSERT_CONTACORRENTE. */
private static final String STMT_INSERT_CONTACORRENTE = NAMESPACE_CONTACORRENTE + "insertContaCorrente";

/** The Constant STMT_UPDATE_CONTACORRENTE. */
private static final String STMT_UPDATE_CONTACORRENTE = NAMESPACE_CONTACORRENTE + "updateContaCorrente";

/** The Constant STMT_DELETE_CONTACORRENTE. */
private static final String STMT_DELETE_CONTACORRENTE = NAMESPACE_CONTACORRENTE + "deleteContaCorrenteById";

	/** The Constant STMT_DELETE_CONTACORRENTE_ALL. */
	private static final String STMT_DELETE_CONTACORRENTE_ALL = NAMESPACE_CONTACORRENTE + "deleteAllContaCorrentes";

	/** The Constant STMT_FETCH_CONTACORRENTE. */
	private static final String STMT_FETCH_CONTACORRENTE = NAMESPACE_CONTACORRENTE + "fetchContaCorrenteById";

	/** The Constant STMT_FETCH_CONTACORRENTE_ALL. */
	private static final String STMT_FETCH_CONTACORRENTE_ALL = NAMESPACE_CONTACORRENTE + "fetchAllContaCorrentes";

	/** The Constant STMT_FETCH_CONTACORRENTE_COUNT. */
	private static final String STMT_FETCH_CONTACORRENTE_COUNT = NAMESPACE_CONTACORRENTE + "fetchContaCorrenteRowCount";

	/** The Constant STMT_FETCH_CONTACORRENTE_ALL_REQUEST. */
	private static final String STMT_FETCH_CONTACORRENTE_ALL_REQUEST = NAMESPACE_CONTACORRENTE + "fetchAllContaCorrentesRequest";

///===================================### CAIXA ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CAIXA = "CaixaMap.";

/** The Constant STMT_INSERT_CAIXA. */
private static final String STMT_INSERT_CAIXA = NAMESPACE_CAIXA + "insertCaixa";

/** The Constant STMT_UPDATE_CAIXA. */
private static final String STMT_UPDATE_CAIXA = NAMESPACE_CAIXA + "updateCaixa";

/** The Constant STMT_DELETE_CAIXA. */
private static final String STMT_DELETE_CAIXA = NAMESPACE_CAIXA + "deleteCaixaById";

	/** The Constant STMT_DELETE_CAIXA_ALL. */
	private static final String STMT_DELETE_CAIXA_ALL = NAMESPACE_CAIXA + "deleteAllCaixas";

	/** The Constant STMT_FETCH_CAIXA. */
	private static final String STMT_FETCH_CAIXA = NAMESPACE_CAIXA + "fetchCaixaById";

	/** The Constant STMT_FETCH_CAIXA_ALL. */
	private static final String STMT_FETCH_CAIXA_ALL = NAMESPACE_CAIXA + "fetchAllCaixas";

	/** The Constant STMT_FETCH_CAIXA_COUNT. */
	private static final String STMT_FETCH_CAIXA_COUNT = NAMESPACE_CAIXA + "fetchCaixaRowCount";

	/** The Constant STMT_FETCH_CAIXA_ALL_REQUEST. */
	private static final String STMT_FETCH_CAIXA_ALL_REQUEST = NAMESPACE_CAIXA + "fetchAllCaixasRequest";

//===================================### CONTASPAGAR ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContasPagarBAR#insertContasPagar(com.qat.samples.sysmgmt.base.model.ContasPagar)
 */
@Override
public InternalResponse insertContasPagar(ContasPagar county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONTASPAGAR, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContasPagarBAR#updateContasPagar(com.qat.samples.sysmgmt.base.model.ContasPagar)
 */
@Override
public InternalResponse updateContasPagar(ContasPagar county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONTASPAGAR, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContasPagarBAR#deleteContasPagar(com.qat.samples.sysmgmt.base.model.ContasPagar)
 */
@Override
public InternalResponse deleteContasPagarById(ContasPagar county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONTASPAGAR, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContasPagarBAR#deleteAllContasPagars()
 */
@Override
public InternalResponse deleteAllContasPagars()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONTASPAGAR_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IContasPagarBAR#fetchContasPagarById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<ContasPagar> fetchContasPagarById(FetchByIdRequest request)
{
	InternalResultsResponse<ContasPagar> response = new InternalResultsResponse<ContasPagar>();
	response.addResult((ContasPagar)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONTASPAGAR,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContasPagarBAR#fetchAllContasPagarsCache()
 */
@Override
public InternalResultsResponse<ContasPagar> fetchAllContasPagars(ContasPagar contaspagar)
{
	InternalResultsResponse<ContasPagar> response = new InternalResultsResponse<ContasPagar>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONTASPAGAR_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IContasPagarBAR#fetchContasPagarsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ContasPagar> fetchContasPagarsByRequest(ContasPagarInquiryRequest request)
{
	InternalResultsResponse<ContasPagar> response = new InternalResultsResponse<ContasPagar>();
	fetchContasPagarsByRequest(getSqlSession(), request, STMT_FETCH_CONTASPAGAR_COUNT, STMT_FETCH_CONTASPAGAR_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchContasPagarsByRequest ####======================================

public static void fetchContasPagarsByRequest(SqlSession sqlSession, ContasPagarInquiryRequest request, String countStatement,
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


//===================================### CONTASRECEBER ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContasReceberBAR#insertContasReceber(com.qat.samples.sysmgmt.base.model.ContasReceber)
 */
@Override
public InternalResponse insertContasReceber(ContasReceber county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONTASRECEBER, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContasReceberBAR#updateContasReceber(com.qat.samples.sysmgmt.base.model.ContasReceber)
 */
@Override
public InternalResponse updateContasReceber(ContasReceber county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONTASRECEBER, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContasReceberBAR#deleteContasReceber(com.qat.samples.sysmgmt.base.model.ContasReceber)
 */
@Override
public InternalResponse deleteContasReceberById(ContasReceber county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONTASRECEBER, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContasReceberBAR#deleteAllContasRecebers()
 */
@Override
public InternalResponse deleteAllContasRecebers()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONTASRECEBER_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IContasReceberBAR#fetchContasReceberById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<ContasReceber> fetchContasReceberById(FetchByIdRequest request)
{
	InternalResultsResponse<ContasReceber> response = new InternalResultsResponse<ContasReceber>();
	response.addResult((ContasReceber)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONTASRECEBER,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContasReceberBAR#fetchAllContasRecebersCache()
 */
@Override
public InternalResultsResponse<ContasReceber> fetchAllContasRecebers(ContasReceber contasreceber)
{
	InternalResultsResponse<ContasReceber> response = new InternalResultsResponse<ContasReceber>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONTASRECEBER_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IContasReceberBAR#fetchContasRecebersByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ContasReceber> fetchContasRecebersByRequest(ContasReceberInquiryRequest request)
{
	InternalResultsResponse<ContasReceber> response = new InternalResultsResponse<ContasReceber>();
	fetchContasRecebersByRequest(getSqlSession(), request, STMT_FETCH_CONTASRECEBER_COUNT, STMT_FETCH_CONTASRECEBER_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchContasRecebersByRequest ####======================================

public static void fetchContasRecebersByRequest(SqlSession sqlSession, ContasReceberInquiryRequest request, String countStatement,
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


//===================================### CONDPAG ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICondPagBAR#insertCondPag(com.qat.samples.sysmgmt.base.model.CondPag)
 */
@Override
public InternalResponse insertCondPag(CondPag county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONDPAG, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICondPagBAR#updateCondPag(com.qat.samples.sysmgmt.base.model.CondPag)
 */
@Override
public InternalResponse updateCondPag(CondPag county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONDPAG, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICondPagBAR#deleteCondPag(com.qat.samples.sysmgmt.base.model.CondPag)
 */
@Override
public InternalResponse deleteCondPagById(CondPag county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONDPAG, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICondPagBAR#deleteAllCondPags()
 */
@Override
public InternalResponse deleteAllCondPags()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONDPAG_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ICondPagBAR#fetchCondPagById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<CondPag> fetchCondPagById(FetchByIdRequest request)
{
	InternalResultsResponse<CondPag> response = new InternalResultsResponse<CondPag>();
	response.addResult((CondPag)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONDPAG,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICondPagBAR#fetchAllCondPagsCache()
 */
@Override
public InternalResultsResponse<CondPag> fetchAllCondPags(CondPag condpag)
{
	InternalResultsResponse<CondPag> response = new InternalResultsResponse<CondPag>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONDPAG_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ICondPagBAR#fetchCondPagsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<CondPag> fetchCondPagsByRequest(CondPagInquiryRequest request)
{
	InternalResultsResponse<CondPag> response = new InternalResultsResponse<CondPag>();
	fetchCondPagsByRequest(getSqlSession(), request, STMT_FETCH_CONDPAG_COUNT, STMT_FETCH_CONDPAG_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchCondPagsByRequest ####======================================

public static void fetchCondPagsByRequest(SqlSession sqlSession, CondPagInquiryRequest request, String countStatement,
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


//===================================### FORMAPG ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFormaPgBAR#insertFormaPg(com.qat.samples.sysmgmt.base.model.FormaPg)
 */
@Override
public InternalResponse insertFormaPg(FormaPg county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_FORMAPG, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFormaPgBAR#updateFormaPg(com.qat.samples.sysmgmt.base.model.FormaPg)
 */
@Override
public InternalResponse updateFormaPg(FormaPg county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_FORMAPG, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFormaPgBAR#deleteFormaPg(com.qat.samples.sysmgmt.base.model.FormaPg)
 */
@Override
public InternalResponse deleteFormaPgById(FormaPg county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_FORMAPG, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFormaPgBAR#deleteAllFormaPgs()
 */
@Override
public InternalResponse deleteAllFormaPgs()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_FORMAPG_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IFormaPgBAR#fetchFormaPgById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<FormaPg> fetchFormaPgById(FetchByIdRequest request)
{
	InternalResultsResponse<FormaPg> response = new InternalResultsResponse<FormaPg>();
	response.addResult((FormaPg)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_FORMAPG,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFormaPgBAR#fetchAllFormaPgsCache()
 */
@Override
public InternalResultsResponse<FormaPg> fetchAllFormaPgs(FormaPg formapg)
{
	InternalResultsResponse<FormaPg> response = new InternalResultsResponse<FormaPg>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_FORMAPG_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IFormaPgBAR#fetchFormaPgsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<FormaPg> fetchFormaPgsByRequest(FormaPgInquiryRequest request)
{
	InternalResultsResponse<FormaPg> response = new InternalResultsResponse<FormaPg>();
	fetchFormaPgsByRequest(getSqlSession(), request, STMT_FETCH_FORMAPG_COUNT, STMT_FETCH_FORMAPG_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchFormaPgsByRequest ####======================================

public static void fetchFormaPgsByRequest(SqlSession sqlSession, FormaPgInquiryRequest request, String countStatement,
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


//===================================### BANCO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IBancoBAR#insertBanco(com.qat.samples.sysmgmt.base.model.Banco)
 */
@Override
public InternalResponse insertBanco(Banco county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_BANCO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IBancoBAR#updateBanco(com.qat.samples.sysmgmt.base.model.Banco)
 */
@Override
public InternalResponse updateBanco(Banco county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_BANCO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IBancoBAR#deleteBanco(com.qat.samples.sysmgmt.base.model.Banco)
 */
@Override
public InternalResponse deleteBancoById(Banco county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_BANCO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IBancoBAR#deleteAllBancos()
 */
@Override
public InternalResponse deleteAllBancos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_BANCO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IBancoBAR#fetchBancoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Banco> fetchBancoById(FetchByIdRequest request)
{
	InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();
	response.addResult((Banco)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_BANCO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IBancoBAR#fetchAllBancosCache()
 */
@Override
public InternalResultsResponse<Banco> fetchAllBancos(Banco banco)
{
	InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_BANCO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IBancoBAR#fetchBancosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Banco> fetchBancosByRequest(BancoInquiryRequest request)
{
	InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();
	fetchBancosByRequest(getSqlSession(), request, STMT_FETCH_BANCO_COUNT, STMT_FETCH_BANCO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchBancosByRequest ####======================================

public static void fetchBancosByRequest(SqlSession sqlSession, BancoInquiryRequest request, String countStatement,
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


//===================================### CONTACORRENTE ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContaCorrenteBAR#insertContaCorrente(com.qat.samples.sysmgmt.base.model.ContaCorrente)
 */
@Override
public InternalResponse insertContaCorrente(ContaCorrente county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONTACORRENTE, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContaCorrenteBAR#updateContaCorrente(com.qat.samples.sysmgmt.base.model.ContaCorrente)
 */
@Override
public InternalResponse updateContaCorrente(ContaCorrente county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONTACORRENTE, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContaCorrenteBAR#deleteContaCorrente(com.qat.samples.sysmgmt.base.model.ContaCorrente)
 */
@Override
public InternalResponse deleteContaCorrenteById(ContaCorrente county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONTACORRENTE, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContaCorrenteBAR#deleteAllContaCorrentes()
 */
@Override
public InternalResponse deleteAllContaCorrentes()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONTACORRENTE_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IContaCorrenteBAR#fetchContaCorrenteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<ContaCorrente> fetchContaCorrenteById(FetchByIdRequest request)
{
	InternalResultsResponse<ContaCorrente> response = new InternalResultsResponse<ContaCorrente>();
	response.addResult((ContaCorrente)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONTACORRENTE,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IContaCorrenteBAR#fetchAllContaCorrentesCache()
 */
@Override
public InternalResultsResponse<ContaCorrente> fetchAllContaCorrentes(ContaCorrente contacorrente)
{
	InternalResultsResponse<ContaCorrente> response = new InternalResultsResponse<ContaCorrente>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONTACORRENTE_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IContaCorrenteBAR#fetchContaCorrentesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ContaCorrente> fetchContaCorrentesByRequest(ContaCorrenteInquiryRequest request)
{
	InternalResultsResponse<ContaCorrente> response = new InternalResultsResponse<ContaCorrente>();
	fetchContaCorrentesByRequest(getSqlSession(), request, STMT_FETCH_CONTACORRENTE_COUNT, STMT_FETCH_CONTACORRENTE_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchContaCorrentesByRequest ####======================================

public static void fetchContaCorrentesByRequest(SqlSession sqlSession, ContaCorrenteInquiryRequest request, String countStatement,
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


//===================================### CAIXA ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICaixaBAR#insertCaixa(com.qat.samples.sysmgmt.base.model.Caixa)
 */
@Override
public InternalResponse insertCaixa(Caixa county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CAIXA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICaixaBAR#updateCaixa(com.qat.samples.sysmgmt.base.model.Caixa)
 */
@Override
public InternalResponse updateCaixa(Caixa county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CAIXA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICaixaBAR#deleteCaixa(com.qat.samples.sysmgmt.base.model.Caixa)
 */
@Override
public InternalResponse deleteCaixaById(Caixa county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CAIXA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICaixaBAR#deleteAllCaixas()
 */
@Override
public InternalResponse deleteAllCaixas()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CAIXA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ICaixaBAR#fetchCaixaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Caixa> fetchCaixaById(FetchByIdRequest request)
{
	InternalResultsResponse<Caixa> response = new InternalResultsResponse<Caixa>();
	response.addResult((Caixa)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CAIXA,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICaixaBAR#fetchAllCaixasCache()
 */
@Override
public InternalResultsResponse<Caixa> fetchAllCaixas(Caixa caixa)
{
	InternalResultsResponse<Caixa> response = new InternalResultsResponse<Caixa>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CAIXA_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ICaixaBAR#fetchCaixasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Caixa> fetchCaixasByRequest(CaixaInquiryRequest request)
{
	InternalResultsResponse<Caixa> response = new InternalResultsResponse<Caixa>();
	fetchCaixasByRequest(getSqlSession(), request, STMT_FETCH_CAIXA_COUNT, STMT_FETCH_CAIXA_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchCaixasByRequest ####======================================

public static void fetchCaixasByRequest(SqlSession sqlSession, CaixaInquiryRequest request, String countStatement,
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
