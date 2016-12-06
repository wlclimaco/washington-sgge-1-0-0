/** create by system gera-java version 1.0.0 28/09/2016 16:26 : 42*/
package com.qat.samples.sysmgmt.bar.mybatis.Nfe;

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
import com.qat.samples.sysmgmt.bar.Cadastros.ICadastrosBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Nfe.INFNotaInfoItemBAR;
import com.qat.samples.sysmgmt.bar.Nfe.INFeBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.Vendas.IVendasBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.InsertHistBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFInfoCupomFiscalReferenciadoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFInfoProdutorRuralReferenciadaBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFInfoReferenciadaBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoAvulsaBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoCanaBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoCanaDeducaoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoCanaFornecimentoDiarioBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoCartaoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoCobrancaBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoCompraBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoDestinatarioBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoDuplicataBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoEmitenteBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoExportacaoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoFaturaBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoICMSTotalBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoISSQNTotalBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoIdentificacaoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoInformacoesAdicionaisBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoItemBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoLocalBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoObservacaoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoPagamentoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoProcessoReferenciadoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoReboqueBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoRetencaoICMSTransporteBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoRetencoesTributosBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoSuplementarBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoTotalBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoTransportadorBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoTransporteBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFNotaInfoVeiculoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NFPessoaAutorizadaDownloadNFeBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.StatusBARD;
import com.qat.samples.sysmgmt.nfe.model.NFInfoCupomFiscalReferenciado;
import com.qat.samples.sysmgmt.nfe.model.NFInfoModelo1Por1AReferenciada;
import com.qat.samples.sysmgmt.nfe.model.NFInfoProdutorRuralReferenciada;
import com.qat.samples.sysmgmt.nfe.model.NFInfoReferenciada;
import com.qat.samples.sysmgmt.nfe.model.NFNota;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfo;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoAvulsa;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCana;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCanaDeducao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCanaFornecimentoDiario;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCartao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCobranca;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCompra;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoDestinatario;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoDuplicata;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoEmitente;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoExportacao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoFatura;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoICMSTotal;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoISSQNTotal;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoIdentificacao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoInformacoesAdicionais;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoLocal;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoObservacao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoPagamento;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoProcessoReferenciado;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoReboque;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoRetencaoICMSTransporte;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoRetencoesTributos;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoSuplementar;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoTotal;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoTransportador;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoTransporte;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoVeiculo;
import com.qat.samples.sysmgmt.nfe.model.NFPessoaAutorizadaDownloadNFe;
import com.qat.samples.sysmgmt.nfe.request.NFNotaInquiryRequest;
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
public class NFeBARImpl extends SqlSessionDaoSupport implements INFeBAR {

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	///===================================### NFINFOCUPOMFISCALREFERENCIADO ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFINFOCUPOMFISCALREFERENCIADO = "NFInfoCupomFiscalReferenciadoMap.";

	/** The Constant STMT_INSERT_NFINFOCUPOMFISCALREFERENCIADO. */
	private static final String STMT_INSERT_NFINFOCUPOMFISCALREFERENCIADO = NAMESPACE_NFINFOCUPOMFISCALREFERENCIADO + "insertNFInfoCupomFiscalReferenciado";

	/** The Constant STMT_UPDATE_NFINFOCUPOMFISCALREFERENCIADO. */
	private static final String STMT_UPDATE_NFINFOCUPOMFISCALREFERENCIADO = NAMESPACE_NFINFOCUPOMFISCALREFERENCIADO + "updateNFInfoCupomFiscalReferenciado";

	/** The Constant STMT_DELETE_NFINFOCUPOMFISCALREFERENCIADO. */
	private static final String STMT_DELETE_NFINFOCUPOMFISCALREFERENCIADO = NAMESPACE_NFINFOCUPOMFISCALREFERENCIADO + "deleteNFInfoCupomFiscalReferenciadoById";

		/** The Constant STMT_DELETE_NFINFOCUPOMFISCALREFERENCIADO_ALL. */
		private static final String STMT_DELETE_NFINFOCUPOMFISCALREFERENCIADO_ALL = NAMESPACE_NFINFOCUPOMFISCALREFERENCIADO + "deleteAllNFInfoCupomFiscalReferenciados";

		/** The Constant STMT_FETCH_NFINFOCUPOMFISCALREFERENCIADO. */
		private static final String STMT_FETCH_NFINFOCUPOMFISCALREFERENCIADO = NAMESPACE_NFINFOCUPOMFISCALREFERENCIADO + "fetchNFInfoCupomFiscalReferenciadoById";

		/** The Constant STMT_FETCH_NFINFOCUPOMFISCALREFERENCIADO_ALL. */
		private static final String STMT_FETCH_NFINFOCUPOMFISCALREFERENCIADO_ALL = NAMESPACE_NFINFOCUPOMFISCALREFERENCIADO + "fetchAllNFInfoCupomFiscalReferenciados";

		/** The Constant STMT_FETCH_NFINFOCUPOMFISCALREFERENCIADO_COUNT. */
		private static final String STMT_FETCH_NFINFOCUPOMFISCALREFERENCIADO_COUNT = NAMESPACE_NFINFOCUPOMFISCALREFERENCIADO + "fetchNFInfoCupomFiscalReferenciadoRowCount";

		/** The Constant STMT_FETCH_NFINFOCUPOMFISCALREFERENCIADO_ALL_REQUEST. */
		private static final String STMT_FETCH_NFINFOCUPOMFISCALREFERENCIADO_ALL_REQUEST = NAMESPACE_NFINFOCUPOMFISCALREFERENCIADO + "fetchAllNFInfoCupomFiscalReferenciadosRequest";

	/// ===================================### NFNOTA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTA = "NFNotaMap.";

	/** The Constant STMT_INSERT_NFNOTA. */
	private static final String STMT_INSERT_NFNOTA = NAMESPACE_NFNOTA + "insertNFNota";

	/** The Constant STMT_UPDATE_NFNOTA. */
	private static final String STMT_UPDATE_NFNOTA = NAMESPACE_NFNOTA + "updateNFNota";

	/** The Constant STMT_DELETE_NFNOTA. */
	private static final String STMT_DELETE_NFNOTA = NAMESPACE_NFNOTA + "deleteNFNotaById";

	/** The Constant STMT_DELETE_NFNOTA_ALL. */
	private static final String STMT_DELETE_NFNOTA_ALL = NAMESPACE_NFNOTA + "deleteAllNFNotas";

	/** The Constant STMT_FETCH_NFNOTA. */
	private static final String STMT_FETCH_NFNOTA = NAMESPACE_NFNOTA + "fetchNFNotaById";

	/** The Constant STMT_FETCH_NFNOTA_ALL. */
	private static final String STMT_FETCH_NFNOTA_ALL = NAMESPACE_NFNOTA + "fetchAllNFNotas";

	/** The Constant STMT_FETCH_NFNOTA_COUNT. */
	private static final String STMT_FETCH_NFNOTA_COUNT = NAMESPACE_NFNOTA + "fetchNFNotaRowCount";

	/** The Constant STMT_FETCH_NFNOTA_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTA_ALL_REQUEST = NAMESPACE_NFNOTA + "fetchAllNFNotasRequest";

	/// ===================================### NFNOTAINFO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFO = "NFNotaInfoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFO. */
	private static final String STMT_INSERT_NFNOTAINFO = NAMESPACE_NFNOTAINFO + "insertNFNotaInfo";

	/** The Constant STMT_UPDATE_NFNOTAINFO. */
	private static final String STMT_UPDATE_NFNOTAINFO = NAMESPACE_NFNOTAINFO + "updateNFNotaInfo";

	/** The Constant STMT_DELETE_NFNOTAINFO. */
	private static final String STMT_DELETE_NFNOTAINFO = NAMESPACE_NFNOTAINFO + "deleteNFNotaInfoById";

	/** The Constant STMT_DELETE_NFNOTAINFO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFO_ALL = NAMESPACE_NFNOTAINFO + "deleteAllNFNotaInfos";

	/** The Constant STMT_FETCH_NFNOTAINFO. */
	private static final String STMT_FETCH_NFNOTAINFO = NAMESPACE_NFNOTAINFO + "fetchNFNotaInfoById";

	/** The Constant STMT_FETCH_NFNOTAINFO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFO_ALL = NAMESPACE_NFNOTAINFO + "fetchAllNFNotaInfos";

	/** The Constant STMT_FETCH_NFNOTAINFO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFO_COUNT = NAMESPACE_NFNOTAINFO + "fetchNFNotaInfoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFO_ALL_REQUEST = NAMESPACE_NFNOTAINFO + "fetchAllNFNotaInfosRequest";

	/// ===================================### NFNOTAINFOIDENTIFICACAO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOIDENTIFICACAO = "NFNotaInfoIdentificacaoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOIDENTIFICACAO. */
	private static final String STMT_INSERT_NFNOTAINFOIDENTIFICACAO = NAMESPACE_NFNOTAINFOIDENTIFICACAO
			+ "insertNFNotaInfoIdentificacao";

	/** The Constant STMT_UPDATE_NFNOTAINFOIDENTIFICACAO. */
	private static final String STMT_UPDATE_NFNOTAINFOIDENTIFICACAO = NAMESPACE_NFNOTAINFOIDENTIFICACAO
			+ "updateNFNotaInfoIdentificacao";

	/** The Constant STMT_DELETE_NFNOTAINFOIDENTIFICACAO. */
	private static final String STMT_DELETE_NFNOTAINFOIDENTIFICACAO = NAMESPACE_NFNOTAINFOIDENTIFICACAO
			+ "deleteNFNotaInfoIdentificacaoById";

	/** The Constant STMT_DELETE_NFNOTAINFOIDENTIFICACAO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOIDENTIFICACAO_ALL = NAMESPACE_NFNOTAINFOIDENTIFICACAO
			+ "deleteAllNFNotaInfoIdentificacaos";

	/** The Constant STMT_FETCH_NFNOTAINFOIDENTIFICACAO. */
	private static final String STMT_FETCH_NFNOTAINFOIDENTIFICACAO = NAMESPACE_NFNOTAINFOIDENTIFICACAO
			+ "fetchNFNotaInfoIdentificacaoById";

	/** The Constant STMT_FETCH_NFNOTAINFOIDENTIFICACAO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOIDENTIFICACAO_ALL = NAMESPACE_NFNOTAINFOIDENTIFICACAO
			+ "fetchAllNFNotaInfoIdentificacaos";

	/** The Constant STMT_FETCH_NFNOTAINFOIDENTIFICACAO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOIDENTIFICACAO_COUNT = NAMESPACE_NFNOTAINFOIDENTIFICACAO
			+ "fetchNFNotaInfoIdentificacaoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOIDENTIFICACAO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOIDENTIFICACAO_ALL_REQUEST = NAMESPACE_NFNOTAINFOIDENTIFICACAO
			+ "fetchAllNFNotaInfoIdentificacaosRequest";

	/// ===================================### NFINFOMODELO1POR1AREFERENCIADA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFINFOMODELO1POR1AREFERENCIADA = "NFInfoModelo1Por1AReferenciadaMap.";

	/** The Constant STMT_INSERT_NFINFOMODELO1POR1AREFERENCIADA. */
	private static final String STMT_INSERT_NFINFOMODELO1POR1AREFERENCIADA = NAMESPACE_NFINFOMODELO1POR1AREFERENCIADA
			+ "insertNFInfoModelo1Por1AReferenciada";

	/** The Constant STMT_UPDATE_NFINFOMODELO1POR1AREFERENCIADA. */
	private static final String STMT_UPDATE_NFINFOMODELO1POR1AREFERENCIADA = NAMESPACE_NFINFOMODELO1POR1AREFERENCIADA
			+ "updateNFInfoModelo1Por1AReferenciada";

	/** The Constant STMT_DELETE_NFINFOMODELO1POR1AREFERENCIADA. */
	private static final String STMT_DELETE_NFINFOMODELO1POR1AREFERENCIADA = NAMESPACE_NFINFOMODELO1POR1AREFERENCIADA
			+ "deleteNFInfoModelo1Por1AReferenciadaById";

	/** The Constant STMT_DELETE_NFINFOMODELO1POR1AREFERENCIADA_ALL. */
	private static final String STMT_DELETE_NFINFOMODELO1POR1AREFERENCIADA_ALL = NAMESPACE_NFINFOMODELO1POR1AREFERENCIADA
			+ "deleteAllNFInfoModelo1Por1AReferenciadas";

	/** The Constant STMT_FETCH_NFINFOMODELO1POR1AREFERENCIADA. */
	private static final String STMT_FETCH_NFINFOMODELO1POR1AREFERENCIADA = NAMESPACE_NFINFOMODELO1POR1AREFERENCIADA
			+ "fetchNFInfoModelo1Por1AReferenciadaById";

	/** The Constant STMT_FETCH_NFINFOMODELO1POR1AREFERENCIADA_ALL. */
	private static final String STMT_FETCH_NFINFOMODELO1POR1AREFERENCIADA_ALL = NAMESPACE_NFINFOMODELO1POR1AREFERENCIADA
			+ "fetchAllNFInfoModelo1Por1AReferenciadas";

	/** The Constant STMT_FETCH_NFINFOMODELO1POR1AREFERENCIADA_COUNT. */
	private static final String STMT_FETCH_NFINFOMODELO1POR1AREFERENCIADA_COUNT = NAMESPACE_NFINFOMODELO1POR1AREFERENCIADA
			+ "fetchNFInfoModelo1Por1AReferenciadaRowCount";

	/** The Constant STMT_FETCH_NFINFOMODELO1POR1AREFERENCIADA_ALL_REQUEST. */
	private static final String STMT_FETCH_NFINFOMODELO1POR1AREFERENCIADA_ALL_REQUEST = NAMESPACE_NFINFOMODELO1POR1AREFERENCIADA
			+ "fetchAllNFInfoModelo1Por1AReferenciadasRequest";

	/// ===================================### NFINFOREFERENCIADA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFINFOREFERENCIADA = "NFInfoReferenciadaMap.";

	/** The Constant STMT_INSERT_NFINFOREFERENCIADA. */
	private static final String STMT_INSERT_NFINFOREFERENCIADA = NAMESPACE_NFINFOREFERENCIADA
			+ "insertNFInfoReferenciada";

	/** The Constant STMT_UPDATE_NFINFOREFERENCIADA. */
	private static final String STMT_UPDATE_NFINFOREFERENCIADA = NAMESPACE_NFINFOREFERENCIADA
			+ "updateNFInfoReferenciada";

	/** The Constant STMT_DELETE_NFINFOREFERENCIADA. */
	private static final String STMT_DELETE_NFINFOREFERENCIADA = NAMESPACE_NFINFOREFERENCIADA
			+ "deleteNFInfoReferenciadaById";

	/** The Constant STMT_DELETE_NFINFOREFERENCIADA_ALL. */
	private static final String STMT_DELETE_NFINFOREFERENCIADA_ALL = NAMESPACE_NFINFOREFERENCIADA
			+ "deleteAllNFInfoReferenciadas";

	/** The Constant STMT_FETCH_NFINFOREFERENCIADA. */
	private static final String STMT_FETCH_NFINFOREFERENCIADA = NAMESPACE_NFINFOREFERENCIADA
			+ "fetchNFInfoReferenciadaById";

	/** The Constant STMT_FETCH_NFINFOREFERENCIADA_ALL. */
	private static final String STMT_FETCH_NFINFOREFERENCIADA_ALL = NAMESPACE_NFINFOREFERENCIADA
			+ "fetchAllNFInfoReferenciadas";

	/** The Constant STMT_FETCH_NFINFOREFERENCIADA_COUNT. */
	private static final String STMT_FETCH_NFINFOREFERENCIADA_COUNT = NAMESPACE_NFINFOREFERENCIADA
			+ "fetchNFInfoReferenciadaRowCount";

	/** The Constant STMT_FETCH_NFINFOREFERENCIADA_ALL_REQUEST. */
	private static final String STMT_FETCH_NFINFOREFERENCIADA_ALL_REQUEST = NAMESPACE_NFINFOREFERENCIADA
			+ "fetchAllNFInfoReferenciadasRequest";

	/// ===================================### NFINFOPRODUTORRURALREFERENCIADA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFINFOPRODUTORRURALREFERENCIADA = "NFInfoProdutorRuralReferenciadaMap.";

	/** The Constant STMT_INSERT_NFINFOPRODUTORRURALREFERENCIADA. */
	private static final String STMT_INSERT_NFINFOPRODUTORRURALREFERENCIADA = NAMESPACE_NFINFOPRODUTORRURALREFERENCIADA
			+ "insertNFInfoProdutorRuralReferenciada";

	/** The Constant STMT_UPDATE_NFINFOPRODUTORRURALREFERENCIADA. */
	private static final String STMT_UPDATE_NFINFOPRODUTORRURALREFERENCIADA = NAMESPACE_NFINFOPRODUTORRURALREFERENCIADA
			+ "updateNFInfoProdutorRuralReferenciada";

	/** The Constant STMT_DELETE_NFINFOPRODUTORRURALREFERENCIADA. */
	private static final String STMT_DELETE_NFINFOPRODUTORRURALREFERENCIADA = NAMESPACE_NFINFOPRODUTORRURALREFERENCIADA
			+ "deleteNFInfoProdutorRuralReferenciadaById";

	/** The Constant STMT_DELETE_NFINFOPRODUTORRURALREFERENCIADA_ALL. */
	private static final String STMT_DELETE_NFINFOPRODUTORRURALREFERENCIADA_ALL = NAMESPACE_NFINFOPRODUTORRURALREFERENCIADA
			+ "deleteAllNFInfoProdutorRuralReferenciadas";

	/** The Constant STMT_FETCH_NFINFOPRODUTORRURALREFERENCIADA. */
	private static final String STMT_FETCH_NFINFOPRODUTORRURALREFERENCIADA = NAMESPACE_NFINFOPRODUTORRURALREFERENCIADA
			+ "fetchNFInfoProdutorRuralReferenciadaById";

	/** The Constant STMT_FETCH_NFINFOPRODUTORRURALREFERENCIADA_ALL. */
	private static final String STMT_FETCH_NFINFOPRODUTORRURALREFERENCIADA_ALL = NAMESPACE_NFINFOPRODUTORRURALREFERENCIADA
			+ "fetchAllNFInfoProdutorRuralReferenciadas";

	/** The Constant STMT_FETCH_NFINFOPRODUTORRURALREFERENCIADA_COUNT. */
	private static final String STMT_FETCH_NFINFOPRODUTORRURALREFERENCIADA_COUNT = NAMESPACE_NFINFOPRODUTORRURALREFERENCIADA
			+ "fetchNFInfoProdutorRuralReferenciadaRowCount";

	/** The Constant STMT_FETCH_NFINFOPRODUTORRURALREFERENCIADA_ALL_REQUEST. */
	private static final String STMT_FETCH_NFINFOPRODUTORRURALREFERENCIADA_ALL_REQUEST = NAMESPACE_NFINFOPRODUTORRURALREFERENCIADA
			+ "fetchAllNFInfoProdutorRuralReferenciadasRequest";

	/// ===================================### NFNOTAINFOEMITENTE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOEMITENTE = "NFNotaInfoEmitenteMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOEMITENTE. */
	private static final String STMT_INSERT_NFNOTAINFOEMITENTE = NAMESPACE_NFNOTAINFOEMITENTE
			+ "insertNFNotaInfoEmitente";

	/** The Constant STMT_UPDATE_NFNOTAINFOEMITENTE. */
	private static final String STMT_UPDATE_NFNOTAINFOEMITENTE = NAMESPACE_NFNOTAINFOEMITENTE
			+ "updateNFNotaInfoEmitente";

	/** The Constant STMT_DELETE_NFNOTAINFOEMITENTE. */
	private static final String STMT_DELETE_NFNOTAINFOEMITENTE = NAMESPACE_NFNOTAINFOEMITENTE
			+ "deleteNFNotaInfoEmitenteById";

	/** The Constant STMT_DELETE_NFNOTAINFOEMITENTE_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOEMITENTE_ALL = NAMESPACE_NFNOTAINFOEMITENTE
			+ "deleteAllNFNotaInfoEmitentes";

	/** The Constant STMT_FETCH_NFNOTAINFOEMITENTE. */
	private static final String STMT_FETCH_NFNOTAINFOEMITENTE = NAMESPACE_NFNOTAINFOEMITENTE
			+ "fetchNFNotaInfoEmitenteById";

	/** The Constant STMT_FETCH_NFNOTAINFOEMITENTE_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOEMITENTE_ALL = NAMESPACE_NFNOTAINFOEMITENTE
			+ "fetchAllNFNotaInfoEmitentes";

	/** The Constant STMT_FETCH_NFNOTAINFOEMITENTE_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOEMITENTE_COUNT = NAMESPACE_NFNOTAINFOEMITENTE
			+ "fetchNFNotaInfoEmitenteRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOEMITENTE_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOEMITENTE_ALL_REQUEST = NAMESPACE_NFNOTAINFOEMITENTE
			+ "fetchAllNFNotaInfoEmitentesRequest";

	/// ===================================### NFNOTAINFOAVULSA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOAVULSA = "NFNotaInfoAvulsaMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOAVULSA. */
	private static final String STMT_INSERT_NFNOTAINFOAVULSA = NAMESPACE_NFNOTAINFOAVULSA + "insertNFNotaInfoAvulsa";

	/** The Constant STMT_UPDATE_NFNOTAINFOAVULSA. */
	private static final String STMT_UPDATE_NFNOTAINFOAVULSA = NAMESPACE_NFNOTAINFOAVULSA + "updateNFNotaInfoAvulsa";

	/** The Constant STMT_DELETE_NFNOTAINFOAVULSA. */
	private static final String STMT_DELETE_NFNOTAINFOAVULSA = NAMESPACE_NFNOTAINFOAVULSA
			+ "deleteNFNotaInfoAvulsaById";

	/** The Constant STMT_DELETE_NFNOTAINFOAVULSA_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOAVULSA_ALL = NAMESPACE_NFNOTAINFOAVULSA
			+ "deleteAllNFNotaInfoAvulsas";

	/** The Constant STMT_FETCH_NFNOTAINFOAVULSA. */
	private static final String STMT_FETCH_NFNOTAINFOAVULSA = NAMESPACE_NFNOTAINFOAVULSA + "fetchNFNotaInfoAvulsaById";

	/** The Constant STMT_FETCH_NFNOTAINFOAVULSA_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOAVULSA_ALL = NAMESPACE_NFNOTAINFOAVULSA
			+ "fetchAllNFNotaInfoAvulsas";

	/** The Constant STMT_FETCH_NFNOTAINFOAVULSA_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOAVULSA_COUNT = NAMESPACE_NFNOTAINFOAVULSA
			+ "fetchNFNotaInfoAvulsaRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOAVULSA_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOAVULSA_ALL_REQUEST = NAMESPACE_NFNOTAINFOAVULSA
			+ "fetchAllNFNotaInfoAvulsasRequest";

	/// ===================================### NFNOTAINFODESTINATARIO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFODESTINATARIO = "NFNotaInfoDestinatarioMap.";

	/** The Constant STMT_INSERT_NFNOTAINFODESTINATARIO. */
	private static final String STMT_INSERT_NFNOTAINFODESTINATARIO = NAMESPACE_NFNOTAINFODESTINATARIO
			+ "insertNFNotaInfoDestinatario";

	/** The Constant STMT_UPDATE_NFNOTAINFODESTINATARIO. */
	private static final String STMT_UPDATE_NFNOTAINFODESTINATARIO = NAMESPACE_NFNOTAINFODESTINATARIO
			+ "updateNFNotaInfoDestinatario";

	/** The Constant STMT_DELETE_NFNOTAINFODESTINATARIO. */
	private static final String STMT_DELETE_NFNOTAINFODESTINATARIO = NAMESPACE_NFNOTAINFODESTINATARIO
			+ "deleteNFNotaInfoDestinatarioById";

	/** The Constant STMT_DELETE_NFNOTAINFODESTINATARIO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFODESTINATARIO_ALL = NAMESPACE_NFNOTAINFODESTINATARIO
			+ "deleteAllNFNotaInfoDestinatarios";

	/** The Constant STMT_FETCH_NFNOTAINFODESTINATARIO. */
	private static final String STMT_FETCH_NFNOTAINFODESTINATARIO = NAMESPACE_NFNOTAINFODESTINATARIO
			+ "fetchNFNotaInfoDestinatarioById";

	/** The Constant STMT_FETCH_NFNOTAINFODESTINATARIO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFODESTINATARIO_ALL = NAMESPACE_NFNOTAINFODESTINATARIO
			+ "fetchAllNFNotaInfoDestinatarios";

	/** The Constant STMT_FETCH_NFNOTAINFODESTINATARIO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFODESTINATARIO_COUNT = NAMESPACE_NFNOTAINFODESTINATARIO
			+ "fetchNFNotaInfoDestinatarioRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFODESTINATARIO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFODESTINATARIO_ALL_REQUEST = NAMESPACE_NFNOTAINFODESTINATARIO
			+ "fetchAllNFNotaInfoDestinatariosRequest";

	/// ===================================### NFNOTAINFOLOCAL
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOLOCAL = "NFNotaInfoLocalMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOLOCAL. */
	private static final String STMT_INSERT_NFNOTAINFOLOCAL = NAMESPACE_NFNOTAINFOLOCAL + "insertNFNotaInfoLocal";

	/** The Constant STMT_UPDATE_NFNOTAINFOLOCAL. */
	private static final String STMT_UPDATE_NFNOTAINFOLOCAL = NAMESPACE_NFNOTAINFOLOCAL + "updateNFNotaInfoLocal";

	/** The Constant STMT_DELETE_NFNOTAINFOLOCAL. */
	private static final String STMT_DELETE_NFNOTAINFOLOCAL = NAMESPACE_NFNOTAINFOLOCAL + "deleteNFNotaInfoLocalById";

	/** The Constant STMT_DELETE_NFNOTAINFOLOCAL_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOLOCAL_ALL = NAMESPACE_NFNOTAINFOLOCAL
			+ "deleteAllNFNotaInfoLocals";

	/** The Constant STMT_FETCH_NFNOTAINFOLOCAL. */
	private static final String STMT_FETCH_NFNOTAINFOLOCAL = NAMESPACE_NFNOTAINFOLOCAL + "fetchNFNotaInfoLocalById";

	/** The Constant STMT_FETCH_NFNOTAINFOLOCAL_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOLOCAL_ALL = NAMESPACE_NFNOTAINFOLOCAL + "fetchAllNFNotaInfoLocals";

	/** The Constant STMT_FETCH_NFNOTAINFOLOCAL_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOLOCAL_COUNT = NAMESPACE_NFNOTAINFOLOCAL
			+ "fetchNFNotaInfoLocalRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOLOCAL_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOLOCAL_ALL_REQUEST = NAMESPACE_NFNOTAINFOLOCAL
			+ "fetchAllNFNotaInfoLocalsRequest";

	/// ===================================### NFPESSOAAUTORIZADADOWNLOADNFE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFPESSOAAUTORIZADADOWNLOADNFE = "NFPessoaAutorizadaDownloadNFeMap.";

	/** The Constant STMT_INSERT_NFPESSOAAUTORIZADADOWNLOADNFE. */
	private static final String STMT_INSERT_NFPESSOAAUTORIZADADOWNLOADNFE = NAMESPACE_NFPESSOAAUTORIZADADOWNLOADNFE
			+ "insertNFPessoaAutorizadaDownloadNFe";

	/** The Constant STMT_UPDATE_NFPESSOAAUTORIZADADOWNLOADNFE. */
	private static final String STMT_UPDATE_NFPESSOAAUTORIZADADOWNLOADNFE = NAMESPACE_NFPESSOAAUTORIZADADOWNLOADNFE
			+ "updateNFPessoaAutorizadaDownloadNFe";

	/** The Constant STMT_DELETE_NFPESSOAAUTORIZADADOWNLOADNFE. */
	private static final String STMT_DELETE_NFPESSOAAUTORIZADADOWNLOADNFE = NAMESPACE_NFPESSOAAUTORIZADADOWNLOADNFE
			+ "deleteNFPessoaAutorizadaDownloadNFeById";

	/** The Constant STMT_DELETE_NFPESSOAAUTORIZADADOWNLOADNFE_ALL. */
	private static final String STMT_DELETE_NFPESSOAAUTORIZADADOWNLOADNFE_ALL = NAMESPACE_NFPESSOAAUTORIZADADOWNLOADNFE
			+ "deleteAllNFPessoaAutorizadaDownloadNFes";

	/** The Constant STMT_FETCH_NFPESSOAAUTORIZADADOWNLOADNFE. */
	private static final String STMT_FETCH_NFPESSOAAUTORIZADADOWNLOADNFE = NAMESPACE_NFPESSOAAUTORIZADADOWNLOADNFE
			+ "fetchNFPessoaAutorizadaDownloadNFeById";

	/** The Constant STMT_FETCH_NFPESSOAAUTORIZADADOWNLOADNFE_ALL. */
	private static final String STMT_FETCH_NFPESSOAAUTORIZADADOWNLOADNFE_ALL = NAMESPACE_NFPESSOAAUTORIZADADOWNLOADNFE
			+ "fetchAllNFPessoaAutorizadaDownloadNFes";

	/** The Constant STMT_FETCH_NFPESSOAAUTORIZADADOWNLOADNFE_COUNT. */
	private static final String STMT_FETCH_NFPESSOAAUTORIZADADOWNLOADNFE_COUNT = NAMESPACE_NFPESSOAAUTORIZADADOWNLOADNFE
			+ "fetchNFPessoaAutorizadaDownloadNFeRowCount";

	/** The Constant STMT_FETCH_NFPESSOAAUTORIZADADOWNLOADNFE_ALL_REQUEST. */
	private static final String STMT_FETCH_NFPESSOAAUTORIZADADOWNLOADNFE_ALL_REQUEST = NAMESPACE_NFPESSOAAUTORIZADADOWNLOADNFE
			+ "fetchAllNFPessoaAutorizadaDownloadNFesRequest";

	/// ===================================### NFNOTAINFOTOTAL
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOTOTAL = "NFNotaInfoTotalMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOTOTAL. */
	private static final String STMT_INSERT_NFNOTAINFOTOTAL = NAMESPACE_NFNOTAINFOTOTAL + "insertNFNotaInfoTotal";

