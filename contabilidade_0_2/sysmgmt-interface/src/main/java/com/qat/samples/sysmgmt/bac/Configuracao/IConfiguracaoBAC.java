/** create by system gera-java version 1.0.0 27/07/2016 15:44 : 43*/
package com.qat.samples.sysmgmt.bac.Configuracao;
import com.qat.framework.model.response.InternalResultsResponse;
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
import com.qat.samples.sysmgmt.entidade.model.request.BoletoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigAlertasMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigCarneMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigEntradaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigFiscalMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigGeralMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigSMTPMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigVendasMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfiguracaoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfiguracaoNFeMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IConfiguracaoBAC. (Business Area Component - BAC)
 */
public interface IConfiguracaoBAC
{



//===================================### CONFIGURACAO ####======================================
	/**

	/**
	 * Insert configuracao.
	 *
* @param request the configuracao maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configuracao> insertConfiguracao(ConfiguracaoMaintenanceRequest request);

	/**
* Update configuracao.
*
* @param request the configuracao maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configuracao> updateConfiguracao(ConfiguracaoMaintenanceRequest request);

	/**
* Delete configuracao.
*
* @param request the configuracao maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configuracao> deleteConfiguracao(ConfiguracaoMaintenanceRequest request);

	/**
* Refresh configuracaos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Configuracao> refreshConfiguracaos(RefreshRequest request);

	/**
* Fetch configuracao by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configuracao> fetchConfiguracaoById(FetchByIdRequest request);

	/**
* Fetch all configuracaos.
*
* @return the internal results response< configuracao>
*/
	public InternalResultsResponse<Configuracao> fetchAllConfiguracaos(Configuracao  configuracao);

	/**
* Fetch configuracaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configuracao> fetchConfiguracaosByRequest(PagedInquiryRequest request);


//===================================### BOLETO ####======================================
	/**

	/**
	 * Insert boleto.
	 *
* @param request the boleto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Boleto> insertBoleto(BoletoMaintenanceRequest request);

	/**
* Update boleto.
*
* @param request the boleto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Boleto> updateBoleto(BoletoMaintenanceRequest request);

	/**
* Delete boleto.
*
* @param request the boleto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Boleto> deleteBoleto(BoletoMaintenanceRequest request);

	/**
* Refresh boletos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Boleto> refreshBoletos(RefreshRequest request);

	/**
* Fetch boleto by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Boleto> fetchBoletoById(FetchByIdRequest request);

	/**
* Fetch all boletos.
*
* @return the internal results response< boleto>
*/
	public InternalResultsResponse<Boleto> fetchAllBoletos(Boleto  boleto);

	/**
* Fetch boletos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Boleto> fetchBoletosByRequest(PagedInquiryRequest request);


//===================================### ConfigCarne ####======================================
	/**

	/**
	 * Insert ConfigCarne.
	 *
* @param request the ConfigCarne maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigCarne> insertConfigCarne(ConfigCarneMaintenanceRequest request);

	/**
* Update ConfigCarne.
*
* @param request the ConfigCarne maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigCarne> updateConfigCarne(ConfigCarneMaintenanceRequest request);

	/**
* Delete ConfigCarne.
*
* @param request the ConfigCarne maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigCarne> deleteConfigCarne(ConfigCarneMaintenanceRequest request);

	/**
* Refresh ConfigCarnes.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ConfigCarne> refreshConfigCarnes(RefreshRequest request);

	/**
* Fetch ConfigCarne by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigCarne> fetchConfigCarneById(FetchByIdRequest request);

	/**
* Fetch all ConfigCarnes.
*
* @return the internal results response< ConfigCarne>
*/
	public InternalResultsResponse<ConfigCarne> fetchAllConfigCarnes(ConfigCarne  ConfigCarne);

	/**
* Fetch ConfigCarnes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigCarne> fetchConfigCarnesByRequest(PagedInquiryRequest request);


//===================================### ConfigEntrada ####======================================
	/**

	/**
	 * Insert ConfigEntrada.
	 *
* @param request the ConfigEntrada maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigEntrada> insertConfigEntrada(ConfigEntradaMaintenanceRequest request);

	/**
* Update ConfigEntrada.
*
* @param request the ConfigEntrada maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigEntrada> updateConfigEntrada(ConfigEntradaMaintenanceRequest request);

	/**
* Delete ConfigEntrada.
*
* @param request the ConfigEntrada maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigEntrada> deleteConfigEntrada(ConfigEntradaMaintenanceRequest request);

	/**
* Refresh ConfigEntradas.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ConfigEntrada> refreshConfigEntradas(RefreshRequest request);

	/**
* Fetch ConfigEntrada by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigEntrada> fetchConfigEntradaById(FetchByIdRequest request);

	/**
* Fetch all ConfigEntradas.
*
* @return the internal results response< ConfigEntrada>
*/
	public InternalResultsResponse<ConfigEntrada> fetchAllConfigEntradas(ConfigEntrada  ConfigEntrada);

