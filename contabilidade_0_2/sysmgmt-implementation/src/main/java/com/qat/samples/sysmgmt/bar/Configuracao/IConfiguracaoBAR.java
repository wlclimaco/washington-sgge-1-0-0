/** create by system gera-java version 1.0.0 27/07/2016 12:37 : 46*/
package com.qat.samples.sysmgmt.bar.undefined;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface ConfiguracaoBAR.. (Data Access Component - DAC)
 */
public interface IConfiguracaoBAR 
{

	/**
	 * Fetch configuracao by id.
	 * 
	 * @param request the request
* @return the internal results response
*/
	public Configuracao fetchConfiguracaoById(FetchByIdRequest request);

	/**
* Insert configuracao.
* 
* @param configuracao the configuracao
* 
* @return the internal response
*/
	public InternalResponse insertConfiguracao(Configuracao configuracao);

	/**
* Update configuracao.
* 
* @param configuracao the configuracao
* 
* @return the internal response
*/
	public InternalResponse updateConfiguracao(Configuracao configuracao);

	/**
* Delete configuracao.
* 
* @param configuracao the configuracao
* 
* @return the internal response
*/
	public InternalResponse deleteConfiguracaoById(Configuracao configuracao);

	/**
* Delete all configuracaos.
* 
* @return the internal response
*/
	public InternalResponse deleteAllConfiguracaos();

	/**
* Fetch all configuracaos.
* 
* @return the list< configuracao>
*/
	public InternalResultsResponse<Configuracao> fetchAllConfiguracaos(Configuracao  configuracao);

	/**
* Fetch configuracaos by request.
* 
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configuracao> fetchConfiguracaosByRequest(ConfiguracaoInquiryRequest request);

	/**
	 * Fetch boleto by id.
	 * 
	 * @param request the request
* @return the internal results response
*/
	public Boleto fetchBoletoById(FetchByIdRequest request);

	/**
* Insert boleto.
* 
* @param boleto the boleto
* 
* @return the internal response
*/
	public InternalResponse insertBoleto(Boleto boleto);

	/**
* Update boleto.
* 
* @param boleto the boleto
* 
* @return the internal response
*/
	public InternalResponse updateBoleto(Boleto boleto);

	/**
* Delete boleto.
* 
* @param boleto the boleto
* 
* @return the internal response
*/
	public InternalResponse deleteBoletoById(Boleto boleto);

	/**
* Delete all boletos.
* 
* @return the internal response
*/
	public InternalResponse deleteAllBoletos();

	/**
* Fetch all boletos.
* 
* @return the list< boleto>
*/
	public InternalResultsResponse<Boleto> fetchAllBoletos(Boleto  boleto);

	/**
* Fetch boletos by request.
* 
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Boleto> fetchBoletosByRequest(BoletoInquiryRequest request);

	/**
	 * Fetch configcarne by id.
	 * 
	 * @param request the request
* @return the internal results response
*/
	public ConfigCarne fetchConfigCarneById(FetchByIdRequest request);

	/**
* Insert configcarne.
* 
* @param configcarne the configcarne
* 
* @return the internal response
*/
	public InternalResponse insertConfigCarne(ConfigCarne configcarne);

	/**
* Update configcarne.
* 
* @param configcarne the configcarne
* 
* @return the internal response
*/
	public InternalResponse updateConfigCarne(ConfigCarne configcarne);

	/**
* Delete configcarne.
* 
* @param configcarne the configcarne
* 
* @return the internal response
*/
	public InternalResponse deleteConfigCarneById(ConfigCarne configcarne);

	/**
* Delete all configcarnes.
* 
* @return the internal response
*/
	public InternalResponse deleteAllConfigCarnes();

	/**
* Fetch all configcarnes.
* 
* @return the list< configcarne>
*/
	public InternalResultsResponse<ConfigCarne> fetchAllConfigCarnes(ConfigCarne  configcarne);

	/**
* Fetch configcarnes by request.
* 
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigCarne> fetchConfigCarnesByRequest(ConfigCarneInquiryRequest request);

	/**
	 * Fetch configentrada by id.
	 * 
	 * @param request the request
* @return the internal results response
*/
	public ConfigEntrada fetchConfigEntradaById(FetchByIdRequest request);

	/**
* Insert configentrada.
* 
* @param configentrada the configentrada
* 
* @return the internal response
*/
	public InternalResponse insertConfigEntrada(ConfigEntrada configentrada);

	/**
* Update configentrada.
* 
* @param configentrada the configentrada
* 
* @return the internal response
*/
	public InternalResponse updateConfigEntrada(ConfigEntrada configentrada);

	/**
* Delete configentrada.
* 
* @param configentrada the configentrada
* 
* @return the internal response
*/
	public InternalResponse deleteConfigEntradaById(ConfigEntrada configentrada);

