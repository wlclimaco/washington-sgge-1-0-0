/** create by system gera-java version 1.0.0 30/04/2016 19:13 : 13*/
package com.qat.samples.sysmgmt.bar.mybatis.Cadastros;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Cadastros.ICadastrosBAR;
import com.qat.samples.sysmgmt.convenio.model.Convenio;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.Tarefa;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class CadastrosBARImpl extends SqlSessionDaoSupport implements ICadastrosBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### CLIENTE ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CLIENTE = "ClienteMap.";

/** The Constant STMT_INSERT_CLIENTE. */
private static final String STMT_INSERT_CLIENTE = NAMESPACE_CLIENTE + "insertCliente";

/** The Constant STMT_UPDATE_CLIENTE. */
private static final String STMT_UPDATE_CLIENTE = NAMESPACE_CLIENTE + "updateCliente";

/** The Constant STMT_DELETE_CLIENTE. */
private static final String STMT_DELETE_CLIENTE = NAMESPACE_CLIENTE + "deleteClienteById";

	/** The Constant STMT_DELETE_CLIENTE_ALL. */
	private static final String STMT_DELETE_CLIENTE_ALL = NAMESPACE_CLIENTE + "deleteAllClientes";

	/** The Constant STMT_FETCH_CLIENTE. */
	private static final String STMT_FETCH_CLIENTE = NAMESPACE_CLIENTE + "fetchClienteById";

	/** The Constant STMT_FETCH_CLIENTE_ALL. */
	private static final String STMT_FETCH_CLIENTE_ALL = NAMESPACE_CLIENTE + "fetchAllClientes";

	/** The Constant STMT_FETCH_CLIENTE_COUNT. */
	private static final String STMT_FETCH_CLIENTE_COUNT = NAMESPACE_CLIENTE + "fetchClienteRowCount";

	/** The Constant STMT_FETCH_CLIENTE_ALL_REQUEST. */
	private static final String STMT_FETCH_CLIENTE_ALL_REQUEST = NAMESPACE_CLIENTE + "fetchAllClientesRequest";

///===================================### FORNECEDOR ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_FORNECEDOR = "FornecedorMap.";

/** The Constant STMT_INSERT_FORNECEDOR. */
private static final String STMT_INSERT_FORNECEDOR = NAMESPACE_FORNECEDOR + "insertFornecedor";

/** The Constant STMT_UPDATE_FORNECEDOR. */
private static final String STMT_UPDATE_FORNECEDOR = NAMESPACE_FORNECEDOR + "updateFornecedor";

/** The Constant STMT_DELETE_FORNECEDOR. */
private static final String STMT_DELETE_FORNECEDOR = NAMESPACE_FORNECEDOR + "deleteFornecedorById";

	/** The Constant STMT_DELETE_FORNECEDOR_ALL. */
	private static final String STMT_DELETE_FORNECEDOR_ALL = NAMESPACE_FORNECEDOR + "deleteAllFornecedors";

	/** The Constant STMT_FETCH_FORNECEDOR. */
	private static final String STMT_FETCH_FORNECEDOR = NAMESPACE_FORNECEDOR + "fetchFornecedorById";

	/** The Constant STMT_FETCH_FORNECEDOR_ALL. */
	private static final String STMT_FETCH_FORNECEDOR_ALL = NAMESPACE_FORNECEDOR + "fetchAllFornecedors";

	/** The Constant STMT_FETCH_FORNECEDOR_COUNT. */
	private static final String STMT_FETCH_FORNECEDOR_COUNT = NAMESPACE_FORNECEDOR + "fetchFornecedorRowCount";

	/** The Constant STMT_FETCH_FORNECEDOR_ALL_REQUEST. */
	private static final String STMT_FETCH_FORNECEDOR_ALL_REQUEST = NAMESPACE_FORNECEDOR + "fetchAllFornecedorsRequest";

///===================================### TRANSPORTADOR ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_TRANSPORTADOR = "TransportadorMap.";

