package com.qat.samples.sysmgmt.listaCompras.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.listaCompras.dac.IListaComprasDAC;
import com.qat.samples.sysmgmt.listaCompras.model.ListaCompras;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Class ListaComprasDACImpl. (Data Access Component - DAC)
 */
public class ListaComprasDACImpl extends SqlSessionDaoSupport implements IListaComprasDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "ListaComprasMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertListaCompras";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updateListaCompras";

	/** The Constant STMT_VERSION. */
	private static final String STMT_VERSION = NAMESPACE + "fetchVersionNumber";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE = NAMESPACE + "deleteListaComprasById";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllListaComprass";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchListaComprasById";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllListaComprass";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchListaComprasRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllListaComprassRequest";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IListaComprasDAC#insertListaCompras(com.qat.samples.sysmgmt.base.model.ListaCompras
	 * )
	 */
	@Override
	public InternalResponse insertListaCompras(ListaCompras cidade)
	{

		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doInsert(getSqlSession(), STMT_INSERT, cidade, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IListaComprasDAC#updateListaCompras(com.qat.samples.sysmgmt.base.model.ListaCompras
	 * )
	 */
	@Override
	public InternalResponse updateListaCompras(ListaCompras cidade)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdateOL(getSqlSession(), STMT_UPDATE, cidade, STMT_VERSION, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IListaComprasDAC#deleteListaCompras(com.qat.samples.sysmgmt.base.model.ListaCompras
	 * )
	 */
	@Override
	public InternalResponse deleteListaCompras(ListaCompras cidade)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE, cidade, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IListaComprasDAC#deleteAllListaComprass()
	 */
	@Override
	public InternalResponse deleteAllListaComprass()
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IListaComprasDAC#fetchListaComprasById
	 * (com.qat.samples.sysmgmt.base.model.ListaCompras
	 * )
	 */
	@Override
	public ListaCompras fetchListaComprasById(FetchByIdRequest request)
	{
		return (ListaCompras)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IListaComprasDAC#fetchAllListaComprass()
	 */
	@Override
	public List<ListaCompras> fetchAllListaComprass()
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.dac.IListaComprasDAC#fetchListaComprassByRequest(com.qat.samples.sysmgmt.model.request.
	 * ListaComprasInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ListaCompras> fetchListaComprassByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<ListaCompras> response = new InternalResultsResponse<ListaCompras>();
		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
				response);
		return response;
	}

	// ====================

	@Override
	public InternalResponse insertListaComprasItens(ListaCompras listaCompras)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse updateListaComprasItens(ListaCompras listaCompras)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteListaComprasItens(ListaCompras listaCompras)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteAllListaComprasItenss()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ListaCompras> fetchAllListaComprasItenss()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaCompras fetchListaComprasItensById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<ListaCompras> fetchListaComprasItenssByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
