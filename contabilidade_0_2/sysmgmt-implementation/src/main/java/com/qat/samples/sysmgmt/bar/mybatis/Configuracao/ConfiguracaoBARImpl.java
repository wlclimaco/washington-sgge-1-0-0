/** create by system gera-java version 1.0.0 27/07/2016 12:37 : 46*/
package com.qat.samples.sysmgmt.bar.mybatis.Configuracao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Configuracao.IConfiguracaoBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.SociosBARD;
import com.qat.samples.sysmgmt.entidade.model.Boleto;
import com.qat.samples.sysmgmt.entidade.model.ConfigAlertas;
import com.qat.samples.sysmgmt.entidade.model.ConfigCarne;
import com.qat.samples.sysmgmt.entidade.model.ConfigEntrada;
import com.qat.samples.sysmgmt.entidade.model.ConfigFiscal;
import com.qat.samples.sysmgmt.entidade.model.ConfigGeral;
import com.qat.samples.sysmgmt.entidade.model.ConfigProduto;
import com.qat.samples.sysmgmt.entidade.model.ConfigSMTP;
import com.qat.samples.sysmgmt.entidade.model.ConfigVendas;
import com.qat.samples.sysmgmt.entidade.model.Configuracao;
import com.qat.samples.sysmgmt.entidade.model.ConfiguracaoNFe;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class ConfiguracaoBARImpl extends SqlSessionDaoSupport implements IConfiguracaoBAR {

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/// ===================================### CONFIGURACAO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONFIGURACAO = "ConfiguracaoMap.";

	/** The Constant STMT_INSERT_CONFIGURACAO. */
	private static final String STMT_INSERT_CONFIGURACAO = NAMESPACE_CONFIGURACAO + "insertConfiguracao";

	/** The Constant STMT_UPDATE_CONFIGURACAO. */
	private static final String STMT_UPDATE_CONFIGURACAO = NAMESPACE_CONFIGURACAO + "updateConfiguracao";

	/** The Constant STMT_DELETE_CONFIGURACAO. */
	private static final String STMT_DELETE_CONFIGURACAO = NAMESPACE_CONFIGURACAO + "deleteConfiguracaoById";

	/** The Constant STMT_DELETE_CONFIGURACAO_ALL. */
	private static final String STMT_DELETE_CONFIGURACAO_ALL = NAMESPACE_CONFIGURACAO + "deleteAllConfiguracaos";

	/** The Constant STMT_FETCH_CONFIGURACAO. */
	private static final String STMT_FETCH_CONFIGURACAO = NAMESPACE_CONFIGURACAO + "fetchConfiguracaoById";

	/** The Constant STMT_FETCH_CONFIGURACAO_ALL. */
	private static final String STMT_FETCH_CONFIGURACAO_ALL = NAMESPACE_CONFIGURACAO + "fetchAllConfiguracaos";

	/** The Constant STMT_FETCH_CONFIGURACAO_COUNT. */
	private static final String STMT_FETCH_CONFIGURACAO_COUNT = NAMESPACE_CONFIGURACAO + "fetchConfiguracaoRowCount";

	/** The Constant STMT_FETCH_CONFIGURACAO_ALL_REQUEST. */
	private static final String STMT_FETCH_CONFIGURACAO_ALL_REQUEST = NAMESPACE_CONFIGURACAO
			+ "fetchAllConfiguracaosRequest";

	/// ===================================### BOLETO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_BOLETO = "BoletoMap.";

	/** The Constant STMT_INSERT_BOLETO. */
	private static final String STMT_INSERT_BOLETO = NAMESPACE_BOLETO + "insertBoleto";

	/** The Constant STMT_UPDATE_BOLETO. */
	private static final String STMT_UPDATE_BOLETO = NAMESPACE_BOLETO + "updateBoleto";

	/** The Constant STMT_DELETE_BOLETO. */
	private static final String STMT_DELETE_BOLETO = NAMESPACE_BOLETO + "deleteBoletoById";

	/** The Constant STMT_DELETE_BOLETO_ALL. */
	private static final String STMT_DELETE_BOLETO_ALL = NAMESPACE_BOLETO + "deleteAllBoletos";

	/** The Constant STMT_FETCH_BOLETO. */
	private static final String STMT_FETCH_BOLETO = NAMESPACE_BOLETO + "fetchBoletoById";

	/** The Constant STMT_FETCH_BOLETO_ALL. */
	private static final String STMT_FETCH_BOLETO_ALL = NAMESPACE_BOLETO + "fetchAllBoletos";

	/** The Constant STMT_FETCH_BOLETO_COUNT. */
	private static final String STMT_FETCH_BOLETO_COUNT = NAMESPACE_BOLETO + "fetchBoletoRowCount";

	/** The Constant STMT_FETCH_BOLETO_ALL_REQUEST. */
	private static final String STMT_FETCH_BOLETO_ALL_REQUEST = NAMESPACE_BOLETO + "fetchAllBoletosRequest";

	/// ===================================### CONFIGCARNE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONFIGCARNE = "ConfigcarneMap.";

	/** The Constant STMT_INSERT_CONFIGCARNE. */
	private static final String STMT_INSERT_CONFIGCARNE = NAMESPACE_CONFIGCARNE + "insertConfigcarne";

	/** The Constant STMT_UPDATE_CONFIGCARNE. */
	private static final String STMT_UPDATE_CONFIGCARNE = NAMESPACE_CONFIGCARNE + "updateConfigcarne";

	/** The Constant STMT_DELETE_CONFIGCARNE. */
	private static final String STMT_DELETE_CONFIGCARNE = NAMESPACE_CONFIGCARNE + "deleteConfigcarneById";

	/** The Constant STMT_DELETE_CONFIGCARNE_ALL. */
	private static final String STMT_DELETE_CONFIGCARNE_ALL = NAMESPACE_CONFIGCARNE + "deleteAllConfigcarnes";

	/** The Constant STMT_FETCH_CONFIGCARNE. */
	private static final String STMT_FETCH_CONFIGCARNE = NAMESPACE_CONFIGCARNE + "fetchConfigcarneById";

	/** The Constant STMT_FETCH_CONFIGCARNE_ALL. */
	private static final String STMT_FETCH_CONFIGCARNE_ALL = NAMESPACE_CONFIGCARNE + "fetchAllConfigcarnes";

	/** The Constant STMT_FETCH_CONFIGCARNE_COUNT. */
	private static final String STMT_FETCH_CONFIGCARNE_COUNT = NAMESPACE_CONFIGCARNE + "fetchConfigcarneRowCount";

	/** The Constant STMT_FETCH_CONFIGCARNE_ALL_REQUEST. */
	private static final String STMT_FETCH_CONFIGCARNE_ALL_REQUEST = NAMESPACE_CONFIGCARNE
			+ "fetchAllConfigcarnesRequest";

	/// ===================================### CONFIGENTRADA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONFIGENTRADA = "ConfigentradaMap.";

	/** The Constant STMT_INSERT_CONFIGENTRADA. */
	private static final String STMT_INSERT_CONFIGENTRADA = NAMESPACE_CONFIGENTRADA + "insertConfigentrada";

	/** The Constant STMT_UPDATE_CONFIGENTRADA. */
	private static final String STMT_UPDATE_CONFIGENTRADA = NAMESPACE_CONFIGENTRADA + "updateConfigentrada";

	/** The Constant STMT_DELETE_CONFIGENTRADA. */
	private static final String STMT_DELETE_CONFIGENTRADA = NAMESPACE_CONFIGENTRADA + "deleteConfigentradaById";

	/** The Constant STMT_DELETE_CONFIGENTRADA_ALL. */
	private static final String STMT_DELETE_CONFIGENTRADA_ALL = NAMESPACE_CONFIGENTRADA + "deleteAllConfigentradas";

	/** The Constant STMT_FETCH_CONFIGENTRADA. */
	private static final String STMT_FETCH_CONFIGENTRADA = NAMESPACE_CONFIGENTRADA + "fetchConfigentradaById";

	/** The Constant STMT_FETCH_CONFIGENTRADA_ALL. */
	private static final String STMT_FETCH_CONFIGENTRADA_ALL = NAMESPACE_CONFIGENTRADA + "fetchAllConfigentradas";

	/** The Constant STMT_FETCH_CONFIGENTRADA_COUNT. */
	private static final String STMT_FETCH_CONFIGENTRADA_COUNT = NAMESPACE_CONFIGENTRADA + "fetchConfigentradaRowCount";

	/** The Constant STMT_FETCH_CONFIGENTRADA_ALL_REQUEST. */
	private static final String STMT_FETCH_CONFIGENTRADA_ALL_REQUEST = NAMESPACE_CONFIGENTRADA
			+ "fetchAllConfigentradasRequest";

	/// ===================================### CONFIGFISCAL
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONFIGFISCAL = "ConfigfiscalMap.";

	/** The Constant STMT_INSERT_CONFIGFISCAL. */
	private static final String STMT_INSERT_CONFIGFISCAL = NAMESPACE_CONFIGFISCAL + "insertConfigfiscal";

	/** The Constant STMT_UPDATE_CONFIGFISCAL. */
	private static final String STMT_UPDATE_CONFIGFISCAL = NAMESPACE_CONFIGFISCAL + "updateConfigfiscal";

	/** The Constant STMT_DELETE_CONFIGFISCAL. */
	private static final String STMT_DELETE_CONFIGFISCAL = NAMESPACE_CONFIGFISCAL + "deleteConfigfiscalById";

	/** The Constant STMT_DELETE_CONFIGFISCAL_ALL. */
	private static final String STMT_DELETE_CONFIGFISCAL_ALL = NAMESPACE_CONFIGFISCAL + "deleteAllConfigfiscals";

	/** The Constant STMT_FETCH_CONFIGFISCAL. */
	private static final String STMT_FETCH_CONFIGFISCAL = NAMESPACE_CONFIGFISCAL + "fetchConfigfiscalById";

	/** The Constant STMT_FETCH_CONFIGFISCAL_ALL. */
	private static final String STMT_FETCH_CONFIGFISCAL_ALL = NAMESPACE_CONFIGFISCAL + "fetchAllConfigfiscals";

	/** The Constant STMT_FETCH_CONFIGFISCAL_COUNT. */
	private static final String STMT_FETCH_CONFIGFISCAL_COUNT = NAMESPACE_CONFIGFISCAL + "fetchConfigfiscalRowCount";

	/** The Constant STMT_FETCH_CONFIGFISCAL_ALL_REQUEST. */
	private static final String STMT_FETCH_CONFIGFISCAL_ALL_REQUEST = NAMESPACE_CONFIGFISCAL
			+ "fetchAllConfigfiscalsRequest";

	/// ===================================### CONFIGALERTAS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONFIGALERTAS = "ConfigalertasMap.";

	/** The Constant STMT_INSERT_CONFIGALERTAS. */
	private static final String STMT_INSERT_CONFIGALERTAS = NAMESPACE_CONFIGALERTAS + "insertConfigalertas";

	/** The Constant STMT_UPDATE_CONFIGALERTAS. */
	private static final String STMT_UPDATE_CONFIGALERTAS = NAMESPACE_CONFIGALERTAS + "updateConfigalertas";

	/** The Constant STMT_DELETE_CONFIGALERTAS. */
	private static final String STMT_DELETE_CONFIGALERTAS = NAMESPACE_CONFIGALERTAS + "deleteConfigalertasById";

	/** The Constant STMT_DELETE_CONFIGALERTAS_ALL. */
	private static final String STMT_DELETE_CONFIGALERTAS_ALL = NAMESPACE_CONFIGALERTAS + "deleteAllConfigalertass";

	/** The Constant STMT_FETCH_CONFIGALERTAS. */
	private static final String STMT_FETCH_CONFIGALERTAS = NAMESPACE_CONFIGALERTAS + "fetchConfigalertasById";

	/** The Constant STMT_FETCH_CONFIGALERTAS_ALL. */
	private static final String STMT_FETCH_CONFIGALERTAS_ALL = NAMESPACE_CONFIGALERTAS + "fetchAllConfigalertass";

	/** The Constant STMT_FETCH_CONFIGALERTAS_COUNT. */
	private static final String STMT_FETCH_CONFIGALERTAS_COUNT = NAMESPACE_CONFIGALERTAS + "fetchConfigalertasRowCount";

	/** The Constant STMT_FETCH_CONFIGALERTAS_ALL_REQUEST. */
	private static final String STMT_FETCH_CONFIGALERTAS_ALL_REQUEST = NAMESPACE_CONFIGALERTAS
			+ "fetchAllConfigalertassRequest";

	/// ===================================### CONFIGGERAL
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONFIGGERAL = "ConfiggeralMap.";

	/** The Constant STMT_INSERT_CONFIGGERAL. */
	private static final String STMT_INSERT_CONFIGGERAL = NAMESPACE_CONFIGGERAL + "insertConfiggeral";

	/** The Constant STMT_UPDATE_CONFIGGERAL. */
	private static final String STMT_UPDATE_CONFIGGERAL = NAMESPACE_CONFIGGERAL + "updateConfiggeral";

	/** The Constant STMT_DELETE_CONFIGGERAL. */
	private static final String STMT_DELETE_CONFIGGERAL = NAMESPACE_CONFIGGERAL + "deleteConfiggeralById";

	/** The Constant STMT_DELETE_CONFIGGERAL_ALL. */
	private static final String STMT_DELETE_CONFIGGERAL_ALL = NAMESPACE_CONFIGGERAL + "deleteAllConfiggerals";

	/** The Constant STMT_FETCH_CONFIGGERAL. */
	private static final String STMT_FETCH_CONFIGGERAL = NAMESPACE_CONFIGGERAL + "fetchConfiggeralById";

	/** The Constant STMT_FETCH_CONFIGGERAL_ALL. */
	private static final String STMT_FETCH_CONFIGGERAL_ALL = NAMESPACE_CONFIGGERAL + "fetchAllConfiggerals";

	/** The Constant STMT_FETCH_CONFIGGERAL_COUNT. */
	private static final String STMT_FETCH_CONFIGGERAL_COUNT = NAMESPACE_CONFIGGERAL + "fetchConfiggeralRowCount";

	/** The Constant STMT_FETCH_CONFIGGERAL_ALL_REQUEST. */
	private static final String STMT_FETCH_CONFIGGERAL_ALL_REQUEST = NAMESPACE_CONFIGGERAL
			+ "fetchAllConfiggeralsRequest";

	/// ===================================### CONFIGPRODUTO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONFIGPRODUTO = "ConfigprodutoMap.";

	/** The Constant STMT_INSERT_CONFIGPRODUTO. */
	private static final String STMT_INSERT_CONFIGPRODUTO = NAMESPACE_CONFIGPRODUTO + "insertConfigproduto";

	/** The Constant STMT_UPDATE_CONFIGPRODUTO. */
	private static final String STMT_UPDATE_CONFIGPRODUTO = NAMESPACE_CONFIGPRODUTO + "updateConfigproduto";

	/** The Constant STMT_DELETE_CONFIGPRODUTO. */
	private static final String STMT_DELETE_CONFIGPRODUTO = NAMESPACE_CONFIGPRODUTO + "deleteConfigprodutoById";

	/** The Constant STMT_DELETE_CONFIGPRODUTO_ALL. */
	private static final String STMT_DELETE_CONFIGPRODUTO_ALL = NAMESPACE_CONFIGPRODUTO + "deleteAllConfigprodutos";

	/** The Constant STMT_FETCH_CONFIGPRODUTO. */
	private static final String STMT_FETCH_CONFIGPRODUTO = NAMESPACE_CONFIGPRODUTO + "fetchConfigprodutoById";

	/** The Constant STMT_FETCH_CONFIGPRODUTO_ALL. */
	private static final String STMT_FETCH_CONFIGPRODUTO_ALL = NAMESPACE_CONFIGPRODUTO + "fetchAllConfigprodutos";

	/** The Constant STMT_FETCH_CONFIGPRODUTO_COUNT. */
	private static final String STMT_FETCH_CONFIGPRODUTO_COUNT = NAMESPACE_CONFIGPRODUTO + "fetchConfigprodutoRowCount";

	/** The Constant STMT_FETCH_CONFIGPRODUTO_ALL_REQUEST. */
	private static final String STMT_FETCH_CONFIGPRODUTO_ALL_REQUEST = NAMESPACE_CONFIGPRODUTO
			+ "fetchAllConfigprodutosRequest";

	/// ===================================### CONFIGSMTP
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONFIGSMTP = "ConfigsmtpMap.";

	/** The Constant STMT_INSERT_CONFIGSMTP. */
	private static final String STMT_INSERT_CONFIGSMTP = NAMESPACE_CONFIGSMTP + "insertConfigsmtp";

	/** The Constant STMT_UPDATE_CONFIGSMTP. */
	private static final String STMT_UPDATE_CONFIGSMTP = NAMESPACE_CONFIGSMTP + "updateConfigsmtp";

	/** The Constant STMT_DELETE_CONFIGSMTP. */
	private static final String STMT_DELETE_CONFIGSMTP = NAMESPACE_CONFIGSMTP + "deleteConfigsmtpById";

	/** The Constant STMT_DELETE_CONFIGSMTP_ALL. */
	private static final String STMT_DELETE_CONFIGSMTP_ALL = NAMESPACE_CONFIGSMTP + "deleteAllConfigsmtps";

	/** The Constant STMT_FETCH_CONFIGSMTP. */
	private static final String STMT_FETCH_CONFIGSMTP = NAMESPACE_CONFIGSMTP + "fetchConfigsmtpById";

	/** The Constant STMT_FETCH_CONFIGSMTP_ALL. */
	private static final String STMT_FETCH_CONFIGSMTP_ALL = NAMESPACE_CONFIGSMTP + "fetchAllConfigsmtps";

	/** The Constant STMT_FETCH_CONFIGSMTP_COUNT. */
	private static final String STMT_FETCH_CONFIGSMTP_COUNT = NAMESPACE_CONFIGSMTP + "fetchConfigsmtpRowCount";

	/** The Constant STMT_FETCH_CONFIGSMTP_ALL_REQUEST. */
	private static final String STMT_FETCH_CONFIGSMTP_ALL_REQUEST = NAMESPACE_CONFIGSMTP + "fetchAllConfigsmtpsRequest";

	/// ===================================### CONFIGURACAONFE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONFIGURACAONFE = "ConfiguracaonfeMap.";

	/** The Constant STMT_INSERT_CONFIGURACAONFE. */
	private static final String STMT_INSERT_CONFIGURACAONFE = NAMESPACE_CONFIGURACAONFE + "insertConfiguracaonfe";

	/** The Constant STMT_UPDATE_CONFIGURACAONFE. */
	private static final String STMT_UPDATE_CONFIGURACAONFE = NAMESPACE_CONFIGURACAONFE + "updateConfiguracaonfe";

	/** The Constant STMT_DELETE_CONFIGURACAONFE. */
	private static final String STMT_DELETE_CONFIGURACAONFE = NAMESPACE_CONFIGURACAONFE + "deleteConfiguracaonfeById";

	/** The Constant STMT_DELETE_CONFIGURACAONFE_ALL. */
	private static final String STMT_DELETE_CONFIGURACAONFE_ALL = NAMESPACE_CONFIGURACAONFE
			+ "deleteAllConfiguracaonfes";

	/** The Constant STMT_FETCH_CONFIGURACAONFE. */
	private static final String STMT_FETCH_CONFIGURACAONFE = NAMESPACE_CONFIGURACAONFE + "fetchConfiguracaonfeById";

	/** The Constant STMT_FETCH_CONFIGURACAONFE_ALL. */
	private static final String STMT_FETCH_CONFIGURACAONFE_ALL = NAMESPACE_CONFIGURACAONFE + "fetchAllConfiguracaonfes";

	/** The Constant STMT_FETCH_CONFIGURACAONFE_COUNT. */
	private static final String STMT_FETCH_CONFIGURACAONFE_COUNT = NAMESPACE_CONFIGURACAONFE
			+ "fetchConfiguracaonfeRowCount";

	/** The Constant STMT_FETCH_CONFIGURACAONFE_ALL_REQUEST. */
	private static final String STMT_FETCH_CONFIGURACAONFE_ALL_REQUEST = NAMESPACE_CONFIGURACAONFE
			+ "fetchAllConfiguracaonfesRequest";

	/// ===================================### CONFIGVENDAS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONFIGVENDAS = "ConfigvendasMap.";

	/** The Constant STMT_INSERT_CONFIGVENDAS. */
	private static final String STMT_INSERT_CONFIGVENDAS = NAMESPACE_CONFIGVENDAS + "insertConfigvendas";

	/** The Constant STMT_UPDATE_CONFIGVENDAS. */
	private static final String STMT_UPDATE_CONFIGVENDAS = NAMESPACE_CONFIGVENDAS + "updateConfigvendas";

	/** The Constant STMT_DELETE_CONFIGVENDAS. */
	private static final String STMT_DELETE_CONFIGVENDAS = NAMESPACE_CONFIGVENDAS + "deleteConfigvendasById";

	/** The Constant STMT_DELETE_CONFIGVENDAS_ALL. */
	private static final String STMT_DELETE_CONFIGVENDAS_ALL = NAMESPACE_CONFIGVENDAS + "deleteAllConfigvendass";

	/** The Constant STMT_FETCH_CONFIGVENDAS. */
	private static final String STMT_FETCH_CONFIGVENDAS = NAMESPACE_CONFIGVENDAS + "fetchConfigvendasById";

	/** The Constant STMT_FETCH_CONFIGVENDAS_ALL. */
	private static final String STMT_FETCH_CONFIGVENDAS_ALL = NAMESPACE_CONFIGVENDAS + "fetchAllConfigvendass";

	/** The Constant STMT_FETCH_CONFIGVENDAS_COUNT. */
	private static final String STMT_FETCH_CONFIGVENDAS_COUNT = NAMESPACE_CONFIGVENDAS + "fetchConfigvendasRowCount";

	/** The Constant STMT_FETCH_CONFIGVENDAS_ALL_REQUEST. */
	private static final String STMT_FETCH_CONFIGVENDAS_ALL_REQUEST = NAMESPACE_CONFIGVENDAS
			+ "fetchAllConfigvendassRequest";

	// ===================================### CONFIGURACAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfiguracaoBAR#insertConfiguracao(com.qat.samples.sysmgmt.base.model.Configuracao)
	 */
	@Override
	public InternalResponse insertConfiguracao(Configuracao configuracao) {
		InternalResponse response = new InternalResponse();

		insertModulosConfiguracao(configuracao);
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONFIGURACAO, configuracao, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfiguracaoBAR#updateConfiguracao(com.
	 * qat.samples.sysmgmt.base.model.Configuracao)
	 */
	@Override
	public InternalResponse updateConfiguracao(Configuracao configuracao) {
		InternalResponse response = new InternalResponse();

		insertModulosConfiguracao(configuracao);
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONFIGURACAO, configuracao, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfiguracaoBAR#deleteConfiguracao(com.
	 * qat.samples.sysmgmt.base.model.Configuracao)
	 */
	@Override
	public InternalResponse deleteConfiguracaoById(Configuracao configuracao) {
		InternalResponse response = new InternalResponse();

		insertModulosConfiguracao(configuracao);
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGURACAO, configuracao, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfiguracaoBAR#deleteAllConfiguracaos(
	 * )
	 */
	@Override
	public InternalResponse deleteAllConfiguracaos() {
		InternalResponse response = new InternalResponse();

		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGURACAO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfiguracaoBAR#fetchConfiguracaoById(com.
	 * qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Configuracao fetchConfiguracaoById(FetchByIdRequest request) {
		return (Configuracao) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONFIGURACAO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfiguracaoBAR#
	 * fetchAllConfiguracaosCache()
	 */
	@Override
	public InternalResultsResponse<Configuracao> fetchAllConfiguracaos(Configuracao configuracao) {
		InternalResultsResponse<Configuracao> response = new InternalResultsResponse<Configuracao>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONFIGURACAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfiguracaoBAR#fetchConfiguracaosByRequest(
	 * com.qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Configuracao> fetchConfiguracaosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Configuracao> response = new InternalResultsResponse<Configuracao>();
		fetchConfiguracaosByRequest(getSqlSession(), request, STMT_FETCH_CONFIGURACAO_COUNT,
				STMT_FETCH_CONFIGURACAO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchConfiguracaosByRequest
	// ####======================================

	public static void fetchConfiguracaosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### BOLETO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IBoletoBAR#insertBoleto(com.qat.samples.sysmgmt.base.model.Boleto)
	 */
	@Override
	public InternalResponse insertBoleto(Boleto boleto) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_BOLETO, boleto, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IBoletoBAR#updateBoleto(com.qat.samples.
	 * sysmgmt.base.model.Boleto)
	 */
	@Override
	public InternalResponse updateBoleto(Boleto boleto) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_BOLETO, boleto, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IBoletoBAR#deleteBoleto(com.qat.samples.
	 * sysmgmt.base.model.Boleto)
	 */
	@Override
	public InternalResponse deleteBoletoById(Boleto boleto) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_BOLETO, boleto, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IBoletoBAR#deleteAllBoletos()
	 */
	@Override
	public InternalResponse deleteAllBoletos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_BOLETO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IBoletoBAR#fetchBoletoById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Boleto fetchBoletoById(FetchByIdRequest request) {
		return (Boleto) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_BOLETO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IBoletoBAR#fetchAllBoletosCache()
	 */
	@Override
	public InternalResultsResponse<Boleto> fetchAllBoletos(Boleto boleto) {
		InternalResultsResponse<Boleto> response = new InternalResultsResponse<Boleto>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_BOLETO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IBoletoBAR#fetchBoletosByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Boleto> fetchBoletosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Boleto> response = new InternalResultsResponse<Boleto>();
		fetchBoletosByRequest(getSqlSession(), request, STMT_FETCH_BOLETO_COUNT, STMT_FETCH_BOLETO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchBoletosByRequest
	// ####======================================

	public static void fetchBoletosByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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

	// ===================================### CONFIGCARNE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigCarneBAR#insertConfigCarne(com.qat.samples.sysmgmt.base.model.ConfigCarne)
	 */
	@Override
	public InternalResponse insertConfigCarne(ConfigCarne configcarne) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONFIGCARNE, configcarne, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigCarneBAR#updateConfigCarne(com.
	 * qat.samples.sysmgmt.base.model.ConfigCarne)
	 */
	@Override
	public InternalResponse updateConfigCarne(ConfigCarne configcarne) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONFIGCARNE, configcarne, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigCarneBAR#deleteConfigCarne(com.
	 * qat.samples.sysmgmt.base.model.ConfigCarne)
	 */
	@Override
	public InternalResponse deleteConfigCarneById(ConfigCarne configcarne) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGCARNE, configcarne, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigCarneBAR#deleteAllConfigCarnes()
	 */
	@Override
	public InternalResponse deleteAllConfigCarnes() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGCARNE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigCarneBAR#fetchConfigCarneById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ConfigCarne fetchConfigCarneById(FetchByIdRequest request) {
		return (ConfigCarne) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONFIGCARNE,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigCarneBAR#
	 * fetchAllConfigCarnesCache()
	 */
	@Override
	public InternalResultsResponse<ConfigCarne> fetchAllConfigCarnes(ConfigCarne configcarne) {
		InternalResultsResponse<ConfigCarne> response = new InternalResultsResponse<ConfigCarne>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONFIGCARNE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigCarneBAR#fetchConfigCarnesByRequest(
	 * com.qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ConfigCarne> fetchConfigCarnesByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<ConfigCarne> response = new InternalResultsResponse<ConfigCarne>();
		fetchConfigCarnesByRequest(getSqlSession(), request, STMT_FETCH_CONFIGCARNE_COUNT,
				STMT_FETCH_CONFIGCARNE_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchConfigCarnesByRequest
	// ####======================================

	public static void fetchConfigCarnesByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### CONFIGENTRADA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigEntradaBAR#insertConfigEntrada(com.qat.samples.sysmgmt.base.model.ConfigEntrada)
	 */
	@Override
	public InternalResponse insertConfigEntrada(ConfigEntrada configentrada) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONFIGENTRADA, configentrada, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigEntradaBAR#updateConfigEntrada(
	 * com.qat.samples.sysmgmt.base.model.ConfigEntrada)
	 */
	@Override
	public InternalResponse updateConfigEntrada(ConfigEntrada configentrada) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONFIGENTRADA, configentrada, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigEntradaBAR#deleteConfigEntrada(
	 * com.qat.samples.sysmgmt.base.model.ConfigEntrada)
	 */
	@Override
	public InternalResponse deleteConfigEntradaById(ConfigEntrada configentrada) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGENTRADA, configentrada, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigEntradaBAR#
	 * deleteAllConfigEntradas()
	 */
	@Override
	public InternalResponse deleteAllConfigEntradas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGENTRADA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigEntradaBAR#fetchConfigEntradaById(com.
	 * qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ConfigEntrada fetchConfigEntradaById(FetchByIdRequest request) {
		return (ConfigEntrada) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONFIGENTRADA,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigEntradaBAR#
	 * fetchAllConfigEntradasCache()
	 */
	@Override
	public InternalResultsResponse<ConfigEntrada> fetchAllConfigEntradas(ConfigEntrada configentrada) {
		InternalResultsResponse<ConfigEntrada> response = new InternalResultsResponse<ConfigEntrada>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONFIGENTRADA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IConfigEntradaBAR#
	 * fetchConfigEntradasByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ConfigEntrada> fetchConfigEntradasByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<ConfigEntrada> response = new InternalResultsResponse<ConfigEntrada>();
		fetchConfigEntradasByRequest(getSqlSession(), request, STMT_FETCH_CONFIGENTRADA_COUNT,
				STMT_FETCH_CONFIGENTRADA_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchConfigEntradasByRequest
	// ####======================================

	public static void fetchConfigEntradasByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### CONFIGFISCAL
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigFiscalBAR#insertConfigFiscal(com.qat.samples.sysmgmt.base.model.ConfigFiscal)
	 */
	@Override
	public InternalResponse insertConfigFiscal(ConfigFiscal configfiscal) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONFIGFISCAL, configfiscal, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigFiscalBAR#updateConfigFiscal(com.
	 * qat.samples.sysmgmt.base.model.ConfigFiscal)
	 */
	@Override
	public InternalResponse updateConfigFiscal(ConfigFiscal configfiscal) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONFIGFISCAL, configfiscal, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigFiscalBAR#deleteConfigFiscal(com.
	 * qat.samples.sysmgmt.base.model.ConfigFiscal)
	 */
	@Override
	public InternalResponse deleteConfigFiscalById(ConfigFiscal configfiscal) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGFISCAL, configfiscal, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigFiscalBAR#deleteAllConfigFiscals(
	 * )
	 */
	@Override
	public InternalResponse deleteAllConfigFiscals() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGFISCAL_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigFiscalBAR#fetchConfigFiscalById(com.
	 * qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ConfigFiscal fetchConfigFiscalById(FetchByIdRequest request) {
		return (ConfigFiscal) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONFIGFISCAL,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigFiscalBAR#
	 * fetchAllConfigFiscalsCache()
	 */
	@Override
	public InternalResultsResponse<ConfigFiscal> fetchAllConfigFiscals(ConfigFiscal configfiscal) {
		InternalResultsResponse<ConfigFiscal> response = new InternalResultsResponse<ConfigFiscal>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONFIGFISCAL_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigFiscalBAR#fetchConfigFiscalsByRequest(
	 * com.qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ConfigFiscal> fetchConfigFiscalsByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<ConfigFiscal> response = new InternalResultsResponse<ConfigFiscal>();
		fetchConfigFiscalsByRequest(getSqlSession(), request, STMT_FETCH_CONFIGFISCAL_COUNT,
				STMT_FETCH_CONFIGFISCAL_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchConfigFiscalsByRequest
	// ####======================================

	public static void fetchConfigFiscalsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### CONFIGALERTAS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigAlertasBAR#insertConfigAlertas(com.qat.samples.sysmgmt.base.model.ConfigAlertas)
	 */
	@Override
	public InternalResponse insertConfigAlertas(ConfigAlertas configalertas) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONFIGALERTAS, configalertas, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigAlertasBAR#updateConfigAlertas(
	 * com.qat.samples.sysmgmt.base.model.ConfigAlertas)
	 */
	@Override
	public InternalResponse updateConfigAlertas(ConfigAlertas configalertas) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONFIGALERTAS, configalertas, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigAlertasBAR#deleteConfigAlertas(
	 * com.qat.samples.sysmgmt.base.model.ConfigAlertas)
	 */
	@Override
	public InternalResponse deleteConfigAlertasById(ConfigAlertas configalertas) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGALERTAS, configalertas, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigAlertasBAR#
	 * deleteAllConfigAlertass()
	 */
	@Override
	public InternalResponse deleteAllConfigAlertass() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGALERTAS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigAlertasBAR#fetchConfigAlertasById(com.
	 * qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ConfigAlertas fetchConfigAlertasById(FetchByIdRequest request) {
		return (ConfigAlertas) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONFIGALERTAS,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigAlertasBAR#
	 * fetchAllConfigAlertassCache()
	 */
	@Override
	public InternalResultsResponse<ConfigAlertas> fetchAllConfigAlertass(ConfigAlertas configalertas) {
		InternalResultsResponse<ConfigAlertas> response = new InternalResultsResponse<ConfigAlertas>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONFIGALERTAS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IConfigAlertasBAR#
	 * fetchConfigAlertassByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ConfigAlertas> fetchConfigAlertassByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<ConfigAlertas> response = new InternalResultsResponse<ConfigAlertas>();
		fetchConfigAlertassByRequest(getSqlSession(), request, STMT_FETCH_CONFIGALERTAS_COUNT,
				STMT_FETCH_CONFIGALERTAS_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchConfigAlertassByRequest
	// ####======================================

	public static void fetchConfigAlertassByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### CONFIGGERAL
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigGeralBAR#insertConfigGeral(com.qat.samples.sysmgmt.base.model.ConfigGeral)
	 */
	@Override
	public InternalResponse insertConfigGeral(ConfigGeral configgeral) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONFIGGERAL, configgeral, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigGeralBAR#updateConfigGeral(com.
	 * qat.samples.sysmgmt.base.model.ConfigGeral)
	 */
	@Override
	public InternalResponse updateConfigGeral(ConfigGeral configgeral) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONFIGGERAL, configgeral, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigGeralBAR#deleteConfigGeral(com.
	 * qat.samples.sysmgmt.base.model.ConfigGeral)
	 */
	@Override
	public InternalResponse deleteConfigGeralById(ConfigGeral configgeral) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGGERAL, configgeral, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigGeralBAR#deleteAllConfigGerals()
	 */
	@Override
	public InternalResponse deleteAllConfigGerals() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGGERAL_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigGeralBAR#fetchConfigGeralById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ConfigGeral fetchConfigGeralById(FetchByIdRequest request) {
		return (ConfigGeral) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONFIGGERAL,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigGeralBAR#
	 * fetchAllConfigGeralsCache()
	 */
	@Override
	public InternalResultsResponse<ConfigGeral> fetchAllConfigGerals(ConfigGeral configgeral) {
		InternalResultsResponse<ConfigGeral> response = new InternalResultsResponse<ConfigGeral>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONFIGGERAL_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigGeralBAR#fetchConfigGeralsByRequest(
	 * com.qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ConfigGeral> fetchConfigGeralsByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<ConfigGeral> response = new InternalResultsResponse<ConfigGeral>();
		fetchConfigGeralsByRequest(getSqlSession(), request, STMT_FETCH_CONFIGGERAL_COUNT,
				STMT_FETCH_CONFIGGERAL_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchConfigGeralsByRequest
	// ####======================================

	public static void fetchConfigGeralsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### CONFIGPRODUTO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigProdutoBAR#insertConfigProduto(com.qat.samples.sysmgmt.base.model.ConfigProduto)
	 */
	@Override
	public InternalResponse insertConfigProduto(ConfigProduto configproduto) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONFIGPRODUTO, configproduto, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigProdutoBAR#updateConfigProduto(
	 * com.qat.samples.sysmgmt.base.model.ConfigProduto)
	 */
	@Override
	public InternalResponse updateConfigProduto(ConfigProduto configproduto) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONFIGPRODUTO, configproduto, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigProdutoBAR#deleteConfigProduto(
	 * com.qat.samples.sysmgmt.base.model.ConfigProduto)
	 */
	@Override
	public InternalResponse deleteConfigProdutoById(ConfigProduto configproduto) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGPRODUTO, configproduto, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigProdutoBAR#
	 * deleteAllConfigProdutos()
	 */
	@Override
	public InternalResponse deleteAllConfigProdutos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGPRODUTO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigProdutoBAR#fetchConfigProdutoById(com.
	 * qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ConfigProduto fetchConfigProdutoById(FetchByIdRequest request) {
		return (ConfigProduto) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONFIGPRODUTO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigProdutoBAR#
	 * fetchAllConfigProdutosCache()
	 */
	@Override
	public InternalResultsResponse<ConfigProduto> fetchAllConfigProdutos(ConfigProduto configproduto) {
		InternalResultsResponse<ConfigProduto> response = new InternalResultsResponse<ConfigProduto>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONFIGPRODUTO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IConfigProdutoBAR#
	 * fetchConfigProdutosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ConfigProduto> fetchConfigProdutosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<ConfigProduto> response = new InternalResultsResponse<ConfigProduto>();
		fetchConfigProdutosByRequest(getSqlSession(), request, STMT_FETCH_CONFIGPRODUTO_COUNT,
				STMT_FETCH_CONFIGPRODUTO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchConfigProdutosByRequest
	// ####======================================

	public static void fetchConfigProdutosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### CONFIGSMTP
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigSMTPBAR#insertConfigSMTP(com.qat.samples.sysmgmt.base.model.ConfigSMTP)
	 */
	@Override
	public InternalResponse insertConfigSMTP(ConfigSMTP configsmtp) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONFIGSMTP, configsmtp, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigSMTPBAR#updateConfigSMTP(com.qat.
	 * samples.sysmgmt.base.model.ConfigSMTP)
	 */
	@Override
	public InternalResponse updateConfigSMTP(ConfigSMTP configsmtp) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONFIGSMTP, configsmtp, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigSMTPBAR#deleteConfigSMTP(com.qat.
	 * samples.sysmgmt.base.model.ConfigSMTP)
	 */
	@Override
	public InternalResponse deleteConfigSMTPById(ConfigSMTP configsmtp) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGSMTP, configsmtp, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigSMTPBAR#deleteAllConfigSMTPs()
	 */
	@Override
	public InternalResponse deleteAllConfigSMTPs() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGSMTP_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigSMTPBAR#fetchConfigSMTPById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ConfigSMTP fetchConfigSMTPById(FetchByIdRequest request) {
		return (ConfigSMTP) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONFIGSMTP,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigSMTPBAR#fetchAllConfigSMTPsCache(
	 * )
	 */
	@Override
	public InternalResultsResponse<ConfigSMTP> fetchAllConfigSMTPs(ConfigSMTP configsmtp) {
		InternalResultsResponse<ConfigSMTP> response = new InternalResultsResponse<ConfigSMTP>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONFIGSMTP_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigSMTPBAR#fetchConfigSMTPsByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ConfigSMTP> fetchConfigSMTPsByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<ConfigSMTP> response = new InternalResultsResponse<ConfigSMTP>();
		fetchConfigSMTPsByRequest(getSqlSession(), request, STMT_FETCH_CONFIGSMTP_COUNT,
				STMT_FETCH_CONFIGSMTP_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchConfigSMTPsByRequest
	// ####======================================

	public static void fetchConfigSMTPsByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### CONFIGURACAONFE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfiguracaoNFeBAR#insertConfiguracaoNFe(com.qat.samples.sysmgmt.base.model.ConfiguracaoNFe)
	 */
	@Override
	public InternalResponse insertConfiguracaoNFe(ConfiguracaoNFe configuracaonfe) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONFIGURACAONFE, configuracaonfe, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfiguracaoNFeBAR#
	 * updateConfiguracaoNFe(com.qat.samples.sysmgmt.base.model.ConfiguracaoNFe)
	 */
	@Override
	public InternalResponse updateConfiguracaoNFe(ConfiguracaoNFe configuracaonfe) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONFIGURACAONFE, configuracaonfe, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfiguracaoNFeBAR#
	 * deleteConfiguracaoNFe(com.qat.samples.sysmgmt.base.model.ConfiguracaoNFe)
	 */
	@Override
	public InternalResponse deleteConfiguracaoNFeById(ConfiguracaoNFe configuracaonfe) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGURACAONFE, configuracaonfe, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfiguracaoNFeBAR#
	 * deleteAllConfiguracaoNFes()
	 */
	@Override
	public InternalResponse deleteAllConfiguracaoNFes() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGURACAONFE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfiguracaoNFeBAR#fetchConfiguracaoNFeById(
	 * com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ConfiguracaoNFe fetchConfiguracaoNFeById(FetchByIdRequest request) {
		return (ConfiguracaoNFe) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONFIGURACAONFE,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfiguracaoNFeBAR#
	 * fetchAllConfiguracaoNFesCache()
	 */
	@Override
	public InternalResultsResponse<ConfiguracaoNFe> fetchAllConfiguracaoNFes(ConfiguracaoNFe configuracaonfe) {
		InternalResultsResponse<ConfiguracaoNFe> response = new InternalResultsResponse<ConfiguracaoNFe>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONFIGURACAONFE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IConfiguracaoNFeBAR#
	 * fetchConfiguracaoNFesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ConfiguracaoNFe> fetchConfiguracaoNFesByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<ConfiguracaoNFe> response = new InternalResultsResponse<ConfiguracaoNFe>();
		fetchConfiguracaoNFesByRequest(getSqlSession(), request, STMT_FETCH_CONFIGURACAONFE_COUNT,
				STMT_FETCH_CONFIGURACAONFE_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchConfiguracaoNFesByRequest
	// ####======================================

	public static void fetchConfiguracaoNFesByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	// ===================================### CONFIGVENDAS
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigVendasBAR#insertConfigVendas(com.qat.samples.sysmgmt.base.model.ConfigVendas)
	 */
	@Override
	public InternalResponse insertConfigVendas(ConfigVendas configvendas) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONFIGVENDAS, configvendas, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigVendasBAR#updateConfigVendas(com.
	 * qat.samples.sysmgmt.base.model.ConfigVendas)
	 */
	@Override
	public InternalResponse updateConfigVendas(ConfigVendas configvendas) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONFIGVENDAS, configvendas, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigVendasBAR#deleteConfigVendas(com.
	 * qat.samples.sysmgmt.base.model.ConfigVendas)
	 */
	@Override
	public InternalResponse deleteConfigVendasById(ConfigVendas configvendas) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGVENDAS, configvendas, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IConfigVendasBAR#deleteAllConfigVendass(
	 * )
	 */
	@Override
	public InternalResponse deleteAllConfigVendass() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONFIGVENDAS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigVendasBAR#fetchConfigVendasById(com.
	 * qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ConfigVendas fetchConfigVendasById(FetchByIdRequest request) {
		return (ConfigVendas) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONFIGVENDAS,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IConfigVendasBAR#
	 * fetchAllConfigVendassCache()
	 */
	@Override
	public InternalResultsResponse<ConfigVendas> fetchAllConfigVendass(ConfigVendas configvendas) {
		InternalResultsResponse<ConfigVendas> response = new InternalResultsResponse<ConfigVendas>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONFIGVENDAS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IConfigVendasBAR#fetchConfigVendassByRequest(
	 * com.qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ConfigVendas> fetchConfigVendassByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<ConfigVendas> response = new InternalResultsResponse<ConfigVendas>();
		fetchConfigVendassByRequest(getSqlSession(), request, STMT_FETCH_CONFIGVENDAS_COUNT,
				STMT_FETCH_CONFIGVENDAS_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchConfigVendassByRequest
	// ####======================================

	public static void fetchConfigVendassByRequest(SqlSession sqlSession, PagedInquiryRequest request,
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

	public static void insertModulosConfiguracao(Configuracao configuracao) {

//		if (!ValidationUtil.isNull(configuracao.getConfGeral())) {
//			a += SociosBARD.maintainSocioAssociations(configuracao.getConfGeral(), response, empresa.getId(), null,
//					null, TabelaEnum.EMPRESA, getSociosBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
//					empresa.getCreateUser(), historicoId, historicoId, getDocumentosBAR());
//
//		}
//		if (!ValidationUtil.isNullOrEmpty(configuracao.getConfFiscal())) {
//			a += SociosBARD.maintainSocioAssociations(configuracao.getConfFiscal(), response, empresa.getId(), null,
//					null, TabelaEnum.EMPRESA, getSociosBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
//					empresa.getCreateUser(), historicoId, historicoId, getDocumentosBAR());
//
//		}
//		if (!ValidationUtil.isNullOrEmpty(configuracao.getConfProd())) {
//			a += SociosBARD.maintainSocioAssociations(configuracao.getConfProd(), response, empresa.getId(), null, null,
//					TabelaEnum.EMPRESA, getSociosBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
//					empresa.getCreateUser(), historicoId, historicoId, getDocumentosBAR());
//
//		}
//		if (!ValidationUtil.isNullOrEmpty(configuracao.getConfVendas())) {
//			a += SociosBARD.maintainSocioAssociations(configuracao.getConfVendas(), response, empresa.getId(), null,
//					null, TabelaEnum.EMPRESA, getSociosBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
//					empresa.getCreateUser(), historicoId, historicoId, getDocumentosBAR());
//
//		}
//		if (!ValidationUtil.isNullOrEmpty(configuracao.getConfCMTP())) {
//			a += SociosBARD.maintainSocioAssociations(empresa.getConfCMTP(), response, empresa.getId(), null, null,
//					TabelaEnum.EMPRESA, getSociosBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
//					empresa.getCreateUser(), historicoId, historicoId, getDocumentosBAR());
//
//		}
//		if (!ValidationUtil.isNullOrEmpty(configuracao.getConfEntrada)) {
//			a += SociosBARD.maintainSocioAssociations(configuracao.getConfEntrada(), response, empresa.getId(), null,
//					null, TabelaEnum.EMPRESA, getSociosBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
//					empresa.getCreateUser(), historicoId, historicoId, getDocumentosBAR());
//
//		}
//		if (!ValidationUtil.isNullOrEmpty(configuracao.getConfCarne)) {
//			a += SociosBARD.maintainSocioAssociations(configuracao.getConfCarne(), response, empresa.getId(), null,
//					null, TabelaEnum.EMPRESA, getSociosBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
//					empresa.getCreateUser(), historicoId, historicoId, getDocumentosBAR());
//
//		}
//		if (!ValidationUtil.isNullOrEmpty(configuracao.getConfCarne)) {
//			a += SociosBARD.maintainSocioAssociations(configuracao.getConfCarne(), response, empresa.getId(), null,
//					null, TabelaEnum.EMPRESA, getSociosBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
//					empresa.getCreateUser(), historicoId, historicoId, getDocumentosBAR());
//
//		}
//		if (!ValidationUtil.isNullOrEmpty(configuracao.getBoletoList)) {
//			a += SociosBARD.maintainSocioAssociations(configuracao.getBoletoList(), response, empresa.getId(), null,
//					null, TabelaEnum.EMPRESA, getSociosBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
//					empresa.getCreateUser(), historicoId, historicoId, getDocumentosBAR());
//
//		}

	}
}
