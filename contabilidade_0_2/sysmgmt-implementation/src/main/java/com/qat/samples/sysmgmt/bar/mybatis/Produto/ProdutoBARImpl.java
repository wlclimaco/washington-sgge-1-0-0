/** create by system gera-java version 1.0.0 13/05/2016 17:59 : 56*/
package com.qat.samples.sysmgmt.bar.mybatis.Produto;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Produto.IProdutoBAR;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.Tributacao;
import com.qat.samples.sysmgmt.produto.model.Custo;
import com.qat.samples.sysmgmt.produto.model.CustoItens;
import com.qat.samples.sysmgmt.produto.model.Estoque;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.MarcaProduto;
import com.qat.samples.sysmgmt.produto.model.Porcao;
import com.qat.samples.sysmgmt.produto.model.PorcaoItens;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.ProdutoParent;
import com.qat.samples.sysmgmt.produto.model.Rentabilidade;
import com.qat.samples.sysmgmt.produto.model.RentabilidadeItens;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoParentInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class ProdutoBARImpl extends SqlSessionDaoSupport implements IProdutoBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### PRODUTOPARENT ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_PRODUTOPARENT = "ProdutoParentMap.";

/** The Constant STMT_INSERT_PRODUTOPARENT. */
private static final String STMT_INSERT_PRODUTOPARENT = NAMESPACE_PRODUTOPARENT + "insertProdutoParent";

/** The Constant STMT_UPDATE_PRODUTOPARENT. */
private static final String STMT_UPDATE_PRODUTOPARENT = NAMESPACE_PRODUTOPARENT + "updateProdutoParent";

/** The Constant STMT_DELETE_PRODUTOPARENT. */
private static final String STMT_DELETE_PRODUTOPARENT = NAMESPACE_PRODUTOPARENT + "deleteProdutoParentById";

	/** The Constant STMT_DELETE_PRODUTOPARENT_ALL. */
	private static final String STMT_DELETE_PRODUTOPARENT_ALL = NAMESPACE_PRODUTOPARENT + "deleteAllProdutoParents";

	/** The Constant STMT_FETCH_PRODUTOPARENT. */
	private static final String STMT_FETCH_PRODUTOPARENT = NAMESPACE_PRODUTOPARENT + "fetchProdutoParentById";

	/** The Constant STMT_FETCH_PRODUTOPARENT_ALL. */
	private static final String STMT_FETCH_PRODUTOPARENT_ALL = NAMESPACE_PRODUTOPARENT + "fetchAllProdutoParents";

	/** The Constant STMT_FETCH_PRODUTOPARENT_COUNT. */
	private static final String STMT_FETCH_PRODUTOPARENT_COUNT = NAMESPACE_PRODUTOPARENT + "fetchProdutoParentRowCount";

	/** The Constant STMT_FETCH_PRODUTOPARENT_ALL_REQUEST. */
	private static final String STMT_FETCH_PRODUTOPARENT_ALL_REQUEST = NAMESPACE_PRODUTOPARENT + "fetchAllProdutoParentsRequest";

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

///===================================### CFOP ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CFOP = "CfopMap.";

/** The Constant STMT_INSERT_CFOP. */
private static final String STMT_INSERT_CFOP = NAMESPACE_CFOP + "insertCfop";

/** The Constant STMT_UPDATE_CFOP. */
private static final String STMT_UPDATE_CFOP = NAMESPACE_CFOP + "updateCfop";

/** The Constant STMT_DELETE_CFOP. */
private static final String STMT_DELETE_CFOP = NAMESPACE_CFOP + "deleteCfopById";

	/** The Constant STMT_DELETE_CFOP_ALL. */
	private static final String STMT_DELETE_CFOP_ALL = NAMESPACE_CFOP + "deleteAllCfops";

	/** The Constant STMT_FETCH_CFOP. */
	private static final String STMT_FETCH_CFOP = NAMESPACE_CFOP + "fetchCfopById";

	/** The Constant STMT_FETCH_CFOP_ALL. */
	private static final String STMT_FETCH_CFOP_ALL = NAMESPACE_CFOP + "fetchAllCfops";

	/** The Constant STMT_FETCH_CFOP_COUNT. */
	private static final String STMT_FETCH_CFOP_COUNT = NAMESPACE_CFOP + "fetchCfopRowCount";

	/** The Constant STMT_FETCH_CFOP_ALL_REQUEST. */
	private static final String STMT_FETCH_CFOP_ALL_REQUEST = NAMESPACE_CFOP + "fetchAllCfopsRequest";

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

///===================================### MARCAPRODUTO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_MARCAPRODUTO = "MarcaProdutoMap.";

/** The Constant STMT_INSERT_MARCAPRODUTO. */
private static final String STMT_INSERT_MARCAPRODUTO = NAMESPACE_MARCAPRODUTO + "insertMarcaProduto";

/** The Constant STMT_UPDATE_MARCAPRODUTO. */
private static final String STMT_UPDATE_MARCAPRODUTO = NAMESPACE_MARCAPRODUTO + "updateMarcaProduto";

/** The Constant STMT_DELETE_MARCAPRODUTO. */
private static final String STMT_DELETE_MARCAPRODUTO = NAMESPACE_MARCAPRODUTO + "deleteMarcaProdutoById";

	/** The Constant STMT_DELETE_MARCAPRODUTO_ALL. */
	private static final String STMT_DELETE_MARCAPRODUTO_ALL = NAMESPACE_MARCAPRODUTO + "deleteAllMarcaProdutos";

	/** The Constant STMT_FETCH_MARCAPRODUTO. */
	private static final String STMT_FETCH_MARCAPRODUTO = NAMESPACE_MARCAPRODUTO + "fetchMarcaProdutoById";

	/** The Constant STMT_FETCH_MARCAPRODUTO_ALL. */
	private static final String STMT_FETCH_MARCAPRODUTO_ALL = NAMESPACE_MARCAPRODUTO + "fetchAllMarcaProdutos";

	/** The Constant STMT_FETCH_MARCAPRODUTO_COUNT. */
	private static final String STMT_FETCH_MARCAPRODUTO_COUNT = NAMESPACE_MARCAPRODUTO + "fetchMarcaProdutoRowCount";

	/** The Constant STMT_FETCH_MARCAPRODUTO_ALL_REQUEST. */
	private static final String STMT_FETCH_MARCAPRODUTO_ALL_REQUEST = NAMESPACE_MARCAPRODUTO + "fetchAllMarcaProdutosRequest";

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

