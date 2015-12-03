package com.qat.samples.sysmgmt.site.bas.ws;

import javax.jws.WebService;

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
import com.qat.samples.sysmgmt.site.bai.ISiteBAI;
import com.qat.samples.sysmgmt.site.bas.ISiteBAS;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.response.SiteResponse;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class SiteBAS implements ISiteBAS
{

	/** The site bai. */
	private ISiteBAI siteBAI; // injected by Spring through setter

	/**
	 * Spring Sets the site bai.
	 * 
	 * @param siteBAI the new site bai
	 */
	public void setSiteBAI(ISiteBAI siteBAI)
	{
		this.siteBAI = siteBAI;
	}

	/**
	 * Gets the site bac.
	 * 
	 * @return the site bac
	 */
	public ISiteBAI getSiteBAI()
	{
		return siteBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISiteBAS#insertSite(com.qat.samples.sysmgmt.model.request.
	 * SiteMaintenanceRequest
	 * )
	 */
	@Override
	public SiteResponse insertSite(SiteMaintenanceRequest request)
	{
		return getSiteBAI().insertSite(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISiteBAS#updateSite(com.qat.samples.sysmgmt.model.request.
	 * SiteMaintenanceRequest
	 * )
	 */
	@Override
	public SiteResponse updateSite(SiteMaintenanceRequest request)
	{
		return getSiteBAI().updateSite(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISiteBAS#deleteSite(com.qat.samples.sysmgmt.model.request.
	 * SiteMaintenanceRequest
	 * )
	 */
	@Override
	public SiteResponse deleteSite(SiteMaintenanceRequest request)
	{
		return getSiteBAI().deleteSite(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.ISiteBAS#refreshSites(com.qat.samples.sysmgmt.model.request.
	 * RefreshRequest)
	 */
	// @Override
	// public SiteResponse refreshSites(RefreshRequest request)
	// {
	// // This method is demo code only. Do not view this as a QAT Global Standard.
	// return getSiteBAI().refreshSites(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.qat.samples.sysmgmt.bas.ISiteBAS#fetchAllSites(com.qat.samples.sysmgmt.model.request.
	// * FetchAllRequest)
	// */
	// @Override
	// public SiteResponse fetchAllSites(FetchAllRequest request)
	// {
	// return getSiteBAI().fetchAllSites(request);
	// }

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISiteBAS#fetchSiteById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public SiteResponse fetchSiteById(FetchByIdRequest request)
	{
		return getSiteBAI().fetchSiteById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISiteBAS#fetchSitesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public SiteResponse fetchSitesByRequest(PagedInquiryRequest request)
	{
		return getSiteBAI().fetchSitesByRequest(request);
	}

	@Override
	public SiteResponse fetchAllSites(FetchAllRequest request)
	{
		return getSiteBAI().fetchAllSites(request);
	}

	@Override
	public SiteResponse refreshSites(RefreshRequest request)
	{
		return getSiteBAI().refreshSites(request);
	}

	@Override
	public ContatoResponse insertContato(ContatoMaintenanceRequest request)
	{
		return getSiteBAI().insertContato(request);
	}

	@Override
	public ContatoResponse updateContato(ContatoMaintenanceRequest request)
	{
		return getSiteBAI().updateContato(request);
	}

	@Override
	public ContatoResponse deleteContato(ContatoMaintenanceRequest request)
	{
		return getSiteBAI().deleteContato(request);
	}

	@Override
	public ContatoResponse fetchAllContatos(FetchAllRequest request)
	{
		return getSiteBAI().fetchAllContatos(request);
	}

	@Override
	public ContatoResponse fetchContatoById(FetchByIdRequest request)
	{
		return getSiteBAI().fetchContatoById(request);
	}

	@Override
	public ContatoResponse refreshContatos(RefreshRequest request)
	{
		return getSiteBAI().refreshContatos(request);
	}

	@Override
	public ContatoResponse fetchContatosByRequest(ContatoInquiryRequest request)
	{
		return getSiteBAI().fetchContatosByRequest(request);
	}

	@Override
	public OrdemServicoResponse insertOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		return getSiteBAI().insertOrdemServico(request);
	}

	@Override
	public OrdemServicoResponse updateOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		return getSiteBAI().updateOrdemServico(request);
	}

	@Override
	public OrdemServicoResponse deleteOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		return getSiteBAI().deleteOrdemServico(request);
	}

	@Override
	public OrdemServicoResponse fetchAllOrdemServicos(FetchAllRequest request)
	{
		return getSiteBAI().fetchAllOrdemServicos(request);
	}

	@Override
	public OrdemServicoResponse fetchOrdemServicoById(FetchByIdRequest request)
	{
		return getSiteBAI().fetchOrdemServicoById(request);
	}

	@Override
	public OrdemServicoResponse refreshOrdemServicos(RefreshRequest request)
	{
		return getSiteBAI().refreshOrdemServicos(request);
	}

	@Override
	public OrdemServicoResponse fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request)
	{
		return getSiteBAI().fetchOrdemServicosByRequest(request);
	}

}
