package com.qat.samples.sysmgmt.produto.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.dac.IProdutoDAC;
import com.qat.samples.sysmgmt.produto.model.Produto;

/**
 * The Class ProdutoDACImpl. (Data Access Component - DAC)
 */
public class ProdutoDACImpl extends SqlSessionDaoSupport implements IProdutoDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "ProdutoMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertProduto";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updateProduto";

	/** The Constant STMT_VERSION. */
	private static final String STMT_VERSION = NAMESPACE + "fetchVersionNumber";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE = NAMESPACE + "deleteProdutoById";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllProdutos";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchProdutoById";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllProdutos";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchProdutoRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllProdutosRequest";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IProdutoDAC#insertProduto(com.qat.samples.sysmgmt.base.model.Produto)
	 */
	@Override
	public InternalResponse insertProduto(Produto produto)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doInsert(getSqlSession(), STMT_INSERT, produto, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IProdutoDAC#updateProduto(com.qat.samples.sysmgmt.base.model.Produto)
	 */
	@Override
	public InternalResponse updateProduto(Produto produto)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdateOL(getSqlSession(), STMT_UPDATE, produto, STMT_VERSION, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IProdutoDAC#deleteProduto(com.qat.samples.sysmgmt.base.model.Produto)
	 */
	@Override
	public InternalResponse deleteProduto(Produto produto)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE, produto, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IProdutoDAC#deleteAllProdutos()
	 */
	@Override
	public InternalResponse deleteAllProdutos()
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IProdutoDAC#fetchProdutoById
	 * (com.qat.samples.sysmgmt.base.model.Produto
	 * )
	 */
	@Override
	public Produto fetchProdutoById(FetchByIdRequest request)
	{
		return (Produto)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IProdutoDAC#fetchAllProdutos()
	 */
	@Override
	public List<Produto> fetchAllProdutos()
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dac.IProdutoDAC#fetchProdutosByRequest(com.qat.samples.sysmgmt.model.request.
	 * ProdutoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> fetchProdutosByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
				response);
		return response;
	}
}
