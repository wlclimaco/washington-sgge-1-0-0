package com.qat.samples.sysmgmt.cidade.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.cidade.dac.ICidadeDAC;
import com.qat.samples.sysmgmt.cidade.model.Cidade;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Class CidadeDACImpl. (Data Access Component - DAC)
 */
public class CidadeDACImpl extends SqlSessionDaoSupport implements ICidadeDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "CidadeMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertCidade";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updateCidade";

	/** The Constant STMT_VERSION. */
	private static final String STMT_VERSION = NAMESPACE + "fetchVersionNumber";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE = NAMESPACE + "deleteCidadeById";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllCidades";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchCidadeById";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllCidades";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchCidadeRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllCidadesRequest";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ICidadeDAC#insertCidade(com.qat.samples.sysmgmt.base.model.Cidade)
	 */
	@Override
	public InternalResponse insertCidade(Cidade cidade)
	{

		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doInsert(getSqlSession(), STMT_INSERT, cidade, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ICidadeDAC#updateCidade(com.qat.samples.sysmgmt.base.model.Cidade)
	 */
	@Override
	public InternalResponse updateCidade(Cidade cidade)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdateOL(getSqlSession(), STMT_UPDATE, cidade, STMT_VERSION, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ICidadeDAC#deleteCidade(com.qat.samples.sysmgmt.base.model.Cidade)
	 */
	@Override
	public InternalResponse deleteCidade(Cidade cidade)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE, cidade, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.ICidadeDAC#deleteAllCidades()
	 */
	@Override
	public InternalResponse deleteAllCidades()
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ICidadeDAC#fetchCidadeById
	 * (com.qat.samples.sysmgmt.base.model.Cidade
	 * )
	 */
	@Override
	public Cidade fetchCidadeById(FetchByIdRequest request)
	{
		return (Cidade)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.ICidadeDAC#fetchAllCidades()
	 */
	@Override
	public List<Cidade> fetchAllCidades()
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dac.ICidadeDAC#fetchCidadesByRequest(com.qat.samples.sysmgmt.model.request.
	 * CidadeInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Cidade> fetchCidadesByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();
		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
				response);
		return response;
	}
}
