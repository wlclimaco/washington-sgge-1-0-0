package com.qat.samples.sysmgmt.bar.mybatis.Compras;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Compras.IComprasBAR;
import com.qat.samples.sysmgmt.cotacao.model.Cotacao;
import com.qat.samples.sysmgmt.cotacao.request.CotacaoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class ComprasBARImpl extends SqlSessionDaoSupport implements IComprasBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### NOTAFISCALENTRADA ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_NOTAFISCALENTRADA = "NotaFiscalEntradaMap.";

/** The Constant STMT_INSERT_NOTAFISCALENTRADA. */
private static final String STMT_INSERT_NOTAFISCALENTRADA = NAMESPACE_NOTAFISCALENTRADA + "insertNotaFiscalEntrada";

/** The Constant STMT_UPDATE_NOTAFISCALENTRADA. */
private static final String STMT_UPDATE_NOTAFISCALENTRADA = NAMESPACE_NOTAFISCALENTRADA + "updateNotaFiscalEntrada";

/** The Constant STMT_DELETE_NOTAFISCALENTRADA. */
private static final String STMT_DELETE_NOTAFISCALENTRADA = NAMESPACE_NOTAFISCALENTRADA + "deleteNotaFiscalEntradaById";

	/** The Constant STMT_DELETE_NOTAFISCALENTRADA_ALL. */
	private static final String STMT_DELETE_NOTAFISCALENTRADA_ALL = NAMESPACE_NOTAFISCALENTRADA + "deleteAllNotaFiscalEntradas";

	/** The Constant STMT_FETCH_NOTAFISCALENTRADA. */
	private static final String STMT_FETCH_NOTAFISCALENTRADA = NAMESPACE_NOTAFISCALENTRADA + "fetchNotaFiscalEntradaById";

	/** The Constant STMT_FETCH_NOTAFISCALENTRADA_ALL. */
	private static final String STMT_FETCH_NOTAFISCALENTRADA_ALL = NAMESPACE_NOTAFISCALENTRADA + "fetchAllNotaFiscalEntradas";

	/** The Constant STMT_FETCH_NOTAFISCALENTRADA_COUNT. */
	private static final String STMT_FETCH_NOTAFISCALENTRADA_COUNT = NAMESPACE_NOTAFISCALENTRADA + "fetchNotaFiscalEntradaRowCount";

	/** The Constant STMT_FETCH_NOTAFISCALENTRADA_ALL_REQUEST. */
	private static final String STMT_FETCH_NOTAFISCALENTRADA_ALL_REQUEST = NAMESPACE_NOTAFISCALENTRADA + "fetchAllNotaFiscalEntradasRequest";

///===================================### PEDIDOCOMPRAS ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_PEDIDOCOMPRAS = "PedidoComprasMap.";

/** The Constant STMT_INSERT_PEDIDOCOMPRAS. */
private static final String STMT_INSERT_PEDIDOCOMPRAS = NAMESPACE_PEDIDOCOMPRAS + "insertPedidoCompras";

/** The Constant STMT_UPDATE_PEDIDOCOMPRAS. */
private static final String STMT_UPDATE_PEDIDOCOMPRAS = NAMESPACE_PEDIDOCOMPRAS + "updatePedidoCompras";

/** The Constant STMT_DELETE_PEDIDOCOMPRAS. */
private static final String STMT_DELETE_PEDIDOCOMPRAS = NAMESPACE_PEDIDOCOMPRAS + "deletePedidoComprasById";

	/** The Constant STMT_DELETE_PEDIDOCOMPRAS_ALL. */
	private static final String STMT_DELETE_PEDIDOCOMPRAS_ALL = NAMESPACE_PEDIDOCOMPRAS + "deleteAllPedidoComprass";

	/** The Constant STMT_FETCH_PEDIDOCOMPRAS. */
	private static final String STMT_FETCH_PEDIDOCOMPRAS = NAMESPACE_PEDIDOCOMPRAS + "fetchPedidoComprasById";

	/** The Constant STMT_FETCH_PEDIDOCOMPRAS_ALL. */
	private static final String STMT_FETCH_PEDIDOCOMPRAS_ALL = NAMESPACE_PEDIDOCOMPRAS + "fetchAllPedidoComprass";

	/** The Constant STMT_FETCH_PEDIDOCOMPRAS_COUNT. */
	private static final String STMT_FETCH_PEDIDOCOMPRAS_COUNT = NAMESPACE_PEDIDOCOMPRAS + "fetchPedidoComprasRowCount";

	/** The Constant STMT_FETCH_PEDIDOCOMPRAS_ALL_REQUEST. */
	private static final String STMT_FETCH_PEDIDOCOMPRAS_ALL_REQUEST = NAMESPACE_PEDIDOCOMPRAS + "fetchAllPedidoComprassRequest";

