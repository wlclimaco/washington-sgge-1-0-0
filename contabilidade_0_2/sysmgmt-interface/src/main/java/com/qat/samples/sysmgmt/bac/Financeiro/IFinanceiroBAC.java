package com.qat.samples.sysmgmt.bac.Financeiro;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.banco.model.request.BancoMaintenanceRequest;
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IFinanceiroBAC. (Business Area Component - BAC)
 */
public interface IFinanceiroBAC
{



//===================================### CONTASPAGAR ####======================================
	/**

	/**
	 * Insert contaspagar.
	 *
* @param request the contaspagar maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ContasPagar> insertContasPagar(ContasPagarMaintenanceRequest request);

	/**
* Update contaspagar.
*
* @param request the contaspagar maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ContasPagar> updateContasPagar(ContasPagarMaintenanceRequest request);

	/**
* Delete contaspagar.
*
* @param request the contaspagar maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ContasPagar> deleteContasPagar(ContasPagarMaintenanceRequest request);

	/**
* Refresh contaspagars.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ContasPagar> refreshContasPagars(RefreshRequest request);

	/**
* Fetch contaspagar by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContasPagar> fetchContasPagarById(FetchByIdRequest request);

	/**
* Fetch all contaspagars.
*
* @return the internal results response< contaspagar>
*/
	public InternalResultsResponse<ContasPagar> fetchAllContasPagars(ContasPagar  contaspagar);

	/**
* Fetch contaspagars by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContasPagar> fetchContasPagarsByRequest(ContasPagarInquiryRequest request);


//===================================### CONTASRECEBER ####======================================
	/**

	/**
	 * Insert contasreceber.
	 *
* @param request the contasreceber maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ContasReceber> insertContasReceber(ContasReceberMaintenanceRequest request);

	/**
* Update contasreceber.
*
* @param request the contasreceber maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ContasReceber> updateContasReceber(ContasReceberMaintenanceRequest request);

	/**
* Delete contasreceber.
*
* @param request the contasreceber maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ContasReceber> deleteContasReceber(ContasReceberMaintenanceRequest request);

	/**
* Refresh contasrecebers.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ContasReceber> refreshContasRecebers(RefreshRequest request);

	/**
* Fetch contasreceber by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContasReceber> fetchContasReceberById(FetchByIdRequest request);

	/**
* Fetch all contasrecebers.
*
* @return the internal results response< contasreceber>
*/
	public InternalResultsResponse<ContasReceber> fetchAllContasRecebers(ContasReceber  contasreceber);

	/**
* Fetch contasrecebers by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContasReceber> fetchContasRecebersByRequest(ContasReceberInquiryRequest request);


//===================================### CONDPAG ####======================================
	/**

	/**
	 * Insert condpag.
	 *
* @param request the condpag maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<CondPag> insertCondPag(CondPagMaintenanceRequest request);

	/**
* Update condpag.
*
* @param request the condpag maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<CondPag> updateCondPag(CondPagMaintenanceRequest request);

	/**
* Delete condpag.
*
* @param request the condpag maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<CondPag> deleteCondPag(CondPagMaintenanceRequest request);

	/**
* Refresh condpags.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<CondPag> refreshCondPags(RefreshRequest request);

	/**
* Fetch condpag by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<CondPag> fetchCondPagById(FetchByIdRequest request);

	/**
* Fetch all condpags.
*
* @return the internal results response< condpag>
*/
	public InternalResultsResponse<CondPag> fetchAllCondPags(CondPag  condpag);

	/**
* Fetch condpags by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<CondPag> fetchCondPagsByRequest(CondPagInquiryRequest request);


//===================================### FORMAPG ####======================================
	/**

	/**
	 * Insert formapg.
	 *
* @param request the formapg maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<FormaPg> insertFormaPg(FormaPgMaintenanceRequest request);

	/**
* Update formapg.
*
* @param request the formapg maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<FormaPg> updateFormaPg(FormaPgMaintenanceRequest request);

	/**
* Delete formapg.
*
* @param request the formapg maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<FormaPg> deleteFormaPg(FormaPgMaintenanceRequest request);

	/**
* Refresh formapgs.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<FormaPg> refreshFormaPgs(RefreshRequest request);

	/**
* Fetch formapg by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<FormaPg> fetchFormaPgById(FetchByIdRequest request);

	/**
* Fetch all formapgs.
*
* @return the internal results response< formapg>
*/
	public InternalResultsResponse<FormaPg> fetchAllFormaPgs(FormaPg  formapg);

	/**
* Fetch formapgs by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<FormaPg> fetchFormaPgsByRequest(FormaPgInquiryRequest request);


//===================================### BANCO ####======================================
	/**

	/**
	 * Insert banco.
	 *
* @param request the banco maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Banco> insertBanco(BancoMaintenanceRequest request);

	/**
* Update banco.
*
* @param request the banco maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Banco> updateBanco(BancoMaintenanceRequest request);

	/**
* Delete banco.
*
* @param request the banco maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Banco> deleteBanco(BancoMaintenanceRequest request);

	/**
* Refresh bancos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Banco> refreshBancos(RefreshRequest request);

	/**
* Fetch banco by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Banco> fetchBancoById(FetchByIdRequest request);

	/**
* Fetch all bancos.
*
* @return the internal results response< banco>
*/
	public InternalResultsResponse<Banco> fetchAllBancos(Banco  banco);

	/**
* Fetch bancos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Banco> fetchBancosByRequest(BancoInquiryRequest request);


//===================================### CONTACORRENTE ####======================================
	/**

	/**
	 * Insert contacorrente.
	 *
* @param request the contacorrente maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ContaCorrente> insertContaCorrente(ContaCorrenteMaintenanceRequest request);

	/**
* Update contacorrente.
*
* @param request the contacorrente maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ContaCorrente> updateContaCorrente(ContaCorrenteMaintenanceRequest request);

	/**
* Delete contacorrente.
*
* @param request the contacorrente maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ContaCorrente> deleteContaCorrente(ContaCorrenteMaintenanceRequest request);

	/**
* Refresh contacorrentes.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ContaCorrente> refreshContaCorrentes(RefreshRequest request);

	/**
* Fetch contacorrente by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContaCorrente> fetchContaCorrenteById(FetchByIdRequest request);

	/**
* Fetch all contacorrentes.
*
* @return the internal results response< contacorrente>
*/
	public InternalResultsResponse<ContaCorrente> fetchAllContaCorrentes(ContaCorrente  contacorrente);

	/**
* Fetch contacorrentes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContaCorrente> fetchContaCorrentesByRequest(ContaCorrenteInquiryRequest request);


//===================================### CAIXA ####======================================
	/**

	/**
	 * Insert caixa.
	 *
* @param request the caixa maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Caixa> insertCaixa(CaixaMaintenanceRequest request);

	/**
* Update caixa.
*
* @param request the caixa maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Caixa> updateCaixa(CaixaMaintenanceRequest request);

	/**
* Delete caixa.
*
* @param request the caixa maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Caixa> deleteCaixa(CaixaMaintenanceRequest request);

	/**
* Refresh caixas.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Caixa> refreshCaixas(RefreshRequest request);

	/**
* Fetch caixa by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Caixa> fetchCaixaById(FetchByIdRequest request);

	/**
* Fetch all caixas.
*
* @return the internal results response< caixa>
*/
	public InternalResultsResponse<Caixa> fetchAllCaixas(Caixa  caixa);

	/**
* Fetch caixas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Caixa> fetchCaixasByRequest(CaixaInquiryRequest request);

}