	/** The Constant STMT_UPDATE_NFNOTAINFOTOTAL. */
	private static final String STMT_UPDATE_NFNOTAINFOTOTAL = NAMESPACE_NFNOTAINFOTOTAL + "updateNFNotaInfoTotal";

	/** The Constant STMT_DELETE_NFNOTAINFOTOTAL. */
	private static final String STMT_DELETE_NFNOTAINFOTOTAL = NAMESPACE_NFNOTAINFOTOTAL + "deleteNFNotaInfoTotalById";

	/** The Constant STMT_DELETE_NFNOTAINFOTOTAL_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOTOTAL_ALL = NAMESPACE_NFNOTAINFOTOTAL
			+ "deleteAllNFNotaInfoTotals";

	/** The Constant STMT_FETCH_NFNOTAINFOTOTAL. */
	private static final String STMT_FETCH_NFNOTAINFOTOTAL = NAMESPACE_NFNOTAINFOTOTAL + "fetchNFNotaInfoTotalById";

	/** The Constant STMT_FETCH_NFNOTAINFOTOTAL_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOTOTAL_ALL = NAMESPACE_NFNOTAINFOTOTAL + "fetchAllNFNotaInfoTotals";

	/** The Constant STMT_FETCH_NFNOTAINFOTOTAL_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOTOTAL_COUNT = NAMESPACE_NFNOTAINFOTOTAL
			+ "fetchNFNotaInfoTotalRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOTOTAL_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOTOTAL_ALL_REQUEST = NAMESPACE_NFNOTAINFOTOTAL
			+ "fetchAllNFNotaInfoTotalsRequest";

	/// ===================================### NFNOTAINFOICMSTOTAL
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOICMSTOTAL = "NFNotaInfoICMSTotalMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOICMSTOTAL. */
	private static final String STMT_INSERT_NFNOTAINFOICMSTOTAL = NAMESPACE_NFNOTAINFOICMSTOTAL
			+ "insertNFNotaInfoICMSTotal";

	/** The Constant STMT_UPDATE_NFNOTAINFOICMSTOTAL. */
	private static final String STMT_UPDATE_NFNOTAINFOICMSTOTAL = NAMESPACE_NFNOTAINFOICMSTOTAL
			+ "updateNFNotaInfoICMSTotal";

	/** The Constant STMT_DELETE_NFNOTAINFOICMSTOTAL. */
	private static final String STMT_DELETE_NFNOTAINFOICMSTOTAL = NAMESPACE_NFNOTAINFOICMSTOTAL
			+ "deleteNFNotaInfoICMSTotalById";

	/** The Constant STMT_DELETE_NFNOTAINFOICMSTOTAL_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOICMSTOTAL_ALL = NAMESPACE_NFNOTAINFOICMSTOTAL
			+ "deleteAllNFNotaInfoICMSTotals";

	/** The Constant STMT_FETCH_NFNOTAINFOICMSTOTAL. */
	private static final String STMT_FETCH_NFNOTAINFOICMSTOTAL = NAMESPACE_NFNOTAINFOICMSTOTAL
			+ "fetchNFNotaInfoICMSTotalById";

	/** The Constant STMT_FETCH_NFNOTAINFOICMSTOTAL_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOICMSTOTAL_ALL = NAMESPACE_NFNOTAINFOICMSTOTAL
			+ "fetchAllNFNotaInfoICMSTotals";

	/** The Constant STMT_FETCH_NFNOTAINFOICMSTOTAL_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOICMSTOTAL_COUNT = NAMESPACE_NFNOTAINFOICMSTOTAL
			+ "fetchNFNotaInfoICMSTotalRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOICMSTOTAL_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOICMSTOTAL_ALL_REQUEST = NAMESPACE_NFNOTAINFOICMSTOTAL
			+ "fetchAllNFNotaInfoICMSTotalsRequest";

	/// ===================================### NFNOTAINFOISSQNTOTAL
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOISSQNTOTAL = "NFNotaInfoISSQNTotalMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOISSQNTOTAL. */
	private static final String STMT_INSERT_NFNOTAINFOISSQNTOTAL = NAMESPACE_NFNOTAINFOISSQNTOTAL
			+ "insertNFNotaInfoISSQNTotal";

	/** The Constant STMT_UPDATE_NFNOTAINFOISSQNTOTAL. */
	private static final String STMT_UPDATE_NFNOTAINFOISSQNTOTAL = NAMESPACE_NFNOTAINFOISSQNTOTAL
			+ "updateNFNotaInfoISSQNTotal";

	/** The Constant STMT_DELETE_NFNOTAINFOISSQNTOTAL. */
	private static final String STMT_DELETE_NFNOTAINFOISSQNTOTAL = NAMESPACE_NFNOTAINFOISSQNTOTAL
			+ "deleteNFNotaInfoISSQNTotalById";

	/** The Constant STMT_DELETE_NFNOTAINFOISSQNTOTAL_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOISSQNTOTAL_ALL = NAMESPACE_NFNOTAINFOISSQNTOTAL
			+ "deleteAllNFNotaInfoISSQNTotals";

	/** The Constant STMT_FETCH_NFNOTAINFOISSQNTOTAL. */
	private static final String STMT_FETCH_NFNOTAINFOISSQNTOTAL = NAMESPACE_NFNOTAINFOISSQNTOTAL
			+ "fetchNFNotaInfoISSQNTotalById";

	/** The Constant STMT_FETCH_NFNOTAINFOISSQNTOTAL_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOISSQNTOTAL_ALL = NAMESPACE_NFNOTAINFOISSQNTOTAL
			+ "fetchAllNFNotaInfoISSQNTotals";

	/** The Constant STMT_FETCH_NFNOTAINFOISSQNTOTAL_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOISSQNTOTAL_COUNT = NAMESPACE_NFNOTAINFOISSQNTOTAL
			+ "fetchNFNotaInfoISSQNTotalRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOISSQNTOTAL_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOISSQNTOTAL_ALL_REQUEST = NAMESPACE_NFNOTAINFOISSQNTOTAL
			+ "fetchAllNFNotaInfoISSQNTotalsRequest";

	/// ===================================### NFNOTAINFORETENCOESTRIBUTOS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFORETENCOESTRIBUTOS = "NFNotaInfoRetencoesTributosMap.";

	/** The Constant STMT_INSERT_NFNOTAINFORETENCOESTRIBUTOS. */
	private static final String STMT_INSERT_NFNOTAINFORETENCOESTRIBUTOS = NAMESPACE_NFNOTAINFORETENCOESTRIBUTOS
			+ "insertNFNotaInfoRetencoesTributos";

	/** The Constant STMT_UPDATE_NFNOTAINFORETENCOESTRIBUTOS. */
	private static final String STMT_UPDATE_NFNOTAINFORETENCOESTRIBUTOS = NAMESPACE_NFNOTAINFORETENCOESTRIBUTOS
			+ "updateNFNotaInfoRetencoesTributos";

	/** The Constant STMT_DELETE_NFNOTAINFORETENCOESTRIBUTOS. */
	private static final String STMT_DELETE_NFNOTAINFORETENCOESTRIBUTOS = NAMESPACE_NFNOTAINFORETENCOESTRIBUTOS
			+ "deleteNFNotaInfoRetencoesTributosById";

	/** The Constant STMT_DELETE_NFNOTAINFORETENCOESTRIBUTOS_ALL. */
	private static final String STMT_DELETE_NFNOTAINFORETENCOESTRIBUTOS_ALL = NAMESPACE_NFNOTAINFORETENCOESTRIBUTOS
			+ "deleteAllNFNotaInfoRetencoesTributoss";

	/** The Constant STMT_FETCH_NFNOTAINFORETENCOESTRIBUTOS. */
	private static final String STMT_FETCH_NFNOTAINFORETENCOESTRIBUTOS = NAMESPACE_NFNOTAINFORETENCOESTRIBUTOS
			+ "fetchNFNotaInfoRetencoesTributosById";

	/** The Constant STMT_FETCH_NFNOTAINFORETENCOESTRIBUTOS_ALL. */
	private static final String STMT_FETCH_NFNOTAINFORETENCOESTRIBUTOS_ALL = NAMESPACE_NFNOTAINFORETENCOESTRIBUTOS
			+ "fetchAllNFNotaInfoRetencoesTributoss";

	/** The Constant STMT_FETCH_NFNOTAINFORETENCOESTRIBUTOS_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFORETENCOESTRIBUTOS_COUNT = NAMESPACE_NFNOTAINFORETENCOESTRIBUTOS
			+ "fetchNFNotaInfoRetencoesTributosRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFORETENCOESTRIBUTOS_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFORETENCOESTRIBUTOS_ALL_REQUEST = NAMESPACE_NFNOTAINFORETENCOESTRIBUTOS
			+ "fetchAllNFNotaInfoRetencoesTributossRequest";

	/// ===================================### NFNOTAINFOTRANSPORTE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOTRANSPORTE = "NFNotaInfoTransporteMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOTRANSPORTE. */
	private static final String STMT_INSERT_NFNOTAINFOTRANSPORTE = NAMESPACE_NFNOTAINFOTRANSPORTE
			+ "insertNFNotaInfoTransporte";

	/** The Constant STMT_UPDATE_NFNOTAINFOTRANSPORTE. */
	private static final String STMT_UPDATE_NFNOTAINFOTRANSPORTE = NAMESPACE_NFNOTAINFOTRANSPORTE
			+ "updateNFNotaInfoTransporte";

	/** The Constant STMT_DELETE_NFNOTAINFOTRANSPORTE. */
	private static final String STMT_DELETE_NFNOTAINFOTRANSPORTE = NAMESPACE_NFNOTAINFOTRANSPORTE
			+ "deleteNFNotaInfoTransporteById";

	/** The Constant STMT_DELETE_NFNOTAINFOTRANSPORTE_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOTRANSPORTE_ALL = NAMESPACE_NFNOTAINFOTRANSPORTE
			+ "deleteAllNFNotaInfoTransportes";

	/** The Constant STMT_FETCH_NFNOTAINFOTRANSPORTE. */
	private static final String STMT_FETCH_NFNOTAINFOTRANSPORTE = NAMESPACE_NFNOTAINFOTRANSPORTE
			+ "fetchNFNotaInfoTransporteById";

	/** The Constant STMT_FETCH_NFNOTAINFOTRANSPORTE_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOTRANSPORTE_ALL = NAMESPACE_NFNOTAINFOTRANSPORTE
			+ "fetchAllNFNotaInfoTransportes";

	/** The Constant STMT_FETCH_NFNOTAINFOTRANSPORTE_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOTRANSPORTE_COUNT = NAMESPACE_NFNOTAINFOTRANSPORTE
			+ "fetchNFNotaInfoTransporteRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOTRANSPORTE_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOTRANSPORTE_ALL_REQUEST = NAMESPACE_NFNOTAINFOTRANSPORTE
			+ "fetchAllNFNotaInfoTransportesRequest";

	/// ===================================### NFNOTAINFORETENCAOICMSTRANSPORTE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFORETENCAOICMSTRANSPORTE = "NFNotaInfoRetencaoICMSTransporteMap.";

	/** The Constant STMT_INSERT_NFNOTAINFORETENCAOICMSTRANSPORTE. */
	private static final String STMT_INSERT_NFNOTAINFORETENCAOICMSTRANSPORTE = NAMESPACE_NFNOTAINFORETENCAOICMSTRANSPORTE
			+ "insertNFNotaInfoRetencaoICMSTransporte";

	/** The Constant STMT_UPDATE_NFNOTAINFORETENCAOICMSTRANSPORTE. */
	private static final String STMT_UPDATE_NFNOTAINFORETENCAOICMSTRANSPORTE = NAMESPACE_NFNOTAINFORETENCAOICMSTRANSPORTE
			+ "updateNFNotaInfoRetencaoICMSTransporte";

	/** The Constant STMT_DELETE_NFNOTAINFORETENCAOICMSTRANSPORTE. */
	private static final String STMT_DELETE_NFNOTAINFORETENCAOICMSTRANSPORTE = NAMESPACE_NFNOTAINFORETENCAOICMSTRANSPORTE
			+ "deleteNFNotaInfoRetencaoICMSTransporteById";

	/** The Constant STMT_DELETE_NFNOTAINFORETENCAOICMSTRANSPORTE_ALL. */
	private static final String STMT_DELETE_NFNOTAINFORETENCAOICMSTRANSPORTE_ALL = NAMESPACE_NFNOTAINFORETENCAOICMSTRANSPORTE
			+ "deleteAllNFNotaInfoRetencaoICMSTransportes";

	/** The Constant STMT_FETCH_NFNOTAINFORETENCAOICMSTRANSPORTE. */
	private static final String STMT_FETCH_NFNOTAINFORETENCAOICMSTRANSPORTE = NAMESPACE_NFNOTAINFORETENCAOICMSTRANSPORTE
			+ "fetchNFNotaInfoRetencaoICMSTransporteById";

	/** The Constant STMT_FETCH_NFNOTAINFORETENCAOICMSTRANSPORTE_ALL. */
	private static final String STMT_FETCH_NFNOTAINFORETENCAOICMSTRANSPORTE_ALL = NAMESPACE_NFNOTAINFORETENCAOICMSTRANSPORTE
			+ "fetchAllNFNotaInfoRetencaoICMSTransportes";

	/** The Constant STMT_FETCH_NFNOTAINFORETENCAOICMSTRANSPORTE_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFORETENCAOICMSTRANSPORTE_COUNT = NAMESPACE_NFNOTAINFORETENCAOICMSTRANSPORTE
			+ "fetchNFNotaInfoRetencaoICMSTransporteRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFORETENCAOICMSTRANSPORTE_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFORETENCAOICMSTRANSPORTE_ALL_REQUEST = NAMESPACE_NFNOTAINFORETENCAOICMSTRANSPORTE
			+ "fetchAllNFNotaInfoRetencaoICMSTransportesRequest";

	/// ===================================### NFNOTAINFOTRANSPORTADOR
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOTRANSPORTADOR = "NFNotaInfoTransportadorMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOTRANSPORTADOR. */
	private static final String STMT_INSERT_NFNOTAINFOTRANSPORTADOR = NAMESPACE_NFNOTAINFOTRANSPORTADOR
			+ "insertNFNotaInfoTransportador";

	/** The Constant STMT_UPDATE_NFNOTAINFOTRANSPORTADOR. */
	private static final String STMT_UPDATE_NFNOTAINFOTRANSPORTADOR = NAMESPACE_NFNOTAINFOTRANSPORTADOR
			+ "updateNFNotaInfoTransportador";

	/** The Constant STMT_DELETE_NFNOTAINFOTRANSPORTADOR. */
	private static final String STMT_DELETE_NFNOTAINFOTRANSPORTADOR = NAMESPACE_NFNOTAINFOTRANSPORTADOR
			+ "deleteNFNotaInfoTransportadorById";

	/** The Constant STMT_DELETE_NFNOTAINFOTRANSPORTADOR_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOTRANSPORTADOR_ALL = NAMESPACE_NFNOTAINFOTRANSPORTADOR
			+ "deleteAllNFNotaInfoTransportadors";

	/** The Constant STMT_FETCH_NFNOTAINFOTRANSPORTADOR. */
	private static final String STMT_FETCH_NFNOTAINFOTRANSPORTADOR = NAMESPACE_NFNOTAINFOTRANSPORTADOR
			+ "fetchNFNotaInfoTransportadorById";

	/** The Constant STMT_FETCH_NFNOTAINFOTRANSPORTADOR_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOTRANSPORTADOR_ALL = NAMESPACE_NFNOTAINFOTRANSPORTADOR
			+ "fetchAllNFNotaInfoTransportadors";

	/** The Constant STMT_FETCH_NFNOTAINFOTRANSPORTADOR_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOTRANSPORTADOR_COUNT = NAMESPACE_NFNOTAINFOTRANSPORTADOR
			+ "fetchNFNotaInfoTransportadorRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOTRANSPORTADOR_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOTRANSPORTADOR_ALL_REQUEST = NAMESPACE_NFNOTAINFOTRANSPORTADOR
			+ "fetchAllNFNotaInfoTransportadorsRequest";

	/// ===================================### NFNOTAINFOVEICULO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOVEICULO = "NFNotaInfoVeiculoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOVEICULO. */
	private static final String STMT_INSERT_NFNOTAINFOVEICULO = NAMESPACE_NFNOTAINFOVEICULO + "insertNFNotaInfoVeiculo";

	/** The Constant STMT_UPDATE_NFNOTAINFOVEICULO. */
	private static final String STMT_UPDATE_NFNOTAINFOVEICULO = NAMESPACE_NFNOTAINFOVEICULO + "updateNFNotaInfoVeiculo";

	/** The Constant STMT_DELETE_NFNOTAINFOVEICULO. */
	private static final String STMT_DELETE_NFNOTAINFOVEICULO = NAMESPACE_NFNOTAINFOVEICULO
			+ "deleteNFNotaInfoVeiculoById";

	/** The Constant STMT_DELETE_NFNOTAINFOVEICULO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOVEICULO_ALL = NAMESPACE_NFNOTAINFOVEICULO
			+ "deleteAllNFNotaInfoVeiculos";

	/** The Constant STMT_FETCH_NFNOTAINFOVEICULO. */
	private static final String STMT_FETCH_NFNOTAINFOVEICULO = NAMESPACE_NFNOTAINFOVEICULO
			+ "fetchNFNotaInfoVeiculoById";

	/** The Constant STMT_FETCH_NFNOTAINFOVEICULO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOVEICULO_ALL = NAMESPACE_NFNOTAINFOVEICULO
			+ "fetchAllNFNotaInfoVeiculos";

	/** The Constant STMT_FETCH_NFNOTAINFOVEICULO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOVEICULO_COUNT = NAMESPACE_NFNOTAINFOVEICULO
			+ "fetchNFNotaInfoVeiculoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOVEICULO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOVEICULO_ALL_REQUEST = NAMESPACE_NFNOTAINFOVEICULO
			+ "fetchAllNFNotaInfoVeiculosRequest";

	/// ===================================### NFNOTAINFOREBOQUE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOREBOQUE = "NFNotaInfoReboqueMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOREBOQUE. */
	private static final String STMT_INSERT_NFNOTAINFOREBOQUE = NAMESPACE_NFNOTAINFOREBOQUE + "insertNFNotaInfoReboque";

	/** The Constant STMT_UPDATE_NFNOTAINFOREBOQUE. */
	private static final String STMT_UPDATE_NFNOTAINFOREBOQUE = NAMESPACE_NFNOTAINFOREBOQUE + "updateNFNotaInfoReboque";

	/** The Constant STMT_DELETE_NFNOTAINFOREBOQUE. */
	private static final String STMT_DELETE_NFNOTAINFOREBOQUE = NAMESPACE_NFNOTAINFOREBOQUE
			+ "deleteNFNotaInfoReboqueById";

	/** The Constant STMT_DELETE_NFNOTAINFOREBOQUE_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOREBOQUE_ALL = NAMESPACE_NFNOTAINFOREBOQUE
			+ "deleteAllNFNotaInfoReboques";

	/** The Constant STMT_FETCH_NFNOTAINFOREBOQUE. */
	private static final String STMT_FETCH_NFNOTAINFOREBOQUE = NAMESPACE_NFNOTAINFOREBOQUE
			+ "fetchNFNotaInfoReboqueById";

	/** The Constant STMT_FETCH_NFNOTAINFOREBOQUE_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOREBOQUE_ALL = NAMESPACE_NFNOTAINFOREBOQUE
			+ "fetchAllNFNotaInfoReboques";

	/** The Constant STMT_FETCH_NFNOTAINFOREBOQUE_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOREBOQUE_COUNT = NAMESPACE_NFNOTAINFOREBOQUE
			+ "fetchNFNotaInfoReboqueRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOREBOQUE_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOREBOQUE_ALL_REQUEST = NAMESPACE_NFNOTAINFOREBOQUE
			+ "fetchAllNFNotaInfoReboquesRequest";

	/// ===================================### NFNOTAINFOCOBRANCA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOCOBRANCA = "NFNotaInfoCobrancaMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOCOBRANCA. */
	private static final String STMT_INSERT_NFNOTAINFOCOBRANCA = NAMESPACE_NFNOTAINFOCOBRANCA
			+ "insertNFNotaInfoCobranca";

	/** The Constant STMT_UPDATE_NFNOTAINFOCOBRANCA. */
	private static final String STMT_UPDATE_NFNOTAINFOCOBRANCA = NAMESPACE_NFNOTAINFOCOBRANCA
			+ "updateNFNotaInfoCobranca";

	/** The Constant STMT_DELETE_NFNOTAINFOCOBRANCA. */
	private static final String STMT_DELETE_NFNOTAINFOCOBRANCA = NAMESPACE_NFNOTAINFOCOBRANCA
			+ "deleteNFNotaInfoCobrancaById";

	/** The Constant STMT_DELETE_NFNOTAINFOCOBRANCA_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOCOBRANCA_ALL = NAMESPACE_NFNOTAINFOCOBRANCA
			+ "deleteAllNFNotaInfoCobrancas";

	/** The Constant STMT_FETCH_NFNOTAINFOCOBRANCA. */
	private static final String STMT_FETCH_NFNOTAINFOCOBRANCA = NAMESPACE_NFNOTAINFOCOBRANCA
			+ "fetchNFNotaInfoCobrancaById";

	/** The Constant STMT_FETCH_NFNOTAINFOCOBRANCA_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOCOBRANCA_ALL = NAMESPACE_NFNOTAINFOCOBRANCA
			+ "fetchAllNFNotaInfoCobrancas";

	/** The Constant STMT_FETCH_NFNOTAINFOCOBRANCA_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOCOBRANCA_COUNT = NAMESPACE_NFNOTAINFOCOBRANCA
			+ "fetchNFNotaInfoCobrancaRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOCOBRANCA_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOCOBRANCA_ALL_REQUEST = NAMESPACE_NFNOTAINFOCOBRANCA
			+ "fetchAllNFNotaInfoCobrancasRequest";

	/// ===================================### NFNOTAINFODUPLICATA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFODUPLICATA = "NFNotaInfoDuplicataMap.";

	/** The Constant STMT_INSERT_NFNOTAINFODUPLICATA. */
	private static final String STMT_INSERT_NFNOTAINFODUPLICATA = NAMESPACE_NFNOTAINFODUPLICATA
			+ "insertNFNotaInfoDuplicata";

	/** The Constant STMT_UPDATE_NFNOTAINFODUPLICATA. */
	private static final String STMT_UPDATE_NFNOTAINFODUPLICATA = NAMESPACE_NFNOTAINFODUPLICATA
			+ "updateNFNotaInfoDuplicata";

	/** The Constant STMT_DELETE_NFNOTAINFODUPLICATA. */
	private static final String STMT_DELETE_NFNOTAINFODUPLICATA = NAMESPACE_NFNOTAINFODUPLICATA
			+ "deleteNFNotaInfoDuplicataById";

	/** The Constant STMT_DELETE_NFNOTAINFODUPLICATA_ALL. */
	private static final String STMT_DELETE_NFNOTAINFODUPLICATA_ALL = NAMESPACE_NFNOTAINFODUPLICATA
			+ "deleteAllNFNotaInfoDuplicatas";

	/** The Constant STMT_FETCH_NFNOTAINFODUPLICATA. */
	private static final String STMT_FETCH_NFNOTAINFODUPLICATA = NAMESPACE_NFNOTAINFODUPLICATA
			+ "fetchNFNotaInfoDuplicataById";

	/** The Constant STMT_FETCH_NFNOTAINFODUPLICATA_ALL. */
	private static final String STMT_FETCH_NFNOTAINFODUPLICATA_ALL = NAMESPACE_NFNOTAINFODUPLICATA
			+ "fetchAllNFNotaInfoDuplicatas";

	/** The Constant STMT_FETCH_NFNOTAINFODUPLICATA_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFODUPLICATA_COUNT = NAMESPACE_NFNOTAINFODUPLICATA
			+ "fetchNFNotaInfoDuplicataRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFODUPLICATA_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFODUPLICATA_ALL_REQUEST = NAMESPACE_NFNOTAINFODUPLICATA
			+ "fetchAllNFNotaInfoDuplicatasRequest";

	/// ===================================### NFNOTAINFOFATURA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOFATURA = "NFNotaInfoFaturaMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOFATURA. */
	private static final String STMT_INSERT_NFNOTAINFOFATURA = NAMESPACE_NFNOTAINFOFATURA + "insertNFNotaInfoFatura";

	/** The Constant STMT_UPDATE_NFNOTAINFOFATURA. */
	private static final String STMT_UPDATE_NFNOTAINFOFATURA = NAMESPACE_NFNOTAINFOFATURA + "updateNFNotaInfoFatura";

	/** The Constant STMT_DELETE_NFNOTAINFOFATURA. */
	private static final String STMT_DELETE_NFNOTAINFOFATURA = NAMESPACE_NFNOTAINFOFATURA
			+ "deleteNFNotaInfoFaturaById";

	/** The Constant STMT_DELETE_NFNOTAINFOFATURA_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOFATURA_ALL = NAMESPACE_NFNOTAINFOFATURA
			+ "deleteAllNFNotaInfoFaturas";

	/** The Constant STMT_FETCH_NFNOTAINFOFATURA. */
	private static final String STMT_FETCH_NFNOTAINFOFATURA = NAMESPACE_NFNOTAINFOFATURA + "fetchNFNotaInfoFaturaById";

	/** The Constant STMT_FETCH_NFNOTAINFOFATURA_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOFATURA_ALL = NAMESPACE_NFNOTAINFOFATURA
			+ "fetchAllNFNotaInfoFaturas";

	/** The Constant STMT_FETCH_NFNOTAINFOFATURA_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOFATURA_COUNT = NAMESPACE_NFNOTAINFOFATURA
			+ "fetchNFNotaInfoFaturaRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOFATURA_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOFATURA_ALL_REQUEST = NAMESPACE_NFNOTAINFOFATURA
			+ "fetchAllNFNotaInfoFaturasRequest";

	/// ===================================### NFNOTAINFOCARTAO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOCARTAO = "NFNotaInfoCartaoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOCARTAO. */
	private static final String STMT_INSERT_NFNOTAINFOCARTAO = NAMESPACE_NFNOTAINFOCARTAO + "insertNFNotaInfoCartao";

	/** The Constant STMT_UPDATE_NFNOTAINFOCARTAO. */
	private static final String STMT_UPDATE_NFNOTAINFOCARTAO = NAMESPACE_NFNOTAINFOCARTAO + "updateNFNotaInfoCartao";

	/** The Constant STMT_DELETE_NFNOTAINFOCARTAO. */
	private static final String STMT_DELETE_NFNOTAINFOCARTAO = NAMESPACE_NFNOTAINFOCARTAO
			+ "deleteNFNotaInfoCartaoById";

	/** The Constant STMT_DELETE_NFNOTAINFOCARTAO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOCARTAO_ALL = NAMESPACE_NFNOTAINFOCARTAO
			+ "deleteAllNFNotaInfoCartaos";

	/** The Constant STMT_FETCH_NFNOTAINFOCARTAO. */
	private static final String STMT_FETCH_NFNOTAINFOCARTAO = NAMESPACE_NFNOTAINFOCARTAO + "fetchNFNotaInfoCartaoById";

	/** The Constant STMT_FETCH_NFNOTAINFOCARTAO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOCARTAO_ALL = NAMESPACE_NFNOTAINFOCARTAO
			+ "fetchAllNFNotaInfoCartaos";

	/** The Constant STMT_FETCH_NFNOTAINFOCARTAO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOCARTAO_COUNT = NAMESPACE_NFNOTAINFOCARTAO
			+ "fetchNFNotaInfoCartaoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOCARTAO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOCARTAO_ALL_REQUEST = NAMESPACE_NFNOTAINFOCARTAO
			+ "fetchAllNFNotaInfoCartaosRequest";

	/// ===================================### NFNOTAINFOPAGAMENTO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOPAGAMENTO = "NFNotaInfoPagamentoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOPAGAMENTO. */
	private static final String STMT_INSERT_NFNOTAINFOPAGAMENTO = NAMESPACE_NFNOTAINFOPAGAMENTO
			+ "insertNFNotaInfoPagamento";

	/** The Constant STMT_UPDATE_NFNOTAINFOPAGAMENTO. */
	private static final String STMT_UPDATE_NFNOTAINFOPAGAMENTO = NAMESPACE_NFNOTAINFOPAGAMENTO
			+ "updateNFNotaInfoPagamento";

	/** The Constant STMT_DELETE_NFNOTAINFOPAGAMENTO. */
	private static final String STMT_DELETE_NFNOTAINFOPAGAMENTO = NAMESPACE_NFNOTAINFOPAGAMENTO
			+ "deleteNFNotaInfoPagamentoById";

	/** The Constant STMT_DELETE_NFNOTAINFOPAGAMENTO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOPAGAMENTO_ALL = NAMESPACE_NFNOTAINFOPAGAMENTO
			+ "deleteAllNFNotaInfoPagamentos";

	/** The Constant STMT_FETCH_NFNOTAINFOPAGAMENTO. */
	private static final String STMT_FETCH_NFNOTAINFOPAGAMENTO = NAMESPACE_NFNOTAINFOPAGAMENTO
			+ "fetchNFNotaInfoPagamentoById";

	/** The Constant STMT_FETCH_NFNOTAINFOPAGAMENTO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOPAGAMENTO_ALL = NAMESPACE_NFNOTAINFOPAGAMENTO
			+ "fetchAllNFNotaInfoPagamentos";

	/** The Constant STMT_FETCH_NFNOTAINFOPAGAMENTO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOPAGAMENTO_COUNT = NAMESPACE_NFNOTAINFOPAGAMENTO
			+ "fetchNFNotaInfoPagamentoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOPAGAMENTO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOPAGAMENTO_ALL_REQUEST = NAMESPACE_NFNOTAINFOPAGAMENTO
			+ "fetchAllNFNotaInfoPagamentosRequest";

	/// ===================================### NFNOTAINFOINFORMACOESADICIONAIS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOINFORMACOESADICIONAIS = "NFNotaInfoInformacoesAdicionaisMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOINFORMACOESADICIONAIS. */
	private static final String STMT_INSERT_NFNOTAINFOINFORMACOESADICIONAIS = NAMESPACE_NFNOTAINFOINFORMACOESADICIONAIS
			+ "insertNFNotaInfoInformacoesAdicionais";

