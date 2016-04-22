package com.qat.samples.sysmgmt.bar.mybatis.Produto;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Produto.IProdutoBAR;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class ProdutoBARImpl extends SqlSessionDaoSupport implements IProdutoBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### PRODUTO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_PRODUTO = "ProdutoMap.";

/** The Constant STMT_INSERT_PRODUTO. */
private static final String STMT_INSERT_PRODUTO = NAMESPACE_PRODUTO + "insertProduto";

/** The Constant STMT_UPDATE_PRODUTO. */
private static final String STMT_UPDATE_PRODUTO = NAMESPACE_PRODUTO + "updateProduto";

/** The Constant STMT_DELETE_PRODUTO. */
private static final String STMT_DELETE_PRODUTO = NAMESPACE_PRODUTO + "deleteProdutoById";

	/** The Constant STMT_DELETE_PRODUTO_ALL. */
	private static final String STMT_DELETE_PRODUTO_ALL = NAMESPACE_PRODUTO + "deleteAllProdutos";

	/** The Constant STMT_FETCH_PRODUTO. */
	private static final String STMT_FETCH_PRODUTO = NAMESPACE_PRODUTO + "fetchProdutoById";

	/** The Constant STMT_FETCH_PRODUTO_ALL. */
	private static final String STMT_FETCH_PRODUTO_ALL = NAMESPACE_PRODUTO + "fetchAllProdutos";

	/** The Constant STMT_FETCH_PRODUTO_COUNT. */
	private static final String STMT_FETCH_PRODUTO_COUNT = NAMESPACE_PRODUTO + "fetchProdutoRowCount";

	/** The Constant STMT_FETCH_PRODUTO_ALL_REQUEST. */
	private static final String STMT_FETCH_PRODUTO_ALL_REQUEST = NAMESPACE_PRODUTO + "fetchAllProdutosRequest";

///===================================### MARCA ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_MARCA = "MarcaMap.";

/** The Constant STMT_INSERT_MARCA. */
private static final String STMT_INSERT_MARCA = NAMESPACE_MARCA + "insertMarca";

/** The Constant STMT_UPDATE_MARCA. */
private static final String STMT_UPDATE_MARCA = NAMESPACE_MARCA + "updateMarca";

/** The Constant STMT_DELETE_MARCA. */
private static final String STMT_DELETE_MARCA = NAMESPACE_MARCA + "deleteMarcaById";

	/** The Constant STMT_DELETE_MARCA_ALL. */
	private static final String STMT_DELETE_MARCA_ALL = NAMESPACE_MARCA + "deleteAllMarcas";

	/** The Constant STMT_FETCH_MARCA. */
	private static final String STMT_FETCH_MARCA = NAMESPACE_MARCA + "fetchMarcaById";

	/** The Constant STMT_FETCH_MARCA_ALL. */
	private static final String STMT_FETCH_MARCA_ALL = NAMESPACE_MARCA + "fetchAllMarcas";

	/** The Constant STMT_FETCH_MARCA_COUNT. */
	private static final String STMT_FETCH_MARCA_COUNT = NAMESPACE_MARCA + "fetchMarcaRowCount";

	/** The Constant STMT_FETCH_MARCA_ALL_REQUEST. */
	private static final String STMT_FETCH_MARCA_ALL_REQUEST = NAMESPACE_MARCA + "fetchAllMarcasRequest";

///===================================### GRUPO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_GRUPO = "GrupoMap.";

/** The Constant STMT_INSERT_GRUPO. */
private static final String STMT_INSERT_GRUPO = NAMESPACE_GRUPO + "insertGrupo";

/** The Constant STMT_UPDATE_GRUPO. */
private static final String STMT_UPDATE_GRUPO = NAMESPACE_GRUPO + "updateGrupo";