/** The Constant STMT_INSERT_TRANSPORTADOR. */
private static final String STMT_INSERT_TRANSPORTADOR = NAMESPACE_TRANSPORTADOR + "insertTransportador";

/** The Constant STMT_UPDATE_TRANSPORTADOR. */
private static final String STMT_UPDATE_TRANSPORTADOR = NAMESPACE_TRANSPORTADOR + "updateTransportador";

/** The Constant STMT_DELETE_TRANSPORTADOR. */
private static final String STMT_DELETE_TRANSPORTADOR = NAMESPACE_TRANSPORTADOR + "deleteTransportadorById";

	/** The Constant STMT_DELETE_TRANSPORTADOR_ALL. */
	private static final String STMT_DELETE_TRANSPORTADOR_ALL = NAMESPACE_TRANSPORTADOR + "deleteAllTransportadors";

	/** The Constant STMT_FETCH_TRANSPORTADOR. */
	private static final String STMT_FETCH_TRANSPORTADOR = NAMESPACE_TRANSPORTADOR + "fetchTransportadorById";

	/** The Constant STMT_FETCH_TRANSPORTADOR_ALL. */
	private static final String STMT_FETCH_TRANSPORTADOR_ALL = NAMESPACE_TRANSPORTADOR + "fetchAllTransportadors";

	/** The Constant STMT_FETCH_TRANSPORTADOR_COUNT. */
	private static final String STMT_FETCH_TRANSPORTADOR_COUNT = NAMESPACE_TRANSPORTADOR + "fetchTransportadorRowCount";

	/** The Constant STMT_FETCH_TRANSPORTADOR_ALL_REQUEST. */
	private static final String STMT_FETCH_TRANSPORTADOR_ALL_REQUEST = NAMESPACE_TRANSPORTADOR + "fetchAllTransportadorsRequest";

///===================================### CONVENIO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CONVENIO = "ConvenioMap.";

/** The Constant STMT_INSERT_CONVENIO. */
private static final String STMT_INSERT_CONVENIO = NAMESPACE_CONVENIO + "insertConvenio";

/** The Constant STMT_UPDATE_CONVENIO. */
private static final String STMT_UPDATE_CONVENIO = NAMESPACE_CONVENIO + "updateConvenio";

/** The Constant STMT_DELETE_CONVENIO. */
private static final String STMT_DELETE_CONVENIO = NAMESPACE_CONVENIO + "deleteConvenioById";

	/** The Constant STMT_DELETE_CONVENIO_ALL. */
	private static final String STMT_DELETE_CONVENIO_ALL = NAMESPACE_CONVENIO + "deleteAllConvenios";

	/** The Constant STMT_FETCH_CONVENIO. */
	private static final String STMT_FETCH_CONVENIO = NAMESPACE_CONVENIO + "fetchConvenioById";

	/** The Constant STMT_FETCH_CONVENIO_ALL. */
	private static final String STMT_FETCH_CONVENIO_ALL = NAMESPACE_CONVENIO + "fetchAllConvenios";

	/** The Constant STMT_FETCH_CONVENIO_COUNT. */
	private static final String STMT_FETCH_CONVENIO_COUNT = NAMESPACE_CONVENIO + "fetchConvenioRowCount";

	/** The Constant STMT_FETCH_CONVENIO_ALL_REQUEST. */
	private static final String STMT_FETCH_CONVENIO_ALL_REQUEST = NAMESPACE_CONVENIO + "fetchAllConveniosRequest";

///===================================### CIDADE ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CIDADE = "CidadeMap.";

/** The Constant STMT_INSERT_CIDADE. */
private static final String STMT_INSERT_CIDADE = NAMESPACE_CIDADE + "insertCidade";

/** The Constant STMT_UPDATE_CIDADE. */
private static final String STMT_UPDATE_CIDADE = NAMESPACE_CIDADE + "updateCidade";