	/**
* Fetch ConfigEntradas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigEntrada> fetchConfigEntradasByRequest(PagedInquiryRequest request);


//===================================### ConfigFiscal ####======================================
	/**

	/**
	 * Insert ConfigFiscal.
	 *
* @param request the ConfigFiscal maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigFiscal> insertConfigFiscal(ConfigFiscalMaintenanceRequest request);

	/**
* Update ConfigFiscal.
*
* @param request the ConfigFiscal maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigFiscal> updateConfigFiscal(ConfigFiscalMaintenanceRequest request);

	/**
* Delete ConfigFiscal.
*
* @param request the ConfigFiscal maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigFiscal> deleteConfigFiscal(ConfigFiscalMaintenanceRequest request);

	/**
* Refresh ConfigFiscals.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ConfigFiscal> refreshConfigFiscals(RefreshRequest request);

	/**
* Fetch ConfigFiscal by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigFiscal> fetchConfigFiscalById(FetchByIdRequest request);

	/**
* Fetch all ConfigFiscals.
*
* @return the internal results response< ConfigFiscal>
*/
	public InternalResultsResponse<ConfigFiscal> fetchAllConfigFiscals(ConfigFiscal  ConfigFiscal);

	/**
* Fetch ConfigFiscals by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigFiscal> fetchConfigFiscalsByRequest(PagedInquiryRequest request);


//===================================### ConfigAlertas ####======================================
	/**

	/**
	 * Insert ConfigAlertas.
	 *
* @param request the ConfigAlertas maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigAlertas> insertConfigAlertas(ConfigAlertasMaintenanceRequest request);

	/**
* Update ConfigAlertas.
*
* @param request the ConfigAlertas maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigAlertas> updateConfigAlertas(ConfigAlertasMaintenanceRequest request);
	/**
* Delete ConfigAlertas.
*
* @param request the ConfigAlertas maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigAlertas> deleteConfigAlertas(ConfigAlertasMaintenanceRequest request);

	/**
* Refresh ConfigAlertass.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ConfigAlertas> refreshConfigAlertass(RefreshRequest request);

	/**
* Fetch ConfigAlertas by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigAlertas> fetchConfigAlertasById(FetchByIdRequest request);

	/**
* Fetch all ConfigAlertass.
*
* @return the internal results response< ConfigAlertas>
*/
	public InternalResultsResponse<ConfigAlertas> fetchAllConfigAlertass(ConfigAlertas  ConfigAlertas);

	/**
* Fetch ConfigAlertass by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigAlertas> fetchConfigAlertassByRequest(PagedInquiryRequest request);


//===================================### ConfigGeral ####======================================
	/**

	/**
	 * Insert ConfigGeral.
	 *
* @param request the ConfigGeral maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigGeral> insertConfigGeral(ConfigGeralMaintenanceRequest request);

	/**
* Update ConfigGeral.
*
* @param request the ConfigGeral maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigGeral> updateConfigGeral(ConfigGeralMaintenanceRequest request);

	/**
* Delete ConfigGeral.
*
* @param request the ConfigGeral maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigGeral> deleteConfigGeral(ConfigGeralMaintenanceRequest request);

	/**
* Refresh ConfigGerals.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ConfigGeral> refreshConfigGerals(RefreshRequest request);

	/**
* Fetch ConfigGeral by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigGeral> fetchConfigGeralById(FetchByIdRequest request);

	/**
* Fetch all ConfigGerals.
*
* @return the internal results response< ConfigGeral>
*/
	public InternalResultsResponse<ConfigGeral> fetchAllConfigGerals(ConfigGeral  ConfigGeral);

