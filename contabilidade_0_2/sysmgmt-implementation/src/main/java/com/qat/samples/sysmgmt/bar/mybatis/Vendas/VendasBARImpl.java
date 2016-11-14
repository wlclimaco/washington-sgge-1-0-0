package com.qat.samples.sysmgmt.bar.mybatis.Vendas;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Nfe.INFeBAR;
import com.qat.samples.sysmgmt.bar.Vendas.IVendasBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.InsertHistBARD;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalItens;
import com.qat.samples.sysmgmt.nfe.model.NFNota;
import com.qat.samples.sysmgmt.nfe.request.NFNotaInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

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


	/** The Constant STMT_FETCH_NOTAFISCALSAIDA. */
	private static final String STMT_FETCH_NOTAFISCALSAIDA = NAMESPACE_NOTAFISCALSAIDA + "fetchNotaFiscalSaidaById";

	/** The Constant STMT_FETCH_NOTAFISCALSAIDA_ALL. */
	private static final String STMT_FETCH_NOTAFISCALSAIDA_ALL = NAMESPACE_NOTAFISCALSAIDA + "fetchAllNotaFiscalSaidas";

	/** The Constant STMT_FETCH_NOTAFISCALSAIDA_COUNT. */
	private static final String STMT_FETCH_NOTAFISCALSAIDA_COUNT = NAMESPACE_NOTAFISCALSAIDA + "fetchNotaFiscalSaidaRowCount";

	/** The Constant STMT_FETCH_NOTAFISCALSAIDA_ALL_REQUEST. */
	private static final String STMT_FETCH_NOTAFISCALSAIDA_ALL_REQUEST = NAMESPACE_NOTAFISCALSAIDA + "fetchAllNotaFiscalSaidasRequest";



	///===================================### CONHECIMENTOTRANSPORTE ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONHECIMENTOTRANSPORTE = "ConhecimentoTransporteMap.";

	/** The Constant STMT_INSERT_CONHECIMENTOTRANSPORTE. */
	private static final String STMT_INSERT_CONHECIMENTOTRANSPORTE = NAMESPACE_CONHECIMENTOTRANSPORTE + "insertConhecimentoTransporte";

	/** The Constant STMT_UPDATE_CONHECIMENTOTRANSPORTE. */
	private static final String STMT_UPDATE_CONHECIMENTOTRANSPORTE = NAMESPACE_CONHECIMENTOTRANSPORTE + "updateConhecimentoTransporte";

	/** The Constant STMT_DELETE_CONHECIMENTOTRANSPORTE. */
	private static final String STMT_DELETE_CONHECIMENTOTRANSPORTE = NAMESPACE_CONHECIMENTOTRANSPORTE + "deleteConhecimentoTransporteById";

		/** The Constant STMT_DELETE_CONHECIMENTOTRANSPORTE_ALL. */
		private static final String STMT_DELETE_CONHECIMENTOTRANSPORTE_ALL = NAMESPACE_CONHECIMENTOTRANSPORTE + "deleteAllConhecimentoTransportes";

		/** The Constant STMT_FETCH_CONHECIMENTOTRANSPORTE. */
		private static final String STMT_FETCH_CONHECIMENTOTRANSPORTE = NAMESPACE_CONHECIMENTOTRANSPORTE + "fetchConhecimentoTransporteById";

		/** The Constant STMT_FETCH_CONHECIMENTOTRANSPORTE_ALL. */
		private static final String STMT_FETCH_CONHECIMENTOTRANSPORTE_ALL = NAMESPACE_CONHECIMENTOTRANSPORTE + "fetchAllConhecimentoTransportes";

		/** The Constant STMT_FETCH_CONHECIMENTOTRANSPORTE_COUNT. */
		private static final String STMT_FETCH_CONHECIMENTOTRANSPORTE_COUNT = NAMESPACE_CONHECIMENTOTRANSPORTE + "fetchConhecimentoTransporteRowCount";

		/** The Constant STMT_FETCH_CONHECIMENTOTRANSPORTE_ALL_REQUEST. */
		private static final String STMT_FETCH_CONHECIMENTOTRANSPORTE_ALL_REQUEST = NAMESPACE_CONHECIMENTOTRANSPORTE + "fetchAllConhecimentoTransportesRequest";

	///===================================### NOTAFISCALITENS ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NOTAFISCALITENS = "NotaFiscalItensMap.";

	/** The Constant STMT_INSERT_NOTAFISCALITENS. */
	private static final String STMT_INSERT_NOTAFISCALITENS = NAMESPACE_NOTAFISCALITENS + "insertNotaFiscalItens";

	/** The Constant STMT_UPDATE_NOTAFISCALITENS. */
	private static final String STMT_UPDATE_NOTAFISCALITENS = NAMESPACE_NOTAFISCALITENS + "updateNotaFiscalItens";

	/** The Constant STMT_DELETE_NOTAFISCALITENS. */
	private static final String STMT_DELETE_NOTAFISCALITENS = NAMESPACE_NOTAFISCALITENS + "deleteNotaFiscalItensById";

		/** The Constant STMT_DELETE_NOTAFISCALITENS_ALL. */
		private static final String STMT_DELETE_NOTAFISCALITENS_ALL = NAMESPACE_NOTAFISCALITENS + "deleteAllNotaFiscalItenss";

		/** The Constant STMT_FETCH_NOTAFISCALITENS. */
		private static final String STMT_FETCH_NOTAFISCALITENS = NAMESPACE_NOTAFISCALITENS + "fetchNotaFiscalItensById";

		/** The Constant STMT_FETCH_NOTAFISCALITENS_ALL. */
		private static final String STMT_FETCH_NOTAFISCALITENS_ALL = NAMESPACE_NOTAFISCALITENS + "fetchAllNotaFiscalItenss";

		/** The Constant STMT_FETCH_NOTAFISCALITENS_COUNT. */
		private static final String STMT_FETCH_NOTAFISCALITENS_COUNT = NAMESPACE_NOTAFISCALITENS + "fetchNotaFiscalItensRowCount";

		/** The Constant STMT_FETCH_NOTAFISCALITENS_ALL_REQUEST. */
		private static final String STMT_FETCH_NOTAFISCALITENS_ALL_REQUEST = NAMESPACE_NOTAFISCALITENS + "fetchAllNotaFiscalItenssRequest";

