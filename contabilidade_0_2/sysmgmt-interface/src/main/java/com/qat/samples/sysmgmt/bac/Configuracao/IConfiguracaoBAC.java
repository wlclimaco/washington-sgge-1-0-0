/** create by system gera-java version 1.0.0 27/07/2016 15:44 : 43*/
package com.qat.samples.sysmgmt.bac.undefined;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
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
	public InternalResultsResponse<Configuracao> fetchConfiguracaosByRequest(ConfiguracaoInquiryRequest request);


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
	public InternalResultsResponse<Boleto> fetchBoletosByRequest(BoletoInquiryRequest request);


//===================================### CONFIGCARNE ####======================================
	/**

	/**
	 * Insert configcarne.
	 *
* @param request the configcarne maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configcarne> insertConfigcarne(ConfigcarneMaintenanceRequest request);

	/**
* Update configcarne.
*
* @param request the configcarne maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configcarne> updateConfigcarne(ConfigcarneMaintenanceRequest request);

	/**
* Delete configcarne.
*
* @param request the configcarne maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configcarne> deleteConfigcarne(ConfigcarneMaintenanceRequest request);

	/**
* Refresh configcarnes.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Configcarne> refreshConfigcarnes(RefreshRequest request);

	/**
* Fetch configcarne by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configcarne> fetchConfigcarneById(FetchByIdRequest request);

	/**
* Fetch all configcarnes.
*
* @return the internal results response< configcarne>
*/
	public InternalResultsResponse<Configcarne> fetchAllConfigcarnes(Configcarne  configcarne);

	/**
* Fetch configcarnes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configcarne> fetchConfigcarnesByRequest(ConfigcarneInquiryRequest request);


//===================================### CONFIGENTRADA ####======================================
	/**

	/**
	 * Insert configentrada.
	 *
* @param request the configentrada maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configentrada> insertConfigentrada(ConfigentradaMaintenanceRequest request);

	/**
* Update configentrada.
*
* @param request the configentrada maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configentrada> updateConfigentrada(ConfigentradaMaintenanceRequest request);

	/**
* Delete configentrada.
*
* @param request the configentrada maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configentrada> deleteConfigentrada(ConfigentradaMaintenanceRequest request);

	/**
* Refresh configentradas.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Configentrada> refreshConfigentradas(RefreshRequest request);

	/**
* Fetch configentrada by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configentrada> fetchConfigentradaById(FetchByIdRequest request);

	/**
* Fetch all configentradas.
*
* @return the internal results response< configentrada>
*/
	public InternalResultsResponse<Configentrada> fetchAllConfigentradas(Configentrada  configentrada);

	/**
* Fetch configentradas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configentrada> fetchConfigentradasByRequest(ConfigentradaInquiryRequest request);


//===================================### CONFIGFISCAL ####======================================
	/**

	/**
	 * Insert configfiscal.
	 *
* @param request the configfiscal maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configfiscal> insertConfigfiscal(ConfigfiscalMaintenanceRequest request);

	/**
* Update configfiscal.
*
* @param request the configfiscal maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configfiscal> updateConfigfiscal(ConfigfiscalMaintenanceRequest request);

	/**
* Delete configfiscal.
*
* @param request the configfiscal maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configfiscal> deleteConfigfiscal(ConfigfiscalMaintenanceRequest request);

	/**
* Refresh configfiscals.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Configfiscal> refreshConfigfiscals(RefreshRequest request);

	/**
* Fetch configfiscal by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configfiscal> fetchConfigfiscalById(FetchByIdRequest request);

	/**
* Fetch all configfiscals.
*
* @return the internal results response< configfiscal>
*/
	public InternalResultsResponse<Configfiscal> fetchAllConfigfiscals(Configfiscal  configfiscal);

	/**
* Fetch configfiscals by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configfiscal> fetchConfigfiscalsByRequest(ConfigfiscalInquiryRequest request);


//===================================### CONFIGALERTAS ####======================================
	/**

	/**
	 * Insert configalertas.
	 *
* @param request the configalertas maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configalertas> insertConfigalertas(ConfigalertasMaintenanceRequest request);

	/**
* Update configalertas.
*
* @param request the configalertas maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configalertas> updateConfigalertas(ConfigalertasMaintenanceRequest request);

	/**
* Delete configalertas.
*
* @param request the configalertas maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configalertas> deleteConfigalertas(ConfigalertasMaintenanceRequest request);

	/**
* Refresh configalertass.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Configalertas> refreshConfigalertass(RefreshRequest request);

	/**
* Fetch configalertas by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configalertas> fetchConfigalertasById(FetchByIdRequest request);

	/**
* Fetch all configalertass.
*
* @return the internal results response< configalertas>
*/
	public InternalResultsResponse<Configalertas> fetchAllConfigalertass(Configalertas  configalertas);

	/**
* Fetch configalertass by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configalertas> fetchConfigalertassByRequest(ConfigalertasInquiryRequest request);


//===================================### CONFIGGERAL ####======================================
	/**

	/**
	 * Insert configgeral.
	 *
* @param request the configgeral maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configgeral> insertConfiggeral(ConfiggeralMaintenanceRequest request);

	/**
* Update configgeral.
*
* @param request the configgeral maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configgeral> updateConfiggeral(ConfiggeralMaintenanceRequest request);

	/**
* Delete configgeral.
*
* @param request the configgeral maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configgeral> deleteConfiggeral(ConfiggeralMaintenanceRequest request);

	/**
* Refresh configgerals.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Configgeral> refreshConfiggerals(RefreshRequest request);

	/**
* Fetch configgeral by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configgeral> fetchConfiggeralById(FetchByIdRequest request);

	/**
* Fetch all configgerals.
*
* @return the internal results response< configgeral>
*/
	public InternalResultsResponse<Configgeral> fetchAllConfiggerals(Configgeral  configgeral);

	/**
* Fetch configgerals by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configgeral> fetchConfiggeralsByRequest(ConfiggeralInquiryRequest request);


//===================================### CONFIGPRODUTO ####======================================
	/**

	/**
	 * Insert configproduto.
	 *
* @param request the configproduto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configproduto> insertConfigproduto(ConfigprodutoMaintenanceRequest request);

	/**
* Update configproduto.
*
* @param request the configproduto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configproduto> updateConfigproduto(ConfigprodutoMaintenanceRequest request);

	/**
* Delete configproduto.
*
* @param request the configproduto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configproduto> deleteConfigproduto(ConfigprodutoMaintenanceRequest request);

	/**
* Refresh configprodutos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Configproduto> refreshConfigprodutos(RefreshRequest request);

	/**
* Fetch configproduto by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configproduto> fetchConfigprodutoById(FetchByIdRequest request);

	/**
* Fetch all configprodutos.
*
* @return the internal results response< configproduto>
*/
	public InternalResultsResponse<Configproduto> fetchAllConfigprodutos(Configproduto  configproduto);

	/**
* Fetch configprodutos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configproduto> fetchConfigprodutosByRequest(ConfigprodutoInquiryRequest request);


//===================================### CONFIGSMTP ####======================================
	/**

	/**
	 * Insert configsmtp.
	 *
* @param request the configsmtp maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configsmtp> insertConfigsmtp(ConfigsmtpMaintenanceRequest request);

	/**
* Update configsmtp.
*
* @param request the configsmtp maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configsmtp> updateConfigsmtp(ConfigsmtpMaintenanceRequest request);

	/**
* Delete configsmtp.
*
* @param request the configsmtp maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configsmtp> deleteConfigsmtp(ConfigsmtpMaintenanceRequest request);

	/**
* Refresh configsmtps.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Configsmtp> refreshConfigsmtps(RefreshRequest request);

	/**
* Fetch configsmtp by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configsmtp> fetchConfigsmtpById(FetchByIdRequest request);

	/**
* Fetch all configsmtps.
*
* @return the internal results response< configsmtp>
*/
	public InternalResultsResponse<Configsmtp> fetchAllConfigsmtps(Configsmtp  configsmtp);

	/**
* Fetch configsmtps by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configsmtp> fetchConfigsmtpsByRequest(ConfigsmtpInquiryRequest request);


//===================================### CONFIGURACAONFE ####======================================
	/**

	/**
	 * Insert configuracaonfe.
	 *
* @param request the configuracaonfe maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configuracaonfe> insertConfiguracaonfe(ConfiguracaonfeMaintenanceRequest request);

	/**
* Update configuracaonfe.
*
* @param request the configuracaonfe maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configuracaonfe> updateConfiguracaonfe(ConfiguracaonfeMaintenanceRequest request);

	/**
* Delete configuracaonfe.
*
* @param request the configuracaonfe maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configuracaonfe> deleteConfiguracaonfe(ConfiguracaonfeMaintenanceRequest request);

	/**
* Refresh configuracaonfes.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Configuracaonfe> refreshConfiguracaonfes(RefreshRequest request);

	/**
* Fetch configuracaonfe by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configuracaonfe> fetchConfiguracaonfeById(FetchByIdRequest request);

	/**
* Fetch all configuracaonfes.
*
* @return the internal results response< configuracaonfe>
*/
	public InternalResultsResponse<Configuracaonfe> fetchAllConfiguracaonfes(Configuracaonfe  configuracaonfe);

	/**
* Fetch configuracaonfes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configuracaonfe> fetchConfiguracaonfesByRequest(ConfiguracaonfeInquiryRequest request);


//===================================### CONFIGVENDAS ####======================================
	/**

	/**
	 * Insert configvendas.
	 *
* @param request the configvendas maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configvendas> insertConfigvendas(ConfigvendasMaintenanceRequest request);

	/**
* Update configvendas.
*
* @param request the configvendas maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configvendas> updateConfigvendas(ConfigvendasMaintenanceRequest request);

	/**
* Delete configvendas.
*
* @param request the configvendas maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Configvendas> deleteConfigvendas(ConfigvendasMaintenanceRequest request);

	/**
* Refresh configvendass.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Configvendas> refreshConfigvendass(RefreshRequest request);

	/**
* Fetch configvendas by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configvendas> fetchConfigvendasById(FetchByIdRequest request);

	/**
* Fetch all configvendass.
*
* @return the internal results response< configvendas>
*/
	public InternalResultsResponse<Configvendas> fetchAllConfigvendass(Configvendas  configvendas);

	/**
* Fetch configvendass by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Configvendas> fetchConfigvendassByRequest(ConfigvendasInquiryRequest request);

}