	/** The Constant STMT_UPDATE_NFNOTAINFOINFORMACOESADICIONAIS. */
	private static final String STMT_UPDATE_NFNOTAINFOINFORMACOESADICIONAIS = NAMESPACE_NFNOTAINFOINFORMACOESADICIONAIS
			+ "updateNFNotaInfoInformacoesAdicionais";

	/** The Constant STMT_DELETE_NFNOTAINFOINFORMACOESADICIONAIS. */
	private static final String STMT_DELETE_NFNOTAINFOINFORMACOESADICIONAIS = NAMESPACE_NFNOTAINFOINFORMACOESADICIONAIS
			+ "deleteNFNotaInfoInformacoesAdicionaisById";

	/** The Constant STMT_DELETE_NFNOTAINFOINFORMACOESADICIONAIS_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOINFORMACOESADICIONAIS_ALL = NAMESPACE_NFNOTAINFOINFORMACOESADICIONAIS
			+ "deleteAllNFNotaInfoInformacoesAdicionaiss";

	/** The Constant STMT_FETCH_NFNOTAINFOINFORMACOESADICIONAIS. */
	private static final String STMT_FETCH_NFNOTAINFOINFORMACOESADICIONAIS = NAMESPACE_NFNOTAINFOINFORMACOESADICIONAIS
			+ "fetchNFNotaInfoInformacoesAdicionaisById";

	/** The Constant STMT_FETCH_NFNOTAINFOINFORMACOESADICIONAIS_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOINFORMACOESADICIONAIS_ALL = NAMESPACE_NFNOTAINFOINFORMACOESADICIONAIS
			+ "fetchAllNFNotaInfoInformacoesAdicionaiss";

	/** The Constant STMT_FETCH_NFNOTAINFOINFORMACOESADICIONAIS_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOINFORMACOESADICIONAIS_COUNT = NAMESPACE_NFNOTAINFOINFORMACOESADICIONAIS
			+ "fetchNFNotaInfoInformacoesAdicionaisRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOINFORMACOESADICIONAIS_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOINFORMACOESADICIONAIS_ALL_REQUEST = NAMESPACE_NFNOTAINFOINFORMACOESADICIONAIS
			+ "fetchAllNFNotaInfoInformacoesAdicionaissRequest";

	/// ===================================### NFNOTAINFOOBSERVACAO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOOBSERVACAO = "NFNotaInfoObservacaoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOOBSERVACAO. */
	private static final String STMT_INSERT_NFNOTAINFOOBSERVACAO = NAMESPACE_NFNOTAINFOOBSERVACAO
			+ "insertNFNotaInfoObservacao";

	/** The Constant STMT_UPDATE_NFNOTAINFOOBSERVACAO. */
	private static final String STMT_UPDATE_NFNOTAINFOOBSERVACAO = NAMESPACE_NFNOTAINFOOBSERVACAO
			+ "updateNFNotaInfoObservacao";

	/** The Constant STMT_DELETE_NFNOTAINFOOBSERVACAO. */
	private static final String STMT_DELETE_NFNOTAINFOOBSERVACAO = NAMESPACE_NFNOTAINFOOBSERVACAO
			+ "deleteNFNotaInfoObservacaoById";

	/** The Constant STMT_DELETE_NFNOTAINFOOBSERVACAO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOOBSERVACAO_ALL = NAMESPACE_NFNOTAINFOOBSERVACAO
			+ "deleteAllNFNotaInfoObservacaos";

	/** The Constant STMT_FETCH_NFNOTAINFOOBSERVACAO. */
	private static final String STMT_FETCH_NFNOTAINFOOBSERVACAO = NAMESPACE_NFNOTAINFOOBSERVACAO
			+ "fetchNFNotaInfoObservacaoById";

	/** The Constant STMT_FETCH_NFNOTAINFOOBSERVACAO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOOBSERVACAO_ALL = NAMESPACE_NFNOTAINFOOBSERVACAO
			+ "fetchAllNFNotaInfoObservacaos";

	/** The Constant STMT_FETCH_NFNOTAINFOOBSERVACAO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOOBSERVACAO_COUNT = NAMESPACE_NFNOTAINFOOBSERVACAO
			+ "fetchNFNotaInfoObservacaoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOOBSERVACAO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOOBSERVACAO_ALL_REQUEST = NAMESPACE_NFNOTAINFOOBSERVACAO
			+ "fetchAllNFNotaInfoObservacaosRequest";

	/// ===================================### NFNOTAINFOPROCESSOREFERENCIADO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOPROCESSOREFERENCIADO = "NFNotaInfoProcessoReferenciadoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOPROCESSOREFERENCIADO. */
	private static final String STMT_INSERT_NFNOTAINFOPROCESSOREFERENCIADO = NAMESPACE_NFNOTAINFOPROCESSOREFERENCIADO
			+ "insertNFNotaInfoProcessoReferenciado";

	/** The Constant STMT_UPDATE_NFNOTAINFOPROCESSOREFERENCIADO. */
	private static final String STMT_UPDATE_NFNOTAINFOPROCESSOREFERENCIADO = NAMESPACE_NFNOTAINFOPROCESSOREFERENCIADO
			+ "updateNFNotaInfoProcessoReferenciado";

	/** The Constant STMT_DELETE_NFNOTAINFOPROCESSOREFERENCIADO. */
	private static final String STMT_DELETE_NFNOTAINFOPROCESSOREFERENCIADO = NAMESPACE_NFNOTAINFOPROCESSOREFERENCIADO
			+ "deleteNFNotaInfoProcessoReferenciadoById";

	/** The Constant STMT_DELETE_NFNOTAINFOPROCESSOREFERENCIADO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOPROCESSOREFERENCIADO_ALL = NAMESPACE_NFNOTAINFOPROCESSOREFERENCIADO
			+ "deleteAllNFNotaInfoProcessoReferenciados";

	/** The Constant STMT_FETCH_NFNOTAINFOPROCESSOREFERENCIADO. */
	private static final String STMT_FETCH_NFNOTAINFOPROCESSOREFERENCIADO = NAMESPACE_NFNOTAINFOPROCESSOREFERENCIADO
			+ "fetchNFNotaInfoProcessoReferenciadoById";

	/** The Constant STMT_FETCH_NFNOTAINFOPROCESSOREFERENCIADO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOPROCESSOREFERENCIADO_ALL = NAMESPACE_NFNOTAINFOPROCESSOREFERENCIADO
			+ "fetchAllNFNotaInfoProcessoReferenciados";

	/** The Constant STMT_FETCH_NFNOTAINFOPROCESSOREFERENCIADO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOPROCESSOREFERENCIADO_COUNT = NAMESPACE_NFNOTAINFOPROCESSOREFERENCIADO
			+ "fetchNFNotaInfoProcessoReferenciadoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOPROCESSOREFERENCIADO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOPROCESSOREFERENCIADO_ALL_REQUEST = NAMESPACE_NFNOTAINFOPROCESSOREFERENCIADO
			+ "fetchAllNFNotaInfoProcessoReferenciadosRequest";

	/// ===================================### NFNOTAINFOEXPORTACAO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOEXPORTACAO = "NFNotaInfoExportacaoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOEXPORTACAO. */
	private static final String STMT_INSERT_NFNOTAINFOEXPORTACAO = NAMESPACE_NFNOTAINFOEXPORTACAO
			+ "insertNFNotaInfoExportacao";

	/** The Constant STMT_UPDATE_NFNOTAINFOEXPORTACAO. */
	private static final String STMT_UPDATE_NFNOTAINFOEXPORTACAO = NAMESPACE_NFNOTAINFOEXPORTACAO
			+ "updateNFNotaInfoExportacao";

	/** The Constant STMT_DELETE_NFNOTAINFOEXPORTACAO. */
	private static final String STMT_DELETE_NFNOTAINFOEXPORTACAO = NAMESPACE_NFNOTAINFOEXPORTACAO
			+ "deleteNFNotaInfoExportacaoById";

	/** The Constant STMT_DELETE_NFNOTAINFOEXPORTACAO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOEXPORTACAO_ALL = NAMESPACE_NFNOTAINFOEXPORTACAO
			+ "deleteAllNFNotaInfoExportacaos";

	/** The Constant STMT_FETCH_NFNOTAINFOEXPORTACAO. */
	private static final String STMT_FETCH_NFNOTAINFOEXPORTACAO = NAMESPACE_NFNOTAINFOEXPORTACAO
			+ "fetchNFNotaInfoExportacaoById";

	/** The Constant STMT_FETCH_NFNOTAINFOEXPORTACAO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOEXPORTACAO_ALL = NAMESPACE_NFNOTAINFOEXPORTACAO
			+ "fetchAllNFNotaInfoExportacaos";

	/** The Constant STMT_FETCH_NFNOTAINFOEXPORTACAO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOEXPORTACAO_COUNT = NAMESPACE_NFNOTAINFOEXPORTACAO
			+ "fetchNFNotaInfoExportacaoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOEXPORTACAO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOEXPORTACAO_ALL_REQUEST = NAMESPACE_NFNOTAINFOEXPORTACAO
			+ "fetchAllNFNotaInfoExportacaosRequest";

	/// ===================================### NFNOTAINFOCOMPRA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOCOMPRA = "NFNotaInfoCompraMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOCOMPRA. */
	private static final String STMT_INSERT_NFNOTAINFOCOMPRA = NAMESPACE_NFNOTAINFOCOMPRA + "insertNFNotaInfoCompra";

	/** The Constant STMT_UPDATE_NFNOTAINFOCOMPRA. */
	private static final String STMT_UPDATE_NFNOTAINFOCOMPRA = NAMESPACE_NFNOTAINFOCOMPRA + "updateNFNotaInfoCompra";

	/** The Constant STMT_DELETE_NFNOTAINFOCOMPRA. */
	private static final String STMT_DELETE_NFNOTAINFOCOMPRA = NAMESPACE_NFNOTAINFOCOMPRA
			+ "deleteNFNotaInfoCompraById";

	/** The Constant STMT_DELETE_NFNOTAINFOCOMPRA_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOCOMPRA_ALL = NAMESPACE_NFNOTAINFOCOMPRA
			+ "deleteAllNFNotaInfoCompras";

	/** The Constant STMT_FETCH_NFNOTAINFOCOMPRA. */
	private static final String STMT_FETCH_NFNOTAINFOCOMPRA = NAMESPACE_NFNOTAINFOCOMPRA + "fetchNFNotaInfoCompraById";

	/** The Constant STMT_FETCH_NFNOTAINFOCOMPRA_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOCOMPRA_ALL = NAMESPACE_NFNOTAINFOCOMPRA
			+ "fetchAllNFNotaInfoCompras";

	/** The Constant STMT_FETCH_NFNOTAINFOCOMPRA_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOCOMPRA_COUNT = NAMESPACE_NFNOTAINFOCOMPRA
			+ "fetchNFNotaInfoCompraRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOCOMPRA_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOCOMPRA_ALL_REQUEST = NAMESPACE_NFNOTAINFOCOMPRA
			+ "fetchAllNFNotaInfoComprasRequest";

	/// ===================================### NFNOTAINFOCANA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOCANA = "NFNotaInfoCanaMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOCANA. */
	private static final String STMT_INSERT_NFNOTAINFOCANA = NAMESPACE_NFNOTAINFOCANA + "insertNFNotaInfoCana";

	/** The Constant STMT_UPDATE_NFNOTAINFOCANA. */
	private static final String STMT_UPDATE_NFNOTAINFOCANA = NAMESPACE_NFNOTAINFOCANA + "updateNFNotaInfoCana";

	/** The Constant STMT_DELETE_NFNOTAINFOCANA. */
	private static final String STMT_DELETE_NFNOTAINFOCANA = NAMESPACE_NFNOTAINFOCANA + "deleteNFNotaInfoCanaById";

	/** The Constant STMT_DELETE_NFNOTAINFOCANA_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOCANA_ALL = NAMESPACE_NFNOTAINFOCANA + "deleteAllNFNotaInfoCanas";

	/** The Constant STMT_FETCH_NFNOTAINFOCANA. */
	private static final String STMT_FETCH_NFNOTAINFOCANA = NAMESPACE_NFNOTAINFOCANA + "fetchNFNotaInfoCanaById";

	/** The Constant STMT_FETCH_NFNOTAINFOCANA_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOCANA_ALL = NAMESPACE_NFNOTAINFOCANA + "fetchAllNFNotaInfoCanas";

	/** The Constant STMT_FETCH_NFNOTAINFOCANA_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOCANA_COUNT = NAMESPACE_NFNOTAINFOCANA
			+ "fetchNFNotaInfoCanaRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOCANA_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOCANA_ALL_REQUEST = NAMESPACE_NFNOTAINFOCANA
			+ "fetchAllNFNotaInfoCanasRequest";

	/// ===================================### NFNOTAINFOCANAFORNECIMENTODIARIO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOCANAFORNECIMENTODIARIO = "NFNotaInfoCanaFornecimentoDiarioMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOCANAFORNECIMENTODIARIO. */
	private static final String STMT_INSERT_NFNOTAINFOCANAFORNECIMENTODIARIO = NAMESPACE_NFNOTAINFOCANAFORNECIMENTODIARIO
			+ "insertNFNotaInfoCanaFornecimentoDiario";

	/** The Constant STMT_UPDATE_NFNOTAINFOCANAFORNECIMENTODIARIO. */
	private static final String STMT_UPDATE_NFNOTAINFOCANAFORNECIMENTODIARIO = NAMESPACE_NFNOTAINFOCANAFORNECIMENTODIARIO
			+ "updateNFNotaInfoCanaFornecimentoDiario";

	/** The Constant STMT_DELETE_NFNOTAINFOCANAFORNECIMENTODIARIO. */
	private static final String STMT_DELETE_NFNOTAINFOCANAFORNECIMENTODIARIO = NAMESPACE_NFNOTAINFOCANAFORNECIMENTODIARIO
			+ "deleteNFNotaInfoCanaFornecimentoDiarioById";

	/** The Constant STMT_DELETE_NFNOTAINFOCANAFORNECIMENTODIARIO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOCANAFORNECIMENTODIARIO_ALL = NAMESPACE_NFNOTAINFOCANAFORNECIMENTODIARIO
			+ "deleteAllNFNotaInfoCanaFornecimentoDiarios";

	/** The Constant STMT_FETCH_NFNOTAINFOCANAFORNECIMENTODIARIO. */
	private static final String STMT_FETCH_NFNOTAINFOCANAFORNECIMENTODIARIO = NAMESPACE_NFNOTAINFOCANAFORNECIMENTODIARIO
			+ "fetchNFNotaInfoCanaFornecimentoDiarioById";

	/** The Constant STMT_FETCH_NFNOTAINFOCANAFORNECIMENTODIARIO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOCANAFORNECIMENTODIARIO_ALL = NAMESPACE_NFNOTAINFOCANAFORNECIMENTODIARIO
			+ "fetchAllNFNotaInfoCanaFornecimentoDiarios";

	/** The Constant STMT_FETCH_NFNOTAINFOCANAFORNECIMENTODIARIO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOCANAFORNECIMENTODIARIO_COUNT = NAMESPACE_NFNOTAINFOCANAFORNECIMENTODIARIO
			+ "fetchNFNotaInfoCanaFornecimentoDiarioRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOCANAFORNECIMENTODIARIO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOCANAFORNECIMENTODIARIO_ALL_REQUEST = NAMESPACE_NFNOTAINFOCANAFORNECIMENTODIARIO
			+ "fetchAllNFNotaInfoCanaFornecimentoDiariosRequest";

	/// ===================================### NFNOTAINFOCANADEDUCAO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOCANADEDUCAO = "NFNotaInfoCanaDeducaoMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOCANADEDUCAO. */
	private static final String STMT_INSERT_NFNOTAINFOCANADEDUCAO = NAMESPACE_NFNOTAINFOCANADEDUCAO
			+ "insertNFNotaInfoCanaDeducao";

	/** The Constant STMT_UPDATE_NFNOTAINFOCANADEDUCAO. */
	private static final String STMT_UPDATE_NFNOTAINFOCANADEDUCAO = NAMESPACE_NFNOTAINFOCANADEDUCAO
			+ "updateNFNotaInfoCanaDeducao";

	/** The Constant STMT_DELETE_NFNOTAINFOCANADEDUCAO. */
	private static final String STMT_DELETE_NFNOTAINFOCANADEDUCAO = NAMESPACE_NFNOTAINFOCANADEDUCAO
			+ "deleteNFNotaInfoCanaDeducaoById";

	/** The Constant STMT_DELETE_NFNOTAINFOCANADEDUCAO_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOCANADEDUCAO_ALL = NAMESPACE_NFNOTAINFOCANADEDUCAO
			+ "deleteAllNFNotaInfoCanaDeducaos";

	/** The Constant STMT_FETCH_NFNOTAINFOCANADEDUCAO. */
	private static final String STMT_FETCH_NFNOTAINFOCANADEDUCAO = NAMESPACE_NFNOTAINFOCANADEDUCAO
			+ "fetchNFNotaInfoCanaDeducaoById";

	/** The Constant STMT_FETCH_NFNOTAINFOCANADEDUCAO_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOCANADEDUCAO_ALL = NAMESPACE_NFNOTAINFOCANADEDUCAO
			+ "fetchAllNFNotaInfoCanaDeducaos";

	/** The Constant STMT_FETCH_NFNOTAINFOCANADEDUCAO_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOCANADEDUCAO_COUNT = NAMESPACE_NFNOTAINFOCANADEDUCAO
			+ "fetchNFNotaInfoCanaDeducaoRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOCANADEDUCAO_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOCANADEDUCAO_ALL_REQUEST = NAMESPACE_NFNOTAINFOCANADEDUCAO
			+ "fetchAllNFNotaInfoCanaDeducaosRequest";

	/// ===================================### NFNOTAINFOSUPLEMENTAR
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_NFNOTAINFOSUPLEMENTAR = "NFNotaInfoSuplementarMap.";

	/** The Constant STMT_INSERT_NFNOTAINFOSUPLEMENTAR. */
	private static final String STMT_INSERT_NFNOTAINFOSUPLEMENTAR = NAMESPACE_NFNOTAINFOSUPLEMENTAR
			+ "insertNFNotaInfoSuplementar";

	/** The Constant STMT_UPDATE_NFNOTAINFOSUPLEMENTAR. */
	private static final String STMT_UPDATE_NFNOTAINFOSUPLEMENTAR = NAMESPACE_NFNOTAINFOSUPLEMENTAR
			+ "updateNFNotaInfoSuplementar";

	/** The Constant STMT_DELETE_NFNOTAINFOSUPLEMENTAR. */
	private static final String STMT_DELETE_NFNOTAINFOSUPLEMENTAR = NAMESPACE_NFNOTAINFOSUPLEMENTAR
			+ "deleteNFNotaInfoSuplementarById";

	/** The Constant STMT_DELETE_NFNOTAINFOSUPLEMENTAR_ALL. */
	private static final String STMT_DELETE_NFNOTAINFOSUPLEMENTAR_ALL = NAMESPACE_NFNOTAINFOSUPLEMENTAR
			+ "deleteAllNFNotaInfoSuplementars";

	/** The Constant STMT_FETCH_NFNOTAINFOSUPLEMENTAR. */
	private static final String STMT_FETCH_NFNOTAINFOSUPLEMENTAR = NAMESPACE_NFNOTAINFOSUPLEMENTAR
			+ "fetchNFNotaInfoSuplementarById";

	/** The Constant STMT_FETCH_NFNOTAINFOSUPLEMENTAR_ALL. */
	private static final String STMT_FETCH_NFNOTAINFOSUPLEMENTAR_ALL = NAMESPACE_NFNOTAINFOSUPLEMENTAR
			+ "fetchAllNFNotaInfoSuplementars";

	/** The Constant STMT_FETCH_NFNOTAINFOSUPLEMENTAR_COUNT. */
	private static final String STMT_FETCH_NFNOTAINFOSUPLEMENTAR_COUNT = NAMESPACE_NFNOTAINFOSUPLEMENTAR
			+ "fetchNFNotaInfoSuplementarRowCount";

	/** The Constant STMT_FETCH_NFNOTAINFOSUPLEMENTAR_ALL_REQUEST. */
	private static final String STMT_FETCH_NFNOTAINFOSUPLEMENTAR_ALL_REQUEST = NAMESPACE_NFNOTAINFOSUPLEMENTAR
			+ "fetchAllNFNotaInfoSuplementarsRequest";

	// ===================================### NFNOTA
	// ####======================================

	IStatusBAR statusBAR;

	IHistoricoBAR historicoBAR;

	INFeBAR nfeBAR;

	IVendasBAR vendasBAR;

	ICadastrosBAR cadastrosBAR;
	
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



	public INFeBAR getNfeBAR() {
		return nfeBAR;
	}

	public void setNfeBAR(INFeBAR nfeBAR) {
		this.nfeBAR = nfeBAR;
	}

	public IVendasBAR getVendasBAR() {
		return vendasBAR;
	}

	public void setVendasBAR(IVendasBAR vendasBAR) {
		this.vendasBAR = vendasBAR;
	}

	public ICadastrosBAR getCadastrosBAR() {
		return cadastrosBAR;
	}

	public void setCadastrosBAR(ICadastrosBAR cadastrosBAR) {
		this.cadastrosBAR = cadastrosBAR;
	}

	
	public INFNotaInfoItemBAR getNfnotaInfoItemBAR() {
		return nfnotaInfoItemBAR;
	}

	public void setNfnotaInfoItemBAR(INFNotaInfoItemBAR nfnotaInfoItemBAR) {
		this.nfnotaInfoItemBAR = nfnotaInfoItemBAR;
	}

	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaBAR#insertNFNota(com.qat.samples.sysmgmt.base.model.NFNota)
	 */
	@Override
	public InternalResponse insertNFNota(NFNota nfnota) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnota.setProcessId(nfnota.getTransactionId());



