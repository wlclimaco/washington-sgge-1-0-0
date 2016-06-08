/** create by system gera-java version 1.0.0 07/05/2016 18:30 : 34*/
package com.qat.samples.sysmgmt.bar.Site;
import org.apache.ibatis.annotations.Insert;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contabilidade.model.PlanoByServico;
import com.qat.samples.sysmgmt.contabilidade.model.PlanoBySite;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.ContatoItens;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServicoItens;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.site.model.ServicoAndPlano;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.site.model.request.SiteInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface SiteBAR.. (Data Access Component - DAC)
 */
public interface ISiteBAR
{

	/**
	 * Fetch servico by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Servico fetchServicoById(FetchByIdRequest request);

	/**
* Insert servico.
*
* @param servico the servico
*
* @return the internal response
*/
	public InternalResponse insertServico(Servico servico);

	/**
* Update servico.
*
* @param servico the servico
*
* @return the internal response
*/
	public InternalResponse updateServico(Servico servico);

	/**
* Delete servico.
*
* @param servico the servico
*
* @return the internal response
*/
	public InternalResponse deleteServicoById(Servico servico);

	/**
* Delete all servicos.
*
* @return the internal response
*/
	public InternalResponse deleteAllServicos();

	/**
* Fetch all servicos.
*
* @return the list< servico>
*/
	public InternalResultsResponse<Servico> fetchAllServicos(Servico  servico);

	/**
* Fetch servicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Servico> fetchServicosByRequest(ServicoInquiryRequest request);

	/**
	 * Fetch site by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Site fetchSiteById(FetchByIdRequest request);

	/**
* Insert site.
*
* @param site the site
*
* @return the internal response
*/
	public InternalResponse insertSite(Site site);

	/**
* Update site.
*
* @param site the site
*
* @return the internal response
*/
	public InternalResponse updateSite(Site site);

	/**
* Delete site.
*
* @param site the site
*
* @return the internal response
*/
	public InternalResponse deleteSiteById(Site site);

	/**
* Delete all sites.
*
* @return the internal response
*/
	public InternalResponse deleteAllSites();

	/**
* Fetch all sites.
*
* @return the list< site>
*/
	public InternalResultsResponse<Site> fetchAllSites(Site  site);

	/**
* Fetch sites by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Site> fetchSitesByRequest(SiteInquiryRequest request);

	/**
	 * Fetch contato by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Contato fetchContatoById(FetchByIdRequest request);

	/**
* Insert contato.
*
* @param contato the contato
*
* @return the internal response
*/
	public InternalResponse insertContato(Contato contato);

	/**
* Update contato.
*
* @param contato the contato
*
* @return the internal response
*/
	public InternalResponse updateContato(Contato contato);

	/**
* Delete contato.
*
* @param contato the contato
*
* @return the internal response
*/
	public InternalResponse deleteContatoById(Contato contato);

	/**
* Delete all contatos.
*
* @return the internal response
*/
	public InternalResponse deleteAllContatos();

	/**
* Fetch all contatos.
*
* @return the list< contato>
*/
	public InternalResultsResponse<Contato> fetchAllContatos(Contato  contato);

	/**
* Fetch contatos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Contato> fetchContatosByRequest(ContatoInquiryRequest request);

	/**
	 * Fetch contatoitens by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public ContatoItens fetchContatoItensById(FetchByIdRequest request);

	/**
* Insert contatoitens.
*
* @param contatoitens the contatoitens
*
* @return the internal response
*/
	public InternalResponse insertContatoItens(ContatoItens contatoitens);

	/**
* Update contatoitens.
*
* @param contatoitens the contatoitens
*
* @return the internal response
*/
	public InternalResponse updateContatoItens(ContatoItens contatoitens);

	/**
* Delete contatoitens.
*
* @param contatoitens the contatoitens
*
* @return the internal response
*/
	public InternalResponse deleteContatoItensById(ContatoItens contatoitens);

	/**
* Delete all contatoitenss.
*
* @return the internal response
*/
	public InternalResponse deleteAllContatoItenss();

	/**
* Fetch all contatoitenss.
*
* @return the list< contatoitens>
*/
	public InternalResultsResponse<ContatoItens> fetchAllContatoItenss(ContatoItens  contatoitens);

	/**
* Fetch contatoitenss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ContatoItens> fetchContatoItenssByRequest(PagedInquiryRequest request);

	/**
	 * Fetch ordemservico by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public OrdemServico fetchOrdemServicoById(FetchByIdRequest request);

	/**
* Insert ordemservico.
*
* @param ordemservico the ordemservico
*
* @return the internal response
*/
	public InternalResponse insertOrdemServico(OrdemServico ordemservico);

	/**
* Update ordemservico.
*
* @param ordemservico the ordemservico
*
* @return the internal response
*/
	public InternalResponse updateOrdemServico(OrdemServico ordemservico);