///===================================### SubGrupo ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_SubGrupo = "SubGrupoMap.";

/** The Constant STMT_INSERT_SubGrupo. */
private static final String STMT_INSERT_SubGrupo = NAMESPACE_SubGrupo + "insertSubGrupo";

/** The Constant STMT_UPDATE_SubGrupo. */
private static final String STMT_UPDATE_SubGrupo = NAMESPACE_SubGrupo + "updateSubGrupo";

/** The Constant STMT_DELETE_SubGrupo. */
private static final String STMT_DELETE_SubGrupo = NAMESPACE_SubGrupo + "deleteSubGrupoById";

	/** The Constant STMT_DELETE_SubGrupo_ALL. */
	private static final String STMT_DELETE_SubGrupo_ALL = NAMESPACE_SubGrupo + "deleteAllSubGrupos";

	/** The Constant STMT_FETCH_SubGrupo. */
	private static final String STMT_FETCH_SubGrupo = NAMESPACE_SubGrupo + "fetchSubGrupoById";

	/** The Constant STMT_FETCH_SubGrupo_ALL. */
	private static final String STMT_FETCH_SubGrupo_ALL = NAMESPACE_SubGrupo + "fetchAllSubGrupos";

	/** The Constant STMT_FETCH_SubGrupo_COUNT. */
	private static final String STMT_FETCH_SubGrupo_COUNT = NAMESPACE_SubGrupo + "fetchSubGrupoRowCount";

	/** The Constant STMT_FETCH_SubGrupo_ALL_REQUEST. */
	private static final String STMT_FETCH_SubGrupo_ALL_REQUEST = NAMESPACE_SubGrupo + "fetchAllSubGruposRequest";

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

///===================================### TRIBUTACAO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_TRIBUTACAO = "TributacaoMap.";

/** The Constant STMT_INSERT_TRIBUTACAO. */
private static final String STMT_INSERT_TRIBUTACAO = NAMESPACE_TRIBUTACAO + "insertTributacao";

/** The Constant STMT_UPDATE_TRIBUTACAO. */
private static final String STMT_UPDATE_TRIBUTACAO = NAMESPACE_TRIBUTACAO + "updateTributacao";

/** The Constant STMT_DELETE_TRIBUTACAO. */
private static final String STMT_DELETE_TRIBUTACAO = NAMESPACE_TRIBUTACAO + "deleteTributacaoById";

	/** The Constant STMT_DELETE_TRIBUTACAO_ALL. */
	private static final String STMT_DELETE_TRIBUTACAO_ALL = NAMESPACE_TRIBUTACAO + "deleteAllTributacaos";

	/** The Constant STMT_FETCH_TRIBUTACAO. */
	private static final String STMT_FETCH_TRIBUTACAO = NAMESPACE_TRIBUTACAO + "fetchTributacaoById";

	/** The Constant STMT_FETCH_TRIBUTACAO_ALL. */
	private static final String STMT_FETCH_TRIBUTACAO_ALL = NAMESPACE_TRIBUTACAO + "fetchAllTributacaos";

	/** The Constant STMT_FETCH_TRIBUTACAO_COUNT. */
	private static final String STMT_FETCH_TRIBUTACAO_COUNT = NAMESPACE_TRIBUTACAO + "fetchTributacaoRowCount";

	/** The Constant STMT_FETCH_TRIBUTACAO_ALL_REQUEST. */
	private static final String STMT_FETCH_TRIBUTACAO_ALL_REQUEST = NAMESPACE_TRIBUTACAO + "fetchAllTributacaosRequest";

///===================================### CUSTO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CUSTO = "CustoMap.";

/** The Constant STMT_INSERT_CUSTO. */
private static final String STMT_INSERT_CUSTO = NAMESPACE_CUSTO + "insertCusto";

/** The Constant STMT_UPDATE_CUSTO. */
private static final String STMT_UPDATE_CUSTO = NAMESPACE_CUSTO + "updateCusto";

/** The Constant STMT_DELETE_CUSTO. */
private static final String STMT_DELETE_CUSTO = NAMESPACE_CUSTO + "deleteCustoById";

	/** The Constant STMT_DELETE_CUSTO_ALL. */
	private static final String STMT_DELETE_CUSTO_ALL = NAMESPACE_CUSTO + "deleteAllCustos";

	/** The Constant STMT_FETCH_CUSTO. */
	private static final String STMT_FETCH_CUSTO = NAMESPACE_CUSTO + "fetchCustoById";

	/** The Constant STMT_FETCH_CUSTO_ALL. */
	private static final String STMT_FETCH_CUSTO_ALL = NAMESPACE_CUSTO + "fetchAllCustos";

	/** The Constant STMT_FETCH_CUSTO_COUNT. */
	private static final String STMT_FETCH_CUSTO_COUNT = NAMESPACE_CUSTO + "fetchCustoRowCount";

	/** The Constant STMT_FETCH_CUSTO_ALL_REQUEST. */
	private static final String STMT_FETCH_CUSTO_ALL_REQUEST = NAMESPACE_CUSTO + "fetchAllCustosRequest";

