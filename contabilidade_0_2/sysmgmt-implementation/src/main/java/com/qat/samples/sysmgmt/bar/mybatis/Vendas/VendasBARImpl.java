package com.qat.samples.sysmgmt.bar.mybatis.Vendas;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Vendas.IVendasBAR;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class VendasBARImpl extends SqlSessionDaoSupport implements IVendasBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### NOTAFISCALSAIDA ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_NOTAFISCALSAIDA = "NotaFiscalSaidaMap.";

/** The Constant STMT_INSERT_NOTAFISCALSAIDA. */
private static final String STMT_INSERT_NOTAFISCALSAIDA = NAMESPACE_NOTAFISCALSAIDA + "insertNotaFiscalSaida";

/** The Constant STMT_UPDATE_NOTAFISCALSAIDA. */
private static final String STMT_UPDATE_NOTAFISCALSAIDA = NAMESPACE_NOTAFISCALSAIDA + "updateNotaFiscalSaida";

/** The Constant STMT_DELETE_NOTAFISCALSAIDA. */
private static final String STMT_DELETE_NOTAFISCALSAIDA = NAMESPACE_NOTAFISCALSAIDA + "deleteNotaFiscalSaidaById";

	/** The Constant STMT_DELETE_NOTAFISCALSAIDA_ALL. */
	private static final String STMT_DELETE_NOTAFISCALSAIDA_ALL = NAMESPACE_NOTAFISCALSAIDA + "deleteAllNotaFiscalSaidas";

	/** The Constant STMT_FETCH_NOTAFISCALSAIDA. */
	private static final String STMT_FETCH_NOTAFISCALSAIDA = NAMESPACE_NOTAFISCALSAIDA + "fetchNotaFiscalSaidaById";

	/** The Constant STMT_FETCH_NOTAFISCALSAIDA_ALL. */
	private static final String STMT_FETCH_NOTAFISCALSAIDA_ALL = NAMESPACE_NOTAFISCALSAIDA + "fetchAllNotaFiscalSaidas";

	/** The Constant STMT_FETCH_NOTAFISCALSAIDA_COUNT. */
	private static final String STMT_FETCH_NOTAFISCALSAIDA_COUNT = NAMESPACE_NOTAFISCALSAIDA + "fetchNotaFiscalSaidaRowCount";

	/** The Constant STMT_FETCH_NOTAFISCALSAIDA_ALL_REQUEST. */
	private static final String STMT_FETCH_NOTAFISCALSAIDA_ALL_REQUEST = NAMESPACE_NOTAFISCALSAIDA + "fetchAllNotaFiscalSaidasRequest";

///===================================### ORCAMENTO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_ORCAMENTO = "OrcamentoMap.";

/** The Constant STMT_INSERT_ORCAMENTO. */
private static final String STMT_INSERT_ORCAMENTO = NAMESPACE_ORCAMENTO + "insertOrcamento";

/** The Constant STMT_UPDATE_ORCAMENTO. */
private static final String STMT_UPDATE_ORCAMENTO = NAMESPACE_ORCAMENTO + "updateOrcamento";

/** The Constant STMT_DELETE_ORCAMENTO. */
private static final String STMT_DELETE_ORCAMENTO = NAMESPACE_ORCAMENTO + "deleteOrcamentoById";

	/** The Constant STMT_DELETE_ORCAMENTO_ALL. */
	private static final String STMT_DELETE_ORCAMENTO_ALL = NAMESPACE_ORCAMENTO + "deleteAllOrcamentos";

	/** The Constant STMT_FETCH_ORCAMENTO. */
	private static final String STMT_FETCH_ORCAMENTO = NAMESPACE_ORCAMENTO + "fetchOrcamentoById";

	/** The Constant STMT_FETCH_ORCAMENTO_ALL. */
	private static final String STMT_FETCH_ORCAMENTO_ALL = NAMESPACE_ORCAMENTO + "fetchAllOrcamentos";

	/** The Constant STMT_FETCH_ORCAMENTO_COUNT. */
	private static final String STMT_FETCH_ORCAMENTO_COUNT = NAMESPACE_ORCAMENTO + "fetchOrcamentoRowCount";

	/** The Constant STMT_FETCH_ORCAMENTO_ALL_REQUEST. */
	private static final String STMT_FETCH_ORCAMENTO_ALL_REQUEST = NAMESPACE_ORCAMENTO + "fetchAllOrcamentosRequest";