	/**
* Delete ordemservico.
*
* @param ordemservico the ordemservico
*
* @return the internal response
*/
	public InternalResponse deleteOrdemServicoById(OrdemServico ordemservico);

	/**
* Delete all ordemservicos.
*
* @return the internal response
*/
	public InternalResponse deleteAllOrdemServicos();

	/**
* Fetch all ordemservicos.
*
* @return the list< ordemservico>
*/
	public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos(OrdemServico  ordemservico);

	/**
* Fetch ordemservicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request);

	/**
	 * Fetch ordemservicoitens by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public OrdemServicoItens fetchOrdemServicoItensById(FetchByIdRequest request);

	/**
* Insert ordemservicoitens.
*
* @param ordemservicoitens the ordemservicoitens
*
* @return the internal response
*/
	public InternalResponse insertOrdemServicoItens(OrdemServicoItens ordemservicoitens);

	/**
* Update ordemservicoitens.
*
* @param ordemservicoitens the ordemservicoitens
*
* @return the internal response
*/
	public InternalResponse updateOrdemServicoItens(OrdemServicoItens ordemservicoitens);

	/**
* Delete ordemservicoitens.
*
* @param ordemservicoitens the ordemservicoitens
*
* @return the internal response
*/
	public InternalResponse deleteOrdemServicoItensById(OrdemServicoItens ordemservicoitens);

	/**
* Delete all ordemservicoitenss.
*
* @return the internal response
*/
	public InternalResponse deleteAllOrdemServicoItenss();

	/**
* Fetch all ordemservicoitenss.
*
* @return the list< ordemservicoitens>
*/
	public InternalResultsResponse<OrdemServicoItens> fetchAllOrdemServicoItenss(OrdemServicoItens  ordemservicoitens);

	/**
* Fetch ordemservicoitenss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServicoItens> fetchOrdemServicoItenssByRequest(PagedInquiryRequest request);

	/**
	 * Fetch plano by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Plano fetchPlanoById(FetchByIdRequest request);

	/**
* Insert plano.
*
* @param plano the plano
*
* @return the internal response
*/
	public InternalResponse insertPlano(Plano plano);

	/**
* Update plano.
*
* @param plano the plano
*
* @return the internal response
*/
	public InternalResponse updatePlano(Plano plano);

	/**
* Delete plano.
*
* @param plano the plano
*
* @return the internal response
*/
	public InternalResponse deletePlanoById(Plano plano);

	/**
* Delete all planos.
*
* @return the internal response
*/
	public InternalResponse deleteAllPlanos();

	/**
* Fetch all planos.
*
* @return the list< plano>
*/
	public InternalResultsResponse<Plano> fetchAllPlanos(Plano  plano);

	/**
* Fetch planos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Plano> fetchPlanosByRequest(PlanoInquiryRequest request);


	/**
* Insert plano.
*
* @param plano the plano
*
* @return the internal response
*/
	public InternalResponse insertPlanoBySite(PlanoBySite plano);

	/**
* Update plano.
*
* @param plano the plano
*
* @return the internal response
*/
	public InternalResponse updatePlanoBySite(PlanoBySite plano);

	/**
* Delete plano.
*
* @param plano the plano
*
* @return the internal response
*/
	public InternalResponse deletePlanoBySiteById(PlanoBySite plano);

	public InternalResponse insertServicoByPlano(PlanoByServico servico);

	/**
* Update servico.
*
* @param servico the servico
*
* @return the internal response
*/
	public InternalResponse updateServicoByPlano(PlanoByServico servico);

	/**
* Delete servico.
*
* @param servico the servico
*
* @return the internal response
*/
	public InternalResponse deleteServicoByPlanoById(PlanoByServico servico);
	/**
	* Insert servicoandplano.
	*
	* @param servicoandplano the servicoandplano
	*
	* @return the internal response
	*/
		public InternalResponse insertServicoAndPlano(ServicoAndPlano servicoandplano);

		/**
	* Update servicoandplano.
	*
	* @param servicoandplano the servicoandplano
	*
	* @return the internal response
	*/
		public InternalResponse updateServicoAndPlano(ServicoAndPlano servicoandplano);

		/**
	* Delete servicoandplano.
	*
	* @param servicoandplano the servicoandplano
	*
	* @return the internal response
	*/
		public InternalResponse deleteServicoAndPlanoById(ServicoAndPlano servicoandplano);

		/**
	* Delete all servicoandplanos.
	*
	* @return the internal response
	*/
		public InternalResponse deleteAllServicoAndPlanos();

		/**
	* Fetch all servicoandplanos.
	*
	* @return the list< servicoandplano>
	*/
		public InternalResultsResponse<ServicoAndPlano> fetchAllServicoAndPlanos(ServicoAndPlano  servicoandplano);

		public ServicoAndPlano fetchServicoAndPlanoById(FetchByIdRequest request);
		/**
	* Fetch servicoandplanos by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<ServicoAndPlano> fetchServicoAndPlanosByRequest(PagedInquiryRequest request);

}