/** The Constant STMT_DELETE_GRUPO. */
private static final String STMT_DELETE_GRUPO = NAMESPACE_GRUPO + "deleteGrupoById";

	/** The Constant STMT_DELETE_GRUPO_ALL. */
	private static final String STMT_DELETE_GRUPO_ALL = NAMESPACE_GRUPO + "deleteAllGrupos";

	/** The Constant STMT_FETCH_GRUPO. */
	private static final String STMT_FETCH_GRUPO = NAMESPACE_GRUPO + "fetchGrupoById";

	/** The Constant STMT_FETCH_GRUPO_ALL. */
	private static final String STMT_FETCH_GRUPO_ALL = NAMESPACE_GRUPO + "fetchAllGrupos";

	/** The Constant STMT_FETCH_GRUPO_COUNT. */
	private static final String STMT_FETCH_GRUPO_COUNT = NAMESPACE_GRUPO + "fetchGrupoRowCount";

	/** The Constant STMT_FETCH_GRUPO_ALL_REQUEST. */
	private static final String STMT_FETCH_GRUPO_ALL_REQUEST = NAMESPACE_GRUPO + "fetchAllGruposRequest";

///===================================### SUBGRUPO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_SUBGRUPO = "SubGrupoMap.";

/** The Constant STMT_INSERT_SUBGRUPO. */
private static final String STMT_INSERT_SUBGRUPO = NAMESPACE_SUBGRUPO + "insertSubGrupo";

/** The Constant STMT_UPDATE_SUBGRUPO. */
private static final String STMT_UPDATE_SUBGRUPO = NAMESPACE_SUBGRUPO + "updateSubGrupo";

/** The Constant STMT_DELETE_SUBGRUPO. */
private static final String STMT_DELETE_SUBGRUPO = NAMESPACE_SUBGRUPO + "deleteSubGrupoById";

	/** The Constant STMT_DELETE_SUBGRUPO_ALL. */
	private static final String STMT_DELETE_SUBGRUPO_ALL = NAMESPACE_SUBGRUPO + "deleteAllSubGrupos";

	/** The Constant STMT_FETCH_SUBGRUPO. */
	private static final String STMT_FETCH_SUBGRUPO = NAMESPACE_SUBGRUPO + "fetchSubGrupoById";

	/** The Constant STMT_FETCH_SUBGRUPO_ALL. */
	private static final String STMT_FETCH_SUBGRUPO_ALL = NAMESPACE_SUBGRUPO + "fetchAllSubGrupos";

	/** The Constant STMT_FETCH_SUBGRUPO_COUNT. */
	private static final String STMT_FETCH_SUBGRUPO_COUNT = NAMESPACE_SUBGRUPO + "fetchSubGrupoRowCount";

	/** The Constant STMT_FETCH_SUBGRUPO_ALL_REQUEST. */
	private static final String STMT_FETCH_SUBGRUPO_ALL_REQUEST = NAMESPACE_SUBGRUPO + "fetchAllSubGruposRequest";

///===================================### UNIMED ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_UNIMED = "UniMedMap.";

/** The Constant STMT_INSERT_UNIMED. */
private static final String STMT_INSERT_UNIMED = NAMESPACE_UNIMED + "insertUniMed";

/** The Constant STMT_UPDATE_UNIMED. */
private static final String STMT_UPDATE_UNIMED = NAMESPACE_UNIMED + "updateUniMed";

/** The Constant STMT_DELETE_UNIMED. */
private static final String STMT_DELETE_UNIMED = NAMESPACE_UNIMED + "deleteUniMedById";

	/** The Constant STMT_DELETE_UNIMED_ALL. */
	private static final String STMT_DELETE_UNIMED_ALL = NAMESPACE_UNIMED + "deleteAllUniMeds";

	/** The Constant STMT_FETCH_UNIMED. */
	private static final String STMT_FETCH_UNIMED = NAMESPACE_UNIMED + "fetchUniMedById";

	/** The Constant STMT_FETCH_UNIMED_ALL. */
	private static final String STMT_FETCH_UNIMED_ALL = NAMESPACE_UNIMED + "fetchAllUniMeds";

	/** The Constant STMT_FETCH_UNIMED_COUNT. */
	private static final String STMT_FETCH_UNIMED_COUNT = NAMESPACE_UNIMED + "fetchUniMedRowCount";

	/** The Constant STMT_FETCH_UNIMED_ALL_REQUEST. */
	private static final String STMT_FETCH_UNIMED_ALL_REQUEST = NAMESPACE_UNIMED + "fetchAllUniMedsRequest";