///===================================### ORDEMSERVICO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_ORDEMSERVICO = "OrdemServicoMap.";

/** The Constant STMT_INSERT_ORDEMSERVICO. */
private static final String STMT_INSERT_ORDEMSERVICO = NAMESPACE_ORDEMSERVICO + "insertOrdemServico";

/** The Constant STMT_UPDATE_ORDEMSERVICO. */
private static final String STMT_UPDATE_ORDEMSERVICO = NAMESPACE_ORDEMSERVICO + "updateOrdemServico";

/** The Constant STMT_DELETE_ORDEMSERVICO. */
private static final String STMT_DELETE_ORDEMSERVICO = NAMESPACE_ORDEMSERVICO + "deleteOrdemServicoById";

	/** The Constant STMT_DELETE_ORDEMSERVICO_ALL. */
	private static final String STMT_DELETE_ORDEMSERVICO_ALL = NAMESPACE_ORDEMSERVICO + "deleteAllOrdemServicos";

	/** The Constant STMT_FETCH_ORDEMSERVICO. */
	private static final String STMT_FETCH_ORDEMSERVICO = NAMESPACE_ORDEMSERVICO + "fetchOrdemServicoById";

	/** The Constant STMT_FETCH_ORDEMSERVICO_ALL. */
	private static final String STMT_FETCH_ORDEMSERVICO_ALL = NAMESPACE_ORDEMSERVICO + "fetchAllOrdemServicos";

	/** The Constant STMT_FETCH_ORDEMSERVICO_COUNT. */
	private static final String STMT_FETCH_ORDEMSERVICO_COUNT = NAMESPACE_ORDEMSERVICO + "fetchOrdemServicoRowCount";

	/** The Constant STMT_FETCH_ORDEMSERVICO_ALL_REQUEST. */
	private static final String STMT_FETCH_ORDEMSERVICO_ALL_REQUEST = NAMESPACE_ORDEMSERVICO + "fetchAllOrdemServicosRequest";

//===================================### NOTAFISCALSAIDA ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalSaidaBAR#insertNotaFiscalSaida(com.qat.samples.sysmgmt.base.model.NotaFiscalSaida)
 */
