package com.qat.samples.sysmgmt.bar.Financeiro;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface FinanceiroBAR.. (Data Access Component - DAC)
 */
public interface IFinanceiroBAR
{

	/**
	 * Fetch contaspagar by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContasPagar> fetchContasPagarById(FetchByIdRequest request);

	/**
* Insert contaspagar.
*
* @param contaspagar the contaspagar
*
* @return the internal response
*/
	public InternalResponse insertContasPagar(ContasPagar contaspagar);

	/**
* Update contaspagar.
*
* @param contaspagar the contaspagar
*
* @return the internal response
*/
	public InternalResponse updateContasPagar(ContasPagar contaspagar);

	/**
* Delete contaspagar.
*
* @param contaspagar the contaspagar
*
* @return the internal response
*/
	public InternalResponse deleteContasPagarById(ContasPagar contaspagar);

	/**
* Delete all contaspagars.
*
* @return the internal response
*/
	public InternalResponse deleteAllContasPagars();

	/**
* Fetch all contaspagars.
*
* @return the list< contaspagar>
*/
	public InternalResultsResponse<ContasPagar> fetchAllContasPagars(ContasPagar  contaspagar);

	/**
* Fetch contaspagars by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContasPagar> fetchContasPagarsByRequest(ContasPagarInquiryRequest request);

	/**
	 * Fetch contasreceber by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContasReceber> fetchContasReceberById(FetchByIdRequest request);

	/**
* Insert contasreceber.
*
* @param contasreceber the contasreceber
*
* @return the internal response
*/
	public InternalResponse insertContasReceber(ContasReceber contasreceber);

	/**
* Update contasreceber.
*
* @param contasreceber the contasreceber
*
* @return the internal response
*/
	public InternalResponse updateContasReceber(ContasReceber contasreceber);

	/**
* Delete contasreceber.
*
* @param contasreceber the contasreceber
*
* @return the internal response
*/
	public InternalResponse deleteContasReceberById(ContasReceber contasreceber);

	/**
* Delete all contasrecebers.
*
* @return the internal response
*/
	public InternalResponse deleteAllContasRecebers();

	/**
* Fetch all contasrecebers.
*
* @return the list< contasreceber>
*/
	public InternalResultsResponse<ContasReceber> fetchAllContasRecebers(ContasReceber  contasreceber);

	/**
* Fetch contasrecebers by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContasReceber> fetchContasRecebersByRequest(ContasReceberInquiryRequest request);

	/**
	 * Fetch condpag by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<CondPag> fetchCondPagById(FetchByIdRequest request);

	/**
* Insert condpag.
*
* @param condpag the condpag
*
* @return the internal response
*/
	public InternalResponse insertCondPag(CondPag condpag);

	/**
* Update condpag.
*
* @param condpag the condpag
*
* @return the internal response
*/
	public InternalResponse updateCondPag(CondPag condpag);

	/**
* Delete condpag.
*
* @param condpag the condpag
*
* @return the internal response
*/
	public InternalResponse deleteCondPagById(CondPag condpag);

	/**
* Delete all condpags.
*
* @return the internal response
*/
	public InternalResponse deleteAllCondPags();

	/**
* Fetch all condpags.
*
* @return the list< condpag>
*/
	public InternalResultsResponse<CondPag> fetchAllCondPags(CondPag  condpag);

	/**
* Fetch condpags by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<CondPag> fetchCondPagsByRequest(CondPagInquiryRequest request);

	/**
	 * Fetch formapg by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<FormaPg> fetchFormaPgById(FetchByIdRequest request);

	/**
* Insert formapg.
*
* @param formapg the formapg
*
* @return the internal response
*/
	public InternalResponse insertFormaPg(FormaPg formapg);

	/**
* Update formapg.
*
* @param formapg the formapg
*
* @return the internal response
*/
	public InternalResponse updateFormaPg(FormaPg formapg);

	/**
* Delete formapg.
*
* @param formapg the formapg
*
* @return the internal response
*/
	public InternalResponse deleteFormaPgById(FormaPg formapg);

	/**
* Delete all formapgs.
*
* @return the internal response
*/
	public InternalResponse deleteAllFormaPgs();

	/**
* Fetch all formapgs.
*
* @return the list< formapg>
*/
	public InternalResultsResponse<FormaPg> fetchAllFormaPgs(FormaPg  formapg);

	/**
* Fetch formapgs by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<FormaPg> fetchFormaPgsByRequest(FormaPgInquiryRequest request);

	/**
	 * Fetch banco by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Banco> fetchBancoById(FetchByIdRequest request);

	/**
* Insert banco.
*
* @param banco the banco
*
* @return the internal response
*/
	public InternalResponse insertBanco(Banco banco);

	/**
* Update banco.
*
* @param banco the banco
*
* @return the internal response
*/
	public InternalResponse updateBanco(Banco banco);

	/**
* Delete banco.
*
* @param banco the banco
*
* @return the internal response
*/
	public InternalResponse deleteBancoById(Banco banco);

	/**
* Delete all bancos.
*
* @return the internal response
*/
	public InternalResponse deleteAllBancos();

	/**
* Fetch all bancos.
*
* @return the list< banco>
*/
	public InternalResultsResponse<Banco> fetchAllBancos(Banco  banco);

	/**
* Fetch bancos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Banco> fetchBancosByRequest(BancoInquiryRequest request);

	/**
	 * Fetch contacorrente by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContaCorrente> fetchContaCorrenteById(FetchByIdRequest request);

	/**
* Insert contacorrente.
*
* @param contacorrente the contacorrente
*
* @return the internal response
*/
	public InternalResponse insertContaCorrente(ContaCorrente contacorrente);

	/**
* Update contacorrente.
*
* @param contacorrente the contacorrente
*
* @return the internal response
*/
	public InternalResponse updateContaCorrente(ContaCorrente contacorrente);

	/**
* Delete contacorrente.
*
* @param contacorrente the contacorrente
*
* @return the internal response
*/
	public InternalResponse deleteContaCorrenteById(ContaCorrente contacorrente);

	/**
* Delete all contacorrentes.
*
* @return the internal response
*/
	public InternalResponse deleteAllContaCorrentes();

	/**
* Fetch all contacorrentes.
*
* @return the list< contacorrente>
*/
	public InternalResultsResponse<ContaCorrente> fetchAllContaCorrentes(ContaCorrente  contacorrente);

	/**
* Fetch contacorrentes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContaCorrente> fetchContaCorrentesByRequest(ContaCorrenteInquiryRequest request);

	/**
	 * Fetch caixa by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Caixa> fetchCaixaById(FetchByIdRequest request);

	/**
* Insert caixa.
*
* @param caixa the caixa
*
* @return the internal response
*/
	public InternalResponse insertCaixa(Caixa caixa);

	/**
* Update caixa.
*
* @param caixa the caixa
*
* @return the internal response
*/
	public InternalResponse updateCaixa(Caixa caixa);

	/**
* Delete caixa.
*
* @param caixa the caixa
*
* @return the internal response
*/
	public InternalResponse deleteCaixaById(Caixa caixa);

	/**
* Delete all caixas.
*
* @return the internal response
*/
	public InternalResponse deleteAllCaixas();

	/**
* Fetch all caixas.
*
* @return the list< caixa>
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
