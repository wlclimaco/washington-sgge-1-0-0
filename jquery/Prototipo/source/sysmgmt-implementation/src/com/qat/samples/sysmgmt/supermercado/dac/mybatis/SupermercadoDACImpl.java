package com.qat.samples.sysmgmt.supermercado.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.supermercado.dac.ISupermercadoDAC;
import com.qat.samples.sysmgmt.supermercado.model.Supermercado;

/**
 * The Class SupermercadoDACImpl. (Data Access Component - DAC)
 */
public class SupermercadoDACImpl extends SqlSessionDaoSupport implements ISupermercadoDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "SupermercadoMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertSupermercado";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updateSupermercado";

	/** The Constant STMT_VERSION. */
	private static final String STMT_VERSION = NAMESPACE + "fetchVersionNumber";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE = NAMESPACE + "deleteSupermercadoById";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllSupermercados";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchSupermercadoById";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllSupermercados";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchSupermercadoRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllSupermercadosRequest";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ISupermercadoDAC#insertSupermercado(com.qat.samples.sysmgmt.base.model.Supermercado
	 * )
	 */
	@Override
	public InternalResponse insertSupermercado(Supermercado supermercado)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doInsert(getSqlSession(), STMT_INSERT, supermercado, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ISupermercadoDAC#updateSupermercado(com.qat.samples.sysmgmt.base.model.Supermercado
	 * )
	 */
	@Override
	public InternalResponse updateSupermercado(Supermercado supermercado)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdateOL(getSqlSession(), STMT_UPDATE, supermercado, STMT_VERSION, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ISupermercadoDAC#deleteSupermercado(com.qat.samples.sysmgmt.base.model.Supermercado
	 * )
	 */
	@Override
	public InternalResponse deleteSupermercado(Supermercado supermercado)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE, supermercado, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.ISupermercadoDAC#deleteAllSupermercados()
	 */
	@Override
	public InternalResponse deleteAllSupermercados()
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ISupermercadoDAC#fetchSupermercadoById
	 * (com.qat.samples.sysmgmt.base.model.Supermercado
	 * )
	 */
	@Override
	public Supermercado fetchSupermercadoById(FetchByIdRequest request)
	{
		return (Supermercado)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.ISupermercadoDAC#fetchAllSupermercados()
	 */
	@Override
	public List<Supermercado> fetchAllSupermercados()
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.dac.ISupermercadoDAC#fetchSupermercadosByRequest(com.qat.samples.sysmgmt.model.request.
	 * SupermercadoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Supermercado> fetchSupermercadosByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Supermercado> response = new InternalResultsResponse<Supermercado>();
		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
				response);
		return response;
	}
}