///===================================### CUSTOITENS ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CUSTOITENS = "CustoItensMap.";

/** The Constant STMT_INSERT_CUSTOITENS. */
private static final String STMT_INSERT_CUSTOITENS = NAMESPACE_CUSTOITENS + "insertCustoItens";

/** The Constant STMT_UPDATE_CUSTOITENS. */
private static final String STMT_UPDATE_CUSTOITENS = NAMESPACE_CUSTOITENS + "updateCustoItens";

/** The Constant STMT_DELETE_CUSTOITENS. */
private static final String STMT_DELETE_CUSTOITENS = NAMESPACE_CUSTOITENS + "deleteCustoItensById";

	/** The Constant STMT_DELETE_CUSTOITENS_ALL. */
	private static final String STMT_DELETE_CUSTOITENS_ALL = NAMESPACE_CUSTOITENS + "deleteAllCustoItenss";

	/** The Constant STMT_FETCH_CUSTOITENS. */
	private static final String STMT_FETCH_CUSTOITENS = NAMESPACE_CUSTOITENS + "fetchCustoItensById";

	/** The Constant STMT_FETCH_CUSTOITENS_ALL. */
	private static final String STMT_FETCH_CUSTOITENS_ALL = NAMESPACE_CUSTOITENS + "fetchAllCustoItenss";

	/** The Constant STMT_FETCH_CUSTOITENS_COUNT. */
	private static final String STMT_FETCH_CUSTOITENS_COUNT = NAMESPACE_CUSTOITENS + "fetchCustoItensRowCount";

	/** The Constant STMT_FETCH_CUSTOITENS_ALL_REQUEST. */
	private static final String STMT_FETCH_CUSTOITENS_ALL_REQUEST = NAMESPACE_CUSTOITENS + "fetchAllCustoItenssRequest";

///===================================### ESTOQUE ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_ESTOQUE = "EstoqueMap.";

/** The Constant STMT_INSERT_ESTOQUE. */
private static final String STMT_INSERT_ESTOQUE = NAMESPACE_ESTOQUE + "insertEstoque";

/** The Constant STMT_UPDATE_ESTOQUE. */
private static final String STMT_UPDATE_ESTOQUE = NAMESPACE_ESTOQUE + "updateEstoque";

/** The Constant STMT_DELETE_ESTOQUE. */
private static final String STMT_DELETE_ESTOQUE = NAMESPACE_ESTOQUE + "deleteEstoqueById";

	/** The Constant STMT_DELETE_ESTOQUE_ALL. */
	private static final String STMT_DELETE_ESTOQUE_ALL = NAMESPACE_ESTOQUE + "deleteAllEstoques";

	/** The Constant STMT_FETCH_ESTOQUE. */
	private static final String STMT_FETCH_ESTOQUE = NAMESPACE_ESTOQUE + "fetchEstoqueById";

	/** The Constant STMT_FETCH_ESTOQUE_ALL. */
	private static final String STMT_FETCH_ESTOQUE_ALL = NAMESPACE_ESTOQUE + "fetchAllEstoques";

	/** The Constant STMT_FETCH_ESTOQUE_COUNT. */
	private static final String STMT_FETCH_ESTOQUE_COUNT = NAMESPACE_ESTOQUE + "fetchEstoqueRowCount";

	/** The Constant STMT_FETCH_ESTOQUE_ALL_REQUEST. */
	private static final String STMT_FETCH_ESTOQUE_ALL_REQUEST = NAMESPACE_ESTOQUE + "fetchAllEstoquesRequest";

///===================================### PORCAO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_PORCAO = "PorcaoMap.";

/** The Constant STMT_INSERT_PORCAO. */
private static final String STMT_INSERT_PORCAO = NAMESPACE_PORCAO + "insertPorcao";

/** The Constant STMT_UPDATE_PORCAO. */
private static final String STMT_UPDATE_PORCAO = NAMESPACE_PORCAO + "updatePorcao";

/** The Constant STMT_DELETE_PORCAO. */
private static final String STMT_DELETE_PORCAO = NAMESPACE_PORCAO + "deletePorcaoById";

	/** The Constant STMT_DELETE_PORCAO_ALL. */
	private static final String STMT_DELETE_PORCAO_ALL = NAMESPACE_PORCAO + "deleteAllPorcaos";

	/** The Constant STMT_FETCH_PORCAO. */
	private static final String STMT_FETCH_PORCAO = NAMESPACE_PORCAO + "fetchPorcaoById";

	/** The Constant STMT_FETCH_PORCAO_ALL. */
	private static final String STMT_FETCH_PORCAO_ALL = NAMESPACE_PORCAO + "fetchAllPorcaos";

	/** The Constant STMT_FETCH_PORCAO_COUNT. */
	private static final String STMT_FETCH_PORCAO_COUNT = NAMESPACE_PORCAO + "fetchPorcaoRowCount";

	/** The Constant STMT_FETCH_PORCAO_ALL_REQUEST. */
	private static final String STMT_FETCH_PORCAO_ALL_REQUEST = NAMESPACE_PORCAO + "fetchAllPorcaosRequest";

///===================================### PorcaoItens ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_PorcaoItens = "PorcaoItensMap.";

/** The Constant STMT_INSERT_PorcaoItens. */
private static final String STMT_INSERT_PorcaoItens = NAMESPACE_PorcaoItens + "insertPorcaoItens";

/** The Constant STMT_UPDATE_PorcaoItens. */
private static final String STMT_UPDATE_PorcaoItens = NAMESPACE_PorcaoItens + "updatePorcaoItens";

