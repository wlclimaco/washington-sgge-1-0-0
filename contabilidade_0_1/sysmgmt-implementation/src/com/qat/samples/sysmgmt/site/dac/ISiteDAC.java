package com.qat.samples.sysmgmt.site.dac;

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
 * The Interface ISiteDAC. (Data Access Component - DAC)
 */
public interface ISiteDAC
{

	public InternalResponse insertSite(Site site);

	public InternalResponse updateSite(Site site);

	public InternalResponse deleteSite(Site site);

	public InternalResultsResponse<Site> fetchAllSites();

	public InternalResultsResponse<Site> fetchSiteById(FetchByIdRequest request);

	public InternalResultsResponse<Site> fetchSitesByRequest(PagedInquiryRequest request);

	// Contato
	public InternalResponse insertContato(Contato contato);

	public InternalResponse updateContato(Contato site);

	public InternalResponse deleteContato(Contato site);

	public InternalResultsResponse<Contato> fetchAllContatos();

	public InternalResultsResponse<Contato> fetchContatoById(FetchByIdRequest request);

	public InternalResultsResponse<Contato> fetchContatosByRequest(ContatoInquiryRequest request);

	// OS

	public InternalResponse insertOrdemServico(OrdemServico site);

	public InternalResponse updateOrdemServico(OrdemServico site);

	public InternalResponse deleteOrdemServico(OrdemServico site);

	public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos();

	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request);

	public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request);

}