//===================================### PRODUTO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#insertProduto(com.qat.samples.sysmgmt.base.model.Produto)
 */
@Override
public InternalResponse insertProduto(Produto county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PRODUTO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#updateProduto(com.qat.samples.sysmgmt.base.model.Produto)
 */
@Override
public InternalResponse updateProduto(Produto county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PRODUTO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#deleteProduto(com.qat.samples.sysmgmt.base.model.Produto)
 */
@Override
public InternalResponse deleteProdutoById(Produto county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PRODUTO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#deleteAllProdutos()
 */
@Override
public InternalResponse deleteAllProdutos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PRODUTO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IProdutoBAR#fetchProdutoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request)
{
	InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
	response.addResult((Produto)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PRODUTO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#fetchAllProdutosCache()
 */
@Override
public InternalResultsResponse<Produto> fetchAllProdutos(Produto produto)
{
	InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PRODUTO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IProdutoBAR#fetchProdutosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Produto> fetchProdutosByRequest(ProdutoInquiryRequest request)
{
	InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
	fetchProdutosByRequest(getSqlSession(), request, STMT_FETCH_PRODUTO_COUNT, STMT_FETCH_PRODUTO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchProdutosByRequest ####======================================

public static void fetchProdutosByRequest(SqlSession sqlSession, ProdutoInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### MARCA ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMarcaBAR#insertMarca(com.qat.samples.sysmgmt.base.model.Marca)
 */
@Override
public InternalResponse insertMarca(Marca county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_MARCA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMarcaBAR#updateMarca(com.qat.samples.sysmgmt.base.model.Marca)
 */
@Override
public InternalResponse updateMarca(Marca county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_MARCA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMarcaBAR#deleteMarca(com.qat.samples.sysmgmt.base.model.Marca)
 */
@Override
public InternalResponse deleteMarcaById(Marca county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MARCA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMarcaBAR#deleteAllMarcas()
 */
@Override
public InternalResponse deleteAllMarcas()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MARCA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IMarcaBAR#fetchMarcaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Marca> fetchMarcaById(FetchByIdRequest request)
{
	InternalResultsResponse<Marca> response = new InternalResultsResponse<Marca>();
	response.addResult((Marca)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_MARCA,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMarcaBAR#fetchAllMarcasCache()
 */
@Override
public InternalResultsResponse<Marca> fetchAllMarcas(Marca marca)
{
	InternalResultsResponse<Marca> response = new InternalResultsResponse<Marca>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_MARCA_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IMarcaBAR#fetchMarcasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Marca> fetchMarcasByRequest(MarcaInquiryRequest request)
{
	InternalResultsResponse<Marca> response = new InternalResultsResponse<Marca>();
	fetchMarcasByRequest(getSqlSession(), request, STMT_FETCH_MARCA_COUNT, STMT_FETCH_MARCA_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchMarcasByRequest ####======================================

public static void fetchMarcasByRequest(SqlSession sqlSession, MarcaInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### GRUPO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IGrupoBAR#insertGrupo(com.qat.samples.sysmgmt.base.model.Grupo)
 */
@Override
public InternalResponse insertGrupo(Grupo county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_GRUPO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IGrupoBAR#updateGrupo(com.qat.samples.sysmgmt.base.model.Grupo)
 */
@Override
public InternalResponse updateGrupo(Grupo county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_GRUPO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IGrupoBAR#deleteGrupo(com.qat.samples.sysmgmt.base.model.Grupo)
 */
@Override
public InternalResponse deleteGrupoById(Grupo county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_GRUPO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IGrupoBAR#deleteAllGrupos()
 */
@Override
public InternalResponse deleteAllGrupos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_GRUPO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IGrupoBAR#fetchGrupoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Grupo> fetchGrupoById(FetchByIdRequest request)
{
	InternalResultsResponse<Grupo> response = new InternalResultsResponse<Grupo>();
	response.addResult((Grupo)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_GRUPO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IGrupoBAR#fetchAllGruposCache()
 */
@Override
public InternalResultsResponse<Grupo> fetchAllGrupos(Grupo grupo)
{
	InternalResultsResponse<Grupo> response = new InternalResultsResponse<Grupo>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_GRUPO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IGrupoBAR#fetchGruposByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Grupo> fetchGruposByRequest(GrupoInquiryRequest request)
{
	InternalResultsResponse<Grupo> response = new InternalResultsResponse<Grupo>();
	fetchGruposByRequest(getSqlSession(), request, STMT_FETCH_GRUPO_COUNT, STMT_FETCH_GRUPO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchGruposByRequest ####======================================

public static void fetchGruposByRequest(SqlSession sqlSession, GrupoInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### SUBGRUPO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#insertSubGrupo(com.qat.samples.sysmgmt.base.model.SubGrupo)
 */
@Override
public InternalResponse insertSubGrupo(SubGrupo county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_SUBGRUPO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#updateSubGrupo(com.qat.samples.sysmgmt.base.model.SubGrupo)
 */
@Override
public InternalResponse updateSubGrupo(SubGrupo county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_SUBGRUPO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#deleteSubGrupo(com.qat.samples.sysmgmt.base.model.SubGrupo)
 */
@Override
public InternalResponse deleteSubGrupoById(SubGrupo county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_SUBGRUPO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#deleteAllSubGrupos()
 */
@Override
public InternalResponse deleteAllSubGrupos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_SUBGRUPO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ISubGrupoBAR#fetchSubGrupoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<SubGrupo> fetchSubGrupoById(FetchByIdRequest request)
{
	InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();
	response.addResult((SubGrupo)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_SUBGRUPO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#fetchAllSubGruposCache()
 */
@Override
public InternalResultsResponse<SubGrupo> fetchAllSubGrupos(SubGrupo subgrupo)
{
	InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_SUBGRUPO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ISubGrupoBAR#fetchSubGruposByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<SubGrupo> fetchSubGruposByRequest(SubGrupoInquiryRequest request)
{
	InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();
	fetchSubGruposByRequest(getSqlSession(), request, STMT_FETCH_SUBGRUPO_COUNT, STMT_FETCH_SUBGRUPO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchSubGruposByRequest ####======================================

public static void fetchSubGruposByRequest(SqlSession sqlSession, SubGrupoInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### UNIMED ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IUniMedBAR#insertUniMed(com.qat.samples.sysmgmt.base.model.UniMed)
 */
@Override
public InternalResponse insertUniMed(UniMed county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_UNIMED, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IUniMedBAR#updateUniMed(com.qat.samples.sysmgmt.base.model.UniMed)
 */
@Override
public InternalResponse updateUniMed(UniMed county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_UNIMED, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IUniMedBAR#deleteUniMed(com.qat.samples.sysmgmt.base.model.UniMed)
 */
@Override
public InternalResponse deleteUniMedById(UniMed county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_UNIMED, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IUniMedBAR#deleteAllUniMeds()
 */
@Override
public InternalResponse deleteAllUniMeds()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_UNIMED_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IUniMedBAR#fetchUniMedById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<UniMed> fetchUniMedById(FetchByIdRequest request)
{
	InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();
	response.addResult((UniMed)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_UNIMED,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IUniMedBAR#fetchAllUniMedsCache()
 */
@Override
public InternalResultsResponse<UniMed> fetchAllUniMeds(UniMed unimed)
{
	InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_UNIMED_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IUniMedBAR#fetchUniMedsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<UniMed> fetchUniMedsByRequest(UniMedInquiryRequest request)
{
	InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();
	fetchUniMedsByRequest(getSqlSession(), request, STMT_FETCH_UNIMED_COUNT, STMT_FETCH_UNIMED_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchUniMedsByRequest ####======================================

public static void fetchUniMedsByRequest(SqlSession sqlSession, UniMedInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

}