@Override
public InternalResponse insertNotaFiscalSaida(NotaFiscalSaida county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NOTAFISCALSAIDA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalSaidaBAR#updateNotaFiscalSaida(com.qat.samples.sysmgmt.base.model.NotaFiscalSaida)
 */
@Override
public InternalResponse updateNotaFiscalSaida(NotaFiscalSaida county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NOTAFISCALSAIDA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalSaidaBAR#deleteNotaFiscalSaida(com.qat.samples.sysmgmt.base.model.NotaFiscalSaida)
 */
@Override
public InternalResponse deleteNotaFiscalSaidaById(NotaFiscalSaida county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NOTAFISCALSAIDA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalSaidaBAR#deleteAllNotaFiscalSaidas()
 */
@Override
public InternalResponse deleteAllNotaFiscalSaidas()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NOTAFISCALSAIDA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.INotaFiscalSaidaBAR#fetchNotaFiscalSaidaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaById(FetchByIdRequest request)
{
	InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();
	response.addResult((NotaFiscalSaida)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NOTAFISCALSAIDA,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalSaidaBAR#fetchAllNotaFiscalSaidasCache()
 */
@Override
public InternalResultsResponse<NotaFiscalSaida> fetchAllNotaFiscalSaidas(NotaFiscalSaida notafiscalsaida)
{
	InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NOTAFISCALSAIDA_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.INotaFiscalSaidaBAR#fetchNotaFiscalSaidasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidasByRequest(NotaFiscalInquiryRequest request)
{
	InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();
	fetchNotaFiscalSaidasByRequest(getSqlSession(), request, STMT_FETCH_NOTAFISCALSAIDA_COUNT, STMT_FETCH_NOTAFISCALSAIDA_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchNotaFiscalSaidasByRequest ####======================================

public static void fetchNotaFiscalSaidasByRequest(SqlSession sqlSession, NotaFiscalInquiryRequest request, String countStatement,
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


//===================================### ORCAMENTO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrcamentoBAR#insertOrcamento(com.qat.samples.sysmgmt.base.model.Orcamento)
 */
@Override
public InternalResponse insertOrcamento(Orcamento county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ORCAMENTO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrcamentoBAR#updateOrcamento(com.qat.samples.sysmgmt.base.model.Orcamento)
 */
@Override
public InternalResponse updateOrcamento(Orcamento county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ORCAMENTO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrcamentoBAR#deleteOrcamento(com.qat.samples.sysmgmt.base.model.Orcamento)
 */
@Override
public InternalResponse deleteOrcamentoById(Orcamento county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ORCAMENTO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrcamentoBAR#deleteAllOrcamentos()
 */
@Override
public InternalResponse deleteAllOrcamentos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ORCAMENTO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IOrcamentoBAR#fetchOrcamentoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request)
{
	InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();
	response.addResult((Orcamento)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ORCAMENTO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrcamentoBAR#fetchAllOrcamentosCache()
 */
@Override
public InternalResultsResponse<Orcamento> fetchAllOrcamentos(Orcamento orcamento)
{
	InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ORCAMENTO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IOrcamentoBAR#fetchOrcamentosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Orcamento> fetchOrcamentosByRequest(OrcamentoInquiryRequest request)
{
	InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();
	fetchOrcamentosByRequest(getSqlSession(), request, STMT_FETCH_ORCAMENTO_COUNT, STMT_FETCH_ORCAMENTO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchOrcamentosByRequest ####======================================

public static void fetchOrcamentosByRequest(SqlSession sqlSession, OrcamentoInquiryRequest request, String countStatement,
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


//===================================### ORDEMSERVICO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrdemServicoBAR#insertOrdemServico(com.qat.samples.sysmgmt.base.model.OrdemServico)
 */
@Override
public InternalResponse insertOrdemServico(OrdemServico county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ORDEMSERVICO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrdemServicoBAR#updateOrdemServico(com.qat.samples.sysmgmt.base.model.OrdemServico)
 */
@Override
public InternalResponse updateOrdemServico(OrdemServico county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ORDEMSERVICO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrdemServicoBAR#deleteOrdemServico(com.qat.samples.sysmgmt.base.model.OrdemServico)
 */
@Override
public InternalResponse deleteOrdemServicoById(OrdemServico county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ORDEMSERVICO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrdemServicoBAR#deleteAllOrdemServicos()
 */
@Override
public InternalResponse deleteAllOrdemServicos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ORDEMSERVICO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IOrdemServicoBAR#fetchOrdemServicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request)
{
	InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
	response.addResult((OrdemServico)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ORDEMSERVICO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IOrdemServicoBAR#fetchAllOrdemServicosCache()
 */
@Override
public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos(OrdemServico ordemservico)
{
	InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ORDEMSERVICO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IOrdemServicoBAR#fetchOrdemServicosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request)
{
	InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
	fetchOrdemServicosByRequest(getSqlSession(), request, STMT_FETCH_ORDEMSERVICO_COUNT, STMT_FETCH_ORDEMSERVICO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchOrdemServicosByRequest ####======================================

public static void fetchOrdemServicosByRequest(SqlSession sqlSession, OrdemServicoInquiryRequest request, String countStatement,
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