/** The Constant STMT_DELETE_PorcaoItens. */
private static final String STMT_DELETE_PorcaoItens = NAMESPACE_PorcaoItens + "deletePorcaoItensById";

	/** The Constant STMT_DELETE_PorcaoItens_ALL. */
	private static final String STMT_DELETE_PorcaoItens_ALL = NAMESPACE_PorcaoItens + "deleteAllPorcaoItenss";

	/** The Constant STMT_FETCH_PorcaoItens. */
	private static final String STMT_FETCH_PorcaoItens = NAMESPACE_PorcaoItens + "fetchPorcaoItensById";

	/** The Constant STMT_FETCH_PorcaoItens_ALL. */
	private static final String STMT_FETCH_PorcaoItens_ALL = NAMESPACE_PorcaoItens + "fetchAllPorcaoItenss";

	/** The Constant STMT_FETCH_PorcaoItens_COUNT. */
	private static final String STMT_FETCH_PorcaoItens_COUNT = NAMESPACE_PorcaoItens + "fetchPorcaoItensRowCount";

	/** The Constant STMT_FETCH_PorcaoItens_ALL_REQUEST. */
	private static final String STMT_FETCH_PorcaoItens_ALL_REQUEST = NAMESPACE_PorcaoItens + "fetchAllPorcaoItenssRequest";

///===================================### RENTABILIDADE ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_RENTABILIDADE = "RentabilidadeMap.";

/** The Constant STMT_INSERT_RENTABILIDADE. */
private static final String STMT_INSERT_RENTABILIDADE = NAMESPACE_RENTABILIDADE + "insertRentabilidade";

/** The Constant STMT_UPDATE_RENTABILIDADE. */
private static final String STMT_UPDATE_RENTABILIDADE = NAMESPACE_RENTABILIDADE + "updateRentabilidade";

/** The Constant STMT_DELETE_RENTABILIDADE. */
private static final String STMT_DELETE_RENTABILIDADE = NAMESPACE_RENTABILIDADE + "deleteRentabilidadeById";

	/** The Constant STMT_DELETE_RENTABILIDADE_ALL. */
	private static final String STMT_DELETE_RENTABILIDADE_ALL = NAMESPACE_RENTABILIDADE + "deleteAllRentabilidades";

	/** The Constant STMT_FETCH_RENTABILIDADE. */
	private static final String STMT_FETCH_RENTABILIDADE = NAMESPACE_RENTABILIDADE + "fetchRentabilidadeById";

	/** The Constant STMT_FETCH_RENTABILIDADE_ALL. */
	private static final String STMT_FETCH_RENTABILIDADE_ALL = NAMESPACE_RENTABILIDADE + "fetchAllRentabilidades";

	/** The Constant STMT_FETCH_RENTABILIDADE_COUNT. */
	private static final String STMT_FETCH_RENTABILIDADE_COUNT = NAMESPACE_RENTABILIDADE + "fetchRentabilidadeRowCount";

	/** The Constant STMT_FETCH_RENTABILIDADE_ALL_REQUEST. */
	private static final String STMT_FETCH_RENTABILIDADE_ALL_REQUEST = NAMESPACE_RENTABILIDADE + "fetchAllRentabilidadesRequest";

///===================================### RENTABILIDADEITENS ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_RENTABILIDADEITENS = "RentabilidadeItensMap.";

/** The Constant STMT_INSERT_RENTABILIDADEITENS. */
private static final String STMT_INSERT_RENTABILIDADEITENS = NAMESPACE_RENTABILIDADEITENS + "insertRentabilidadeItens";

/** The Constant STMT_UPDATE_RENTABILIDADEITENS. */
private static final String STMT_UPDATE_RENTABILIDADEITENS = NAMESPACE_RENTABILIDADEITENS + "updateRentabilidadeItens";

/** The Constant STMT_DELETE_RENTABILIDADEITENS. */
private static final String STMT_DELETE_RENTABILIDADEITENS = NAMESPACE_RENTABILIDADEITENS + "deleteRentabilidadeItensById";

	/** The Constant STMT_DELETE_RENTABILIDADEITENS_ALL. */
	private static final String STMT_DELETE_RENTABILIDADEITENS_ALL = NAMESPACE_RENTABILIDADEITENS + "deleteAllRentabilidadeItenss";

	/** The Constant STMT_FETCH_RENTABILIDADEITENS. */
	private static final String STMT_FETCH_RENTABILIDADEITENS = NAMESPACE_RENTABILIDADEITENS + "fetchRentabilidadeItensById";

	/** The Constant STMT_FETCH_RENTABILIDADEITENS_ALL. */
	private static final String STMT_FETCH_RENTABILIDADEITENS_ALL = NAMESPACE_RENTABILIDADEITENS + "fetchAllRentabilidadeItenss";

	/** The Constant STMT_FETCH_RENTABILIDADEITENS_COUNT. */
	private static final String STMT_FETCH_RENTABILIDADEITENS_COUNT = NAMESPACE_RENTABILIDADEITENS + "fetchRentabilidadeItensRowCount";

	/** The Constant STMT_FETCH_RENTABILIDADEITENS_ALL_REQUEST. */
	private static final String STMT_FETCH_RENTABILIDADEITENS_ALL_REQUEST = NAMESPACE_RENTABILIDADEITENS + "fetchAllRentabilidadeItenssRequest";

//===================================### PRODUTOPARENT ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoParentBAR#insertProdutoParent(com.qat.samples.sysmgmt.base.model.ProdutoParent)
 */
@Override
public InternalResponse insertProdutoParent(ProdutoParent produtoparent)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PRODUTOPARENT, produtoparent, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoParentBAR#updateProdutoParent(com.qat.samples.sysmgmt.base.model.ProdutoParent)
 */
@Override
public InternalResponse updateProdutoParent(ProdutoParent produtoparent)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PRODUTOPARENT, produtoparent, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoParentBAR#deleteProdutoParent(com.qat.samples.sysmgmt.base.model.ProdutoParent)
 */