	/**
* Delete all configentradas.
* 
* @return the internal response
*/
	public InternalResponse deleteAllConfigEntradas();

	/**
* Fetch all configentradas.
* 
* @return the list< configentrada>
*/
	public InternalResultsResponse<ConfigEntrada> fetchAllConfigEntradas(ConfigEntrada  configentrada);

	/**
* Fetch configentradas by request.
* 
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigEntrada> fetchConfigEntradasByRequest(ConfigEntradaInquiryRequest request);

	/**
	 * Fetch configfiscal by id.
	 * 
	 * @param request the request
* @return the internal results response
*/
	public ConfigFiscal fetchConfigFiscalById(FetchByIdRequest request);

	/**
* Insert configfiscal.
* 
* @param configfiscal the configfiscal
* 
* @return the internal response
*/
	public InternalResponse insertConfigFiscal(ConfigFiscal configfiscal);

	/**
* Update configfiscal.
* 
* @param configfiscal the configfiscal
* 
* @return the internal response
*/
	public InternalResponse updateConfigFiscal(ConfigFiscal configfiscal);

	/**
* Delete configfiscal.
* 
* @param configfiscal the configfiscal
* 
* @return the internal response
*/
	public InternalResponse deleteConfigFiscalById(ConfigFiscal configfiscal);

	/**
* Delete all configfiscals.
* 
* @return the internal response
*/
	public InternalResponse deleteAllConfigFiscals();

	/**
* Fetch all configfiscals.
* 
* @return the list< configfiscal>
*/
	public InternalResultsResponse<ConfigFiscal> fetchAllConfigFiscals(ConfigFiscal  configfiscal);

	/**
* Fetch configfiscals by request.
* 
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigFiscal> fetchConfigFiscalsByRequest(ConfigFiscalInquiryRequest request);

	/**
	 * Fetch configalertas by id.
	 * 
	 * @param request the request
* @return the internal results response
*/
	public ConfigAlertas fetchConfigAlertasById(FetchByIdRequest request);

	/**
* Insert configalertas.
* 
* @param configalertas the configalertas
* 
* @return the internal response
*/
	public InternalResponse insertConfigAlertas(ConfigAlertas configalertas);

	/**
* Update configalertas.
* 
* @param configalertas the configalertas
* 
* @return the internal response
*/
	public InternalResponse updateConfigAlertas(ConfigAlertas configalertas);

	/**
* Delete configalertas.
* 
* @param configalertas the configalertas
* 
* @return the internal response
*/
	public InternalResponse deleteConfigAlertasById(ConfigAlertas configalertas);

	/**
* Delete all configalertass.
* 
* @return the internal response
*/
	public InternalResponse deleteAllConfigAlertass();

	/**
* Fetch all configalertass.
* 
* @return the list< configalertas>
*/
	public InternalResultsResponse<ConfigAlertas> fetchAllConfigAlertass(ConfigAlertas  configalertas);

	/**
* Fetch configalertass by request.
* 
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigAlertas> fetchConfigAlertassByRequest(ConfigAlertasInquiryRequest request);

	/**
	 * Fetch configgeral by id.
	 * 
	 * @param request the request
* @return the internal results response
*/
	public ConfigGeral fetchConfigGeralById(FetchByIdRequest request);

	/**
* Insert configgeral.
* 
* @param configgeral the configgeral
* 
* @return the internal response
*/
	public InternalResponse insertConfigGeral(ConfigGeral configgeral);

	/**
* Update configgeral.
* 
* @param configgeral the configgeral
* 
* @return the internal response
*/
	public InternalResponse updateConfigGeral(ConfigGeral configgeral);

	/**
* Delete configgeral.
* 
* @param configgeral the configgeral
* 
* @return the internal response
*/
	public InternalResponse deleteConfigGeralById(ConfigGeral configgeral);

	/**
* Delete all configgerals.
* 
* @return the internal response
*/
	public InternalResponse deleteAllConfigGerals();

	/**
* Fetch all configgerals.
* 
* @return the list< configgeral>
*/
	public InternalResultsResponse<ConfigGeral> fetchAllConfigGerals(ConfigGeral  configgeral);

	/**
* Fetch configgerals by request.
* 
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigGeral> fetchConfigGeralsByRequest(ConfigGeralInquiryRequest request);

	/**
	 * Fetch configproduto by id.
	 * 
	 * @param request the request
* @return the internal results response
*/
	public ConfigProduto fetchConfigProdutoById(FetchByIdRequest request);

	/**
* Insert configproduto.
* 
* @param configproduto the configproduto
* 
* @return the internal response
*/
	public InternalResponse insertConfigProduto(ConfigProduto configproduto);

	/**
* Update configproduto.
* 
* @param configproduto the configproduto
* 
* @return the internal response
*/
	public InternalResponse updateConfigProduto(ConfigProduto configproduto);