///===================================### COTACAO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_COTACAO = "CotacaoMap.";

/** The Constant STMT_INSERT_COTACAO. */
private static final String STMT_INSERT_COTACAO = NAMESPACE_COTACAO + "insertCotacao";

/** The Constant STMT_UPDATE_COTACAO. */
private static final String STMT_UPDATE_COTACAO = NAMESPACE_COTACAO + "updateCotacao";

/** The Constant STMT_DELETE_COTACAO. */
private static final String STMT_DELETE_COTACAO = NAMESPACE_COTACAO + "deleteCotacaoById";

	/** The Constant STMT_DELETE_COTACAO_ALL. */
	private static final String STMT_DELETE_COTACAO_ALL = NAMESPACE_COTACAO + "deleteAllCotacaos";

	/** The Constant STMT_FETCH_COTACAO. */
	private static final String STMT_FETCH_COTACAO = NAMESPACE_COTACAO + "fetchCotacaoById";

	/** The Constant STMT_FETCH_COTACAO_ALL. */
	private static final String STMT_FETCH_COTACAO_ALL = NAMESPACE_COTACAO + "fetchAllCotacaos";

	/** The Constant STMT_FETCH_COTACAO_COUNT. */
	private static final String STMT_FETCH_COTACAO_COUNT = NAMESPACE_COTACAO + "fetchCotacaoRowCount";

	/** The Constant STMT_FETCH_COTACAO_ALL_REQUEST. */
	private static final String STMT_FETCH_COTACAO_ALL_REQUEST = NAMESPACE_COTACAO + "fetchAllCotacaosRequest";

//===================================### NOTAFISCALENTRADA ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalEntradaBAR#insertNotaFiscalEntrada(com.qat.samples.sysmgmt.base.model.NotaFiscalEntrada)
 */
