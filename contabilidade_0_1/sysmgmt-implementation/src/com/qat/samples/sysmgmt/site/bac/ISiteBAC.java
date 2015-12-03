package com.qat.samples.sysmgmt.site.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.contato.Contato;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.site.Site;

/**
 * The Interface ISiteBAC. (Business Area Component - BAC)
 */
public interface ISiteBAC
{

	public InternalResponse insertSite(Site procedure);

	public InternalResponse updateSite(Site procedure);

	public InternalResponse deleteSite(Site procedure);

	public void refreshSites(Integer refreshNumber);

	public InternalResultsResponse<Site> fetchSiteById(FetchByIdRequest request);

	public InternalResultsResponse<Site> fetchAllSites();

	public InternalResultsResponse<Site> fetchSitesByRequest(PagedInquiryRequest request);

	// Contato
	public InternalResponse insertContato(Contato procedure);

	public InternalResponse updateContato(Contato procedure);

	public InternalResponse deleteContato(Contato procedure);

	public void refreshContatos(Integer refreshNumber);

	public InternalResultsResponse<Contato> fetchContatoById(FetchByIdRequest request);

	public InternalResultsResponse<Contato> fetchAllContatos();

	public InternalResultsResponse<Contato> fetchContatosByRequest(ContatoInquiryRequest request);

	// OS
	public InternalResponse insertOrdemServico(OrdemServico ordemServico);

	public InternalResponse updateOrdemServico(OrdemServico procedure);

	public InternalResponse deleteOrdemServico(OrdemServico procedure);

	public void refreshOrdemServicos(Integer refreshNumber);

	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request);

	public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos();

	public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request);
}
