package com.qat.samples.sysmgmt.endereco.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.endereco.dac.IEnderecoDAC;
import com.qat.samples.sysmgmt.endereco.model.Endereco;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Class EnderecoDACImpl. (Data Access Component - DAC)
 */
public class EnderecoDACImpl extends SqlSessionDaoSupport implements IEnderecoDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "EnderecoMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertEndereco";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updateEndereco";

	/** The Constant STMT_VERSION. */
	private static final String STMT_VERSION = NAMESPACE + "fetchVersionNumber";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE = NAMESPACE + "deleteEnderecoById";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllEnderecos";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchEnderecoById";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllEnderecos";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchEnderecoRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllEnderecosRequest";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IEnderecoDAC#insertEndereco(com.qat.samples.sysmgmt.base.model.Endereco)
	 */
	@Override
	public InternalResponse insertEndereco(Endereco endereco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doInsert(getSqlSession(), STMT_INSERT, endereco, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IEnderecoDAC#updateEndereco(com.qat.samples.sysmgmt.base.model.Endereco)
	 */
	@Override
	public InternalResponse updateEndereco(Endereco endereco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdateOL(getSqlSession(), STMT_UPDATE, endereco, STMT_VERSION, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IEnderecoDAC#deleteEndereco(com.qat.samples.sysmgmt.base.model.Endereco)
	 */
	@Override
	public InternalResponse deleteEndereco(Endereco endereco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE, endereco, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IEnderecoDAC#deleteAllEnderecos()
	 */
	@Override
	public InternalResponse deleteAllEnderecos()
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IEnderecoDAC#fetchEnderecoById
	 * (com.qat.samples.sysmgmt.base.model.Endereco
	 * )
	 */
	@Override
	public Endereco fetchEnderecoById(FetchByIdRequest request)
	{
		return (Endereco)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IEnderecoDAC#fetchAllEnderecos()
	 */
	@Override
	public List<Endereco> fetchAllEnderecos()
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dac.IEnderecoDAC#fetchEnderecosByRequest(com.qat.samples.sysmgmt.model.request.
	 * EnderecoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Endereco> fetchEnderecosByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();
		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
				response);
		return response;
	}
}
