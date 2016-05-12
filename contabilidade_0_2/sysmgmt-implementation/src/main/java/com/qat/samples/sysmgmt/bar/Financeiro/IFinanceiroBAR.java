/** create by system gera-java version 1.0.0 11/05/2016 23:5 : 6*/
package com.qat.samples.sysmgmt.bar.Financeiro;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.financeiro.model.BaixaTitulo;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.financeiro.model.TipoBaixa;
import com.qat.samples.sysmgmt.financeiro.model.Titulo;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

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
	public ContasPagar fetchContasPagarById(FetchByIdRequest request);

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
	 * Fetch titulo by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Titulo fetchTituloById(FetchByIdRequest request);

	/**
* Insert titulo.
*
* @param titulo the titulo
*
* @return the internal response
*/
	public InternalResponse insertTitulo(Titulo titulo);

	/**
* Update titulo.
*
* @param titulo the titulo
*
* @return the internal response
*/
	public InternalResponse updateTitulo(Titulo titulo);

	/**
* Delete titulo.
*
* @param titulo the titulo
*
* @return the internal response
*/
	public InternalResponse deleteTituloById(Titulo titulo);

	/**
* Delete all titulos.
*
* @return the internal response
*/
	public InternalResponse deleteAllTitulos();

	/**
* Fetch all titulos.
*
* @return the list< titulo>
*/
	public InternalResultsResponse<Titulo> fetchAllTitulos(Titulo  titulo);

	/**
* Fetch titulos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Titulo> fetchTitulosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch baixatitulo by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public BaixaTitulo fetchBaixaTituloById(FetchByIdRequest request);

	/**
* Insert baixatitulo.
*
* @param baixatitulo the baixatitulo
*
* @return the internal response
*/
	public InternalResponse insertBaixaTitulo(BaixaTitulo baixatitulo);

	/**
* Update baixatitulo.
*
* @param baixatitulo the baixatitulo
*
* @return the internal response
*/
	public InternalResponse updateBaixaTitulo(BaixaTitulo baixatitulo);

	/**
* Delete baixatitulo.
*
* @param baixatitulo the baixatitulo
*
* @return the internal response
*/
	public InternalResponse deleteBaixaTituloById(BaixaTitulo baixatitulo);

	/**
* Delete all baixatitulos.
*
* @return the internal response
*/
	public InternalResponse deleteAllBaixaTitulos();

	/**
* Fetch all baixatitulos.
*
* @return the list< baixatitulo>
*/
	public InternalResultsResponse<BaixaTitulo> fetchAllBaixaTitulos(BaixaTitulo  baixatitulo);

	/**
* Fetch baixatitulos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<BaixaTitulo> fetchBaixaTitulosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch tipobaixa by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public TipoBaixa fetchTipoBaixaById(FetchByIdRequest request);

	/**
* Insert tipobaixa.
*
* @param tipobaixa the tipobaixa
*
* @return the internal response
*/
	public InternalResponse insertTipoBaixa(TipoBaixa tipobaixa);

	/**
* Update tipobaixa.
*
* @param tipobaixa the tipobaixa
*
* @return the internal response
*/
	public InternalResponse updateTipoBaixa(TipoBaixa tipobaixa);

	/**
* Delete tipobaixa.
*
* @param tipobaixa the tipobaixa
*
* @return the internal response
*/
	public InternalResponse deleteTipoBaixaById(TipoBaixa tipobaixa);

	/**
* Delete all tipobaixas.
*
* @return the internal response
*/
	public InternalResponse deleteAllTipoBaixas();

	/**
* Fetch all tipobaixas.
*
* @return the list< tipobaixa>
*/
	public InternalResultsResponse<TipoBaixa> fetchAllTipoBaixas(TipoBaixa  tipobaixa);

	/**
* Fetch tipobaixas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<TipoBaixa> fetchTipoBaixasByRequest(PagedInquiryRequest request);

	/**
	 * Fetch contasreceber by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public ContasReceber fetchContasReceberById(FetchByIdRequest request);

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
	public CondPag fetchCondPagById(FetchByIdRequest request);

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
	public FormaPg fetchFormaPgById(FetchByIdRequest request);

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
	public Banco fetchBancoById(FetchByIdRequest request);

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
	public ContaCorrente fetchContaCorrenteById(FetchByIdRequest request);

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
	public Caixa fetchCaixaById(FetchByIdRequest request);

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