/** The Constant STMT_DELETE_CIDADE. */
private static final String STMT_DELETE_CIDADE = NAMESPACE_CIDADE + "deleteCidadeById";

	/** The Constant STMT_DELETE_CIDADE_ALL. */
	private static final String STMT_DELETE_CIDADE_ALL = NAMESPACE_CIDADE + "deleteAllCidades";

	/** The Constant STMT_FETCH_CIDADE. */
	private static final String STMT_FETCH_CIDADE = NAMESPACE_CIDADE + "fetchCidadeById";

	/** The Constant STMT_FETCH_CIDADE_ALL. */
	private static final String STMT_FETCH_CIDADE_ALL = NAMESPACE_CIDADE + "fetchAllCidades";

	/** The Constant STMT_FETCH_CIDADE_COUNT. */
	private static final String STMT_FETCH_CIDADE_COUNT = NAMESPACE_CIDADE + "fetchCidadeRowCount";

	/** The Constant STMT_FETCH_CIDADE_ALL_REQUEST. */
	private static final String STMT_FETCH_CIDADE_ALL_REQUEST = NAMESPACE_CIDADE + "fetchAllCidadesRequest";

///===================================### ESTADO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_ESTADO = "EstadoMap.";

/** The Constant STMT_INSERT_ESTADO. */
private static final String STMT_INSERT_ESTADO = NAMESPACE_ESTADO + "insertEstado";

/** The Constant STMT_UPDATE_ESTADO. */
private static final String STMT_UPDATE_ESTADO = NAMESPACE_ESTADO + "updateEstado";

/** The Constant STMT_DELETE_ESTADO. */
private static final String STMT_DELETE_ESTADO = NAMESPACE_ESTADO + "deleteEstadoById";

	/** The Constant STMT_DELETE_ESTADO_ALL. */
	private static final String STMT_DELETE_ESTADO_ALL = NAMESPACE_ESTADO + "deleteAllEstados";

	/** The Constant STMT_FETCH_ESTADO. */
	private static final String STMT_FETCH_ESTADO = NAMESPACE_ESTADO + "fetchEstadoById";

	/** The Constant STMT_FETCH_ESTADO_ALL. */
	private static final String STMT_FETCH_ESTADO_ALL = NAMESPACE_ESTADO + "fetchAllEstados";

	/** The Constant STMT_FETCH_ESTADO_COUNT. */
	private static final String STMT_FETCH_ESTADO_COUNT = NAMESPACE_ESTADO + "fetchEstadoRowCount";

	/** The Constant STMT_FETCH_ESTADO_ALL_REQUEST. */
	private static final String STMT_FETCH_ESTADO_ALL_REQUEST = NAMESPACE_ESTADO + "fetchAllEstadosRequest";

///===================================### TAREFA ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_TAREFA = "TarefaMap.";

/** The Constant STMT_INSERT_TAREFA. */
private static final String STMT_INSERT_TAREFA = NAMESPACE_TAREFA + "insertTarefa";

/** The Constant STMT_UPDATE_TAREFA. */
private static final String STMT_UPDATE_TAREFA = NAMESPACE_TAREFA + "updateTarefa";

/** The Constant STMT_DELETE_TAREFA. */
private static final String STMT_DELETE_TAREFA = NAMESPACE_TAREFA + "deleteTarefaById";

	/** The Constant STMT_DELETE_TAREFA_ALL. */
	private static final String STMT_DELETE_TAREFA_ALL = NAMESPACE_TAREFA + "deleteAllTarefas";

	/** The Constant STMT_FETCH_TAREFA. */
	private static final String STMT_FETCH_TAREFA = NAMESPACE_TAREFA + "fetchTarefaById";

	/** The Constant STMT_FETCH_TAREFA_ALL. */
	private static final String STMT_FETCH_TAREFA_ALL = NAMESPACE_TAREFA + "fetchAllTarefas";

	/** The Constant STMT_FETCH_TAREFA_COUNT. */
	private static final String STMT_FETCH_TAREFA_COUNT = NAMESPACE_TAREFA + "fetchTarefaRowCount";

	/** The Constant STMT_FETCH_TAREFA_ALL_REQUEST. */
	private static final String STMT_FETCH_TAREFA_ALL_REQUEST = NAMESPACE_TAREFA + "fetchAllTarefasRequest";

