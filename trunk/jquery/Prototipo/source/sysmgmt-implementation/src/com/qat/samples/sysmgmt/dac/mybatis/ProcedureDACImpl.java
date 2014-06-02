package com.qat.samples.sysmgmt.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dac.IProcedureDAC;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Class ProcedureDACImpl. (Data Access Component - DAC)
 */
public class ProcedureDACImpl extends SqlSessionDaoSupport implements IProcedureDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "ProcedureMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertProcedure";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updateProcedure";

	/** The Constant STMT_VERSION. */
	private static final String STMT_VERSION = NAMESPACE + "fetchVersionNumber";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE = NAMESPACE + "deleteProcedureById";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllProcedures";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchProcedureById";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllProcedures";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchProcedureRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllProceduresRequest";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IProcedureDAC#insertProcedure(com.qat.samples.sysmgmt.base.model.Procedure)
	 */
	@Override
	public InternalResponse insertProcedure(Procedure procedure)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doInsert(getSqlSession(), STMT_INSERT, procedure, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IProcedureDAC#updateProcedure(com.qat.samples.sysmgmt.base.model.Procedure)
	 */
	@Override
	public InternalResponse updateProcedure(Procedure procedure)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdateOL(getSqlSession(), STMT_UPDATE, procedure, STMT_VERSION, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IProcedureDAC#deleteProcedure(com.qat.samples.sysmgmt.base.model.Procedure)
	 */
	@Override
	public InternalResponse deleteProcedure(Procedure procedure)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE, procedure, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IProcedureDAC#deleteAllProcedures()
	 */
	@Override
	public InternalResponse deleteAllProcedures()
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IProcedureDAC#fetchProcedureById
	 * (com.qat.samples.sysmgmt.base.model.Procedure
	 * )
	 */
	@Override
	public Procedure fetchProcedureById(FetchByIdRequest request)
	{
		return (Procedure)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IProcedureDAC#fetchAllProcedures()
	 */
	@Override
	public List<Procedure> fetchAllProcedures()
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dac.IProcedureDAC#fetchProceduresByRequest(com.qat.samples.sysmgmt.model.request.ProcedureInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Procedure> fetchProceduresByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Procedure> response = new InternalResultsResponse<Procedure>();
		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST, response);
		return response;
	}
}
