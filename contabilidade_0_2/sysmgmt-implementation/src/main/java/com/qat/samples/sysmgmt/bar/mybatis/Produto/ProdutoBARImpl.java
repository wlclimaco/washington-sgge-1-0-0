/** create by system gera-java version 1.0.0 29/08/2016 23:0 : 35*/
package com.qat.samples.sysmgmt.bar.mybatis.Produto;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Email.IEmailBAR;
import com.qat.samples.sysmgmt.bar.Fiscal.IFiscalBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Nfe.INFNotaInfoItemBAR;
import com.qat.samples.sysmgmt.bar.Nfe.INFeBAR;
import com.qat.samples.sysmgmt.bar.Notes.INotesBAR;
import com.qat.samples.sysmgmt.bar.Produto.IPrecoBAR;
import com.qat.samples.sysmgmt.bar.Produto.IProdutoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.Telefone.ITelefoneBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.CustoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.EmailBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.EstoqueBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.InsertHistBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.MarcaBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFImpostoDevolvidoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemProdutoArmamentoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemProdutoCombustivelBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemProdutoMedicamentoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemProdutoVeiculoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.PrecoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.ProdutoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.StatusBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.TelefoneBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.TributacaoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.UniMedBARD;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoCombustivel;
import com.qat.samples.sysmgmt.produto.model.Categoria;
import com.qat.samples.sysmgmt.produto.model.Cofins;
import com.qat.samples.sysmgmt.produto.model.Custo;
import com.qat.samples.sysmgmt.produto.model.CustoItens;
import com.qat.samples.sysmgmt.produto.model.Estoque;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.ICMSOpInter;
import com.qat.samples.sysmgmt.produto.model.Icms;
import com.qat.samples.sysmgmt.produto.model.Ipi;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.MarcaProduto;
import com.qat.samples.sysmgmt.produto.model.Pis;
import com.qat.samples.sysmgmt.produto.model.Porcao;
import com.qat.samples.sysmgmt.produto.model.PorcaoItens;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.ProdutoEmpresa;
import com.qat.samples.sysmgmt.produto.model.Rentabilidade;
import com.qat.samples.sysmgmt.produto.model.RentabilidadeItens;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.Tributacao;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.CofinsInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.IcmsInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.IpiInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PisInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoEmpresaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.AcaoTypeEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class ProdutoBARImpl extends SqlSessionDaoSupport implements IProdutoBAR {

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/// ===================================### PRODUTOEMPRESA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_PRODUTOEMPRESA = "ProdutoEmpresaMap.";

	/** The Constant STMT_INSERT_PRODUTOEMPRESA. */
	private static final String STMT_INSERT_PRODUTOEMPRESA = NAMESPACE_PRODUTOEMPRESA + "insertProdutoEmpresa";

	/** The Constant STMT_UPDATE_PRODUTOEMPRESA. */
	private static final String STMT_UPDATE_PRODUTOEMPRESA = NAMESPACE_PRODUTOEMPRESA + "updateProdutoEmpresa";

	/** The Constant STMT_DELETE_PRODUTOEMPRESA. */
	private static final String STMT_DELETE_PRODUTOEMPRESA = NAMESPACE_PRODUTOEMPRESA + "deleteProdutoEmpresaById";

	/** The Constant STMT_DELETE_PRODUTOEMPRESA_ALL. */
	private static final String STMT_DELETE_PRODUTOEMPRESA_ALL = NAMESPACE_PRODUTOEMPRESA + "deleteAllProdutoEmpresas";

	/** The Constant STMT_FETCH_PRODUTOEMPRESA. */
	private static final String STMT_FETCH_PRODUTOEMPRESA = NAMESPACE_PRODUTOEMPRESA + "fetchProdutoEmpresaById";

	/** The Constant STMT_FETCH_PRODUTOEMPRESA_ALL. */
	private static final String STMT_FETCH_PRODUTOEMPRESA_ALL = NAMESPACE_PRODUTOEMPRESA + "fetchAllProdutoEmpresas";

	/** The Constant STMT_FETCH_PRODUTOEMPRESA_COUNT. */
	private static final String STMT_FETCH_PRODUTOEMPRESA_COUNT = NAMESPACE_PRODUTOEMPRESA
			+ "fetchProdutoEmpresaRowCount";

	/** The Constant STMT_FETCH_PRODUTOEMPRESA_ALL_REQUEST. */
	private static final String STMT_FETCH_PRODUTOEMPRESA_ALL_REQUEST = NAMESPACE_PRODUTOEMPRESA
			+ "fetchAllProdutoEmpresasRequest";

	/// ===================================### PRODUTO
	/// ####======================================
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

	/// ===================================### CFOP
	/// ####======================================
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

	/// ===================================### MARCA
	/// ####======================================
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

	/// ===================================### MARCAPRODUTO
	/// ####======================================
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
	private static final String STMT_FETCH_MARCAPRODUTO_ALL_REQUEST = NAMESPACE_MARCAPRODUTO
			+ "fetchAllMarcaProdutosRequest";

	/// ===================================### GRUPO
	/// ####======================================
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

	/// ===================================### SUBGRUPO
	/// ####======================================
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

	/// ===================================### UNIMED
	/// ####======================================
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

	/// ===================================### TRIBUTACAO
	/// ####======================================
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

	/// ===================================### ICMS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_ICMS = "IcmsMap.";

	/** The Constant STMT_INSERT_ICMS. */
	private static final String STMT_INSERT_ICMS = NAMESPACE_ICMS + "insertIcms";

	/** The Constant STMT_UPDATE_ICMS. */
	private static final String STMT_UPDATE_ICMS = NAMESPACE_ICMS + "updateIcms";

	/** The Constant STMT_DELETE_ICMS. */
	private static final String STMT_DELETE_ICMS = NAMESPACE_ICMS + "deleteIcmsById";

	/** The Constant STMT_DELETE_ICMS_ALL. */
	private static final String STMT_DELETE_ICMS_ALL = NAMESPACE_ICMS + "deleteAllIcmss";

	/** The Constant STMT_FETCH_ICMS. */
	private static final String STMT_FETCH_ICMS = NAMESPACE_ICMS + "fetchIcmsById";

	/** The Constant STMT_FETCH_ICMS_ALL. */
	private static final String STMT_FETCH_ICMS_ALL = NAMESPACE_ICMS + "fetchAllIcmss";

	/** The Constant STMT_FETCH_ICMS_COUNT. */
	private static final String STMT_FETCH_ICMS_COUNT = NAMESPACE_ICMS + "fetchIcmsRowCount";

	/** The Constant STMT_FETCH_ICMS_ALL_REQUEST. */
	private static final String STMT_FETCH_ICMS_ALL_REQUEST = NAMESPACE_ICMS + "fetchAllIcmssRequest";

	/// ===================================### PIS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_PIS = "PisMap.";

	/** The Constant STMT_INSERT_PIS. */
	private static final String STMT_INSERT_PIS = NAMESPACE_PIS + "insertPis";

	/** The Constant STMT_UPDATE_PIS. */
	private static final String STMT_UPDATE_PIS = NAMESPACE_PIS + "updatePis";

	/** The Constant STMT_DELETE_PIS. */
	private static final String STMT_DELETE_PIS = NAMESPACE_PIS + "deletePisById";

	/** The Constant STMT_DELETE_PIS_ALL. */
	private static final String STMT_DELETE_PIS_ALL = NAMESPACE_PIS + "deleteAllPiss";

	/** The Constant STMT_FETCH_PIS. */
	private static final String STMT_FETCH_PIS = NAMESPACE_PIS + "fetchPisById";

	/** The Constant STMT_FETCH_PIS_ALL. */
	private static final String STMT_FETCH_PIS_ALL = NAMESPACE_PIS + "fetchAllPiss";

	/** The Constant STMT_FETCH_PIS_COUNT. */
	private static final String STMT_FETCH_PIS_COUNT = NAMESPACE_PIS + "fetchPisRowCount";

	/** The Constant STMT_FETCH_PIS_ALL_REQUEST. */
	private static final String STMT_FETCH_PIS_ALL_REQUEST = NAMESPACE_PIS + "fetchAllPissRequest";

	/// ===================================### IPI
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_IPI = "IpiMap.";

	/** The Constant STMT_INSERT_IPI. */
	private static final String STMT_INSERT_IPI = NAMESPACE_IPI + "insertIpi";

	/** The Constant STMT_UPDATE_IPI. */
	private static final String STMT_UPDATE_IPI = NAMESPACE_IPI + "updateIpi";

	/** The Constant STMT_DELETE_IPI. */
	private static final String STMT_DELETE_IPI = NAMESPACE_IPI + "deleteIpiById";

	/** The Constant STMT_DELETE_IPI_ALL. */
	private static final String STMT_DELETE_IPI_ALL = NAMESPACE_IPI + "deleteAllIpis";

	/** The Constant STMT_FETCH_IPI. */
	private static final String STMT_FETCH_IPI = NAMESPACE_IPI + "fetchIpiById";

	/** The Constant STMT_FETCH_IPI_ALL. */
	private static final String STMT_FETCH_IPI_ALL = NAMESPACE_IPI + "fetchAllIpis";

	/** The Constant STMT_FETCH_IPI_COUNT. */
	private static final String STMT_FETCH_IPI_COUNT = NAMESPACE_IPI + "fetchIpiRowCount";

	/** The Constant STMT_FETCH_IPI_ALL_REQUEST. */
	private static final String STMT_FETCH_IPI_ALL_REQUEST = NAMESPACE_IPI + "fetchAllIpisRequest";

	/// ===================================### COFINS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_COFINS = "CofinsMap.";

	/** The Constant STMT_INSERT_COFINS. */
	private static final String STMT_INSERT_COFINS = NAMESPACE_COFINS + "insertCofins";

	/** The Constant STMT_UPDATE_COFINS. */
	private static final String STMT_UPDATE_COFINS = NAMESPACE_COFINS + "updateCofins";

	/** The Constant STMT_DELETE_COFINS. */
	private static final String STMT_DELETE_COFINS = NAMESPACE_COFINS + "deleteCofinsById";

	/** The Constant STMT_DELETE_COFINS_ALL. */
	private static final String STMT_DELETE_COFINS_ALL = NAMESPACE_COFINS + "deleteAllCofinss";

	/** The Constant STMT_FETCH_COFINS. */
	private static final String STMT_FETCH_COFINS = NAMESPACE_COFINS + "fetchCofinsById";

	/** The Constant STMT_FETCH_COFINS_ALL. */
	private static final String STMT_FETCH_COFINS_ALL = NAMESPACE_COFINS + "fetchAllCofinss";

	/** The Constant STMT_FETCH_COFINS_COUNT. */
	private static final String STMT_FETCH_COFINS_COUNT = NAMESPACE_COFINS + "fetchCofinsRowCount";

	/** The Constant STMT_FETCH_COFINS_ALL_REQUEST. */
	private static final String STMT_FETCH_COFINS_ALL_REQUEST = NAMESPACE_COFINS + "fetchAllCofinssRequest";

	/// ===================================### CUSTO
	/// ####======================================
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

	/// ===================================### CUSTOITENS
	/// ####======================================
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

	/// ===================================### ESTOQUE
	/// ####======================================
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

	/// ===================================### PORCAO
	/// ####======================================
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

	/// ===================================### PORCAOITENS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_PORCAOITENS = "PorcaoItensMap.";

	/** The Constant STMT_INSERT_PORCAOITENS. */
	private static final String STMT_INSERT_PORCAOITENS = NAMESPACE_PORCAOITENS + "insertPorcaoItens";

	/** The Constant STMT_UPDATE_PORCAOITENS. */
	private static final String STMT_UPDATE_PORCAOITENS = NAMESPACE_PORCAOITENS + "updatePorcaoItens";

	/** The Constant STMT_DELETE_PORCAOITENS. */
	private static final String STMT_DELETE_PORCAOITENS = NAMESPACE_PORCAOITENS + "deletePorcaoItensById";

	/** The Constant STMT_DELETE_PORCAOITENS_ALL. */
	private static final String STMT_DELETE_PORCAOITENS_ALL = NAMESPACE_PORCAOITENS + "deleteAllPorcaoItenss";

	/** The Constant STMT_FETCH_PORCAOITENS. */
	private static final String STMT_FETCH_PORCAOITENS = NAMESPACE_PORCAOITENS + "fetchPorcaoItensById";

	/** The Constant STMT_FETCH_PORCAOITENS_ALL. */
	private static final String STMT_FETCH_PORCAOITENS_ALL = NAMESPACE_PORCAOITENS + "fetchAllPorcaoItenss";

	/** The Constant STMT_FETCH_PORCAOITENS_COUNT. */
	private static final String STMT_FETCH_PORCAOITENS_COUNT = NAMESPACE_PORCAOITENS + "fetchPorcaoItensRowCount";

	/** The Constant STMT_FETCH_PORCAOITENS_ALL_REQUEST. */
	private static final String STMT_FETCH_PORCAOITENS_ALL_REQUEST = NAMESPACE_PORCAOITENS
			+ "fetchAllPorcaoItenssRequest";

	/// ===================================### RENTABILIDADE
	/// ####======================================
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
	private static final String STMT_FETCH_RENTABILIDADE_ALL_REQUEST = NAMESPACE_RENTABILIDADE
			+ "fetchAllRentabilidadesRequest";

	///===================================### ICMSOPINTER ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_ICMSOPINTER = "ICMSOpInterMap.";

	/** The Constant STMT_INSERT_ICMSOPINTER. */
	private static final String STMT_INSERT_ICMSOPINTER = NAMESPACE_ICMSOPINTER + "insertICMSOpInter";

	/** The Constant STMT_UPDATE_ICMSOPINTER. */
	private static final String STMT_UPDATE_ICMSOPINTER = NAMESPACE_ICMSOPINTER + "updateICMSOpInter";

	/** The Constant STMT_DELETE_ICMSOPINTER. */
	private static final String STMT_DELETE_ICMSOPINTER = NAMESPACE_ICMSOPINTER + "deleteICMSOpInterById";

		/** The Constant STMT_DELETE_ICMSOPINTER_ALL. */
		private static final String STMT_DELETE_ICMSOPINTER_ALL = NAMESPACE_ICMSOPINTER + "deleteAllICMSOpInters";

		/** The Constant STMT_FETCH_ICMSOPINTER. */
		private static final String STMT_FETCH_ICMSOPINTER = NAMESPACE_ICMSOPINTER + "fetchICMSOpInterById";

		/** The Constant STMT_FETCH_ICMSOPINTER_ALL. */
		private static final String STMT_FETCH_ICMSOPINTER_ALL = NAMESPACE_ICMSOPINTER + "fetchAllICMSOpInters";

		/** The Constant STMT_FETCH_ICMSOPINTER_COUNT. */
		private static final String STMT_FETCH_ICMSOPINTER_COUNT = NAMESPACE_ICMSOPINTER + "fetchICMSOpInterRowCount";

		/** The Constant STMT_FETCH_ICMSOPINTER_ALL_REQUEST. */
		private static final String STMT_FETCH_ICMSOPINTER_ALL_REQUEST = NAMESPACE_ICMSOPINTER + "fetchAllICMSOpIntersRequest";
	/// ===================================### RENTABILIDADEITENS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_RENTABILIDADEITENS = "RentabilidadeItensMap.";

	/** The Constant STMT_INSERT_RENTABILIDADEITENS. */
	private static final String STMT_INSERT_RENTABILIDADEITENS = NAMESPACE_RENTABILIDADEITENS
			+ "insertRentabilidadeItens";

	/** The Constant STMT_UPDATE_RENTABILIDADEITENS. */
	private static final String STMT_UPDATE_RENTABILIDADEITENS = NAMESPACE_RENTABILIDADEITENS
			+ "updateRentabilidadeItens";

	/** The Constant STMT_DELETE_RENTABILIDADEITENS. */
	private static final String STMT_DELETE_RENTABILIDADEITENS = NAMESPACE_RENTABILIDADEITENS
			+ "deleteRentabilidadeItensById";

	/** The Constant STMT_DELETE_RENTABILIDADEITENS_ALL. */
	private static final String STMT_DELETE_RENTABILIDADEITENS_ALL = NAMESPACE_RENTABILIDADEITENS
			+ "deleteAllRentabilidadeItenss";

	/** The Constant STMT_FETCH_RENTABILIDADEITENS. */
	private static final String STMT_FETCH_RENTABILIDADEITENS = NAMESPACE_RENTABILIDADEITENS
			+ "fetchRentabilidadeItensById";

	/** The Constant STMT_FETCH_RENTABILIDADEITENS_ALL. */
	private static final String STMT_FETCH_RENTABILIDADEITENS_ALL = NAMESPACE_RENTABILIDADEITENS
			+ "fetchAllRentabilidadeItenss";

	/** The Constant STMT_FETCH_RENTABILIDADEITENS_COUNT. */
	private static final String STMT_FETCH_RENTABILIDADEITENS_COUNT = NAMESPACE_RENTABILIDADEITENS
			+ "fetchRentabilidadeItensRowCount";

	/** The Constant STMT_FETCH_RENTABILIDADEITENS_ALL_REQUEST. */
	private static final String STMT_FETCH_RENTABILIDADEITENS_ALL_REQUEST = NAMESPACE_RENTABILIDADEITENS
			+ "fetchAllRentabilidadeItenssRequest";

	/// ===================================### CATEGORIA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CATEGORIA = "CategoriaMap.";

	/** The Constant STMT_INSERT_CATEGORIA. */
	private static final String STMT_INSERT_CATEGORIA = NAMESPACE_CATEGORIA + "insertCategoria";

	/** The Constant STMT_UPDATE_CATEGORIA. */
	private static final String STMT_UPDATE_CATEGORIA = NAMESPACE_CATEGORIA + "updateCategoria";

	/** The Constant STMT_DELETE_CATEGORIA. */
	private static final String STMT_DELETE_CATEGORIA = NAMESPACE_CATEGORIA + "deleteCategoriaById";

	/** The Constant STMT_DELETE_CATEGORIA_ALL. */
	private static final String STMT_DELETE_CATEGORIA_ALL = NAMESPACE_CATEGORIA + "deleteAllCategorias";

	/** The Constant STMT_FETCH_CATEGORIA. */
	private static final String STMT_FETCH_CATEGORIA = NAMESPACE_CATEGORIA + "fetchCategoriaById";

	/** The Constant STMT_FETCH_CATEGORIA_ALL. */
	private static final String STMT_FETCH_CATEGORIA_ALL = NAMESPACE_CATEGORIA + "fetchAllCategorias";

	/** The Constant STMT_FETCH_CATEGORIA_COUNT. */
	private static final String STMT_FETCH_CATEGORIA_COUNT = NAMESPACE_CATEGORIA + "fetchCategoriaRowCount";

	/** The Constant STMT_FETCH_CATEGORIA_ALL_REQUEST. */
	private static final String STMT_FETCH_CATEGORIA_ALL_REQUEST = NAMESPACE_CATEGORIA + "fetchAllCategoriasRequest";

	// ===================================### PRODUTOEMPRESA
	// ####======================================

	IStatusBAR statusBAR;

	IProdutoBAR produtoBAR;

	IHistoricoBAR historicoBAR;

	ITelefoneBAR telefoneBAR;

	IEmailBAR emailBAR;

	INotesBAR notesBAR;

	IFiscalBAR fiscalBAR;

	IPrecoBAR precoBAR;

	INFNotaInfoItemBAR nfnotaInfoItemBAR;

	INFeBAR nfeBAR;

	public IPrecoBAR getPrecoBAR() {
		return precoBAR;
	}

	public void setPrecoBAR(IPrecoBAR precoBAR) {
		this.precoBAR = precoBAR;
	}

	public IStatusBAR getStatusBAR() {
		return statusBAR;
	}

	public void setStatusBAR(IStatusBAR statusBAR) {
		this.statusBAR = statusBAR;
	}

	public IProdutoBAR getProdutoBAR() {
		return produtoBAR;
	}

	public void setProdutoBAR(IProdutoBAR produtoBAR) {
		this.produtoBAR = produtoBAR;
	}

	public IHistoricoBAR getHistoricoBAR() {
		return historicoBAR;
	}

	public void setHistoricoBAR(IHistoricoBAR historicoBAR) {
		this.historicoBAR = historicoBAR;
	}

	public ITelefoneBAR getTelefoneBAR() {
		return telefoneBAR;
	}

	public void setTelefoneBAR(ITelefoneBAR telefoneBAR) {
		this.telefoneBAR = telefoneBAR;
	}

	public IEmailBAR getEmailBAR() {
		return emailBAR;
	}

	public void setEmailBAR(IEmailBAR emailBAR) {
		this.emailBAR = emailBAR;
	}

	public INotesBAR getNotesBAR() {
		return notesBAR;
	}

	public void setNotesBAR(INotesBAR notesBAR) {
		this.notesBAR = notesBAR;
	}

	public IFiscalBAR getFiscalBAR() {
		return fiscalBAR;
	}

	public void setFiscalBAR(IFiscalBAR fiscalBAR) {
		this.fiscalBAR = fiscalBAR;
	}


	public INFNotaInfoItemBAR getNfnotaInfoItemBAR() {
		return nfnotaInfoItemBAR;
	}

	public void setNfnotaInfoItemBAR(INFNotaInfoItemBAR nfnotaInfoItemBAR) {
		this.nfnotaInfoItemBAR = nfnotaInfoItemBAR;
	}


	public INFeBAR getNfeBAR() {
		return nfeBAR;
	}

	public void setNfeBAR(INFeBAR nfeBAR) {
		this.nfeBAR = nfeBAR;
	}

	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IProdutoEmpresaBAR#insertProdutoEmpresa(com.qat.samples.sysmgmt.base.model.ProdutoEmpresa)
	 */
	@Override
	public InternalResponse insertProdutoEmpresa(ProdutoEmpresa produtoempresa) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		produtoempresa.setProcessId(produtoempresa.getTransactionId());

		if (ValidationUtil.isNullOrZero(produtoempresa.getProdId().getId()))
		{
			ProdutoBARD.maintainProdutoAssociations(produtoempresa.getProdId(), response,null,  TypeEnum.LOW, AcaoTypeEnum.INSERT, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produtoempresa.getEmprId(), produtoempresa.getUserId(), produtoempresa.getTransactionId(), produtoempresa.getTransactionId());
		}



		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PRODUTOEMPRESA, produtoempresa, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PRODUTOEMPRESA, AcaoEnum.INSERT,
				produtoempresa.getTransactionId(), getHistoricoBAR(), response, produtoempresa.getId(),
				produtoempresa.getUserId());

		if (!ValidationUtil.isNull(produtoempresa.getTributacao()))
		{

			produtoempresa.getTributacao().setProdId(produtoempresa.getId());
			TributacaoBARD.maintainTributacaoAssociations(produtoempresa.getTributacao(), response,null,  TypeEnum.LOW, AcaoTypeEnum.INSERT, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produtoempresa.getEmprId(), produtoempresa.getUserId(), produtoempresa.getTransactionId(), produtoempresa.getTransactionId());

		}

		if (!ValidationUtil.isNullOrEmpty(produtoempresa.getEstoqueList()))
		{

			EstoqueBARD.maintainEstoqueAssociations(produtoempresa.getEstoqueList(), response,produtoempresa.getId(),  TypeEnum.LOW, AcaoTypeEnum.INSERT, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produtoempresa.getEmprId(), produtoempresa.getUserId(), produtoempresa.getTransactionId(), produtoempresa.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(produtoempresa.getPrecoList()))
		{
			PrecoBARD.maintainPrecoAssociations(produtoempresa.getPrecoList(), response,produtoempresa.getId(),  TypeEnum.LOW, AcaoTypeEnum.INSERT, TabelaEnum.PRODUTO,
					getPrecoBAR(), getStatusBAR(), getHistoricoBAR(), produtoempresa.getEmprId(), produtoempresa.getUserId(), produtoempresa.getTransactionId(), produtoempresa.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(produtoempresa.getCustoList()))
		{
			CustoBARD.maintainCustoAssociations(produtoempresa.getCustoList(), response,produtoempresa.getId(),  TypeEnum.LOW, AcaoTypeEnum.INSERT, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produtoempresa.getEmprId(), produtoempresa.getUserId(), produtoempresa.getTransactionId(), produtoempresa.getTransactionId());
		}

		if (produtoempresa.getId() != 0 && produtoempresa.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, produtoempresa.getId(), null,
					AcaoEnum.INSERT, produtoempresa.getCreateUser(), produtoempresa.getId(), TabelaEnum.PRODUTOEMPRESA,
					statusBAR, historicoBAR, produtoempresa.getTransactionId(), produtoempresa.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IProdutoEmpresaBAR#updateProdutoEmpresa(
	 * com.qat.samples.sysmgmt.base.model.ProdutoEmpresa)
	 */
	@Override
	public InternalResponse updateProdutoEmpresa(ProdutoEmpresa produtoempresa) {
		InternalResponse response = new InternalResponse();

		produtoempresa.setProcessId(produtoempresa.getTransactionId());

		if (!ValidationUtil.isNullOrZero(produtoempresa.getProdId().getId()))
		{
			ProdutoBARD.maintainProdutoAssociations(produtoempresa.getProdId(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produtoempresa.getEmprId(), produtoempresa.getUserId(), produtoempresa.getTransactionId(), produtoempresa.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PRODUTOEMPRESA, produtoempresa, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PRODUTOEMPRESA, AcaoEnum.UPDATE,
				produtoempresa.getTransactionId(), getHistoricoBAR(), response, produtoempresa.getId(),
				produtoempresa.getUserId());

//		if (!ValidationUtil.isNull(produtoempresa.getTributacao()))
//		{
//
//			produtoempresa.getTributacao().setProdId(produtoempresa.getId());
//			TributacaoBARD.maintainTributacaoAssociations(produtoempresa.getTributacao(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
//					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produtoempresa.getEmprId(), produtoempresa.getUserId(), produtoempresa.getTransactionId(), produtoempresa.getTransactionId());
//
//		}
		if (!ValidationUtil.isNullOrEmpty(produtoempresa.getEstoqueList()))
		{

			EstoqueBARD.maintainEstoqueAssociations(produtoempresa.getEstoqueList(), response,produtoempresa.getId(),  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produtoempresa.getEmprId(), produtoempresa.getUserId(), produtoempresa.getTransactionId(), produtoempresa.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(produtoempresa.getPrecoList()))
		{
			PrecoBARD.maintainPrecoAssociations(produtoempresa.getPrecoList(), response,produtoempresa.getId(),  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getPrecoBAR(), getStatusBAR(), getHistoricoBAR(), produtoempresa.getEmprId(), produtoempresa.getUserId(), produtoempresa.getTransactionId(), produtoempresa.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(produtoempresa.getCustoList()))
		{
			CustoBARD.maintainCustoAssociations(produtoempresa.getCustoList(), response,produtoempresa.getId(),  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produtoempresa.getEmprId(), produtoempresa.getUserId(), produtoempresa.getTransactionId(), produtoempresa.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(produtoempresa.getCustoList()))
		{
			CustoBARD.maintainCustoAssociations(produtoempresa.getCustoList(), response,produtoempresa.getId(),  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produtoempresa.getEmprId(), produtoempresa.getUserId(), produtoempresa.getTransactionId(), produtoempresa.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IProdutoEmpresaBAR#deleteProdutoEmpresa(
	 * com.qat.samples.sysmgmt.base.model.ProdutoEmpresa)
	 */
	@Override
	public InternalResponse deleteProdutoEmpresaById(ProdutoEmpresa produtoempresa) {
		InternalResponse response = new InternalResponse();
		produtoempresa.setProcessId(produtoempresa.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PRODUTOEMPRESA, produtoempresa, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PRODUTOEMPRESA, AcaoEnum.DELETE,
				produtoempresa.getTransactionId(), getHistoricoBAR(), response, produtoempresa.getId(),
				produtoempresa.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IProdutoEmpresaBAR#
	 * deleteAllProdutoEmpresas()
	 */
	@Override
	public InternalResponse deleteAllProdutoEmpresas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PRODUTOEMPRESA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IProdutoEmpresaBAR#fetchProdutoEmpresaById(
	 * com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ProdutoEmpresa fetchProdutoEmpresaById(FetchByIdRequest request) {
		return (ProdutoEmpresa) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PRODUTOEMPRESA,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IProdutoEmpresaBAR#
	 * fetchAllProdutoEmpresasCache()
	 */
	@Override
	public InternalResultsResponse<ProdutoEmpresa> fetchAllProdutoEmpresas(ProdutoEmpresa produtoempresa) {
		InternalResultsResponse<ProdutoEmpresa> response = new InternalResultsResponse<ProdutoEmpresa>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PRODUTOEMPRESA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IProdutoEmpresaBAR#
	 * fetchProdutoEmpresasByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ProdutoEmpresa> fetchProdutoEmpresasByRequest(ProdutoEmpresaInquiryRequest request) {
		InternalResultsResponse<ProdutoEmpresa> response = new InternalResultsResponse<ProdutoEmpresa>();
		fetchProdutoEmpresasByRequest(getSqlSession(), request, STMT_FETCH_PRODUTOEMPRESA_COUNT,
				STMT_FETCH_PRODUTOEMPRESA_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchProdutoEmpresasByRequest
	// ####======================================

	public static void fetchProdutoEmpresasByRequest(SqlSession sqlSession, ProdutoEmpresaInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### PRODUTO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#insertProduto(com.qat.samples.sysmgmt.base.model.Produto)
	 */
	@Override
	public InternalResponse insertProduto(Produto produto) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		produto.setProcessId(produto.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PRODUTO, produto, response);

		if (ValidationUtil.isNull(produto.getUniMed()))
		{
			UniMedBARD.maintainUniMedAssociations(produto.getUniMed(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (ValidationUtil.isNull(produto.getMarca()))
		{
			MarcaBARD.maintainMarcaAssociations(produto.getMarca(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (!ValidationUtil.isNull(produto.getVeiculo()))
		{
			NFNotaInfoItemProdutoVeiculoBARD.maintainNFNotaInfoItemProdutoVeiculoAssociations(produto.getVeiculo(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getNfnotaInfoItemBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (ValidationUtil.isNull(produto.getMedicamento()))
		{
			NFNotaInfoItemProdutoMedicamentoBARD.maintainNFNotaInfoItemProdutoMedicamentoAssociations(produto.getMedicamento(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getNfnotaInfoItemBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (ValidationUtil.isNull(produto.getArmamento()))
		{
			NFNotaInfoItemProdutoArmamentoBARD.maintainNFNotaInfoItemProdutoArmamentoAssociations(produto.getArmamento(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getNfnotaInfoItemBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (ValidationUtil.isNull(produto.getCombustivel()))
		{
			NFNotaInfoItemProdutoCombustivelBARD.maintainNFNotaInfoItemProdutoCombustivelAssociations(produto.getCombustivel(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getNfnotaInfoItemBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PRODUTO, AcaoEnum.INSERT,
				produto.getTransactionId(), getHistoricoBAR(), response, produto.getId(), produto.getUserId());

		if (produto.getId() != 0 && produto.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, produto.getId(), null, AcaoEnum.INSERT,
					produto.getCreateUser(), produto.getId(), TabelaEnum.PRODUTO, statusBAR, historicoBAR,
					produto.getTransactionId(), produto.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#updateProduto(com.qat.
	 * samples.sysmgmt.base.model.Produto)
	 */
	@Override
	public InternalResponse updateProduto(Produto produto) {
		InternalResponse response = new InternalResponse();
		produto.setProcessId(produto.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PRODUTO, produto, response);

		if (ValidationUtil.isNull(produto.getUniMed()))
		{
			UniMedBARD.maintainUniMedAssociations(produto.getUniMed(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (ValidationUtil.isNull(produto.getMarca()))
		{
			MarcaBARD.maintainMarcaAssociations(produto.getMarca(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (!ValidationUtil.isNull(produto.getVeiculo()))
		{
			NFNotaInfoItemProdutoVeiculoBARD.maintainNFNotaInfoItemProdutoVeiculoAssociations(produto.getVeiculo(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getNfnotaInfoItemBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (ValidationUtil.isNull(produto.getMedicamento()))
		{
			NFNotaInfoItemProdutoMedicamentoBARD.maintainNFNotaInfoItemProdutoMedicamentoAssociations(produto.getMedicamento(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getNfnotaInfoItemBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (ValidationUtil.isNull(produto.getArmamento()))
		{
			NFNotaInfoItemProdutoArmamentoBARD.maintainNFNotaInfoItemProdutoArmamentoAssociations(produto.getArmamento(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getNfnotaInfoItemBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (ValidationUtil.isNull(produto.getCombustivel()))
		{
			NFNotaInfoItemProdutoCombustivelBARD.maintainNFNotaInfoItemProdutoCombustivelAssociations(produto.getCombustivel(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getNfnotaInfoItemBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PRODUTO, AcaoEnum.UPDATE,
				produto.getTransactionId(), getHistoricoBAR(), response, produto.getId(), produto.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#deleteProduto(com.qat.
	 * samples.sysmgmt.base.model.Produto)
	 */
	@Override
	public InternalResponse deleteProdutoById(Produto produto) {
		InternalResponse response = new InternalResponse();
		produto.setProcessId(produto.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PRODUTO, produto, response);

		if (ValidationUtil.isNull(produto.getUniMed()))
		{
			UniMedBARD.maintainUniMedAssociations(produto.getUniMed(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (ValidationUtil.isNull(produto.getMarca()))
		{
			MarcaBARD.maintainMarcaAssociations(produto.getMarca(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getProdutoBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (!ValidationUtil.isNull(produto.getVeiculo()))
		{
			NFNotaInfoItemProdutoVeiculoBARD.maintainNFNotaInfoItemProdutoVeiculoAssociations(produto.getVeiculo(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getNfnotaInfoItemBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (ValidationUtil.isNull(produto.getMedicamento()))
		{
			NFNotaInfoItemProdutoMedicamentoBARD.maintainNFNotaInfoItemProdutoMedicamentoAssociations(produto.getMedicamento(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getNfnotaInfoItemBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (ValidationUtil.isNull(produto.getArmamento()))
		{
			NFNotaInfoItemProdutoArmamentoBARD.maintainNFNotaInfoItemProdutoArmamentoAssociations(produto.getArmamento(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getNfnotaInfoItemBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		if (ValidationUtil.isNull(produto.getCombustivel()))
		{
			NFNotaInfoItemProdutoCombustivelBARD.maintainNFNotaInfoItemProdutoCombustivelAssociations(produto.getCombustivel(), response,null,  TypeEnum.LOW, AcaoTypeEnum.UPDATE, TabelaEnum.PRODUTO,
					getNfnotaInfoItemBAR(), getStatusBAR(), getHistoricoBAR(), produto.getEmprId(), produto.getUserId(), produto.getTransactionId(), produto.getTransactionId());
		}

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PRODUTO, AcaoEnum.DELETE,
				produto.getTransactionId(), getHistoricoBAR(), response, produto.getId(), produto.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#deleteAllProdutos()
	 */
	@Override
	public InternalResponse deleteAllProdutos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PRODUTO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IProdutoBAR#fetchProdutoById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Produto fetchProdutoById(FetchByIdRequest request) {
		return (Produto) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PRODUTO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IProdutoBAR#fetchAllProdutosCache()
	 */
	@Override
	public InternalResultsResponse<Produto> fetchAllProdutos(Produto produto) {
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PRODUTO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IProdutoBAR#fetchProdutosByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> fetchProdutosByRequest(ProdutoInquiryRequest request) {
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
		fetchProdutosByRequest(getSqlSession(), request, STMT_FETCH_PRODUTO_COUNT, STMT_FETCH_PRODUTO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchProdutosByRequest
	// ####======================================

	public static void fetchProdutosByRequest(SqlSession sqlSession, ProdutoInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### CFOP
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#insertCfop(com.qat.samples.sysmgmt.base.model.Cfop)
	 */
	@Override
	public InternalResponse insertCfop(Cfop cfop) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		cfop.setProcessId(cfop.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CFOP, cfop, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CFOP, AcaoEnum.INSERT,
				cfop.getTransactionId(), getHistoricoBAR(), response, cfop.getId(), cfop.getUserId());

		if (cfop.getId() != 0 && cfop.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, cfop.getId(), null, AcaoEnum.INSERT,
					cfop.getCreateUser(), cfop.getId(), TabelaEnum.CFOP, statusBAR, historicoBAR,
					cfop.getTransactionId(), cfop.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICfopBAR#updateCfop(com.qat.samples.
	 * sysmgmt.base.model.Cfop)
	 */
	@Override
	public InternalResponse updateCfop(Cfop cfop) {
		InternalResponse response = new InternalResponse();
		cfop.setProcessId(cfop.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CFOP, cfop, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CFOP, AcaoEnum.UPDATE,
				cfop.getTransactionId(), getHistoricoBAR(), response, cfop.getId(), cfop.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICfopBAR#deleteCfop(com.qat.samples.
	 * sysmgmt.base.model.Cfop)
	 */
	@Override
	public InternalResponse deleteCfopById(Cfop cfop) {
		InternalResponse response = new InternalResponse();
		cfop.setProcessId(cfop.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CFOP, cfop, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CFOP, AcaoEnum.DELETE,
				cfop.getTransactionId(), getHistoricoBAR(), response, cfop.getId(), cfop.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#deleteAllCfops()
	 */
	@Override
	public InternalResponse deleteAllCfops() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CFOP_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.ICfopBAR#fetchCfopById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Cfop fetchCfopById(FetchByIdRequest request) {
		return (Cfop) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CFOP, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICfopBAR#fetchAllCfopsCache()
	 */
	@Override
	public InternalResultsResponse<Cfop> fetchAllCfops(Cfop cfop) {
		InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CFOP_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICfopBAR#fetchCfopsByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Cfop> fetchCfopsByRequest(CfopInquiryRequest request) {
		InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();
		fetchCfopsByRequest(getSqlSession(), request, STMT_FETCH_CFOP_COUNT, STMT_FETCH_CFOP_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchCfopsByRequest
	// ####======================================

	public static void fetchCfopsByRequest(SqlSession sqlSession, CfopInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### MARCA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IMarcaBAR#insertMarca(com.qat.samples.sysmgmt.base.model.Marca)
	 */
	@Override
	public InternalResponse insertMarca(Marca marca) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		marca.setProcessId(marca.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_MARCA, marca, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.MARCA, AcaoEnum.INSERT,
				marca.getTransactionId(), getHistoricoBAR(), response, marca.getId(), marca.getUserId());

		if (!ValidationUtil.isNullOrEmpty(marca.getEmailList())) {
			count += EmailBARD.maintainEmailAssociations(marca.getEmailList(), response, marca.getId(), null, null,
					TabelaEnum.MARCA, emailBAR, statusBAR, historicoBAR, marca.getId(), marca.getCreateUser(),
					marca.getTransactionId(), marca.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(marca.getTelefoneList())) {
			count += TelefoneBARD.maintainTelefoneAssociations(marca.getTelefoneList(), response, marca.getId(), null,
					null, TabelaEnum.MARCA, telefoneBAR, statusBAR, historicoBAR, marca.getId(), marca.getCreateUser(),
					marca.getTransactionId(), marca.getTransactionId());
		}

		if (marca.getId() != 0 && marca.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, marca.getId(), null, AcaoEnum.INSERT,
					marca.getCreateUser(), marca.getId(), TabelaEnum.MARCA, statusBAR, historicoBAR,
					marca.getTransactionId(), marca.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IMarcaBAR#updateMarca(com.qat.samples.
	 * sysmgmt.base.model.Marca)
	 */
	@Override
	public InternalResponse updateMarca(Marca marca) {
		Integer count = 0;
		InternalResponse response = new InternalResponse();
		marca.setProcessId(marca.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_MARCA, marca, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.MARCA, AcaoEnum.UPDATE,
				marca.getTransactionId(), getHistoricoBAR(), response, marca.getId(), marca.getUserId());

		if (!ValidationUtil.isNullOrEmpty(marca.getEmailList())) {
			count += EmailBARD.maintainEmailAssociations(marca.getEmailList(), response, marca.getId(), null, null,
					TabelaEnum.MARCA, emailBAR, statusBAR, historicoBAR, marca.getId(), marca.getCreateUser(),
					marca.getTransactionId(), marca.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(marca.getTelefoneList())) {
			count += TelefoneBARD.maintainTelefoneAssociations(marca.getTelefoneList(), response, marca.getId(), null,
					null, TabelaEnum.MARCA, telefoneBAR, statusBAR, historicoBAR, marca.getId(), marca.getCreateUser(),
					marca.getTransactionId(), marca.getTransactionId());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IMarcaBAR#deleteMarca(com.qat.samples.
	 * sysmgmt.base.model.Marca)
	 */
	@Override
	public InternalResponse deleteMarcaById(Marca marca) {
		InternalResponse response = new InternalResponse();
		marca.setProcessId(marca.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MARCA, marca, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.MARCA, AcaoEnum.DELETE,
				marca.getTransactionId(), getHistoricoBAR(), response, marca.getId(), marca.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IMarcaBAR#deleteAllMarcas()
	 */
	@Override
	public InternalResponse deleteAllMarcas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MARCA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IMarcaBAR#fetchMarcaById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Marca fetchMarcaById(FetchByIdRequest request) {
		return (Marca) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_MARCA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IMarcaBAR#fetchAllMarcasCache()
	 */
	@Override
	public InternalResultsResponse<Marca> fetchAllMarcas(Marca marca) {
		InternalResultsResponse<Marca> response = new InternalResultsResponse<Marca>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_MARCA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IMarcaBAR#fetchMarcasByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Marca> fetchMarcasByRequest(MarcaInquiryRequest request) {
		InternalResultsResponse<Marca> response = new InternalResultsResponse<Marca>();
		fetchMarcasByRequest(getSqlSession(), request, STMT_FETCH_MARCA_COUNT, STMT_FETCH_MARCA_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchMarcasByRequest
	// ####======================================

	public static void fetchMarcasByRequest(SqlSession sqlSession, MarcaInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### MARCAPRODUTO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IMarcaProdutoBAR#insertMarcaProduto(com.qat.samples.sysmgmt.base.model.MarcaProduto)
	 */
	@Override
	public InternalResponse insertMarcaProduto(MarcaProduto marcaproduto) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		marcaproduto.setProcessId(marcaproduto.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_MARCAPRODUTO, marcaproduto, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.MARCAPRODUTO, AcaoEnum.INSERT,
				marcaproduto.getTransactionId(), getHistoricoBAR(), response, marcaproduto.getId(),
				marcaproduto.getUserId());

		if (marcaproduto.getId() != 0 && marcaproduto.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, marcaproduto.getId(), null,
					AcaoEnum.INSERT, marcaproduto.getCreateUser(), marcaproduto.getId(), TabelaEnum.MARCAPRODUTO,
					statusBAR, historicoBAR, marcaproduto.getTransactionId(), marcaproduto.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IMarcaProdutoBAR#updateMarcaProduto(com.
	 * qat.samples.sysmgmt.base.model.MarcaProduto)
	 */
	@Override
	public InternalResponse updateMarcaProduto(MarcaProduto marcaproduto) {
		InternalResponse response = new InternalResponse();
		marcaproduto.setProcessId(marcaproduto.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_MARCAPRODUTO, marcaproduto, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.MARCAPRODUTO, AcaoEnum.UPDATE,
				marcaproduto.getTransactionId(), getHistoricoBAR(), response, marcaproduto.getId(),
				marcaproduto.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IMarcaProdutoBAR#deleteMarcaProduto(com.
	 * qat.samples.sysmgmt.base.model.MarcaProduto)
	 */
	@Override
	public InternalResponse deleteMarcaProdutoById(MarcaProduto marcaproduto) {
		InternalResponse response = new InternalResponse();
		marcaproduto.setProcessId(marcaproduto.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MARCAPRODUTO, marcaproduto, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.MARCAPRODUTO, AcaoEnum.DELETE,
				marcaproduto.getTransactionId(), getHistoricoBAR(), response, marcaproduto.getId(),
				marcaproduto.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IMarcaProdutoBAR#deleteAllMarcaProdutos(
	 * )
	 */
	@Override
	public InternalResponse deleteAllMarcaProdutos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MARCAPRODUTO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IMarcaProdutoBAR#fetchMarcaProdutoById(com.
	 * qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public MarcaProduto fetchMarcaProdutoById(FetchByIdRequest request) {
		return (MarcaProduto) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_MARCAPRODUTO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IMarcaProdutoBAR#
	 * fetchAllMarcaProdutosCache()
	 */
	@Override
	public InternalResultsResponse<MarcaProduto> fetchAllMarcaProdutos(MarcaProduto marcaproduto) {
		InternalResultsResponse<MarcaProduto> response = new InternalResultsResponse<MarcaProduto>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_MARCAPRODUTO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IMarcaProdutoBAR#fetchMarcaProdutosByRequest(
	 * com.qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<MarcaProduto> fetchMarcaProdutosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<MarcaProduto> response = new InternalResultsResponse<MarcaProduto>();
		fetchMarcaProdutosByRequest(getSqlSession(), request, STMT_FETCH_MARCAPRODUTO_COUNT,
				STMT_FETCH_MARCAPRODUTO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchMarcaProdutosByRequest
	// ####======================================

	public static void fetchMarcaProdutosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### GRUPO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IGrupoBAR#insertGrupo(com.qat.samples.sysmgmt.base.model.Grupo)
	 */
	@Override
	public InternalResponse insertGrupo(Grupo grupo) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		grupo.setProcessId(grupo.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_GRUPO, grupo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.GRUPO, AcaoEnum.INSERT,
				grupo.getTransactionId(), getHistoricoBAR(), response, grupo.getId(), grupo.getUserId());

		if (grupo.getId() != 0 && grupo.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, grupo.getId(), null, AcaoEnum.INSERT,
					grupo.getCreateUser(), grupo.getId(), TabelaEnum.GRUPO, statusBAR, historicoBAR,
					grupo.getTransactionId(), grupo.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IGrupoBAR#updateGrupo(com.qat.samples.
	 * sysmgmt.base.model.Grupo)
	 */
	@Override
	public InternalResponse updateGrupo(Grupo grupo) {
		InternalResponse response = new InternalResponse();
		grupo.setProcessId(grupo.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_GRUPO, grupo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.GRUPO, AcaoEnum.UPDATE,
				grupo.getTransactionId(), getHistoricoBAR(), response, grupo.getId(), grupo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IGrupoBAR#deleteGrupo(com.qat.samples.
	 * sysmgmt.base.model.Grupo)
	 */
	@Override
	public InternalResponse deleteGrupoById(Grupo grupo) {
		InternalResponse response = new InternalResponse();
		grupo.setProcessId(grupo.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_GRUPO, grupo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.GRUPO, AcaoEnum.DELETE,
				grupo.getTransactionId(), getHistoricoBAR(), response, grupo.getId(), grupo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IGrupoBAR#deleteAllGrupos()
	 */
	@Override
	public InternalResponse deleteAllGrupos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_GRUPO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IGrupoBAR#fetchGrupoById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Grupo fetchGrupoById(FetchByIdRequest request) {
		return (Grupo) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_GRUPO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IGrupoBAR#fetchAllGruposCache()
	 */
	@Override
	public InternalResultsResponse<Grupo> fetchAllGrupos(Grupo grupo) {
		InternalResultsResponse<Grupo> response = new InternalResultsResponse<Grupo>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_GRUPO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IGrupoBAR#fetchGruposByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Grupo> fetchGruposByRequest(GrupoInquiryRequest request) {
		InternalResultsResponse<Grupo> response = new InternalResultsResponse<Grupo>();
		fetchGruposByRequest(getSqlSession(), request, STMT_FETCH_GRUPO_COUNT, STMT_FETCH_GRUPO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchGruposByRequest
	// ####======================================

	public static void fetchGruposByRequest(SqlSession sqlSession, GrupoInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### SUBGRUPO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#insertSubGrupo(com.qat.samples.sysmgmt.base.model.SubGrupo)
	 */
	@Override
	public InternalResponse insertSubGrupo(SubGrupo subgrupo) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		subgrupo.setProcessId(subgrupo.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_SUBGRUPO, subgrupo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.SUBGRUPO, AcaoEnum.INSERT,
				subgrupo.getTransactionId(), getHistoricoBAR(), response, subgrupo.getId(), subgrupo.getUserId());

		if (subgrupo.getId() != 0 && subgrupo.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, subgrupo.getId(), null,
					AcaoEnum.INSERT, subgrupo.getCreateUser(), subgrupo.getId(), TabelaEnum.SUBGRUPO, statusBAR,
					historicoBAR, subgrupo.getTransactionId(), subgrupo.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#updateSubGrupo(com.qat.
	 * samples.sysmgmt.base.model.SubGrupo)
	 */
	@Override
	public InternalResponse updateSubGrupo(SubGrupo subgrupo) {
		InternalResponse response = new InternalResponse();
		subgrupo.setProcessId(subgrupo.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_SUBGRUPO, subgrupo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.SUBGRUPO, AcaoEnum.UPDATE,
				subgrupo.getTransactionId(), getHistoricoBAR(), response, subgrupo.getId(), subgrupo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#deleteSubGrupo(com.qat.
	 * samples.sysmgmt.base.model.SubGrupo)
	 */
	@Override
	public InternalResponse deleteSubGrupoById(SubGrupo subgrupo) {
		InternalResponse response = new InternalResponse();
		subgrupo.setProcessId(subgrupo.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_SUBGRUPO, subgrupo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.SUBGRUPO, AcaoEnum.DELETE,
				subgrupo.getTransactionId(), getHistoricoBAR(), response, subgrupo.getId(), subgrupo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#deleteAllSubGrupos()
	 */
	@Override
	public InternalResponse deleteAllSubGrupos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_SUBGRUPO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.ISubGrupoBAR#fetchSubGrupoById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public SubGrupo fetchSubGrupoById(FetchByIdRequest request) {
		return (SubGrupo) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_SUBGRUPO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ISubGrupoBAR#fetchAllSubGruposCache()
	 */
	@Override
	public InternalResultsResponse<SubGrupo> fetchAllSubGrupos(SubGrupo subgrupo) {
		InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_SUBGRUPO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ISubGrupoBAR#fetchSubGruposByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<SubGrupo> fetchSubGruposByRequest(SubGrupoInquiryRequest request) {
		InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();
		fetchSubGruposByRequest(getSqlSession(), request, STMT_FETCH_SUBGRUPO_COUNT, STMT_FETCH_SUBGRUPO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchSubGruposByRequest
	// ####======================================

	public static void fetchSubGruposByRequest(SqlSession sqlSession, SubGrupoInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### UNIMED
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUniMedBAR#insertUniMed(com.qat.samples.sysmgmt.base.model.UniMed)
	 */
	@Override
	public InternalResponse insertUniMed(UniMed unimed) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		unimed.setProcessId(unimed.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_UNIMED, unimed, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.UNIMED, AcaoEnum.INSERT,
				unimed.getTransactionId(), getHistoricoBAR(), response, unimed.getId(), unimed.getUserId());

		if (unimed.getId() != 0 && unimed.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, unimed.getId(), null, AcaoEnum.INSERT,
					unimed.getCreateUser(), unimed.getId(), TabelaEnum.UNIMED, statusBAR, historicoBAR,
					unimed.getTransactionId(), unimed.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IUniMedBAR#updateUniMed(com.qat.samples.
	 * sysmgmt.base.model.UniMed)
	 */
	@Override
	public InternalResponse updateUniMed(UniMed unimed) {
		InternalResponse response = new InternalResponse();
		unimed.setProcessId(unimed.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_UNIMED, unimed, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.UNIMED, AcaoEnum.UPDATE,
				unimed.getTransactionId(), getHistoricoBAR(), response, unimed.getId(), unimed.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IUniMedBAR#deleteUniMed(com.qat.samples.
	 * sysmgmt.base.model.UniMed)
	 */
	@Override
	public InternalResponse deleteUniMedById(UniMed unimed) {
		InternalResponse response = new InternalResponse();
		unimed.setProcessId(unimed.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_UNIMED, unimed, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.UNIMED, AcaoEnum.DELETE,
				unimed.getTransactionId(), getHistoricoBAR(), response, unimed.getId(), unimed.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUniMedBAR#deleteAllUniMeds()
	 */
	@Override
	public InternalResponse deleteAllUniMeds() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_UNIMED_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IUniMedBAR#fetchUniMedById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public UniMed fetchUniMedById(FetchByIdRequest request) {
		return (UniMed) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_UNIMED, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUniMedBAR#fetchAllUniMedsCache()
	 */
	@Override
	public InternalResultsResponse<UniMed> fetchAllUniMeds(UniMed unimed) {
		InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_UNIMED_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IUniMedBAR#fetchUniMedsByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<UniMed> fetchUniMedsByRequest(UniMedInquiryRequest request) {
		InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();
		fetchUniMedsByRequest(getSqlSession(), request, STMT_FETCH_UNIMED_COUNT, STMT_FETCH_UNIMED_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchUniMedsByRequest
	// ####======================================

	public static void fetchUniMedsByRequest(SqlSession sqlSession, UniMedInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### TRIBUTACAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#insertTributacao(com.qat.samples.sysmgmt.base.model.Tributacao)
	 */
	@Override
	public InternalResponse insertTributacao(Tributacao tributacao) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		tributacao.setTransactionId(tributacao.getTransactionId());

		if (!ValidationUtil.isNull(tributacao.getImposto())) {
			count += NFNotaInfoItemImpostoBARD.maintainNFNotaInfoItemImpostoAssociations(tributacao.getImposto(),
					response, tributacao.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, tributacao.getId(), tributacao.getCreateUser(),
					tributacao.getTransactionId(), tributacao.getTransactionId());
		}

		if (!ValidationUtil.isNull(tributacao.getImpostoDevolvido())) {
			count += NFImpostoDevolvidoBARD.maintainNFImpostoDevolvidoAssociations(tributacao.getImpostoDevolvido(),
					response, tributacao.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, tributacao.getId(), tributacao.getCreateUser(),
					tributacao.getTransactionId(), tributacao.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_TRIBUTACAO, tributacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.TRIBUTACAO, AcaoEnum.INSERT,
				tributacao.getTransactionId(), getHistoricoBAR(), response, tributacao.getId(), tributacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#updateTributacao(com.qat.
	 * samples.sysmgmt.base.model.Tributacao)
	 */
	@Override
	public InternalResponse updateTributacao(Tributacao tributacao) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		tributacao.setProcessId(tributacao.getTransactionId());
		if (!ValidationUtil.isNull(tributacao.getImposto())) {
			count += NFNotaInfoItemImpostoBARD.maintainNFNotaInfoItemImpostoAssociations(tributacao.getImposto(),
					response, tributacao.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, tributacao.getId(), tributacao.getCreateUser(),
					tributacao.getTransactionId(), tributacao.getTransactionId());
		}

		if (!ValidationUtil.isNull(tributacao.getImpostoDevolvido())) {
			count += NFImpostoDevolvidoBARD.maintainNFImpostoDevolvidoAssociations(tributacao.getImpostoDevolvido(),
					response, tributacao.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, tributacao.getId(), tributacao.getCreateUser(),
					tributacao.getTransactionId(), tributacao.getTransactionId());
		}
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_TRIBUTACAO, tributacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.TRIBUTACAO, AcaoEnum.UPDATE,
				tributacao.getTransactionId(), getHistoricoBAR(), response, tributacao.getId(), tributacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#deleteTributacao(com.qat.
	 * samples.sysmgmt.base.model.Tributacao)
	 */
	@Override
	public InternalResponse deleteTributacaoById(Tributacao tributacao) {
		InternalResponse response = new InternalResponse();
		tributacao.setProcessId(tributacao.getTransactionId());


		Integer count = 0;
		tributacao.setProcessId(tributacao.getTransactionId());
		if (!ValidationUtil.isNull(tributacao.getImposto())) {
			count += NFNotaInfoItemImpostoBARD.maintainNFNotaInfoItemImpostoAssociations(tributacao.getImposto(),
					response, tributacao.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, tributacao.getId(), tributacao.getCreateUser(),
					tributacao.getTransactionId(), tributacao.getTransactionId());
		}

		if (!ValidationUtil.isNull(tributacao.getImpostoDevolvido())) {
			count += NFImpostoDevolvidoBARD.maintainNFImpostoDevolvidoAssociations(tributacao.getImpostoDevolvido(),
					response, tributacao.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, tributacao.getId(), tributacao.getCreateUser(),
					tributacao.getTransactionId(), tributacao.getTransactionId());
		}

		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_TRIBUTACAO, tributacao, response);
		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.TRIBUTACAO, AcaoEnum.DELETE,
				tributacao.getTransactionId(), getHistoricoBAR(), response, tributacao.getId(), tributacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#deleteAllTributacaos()
	 */
	@Override
	public InternalResponse deleteAllTributacaos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_TRIBUTACAO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ITributacaoBAR#fetchTributacaoById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Tributacao fetchTributacaoById(FetchByIdRequest request) {
		return (Tributacao) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_TRIBUTACAO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ITributacaoBAR#fetchAllTributacaosCache(
	 * )
	 */
	@Override
	public InternalResultsResponse<Tributacao> fetchAllTributacaos(Tributacao tributacao) {
		InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_TRIBUTACAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ITributacaoBAR#fetchTributacaosByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Tributacao> fetchTributacaosByRequest(TributacaoInquiryRequest request) {
		InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();
		fetchTributacaosByRequest(getSqlSession(), request, STMT_FETCH_TRIBUTACAO_COUNT,
				STMT_FETCH_TRIBUTACAO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchTributacaosByRequest
	// ####======================================

	public static void fetchTributacaosByRequest(SqlSession sqlSession, TributacaoInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### ICMS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IIcmsBAR#insertIcms(com.qat.samples.sysmgmt.base.model.Icms)
	 */
	@Override
	public InternalResponse insertIcms(Icms icms) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		icms.setProcessId(icms.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ICMS, icms, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ICMS, AcaoEnum.INSERT,
				icms.getTransactionId(), getHistoricoBAR(), response, icms.getId(), icms.getUserId());

		if (icms.getId() != 0 && icms.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, icms.getId(), null, AcaoEnum.INSERT,
					icms.getCreateUser(), icms.getId(), TabelaEnum.ICMS, statusBAR, historicoBAR,
					icms.getTransactionId(), icms.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IIcmsBAR#updateIcms(com.qat.samples.
	 * sysmgmt.base.model.Icms)
	 */
	@Override
	public InternalResponse updateIcms(Icms icms) {
		InternalResponse response = new InternalResponse();
		icms.setProcessId(icms.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ICMS, icms, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ICMS, AcaoEnum.UPDATE,
				icms.getTransactionId(), getHistoricoBAR(), response, icms.getId(), icms.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IIcmsBAR#deleteIcms(com.qat.samples.
	 * sysmgmt.base.model.Icms)
	 */
	@Override
	public InternalResponse deleteIcmsById(Icms icms) {
		InternalResponse response = new InternalResponse();
		icms.setProcessId(icms.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ICMS, icms, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ICMS, AcaoEnum.DELETE,
				icms.getTransactionId(), getHistoricoBAR(), response, icms.getId(), icms.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IIcmsBAR#deleteAllIcmss()
	 */
	@Override
	public InternalResponse deleteAllIcmss() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ICMS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IIcmsBAR#fetchIcmsById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Icms fetchIcmsById(FetchByIdRequest request) {
		return (Icms) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ICMS, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IIcmsBAR#fetchAllIcmssCache()
	 */
	@Override
	public InternalResultsResponse<Icms> fetchAllIcmss(Icms icms) {
		InternalResultsResponse<Icms> response = new InternalResultsResponse<Icms>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ICMS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IIcmsBAR#fetchIcmssByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Icms> fetchIcmssByRequest(IcmsInquiryRequest request) {
		InternalResultsResponse<Icms> response = new InternalResultsResponse<Icms>();
		fetchIcmssByRequest(getSqlSession(), request, STMT_FETCH_ICMS_COUNT, STMT_FETCH_ICMS_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchIcmssByRequest
	// ####======================================

	public static void fetchIcmssByRequest(SqlSession sqlSession, IcmsInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### PIS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPisBAR#insertPis(com.qat.samples.sysmgmt.base.model.Pis)
	 */
	@Override
	public InternalResponse insertPis(Pis pis) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		pis.setProcessId(pis.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PIS, pis, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PIS, AcaoEnum.INSERT, pis.getTransactionId(),
				getHistoricoBAR(), response, pis.getId(), pis.getUserId());

		if (pis.getId() != 0 && pis.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, pis.getId(), null, AcaoEnum.INSERT,
					pis.getCreateUser(), pis.getId(), TabelaEnum.PIS, statusBAR, historicoBAR, pis.getTransactionId(),
					pis.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPisBAR#updatePis(com.qat.samples.
	 * sysmgmt.base.model.Pis)
	 */
	@Override
	public InternalResponse updatePis(Pis pis) {
		InternalResponse response = new InternalResponse();
		pis.setProcessId(pis.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PIS, pis, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PIS, AcaoEnum.UPDATE, pis.getTransactionId(),
				getHistoricoBAR(), response, pis.getId(), pis.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPisBAR#deletePis(com.qat.samples.
	 * sysmgmt.base.model.Pis)
	 */
	@Override
	public InternalResponse deletePisById(Pis pis) {
		InternalResponse response = new InternalResponse();
		pis.setProcessId(pis.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PIS, pis, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PIS, AcaoEnum.DELETE, pis.getTransactionId(),
				getHistoricoBAR(), response, pis.getId(), pis.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPisBAR#deleteAllPiss()
	 */
	@Override
	public InternalResponse deleteAllPiss() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PIS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IPisBAR#fetchPisById(com.qat.samples.sysmgmt.
	 * model.request.FetchByIdRequest)
	 */
	@Override
	public Pis fetchPisById(FetchByIdRequest request) {
		return (Pis) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PIS, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPisBAR#fetchAllPissCache()
	 */
	@Override
	public InternalResultsResponse<Pis> fetchAllPiss(Pis pis) {
		InternalResultsResponse<Pis> response = new InternalResultsResponse<Pis>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PIS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IPisBAR#fetchPissByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Pis> fetchPissByRequest(PisInquiryRequest request) {
		InternalResultsResponse<Pis> response = new InternalResultsResponse<Pis>();
		fetchPissByRequest(getSqlSession(), request, STMT_FETCH_PIS_COUNT, STMT_FETCH_PIS_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchPissByRequest
	// ####======================================

	public static void fetchPissByRequest(SqlSession sqlSession, PisInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### IPI
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IIpiBAR#insertIpi(com.qat.samples.sysmgmt.base.model.Ipi)
	 */
	@Override
	public InternalResponse insertIpi(Ipi ipi) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		ipi.setProcessId(ipi.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_IPI, ipi, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.IPI, AcaoEnum.INSERT, ipi.getTransactionId(),
				getHistoricoBAR(), response, ipi.getId(), ipi.getUserId());

		if (ipi.getId() != 0 && ipi.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, ipi.getId(), null, AcaoEnum.INSERT,
					ipi.getCreateUser(), ipi.getId(), TabelaEnum.IPI, statusBAR, historicoBAR, ipi.getTransactionId(),
					ipi.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IIpiBAR#updateIpi(com.qat.samples.
	 * sysmgmt.base.model.Ipi)
	 */
	@Override
	public InternalResponse updateIpi(Ipi ipi) {
		InternalResponse response = new InternalResponse();
		ipi.setProcessId(ipi.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_IPI, ipi, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.IPI, AcaoEnum.UPDATE, ipi.getTransactionId(),
				getHistoricoBAR(), response, ipi.getId(), ipi.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IIpiBAR#deleteIpi(com.qat.samples.
	 * sysmgmt.base.model.Ipi)
	 */
	@Override
	public InternalResponse deleteIpiById(Ipi ipi) {
		InternalResponse response = new InternalResponse();
		ipi.setProcessId(ipi.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_IPI, ipi, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.IPI, AcaoEnum.DELETE, ipi.getTransactionId(),
				getHistoricoBAR(), response, ipi.getId(), ipi.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IIpiBAR#deleteAllIpis()
	 */
	@Override
	public InternalResponse deleteAllIpis() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_IPI_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IIpiBAR#fetchIpiById(com.qat.samples.sysmgmt.
	 * model.request.FetchByIdRequest)
	 */
	@Override
	public Ipi fetchIpiById(FetchByIdRequest request) {
		return (Ipi) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_IPI, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IIpiBAR#fetchAllIpisCache()
	 */
	@Override
	public InternalResultsResponse<Ipi> fetchAllIpis(Ipi ipi) {
		InternalResultsResponse<Ipi> response = new InternalResultsResponse<Ipi>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_IPI_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IIpiBAR#fetchIpisByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Ipi> fetchIpisByRequest(IpiInquiryRequest request) {
		InternalResultsResponse<Ipi> response = new InternalResultsResponse<Ipi>();
		fetchIpisByRequest(getSqlSession(), request, STMT_FETCH_IPI_COUNT, STMT_FETCH_IPI_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchIpisByRequest
	// ####======================================

	public static void fetchIpisByRequest(SqlSession sqlSession, IpiInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### COFINS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICofinsBAR#insertCofins(com.qat.samples.sysmgmt.base.model.Cofins)
	 */
	@Override
	public InternalResponse insertCofins(Cofins cofins) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		cofins.setProcessId(cofins.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_COFINS, cofins, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.COFINS, AcaoEnum.INSERT,
				cofins.getTransactionId(), getHistoricoBAR(), response, cofins.getId(), cofins.getUserId());

		if (cofins.getId() != 0 && cofins.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, cofins.getId(), null, AcaoEnum.INSERT,
					cofins.getCreateUser(), cofins.getId(), TabelaEnum.COFINS, statusBAR, historicoBAR,
					cofins.getTransactionId(), cofins.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICofinsBAR#updateCofins(com.qat.samples.
	 * sysmgmt.base.model.Cofins)
	 */
	@Override
	public InternalResponse updateCofins(Cofins cofins) {
		InternalResponse response = new InternalResponse();
		cofins.setProcessId(cofins.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_COFINS, cofins, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.COFINS, AcaoEnum.UPDATE,
				cofins.getTransactionId(), getHistoricoBAR(), response, cofins.getId(), cofins.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICofinsBAR#deleteCofins(com.qat.samples.
	 * sysmgmt.base.model.Cofins)
	 */
	@Override
	public InternalResponse deleteCofinsById(Cofins cofins) {
		InternalResponse response = new InternalResponse();
		cofins.setProcessId(cofins.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_COFINS, cofins, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.COFINS, AcaoEnum.DELETE,
				cofins.getTransactionId(), getHistoricoBAR(), response, cofins.getId(), cofins.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICofinsBAR#deleteAllCofinss()
	 */
	@Override
	public InternalResponse deleteAllCofinss() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_COFINS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICofinsBAR#fetchCofinsById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Cofins fetchCofinsById(FetchByIdRequest request) {
		return (Cofins) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_COFINS, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICofinsBAR#fetchAllCofinssCache()
	 */
	@Override
	public InternalResultsResponse<Cofins> fetchAllCofinss(Cofins cofins) {
		InternalResultsResponse<Cofins> response = new InternalResultsResponse<Cofins>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_COFINS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICofinsBAR#fetchCofinssByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Cofins> fetchCofinssByRequest(CofinsInquiryRequest request) {
		InternalResultsResponse<Cofins> response = new InternalResultsResponse<Cofins>();
		fetchCofinssByRequest(getSqlSession(), request, STMT_FETCH_COFINS_COUNT, STMT_FETCH_COFINS_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchCofinssByRequest
	// ####======================================

	public static void fetchCofinssByRequest(SqlSession sqlSession, CofinsInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### CUSTO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICustoBAR#insertCusto(com.qat.samples.sysmgmt.base.model.Custo)
	 */
	@Override
	public InternalResponse insertCusto(Custo custo) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		custo.setProcessId(custo.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CUSTO, custo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CUSTO, AcaoEnum.INSERT,
				custo.getTransactionId(), getHistoricoBAR(), response, custo.getId(), custo.getUserId());

		if (custo.getId() != 0 && custo.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, custo.getId(), null, AcaoEnum.INSERT,
					custo.getCreateUser(), custo.getId(), TabelaEnum.CUSTO, statusBAR, historicoBAR,
					custo.getTransactionId(), custo.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICustoBAR#updateCusto(com.qat.samples.
	 * sysmgmt.base.model.Custo)
	 */
	@Override
	public InternalResponse updateCusto(Custo custo) {
		InternalResponse response = new InternalResponse();
		custo.setProcessId(custo.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CUSTO, custo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CUSTO, AcaoEnum.UPDATE,
				custo.getTransactionId(), getHistoricoBAR(), response, custo.getId(), custo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICustoBAR#deleteCusto(com.qat.samples.
	 * sysmgmt.base.model.Custo)
	 */
	@Override
	public InternalResponse deleteCustoById(Custo custo) {
		InternalResponse response = new InternalResponse();
		custo.setProcessId(custo.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CUSTO, custo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CUSTO, AcaoEnum.DELETE,
				custo.getTransactionId(), getHistoricoBAR(), response, custo.getId(), custo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICustoBAR#deleteAllCustos()
	 */
	@Override
	public InternalResponse deleteAllCustos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CUSTO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICustoBAR#fetchCustoById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Custo fetchCustoById(FetchByIdRequest request) {
		return (Custo) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CUSTO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICustoBAR#fetchAllCustosCache()
	 */
	@Override
	public InternalResultsResponse<Custo> fetchAllCustos(Custo custo) {
		InternalResultsResponse<Custo> response = new InternalResultsResponse<Custo>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CUSTO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.ICustoBAR#fetchCustosByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Custo> fetchCustosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Custo> response = new InternalResultsResponse<Custo>();
		fetchCustosByRequest(getSqlSession(), request, STMT_FETCH_CUSTO_COUNT, STMT_FETCH_CUSTO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchCustosByRequest
	// ####======================================

	public static void fetchCustosByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### CUSTOITENS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICustoItensBAR#insertCustoItens(com.qat.samples.sysmgmt.base.model.CustoItens)
	 */
	@Override
	public InternalResponse insertCustoItens(CustoItens custoitens) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		custoitens.setProcessId(custoitens.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CUSTOITENS, custoitens, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CUSTOITENS, AcaoEnum.INSERT,
				custoitens.getTransactionId(), getHistoricoBAR(), response, custoitens.getId(), custoitens.getUserId());

		if (custoitens.getId() != 0 && custoitens.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, custoitens.getId(), null,
					AcaoEnum.INSERT, custoitens.getCreateUser(), custoitens.getId(), TabelaEnum.CUSTOITENS, statusBAR,
					historicoBAR, custoitens.getTransactionId(), custoitens.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICustoItensBAR#updateCustoItens(com.qat.
	 * samples.sysmgmt.base.model.CustoItens)
	 */
	@Override
	public InternalResponse updateCustoItens(CustoItens custoitens) {
		InternalResponse response = new InternalResponse();
		custoitens.setProcessId(custoitens.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CUSTOITENS, custoitens, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CUSTOITENS, AcaoEnum.UPDATE,
				custoitens.getTransactionId(), getHistoricoBAR(), response, custoitens.getId(), custoitens.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICustoItensBAR#deleteCustoItens(com.qat.
	 * samples.sysmgmt.base.model.CustoItens)
	 */
	@Override
	public InternalResponse deleteCustoItensById(CustoItens custoitens) {
		InternalResponse response = new InternalResponse();
		custoitens.setProcessId(custoitens.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CUSTOITENS, custoitens, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CUSTOITENS, AcaoEnum.DELETE,
				custoitens.getTransactionId(), getHistoricoBAR(), response, custoitens.getId(), custoitens.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICustoItensBAR#deleteAllCustoItenss()
	 */
	@Override
	public InternalResponse deleteAllCustoItenss() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CUSTOITENS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICustoItensBAR#fetchCustoItensById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public CustoItens fetchCustoItensById(FetchByIdRequest request) {
		return (CustoItens) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CUSTOITENS,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICustoItensBAR#fetchAllCustoItenssCache(
	 * )
	 */
	@Override
	public InternalResultsResponse<CustoItens> fetchAllCustoItenss(CustoItens custoitens) {
		InternalResultsResponse<CustoItens> response = new InternalResultsResponse<CustoItens>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CUSTOITENS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICustoItensBAR#fetchCustoItenssByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<CustoItens> fetchCustoItenssByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<CustoItens> response = new InternalResultsResponse<CustoItens>();
		fetchCustoItenssByRequest(getSqlSession(), request, STMT_FETCH_CUSTOITENS_COUNT,
				STMT_FETCH_CUSTOITENS_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchCustoItenssByRequest
	// ####======================================

	public static void fetchCustoItenssByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### ESTOQUE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEstoqueBAR#insertEstoque(com.qat.samples.sysmgmt.base.model.Estoque)
	 */
	@Override
	public InternalResponse insertEstoque(Estoque estoque) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		estoque.setProcessId(estoque.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ESTOQUE, estoque, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ESTOQUE, AcaoEnum.INSERT,
				estoque.getTransactionId(), getHistoricoBAR(), response, estoque.getId(), estoque.getUserId());

		if (estoque.getId() != 0 && estoque.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, estoque.getId(), null, AcaoEnum.INSERT,
					estoque.getCreateUser(), estoque.getId(), TabelaEnum.ESTOQUE, statusBAR, historicoBAR,
					estoque.getTransactionId(), estoque.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEstoqueBAR#updateEstoque(com.qat.
	 * samples.sysmgmt.base.model.Estoque)
	 */
	@Override
	public InternalResponse updateEstoque(Estoque estoque) {
		InternalResponse response = new InternalResponse();
		estoque.setProcessId(estoque.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ESTOQUE, estoque, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ESTOQUE, AcaoEnum.UPDATE,
				estoque.getTransactionId(), getHistoricoBAR(), response, estoque.getId(), estoque.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEstoqueBAR#deleteEstoque(com.qat.
	 * samples.sysmgmt.base.model.Estoque)
	 */
	@Override
	public InternalResponse deleteEstoqueById(Estoque estoque) {
		InternalResponse response = new InternalResponse();
		estoque.setProcessId(estoque.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ESTOQUE, estoque, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ESTOQUE, AcaoEnum.DELETE,
				estoque.getTransactionId(), getHistoricoBAR(), response, estoque.getId(), estoque.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEstoqueBAR#deleteAllEstoques()
	 */
	@Override
	public InternalResponse deleteAllEstoques() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ESTOQUE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IEstoqueBAR#fetchEstoqueById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Estoque fetchEstoqueById(FetchByIdRequest request) {
		return (Estoque) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ESTOQUE, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEstoqueBAR#fetchAllEstoquesCache()
	 */
	@Override
	public InternalResultsResponse<Estoque> fetchAllEstoques(Estoque estoque) {
		InternalResultsResponse<Estoque> response = new InternalResultsResponse<Estoque>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ESTOQUE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IEstoqueBAR#fetchEstoquesByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Estoque> fetchEstoquesByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Estoque> response = new InternalResultsResponse<Estoque>();
		fetchEstoquesByRequest(getSqlSession(), request, STMT_FETCH_ESTOQUE_COUNT, STMT_FETCH_ESTOQUE_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchEstoquesByRequest
	// ####======================================

	public static void fetchEstoquesByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### PORCAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoBAR#insertPorcao(com.qat.samples.sysmgmt.base.model.Porcao)
	 */
	@Override
	public InternalResponse insertPorcao(Porcao porcao) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		porcao.setProcessId(porcao.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PORCAO, porcao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PORCAO, AcaoEnum.INSERT,
				porcao.getTransactionId(), getHistoricoBAR(), response, porcao.getId(), porcao.getUserId());

		if (porcao.getId() != 0 && porcao.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, porcao.getId(), null, AcaoEnum.INSERT,
					porcao.getCreateUser(), porcao.getId(), TabelaEnum.PORCAO, statusBAR, historicoBAR,
					porcao.getTransactionId(), porcao.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IPorcaoBAR#updatePorcao(com.qat.samples.
	 * sysmgmt.base.model.Porcao)
	 */
	@Override
	public InternalResponse updatePorcao(Porcao porcao) {
		InternalResponse response = new InternalResponse();
		porcao.setProcessId(porcao.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PORCAO, porcao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PORCAO, AcaoEnum.UPDATE,
				porcao.getTransactionId(), getHistoricoBAR(), response, porcao.getId(), porcao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IPorcaoBAR#deletePorcao(com.qat.samples.
	 * sysmgmt.base.model.Porcao)
	 */
	@Override
	public InternalResponse deletePorcaoById(Porcao porcao) {
		InternalResponse response = new InternalResponse();
		porcao.setProcessId(porcao.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PORCAO, porcao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PORCAO, AcaoEnum.DELETE,
				porcao.getTransactionId(), getHistoricoBAR(), response, porcao.getId(), porcao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoBAR#deleteAllPorcaos()
	 */
	@Override
	public InternalResponse deleteAllPorcaos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PORCAO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IPorcaoBAR#fetchPorcaoById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Porcao fetchPorcaoById(FetchByIdRequest request) {
		return (Porcao) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PORCAO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoBAR#fetchAllPorcaosCache()
	 */
	@Override
	public InternalResultsResponse<Porcao> fetchAllPorcaos(Porcao porcao) {
		InternalResultsResponse<Porcao> response = new InternalResultsResponse<Porcao>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PORCAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IPorcaoBAR#fetchPorcaosByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Porcao> fetchPorcaosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Porcao> response = new InternalResultsResponse<Porcao>();
		fetchPorcaosByRequest(getSqlSession(), request, STMT_FETCH_PORCAO_COUNT, STMT_FETCH_PORCAO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchPorcaosByRequest
	// ####======================================

	public static void fetchPorcaosByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### PORCAOITENS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoItensBAR#insertPorcaoItens(com.qat.samples.sysmgmt.base.model.PorcaoItens)
	 */
	@Override
	public InternalResponse insertPorcaoItens(PorcaoItens porcaoitens) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		porcaoitens.setProcessId(porcaoitens.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PORCAOITENS, porcaoitens, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PORCAOITENS, AcaoEnum.INSERT,
				porcaoitens.getTransactionId(), getHistoricoBAR(), response, porcaoitens.getId(),
				porcaoitens.getUserId());

		if (porcaoitens.getId() != 0 && porcaoitens.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, porcaoitens.getId(), null,
					AcaoEnum.INSERT, porcaoitens.getCreateUser(), porcaoitens.getId(), TabelaEnum.PORCAOITENS,
					statusBAR, historicoBAR, porcaoitens.getTransactionId(), porcaoitens.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IPorcaoItensBAR#updatePorcaoItens(com.
	 * qat.samples.sysmgmt.base.model.PorcaoItens)
	 */
	@Override
	public InternalResponse updatePorcaoItens(PorcaoItens porcaoitens) {
		InternalResponse response = new InternalResponse();
		porcaoitens.setProcessId(porcaoitens.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PORCAOITENS, porcaoitens, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PORCAOITENS, AcaoEnum.UPDATE,
				porcaoitens.getTransactionId(), getHistoricoBAR(), response, porcaoitens.getId(),
				porcaoitens.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IPorcaoItensBAR#deletePorcaoItens(com.
	 * qat.samples.sysmgmt.base.model.PorcaoItens)
	 */
	@Override
	public InternalResponse deletePorcaoItensById(PorcaoItens porcaoitens) {
		InternalResponse response = new InternalResponse();
		porcaoitens.setProcessId(porcaoitens.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PORCAOITENS, porcaoitens, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PORCAOITENS, AcaoEnum.DELETE,
				porcaoitens.getTransactionId(), getHistoricoBAR(), response, porcaoitens.getId(),
				porcaoitens.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IPorcaoItensBAR#deleteAllPorcaoItenss()
	 */
	@Override
	public InternalResponse deleteAllPorcaoItenss() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PORCAOITENS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IPorcaoItensBAR#fetchPorcaoItensById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public PorcaoItens fetchPorcaoItensById(FetchByIdRequest request) {
		return (PorcaoItens) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PORCAOITENS,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPorcaoItensBAR#
	 * fetchAllPorcaoItenssCache()
	 */
	@Override
	public InternalResultsResponse<PorcaoItens> fetchAllPorcaoItenss(PorcaoItens porcaoitens) {
		InternalResultsResponse<PorcaoItens> response = new InternalResultsResponse<PorcaoItens>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PORCAOITENS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IPorcaoItensBAR#fetchPorcaoItenssByRequest(
	 * com.qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<PorcaoItens> fetchPorcaoItenssByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<PorcaoItens> response = new InternalResultsResponse<PorcaoItens>();
		fetchPorcaoItenssByRequest(getSqlSession(), request, STMT_FETCH_PORCAOITENS_COUNT,
				STMT_FETCH_PORCAOITENS_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchPorcaoItenssByRequest
	// ####======================================

	public static void fetchPorcaoItenssByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### RENTABILIDADE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeBAR#insertRentabilidade(com.qat.samples.sysmgmt.base.model.Rentabilidade)
	 */
	@Override
	public InternalResponse insertRentabilidade(Rentabilidade rentabilidade) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		rentabilidade.setProcessId(rentabilidade.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_RENTABILIDADE, rentabilidade, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.RENTABILIDADE, AcaoEnum.INSERT,
				rentabilidade.getTransactionId(), getHistoricoBAR(), response, rentabilidade.getId(),
				rentabilidade.getUserId());

		if (rentabilidade.getId() != 0 && rentabilidade.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, rentabilidade.getId(), null,
					AcaoEnum.INSERT, rentabilidade.getCreateUser(), rentabilidade.getId(), TabelaEnum.RENTABILIDADE,
					statusBAR, historicoBAR, rentabilidade.getTransactionId(), rentabilidade.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IRentabilidadeBAR#updateRentabilidade(
	 * com.qat.samples.sysmgmt.base.model.Rentabilidade)
	 */
	@Override
	public InternalResponse updateRentabilidade(Rentabilidade rentabilidade) {
		InternalResponse response = new InternalResponse();
		rentabilidade.setProcessId(rentabilidade.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_RENTABILIDADE, rentabilidade, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.RENTABILIDADE, AcaoEnum.UPDATE,
				rentabilidade.getTransactionId(), getHistoricoBAR(), response, rentabilidade.getId(),
				rentabilidade.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IRentabilidadeBAR#deleteRentabilidade(
	 * com.qat.samples.sysmgmt.base.model.Rentabilidade)
	 */
	@Override
	public InternalResponse deleteRentabilidadeById(Rentabilidade rentabilidade) {
		InternalResponse response = new InternalResponse();
		rentabilidade.setProcessId(rentabilidade.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_RENTABILIDADE, rentabilidade, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.RENTABILIDADE, AcaoEnum.DELETE,
				rentabilidade.getTransactionId(), getHistoricoBAR(), response, rentabilidade.getId(),
				rentabilidade.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeBAR#
	 * deleteAllRentabilidades()
	 */
	@Override
	public InternalResponse deleteAllRentabilidades() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_RENTABILIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IRentabilidadeBAR#fetchRentabilidadeById(com.
	 * qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Rentabilidade fetchRentabilidadeById(FetchByIdRequest request) {
		return (Rentabilidade) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_RENTABILIDADE,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeBAR#
	 * fetchAllRentabilidadesCache()
	 */
	@Override
	public InternalResultsResponse<Rentabilidade> fetchAllRentabilidades(Rentabilidade rentabilidade) {
		InternalResultsResponse<Rentabilidade> response = new InternalResultsResponse<Rentabilidade>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_RENTABILIDADE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IRentabilidadeBAR#
	 * fetchRentabilidadesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Rentabilidade> fetchRentabilidadesByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Rentabilidade> response = new InternalResultsResponse<Rentabilidade>();
		fetchRentabilidadesByRequest(getSqlSession(), request, STMT_FETCH_RENTABILIDADE_COUNT,
				STMT_FETCH_RENTABILIDADE_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchRentabilidadesByRequest
	// ####======================================

	public static void fetchRentabilidadesByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### RENTABILIDADEITENS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeItensBAR#insertRentabilidadeItens(com.qat.samples.sysmgmt.base.model.RentabilidadeItens)
	 */
	@Override
	public InternalResponse insertRentabilidadeItens(RentabilidadeItens rentabilidadeitens) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		rentabilidadeitens.setProcessId(rentabilidadeitens.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_RENTABILIDADEITENS, rentabilidadeitens, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.RENTABILIDADEITENS, AcaoEnum.INSERT,
				rentabilidadeitens.getTransactionId(), getHistoricoBAR(), response, rentabilidadeitens.getId(),
				rentabilidadeitens.getUserId());

		if (rentabilidadeitens.getId() != 0 && rentabilidadeitens.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, rentabilidadeitens.getId(), null,
					AcaoEnum.INSERT, rentabilidadeitens.getCreateUser(), rentabilidadeitens.getId(),
					TabelaEnum.RENTABILIDADEITENS, statusBAR, historicoBAR, rentabilidadeitens.getTransactionId(),
					rentabilidadeitens.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeItensBAR#
	 * updateRentabilidadeItens(com.qat.samples.sysmgmt.base.model.
	 * RentabilidadeItens)
	 */
	@Override
	public InternalResponse updateRentabilidadeItens(RentabilidadeItens rentabilidadeitens) {
		InternalResponse response = new InternalResponse();
		rentabilidadeitens.setProcessId(rentabilidadeitens.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_RENTABILIDADEITENS, rentabilidadeitens, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.RENTABILIDADEITENS, AcaoEnum.UPDATE,
				rentabilidadeitens.getTransactionId(), getHistoricoBAR(), response, rentabilidadeitens.getId(),
				rentabilidadeitens.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeItensBAR#
	 * deleteRentabilidadeItens(com.qat.samples.sysmgmt.base.model.
	 * RentabilidadeItens)
	 */
	@Override
	public InternalResponse deleteRentabilidadeItensById(RentabilidadeItens rentabilidadeitens) {
		InternalResponse response = new InternalResponse();
		rentabilidadeitens.setProcessId(rentabilidadeitens.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_RENTABILIDADEITENS, rentabilidadeitens, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.RENTABILIDADEITENS, AcaoEnum.DELETE,
				rentabilidadeitens.getTransactionId(), getHistoricoBAR(), response, rentabilidadeitens.getId(),
				rentabilidadeitens.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeItensBAR#
	 * deleteAllRentabilidadeItenss()
	 */
	@Override
	public InternalResponse deleteAllRentabilidadeItenss() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_RENTABILIDADEITENS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IRentabilidadeItensBAR#
	 * fetchRentabilidadeItensById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public RentabilidadeItens fetchRentabilidadeItensById(FetchByIdRequest request) {
		return (RentabilidadeItens) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_RENTABILIDADEITENS,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IRentabilidadeItensBAR#
	 * fetchAllRentabilidadeItenssCache()
	 */
	@Override
	public InternalResultsResponse<RentabilidadeItens> fetchAllRentabilidadeItenss(
			RentabilidadeItens rentabilidadeitens) {
		InternalResultsResponse<RentabilidadeItens> response = new InternalResultsResponse<RentabilidadeItens>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_RENTABILIDADEITENS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IRentabilidadeItensBAR#
	 * fetchRentabilidadeItenssByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<RentabilidadeItens> fetchRentabilidadeItenssByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<RentabilidadeItens> response = new InternalResultsResponse<RentabilidadeItens>();
		fetchRentabilidadeItenssByRequest(getSqlSession(), request, STMT_FETCH_RENTABILIDADEITENS_COUNT,
				STMT_FETCH_RENTABILIDADEITENS_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchRentabilidadeItenssByRequest
	// ####======================================

	public static void fetchRentabilidadeItenssByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### CATEGORIA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICategoriaBAR#insertCategoria(com.qat.samples.sysmgmt.base.model.Categoria)
	 */
	@Override
	public InternalResponse insertCategoria(Categoria categoria) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		categoria.setProcessId(categoria.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CATEGORIA, categoria, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CATEGORIA, AcaoEnum.INSERT,
				categoria.getTransactionId(), getHistoricoBAR(), response, categoria.getId(), categoria.getUserId());

		if (categoria.getId() != 0 && categoria.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, categoria.getId(), null,
					AcaoEnum.INSERT, categoria.getCreateUser(), categoria.getId(), TabelaEnum.CATEGORIA, statusBAR,
					historicoBAR, categoria.getTransactionId(), categoria.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICategoriaBAR#updateCategoria(com.qat.
	 * samples.sysmgmt.base.model.Categoria)
	 */
	@Override
	public InternalResponse updateCategoria(Categoria categoria) {
		InternalResponse response = new InternalResponse();
		categoria.setProcessId(categoria.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CATEGORIA, categoria, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CATEGORIA, AcaoEnum.UPDATE,
				categoria.getTransactionId(), getHistoricoBAR(), response, categoria.getId(), categoria.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICategoriaBAR#deleteCategoria(com.qat.
	 * samples.sysmgmt.base.model.Categoria)
	 */
	@Override
	public InternalResponse deleteCategoriaById(Categoria categoria) {
		InternalResponse response = new InternalResponse();
		categoria.setProcessId(categoria.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CATEGORIA, categoria, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CATEGORIA, AcaoEnum.DELETE,
				categoria.getTransactionId(), getHistoricoBAR(), response, categoria.getId(), categoria.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICategoriaBAR#deleteAllCategorias()
	 */
	@Override
	public InternalResponse deleteAllCategorias() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CATEGORIA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICategoriaBAR#fetchCategoriaById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Categoria fetchCategoriaById(FetchByIdRequest request) {
		return (Categoria) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CATEGORIA,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICategoriaBAR#fetchAllCategoriasCache()
	 */
	@Override
	public InternalResultsResponse<Categoria> fetchAllCategorias(Categoria categoria) {
		InternalResultsResponse<Categoria> response = new InternalResultsResponse<Categoria>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CATEGORIA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICategoriaBAR#fetchCategoriasByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Categoria> fetchCategoriasByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Categoria> response = new InternalResultsResponse<Categoria>();
		fetchCategoriasByRequest(getSqlSession(), request, STMT_FETCH_CATEGORIA_COUNT, STMT_FETCH_CATEGORIA_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchCategoriasByRequest
	// ####======================================

	public static void fetchCategoriasByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	//===================================### ICMSOPINTER ####======================================
		/**
	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IICMSOpInterBAR#insertICMSOpInter(com.qat.samples.sysmgmt.base.model.ICMSOpInter)
	 */
	@Override
	public InternalResponse insertICMSOpInter(ICMSOpInter icmsopinter)
	{
		InternalResponse response = new InternalResponse();
			Integer count = 0;
			Boolean count1 = false;

	icmsopinter.setProcessId(icmsopinter.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ICMSOPINTER, icmsopinter, response);


	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ICMSOPINTER, AcaoEnum.INSERT, icmsopinter.getTransactionId(),
				getHistoricoBAR(), response, icmsopinter.getId(),icmsopinter.getUserId());


	if (icmsopinter.getId() !=  0 && icmsopinter.getId() != null)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 =
					StatusBARD.maintainStatusAssociations(statusList, response, icmsopinter.getId(), null, AcaoEnum.INSERT,
							icmsopinter.getCreateUser(), icmsopinter.getId(), TabelaEnum.ICMSOPINTER, statusBAR,
							historicoBAR, icmsopinter.getTransactionId(), icmsopinter.getTransactionId());

		}


		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IICMSOpInterBAR#updateICMSOpInter(com.qat.samples.sysmgmt.base.model.ICMSOpInter)
	 */
	@Override
	public InternalResponse updateICMSOpInter(ICMSOpInter icmsopinter)
	{
		InternalResponse response = new InternalResponse();
	 icmsopinter.setProcessId(icmsopinter.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ICMSOPINTER, icmsopinter, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ICMSOPINTER, AcaoEnum.UPDATE, icmsopinter.getTransactionId(),
				getHistoricoBAR(), response, icmsopinter.getId(),icmsopinter.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IICMSOpInterBAR#deleteICMSOpInter(com.qat.samples.sysmgmt.base.model.ICMSOpInter)
	 */
	@Override
	public InternalResponse deleteICMSOpInterById(ICMSOpInter icmsopinter)
	{
		InternalResponse response = new InternalResponse();
	icmsopinter.setProcessId(icmsopinter.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ICMSOPINTER, icmsopinter, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ICMSOPINTER, AcaoEnum.DELETE, icmsopinter.getTransactionId(),
				getHistoricoBAR(), response, icmsopinter.getId(),icmsopinter.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IICMSOpInterBAR#deleteAllICMSOpInters()
	 */
	@Override
	public InternalResponse deleteAllICMSOpInters()
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ICMSOPINTER_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bar.IICMSOpInterBAR#fetchICMSOpInterById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ICMSOpInter fetchICMSOpInterById(FetchByIdRequest request)
	{
	return (ICMSOpInter)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ICMSOPINTER, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IICMSOpInterBAR#fetchAllICMSOpIntersCache()
	 */
	@Override
	public InternalResultsResponse<ICMSOpInter> fetchAllICMSOpInters(ICMSOpInter icmsopinter)
	{
		InternalResultsResponse<ICMSOpInter> response = new InternalResultsResponse<ICMSOpInter>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ICMSOPINTER_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bar.IICMSOpInterBAR#fetchICMSOpIntersByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ICMSOpInter> fetchICMSOpIntersByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<ICMSOpInter> response = new InternalResultsResponse<ICMSOpInter>();
		fetchICMSOpIntersByRequest(getSqlSession(), request, STMT_FETCH_ICMSOPINTER_COUNT, STMT_FETCH_ICMSOPINTER_ALL_REQUEST,
				response);
		return response;
	}

	//===================================### fetchICMSOpIntersByRequest ####======================================

	public static void fetchICMSOpIntersByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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