///===================================### ORCAMENTO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_ORCAMENTO = "OrcamentoMap.";

/** The Constant STMT_INSERT_ORCAMENTO. */
private static final String STMT_INSERT_NF = "NotaFiscalMap." + "insertNotaFiscal";

/** The Constant STMT_UPDATE_ORCAMENTO. */
private static final String STMT_UPDATE_NF = "NotaFiscalMap." + "updateNotaFiscal";

/** The Constant STMT_DELETE_ORCAMENTO. */
private static final String STMT_DELETE_NF = "NotaFiscalMap." + "deleteNotaFiscalById";

	/** The Constant STMT_DELETE_ORCAMENTO_ALL. */
	private static final String STMT_DELETE_NF_ALL = "NotaFiscalMap." + "deleteAllNotaFiscals";

	/** The Constant STMT_FETCH_ORCAMENTO. */
	private static final String STMT_FETCH_ORCAMENTO = NAMESPACE_ORCAMENTO + "fetchOrcamentoById";

	/** The Constant STMT_FETCH_ORCAMENTO_ALL. */
	private static final String STMT_FETCH_ORCAMENTO_ALL = NAMESPACE_ORCAMENTO + "fetchAllOrcamentos";

	/** The Constant STMT_FETCH_ORCAMENTO_COUNT. */
	private static final String STMT_FETCH_ORCAMENTO_COUNT = NAMESPACE_ORCAMENTO + "fetchOrcamentoRowCount";

	/** The Constant STMT_FETCH_ORCAMENTO_ALL_REQUEST. */
	private static final String STMT_FETCH_ORCAMENTO_ALL_REQUEST = NAMESPACE_ORCAMENTO + "fetchAllOrcamentoByRequest";

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

//===================================### NOTAFISCALSAIDA ####====================================//

	INFeBAR nfeBAR;
	IHistoricoBAR historicoBAR;

	public IHistoricoBAR getHistoricoBAR() {
		return historicoBAR;
	}

	public void setHistoricoBAR(IHistoricoBAR historicoBAR) {
		this.historicoBAR = historicoBAR;
	}

	public INFeBAR getNfeBAR() {
		return nfeBAR;
	}

	public void setNfeBAR(INFeBAR nfeBAR) {
		this.nfeBAR = nfeBAR;
	}

	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalSaidaBAR#insertNotaFiscalSaida(com.qat.samples.sysmgmt.base.model.NotaFiscalSaida)
 */