//===================================### CLIENTE ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClienteBAR#insertCliente(com.qat.samples.sysmgmt.base.model.Cliente)
 */
@Override
public InternalResponse insertCliente(Cliente cliente)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CLIENTE, cliente, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClienteBAR#updateCliente(com.qat.samples.sysmgmt.base.model.Cliente)
 */
@Override
public InternalResponse updateCliente(Cliente cliente)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CLIENTE, cliente, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClienteBAR#deleteCliente(com.qat.samples.sysmgmt.base.model.Cliente)
 */
@Override
public InternalResponse deleteClienteById(Cliente cliente)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CLIENTE, cliente, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClienteBAR#deleteAllClientes()
 */
@Override
public InternalResponse deleteAllClientes()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CLIENTE_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IClienteBAR#fetchClienteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Cliente fetchClienteById(FetchByIdRequest request)
{
return (Cliente)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CLIENTE, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClienteBAR#fetchAllClientesCache()
 */
@Override
public InternalResultsResponse<Cliente> fetchAllClientes(Cliente cliente)
{
	InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CLIENTE_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IClienteBAR#fetchClientesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Cliente> fetchClientesByRequest(ClienteInquiryRequest request)
{
	InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();
	fetchClientesByRequest(getSqlSession(), request, STMT_FETCH_CLIENTE_COUNT, STMT_FETCH_CLIENTE_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchClientesByRequest ####======================================

public static void fetchClientesByRequest(SqlSession sqlSession, ClienteInquiryRequest request, String countStatement,
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


//===================================### FORNECEDOR ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFornecedorBAR#insertFornecedor(com.qat.samples.sysmgmt.base.model.Fornecedor)
 */
@Override
public InternalResponse insertFornecedor(Fornecedor fornecedor)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_FORNECEDOR, fornecedor, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFornecedorBAR#updateFornecedor(com.qat.samples.sysmgmt.base.model.Fornecedor)
 */
@Override
public InternalResponse updateFornecedor(Fornecedor fornecedor)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_FORNECEDOR, fornecedor, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFornecedorBAR#deleteFornecedor(com.qat.samples.sysmgmt.base.model.Fornecedor)
 */
@Override
public InternalResponse deleteFornecedorById(Fornecedor fornecedor)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_FORNECEDOR, fornecedor, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFornecedorBAR#deleteAllFornecedors()
 */
@Override
public InternalResponse deleteAllFornecedors()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_FORNECEDOR_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IFornecedorBAR#fetchFornecedorById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Fornecedor fetchFornecedorById(FetchByIdRequest request)
{
return (Fornecedor)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_FORNECEDOR, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFornecedorBAR#fetchAllFornecedorsCache()
 */
@Override
public InternalResultsResponse<Fornecedor> fetchAllFornecedors(Fornecedor fornecedor)
{
	InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_FORNECEDOR_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IFornecedorBAR#fetchFornecedorsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Fornecedor> fetchFornecedorsByRequest(FornecedorInquiryRequest request)
{
	InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();
	fetchFornecedorsByRequest(getSqlSession(), request, STMT_FETCH_FORNECEDOR_COUNT, STMT_FETCH_FORNECEDOR_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchFornecedorsByRequest ####======================================

public static void fetchFornecedorsByRequest(SqlSession sqlSession, FornecedorInquiryRequest request, String countStatement,
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


//===================================### TRANSPORTADOR ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITransportadorBAR#insertTransportador(com.qat.samples.sysmgmt.base.model.Transportador)
 */
@Override
public InternalResponse insertTransportador(Transportador transportador)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_TRANSPORTADOR, transportador, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITransportadorBAR#updateTransportador(com.qat.samples.sysmgmt.base.model.Transportador)
 */
@Override
public InternalResponse updateTransportador(Transportador transportador)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_TRANSPORTADOR, transportador, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITransportadorBAR#deleteTransportador(com.qat.samples.sysmgmt.base.model.Transportador)
 */
@Override
public InternalResponse deleteTransportadorById(Transportador transportador)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_TRANSPORTADOR, transportador, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITransportadorBAR#deleteAllTransportadors()
 */
@Override
public InternalResponse deleteAllTransportadors()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_TRANSPORTADOR_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ITransportadorBAR#fetchTransportadorById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Transportador fetchTransportadorById(FetchByIdRequest request)
{
return (Transportador)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_TRANSPORTADOR, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITransportadorBAR#fetchAllTransportadorsCache()
 */
@Override
public InternalResultsResponse<Transportador> fetchAllTransportadors(Transportador transportador)
{
	InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_TRANSPORTADOR_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ITransportadorBAR#fetchTransportadorsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Transportador> fetchTransportadorsByRequest(TransportadorInquiryRequest request)
{
	InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();
	fetchTransportadorsByRequest(getSqlSession(), request, STMT_FETCH_TRANSPORTADOR_COUNT, STMT_FETCH_TRANSPORTADOR_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchTransportadorsByRequest ####======================================

public static void fetchTransportadorsByRequest(SqlSession sqlSession, TransportadorInquiryRequest request, String countStatement,
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


//===================================### CONVENIO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IConvenioBAR#insertConvenio(com.qat.samples.sysmgmt.base.model.Convenio)
 */
@Override
public InternalResponse insertConvenio(Convenio convenio)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONVENIO, convenio, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IConvenioBAR#updateConvenio(com.qat.samples.sysmgmt.base.model.Convenio)
 */
@Override
public InternalResponse updateConvenio(Convenio convenio)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONVENIO, convenio, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IConvenioBAR#deleteConvenio(com.qat.samples.sysmgmt.base.model.Convenio)
 */
@Override
public InternalResponse deleteConvenioById(Convenio convenio)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONVENIO, convenio, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IConvenioBAR#deleteAllConvenios()
 */
@Override
public InternalResponse deleteAllConvenios()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONVENIO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IConvenioBAR#fetchConvenioById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Convenio fetchConvenioById(FetchByIdRequest request)
{
return (Convenio)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONVENIO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IConvenioBAR#fetchAllConveniosCache()
 */
@Override
public InternalResultsResponse<Convenio> fetchAllConvenios(Convenio convenio)
{
	InternalResultsResponse<Convenio> response = new InternalResultsResponse<Convenio>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONVENIO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IConvenioBAR#fetchConveniosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Convenio> fetchConveniosByRequest(ConvenioInquiryRequest request)
{
	InternalResultsResponse<Convenio> response = new InternalResultsResponse<Convenio>();
	fetchConveniosByRequest(getSqlSession(), request, STMT_FETCH_CONVENIO_COUNT, STMT_FETCH_CONVENIO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchConveniosByRequest ####======================================

public static void fetchConveniosByRequest(SqlSession sqlSession, ConvenioInquiryRequest request, String countStatement,
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


//===================================### CIDADE ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICidadeBAR#insertCidade(com.qat.samples.sysmgmt.base.model.Cidade)
 */
@Override
public InternalResponse insertCidade(Cidade cidade)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CIDADE, cidade, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICidadeBAR#updateCidade(com.qat.samples.sysmgmt.base.model.Cidade)
 */
@Override
public InternalResponse updateCidade(Cidade cidade)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CIDADE, cidade, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICidadeBAR#deleteCidade(com.qat.samples.sysmgmt.base.model.Cidade)
 */
@Override
public InternalResponse deleteCidadeById(Cidade cidade)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CIDADE, cidade, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICidadeBAR#deleteAllCidades()
 */
@Override
public InternalResponse deleteAllCidades()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CIDADE_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ICidadeBAR#fetchCidadeById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Cidade fetchCidadeById(FetchByIdRequest request)
{
return (Cidade)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CIDADE, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICidadeBAR#fetchAllCidadesCache()
 */
@Override
public InternalResultsResponse<Cidade> fetchAllCidades(Cidade cidade)
{
	InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CIDADE_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ICidadeBAR#fetchCidadesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Cidade> fetchCidadesByRequest(CidadeInquiryRequest request)
{
	InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();
	fetchCidadesByRequest(getSqlSession(), request, STMT_FETCH_CIDADE_COUNT, STMT_FETCH_CIDADE_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchCidadesByRequest ####======================================

public static void fetchCidadesByRequest(SqlSession sqlSession, CidadeInquiryRequest request, String countStatement,
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


//===================================### ESTADO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEstadoBAR#insertEstado(com.qat.samples.sysmgmt.base.model.Estado)
 */
@Override
public InternalResponse insertEstado(Estado estado)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ESTADO, estado, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEstadoBAR#updateEstado(com.qat.samples.sysmgmt.base.model.Estado)
 */
@Override
public InternalResponse updateEstado(Estado estado)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ESTADO, estado, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEstadoBAR#deleteEstado(com.qat.samples.sysmgmt.base.model.Estado)
 */
@Override
public InternalResponse deleteEstadoById(Estado estado)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ESTADO, estado, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEstadoBAR#deleteAllEstados()
 */
@Override
public InternalResponse deleteAllEstados()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ESTADO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IEstadoBAR#fetchEstadoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Estado fetchEstadoById(FetchByIdRequest request)
{
return (Estado)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ESTADO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEstadoBAR#fetchAllEstadosCache()
 */
@Override
public InternalResultsResponse<Estado> fetchAllEstados(Estado estado)
{
	InternalResultsResponse<Estado> response = new InternalResultsResponse<Estado>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ESTADO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IEstadoBAR#fetchEstadosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Estado> fetchEstadosByRequest(EstadoInquiryRequest request)
{
	InternalResultsResponse<Estado> response = new InternalResultsResponse<Estado>();
	fetchEstadosByRequest(getSqlSession(), request, STMT_FETCH_ESTADO_COUNT, STMT_FETCH_ESTADO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchEstadosByRequest ####======================================

public static void fetchEstadosByRequest(SqlSession sqlSession, EstadoInquiryRequest request, String countStatement,
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


//===================================### TAREFA ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITarefaBAR#insertTarefa(com.qat.samples.sysmgmt.base.model.Tarefa)
 */
@Override
public InternalResponse insertTarefa(Tarefa tarefa)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_TAREFA, tarefa, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITarefaBAR#updateTarefa(com.qat.samples.sysmgmt.base.model.Tarefa)
 */
@Override
public InternalResponse updateTarefa(Tarefa tarefa)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_TAREFA, tarefa, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITarefaBAR#deleteTarefa(com.qat.samples.sysmgmt.base.model.Tarefa)
 */
@Override
public InternalResponse deleteTarefaById(Tarefa tarefa)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_TAREFA, tarefa, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITarefaBAR#deleteAllTarefas()
 */
@Override
public InternalResponse deleteAllTarefas()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_TAREFA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ITarefaBAR#fetchTarefaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Tarefa fetchTarefaById(FetchByIdRequest request)
{
return (Tarefa)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_TAREFA, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITarefaBAR#fetchAllTarefasCache()
 */
@Override
public InternalResultsResponse<Tarefa> fetchAllTarefas(Tarefa tarefa)
{
	InternalResultsResponse<Tarefa> response = new InternalResultsResponse<Tarefa>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_TAREFA_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ITarefaBAR#fetchTarefasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Tarefa> fetchTarefasByRequest(TarefaInquiryRequest request)
{
	InternalResultsResponse<Tarefa> response = new InternalResultsResponse<Tarefa>();
	fetchTarefasByRequest(getSqlSession(), request, STMT_FETCH_TAREFA_COUNT, STMT_FETCH_TAREFA_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchTarefasByRequest ####======================================

public static void fetchTarefasByRequest(SqlSession sqlSession, TarefaInquiryRequest request, String countStatement,
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
