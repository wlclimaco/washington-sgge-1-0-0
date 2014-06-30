package com.qat.samples.sysmgmt.cliente.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.cliente.dac.IClienteDAC;
import com.qat.samples.sysmgmt.cliente.model.Cliente;
import com.qat.samples.sysmgmt.dac.IDocumentoDAC;
import com.qat.samples.sysmgmt.dac.IEnderecoDAC;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.response.InternalResponseLocal;

/**
 * The Class ClienteDACImpl. (Data Access Component - DAC)
 */
public class ClienteDACImpl extends SqlSessionDaoSupport implements IClienteDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "ClienteMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertCliente";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updateCliente";

	/** The Constant STMT_VERSION. */
	private static final String STMT_VERSION = NAMESPACE + "fetchVersionNumber";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE = NAMESPACE + "deleteClienteById";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllClientes";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchClienteById";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllClientes";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchClienteRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllClientesRequest";

	private IEnderecoDAC enderecoDac;

	private IDocumentoDAC documentoDac;

	public IDocumentoDAC getDocumentoDac()
	{
		return documentoDac;
	}

	public void setDocumentoDac(IDocumentoDAC documentoDac)
	{
		this.documentoDac = documentoDac;
	}

	public IEnderecoDAC getEnderecoDac()
	{
		return enderecoDac;
	}

	public void setEnderecoDac(IEnderecoDAC enderecoDac)
	{
		this.enderecoDac = enderecoDac;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IClienteDAC#insertCliente(com.qat.samples.sysmgmt.base.model.Cliente)
	 */
	@Override
	public InternalResponseLocal insertCliente(Cliente cliente)
	{
		InternalResponseLocal response = new InternalResponseLocal();
		Integer academiaId =
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_INSERT, cliente);
		response.setId(academiaId);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IClienteDAC#updateCliente(com.qat.samples.sysmgmt.base.model.Cliente)
	 */
	@Override
	public InternalResponseLocal updateCliente(Cliente cliente)
	{

		InternalResponseLocal response = new InternalResponseLocal();
		Integer academiaId =
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_UPDATE, cliente);
		response.setId(academiaId);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IClienteDAC#deleteCliente(com.qat.samples.sysmgmt.base.model.Cliente)
	 */
	@Override
	public InternalResponse deleteCliente(Cliente cliente)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE, cliente, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IClienteDAC#deleteAllClientes()
	 */
	@Override
	public InternalResponse deleteAllClientes()
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IClienteDAC#fetchClienteById
	 * (com.qat.samples.sysmgmt.base.model.Cliente
	 * )
	 */
	@Override
	public Cliente fetchClienteById(FetchByIdRequest request)
	{
		return (Cliente)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IClienteDAC#fetchAllClientes()
	 */
	@Override
	public List<Cliente> fetchAllClientes()
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dac.IClienteDAC#fetchClientesByRequest(com.qat.samples.sysmgmt.model.request.
	 * ClienteInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Cliente> fetchClientesByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();
		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
				response);
		return response;
	}
}