	/**
* Delete configproduto.
* 
* @param configproduto the configproduto
* 
* @return the internal response
*/
	public InternalResponse deleteConfigProdutoById(ConfigProduto configproduto);

	/**
* Delete all configprodutos.
* 
* @return the internal response
*/
	public InternalResponse deleteAllConfigProdutos();

	/**
* Fetch all configprodutos.
* 
* @return the list< configproduto>
*/
	public InternalResultsResponse<ConfigProduto> fetchAllConfigProdutos(ConfigProduto  configproduto);

	/**
* Fetch configprodutos by request.
* 
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigProduto> fetchConfigProdutosByRequest(ConfigProdutoInquiryRequest request);

	/**
	 * Fetch configsmtp by id.
	 * 
	 * @param request the request
* @return the internal results response
*/
	public ConfigSMTP fetchConfigSMTPById(FetchByIdRequest request);

	/**
* Insert configsmtp.
* 
* @param configsmtp the configsmtp
* 
* @return the internal response
*/
	public InternalResponse insertConfigSMTP(ConfigSMTP configsmtp);

	/**
* Update configsmtp.
* 
* @param configsmtp the configsmtp
* 
* @return the internal response
*/
	public InternalResponse updateConfigSMTP(ConfigSMTP configsmtp);

	/**
* Delete configsmtp.
* 
* @param configsmtp the configsmtp
* 
* @return the internal response
*/
	public InternalResponse deleteConfigSMTPById(ConfigSMTP configsmtp);

	/**
* Delete all configsmtps.
* 
* @return the internal response
*/
	public InternalResponse deleteAllConfigSMTPs();

	/**
* Fetch all configsmtps.
* 
* @return the list< configsmtp>
*/
	public InternalResultsResponse<ConfigSMTP> fetchAllConfigSMTPs(ConfigSMTP  configsmtp);

	/**
* Fetch configsmtps by request.
* 
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigSMTP> fetchConfigSMTPsByRequest(ConfigSMTPInquiryRequest request);

	/**
	 * Fetch configuracaonfe by id.
	 * 
	 * @param request the request
* @return the internal results response
*/
	public ConfiguracaoNFe fetchConfiguracaoNFeById(FetchByIdRequest request);

	/**
* Insert configuracaonfe.
* 
* @param configuracaonfe the configuracaonfe
* 
* @return the internal response
*/
	public InternalResponse insertConfiguracaoNFe(ConfiguracaoNFe configuracaonfe);

	/**
* Update configuracaonfe.
* 
* @param configuracaonfe the configuracaonfe
* 
* @return the internal response
*/
	public InternalResponse updateConfiguracaoNFe(ConfiguracaoNFe configuracaonfe);

	/**
* Delete configuracaonfe.
* 
* @param configuracaonfe the configuracaonfe
* 
* @return the internal response
*/
	public InternalResponse deleteConfiguracaoNFeById(ConfiguracaoNFe configuracaonfe);

	/**
* Delete all configuracaonfes.
* 
* @return the internal response
*/
	public InternalResponse deleteAllConfiguracaoNFes();

	/**
* Fetch all configuracaonfes.
* 
* @return the list< configuracaonfe>
*/
	public InternalResultsResponse<ConfiguracaoNFe> fetchAllConfiguracaoNFes(ConfiguracaoNFe  configuracaonfe);

	/**
* Fetch configuracaonfes by request.
* 
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfiguracaoNFe> fetchConfiguracaoNFesByRequest(ConfiguracaoNFeInquiryRequest request);

	/**
	 * Fetch configvendas by id.
	 * 
	 * @param request the request
* @return the internal results response
*/
	public ConfigVendas fetchConfigVendasById(FetchByIdRequest request);

	/**
* Insert configvendas.
* 
* @param configvendas the configvendas
* 
* @return the internal response
*/
	public InternalResponse insertConfigVendas(ConfigVendas configvendas);

	/**
* Update configvendas.
* 
* @param configvendas the configvendas
* 
* @return the internal response
*/
	public InternalResponse updateConfigVendas(ConfigVendas configvendas);

	/**
* Delete configvendas.
* 
* @param configvendas the configvendas
* 
* @return the internal response
*/
	public InternalResponse deleteConfigVendasById(ConfigVendas configvendas);

	/**
* Delete all configvendass.
* 
* @return the internal response
*/
	public InternalResponse deleteAllConfigVendass();

	/**
* Fetch all configvendass.
* 
* @return the list< configvendas>
*/
	public InternalResultsResponse<ConfigVendas> fetchAllConfigVendass(ConfigVendas  configvendas);

	/**
* Fetch configvendass by request.
* 
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigVendas> fetchConfigVendassByRequest(ConfigVendasInquiryRequest request);

}
