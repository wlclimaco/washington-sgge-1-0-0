/** create by system gera-java version 1.0.0 03/12/2016 15:59 : 25*/
package com.qat.samples.sysmgmt.bar.mybatis.Nfe;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Nfe.INFNotaInfoItemBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.InsertHistBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFImpostoDevolvidoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFInformacaoImpostoDevolvidoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemDetalheExportacaoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemExportacaoIndiretaBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoCOFINSAliquotaBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoCOFINSBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoCOFINSNaoTributavelBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoCOFINSOutrasOperacoesBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoCOFINSQuantidadeBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoCOFINSSTBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMS00BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMS10BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMS20BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMS30BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMS40BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMS51BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMS60BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMS70BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMS90BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMSBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMSPartilhadoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMSSN101BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMSSN102BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMSSN201BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMSSN202BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMSSN500BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMSSN900BARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMSSTBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoICMSUFDestinoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoIPIBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoIPINaoTributadoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoIPITributadoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoISSQNBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoImportacaoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoPISAliquotaBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoPISBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoPISNaoTributadoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoPISOutrasOperacoesBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoPISQuantidadeBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemImpostoPISSTBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemProdutoArmamentoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemProdutoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemProdutoCombustivelBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemProdutoCombustivelCIDEBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemProdutoDeclaracaoImportacaoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemProdutoMedicamentoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemProdutoVeiculoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.StatusBARD;
import com.qat.samples.sysmgmt.nfeItens.model.NFImpostoDevolvido;
import com.qat.samples.sysmgmt.nfeItens.model.NFInformacaoImpostoDevolvido;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItem;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemDetalheExportacao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemExportacaoIndireta;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImposto;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINS;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSAliquota;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSNaoTributavel;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSOutrasOperacoes;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSQuantidade;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSST;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS00;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS10;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS20;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS30;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS40;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS51;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS60;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS70;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS90;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSPartilhado;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN101;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN102;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN201;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN202;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN500;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN900;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSST;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSUFDestino;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoIPI;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoIPINaoTributado;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoIPITributado;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoISSQN;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoImportacao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPIS;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISAliquota;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISNaoTributado;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISOutrasOperacoes;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISQuantidade;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISST;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProduto;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoArmamento;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoCombustivel;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoCombustivelCIDE;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoDeclaracaoImportacao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoMedicamento;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoVeiculo;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class NFNotaInfoItemBARImpl extends SqlSessionDaoSupport implements INFNotaInfoItemBAR {

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/// ===================================### NFNOTAINFOITEM
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEM = "NFNotaInfoItemMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEM. */
	private static final String STMT_INSERT_NFNOTAINFOITEM = NAMESPACE_NFNOTAINFOITEM + "insertNFNotaInfoItem";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEM. */
	private static final String STMT_UPDATE_NFNOTAINFOITEM = NAMESPACE_NFNOTAINFOITEM + "updateNFNotaInfoItem";

	/** The Constant STMT_DELETE_NFNOTAINFOITEM. */
	private static final String STMT_DELETE_NFNOTAINFOITEM = NAMESPACE_NFNOTAINFOITEM + "deleteNFNotaInfoItemById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEM_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEM_ALL = NAMESPACE_NFNOTAINFOITEM + "deleteAllNFNotaInfoItems";

	/** The Constant STMT_FETCH_NFNOTAINFOITEM. */
	private static final String STMT_FETCH_NFNOTAINFOITEM = NAMESPACE_NFNOTAINFOITEM + "fetchNFNotaInfoItemById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEM_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEM_ALL = NAMESPACE_NFNOTAINFOITEM + "fetchAllNFNotaInfoItems";

	/** The Constant STMT_FETCH_NFNOTAINFOITEM_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEM_COUNT = NAMESPACE_NFNOTAINFOITEM
			+ "fetchNFNotaInfoItemRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEM_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEM_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEM
			+ "fetchAllNFNotaInfoItemsRequest";

	/// ===================================### NFNOTAINFOITEMPRODUTO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMPRODUTO = "NFNotaInfoItemProdutoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMPRODUTO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMPRODUTO = NAMESPACE_NFNOTAINFOITEMPRODUTO
			+ "insertNFNotaInfoItemProduto";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMPRODUTO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMPRODUTO = NAMESPACE_NFNOTAINFOITEMPRODUTO
			+ "updateNFNotaInfoItemProduto";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTO = NAMESPACE_NFNOTAINFOITEMPRODUTO
			+ "deleteNFNotaInfoItemProdutoById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTO_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTO
			+ "deleteAllNFNotaInfoItemProdutos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTO = NAMESPACE_NFNOTAINFOITEMPRODUTO
			+ "fetchNFNotaInfoItemProdutoById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTO_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTO
			+ "fetchAllNFNotaInfoItemProdutos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTO_COUNT = NAMESPACE_NFNOTAINFOITEMPRODUTO
			+ "fetchNFNotaInfoItemProdutoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMPRODUTO
			+ "fetchAllNFNotaInfoItemProdutosRequest";

	/// ===================================###
	/// NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO = "NFNotaInfoItemProdutoDeclaracaoImportacaoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO
			+ "insertNFNotaInfoItemProdutoDeclaracaoImportacao";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO
			+ "updateNFNotaInfoItemProdutoDeclaracaoImportacao";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO
			+ "deleteNFNotaInfoItemProdutoDeclaracaoImportacaoById";

	/**
	 * The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO_ALL.
	 */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO
			+ "deleteAllNFNotaInfoItemProdutoDeclaracaoImportacaos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO
			+ "fetchNFNotaInfoItemProdutoDeclaracaoImportacaoById";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO_ALL.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO
			+ "fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaos";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO_COUNT.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO_COUNT = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO
			+ "fetchNFNotaInfoItemProdutoDeclaracaoImportacaoRowCount";

	/**
	 * The Constant
	 * STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO
			+ "fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaosRequest";

	/// ===================================###
	/// NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO = "NFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoMap.";

	/**
	 * The Constant STMT_INSERT_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO.
	 */
	private static final String STMT_INSERT_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO
			+ "insertNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao";

	/**
	 * The Constant STMT_UPDATE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO.
	 */
	private static final String STMT_UPDATE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO
			+ "updateNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao";

	/**
	 * The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO.
	 */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO
			+ "deleteNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById";

	/**
	 * The Constant
	 * STMT_DELETE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO_ALL.
	 */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO
			+ "deleteAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO
			+ "fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById";

	/**
	 * The Constant
	 * STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO_ALL.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO
			+ "fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos";

	/**
	 * The Constant
	 * STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO_COUNT.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO_COUNT = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO
			+ "fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoRowCount";

	/**
	 * The Constant
	 * STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO
			+ "fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosRequest";

	/// ===================================### NFNOTAINFOITEMDETALHEEXPORTACAO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMDETALHEEXPORTACAO = "NFNotaInfoItemDetalheExportacaoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMDETALHEEXPORTACAO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMDETALHEEXPORTACAO = NAMESPACE_NFNOTAINFOITEMDETALHEEXPORTACAO
			+ "insertNFNotaInfoItemDetalheExportacao";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMDETALHEEXPORTACAO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMDETALHEEXPORTACAO = NAMESPACE_NFNOTAINFOITEMDETALHEEXPORTACAO
			+ "updateNFNotaInfoItemDetalheExportacao";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMDETALHEEXPORTACAO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMDETALHEEXPORTACAO = NAMESPACE_NFNOTAINFOITEMDETALHEEXPORTACAO
			+ "deleteNFNotaInfoItemDetalheExportacaoById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMDETALHEEXPORTACAO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMDETALHEEXPORTACAO_ALL = NAMESPACE_NFNOTAINFOITEMDETALHEEXPORTACAO
			+ "deleteAllNFNotaInfoItemDetalheExportacaos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMDETALHEEXPORTACAO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMDETALHEEXPORTACAO = NAMESPACE_NFNOTAINFOITEMDETALHEEXPORTACAO
			+ "fetchNFNotaInfoItemDetalheExportacaoById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMDETALHEEXPORTACAO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMDETALHEEXPORTACAO_ALL = NAMESPACE_NFNOTAINFOITEMDETALHEEXPORTACAO
			+ "fetchAllNFNotaInfoItemDetalheExportacaos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMDETALHEEXPORTACAO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMDETALHEEXPORTACAO_COUNT = NAMESPACE_NFNOTAINFOITEMDETALHEEXPORTACAO
			+ "fetchNFNotaInfoItemDetalheExportacaoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMDETALHEEXPORTACAO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMDETALHEEXPORTACAO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMDETALHEEXPORTACAO
			+ "fetchAllNFNotaInfoItemDetalheExportacaosRequest";

	/// ===================================### NFNOTAINFOITEMEXPORTACAOINDIRETA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMEXPORTACAOINDIRETA = "NFNotaInfoItemExportacaoIndiretaMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMEXPORTACAOINDIRETA. */
	private static final String STMT_INSERT_NFNOTAINFOITEMEXPORTACAOINDIRETA = NAMESPACE_NFNOTAINFOITEMEXPORTACAOINDIRETA
			+ "insertNFNotaInfoItemExportacaoIndireta";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMEXPORTACAOINDIRETA. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMEXPORTACAOINDIRETA = NAMESPACE_NFNOTAINFOITEMEXPORTACAOINDIRETA
			+ "updateNFNotaInfoItemExportacaoIndireta";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMEXPORTACAOINDIRETA. */
	private static final String STMT_DELETE_NFNOTAINFOITEMEXPORTACAOINDIRETA = NAMESPACE_NFNOTAINFOITEMEXPORTACAOINDIRETA
			+ "deleteNFNotaInfoItemExportacaoIndiretaById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMEXPORTACAOINDIRETA_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMEXPORTACAOINDIRETA_ALL = NAMESPACE_NFNOTAINFOITEMEXPORTACAOINDIRETA
			+ "deleteAllNFNotaInfoItemExportacaoIndiretas";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMEXPORTACAOINDIRETA. */
	private static final String STMT_FETCH_NFNOTAINFOITEMEXPORTACAOINDIRETA = NAMESPACE_NFNOTAINFOITEMEXPORTACAOINDIRETA
			+ "fetchNFNotaInfoItemExportacaoIndiretaById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMEXPORTACAOINDIRETA_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMEXPORTACAOINDIRETA_ALL = NAMESPACE_NFNOTAINFOITEMEXPORTACAOINDIRETA
			+ "fetchAllNFNotaInfoItemExportacaoIndiretas";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMEXPORTACAOINDIRETA_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMEXPORTACAOINDIRETA_COUNT = NAMESPACE_NFNOTAINFOITEMEXPORTACAOINDIRETA
			+ "fetchNFNotaInfoItemExportacaoIndiretaRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMEXPORTACAOINDIRETA_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMEXPORTACAOINDIRETA_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMEXPORTACAOINDIRETA
			+ "fetchAllNFNotaInfoItemExportacaoIndiretasRequest";

	/// ===================================### NFNOTAINFOITEMPRODUTOVEICULO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMPRODUTOVEICULO = "NFNotaInfoItemProdutoVeiculoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMPRODUTOVEICULO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMPRODUTOVEICULO = NAMESPACE_NFNOTAINFOITEMPRODUTOVEICULO
			+ "insertNFNotaInfoItemProdutoVeiculo";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMPRODUTOVEICULO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMPRODUTOVEICULO = NAMESPACE_NFNOTAINFOITEMPRODUTOVEICULO
			+ "updateNFNotaInfoItemProdutoVeiculo";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTOVEICULO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTOVEICULO = NAMESPACE_NFNOTAINFOITEMPRODUTOVEICULO
			+ "deleteNFNotaInfoItemProdutoVeiculoById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTOVEICULO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTOVEICULO_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTOVEICULO
			+ "deleteAllNFNotaInfoItemProdutoVeiculos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOVEICULO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOVEICULO = NAMESPACE_NFNOTAINFOITEMPRODUTOVEICULO
			+ "fetchNFNotaInfoItemProdutoVeiculoById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOVEICULO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOVEICULO_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTOVEICULO
			+ "fetchAllNFNotaInfoItemProdutoVeiculos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOVEICULO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOVEICULO_COUNT = NAMESPACE_NFNOTAINFOITEMPRODUTOVEICULO
			+ "fetchNFNotaInfoItemProdutoVeiculoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOVEICULO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOVEICULO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMPRODUTOVEICULO
			+ "fetchAllNFNotaInfoItemProdutoVeiculosRequest";

	/// ===================================### NFNOTAINFOITEMPRODUTOMEDICAMENTO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMPRODUTOMEDICAMENTO = "NFNotaInfoItemProdutoMedicamentoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMPRODUTOMEDICAMENTO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMPRODUTOMEDICAMENTO = NAMESPACE_NFNOTAINFOITEMPRODUTOMEDICAMENTO
			+ "insertNFNotaInfoItemProdutoMedicamento";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMPRODUTOMEDICAMENTO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMPRODUTOMEDICAMENTO = NAMESPACE_NFNOTAINFOITEMPRODUTOMEDICAMENTO
			+ "updateNFNotaInfoItemProdutoMedicamento";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTOMEDICAMENTO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTOMEDICAMENTO = NAMESPACE_NFNOTAINFOITEMPRODUTOMEDICAMENTO
			+ "deleteNFNotaInfoItemProdutoMedicamentoById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTOMEDICAMENTO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTOMEDICAMENTO_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTOMEDICAMENTO
			+ "deleteAllNFNotaInfoItemProdutoMedicamentos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOMEDICAMENTO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOMEDICAMENTO = NAMESPACE_NFNOTAINFOITEMPRODUTOMEDICAMENTO
			+ "fetchNFNotaInfoItemProdutoMedicamentoById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOMEDICAMENTO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOMEDICAMENTO_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTOMEDICAMENTO
			+ "fetchAllNFNotaInfoItemProdutoMedicamentos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOMEDICAMENTO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOMEDICAMENTO_COUNT = NAMESPACE_NFNOTAINFOITEMPRODUTOMEDICAMENTO
			+ "fetchNFNotaInfoItemProdutoMedicamentoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOMEDICAMENTO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOMEDICAMENTO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMPRODUTOMEDICAMENTO
			+ "fetchAllNFNotaInfoItemProdutoMedicamentosRequest";

	/// ===================================### NFNOTAINFOITEMPRODUTOARMAMENTO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMPRODUTOARMAMENTO = "NFNotaInfoItemProdutoArmamentoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMPRODUTOARMAMENTO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMPRODUTOARMAMENTO = NAMESPACE_NFNOTAINFOITEMPRODUTOARMAMENTO
			+ "insertNFNotaInfoItemProdutoArmamento";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMPRODUTOARMAMENTO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMPRODUTOARMAMENTO = NAMESPACE_NFNOTAINFOITEMPRODUTOARMAMENTO
			+ "updateNFNotaInfoItemProdutoArmamento";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTOARMAMENTO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTOARMAMENTO = NAMESPACE_NFNOTAINFOITEMPRODUTOARMAMENTO
			+ "deleteNFNotaInfoItemProdutoArmamentoById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTOARMAMENTO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTOARMAMENTO_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTOARMAMENTO
			+ "deleteAllNFNotaInfoItemProdutoArmamentos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOARMAMENTO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOARMAMENTO = NAMESPACE_NFNOTAINFOITEMPRODUTOARMAMENTO
			+ "fetchNFNotaInfoItemProdutoArmamentoById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOARMAMENTO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOARMAMENTO_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTOARMAMENTO
			+ "fetchAllNFNotaInfoItemProdutoArmamentos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOARMAMENTO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOARMAMENTO_COUNT = NAMESPACE_NFNOTAINFOITEMPRODUTOARMAMENTO
			+ "fetchNFNotaInfoItemProdutoArmamentoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOARMAMENTO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOARMAMENTO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMPRODUTOARMAMENTO
			+ "fetchAllNFNotaInfoItemProdutoArmamentosRequest";

	/// ===================================### NFNOTAINFOITEMPRODUTOCOMBUSTIVEL
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL = "NFNotaInfoItemProdutoCombustivelMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL. */
	private static final String STMT_INSERT_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL
			+ "insertNFNotaInfoItemProdutoCombustivel";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL
			+ "updateNFNotaInfoItemProdutoCombustivel";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL
			+ "deleteNFNotaInfoItemProdutoCombustivelById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL
			+ "deleteAllNFNotaInfoItemProdutoCombustivels";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL
			+ "fetchNFNotaInfoItemProdutoCombustivelById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL
			+ "fetchAllNFNotaInfoItemProdutoCombustivels";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL_COUNT = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL
			+ "fetchNFNotaInfoItemProdutoCombustivelRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL
			+ "fetchAllNFNotaInfoItemProdutoCombustivelsRequest";

	/// ===================================###
	/// NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE = "NFNotaInfoItemProdutoCombustivelCIDEMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE. */
	private static final String STMT_INSERT_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE
			+ "insertNFNotaInfoItemProdutoCombustivelCIDE";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE
			+ "updateNFNotaInfoItemProdutoCombustivelCIDE";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE
			+ "deleteNFNotaInfoItemProdutoCombustivelCIDEById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE
			+ "deleteAllNFNotaInfoItemProdutoCombustivelCIDEs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE
			+ "fetchNFNotaInfoItemProdutoCombustivelCIDEById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE_ALL = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE
			+ "fetchAllNFNotaInfoItemProdutoCombustivelCIDEs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE_COUNT = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE
			+ "fetchNFNotaInfoItemProdutoCombustivelCIDERowCount";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE
			+ "fetchAllNFNotaInfoItemProdutoCombustivelCIDEsRequest";

	/// ===================================### NFIMPOSTODEVOLVIDO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFIMPOSTODEVOLVIDO = "NFImpostoDevolvidoMap.";

	/** The Constant STMT_INSERT_NFIMPOSTODEVOLVIDO. */
	private static final String STMT_INSERT_NFIMPOSTODEVOLVIDO = NAMESPACE_NFIMPOSTODEVOLVIDO
			+ "insertNFImpostoDevolvido";

	/** The Constant STMT_UPDATE_NFIMPOSTODEVOLVIDO. */
	private static final String STMT_UPDATE_NFIMPOSTODEVOLVIDO = NAMESPACE_NFIMPOSTODEVOLVIDO
			+ "updateNFImpostoDevolvido";

	/** The Constant STMT_DELETE_NFIMPOSTODEVOLVIDO. */
	private static final String STMT_DELETE_NFIMPOSTODEVOLVIDO = NAMESPACE_NFIMPOSTODEVOLVIDO
			+ "deleteNFImpostoDevolvidoById";

	/** The Constant STMT_DELETE_NFIMPOSTODEVOLVIDO_ALL. */
	private static final String STMT_DELETE_NFIMPOSTODEVOLVIDO_ALL = NAMESPACE_NFIMPOSTODEVOLVIDO
			+ "deleteAllNFImpostoDevolvidos";

	/** The Constant STMT_FETCH_NFIMPOSTODEVOLVIDO. */
	private static final String STMT_FETCH_NFIMPOSTODEVOLVIDO = NAMESPACE_NFIMPOSTODEVOLVIDO
			+ "fetchNFImpostoDevolvidoById";

	/** The Constant STMT_FETCH_NFIMPOSTODEVOLVIDO_ALL. */
	private static final String STMT_FETCH_NFIMPOSTODEVOLVIDO_ALL = NAMESPACE_NFIMPOSTODEVOLVIDO
			+ "fetchAllNFImpostoDevolvidos";

	/** The Constant STMT_FETCH_NFIMPOSTODEVOLVIDO_COUNT. */
	private static final String STMT_FETCH_NFIMPOSTODEVOLVIDO_COUNT = NAMESPACE_NFIMPOSTODEVOLVIDO
			+ "fetchNFImpostoDevolvidoRowCount";

	/** The Constant STMT_FETCH_NFIMPOSTODEVOLVIDO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFIMPOSTODEVOLVIDO_ALL_REQUEST = NAMESPACE_NFIMPOSTODEVOLVIDO
			+ "fetchAllNFImpostoDevolvidosRequest";

	/// ===================================### NFINFORMACAOIMPOSTODEVOLVIDO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFINFORMACAOIMPOSTODEVOLVIDO = "NFInformacaoImpostoDevolvidoMap.";

	/** The Constant STMT_INSERT_NFINFORMACAOIMPOSTODEVOLVIDO. */
	private static final String STMT_INSERT_NFINFORMACAOIMPOSTODEVOLVIDO = NAMESPACE_NFINFORMACAOIMPOSTODEVOLVIDO
			+ "insertNFInformacaoImpostoDevolvido";

	/** The Constant STMT_UPDATE_NFINFORMACAOIMPOSTODEVOLVIDO. */
	private static final String STMT_UPDATE_NFINFORMACAOIMPOSTODEVOLVIDO = NAMESPACE_NFINFORMACAOIMPOSTODEVOLVIDO
			+ "updateNFInformacaoImpostoDevolvido";

	/** The Constant STMT_DELETE_NFINFORMACAOIMPOSTODEVOLVIDO. */
	private static final String STMT_DELETE_NFINFORMACAOIMPOSTODEVOLVIDO = NAMESPACE_NFINFORMACAOIMPOSTODEVOLVIDO
			+ "deleteNFInformacaoImpostoDevolvidoById";

	/** The Constant STMT_DELETE_NFINFORMACAOIMPOSTODEVOLVIDO_ALL. */
	private static final String STMT_DELETE_NFINFORMACAOIMPOSTODEVOLVIDO_ALL = NAMESPACE_NFINFORMACAOIMPOSTODEVOLVIDO
			+ "deleteAllNFInformacaoImpostoDevolvidos";

	/** The Constant STMT_FETCH_NFINFORMACAOIMPOSTODEVOLVIDO. */
	private static final String STMT_FETCH_NFINFORMACAOIMPOSTODEVOLVIDO = NAMESPACE_NFINFORMACAOIMPOSTODEVOLVIDO
			+ "fetchNFInformacaoImpostoDevolvidoById";

	/** The Constant STMT_FETCH_NFINFORMACAOIMPOSTODEVOLVIDO_ALL. */
	private static final String STMT_FETCH_NFINFORMACAOIMPOSTODEVOLVIDO_ALL = NAMESPACE_NFINFORMACAOIMPOSTODEVOLVIDO
			+ "fetchAllNFInformacaoImpostoDevolvidos";

	/** The Constant STMT_FETCH_NFINFORMACAOIMPOSTODEVOLVIDO_COUNT. */
	private static final String STMT_FETCH_NFINFORMACAOIMPOSTODEVOLVIDO_COUNT = NAMESPACE_NFINFORMACAOIMPOSTODEVOLVIDO
			+ "fetchNFInformacaoImpostoDevolvidoRowCount";

	/** The Constant STMT_FETCH_NFINFORMACAOIMPOSTODEVOLVIDO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFINFORMACAOIMPOSTODEVOLVIDO_ALL_REQUEST = NAMESPACE_NFINFORMACAOIMPOSTODEVOLVIDO
			+ "fetchAllNFInformacaoImpostoDevolvidosRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTO = "NFNotaInfoItemImpostoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTO = NAMESPACE_NFNOTAINFOITEMIMPOSTO
			+ "insertNFNotaInfoItemImposto";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTO = NAMESPACE_NFNOTAINFOITEMIMPOSTO
			+ "updateNFNotaInfoItemImposto";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTO = NAMESPACE_NFNOTAINFOITEMIMPOSTO
			+ "deleteNFNotaInfoItemImpostoById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTO
			+ "deleteAllNFNotaInfoItemImpostos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTO = NAMESPACE_NFNOTAINFOITEMIMPOSTO
			+ "fetchNFNotaInfoItemImpostoById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTO
			+ "fetchAllNFNotaInfoItemImpostos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTO_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTO
			+ "fetchNFNotaInfoItemImpostoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTO
			+ "fetchAllNFNotaInfoItemImpostosRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS = "NFNotaInfoItemImpostoICMSMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS
			+ "insertNFNotaInfoItemImpostoICMS";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS
			+ "updateNFNotaInfoItemImpostoICMS";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS
			+ "deleteNFNotaInfoItemImpostoICMSById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS
			+ "deleteAllNFNotaInfoItemImpostoICMSs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS
			+ "fetchNFNotaInfoItemImpostoICMSById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS
			+ "fetchAllNFNotaInfoItemImpostoICMSs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS
			+ "fetchNFNotaInfoItemImpostoICMSRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS
			+ "fetchAllNFNotaInfoItemImpostoICMSsRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMS00
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS00 = "NFNotaInfoItemImpostoICMS00Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS00. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS00 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS00
			+ "insertNFNotaInfoItemImpostoICMS00";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS00. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS00 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS00
			+ "updateNFNotaInfoItemImpostoICMS00";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS00. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS00 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS00
			+ "deleteNFNotaInfoItemImpostoICMS00ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS00_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS00_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS00
			+ "deleteAllNFNotaInfoItemImpostoICMS00s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS00. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS00 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS00
			+ "fetchNFNotaInfoItemImpostoICMS00ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS00_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS00_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS00
			+ "fetchAllNFNotaInfoItemImpostoICMS00s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS00_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS00_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS00
			+ "fetchNFNotaInfoItemImpostoICMS00RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS00_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS00_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS00
			+ "fetchAllNFNotaInfoItemImpostoICMS00sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMS10
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS10 = "NFNotaInfoItemImpostoICMS10Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS10. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS10 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS10
			+ "insertNFNotaInfoItemImpostoICMS10";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS10. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS10 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS10
			+ "updateNFNotaInfoItemImpostoICMS10";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS10. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS10 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS10
			+ "deleteNFNotaInfoItemImpostoICMS10ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS10_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS10_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS10
			+ "deleteAllNFNotaInfoItemImpostoICMS10s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS10. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS10 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS10
			+ "fetchNFNotaInfoItemImpostoICMS10ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS10_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS10_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS10
			+ "fetchAllNFNotaInfoItemImpostoICMS10s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS10_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS10_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS10
			+ "fetchNFNotaInfoItemImpostoICMS10RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS10_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS10_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS10
			+ "fetchAllNFNotaInfoItemImpostoICMS10sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMS20
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS20 = "NFNotaInfoItemImpostoICMS20Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS20. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS20 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS20
			+ "insertNFNotaInfoItemImpostoICMS20";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS20. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS20 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS20
			+ "updateNFNotaInfoItemImpostoICMS20";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS20. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS20 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS20
			+ "deleteNFNotaInfoItemImpostoICMS20ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS20_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS20_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS20
			+ "deleteAllNFNotaInfoItemImpostoICMS20s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS20. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS20 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS20
			+ "fetchNFNotaInfoItemImpostoICMS20ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS20_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS20_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS20
			+ "fetchAllNFNotaInfoItemImpostoICMS20s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS20_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS20_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS20
			+ "fetchNFNotaInfoItemImpostoICMS20RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS20_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS20_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS20
			+ "fetchAllNFNotaInfoItemImpostoICMS20sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMS30
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS30 = "NFNotaInfoItemImpostoICMS30Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS30. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS30 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS30
			+ "insertNFNotaInfoItemImpostoICMS30";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS30. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS30 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS30
			+ "updateNFNotaInfoItemImpostoICMS30";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS30. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS30 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS30
			+ "deleteNFNotaInfoItemImpostoICMS30ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS30_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS30_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS30
			+ "deleteAllNFNotaInfoItemImpostoICMS30s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS30. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS30 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS30
			+ "fetchNFNotaInfoItemImpostoICMS30ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS30_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS30_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS30
			+ "fetchAllNFNotaInfoItemImpostoICMS30s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS30_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS30_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS30
			+ "fetchNFNotaInfoItemImpostoICMS30RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS30_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS30_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS30
			+ "fetchAllNFNotaInfoItemImpostoICMS30sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMS40
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS40 = "NFNotaInfoItemImpostoICMS40Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS40. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS40 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS40
			+ "insertNFNotaInfoItemImpostoICMS40";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS40. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS40 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS40
			+ "updateNFNotaInfoItemImpostoICMS40";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS40. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS40 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS40
			+ "deleteNFNotaInfoItemImpostoICMS40ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS40_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS40_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS40
			+ "deleteAllNFNotaInfoItemImpostoICMS40s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS40. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS40 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS40
			+ "fetchNFNotaInfoItemImpostoICMS40ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS40_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS40_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS40
			+ "fetchAllNFNotaInfoItemImpostoICMS40s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS40_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS40_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS40
			+ "fetchNFNotaInfoItemImpostoICMS40RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS40_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS40_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS40
			+ "fetchAllNFNotaInfoItemImpostoICMS40sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMS51
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS51 = "NFNotaInfoItemImpostoICMS51Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS51. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS51 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS51
			+ "insertNFNotaInfoItemImpostoICMS51";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS51. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS51 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS51
			+ "updateNFNotaInfoItemImpostoICMS51";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS51. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS51 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS51
			+ "deleteNFNotaInfoItemImpostoICMS51ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS51_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS51_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS51
			+ "deleteAllNFNotaInfoItemImpostoICMS51s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS51. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS51 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS51
			+ "fetchNFNotaInfoItemImpostoICMS51ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS51_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS51_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS51
			+ "fetchAllNFNotaInfoItemImpostoICMS51s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS51_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS51_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS51
			+ "fetchNFNotaInfoItemImpostoICMS51RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS51_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS51_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS51
			+ "fetchAllNFNotaInfoItemImpostoICMS51sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMS60
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS60 = "NFNotaInfoItemImpostoICMS60Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS60. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS60 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS60
			+ "insertNFNotaInfoItemImpostoICMS60";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS60. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS60 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS60
			+ "updateNFNotaInfoItemImpostoICMS60";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS60. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS60 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS60
			+ "deleteNFNotaInfoItemImpostoICMS60ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS60_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS60_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS60
			+ "deleteAllNFNotaInfoItemImpostoICMS60s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS60. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS60 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS60
			+ "fetchNFNotaInfoItemImpostoICMS60ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS60_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS60_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS60
			+ "fetchAllNFNotaInfoItemImpostoICMS60s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS60_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS60_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS60
			+ "fetchNFNotaInfoItemImpostoICMS60RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS60_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS60_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS60
			+ "fetchAllNFNotaInfoItemImpostoICMS60sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMS70
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS70 = "NFNotaInfoItemImpostoICMS70Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS70. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS70 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS70
			+ "insertNFNotaInfoItemImpostoICMS70";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS70. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS70 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS70
			+ "updateNFNotaInfoItemImpostoICMS70";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS70. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS70 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS70
			+ "deleteNFNotaInfoItemImpostoICMS70ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS70_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS70_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS70
			+ "deleteAllNFNotaInfoItemImpostoICMS70s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS70. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS70 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS70
			+ "fetchNFNotaInfoItemImpostoICMS70ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS70_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS70_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS70
			+ "fetchAllNFNotaInfoItemImpostoICMS70s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS70_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS70_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS70
			+ "fetchNFNotaInfoItemImpostoICMS70RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS70_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS70_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS70
			+ "fetchAllNFNotaInfoItemImpostoICMS70sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMS90
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS90 = "NFNotaInfoItemImpostoICMS90Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS90. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS90 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS90
			+ "insertNFNotaInfoItemImpostoICMS90";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS90. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS90 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS90
			+ "updateNFNotaInfoItemImpostoICMS90";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS90. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS90 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS90
			+ "deleteNFNotaInfoItemImpostoICMS90ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS90_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS90_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS90
			+ "deleteAllNFNotaInfoItemImpostoICMS90s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS90. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS90 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS90
			+ "fetchNFNotaInfoItemImpostoICMS90ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS90_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS90_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS90
			+ "fetchAllNFNotaInfoItemImpostoICMS90s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS90_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS90_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS90
			+ "fetchNFNotaInfoItemImpostoICMS90RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS90_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS90_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMS90
			+ "fetchAllNFNotaInfoItemImpostoICMS90sRequest";

	/// ===================================###
	/// NFNOTAINFOITEMIMPOSTOICMSPARTILHADO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO = "NFNotaInfoItemImpostoICMSPartilhadoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO
			+ "insertNFNotaInfoItemImpostoICMSPartilhado";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO
			+ "updateNFNotaInfoItemImpostoICMSPartilhado";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO
			+ "deleteNFNotaInfoItemImpostoICMSPartilhadoById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO
			+ "deleteAllNFNotaInfoItemImpostoICMSPartilhados";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO
			+ "fetchNFNotaInfoItemImpostoICMSPartilhadoById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO
			+ "fetchAllNFNotaInfoItemImpostoICMSPartilhados";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO
			+ "fetchNFNotaInfoItemImpostoICMSPartilhadoRowCount";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO
			+ "fetchAllNFNotaInfoItemImpostoICMSPartilhadosRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMSST
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSST = "NFNotaInfoItemImpostoICMSSTMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSST. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSST
			+ "insertNFNotaInfoItemImpostoICMSST";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSST. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSST
			+ "updateNFNotaInfoItemImpostoICMSST";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSST. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSST
			+ "deleteNFNotaInfoItemImpostoICMSSTById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSST_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSST_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSST
			+ "deleteAllNFNotaInfoItemImpostoICMSSTs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSST
			+ "fetchNFNotaInfoItemImpostoICMSSTById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSST_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSST_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSST
			+ "fetchAllNFNotaInfoItemImpostoICMSSTs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSST_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSST_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSST
			+ "fetchNFNotaInfoItemImpostoICMSSTRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSST_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSST_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSST
			+ "fetchAllNFNotaInfoItemImpostoICMSSTsRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN101
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN101 = "NFNotaInfoItemImpostoICMSSN101Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN101. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN101 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN101
			+ "insertNFNotaInfoItemImpostoICMSSN101";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN101. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN101 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN101
			+ "updateNFNotaInfoItemImpostoICMSSN101";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN101. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN101 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN101
			+ "deleteNFNotaInfoItemImpostoICMSSN101ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN101_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN101_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN101
			+ "deleteAllNFNotaInfoItemImpostoICMSSN101s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN101. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN101 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN101
			+ "fetchNFNotaInfoItemImpostoICMSSN101ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN101_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN101_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN101
			+ "fetchAllNFNotaInfoItemImpostoICMSSN101s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN101_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN101_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN101
			+ "fetchNFNotaInfoItemImpostoICMSSN101RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN101_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN101_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN101
			+ "fetchAllNFNotaInfoItemImpostoICMSSN101sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN102
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN102 = "NFNotaInfoItemImpostoICMSSN102Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN102. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN102 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN102
			+ "insertNFNotaInfoItemImpostoICMSSN102";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN102. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN102 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN102
			+ "updateNFNotaInfoItemImpostoICMSSN102";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN102. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN102 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN102
			+ "deleteNFNotaInfoItemImpostoICMSSN102ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN102_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN102_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN102
			+ "deleteAllNFNotaInfoItemImpostoICMSSN102s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN102. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN102 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN102
			+ "fetchNFNotaInfoItemImpostoICMSSN102ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN102_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN102_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN102
			+ "fetchAllNFNotaInfoItemImpostoICMSSN102s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN102_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN102_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN102
			+ "fetchNFNotaInfoItemImpostoICMSSN102RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN102_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN102_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN102
			+ "fetchAllNFNotaInfoItemImpostoICMSSN102sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN201
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN201 = "NFNotaInfoItemImpostoICMSSN201Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN201. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN201 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN201
			+ "insertNFNotaInfoItemImpostoICMSSN201";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN201. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN201 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN201
			+ "updateNFNotaInfoItemImpostoICMSSN201";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN201. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN201 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN201
			+ "deleteNFNotaInfoItemImpostoICMSSN201ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN201_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN201_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN201
			+ "deleteAllNFNotaInfoItemImpostoICMSSN201s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN201. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN201 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN201
			+ "fetchNFNotaInfoItemImpostoICMSSN201ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN201_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN201_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN201
			+ "fetchAllNFNotaInfoItemImpostoICMSSN201s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN201_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN201_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN201
			+ "fetchNFNotaInfoItemImpostoICMSSN201RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN201_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN201_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN201
			+ "fetchAllNFNotaInfoItemImpostoICMSSN201sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN202
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN202 = "NFNotaInfoItemImpostoICMSSN202Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN202. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN202 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN202
			+ "insertNFNotaInfoItemImpostoICMSSN202";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN202. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN202 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN202
			+ "updateNFNotaInfoItemImpostoICMSSN202";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN202. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN202 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN202
			+ "deleteNFNotaInfoItemImpostoICMSSN202ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN202_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN202_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN202
			+ "deleteAllNFNotaInfoItemImpostoICMSSN202s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN202. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN202 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN202
			+ "fetchNFNotaInfoItemImpostoICMSSN202ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN202_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN202_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN202
			+ "fetchAllNFNotaInfoItemImpostoICMSSN202s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN202_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN202_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN202
			+ "fetchNFNotaInfoItemImpostoICMSSN202RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN202_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN202_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN202
			+ "fetchAllNFNotaInfoItemImpostoICMSSN202sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN500
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN500 = "NFNotaInfoItemImpostoICMSSN500Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN500. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN500 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN500
			+ "insertNFNotaInfoItemImpostoICMSSN500";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN500. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN500 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN500
			+ "updateNFNotaInfoItemImpostoICMSSN500";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN500. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN500 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN500
			+ "deleteNFNotaInfoItemImpostoICMSSN500ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN500_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN500_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN500
			+ "deleteAllNFNotaInfoItemImpostoICMSSN500s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN500. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN500 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN500
			+ "fetchNFNotaInfoItemImpostoICMSSN500ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN500_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN500_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN500
			+ "fetchAllNFNotaInfoItemImpostoICMSSN500s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN500_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN500_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN500
			+ "fetchNFNotaInfoItemImpostoICMSSN500RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN500_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN500_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN500
			+ "fetchAllNFNotaInfoItemImpostoICMSSN500sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN900
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN900 = "NFNotaInfoItemImpostoICMSSN900Map.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN900. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN900 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN900
			+ "insertNFNotaInfoItemImpostoICMSSN900";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN900. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN900 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN900
			+ "updateNFNotaInfoItemImpostoICMSSN900";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN900. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN900 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN900
			+ "deleteNFNotaInfoItemImpostoICMSSN900ById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN900_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN900_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN900
			+ "deleteAllNFNotaInfoItemImpostoICMSSN900s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN900. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN900 = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN900
			+ "fetchNFNotaInfoItemImpostoICMSSN900ById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN900_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN900_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN900
			+ "fetchAllNFNotaInfoItemImpostoICMSSN900s";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN900_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN900_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN900
			+ "fetchNFNotaInfoItemImpostoICMSSN900RowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN900_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN900_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSSN900
			+ "fetchAllNFNotaInfoItemImpostoICMSSN900sRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOIPI
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOIPI = "NFNotaInfoItemImpostoIPIMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOIPI. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOIPI = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPI
			+ "insertNFNotaInfoItemImpostoIPI";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOIPI. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOIPI = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPI
			+ "updateNFNotaInfoItemImpostoIPI";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPI. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPI = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPI
			+ "deleteNFNotaInfoItemImpostoIPIById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPI_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPI_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPI
			+ "deleteAllNFNotaInfoItemImpostoIPIs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPI. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPI = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPI
			+ "fetchNFNotaInfoItemImpostoIPIById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPI_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPI_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPI
			+ "fetchAllNFNotaInfoItemImpostoIPIs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPI_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPI_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPI
			+ "fetchNFNotaInfoItemImpostoIPIRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPI_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPI_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPI
			+ "fetchAllNFNotaInfoItemImpostoIPIsRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOIPITRIBUTADO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO = "NFNotaInfoItemImpostoIPITributadoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO
			+ "insertNFNotaInfoItemImpostoIPITributado";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO
			+ "updateNFNotaInfoItemImpostoIPITributado";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO
			+ "deleteNFNotaInfoItemImpostoIPITributadoById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO
			+ "deleteAllNFNotaInfoItemImpostoIPITributados";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO
			+ "fetchNFNotaInfoItemImpostoIPITributadoById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO
			+ "fetchAllNFNotaInfoItemImpostoIPITributados";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO
			+ "fetchNFNotaInfoItemImpostoIPITributadoRowCount";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO
			+ "fetchAllNFNotaInfoItemImpostoIPITributadosRequest";

	/// ===================================###
	/// NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO = "NFNotaInfoItemImpostoIPINaoTributadoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO
			+ "insertNFNotaInfoItemImpostoIPINaoTributado";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO
			+ "updateNFNotaInfoItemImpostoIPINaoTributado";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO
			+ "deleteNFNotaInfoItemImpostoIPINaoTributadoById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO
			+ "deleteAllNFNotaInfoItemImpostoIPINaoTributados";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO
			+ "fetchNFNotaInfoItemImpostoIPINaoTributadoById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO
			+ "fetchAllNFNotaInfoItemImpostoIPINaoTributados";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO
			+ "fetchNFNotaInfoItemImpostoIPINaoTributadoRowCount";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO
			+ "fetchAllNFNotaInfoItemImpostoIPINaoTributadosRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOIMPORTACAO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOIMPORTACAO = "NFNotaInfoItemImpostoImportacaoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOIMPORTACAO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOIMPORTACAO = NAMESPACE_NFNOTAINFOITEMIMPOSTOIMPORTACAO
			+ "insertNFNotaInfoItemImpostoImportacao";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOIMPORTACAO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOIMPORTACAO = NAMESPACE_NFNOTAINFOITEMIMPOSTOIMPORTACAO
			+ "updateNFNotaInfoItemImpostoImportacao";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOIMPORTACAO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOIMPORTACAO = NAMESPACE_NFNOTAINFOITEMIMPOSTOIMPORTACAO
			+ "deleteNFNotaInfoItemImpostoImportacaoById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOIMPORTACAO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOIMPORTACAO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOIMPORTACAO
			+ "deleteAllNFNotaInfoItemImpostoImportacaos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIMPORTACAO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIMPORTACAO = NAMESPACE_NFNOTAINFOITEMIMPOSTOIMPORTACAO
			+ "fetchNFNotaInfoItemImpostoImportacaoById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIMPORTACAO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIMPORTACAO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOIMPORTACAO
			+ "fetchAllNFNotaInfoItemImpostoImportacaos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIMPORTACAO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIMPORTACAO_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOIMPORTACAO
			+ "fetchNFNotaInfoItemImpostoImportacaoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOIMPORTACAO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOIMPORTACAO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOIMPORTACAO
			+ "fetchAllNFNotaInfoItemImpostoImportacaosRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOISSQN
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOISSQN = "NFNotaInfoItemImpostoISSQNMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOISSQN. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOISSQN = NAMESPACE_NFNOTAINFOITEMIMPOSTOISSQN
			+ "insertNFNotaInfoItemImpostoISSQN";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOISSQN. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOISSQN = NAMESPACE_NFNOTAINFOITEMIMPOSTOISSQN
			+ "updateNFNotaInfoItemImpostoISSQN";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOISSQN. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOISSQN = NAMESPACE_NFNOTAINFOITEMIMPOSTOISSQN
			+ "deleteNFNotaInfoItemImpostoISSQNById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOISSQN_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOISSQN_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOISSQN
			+ "deleteAllNFNotaInfoItemImpostoISSQNs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOISSQN. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOISSQN = NAMESPACE_NFNOTAINFOITEMIMPOSTOISSQN
			+ "fetchNFNotaInfoItemImpostoISSQNById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOISSQN_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOISSQN_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOISSQN
			+ "fetchAllNFNotaInfoItemImpostoISSQNs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOISSQN_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOISSQN_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOISSQN
			+ "fetchNFNotaInfoItemImpostoISSQNRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOISSQN_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOISSQN_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOISSQN
			+ "fetchAllNFNotaInfoItemImpostoISSQNsRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOPIS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOPIS = "NFNotaInfoItemImpostoPISMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOPIS. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOPIS = NAMESPACE_NFNOTAINFOITEMIMPOSTOPIS
			+ "insertNFNotaInfoItemImpostoPIS";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPIS. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPIS = NAMESPACE_NFNOTAINFOITEMIMPOSTOPIS
			+ "updateNFNotaInfoItemImpostoPIS";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOPIS. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOPIS = NAMESPACE_NFNOTAINFOITEMIMPOSTOPIS
			+ "deleteNFNotaInfoItemImpostoPISById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOPIS_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOPIS_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOPIS
			+ "deleteAllNFNotaInfoItemImpostoPISs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPIS. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPIS = NAMESPACE_NFNOTAINFOITEMIMPOSTOPIS
			+ "fetchNFNotaInfoItemImpostoPISById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPIS_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPIS_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOPIS
			+ "fetchAllNFNotaInfoItemImpostoPISs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPIS_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPIS_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOPIS
			+ "fetchNFNotaInfoItemImpostoPISRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPIS_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPIS_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOPIS
			+ "fetchAllNFNotaInfoItemImpostoPISsRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOPISALIQUOTA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA = "NFNotaInfoItemImpostoPISAliquotaMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISALIQUOTA. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISALIQUOTA = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA
			+ "insertNFNotaInfoItemImpostoPISAliquota";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA
			+ "updateNFNotaInfoItemImpostoPISAliquota";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA
			+ "deleteNFNotaInfoItemImpostoPISAliquotaById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA
			+ "deleteAllNFNotaInfoItemImpostoPISAliquotas";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISALIQUOTA. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISALIQUOTA = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA
			+ "fetchNFNotaInfoItemImpostoPISAliquotaById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISALIQUOTA_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISALIQUOTA_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA
			+ "fetchAllNFNotaInfoItemImpostoPISAliquotas";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISALIQUOTA_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISALIQUOTA_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA
			+ "fetchNFNotaInfoItemImpostoPISAliquotaRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISALIQUOTA_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISALIQUOTA_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA
			+ "fetchAllNFNotaInfoItemImpostoPISAliquotasRequest";

	/// ===================================###
	/// NFNOTAINFOITEMIMPOSTOPISQUANTIDADE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE = "NFNotaInfoItemImpostoPISQuantidadeMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE
			+ "insertNFNotaInfoItemImpostoPISQuantidade";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE
			+ "updateNFNotaInfoItemImpostoPISQuantidade";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE
			+ "deleteNFNotaInfoItemImpostoPISQuantidadeById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE
			+ "deleteAllNFNotaInfoItemImpostoPISQuantidades";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE
			+ "fetchNFNotaInfoItemImpostoPISQuantidadeById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE
			+ "fetchAllNFNotaInfoItemImpostoPISQuantidades";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE
			+ "fetchNFNotaInfoItemImpostoPISQuantidadeRowCount";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE
			+ "fetchAllNFNotaInfoItemImpostoPISQuantidadesRequest";

	/// ===================================###
	/// NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO = "NFNotaInfoItemImpostoPISNaoTributadoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO
			+ "insertNFNotaInfoItemImpostoPISNaoTributado";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO
			+ "updateNFNotaInfoItemImpostoPISNaoTributado";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO
			+ "deleteNFNotaInfoItemImpostoPISNaoTributadoById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO
			+ "deleteAllNFNotaInfoItemImpostoPISNaoTributados";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO
			+ "fetchNFNotaInfoItemImpostoPISNaoTributadoById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO
			+ "fetchAllNFNotaInfoItemImpostoPISNaoTributados";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO
			+ "fetchNFNotaInfoItemImpostoPISNaoTributadoRowCount";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO
			+ "fetchAllNFNotaInfoItemImpostoPISNaoTributadosRequest";

	/// ===================================###
	/// NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES = "NFNotaInfoItemImpostoPISOutrasOperacoesMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES
			+ "insertNFNotaInfoItemImpostoPISOutrasOperacoes";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES
			+ "updateNFNotaInfoItemImpostoPISOutrasOperacoes";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES
			+ "deleteNFNotaInfoItemImpostoPISOutrasOperacoesById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES
			+ "deleteAllNFNotaInfoItemImpostoPISOutrasOperacoess";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES
			+ "fetchNFNotaInfoItemImpostoPISOutrasOperacoesById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES
			+ "fetchAllNFNotaInfoItemImpostoPISOutrasOperacoess";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES_COUNT.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES
			+ "fetchNFNotaInfoItemImpostoPISOutrasOperacoesRowCount";

	/**
	 * The Constant
	 * STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES
			+ "fetchAllNFNotaInfoItemImpostoPISOutrasOperacoessRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOPISST
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOPISST = "NFNotaInfoItemImpostoPISSTMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISST. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISST = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISST
			+ "insertNFNotaInfoItemImpostoPISST";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISST. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISST = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISST
			+ "updateNFNotaInfoItemImpostoPISST";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISST. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISST = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISST
			+ "deleteNFNotaInfoItemImpostoPISSTById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISST_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISST_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISST
			+ "deleteAllNFNotaInfoItemImpostoPISSTs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISST = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISST
			+ "fetchNFNotaInfoItemImpostoPISSTById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISST_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISST_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISST
			+ "fetchAllNFNotaInfoItemImpostoPISSTs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISST_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISST_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISST
			+ "fetchNFNotaInfoItemImpostoPISSTRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISST_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISST_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOPISST
			+ "fetchAllNFNotaInfoItemImpostoPISSTsRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOCOFINS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINS = "NFNotaInfoItemImpostoCOFINSMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINS. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINS = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINS
			+ "insertNFNotaInfoItemImpostoCOFINS";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINS. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINS = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINS
			+ "updateNFNotaInfoItemImpostoCOFINS";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINS. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINS = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINS
			+ "deleteNFNotaInfoItemImpostoCOFINSById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINS_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINS_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINS
			+ "deleteAllNFNotaInfoItemImpostoCOFINSs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINS. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINS = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINS
			+ "fetchNFNotaInfoItemImpostoCOFINSById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINS_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINS_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINS
			+ "fetchAllNFNotaInfoItemImpostoCOFINSs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINS_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINS_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINS
			+ "fetchNFNotaInfoItemImpostoCOFINSRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINS_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINS_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINS
			+ "fetchAllNFNotaInfoItemImpostoCOFINSsRequest";

	/// ===================================###
	/// NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA = "NFNotaInfoItemImpostoCOFINSAliquotaMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA
			+ "insertNFNotaInfoItemImpostoCOFINSAliquota";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA
			+ "updateNFNotaInfoItemImpostoCOFINSAliquota";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA
			+ "deleteNFNotaInfoItemImpostoCOFINSAliquotaById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA
			+ "deleteAllNFNotaInfoItemImpostoCOFINSAliquotas";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA
			+ "fetchNFNotaInfoItemImpostoCOFINSAliquotaById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA
			+ "fetchAllNFNotaInfoItemImpostoCOFINSAliquotas";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA
			+ "fetchNFNotaInfoItemImpostoCOFINSAliquotaRowCount";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA
			+ "fetchAllNFNotaInfoItemImpostoCOFINSAliquotasRequest";

	/// ===================================###
	/// NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE = "NFNotaInfoItemImpostoCOFINSQuantidadeMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE
			+ "insertNFNotaInfoItemImpostoCOFINSQuantidade";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE
			+ "updateNFNotaInfoItemImpostoCOFINSQuantidade";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE
			+ "deleteNFNotaInfoItemImpostoCOFINSQuantidadeById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE
			+ "deleteAllNFNotaInfoItemImpostoCOFINSQuantidades";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE
			+ "fetchNFNotaInfoItemImpostoCOFINSQuantidadeById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE
			+ "fetchAllNFNotaInfoItemImpostoCOFINSQuantidades";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE
			+ "fetchNFNotaInfoItemImpostoCOFINSQuantidadeRowCount";

	/**
	 * The Constant
	 * STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE
			+ "fetchAllNFNotaInfoItemImpostoCOFINSQuantidadesRequest";

	/// ===================================###
	/// NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL = "NFNotaInfoItemImpostoCOFINSNaoTributavelMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL
			+ "insertNFNotaInfoItemImpostoCOFINSNaoTributavel";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL
			+ "updateNFNotaInfoItemImpostoCOFINSNaoTributavel";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL
			+ "deleteNFNotaInfoItemImpostoCOFINSNaoTributavelById";

	/**
	 * The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL_ALL.
	 */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL
			+ "deleteAllNFNotaInfoItemImpostoCOFINSNaoTributavels";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL
			+ "fetchNFNotaInfoItemImpostoCOFINSNaoTributavelById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL
			+ "fetchAllNFNotaInfoItemImpostoCOFINSNaoTributavels";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL_COUNT.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL
			+ "fetchNFNotaInfoItemImpostoCOFINSNaoTributavelRowCount";

	/**
	 * The Constant
	 * STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL
			+ "fetchAllNFNotaInfoItemImpostoCOFINSNaoTributavelsRequest";

	/// ===================================###
	/// NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES = "NFNotaInfoItemImpostoCOFINSOutrasOperacoesMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES
			+ "insertNFNotaInfoItemImpostoCOFINSOutrasOperacoes";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES
			+ "updateNFNotaInfoItemImpostoCOFINSOutrasOperacoes";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES
			+ "deleteNFNotaInfoItemImpostoCOFINSOutrasOperacoesById";

	/**
	 * The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES_ALL.
	 */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES
			+ "deleteAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES
			+ "fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoesById";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES_ALL.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES
			+ "fetchAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES_COUNT.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES
			+ "fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoesRowCount";

	/**
	 * The Constant
	 * STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES
			+ "fetchAllNFNotaInfoItemImpostoCOFINSOutrasOperacoessRequest";

	/// ===================================### NFNOTAINFOITEMIMPOSTOCOFINSST
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSST = "NFNotaInfoItemImpostoCOFINSSTMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSST. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSST = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSST
			+ "insertNFNotaInfoItemImpostoCOFINSST";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSST. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSST = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSST
			+ "updateNFNotaInfoItemImpostoCOFINSST";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSST. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSST = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSST
			+ "deleteNFNotaInfoItemImpostoCOFINSSTById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSST_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSST_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSST
			+ "deleteAllNFNotaInfoItemImpostoCOFINSSTs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSST = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSST
			+ "fetchNFNotaInfoItemImpostoCOFINSSTById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSST_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSST_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSST
			+ "fetchAllNFNotaInfoItemImpostoCOFINSSTs";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSST_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSST_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSST
			+ "fetchNFNotaInfoItemImpostoCOFINSSTRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSST_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSST_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOCOFINSST
			+ "fetchAllNFNotaInfoItemImpostoCOFINSSTsRequest";

	/// ===================================###
	/// NFNOTAINFOITEMIMPOSTOICMSUFDESTINO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO = "NFNotaInfoItemImpostoICMSUFDestinoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO. */
	private static final String STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO
			+ "insertNFNotaInfoItemImpostoICMSUFDestino";

	/** The Constant STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO. */
	private static final String STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO
			+ "updateNFNotaInfoItemImpostoICMSUFDestino";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO
			+ "deleteNFNotaInfoItemImpostoICMSUFDestinoById";

	/** The Constant STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO
			+ "deleteAllNFNotaInfoItemImpostoICMSUFDestinos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO
			+ "fetchNFNotaInfoItemImpostoICMSUFDestinoById";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO_ALL = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO
			+ "fetchAllNFNotaInfoItemImpostoICMSUFDestinos";

	/** The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO_COUNT = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO
			+ "fetchNFNotaInfoItemImpostoICMSUFDestinoRowCount";

	/**
	 * The Constant STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO_ALL_REQUEST.
	 */
	private static final String STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO_ALL_REQUEST = NAMESPACE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO
			+ "fetchAllNFNotaInfoItemImpostoICMSUFDestinosRequest";

	IStatusBAR statusBAR;

	IHistoricoBAR historicoBAR;

	INFNotaInfoItemBAR nfnotaInfoItemBAR;

	public IStatusBAR getStatusBAR() {
		return statusBAR;
	}

	public void setStatusBAR(IStatusBAR statusBAR) {
		this.statusBAR = statusBAR;
	}

	public IHistoricoBAR getHistoricoBAR() {
		return historicoBAR;
	}

	public void setHistoricoBAR(IHistoricoBAR historicoBAR) {
		this.historicoBAR = historicoBAR;
	}

	public INFNotaInfoItemBAR getNfnotaInfoItemBAR() {
		return nfnotaInfoItemBAR;
	}

	public void setNfnotaInfoItemBAR(INFNotaInfoItemBAR nfnotaInfoItemBAR) {
		this.nfnotaInfoItemBAR = nfnotaInfoItemBAR;
	}

	// ===================================### NFNOTAINFOITEM
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemBAR#insertNFNotaInfoItem(com.qat.samples.sysmgmt.base.model.NFNotaInfoItem)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItem(NFNotaInfoItem nfnotainfoitem) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitem.setProcessId(nfnotainfoitem.getTransactionId());

		if (!ValidationUtil.isNull(nfnotainfoitem.getProduto())) {
			count += NFNotaInfoItemProdutoBARD.maintainNFNotaInfoItemProdutoAssociations(nfnotainfoitem.getProduto(),
					response, nfnotainfoitem.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, nfnotainfoitem.getId(), nfnotainfoitem.getCreateUser(),
					nfnotainfoitem.getTransactionId(), nfnotainfoitem.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitem.getImposto())) {
			count += NFNotaInfoItemImpostoBARD.maintainNFNotaInfoItemImpostoAssociations(nfnotainfoitem.getImposto(),
					response, nfnotainfoitem.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, nfnotainfoitem.getId(), nfnotainfoitem.getCreateUser(),
					nfnotainfoitem.getTransactionId(), nfnotainfoitem.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitem.getImpostoDevolvido())) {
			count += NFImpostoDevolvidoBARD.maintainNFImpostoDevolvidoAssociations(nfnotainfoitem.getImpostoDevolvido(),
					response, nfnotainfoitem.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, nfnotainfoitem.getId(), nfnotainfoitem.getCreateUser(),
					nfnotainfoitem.getTransactionId(), nfnotainfoitem.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEM, nfnotainfoitem, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEM, AcaoEnum.INSERT,
				nfnotainfoitem.getTransactionId(), getHistoricoBAR(), response, nfnotainfoitem.getId(),
				nfnotainfoitem.getUserId());

		if (nfnotainfoitem.getId() != 0 && nfnotainfoitem.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfoitem.getId(), null,
					AcaoEnum.INSERT, nfnotainfoitem.getCreateUser(), nfnotainfoitem.getId(), TabelaEnum.NFNOTAINFOITEM,
					statusBAR, historicoBAR, nfnotainfoitem.getTransactionId(), nfnotainfoitem.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemBAR#updateNFNotaInfoItem(
	 * com.qat.samples.sysmgmt.base.model.NFNotaInfoItem)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItem(NFNotaInfoItem nfnotainfoitem) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitem.setProcessId(nfnotainfoitem.getTransactionId());
		Integer a = 0;
		if (!ValidationUtil.isNull(nfnotainfoitem.getProduto())) {
			a += NFNotaInfoItemProdutoBARD.maintainNFNotaInfoItemProdutoAssociations(nfnotainfoitem.getProduto(),
					response, nfnotainfoitem.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, nfnotainfoitem.getId(), nfnotainfoitem.getCreateUser(),
					nfnotainfoitem.getTransactionId(), nfnotainfoitem.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitem.getImposto())) {
			a += NFNotaInfoItemImpostoBARD.maintainNFNotaInfoItemImpostoAssociations(nfnotainfoitem.getImposto(),
					response, nfnotainfoitem.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, nfnotainfoitem.getId(), nfnotainfoitem.getCreateUser(),
					nfnotainfoitem.getTransactionId(), nfnotainfoitem.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitem.getImpostoDevolvido())) {
			a += NFImpostoDevolvidoBARD.maintainNFImpostoDevolvidoAssociations(nfnotainfoitem.getImpostoDevolvido(),
					response, nfnotainfoitem.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, nfnotainfoitem.getId(), nfnotainfoitem.getCreateUser(),
					nfnotainfoitem.getTransactionId(), nfnotainfoitem.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEM, nfnotainfoitem, response);

		a += InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEM, AcaoEnum.UPDATE,
				nfnotainfoitem.getTransactionId(), getHistoricoBAR(), response, nfnotainfoitem.getId(),
				nfnotainfoitem.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemBAR#deleteNFNotaInfoItem(
	 * com.qat.samples.sysmgmt.base.model.NFNotaInfoItem)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemById(NFNotaInfoItem nfnotainfoitem) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitem.setProcessId(nfnotainfoitem.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEM, nfnotainfoitem, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEM, AcaoEnum.DELETE,
				nfnotainfoitem.getTransactionId(), getHistoricoBAR(), response, nfnotainfoitem.getId(),
				nfnotainfoitem.getUserId());

		if (!ValidationUtil.isNull(nfnotainfoitem.getProduto())) {
			a += NFNotaInfoItemProdutoBARD.maintainNFNotaInfoItemProdutoAssociations(nfnotainfoitem.getProduto(),
					response, nfnotainfoitem.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, nfnotainfoitem.getId(), nfnotainfoitem.getCreateUser(),
					nfnotainfoitem.getTransactionId(), nfnotainfoitem.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitem.getImposto())) {
			a += NFNotaInfoItemImpostoBARD.maintainNFNotaInfoItemImpostoAssociations(nfnotainfoitem.getImposto(),
					response, nfnotainfoitem.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, nfnotainfoitem.getId(), nfnotainfoitem.getCreateUser(),
					nfnotainfoitem.getTransactionId(), nfnotainfoitem.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitem.getImpostoDevolvido())) {
			a += NFImpostoDevolvidoBARD.maintainNFImpostoDevolvidoAssociations(nfnotainfoitem.getImpostoDevolvido(),
					response, nfnotainfoitem.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, nfnotainfoitem.getId(), nfnotainfoitem.getCreateUser(),
					nfnotainfoitem.getTransactionId(), nfnotainfoitem.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemBAR#
	 * deleteAllNFNotaInfoItems()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItems() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEM_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemBAR#fetchNFNotaInfoItemById(
	 * com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItem fetchNFNotaInfoItemById(FetchByIdRequest request) {
		return (NFNotaInfoItem) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOITEM,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemBAR#
	 * fetchAllNFNotaInfoItemsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItem> fetchAllNFNotaInfoItems(NFNotaInfoItem nfnotainfoitem) {
		InternalResultsResponse<NFNotaInfoItem> response = new InternalResultsResponse<NFNotaInfoItem>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEM_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemBAR#
	 * fetchNFNotaInfoItemsByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItem> fetchNFNotaInfoItemsByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItem> response = new InternalResultsResponse<NFNotaInfoItem>();
		fetchNFNotaInfoItemsByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOITEM_COUNT,
				STMT_FETCH_NFNOTAINFOITEM_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoItemsByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMPRODUTO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoBAR#insertNFNotaInfoItemProduto(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemProduto)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemProduto(NFNotaInfoItemProduto nfnotainfoitemproduto) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemproduto.setProcessId(nfnotainfoitemproduto.getTransactionId());

		if (!ValidationUtil.isNull(nfnotainfoitemproduto.getVeiculo())) {
			count += NFNotaInfoItemProdutoVeiculoBARD.maintainNFNotaInfoItemProdutoVeiculoAssociations(
					nfnotainfoitemproduto.getVeiculo(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemproduto.getCombustivel())) {
			count += NFNotaInfoItemProdutoCombustivelBARD.maintainNFNotaInfoItemProdutoCombustivelAssociations(
					nfnotainfoitemproduto.getCombustivel(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMPRODUTO, nfnotainfoitemproduto, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTO, AcaoEnum.INSERT,
				nfnotainfoitemproduto.getTransactionId(), getHistoricoBAR(), response, nfnotainfoitemproduto.getId(),
				nfnotainfoitemproduto.getUserId());

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemproduto.getDeclaracoesImportacao())) {
			count += NFNotaInfoItemProdutoDeclaracaoImportacaoBARD
					.maintainNFNotaInfoItemProdutoDeclaracaoImportacaoAssociations(
							nfnotainfoitemproduto.getDeclaracoesImportacao(), response, nfnotainfoitemproduto.getId(),
							null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemproduto.getId(), nfnotainfoitemproduto.getCreateUser(),
							nfnotainfoitemproduto.getTransactionId(), nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemproduto.getDetalhesExportacao())) {
			count += NFNotaInfoItemDetalheExportacaoBARD.maintainNFNotaInfoItemDetalheExportacaoAssociations(
					nfnotainfoitemproduto.getDetalhesExportacao(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemproduto.getMedicamentos())) {
			count += NFNotaInfoItemProdutoMedicamentoBARD.maintainNFNotaInfoItemProdutoMedicamentoAssociations(
					nfnotainfoitemproduto.getMedicamentos(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemproduto.getArmamentos())) {
			count += NFNotaInfoItemProdutoArmamentoBARD.maintainNFNotaInfoItemProdutoArmamentoAssociations(
					nfnotainfoitemproduto.getArmamentos(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoBAR#
	 * updateNFNotaInfoItemProduto(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemProduto)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemProduto(NFNotaInfoItemProduto nfnotainfoitemproduto) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemproduto.setProcessId(nfnotainfoitemproduto.getTransactionId());
		Integer a = 0;
		if (!ValidationUtil.isNull(nfnotainfoitemproduto.getVeiculo())) {
			a += NFNotaInfoItemProdutoVeiculoBARD.maintainNFNotaInfoItemProdutoVeiculoAssociations(
					nfnotainfoitemproduto.getVeiculo(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemproduto.getCombustivel())) {
			a += NFNotaInfoItemProdutoCombustivelBARD.maintainNFNotaInfoItemProdutoCombustivelAssociations(
					nfnotainfoitemproduto.getCombustivel(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMPRODUTO, nfnotainfoitemproduto, response);

		a += InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTO, AcaoEnum.UPDATE,
				nfnotainfoitemproduto.getTransactionId(), getHistoricoBAR(), response, nfnotainfoitemproduto.getId(),
				nfnotainfoitemproduto.getUserId());

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemproduto.getDeclaracoesImportacao())) {
			a += NFNotaInfoItemProdutoDeclaracaoImportacaoBARD
					.maintainNFNotaInfoItemProdutoDeclaracaoImportacaoAssociations(
							nfnotainfoitemproduto.getDeclaracoesImportacao(), response, nfnotainfoitemproduto.getId(),
							null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemproduto.getId(), nfnotainfoitemproduto.getCreateUser(),
							nfnotainfoitemproduto.getTransactionId(), nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemproduto.getDetalhesExportacao())) {
			a += NFNotaInfoItemDetalheExportacaoBARD.maintainNFNotaInfoItemDetalheExportacaoAssociations(
					nfnotainfoitemproduto.getDetalhesExportacao(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemproduto.getMedicamentos())) {
			a += NFNotaInfoItemProdutoMedicamentoBARD.maintainNFNotaInfoItemProdutoMedicamentoAssociations(
					nfnotainfoitemproduto.getMedicamentos(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemproduto.getArmamentos())) {
			a += NFNotaInfoItemProdutoArmamentoBARD.maintainNFNotaInfoItemProdutoArmamentoAssociations(
					nfnotainfoitemproduto.getArmamentos(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoBAR#
	 * deleteNFNotaInfoItemProduto(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemProduto)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemProdutoById(NFNotaInfoItemProduto nfnotainfoitemproduto) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemproduto.setProcessId(nfnotainfoitemproduto.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTO, nfnotainfoitemproduto, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTO, AcaoEnum.DELETE,
				nfnotainfoitemproduto.getTransactionId(), getHistoricoBAR(), response, nfnotainfoitemproduto.getId(),
				nfnotainfoitemproduto.getUserId());

		if (!ValidationUtil.isNull(nfnotainfoitemproduto.getVeiculo())) {
			a += NFNotaInfoItemProdutoVeiculoBARD.maintainNFNotaInfoItemProdutoVeiculoAssociations(
					nfnotainfoitemproduto.getVeiculo(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemproduto.getCombustivel())) {
			a += NFNotaInfoItemProdutoCombustivelBARD.maintainNFNotaInfoItemProdutoCombustivelAssociations(
					nfnotainfoitemproduto.getCombustivel(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemproduto.getDeclaracoesImportacao())) {
			a += NFNotaInfoItemProdutoDeclaracaoImportacaoBARD
					.maintainNFNotaInfoItemProdutoDeclaracaoImportacaoAssociations(
							nfnotainfoitemproduto.getDeclaracoesImportacao(), response, nfnotainfoitemproduto.getId(),
							null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemproduto.getId(), nfnotainfoitemproduto.getCreateUser(),
							nfnotainfoitemproduto.getTransactionId(), nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemproduto.getDetalhesExportacao())) {
			a += NFNotaInfoItemDetalheExportacaoBARD.maintainNFNotaInfoItemDetalheExportacaoAssociations(
					nfnotainfoitemproduto.getDetalhesExportacao(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemproduto.getMedicamentos())) {
			a += NFNotaInfoItemProdutoMedicamentoBARD.maintainNFNotaInfoItemProdutoMedicamentoAssociations(
					nfnotainfoitemproduto.getMedicamentos(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemproduto.getArmamentos())) {
			a += NFNotaInfoItemProdutoArmamentoBARD.maintainNFNotaInfoItemProdutoArmamentoAssociations(
					nfnotainfoitemproduto.getArmamentos(), response, nfnotainfoitemproduto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemproduto.getId(),
					nfnotainfoitemproduto.getCreateUser(), nfnotainfoitemproduto.getTransactionId(),
					nfnotainfoitemproduto.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoBAR#
	 * deleteAllNFNotaInfoItemProdutos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemProdutos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoBAR#
	 * fetchNFNotaInfoItemProdutoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemProduto fetchNFNotaInfoItemProdutoById(FetchByIdRequest request) {
		return (NFNotaInfoItemProduto) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMPRODUTO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoBAR#
	 * fetchAllNFNotaInfoItemProdutosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProduto> fetchAllNFNotaInfoItemProdutos(
			NFNotaInfoItemProduto nfnotainfoitemproduto) {
		InternalResultsResponse<NFNotaInfoItemProduto> response = new InternalResultsResponse<NFNotaInfoItemProduto>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMPRODUTO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoBAR#
	 * fetchNFNotaInfoItemProdutosByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProduto> fetchNFNotaInfoItemProdutosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemProduto> response = new InternalResultsResponse<NFNotaInfoItemProduto>();
		fetchNFNotaInfoItemProdutosByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOITEMPRODUTO_COUNT,
				STMT_FETCH_NFNOTAINFOITEMPRODUTO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemProdutosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemProdutosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================###
	// NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoDeclaracaoImportacaoBAR#insertNFNotaInfoItemProdutoDeclaracaoImportacao(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemProdutoDeclaracaoImportacao)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemProdutoDeclaracaoImportacao(
			NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemprodutodeclaracaoimportacao
				.setProcessId(nfnotainfoitemprodutodeclaracaoimportacao.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO,
				nfnotainfoitemprodutodeclaracaoimportacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO,
				AcaoEnum.INSERT, nfnotainfoitemprodutodeclaracaoimportacao.getTransactionId(), getHistoricoBAR(),
				response, nfnotainfoitemprodutodeclaracaoimportacao.getId(),
				nfnotainfoitemprodutodeclaracaoimportacao.getUserId());

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemprodutodeclaracaoimportacao.getAdicoes())) {
			a += NFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoBARD
					.maintainNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoAssociations(
							nfnotainfoitemprodutodeclaracaoimportacao.getAdicoes(), response,
							nfnotainfoitemprodutodeclaracaoimportacao.getId(), null, null, TabelaEnum.NFNOTA,
							getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemprodutodeclaracaoimportacao.getId(),
							nfnotainfoitemprodutodeclaracaoimportacao.getCreateUser(),
							nfnotainfoitemprodutodeclaracaoimportacao.getTransactionId(),
							nfnotainfoitemprodutodeclaracaoimportacao.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemProdutoDeclaracaoImportacaoBAR#
	 * updateNFNotaInfoItemProdutoDeclaracaoImportacao(com.qat.samples.sysmgmt.
	 * base.model.NFNotaInfoItemProdutoDeclaracaoImportacao)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemProdutoDeclaracaoImportacao(
			NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutodeclaracaoimportacao
				.setProcessId(nfnotainfoitemprodutodeclaracaoimportacao.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO,
				nfnotainfoitemprodutodeclaracaoimportacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO,
				AcaoEnum.UPDATE, nfnotainfoitemprodutodeclaracaoimportacao.getTransactionId(), getHistoricoBAR(),
				response, nfnotainfoitemprodutodeclaracaoimportacao.getId(),
				nfnotainfoitemprodutodeclaracaoimportacao.getUserId());

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemprodutodeclaracaoimportacao.getAdicoes())) {
			a += NFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoBARD
					.maintainNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoAssociations(
							nfnotainfoitemprodutodeclaracaoimportacao.getAdicoes(), response,
							nfnotainfoitemprodutodeclaracaoimportacao.getId(), null, null, TabelaEnum.NFNOTA,
							getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemprodutodeclaracaoimportacao.getId(),
							nfnotainfoitemprodutodeclaracaoimportacao.getCreateUser(),
							nfnotainfoitemprodutodeclaracaoimportacao.getTransactionId(),
							nfnotainfoitemprodutodeclaracaoimportacao.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemProdutoDeclaracaoImportacaoBAR#
	 * deleteNFNotaInfoItemProdutoDeclaracaoImportacao(com.qat.samples.sysmgmt.
	 * base.model.NFNotaInfoItemProdutoDeclaracaoImportacao)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemProdutoDeclaracaoImportacaoById(
			NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutodeclaracaoimportacao
				.setProcessId(nfnotainfoitemprodutodeclaracaoimportacao.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO,
				nfnotainfoitemprodutodeclaracaoimportacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO,
				AcaoEnum.DELETE, nfnotainfoitemprodutodeclaracaoimportacao.getTransactionId(), getHistoricoBAR(),
				response, nfnotainfoitemprodutodeclaracaoimportacao.getId(),
				nfnotainfoitemprodutodeclaracaoimportacao.getUserId());

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoitemprodutodeclaracaoimportacao.getAdicoes())) {
			a += NFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoBARD
					.maintainNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoAssociations(
							nfnotainfoitemprodutodeclaracaoimportacao.getAdicoes(), response,
							nfnotainfoitemprodutodeclaracaoimportacao.getId(), null, null, TabelaEnum.NFNOTA,
							getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemprodutodeclaracaoimportacao.getId(),
							nfnotainfoitemprodutodeclaracaoimportacao.getCreateUser(),
							nfnotainfoitemprodutodeclaracaoimportacao.getTransactionId(),
							nfnotainfoitemprodutodeclaracaoimportacao.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemProdutoDeclaracaoImportacaoBAR#
	 * deleteAllNFNotaInfoItemProdutoDeclaracaoImportacaos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemProdutoDeclaracaoImportacaos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoDeclaracaoImportacaoBAR
	 * #fetchNFNotaInfoItemProdutoDeclaracaoImportacaoById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemProdutoDeclaracaoImportacao fetchNFNotaInfoItemProdutoDeclaracaoImportacaoById(
			FetchByIdRequest request) {
		return (NFNotaInfoItemProdutoDeclaracaoImportacao) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemProdutoDeclaracaoImportacaoBAR#
	 * fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacao> fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaos(
			NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao) {
		InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacao> response = new InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacao>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoDeclaracaoImportacaoBAR
	 * #fetchNFNotaInfoItemProdutoDeclaracaoImportacaosByRequest(com.qat.samples
	 * .sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacao> fetchNFNotaInfoItemProdutoDeclaracaoImportacaosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacao> response = new InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacao>();
		fetchNFNotaInfoItemProdutoDeclaracaoImportacaosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO_COUNT,
				STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemProdutoDeclaracaoImportacaosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemProdutoDeclaracaoImportacaosByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================###
	// NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoBAR#insertNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(
			NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemprodutodeclaracaoimportacaoadicao
				.setProcessId(nfnotainfoitemprodutodeclaracaoimportacaoadicao.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO,
				nfnotainfoitemprodutodeclaracaoimportacaoadicao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(
				TabelaEnum.NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO, AcaoEnum.INSERT,
				nfnotainfoitemprodutodeclaracaoimportacaoadicao.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutodeclaracaoimportacaoadicao.getId(),
				nfnotainfoitemprodutodeclaracaoimportacaoadicao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoBAR#
	 * updateNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(com.qat.samples.
	 * sysmgmt.base.model.NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(
			NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutodeclaracaoimportacaoadicao
				.setProcessId(nfnotainfoitemprodutodeclaracaoimportacaoadicao.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO,
				nfnotainfoitemprodutodeclaracaoimportacaoadicao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(
				TabelaEnum.NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO, AcaoEnum.UPDATE,
				nfnotainfoitemprodutodeclaracaoimportacaoadicao.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutodeclaracaoimportacaoadicao.getId(),
				nfnotainfoitemprodutodeclaracaoimportacaoadicao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoBAR#
	 * deleteNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(com.qat.samples.
	 * sysmgmt.base.model.NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById(
			NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutodeclaracaoimportacaoadicao
				.setProcessId(nfnotainfoitemprodutodeclaracaoimportacaoadicao.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO,
				nfnotainfoitemprodutodeclaracaoimportacaoadicao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(
				TabelaEnum.NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO, AcaoEnum.DELETE,
				nfnotainfoitemprodutodeclaracaoimportacaoadicao.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutodeclaracaoimportacaoadicao.getId(),
				nfnotainfoitemprodutodeclaracaoimportacaoadicao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoBAR#
	 * deleteAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO_ALL,
				response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.
	 * INFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoBAR#
	 * fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById(
			FetchByIdRequest request) {
		return (NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoBAR#
	 * fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos(
			NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao) {
		InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> response = new InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.
	 * INFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoBAR#
	 * fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> response = new InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao>();
		fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO_COUNT,
				STMT_FETCH_NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================### NFNOTAINFOITEMDETALHEEXPORTACAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemDetalheExportacaoBAR#insertNFNotaInfoItemDetalheExportacao(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemDetalheExportacao)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemDetalheExportacao(
			NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao) {
		InternalResponse response = new InternalResponse();
		Integer a = 0;
		Boolean count1 = false;

		nfnotainfoitemdetalheexportacao.setProcessId(nfnotainfoitemdetalheexportacao.getTransactionId());

		if (!ValidationUtil.isNull(nfnotainfoitemdetalheexportacao.getExportacaoIndireta())) {
			a += NFNotaInfoItemExportacaoIndiretaBARD.maintainNFNotaInfoItemExportacaoIndiretaAssociations(
					nfnotainfoitemdetalheexportacao.getExportacaoIndireta(), response,
					nfnotainfoitemdetalheexportacao.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(),
					statusBAR, historicoBAR, nfnotainfoitemdetalheexportacao.getId(),
					nfnotainfoitemdetalheexportacao.getCreateUser(), nfnotainfoitemdetalheexportacao.getTransactionId(),
					nfnotainfoitemdetalheexportacao.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMDETALHEEXPORTACAO,
				nfnotainfoitemdetalheexportacao, response);

		a += InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMDETALHEEXPORTACAO, AcaoEnum.INSERT,
				nfnotainfoitemdetalheexportacao.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemdetalheexportacao.getId(), nfnotainfoitemdetalheexportacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemDetalheExportacaoBAR#
	 * updateNFNotaInfoItemDetalheExportacao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemDetalheExportacao)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemDetalheExportacao(
			NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemdetalheexportacao.setProcessId(nfnotainfoitemdetalheexportacao.getTransactionId());
		Integer a = 0;
		if (!ValidationUtil.isNull(nfnotainfoitemdetalheexportacao.getExportacaoIndireta())) {
			a += NFNotaInfoItemExportacaoIndiretaBARD.maintainNFNotaInfoItemExportacaoIndiretaAssociations(
					nfnotainfoitemdetalheexportacao.getExportacaoIndireta(), response,
					nfnotainfoitemdetalheexportacao.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(),
					statusBAR, historicoBAR, nfnotainfoitemdetalheexportacao.getId(),
					nfnotainfoitemdetalheexportacao.getCreateUser(), nfnotainfoitemdetalheexportacao.getTransactionId(),
					nfnotainfoitemdetalheexportacao.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMDETALHEEXPORTACAO,
				nfnotainfoitemdetalheexportacao, response);

		a += InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMDETALHEEXPORTACAO, AcaoEnum.UPDATE,
				nfnotainfoitemdetalheexportacao.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemdetalheexportacao.getId(), nfnotainfoitemdetalheexportacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemDetalheExportacaoBAR#
	 * deleteNFNotaInfoItemDetalheExportacao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemDetalheExportacao)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemDetalheExportacaoById(
			NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemdetalheexportacao.setProcessId(nfnotainfoitemdetalheexportacao.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMDETALHEEXPORTACAO,
				nfnotainfoitemdetalheexportacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMDETALHEEXPORTACAO,
				AcaoEnum.DELETE, nfnotainfoitemdetalheexportacao.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemdetalheexportacao.getId(), nfnotainfoitemdetalheexportacao.getUserId());

		if (!ValidationUtil.isNull(nfnotainfoitemdetalheexportacao.getExportacaoIndireta())) {
			a += NFNotaInfoItemExportacaoIndiretaBARD.maintainNFNotaInfoItemExportacaoIndiretaAssociations(
					nfnotainfoitemdetalheexportacao.getExportacaoIndireta(), response,
					nfnotainfoitemdetalheexportacao.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(),
					statusBAR, historicoBAR, nfnotainfoitemdetalheexportacao.getId(),
					nfnotainfoitemdetalheexportacao.getCreateUser(), nfnotainfoitemdetalheexportacao.getTransactionId(),
					nfnotainfoitemdetalheexportacao.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemDetalheExportacaoBAR#
	 * deleteAllNFNotaInfoItemDetalheExportacaos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemDetalheExportacaos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMDETALHEEXPORTACAO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemDetalheExportacaoBAR#
	 * fetchNFNotaInfoItemDetalheExportacaoById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemDetalheExportacao fetchNFNotaInfoItemDetalheExportacaoById(FetchByIdRequest request) {
		return (NFNotaInfoItemDetalheExportacao) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMDETALHEEXPORTACAO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemDetalheExportacaoBAR#
	 * fetchAllNFNotaInfoItemDetalheExportacaosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemDetalheExportacao> fetchAllNFNotaInfoItemDetalheExportacaos(
			NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao) {
		InternalResultsResponse<NFNotaInfoItemDetalheExportacao> response = new InternalResultsResponse<NFNotaInfoItemDetalheExportacao>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMDETALHEEXPORTACAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemDetalheExportacaoBAR#
	 * fetchNFNotaInfoItemDetalheExportacaosByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemDetalheExportacao> fetchNFNotaInfoItemDetalheExportacaosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemDetalheExportacao> response = new InternalResultsResponse<NFNotaInfoItemDetalheExportacao>();
		fetchNFNotaInfoItemDetalheExportacaosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMDETALHEEXPORTACAO_COUNT,
				STMT_FETCH_NFNOTAINFOITEMDETALHEEXPORTACAO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemDetalheExportacaosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemDetalheExportacaosByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================### NFNOTAINFOITEMEXPORTACAOINDIRETA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemExportacaoIndiretaBAR#insertNFNotaInfoItemExportacaoIndireta(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemExportacaoIndireta)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemExportacaoIndireta(
			NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemexportacaoindireta.setProcessId(nfnotainfoitemexportacaoindireta.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMEXPORTACAOINDIRETA,
				nfnotainfoitemexportacaoindireta, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMEXPORTACAOINDIRETA,
				AcaoEnum.INSERT, nfnotainfoitemexportacaoindireta.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemexportacaoindireta.getId(), nfnotainfoitemexportacaoindireta.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemExportacaoIndiretaBAR#
	 * updateNFNotaInfoItemExportacaoIndireta(com.qat.samples.sysmgmt.base.model
	 * .NFNotaInfoItemExportacaoIndireta)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemExportacaoIndireta(
			NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemexportacaoindireta.setProcessId(nfnotainfoitemexportacaoindireta.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMEXPORTACAOINDIRETA,
				nfnotainfoitemexportacaoindireta, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMEXPORTACAOINDIRETA,
				AcaoEnum.UPDATE, nfnotainfoitemexportacaoindireta.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemexportacaoindireta.getId(), nfnotainfoitemexportacaoindireta.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemExportacaoIndiretaBAR#
	 * deleteNFNotaInfoItemExportacaoIndireta(com.qat.samples.sysmgmt.base.model
	 * .NFNotaInfoItemExportacaoIndireta)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemExportacaoIndiretaById(
			NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemexportacaoindireta.setProcessId(nfnotainfoitemexportacaoindireta.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMEXPORTACAOINDIRETA,
				nfnotainfoitemexportacaoindireta, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMEXPORTACAOINDIRETA,
				AcaoEnum.DELETE, nfnotainfoitemexportacaoindireta.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemexportacaoindireta.getId(), nfnotainfoitemexportacaoindireta.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemExportacaoIndiretaBAR#
	 * deleteAllNFNotaInfoItemExportacaoIndiretas()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemExportacaoIndiretas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMEXPORTACAOINDIRETA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemExportacaoIndiretaBAR#
	 * fetchNFNotaInfoItemExportacaoIndiretaById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemExportacaoIndireta fetchNFNotaInfoItemExportacaoIndiretaById(FetchByIdRequest request) {
		return (NFNotaInfoItemExportacaoIndireta) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMEXPORTACAOINDIRETA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemExportacaoIndiretaBAR#
	 * fetchAllNFNotaInfoItemExportacaoIndiretasCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemExportacaoIndireta> fetchAllNFNotaInfoItemExportacaoIndiretas(
			NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta) {
		InternalResultsResponse<NFNotaInfoItemExportacaoIndireta> response = new InternalResultsResponse<NFNotaInfoItemExportacaoIndireta>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMEXPORTACAOINDIRETA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemExportacaoIndiretaBAR#
	 * fetchNFNotaInfoItemExportacaoIndiretasByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemExportacaoIndireta> fetchNFNotaInfoItemExportacaoIndiretasByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemExportacaoIndireta> response = new InternalResultsResponse<NFNotaInfoItemExportacaoIndireta>();
		fetchNFNotaInfoItemExportacaoIndiretasByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMEXPORTACAOINDIRETA_COUNT,
				STMT_FETCH_NFNOTAINFOITEMEXPORTACAOINDIRETA_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemExportacaoIndiretasByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemExportacaoIndiretasByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================### NFNOTAINFOITEMPRODUTOVEICULO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoVeiculoBAR#insertNFNotaInfoItemProdutoVeiculo(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemProdutoVeiculo)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemProdutoVeiculo(
			NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemprodutoveiculo.setProcessId(nfnotainfoitemprodutoveiculo.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMPRODUTOVEICULO,
				nfnotainfoitemprodutoveiculo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOVEICULO,
				AcaoEnum.INSERT, nfnotainfoitemprodutoveiculo.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutoveiculo.getId(), nfnotainfoitemprodutoveiculo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoVeiculoBAR#
	 * updateNFNotaInfoItemProdutoVeiculo(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemProdutoVeiculo)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemProdutoVeiculo(
			NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutoveiculo.setProcessId(nfnotainfoitemprodutoveiculo.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMPRODUTOVEICULO,
				nfnotainfoitemprodutoveiculo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOVEICULO,
				AcaoEnum.UPDATE, nfnotainfoitemprodutoveiculo.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutoveiculo.getId(), nfnotainfoitemprodutoveiculo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoVeiculoBAR#
	 * deleteNFNotaInfoItemProdutoVeiculo(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemProdutoVeiculo)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemProdutoVeiculoById(
			NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutoveiculo.setProcessId(nfnotainfoitemprodutoveiculo.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTOVEICULO,
				nfnotainfoitemprodutoveiculo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOVEICULO,
				AcaoEnum.DELETE, nfnotainfoitemprodutoveiculo.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutoveiculo.getId(), nfnotainfoitemprodutoveiculo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoVeiculoBAR#
	 * deleteAllNFNotaInfoItemProdutoVeiculos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemProdutoVeiculos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTOVEICULO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoVeiculoBAR#
	 * fetchNFNotaInfoItemProdutoVeiculoById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemProdutoVeiculo fetchNFNotaInfoItemProdutoVeiculoById(FetchByIdRequest request) {
		return (NFNotaInfoItemProdutoVeiculo) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMPRODUTOVEICULO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoVeiculoBAR#
	 * fetchAllNFNotaInfoItemProdutoVeiculosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoVeiculo> fetchAllNFNotaInfoItemProdutoVeiculos(
			NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo) {
		InternalResultsResponse<NFNotaInfoItemProdutoVeiculo> response = new InternalResultsResponse<NFNotaInfoItemProdutoVeiculo>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMPRODUTOVEICULO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoVeiculoBAR#
	 * fetchNFNotaInfoItemProdutoVeiculosByRequest(com.qat.samples.sysmgmt.model
	 * .request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoVeiculo> fetchNFNotaInfoItemProdutoVeiculosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemProdutoVeiculo> response = new InternalResultsResponse<NFNotaInfoItemProdutoVeiculo>();
		fetchNFNotaInfoItemProdutoVeiculosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMPRODUTOVEICULO_COUNT, STMT_FETCH_NFNOTAINFOITEMPRODUTOVEICULO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemProdutoVeiculosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemProdutoVeiculosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMPRODUTOMEDICAMENTO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoMedicamentoBAR#insertNFNotaInfoItemProdutoMedicamento(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemProdutoMedicamento)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemProdutoMedicamento(
			NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemprodutomedicamento.setProcessId(nfnotainfoitemprodutomedicamento.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMPRODUTOMEDICAMENTO,
				nfnotainfoitemprodutomedicamento, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOMEDICAMENTO,
				AcaoEnum.INSERT, nfnotainfoitemprodutomedicamento.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutomedicamento.getId(), nfnotainfoitemprodutomedicamento.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoMedicamentoBAR#
	 * updateNFNotaInfoItemProdutoMedicamento(com.qat.samples.sysmgmt.base.model
	 * .NFNotaInfoItemProdutoMedicamento)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemProdutoMedicamento(
			NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutomedicamento.setProcessId(nfnotainfoitemprodutomedicamento.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMPRODUTOMEDICAMENTO,
				nfnotainfoitemprodutomedicamento, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOMEDICAMENTO,
				AcaoEnum.UPDATE, nfnotainfoitemprodutomedicamento.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutomedicamento.getId(), nfnotainfoitemprodutomedicamento.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoMedicamentoBAR#
	 * deleteNFNotaInfoItemProdutoMedicamento(com.qat.samples.sysmgmt.base.model
	 * .NFNotaInfoItemProdutoMedicamento)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemProdutoMedicamentoById(
			NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutomedicamento.setProcessId(nfnotainfoitemprodutomedicamento.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTOMEDICAMENTO,
				nfnotainfoitemprodutomedicamento, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOMEDICAMENTO,
				AcaoEnum.DELETE, nfnotainfoitemprodutomedicamento.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutomedicamento.getId(), nfnotainfoitemprodutomedicamento.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoMedicamentoBAR#
	 * deleteAllNFNotaInfoItemProdutoMedicamentos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemProdutoMedicamentos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTOMEDICAMENTO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoMedicamentoBAR#
	 * fetchNFNotaInfoItemProdutoMedicamentoById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemProdutoMedicamento fetchNFNotaInfoItemProdutoMedicamentoById(FetchByIdRequest request) {
		return (NFNotaInfoItemProdutoMedicamento) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMPRODUTOMEDICAMENTO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoMedicamentoBAR#
	 * fetchAllNFNotaInfoItemProdutoMedicamentosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoMedicamento> fetchAllNFNotaInfoItemProdutoMedicamentos(
			NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento) {
		InternalResultsResponse<NFNotaInfoItemProdutoMedicamento> response = new InternalResultsResponse<NFNotaInfoItemProdutoMedicamento>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMPRODUTOMEDICAMENTO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoMedicamentoBAR#
	 * fetchNFNotaInfoItemProdutoMedicamentosByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoMedicamento> fetchNFNotaInfoItemProdutoMedicamentosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemProdutoMedicamento> response = new InternalResultsResponse<NFNotaInfoItemProdutoMedicamento>();
		fetchNFNotaInfoItemProdutoMedicamentosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMPRODUTOMEDICAMENTO_COUNT,
				STMT_FETCH_NFNOTAINFOITEMPRODUTOMEDICAMENTO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemProdutoMedicamentosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemProdutoMedicamentosByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================### NFNOTAINFOITEMPRODUTOARMAMENTO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoArmamentoBAR#insertNFNotaInfoItemProdutoArmamento(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemProdutoArmamento)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemProdutoArmamento(
			NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemprodutoarmamento.setProcessId(nfnotainfoitemprodutoarmamento.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMPRODUTOARMAMENTO,
				nfnotainfoitemprodutoarmamento, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOARMAMENTO,
				AcaoEnum.INSERT, nfnotainfoitemprodutoarmamento.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutoarmamento.getId(), nfnotainfoitemprodutoarmamento.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoArmamentoBAR#
	 * updateNFNotaInfoItemProdutoArmamento(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemProdutoArmamento)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemProdutoArmamento(
			NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutoarmamento.setProcessId(nfnotainfoitemprodutoarmamento.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMPRODUTOARMAMENTO,
				nfnotainfoitemprodutoarmamento, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOARMAMENTO,
				AcaoEnum.UPDATE, nfnotainfoitemprodutoarmamento.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutoarmamento.getId(), nfnotainfoitemprodutoarmamento.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoArmamentoBAR#
	 * deleteNFNotaInfoItemProdutoArmamento(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemProdutoArmamento)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemProdutoArmamentoById(
			NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutoarmamento.setProcessId(nfnotainfoitemprodutoarmamento.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTOARMAMENTO,
				nfnotainfoitemprodutoarmamento, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOARMAMENTO,
				AcaoEnum.DELETE, nfnotainfoitemprodutoarmamento.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutoarmamento.getId(), nfnotainfoitemprodutoarmamento.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoArmamentoBAR#
	 * deleteAllNFNotaInfoItemProdutoArmamentos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemProdutoArmamentos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTOARMAMENTO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoArmamentoBAR#
	 * fetchNFNotaInfoItemProdutoArmamentoById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemProdutoArmamento fetchNFNotaInfoItemProdutoArmamentoById(FetchByIdRequest request) {
		return (NFNotaInfoItemProdutoArmamento) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMPRODUTOARMAMENTO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoArmamentoBAR#
	 * fetchAllNFNotaInfoItemProdutoArmamentosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoArmamento> fetchAllNFNotaInfoItemProdutoArmamentos(
			NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento) {
		InternalResultsResponse<NFNotaInfoItemProdutoArmamento> response = new InternalResultsResponse<NFNotaInfoItemProdutoArmamento>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMPRODUTOARMAMENTO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoArmamentoBAR#
	 * fetchNFNotaInfoItemProdutoArmamentosByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoArmamento> fetchNFNotaInfoItemProdutoArmamentosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemProdutoArmamento> response = new InternalResultsResponse<NFNotaInfoItemProdutoArmamento>();
		fetchNFNotaInfoItemProdutoArmamentosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMPRODUTOARMAMENTO_COUNT, STMT_FETCH_NFNOTAINFOITEMPRODUTOARMAMENTO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemProdutoArmamentosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemProdutoArmamentosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMPRODUTOCOMBUSTIVEL
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoCombustivelBAR#insertNFNotaInfoItemProdutoCombustivel(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemProdutoCombustivel)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemProdutoCombustivel(
			NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel) {
		InternalResponse response = new InternalResponse();
		Integer a = 0;
		Boolean count1 = false;

		nfnotainfoitemprodutocombustivel.setProcessId(nfnotainfoitemprodutocombustivel.getTransactionId());

		if (!ValidationUtil.isNull(nfnotainfoitemprodutocombustivel.getCide())) {
			a += NFNotaInfoItemProdutoCombustivelCIDEBARD.maintainNFNotaInfoItemProdutoCombustivelCIDEAssociations(
					nfnotainfoitemprodutocombustivel.getCide(), response, nfnotainfoitemprodutocombustivel.getId(),
					null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemprodutocombustivel.getId(), nfnotainfoitemprodutocombustivel.getCreateUser(),
					nfnotainfoitemprodutocombustivel.getTransactionId(),
					nfnotainfoitemprodutocombustivel.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL,
				nfnotainfoitemprodutocombustivel, response);

		a += InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOCOMBUSTIVEL, AcaoEnum.INSERT,
				nfnotainfoitemprodutocombustivel.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutocombustivel.getId(), nfnotainfoitemprodutocombustivel.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoCombustivelBAR#
	 * updateNFNotaInfoItemProdutoCombustivel(com.qat.samples.sysmgmt.base.model
	 * .NFNotaInfoItemProdutoCombustivel)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemProdutoCombustivel(
			NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutocombustivel.setProcessId(nfnotainfoitemprodutocombustivel.getTransactionId());
		Integer a = 0;
		if (!ValidationUtil.isNull(nfnotainfoitemprodutocombustivel.getCide())) {
			a += NFNotaInfoItemProdutoCombustivelCIDEBARD.maintainNFNotaInfoItemProdutoCombustivelCIDEAssociations(
					nfnotainfoitemprodutocombustivel.getCide(), response, nfnotainfoitemprodutocombustivel.getId(),
					null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemprodutocombustivel.getId(), nfnotainfoitemprodutocombustivel.getCreateUser(),
					nfnotainfoitemprodutocombustivel.getTransactionId(),
					nfnotainfoitemprodutocombustivel.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL,
				nfnotainfoitemprodutocombustivel, response);

		a += InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOCOMBUSTIVEL, AcaoEnum.UPDATE,
				nfnotainfoitemprodutocombustivel.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutocombustivel.getId(), nfnotainfoitemprodutocombustivel.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoCombustivelBAR#
	 * deleteNFNotaInfoItemProdutoCombustivel(com.qat.samples.sysmgmt.base.model
	 * .NFNotaInfoItemProdutoCombustivel)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemProdutoCombustivelById(
			NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutocombustivel.setProcessId(nfnotainfoitemprodutocombustivel.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL,
				nfnotainfoitemprodutocombustivel, response);

		Integer a = 0;
		if (!ValidationUtil.isNull(nfnotainfoitemprodutocombustivel.getCide())) {
			a += NFNotaInfoItemProdutoCombustivelCIDEBARD.maintainNFNotaInfoItemProdutoCombustivelCIDEAssociations(
					nfnotainfoitemprodutocombustivel.getCide(), response, nfnotainfoitemprodutocombustivel.getId(),
					null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemprodutocombustivel.getId(), nfnotainfoitemprodutocombustivel.getCreateUser(),
					nfnotainfoitemprodutocombustivel.getTransactionId(),
					nfnotainfoitemprodutocombustivel.getTransactionId());
		}

		a += InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOCOMBUSTIVEL, AcaoEnum.DELETE,
				nfnotainfoitemprodutocombustivel.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutocombustivel.getId(), nfnotainfoitemprodutocombustivel.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoCombustivelBAR#
	 * deleteAllNFNotaInfoItemProdutoCombustivels()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemProdutoCombustivels() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoCombustivelBAR#
	 * fetchNFNotaInfoItemProdutoCombustivelById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemProdutoCombustivel fetchNFNotaInfoItemProdutoCombustivelById(FetchByIdRequest request) {
		return (NFNotaInfoItemProdutoCombustivel) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoCombustivelBAR#
	 * fetchAllNFNotaInfoItemProdutoCombustivelsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoCombustivel> fetchAllNFNotaInfoItemProdutoCombustivels(
			NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel) {
		InternalResultsResponse<NFNotaInfoItemProdutoCombustivel> response = new InternalResultsResponse<NFNotaInfoItemProdutoCombustivel>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoCombustivelBAR#
	 * fetchNFNotaInfoItemProdutoCombustivelsByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoCombustivel> fetchNFNotaInfoItemProdutoCombustivelsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemProdutoCombustivel> response = new InternalResultsResponse<NFNotaInfoItemProdutoCombustivel>();
		fetchNFNotaInfoItemProdutoCombustivelsByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL_COUNT,
				STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVEL_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemProdutoCombustivelsByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemProdutoCombustivelsByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================###
	// NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoCombustivelCIDEBAR#insertNFNotaInfoItemProdutoCombustivelCIDE(com.qat.samples.sysmgmt.NFNotaInfoItemProdutoCombustivelCIDEBARD.model.NFNotaInfoItemProdutoCombustivelCIDE)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemProdutoCombustivelCIDE(
			NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemprodutocombustivelcide.setProcessId(nfnotainfoitemprodutocombustivelcide.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE,
				nfnotainfoitemprodutocombustivelcide, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE,
				AcaoEnum.INSERT, nfnotainfoitemprodutocombustivelcide.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutocombustivelcide.getId(), nfnotainfoitemprodutocombustivelcide.getUserId());

		if (nfnotainfoitemprodutocombustivelcide.getId() != 0 && nfnotainfoitemprodutocombustivelcide.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response,
					nfnotainfoitemprodutocombustivelcide.getId(), null, AcaoEnum.INSERT,
					nfnotainfoitemprodutocombustivelcide.getCreateUser(), nfnotainfoitemprodutocombustivelcide.getId(),
					TabelaEnum.NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE, statusBAR, historicoBAR,
					nfnotainfoitemprodutocombustivelcide.getTransactionId(),
					nfnotainfoitemprodutocombustivelcide.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoCombustivelCIDEBAR
	 * #updateNFNotaInfoItemProdutoCombustivelCIDE(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemProdutoCombustivelCIDE)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemProdutoCombustivelCIDE(
			NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutocombustivelcide.setProcessId(nfnotainfoitemprodutocombustivelcide.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE,
				nfnotainfoitemprodutocombustivelcide, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE,
				AcaoEnum.UPDATE, nfnotainfoitemprodutocombustivelcide.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutocombustivelcide.getId(), nfnotainfoitemprodutocombustivelcide.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoCombustivelCIDEBAR
	 * #deleteNFNotaInfoItemProdutoCombustivelCIDE(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemProdutoCombustivelCIDE)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemProdutoCombustivelCIDEById(
			NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemprodutocombustivelcide.setProcessId(nfnotainfoitemprodutocombustivelcide.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE,
				nfnotainfoitemprodutocombustivelcide, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE,
				AcaoEnum.DELETE, nfnotainfoitemprodutocombustivelcide.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemprodutocombustivelcide.getId(), nfnotainfoitemprodutocombustivelcide.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoCombustivelCIDEBAR
	 * #deleteAllNFNotaInfoItemProdutoCombustivelCIDEs()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemProdutoCombustivelCIDEs() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoCombustivelCIDEBAR#
	 * fetchNFNotaInfoItemProdutoCombustivelCIDEById(com.qat.samples.sysmgmt.
	 * model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemProdutoCombustivelCIDE fetchNFNotaInfoItemProdutoCombustivelCIDEById(
			FetchByIdRequest request) {
		return (NFNotaInfoItemProdutoCombustivelCIDE) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemProdutoCombustivelCIDEBAR
	 * #fetchAllNFNotaInfoItemProdutoCombustivelCIDEsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoCombustivelCIDE> fetchAllNFNotaInfoItemProdutoCombustivelCIDEs(
			NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide) {
		InternalResultsResponse<NFNotaInfoItemProdutoCombustivelCIDE> response = new InternalResultsResponse<NFNotaInfoItemProdutoCombustivelCIDE>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemProdutoCombustivelCIDEBAR#
	 * fetchNFNotaInfoItemProdutoCombustivelCIDEsByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemProdutoCombustivelCIDE> fetchNFNotaInfoItemProdutoCombustivelCIDEsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemProdutoCombustivelCIDE> response = new InternalResultsResponse<NFNotaInfoItemProdutoCombustivelCIDE>();
		fetchNFNotaInfoItemProdutoCombustivelCIDEsByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE_COUNT,
				STMT_FETCH_NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemProdutoCombustivelCIDEsByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemProdutoCombustivelCIDEsByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================### fetchNFImpostoDevolvidosByRequest
	// ####======================================

	public static void fetchNFImpostoDevolvidosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFINFORMACAOIMPOSTODEVOLVIDO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInformacaoImpostoDevolvidoBAR#insertNFInformacaoImpostoDevolvido(com.qat.samples.sysmgmt.base.model.NFInformacaoImpostoDevolvido)
	 */
	@Override
	public InternalResponse insertNFInformacaoImpostoDevolvido(
			NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfinformacaoimpostodevolvido.setProcessId(nfinformacaoimpostodevolvido.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFINFORMACAOIMPOSTODEVOLVIDO,
				nfinformacaoimpostodevolvido, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFORMACAOIMPOSTODEVOLVIDO,
				AcaoEnum.INSERT, nfinformacaoimpostodevolvido.getTransactionId(), getHistoricoBAR(), response,
				nfinformacaoimpostodevolvido.getId(), nfinformacaoimpostodevolvido.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInformacaoImpostoDevolvidoBAR#
	 * updateNFInformacaoImpostoDevolvido(com.qat.samples.sysmgmt.base.model.
	 * NFInformacaoImpostoDevolvido)
	 */
	@Override
	public InternalResponse updateNFInformacaoImpostoDevolvido(
			NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido) {
		InternalResponse response = new InternalResponse();
		nfinformacaoimpostodevolvido.setProcessId(nfinformacaoimpostodevolvido.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFINFORMACAOIMPOSTODEVOLVIDO,
				nfinformacaoimpostodevolvido, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFORMACAOIMPOSTODEVOLVIDO,
				AcaoEnum.UPDATE, nfinformacaoimpostodevolvido.getTransactionId(), getHistoricoBAR(), response,
				nfinformacaoimpostodevolvido.getId(), nfinformacaoimpostodevolvido.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInformacaoImpostoDevolvidoBAR#
	 * deleteNFInformacaoImpostoDevolvido(com.qat.samples.sysmgmt.base.model.
	 * NFInformacaoImpostoDevolvido)
	 */
	@Override
	public InternalResponse deleteNFInformacaoImpostoDevolvidoById(
			NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido) {
		InternalResponse response = new InternalResponse();
		nfinformacaoimpostodevolvido.setProcessId(nfinformacaoimpostodevolvido.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFINFORMACAOIMPOSTODEVOLVIDO,
				nfinformacaoimpostodevolvido, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFORMACAOIMPOSTODEVOLVIDO,
				AcaoEnum.DELETE, nfinformacaoimpostodevolvido.getTransactionId(), getHistoricoBAR(), response,
				nfinformacaoimpostodevolvido.getId(), nfinformacaoimpostodevolvido.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInformacaoImpostoDevolvidoBAR#
	 * deleteAllNFInformacaoImpostoDevolvidos()
	 */
	@Override
	public InternalResponse deleteAllNFInformacaoImpostoDevolvidos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFINFORMACAOIMPOSTODEVOLVIDO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFInformacaoImpostoDevolvidoBAR#
	 * fetchNFInformacaoImpostoDevolvidoById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFInformacaoImpostoDevolvido fetchNFInformacaoImpostoDevolvidoById(FetchByIdRequest request) {
		return (NFInformacaoImpostoDevolvido) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFINFORMACAOIMPOSTODEVOLVIDO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInformacaoImpostoDevolvidoBAR#
	 * fetchAllNFInformacaoImpostoDevolvidosCache()
	 */
	@Override
	public InternalResultsResponse<NFInformacaoImpostoDevolvido> fetchAllNFInformacaoImpostoDevolvidos(
			NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido) {
		InternalResultsResponse<NFInformacaoImpostoDevolvido> response = new InternalResultsResponse<NFInformacaoImpostoDevolvido>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFINFORMACAOIMPOSTODEVOLVIDO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFInformacaoImpostoDevolvidoBAR#
	 * fetchNFInformacaoImpostoDevolvidosByRequest(com.qat.samples.sysmgmt.model
	 * .request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFInformacaoImpostoDevolvido> fetchNFInformacaoImpostoDevolvidosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFInformacaoImpostoDevolvido> response = new InternalResultsResponse<NFInformacaoImpostoDevolvido>();
		fetchNFInformacaoImpostoDevolvidosByRequest(getSqlSession(), request,
				STMT_FETCH_NFINFORMACAOIMPOSTODEVOLVIDO_COUNT, STMT_FETCH_NFINFORMACAOIMPOSTODEVOLVIDO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFInformacaoImpostoDevolvidosByRequest
	// ####======================================

	public static void fetchNFInformacaoImpostoDevolvidosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoBAR#insertNFNotaInfoItemImposto(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImposto)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImposto(NFNotaInfoItemImposto nfnotainfoitemimposto) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimposto.setProcessId(nfnotainfoitemimposto.getTransactionId());

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getIcms())) {
			count += NFNotaInfoItemImpostoICMSBARD.maintainNFNotaInfoItemImpostoICMSAssociations(
					nfnotainfoitemimposto.getIcms(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getIpi())) {
			count += NFNotaInfoItemImpostoIPIBARD.maintainNFNotaInfoItemImpostoIPIAssociations(
					nfnotainfoitemimposto.getIpi(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getImpostoImportacao())) {
			count += NFNotaInfoItemImpostoImportacaoBARD.maintainNFNotaInfoItemImpostoImportacaoAssociations(
					nfnotainfoitemimposto.getImpostoImportacao(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getIssqn())) {
			count += NFNotaInfoItemImpostoISSQNBARD.maintainNFNotaInfoItemImpostoISSQNAssociations(
					nfnotainfoitemimposto.getIssqn(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getPis())) {
			count += NFNotaInfoItemImpostoPISBARD.maintainNFNotaInfoItemImpostoPISAssociations(
					nfnotainfoitemimposto.getPis(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getPisst())) {
			count += NFNotaInfoItemImpostoPISSTBARD.maintainNFNotaInfoItemImpostoPISSTAssociations(
					nfnotainfoitemimposto.getPisst(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getCofins())) {
			count += NFNotaInfoItemImpostoCOFINSBARD.maintainNFNotaInfoItemImpostoCOFINSAssociations(
					nfnotainfoitemimposto.getCofins(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getCofinsst())) {
			count += NFNotaInfoItemImpostoCOFINSSTBARD.maintainNFNotaInfoItemImpostoCOFINSSTAssociations(
					nfnotainfoitemimposto.getCofinsst(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getIcmsUfDestino())) {
			count += NFNotaInfoItemImpostoICMSUFDestinoBARD.maintainNFNotaInfoItemImpostoICMSUFDestinoAssociations(
					nfnotainfoitemimposto.getIcmsUfDestino(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTO, nfnotainfoitemimposto, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTO, AcaoEnum.INSERT,
				nfnotainfoitemimposto.getTransactionId(), getHistoricoBAR(), response, nfnotainfoitemimposto.getId(),
				nfnotainfoitemimposto.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoBAR#
	 * updateNFNotaInfoItemImposto(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImposto)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImposto(NFNotaInfoItemImposto nfnotainfoitemimposto) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimposto.setProcessId(nfnotainfoitemimposto.getTransactionId());
		Integer count = 0;
		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getIcms())) {
			count += NFNotaInfoItemImpostoICMSBARD.maintainNFNotaInfoItemImpostoICMSAssociations(
					nfnotainfoitemimposto.getIcms(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getIpi())) {
			count += NFNotaInfoItemImpostoIPIBARD.maintainNFNotaInfoItemImpostoIPIAssociations(
					nfnotainfoitemimposto.getIpi(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getImpostoImportacao())) {
			count += NFNotaInfoItemImpostoImportacaoBARD.maintainNFNotaInfoItemImpostoImportacaoAssociations(
					nfnotainfoitemimposto.getImpostoImportacao(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getIssqn())) {
			count += NFNotaInfoItemImpostoISSQNBARD.maintainNFNotaInfoItemImpostoISSQNAssociations(
					nfnotainfoitemimposto.getIssqn(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getPis())) {
			count += NFNotaInfoItemImpostoPISBARD.maintainNFNotaInfoItemImpostoPISAssociations(
					nfnotainfoitemimposto.getPis(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getPisst())) {
			count += NFNotaInfoItemImpostoPISSTBARD.maintainNFNotaInfoItemImpostoPISSTAssociations(
					nfnotainfoitemimposto.getPisst(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getCofins())) {
			count += NFNotaInfoItemImpostoCOFINSBARD.maintainNFNotaInfoItemImpostoCOFINSAssociations(
					nfnotainfoitemimposto.getCofins(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getCofinsst())) {
			count += NFNotaInfoItemImpostoCOFINSSTBARD.maintainNFNotaInfoItemImpostoCOFINSSTAssociations(
					nfnotainfoitemimposto.getCofinsst(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getIcmsUfDestino())) {
			count += NFNotaInfoItemImpostoICMSUFDestinoBARD.maintainNFNotaInfoItemImpostoICMSUFDestinoAssociations(
					nfnotainfoitemimposto.getIcmsUfDestino(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTO, nfnotainfoitemimposto, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTO, AcaoEnum.UPDATE,
				nfnotainfoitemimposto.getTransactionId(), getHistoricoBAR(), response, nfnotainfoitemimposto.getId(),
				nfnotainfoitemimposto.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoBAR#
	 * deleteNFNotaInfoItemImposto(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImposto)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoById(NFNotaInfoItemImposto nfnotainfoitemimposto) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimposto.setProcessId(nfnotainfoitemimposto.getTransactionId());

		Integer count = 0;
		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getIcms())) {
			count += NFNotaInfoItemImpostoICMSBARD.maintainNFNotaInfoItemImpostoICMSAssociations(
					nfnotainfoitemimposto.getIcms(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getIpi())) {
			count += NFNotaInfoItemImpostoIPIBARD.maintainNFNotaInfoItemImpostoIPIAssociations(
					nfnotainfoitemimposto.getIpi(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getImpostoImportacao())) {
			count += NFNotaInfoItemImpostoImportacaoBARD.maintainNFNotaInfoItemImpostoImportacaoAssociations(
					nfnotainfoitemimposto.getImpostoImportacao(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getIssqn())) {
			count += NFNotaInfoItemImpostoISSQNBARD.maintainNFNotaInfoItemImpostoISSQNAssociations(
					nfnotainfoitemimposto.getIssqn(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getPis())) {
			count += NFNotaInfoItemImpostoPISBARD.maintainNFNotaInfoItemImpostoPISAssociations(
					nfnotainfoitemimposto.getPis(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getPisst())) {
			count += NFNotaInfoItemImpostoPISSTBARD.maintainNFNotaInfoItemImpostoPISSTAssociations(
					nfnotainfoitemimposto.getPisst(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getCofins())) {
			count += NFNotaInfoItemImpostoCOFINSBARD.maintainNFNotaInfoItemImpostoCOFINSAssociations(
					nfnotainfoitemimposto.getCofins(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getCofinsst())) {
			count += NFNotaInfoItemImpostoCOFINSSTBARD.maintainNFNotaInfoItemImpostoCOFINSSTAssociations(
					nfnotainfoitemimposto.getCofinsst(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimposto.getIcmsUfDestino())) {
			count += NFNotaInfoItemImpostoICMSUFDestinoBARD.maintainNFNotaInfoItemImpostoICMSUFDestinoAssociations(
					nfnotainfoitemimposto.getIcmsUfDestino(), response, nfnotainfoitemimposto.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfoitemimposto.getId(),
					nfnotainfoitemimposto.getCreateUser(), nfnotainfoitemimposto.getTransactionId(),
					nfnotainfoitemimposto.getTransactionId());
		}

		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTO, nfnotainfoitemimposto, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTO, AcaoEnum.DELETE,
				nfnotainfoitemimposto.getTransactionId(), getHistoricoBAR(), response, nfnotainfoitemimposto.getId(),
				nfnotainfoitemimposto.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoBAR#
	 * deleteAllNFNotaInfoItemImpostos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoBAR#
	 * fetchNFNotaInfoItemImpostoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImposto fetchNFNotaInfoItemImpostoById(FetchByIdRequest request) {
		return (NFNotaInfoItemImposto) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoBAR#
	 * fetchAllNFNotaInfoItemImpostosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImposto> fetchAllNFNotaInfoItemImpostos(
			NFNotaInfoItemImposto nfnotainfoitemimposto) {
		InternalResultsResponse<NFNotaInfoItemImposto> response = new InternalResultsResponse<NFNotaInfoItemImposto>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoBAR#
	 * fetchNFNotaInfoItemImpostosByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImposto> fetchNFNotaInfoItemImpostosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImposto> response = new InternalResultsResponse<NFNotaInfoItemImposto>();
		fetchNFNotaInfoItemImpostosByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOITEMIMPOSTO_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSBAR#insertNFNotaInfoItemImpostoICMS(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMS)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMS(NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicms.setProcessId(nfnotainfoitemimpostoicms.getTransactionId());

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms00())) {
			count += NFNotaInfoItemImpostoICMS00BARD.maintainNFNotaInfoItemImpostoICMS00Associations(
					nfnotainfoitemimpostoicms.getIcms00(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms10())) {
			count += NFNotaInfoItemImpostoICMS10BARD.maintainNFNotaInfoItemImpostoICMS10Associations(
					nfnotainfoitemimpostoicms.getIcms10(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms20())) {
			count += NFNotaInfoItemImpostoICMS20BARD.maintainNFNotaInfoItemImpostoICMS20Associations(
					nfnotainfoitemimpostoicms.getIcms20(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms30())) {
			count += NFNotaInfoItemImpostoICMS30BARD.maintainNFNotaInfoItemImpostoICMS30Associations(
					nfnotainfoitemimpostoicms.getIcms30(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms40())) {
			count += NFNotaInfoItemImpostoICMS40BARD.maintainNFNotaInfoItemImpostoICMS40Associations(
					nfnotainfoitemimpostoicms.getIcms40(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms51())) {
			count += NFNotaInfoItemImpostoICMS51BARD.maintainNFNotaInfoItemImpostoICMS51Associations(
					nfnotainfoitemimpostoicms.getIcms51(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms60())) {
			count += NFNotaInfoItemImpostoICMS60BARD.maintainNFNotaInfoItemImpostoICMS60Associations(
					nfnotainfoitemimpostoicms.getIcms60(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms70())) {
			count += NFNotaInfoItemImpostoICMS70BARD.maintainNFNotaInfoItemImpostoICMS70Associations(
					nfnotainfoitemimpostoicms.getIcms70(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms90())) {
			count += NFNotaInfoItemImpostoICMS90BARD.maintainNFNotaInfoItemImpostoICMS90Associations(
					nfnotainfoitemimpostoicms.getIcms90(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmsPartilhado())) {
			count += NFNotaInfoItemImpostoICMSPartilhadoBARD.maintainNFNotaInfoItemImpostoICMSPartilhadoAssociations(
					nfnotainfoitemimpostoicms.getIcmsPartilhado(), response, nfnotainfoitemimpostoicms.getId(), null,
					null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmsst())) {
			count += NFNotaInfoItemImpostoICMSSTBARD.maintainNFNotaInfoItemImpostoICMSSTAssociations(
					nfnotainfoitemimpostoicms.getIcmsst(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn101())) {
			count += NFNotaInfoItemImpostoICMSSN101BARD.maintainNFNotaInfoItemImpostoICMSSN101Associations(
					nfnotainfoitemimpostoicms.getIcmssn101(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn102())) {
			count += NFNotaInfoItemImpostoICMSSN102BARD.maintainNFNotaInfoItemImpostoICMSSN102Associations(
					nfnotainfoitemimpostoicms.getIcmssn102(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn201())) {
			count += NFNotaInfoItemImpostoICMSSN201BARD.maintainNFNotaInfoItemImpostoICMSSN201Associations(
					nfnotainfoitemimpostoicms.getIcmssn201(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn202())) {
			count += NFNotaInfoItemImpostoICMSSN202BARD.maintainNFNotaInfoItemImpostoICMSSN202Associations(
					nfnotainfoitemimpostoicms.getIcmssn202(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn500())) {
			count += NFNotaInfoItemImpostoICMSSN500BARD.maintainNFNotaInfoItemImpostoICMSSN500Associations(
					nfnotainfoitemimpostoicms.getIcmssn500(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn900())) {
			count += NFNotaInfoItemImpostoICMSSN900BARD.maintainNFNotaInfoItemImpostoICMSSN900Associations(
					nfnotainfoitemimpostoicms.getIcmssn900(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS, nfnotainfoitemimpostoicms,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS, AcaoEnum.INSERT,
				nfnotainfoitemimpostoicms.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getUserId());

		if (nfnotainfoitemimpostoicms.getId() != 0 && nfnotainfoitemimpostoicms.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfoitemimpostoicms.getId(),
					null, AcaoEnum.INSERT, nfnotainfoitemimpostoicms.getCreateUser(), nfnotainfoitemimpostoicms.getId(),
					TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS, statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSBAR#
	 * updateNFNotaInfoItemImpostoICMS(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMS(NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms.setProcessId(nfnotainfoitemimpostoicms.getTransactionId());
		Integer count = 0;
		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms00())) {
			count += NFNotaInfoItemImpostoICMS00BARD.maintainNFNotaInfoItemImpostoICMS00Associations(
					nfnotainfoitemimpostoicms.getIcms00(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms10())) {
			count += NFNotaInfoItemImpostoICMS10BARD.maintainNFNotaInfoItemImpostoICMS10Associations(
					nfnotainfoitemimpostoicms.getIcms10(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms20())) {
			count += NFNotaInfoItemImpostoICMS20BARD.maintainNFNotaInfoItemImpostoICMS20Associations(
					nfnotainfoitemimpostoicms.getIcms20(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms30())) {
			count += NFNotaInfoItemImpostoICMS30BARD.maintainNFNotaInfoItemImpostoICMS30Associations(
					nfnotainfoitemimpostoicms.getIcms30(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms40())) {
			count += NFNotaInfoItemImpostoICMS40BARD.maintainNFNotaInfoItemImpostoICMS40Associations(
					nfnotainfoitemimpostoicms.getIcms40(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms51())) {
			count += NFNotaInfoItemImpostoICMS51BARD.maintainNFNotaInfoItemImpostoICMS51Associations(
					nfnotainfoitemimpostoicms.getIcms51(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms60())) {
			count += NFNotaInfoItemImpostoICMS60BARD.maintainNFNotaInfoItemImpostoICMS60Associations(
					nfnotainfoitemimpostoicms.getIcms60(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms70())) {
			count += NFNotaInfoItemImpostoICMS70BARD.maintainNFNotaInfoItemImpostoICMS70Associations(
					nfnotainfoitemimpostoicms.getIcms70(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms90())) {
			count += NFNotaInfoItemImpostoICMS90BARD.maintainNFNotaInfoItemImpostoICMS90Associations(
					nfnotainfoitemimpostoicms.getIcms90(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmsPartilhado())) {
			count += NFNotaInfoItemImpostoICMSPartilhadoBARD.maintainNFNotaInfoItemImpostoICMSPartilhadoAssociations(
					nfnotainfoitemimpostoicms.getIcmsPartilhado(), response, nfnotainfoitemimpostoicms.getId(), null,
					null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmsst())) {
			count += NFNotaInfoItemImpostoICMSSTBARD.maintainNFNotaInfoItemImpostoICMSSTAssociations(
					nfnotainfoitemimpostoicms.getIcmsst(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn101())) {
			count += NFNotaInfoItemImpostoICMSSN101BARD.maintainNFNotaInfoItemImpostoICMSSN101Associations(
					nfnotainfoitemimpostoicms.getIcmssn101(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn102())) {
			count += NFNotaInfoItemImpostoICMSSN102BARD.maintainNFNotaInfoItemImpostoICMSSN102Associations(
					nfnotainfoitemimpostoicms.getIcmssn102(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn201())) {
			count += NFNotaInfoItemImpostoICMSSN201BARD.maintainNFNotaInfoItemImpostoICMSSN201Associations(
					nfnotainfoitemimpostoicms.getIcmssn201(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn202())) {
			count += NFNotaInfoItemImpostoICMSSN202BARD.maintainNFNotaInfoItemImpostoICMSSN202Associations(
					nfnotainfoitemimpostoicms.getIcmssn202(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn500())) {
			count += NFNotaInfoItemImpostoICMSSN500BARD.maintainNFNotaInfoItemImpostoICMSSN500Associations(
					nfnotainfoitemimpostoicms.getIcmssn500(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn900())) {
			count += NFNotaInfoItemImpostoICMSSN900BARD.maintainNFNotaInfoItemImpostoICMSSN900Associations(
					nfnotainfoitemimpostoicms.getIcmssn900(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS, nfnotainfoitemimpostoicms,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoicms.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSBAR#
	 * deleteNFNotaInfoItemImpostoICMS(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMSById(NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms.setProcessId(nfnotainfoitemimpostoicms.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS, nfnotainfoitemimpostoicms,
				response);
		Integer count = 0;
		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms00())) {
			count += NFNotaInfoItemImpostoICMS00BARD.maintainNFNotaInfoItemImpostoICMS00Associations(
					nfnotainfoitemimpostoicms.getIcms00(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms10())) {
			count += NFNotaInfoItemImpostoICMS10BARD.maintainNFNotaInfoItemImpostoICMS10Associations(
					nfnotainfoitemimpostoicms.getIcms10(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms20())) {
			count += NFNotaInfoItemImpostoICMS20BARD.maintainNFNotaInfoItemImpostoICMS20Associations(
					nfnotainfoitemimpostoicms.getIcms20(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms30())) {
			count += NFNotaInfoItemImpostoICMS30BARD.maintainNFNotaInfoItemImpostoICMS30Associations(
					nfnotainfoitemimpostoicms.getIcms30(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms40())) {
			count += NFNotaInfoItemImpostoICMS40BARD.maintainNFNotaInfoItemImpostoICMS40Associations(
					nfnotainfoitemimpostoicms.getIcms40(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms51())) {
			count += NFNotaInfoItemImpostoICMS51BARD.maintainNFNotaInfoItemImpostoICMS51Associations(
					nfnotainfoitemimpostoicms.getIcms51(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms60())) {
			count += NFNotaInfoItemImpostoICMS60BARD.maintainNFNotaInfoItemImpostoICMS60Associations(
					nfnotainfoitemimpostoicms.getIcms60(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms70())) {
			count += NFNotaInfoItemImpostoICMS70BARD.maintainNFNotaInfoItemImpostoICMS70Associations(
					nfnotainfoitemimpostoicms.getIcms70(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcms90())) {
			count += NFNotaInfoItemImpostoICMS90BARD.maintainNFNotaInfoItemImpostoICMS90Associations(
					nfnotainfoitemimpostoicms.getIcms90(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmsPartilhado())) {
			count += NFNotaInfoItemImpostoICMSPartilhadoBARD.maintainNFNotaInfoItemImpostoICMSPartilhadoAssociations(
					nfnotainfoitemimpostoicms.getIcmsPartilhado(), response, nfnotainfoitemimpostoicms.getId(), null,
					null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmsst())) {
			count += NFNotaInfoItemImpostoICMSSTBARD.maintainNFNotaInfoItemImpostoICMSSTAssociations(
					nfnotainfoitemimpostoicms.getIcmsst(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn101())) {
			count += NFNotaInfoItemImpostoICMSSN101BARD.maintainNFNotaInfoItemImpostoICMSSN101Associations(
					nfnotainfoitemimpostoicms.getIcmssn101(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn102())) {
			count += NFNotaInfoItemImpostoICMSSN102BARD.maintainNFNotaInfoItemImpostoICMSSN102Associations(
					nfnotainfoitemimpostoicms.getIcmssn102(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn201())) {
			count += NFNotaInfoItemImpostoICMSSN201BARD.maintainNFNotaInfoItemImpostoICMSSN201Associations(
					nfnotainfoitemimpostoicms.getIcmssn201(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn202())) {
			count += NFNotaInfoItemImpostoICMSSN202BARD.maintainNFNotaInfoItemImpostoICMSSN202Associations(
					nfnotainfoitemimpostoicms.getIcmssn202(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn500())) {
			count += NFNotaInfoItemImpostoICMSSN500BARD.maintainNFNotaInfoItemImpostoICMSSN500Associations(
					nfnotainfoitemimpostoicms.getIcmssn500(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoicms.getIcmssn900())) {
			count += NFNotaInfoItemImpostoICMSSN900BARD.maintainNFNotaInfoItemImpostoICMSSN900Associations(
					nfnotainfoitemimpostoicms.getIcmssn900(), response, nfnotainfoitemimpostoicms.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getCreateUser(),
					nfnotainfoitemimpostoicms.getTransactionId(), nfnotainfoitemimpostoicms.getTransactionId());
		}

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS, AcaoEnum.DELETE,
				nfnotainfoitemimpostoicms.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicms.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSBAR#
	 * deleteAllNFNotaInfoItemImpostoICMSs()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSs() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSBAR#
	 * fetchNFNotaInfoItemImpostoICMSById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMS fetchNFNotaInfoItemImpostoICMSById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMS) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSBAR#
	 * fetchAllNFNotaInfoItemImpostoICMSsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS> fetchAllNFNotaInfoItemImpostoICMSs(
			NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSBAR#
	 * fetchNFNotaInfoItemImpostoICMSsByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS> fetchNFNotaInfoItemImpostoICMSsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS>();
		fetchNFNotaInfoItemImpostoICMSsByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMSsByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMSsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS00
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS00BAR#insertNFNotaInfoItemImpostoICMS00(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMS00)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMS00(NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicms00.setProcessId(nfnotainfoitemimpostoicms00.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS00, nfnotainfoitemimpostoicms00,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS00, AcaoEnum.INSERT,
				nfnotainfoitemimpostoicms00.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms00.getId(), nfnotainfoitemimpostoicms00.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS00BAR#
	 * updateNFNotaInfoItemImpostoICMS00(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS00)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMS00(NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms00.setProcessId(nfnotainfoitemimpostoicms00.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS00, nfnotainfoitemimpostoicms00,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS00, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoicms00.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms00.getId(), nfnotainfoitemimpostoicms00.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS00BAR#
	 * deleteNFNotaInfoItemImpostoICMS00(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS00)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMS00ById(
			NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms00.setProcessId(nfnotainfoitemimpostoicms00.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS00, nfnotainfoitemimpostoicms00,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS00, AcaoEnum.DELETE,
				nfnotainfoitemimpostoicms00.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms00.getId(), nfnotainfoitemimpostoicms00.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS00BAR#
	 * deleteAllNFNotaInfoItemImpostoICMS00s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS00s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS00_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS00BAR#
	 * fetchNFNotaInfoItemImpostoICMS00ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMS00 fetchNFNotaInfoItemImpostoICMS00ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMS00) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS00, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS00BAR#
	 * fetchAllNFNotaInfoItemImpostoICMS00sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS00> fetchAllNFNotaInfoItemImpostoICMS00s(
			NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS00> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS00>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS00_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS00BAR#
	 * fetchNFNotaInfoItemImpostoICMS00sByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS00> fetchNFNotaInfoItemImpostoICMS00sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS00> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS00>();
		fetchNFNotaInfoItemImpostoICMS00sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS00_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS00_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMS00sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMS00sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS10
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS10BAR#insertNFNotaInfoItemImpostoICMS10(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMS10)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMS10(NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicms10.setProcessId(nfnotainfoitemimpostoicms10.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS10, nfnotainfoitemimpostoicms10,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS10, AcaoEnum.INSERT,
				nfnotainfoitemimpostoicms10.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms10.getId(), nfnotainfoitemimpostoicms10.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS10BAR#
	 * updateNFNotaInfoItemImpostoICMS10(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS10)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMS10(NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms10.setProcessId(nfnotainfoitemimpostoicms10.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS10, nfnotainfoitemimpostoicms10,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS10, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoicms10.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms10.getId(), nfnotainfoitemimpostoicms10.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS10BAR#
	 * deleteNFNotaInfoItemImpostoICMS10(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS10)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMS10ById(
			NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms10.setProcessId(nfnotainfoitemimpostoicms10.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS10, nfnotainfoitemimpostoicms10,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS10, AcaoEnum.DELETE,
				nfnotainfoitemimpostoicms10.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms10.getId(), nfnotainfoitemimpostoicms10.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS10BAR#
	 * deleteAllNFNotaInfoItemImpostoICMS10s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS10s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS10_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS10BAR#
	 * fetchNFNotaInfoItemImpostoICMS10ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMS10 fetchNFNotaInfoItemImpostoICMS10ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMS10) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS10, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS10BAR#
	 * fetchAllNFNotaInfoItemImpostoICMS10sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS10> fetchAllNFNotaInfoItemImpostoICMS10s(
			NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS10> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS10>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS10_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS10BAR#
	 * fetchNFNotaInfoItemImpostoICMS10sByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS10> fetchNFNotaInfoItemImpostoICMS10sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS10> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS10>();
		fetchNFNotaInfoItemImpostoICMS10sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS10_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS10_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMS10sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMS10sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS20
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS20BAR#insertNFNotaInfoItemImpostoICMS20(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMS20)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMS20(NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicms20.setProcessId(nfnotainfoitemimpostoicms20.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS20, nfnotainfoitemimpostoicms20,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS20, AcaoEnum.INSERT,
				nfnotainfoitemimpostoicms20.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms20.getId(), nfnotainfoitemimpostoicms20.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS20BAR#
	 * updateNFNotaInfoItemImpostoICMS20(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS20)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMS20(NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms20.setProcessId(nfnotainfoitemimpostoicms20.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS20, nfnotainfoitemimpostoicms20,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS20, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoicms20.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms20.getId(), nfnotainfoitemimpostoicms20.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS20BAR#
	 * deleteNFNotaInfoItemImpostoICMS20(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS20)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMS20ById(
			NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms20.setProcessId(nfnotainfoitemimpostoicms20.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS20, nfnotainfoitemimpostoicms20,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS20, AcaoEnum.DELETE,
				nfnotainfoitemimpostoicms20.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms20.getId(), nfnotainfoitemimpostoicms20.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS20BAR#
	 * deleteAllNFNotaInfoItemImpostoICMS20s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS20s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS20_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS20BAR#
	 * fetchNFNotaInfoItemImpostoICMS20ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMS20 fetchNFNotaInfoItemImpostoICMS20ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMS20) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS20, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS20BAR#
	 * fetchAllNFNotaInfoItemImpostoICMS20sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS20> fetchAllNFNotaInfoItemImpostoICMS20s(
			NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS20> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS20>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS20_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS20BAR#
	 * fetchNFNotaInfoItemImpostoICMS20sByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS20> fetchNFNotaInfoItemImpostoICMS20sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS20> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS20>();
		fetchNFNotaInfoItemImpostoICMS20sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS20_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS20_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMS20sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMS20sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS30
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS30BAR#insertNFNotaInfoItemImpostoICMS30(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMS30)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMS30(NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicms30.setProcessId(nfnotainfoitemimpostoicms30.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS30, nfnotainfoitemimpostoicms30,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS30, AcaoEnum.INSERT,
				nfnotainfoitemimpostoicms30.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms30.getId(), nfnotainfoitemimpostoicms30.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS30BAR#
	 * updateNFNotaInfoItemImpostoICMS30(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS30)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMS30(NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms30.setProcessId(nfnotainfoitemimpostoicms30.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS30, nfnotainfoitemimpostoicms30,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS30, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoicms30.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms30.getId(), nfnotainfoitemimpostoicms30.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS30BAR#
	 * deleteNFNotaInfoItemImpostoICMS30(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS30)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMS30ById(
			NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms30.setProcessId(nfnotainfoitemimpostoicms30.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS30, nfnotainfoitemimpostoicms30,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS30, AcaoEnum.DELETE,
				nfnotainfoitemimpostoicms30.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms30.getId(), nfnotainfoitemimpostoicms30.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS30BAR#
	 * deleteAllNFNotaInfoItemImpostoICMS30s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS30s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS30_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS30BAR#
	 * fetchNFNotaInfoItemImpostoICMS30ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMS30 fetchNFNotaInfoItemImpostoICMS30ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMS30) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS30, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS30BAR#
	 * fetchAllNFNotaInfoItemImpostoICMS30sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS30> fetchAllNFNotaInfoItemImpostoICMS30s(
			NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS30> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS30>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS30_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS30BAR#
	 * fetchNFNotaInfoItemImpostoICMS30sByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS30> fetchNFNotaInfoItemImpostoICMS30sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS30> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS30>();
		fetchNFNotaInfoItemImpostoICMS30sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS30_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS30_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMS30sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMS30sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS40
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS40BAR#insertNFNotaInfoItemImpostoICMS40(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMS40)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMS40(NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicms40.setProcessId(nfnotainfoitemimpostoicms40.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS40, nfnotainfoitemimpostoicms40,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS40, AcaoEnum.INSERT,
				nfnotainfoitemimpostoicms40.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms40.getId(), nfnotainfoitemimpostoicms40.getUserId());

		if (nfnotainfoitemimpostoicms40.getId() != 0 && nfnotainfoitemimpostoicms40.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfoitemimpostoicms40.getId(),
					null, AcaoEnum.INSERT, nfnotainfoitemimpostoicms40.getCreateUser(),
					nfnotainfoitemimpostoicms40.getId(), TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS40, statusBAR,
					historicoBAR, nfnotainfoitemimpostoicms40.getTransactionId(),
					nfnotainfoitemimpostoicms40.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS40BAR#
	 * updateNFNotaInfoItemImpostoICMS40(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS40)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMS40(NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms40.setProcessId(nfnotainfoitemimpostoicms40.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS40, nfnotainfoitemimpostoicms40,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS40, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoicms40.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms40.getId(), nfnotainfoitemimpostoicms40.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS40BAR#
	 * deleteNFNotaInfoItemImpostoICMS40(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS40)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMS40ById(
			NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms40.setProcessId(nfnotainfoitemimpostoicms40.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS40, nfnotainfoitemimpostoicms40,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS40, AcaoEnum.DELETE,
				nfnotainfoitemimpostoicms40.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms40.getId(), nfnotainfoitemimpostoicms40.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS40BAR#
	 * deleteAllNFNotaInfoItemImpostoICMS40s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS40s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS40_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS40BAR#
	 * fetchNFNotaInfoItemImpostoICMS40ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMS40 fetchNFNotaInfoItemImpostoICMS40ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMS40) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS40, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS40BAR#
	 * fetchAllNFNotaInfoItemImpostoICMS40sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS40> fetchAllNFNotaInfoItemImpostoICMS40s(
			NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS40> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS40>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS40_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS40BAR#
	 * fetchNFNotaInfoItemImpostoICMS40sByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS40> fetchNFNotaInfoItemImpostoICMS40sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS40> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS40>();
		fetchNFNotaInfoItemImpostoICMS40sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS40_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS40_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMS40sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMS40sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS51
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS51BAR#insertNFNotaInfoItemImpostoICMS51(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMS51)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMS51(NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicms51.setProcessId(nfnotainfoitemimpostoicms51.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS51, nfnotainfoitemimpostoicms51,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS51, AcaoEnum.INSERT,
				nfnotainfoitemimpostoicms51.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms51.getId(), nfnotainfoitemimpostoicms51.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS51BAR#
	 * updateNFNotaInfoItemImpostoICMS51(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS51)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMS51(NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms51.setProcessId(nfnotainfoitemimpostoicms51.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS51, nfnotainfoitemimpostoicms51,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS51, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoicms51.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms51.getId(), nfnotainfoitemimpostoicms51.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS51BAR#
	 * deleteNFNotaInfoItemImpostoICMS51(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS51)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMS51ById(
			NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms51.setProcessId(nfnotainfoitemimpostoicms51.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS51, nfnotainfoitemimpostoicms51,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS51, AcaoEnum.DELETE,
				nfnotainfoitemimpostoicms51.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms51.getId(), nfnotainfoitemimpostoicms51.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS51BAR#
	 * deleteAllNFNotaInfoItemImpostoICMS51s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS51s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS51_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS51BAR#
	 * fetchNFNotaInfoItemImpostoICMS51ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMS51 fetchNFNotaInfoItemImpostoICMS51ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMS51) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS51, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS51BAR#
	 * fetchAllNFNotaInfoItemImpostoICMS51sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS51> fetchAllNFNotaInfoItemImpostoICMS51s(
			NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS51> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS51>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS51_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS51BAR#
	 * fetchNFNotaInfoItemImpostoICMS51sByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS51> fetchNFNotaInfoItemImpostoICMS51sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS51> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS51>();
		fetchNFNotaInfoItemImpostoICMS51sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS51_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS51_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMS51sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMS51sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS60
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS60BAR#insertNFNotaInfoItemImpostoICMS60(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMS60)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMS60(NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicms60.setProcessId(nfnotainfoitemimpostoicms60.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS60, nfnotainfoitemimpostoicms60,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS60, AcaoEnum.INSERT,
				nfnotainfoitemimpostoicms60.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms60.getId(), nfnotainfoitemimpostoicms60.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS60BAR#
	 * updateNFNotaInfoItemImpostoICMS60(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS60)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMS60(NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms60.setProcessId(nfnotainfoitemimpostoicms60.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS60, nfnotainfoitemimpostoicms60,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS60, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoicms60.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms60.getId(), nfnotainfoitemimpostoicms60.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS60BAR#
	 * deleteNFNotaInfoItemImpostoICMS60(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS60)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMS60ById(
			NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms60.setProcessId(nfnotainfoitemimpostoicms60.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS60, nfnotainfoitemimpostoicms60,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS60, AcaoEnum.DELETE,
				nfnotainfoitemimpostoicms60.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms60.getId(), nfnotainfoitemimpostoicms60.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS60BAR#
	 * deleteAllNFNotaInfoItemImpostoICMS60s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS60s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS60_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS60BAR#
	 * fetchNFNotaInfoItemImpostoICMS60ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMS60 fetchNFNotaInfoItemImpostoICMS60ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMS60) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS60, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS60BAR#
	 * fetchAllNFNotaInfoItemImpostoICMS60sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS60> fetchAllNFNotaInfoItemImpostoICMS60s(
			NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS60> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS60>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS60_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS60BAR#
	 * fetchNFNotaInfoItemImpostoICMS60sByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS60> fetchNFNotaInfoItemImpostoICMS60sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS60> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS60>();
		fetchNFNotaInfoItemImpostoICMS60sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS60_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS60_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMS60sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMS60sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS70
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS70BAR#insertNFNotaInfoItemImpostoICMS70(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMS70)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMS70(NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicms70.setProcessId(nfnotainfoitemimpostoicms70.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS70, nfnotainfoitemimpostoicms70,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS70, AcaoEnum.INSERT,
				nfnotainfoitemimpostoicms70.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms70.getId(), nfnotainfoitemimpostoicms70.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS70BAR#
	 * updateNFNotaInfoItemImpostoICMS70(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS70)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMS70(NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms70.setProcessId(nfnotainfoitemimpostoicms70.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS70, nfnotainfoitemimpostoicms70,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS70, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoicms70.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms70.getId(), nfnotainfoitemimpostoicms70.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS70BAR#
	 * deleteNFNotaInfoItemImpostoICMS70(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS70)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMS70ById(
			NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms70.setProcessId(nfnotainfoitemimpostoicms70.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS70, nfnotainfoitemimpostoicms70,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS70, AcaoEnum.DELETE,
				nfnotainfoitemimpostoicms70.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms70.getId(), nfnotainfoitemimpostoicms70.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS70BAR#
	 * deleteAllNFNotaInfoItemImpostoICMS70s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS70s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS70_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS70BAR#
	 * fetchNFNotaInfoItemImpostoICMS70ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMS70 fetchNFNotaInfoItemImpostoICMS70ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMS70) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS70, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS70BAR#
	 * fetchAllNFNotaInfoItemImpostoICMS70sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS70> fetchAllNFNotaInfoItemImpostoICMS70s(
			NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS70> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS70>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS70_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS70BAR#
	 * fetchNFNotaInfoItemImpostoICMS70sByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS70> fetchNFNotaInfoItemImpostoICMS70sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS70> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS70>();
		fetchNFNotaInfoItemImpostoICMS70sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS70_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS70_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMS70sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMS70sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS90
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS90BAR#insertNFNotaInfoItemImpostoICMS90(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMS90)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMS90(NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicms90.setProcessId(nfnotainfoitemimpostoicms90.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMS90, nfnotainfoitemimpostoicms90,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS90, AcaoEnum.INSERT,
				nfnotainfoitemimpostoicms90.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms90.getId(), nfnotainfoitemimpostoicms90.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS90BAR#
	 * updateNFNotaInfoItemImpostoICMS90(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS90)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMS90(NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms90.setProcessId(nfnotainfoitemimpostoicms90.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMS90, nfnotainfoitemimpostoicms90,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS90, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoicms90.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms90.getId(), nfnotainfoitemimpostoicms90.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS90BAR#
	 * deleteNFNotaInfoItemImpostoICMS90(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMS90)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMS90ById(
			NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicms90.setProcessId(nfnotainfoitemimpostoicms90.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS90, nfnotainfoitemimpostoicms90,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS90, AcaoEnum.DELETE,
				nfnotainfoitemimpostoicms90.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicms90.getId(), nfnotainfoitemimpostoicms90.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS90BAR#
	 * deleteAllNFNotaInfoItemImpostoICMS90s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS90s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMS90_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS90BAR#
	 * fetchNFNotaInfoItemImpostoICMS90ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMS90 fetchNFNotaInfoItemImpostoICMS90ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMS90) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS90, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMS90BAR#
	 * fetchAllNFNotaInfoItemImpostoICMS90sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS90> fetchAllNFNotaInfoItemImpostoICMS90s(
			NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS90> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS90>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS90_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMS90BAR#
	 * fetchNFNotaInfoItemImpostoICMS90sByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS90> fetchNFNotaInfoItemImpostoICMS90sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMS90> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMS90>();
		fetchNFNotaInfoItemImpostoICMS90sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS90_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMS90_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMS90sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMS90sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOICMSPARTILHADO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSPartilhadoBAR#insertNFNotaInfoItemImpostoICMSPartilhado(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMSPartilhado)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMSPartilhado(
			NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicmspartilhado.setProcessId(nfnotainfoitemimpostoicmspartilhado.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO,
				nfnotainfoitemimpostoicmspartilhado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSPARTILHADO,
				AcaoEnum.INSERT, nfnotainfoitemimpostoicmspartilhado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmspartilhado.getId(), nfnotainfoitemimpostoicmspartilhado.getUserId());
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSPartilhadoBAR#
	 * updateNFNotaInfoItemImpostoICMSPartilhado(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoICMSPartilhado)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMSPartilhado(
			NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmspartilhado.setProcessId(nfnotainfoitemimpostoicmspartilhado.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO,
				nfnotainfoitemimpostoicmspartilhado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSPARTILHADO,
				AcaoEnum.UPDATE, nfnotainfoitemimpostoicmspartilhado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmspartilhado.getId(), nfnotainfoitemimpostoicmspartilhado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSPartilhadoBAR#
	 * deleteNFNotaInfoItemImpostoICMSPartilhado(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoICMSPartilhado)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMSPartilhadoById(
			NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmspartilhado.setProcessId(nfnotainfoitemimpostoicmspartilhado.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO,
				nfnotainfoitemimpostoicmspartilhado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSPARTILHADO,
				AcaoEnum.DELETE, nfnotainfoitemimpostoicmspartilhado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmspartilhado.getId(), nfnotainfoitemimpostoicmspartilhado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSPartilhadoBAR#
	 * deleteAllNFNotaInfoItemImpostoICMSPartilhados()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSPartilhados() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSPartilhadoBAR#
	 * fetchNFNotaInfoItemImpostoICMSPartilhadoById(com.qat.samples.sysmgmt.
	 * model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMSPartilhado fetchNFNotaInfoItemImpostoICMSPartilhadoById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMSPartilhado) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSPartilhadoBAR#
	 * fetchAllNFNotaInfoItemImpostoICMSPartilhadosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSPartilhado> fetchAllNFNotaInfoItemImpostoICMSPartilhados(
			NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSPartilhado> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSPartilhado>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSPartilhadoBAR#
	 * fetchNFNotaInfoItemImpostoICMSPartilhadosByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSPartilhado> fetchNFNotaInfoItemImpostoICMSPartilhadosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSPartilhado> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSPartilhado>();
		fetchNFNotaInfoItemImpostoICMSPartilhadosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSPARTILHADO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMSPartilhadosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMSPartilhadosByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSST
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSTBAR#insertNFNotaInfoItemImpostoICMSST(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMSST)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMSST(NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicmsst.setProcessId(nfnotainfoitemimpostoicmsst.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSST, nfnotainfoitemimpostoicmsst,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSST, AcaoEnum.INSERT,
				nfnotainfoitemimpostoicmsst.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmsst.getId(), nfnotainfoitemimpostoicmsst.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSTBAR#
	 * updateNFNotaInfoItemImpostoICMSST(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSST)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMSST(NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmsst.setProcessId(nfnotainfoitemimpostoicmsst.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSST, nfnotainfoitemimpostoicmsst,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSST, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoicmsst.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmsst.getId(), nfnotainfoitemimpostoicmsst.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSTBAR#
	 * deleteNFNotaInfoItemImpostoICMSST(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSST)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSTById(
			NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmsst.setProcessId(nfnotainfoitemimpostoicmsst.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSST, nfnotainfoitemimpostoicmsst,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSST, AcaoEnum.DELETE,
				nfnotainfoitemimpostoicmsst.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmsst.getId(), nfnotainfoitemimpostoicmsst.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSTBAR#
	 * deleteAllNFNotaInfoItemImpostoICMSSTs()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSTs() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSST_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSTBAR#
	 * fetchNFNotaInfoItemImpostoICMSSTById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMSST fetchNFNotaInfoItemImpostoICMSSTById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMSST) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSST, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSTBAR#
	 * fetchAllNFNotaInfoItemImpostoICMSSTsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSST> fetchAllNFNotaInfoItemImpostoICMSSTs(
			NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSST> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSST>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSST_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSTBAR#
	 * fetchNFNotaInfoItemImpostoICMSSTsByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSST> fetchNFNotaInfoItemImpostoICMSSTsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSST> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSST>();
		fetchNFNotaInfoItemImpostoICMSSTsByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSST_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSST_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMSSTsByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMSSTsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN101
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN101BAR#insertNFNotaInfoItemImpostoICMSSN101(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMSSN101)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMSSN101(
			NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicmssn101.setProcessId(nfnotainfoitemimpostoicmssn101.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN101,
				nfnotainfoitemimpostoicmssn101, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN101,
				AcaoEnum.INSERT, nfnotainfoitemimpostoicmssn101.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn101.getId(), nfnotainfoitemimpostoicmssn101.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN101BAR#
	 * updateNFNotaInfoItemImpostoICMSSN101(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSSN101)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMSSN101(
			NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmssn101.setProcessId(nfnotainfoitemimpostoicmssn101.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN101,
				nfnotainfoitemimpostoicmssn101, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN101,
				AcaoEnum.UPDATE, nfnotainfoitemimpostoicmssn101.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn101.getId(), nfnotainfoitemimpostoicmssn101.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN101BAR#
	 * deleteNFNotaInfoItemImpostoICMSSN101(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSSN101)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSN101ById(
			NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmssn101.setProcessId(nfnotainfoitemimpostoicmssn101.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN101,
				nfnotainfoitemimpostoicmssn101, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN101,
				AcaoEnum.DELETE, nfnotainfoitemimpostoicmssn101.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn101.getId(), nfnotainfoitemimpostoicmssn101.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN101BAR#
	 * deleteAllNFNotaInfoItemImpostoICMSSN101s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSN101s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN101_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSN101BAR#
	 * fetchNFNotaInfoItemImpostoICMSSN101ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMSSN101 fetchNFNotaInfoItemImpostoICMSSN101ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMSSN101) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN101, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN101BAR#
	 * fetchAllNFNotaInfoItemImpostoICMSSN101sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN101> fetchAllNFNotaInfoItemImpostoICMSSN101s(
			NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN101> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSSN101>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN101_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSN101BAR#
	 * fetchNFNotaInfoItemImpostoICMSSN101sByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN101> fetchNFNotaInfoItemImpostoICMSSN101sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN101> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSSN101>();
		fetchNFNotaInfoItemImpostoICMSSN101sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN101_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN101_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMSSN101sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMSSN101sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN102
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN102BAR#insertNFNotaInfoItemImpostoICMSSN102(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMSSN102)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMSSN102(
			NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicmssn102.setProcessId(nfnotainfoitemimpostoicmssn102.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN102,
				nfnotainfoitemimpostoicmssn102, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN102,
				AcaoEnum.INSERT, nfnotainfoitemimpostoicmssn102.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn102.getId(), nfnotainfoitemimpostoicmssn102.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN102BAR#
	 * updateNFNotaInfoItemImpostoICMSSN102(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSSN102)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMSSN102(
			NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmssn102.setProcessId(nfnotainfoitemimpostoicmssn102.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN102,
				nfnotainfoitemimpostoicmssn102, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN102,
				AcaoEnum.UPDATE, nfnotainfoitemimpostoicmssn102.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn102.getId(), nfnotainfoitemimpostoicmssn102.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN102BAR#
	 * deleteNFNotaInfoItemImpostoICMSSN102(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSSN102)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSN102ById(
			NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmssn102.setProcessId(nfnotainfoitemimpostoicmssn102.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN102,
				nfnotainfoitemimpostoicmssn102, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN102,
				AcaoEnum.DELETE, nfnotainfoitemimpostoicmssn102.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn102.getId(), nfnotainfoitemimpostoicmssn102.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN102BAR#
	 * deleteAllNFNotaInfoItemImpostoICMSSN102s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSN102s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN102_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSN102BAR#
	 * fetchNFNotaInfoItemImpostoICMSSN102ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMSSN102 fetchNFNotaInfoItemImpostoICMSSN102ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMSSN102) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN102, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN102BAR#
	 * fetchAllNFNotaInfoItemImpostoICMSSN102sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN102> fetchAllNFNotaInfoItemImpostoICMSSN102s(
			NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN102> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSSN102>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN102_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSN102BAR#
	 * fetchNFNotaInfoItemImpostoICMSSN102sByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN102> fetchNFNotaInfoItemImpostoICMSSN102sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN102> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSSN102>();
		fetchNFNotaInfoItemImpostoICMSSN102sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN102_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN102_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMSSN102sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMSSN102sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN201
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN201BAR#insertNFNotaInfoItemImpostoICMSSN201(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMSSN201)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMSSN201(
			NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicmssn201.setProcessId(nfnotainfoitemimpostoicmssn201.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN201,
				nfnotainfoitemimpostoicmssn201, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN201,
				AcaoEnum.INSERT, nfnotainfoitemimpostoicmssn201.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn201.getId(), nfnotainfoitemimpostoicmssn201.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN201BAR#
	 * updateNFNotaInfoItemImpostoICMSSN201(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSSN201)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMSSN201(
			NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmssn201.setProcessId(nfnotainfoitemimpostoicmssn201.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN201,
				nfnotainfoitemimpostoicmssn201, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN201,
				AcaoEnum.UPDATE, nfnotainfoitemimpostoicmssn201.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn201.getId(), nfnotainfoitemimpostoicmssn201.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN201BAR#
	 * deleteNFNotaInfoItemImpostoICMSSN201(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSSN201)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSN201ById(
			NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmssn201.setProcessId(nfnotainfoitemimpostoicmssn201.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN201,
				nfnotainfoitemimpostoicmssn201, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN201,
				AcaoEnum.DELETE, nfnotainfoitemimpostoicmssn201.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn201.getId(), nfnotainfoitemimpostoicmssn201.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN201BAR#
	 * deleteAllNFNotaInfoItemImpostoICMSSN201s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSN201s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN201_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSN201BAR#
	 * fetchNFNotaInfoItemImpostoICMSSN201ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMSSN201 fetchNFNotaInfoItemImpostoICMSSN201ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMSSN201) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN201, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN201BAR#
	 * fetchAllNFNotaInfoItemImpostoICMSSN201sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN201> fetchAllNFNotaInfoItemImpostoICMSSN201s(
			NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN201> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSSN201>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN201_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSN201BAR#
	 * fetchNFNotaInfoItemImpostoICMSSN201sByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN201> fetchNFNotaInfoItemImpostoICMSSN201sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN201> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSSN201>();
		fetchNFNotaInfoItemImpostoICMSSN201sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN201_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN201_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMSSN201sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMSSN201sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN202
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN202BAR#insertNFNotaInfoItemImpostoICMSSN202(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMSSN202)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMSSN202(
			NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicmssn202.setProcessId(nfnotainfoitemimpostoicmssn202.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN202,
				nfnotainfoitemimpostoicmssn202, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN202,
				AcaoEnum.INSERT, nfnotainfoitemimpostoicmssn202.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn202.getId(), nfnotainfoitemimpostoicmssn202.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN202BAR#
	 * updateNFNotaInfoItemImpostoICMSSN202(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSSN202)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMSSN202(
			NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmssn202.setProcessId(nfnotainfoitemimpostoicmssn202.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN202,
				nfnotainfoitemimpostoicmssn202, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN202,
				AcaoEnum.UPDATE, nfnotainfoitemimpostoicmssn202.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn202.getId(), nfnotainfoitemimpostoicmssn202.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN202BAR#
	 * deleteNFNotaInfoItemImpostoICMSSN202(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSSN202)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSN202ById(
			NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmssn202.setProcessId(nfnotainfoitemimpostoicmssn202.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN202,
				nfnotainfoitemimpostoicmssn202, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN202,
				AcaoEnum.DELETE, nfnotainfoitemimpostoicmssn202.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn202.getId(), nfnotainfoitemimpostoicmssn202.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN202BAR#
	 * deleteAllNFNotaInfoItemImpostoICMSSN202s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSN202s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN202_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSN202BAR#
	 * fetchNFNotaInfoItemImpostoICMSSN202ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMSSN202 fetchNFNotaInfoItemImpostoICMSSN202ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMSSN202) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN202, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN202BAR#
	 * fetchAllNFNotaInfoItemImpostoICMSSN202sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN202> fetchAllNFNotaInfoItemImpostoICMSSN202s(
			NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN202> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSSN202>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN202_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSN202BAR#
	 * fetchNFNotaInfoItemImpostoICMSSN202sByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN202> fetchNFNotaInfoItemImpostoICMSSN202sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN202> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSSN202>();
		fetchNFNotaInfoItemImpostoICMSSN202sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN202_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN202_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMSSN202sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMSSN202sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN500
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN500BAR#insertNFNotaInfoItemImpostoICMSSN500(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMSSN500)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMSSN500(
			NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicmssn500.setProcessId(nfnotainfoitemimpostoicmssn500.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN500,
				nfnotainfoitemimpostoicmssn500, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN500,
				AcaoEnum.INSERT, nfnotainfoitemimpostoicmssn500.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn500.getId(), nfnotainfoitemimpostoicmssn500.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN500BAR#
	 * updateNFNotaInfoItemImpostoICMSSN500(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSSN500)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMSSN500(
			NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmssn500.setProcessId(nfnotainfoitemimpostoicmssn500.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN500,
				nfnotainfoitemimpostoicmssn500, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN500,
				AcaoEnum.UPDATE, nfnotainfoitemimpostoicmssn500.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn500.getId(), nfnotainfoitemimpostoicmssn500.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN500BAR#
	 * deleteNFNotaInfoItemImpostoICMSSN500(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSSN500)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSN500ById(
			NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmssn500.setProcessId(nfnotainfoitemimpostoicmssn500.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN500,
				nfnotainfoitemimpostoicmssn500, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN500,
				AcaoEnum.DELETE, nfnotainfoitemimpostoicmssn500.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn500.getId(), nfnotainfoitemimpostoicmssn500.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN500BAR#
	 * deleteAllNFNotaInfoItemImpostoICMSSN500s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSN500s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN500_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSN500BAR#
	 * fetchNFNotaInfoItemImpostoICMSSN500ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMSSN500 fetchNFNotaInfoItemImpostoICMSSN500ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMSSN500) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN500, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN500BAR#
	 * fetchAllNFNotaInfoItemImpostoICMSSN500sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN500> fetchAllNFNotaInfoItemImpostoICMSSN500s(
			NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN500> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSSN500>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN500_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSN500BAR#
	 * fetchNFNotaInfoItemImpostoICMSSN500sByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN500> fetchNFNotaInfoItemImpostoICMSSN500sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN500> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSSN500>();
		fetchNFNotaInfoItemImpostoICMSSN500sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN500_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN500_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMSSN500sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMSSN500sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN900
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN900BAR#insertNFNotaInfoItemImpostoICMSSN900(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMSSN900)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMSSN900(
			NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicmssn900.setProcessId(nfnotainfoitemimpostoicmssn900.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSSN900,
				nfnotainfoitemimpostoicmssn900, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN900,
				AcaoEnum.INSERT, nfnotainfoitemimpostoicmssn900.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn900.getId(), nfnotainfoitemimpostoicmssn900.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN900BAR#
	 * updateNFNotaInfoItemImpostoICMSSN900(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSSN900)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMSSN900(
			NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmssn900.setProcessId(nfnotainfoitemimpostoicmssn900.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSSN900,
				nfnotainfoitemimpostoicmssn900, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN900,
				AcaoEnum.UPDATE, nfnotainfoitemimpostoicmssn900.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn900.getId(), nfnotainfoitemimpostoicmssn900.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN900BAR#
	 * deleteNFNotaInfoItemImpostoICMSSN900(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoICMSSN900)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSN900ById(
			NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmssn900.setProcessId(nfnotainfoitemimpostoicmssn900.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN900,
				nfnotainfoitemimpostoicmssn900, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN900,
				AcaoEnum.DELETE, nfnotainfoitemimpostoicmssn900.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmssn900.getId(), nfnotainfoitemimpostoicmssn900.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN900BAR#
	 * deleteAllNFNotaInfoItemImpostoICMSSN900s()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSN900s() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSSN900_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSN900BAR#
	 * fetchNFNotaInfoItemImpostoICMSSN900ById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMSSN900 fetchNFNotaInfoItemImpostoICMSSN900ById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMSSN900) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN900, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSSN900BAR#
	 * fetchAllNFNotaInfoItemImpostoICMSSN900sCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN900> fetchAllNFNotaInfoItemImpostoICMSSN900s(
			NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN900> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSSN900>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN900_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSSN900BAR#
	 * fetchNFNotaInfoItemImpostoICMSSN900sByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN900> fetchNFNotaInfoItemImpostoICMSSN900sByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN900> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSSN900>();
		fetchNFNotaInfoItemImpostoICMSSN900sByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN900_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSSN900_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMSSN900sByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMSSN900sByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOIPI
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPIBAR#insertNFNotaInfoItemImpostoIPI(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoIPI)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoIPI(NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoipi.setProcessId(nfnotainfoitemimpostoipi.getTransactionId());

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoipi.getTributado())) {
			count += NFNotaInfoItemImpostoIPITributadoBARD.maintainNFNotaInfoItemImpostoIPITributadoAssociations(
					nfnotainfoitemimpostoipi.getTributado(), response, nfnotainfoitemimpostoipi.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoipi.getId(), nfnotainfoitemimpostoipi.getCreateUser(),
					nfnotainfoitemimpostoipi.getTransactionId(), nfnotainfoitemimpostoipi.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoipi.getNaoTributado())) {
			count += NFNotaInfoItemImpostoIPINaoTributadoBARD.maintainNFNotaInfoItemImpostoIPINaoTributadoAssociations(
					nfnotainfoitemimpostoipi.getNaoTributado(), response, nfnotainfoitemimpostoipi.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoipi.getId(), nfnotainfoitemimpostoipi.getCreateUser(),
					nfnotainfoitemimpostoipi.getTransactionId(), nfnotainfoitemimpostoipi.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOIPI, nfnotainfoitemimpostoipi,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOIPI, AcaoEnum.INSERT,
				nfnotainfoitemimpostoipi.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoipi.getId(), nfnotainfoitemimpostoipi.getUserId());



		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPIBAR#
	 * updateNFNotaInfoItemImpostoIPI(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoIPI)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoIPI(NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoipi.setProcessId(nfnotainfoitemimpostoipi.getTransactionId());
		Integer a = 0;
		if (!ValidationUtil.isNull(nfnotainfoitemimpostoipi.getTributado())) {
			nfnotainfoitemimpostoipi.getTributado().setModelAction(PersistenceActionEnum.DELETE);
			a += NFNotaInfoItemImpostoIPITributadoBARD.maintainNFNotaInfoItemImpostoIPITributadoAssociations(
					nfnotainfoitemimpostoipi.getTributado(), response, nfnotainfoitemimpostoipi.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoipi.getId(), nfnotainfoitemimpostoipi.getCreateUser(),
					nfnotainfoitemimpostoipi.getTransactionId(), nfnotainfoitemimpostoipi.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoipi.getNaoTributado())) {
			nfnotainfoitemimpostoipi.getNaoTributado().setModelAction(PersistenceActionEnum.DELETE);
			a += NFNotaInfoItemImpostoIPINaoTributadoBARD.maintainNFNotaInfoItemImpostoIPINaoTributadoAssociations(
					nfnotainfoitemimpostoipi.getNaoTributado(), response, nfnotainfoitemimpostoipi.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoipi.getId(), nfnotainfoitemimpostoipi.getCreateUser(),
					nfnotainfoitemimpostoipi.getTransactionId(), nfnotainfoitemimpostoipi.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOIPI, nfnotainfoitemimpostoipi,
				response);

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoipi.getTributado())) {
			nfnotainfoitemimpostoipi.getTributado().setModelAction(PersistenceActionEnum.INSERT);
			a += NFNotaInfoItemImpostoIPITributadoBARD.maintainNFNotaInfoItemImpostoIPITributadoAssociations(
					nfnotainfoitemimpostoipi.getTributado(), response, nfnotainfoitemimpostoipi.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoipi.getId(), nfnotainfoitemimpostoipi.getCreateUser(),
					nfnotainfoitemimpostoipi.getTransactionId(), nfnotainfoitemimpostoipi.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoipi.getNaoTributado())) {
			nfnotainfoitemimpostoipi.getTributado().setModelAction(PersistenceActionEnum.INSERT);
			a += NFNotaInfoItemImpostoIPINaoTributadoBARD.maintainNFNotaInfoItemImpostoIPINaoTributadoAssociations(
					nfnotainfoitemimpostoipi.getNaoTributado(), response, nfnotainfoitemimpostoipi.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoipi.getId(), nfnotainfoitemimpostoipi.getCreateUser(),
					nfnotainfoitemimpostoipi.getTransactionId(), nfnotainfoitemimpostoipi.getTransactionId());
		}

		a += InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOIPI, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoipi.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoipi.getId(), nfnotainfoitemimpostoipi.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPIBAR#
	 * deleteNFNotaInfoItemImpostoIPI(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoIPI)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoIPIById(NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoipi.setProcessId(nfnotainfoitemimpostoipi.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPI, nfnotainfoitemimpostoipi,
				response);
		Integer a = 0;
		if (!ValidationUtil.isNull(nfnotainfoitemimpostoipi.getTributado())) {
			a += NFNotaInfoItemImpostoIPITributadoBARD.maintainNFNotaInfoItemImpostoIPITributadoAssociations(
					nfnotainfoitemimpostoipi.getTributado(), response, nfnotainfoitemimpostoipi.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoipi.getId(), nfnotainfoitemimpostoipi.getCreateUser(),
					nfnotainfoitemimpostoipi.getTransactionId(), nfnotainfoitemimpostoipi.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostoipi.getNaoTributado())) {
			a += NFNotaInfoItemImpostoIPINaoTributadoBARD.maintainNFNotaInfoItemImpostoIPINaoTributadoAssociations(
					nfnotainfoitemimpostoipi.getNaoTributado(), response, nfnotainfoitemimpostoipi.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostoipi.getId(), nfnotainfoitemimpostoipi.getCreateUser(),
					nfnotainfoitemimpostoipi.getTransactionId(), nfnotainfoitemimpostoipi.getTransactionId());
		}

		a += InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOIPI, AcaoEnum.DELETE,
				nfnotainfoitemimpostoipi.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoipi.getId(), nfnotainfoitemimpostoipi.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPIBAR#
	 * deleteAllNFNotaInfoItemImpostoIPIs()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoIPIs() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPI_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoIPIBAR#
	 * fetchNFNotaInfoItemImpostoIPIById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoIPI fetchNFNotaInfoItemImpostoIPIById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoIPI) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPI, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPIBAR#
	 * fetchAllNFNotaInfoItemImpostoIPIsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoIPI> fetchAllNFNotaInfoItemImpostoIPIs(
			NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi) {
		InternalResultsResponse<NFNotaInfoItemImpostoIPI> response = new InternalResultsResponse<NFNotaInfoItemImpostoIPI>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPI_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoIPIBAR#
	 * fetchNFNotaInfoItemImpostoIPIsByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoIPI> fetchNFNotaInfoItemImpostoIPIsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoIPI> response = new InternalResultsResponse<NFNotaInfoItemImpostoIPI>();
		fetchNFNotaInfoItemImpostoIPIsByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPI_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPI_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoIPIsByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoIPIsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOIPITRIBUTADO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPITributadoBAR#insertNFNotaInfoItemImpostoIPITributado(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoIPITributado)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoIPITributado(
			NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoipitributado.setProcessId(nfnotainfoitemimpostoipitributado.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO,
				nfnotainfoitemimpostoipitributado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOIPITRIBUTADO,
				AcaoEnum.INSERT, nfnotainfoitemimpostoipitributado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoipitributado.getId(), nfnotainfoitemimpostoipitributado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPITributadoBAR#
	 * updateNFNotaInfoItemImpostoIPITributado(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoIPITributado)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoIPITributado(
			NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoipitributado.setProcessId(nfnotainfoitemimpostoipitributado.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO,
				nfnotainfoitemimpostoipitributado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOIPITRIBUTADO,
				AcaoEnum.UPDATE, nfnotainfoitemimpostoipitributado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoipitributado.getId(), nfnotainfoitemimpostoipitributado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPITributadoBAR#
	 * deleteNFNotaInfoItemImpostoIPITributado(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoIPITributado)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoIPITributadoById(
			NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoipitributado.setProcessId(nfnotainfoitemimpostoipitributado.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO,
				nfnotainfoitemimpostoipitributado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOIPITRIBUTADO,
				AcaoEnum.DELETE, nfnotainfoitemimpostoipitributado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoipitributado.getId(), nfnotainfoitemimpostoipitributado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPITributadoBAR#
	 * deleteAllNFNotaInfoItemImpostoIPITributados()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoIPITributados() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoIPITributadoBAR#
	 * fetchNFNotaInfoItemImpostoIPITributadoById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoIPITributado fetchNFNotaInfoItemImpostoIPITributadoById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoIPITributado) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPITributadoBAR#
	 * fetchAllNFNotaInfoItemImpostoIPITributadosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoIPITributado> fetchAllNFNotaInfoItemImpostoIPITributados(
			NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado) {
		InternalResultsResponse<NFNotaInfoItemImpostoIPITributado> response = new InternalResultsResponse<NFNotaInfoItemImpostoIPITributado>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoIPITributadoBAR#
	 * fetchNFNotaInfoItemImpostoIPITributadosByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoIPITributado> fetchNFNotaInfoItemImpostoIPITributadosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoIPITributado> response = new InternalResultsResponse<NFNotaInfoItemImpostoIPITributado>();
		fetchNFNotaInfoItemImpostoIPITributadosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPITRIBUTADO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoIPITributadosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoIPITributadosByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPINaoTributadoBAR#insertNFNotaInfoItemImpostoIPINaoTributado(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoIPINaoTributado)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoIPINaoTributado(
			NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoipinaotributado.setProcessId(nfnotainfoitemimpostoipinaotributado.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO,
				nfnotainfoitemimpostoipinaotributado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO,
				AcaoEnum.INSERT, nfnotainfoitemimpostoipinaotributado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoipinaotributado.getId(), nfnotainfoitemimpostoipinaotributado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPINaoTributadoBAR
	 * #updateNFNotaInfoItemImpostoIPINaoTributado(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoIPINaoTributado)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoIPINaoTributado(
			NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoipinaotributado.setProcessId(nfnotainfoitemimpostoipinaotributado.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO,
				nfnotainfoitemimpostoipinaotributado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO,
				AcaoEnum.UPDATE, nfnotainfoitemimpostoipinaotributado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoipinaotributado.getId(), nfnotainfoitemimpostoipinaotributado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPINaoTributadoBAR
	 * #deleteNFNotaInfoItemImpostoIPINaoTributado(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoIPINaoTributado)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoIPINaoTributadoById(
			NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoipinaotributado.setProcessId(nfnotainfoitemimpostoipinaotributado.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO,
				nfnotainfoitemimpostoipinaotributado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO,
				AcaoEnum.DELETE, nfnotainfoitemimpostoipinaotributado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoipinaotributado.getId(), nfnotainfoitemimpostoipinaotributado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPINaoTributadoBAR
	 * #deleteAllNFNotaInfoItemImpostoIPINaoTributados()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoIPINaoTributados() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoIPINaoTributadoBAR#
	 * fetchNFNotaInfoItemImpostoIPINaoTributadoById(com.qat.samples.sysmgmt.
	 * model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoIPINaoTributado fetchNFNotaInfoItemImpostoIPINaoTributadoById(
			FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoIPINaoTributado) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoIPINaoTributadoBAR
	 * #fetchAllNFNotaInfoItemImpostoIPINaoTributadosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoIPINaoTributado> fetchAllNFNotaInfoItemImpostoIPINaoTributados(
			NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado) {
		InternalResultsResponse<NFNotaInfoItemImpostoIPINaoTributado> response = new InternalResultsResponse<NFNotaInfoItemImpostoIPINaoTributado>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoIPINaoTributadoBAR#
	 * fetchNFNotaInfoItemImpostoIPINaoTributadosByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoIPINaoTributado> fetchNFNotaInfoItemImpostoIPINaoTributadosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoIPINaoTributado> response = new InternalResultsResponse<NFNotaInfoItemImpostoIPINaoTributado>();
		fetchNFNotaInfoItemImpostoIPINaoTributadosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoIPINaoTributadosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoIPINaoTributadosByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================### NFNOTAINFOITEMIMPOSTOIMPORTACAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoImportacaoBAR#insertNFNotaInfoItemImpostoImportacao(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoImportacao)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoImportacao(
			NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoimportacao.setProcessId(nfnotainfoitemimpostoimportacao.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOIMPORTACAO,
				nfnotainfoitemimpostoimportacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOIMPORTACAO,
				AcaoEnum.INSERT, nfnotainfoitemimpostoimportacao.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoimportacao.getId(), nfnotainfoitemimpostoimportacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoImportacaoBAR#
	 * updateNFNotaInfoItemImpostoImportacao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoImportacao)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoImportacao(
			NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoimportacao.setProcessId(nfnotainfoitemimpostoimportacao.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOIMPORTACAO,
				nfnotainfoitemimpostoimportacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOIMPORTACAO,
				AcaoEnum.UPDATE, nfnotainfoitemimpostoimportacao.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoimportacao.getId(), nfnotainfoitemimpostoimportacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoImportacaoBAR#
	 * deleteNFNotaInfoItemImpostoImportacao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoImportacao)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoImportacaoById(
			NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoimportacao.setProcessId(nfnotainfoitemimpostoimportacao.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOIMPORTACAO,
				nfnotainfoitemimpostoimportacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOIMPORTACAO,
				AcaoEnum.DELETE, nfnotainfoitemimpostoimportacao.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoimportacao.getId(), nfnotainfoitemimpostoimportacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoImportacaoBAR#
	 * deleteAllNFNotaInfoItemImpostoImportacaos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoImportacaos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOIMPORTACAO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoImportacaoBAR#
	 * fetchNFNotaInfoItemImpostoImportacaoById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoImportacao fetchNFNotaInfoItemImpostoImportacaoById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoImportacao) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOIMPORTACAO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoImportacaoBAR#
	 * fetchAllNFNotaInfoItemImpostoImportacaosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoImportacao> fetchAllNFNotaInfoItemImpostoImportacaos(
			NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao) {
		InternalResultsResponse<NFNotaInfoItemImpostoImportacao> response = new InternalResultsResponse<NFNotaInfoItemImpostoImportacao>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOIMPORTACAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoImportacaoBAR#
	 * fetchNFNotaInfoItemImpostoImportacaosByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoImportacao> fetchNFNotaInfoItemImpostoImportacaosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoImportacao> response = new InternalResultsResponse<NFNotaInfoItemImpostoImportacao>();
		fetchNFNotaInfoItemImpostoImportacaosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOIMPORTACAO_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOIMPORTACAO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoImportacaosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoImportacaosByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================### NFNOTAINFOITEMIMPOSTOISSQN
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoISSQNBAR#insertNFNotaInfoItemImpostoISSQN(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoISSQN)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoISSQN(NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoissqn.setProcessId(nfnotainfoitemimpostoissqn.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOISSQN, nfnotainfoitemimpostoissqn,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOISSQN, AcaoEnum.INSERT,
				nfnotainfoitemimpostoissqn.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoissqn.getId(), nfnotainfoitemimpostoissqn.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoISSQNBAR#
	 * updateNFNotaInfoItemImpostoISSQN(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoISSQN)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoISSQN(NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoissqn.setProcessId(nfnotainfoitemimpostoissqn.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOISSQN, nfnotainfoitemimpostoissqn,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOISSQN, AcaoEnum.UPDATE,
				nfnotainfoitemimpostoissqn.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoissqn.getId(), nfnotainfoitemimpostoissqn.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoISSQNBAR#
	 * deleteNFNotaInfoItemImpostoISSQN(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoISSQN)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoISSQNById(
			NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoissqn.setProcessId(nfnotainfoitemimpostoissqn.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOISSQN, nfnotainfoitemimpostoissqn,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOISSQN, AcaoEnum.DELETE,
				nfnotainfoitemimpostoissqn.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoissqn.getId(), nfnotainfoitemimpostoissqn.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoISSQNBAR#
	 * deleteAllNFNotaInfoItemImpostoISSQNs()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoISSQNs() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOISSQN_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoISSQNBAR#
	 * fetchNFNotaInfoItemImpostoISSQNById(com.qat.samples.sysmgmt.model.request
	 * .FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoISSQN fetchNFNotaInfoItemImpostoISSQNById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoISSQN) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOISSQN, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoISSQNBAR#
	 * fetchAllNFNotaInfoItemImpostoISSQNsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoISSQN> fetchAllNFNotaInfoItemImpostoISSQNs(
			NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn) {
		InternalResultsResponse<NFNotaInfoItemImpostoISSQN> response = new InternalResultsResponse<NFNotaInfoItemImpostoISSQN>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOISSQN_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoISSQNBAR#
	 * fetchNFNotaInfoItemImpostoISSQNsByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoISSQN> fetchNFNotaInfoItemImpostoISSQNsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoISSQN> response = new InternalResultsResponse<NFNotaInfoItemImpostoISSQN>();
		fetchNFNotaInfoItemImpostoISSQNsByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOITEMIMPOSTOISSQN_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOISSQN_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoISSQNsByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoISSQNsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOPIS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISBAR#insertNFNotaInfoItemImpostoPIS(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoPIS)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoPIS(NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostopis.setProcessId(nfnotainfoitemimpostopis.getTransactionId());

		if (!ValidationUtil.isNull(nfnotainfoitemimpostopis.getAliquota())) {
			count += NFNotaInfoItemImpostoPISAliquotaBARD.maintainNFNotaInfoItemImpostoPISAliquotaAssociations(
					nfnotainfoitemimpostopis.getAliquota(), response, nfnotainfoitemimpostopis.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostopis.getId(), nfnotainfoitemimpostopis.getCreateUser(),
					nfnotainfoitemimpostopis.getTransactionId(), nfnotainfoitemimpostopis.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostopis.getQuantidade())) {
			count += NFNotaInfoItemImpostoPISQuantidadeBARD.maintainNFNotaInfoItemImpostoPISQuantidadeAssociations(
					nfnotainfoitemimpostopis.getQuantidade(), response, nfnotainfoitemimpostopis.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostopis.getId(), nfnotainfoitemimpostopis.getCreateUser(),
					nfnotainfoitemimpostopis.getTransactionId(), nfnotainfoitemimpostopis.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostopis.getNaoTributado())) {
			count += NFNotaInfoItemImpostoPISNaoTributadoBARD.maintainNFNotaInfoItemImpostoPISNaoTributadoAssociations(
					nfnotainfoitemimpostopis.getNaoTributado(), response, nfnotainfoitemimpostopis.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostopis.getId(), nfnotainfoitemimpostopis.getCreateUser(),
					nfnotainfoitemimpostopis.getTransactionId(), nfnotainfoitemimpostopis.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostopis.getOutrasOperacoes())) {
			count += NFNotaInfoItemImpostoPISOutrasOperacoesBARD
					.maintainNFNotaInfoItemImpostoPISOutrasOperacoesAssociations(
							nfnotainfoitemimpostopis.getOutrasOperacoes(), response, nfnotainfoitemimpostopis.getId(),
							null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemimpostopis.getId(), nfnotainfoitemimpostopis.getCreateUser(),
							nfnotainfoitemimpostopis.getTransactionId(), nfnotainfoitemimpostopis.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOPIS, nfnotainfoitemimpostopis,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPIS, AcaoEnum.INSERT,
				nfnotainfoitemimpostopis.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopis.getId(), nfnotainfoitemimpostopis.getUserId());


		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISBAR#
	 * updateNFNotaInfoItemImpostoPIS(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoPIS)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoPIS(NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostopis.setProcessId(nfnotainfoitemimpostopis.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPIS, nfnotainfoitemimpostopis,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPIS, AcaoEnum.UPDATE,
				nfnotainfoitemimpostopis.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopis.getId(), nfnotainfoitemimpostopis.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISBAR#
	 * deleteNFNotaInfoItemImpostoPIS(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoPIS)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoPISById(NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostopis.setProcessId(nfnotainfoitemimpostopis.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOPIS, nfnotainfoitemimpostopis,
				response);
		Integer count = 0;
		if (!ValidationUtil.isNull(nfnotainfoitemimpostopis.getAliquota())) {
			count += NFNotaInfoItemImpostoPISAliquotaBARD.maintainNFNotaInfoItemImpostoPISAliquotaAssociations(
					nfnotainfoitemimpostopis.getAliquota(), response, nfnotainfoitemimpostopis.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostopis.getId(), nfnotainfoitemimpostopis.getCreateUser(),
					nfnotainfoitemimpostopis.getTransactionId(), nfnotainfoitemimpostopis.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostopis.getQuantidade())) {
			count += NFNotaInfoItemImpostoPISQuantidadeBARD.maintainNFNotaInfoItemImpostoPISQuantidadeAssociations(
					nfnotainfoitemimpostopis.getQuantidade(), response, nfnotainfoitemimpostopis.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostopis.getId(), nfnotainfoitemimpostopis.getCreateUser(),
					nfnotainfoitemimpostopis.getTransactionId(), nfnotainfoitemimpostopis.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostopis.getNaoTributado())) {
			count += NFNotaInfoItemImpostoPISNaoTributadoBARD.maintainNFNotaInfoItemImpostoPISNaoTributadoAssociations(
					nfnotainfoitemimpostopis.getNaoTributado(), response, nfnotainfoitemimpostopis.getId(), null, null,
					TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostopis.getId(), nfnotainfoitemimpostopis.getCreateUser(),
					nfnotainfoitemimpostopis.getTransactionId(), nfnotainfoitemimpostopis.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostopis.getOutrasOperacoes())) {
			count += NFNotaInfoItemImpostoPISOutrasOperacoesBARD
					.maintainNFNotaInfoItemImpostoPISOutrasOperacoesAssociations(
							nfnotainfoitemimpostopis.getOutrasOperacoes(), response, nfnotainfoitemimpostopis.getId(),
							null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemimpostopis.getId(), nfnotainfoitemimpostopis.getCreateUser(),
							nfnotainfoitemimpostopis.getTransactionId(), nfnotainfoitemimpostopis.getTransactionId());
		}

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPIS, AcaoEnum.DELETE,
				nfnotainfoitemimpostopis.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopis.getId(), nfnotainfoitemimpostopis.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISBAR#
	 * deleteAllNFNotaInfoItemImpostoPISs()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoPISs() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOPIS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoPISBAR#
	 * fetchNFNotaInfoItemImpostoPISById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoPIS fetchNFNotaInfoItemImpostoPISById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoPIS) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPIS, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISBAR#
	 * fetchAllNFNotaInfoItemImpostoPISsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoPIS> fetchAllNFNotaInfoItemImpostoPISs(
			NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis) {
		InternalResultsResponse<NFNotaInfoItemImpostoPIS> response = new InternalResultsResponse<NFNotaInfoItemImpostoPIS>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOPIS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoPISBAR#
	 * fetchNFNotaInfoItemImpostoPISsByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoPIS> fetchNFNotaInfoItemImpostoPISsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoPIS> response = new InternalResultsResponse<NFNotaInfoItemImpostoPIS>();
		fetchNFNotaInfoItemImpostoPISsByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOITEMIMPOSTOPIS_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPIS_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoPISsByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoPISsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOPISALIQUOTA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISAliquotaBAR#insertNFNotaInfoItemImpostoPISAliquota(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoPISAliquota)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoPISAliquota(
			NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostopisaliquota.setProcessId(nfnotainfoitemimpostopisaliquota.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISALIQUOTA,
				nfnotainfoitemimpostopisaliquota, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISALIQUOTA,
				AcaoEnum.INSERT, nfnotainfoitemimpostopisaliquota.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopisaliquota.getId(), nfnotainfoitemimpostopisaliquota.getUserId());

		if (nfnotainfoitemimpostopisaliquota.getId() != 0 && nfnotainfoitemimpostopisaliquota.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response,
					nfnotainfoitemimpostopisaliquota.getId(), null, AcaoEnum.INSERT,
					nfnotainfoitemimpostopisaliquota.getCreateUser(), nfnotainfoitemimpostopisaliquota.getId(),
					TabelaEnum.NFNOTAINFOITEMIMPOSTOPISALIQUOTA, statusBAR, historicoBAR,
					nfnotainfoitemimpostopisaliquota.getTransactionId(),
					nfnotainfoitemimpostopisaliquota.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISAliquotaBAR#
	 * updateNFNotaInfoItemImpostoPISAliquota(com.qat.samples.sysmgmt.base.model
	 * .NFNotaInfoItemImpostoPISAliquota)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoPISAliquota(
			NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostopisaliquota.setProcessId(nfnotainfoitemimpostopisaliquota.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA,
				nfnotainfoitemimpostopisaliquota, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISALIQUOTA,
				AcaoEnum.UPDATE, nfnotainfoitemimpostopisaliquota.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopisaliquota.getId(), nfnotainfoitemimpostopisaliquota.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISAliquotaBAR#
	 * deleteNFNotaInfoItemImpostoPISAliquota(com.qat.samples.sysmgmt.base.model
	 * .NFNotaInfoItemImpostoPISAliquota)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoPISAliquotaById(
			NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostopisaliquota.setProcessId(nfnotainfoitemimpostopisaliquota.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA,
				nfnotainfoitemimpostopisaliquota, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISALIQUOTA,
				AcaoEnum.DELETE, nfnotainfoitemimpostopisaliquota.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopisaliquota.getId(), nfnotainfoitemimpostopisaliquota.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISAliquotaBAR#
	 * deleteAllNFNotaInfoItemImpostoPISAliquotas()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoPISAliquotas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISALIQUOTA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoPISAliquotaBAR#
	 * fetchNFNotaInfoItemImpostoPISAliquotaById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoPISAliquota fetchNFNotaInfoItemImpostoPISAliquotaById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoPISAliquota) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISALIQUOTA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISAliquotaBAR#
	 * fetchAllNFNotaInfoItemImpostoPISAliquotasCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoPISAliquota> fetchAllNFNotaInfoItemImpostoPISAliquotas(
			NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota) {
		InternalResultsResponse<NFNotaInfoItemImpostoPISAliquota> response = new InternalResultsResponse<NFNotaInfoItemImpostoPISAliquota>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISALIQUOTA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoPISAliquotaBAR#
	 * fetchNFNotaInfoItemImpostoPISAliquotasByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoPISAliquota> fetchNFNotaInfoItemImpostoPISAliquotasByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoPISAliquota> response = new InternalResultsResponse<NFNotaInfoItemImpostoPISAliquota>();
		fetchNFNotaInfoItemImpostoPISAliquotasByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISALIQUOTA_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISALIQUOTA_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoPISAliquotasByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoPISAliquotasByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================### NFNOTAINFOITEMIMPOSTOPISQUANTIDADE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISQuantidadeBAR#insertNFNotaInfoItemImpostoPISQuantidade(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoPISQuantidade)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoPISQuantidade(
			NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostopisquantidade.setProcessId(nfnotainfoitemimpostopisquantidade.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE,
				nfnotainfoitemimpostopisquantidade, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISQUANTIDADE,
				AcaoEnum.INSERT, nfnotainfoitemimpostopisquantidade.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopisquantidade.getId(), nfnotainfoitemimpostopisquantidade.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISQuantidadeBAR#
	 * updateNFNotaInfoItemImpostoPISQuantidade(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoPISQuantidade)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoPISQuantidade(
			NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostopisquantidade.setProcessId(nfnotainfoitemimpostopisquantidade.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE,
				nfnotainfoitemimpostopisquantidade, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISQUANTIDADE,
				AcaoEnum.UPDATE, nfnotainfoitemimpostopisquantidade.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopisquantidade.getId(), nfnotainfoitemimpostopisquantidade.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISQuantidadeBAR#
	 * deleteNFNotaInfoItemImpostoPISQuantidade(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoPISQuantidade)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoPISQuantidadeById(
			NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostopisquantidade.setProcessId(nfnotainfoitemimpostopisquantidade.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE,
				nfnotainfoitemimpostopisquantidade, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISQUANTIDADE,
				AcaoEnum.DELETE, nfnotainfoitemimpostopisquantidade.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopisquantidade.getId(), nfnotainfoitemimpostopisquantidade.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISQuantidadeBAR#
	 * deleteAllNFNotaInfoItemImpostoPISQuantidades()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoPISQuantidades() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoPISQuantidadeBAR#
	 * fetchNFNotaInfoItemImpostoPISQuantidadeById(com.qat.samples.sysmgmt.model
	 * .request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoPISQuantidade fetchNFNotaInfoItemImpostoPISQuantidadeById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoPISQuantidade) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISQuantidadeBAR#
	 * fetchAllNFNotaInfoItemImpostoPISQuantidadesCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoPISQuantidade> fetchAllNFNotaInfoItemImpostoPISQuantidades(
			NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade) {
		InternalResultsResponse<NFNotaInfoItemImpostoPISQuantidade> response = new InternalResultsResponse<NFNotaInfoItemImpostoPISQuantidade>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoPISQuantidadeBAR#
	 * fetchNFNotaInfoItemImpostoPISQuantidadesByRequest(com.qat.samples.sysmgmt
	 * .model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoPISQuantidade> fetchNFNotaInfoItemImpostoPISQuantidadesByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoPISQuantidade> response = new InternalResultsResponse<NFNotaInfoItemImpostoPISQuantidade>();
		fetchNFNotaInfoItemImpostoPISQuantidadesByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISQUANTIDADE_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoPISQuantidadesByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoPISQuantidadesByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISNaoTributadoBAR#insertNFNotaInfoItemImpostoPISNaoTributado(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoPISNaoTributado)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoPISNaoTributado(
			NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostopisnaotributado.setProcessId(nfnotainfoitemimpostopisnaotributado.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO,
				nfnotainfoitemimpostopisnaotributado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO,
				AcaoEnum.INSERT, nfnotainfoitemimpostopisnaotributado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopisnaotributado.getId(), nfnotainfoitemimpostopisnaotributado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISNaoTributadoBAR
	 * #updateNFNotaInfoItemImpostoPISNaoTributado(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoPISNaoTributado)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoPISNaoTributado(
			NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostopisnaotributado.setProcessId(nfnotainfoitemimpostopisnaotributado.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO,
				nfnotainfoitemimpostopisnaotributado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO,
				AcaoEnum.UPDATE, nfnotainfoitemimpostopisnaotributado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopisnaotributado.getId(), nfnotainfoitemimpostopisnaotributado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISNaoTributadoBAR
	 * #deleteNFNotaInfoItemImpostoPISNaoTributado(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoPISNaoTributado)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoPISNaoTributadoById(
			NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostopisnaotributado.setProcessId(nfnotainfoitemimpostopisnaotributado.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO,
				nfnotainfoitemimpostopisnaotributado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO,
				AcaoEnum.DELETE, nfnotainfoitemimpostopisnaotributado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopisnaotributado.getId(), nfnotainfoitemimpostopisnaotributado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISNaoTributadoBAR
	 * #deleteAllNFNotaInfoItemImpostoPISNaoTributados()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoPISNaoTributados() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoPISNaoTributadoBAR#
	 * fetchNFNotaInfoItemImpostoPISNaoTributadoById(com.qat.samples.sysmgmt.
	 * model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoPISNaoTributado fetchNFNotaInfoItemImpostoPISNaoTributadoById(
			FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoPISNaoTributado) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISNaoTributadoBAR
	 * #fetchAllNFNotaInfoItemImpostoPISNaoTributadosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoPISNaoTributado> fetchAllNFNotaInfoItemImpostoPISNaoTributados(
			NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado) {
		InternalResultsResponse<NFNotaInfoItemImpostoPISNaoTributado> response = new InternalResultsResponse<NFNotaInfoItemImpostoPISNaoTributado>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoPISNaoTributadoBAR#
	 * fetchNFNotaInfoItemImpostoPISNaoTributadosByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoPISNaoTributado> fetchNFNotaInfoItemImpostoPISNaoTributadosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoPISNaoTributado> response = new InternalResultsResponse<NFNotaInfoItemImpostoPISNaoTributado>();
		fetchNFNotaInfoItemImpostoPISNaoTributadosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoPISNaoTributadosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoPISNaoTributadosByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISOutrasOperacoesBAR#insertNFNotaInfoItemImpostoPISOutrasOperacoes(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoPISOutrasOperacoes)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoPISOutrasOperacoes(
			NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostopisoutrasoperacoes
				.setProcessId(nfnotainfoitemimpostopisoutrasoperacoes.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES,
				nfnotainfoitemimpostopisoutrasoperacoes, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES,
				AcaoEnum.INSERT, nfnotainfoitemimpostopisoutrasoperacoes.getTransactionId(), getHistoricoBAR(),
				response, nfnotainfoitemimpostopisoutrasoperacoes.getId(),
				nfnotainfoitemimpostopisoutrasoperacoes.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoPISOutrasOperacoesBAR#
	 * updateNFNotaInfoItemImpostoPISOutrasOperacoes(com.qat.samples.sysmgmt.
	 * base.model.NFNotaInfoItemImpostoPISOutrasOperacoes)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoPISOutrasOperacoes(
			NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostopisoutrasoperacoes
				.setProcessId(nfnotainfoitemimpostopisoutrasoperacoes.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES,
				nfnotainfoitemimpostopisoutrasoperacoes, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES,
				AcaoEnum.UPDATE, nfnotainfoitemimpostopisoutrasoperacoes.getTransactionId(), getHistoricoBAR(),
				response, nfnotainfoitemimpostopisoutrasoperacoes.getId(),
				nfnotainfoitemimpostopisoutrasoperacoes.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoPISOutrasOperacoesBAR#
	 * deleteNFNotaInfoItemImpostoPISOutrasOperacoes(com.qat.samples.sysmgmt.
	 * base.model.NFNotaInfoItemImpostoPISOutrasOperacoes)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoPISOutrasOperacoesById(
			NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostopisoutrasoperacoes
				.setProcessId(nfnotainfoitemimpostopisoutrasoperacoes.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES,
				nfnotainfoitemimpostopisoutrasoperacoes, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES,
				AcaoEnum.DELETE, nfnotainfoitemimpostopisoutrasoperacoes.getTransactionId(), getHistoricoBAR(),
				response, nfnotainfoitemimpostopisoutrasoperacoes.getId(),
				nfnotainfoitemimpostopisoutrasoperacoes.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoPISOutrasOperacoesBAR#
	 * deleteAllNFNotaInfoItemImpostoPISOutrasOperacoess()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoPISOutrasOperacoess() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoPISOutrasOperacoesBAR#
	 * fetchNFNotaInfoItemImpostoPISOutrasOperacoesById(com.qat.samples.sysmgmt.
	 * model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoPISOutrasOperacoes fetchNFNotaInfoItemImpostoPISOutrasOperacoesById(
			FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoPISOutrasOperacoes) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoPISOutrasOperacoesBAR#
	 * fetchAllNFNotaInfoItemImpostoPISOutrasOperacoessCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoPISOutrasOperacoes> fetchAllNFNotaInfoItemImpostoPISOutrasOperacoess(
			NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes) {
		InternalResultsResponse<NFNotaInfoItemImpostoPISOutrasOperacoes> response = new InternalResultsResponse<NFNotaInfoItemImpostoPISOutrasOperacoes>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoPISOutrasOperacoesBAR#
	 * fetchNFNotaInfoItemImpostoPISOutrasOperacoessByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoPISOutrasOperacoes> fetchNFNotaInfoItemImpostoPISOutrasOperacoessByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoPISOutrasOperacoes> response = new InternalResultsResponse<NFNotaInfoItemImpostoPISOutrasOperacoes>();
		fetchNFNotaInfoItemImpostoPISOutrasOperacoessByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoPISOutrasOperacoessByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoPISOutrasOperacoessByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================### NFNOTAINFOITEMIMPOSTOPISST
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISSTBAR#insertNFNotaInfoItemImpostoPISST(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoPISST)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoPISST(NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostopisst.setProcessId(nfnotainfoitemimpostopisst.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOPISST, nfnotainfoitemimpostopisst,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISST, AcaoEnum.INSERT,
				nfnotainfoitemimpostopisst.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopisst.getId(), nfnotainfoitemimpostopisst.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISSTBAR#
	 * updateNFNotaInfoItemImpostoPISST(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoPISST)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoPISST(NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostopisst.setProcessId(nfnotainfoitemimpostopisst.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOPISST, nfnotainfoitemimpostopisst,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISST, AcaoEnum.UPDATE,
				nfnotainfoitemimpostopisst.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopisst.getId(), nfnotainfoitemimpostopisst.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISSTBAR#
	 * deleteNFNotaInfoItemImpostoPISST(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoPISST)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoPISSTById(
			NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostopisst.setProcessId(nfnotainfoitemimpostopisst.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISST, nfnotainfoitemimpostopisst,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOPISST, AcaoEnum.DELETE,
				nfnotainfoitemimpostopisst.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostopisst.getId(), nfnotainfoitemimpostopisst.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISSTBAR#
	 * deleteAllNFNotaInfoItemImpostoPISSTs()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoPISSTs() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOPISST_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoPISSTBAR#
	 * fetchNFNotaInfoItemImpostoPISSTById(com.qat.samples.sysmgmt.model.request
	 * .FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoPISST fetchNFNotaInfoItemImpostoPISSTById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoPISST) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISST, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoPISSTBAR#
	 * fetchAllNFNotaInfoItemImpostoPISSTsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoPISST> fetchAllNFNotaInfoItemImpostoPISSTs(
			NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst) {
		InternalResultsResponse<NFNotaInfoItemImpostoPISST> response = new InternalResultsResponse<NFNotaInfoItemImpostoPISST>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISST_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoPISSTBAR#
	 * fetchNFNotaInfoItemImpostoPISSTsByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoPISST> fetchNFNotaInfoItemImpostoPISSTsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoPISST> response = new InternalResultsResponse<NFNotaInfoItemImpostoPISST>();
		fetchNFNotaInfoItemImpostoPISSTsByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISST_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOPISST_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoPISSTsByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoPISSTsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOCOFINS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSBAR#insertNFNotaInfoItemImpostoCOFINS(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoCOFINS)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoCOFINS(NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostocofins.setProcessId(nfnotainfoitemimpostocofins.getTransactionId());

		if (!ValidationUtil.isNull(nfnotainfoitemimpostocofins.getAliquota())) {
			count += NFNotaInfoItemImpostoCOFINSAliquotaBARD.maintainNFNotaInfoItemImpostoCOFINSAliquotaAssociations(
					nfnotainfoitemimpostocofins.getAliquota(), response, nfnotainfoitemimpostocofins.getId(), null,
					null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofins.getCreateUser(),
					nfnotainfoitemimpostocofins.getTransactionId(), nfnotainfoitemimpostocofins.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostocofins.getQuantidade())) {
			count += NFNotaInfoItemImpostoCOFINSQuantidadeBARD
					.maintainNFNotaInfoItemImpostoCOFINSQuantidadeAssociations(
							nfnotainfoitemimpostocofins.getQuantidade(), response, nfnotainfoitemimpostocofins.getId(),
							null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofins.getCreateUser(),
							nfnotainfoitemimpostocofins.getTransactionId(),
							nfnotainfoitemimpostocofins.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostocofins.getNaoTributavel())) {
			count += NFNotaInfoItemImpostoCOFINSNaoTributavelBARD
					.maintainNFNotaInfoItemImpostoCOFINSNaoTributavelAssociations(
							nfnotainfoitemimpostocofins.getNaoTributavel(), response, nfnotainfoitemimpostocofins.getId(),
							null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofins.getCreateUser(),
							nfnotainfoitemimpostocofins.getTransactionId(),
							nfnotainfoitemimpostocofins.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostocofins.getOutrasOperacoes())) {
			count += NFNotaInfoItemImpostoCOFINSOutrasOperacoesBARD
					.maintainNFNotaInfoItemImpostoCOFINSOutrasOperacoesAssociations(
							nfnotainfoitemimpostocofins.getOutrasOperacoes(), response,
							nfnotainfoitemimpostocofins.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(),
							statusBAR, historicoBAR, nfnotainfoitemimpostocofins.getId(),
							nfnotainfoitemimpostocofins.getCreateUser(), nfnotainfoitemimpostocofins.getTransactionId(),
							nfnotainfoitemimpostocofins.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINS, nfnotainfoitemimpostocofins,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINS, AcaoEnum.INSERT,
				nfnotainfoitemimpostocofins.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofins.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSBAR#
	 * updateNFNotaInfoItemImpostoCOFINS(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoCOFINS)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoCOFINS(NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostocofins.setProcessId(nfnotainfoitemimpostocofins.getTransactionId());
		Integer count =0;
		if (!ValidationUtil.isNull(nfnotainfoitemimpostocofins.getAliquota())) {
			count += NFNotaInfoItemImpostoCOFINSAliquotaBARD.maintainNFNotaInfoItemImpostoCOFINSAliquotaAssociations(
					nfnotainfoitemimpostocofins.getAliquota(), response, nfnotainfoitemimpostocofins.getId(), null,
					null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofins.getCreateUser(),
					nfnotainfoitemimpostocofins.getTransactionId(), nfnotainfoitemimpostocofins.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostocofins.getQuantidade())) {
			count += NFNotaInfoItemImpostoCOFINSQuantidadeBARD
					.maintainNFNotaInfoItemImpostoCOFINSQuantidadeAssociations(
							nfnotainfoitemimpostocofins.getQuantidade(), response, nfnotainfoitemimpostocofins.getId(),
							null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofins.getCreateUser(),
							nfnotainfoitemimpostocofins.getTransactionId(),
							nfnotainfoitemimpostocofins.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostocofins.getNaoTributavel())) {
			count += NFNotaInfoItemImpostoCOFINSNaoTributavelBARD
					.maintainNFNotaInfoItemImpostoCOFINSNaoTributavelAssociations(
							nfnotainfoitemimpostocofins.getNaoTributavel(), response, nfnotainfoitemimpostocofins.getId(),
							null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofins.getCreateUser(),
							nfnotainfoitemimpostocofins.getTransactionId(),
							nfnotainfoitemimpostocofins.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostocofins.getOutrasOperacoes())) {
			count += NFNotaInfoItemImpostoCOFINSOutrasOperacoesBARD
					.maintainNFNotaInfoItemImpostoCOFINSOutrasOperacoesAssociations(
							nfnotainfoitemimpostocofins.getOutrasOperacoes(), response,
							nfnotainfoitemimpostocofins.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(),
							statusBAR, historicoBAR, nfnotainfoitemimpostocofins.getId(),
							nfnotainfoitemimpostocofins.getCreateUser(), nfnotainfoitemimpostocofins.getTransactionId(),
							nfnotainfoitemimpostocofins.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINS, nfnotainfoitemimpostocofins,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINS, AcaoEnum.UPDATE,
				nfnotainfoitemimpostocofins.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofins.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSBAR#
	 * deleteNFNotaInfoItemImpostoCOFINS(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoCOFINS)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoCOFINSById(
			NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins) {
		InternalResponse response = new InternalResponse();
		Integer count =0;
		nfnotainfoitemimpostocofins.setProcessId(nfnotainfoitemimpostocofins.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINS, nfnotainfoitemimpostocofins,
				response);
		if (!ValidationUtil.isNull(nfnotainfoitemimpostocofins.getAliquota())) {
			count += NFNotaInfoItemImpostoCOFINSAliquotaBARD.maintainNFNotaInfoItemImpostoCOFINSAliquotaAssociations(
					nfnotainfoitemimpostocofins.getAliquota(), response, nfnotainfoitemimpostocofins.getId(), null,
					null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
					nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofins.getCreateUser(),
					nfnotainfoitemimpostocofins.getTransactionId(), nfnotainfoitemimpostocofins.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostocofins.getQuantidade())) {
			count += NFNotaInfoItemImpostoCOFINSQuantidadeBARD
					.maintainNFNotaInfoItemImpostoCOFINSQuantidadeAssociations(
							nfnotainfoitemimpostocofins.getQuantidade(), response, nfnotainfoitemimpostocofins.getId(),
							null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofins.getCreateUser(),
							nfnotainfoitemimpostocofins.getTransactionId(),
							nfnotainfoitemimpostocofins.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostocofins.getNaoTributavel())) {
			count += NFNotaInfoItemImpostoCOFINSNaoTributavelBARD
					.maintainNFNotaInfoItemImpostoCOFINSNaoTributavelAssociations(
							nfnotainfoitemimpostocofins.getNaoTributavel(), response, nfnotainfoitemimpostocofins.getId(),
							null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR, historicoBAR,
							nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofins.getCreateUser(),
							nfnotainfoitemimpostocofins.getTransactionId(),
							nfnotainfoitemimpostocofins.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfoitemimpostocofins.getOutrasOperacoes())) {
			count += NFNotaInfoItemImpostoCOFINSOutrasOperacoesBARD
					.maintainNFNotaInfoItemImpostoCOFINSOutrasOperacoesAssociations(
							nfnotainfoitemimpostocofins.getOutrasOperacoes(), response,
							nfnotainfoitemimpostocofins.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(),
							statusBAR, historicoBAR, nfnotainfoitemimpostocofins.getId(),
							nfnotainfoitemimpostocofins.getCreateUser(), nfnotainfoitemimpostocofins.getTransactionId(),
							nfnotainfoitemimpostocofins.getTransactionId());
		}

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINS, AcaoEnum.DELETE,
				nfnotainfoitemimpostocofins.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofins.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSBAR#
	 * deleteAllNFNotaInfoItemImpostoCOFINSs()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoCOFINSs() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoCOFINSBAR#
	 * fetchNFNotaInfoItemImpostoCOFINSById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoCOFINS fetchNFNotaInfoItemImpostoCOFINSById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoCOFINS) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINS, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSBAR#
	 * fetchAllNFNotaInfoItemImpostoCOFINSsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINS> fetchAllNFNotaInfoItemImpostoCOFINSs(
			NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins) {
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINS> response = new InternalResultsResponse<NFNotaInfoItemImpostoCOFINS>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoCOFINSBAR#
	 * fetchNFNotaInfoItemImpostoCOFINSsByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINS> fetchNFNotaInfoItemImpostoCOFINSsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINS> response = new InternalResultsResponse<NFNotaInfoItemImpostoCOFINS>();
		fetchNFNotaInfoItemImpostoCOFINSsByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINS_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINS_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoCOFINSsByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoCOFINSsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSAliquotaBAR#insertNFNotaInfoItemImpostoCOFINSAliquota(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoCOFINSAliquota)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoCOFINSAliquota(
			NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostocofinsaliquota.setProcessId(nfnotainfoitemimpostocofinsaliquota.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA,
				nfnotainfoitemimpostocofinsaliquota, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA,
				AcaoEnum.INSERT, nfnotainfoitemimpostocofinsaliquota.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostocofinsaliquota.getId(), nfnotainfoitemimpostocofinsaliquota.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSAliquotaBAR#
	 * updateNFNotaInfoItemImpostoCOFINSAliquota(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoCOFINSAliquota)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoCOFINSAliquota(
			NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostocofinsaliquota.setProcessId(nfnotainfoitemimpostocofinsaliquota.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA,
				nfnotainfoitemimpostocofinsaliquota, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA,
				AcaoEnum.UPDATE, nfnotainfoitemimpostocofinsaliquota.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostocofinsaliquota.getId(), nfnotainfoitemimpostocofinsaliquota.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSAliquotaBAR#
	 * deleteNFNotaInfoItemImpostoCOFINSAliquota(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoCOFINSAliquota)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoCOFINSAliquotaById(
			NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostocofinsaliquota.setProcessId(nfnotainfoitemimpostocofinsaliquota.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA,
				nfnotainfoitemimpostocofinsaliquota, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA,
				AcaoEnum.DELETE, nfnotainfoitemimpostocofinsaliquota.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostocofinsaliquota.getId(), nfnotainfoitemimpostocofinsaliquota.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSAliquotaBAR#
	 * deleteAllNFNotaInfoItemImpostoCOFINSAliquotas()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoCOFINSAliquotas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoCOFINSAliquotaBAR#
	 * fetchNFNotaInfoItemImpostoCOFINSAliquotaById(com.qat.samples.sysmgmt.
	 * model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoCOFINSAliquota fetchNFNotaInfoItemImpostoCOFINSAliquotaById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoCOFINSAliquota) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSAliquotaBAR#
	 * fetchAllNFNotaInfoItemImpostoCOFINSAliquotasCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSAliquota> fetchAllNFNotaInfoItemImpostoCOFINSAliquotas(
			NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota) {
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSAliquota> response = new InternalResultsResponse<NFNotaInfoItemImpostoCOFINSAliquota>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoCOFINSAliquotaBAR#
	 * fetchNFNotaInfoItemImpostoCOFINSAliquotasByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSAliquota> fetchNFNotaInfoItemImpostoCOFINSAliquotasByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSAliquota> response = new InternalResultsResponse<NFNotaInfoItemImpostoCOFINSAliquota>();
		fetchNFNotaInfoItemImpostoCOFINSAliquotasByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoCOFINSAliquotasByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoCOFINSAliquotasByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSQuantidadeBAR#insertNFNotaInfoItemImpostoCOFINSQuantidade(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoCOFINSQuantidade)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoCOFINSQuantidade(
			NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostocofinsquantidade.setProcessId(nfnotainfoitemimpostocofinsquantidade.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE,
				nfnotainfoitemimpostocofinsquantidade, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE,
				AcaoEnum.INSERT, nfnotainfoitemimpostocofinsquantidade.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostocofinsquantidade.getId(), nfnotainfoitemimpostocofinsquantidade.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoCOFINSQuantidadeBAR#
	 * updateNFNotaInfoItemImpostoCOFINSQuantidade(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoCOFINSQuantidade)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoCOFINSQuantidade(
			NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostocofinsquantidade.setProcessId(nfnotainfoitemimpostocofinsquantidade.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE,
				nfnotainfoitemimpostocofinsquantidade, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE,
				AcaoEnum.UPDATE, nfnotainfoitemimpostocofinsquantidade.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostocofinsquantidade.getId(), nfnotainfoitemimpostocofinsquantidade.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoCOFINSQuantidadeBAR#
	 * deleteNFNotaInfoItemImpostoCOFINSQuantidade(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoCOFINSQuantidade)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoCOFINSQuantidadeById(
			NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostocofinsquantidade.setProcessId(nfnotainfoitemimpostocofinsquantidade.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE,
				nfnotainfoitemimpostocofinsquantidade, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE,
				AcaoEnum.DELETE, nfnotainfoitemimpostocofinsquantidade.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostocofinsquantidade.getId(), nfnotainfoitemimpostocofinsquantidade.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoCOFINSQuantidadeBAR#
	 * deleteAllNFNotaInfoItemImpostoCOFINSQuantidades()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoCOFINSQuantidades() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoCOFINSQuantidadeBAR#
	 * fetchNFNotaInfoItemImpostoCOFINSQuantidadeById(com.qat.samples.sysmgmt.
	 * model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoCOFINSQuantidade fetchNFNotaInfoItemImpostoCOFINSQuantidadeById(
			FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoCOFINSQuantidade) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoCOFINSQuantidadeBAR#
	 * fetchAllNFNotaInfoItemImpostoCOFINSQuantidadesCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSQuantidade> fetchAllNFNotaInfoItemImpostoCOFINSQuantidades(
			NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade) {
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSQuantidade> response = new InternalResultsResponse<NFNotaInfoItemImpostoCOFINSQuantidade>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoCOFINSQuantidadeBAR#
	 * fetchNFNotaInfoItemImpostoCOFINSQuantidadesByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSQuantidade> fetchNFNotaInfoItemImpostoCOFINSQuantidadesByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSQuantidade> response = new InternalResultsResponse<NFNotaInfoItemImpostoCOFINSQuantidade>();
		fetchNFNotaInfoItemImpostoCOFINSQuantidadesByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoCOFINSQuantidadesByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoCOFINSQuantidadesByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSNaoTributavelBAR#insertNFNotaInfoItemImpostoCOFINSNaoTributavel(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoCOFINSNaoTributavel)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoCOFINSNaoTributavel(
			NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostocofinsnaotributavel
				.setProcessId(nfnotainfoitemimpostocofinsnaotributavel.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL,
				nfnotainfoitemimpostocofinsnaotributavel, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL,
				AcaoEnum.INSERT, nfnotainfoitemimpostocofinsnaotributavel.getTransactionId(), getHistoricoBAR(),
				response, nfnotainfoitemimpostocofinsnaotributavel.getId(),
				nfnotainfoitemimpostocofinsnaotributavel.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoCOFINSNaoTributavelBAR#
	 * updateNFNotaInfoItemImpostoCOFINSNaoTributavel(com.qat.samples.sysmgmt.
	 * base.model.NFNotaInfoItemImpostoCOFINSNaoTributavel)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoCOFINSNaoTributavel(
			NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostocofinsnaotributavel
				.setProcessId(nfnotainfoitemimpostocofinsnaotributavel.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL,
				nfnotainfoitemimpostocofinsnaotributavel, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL,
				AcaoEnum.UPDATE, nfnotainfoitemimpostocofinsnaotributavel.getTransactionId(), getHistoricoBAR(),
				response, nfnotainfoitemimpostocofinsnaotributavel.getId(),
				nfnotainfoitemimpostocofinsnaotributavel.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoCOFINSNaoTributavelBAR#
	 * deleteNFNotaInfoItemImpostoCOFINSNaoTributavel(com.qat.samples.sysmgmt.
	 * base.model.NFNotaInfoItemImpostoCOFINSNaoTributavel)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoCOFINSNaoTributavelById(
			NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostocofinsnaotributavel
				.setProcessId(nfnotainfoitemimpostocofinsnaotributavel.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL,
				nfnotainfoitemimpostocofinsnaotributavel, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL,
				AcaoEnum.DELETE, nfnotainfoitemimpostocofinsnaotributavel.getTransactionId(), getHistoricoBAR(),
				response, nfnotainfoitemimpostocofinsnaotributavel.getId(),
				nfnotainfoitemimpostocofinsnaotributavel.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoCOFINSNaoTributavelBAR#
	 * deleteAllNFNotaInfoItemImpostoCOFINSNaoTributavels()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoCOFINSNaoTributavels() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoCOFINSNaoTributavelBAR#
	 * fetchNFNotaInfoItemImpostoCOFINSNaoTributavelById(com.qat.samples.sysmgmt
	 * .model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoCOFINSNaoTributavel fetchNFNotaInfoItemImpostoCOFINSNaoTributavelById(
			FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoCOFINSNaoTributavel) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoCOFINSNaoTributavelBAR#
	 * fetchAllNFNotaInfoItemImpostoCOFINSNaoTributavelsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSNaoTributavel> fetchAllNFNotaInfoItemImpostoCOFINSNaoTributavels(
			NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel) {
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSNaoTributavel> response = new InternalResultsResponse<NFNotaInfoItemImpostoCOFINSNaoTributavel>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoCOFINSNaoTributavelBAR#
	 * fetchNFNotaInfoItemImpostoCOFINSNaoTributavelsByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSNaoTributavel> fetchNFNotaInfoItemImpostoCOFINSNaoTributavelsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSNaoTributavel> response = new InternalResultsResponse<NFNotaInfoItemImpostoCOFINSNaoTributavel>();
		fetchNFNotaInfoItemImpostoCOFINSNaoTributavelsByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoCOFINSNaoTributavelsByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoCOFINSNaoTributavelsByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSOutrasOperacoesBAR#insertNFNotaInfoItemImpostoCOFINSOutrasOperacoes(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoCOFINSOutrasOperacoes)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoCOFINSOutrasOperacoes(
			NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostocofinsoutrasoperacoes
				.setProcessId(nfnotainfoitemimpostocofinsoutrasoperacoes.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES,
				nfnotainfoitemimpostocofinsoutrasoperacoes, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES,
				AcaoEnum.INSERT, nfnotainfoitemimpostocofinsoutrasoperacoes.getTransactionId(), getHistoricoBAR(),
				response, nfnotainfoitemimpostocofinsoutrasoperacoes.getId(),
				nfnotainfoitemimpostocofinsoutrasoperacoes.getUserId());



		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoCOFINSOutrasOperacoesBAR#
	 * updateNFNotaInfoItemImpostoCOFINSOutrasOperacoes(com.qat.samples.sysmgmt.
	 * base.model.NFNotaInfoItemImpostoCOFINSOutrasOperacoes)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoCOFINSOutrasOperacoes(
			NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostocofinsoutrasoperacoes
				.setProcessId(nfnotainfoitemimpostocofinsoutrasoperacoes.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES,
				nfnotainfoitemimpostocofinsoutrasoperacoes, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES,
				AcaoEnum.UPDATE, nfnotainfoitemimpostocofinsoutrasoperacoes.getTransactionId(), getHistoricoBAR(),
				response, nfnotainfoitemimpostocofinsoutrasoperacoes.getId(),
				nfnotainfoitemimpostocofinsoutrasoperacoes.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoCOFINSOutrasOperacoesBAR#
	 * deleteNFNotaInfoItemImpostoCOFINSOutrasOperacoes(com.qat.samples.sysmgmt.
	 * base.model.NFNotaInfoItemImpostoCOFINSOutrasOperacoes)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoCOFINSOutrasOperacoesById(
			NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostocofinsoutrasoperacoes
				.setProcessId(nfnotainfoitemimpostocofinsoutrasoperacoes.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES,
				nfnotainfoitemimpostocofinsoutrasoperacoes, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES,
				AcaoEnum.DELETE, nfnotainfoitemimpostocofinsoutrasoperacoes.getTransactionId(), getHistoricoBAR(),
				response, nfnotainfoitemimpostocofinsoutrasoperacoes.getId(),
				nfnotainfoitemimpostocofinsoutrasoperacoes.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoCOFINSOutrasOperacoesBAR#
	 * deleteAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES_ALL,
				response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.
	 * INFNotaInfoItemImpostoCOFINSOutrasOperacoesBAR#
	 * fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoesById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoCOFINSOutrasOperacoes fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoesById(
			FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoCOFINSOutrasOperacoes) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.
	 * INFNotaInfoItemImpostoCOFINSOutrasOperacoesBAR#
	 * fetchAllNFNotaInfoItemImpostoCOFINSOutrasOperacoessCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSOutrasOperacoes> fetchAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess(
			NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes) {
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSOutrasOperacoes> response = new InternalResultsResponse<NFNotaInfoItemImpostoCOFINSOutrasOperacoes>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.
	 * INFNotaInfoItemImpostoCOFINSOutrasOperacoesBAR#
	 * fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoessByRequest(com.qat.samples
	 * .sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSOutrasOperacoes> fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoessByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSOutrasOperacoes> response = new InternalResultsResponse<NFNotaInfoItemImpostoCOFINSOutrasOperacoes>();
		fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoessByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoessByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoessByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================### NFNOTAINFOITEMIMPOSTOCOFINSST
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSSTBAR#insertNFNotaInfoItemImpostoCOFINSST(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoCOFINSST)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoCOFINSST(
			NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostocofinsst.setProcessId(nfnotainfoitemimpostocofinsst.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOCOFINSST,
				nfnotainfoitemimpostocofinsst, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSST,
				AcaoEnum.INSERT, nfnotainfoitemimpostocofinsst.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostocofinsst.getId(), nfnotainfoitemimpostocofinsst.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSSTBAR#
	 * updateNFNotaInfoItemImpostoCOFINSST(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoCOFINSST)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoCOFINSST(
			NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostocofinsst.setProcessId(nfnotainfoitemimpostocofinsst.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOCOFINSST,
				nfnotainfoitemimpostocofinsst, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSST,
				AcaoEnum.UPDATE, nfnotainfoitemimpostocofinsst.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostocofinsst.getId(), nfnotainfoitemimpostocofinsst.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSSTBAR#
	 * deleteNFNotaInfoItemImpostoCOFINSST(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoItemImpostoCOFINSST)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoCOFINSSTById(
			NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostocofinsst.setProcessId(nfnotainfoitemimpostocofinsst.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSST,
				nfnotainfoitemimpostocofinsst, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSST,
				AcaoEnum.DELETE, nfnotainfoitemimpostocofinsst.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostocofinsst.getId(), nfnotainfoitemimpostocofinsst.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSSTBAR#
	 * deleteAllNFNotaInfoItemImpostoCOFINSSTs()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoCOFINSSTs() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOCOFINSST_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoCOFINSSTBAR#
	 * fetchNFNotaInfoItemImpostoCOFINSSTById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoCOFINSST fetchNFNotaInfoItemImpostoCOFINSSTById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoCOFINSST) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSST, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoCOFINSSTBAR#
	 * fetchAllNFNotaInfoItemImpostoCOFINSSTsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSST> fetchAllNFNotaInfoItemImpostoCOFINSSTs(
			NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst) {
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSST> response = new InternalResultsResponse<NFNotaInfoItemImpostoCOFINSST>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSST_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoCOFINSSTBAR#
	 * fetchNFNotaInfoItemImpostoCOFINSSTsByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSST> fetchNFNotaInfoItemImpostoCOFINSSTsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSST> response = new InternalResultsResponse<NFNotaInfoItemImpostoCOFINSST>();
		fetchNFNotaInfoItemImpostoCOFINSSTsByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSST_COUNT, STMT_FETCH_NFNOTAINFOITEMIMPOSTOCOFINSST_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoCOFINSSTsByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoCOFINSSTsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSUFDESTINO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSUFDestinoBAR#insertNFNotaInfoItemImpostoICMSUFDestino(com.qat.samples.sysmgmt.base.model.NFNotaInfoItemImpostoICMSUFDestino)
	 */
	@Override
	public InternalResponse insertNFNotaInfoItemImpostoICMSUFDestino(
			NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoitemimpostoicmsufdestino.setProcessId(nfnotainfoitemimpostoicmsufdestino.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO,
				nfnotainfoitemimpostoicmsufdestino, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSUFDESTINO,
				AcaoEnum.INSERT, nfnotainfoitemimpostoicmsufdestino.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmsufdestino.getId(), nfnotainfoitemimpostoicmsufdestino.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSUFDestinoBAR#
	 * updateNFNotaInfoItemImpostoICMSUFDestino(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoICMSUFDestino)
	 */
	@Override
	public InternalResponse updateNFNotaInfoItemImpostoICMSUFDestino(
			NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmsufdestino.setProcessId(nfnotainfoitemimpostoicmsufdestino.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO,
				nfnotainfoitemimpostoicmsufdestino, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSUFDESTINO,
				AcaoEnum.UPDATE, nfnotainfoitemimpostoicmsufdestino.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmsufdestino.getId(), nfnotainfoitemimpostoicmsufdestino.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSUFDestinoBAR#
	 * deleteNFNotaInfoItemImpostoICMSUFDestino(com.qat.samples.sysmgmt.base.
	 * model.NFNotaInfoItemImpostoICMSUFDestino)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoItemImpostoICMSUFDestinoById(
			NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino) {
		InternalResponse response = new InternalResponse();
		nfnotainfoitemimpostoicmsufdestino.setProcessId(nfnotainfoitemimpostoicmsufdestino.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO,
				nfnotainfoitemimpostoicmsufdestino, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSUFDESTINO,
				AcaoEnum.DELETE, nfnotainfoitemimpostoicmsufdestino.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoitemimpostoicmsufdestino.getId(), nfnotainfoitemimpostoicmsufdestino.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSUFDestinoBAR#
	 * deleteAllNFNotaInfoItemImpostoICMSUFDestinos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSUFDestinos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSUFDestinoBAR#
	 * fetchNFNotaInfoItemImpostoICMSUFDestinoById(com.qat.samples.sysmgmt.model
	 * .request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoItemImpostoICMSUFDestino fetchNFNotaInfoItemImpostoICMSUFDestinoById(FetchByIdRequest request) {
		return (NFNotaInfoItemImpostoICMSUFDestino) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoItemImpostoICMSUFDestinoBAR#
	 * fetchAllNFNotaInfoItemImpostoICMSUFDestinosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSUFDestino> fetchAllNFNotaInfoItemImpostoICMSUFDestinos(
			NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSUFDestino> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSUFDestino>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoItemImpostoICMSUFDestinoBAR#
	 * fetchNFNotaInfoItemImpostoICMSUFDestinosByRequest(com.qat.samples.sysmgmt
	 * .model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSUFDestino> fetchNFNotaInfoItemImpostoICMSUFDestinosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoItemImpostoICMSUFDestino> response = new InternalResultsResponse<NFNotaInfoItemImpostoICMSUFDestino>();
		fetchNFNotaInfoItemImpostoICMSUFDestinosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO_COUNT,
				STMT_FETCH_NFNOTAINFOITEMIMPOSTOICMSUFDESTINO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoItemImpostoICMSUFDestinosByRequest
	// ####======================================

	public static void fetchNFNotaInfoItemImpostoICMSUFDestinosByRequest(SqlSession sqlSession,
			PagedInquiryRequest request, String countStatement, String fetchPagedStatement,
			InternalResultsResponse<?> response) {

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

	// ===================================### NFIMPOSTODEVOLVIDO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFImpostoDevolvidoBAR#insertNFImpostoDevolvido(com.qat.samples.sysmgmt.base.model.NFImpostoDevolvido)
	 */
	@Override
	public InternalResponse insertNFImpostoDevolvido(NFImpostoDevolvido nfimpostodevolvido) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfimpostodevolvido.setProcessId(nfimpostodevolvido.getTransactionId());

		if (!ValidationUtil.isNull(nfimpostodevolvido.getInformacaoIPIDevolvido())) {
			count += NFInformacaoImpostoDevolvidoBARD.maintainNFInformacaoImpostoDevolvidoAssociations(nfimpostodevolvido.getInformacaoIPIDevolvido(),
					response, nfimpostodevolvido.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, nfimpostodevolvido.getId(), nfimpostodevolvido.getCreateUser(),
					nfimpostodevolvido.getTransactionId(), nfimpostodevolvido.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFIMPOSTODEVOLVIDO, nfimpostodevolvido, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFIMPOSTODEVOLVIDO, AcaoEnum.INSERT,
				nfimpostodevolvido.getTransactionId(), getHistoricoBAR(), response, nfimpostodevolvido.getId(),
				nfimpostodevolvido.getUserId());


		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFImpostoDevolvidoBAR#
	 * updateNFImpostoDevolvido(com.qat.samples.sysmgmt.base.model.
	 * NFImpostoDevolvido)
	 */
	@Override
	public InternalResponse updateNFImpostoDevolvido(NFImpostoDevolvido nfimpostodevolvido) {
		InternalResponse response = new InternalResponse();
		nfimpostodevolvido.setProcessId(nfimpostodevolvido.getTransactionId());
		Integer a = 0;

		if (!ValidationUtil.isNull(nfimpostodevolvido.getInformacaoIPIDevolvido())) {
			a += NFInformacaoImpostoDevolvidoBARD.maintainNFInformacaoImpostoDevolvidoAssociations(nfimpostodevolvido.getInformacaoIPIDevolvido(),
					response, nfimpostodevolvido.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, nfimpostodevolvido.getId(), nfimpostodevolvido.getCreateUser(),
					nfimpostodevolvido.getTransactionId(), nfimpostodevolvido.getTransactionId());
		}


		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFIMPOSTODEVOLVIDO, nfimpostodevolvido, response);

		 a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFIMPOSTODEVOLVIDO, AcaoEnum.UPDATE,
				nfimpostodevolvido.getTransactionId(), getHistoricoBAR(), response, nfimpostodevolvido.getId(),
				nfimpostodevolvido.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFImpostoDevolvidoBAR#
	 * deleteNFImpostoDevolvido(com.qat.samples.sysmgmt.base.model.
	 * NFImpostoDevolvido)
	 */
	@Override
	public InternalResponse deleteNFImpostoDevolvidoById(NFImpostoDevolvido nfimpostodevolvido) {
		InternalResponse response = new InternalResponse();
		nfimpostodevolvido.setProcessId(nfimpostodevolvido.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFIMPOSTODEVOLVIDO, nfimpostodevolvido, response);
		Integer a = 0;
		if (!ValidationUtil.isNull(nfimpostodevolvido.getInformacaoIPIDevolvido())) {
			a += NFInformacaoImpostoDevolvidoBARD.maintainNFInformacaoImpostoDevolvidoAssociations(nfimpostodevolvido.getInformacaoIPIDevolvido(),
					response, nfimpostodevolvido.getId(), null, null, TabelaEnum.NFNOTA, getNfnotaInfoItemBAR(), statusBAR,
					historicoBAR, nfimpostodevolvido.getId(), nfimpostodevolvido.getCreateUser(),
					nfimpostodevolvido.getTransactionId(), nfimpostodevolvido.getTransactionId());
		}

		a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFIMPOSTODEVOLVIDO, AcaoEnum.DELETE,
				nfimpostodevolvido.getTransactionId(), getHistoricoBAR(), response, nfimpostodevolvido.getId(),
				nfimpostodevolvido.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFImpostoDevolvidoBAR#
	 * deleteAllNFImpostoDevolvidos()
	 */
	@Override
	public InternalResponse deleteAllNFImpostoDevolvidos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFIMPOSTODEVOLVIDO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFImpostoDevolvidoBAR#
	 * fetchNFImpostoDevolvidoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFImpostoDevolvido fetchNFImpostoDevolvidoById(FetchByIdRequest request) {
		return (NFImpostoDevolvido) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFIMPOSTODEVOLVIDO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFImpostoDevolvidoBAR#
	 * fetchAllNFImpostoDevolvidosCache()
	 */
	@Override
	public InternalResultsResponse<NFImpostoDevolvido> fetchAllNFImpostoDevolvidos(
			NFImpostoDevolvido nfimpostodevolvido) {
		InternalResultsResponse<NFImpostoDevolvido> response = new InternalResultsResponse<NFImpostoDevolvido>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFIMPOSTODEVOLVIDO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFImpostoDevolvidoBAR#
	 * fetchNFImpostoDevolvidosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFImpostoDevolvido> fetchNFImpostoDevolvidosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFImpostoDevolvido> response = new InternalResultsResponse<NFImpostoDevolvido>();
		fetchNFImpostoDevolvidosByRequest(getSqlSession(), request, STMT_FETCH_NFIMPOSTODEVOLVIDO_COUNT,
				STMT_FETCH_NFIMPOSTODEVOLVIDO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFInformacaoImpostoDevolvidosByRequest
	// ####======================================

	public static void fetchNFInformacaoImpostoDevolvidosByRequestd(SqlSession sqlSession, PagedInquiryRequest request,
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

}
