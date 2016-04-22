package com.qat.samples.sysmgmt.bar.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.IProcedureBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.PagedResultsBARD;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProcedureBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class ProcedureBARImpl extends SqlSessionDaoSupport implements IProcedureBAR
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
	 * com.qat.samples.sysmgmt.base.bar.IProcedureBAR#insertProcedure(com.qat.samples.sysmgmt.base.model.Procedure)
	 */
	@Override
	public InternalResponse insertProcedure(Procedure procedure)
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT, procedure, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IProcedureBAR#updateProcedure(com.qat.samples.sysmgmt.base.model.Procedure)
	 */
	@Override
	public InternalResponse updateProcedure(Procedure procedure)
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdateOL(getSqlSession(), STMT_UPDATE, procedure, STMT_VERSION, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IProcedureBAR#deleteProcedure(com.qat.samples.sysmgmt.base.model.Procedure)
	 */
	@Override
	public InternalResponse deleteProcedure(Procedure procedure)
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE, procedure, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IProcedureBAR#deleteAllProcedures()
	 */
	@Override
	public InternalResponse deleteAllProcedures()
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IProcedureBAR#fetchProcedureById
	 * (com.qat.samples.sysmgmt.base.model.Procedure
	 * )
	 */
	@Override
	public Procedure fetchProcedureById(FetchByIdRequest request)
	{
		return (Procedure)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IProcedureBAR#fetchAllProcedures()
	 */
	@Override
	public List<Procedure> fetchAllProcedures()
	{
		return MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bar.IProcedureBAR#fetchProceduresByRequest(com.qat.samples.sysmgmt.model.request.
	 * ProcedureInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Procedure> fetchProceduresByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Procedure> response = new InternalResultsResponse<Procedure>();
		PagedResultsBARD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
				response);
		return response;
	}
}
