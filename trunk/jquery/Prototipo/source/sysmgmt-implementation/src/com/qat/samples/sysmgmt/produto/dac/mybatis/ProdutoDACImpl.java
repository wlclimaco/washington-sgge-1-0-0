package com.qat.samples.sysmgmt.produto.dac.mybatis;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.response.InternalResponseLocal;
import com.qat.samples.sysmgmt.produto.dac.IProdutoDAC;
import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.Tabelapreco;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;

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

	// ============================================================================

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT_CADASTRO = NAMESPACE + "insertCadastro";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE_CADASTRO = NAMESPACE + "updateCadastro";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE_CADASTRO = NAMESPACE + "deleteCadastroById";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL_CADASTRO = NAMESPACE + "deleteAllCadastros";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH_CADASTRO = NAMESPACE + "fetchCadastroById";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL_CADASTRO = NAMESPACE + "fetchAllCadastros";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT_CADASTRO = NAMESPACE + "fetchCadastroRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST_CADASTRO = NAMESPACE + "fetchAllCadastrosRequest";

	// =============================================================================

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IProdutoDAC#insertProduto(com.qat.samples.sysmgmt.base.model.Produto)
	 */
	@Override
	public InternalResponseLocal insertProduto(Produto produto)
	{

		InternalResponseLocal response = new InternalResponseLocal();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(14);
		paramMap.put("produtoid", produto.getId());
		paramMap.put("codbarra", produto.getCodBarra());
		paramMap.put("nome", produto.getNome());
		paramMap.put("marca", produto.getMarca().getId());
		paramMap.put("menu", produto.getMenu().getId());
		paramMap.put("unimed", produto.getUnimed().getId());
		paramMap.put("supermercadoId", 0);
		paramMap.put("foto", produto.getFoto());
		paramMap.put("usuarioid", produto.getUserId());

		response.setId(
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_INSERT, paramMap));
		response.setStatus(Status.OperationSuccess);
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
		HashMap<String, Object> paramMap = new HashMap<String, Object>(14);
		paramMap.put("produtoid", produto.getId());
		paramMap.put("codbarra", produto.getCodBarra());
		paramMap.put("nome", produto.getNome());
		paramMap.put("marca", produto.getMarca().getId());
		paramMap.put("menu", produto.getMenu().getId());
		paramMap.put("unimed", produto.getUnimed().getId());
		paramMap.put("precoid", 1);
		paramMap.put("controleid", 1);
		paramMap.put("foto", produto.getFoto());
		paramMap.put("preco", new Double(2.2));
		paramMap.put("usuarioid", produto.getUserId());

		InternalResponse response = new InternalResponse();

		Integer academiaId =
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_UPDATE, paramMap);
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
		Produto produto = new Produto();
		produto = (Produto)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, request.getFetchId());
		return produto;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.IProdutoDAC#fetchAllProdutos()
	 */
	@Override
	public List<Produto> fetchAllProdutos(ProdutoInquiryRequest request)
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL, request);

	}

	@Override
	public List<Tabelapreco> fetchAllProdutosPreco(ProdutoInquiryRequest request)
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), "PrecoMap.fetchAllPrecosSup", request
				.getProduto().getPrecos().get(0).getSupermercadoid().getSuperId());
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

	// ===============================

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IProdutoDAC#insertProduto(com.qat.samples.sysmgmt.base.model.Produto)
	 */
	@Override
	public InternalResponse insertCadastro(Cadastro cadastro)
	{
		InternalResponse response = new InternalResponse();
		Integer academiaId =
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_INSERT_CADASTRO, cadastro);

		response.setStatus(Status.OperationSuccess);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ICadastroDAC#updateCadastro(com.qat.samples.sysmgmt.base.model.Cadastro)
	 */
	@Override
	public InternalResponse updateCadastro(Cadastro cadastro)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdateOL(getSqlSession(), STMT_UPDATE_CADASTRO, cadastro, STMT_VERSION, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ICadastroDAC#deleteCadastro(com.qat.samples.sysmgmt.base.model.Cadastro)
	 */
	@Override
	public InternalResponse deleteCadastro(Cadastro cadastro)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_CADASTRO, cadastro, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.dac.ICadastroDAC#deleteAllCadastros()
	 */
	@Override
	public InternalResponse deleteAllCadastros(Cadastro cadastro)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), STMT_DELETE_ALL_CADASTRO, cadastro, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.ICadastroDAC#fetchCadastroById
	 * (com.qat.samples.sysmgmt.base.model.Cadastro
	 * )
	 */
	@Override
	public Cadastro fetchCadastroById(Cadastro request)
	{
		return (Cadastro)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CADASTRO,
				request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dac.ICadastroDAC#fetchCadastrosByRequest(com.qat.samples.sysmgmt.model.request.
	 * CadastroInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Cadastro> fetchCadastrosByRequest(CadastroInquiryRequest request)
	{
		InternalResultsResponse<Cadastro> response = new InternalResultsResponse<Cadastro>();
		PagedResultsDACD.fetchObjectsByRequestCadastro(getSqlSession(), request, STMT_FETCH_COUNT_CADASTRO,
				STMT_FETCH_ALL_REQUEST_CADASTRO,
				response);
		return response;
	}

	@Override
	public List<Cadastro> fetchAllCadastros(CadastroInquiryRequest cadastro)
	{
		if (cadastro.getCadastro().getTableTypeEnumValue() == 1)
		{
			return QATMyBatisDacHelper
					.doQueryForList(getSqlSession(), "fetchAllCadastrosTotal", cadastro.getCadastro());
		}
		else
		{
			return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL_CADASTRO, cadastro.getCadastro());
		}

	}
}
