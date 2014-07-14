package com.qat.samples.sysmgmt.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dac.IPrecoDAC;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Tabelapreco;

/**
 * The Class PrecoDACImpl. (Data Access Component - DAC)
 */
public class PrecoDACImpl extends SqlSessionDaoSupport implements IPrecoDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "PrecoMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertPreco";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updatePreco";

	/** The Constant STMT_VERSION. */
	private static final String STMT_VERSION = NAMESPACE + "fetchVersionNumber";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE = NAMESPACE + "deletePrecoById";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllPrecos";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchPrecoById";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllPrecos";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchPrecoRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllPrecosRequest";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IPrecoDAC#insertPreco(com.qat.samples.sysmgmt.base.model.Preco)
	 */
	@Override
	public InternalResponse insertPreco(Tabelapreco preco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doInsert(getSqlSession(), STMT_INSERT, preco, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IPrecoDAC#updatePreco(com.qat.samples.sysmgmt.base.model.Preco)
	 */
	@Override
	public InternalResponse updatePreco(Tabelapreco preco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdateOL(getSqlSession(), STMT_UPDATE, preco, STMT_VERSION, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IPrecoDAC#deletePreco(com.qat.samples.sysmgmt.base.model.Preco)
	 */
	@Override
	public InternalResponse deletePreco(Tabelapreco preco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE, preco, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IPrecoDAC#deleteAllPrecos()
	 */
	@Override
	public InternalResponse deleteAllPrecos()
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IPrecoDAC#fetchPrecoById
	 * (com.qat.samples.sysmgmt.base.model.Preco
	 * )
	 */
	@Override
	public Tabelapreco fetchPrecoById(FetchByIdRequest request)
	{
		return (Tabelapreco)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IPrecoDAC#fetchAllPrecos()
	 */
	@Override
	public List<Tabelapreco> fetchAllPrecos()
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dac.IPrecoDAC#fetchPrecosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PrecoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Tabelapreco> fetchPrecosByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Tabelapreco> response = new InternalResultsResponse<Tabelapreco>();
		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
				response);
		return response;
	}
}