@Override
public InternalResponse insertNotaFiscalEntrada(NotaFiscalEntrada county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NOTAFISCALENTRADA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalEntradaBAR#updateNotaFiscalEntrada(com.qat.samples.sysmgmt.base.model.NotaFiscalEntrada)
 */
@Override
public InternalResponse updateNotaFiscalEntrada(NotaFiscalEntrada county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NOTAFISCALENTRADA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalEntradaBAR#deleteNotaFiscalEntrada(com.qat.samples.sysmgmt.base.model.NotaFiscalEntrada)
 */
@Override
public InternalResponse deleteNotaFiscalEntradaById(NotaFiscalEntrada county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NOTAFISCALENTRADA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalEntradaBAR#deleteAllNotaFiscalEntradas()
 */
@Override
public InternalResponse deleteAllNotaFiscalEntradas()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NOTAFISCALENTRADA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.INotaFiscalEntradaBAR#fetchNotaFiscalEntradaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaById(FetchByIdRequest request)
{
	InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();
	response.addResult((NotaFiscalEntrada)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NOTAFISCALENTRADA,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalEntradaBAR#fetchAllNotaFiscalEntradasCache()
 */
@Override
public InternalResultsResponse<NotaFiscalEntrada> fetchAllNotaFiscalEntradas(NotaFiscalEntrada notafiscalentrada)
{
	InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NOTAFISCALENTRADA_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.INotaFiscalEntradaBAR#fetchNotaFiscalEntradasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradasByRequest(NotaFiscalInquiryRequest request)
{
	InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();
	fetchNotaFiscalEntradasByRequest(getSqlSession(), request, STMT_FETCH_NOTAFISCALENTRADA_COUNT, STMT_FETCH_NOTAFISCALENTRADA_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchNotaFiscalEntradasByRequest ####======================================

public static void fetchNotaFiscalEntradasByRequest(SqlSession sqlSession, NotaFiscalInquiryRequest request, String countStatement,
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


//===================================### PEDIDOCOMPRAS ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPedidoComprasBAR#insertPedidoCompras(com.qat.samples.sysmgmt.base.model.PedidoCompras)
 */
@Override
public InternalResponse insertPedidoCompras(PedidoCompras county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PEDIDOCOMPRAS, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPedidoComprasBAR#updatePedidoCompras(com.qat.samples.sysmgmt.base.model.PedidoCompras)
 */
@Override
public InternalResponse updatePedidoCompras(PedidoCompras county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PEDIDOCOMPRAS, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPedidoComprasBAR#deletePedidoCompras(com.qat.samples.sysmgmt.base.model.PedidoCompras)
 */
@Override
public InternalResponse deletePedidoComprasById(PedidoCompras county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PEDIDOCOMPRAS, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPedidoComprasBAR#deleteAllPedidoComprass()
 */
@Override
public InternalResponse deleteAllPedidoComprass()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PEDIDOCOMPRAS_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IPedidoComprasBAR#fetchPedidoComprasById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request)
{
	InternalResultsResponse<PedidoCompras> response = new InternalResultsResponse<PedidoCompras>();
	response.addResult((PedidoCompras)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PEDIDOCOMPRAS,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPedidoComprasBAR#fetchAllPedidoComprassCache()
 */
@Override
public InternalResultsResponse<PedidoCompras> fetchAllPedidoComprass(PedidoCompras pedidocompras)
{
	InternalResultsResponse<PedidoCompras> response = new InternalResultsResponse<PedidoCompras>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PEDIDOCOMPRAS_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IPedidoComprasBAR#fetchPedidoComprassByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<PedidoCompras> fetchPedidoComprassByRequest(PedidoComprasInquiryRequest request)
{
	InternalResultsResponse<PedidoCompras> response = new InternalResultsResponse<PedidoCompras>();
	fetchPedidoComprassByRequest(getSqlSession(), request, STMT_FETCH_PEDIDOCOMPRAS_COUNT, STMT_FETCH_PEDIDOCOMPRAS_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchPedidoComprassByRequest ####======================================

public static void fetchPedidoComprassByRequest(SqlSession sqlSession, PedidoComprasInquiryRequest request, String countStatement,
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


//===================================### COTACAO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICotacaoBAR#insertCotacao(com.qat.samples.sysmgmt.base.model.Cotacao)
 */
@Override
public InternalResponse insertCotacao(Cotacao county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_COTACAO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICotacaoBAR#updateCotacao(com.qat.samples.sysmgmt.base.model.Cotacao)
 */
@Override
public InternalResponse updateCotacao(Cotacao county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_COTACAO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICotacaoBAR#deleteCotacao(com.qat.samples.sysmgmt.base.model.Cotacao)
 */
@Override
public InternalResponse deleteCotacaoById(Cotacao county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_COTACAO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICotacaoBAR#deleteAllCotacaos()
 */
@Override
public InternalResponse deleteAllCotacaos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_COTACAO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ICotacaoBAR#fetchCotacaoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Cotacao> fetchCotacaoById(FetchByIdRequest request)
{
	InternalResultsResponse<Cotacao> response = new InternalResultsResponse<Cotacao>();
	response.addResult((Cotacao)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_COTACAO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICotacaoBAR#fetchAllCotacaosCache()
 */
@Override
public InternalResultsResponse<Cotacao> fetchAllCotacaos(Cotacao cotacao)
{
	InternalResultsResponse<Cotacao> response = new InternalResultsResponse<Cotacao>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_COTACAO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ICotacaoBAR#fetchCotacaosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Cotacao> fetchCotacaosByRequest(CotacaoInquiryRequest request)
{
	InternalResultsResponse<Cotacao> response = new InternalResultsResponse<Cotacao>();
	fetchCotacaosByRequest(getSqlSession(), request, STMT_FETCH_COTACAO_COUNT, STMT_FETCH_COTACAO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchCotacaosByRequest ####======================================

public static void fetchCotacaosByRequest(SqlSession sqlSession, CotacaoInquiryRequest request, String countStatement,
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