@Override
public InternalResponse deleteProdutoParentById(ProdutoParent produtoparent)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PRODUTOPARENT, produtoparent, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoParentBAR#deleteAllProdutoParents()
 */
@Override
public InternalResponse deleteAllProdutoParents()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PRODUTOPARENT_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IProdutoParentBAR#fetchProdutoParentById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public ProdutoParent fetchProdutoParentById(FetchByIdRequest request)
{
return (ProdutoParent)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PRODUTOPARENT, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoParentBAR#fetchAllProdutoParentsCache()
 */
@Override
public InternalResultsResponse<ProdutoParent> fetchAllProdutoParents(ProdutoParent produtoparent)
{
	InternalResultsResponse<ProdutoParent> response = new InternalResultsResponse<ProdutoParent>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PRODUTOPARENT_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IProdutoParentBAR#fetchProdutoParentsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ProdutoParent> fetchProdutoParentsByRequest(ProdutoParentInquiryRequest request)
{
	InternalResultsResponse<ProdutoParent> response = new InternalResultsResponse<ProdutoParent>();
	fetchProdutoParentsByRequest(getSqlSession(), request, STMT_FETCH_PRODUTOPARENT_COUNT, STMT_FETCH_PRODUTOPARENT_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchProdutoParentsByRequest ####======================================

public static void fetchProdutoParentsByRequest(SqlSession sqlSession, ProdutoParentInquiryRequest request, String countStatement,
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


//===================================### PRODUTO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#insertProduto(com.qat.samples.sysmgmt.base.model.Produto)
 */
@Override
public InternalResponse insertProduto(Produto produto)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PRODUTO, produto, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#updateProduto(com.qat.samples.sysmgmt.base.model.Produto)
 */
@Override
public InternalResponse updateProduto(Produto produto)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PRODUTO, produto, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#deleteProduto(com.qat.samples.sysmgmt.base.model.Produto)
 */
@Override
public InternalResponse deleteProdutoById(Produto produto)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PRODUTO, produto, response);
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
public Produto fetchProdutoById(FetchByIdRequest request)
{
return (Produto)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PRODUTO, request.getFetchId());

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


//===================================### CFOP ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#insertCfop(com.qat.samples.sysmgmt.base.model.Cfop)
 */
@Override
public InternalResponse insertCfop(Cfop cfop)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CFOP, cfop, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#updateCfop(com.qat.samples.sysmgmt.base.model.Cfop)
 */
@Override
public InternalResponse updateCfop(Cfop cfop)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CFOP, cfop, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#deleteCfop(com.qat.samples.sysmgmt.base.model.Cfop)
 */
@Override
public InternalResponse deleteCfopById(Cfop cfop)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CFOP, cfop, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#deleteAllCfops()
 */
@Override
public InternalResponse deleteAllCfops()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CFOP_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ICfopBAR#fetchCfopById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Cfop fetchCfopById(FetchByIdRequest request)
{
return (Cfop)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CFOP, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#fetchAllCfopsCache()
 */
@Override
public InternalResultsResponse<Cfop> fetchAllCfops(Cfop cfop)
{
	InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CFOP_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ICfopBAR#fetchCfopsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Cfop> fetchCfopsByRequest(CfopInquiryRequest request)
{
	InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();
	fetchCfopsByRequest(getSqlSession(), request, STMT_FETCH_CFOP_COUNT, STMT_FETCH_CFOP_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchCfopsByRequest ####======================================

public static void fetchCfopsByRequest(SqlSession sqlSession, CfopInquiryRequest request, String countStatement,
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
public InternalResponse insertMarca(Marca marca)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_MARCA, marca, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMarcaBAR#updateMarca(com.qat.samples.sysmgmt.base.model.Marca)
 */
@Override
public InternalResponse updateMarca(Marca marca)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_MARCA, marca, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMarcaBAR#deleteMarca(com.qat.samples.sysmgmt.base.model.Marca)
 */
@Override
public InternalResponse deleteMarcaById(Marca marca)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MARCA, marca, response);
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
public Marca fetchMarcaById(FetchByIdRequest request)
{
return (Marca)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_MARCA, request.getFetchId());

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


//===================================### MARCAPRODUTO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMarcaProdutoBAR#insertMarcaProduto(com.qat.samples.sysmgmt.base.model.MarcaProduto)
 */
@Override
public InternalResponse insertMarcaProduto(MarcaProduto marcaproduto)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_MARCAPRODUTO, marcaproduto, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMarcaProdutoBAR#updateMarcaProduto(com.qat.samples.sysmgmt.base.model.MarcaProduto)
 */
@Override
public InternalResponse updateMarcaProduto(MarcaProduto marcaproduto)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_MARCAPRODUTO, marcaproduto, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMarcaProdutoBAR#deleteMarcaProduto(com.qat.samples.sysmgmt.base.model.MarcaProduto)
 */
@Override
public InternalResponse deleteMarcaProdutoById(MarcaProduto marcaproduto)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MARCAPRODUTO, marcaproduto, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMarcaProdutoBAR#deleteAllMarcaProdutos()
 */
@Override
public InternalResponse deleteAllMarcaProdutos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MARCAPRODUTO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IMarcaProdutoBAR#fetchMarcaProdutoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public MarcaProduto fetchMarcaProdutoById(FetchByIdRequest request)
{
return (MarcaProduto)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_MARCAPRODUTO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMarcaProdutoBAR#fetchAllMarcaProdutosCache()
 */
@Override
public InternalResultsResponse<MarcaProduto> fetchAllMarcaProdutos(MarcaProduto marcaproduto)
{
	InternalResultsResponse<MarcaProduto> response = new InternalResultsResponse<MarcaProduto>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_MARCAPRODUTO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IMarcaProdutoBAR#fetchMarcaProdutosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<MarcaProduto> fetchMarcaProdutosByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<MarcaProduto> response = new InternalResultsResponse<MarcaProduto>();
	fetchMarcaProdutosByRequest(getSqlSession(), request, STMT_FETCH_MARCAPRODUTO_COUNT, STMT_FETCH_MARCAPRODUTO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchMarcaProdutosByRequest ####======================================

public static void fetchMarcaProdutosByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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
public InternalResponse insertGrupo(Grupo grupo)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_GRUPO, grupo, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IGrupoBAR#updateGrupo(com.qat.samples.sysmgmt.base.model.Grupo)
 */
@Override
public InternalResponse updateGrupo(Grupo grupo)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_GRUPO, grupo, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IGrupoBAR#deleteGrupo(com.qat.samples.sysmgmt.base.model.Grupo)
 */
@Override
public InternalResponse deleteGrupoById(Grupo grupo)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_GRUPO, grupo, response);
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
public Grupo fetchGrupoById(FetchByIdRequest request)
{
return (Grupo)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_GRUPO, request.getFetchId());

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


//===================================### SubGrupo ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#insertSubGrupo(com.qat.samples.sysmgmt.base.model.SubGrupo)
 */
@Override
public InternalResponse insertSubGrupo(SubGrupo SubGrupo)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_SubGrupo, SubGrupo, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#updateSubGrupo(com.qat.samples.sysmgmt.base.model.SubGrupo)
 */
@Override
public InternalResponse updateSubGrupo(SubGrupo SubGrupo)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_SubGrupo, SubGrupo, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#deleteSubGrupo(com.qat.samples.sysmgmt.base.model.SubGrupo)
 */
@Override
public InternalResponse deleteSubGrupoById(SubGrupo SubGrupo)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_SubGrupo, SubGrupo, response);
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
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_SubGrupo_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ISubGrupoBAR#fetchSubGrupoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public SubGrupo fetchSubGrupoById(FetchByIdRequest request)
{
return (SubGrupo)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_SubGrupo, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#fetchAllSubGruposCache()
 */
@Override
public InternalResultsResponse<SubGrupo> fetchAllSubGrupos(SubGrupo SubGrupo)
{
	InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_SubGrupo_ALL));
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
	fetchSubGruposByRequest(getSqlSession(), request, STMT_FETCH_SubGrupo_COUNT, STMT_FETCH_SubGrupo_ALL_REQUEST,
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
public InternalResponse insertUniMed(UniMed unimed)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_UNIMED, unimed, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IUniMedBAR#updateUniMed(com.qat.samples.sysmgmt.base.model.UniMed)
 */
@Override
public InternalResponse updateUniMed(UniMed unimed)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_UNIMED, unimed, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IUniMedBAR#deleteUniMed(com.qat.samples.sysmgmt.base.model.UniMed)
 */
@Override
public InternalResponse deleteUniMedById(UniMed unimed)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_UNIMED, unimed, response);
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
public UniMed fetchUniMedById(FetchByIdRequest request)
{
return (UniMed)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_UNIMED, request.getFetchId());

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


//===================================### TRIBUTACAO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#insertTributacao(com.qat.samples.sysmgmt.base.model.Tributacao)
 */
@Override
public InternalResponse insertTributacao(Tributacao tributacao)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_TRIBUTACAO, tributacao, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#updateTributacao(com.qat.samples.sysmgmt.base.model.Tributacao)
 */
@Override
public InternalResponse updateTributacao(Tributacao tributacao)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_TRIBUTACAO, tributacao, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#deleteTributacao(com.qat.samples.sysmgmt.base.model.Tributacao)
 */
@Override
public InternalResponse deleteTributacaoById(Tributacao tributacao)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_TRIBUTACAO, tributacao, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#deleteAllTributacaos()
 */
@Override
public InternalResponse deleteAllTributacaos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_TRIBUTACAO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ITributacaoBAR#fetchTributacaoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Tributacao fetchTributacaoById(FetchByIdRequest request)
{
return (Tributacao)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_TRIBUTACAO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#fetchAllTributacaosCache()
 */
@Override
public InternalResultsResponse<Tributacao> fetchAllTributacaos(Tributacao tributacao)
{
	InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_TRIBUTACAO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ITributacaoBAR#fetchTributacaosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Tributacao> fetchTributacaosByRequest(TributacaoInquiryRequest request)
{
	InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();
	fetchTributacaosByRequest(getSqlSession(), request, STMT_FETCH_TRIBUTACAO_COUNT, STMT_FETCH_TRIBUTACAO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchTributacaosByRequest ####======================================

public static void fetchTributacaosByRequest(SqlSession sqlSession, TributacaoInquiryRequest request, String countStatement,
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


//===================================### CUSTO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICustoBAR#insertCusto(com.qat.samples.sysmgmt.base.model.Custo)
 */
@Override
public InternalResponse insertCusto(Custo custo)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CUSTO, custo, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICustoBAR#updateCusto(com.qat.samples.sysmgmt.base.model.Custo)
 */
@Override
public InternalResponse updateCusto(Custo custo)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CUSTO, custo, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICustoBAR#deleteCusto(com.qat.samples.sysmgmt.base.model.Custo)
 */
@Override
public InternalResponse deleteCustoById(Custo custo)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CUSTO, custo, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICustoBAR#deleteAllCustos()
 */
@Override
public InternalResponse deleteAllCustos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CUSTO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ICustoBAR#fetchCustoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Custo fetchCustoById(FetchByIdRequest request)
{
return (Custo)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CUSTO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICustoBAR#fetchAllCustosCache()
 */
@Override
public InternalResultsResponse<Custo> fetchAllCustos(Custo custo)
{
	InternalResultsResponse<Custo> response = new InternalResultsResponse<Custo>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CUSTO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ICustoBAR#fetchCustosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Custo> fetchCustosByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<Custo> response = new InternalResultsResponse<Custo>();
	fetchCustosByRequest(getSqlSession(), request, STMT_FETCH_CUSTO_COUNT, STMT_FETCH_CUSTO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchCustosByRequest ####======================================

public static void fetchCustosByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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


//===================================### CUSTOITENS ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICustoItensBAR#insertCustoItens(com.qat.samples.sysmgmt.base.model.CustoItens)
 */
@Override
public InternalResponse insertCustoItens(CustoItens custoitens)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CUSTOITENS, custoitens, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICustoItensBAR#updateCustoItens(com.qat.samples.sysmgmt.base.model.CustoItens)
 */
@Override
public InternalResponse updateCustoItens(CustoItens custoitens)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CUSTOITENS, custoitens, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICustoItensBAR#deleteCustoItens(com.qat.samples.sysmgmt.base.model.CustoItens)
 */
@Override
public InternalResponse deleteCustoItensById(CustoItens custoitens)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CUSTOITENS, custoitens, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICustoItensBAR#deleteAllCustoItenss()
 */
@Override
public InternalResponse deleteAllCustoItenss()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CUSTOITENS_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ICustoItensBAR#fetchCustoItensById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public CustoItens fetchCustoItensById(FetchByIdRequest request)
{
return (CustoItens)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CUSTOITENS, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ICustoItensBAR#fetchAllCustoItenssCache()
 */
@Override
public InternalResultsResponse<CustoItens> fetchAllCustoItenss(CustoItens custoitens)
{
	InternalResultsResponse<CustoItens> response = new InternalResultsResponse<CustoItens>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CUSTOITENS_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ICustoItensBAR#fetchCustoItenssByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<CustoItens> fetchCustoItenssByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<CustoItens> response = new InternalResultsResponse<CustoItens>();
	fetchCustoItenssByRequest(getSqlSession(), request, STMT_FETCH_CUSTOITENS_COUNT, STMT_FETCH_CUSTOITENS_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchCustoItenssByRequest ####======================================

public static void fetchCustoItenssByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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


//===================================### ESTOQUE ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEstoqueBAR#insertEstoque(com.qat.samples.sysmgmt.base.model.Estoque)
 */
@Override
public InternalResponse insertEstoque(Estoque estoque)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ESTOQUE, estoque, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEstoqueBAR#updateEstoque(com.qat.samples.sysmgmt.base.model.Estoque)
 */
@Override
public InternalResponse updateEstoque(Estoque estoque)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ESTOQUE, estoque, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEstoqueBAR#deleteEstoque(com.qat.samples.sysmgmt.base.model.Estoque)
 */
@Override
public InternalResponse deleteEstoqueById(Estoque estoque)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ESTOQUE, estoque, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEstoqueBAR#deleteAllEstoques()
 */
@Override
public InternalResponse deleteAllEstoques()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ESTOQUE_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IEstoqueBAR#fetchEstoqueById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Estoque fetchEstoqueById(FetchByIdRequest request)
{
return (Estoque)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ESTOQUE, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEstoqueBAR#fetchAllEstoquesCache()
 */
@Override
public InternalResultsResponse<Estoque> fetchAllEstoques(Estoque estoque)
{
	InternalResultsResponse<Estoque> response = new InternalResultsResponse<Estoque>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ESTOQUE_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IEstoqueBAR#fetchEstoquesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Estoque> fetchEstoquesByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<Estoque> response = new InternalResultsResponse<Estoque>();
	fetchEstoquesByRequest(getSqlSession(), request, STMT_FETCH_ESTOQUE_COUNT, STMT_FETCH_ESTOQUE_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchEstoquesByRequest ####======================================

public static void fetchEstoquesByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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


//===================================### PORCAO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoBAR#insertPorcao(com.qat.samples.sysmgmt.base.model.Porcao)
 */
@Override
public InternalResponse insertPorcao(Porcao porcao)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PORCAO, porcao, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoBAR#updatePorcao(com.qat.samples.sysmgmt.base.model.Porcao)
 */
@Override
public InternalResponse updatePorcao(Porcao porcao)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PORCAO, porcao, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoBAR#deletePorcao(com.qat.samples.sysmgmt.base.model.Porcao)
 */
@Override
public InternalResponse deletePorcaoById(Porcao porcao)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PORCAO, porcao, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoBAR#deleteAllPorcaos()
 */
@Override
public InternalResponse deleteAllPorcaos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PORCAO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IPorcaoBAR#fetchPorcaoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Porcao fetchPorcaoById(FetchByIdRequest request)
{
return (Porcao)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PORCAO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoBAR#fetchAllPorcaosCache()
 */
@Override
public InternalResultsResponse<Porcao> fetchAllPorcaos(Porcao porcao)
{
	InternalResultsResponse<Porcao> response = new InternalResultsResponse<Porcao>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PORCAO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IPorcaoBAR#fetchPorcaosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Porcao> fetchPorcaosByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<Porcao> response = new InternalResultsResponse<Porcao>();
	fetchPorcaosByRequest(getSqlSession(), request, STMT_FETCH_PORCAO_COUNT, STMT_FETCH_PORCAO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchPorcaosByRequest ####======================================

public static void fetchPorcaosByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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


//===================================### PorcaoItens ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoItensBAR#insertPorcaoItens(com.qat.samples.sysmgmt.base.model.PorcaoItens)
 */
@Override
public InternalResponse insertPorcaoItens(PorcaoItens PorcaoItens)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PorcaoItens, PorcaoItens, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoItensBAR#updatePorcaoItens(com.qat.samples.sysmgmt.base.model.PorcaoItens)
 */
@Override
public InternalResponse updatePorcaoItens(PorcaoItens PorcaoItens)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PorcaoItens, PorcaoItens, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoItensBAR#deletePorcaoItens(com.qat.samples.sysmgmt.base.model.PorcaoItens)
 */
@Override
public InternalResponse deletePorcaoItensById(PorcaoItens PorcaoItens)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PorcaoItens, PorcaoItens, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoItensBAR#deleteAllPorcaoItenss()
 */
@Override
public InternalResponse deleteAllPorcaoItenss()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PorcaoItens_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IPorcaoItensBAR#fetchPorcaoItensById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public PorcaoItens fetchPorcaoItensById(FetchByIdRequest request)
{
return (PorcaoItens)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PorcaoItens, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoItensBAR#fetchAllPorcaoItenssCache()
 */
@Override
public InternalResultsResponse<PorcaoItens> fetchAllPorcaoItenss(PorcaoItens PorcaoItens)
{
	InternalResultsResponse<PorcaoItens> response = new InternalResultsResponse<PorcaoItens>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PorcaoItens_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IPorcaoItensBAR#fetchPorcaoItenssByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<PorcaoItens> fetchPorcaoItenssByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<PorcaoItens> response = new InternalResultsResponse<PorcaoItens>();
	fetchPorcaoItenssByRequest(getSqlSession(), request, STMT_FETCH_PorcaoItens_COUNT, STMT_FETCH_PorcaoItens_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchPorcaoItenssByRequest ####======================================

public static void fetchPorcaoItenssByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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


//===================================### RENTABILIDADE ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeBAR#insertRentabilidade(com.qat.samples.sysmgmt.base.model.Rentabilidade)
 */
@Override
public InternalResponse insertRentabilidade(Rentabilidade rentabilidade)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_RENTABILIDADE, rentabilidade, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeBAR#updateRentabilidade(com.qat.samples.sysmgmt.base.model.Rentabilidade)
 */
@Override
public InternalResponse updateRentabilidade(Rentabilidade rentabilidade)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_RENTABILIDADE, rentabilidade, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeBAR#deleteRentabilidade(com.qat.samples.sysmgmt.base.model.Rentabilidade)
 */
@Override
public InternalResponse deleteRentabilidadeById(Rentabilidade rentabilidade)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_RENTABILIDADE, rentabilidade, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeBAR#deleteAllRentabilidades()
 */
@Override
public InternalResponse deleteAllRentabilidades()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_RENTABILIDADE_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IRentabilidadeBAR#fetchRentabilidadeById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Rentabilidade fetchRentabilidadeById(FetchByIdRequest request)
{
return (Rentabilidade)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_RENTABILIDADE, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeBAR#fetchAllRentabilidadesCache()
 */
@Override
public InternalResultsResponse<Rentabilidade> fetchAllRentabilidades(Rentabilidade rentabilidade)
{
	InternalResultsResponse<Rentabilidade> response = new InternalResultsResponse<Rentabilidade>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_RENTABILIDADE_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IRentabilidadeBAR#fetchRentabilidadesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Rentabilidade> fetchRentabilidadesByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<Rentabilidade> response = new InternalResultsResponse<Rentabilidade>();
	fetchRentabilidadesByRequest(getSqlSession(), request, STMT_FETCH_RENTABILIDADE_COUNT, STMT_FETCH_RENTABILIDADE_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchRentabilidadesByRequest ####======================================

public static void fetchRentabilidadesByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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


//===================================### RENTABILIDADEITENS ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeItensBAR#insertRentabilidadeItens(com.qat.samples.sysmgmt.base.model.RentabilidadeItens)
 */
@Override
public InternalResponse insertRentabilidadeItens(RentabilidadeItens rentabilidadeitens)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_RENTABILIDADEITENS, rentabilidadeitens, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeItensBAR#updateRentabilidadeItens(com.qat.samples.sysmgmt.base.model.RentabilidadeItens)
 */
@Override
public InternalResponse updateRentabilidadeItens(RentabilidadeItens rentabilidadeitens)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_RENTABILIDADEITENS, rentabilidadeitens, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeItensBAR#deleteRentabilidadeItens(com.qat.samples.sysmgmt.base.model.RentabilidadeItens)
 */
@Override
public InternalResponse deleteRentabilidadeItensById(RentabilidadeItens rentabilidadeitens)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_RENTABILIDADEITENS, rentabilidadeitens, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeItensBAR#deleteAllRentabilidadeItenss()
 */
@Override
public InternalResponse deleteAllRentabilidadeItenss()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_RENTABILIDADEITENS_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IRentabilidadeItensBAR#fetchRentabilidadeItensById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public RentabilidadeItens fetchRentabilidadeItensById(FetchByIdRequest request)
{
return (RentabilidadeItens)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_RENTABILIDADEITENS, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeItensBAR#fetchAllRentabilidadeItenssCache()
 */
@Override
public InternalResultsResponse<RentabilidadeItens> fetchAllRentabilidadeItenss(RentabilidadeItens rentabilidadeitens)
{
	InternalResultsResponse<RentabilidadeItens> response = new InternalResultsResponse<RentabilidadeItens>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_RENTABILIDADEITENS_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IRentabilidadeItensBAR#fetchRentabilidadeItenssByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<RentabilidadeItens> fetchRentabilidadeItenssByRequest(PagedInquiryRequest request)
{
	InternalResultsResponse<RentabilidadeItens> response = new InternalResultsResponse<RentabilidadeItens>();
	fetchRentabilidadeItenssByRequest(getSqlSession(), request, STMT_FETCH_RENTABILIDADEITENS_COUNT, STMT_FETCH_RENTABILIDADEITENS_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchRentabilidadeItenssByRequest ####======================================

public static void fetchRentabilidadeItenssByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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

@Override
public MarcaProduto fetchMarcaProdutosById(FetchByIdRequest request) {
	// TODO Auto-generated method stub
	return null;
}

}
