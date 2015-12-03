package com.qat.samples.sysmgmt.site.bai;

import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoMaintenanceRequest;
import com.qat.samples.sysmgmt.contato.model.response.ContatoResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.response.OrdemServicoResponse;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.response.SiteResponse;

/**
 * The Interface ISiteBAI. (Business Area Interface - BAI)
 */
public interface ISiteBAI
{

	public SiteResponse insertSite(SiteMaintenanceRequest request);

	public SiteResponse updateSite(SiteMaintenanceRequest request);

	public SiteResponse deleteSite(SiteMaintenanceRequest request);

	public SiteResponse fetchSiteById(FetchByIdRequest request);

	public SiteResponse fetchSitesByRequest(PagedInquiryRequest request);

	public SiteResponse refreshSites(RefreshRequest request);

	public SiteResponse fetchAllSites(FetchAllRequest request);

	// contato
	public ContatoResponse insertContato(ContatoMaintenanceRequest request);

	public ContatoResponse updateContato(ContatoMaintenanceRequest request);

	public ContatoResponse deleteContato(ContatoMaintenanceRequest request);

	public ContatoResponse fetchContatoById(FetchByIdRequest request);

	public ContatoResponse fetchContatosByRequest(ContatoInquiryRequest request);

	public ContatoResponse refreshContatos(RefreshRequest request);

	public ContatoResponse fetchAllContatos(FetchAllRequest request);

	// OS

	public OrdemServicoResponse insertOrdemServico(OrdemServicoMaintenanceRequest request);

	public OrdemServicoResponse updateOrdemServico(OrdemServicoMaintenanceRequest request);

	public OrdemServicoResponse deleteOrdemServico(OrdemServicoMaintenanceRequest request);

	public OrdemServicoResponse fetchOrdemServicoById(FetchByIdRequest request);

	public OrdemServicoResponse fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request);

	public OrdemServicoResponse refreshOrdemServicos(RefreshRequest request);

	public OrdemServicoResponse fetchAllOrdemServicos(FetchAllRequest request);

}
