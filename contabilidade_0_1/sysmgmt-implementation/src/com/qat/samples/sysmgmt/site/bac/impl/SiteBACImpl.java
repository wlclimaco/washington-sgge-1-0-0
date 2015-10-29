package com.qat.samples.sysmgmt.site.bac.impl;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.site.Site;
import com.qat.samples.sysmgmt.site.bac.ISiteBAC;
import com.qat.samples.sysmgmt.site.dac.ISiteDAC;

/**
 * Implementation of the ISiteBAC leveraging a BAD, SiteBAD.
 */
public class SiteBACImpl implements ISiteBAC
{

	private ISiteDAC siteDAC;

	public ISiteDAC getSiteDAC()
	{
		return siteDAC;
	}

	public void setSiteDAC(ISiteDAC siteDAC)
	{
		this.siteDAC = siteDAC;
	}

	@Override
	public InternalResponse insertSite(Site site)
	{
		return getSiteDAC().insertSite(site);
	}

	@Override
	public InternalResponse updateSite(Site site)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteSite(Site site)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshSites(Integer refreshNumber)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public InternalResultsResponse<Site> fetchSiteById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Site> fetchAllSites()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Site> fetchSitesByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	// /** The Constant REFRESH_SEED. */
	// private static final int REFRESH_SEED = 1050;
	//
	// /** The Constant UPDATE_SEED. */
	// private static final int UPDATE_SEED = 3000;
	//
	// /** The Constant INSERT_SEED. */
	// private static final int INSERT_SEED = 9000;
	//
	// /** The Constant MINIMUM_ENTRIES. */
	// private static final int MINIMUM_ENTRIES = 5;
	//
	// /** The Constant DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG. */
	// private static final String DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG =
	// "sysmgmt.base.supermercadobacimpl.defaultexception";
	//
	// /** The supermercado dac. */
	// private ISiteDAC supermercadoDAC; // injected by Spring through setter
	//
	// private IEnderecoDAC enderecoDAC; // injected by Spring through setter
	//
	// private IDocumentoDAC documentoDAC; // injected by Spring through setter
	//
	// /**
	// * Spring Sets the supermercado dac.
	// *
	// * @param supermercadoDAC the new supermercado dac
	// */
	// public void setSiteDAC(ISiteDAC supermercadoDAC)
	// {
	// this.supermercadoDAC = supermercadoDAC;
	// }
	//
	// /**
	// * Gets the supermercado dac.
	// *
	// * @return the supermercado dac
	// */
	// public ISiteDAC getSiteDAC()
	// {
	// return supermercadoDAC;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// *
	// com.qat.samples.sysmgmt.base.bac.ISiteBAC#insertSite(com.qat.samples.sysmgmt.base.model.Site
	// * )
	// */
	// public IEnderecoDAC getEnderecoDAC()
	// {
	// return enderecoDAC;
	// }
	//
	// public void setEnderecoDAC(IEnderecoDAC enderecoDAC)
	// {
	// this.enderecoDAC = enderecoDAC;
	// }
	//
	// public IDocumentoDAC getDocumentoDAC()
	// {
	// return documentoDAC;
	// }
	//
	// public void setDocumentoDAC(IDocumentoDAC documentoDAC)
	// {
	// this.documentoDAC = documentoDAC;
	// }
	//
	// @Override
	// public InternalResponse insertSite(Site supermercado)
	// {
	// // supermercado.setPrice(SiteBAD.calculatePrice(INSERT_SEED));
	// // InternalResponseLocal responseEndereco = new InternalResponseLocal();
	// // responseEndereco = getEnderecoDAC().insertEndereco(supermercado.getEnderecos().get(0));
	// // supermercado.getEnderecos().get(0).setId(responseEndereco.getId());
	// return getSiteDAC().insertSite(supermercado);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// *
	// com.qat.samples.sysmgmt.base.bac.ISiteBAC#updateSite(com.qat.samples.sysmgmt.base.model.Site
	// * )
	// */
	// @Override
	// public InternalResponse updateSite(Site supermercado)
	// {
	// // supermercado.setPrice(SiteBAD.calculatePrice(UPDATE_SEED));
	// InternalResponse internalResponse = getSiteDAC().updateSite(supermercado);
	// // Check for error because in business case all non-success returns are failures (updating of zero rows or
	// // optimistic locking error) according to the business
	// if (internalResponse.getStatus() != Status.OperationSuccess)
	// {
	// internalResponse.addMessage(DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error,
	// Message.MessageLevel.Object, new Object[] {internalResponse
	// .getStatus().toString()});
	// }
	// return internalResponse;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// *
	// com.qat.samples.sysmgmt.base.bac.ISiteBAC#deleteSite(com.qat.samples.sysmgmt.base.model.Site
	// * )
	// */
	// @Override
	// public InternalResponse deleteSite(Site supermercado)
	// {
	// InternalResponse internalResponse = getSiteDAC().deleteSite(supermercado);
	// // Check for error because in business case all non-success returns are failures (removal of zero rows)
	// // according to the business
	// if (internalResponse.getStatus() != Status.OperationSuccess)
	// {
	// internalResponse.addMessage(DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error,
	// Message.MessageLevel.Object, new Object[] {internalResponse
	// .getStatus().toString()});
	// }
	// return internalResponse;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.qat.samples.sysmgmt.base.bac.ISiteBAC#refreshSites(int)
	// */
	// @Override
	// public void refreshSites(Integer refreshNumber)
	// {
	// // This method is demo code only. Do not view this as a QAT Standard.
	// getSiteDAC().deleteAllSites();
	// refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;
	//
	// for (int i = 1; i <= refreshNumber; i++)
	// {
	// getSiteDAC().insertSite(new Site(i));
	// }
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.qat.samples.sysmgmt.base.bac.ISiteBAC#fetchAllSites()
	// */
	// @Override
	// public InternalResultsResponse<Site> fetchAllSites()
	// {
	// InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
	// response.getResultsList().addAll(getSiteDAC().fetchAllSites());
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.qat.samples.sysmgmt.base.bac.ISiteBAC#fetchSiteById(com.qat.samples.sysmgmt.base.model.
	// * Site
	// * )
	// */
	// @Override
	// public InternalResultsResponse<Site> fetchSiteById(FetchByIdRequest request)
	// {
	// InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
	// response.getResultsList().add(getSiteDAC().fetchSiteById(request));
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.qat.samples.sysmgmt.bac.ISiteBAC#fetchSitesByRequest(com.qat.samples.sysmgmt.model.request.
	// * SiteInquiryRequest)
	// */
	// @Override
	// public InternalResultsResponse<Site> fetchSitesByRequest(PagedInquiryRequest request)
	// {
	// return getSiteDAC().fetchSitesByRequest(request);
	// }

}