@Override
public InternalResponse insertNotaFiscalSaida(NFNota nota)
{

	return getNfeBAR().insertNFNota(nota);
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalSaidaBAR#updateNotaFiscalSaida(com.qat.samples.sysmgmt.base.model.NotaFiscalSaida)
 */
@Override
public InternalResponse updateNotaFiscalSaida(NFNota nota)
{
	return getNfeBAR().updateNFNota(nota);
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalSaidaBAR#deleteNotaFiscalSaida(com.qat.samples.sysmgmt.base.model.NotaFiscalSaida)
 */
@Override
public InternalResponse deleteNotaFiscalSaidaById(NFNota nota)
{
	return getNfeBAR().deleteNFNotaById(nota);
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalSaidaBAR#deleteAllNotaFiscalSaidas()
 */
@Override
public InternalResponse deleteAllNotaFiscalSaidas()
{
	return null;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.INotaFiscalSaidaBAR#fetchNotaFiscalSaidaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public NFNota fetchNotaFiscalSaidaById(FetchByIdRequest request)
{

	return getNfeBAR().fetchNFNotaById(request);
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.INotaFiscalSaidaBAR#fetchAllNotaFiscalSaidasCache()
 */
@Override
public InternalResultsResponse<NFNota> fetchAllNotaFiscalSaidas(NFNota notafiscalsaida)
{
	return getNfeBAR().fetchAllNFNotas(notafiscalsaida);
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.INotaFiscalSaidaBAR#fetchNotaFiscalSaidasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<NFNota> fetchNotaFiscalSaidasByRequest(NFNotaInquiryRequest request)
{
	return getNfeBAR().fetchNFNotasByRequest(request);
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
public OrdemServico fetchOrdemServicoById(FetchByIdRequest request)
{
	return (OrdemServico)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ORDEMSERVICO, request.getFetchId());
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



//===================================### NOTAFISCALITENS ####======================================
	/**
/*
* (non-Javadoc)
* @see com.qat.samples.sysmgmt.base.bar.INotaFiscalItensBAR#insertNotaFiscalItens(com.qat.samples.sysmgmt.base.model.NotaFiscalItens)
*/
@Override
public InternalResponse insertNotaFiscalItens(NotaFiscalItens notafiscalitens)
{
	InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

notafiscalitens.setProcessId(notafiscalitens.getTransactionId());

	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NOTAFISCALITENS, notafiscalitens, response);


Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NOTAFISCALITENS, AcaoEnum.INSERT, notafiscalitens.getTransactionId(),
			getHistoricoBAR(), response, notafiscalitens.getId(),notafiscalitens.getUserId());





	return response;
}

/*
* (non-Javadoc)
* @see com.qat.samples.sysmgmt.base.bar.INotaFiscalItensBAR#updateNotaFiscalItens(com.qat.samples.sysmgmt.base.model.NotaFiscalItens)
*/
@Override
public InternalResponse updateNotaFiscalItens(NotaFiscalItens notafiscalitens)
{
	InternalResponse response = new InternalResponse();
notafiscalitens.setProcessId(notafiscalitens.getTransactionId());
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NOTAFISCALITENS, notafiscalitens, response);

Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NOTAFISCALITENS, AcaoEnum.UPDATE, notafiscalitens.getTransactionId(),
			getHistoricoBAR(), response, notafiscalitens.getId(),notafiscalitens.getUserId());

	return response;
}

/*
* (non-Javadoc)
* @see com.qat.samples.sysmgmt.base.bar.INotaFiscalItensBAR#deleteNotaFiscalItens(com.qat.samples.sysmgmt.base.model.NotaFiscalItens)
*/
@Override
public InternalResponse deleteNotaFiscalItensById(NotaFiscalItens notafiscalitens)
{
	InternalResponse response = new InternalResponse();
notafiscalitens.setProcessId(notafiscalitens.getTransactionId());
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NOTAFISCALITENS, notafiscalitens, response);

Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NOTAFISCALITENS, AcaoEnum.DELETE, notafiscalitens.getTransactionId(),
			getHistoricoBAR(), response, notafiscalitens.getId(),notafiscalitens.getUserId());

	return response;
}

/*
* (non-Javadoc)
* @see com.qat.samples.sysmgmt.base.bar.INotaFiscalItensBAR#deleteAllNotaFiscalItenss()
*/
@Override
public InternalResponse deleteAllNotaFiscalItenss()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NOTAFISCALITENS_ALL, response);
	return response;
}

/*
* (non-Javadoc)
* @see
* com.qat.samples.sysmgmt.bar.INotaFiscalItensBAR#fetchNotaFiscalItensById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
*/
@Override
public NotaFiscalItens fetchNotaFiscalItensById(FetchByIdRequest request)
{
return (NotaFiscalItens)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NOTAFISCALITENS, request.getFetchId());

}

/*
* (non-Javadoc)
* @see com.qat.samples.sysmgmt.base.bar.INotaFiscalItensBAR#fetchAllNotaFiscalItenssCache()
*/
@Override
public InternalResultsResponse<NotaFiscalItens> fetchAllNotaFiscalItenss(NotaFiscalItens notafiscalitens)
{
	InternalResultsResponse<NotaFiscalItens> response = new InternalResultsResponse<NotaFiscalItens>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NOTAFISCALITENS_ALL));
	return response;
}

/*
* (non-Javadoc)
* @see com.qat.samples.sysmgmt.bar.INotaFiscalItensBAR#fetchNotaFiscalItenssByRequest(com.qat.samples.sysmgmt.model.request.
* PagedInquiryRequest)
*/
@Override
public InternalResultsResponse<NotaFiscalItens> fetchNotaFiscalItenssByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<NotaFiscalItens> response = new InternalResultsResponse<NotaFiscalItens>();
	fetchNotaFiscalItenssByRequest(getSqlSession(), request, STMT_FETCH_NOTAFISCALITENS_COUNT, STMT_FETCH_NOTAFISCALITENS_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchNotaFiscalItenssByRequest ####======================================

public static void fetchNotaFiscalItenssByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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