	/**
* Fetch ConfigGerals by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigGeral> fetchConfigGeralsByRequest(PagedInquiryRequest request);


//===================================### ConfigProduto ####======================================
	/**

	/**
	 * Insert ConfigProduto.
	 *
* @param request the ConfigProduto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigProduto> insertConfigProduto(ConfigProdutoMaintenanceRequest request);

	/**
* Update ConfigProduto.
*
* @param request the ConfigProduto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigProduto> updateConfigProduto(ConfigProdutoMaintenanceRequest request);

	/**
* Delete ConfigProduto.
*
* @param request the ConfigProduto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigProduto> deleteConfigProduto(ConfigProdutoMaintenanceRequest request);

	/**
* Refresh ConfigProdutos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ConfigProduto> refreshConfigProdutos(RefreshRequest request);

	/**
* Fetch ConfigProduto by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigProduto> fetchConfigProdutoById(FetchByIdRequest request);

	/**
* Fetch all ConfigProdutos.
*
* @return the internal results response< ConfigProduto>
*/
	public InternalResultsResponse<ConfigProduto> fetchAllConfigProdutos(ConfigProduto  ConfigProduto);

	/**
* Fetch ConfigProdutos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigProduto> fetchConfigProdutosByRequest(PagedInquiryRequest request);


//===================================### ConfigSMTP ####======================================
	/**

	/**
	 * Insert ConfigSMTP.
	 *
* @param request the ConfigSMTP maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigSMTP> insertConfigSMTP(ConfigSMTPMaintenanceRequest request);

	/**
* Update ConfigSMTP.
*
* @param request the ConfigSMTP maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigSMTP> updateConfigSMTP(ConfigSMTPMaintenanceRequest request);

	/**
* Delete ConfigSMTP.
*
* @param request the ConfigSMTP maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigSMTP> deleteConfigSMTP(ConfigSMTPMaintenanceRequest request);

	/**
* Refresh ConfigSMTPs.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ConfigSMTP> refreshConfigSMTPs(RefreshRequest request);

	/**
* Fetch ConfigSMTP by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigSMTP> fetchConfigSMTPById(FetchByIdRequest request);

	/**
* Fetch all ConfigSMTPs.
*
* @return the internal results response< ConfigSMTP>
*/
	public InternalResultsResponse<ConfigSMTP> fetchAllConfigSMTPs(ConfigSMTP  ConfigSMTP);

	/**
* Fetch ConfigSMTPs by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigSMTP> fetchConfigSMTPsByRequest(PagedInquiryRequest request);


//===================================### ConfiguracaoNFe ####======================================
	/**

	/**
	 * Insert ConfiguracaoNFe.
	 *
* @param request the ConfiguracaoNFe maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfiguracaoNFe> insertConfiguracaoNFe(ConfiguracaoNFeMaintenanceRequest request);

	/**
* Update ConfiguracaoNFe.
*
* @param request the ConfiguracaoNFe maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfiguracaoNFe> updateConfiguracaoNFe(ConfiguracaoNFeMaintenanceRequest request);

	/**
* Delete ConfiguracaoNFe.
*
* @param request the ConfiguracaoNFe maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfiguracaoNFe> deleteConfiguracaoNFe(ConfiguracaoNFeMaintenanceRequest request);

	/**
* Refresh ConfiguracaoNFes.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ConfiguracaoNFe> refreshConfiguracaoNFes(RefreshRequest request);

	/**
* Fetch ConfiguracaoNFe by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfiguracaoNFe> fetchConfiguracaoNFeById(FetchByIdRequest request);

	/**
* Fetch all ConfiguracaoNFes.
*
* @return the internal results response< ConfiguracaoNFe>
*/
	public InternalResultsResponse<ConfiguracaoNFe> fetchAllConfiguracaoNFes(ConfiguracaoNFe  ConfiguracaoNFe);

	/**
* Fetch ConfiguracaoNFes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfiguracaoNFe> fetchConfiguracaoNFesByRequest(PagedInquiryRequest request);


//===================================### ConfigVendas ####======================================
	/**

	/**
	 * Insert ConfigVendas.
	 *
* @param request the ConfigVendas maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigVendas> insertConfigVendas(ConfigVendasMaintenanceRequest request);

	/**
* Update ConfigVendas.
*
* @param request the ConfigVendas maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigVendas> updateConfigVendas(ConfigVendasMaintenanceRequest request);

	/**
* Delete ConfigVendas.
*
* @param request the ConfigVendas maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ConfigVendas> deleteConfigVendas(ConfigVendasMaintenanceRequest request);

	/**
* Refresh ConfigVendass.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ConfigVendas> refreshConfigVendass(RefreshRequest request);

	/**
* Fetch ConfigVendas by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigVendas> fetchConfigVendasById(FetchByIdRequest request);

	/**
* Fetch all ConfigVendass.
*
* @return the internal results response< ConfigVendas>
*/
	public InternalResultsResponse<ConfigVendas> fetchAllConfigVendass(ConfigVendas  ConfigVendas);

	/**
* Fetch ConfigVendass by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConfigVendas> fetchConfigVendassByRequest(PagedInquiryRequest request);

}