		if (!ValidationUtil.isNull(nfnota.getInfo()))
		{
			count +=
					NFNotaInfoBARD.maintainNFNotaInfoAssociations(nfnota.getInfo(), response, nfnota.getId(), null,
							null,
							TabelaEnum.NFNOTA, getNfeBAR(), statusBAR, historicoBAR, nfnota.getId(),
							nfnota.getCreateUser(), nfnota.getTransactionId(), nfnota.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnota.getInfoSuplementar()))
		{
			count +=
					NFNotaInfoSuplementarBARD.maintainNFNotaInfoSuplementarAssociations(nfnota.getInfoSuplementar(), response, nfnota.getId(), null,
							null,
							TabelaEnum.NFNOTA, getNfeBAR(), statusBAR, historicoBAR, nfnota.getId(),
							nfnota.getCreateUser(), nfnota.getTransactionId(), nfnota.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTA, nfnota, response);


		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTA, AcaoEnum.INSERT,
				nfnota.getTransactionId(), getHistoricoBAR(), response, nfnota.getId(), nfnota.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaBAR#updateNFNota(com.qat.samples.
	 * sysmgmt.base.model.NFNota)
	 */
	@Override
	public InternalResponse updateNFNota(NFNota nfnota) {
		InternalResponse response = new InternalResponse();
		nfnota.setProcessId(nfnota.getTransactionId());
		Integer count = 0;



		if (!ValidationUtil.isNull(nfnota.getInfo()))
		{
			count +=
					NFNotaInfoBARD.maintainNFNotaInfoAssociations(nfnota.getInfo(), response, nfnota.getId(), null,
							null,
							TabelaEnum.NFNOTA, getNfeBAR(), statusBAR, historicoBAR, nfnota.getId(),
							nfnota.getCreateUser(), nfnota.getTransactionId(), nfnota.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnota.getInfoSuplementar()))
		{
			count +=
					NFNotaInfoSuplementarBARD.maintainNFNotaInfoSuplementarAssociations(nfnota.getInfoSuplementar(), response, nfnota.getId(), null,
							null,
							TabelaEnum.NFNOTA, getNfeBAR(), statusBAR, historicoBAR, nfnota.getId(),
							nfnota.getCreateUser(), nfnota.getTransactionId(), nfnota.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTA, nfnota, response);

		count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTA, AcaoEnum.UPDATE,
				nfnota.getTransactionId(), getHistoricoBAR(), response, nfnota.getId(), nfnota.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaBAR#deleteNFNota(com.qat.samples.
	 * sysmgmt.base.model.NFNota)
	 */
	@Override
	public InternalResponse deleteNFNotaById(NFNota nfnota) {
		InternalResponse response = new InternalResponse();
		nfnota.setProcessId(nfnota.getTransactionId());


		Integer count = 0;

		if (!ValidationUtil.isNull(nfnota.getInfo()))
		{
			count +=
					NFNotaInfoBARD.maintainNFNotaInfoAssociations(nfnota.getInfo(), response, nfnota.getId(), null,
							null,
							TabelaEnum.NFNOTA, getNfeBAR(), statusBAR, historicoBAR, nfnota.getId(),
							nfnota.getCreateUser(), nfnota.getTransactionId(), nfnota.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnota.getInfoSuplementar()))
		{
			count +=
					NFNotaInfoSuplementarBARD.maintainNFNotaInfoSuplementarAssociations(nfnota.getInfoSuplementar(), response, nfnota.getId(), null,
							null,
							TabelaEnum.NFNOTA, getNfeBAR(), statusBAR, historicoBAR, nfnota.getId(),
							nfnota.getCreateUser(), nfnota.getTransactionId(), nfnota.getTransactionId());
		}

MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTA, nfnota, response);

		count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTA, AcaoEnum.DELETE,
				nfnota.getTransactionId(), getHistoricoBAR(), response, nfnota.getId(), nfnota.getUserId());
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaBAR#deleteAllNFNotas()
	 */
	@Override
	public InternalResponse deleteAllNFNotas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaBAR#fetchNFNotaById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public NFNota fetchNFNotaById(FetchByIdRequest request) {
		return (NFNota) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaBAR#fetchAllNFNotasCache()
	 */
	@Override
	public InternalResultsResponse<NFNota> fetchAllNFNotas(NFNota nfnota) {
		InternalResultsResponse<NFNota> response = new InternalResultsResponse<NFNota>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaBAR#fetchNFNotasByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNota> fetchNFNotasByRequest(NFNotaInquiryRequest request) {
		InternalResultsResponse<NFNota> response = new InternalResultsResponse<NFNota>();
		fetchNFNotasByRequest(getSqlSession(), request, STMT_FETCH_NFNOTA_COUNT, STMT_FETCH_NFNOTA_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchNFNotasByRequest
	// ####======================================

	public static void fetchNFNotasByRequest(SqlSession sqlSession, NFNotaInquiryRequest request, String countStatement,
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

	// ===================================### NFNOTAINFO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoBAR#insertNFNotaInfo(com.qat.samples.sysmgmt.base.model.NFNotaInfo)
	 */
	@Override
	public InternalResponse insertNFNotaInfo(NFNotaInfo nfnotainfo) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfo.setProcessId(nfnotainfo.getTransactionId());

		insertNFNotaInfoBARD(nfnotainfo, response);

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFO, nfnotainfo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFO, AcaoEnum.INSERT,
				nfnotainfo.getTransactionId(), getHistoricoBAR(), response, nfnotainfo.getId(), nfnotainfo.getUserId());

		if (!ValidationUtil.isNullOrEmpty(nfnotainfo.getPessoasautorizadasdownloadnfe()))
		{
			count +=
					NFPessoaAutorizadaDownloadNFeBARD.maintainNFPessoaAutorizadaDownloadNFeAssociations(nfnotainfo.getPessoasautorizadasdownloadnfe(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfo.getItens()))
		{
			count += 
					NFNotaInfoItemBARD.maintainNFNotaInfoItemAssociations(nfnotainfo.getItens(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfnotaInfoItemBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfo.getPagamentos()))
		{
			count +=
					NFNotaInfoPagamentoBARD.maintainNFNotaInfoPagamentoAssociations(nfnotainfo.getPagamentos(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoBAR#updateNFNotaInfo(com.qat.
	 * samples.sysmgmt.base.model.NFNotaInfo)
	 */
	@Override
	public InternalResponse updateNFNotaInfo(NFNotaInfo nfnotainfo) {
		InternalResponse response = new InternalResponse();
		nfnotainfo.setProcessId(nfnotainfo.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFO, nfnotainfo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFO, AcaoEnum.UPDATE,
				nfnotainfo.getTransactionId(), getHistoricoBAR(), response, nfnotainfo.getId(), nfnotainfo.getUserId());

		insertNFNotaInfoBARD(nfnotainfo, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoBAR#deleteNFNotaInfo(com.qat.
	 * samples.sysmgmt.base.model.NFNotaInfo)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoById(NFNotaInfo nfnotainfo) {
		InternalResponse response = new InternalResponse();
		nfnotainfo.setProcessId(nfnotainfo.getTransactionId());


		insertNFNotaInfoBARD(nfnotainfo, response);

		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFO, nfnotainfo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFO, AcaoEnum.DELETE,
				nfnotainfo.getTransactionId(), getHistoricoBAR(), response, nfnotainfo.getId(), nfnotainfo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoBAR#deleteAllNFNotaInfos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoBAR#fetchNFNotaInfoById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfo fetchNFNotaInfoById(FetchByIdRequest request) {
		return (NFNotaInfo) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoBAR#fetchAllNFNotaInfosCache(
	 * )
	 */
	@Override
	public InternalResultsResponse<NFNotaInfo> fetchAllNFNotaInfos(NFNotaInfo nfnotainfo) {
		InternalResultsResponse<NFNotaInfo> response = new InternalResultsResponse<NFNotaInfo>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoBAR#fetchNFNotaInfosByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfo> fetchNFNotaInfosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfo> response = new InternalResultsResponse<NFNotaInfo>();
		fetchNFNotaInfosByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFO_COUNT,
				STMT_FETCH_NFNOTAINFO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfosByRequest
	// ####======================================

	public static void fetchNFNotaInfosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOIDENTIFICACAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoIdentificacaoBAR#insertNFNotaInfoIdentificacao(com.qat.samples.sysmgmt.base.model.NFNotaInfoIdentificacao)
	 */
	@Override
	public InternalResponse insertNFNotaInfoIdentificacao(NFNotaInfoIdentificacao nfnotainfoidentificacao) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoidentificacao.setProcessId(nfnotainfoidentificacao.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOIDENTIFICACAO, nfnotainfoidentificacao,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOIDENTIFICACAO, AcaoEnum.INSERT,
				nfnotainfoidentificacao.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoidentificacao.getId(), nfnotainfoidentificacao.getUserId());

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoidentificacao.getReferenciadas()))
		{
			count +=
					NFInfoReferenciadaBARD.maintainNFInfoReferenciadaAssociations(nfnotainfoidentificacao.getReferenciadas(), response, nfnotainfoidentificacao.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOIDENTIFICACAO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfoidentificacao.getId(),
							nfnotainfoidentificacao.getCreateUser(), nfnotainfoidentificacao.getTransactionId(), nfnotainfoidentificacao.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoIdentificacaoBAR#
	 * updateNFNotaInfoIdentificacao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoIdentificacao)
	 */
	@Override
	public InternalResponse updateNFNotaInfoIdentificacao(NFNotaInfoIdentificacao nfnotainfoidentificacao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoidentificacao.setProcessId(nfnotainfoidentificacao.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOIDENTIFICACAO, nfnotainfoidentificacao,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOIDENTIFICACAO, AcaoEnum.UPDATE,
				nfnotainfoidentificacao.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoidentificacao.getId(), nfnotainfoidentificacao.getUserId());
		Integer count = 0;
		if (!ValidationUtil.isNullOrEmpty(nfnotainfoidentificacao.getReferenciadas()))
		{
			count +=
					NFInfoReferenciadaBARD.maintainNFInfoReferenciadaAssociations(nfnotainfoidentificacao.getReferenciadas(), response, nfnotainfoidentificacao.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOIDENTIFICACAO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfoidentificacao.getId(),
							nfnotainfoidentificacao.getCreateUser(), nfnotainfoidentificacao.getTransactionId(), nfnotainfoidentificacao.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoIdentificacaoBAR#
	 * deleteNFNotaInfoIdentificacao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoIdentificacao)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoIdentificacaoById(NFNotaInfoIdentificacao nfnotainfoidentificacao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoidentificacao.setProcessId(nfnotainfoidentificacao.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOIDENTIFICACAO, nfnotainfoidentificacao,
				response);

		Integer count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOIDENTIFICACAO, AcaoEnum.DELETE,
				nfnotainfoidentificacao.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoidentificacao.getId(), nfnotainfoidentificacao.getUserId());

		if (!ValidationUtil.isNullOrEmpty(nfnotainfoidentificacao.getReferenciadas()))
		{
			count +=
					NFInfoReferenciadaBARD.maintainNFInfoReferenciadaAssociations(nfnotainfoidentificacao.getReferenciadas(), response, nfnotainfoidentificacao.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOIDENTIFICACAO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfoidentificacao.getId(),
							nfnotainfoidentificacao.getCreateUser(), nfnotainfoidentificacao.getTransactionId(), nfnotainfoidentificacao.getTransactionId());
		}


		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoIdentificacaoBAR#
	 * deleteAllNFNotaInfoIdentificacaos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoIdentificacaos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOIDENTIFICACAO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoIdentificacaoBAR#
	 * fetchNFNotaInfoIdentificacaoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoIdentificacao fetchNFNotaInfoIdentificacaoById(FetchByIdRequest request) {
		return (NFNotaInfoIdentificacao) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOIDENTIFICACAO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoIdentificacaoBAR#
	 * fetchAllNFNotaInfoIdentificacaosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoIdentificacao> fetchAllNFNotaInfoIdentificacaos(
			NFNotaInfoIdentificacao nfnotainfoidentificacao) {
		InternalResultsResponse<NFNotaInfoIdentificacao> response = new InternalResultsResponse<NFNotaInfoIdentificacao>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOIDENTIFICACAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoIdentificacaoBAR#
	 * fetchNFNotaInfoIdentificacaosByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoIdentificacao> fetchNFNotaInfoIdentificacaosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoIdentificacao> response = new InternalResultsResponse<NFNotaInfoIdentificacao>();
		fetchNFNotaInfoIdentificacaosByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOIDENTIFICACAO_COUNT,
				STMT_FETCH_NFNOTAINFOIDENTIFICACAO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoIdentificacaosByRequest
	// ####======================================

	public static void fetchNFNotaInfoIdentificacaosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	//===================================### NFINFOCUPOMFISCALREFERENCIADO ####======================================
		/**
	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoCupomFiscalReferenciadoBAR#insertNFInfoCupomFiscalReferenciado(com.qat.samples.sysmgmt.base.model.NFInfoCupomFiscalReferenciado)
	 */
	@Override
	public InternalResponse insertNFInfoCupomFiscalReferenciado(NFInfoCupomFiscalReferenciado nfinfocupomfiscalreferenciado)
	{
		InternalResponse response = new InternalResponse();
			Integer count = 0;
			Boolean count1 = false;

	nfinfocupomfiscalreferenciado.setProcessId(nfinfocupomfiscalreferenciado.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFINFOCUPOMFISCALREFERENCIADO, nfinfocupomfiscalreferenciado, response);


	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFOCUPOMFISCALREFERENCIADO, AcaoEnum.INSERT, nfinfocupomfiscalreferenciado.getTransactionId(),
				getHistoricoBAR(), response, nfinfocupomfiscalreferenciado.getId(),nfinfocupomfiscalreferenciado.getUserId());


	if (nfinfocupomfiscalreferenciado.getId() !=  0 && nfinfocupomfiscalreferenciado.getId() != null)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 =
					StatusBARD.maintainStatusAssociations(statusList, response, nfinfocupomfiscalreferenciado.getId(), null, AcaoEnum.INSERT,
							nfinfocupomfiscalreferenciado.getCreateUser(), nfinfocupomfiscalreferenciado.getId(), TabelaEnum.NFINFOCUPOMFISCALREFERENCIADO, statusBAR,
							historicoBAR, nfinfocupomfiscalreferenciado.getTransactionId(), nfinfocupomfiscalreferenciado.getTransactionId());

		}


		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoCupomFiscalReferenciadoBAR#updateNFInfoCupomFiscalReferenciado(com.qat.samples.sysmgmt.base.model.NFInfoCupomFiscalReferenciado)
	 */
	@Override
	public InternalResponse updateNFInfoCupomFiscalReferenciado(NFInfoCupomFiscalReferenciado nfinfocupomfiscalreferenciado)
	{
		InternalResponse response = new InternalResponse();
	 nfinfocupomfiscalreferenciado.setProcessId(nfinfocupomfiscalreferenciado.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFINFOCUPOMFISCALREFERENCIADO, nfinfocupomfiscalreferenciado, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFOCUPOMFISCALREFERENCIADO, AcaoEnum.UPDATE, nfinfocupomfiscalreferenciado.getTransactionId(),
				getHistoricoBAR(), response, nfinfocupomfiscalreferenciado.getId(),nfinfocupomfiscalreferenciado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoCupomFiscalReferenciadoBAR#deleteNFInfoCupomFiscalReferenciado(com.qat.samples.sysmgmt.base.model.NFInfoCupomFiscalReferenciado)
	 */
	@Override
	public InternalResponse deleteNFInfoCupomFiscalReferenciadoById(NFInfoCupomFiscalReferenciado nfinfocupomfiscalreferenciado)
	{
		InternalResponse response = new InternalResponse();
	nfinfocupomfiscalreferenciado.setProcessId(nfinfocupomfiscalreferenciado.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFINFOCUPOMFISCALREFERENCIADO, nfinfocupomfiscalreferenciado, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFOCUPOMFISCALREFERENCIADO, AcaoEnum.DELETE, nfinfocupomfiscalreferenciado.getTransactionId(),
				getHistoricoBAR(), response, nfinfocupomfiscalreferenciado.getId(),nfinfocupomfiscalreferenciado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoCupomFiscalReferenciadoBAR#deleteAllNFInfoCupomFiscalReferenciados()
	 */
	@Override
	public InternalResponse deleteAllNFInfoCupomFiscalReferenciados()
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFINFOCUPOMFISCALREFERENCIADO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFInfoCupomFiscalReferenciadoBAR#fetchNFInfoCupomFiscalReferenciadoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public NFInfoCupomFiscalReferenciado fetchNFInfoCupomFiscalReferenciadoById(FetchByIdRequest request)
	{
	return (NFInfoCupomFiscalReferenciado)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFINFOCUPOMFISCALREFERENCIADO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoCupomFiscalReferenciadoBAR#fetchAllNFInfoCupomFiscalReferenciadosCache()
	 */
	@Override
	public InternalResultsResponse<NFInfoCupomFiscalReferenciado> fetchAllNFInfoCupomFiscalReferenciados(NFInfoCupomFiscalReferenciado nfinfocupomfiscalreferenciado)
	{
		InternalResultsResponse<NFInfoCupomFiscalReferenciado> response = new InternalResultsResponse<NFInfoCupomFiscalReferenciado>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFINFOCUPOMFISCALREFERENCIADO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bar.INFInfoCupomFiscalReferenciadoBAR#fetchNFInfoCupomFiscalReferenciadosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFInfoCupomFiscalReferenciado> fetchNFInfoCupomFiscalReferenciadosByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<NFInfoCupomFiscalReferenciado> response = new InternalResultsResponse<NFInfoCupomFiscalReferenciado>();
		fetchNFInfoCupomFiscalReferenciadosByRequest(getSqlSession(), request, STMT_FETCH_NFINFOCUPOMFISCALREFERENCIADO_COUNT, STMT_FETCH_NFINFOCUPOMFISCALREFERENCIADO_ALL_REQUEST,
				response);
		return response;
	}

	//===================================### fetchNFInfoCupomFiscalReferenciadosByRequest ####======================================

	public static void fetchNFInfoCupomFiscalReferenciadosByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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


	// ===================================### NFINFOMODELO1POR1AREFERENCIADA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoModelo1Por1AReferenciadaBAR#insertNFInfoModelo1Por1AReferenciada(com.qat.samples.sysmgmt.base.model.NFInfoModelo1Por1AReferenciada)
	 */
	@Override
	public InternalResponse insertNFInfoModelo1Por1AReferenciada(
			NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciada) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfinfomodelo1por1areferenciada.setProcessId(nfinfomodelo1por1areferenciada.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFINFOMODELO1POR1AREFERENCIADA,
				nfinfomodelo1por1areferenciada, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFOMODELO1POR1AREFERENCIADA,
				AcaoEnum.INSERT, nfinfomodelo1por1areferenciada.getTransactionId(), getHistoricoBAR(), response,
				nfinfomodelo1por1areferenciada.getId(), nfinfomodelo1por1areferenciada.getUserId());


		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoModelo1Por1AReferenciadaBAR#
	 * updateNFInfoModelo1Por1AReferenciada(com.qat.samples.sysmgmt.base.model.
	 * NFInfoModelo1Por1AReferenciada)
	 */
	@Override
	public InternalResponse updateNFInfoModelo1Por1AReferenciada(
			NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciada) {
		InternalResponse response = new InternalResponse();
		nfinfomodelo1por1areferenciada.setProcessId(nfinfomodelo1por1areferenciada.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFINFOMODELO1POR1AREFERENCIADA,
				nfinfomodelo1por1areferenciada, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFOMODELO1POR1AREFERENCIADA,
				AcaoEnum.UPDATE, nfinfomodelo1por1areferenciada.getTransactionId(), getHistoricoBAR(), response,
				nfinfomodelo1por1areferenciada.getId(), nfinfomodelo1por1areferenciada.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoModelo1Por1AReferenciadaBAR#
	 * deleteNFInfoModelo1Por1AReferenciada(com.qat.samples.sysmgmt.base.model.
	 * NFInfoModelo1Por1AReferenciada)
	 */
	@Override
	public InternalResponse deleteNFInfoModelo1Por1AReferenciadaById(
			NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciada) {
		InternalResponse response = new InternalResponse();
		nfinfomodelo1por1areferenciada.setProcessId(nfinfomodelo1por1areferenciada.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFINFOMODELO1POR1AREFERENCIADA,
				nfinfomodelo1por1areferenciada, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFOMODELO1POR1AREFERENCIADA,
				AcaoEnum.DELETE, nfinfomodelo1por1areferenciada.getTransactionId(), getHistoricoBAR(), response,
				nfinfomodelo1por1areferenciada.getId(), nfinfomodelo1por1areferenciada.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoModelo1Por1AReferenciadaBAR#
	 * deleteAllNFInfoModelo1Por1AReferenciadas()
	 */
	@Override
	public InternalResponse deleteAllNFInfoModelo1Por1AReferenciadas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFINFOMODELO1POR1AREFERENCIADA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFInfoModelo1Por1AReferenciadaBAR#
	 * fetchNFInfoModelo1Por1AReferenciadaById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFInfoModelo1Por1AReferenciada fetchNFInfoModelo1Por1AReferenciadaById(FetchByIdRequest request) {
		return (NFInfoModelo1Por1AReferenciada) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFINFOMODELO1POR1AREFERENCIADA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoModelo1Por1AReferenciadaBAR#
	 * fetchAllNFInfoModelo1Por1AReferenciadasCache()
	 */
	@Override
	public InternalResultsResponse<NFInfoModelo1Por1AReferenciada> fetchAllNFInfoModelo1Por1AReferenciadas(
			NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciada) {
		InternalResultsResponse<NFInfoModelo1Por1AReferenciada> response = new InternalResultsResponse<NFInfoModelo1Por1AReferenciada>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFINFOMODELO1POR1AREFERENCIADA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFInfoModelo1Por1AReferenciadaBAR#
	 * fetchNFInfoModelo1Por1AReferenciadasByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFInfoModelo1Por1AReferenciada> fetchNFInfoModelo1Por1AReferenciadasByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFInfoModelo1Por1AReferenciada> response = new InternalResultsResponse<NFInfoModelo1Por1AReferenciada>();
		fetchNFInfoModelo1Por1AReferenciadasByRequest(getSqlSession(), request,
				STMT_FETCH_NFINFOMODELO1POR1AREFERENCIADA_COUNT, STMT_FETCH_NFINFOMODELO1POR1AREFERENCIADA_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFInfoModelo1Por1AReferenciadasByRequest
	// ####======================================

	public static void fetchNFInfoModelo1Por1AReferenciadasByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFINFOREFERENCIADA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoReferenciadaBAR#insertNFInfoReferenciada(com.qat.samples.sysmgmt.base.model.NFInfoReferenciada)
	 */
	@Override
	public InternalResponse insertNFInfoReferenciada(NFInfoReferenciada nfinforeferenciada) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfinforeferenciada.setProcessId(nfinforeferenciada.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFINFOREFERENCIADA, nfinforeferenciada, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFOREFERENCIADA, AcaoEnum.INSERT,
				nfinforeferenciada.getTransactionId(), getHistoricoBAR(), response, nfinforeferenciada.getId(),
				nfinforeferenciada.getUserId());

		if (!ValidationUtil.isNull(nfinforeferenciada.getInfoNFProdutorRuralReferenciada()))
		{
			count +=
					NFInfoProdutorRuralReferenciadaBARD.maintainNFInfoProdutorRuralReferenciadaAssociations(nfinforeferenciada.getInfoNFProdutorRuralReferenciada(), response, nfinforeferenciada.getId(), null,
							null,
							TabelaEnum.NFINFOREFERENCIADA, getNfeBAR(), statusBAR, historicoBAR, nfinforeferenciada.getId(),
							nfinforeferenciada.getCreateUser(), nfinforeferenciada.getTransactionId(), nfinforeferenciada.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfinforeferenciada.getCupomFiscalReferenciado()))
		{
			count +=
					NFInfoCupomFiscalReferenciadoBARD.maintainNFInfoCupomFiscalReferenciadoAssociations(nfinforeferenciada.getCupomFiscalReferenciado(), response, nfinforeferenciada.getId(), null,
							null,
							TabelaEnum.NFINFOREFERENCIADA, getNfeBAR(), statusBAR, historicoBAR, nfinforeferenciada.getId(),
							nfinforeferenciada.getCreateUser(), nfinforeferenciada.getTransactionId(), nfinforeferenciada.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoReferenciadaBAR#
	 * updateNFInfoReferenciada(com.qat.samples.sysmgmt.base.model.
	 * NFInfoReferenciada)
	 */
	@Override
	public InternalResponse updateNFInfoReferenciada(NFInfoReferenciada nfinforeferenciada) {
		InternalResponse response = new InternalResponse();
		nfinforeferenciada.setProcessId(nfinforeferenciada.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFINFOREFERENCIADA, nfinforeferenciada, response);

		Integer count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFOREFERENCIADA, AcaoEnum.UPDATE,
				nfinforeferenciada.getTransactionId(), getHistoricoBAR(), response, nfinforeferenciada.getId(),
				nfinforeferenciada.getUserId());


		if (!ValidationUtil.isNull(nfinforeferenciada.getInfoNFProdutorRuralReferenciada()))
		{
			count +=
					NFInfoProdutorRuralReferenciadaBARD.maintainNFInfoProdutorRuralReferenciadaAssociations(nfinforeferenciada.getInfoNFProdutorRuralReferenciada(), response, nfinforeferenciada.getId(), null,
							null,
							TabelaEnum.NFINFOREFERENCIADA, getNfeBAR(), statusBAR, historicoBAR, nfinforeferenciada.getId(),
							nfinforeferenciada.getCreateUser(), nfinforeferenciada.getTransactionId(), nfinforeferenciada.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfinforeferenciada.getCupomFiscalReferenciado()))
		{
			count +=
					NFInfoCupomFiscalReferenciadoBARD.maintainNFInfoCupomFiscalReferenciadoAssociations(nfinforeferenciada.getCupomFiscalReferenciado(), response, nfinforeferenciada.getId(), null,
							null,
							TabelaEnum.NFINFOREFERENCIADA, getNfeBAR(), statusBAR, historicoBAR, nfinforeferenciada.getId(),
							nfinforeferenciada.getCreateUser(), nfinforeferenciada.getTransactionId(), nfinforeferenciada.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoReferenciadaBAR#
	 * deleteNFInfoReferenciada(com.qat.samples.sysmgmt.base.model.
	 * NFInfoReferenciada)
	 */
	@Override
	public InternalResponse deleteNFInfoReferenciadaById(NFInfoReferenciada nfinforeferenciada) {
		InternalResponse response = new InternalResponse();
		nfinforeferenciada.setProcessId(nfinforeferenciada.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFINFOREFERENCIADA, nfinforeferenciada, response);

		Integer count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFOREFERENCIADA, AcaoEnum.DELETE,
				nfinforeferenciada.getTransactionId(), getHistoricoBAR(), response, nfinforeferenciada.getId(),
				nfinforeferenciada.getUserId());

		if (!ValidationUtil.isNull(nfinforeferenciada.getInfoNFProdutorRuralReferenciada()))
		{
			count +=
					NFInfoProdutorRuralReferenciadaBARD.maintainNFInfoProdutorRuralReferenciadaAssociations(nfinforeferenciada.getInfoNFProdutorRuralReferenciada(), response, nfinforeferenciada.getId(), null,
							null,
							TabelaEnum.NFINFOREFERENCIADA, getNfeBAR(), statusBAR, historicoBAR, nfinforeferenciada.getId(),
							nfinforeferenciada.getCreateUser(), nfinforeferenciada.getTransactionId(), nfinforeferenciada.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfinforeferenciada.getCupomFiscalReferenciado()))
		{
			count +=
					NFInfoCupomFiscalReferenciadoBARD.maintainNFInfoCupomFiscalReferenciadoAssociations(nfinforeferenciada.getCupomFiscalReferenciado(), response, nfinforeferenciada.getId(), null,
							null,
							TabelaEnum.NFINFOREFERENCIADA, getNfeBAR(), statusBAR, historicoBAR, nfinforeferenciada.getId(),
							nfinforeferenciada.getCreateUser(), nfinforeferenciada.getTransactionId(), nfinforeferenciada.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoReferenciadaBAR#
	 * deleteAllNFInfoReferenciadas()
	 */
	@Override
	public InternalResponse deleteAllNFInfoReferenciadas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFINFOREFERENCIADA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFInfoReferenciadaBAR#
	 * fetchNFInfoReferenciadaById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFInfoReferenciada fetchNFInfoReferenciadaById(FetchByIdRequest request) {
		return (NFInfoReferenciada) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFINFOREFERENCIADA,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoReferenciadaBAR#
	 * fetchAllNFInfoReferenciadasCache()
	 */
	@Override
	public InternalResultsResponse<NFInfoReferenciada> fetchAllNFInfoReferenciadas(
			NFInfoReferenciada nfinforeferenciada) {
		InternalResultsResponse<NFInfoReferenciada> response = new InternalResultsResponse<NFInfoReferenciada>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFINFOREFERENCIADA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFInfoReferenciadaBAR#
	 * fetchNFInfoReferenciadasByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFInfoReferenciada> fetchNFInfoReferenciadasByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFInfoReferenciada> response = new InternalResultsResponse<NFInfoReferenciada>();
		fetchNFInfoReferenciadasByRequest(getSqlSession(), request, STMT_FETCH_NFINFOREFERENCIADA_COUNT,
				STMT_FETCH_NFINFOREFERENCIADA_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFInfoReferenciadasByRequest
	// ####======================================

	public static void fetchNFInfoReferenciadasByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFINFOPRODUTORRURALREFERENCIADA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFInfoProdutorRuralReferenciadaBAR#insertNFInfoProdutorRuralReferenciada(com.qat.samples.sysmgmt.base.model.NFInfoProdutorRuralReferenciada)
	 */
	@Override
	public InternalResponse insertNFInfoProdutorRuralReferenciada(
			NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciada) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfinfoprodutorruralreferenciada.setProcessId(nfinfoprodutorruralreferenciada.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFINFOPRODUTORRURALREFERENCIADA,
				nfinfoprodutorruralreferenciada, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFOPRODUTORRURALREFERENCIADA,
				AcaoEnum.INSERT, nfinfoprodutorruralreferenciada.getTransactionId(), getHistoricoBAR(), response,
				nfinfoprodutorruralreferenciada.getId(), nfinfoprodutorruralreferenciada.getUserId());

		if (nfinfoprodutorruralreferenciada.getId() != 0 && nfinfoprodutorruralreferenciada.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response,
					nfinfoprodutorruralreferenciada.getId(), null, AcaoEnum.INSERT,
					nfinfoprodutorruralreferenciada.getCreateUser(), nfinfoprodutorruralreferenciada.getId(),
					TabelaEnum.NFINFOPRODUTORRURALREFERENCIADA, statusBAR, historicoBAR,
					nfinfoprodutorruralreferenciada.getTransactionId(),
					nfinfoprodutorruralreferenciada.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFInfoProdutorRuralReferenciadaBAR#
	 * updateNFInfoProdutorRuralReferenciada(com.qat.samples.sysmgmt.base.model.
	 * NFInfoProdutorRuralReferenciada)
	 */
	@Override
	public InternalResponse updateNFInfoProdutorRuralReferenciada(
			NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciada) {
		InternalResponse response = new InternalResponse();
		nfinfoprodutorruralreferenciada.setProcessId(nfinfoprodutorruralreferenciada.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFINFOPRODUTORRURALREFERENCIADA,
				nfinfoprodutorruralreferenciada, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFOPRODUTORRURALREFERENCIADA,
				AcaoEnum.UPDATE, nfinfoprodutorruralreferenciada.getTransactionId(), getHistoricoBAR(), response,
				nfinfoprodutorruralreferenciada.getId(), nfinfoprodutorruralreferenciada.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFInfoProdutorRuralReferenciadaBAR#
	 * deleteNFInfoProdutorRuralReferenciada(com.qat.samples.sysmgmt.base.model.
	 * NFInfoProdutorRuralReferenciada)
	 */
	@Override
	public InternalResponse deleteNFInfoProdutorRuralReferenciadaById(
			NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciada) {
		InternalResponse response = new InternalResponse();
		nfinfoprodutorruralreferenciada.setProcessId(nfinfoprodutorruralreferenciada.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFINFOPRODUTORRURALREFERENCIADA,
				nfinfoprodutorruralreferenciada, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFINFOPRODUTORRURALREFERENCIADA,
				AcaoEnum.DELETE, nfinfoprodutorruralreferenciada.getTransactionId(), getHistoricoBAR(), response,
				nfinfoprodutorruralreferenciada.getId(), nfinfoprodutorruralreferenciada.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFInfoProdutorRuralReferenciadaBAR#
	 * deleteAllNFInfoProdutorRuralReferenciadas()
	 */
	@Override
	public InternalResponse deleteAllNFInfoProdutorRuralReferenciadas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFINFOPRODUTORRURALREFERENCIADA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFInfoProdutorRuralReferenciadaBAR#
	 * fetchNFInfoProdutorRuralReferenciadaById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFInfoProdutorRuralReferenciada fetchNFInfoProdutorRuralReferenciadaById(FetchByIdRequest request) {
		return (NFInfoProdutorRuralReferenciada) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFINFOPRODUTORRURALREFERENCIADA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFInfoProdutorRuralReferenciadaBAR#
	 * fetchAllNFInfoProdutorRuralReferenciadasCache()
	 */
	@Override
	public InternalResultsResponse<NFInfoProdutorRuralReferenciada> fetchAllNFInfoProdutorRuralReferenciadas(
			NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciada) {
		InternalResultsResponse<NFInfoProdutorRuralReferenciada> response = new InternalResultsResponse<NFInfoProdutorRuralReferenciada>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFINFOPRODUTORRURALREFERENCIADA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFInfoProdutorRuralReferenciadaBAR#
	 * fetchNFInfoProdutorRuralReferenciadasByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFInfoProdutorRuralReferenciada> fetchNFInfoProdutorRuralReferenciadasByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFInfoProdutorRuralReferenciada> response = new InternalResultsResponse<NFInfoProdutorRuralReferenciada>();
		fetchNFInfoProdutorRuralReferenciadasByRequest(getSqlSession(), request,
				STMT_FETCH_NFINFOPRODUTORRURALREFERENCIADA_COUNT,
				STMT_FETCH_NFINFOPRODUTORRURALREFERENCIADA_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFInfoProdutorRuralReferenciadasByRequest
	// ####======================================

	public static void fetchNFInfoProdutorRuralReferenciadasByRequest(SqlSession sqlSession,
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

	// ===================================### NFNOTAINFOEMITENTE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoEmitenteBAR#insertNFNotaInfoEmitente(com.qat.samples.sysmgmt.base.model.NFNotaInfoEmitente)
	 */
	@Override
	public InternalResponse insertNFNotaInfoEmitente(NFNotaInfoEmitente nfnotainfoemitente) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoemitente.setProcessId(nfnotainfoemitente.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOEMITENTE, nfnotainfoemitente, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOEMITENTE, AcaoEnum.INSERT,
				nfnotainfoemitente.getTransactionId(), getHistoricoBAR(), response, nfnotainfoemitente.getId(),
				nfnotainfoemitente.getUserId());

		if (nfnotainfoemitente.getId() != 0 && nfnotainfoemitente.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfoemitente.getId(), null,
					AcaoEnum.INSERT, nfnotainfoemitente.getCreateUser(), nfnotainfoemitente.getId(),
					TabelaEnum.NFNOTAINFOEMITENTE, statusBAR, historicoBAR, nfnotainfoemitente.getTransactionId(),
					nfnotainfoemitente.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoEmitenteBAR#
	 * updateNFNotaInfoEmitente(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoEmitente)
	 */
	@Override
	public InternalResponse updateNFNotaInfoEmitente(NFNotaInfoEmitente nfnotainfoemitente) {
		InternalResponse response = new InternalResponse();
		nfnotainfoemitente.setProcessId(nfnotainfoemitente.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOEMITENTE, nfnotainfoemitente, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOEMITENTE, AcaoEnum.UPDATE,
				nfnotainfoemitente.getTransactionId(), getHistoricoBAR(), response, nfnotainfoemitente.getId(),
				nfnotainfoemitente.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoEmitenteBAR#
	 * deleteNFNotaInfoEmitente(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoEmitente)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoEmitenteById(NFNotaInfoEmitente nfnotainfoemitente) {
		InternalResponse response = new InternalResponse();
		nfnotainfoemitente.setProcessId(nfnotainfoemitente.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOEMITENTE, nfnotainfoemitente, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOEMITENTE, AcaoEnum.DELETE,
				nfnotainfoemitente.getTransactionId(), getHistoricoBAR(), response, nfnotainfoemitente.getId(),
				nfnotainfoemitente.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoEmitenteBAR#
	 * deleteAllNFNotaInfoEmitentes()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoEmitentes() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOEMITENTE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoEmitenteBAR#
	 * fetchNFNotaInfoEmitenteById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoEmitente fetchNFNotaInfoEmitenteById(FetchByIdRequest request) {
		return (NFNotaInfoEmitente) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOEMITENTE,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoEmitenteBAR#
	 * fetchAllNFNotaInfoEmitentesCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoEmitente> fetchAllNFNotaInfoEmitentes(
			NFNotaInfoEmitente nfnotainfoemitente) {
		InternalResultsResponse<NFNotaInfoEmitente> response = new InternalResultsResponse<NFNotaInfoEmitente>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOEMITENTE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoEmitenteBAR#
	 * fetchNFNotaInfoEmitentesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoEmitente> fetchNFNotaInfoEmitentesByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoEmitente> response = new InternalResultsResponse<NFNotaInfoEmitente>();
		fetchNFNotaInfoEmitentesByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOEMITENTE_COUNT,
				STMT_FETCH_NFNOTAINFOEMITENTE_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoEmitentesByRequest
	// ####======================================

	public static void fetchNFNotaInfoEmitentesByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOAVULSA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoAvulsaBAR#insertNFNotaInfoAvulsa(com.qat.samples.sysmgmt.base.model.NFNotaInfoAvulsa)
	 */
	@Override
	public InternalResponse insertNFNotaInfoAvulsa(NFNotaInfoAvulsa nfnotainfoavulsa) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoavulsa.setProcessId(nfnotainfoavulsa.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOAVULSA, nfnotainfoavulsa, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOAVULSA, AcaoEnum.INSERT,
				nfnotainfoavulsa.getTransactionId(), getHistoricoBAR(), response, nfnotainfoavulsa.getId(),
				nfnotainfoavulsa.getUserId());

		if (nfnotainfoavulsa.getId() != 0 && nfnotainfoavulsa.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfoavulsa.getId(), null,
					AcaoEnum.INSERT, nfnotainfoavulsa.getCreateUser(), nfnotainfoavulsa.getId(),
					TabelaEnum.NFNOTAINFOAVULSA, statusBAR, historicoBAR, nfnotainfoavulsa.getTransactionId(),
					nfnotainfoavulsa.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoAvulsaBAR#
	 * updateNFNotaInfoAvulsa(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoAvulsa)
	 */
	@Override
	public InternalResponse updateNFNotaInfoAvulsa(NFNotaInfoAvulsa nfnotainfoavulsa) {
		InternalResponse response = new InternalResponse();
		nfnotainfoavulsa.setProcessId(nfnotainfoavulsa.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOAVULSA, nfnotainfoavulsa, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOAVULSA, AcaoEnum.UPDATE,
				nfnotainfoavulsa.getTransactionId(), getHistoricoBAR(), response, nfnotainfoavulsa.getId(),
				nfnotainfoavulsa.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoAvulsaBAR#
	 * deleteNFNotaInfoAvulsa(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoAvulsa)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoAvulsaById(NFNotaInfoAvulsa nfnotainfoavulsa) {
		InternalResponse response = new InternalResponse();
		nfnotainfoavulsa.setProcessId(nfnotainfoavulsa.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOAVULSA, nfnotainfoavulsa, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOAVULSA, AcaoEnum.DELETE,
				nfnotainfoavulsa.getTransactionId(), getHistoricoBAR(), response, nfnotainfoavulsa.getId(),
				nfnotainfoavulsa.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoAvulsaBAR#
	 * deleteAllNFNotaInfoAvulsas()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoAvulsas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOAVULSA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoAvulsaBAR#
	 * fetchNFNotaInfoAvulsaById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoAvulsa fetchNFNotaInfoAvulsaById(FetchByIdRequest request) {
		return (NFNotaInfoAvulsa) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOAVULSA,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoAvulsaBAR#
	 * fetchAllNFNotaInfoAvulsasCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoAvulsa> fetchAllNFNotaInfoAvulsas(NFNotaInfoAvulsa nfnotainfoavulsa) {
		InternalResultsResponse<NFNotaInfoAvulsa> response = new InternalResultsResponse<NFNotaInfoAvulsa>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOAVULSA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoAvulsaBAR#
	 * fetchNFNotaInfoAvulsasByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoAvulsa> fetchNFNotaInfoAvulsasByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoAvulsa> response = new InternalResultsResponse<NFNotaInfoAvulsa>();
		fetchNFNotaInfoAvulsasByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOAVULSA_COUNT,
				STMT_FETCH_NFNOTAINFOAVULSA_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoAvulsasByRequest
	// ####======================================

	public static void fetchNFNotaInfoAvulsasByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFODESTINATARIO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoDestinatarioBAR#insertNFNotaInfoDestinatario(com.qat.samples.sysmgmt.base.model.NFNotaInfoDestinatario)
	 */
	@Override
	public InternalResponse insertNFNotaInfoDestinatario(NFNotaInfoDestinatario nfnotainfodestinatario) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfodestinatario.setProcessId(nfnotainfodestinatario.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFODESTINATARIO, nfnotainfodestinatario,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFODESTINATARIO, AcaoEnum.INSERT,
				nfnotainfodestinatario.getTransactionId(), getHistoricoBAR(), response, nfnotainfodestinatario.getId(),
				nfnotainfodestinatario.getUserId());

		if (nfnotainfodestinatario.getId() != 0 && nfnotainfodestinatario.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfodestinatario.getId(), null,
					AcaoEnum.INSERT, nfnotainfodestinatario.getCreateUser(), nfnotainfodestinatario.getId(),
					TabelaEnum.NFNOTAINFODESTINATARIO, statusBAR, historicoBAR,
					nfnotainfodestinatario.getTransactionId(), nfnotainfodestinatario.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoDestinatarioBAR#
	 * updateNFNotaInfoDestinatario(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoDestinatario)
	 */
	@Override
	public InternalResponse updateNFNotaInfoDestinatario(NFNotaInfoDestinatario nfnotainfodestinatario) {
		InternalResponse response = new InternalResponse();
		nfnotainfodestinatario.setProcessId(nfnotainfodestinatario.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFODESTINATARIO, nfnotainfodestinatario,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFODESTINATARIO, AcaoEnum.UPDATE,
				nfnotainfodestinatario.getTransactionId(), getHistoricoBAR(), response, nfnotainfodestinatario.getId(),
				nfnotainfodestinatario.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoDestinatarioBAR#
	 * deleteNFNotaInfoDestinatario(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoDestinatario)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoDestinatarioById(NFNotaInfoDestinatario nfnotainfodestinatario) {
		InternalResponse response = new InternalResponse();
		nfnotainfodestinatario.setProcessId(nfnotainfodestinatario.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFODESTINATARIO, nfnotainfodestinatario,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFODESTINATARIO, AcaoEnum.DELETE,
				nfnotainfodestinatario.getTransactionId(), getHistoricoBAR(), response, nfnotainfodestinatario.getId(),
				nfnotainfodestinatario.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoDestinatarioBAR#
	 * deleteAllNFNotaInfoDestinatarios()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoDestinatarios() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFODESTINATARIO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoDestinatarioBAR#
	 * fetchNFNotaInfoDestinatarioById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoDestinatario fetchNFNotaInfoDestinatarioById(FetchByIdRequest request) {
		return (NFNotaInfoDestinatario) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFODESTINATARIO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoDestinatarioBAR#
	 * fetchAllNFNotaInfoDestinatariosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoDestinatario> fetchAllNFNotaInfoDestinatarios(
			NFNotaInfoDestinatario nfnotainfodestinatario) {
		InternalResultsResponse<NFNotaInfoDestinatario> response = new InternalResultsResponse<NFNotaInfoDestinatario>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFODESTINATARIO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoDestinatarioBAR#
	 * fetchNFNotaInfoDestinatariosByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoDestinatario> fetchNFNotaInfoDestinatariosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoDestinatario> response = new InternalResultsResponse<NFNotaInfoDestinatario>();
		fetchNFNotaInfoDestinatariosByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFODESTINATARIO_COUNT,
				STMT_FETCH_NFNOTAINFODESTINATARIO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoDestinatariosByRequest
	// ####======================================

	public static void fetchNFNotaInfoDestinatariosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOLOCAL
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoLocalBAR#insertNFNotaInfoLocal(com.qat.samples.sysmgmt.base.model.NFNotaInfoLocal)
	 */
	@Override
	public InternalResponse insertNFNotaInfoLocal(NFNotaInfoLocal nfnotainfolocal) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfolocal.setProcessId(nfnotainfolocal.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOLOCAL, nfnotainfolocal, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOLOCAL, AcaoEnum.INSERT,
				nfnotainfolocal.getTransactionId(), getHistoricoBAR(), response, nfnotainfolocal.getId(),
				nfnotainfolocal.getUserId());

		if (nfnotainfolocal.getId() != 0 && nfnotainfolocal.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfolocal.getId(), null,
					AcaoEnum.INSERT, nfnotainfolocal.getCreateUser(), nfnotainfolocal.getId(),
					TabelaEnum.NFNOTAINFOLOCAL, statusBAR, historicoBAR, nfnotainfolocal.getTransactionId(),
					nfnotainfolocal.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoLocalBAR#
	 * updateNFNotaInfoLocal(com.qat.samples.sysmgmt.base.model.NFNotaInfoLocal)
	 */
	@Override
	public InternalResponse updateNFNotaInfoLocal(NFNotaInfoLocal nfnotainfolocal) {
		InternalResponse response = new InternalResponse();
		nfnotainfolocal.setProcessId(nfnotainfolocal.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOLOCAL, nfnotainfolocal, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOLOCAL, AcaoEnum.UPDATE,
				nfnotainfolocal.getTransactionId(), getHistoricoBAR(), response, nfnotainfolocal.getId(),
				nfnotainfolocal.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoLocalBAR#
	 * deleteNFNotaInfoLocal(com.qat.samples.sysmgmt.base.model.NFNotaInfoLocal)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoLocalById(NFNotaInfoLocal nfnotainfolocal) {
		InternalResponse response = new InternalResponse();
		nfnotainfolocal.setProcessId(nfnotainfolocal.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOLOCAL, nfnotainfolocal, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOLOCAL, AcaoEnum.DELETE,
				nfnotainfolocal.getTransactionId(), getHistoricoBAR(), response, nfnotainfolocal.getId(),
				nfnotainfolocal.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoLocalBAR#
	 * deleteAllNFNotaInfoLocals()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoLocals() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOLOCAL_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoLocalBAR#fetchNFNotaInfoLocalById(
	 * com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoLocal fetchNFNotaInfoLocalById(FetchByIdRequest request) {
		return (NFNotaInfoLocal) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOLOCAL,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoLocalBAR#
	 * fetchAllNFNotaInfoLocalsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoLocal> fetchAllNFNotaInfoLocals(NFNotaInfoLocal nfnotainfolocal) {
		InternalResultsResponse<NFNotaInfoLocal> response = new InternalResultsResponse<NFNotaInfoLocal>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOLOCAL_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoLocalBAR#
	 * fetchNFNotaInfoLocalsByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoLocal> fetchNFNotaInfoLocalsByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoLocal> response = new InternalResultsResponse<NFNotaInfoLocal>();
		fetchNFNotaInfoLocalsByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOLOCAL_COUNT,
				STMT_FETCH_NFNOTAINFOLOCAL_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoLocalsByRequest
	// ####======================================

	public static void fetchNFNotaInfoLocalsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFPESSOAAUTORIZADADOWNLOADNFE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFPessoaAutorizadaDownloadNFeBAR#insertNFPessoaAutorizadaDownloadNFe(com.qat.samples.sysmgmt.base.model.NFPessoaAutorizadaDownloadNFe)
	 */
	@Override
	public InternalResponse insertNFPessoaAutorizadaDownloadNFe(
			NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfpessoaautorizadadownloadnfe.setProcessId(nfpessoaautorizadadownloadnfe.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFPESSOAAUTORIZADADOWNLOADNFE,
				nfpessoaautorizadadownloadnfe, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFPESSOAAUTORIZADADOWNLOADNFE,
				AcaoEnum.INSERT, nfpessoaautorizadadownloadnfe.getTransactionId(), getHistoricoBAR(), response,
				nfpessoaautorizadadownloadnfe.getId(), nfpessoaautorizadadownloadnfe.getUserId());

		if (nfpessoaautorizadadownloadnfe.getId() != 0 && nfpessoaautorizadadownloadnfe.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfpessoaautorizadadownloadnfe.getId(),
					null, AcaoEnum.INSERT, nfpessoaautorizadadownloadnfe.getCreateUser(),
					nfpessoaautorizadadownloadnfe.getId(), TabelaEnum.NFPESSOAAUTORIZADADOWNLOADNFE, statusBAR,
					historicoBAR, nfpessoaautorizadadownloadnfe.getTransactionId(),
					nfpessoaautorizadadownloadnfe.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFPessoaAutorizadaDownloadNFeBAR#
	 * updateNFPessoaAutorizadaDownloadNFe(com.qat.samples.sysmgmt.base.model.
	 * NFPessoaAutorizadaDownloadNFe)
	 */
	@Override
	public InternalResponse updateNFPessoaAutorizadaDownloadNFe(
			NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe) {
		InternalResponse response = new InternalResponse();
		nfpessoaautorizadadownloadnfe.setProcessId(nfpessoaautorizadadownloadnfe.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFPESSOAAUTORIZADADOWNLOADNFE,
				nfpessoaautorizadadownloadnfe, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFPESSOAAUTORIZADADOWNLOADNFE,
				AcaoEnum.UPDATE, nfpessoaautorizadadownloadnfe.getTransactionId(), getHistoricoBAR(), response,
				nfpessoaautorizadadownloadnfe.getId(), nfpessoaautorizadadownloadnfe.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFPessoaAutorizadaDownloadNFeBAR#
	 * deleteNFPessoaAutorizadaDownloadNFe(com.qat.samples.sysmgmt.base.model.
	 * NFPessoaAutorizadaDownloadNFe)
	 */
	@Override
	public InternalResponse deleteNFPessoaAutorizadaDownloadNFeById(
			NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe) {
		InternalResponse response = new InternalResponse();
		nfpessoaautorizadadownloadnfe.setProcessId(nfpessoaautorizadadownloadnfe.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFPESSOAAUTORIZADADOWNLOADNFE,
				nfpessoaautorizadadownloadnfe, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFPESSOAAUTORIZADADOWNLOADNFE,
				AcaoEnum.DELETE, nfpessoaautorizadadownloadnfe.getTransactionId(), getHistoricoBAR(), response,
				nfpessoaautorizadadownloadnfe.getId(), nfpessoaautorizadadownloadnfe.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFPessoaAutorizadaDownloadNFeBAR#
	 * deleteAllNFPessoaAutorizadaDownloadNFes()
	 */
	@Override
	public InternalResponse deleteAllNFPessoaAutorizadaDownloadNFes() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFPESSOAAUTORIZADADOWNLOADNFE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFPessoaAutorizadaDownloadNFeBAR#
	 * fetchNFPessoaAutorizadaDownloadNFeById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFPessoaAutorizadaDownloadNFe fetchNFPessoaAutorizadaDownloadNFeById(FetchByIdRequest request) {
		return (NFPessoaAutorizadaDownloadNFe) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFPESSOAAUTORIZADADOWNLOADNFE, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFPessoaAutorizadaDownloadNFeBAR#
	 * fetchAllNFPessoaAutorizadaDownloadNFesCache()
	 */
	@Override
	public InternalResultsResponse<NFPessoaAutorizadaDownloadNFe> fetchAllNFPessoaAutorizadaDownloadNFes(
			NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe) {
		InternalResultsResponse<NFPessoaAutorizadaDownloadNFe> response = new InternalResultsResponse<NFPessoaAutorizadaDownloadNFe>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFPESSOAAUTORIZADADOWNLOADNFE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFPessoaAutorizadaDownloadNFeBAR#
	 * fetchNFPessoaAutorizadaDownloadNFesByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFPessoaAutorizadaDownloadNFe> fetchNFPessoaAutorizadaDownloadNFesByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFPessoaAutorizadaDownloadNFe> response = new InternalResultsResponse<NFPessoaAutorizadaDownloadNFe>();
		fetchNFPessoaAutorizadaDownloadNFesByRequest(getSqlSession(), request,
				STMT_FETCH_NFPESSOAAUTORIZADADOWNLOADNFE_COUNT, STMT_FETCH_NFPESSOAAUTORIZADADOWNLOADNFE_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFPessoaAutorizadaDownloadNFesByRequest
	// ####======================================

	public static void fetchNFPessoaAutorizadaDownloadNFesByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOTOTAL
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTotalBAR#insertNFNotaInfoTotal(com.qat.samples.sysmgmt.base.model.NFNotaInfoTotal)
	 */
	@Override
	public InternalResponse insertNFNotaInfoTotal(NFNotaInfoTotal nfnotainfototal) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfototal.setProcessId(nfnotainfototal.getTransactionId());

		if (!ValidationUtil.isNull(nfnotainfototal.getIcmstotal()))
		{
			count +=
					NFNotaInfoICMSTotalBARD.maintainNFNotaInfoICMSTotalAssociations(nfnotainfototal.getIcmstotal(), response, nfnotainfototal.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOTOTAL, getNfeBAR(), statusBAR, historicoBAR, nfnotainfototal.getId(),
							nfnotainfototal.getCreateUser(), nfnotainfototal.getTransactionId(), nfnotainfototal.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfototal.getIssqntotal()))
		{
			count +=
					NFNotaInfoISSQNTotalBARD.maintainNFNotaInfoISSQNTotalAssociations(nfnotainfototal.getIssqntotal(), response, nfnotainfototal.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOTOTAL, getNfeBAR(), statusBAR, historicoBAR, nfnotainfototal.getId(),
							nfnotainfototal.getCreateUser(), nfnotainfototal.getTransactionId(), nfnotainfototal.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfototal.getRetencoestributos()))
		{
			count +=
					NFNotaInfoRetencoesTributosBARD.maintainNFNotaInfoRetencoesTributosAssociations(nfnotainfototal.getRetencoestributos(), response, nfnotainfototal.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOTOTAL, getNfeBAR(), statusBAR, historicoBAR, nfnotainfototal.getId(),
							nfnotainfototal.getCreateUser(), nfnotainfototal.getTransactionId(), nfnotainfototal.getTransactionId());
		}


		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOTOTAL, nfnotainfototal, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOTOTAL, AcaoEnum.INSERT,
				nfnotainfototal.getTransactionId(), getHistoricoBAR(), response, nfnotainfototal.getId(),
				nfnotainfototal.getUserId());


		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTotalBAR#
	 * updateNFNotaInfoTotal(com.qat.samples.sysmgmt.base.model.NFNotaInfoTotal)
	 */
	@Override
	public InternalResponse updateNFNotaInfoTotal(NFNotaInfoTotal nfnotainfototal) {
		InternalResponse response = new InternalResponse();
		nfnotainfototal.setProcessId(nfnotainfototal.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOTOTAL, nfnotainfototal, response);

		Integer count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOTOTAL, AcaoEnum.UPDATE,
				nfnotainfototal.getTransactionId(), getHistoricoBAR(), response, nfnotainfototal.getId(),
				nfnotainfototal.getUserId());

		if (!ValidationUtil.isNull(nfnotainfototal.getIcmstotal()))
		{
			count +=
					NFNotaInfoICMSTotalBARD.maintainNFNotaInfoICMSTotalAssociations(nfnotainfototal.getIcmstotal(), response, nfnotainfototal.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOTOTAL, getNfeBAR(), statusBAR, historicoBAR, nfnotainfototal.getId(),
							nfnotainfototal.getCreateUser(), nfnotainfototal.getTransactionId(), nfnotainfototal.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfototal.getIssqntotal()))
		{
			count +=
					NFNotaInfoISSQNTotalBARD.maintainNFNotaInfoISSQNTotalAssociations(nfnotainfototal.getIssqntotal(), response, nfnotainfototal.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOTOTAL, getNfeBAR(), statusBAR, historicoBAR, nfnotainfototal.getId(),
							nfnotainfototal.getCreateUser(), nfnotainfototal.getTransactionId(), nfnotainfototal.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfototal.getRetencoestributos()))
		{
			count +=
					NFNotaInfoRetencoesTributosBARD.maintainNFNotaInfoRetencoesTributosAssociations(nfnotainfototal.getRetencoestributos(), response, nfnotainfototal.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOTOTAL, getNfeBAR(), statusBAR, historicoBAR, nfnotainfototal.getId(),
							nfnotainfototal.getCreateUser(), nfnotainfototal.getTransactionId(), nfnotainfototal.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTotalBAR#
	 * deleteNFNotaInfoTotal(com.qat.samples.sysmgmt.base.model.NFNotaInfoTotal)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoTotalById(NFNotaInfoTotal nfnotainfototal) {
		InternalResponse response = new InternalResponse();
		nfnotainfototal.setProcessId(nfnotainfototal.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOTOTAL, nfnotainfototal, response);

		Integer count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOTOTAL, AcaoEnum.DELETE,
				nfnotainfototal.getTransactionId(), getHistoricoBAR(), response, nfnotainfototal.getId(),
				nfnotainfototal.getUserId());

		if (!ValidationUtil.isNull(nfnotainfototal.getIcmstotal()))
		{
			count +=
					NFNotaInfoICMSTotalBARD.maintainNFNotaInfoICMSTotalAssociations(nfnotainfototal.getIcmstotal(), response, nfnotainfototal.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOTOTAL, getNfeBAR(), statusBAR, historicoBAR, nfnotainfototal.getId(),
							nfnotainfototal.getCreateUser(), nfnotainfototal.getTransactionId(), nfnotainfototal.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfototal.getIssqntotal()))
		{
			count +=
					NFNotaInfoISSQNTotalBARD.maintainNFNotaInfoISSQNTotalAssociations(nfnotainfototal.getIssqntotal(), response, nfnotainfototal.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOTOTAL, getNfeBAR(), statusBAR, historicoBAR, nfnotainfototal.getId(),
							nfnotainfototal.getCreateUser(), nfnotainfototal.getTransactionId(), nfnotainfototal.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfototal.getRetencoestributos()))
		{
			count +=
					NFNotaInfoRetencoesTributosBARD.maintainNFNotaInfoRetencoesTributosAssociations(nfnotainfototal.getRetencoestributos(), response, nfnotainfototal.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOTOTAL, getNfeBAR(), statusBAR, historicoBAR, nfnotainfototal.getId(),
							nfnotainfototal.getCreateUser(), nfnotainfototal.getTransactionId(), nfnotainfototal.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTotalBAR#
	 * deleteAllNFNotaInfoTotals()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoTotals() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOTOTAL_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoTotalBAR#fetchNFNotaInfoTotalById(
	 * com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoTotal fetchNFNotaInfoTotalById(FetchByIdRequest request) {
		return (NFNotaInfoTotal) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOTOTAL,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTotalBAR#
	 * fetchAllNFNotaInfoTotalsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoTotal> fetchAllNFNotaInfoTotals(NFNotaInfoTotal nfnotainfototal) {
		InternalResultsResponse<NFNotaInfoTotal> response = new InternalResultsResponse<NFNotaInfoTotal>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOTOTAL_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoTotalBAR#
	 * fetchNFNotaInfoTotalsByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoTotal> fetchNFNotaInfoTotalsByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoTotal> response = new InternalResultsResponse<NFNotaInfoTotal>();
		fetchNFNotaInfoTotalsByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOTOTAL_COUNT,
				STMT_FETCH_NFNOTAINFOTOTAL_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoTotalsByRequest
	// ####======================================

	public static void fetchNFNotaInfoTotalsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOICMSTOTAL
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoICMSTotalBAR#insertNFNotaInfoICMSTotal(com.qat.samples.sysmgmt.base.model.NFNotaInfoICMSTotal)
	 */
	@Override
	public InternalResponse insertNFNotaInfoICMSTotal(NFNotaInfoICMSTotal nfnotainfoicmstotal) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoicmstotal.setProcessId(nfnotainfoicmstotal.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOICMSTOTAL, nfnotainfoicmstotal, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOICMSTOTAL, AcaoEnum.INSERT,
				nfnotainfoicmstotal.getTransactionId(), getHistoricoBAR(), response, nfnotainfoicmstotal.getId(),
				nfnotainfoicmstotal.getUserId());

		if (nfnotainfoicmstotal.getId() != 0 && nfnotainfoicmstotal.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfoicmstotal.getId(), null,
					AcaoEnum.INSERT, nfnotainfoicmstotal.getCreateUser(), nfnotainfoicmstotal.getId(),
					TabelaEnum.NFNOTAINFOICMSTOTAL, statusBAR, historicoBAR, nfnotainfoicmstotal.getTransactionId(),
					nfnotainfoicmstotal.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoICMSTotalBAR#
	 * updateNFNotaInfoICMSTotal(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoICMSTotal)
	 */
	@Override
	public InternalResponse updateNFNotaInfoICMSTotal(NFNotaInfoICMSTotal nfnotainfoicmstotal) {
		InternalResponse response = new InternalResponse();
		nfnotainfoicmstotal.setProcessId(nfnotainfoicmstotal.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOICMSTOTAL, nfnotainfoicmstotal, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOICMSTOTAL, AcaoEnum.UPDATE,
				nfnotainfoicmstotal.getTransactionId(), getHistoricoBAR(), response, nfnotainfoicmstotal.getId(),
				nfnotainfoicmstotal.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoICMSTotalBAR#
	 * deleteNFNotaInfoICMSTotal(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoICMSTotal)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoICMSTotalById(NFNotaInfoICMSTotal nfnotainfoicmstotal) {
		InternalResponse response = new InternalResponse();
		nfnotainfoicmstotal.setProcessId(nfnotainfoicmstotal.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOICMSTOTAL, nfnotainfoicmstotal, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOICMSTOTAL, AcaoEnum.DELETE,
				nfnotainfoicmstotal.getTransactionId(), getHistoricoBAR(), response, nfnotainfoicmstotal.getId(),
				nfnotainfoicmstotal.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoICMSTotalBAR#
	 * deleteAllNFNotaInfoICMSTotals()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoICMSTotals() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOICMSTOTAL_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoICMSTotalBAR#
	 * fetchNFNotaInfoICMSTotalById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoICMSTotal fetchNFNotaInfoICMSTotalById(FetchByIdRequest request) {
		return (NFNotaInfoICMSTotal) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOICMSTOTAL,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoICMSTotalBAR#
	 * fetchAllNFNotaInfoICMSTotalsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoICMSTotal> fetchAllNFNotaInfoICMSTotals(
			NFNotaInfoICMSTotal nfnotainfoicmstotal) {
		InternalResultsResponse<NFNotaInfoICMSTotal> response = new InternalResultsResponse<NFNotaInfoICMSTotal>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOICMSTOTAL_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoICMSTotalBAR#
	 * fetchNFNotaInfoICMSTotalsByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoICMSTotal> fetchNFNotaInfoICMSTotalsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoICMSTotal> response = new InternalResultsResponse<NFNotaInfoICMSTotal>();
		fetchNFNotaInfoICMSTotalsByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOICMSTOTAL_COUNT,
				STMT_FETCH_NFNOTAINFOICMSTOTAL_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoICMSTotalsByRequest
	// ####======================================

	public static void fetchNFNotaInfoICMSTotalsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOISSQNTOTAL
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoISSQNTotalBAR#insertNFNotaInfoISSQNTotal(com.qat.samples.sysmgmt.base.model.NFNotaInfoISSQNTotal)
	 */
	@Override
	public InternalResponse insertNFNotaInfoISSQNTotal(NFNotaInfoISSQNTotal nfnotainfoissqntotal) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoissqntotal.setProcessId(nfnotainfoissqntotal.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOISSQNTOTAL, nfnotainfoissqntotal, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOISSQNTOTAL, AcaoEnum.INSERT,
				nfnotainfoissqntotal.getTransactionId(), getHistoricoBAR(), response, nfnotainfoissqntotal.getId(),
				nfnotainfoissqntotal.getUserId());

		if (nfnotainfoissqntotal.getId() != 0 && nfnotainfoissqntotal.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfoissqntotal.getId(), null,
					AcaoEnum.INSERT, nfnotainfoissqntotal.getCreateUser(), nfnotainfoissqntotal.getId(),
					TabelaEnum.NFNOTAINFOISSQNTOTAL, statusBAR, historicoBAR, nfnotainfoissqntotal.getTransactionId(),
					nfnotainfoissqntotal.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoISSQNTotalBAR#
	 * updateNFNotaInfoISSQNTotal(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoISSQNTotal)
	 */
	@Override
	public InternalResponse updateNFNotaInfoISSQNTotal(NFNotaInfoISSQNTotal nfnotainfoissqntotal) {
		InternalResponse response = new InternalResponse();
		nfnotainfoissqntotal.setProcessId(nfnotainfoissqntotal.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOISSQNTOTAL, nfnotainfoissqntotal, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOISSQNTOTAL, AcaoEnum.UPDATE,
				nfnotainfoissqntotal.getTransactionId(), getHistoricoBAR(), response, nfnotainfoissqntotal.getId(),
				nfnotainfoissqntotal.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoISSQNTotalBAR#
	 * deleteNFNotaInfoISSQNTotal(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoISSQNTotal)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoISSQNTotalById(NFNotaInfoISSQNTotal nfnotainfoissqntotal) {
		InternalResponse response = new InternalResponse();
		nfnotainfoissqntotal.setProcessId(nfnotainfoissqntotal.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOISSQNTOTAL, nfnotainfoissqntotal, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOISSQNTOTAL, AcaoEnum.DELETE,
				nfnotainfoissqntotal.getTransactionId(), getHistoricoBAR(), response, nfnotainfoissqntotal.getId(),
				nfnotainfoissqntotal.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoISSQNTotalBAR#
	 * deleteAllNFNotaInfoISSQNTotals()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoISSQNTotals() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOISSQNTOTAL_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoISSQNTotalBAR#
	 * fetchNFNotaInfoISSQNTotalById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoISSQNTotal fetchNFNotaInfoISSQNTotalById(FetchByIdRequest request) {
		return (NFNotaInfoISSQNTotal) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOISSQNTOTAL, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoISSQNTotalBAR#
	 * fetchAllNFNotaInfoISSQNTotalsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoISSQNTotal> fetchAllNFNotaInfoISSQNTotals(
			NFNotaInfoISSQNTotal nfnotainfoissqntotal) {
		InternalResultsResponse<NFNotaInfoISSQNTotal> response = new InternalResultsResponse<NFNotaInfoISSQNTotal>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOISSQNTOTAL_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoISSQNTotalBAR#
	 * fetchNFNotaInfoISSQNTotalsByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoISSQNTotal> fetchNFNotaInfoISSQNTotalsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoISSQNTotal> response = new InternalResultsResponse<NFNotaInfoISSQNTotal>();
		fetchNFNotaInfoISSQNTotalsByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOISSQNTOTAL_COUNT,
				STMT_FETCH_NFNOTAINFOISSQNTOTAL_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoISSQNTotalsByRequest
	// ####======================================

	public static void fetchNFNotaInfoISSQNTotalsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFORETENCOESTRIBUTOS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoRetencoesTributosBAR#insertNFNotaInfoRetencoesTributos(com.qat.samples.sysmgmt.base.model.NFNotaInfoRetencoesTributos)
	 */
	@Override
	public InternalResponse insertNFNotaInfoRetencoesTributos(NFNotaInfoRetencoesTributos nfnotainforetencoestributos) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainforetencoestributos.setProcessId(nfnotainforetencoestributos.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFORETENCOESTRIBUTOS, nfnotainforetencoestributos,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFORETENCOESTRIBUTOS, AcaoEnum.INSERT,
				nfnotainforetencoestributos.getTransactionId(), getHistoricoBAR(), response,
				nfnotainforetencoestributos.getId(), nfnotainforetencoestributos.getUserId());

		if (nfnotainforetencoestributos.getId() != 0 && nfnotainforetencoestributos.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainforetencoestributos.getId(),
					null, AcaoEnum.INSERT, nfnotainforetencoestributos.getCreateUser(),
					nfnotainforetencoestributos.getId(), TabelaEnum.NFNOTAINFORETENCOESTRIBUTOS, statusBAR,
					historicoBAR, nfnotainforetencoestributos.getTransactionId(),
					nfnotainforetencoestributos.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoRetencoesTributosBAR#
	 * updateNFNotaInfoRetencoesTributos(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoRetencoesTributos)
	 */
	@Override
	public InternalResponse updateNFNotaInfoRetencoesTributos(NFNotaInfoRetencoesTributos nfnotainforetencoestributos) {
		InternalResponse response = new InternalResponse();
		nfnotainforetencoestributos.setProcessId(nfnotainforetencoestributos.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFORETENCOESTRIBUTOS, nfnotainforetencoestributos,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFORETENCOESTRIBUTOS, AcaoEnum.UPDATE,
				nfnotainforetencoestributos.getTransactionId(), getHistoricoBAR(), response,
				nfnotainforetencoestributos.getId(), nfnotainforetencoestributos.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoRetencoesTributosBAR#
	 * deleteNFNotaInfoRetencoesTributos(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoRetencoesTributos)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoRetencoesTributosById(
			NFNotaInfoRetencoesTributos nfnotainforetencoestributos) {
		InternalResponse response = new InternalResponse();
		nfnotainforetencoestributos.setProcessId(nfnotainforetencoestributos.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFORETENCOESTRIBUTOS, nfnotainforetencoestributos,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFORETENCOESTRIBUTOS, AcaoEnum.DELETE,
				nfnotainforetencoestributos.getTransactionId(), getHistoricoBAR(), response,
				nfnotainforetencoestributos.getId(), nfnotainforetencoestributos.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoRetencoesTributosBAR#
	 * deleteAllNFNotaInfoRetencoesTributoss()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoRetencoesTributoss() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFORETENCOESTRIBUTOS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoRetencoesTributosBAR#
	 * fetchNFNotaInfoRetencoesTributosById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoRetencoesTributos fetchNFNotaInfoRetencoesTributosById(FetchByIdRequest request) {
		return (NFNotaInfoRetencoesTributos) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFORETENCOESTRIBUTOS, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoRetencoesTributosBAR#
	 * fetchAllNFNotaInfoRetencoesTributossCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoRetencoesTributos> fetchAllNFNotaInfoRetencoesTributoss(
			NFNotaInfoRetencoesTributos nfnotainforetencoestributos) {
		InternalResultsResponse<NFNotaInfoRetencoesTributos> response = new InternalResultsResponse<NFNotaInfoRetencoesTributos>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFORETENCOESTRIBUTOS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoRetencoesTributosBAR#
	 * fetchNFNotaInfoRetencoesTributossByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoRetencoesTributos> fetchNFNotaInfoRetencoesTributossByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoRetencoesTributos> response = new InternalResultsResponse<NFNotaInfoRetencoesTributos>();
		fetchNFNotaInfoRetencoesTributossByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFORETENCOESTRIBUTOS_COUNT, STMT_FETCH_NFNOTAINFORETENCOESTRIBUTOS_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoRetencoesTributossByRequest
	// ####======================================

	public static void fetchNFNotaInfoRetencoesTributossByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOTRANSPORTE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTransporteBAR#insertNFNotaInfoTransporte(com.qat.samples.sysmgmt.base.model.NFNotaInfoTransporte)
	 */
	@Override
	public InternalResponse insertNFNotaInfoTransporte(NFNotaInfoTransporte nfnotainfotransporte) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfotransporte.setProcessId(nfnotainfotransporte.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOTRANSPORTE, nfnotainfotransporte, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOTRANSPORTE, AcaoEnum.INSERT,
				nfnotainfotransporte.getTransactionId(), getHistoricoBAR(), response, nfnotainfotransporte.getId(),
				nfnotainfotransporte.getUserId());

		if (!ValidationUtil.isNull(nfnotainfotransporte.getTransportador()))
		{
			count +=
					NFNotaInfoTransportadorBARD.maintainNFNotaInfoTransportadorAssociations(nfnotainfotransporte.getTransportador(), response, nfnotainfotransporte.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfotransporte.getId(),
							nfnotainfotransporte.getCreateUser(), nfnotainfotransporte.getTransactionId(), nfnotainfotransporte.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfotransporte.getReboques()))
		{
			count +=
					NFNotaInfoReboqueBARD.maintainNFNotaInfoReboqueAssociations(nfnotainfotransporte.getReboques(), response, nfnotainfotransporte.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfotransporte.getId(),
							nfnotainfotransporte.getCreateUser(), nfnotainfotransporte.getTransactionId(), nfnotainfotransporte.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfotransporte.getVeiculo()))
		{
			count +=
					NFNotaInfoVeiculoBARD.maintainNFNotaInfoVeiculoAssociations(nfnotainfotransporte.getVeiculo(), response, nfnotainfotransporte.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfotransporte.getId(),
							nfnotainfotransporte.getCreateUser(), nfnotainfotransporte.getTransactionId(), nfnotainfotransporte.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfotransporte.getIcmsTransporte()))
		{
			count +=
					NFNotaInfoRetencaoICMSTransporteBARD.maintainNFNotaInfoRetencaoICMSTransporteAssociations(nfnotainfotransporte.getIcmsTransporte(), response, nfnotainfotransporte.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfotransporte.getId(),
							nfnotainfotransporte.getCreateUser(), nfnotainfotransporte.getTransactionId(), nfnotainfotransporte.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTransporteBAR#
	 * updateNFNotaInfoTransporte(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoTransporte)
	 */
	@Override
	public InternalResponse updateNFNotaInfoTransporte(NFNotaInfoTransporte nfnotainfotransporte) {
		InternalResponse response = new InternalResponse();
		nfnotainfotransporte.setProcessId(nfnotainfotransporte.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOTRANSPORTE, nfnotainfotransporte, response);

		Integer count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOTRANSPORTE, AcaoEnum.UPDATE,
				nfnotainfotransporte.getTransactionId(), getHistoricoBAR(), response, nfnotainfotransporte.getId(),
				nfnotainfotransporte.getUserId());

		if (!ValidationUtil.isNull(nfnotainfotransporte.getTransportador()))
		{
			count +=
					NFNotaInfoTransportadorBARD.maintainNFNotaInfoTransportadorAssociations(nfnotainfotransporte.getTransportador(), response, nfnotainfotransporte.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfotransporte.getId(),
							nfnotainfotransporte.getCreateUser(), nfnotainfotransporte.getTransactionId(), nfnotainfotransporte.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfotransporte.getReboques()))
		{
			count +=
					NFNotaInfoReboqueBARD.maintainNFNotaInfoReboqueAssociations(nfnotainfotransporte.getReboques(), response, nfnotainfotransporte.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfotransporte.getId(),
							nfnotainfotransporte.getCreateUser(), nfnotainfotransporte.getTransactionId(), nfnotainfotransporte.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfotransporte.getVeiculo()))
		{
			count +=
					NFNotaInfoVeiculoBARD.maintainNFNotaInfoVeiculoAssociations(nfnotainfotransporte.getVeiculo(), response, nfnotainfotransporte.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfotransporte.getId(),
							nfnotainfotransporte.getCreateUser(), nfnotainfotransporte.getTransactionId(), nfnotainfotransporte.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfotransporte.getIcmsTransporte()))
		{
			count +=
					NFNotaInfoRetencaoICMSTransporteBARD.maintainNFNotaInfoRetencaoICMSTransporteAssociations(nfnotainfotransporte.getIcmsTransporte(), response, nfnotainfotransporte.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfotransporte.getId(),
							nfnotainfotransporte.getCreateUser(), nfnotainfotransporte.getTransactionId(), nfnotainfotransporte.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTransporteBAR#
	 * deleteNFNotaInfoTransporte(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoTransporte)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoTransporteById(NFNotaInfoTransporte nfnotainfotransporte) {
		InternalResponse response = new InternalResponse();
		nfnotainfotransporte.setProcessId(nfnotainfotransporte.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOTRANSPORTE, nfnotainfotransporte, response);

		Integer count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOTRANSPORTE, AcaoEnum.DELETE,
				nfnotainfotransporte.getTransactionId(), getHistoricoBAR(), response, nfnotainfotransporte.getId(),
				nfnotainfotransporte.getUserId());

		if (!ValidationUtil.isNull(nfnotainfotransporte.getTransportador()))
		{
			count +=
					NFNotaInfoTransportadorBARD.maintainNFNotaInfoTransportadorAssociations(nfnotainfotransporte.getTransportador(), response, nfnotainfotransporte.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfotransporte.getId(),
							nfnotainfotransporte.getCreateUser(), nfnotainfotransporte.getTransactionId(), nfnotainfotransporte.getTransactionId());
		}

		if (!ValidationUtil.isNullOrEmpty(nfnotainfotransporte.getReboques()))
		{
			count +=
					NFNotaInfoReboqueBARD.maintainNFNotaInfoReboqueAssociations(nfnotainfotransporte.getReboques(), response, nfnotainfotransporte.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfotransporte.getId(),
							nfnotainfotransporte.getCreateUser(), nfnotainfotransporte.getTransactionId(), nfnotainfotransporte.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfotransporte.getVeiculo()))
		{
			count +=
					NFNotaInfoVeiculoBARD.maintainNFNotaInfoVeiculoAssociations(nfnotainfotransporte.getVeiculo(), response, nfnotainfotransporte.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfotransporte.getId(),
							nfnotainfotransporte.getCreateUser(), nfnotainfotransporte.getTransactionId(), nfnotainfotransporte.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfotransporte.getIcmsTransporte()))
		{
			count +=
					NFNotaInfoRetencaoICMSTransporteBARD.maintainNFNotaInfoRetencaoICMSTransporteAssociations(nfnotainfotransporte.getIcmsTransporte(), response, nfnotainfotransporte.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfotransporte.getId(),
							nfnotainfotransporte.getCreateUser(), nfnotainfotransporte.getTransactionId(), nfnotainfotransporte.getTransactionId());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTransporteBAR#
	 * deleteAllNFNotaInfoTransportes()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoTransportes() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOTRANSPORTE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoTransporteBAR#
	 * fetchNFNotaInfoTransporteById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoTransporte fetchNFNotaInfoTransporteById(FetchByIdRequest request) {
		return (NFNotaInfoTransporte) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOTRANSPORTE, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTransporteBAR#
	 * fetchAllNFNotaInfoTransportesCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoTransporte> fetchAllNFNotaInfoTransportes(
			NFNotaInfoTransporte nfnotainfotransporte) {
		InternalResultsResponse<NFNotaInfoTransporte> response = new InternalResultsResponse<NFNotaInfoTransporte>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOTRANSPORTE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoTransporteBAR#
	 * fetchNFNotaInfoTransportesByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoTransporte> fetchNFNotaInfoTransportesByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoTransporte> response = new InternalResultsResponse<NFNotaInfoTransporte>();
		fetchNFNotaInfoTransportesByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOTRANSPORTE_COUNT,
				STMT_FETCH_NFNOTAINFOTRANSPORTE_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoTransportesByRequest
	// ####======================================

	public static void fetchNFNotaInfoTransportesByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFORETENCAOICMSTRANSPORTE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoRetencaoICMSTransporteBAR#insertNFNotaInfoRetencaoICMSTransporte(com.qat.samples.sysmgmt.base.model.NFNotaInfoRetencaoICMSTransporte)
	 */
	@Override
	public InternalResponse insertNFNotaInfoRetencaoICMSTransporte(
			NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporte) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainforetencaoicmstransporte.setProcessId(nfnotainforetencaoicmstransporte.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFORETENCAOICMSTRANSPORTE,
				nfnotainforetencaoicmstransporte, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFORETENCAOICMSTRANSPORTE,
				AcaoEnum.INSERT, nfnotainforetencaoicmstransporte.getTransactionId(), getHistoricoBAR(), response,
				nfnotainforetencaoicmstransporte.getId(), nfnotainforetencaoicmstransporte.getUserId());

		if (nfnotainforetencaoicmstransporte.getId() != 0 && nfnotainforetencaoicmstransporte.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response,
					nfnotainforetencaoicmstransporte.getId(), null, AcaoEnum.INSERT,
					nfnotainforetencaoicmstransporte.getCreateUser(), nfnotainforetencaoicmstransporte.getId(),
					TabelaEnum.NFNOTAINFORETENCAOICMSTRANSPORTE, statusBAR, historicoBAR,
					nfnotainforetencaoicmstransporte.getTransactionId(),
					nfnotainforetencaoicmstransporte.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoRetencaoICMSTransporteBAR#
	 * updateNFNotaInfoRetencaoICMSTransporte(com.qat.samples.sysmgmt.base.model
	 * .NFNotaInfoRetencaoICMSTransporte)
	 */
	@Override
	public InternalResponse updateNFNotaInfoRetencaoICMSTransporte(
			NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporte) {
		InternalResponse response = new InternalResponse();
		nfnotainforetencaoicmstransporte.setProcessId(nfnotainforetencaoicmstransporte.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFORETENCAOICMSTRANSPORTE,
				nfnotainforetencaoicmstransporte, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFORETENCAOICMSTRANSPORTE,
				AcaoEnum.UPDATE, nfnotainforetencaoicmstransporte.getTransactionId(), getHistoricoBAR(), response,
				nfnotainforetencaoicmstransporte.getId(), nfnotainforetencaoicmstransporte.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoRetencaoICMSTransporteBAR#
	 * deleteNFNotaInfoRetencaoICMSTransporte(com.qat.samples.sysmgmt.base.model
	 * .NFNotaInfoRetencaoICMSTransporte)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoRetencaoICMSTransporteById(
			NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporte) {
		InternalResponse response = new InternalResponse();
		nfnotainforetencaoicmstransporte.setProcessId(nfnotainforetencaoicmstransporte.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFORETENCAOICMSTRANSPORTE,
				nfnotainforetencaoicmstransporte, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFORETENCAOICMSTRANSPORTE,
				AcaoEnum.DELETE, nfnotainforetencaoicmstransporte.getTransactionId(), getHistoricoBAR(), response,
				nfnotainforetencaoicmstransporte.getId(), nfnotainforetencaoicmstransporte.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoRetencaoICMSTransporteBAR#
	 * deleteAllNFNotaInfoRetencaoICMSTransportes()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoRetencaoICMSTransportes() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFORETENCAOICMSTRANSPORTE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoRetencaoICMSTransporteBAR#
	 * fetchNFNotaInfoRetencaoICMSTransporteById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoRetencaoICMSTransporte fetchNFNotaInfoRetencaoICMSTransporteById(FetchByIdRequest request) {
		return (NFNotaInfoRetencaoICMSTransporte) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFORETENCAOICMSTRANSPORTE, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoRetencaoICMSTransporteBAR#
	 * fetchAllNFNotaInfoRetencaoICMSTransportesCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoRetencaoICMSTransporte> fetchAllNFNotaInfoRetencaoICMSTransportes(
			NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporte) {
		InternalResultsResponse<NFNotaInfoRetencaoICMSTransporte> response = new InternalResultsResponse<NFNotaInfoRetencaoICMSTransporte>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFORETENCAOICMSTRANSPORTE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoRetencaoICMSTransporteBAR#
	 * fetchNFNotaInfoRetencaoICMSTransportesByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoRetencaoICMSTransporte> fetchNFNotaInfoRetencaoICMSTransportesByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoRetencaoICMSTransporte> response = new InternalResultsResponse<NFNotaInfoRetencaoICMSTransporte>();
		fetchNFNotaInfoRetencaoICMSTransportesByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFORETENCAOICMSTRANSPORTE_COUNT,
				STMT_FETCH_NFNOTAINFORETENCAOICMSTRANSPORTE_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoRetencaoICMSTransportesByRequest
	// ####======================================

	public static void fetchNFNotaInfoRetencaoICMSTransportesByRequest(SqlSession sqlSession,
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

	// ===================================### NFNOTAINFOTRANSPORTADOR
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTransportadorBAR#insertNFNotaInfoTransportador(com.qat.samples.sysmgmt.base.model.NFNotaInfoTransportador)
	 */
	@Override
	public InternalResponse insertNFNotaInfoTransportador(NFNotaInfoTransportador nfnotainfotransportador) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfotransportador.setProcessId(nfnotainfotransportador.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOTRANSPORTADOR, nfnotainfotransportador,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOTRANSPORTADOR, AcaoEnum.INSERT,
				nfnotainfotransportador.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfotransportador.getId(), nfnotainfotransportador.getUserId());

		if (nfnotainfotransportador.getId() != 0 && nfnotainfotransportador.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfotransportador.getId(), null,
					AcaoEnum.INSERT, nfnotainfotransportador.getCreateUser(), nfnotainfotransportador.getId(),
					TabelaEnum.NFNOTAINFOTRANSPORTADOR, statusBAR, historicoBAR,
					nfnotainfotransportador.getTransactionId(), nfnotainfotransportador.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTransportadorBAR#
	 * updateNFNotaInfoTransportador(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoTransportador)
	 */
	@Override
	public InternalResponse updateNFNotaInfoTransportador(NFNotaInfoTransportador nfnotainfotransportador) {
		InternalResponse response = new InternalResponse();
		nfnotainfotransportador.setProcessId(nfnotainfotransportador.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOTRANSPORTADOR, nfnotainfotransportador,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOTRANSPORTADOR, AcaoEnum.UPDATE,
				nfnotainfotransportador.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfotransportador.getId(), nfnotainfotransportador.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTransportadorBAR#
	 * deleteNFNotaInfoTransportador(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoTransportador)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoTransportadorById(NFNotaInfoTransportador nfnotainfotransportador) {
		InternalResponse response = new InternalResponse();
		nfnotainfotransportador.setProcessId(nfnotainfotransportador.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOTRANSPORTADOR, nfnotainfotransportador,
				response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOTRANSPORTADOR, AcaoEnum.DELETE,
				nfnotainfotransportador.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfotransportador.getId(), nfnotainfotransportador.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTransportadorBAR#
	 * deleteAllNFNotaInfoTransportadors()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoTransportadors() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOTRANSPORTADOR_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoTransportadorBAR#
	 * fetchNFNotaInfoTransportadorById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoTransportador fetchNFNotaInfoTransportadorById(FetchByIdRequest request) {
		return (NFNotaInfoTransportador) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOTRANSPORTADOR, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoTransportadorBAR#
	 * fetchAllNFNotaInfoTransportadorsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoTransportador> fetchAllNFNotaInfoTransportadors(
			NFNotaInfoTransportador nfnotainfotransportador) {
		InternalResultsResponse<NFNotaInfoTransportador> response = new InternalResultsResponse<NFNotaInfoTransportador>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOTRANSPORTADOR_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoTransportadorBAR#
	 * fetchNFNotaInfoTransportadorsByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoTransportador> fetchNFNotaInfoTransportadorsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoTransportador> response = new InternalResultsResponse<NFNotaInfoTransportador>();
		fetchNFNotaInfoTransportadorsByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOTRANSPORTADOR_COUNT,
				STMT_FETCH_NFNOTAINFOTRANSPORTADOR_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoTransportadorsByRequest
	// ####======================================

	public static void fetchNFNotaInfoTransportadorsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOVEICULO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoVeiculoBAR#insertNFNotaInfoVeiculo(com.qat.samples.sysmgmt.base.model.NFNotaInfoVeiculo)
	 */
	@Override
	public InternalResponse insertNFNotaInfoVeiculo(NFNotaInfoVeiculo nfnotainfoveiculo) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoveiculo.setProcessId(nfnotainfoveiculo.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOVEICULO, nfnotainfoveiculo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOVEICULO, AcaoEnum.INSERT,
				nfnotainfoveiculo.getTransactionId(), getHistoricoBAR(), response, nfnotainfoveiculo.getId(),
				nfnotainfoveiculo.getUserId());

		if (nfnotainfoveiculo.getId() != 0 && nfnotainfoveiculo.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfoveiculo.getId(), null,
					AcaoEnum.INSERT, nfnotainfoveiculo.getCreateUser(), nfnotainfoveiculo.getId(),
					TabelaEnum.NFNOTAINFOVEICULO, statusBAR, historicoBAR, nfnotainfoveiculo.getTransactionId(),
					nfnotainfoveiculo.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoVeiculoBAR#
	 * updateNFNotaInfoVeiculo(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoVeiculo)
	 */
	@Override
	public InternalResponse updateNFNotaInfoVeiculo(NFNotaInfoVeiculo nfnotainfoveiculo) {
		InternalResponse response = new InternalResponse();
		nfnotainfoveiculo.setProcessId(nfnotainfoveiculo.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOVEICULO, nfnotainfoveiculo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOVEICULO, AcaoEnum.UPDATE,
				nfnotainfoveiculo.getTransactionId(), getHistoricoBAR(), response, nfnotainfoveiculo.getId(),
				nfnotainfoveiculo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoVeiculoBAR#
	 * deleteNFNotaInfoVeiculo(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoVeiculo)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoVeiculoById(NFNotaInfoVeiculo nfnotainfoveiculo) {
		InternalResponse response = new InternalResponse();
		nfnotainfoveiculo.setProcessId(nfnotainfoveiculo.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOVEICULO, nfnotainfoveiculo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOVEICULO, AcaoEnum.DELETE,
				nfnotainfoveiculo.getTransactionId(), getHistoricoBAR(), response, nfnotainfoveiculo.getId(),
				nfnotainfoveiculo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoVeiculoBAR#
	 * deleteAllNFNotaInfoVeiculos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoVeiculos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOVEICULO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoVeiculoBAR#
	 * fetchNFNotaInfoVeiculoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoVeiculo fetchNFNotaInfoVeiculoById(FetchByIdRequest request) {
		return (NFNotaInfoVeiculo) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOVEICULO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoVeiculoBAR#
	 * fetchAllNFNotaInfoVeiculosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoVeiculo> fetchAllNFNotaInfoVeiculos(NFNotaInfoVeiculo nfnotainfoveiculo) {
		InternalResultsResponse<NFNotaInfoVeiculo> response = new InternalResultsResponse<NFNotaInfoVeiculo>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOVEICULO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoVeiculoBAR#
	 * fetchNFNotaInfoVeiculosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoVeiculo> fetchNFNotaInfoVeiculosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoVeiculo> response = new InternalResultsResponse<NFNotaInfoVeiculo>();
		fetchNFNotaInfoVeiculosByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOVEICULO_COUNT,
				STMT_FETCH_NFNOTAINFOVEICULO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoVeiculosByRequest
	// ####======================================

	public static void fetchNFNotaInfoVeiculosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOREBOQUE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoReboqueBAR#insertNFNotaInfoReboque(com.qat.samples.sysmgmt.base.model.NFNotaInfoReboque)
	 */
	@Override
	public InternalResponse insertNFNotaInfoReboque(NFNotaInfoReboque nfnotainforeboque) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainforeboque.setProcessId(nfnotainforeboque.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOREBOQUE, nfnotainforeboque, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOREBOQUE, AcaoEnum.INSERT,
				nfnotainforeboque.getTransactionId(), getHistoricoBAR(), response, nfnotainforeboque.getId(),
				nfnotainforeboque.getUserId());

		if (nfnotainforeboque.getId() != 0 && nfnotainforeboque.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainforeboque.getId(), null,
					AcaoEnum.INSERT, nfnotainforeboque.getCreateUser(), nfnotainforeboque.getId(),
					TabelaEnum.NFNOTAINFOREBOQUE, statusBAR, historicoBAR, nfnotainforeboque.getTransactionId(),
					nfnotainforeboque.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoReboqueBAR#
	 * updateNFNotaInfoReboque(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoReboque)
	 */
	@Override
	public InternalResponse updateNFNotaInfoReboque(NFNotaInfoReboque nfnotainforeboque) {
		InternalResponse response = new InternalResponse();
		nfnotainforeboque.setProcessId(nfnotainforeboque.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOREBOQUE, nfnotainforeboque, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOREBOQUE, AcaoEnum.UPDATE,
				nfnotainforeboque.getTransactionId(), getHistoricoBAR(), response, nfnotainforeboque.getId(),
				nfnotainforeboque.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoReboqueBAR#
	 * deleteNFNotaInfoReboque(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoReboque)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoReboqueById(NFNotaInfoReboque nfnotainforeboque) {
		InternalResponse response = new InternalResponse();
		nfnotainforeboque.setProcessId(nfnotainforeboque.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOREBOQUE, nfnotainforeboque, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOREBOQUE, AcaoEnum.DELETE,
				nfnotainforeboque.getTransactionId(), getHistoricoBAR(), response, nfnotainforeboque.getId(),
				nfnotainforeboque.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoReboqueBAR#
	 * deleteAllNFNotaInfoReboques()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoReboques() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOREBOQUE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoReboqueBAR#
	 * fetchNFNotaInfoReboqueById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoReboque fetchNFNotaInfoReboqueById(FetchByIdRequest request) {
		return (NFNotaInfoReboque) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOREBOQUE,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoReboqueBAR#
	 * fetchAllNFNotaInfoReboquesCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoReboque> fetchAllNFNotaInfoReboques(NFNotaInfoReboque nfnotainforeboque) {
		InternalResultsResponse<NFNotaInfoReboque> response = new InternalResultsResponse<NFNotaInfoReboque>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOREBOQUE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoReboqueBAR#
	 * fetchNFNotaInfoReboquesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoReboque> fetchNFNotaInfoReboquesByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoReboque> response = new InternalResultsResponse<NFNotaInfoReboque>();
		fetchNFNotaInfoReboquesByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOREBOQUE_COUNT,
				STMT_FETCH_NFNOTAINFOREBOQUE_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoReboquesByRequest
	// ####======================================

	public static void fetchNFNotaInfoReboquesByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOCOBRANCA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCobrancaBAR#insertNFNotaInfoCobranca(com.qat.samples.sysmgmt.base.model.NFNotaInfoCobranca)
	 */
	@Override
	public InternalResponse insertNFNotaInfoCobranca(NFNotaInfoCobranca nfnotainfocobranca) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfocobranca.setProcessId(nfnotainfocobranca.getTransactionId());

		if (!ValidationUtil.isNull(nfnotainfocobranca.getFatura()))
		{
			count +=
					NFNotaInfoFaturaBARD.maintainNFNotaInfoFaturaAssociations(nfnotainfocobranca.getFatura(), response, nfnotainfocobranca.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOCOBRANCA, getNfeBAR(), statusBAR, historicoBAR, nfnotainfocobranca.getId(),
							nfnotainfocobranca.getCreateUser(), nfnotainfocobranca.getTransactionId(), nfnotainfocobranca.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfocobranca.getDuplicatas()))
		{
			count +=
					NFNotaInfoDuplicataBARD.maintainNFNotaInfoDuplicataAssociations(nfnotainfocobranca.getDuplicatas(), response, nfnotainfocobranca.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOCOBRANCA, getNfeBAR(), statusBAR, historicoBAR, nfnotainfocobranca.getId(),
							nfnotainfocobranca.getCreateUser(), nfnotainfocobranca.getTransactionId(), nfnotainfocobranca.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOCOBRANCA, nfnotainfocobranca, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCOBRANCA, AcaoEnum.INSERT,
				nfnotainfocobranca.getTransactionId(), getHistoricoBAR(), response, nfnotainfocobranca.getId(),
				nfnotainfocobranca.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCobrancaBAR#
	 * updateNFNotaInfoCobranca(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoCobranca)
	 */
	@Override
	public InternalResponse updateNFNotaInfoCobranca(NFNotaInfoCobranca nfnotainfocobranca) {
		InternalResponse response = new InternalResponse();
		nfnotainfocobranca.setProcessId(nfnotainfocobranca.getTransactionId());
		Integer count = 0;
		if (!ValidationUtil.isNull(nfnotainfocobranca.getFatura()))
		{
			count +=
					NFNotaInfoFaturaBARD.maintainNFNotaInfoFaturaAssociations(nfnotainfocobranca.getFatura(), response, nfnotainfocobranca.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOCOBRANCA, getNfeBAR(), statusBAR, historicoBAR, nfnotainfocobranca.getId(),
							nfnotainfocobranca.getCreateUser(), nfnotainfocobranca.getTransactionId(), nfnotainfocobranca.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfocobranca.getDuplicatas()))
		{
			count +=
					NFNotaInfoDuplicataBARD.maintainNFNotaInfoDuplicataAssociations(nfnotainfocobranca.getDuplicatas(), response, nfnotainfocobranca.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOCOBRANCA, getNfeBAR(), statusBAR, historicoBAR, nfnotainfocobranca.getId(),
							nfnotainfocobranca.getCreateUser(), nfnotainfocobranca.getTransactionId(), nfnotainfocobranca.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOCOBRANCA, nfnotainfocobranca, response);

		count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCOBRANCA, AcaoEnum.UPDATE,
				nfnotainfocobranca.getTransactionId(), getHistoricoBAR(), response, nfnotainfocobranca.getId(),
				nfnotainfocobranca.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCobrancaBAR#
	 * deleteNFNotaInfoCobranca(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoCobranca)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoCobrancaById(NFNotaInfoCobranca nfnotainfocobranca) {
		InternalResponse response = new InternalResponse();
		nfnotainfocobranca.setProcessId(nfnotainfocobranca.getTransactionId());

		Integer count = 0;
		if (!ValidationUtil.isNull(nfnotainfocobranca.getFatura()))
		{
			count +=
					NFNotaInfoFaturaBARD.maintainNFNotaInfoFaturaAssociations(nfnotainfocobranca.getFatura(), response, nfnotainfocobranca.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOCOBRANCA, getNfeBAR(), statusBAR, historicoBAR, nfnotainfocobranca.getId(),
							nfnotainfocobranca.getCreateUser(), nfnotainfocobranca.getTransactionId(), nfnotainfocobranca.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfocobranca.getDuplicatas()))
		{
			count +=
					NFNotaInfoDuplicataBARD.maintainNFNotaInfoDuplicataAssociations(nfnotainfocobranca.getDuplicatas(), response, nfnotainfocobranca.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOCOBRANCA, getNfeBAR(), statusBAR, historicoBAR, nfnotainfocobranca.getId(),
							nfnotainfocobranca.getCreateUser(), nfnotainfocobranca.getTransactionId(), nfnotainfocobranca.getTransactionId());
		}

		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOCOBRANCA, nfnotainfocobranca, response);

		 count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCOBRANCA, AcaoEnum.DELETE,
				nfnotainfocobranca.getTransactionId(), getHistoricoBAR(), response, nfnotainfocobranca.getId(),
				nfnotainfocobranca.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCobrancaBAR#
	 * deleteAllNFNotaInfoCobrancas()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoCobrancas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOCOBRANCA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoCobrancaBAR#
	 * fetchNFNotaInfoCobrancaById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoCobranca fetchNFNotaInfoCobrancaById(FetchByIdRequest request) {
		return (NFNotaInfoCobranca) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOCOBRANCA,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCobrancaBAR#
	 * fetchAllNFNotaInfoCobrancasCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoCobranca> fetchAllNFNotaInfoCobrancas(
			NFNotaInfoCobranca nfnotainfocobranca) {
		InternalResultsResponse<NFNotaInfoCobranca> response = new InternalResultsResponse<NFNotaInfoCobranca>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOCOBRANCA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoCobrancaBAR#
	 * fetchNFNotaInfoCobrancasByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoCobranca> fetchNFNotaInfoCobrancasByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoCobranca> response = new InternalResultsResponse<NFNotaInfoCobranca>();
		fetchNFNotaInfoCobrancasByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOCOBRANCA_COUNT,
				STMT_FETCH_NFNOTAINFOCOBRANCA_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoCobrancasByRequest
	// ####======================================

	public static void fetchNFNotaInfoCobrancasByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFODUPLICATA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoDuplicataBAR#insertNFNotaInfoDuplicata(com.qat.samples.sysmgmt.base.model.NFNotaInfoDuplicata)
	 */
	@Override
	public InternalResponse insertNFNotaInfoDuplicata(NFNotaInfoDuplicata nfnotainfoduplicata) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoduplicata.setProcessId(nfnotainfoduplicata.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFODUPLICATA, nfnotainfoduplicata, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFODUPLICATA, AcaoEnum.INSERT,
				nfnotainfoduplicata.getTransactionId(), getHistoricoBAR(), response, nfnotainfoduplicata.getId(),
				nfnotainfoduplicata.getUserId());

		if (nfnotainfoduplicata.getId() != 0 && nfnotainfoduplicata.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfoduplicata.getId(), null,
					AcaoEnum.INSERT, nfnotainfoduplicata.getCreateUser(), nfnotainfoduplicata.getId(),
					TabelaEnum.NFNOTAINFODUPLICATA, statusBAR, historicoBAR, nfnotainfoduplicata.getTransactionId(),
					nfnotainfoduplicata.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoDuplicataBAR#
	 * updateNFNotaInfoDuplicata(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoDuplicata)
	 */
	@Override
	public InternalResponse updateNFNotaInfoDuplicata(NFNotaInfoDuplicata nfnotainfoduplicata) {
		InternalResponse response = new InternalResponse();
		nfnotainfoduplicata.setProcessId(nfnotainfoduplicata.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFODUPLICATA, nfnotainfoduplicata, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFODUPLICATA, AcaoEnum.UPDATE,
				nfnotainfoduplicata.getTransactionId(), getHistoricoBAR(), response, nfnotainfoduplicata.getId(),
				nfnotainfoduplicata.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoDuplicataBAR#
	 * deleteNFNotaInfoDuplicata(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoDuplicata)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoDuplicataById(NFNotaInfoDuplicata nfnotainfoduplicata) {
		InternalResponse response = new InternalResponse();
		nfnotainfoduplicata.setProcessId(nfnotainfoduplicata.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFODUPLICATA, nfnotainfoduplicata, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFODUPLICATA, AcaoEnum.DELETE,
				nfnotainfoduplicata.getTransactionId(), getHistoricoBAR(), response, nfnotainfoduplicata.getId(),
				nfnotainfoduplicata.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoDuplicataBAR#
	 * deleteAllNFNotaInfoDuplicatas()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoDuplicatas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFODUPLICATA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoDuplicataBAR#
	 * fetchNFNotaInfoDuplicataById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoDuplicata fetchNFNotaInfoDuplicataById(FetchByIdRequest request) {
		return (NFNotaInfoDuplicata) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFODUPLICATA,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoDuplicataBAR#
	 * fetchAllNFNotaInfoDuplicatasCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoDuplicata> fetchAllNFNotaInfoDuplicatas(
			NFNotaInfoDuplicata nfnotainfoduplicata) {
		InternalResultsResponse<NFNotaInfoDuplicata> response = new InternalResultsResponse<NFNotaInfoDuplicata>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFODUPLICATA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoDuplicataBAR#
	 * fetchNFNotaInfoDuplicatasByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoDuplicata> fetchNFNotaInfoDuplicatasByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoDuplicata> response = new InternalResultsResponse<NFNotaInfoDuplicata>();
		fetchNFNotaInfoDuplicatasByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFODUPLICATA_COUNT,
				STMT_FETCH_NFNOTAINFODUPLICATA_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoDuplicatasByRequest
	// ####======================================

	public static void fetchNFNotaInfoDuplicatasByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOFATURA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoFaturaBAR#insertNFNotaInfoFatura(com.qat.samples.sysmgmt.base.model.NFNotaInfoFatura)
	 */
	@Override
	public InternalResponse insertNFNotaInfoFatura(NFNotaInfoFatura nfnotainfofatura) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfofatura.setProcessId(nfnotainfofatura.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOFATURA, nfnotainfofatura, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOFATURA, AcaoEnum.INSERT,
				nfnotainfofatura.getTransactionId(), getHistoricoBAR(), response, nfnotainfofatura.getId(),
				nfnotainfofatura.getUserId());

		if (nfnotainfofatura.getId() != 0 && nfnotainfofatura.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfofatura.getId(), null,
					AcaoEnum.INSERT, nfnotainfofatura.getCreateUser(), nfnotainfofatura.getId(),
					TabelaEnum.NFNOTAINFOFATURA, statusBAR, historicoBAR, nfnotainfofatura.getTransactionId(),
					nfnotainfofatura.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoFaturaBAR#
	 * updateNFNotaInfoFatura(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoFatura)
	 */
	@Override
	public InternalResponse updateNFNotaInfoFatura(NFNotaInfoFatura nfnotainfofatura) {
		InternalResponse response = new InternalResponse();
		nfnotainfofatura.setProcessId(nfnotainfofatura.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOFATURA, nfnotainfofatura, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOFATURA, AcaoEnum.UPDATE,
				nfnotainfofatura.getTransactionId(), getHistoricoBAR(), response, nfnotainfofatura.getId(),
				nfnotainfofatura.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoFaturaBAR#
	 * deleteNFNotaInfoFatura(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoFatura)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoFaturaById(NFNotaInfoFatura nfnotainfofatura) {
		InternalResponse response = new InternalResponse();
		nfnotainfofatura.setProcessId(nfnotainfofatura.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOFATURA, nfnotainfofatura, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOFATURA, AcaoEnum.DELETE,
				nfnotainfofatura.getTransactionId(), getHistoricoBAR(), response, nfnotainfofatura.getId(),
				nfnotainfofatura.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoFaturaBAR#
	 * deleteAllNFNotaInfoFaturas()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoFaturas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOFATURA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoFaturaBAR#
	 * fetchNFNotaInfoFaturaById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoFatura fetchNFNotaInfoFaturaById(FetchByIdRequest request) {
		return (NFNotaInfoFatura) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOFATURA,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoFaturaBAR#
	 * fetchAllNFNotaInfoFaturasCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoFatura> fetchAllNFNotaInfoFaturas(NFNotaInfoFatura nfnotainfofatura) {
		InternalResultsResponse<NFNotaInfoFatura> response = new InternalResultsResponse<NFNotaInfoFatura>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOFATURA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoFaturaBAR#
	 * fetchNFNotaInfoFaturasByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoFatura> fetchNFNotaInfoFaturasByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoFatura> response = new InternalResultsResponse<NFNotaInfoFatura>();
		fetchNFNotaInfoFaturasByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOFATURA_COUNT,
				STMT_FETCH_NFNOTAINFOFATURA_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoFaturasByRequest
	// ####======================================

	public static void fetchNFNotaInfoFaturasByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOCARTAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCartaoBAR#insertNFNotaInfoCartao(com.qat.samples.sysmgmt.base.model.NFNotaInfoCartao)
	 */
	@Override
	public InternalResponse insertNFNotaInfoCartao(NFNotaInfoCartao nfnotainfocartao) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfocartao.setProcessId(nfnotainfocartao.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOCARTAO, nfnotainfocartao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCARTAO, AcaoEnum.INSERT,
				nfnotainfocartao.getTransactionId(), getHistoricoBAR(), response, nfnotainfocartao.getId(),
				nfnotainfocartao.getUserId());

		if (nfnotainfocartao.getId() != 0 && nfnotainfocartao.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfocartao.getId(), null,
					AcaoEnum.INSERT, nfnotainfocartao.getCreateUser(), nfnotainfocartao.getId(),
					TabelaEnum.NFNOTAINFOCARTAO, statusBAR, historicoBAR, nfnotainfocartao.getTransactionId(),
					nfnotainfocartao.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCartaoBAR#
	 * updateNFNotaInfoCartao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoCartao)
	 */
	@Override
	public InternalResponse updateNFNotaInfoCartao(NFNotaInfoCartao nfnotainfocartao) {
		InternalResponse response = new InternalResponse();
		nfnotainfocartao.setProcessId(nfnotainfocartao.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOCARTAO, nfnotainfocartao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCARTAO, AcaoEnum.UPDATE,
				nfnotainfocartao.getTransactionId(), getHistoricoBAR(), response, nfnotainfocartao.getId(),
				nfnotainfocartao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCartaoBAR#
	 * deleteNFNotaInfoCartao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoCartao)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoCartaoById(NFNotaInfoCartao nfnotainfocartao) {
		InternalResponse response = new InternalResponse();
		nfnotainfocartao.setProcessId(nfnotainfocartao.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOCARTAO, nfnotainfocartao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCARTAO, AcaoEnum.DELETE,
				nfnotainfocartao.getTransactionId(), getHistoricoBAR(), response, nfnotainfocartao.getId(),
				nfnotainfocartao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCartaoBAR#
	 * deleteAllNFNotaInfoCartaos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoCartaos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOCARTAO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoCartaoBAR#
	 * fetchNFNotaInfoCartaoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoCartao fetchNFNotaInfoCartaoById(FetchByIdRequest request) {
		return (NFNotaInfoCartao) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOCARTAO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCartaoBAR#
	 * fetchAllNFNotaInfoCartaosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoCartao> fetchAllNFNotaInfoCartaos(NFNotaInfoCartao nfnotainfocartao) {
		InternalResultsResponse<NFNotaInfoCartao> response = new InternalResultsResponse<NFNotaInfoCartao>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOCARTAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoCartaoBAR#
	 * fetchNFNotaInfoCartaosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoCartao> fetchNFNotaInfoCartaosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoCartao> response = new InternalResultsResponse<NFNotaInfoCartao>();
		fetchNFNotaInfoCartaosByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOCARTAO_COUNT,
				STMT_FETCH_NFNOTAINFOCARTAO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoCartaosByRequest
	// ####======================================

	public static void fetchNFNotaInfoCartaosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOPAGAMENTO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoPagamentoBAR#insertNFNotaInfoPagamento(com.qat.samples.sysmgmt.base.model.NFNotaInfoPagamento)
	 */
	@Override
	public InternalResponse insertNFNotaInfoPagamento(NFNotaInfoPagamento nfnotainfopagamento) {
		InternalResponse response = new InternalResponse();


		nfnotainfopagamento.setProcessId(nfnotainfopagamento.getTransactionId());

		Integer count = 0;
		if (!ValidationUtil.isNull(nfnotainfopagamento.getCartao()))
		{
			count +=
					NFNotaInfoCartaoBARD.maintainNFNotaInfoCartaoAssociations(nfnotainfopagamento.getCartao(), response, nfnotainfopagamento.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOPAGAMENTO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfopagamento.getId(),
							nfnotainfopagamento.getCreateUser(), nfnotainfopagamento.getTransactionId(), nfnotainfopagamento.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOPAGAMENTO, nfnotainfopagamento, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOPAGAMENTO, AcaoEnum.INSERT,
				nfnotainfopagamento.getTransactionId(), getHistoricoBAR(), response, nfnotainfopagamento.getId(),
				nfnotainfopagamento.getUserId());


		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoPagamentoBAR#
	 * updateNFNotaInfoPagamento(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoPagamento)
	 */
	@Override
	public InternalResponse updateNFNotaInfoPagamento(NFNotaInfoPagamento nfnotainfopagamento) {
		InternalResponse response = new InternalResponse();
		nfnotainfopagamento.setProcessId(nfnotainfopagamento.getTransactionId());

		Integer count = 0;
		if (!ValidationUtil.isNull(nfnotainfopagamento.getCartao()))
		{
			count +=
					NFNotaInfoCartaoBARD.maintainNFNotaInfoCartaoAssociations(nfnotainfopagamento.getCartao(), response, nfnotainfopagamento.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOPAGAMENTO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfopagamento.getId(),
							nfnotainfopagamento.getCreateUser(), nfnotainfopagamento.getTransactionId(), nfnotainfopagamento.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOPAGAMENTO, nfnotainfopagamento, response);


		count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOPAGAMENTO, AcaoEnum.UPDATE,
				nfnotainfopagamento.getTransactionId(), getHistoricoBAR(), response, nfnotainfopagamento.getId(),
				nfnotainfopagamento.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoPagamentoBAR#
	 * deleteNFNotaInfoPagamento(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoPagamento)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoPagamentoById(NFNotaInfoPagamento nfnotainfopagamento) {
		InternalResponse response = new InternalResponse();
		nfnotainfopagamento.setProcessId(nfnotainfopagamento.getTransactionId());

		Integer count = 0;
		if (!ValidationUtil.isNull(nfnotainfopagamento.getCartao()))
		{
			count +=
					NFNotaInfoCartaoBARD.maintainNFNotaInfoCartaoAssociations(nfnotainfopagamento.getCartao(), response, nfnotainfopagamento.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOPAGAMENTO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfopagamento.getId(),
							nfnotainfopagamento.getCreateUser(), nfnotainfopagamento.getTransactionId(), nfnotainfopagamento.getTransactionId());
		}

		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOPAGAMENTO, nfnotainfopagamento, response);

		 count = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOPAGAMENTO, AcaoEnum.DELETE,
				nfnotainfopagamento.getTransactionId(), getHistoricoBAR(), response, nfnotainfopagamento.getId(),
				nfnotainfopagamento.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoPagamentoBAR#
	 * deleteAllNFNotaInfoPagamentos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoPagamentos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOPAGAMENTO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoPagamentoBAR#
	 * fetchNFNotaInfoPagamentoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoPagamento fetchNFNotaInfoPagamentoById(FetchByIdRequest request) {
		return (NFNotaInfoPagamento) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOPAGAMENTO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoPagamentoBAR#
	 * fetchAllNFNotaInfoPagamentosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoPagamento> fetchAllNFNotaInfoPagamentos(
			NFNotaInfoPagamento nfnotainfopagamento) {
		InternalResultsResponse<NFNotaInfoPagamento> response = new InternalResultsResponse<NFNotaInfoPagamento>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOPAGAMENTO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoPagamentoBAR#
	 * fetchNFNotaInfoPagamentosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoPagamento> fetchNFNotaInfoPagamentosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoPagamento> response = new InternalResultsResponse<NFNotaInfoPagamento>();
		fetchNFNotaInfoPagamentosByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOPAGAMENTO_COUNT,
				STMT_FETCH_NFNOTAINFOPAGAMENTO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoPagamentosByRequest
	// ####======================================

	public static void fetchNFNotaInfoPagamentosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOINFORMACOESADICIONAIS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoInformacoesAdicionaisBAR#insertNFNotaInfoInformacoesAdicionais(com.qat.samples.sysmgmt.base.model.NFNotaInfoInformacoesAdicionais)
	 */
	@Override
	public InternalResponse insertNFNotaInfoInformacoesAdicionais(
			NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionais) {
		InternalResponse response = new InternalResponse();

		Boolean count1 = false;

		nfnotainfoinformacoesadicionais.setProcessId(nfnotainfoinformacoesadicionais.getTransactionId());

		Integer count = 0;
		if (!ValidationUtil.isNullOrEmpty(nfnotainfoinformacoesadicionais.getObservacoescontribuinte()))
		{
			count +=
					NFNotaInfoObservacaoBARD.maintainNFNotaInfoObservacaoAssociations(nfnotainfoinformacoesadicionais.getObservacoescontribuinte(), response, nfnotainfoinformacoesadicionais.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, getNfeBAR(), statusBAR, historicoBAR, nfnotainfoinformacoesadicionais.getId(),
							nfnotainfoinformacoesadicionais.getCreateUser(), nfnotainfoinformacoesadicionais.getTransactionId(), nfnotainfoinformacoesadicionais.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(nfnotainfoinformacoesadicionais.getObservacoesfisco()))
		{
			count +=
					NFNotaInfoObservacaoBARD.maintainNFNotaInfoObservacaoAssociations(nfnotainfoinformacoesadicionais.getObservacoesfisco(), response, nfnotainfoinformacoesadicionais.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, getNfeBAR(), statusBAR, historicoBAR, nfnotainfoinformacoesadicionais.getId(),
							nfnotainfoinformacoesadicionais.getCreateUser(), nfnotainfoinformacoesadicionais.getTransactionId(), nfnotainfoinformacoesadicionais.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(nfnotainfoinformacoesadicionais.getProcessosrefenciado()))
		{
			count +=
					NFNotaInfoProcessoReferenciadoBARD.maintainNFNotaInfoProcessoReferenciadoAssociations(nfnotainfoinformacoesadicionais.getProcessosrefenciado(), response, nfnotainfoinformacoesadicionais.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, getNfeBAR(), statusBAR, historicoBAR, nfnotainfoinformacoesadicionais.getId(),
							nfnotainfoinformacoesadicionais.getCreateUser(), nfnotainfoinformacoesadicionais.getTransactionId(), nfnotainfoinformacoesadicionais.getTransactionId());
		}

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOINFORMACOESADICIONAIS,
				nfnotainfoinformacoesadicionais, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS,
				AcaoEnum.INSERT, nfnotainfoinformacoesadicionais.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoinformacoesadicionais.getId(), nfnotainfoinformacoesadicionais.getUserId());

		if (nfnotainfoinformacoesadicionais.getId() != 0 && nfnotainfoinformacoesadicionais.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response,
					nfnotainfoinformacoesadicionais.getId(), null, AcaoEnum.INSERT,
					nfnotainfoinformacoesadicionais.getCreateUser(), nfnotainfoinformacoesadicionais.getId(),
					TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, statusBAR, historicoBAR,
					nfnotainfoinformacoesadicionais.getTransactionId(),
					nfnotainfoinformacoesadicionais.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoInformacoesAdicionaisBAR#
	 * updateNFNotaInfoInformacoesAdicionais(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoInformacoesAdicionais)
	 */
	@Override
	public InternalResponse updateNFNotaInfoInformacoesAdicionais(
			NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionais) {
		InternalResponse response = new InternalResponse();
		nfnotainfoinformacoesadicionais.setProcessId(nfnotainfoinformacoesadicionais.getTransactionId());
		Integer count = 0;
		if (!ValidationUtil.isNullOrEmpty(nfnotainfoinformacoesadicionais.getObservacoescontribuinte()))
		{
			count +=
					NFNotaInfoObservacaoBARD.maintainNFNotaInfoObservacaoAssociations(nfnotainfoinformacoesadicionais.getObservacoescontribuinte(), response, nfnotainfoinformacoesadicionais.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, getNfeBAR(), statusBAR, historicoBAR, nfnotainfoinformacoesadicionais.getId(),
							nfnotainfoinformacoesadicionais.getCreateUser(), nfnotainfoinformacoesadicionais.getTransactionId(), nfnotainfoinformacoesadicionais.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(nfnotainfoinformacoesadicionais.getObservacoesfisco()))
		{
			count +=
					NFNotaInfoObservacaoBARD.maintainNFNotaInfoObservacaoAssociations(nfnotainfoinformacoesadicionais.getObservacoesfisco(), response, nfnotainfoinformacoesadicionais.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, getNfeBAR(), statusBAR, historicoBAR, nfnotainfoinformacoesadicionais.getId(),
							nfnotainfoinformacoesadicionais.getCreateUser(), nfnotainfoinformacoesadicionais.getTransactionId(), nfnotainfoinformacoesadicionais.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(nfnotainfoinformacoesadicionais.getProcessosrefenciado()))
		{
			count +=
					NFNotaInfoProcessoReferenciadoBARD.maintainNFNotaInfoProcessoReferenciadoAssociations(nfnotainfoinformacoesadicionais.getProcessosrefenciado(), response, nfnotainfoinformacoesadicionais.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, getNfeBAR(), statusBAR, historicoBAR, nfnotainfoinformacoesadicionais.getId(),
							nfnotainfoinformacoesadicionais.getCreateUser(), nfnotainfoinformacoesadicionais.getTransactionId(), nfnotainfoinformacoesadicionais.getTransactionId());
		}
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOINFORMACOESADICIONAIS,
				nfnotainfoinformacoesadicionais, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS,
				AcaoEnum.UPDATE, nfnotainfoinformacoesadicionais.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoinformacoesadicionais.getId(), nfnotainfoinformacoesadicionais.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoInformacoesAdicionaisBAR#
	 * deleteNFNotaInfoInformacoesAdicionais(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoInformacoesAdicionais)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoInformacoesAdicionaisById(
			NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionais) {
		InternalResponse response = new InternalResponse();
		nfnotainfoinformacoesadicionais.setProcessId(nfnotainfoinformacoesadicionais.getTransactionId());
		Integer count = 0;
		if (!ValidationUtil.isNullOrEmpty(nfnotainfoinformacoesadicionais.getObservacoescontribuinte()))
		{
			count +=
					NFNotaInfoObservacaoBARD.maintainNFNotaInfoObservacaoAssociations(nfnotainfoinformacoesadicionais.getObservacoescontribuinte(), response, nfnotainfoinformacoesadicionais.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, getNfeBAR(), statusBAR, historicoBAR, nfnotainfoinformacoesadicionais.getId(),
							nfnotainfoinformacoesadicionais.getCreateUser(), nfnotainfoinformacoesadicionais.getTransactionId(), nfnotainfoinformacoesadicionais.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(nfnotainfoinformacoesadicionais.getObservacoesfisco()))
		{
			count +=
					NFNotaInfoObservacaoBARD.maintainNFNotaInfoObservacaoAssociations(nfnotainfoinformacoesadicionais.getObservacoesfisco(), response, nfnotainfoinformacoesadicionais.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, getNfeBAR(), statusBAR, historicoBAR, nfnotainfoinformacoesadicionais.getId(),
							nfnotainfoinformacoesadicionais.getCreateUser(), nfnotainfoinformacoesadicionais.getTransactionId(), nfnotainfoinformacoesadicionais.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(nfnotainfoinformacoesadicionais.getProcessosrefenciado()))
		{
			count +=
					NFNotaInfoProcessoReferenciadoBARD.maintainNFNotaInfoProcessoReferenciadoAssociations(nfnotainfoinformacoesadicionais.getProcessosrefenciado(), response, nfnotainfoinformacoesadicionais.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, getNfeBAR(), statusBAR, historicoBAR, nfnotainfoinformacoesadicionais.getId(),
							nfnotainfoinformacoesadicionais.getCreateUser(), nfnotainfoinformacoesadicionais.getTransactionId(), nfnotainfoinformacoesadicionais.getTransactionId());
		}
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOINFORMACOESADICIONAIS,
				nfnotainfoinformacoesadicionais, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS,
				AcaoEnum.DELETE, nfnotainfoinformacoesadicionais.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoinformacoesadicionais.getId(), nfnotainfoinformacoesadicionais.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoInformacoesAdicionaisBAR#
	 * deleteAllNFNotaInfoInformacoesAdicionaiss()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoInformacoesAdicionaiss() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOINFORMACOESADICIONAIS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoInformacoesAdicionaisBAR#
	 * fetchNFNotaInfoInformacoesAdicionaisById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoInformacoesAdicionais fetchNFNotaInfoInformacoesAdicionaisById(FetchByIdRequest request) {
		return (NFNotaInfoInformacoesAdicionais) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOINFORMACOESADICIONAIS, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoInformacoesAdicionaisBAR#
	 * fetchAllNFNotaInfoInformacoesAdicionaissCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoInformacoesAdicionais> fetchAllNFNotaInfoInformacoesAdicionaiss(
			NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionais) {
		InternalResultsResponse<NFNotaInfoInformacoesAdicionais> response = new InternalResultsResponse<NFNotaInfoInformacoesAdicionais>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOINFORMACOESADICIONAIS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoInformacoesAdicionaisBAR#
	 * fetchNFNotaInfoInformacoesAdicionaissByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoInformacoesAdicionais> fetchNFNotaInfoInformacoesAdicionaissByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoInformacoesAdicionais> response = new InternalResultsResponse<NFNotaInfoInformacoesAdicionais>();
		fetchNFNotaInfoInformacoesAdicionaissByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOINFORMACOESADICIONAIS_COUNT,
				STMT_FETCH_NFNOTAINFOINFORMACOESADICIONAIS_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoInformacoesAdicionaissByRequest
	// ####======================================

	public static void fetchNFNotaInfoInformacoesAdicionaissByRequest(SqlSession sqlSession,
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

	// ===================================### NFNOTAINFOOBSERVACAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoObservacaoBAR#insertNFNotaInfoObservacao(com.qat.samples.sysmgmt.base.model.NFNotaInfoObservacao)
	 */
	@Override
	public InternalResponse insertNFNotaInfoObservacao(NFNotaInfoObservacao nfnotainfoobservacao) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoobservacao.setProcessId(nfnotainfoobservacao.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOOBSERVACAO, nfnotainfoobservacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOOBSERVACAO, AcaoEnum.INSERT,
				nfnotainfoobservacao.getTransactionId(), getHistoricoBAR(), response, nfnotainfoobservacao.getId(),
				nfnotainfoobservacao.getUserId());

		if (nfnotainfoobservacao.getId() != 0 && nfnotainfoobservacao.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfoobservacao.getId(), null,
					AcaoEnum.INSERT, nfnotainfoobservacao.getCreateUser(), nfnotainfoobservacao.getId(),
					TabelaEnum.NFNOTAINFOOBSERVACAO, statusBAR, historicoBAR, nfnotainfoobservacao.getTransactionId(),
					nfnotainfoobservacao.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoObservacaoBAR#
	 * updateNFNotaInfoObservacao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoObservacao)
	 */
	@Override
	public InternalResponse updateNFNotaInfoObservacao(NFNotaInfoObservacao nfnotainfoobservacao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoobservacao.setProcessId(nfnotainfoobservacao.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOOBSERVACAO, nfnotainfoobservacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOOBSERVACAO, AcaoEnum.UPDATE,
				nfnotainfoobservacao.getTransactionId(), getHistoricoBAR(), response, nfnotainfoobservacao.getId(),
				nfnotainfoobservacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoObservacaoBAR#
	 * deleteNFNotaInfoObservacao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoObservacao)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoObservacaoById(NFNotaInfoObservacao nfnotainfoobservacao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoobservacao.setProcessId(nfnotainfoobservacao.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOOBSERVACAO, nfnotainfoobservacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOOBSERVACAO, AcaoEnum.DELETE,
				nfnotainfoobservacao.getTransactionId(), getHistoricoBAR(), response, nfnotainfoobservacao.getId(),
				nfnotainfoobservacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoObservacaoBAR#
	 * deleteAllNFNotaInfoObservacaos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoObservacaos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOOBSERVACAO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoObservacaoBAR#
	 * fetchNFNotaInfoObservacaoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoObservacao fetchNFNotaInfoObservacaoById(FetchByIdRequest request) {
		return (NFNotaInfoObservacao) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOOBSERVACAO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoObservacaoBAR#
	 * fetchAllNFNotaInfoObservacaosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoObservacao> fetchAllNFNotaInfoObservacaos(
			NFNotaInfoObservacao nfnotainfoobservacao) {
		InternalResultsResponse<NFNotaInfoObservacao> response = new InternalResultsResponse<NFNotaInfoObservacao>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOOBSERVACAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoObservacaoBAR#
	 * fetchNFNotaInfoObservacaosByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoObservacao> fetchNFNotaInfoObservacaosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoObservacao> response = new InternalResultsResponse<NFNotaInfoObservacao>();
		fetchNFNotaInfoObservacaosByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOOBSERVACAO_COUNT,
				STMT_FETCH_NFNOTAINFOOBSERVACAO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoObservacaosByRequest
	// ####======================================

	public static void fetchNFNotaInfoObservacaosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOPROCESSOREFERENCIADO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoProcessoReferenciadoBAR#insertNFNotaInfoProcessoReferenciado(com.qat.samples.sysmgmt.base.model.NFNotaInfoProcessoReferenciado)
	 */
	@Override
	public InternalResponse insertNFNotaInfoProcessoReferenciado(
			NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciado) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoprocessoreferenciado.setProcessId(nfnotainfoprocessoreferenciado.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOPROCESSOREFERENCIADO,
				nfnotainfoprocessoreferenciado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOPROCESSOREFERENCIADO,
				AcaoEnum.INSERT, nfnotainfoprocessoreferenciado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoprocessoreferenciado.getId(), nfnotainfoprocessoreferenciado.getUserId());

		if (nfnotainfoprocessoreferenciado.getId() != 0 && nfnotainfoprocessoreferenciado.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfoprocessoreferenciado.getId(),
					null, AcaoEnum.INSERT, nfnotainfoprocessoreferenciado.getCreateUser(),
					nfnotainfoprocessoreferenciado.getId(), TabelaEnum.NFNOTAINFOPROCESSOREFERENCIADO, statusBAR,
					historicoBAR, nfnotainfoprocessoreferenciado.getTransactionId(),
					nfnotainfoprocessoreferenciado.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoProcessoReferenciadoBAR#
	 * updateNFNotaInfoProcessoReferenciado(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoProcessoReferenciado)
	 */
	@Override
	public InternalResponse updateNFNotaInfoProcessoReferenciado(
			NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciado) {
		InternalResponse response = new InternalResponse();
		nfnotainfoprocessoreferenciado.setProcessId(nfnotainfoprocessoreferenciado.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOPROCESSOREFERENCIADO,
				nfnotainfoprocessoreferenciado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOPROCESSOREFERENCIADO,
				AcaoEnum.UPDATE, nfnotainfoprocessoreferenciado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoprocessoreferenciado.getId(), nfnotainfoprocessoreferenciado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoProcessoReferenciadoBAR#
	 * deleteNFNotaInfoProcessoReferenciado(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoProcessoReferenciado)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoProcessoReferenciadoById(
			NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciado) {
		InternalResponse response = new InternalResponse();
		nfnotainfoprocessoreferenciado.setProcessId(nfnotainfoprocessoreferenciado.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOPROCESSOREFERENCIADO,
				nfnotainfoprocessoreferenciado, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOPROCESSOREFERENCIADO,
				AcaoEnum.DELETE, nfnotainfoprocessoreferenciado.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfoprocessoreferenciado.getId(), nfnotainfoprocessoreferenciado.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoProcessoReferenciadoBAR#
	 * deleteAllNFNotaInfoProcessoReferenciados()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoProcessoReferenciados() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOPROCESSOREFERENCIADO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoProcessoReferenciadoBAR#
	 * fetchNFNotaInfoProcessoReferenciadoById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoProcessoReferenciado fetchNFNotaInfoProcessoReferenciadoById(FetchByIdRequest request) {
		return (NFNotaInfoProcessoReferenciado) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOPROCESSOREFERENCIADO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoProcessoReferenciadoBAR#
	 * fetchAllNFNotaInfoProcessoReferenciadosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoProcessoReferenciado> fetchAllNFNotaInfoProcessoReferenciados(
			NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciado) {
		InternalResultsResponse<NFNotaInfoProcessoReferenciado> response = new InternalResultsResponse<NFNotaInfoProcessoReferenciado>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOPROCESSOREFERENCIADO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoProcessoReferenciadoBAR#
	 * fetchNFNotaInfoProcessoReferenciadosByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoProcessoReferenciado> fetchNFNotaInfoProcessoReferenciadosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoProcessoReferenciado> response = new InternalResultsResponse<NFNotaInfoProcessoReferenciado>();
		fetchNFNotaInfoProcessoReferenciadosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOPROCESSOREFERENCIADO_COUNT, STMT_FETCH_NFNOTAINFOPROCESSOREFERENCIADO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoProcessoReferenciadosByRequest
	// ####======================================

	public static void fetchNFNotaInfoProcessoReferenciadosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOEXPORTACAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoExportacaoBAR#insertNFNotaInfoExportacao(com.qat.samples.sysmgmt.base.model.NFNotaInfoExportacao)
	 */
	@Override
	public InternalResponse insertNFNotaInfoExportacao(NFNotaInfoExportacao nfnotainfoexportacao) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfoexportacao.setProcessId(nfnotainfoexportacao.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOEXPORTACAO, nfnotainfoexportacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOEXPORTACAO, AcaoEnum.INSERT,
				nfnotainfoexportacao.getTransactionId(), getHistoricoBAR(), response, nfnotainfoexportacao.getId(),
				nfnotainfoexportacao.getUserId());

		if (nfnotainfoexportacao.getId() != 0 && nfnotainfoexportacao.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfoexportacao.getId(), null,
					AcaoEnum.INSERT, nfnotainfoexportacao.getCreateUser(), nfnotainfoexportacao.getId(),
					TabelaEnum.NFNOTAINFOEXPORTACAO, statusBAR, historicoBAR, nfnotainfoexportacao.getTransactionId(),
					nfnotainfoexportacao.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoExportacaoBAR#
	 * updateNFNotaInfoExportacao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoExportacao)
	 */
	@Override
	public InternalResponse updateNFNotaInfoExportacao(NFNotaInfoExportacao nfnotainfoexportacao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoexportacao.setProcessId(nfnotainfoexportacao.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOEXPORTACAO, nfnotainfoexportacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOEXPORTACAO, AcaoEnum.UPDATE,
				nfnotainfoexportacao.getTransactionId(), getHistoricoBAR(), response, nfnotainfoexportacao.getId(),
				nfnotainfoexportacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoExportacaoBAR#
	 * deleteNFNotaInfoExportacao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoExportacao)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoExportacaoById(NFNotaInfoExportacao nfnotainfoexportacao) {
		InternalResponse response = new InternalResponse();
		nfnotainfoexportacao.setProcessId(nfnotainfoexportacao.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOEXPORTACAO, nfnotainfoexportacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOEXPORTACAO, AcaoEnum.DELETE,
				nfnotainfoexportacao.getTransactionId(), getHistoricoBAR(), response, nfnotainfoexportacao.getId(),
				nfnotainfoexportacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoExportacaoBAR#
	 * deleteAllNFNotaInfoExportacaos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoExportacaos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOEXPORTACAO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoExportacaoBAR#
	 * fetchNFNotaInfoExportacaoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoExportacao fetchNFNotaInfoExportacaoById(FetchByIdRequest request) {
		return (NFNotaInfoExportacao) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOEXPORTACAO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoExportacaoBAR#
	 * fetchAllNFNotaInfoExportacaosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoExportacao> fetchAllNFNotaInfoExportacaos(
			NFNotaInfoExportacao nfnotainfoexportacao) {
		InternalResultsResponse<NFNotaInfoExportacao> response = new InternalResultsResponse<NFNotaInfoExportacao>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOEXPORTACAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoExportacaoBAR#
	 * fetchNFNotaInfoExportacaosByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoExportacao> fetchNFNotaInfoExportacaosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoExportacao> response = new InternalResultsResponse<NFNotaInfoExportacao>();
		fetchNFNotaInfoExportacaosByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOEXPORTACAO_COUNT,
				STMT_FETCH_NFNOTAINFOEXPORTACAO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoExportacaosByRequest
	// ####======================================

	public static void fetchNFNotaInfoExportacaosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOCOMPRA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCompraBAR#insertNFNotaInfoCompra(com.qat.samples.sysmgmt.base.model.NFNotaInfoCompra)
	 */
	@Override
	public InternalResponse insertNFNotaInfoCompra(NFNotaInfoCompra nfnotainfocompra) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfocompra.setProcessId(nfnotainfocompra.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOCOMPRA, nfnotainfocompra, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCOMPRA, AcaoEnum.INSERT,
				nfnotainfocompra.getTransactionId(), getHistoricoBAR(), response, nfnotainfocompra.getId(),
				nfnotainfocompra.getUserId());

		if (nfnotainfocompra.getId() != 0 && nfnotainfocompra.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfocompra.getId(), null,
					AcaoEnum.INSERT, nfnotainfocompra.getCreateUser(), nfnotainfocompra.getId(),
					TabelaEnum.NFNOTAINFOCOMPRA, statusBAR, historicoBAR, nfnotainfocompra.getTransactionId(),
					nfnotainfocompra.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCompraBAR#
	 * updateNFNotaInfoCompra(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoCompra)
	 */
	@Override
	public InternalResponse updateNFNotaInfoCompra(NFNotaInfoCompra nfnotainfocompra) {
		InternalResponse response = new InternalResponse();
		nfnotainfocompra.setProcessId(nfnotainfocompra.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOCOMPRA, nfnotainfocompra, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCOMPRA, AcaoEnum.UPDATE,
				nfnotainfocompra.getTransactionId(), getHistoricoBAR(), response, nfnotainfocompra.getId(),
				nfnotainfocompra.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCompraBAR#
	 * deleteNFNotaInfoCompra(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoCompra)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoCompraById(NFNotaInfoCompra nfnotainfocompra) {
		InternalResponse response = new InternalResponse();
		nfnotainfocompra.setProcessId(nfnotainfocompra.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOCOMPRA, nfnotainfocompra, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCOMPRA, AcaoEnum.DELETE,
				nfnotainfocompra.getTransactionId(), getHistoricoBAR(), response, nfnotainfocompra.getId(),
				nfnotainfocompra.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCompraBAR#
	 * deleteAllNFNotaInfoCompras()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoCompras() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOCOMPRA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoCompraBAR#
	 * fetchNFNotaInfoCompraById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoCompra fetchNFNotaInfoCompraById(FetchByIdRequest request) {
		return (NFNotaInfoCompra) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOCOMPRA,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCompraBAR#
	 * fetchAllNFNotaInfoComprasCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoCompra> fetchAllNFNotaInfoCompras(NFNotaInfoCompra nfnotainfocompra) {
		InternalResultsResponse<NFNotaInfoCompra> response = new InternalResultsResponse<NFNotaInfoCompra>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOCOMPRA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoCompraBAR#
	 * fetchNFNotaInfoComprasByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoCompra> fetchNFNotaInfoComprasByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoCompra> response = new InternalResultsResponse<NFNotaInfoCompra>();
		fetchNFNotaInfoComprasByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOCOMPRA_COUNT,
				STMT_FETCH_NFNOTAINFOCOMPRA_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoComprasByRequest
	// ####======================================

	public static void fetchNFNotaInfoComprasByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOCANA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaBAR#insertNFNotaInfoCana(com.qat.samples.sysmgmt.base.model.NFNotaInfoCana)
	 */
	@Override
	public InternalResponse insertNFNotaInfoCana(NFNotaInfoCana nfnotainfocana) {
		InternalResponse response = new InternalResponse();

		Boolean count1 = false;

		nfnotainfocana.setProcessId(nfnotainfocana.getTransactionId());
		Integer count = 0;
		if (!ValidationUtil.isNullOrEmpty(nfnotainfocana.getFornecimentosDiario()))
		{
			count +=
					NFNotaInfoCanaFornecimentoDiarioBARD.maintainNFNotaInfoCanaFornecimentoDiarioAssociations(nfnotainfocana.getFornecimentosDiario(), response, nfnotainfocana.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOCANA, getNfeBAR(), statusBAR, historicoBAR, nfnotainfocana.getId(),
							nfnotainfocana.getCreateUser(), nfnotainfocana.getTransactionId(), nfnotainfocana.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(nfnotainfocana.getDeducoes()))
		{
			count +=
					NFNotaInfoCanaDeducaoBARD.maintainNFNotaInfoCanaDeducaoAssociations(nfnotainfocana.getDeducoes(), response, nfnotainfocana.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOCANA, getNfeBAR(), statusBAR, historicoBAR, nfnotainfocana.getId(),
							nfnotainfocana.getCreateUser(), nfnotainfocana.getTransactionId(), nfnotainfocana.getTransactionId());
		}
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOCANA, nfnotainfocana, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCANA, AcaoEnum.INSERT,
				nfnotainfocana.getTransactionId(), getHistoricoBAR(), response, nfnotainfocana.getId(),
				nfnotainfocana.getUserId());



		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaBAR#updateNFNotaInfoCana(
	 * com.qat.samples.sysmgmt.base.model.NFNotaInfoCana)
	 */
	@Override
	public InternalResponse updateNFNotaInfoCana(NFNotaInfoCana nfnotainfocana) {
		InternalResponse response = new InternalResponse();
		nfnotainfocana.setProcessId(nfnotainfocana.getTransactionId());

		Integer count = 0;
		if (!ValidationUtil.isNullOrEmpty(nfnotainfocana.getFornecimentosDiario()))
		{
			count +=
					NFNotaInfoCanaFornecimentoDiarioBARD.maintainNFNotaInfoCanaFornecimentoDiarioAssociations(nfnotainfocana.getFornecimentosDiario(), response, nfnotainfocana.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOCANA, getNfeBAR(), statusBAR, historicoBAR, nfnotainfocana.getId(),
							nfnotainfocana.getCreateUser(), nfnotainfocana.getTransactionId(), nfnotainfocana.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(nfnotainfocana.getDeducoes()))
		{
			count +=
					NFNotaInfoCanaDeducaoBARD.maintainNFNotaInfoCanaDeducaoAssociations(nfnotainfocana.getDeducoes(), response, nfnotainfocana.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOCANA, getNfeBAR(), statusBAR, historicoBAR, nfnotainfocana.getId(),
							nfnotainfocana.getCreateUser(), nfnotainfocana.getTransactionId(), nfnotainfocana.getTransactionId());
		}

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOCANA, nfnotainfocana, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCANA, AcaoEnum.UPDATE,
				nfnotainfocana.getTransactionId(), getHistoricoBAR(), response, nfnotainfocana.getId(),
				nfnotainfocana.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaBAR#deleteNFNotaInfoCana(
	 * com.qat.samples.sysmgmt.base.model.NFNotaInfoCana)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoCanaById(NFNotaInfoCana nfnotainfocana) {
		InternalResponse response = new InternalResponse();
		nfnotainfocana.setProcessId(nfnotainfocana.getTransactionId());

		Integer count = 0;
		if (!ValidationUtil.isNullOrEmpty(nfnotainfocana.getFornecimentosDiario()))
		{
			count +=
					NFNotaInfoCanaFornecimentoDiarioBARD.maintainNFNotaInfoCanaFornecimentoDiarioAssociations(nfnotainfocana.getFornecimentosDiario(), response, nfnotainfocana.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOCANA, getNfeBAR(), statusBAR, historicoBAR, nfnotainfocana.getId(),
							nfnotainfocana.getCreateUser(), nfnotainfocana.getTransactionId(), nfnotainfocana.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(nfnotainfocana.getDeducoes()))
		{
			count +=
					NFNotaInfoCanaDeducaoBARD.maintainNFNotaInfoCanaDeducaoAssociations(nfnotainfocana.getDeducoes(), response, nfnotainfocana.getId(), null,
							null,
							TabelaEnum.NFNOTAINFOCANA, getNfeBAR(), statusBAR, historicoBAR, nfnotainfocana.getId(),
							nfnotainfocana.getCreateUser(), nfnotainfocana.getTransactionId(), nfnotainfocana.getTransactionId());
		}

		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOCANA, nfnotainfocana, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCANA, AcaoEnum.DELETE,
				nfnotainfocana.getTransactionId(), getHistoricoBAR(), response, nfnotainfocana.getId(),
				nfnotainfocana.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaBAR#
	 * deleteAllNFNotaInfoCanas()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoCanas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOCANA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.INFNotaInfoCanaBAR#fetchNFNotaInfoCanaById(
	 * com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoCana fetchNFNotaInfoCanaById(FetchByIdRequest request) {
		return (NFNotaInfoCana) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_NFNOTAINFOCANA,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaBAR#
	 * fetchAllNFNotaInfoCanasCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoCana> fetchAllNFNotaInfoCanas(NFNotaInfoCana nfnotainfocana) {
		InternalResultsResponse<NFNotaInfoCana> response = new InternalResultsResponse<NFNotaInfoCana>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOCANA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoCanaBAR#
	 * fetchNFNotaInfoCanasByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoCana> fetchNFNotaInfoCanasByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoCana> response = new InternalResultsResponse<NFNotaInfoCana>();
		fetchNFNotaInfoCanasByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOCANA_COUNT,
				STMT_FETCH_NFNOTAINFOCANA_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchNFNotaInfoCanasByRequest
	// ####======================================

	public static void fetchNFNotaInfoCanasByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOCANAFORNECIMENTODIARIO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaFornecimentoDiarioBAR#insertNFNotaInfoCanaFornecimentoDiario(com.qat.samples.sysmgmt.base.model.NFNotaInfoCanaFornecimentoDiario)
	 */
	@Override
	public InternalResponse insertNFNotaInfoCanaFornecimentoDiario(
			NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiario) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfocanafornecimentodiario.setProcessId(nfnotainfocanafornecimentodiario.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOCANAFORNECIMENTODIARIO,
				nfnotainfocanafornecimentodiario, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCANAFORNECIMENTODIARIO,
				AcaoEnum.INSERT, nfnotainfocanafornecimentodiario.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfocanafornecimentodiario.getId(), nfnotainfocanafornecimentodiario.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaFornecimentoDiarioBAR#
	 * updateNFNotaInfoCanaFornecimentoDiario(com.qat.samples.sysmgmt.base.model
	 * .NFNotaInfoCanaFornecimentoDiario)
	 */
	@Override
	public InternalResponse updateNFNotaInfoCanaFornecimentoDiario(
			NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiario) {
		InternalResponse response = new InternalResponse();
		nfnotainfocanafornecimentodiario.setProcessId(nfnotainfocanafornecimentodiario.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOCANAFORNECIMENTODIARIO,
				nfnotainfocanafornecimentodiario, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCANAFORNECIMENTODIARIO,
				AcaoEnum.UPDATE, nfnotainfocanafornecimentodiario.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfocanafornecimentodiario.getId(), nfnotainfocanafornecimentodiario.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaFornecimentoDiarioBAR#
	 * deleteNFNotaInfoCanaFornecimentoDiario(com.qat.samples.sysmgmt.base.model
	 * .NFNotaInfoCanaFornecimentoDiario)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoCanaFornecimentoDiarioById(
			NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiario) {
		InternalResponse response = new InternalResponse();
		nfnotainfocanafornecimentodiario.setProcessId(nfnotainfocanafornecimentodiario.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOCANAFORNECIMENTODIARIO,
				nfnotainfocanafornecimentodiario, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCANAFORNECIMENTODIARIO,
				AcaoEnum.DELETE, nfnotainfocanafornecimentodiario.getTransactionId(), getHistoricoBAR(), response,
				nfnotainfocanafornecimentodiario.getId(), nfnotainfocanafornecimentodiario.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaFornecimentoDiarioBAR#
	 * deleteAllNFNotaInfoCanaFornecimentoDiarios()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoCanaFornecimentoDiarios() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOCANAFORNECIMENTODIARIO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoCanaFornecimentoDiarioBAR#
	 * fetchNFNotaInfoCanaFornecimentoDiarioById(com.qat.samples.sysmgmt.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoCanaFornecimentoDiario fetchNFNotaInfoCanaFornecimentoDiarioById(FetchByIdRequest request) {
		return (NFNotaInfoCanaFornecimentoDiario) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOCANAFORNECIMENTODIARIO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaFornecimentoDiarioBAR#
	 * fetchAllNFNotaInfoCanaFornecimentoDiariosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoCanaFornecimentoDiario> fetchAllNFNotaInfoCanaFornecimentoDiarios(
			NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiario) {
		InternalResultsResponse<NFNotaInfoCanaFornecimentoDiario> response = new InternalResultsResponse<NFNotaInfoCanaFornecimentoDiario>();
		response.getResultsList().addAll(
				MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOCANAFORNECIMENTODIARIO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoCanaFornecimentoDiarioBAR#
	 * fetchNFNotaInfoCanaFornecimentoDiariosByRequest(com.qat.samples.sysmgmt.
	 * model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoCanaFornecimentoDiario> fetchNFNotaInfoCanaFornecimentoDiariosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoCanaFornecimentoDiario> response = new InternalResultsResponse<NFNotaInfoCanaFornecimentoDiario>();
		fetchNFNotaInfoCanaFornecimentoDiariosByRequest(getSqlSession(), request,
				STMT_FETCH_NFNOTAINFOCANAFORNECIMENTODIARIO_COUNT,
				STMT_FETCH_NFNOTAINFOCANAFORNECIMENTODIARIO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoCanaFornecimentoDiariosByRequest
	// ####======================================

	public static void fetchNFNotaInfoCanaFornecimentoDiariosByRequest(SqlSession sqlSession,
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

	// ===================================### NFNOTAINFOCANADEDUCAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaDeducaoBAR#insertNFNotaInfoCanaDeducao(com.qat.samples.sysmgmt.base.model.NFNotaInfoCanaDeducao)
	 */
	@Override
	public InternalResponse insertNFNotaInfoCanaDeducao(NFNotaInfoCanaDeducao nfnotainfocanadeducao) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfocanadeducao.setProcessId(nfnotainfocanadeducao.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOCANADEDUCAO, nfnotainfocanadeducao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCANADEDUCAO, AcaoEnum.INSERT,
				nfnotainfocanadeducao.getTransactionId(), getHistoricoBAR(), response, nfnotainfocanadeducao.getId(),
				nfnotainfocanadeducao.getUserId());

		if (nfnotainfocanadeducao.getId() != 0 && nfnotainfocanadeducao.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfocanadeducao.getId(), null,
					AcaoEnum.INSERT, nfnotainfocanadeducao.getCreateUser(), nfnotainfocanadeducao.getId(),
					TabelaEnum.NFNOTAINFOCANADEDUCAO, statusBAR, historicoBAR, nfnotainfocanadeducao.getTransactionId(),
					nfnotainfocanadeducao.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaDeducaoBAR#
	 * updateNFNotaInfoCanaDeducao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoCanaDeducao)
	 */
	@Override
	public InternalResponse updateNFNotaInfoCanaDeducao(NFNotaInfoCanaDeducao nfnotainfocanadeducao) {
		InternalResponse response = new InternalResponse();
		nfnotainfocanadeducao.setProcessId(nfnotainfocanadeducao.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOCANADEDUCAO, nfnotainfocanadeducao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCANADEDUCAO, AcaoEnum.UPDATE,
				nfnotainfocanadeducao.getTransactionId(), getHistoricoBAR(), response, nfnotainfocanadeducao.getId(),
				nfnotainfocanadeducao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaDeducaoBAR#
	 * deleteNFNotaInfoCanaDeducao(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoCanaDeducao)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoCanaDeducaoById(NFNotaInfoCanaDeducao nfnotainfocanadeducao) {
		InternalResponse response = new InternalResponse();
		nfnotainfocanadeducao.setProcessId(nfnotainfocanadeducao.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOCANADEDUCAO, nfnotainfocanadeducao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOCANADEDUCAO, AcaoEnum.DELETE,
				nfnotainfocanadeducao.getTransactionId(), getHistoricoBAR(), response, nfnotainfocanadeducao.getId(),
				nfnotainfocanadeducao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaDeducaoBAR#
	 * deleteAllNFNotaInfoCanaDeducaos()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoCanaDeducaos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOCANADEDUCAO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoCanaDeducaoBAR#
	 * fetchNFNotaInfoCanaDeducaoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoCanaDeducao fetchNFNotaInfoCanaDeducaoById(FetchByIdRequest request) {
		return (NFNotaInfoCanaDeducao) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOCANADEDUCAO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoCanaDeducaoBAR#
	 * fetchAllNFNotaInfoCanaDeducaosCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoCanaDeducao> fetchAllNFNotaInfoCanaDeducaos(
			NFNotaInfoCanaDeducao nfnotainfocanadeducao) {
		InternalResultsResponse<NFNotaInfoCanaDeducao> response = new InternalResultsResponse<NFNotaInfoCanaDeducao>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOCANADEDUCAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoCanaDeducaoBAR#
	 * fetchNFNotaInfoCanaDeducaosByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoCanaDeducao> fetchNFNotaInfoCanaDeducaosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoCanaDeducao> response = new InternalResultsResponse<NFNotaInfoCanaDeducao>();
		fetchNFNotaInfoCanaDeducaosByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOCANADEDUCAO_COUNT,
				STMT_FETCH_NFNOTAINFOCANADEDUCAO_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoCanaDeducaosByRequest
	// ####======================================

	public static void fetchNFNotaInfoCanaDeducaosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### NFNOTAINFOSUPLEMENTAR
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoSuplementarBAR#insertNFNotaInfoSuplementar(com.qat.samples.sysmgmt.base.model.NFNotaInfoSuplementar)
	 */
	@Override
	public InternalResponse insertNFNotaInfoSuplementar(NFNotaInfoSuplementar nfnotainfosuplementar) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		nfnotainfosuplementar.setProcessId(nfnotainfosuplementar.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_NFNOTAINFOSUPLEMENTAR, nfnotainfosuplementar, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOSUPLEMENTAR, AcaoEnum.INSERT,
				nfnotainfosuplementar.getTransactionId(), getHistoricoBAR(), response, nfnotainfosuplementar.getId(),
				nfnotainfosuplementar.getUserId());

		if (nfnotainfosuplementar.getId() != 0 && nfnotainfosuplementar.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, nfnotainfosuplementar.getId(), null,
					AcaoEnum.INSERT, nfnotainfosuplementar.getCreateUser(), nfnotainfosuplementar.getId(),
					TabelaEnum.NFNOTAINFOSUPLEMENTAR, statusBAR, historicoBAR, nfnotainfosuplementar.getTransactionId(),
					nfnotainfosuplementar.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoSuplementarBAR#
	 * updateNFNotaInfoSuplementar(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoSuplementar)
	 */
	@Override
	public InternalResponse updateNFNotaInfoSuplementar(NFNotaInfoSuplementar nfnotainfosuplementar) {
		InternalResponse response = new InternalResponse();
		nfnotainfosuplementar.setProcessId(nfnotainfosuplementar.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_NFNOTAINFOSUPLEMENTAR, nfnotainfosuplementar, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOSUPLEMENTAR, AcaoEnum.UPDATE,
				nfnotainfosuplementar.getTransactionId(), getHistoricoBAR(), response, nfnotainfosuplementar.getId(),
				nfnotainfosuplementar.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoSuplementarBAR#
	 * deleteNFNotaInfoSuplementar(com.qat.samples.sysmgmt.base.model.
	 * NFNotaInfoSuplementar)
	 */
	@Override
	public InternalResponse deleteNFNotaInfoSuplementarById(NFNotaInfoSuplementar nfnotainfosuplementar) {
		InternalResponse response = new InternalResponse();
		nfnotainfosuplementar.setProcessId(nfnotainfosuplementar.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOSUPLEMENTAR, nfnotainfosuplementar, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.NFNOTAINFOSUPLEMENTAR, AcaoEnum.DELETE,
				nfnotainfosuplementar.getTransactionId(), getHistoricoBAR(), response, nfnotainfosuplementar.getId(),
				nfnotainfosuplementar.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoSuplementarBAR#
	 * deleteAllNFNotaInfoSuplementars()
	 */
	@Override
	public InternalResponse deleteAllNFNotaInfoSuplementars() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_NFNOTAINFOSUPLEMENTAR_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoSuplementarBAR#
	 * fetchNFNotaInfoSuplementarById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NFNotaInfoSuplementar fetchNFNotaInfoSuplementarById(FetchByIdRequest request) {
		return (NFNotaInfoSuplementar) MyBatisBARHelper.doQueryForObject(getSqlSession(),
				STMT_FETCH_NFNOTAINFOSUPLEMENTAR, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.INFNotaInfoSuplementarBAR#
	 * fetchAllNFNotaInfoSuplementarsCache()
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoSuplementar> fetchAllNFNotaInfoSuplementars(
			NFNotaInfoSuplementar nfnotainfosuplementar) {
		InternalResultsResponse<NFNotaInfoSuplementar> response = new InternalResultsResponse<NFNotaInfoSuplementar>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_NFNOTAINFOSUPLEMENTAR_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.INFNotaInfoSuplementarBAR#
	 * fetchNFNotaInfoSuplementarsByRequest(com.qat.samples.sysmgmt.model.
	 * request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NFNotaInfoSuplementar> fetchNFNotaInfoSuplementarsByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<NFNotaInfoSuplementar> response = new InternalResultsResponse<NFNotaInfoSuplementar>();
		fetchNFNotaInfoSuplementarsByRequest(getSqlSession(), request, STMT_FETCH_NFNOTAINFOSUPLEMENTAR_COUNT,
				STMT_FETCH_NFNOTAINFOSUPLEMENTAR_ALL_REQUEST, response);
		return response;
	}

	// ===================================###
	// fetchNFNotaInfoSuplementarsByRequest
	// ####======================================

	public static void fetchNFNotaInfoSuplementarsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	public Integer insertNFNotaInfoBARD(NFNotaInfo nfnotainfo,InternalResponse response) {
		Integer count = 0;

		if (!ValidationUtil.isNull(nfnotainfo.getIdentificacao()))
		{
			count +=
					NFNotaInfoIdentificacaoBARD.maintainNFNotaInfoIdentificacaoAssociations(nfnotainfo.getIdentificacao(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfo.getEmitente()))
		{
			count +=
					NFNotaInfoEmitenteBARD.maintainNFNotaInfoEmitenteAssociations(nfnotainfo.getEmitente(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfo.getAvulsa()))
		{
			count +=
					NFNotaInfoAvulsaBARD.maintainNFNotaInfoAvulsaAssociations(nfnotainfo.getAvulsa(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}
		//NFNotaInfoDestinatario
		if (!ValidationUtil.isNull(nfnotainfo.getDestinatario()))
		{
			count +=
					NFNotaInfoDestinatarioBARD.maintainNFNotaInfoDestinatarioAssociations(nfnotainfo.getDestinatario(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}
		//NFNotaInfoLocal
		if (!ValidationUtil.isNull(nfnotainfo.getRetirada()))
		{
			count +=
					NFNotaInfoLocalBARD.maintainNFNotaInfoLocalAssociations(nfnotainfo.getRetirada(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}
		//NFNotaInfoLocal
		if (!ValidationUtil.isNull(nfnotainfo.getEntrega()))
		{
			count +=
					NFNotaInfoLocalBARD.maintainNFNotaInfoLocalAssociations(nfnotainfo.getEntrega(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfo.getTotal()))
		{
			count +=
					NFNotaInfoTotalBARD.maintainNFNotaInfoTotalAssociations(nfnotainfo.getTotal(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfo.getTransporte()))
		{
			count +=
					NFNotaInfoTransporteBARD.maintainNFNotaInfoTransporteAssociations(nfnotainfo.getTransporte(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfo.getCobranca()))
		{
			count +=
					NFNotaInfoCobrancaBARD.maintainNFNotaInfoCobrancaAssociations(nfnotainfo.getCobranca(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfo.getInformacoesadicionais()))
		{
			count +=
					NFNotaInfoInformacoesAdicionaisBARD.maintainNFNotaInfoInformacoesAdicionaisAssociations(nfnotainfo.getInformacoesadicionais(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}

		if (!ValidationUtil.isNull(nfnotainfo.getExportacao()))
		{
			count +=
					NFNotaInfoExportacaoBARD.maintainNFNotaInfoExportacaoAssociations(nfnotainfo.getExportacao(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfo.getCompra()))
		{
			count +=
					NFNotaInfoCompraBARD.maintainNFNotaInfoCompraAssociations(nfnotainfo.getCompra(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}
		if (!ValidationUtil.isNull(nfnotainfo.getCana()))
		{
			count +=
					NFNotaInfoCanaBARD.maintainNFNotaInfoCanaAssociations(nfnotainfo.getCana(), response, nfnotainfo.getId(), null,
							null,
							TabelaEnum.NFNOTAINFO, getNfeBAR(), statusBAR, historicoBAR, nfnotainfo.getId(),
							nfnotainfo.getCreateUser(), nfnotainfo.getTransactionId(), nfnotainfo.getTransactionId());
		}
		return count;
	}
}
