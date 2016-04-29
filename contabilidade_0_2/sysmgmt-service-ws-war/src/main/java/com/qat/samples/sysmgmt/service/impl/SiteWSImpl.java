/** create by system gera-java version 1.0.0 28/04/2016 14:31 : 5*/

package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Site.ISiteBAC;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoMaintenanceRequest;
import com.qat.samples.sysmgmt.contato.model.response.ContatoResponse;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.response.OrdemServicoResponse;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.PlanoResponse;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.site.model.request.SiteInquiryRequest;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.response.SiteResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class SiteWSImpl implements com.qat.samples.sysmgmt.service.ISiteWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.sitewsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.sitewsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = SiteWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SiteWSImpl.class);

	/** The site BAC. */
	private ISiteBAC siteBAC; // injected by Spring through setter

	/**
	 * Spring Sets the site BAC.
	 *
	 * @param siteBAC the new site BAC
	 */
	public void setSiteBAC(ISiteBAC siteBAC)
	{
		this.siteBAC = siteBAC;
	}

	/**
	 * Gets the site bac.
	 *
	 * @return the site bac
	 */
	public ISiteBAC getSiteBAC()
	{
		return siteBAC;
	}


//===================================### SITE ####======================================
	@Override
	public SiteResponse insertSite(SiteMaintenanceRequest request)
	{
		SiteResponse response = new SiteResponse();

		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().insertSite(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public SiteResponse updateSite(SiteMaintenanceRequest request)
	{
		SiteResponse response = new SiteResponse();

		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().updateSite(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public SiteResponse deleteSite(SiteMaintenanceRequest request)
	{
		SiteResponse response = new SiteResponse();

		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().deleteSite(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public SiteResponse refreshSites(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SiteResponse response = new SiteResponse();

		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().refreshSites(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public SiteResponse fetchAllSites(FetchAllRequest request)
	{
		SiteResponse response = new SiteResponse();

		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().fetchAllSites(new Site());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.ISiteWS#fetchSiteById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public SiteResponse fetchSiteById(FetchByIdRequest request)
	{
		SiteResponse response = new SiteResponse();

		try
		{
			InternalResultsResponse<Site> internalResponse = new InternalResultsResponse<Site>();

			internalResponse = getSiteBAC().fetchSiteById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public SiteResponse fetchSitesByRequest(SiteInquiryRequest request)
	{
		SiteResponse response = new SiteResponse();

		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().fetchSitesByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### CONTATO ####======================================
	@Override
	public ContatoResponse insertContato(ContatoMaintenanceRequest request)
	{
		ContatoResponse response = new ContatoResponse();

		try
		{
			InternalResultsResponse<Contato> internalResponse = getSiteBAC().insertContato(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ContatoResponse updateContato(ContatoMaintenanceRequest request)
	{
		ContatoResponse response = new ContatoResponse();

		try
		{
			InternalResultsResponse<Contato> internalResponse = getSiteBAC().updateContato(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ContatoResponse deleteContato(ContatoMaintenanceRequest request)
	{
		ContatoResponse response = new ContatoResponse();

		try
		{
			InternalResultsResponse<Contato> internalResponse = getSiteBAC().deleteContato(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ContatoResponse refreshContatos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContatoResponse response = new ContatoResponse();

		try
		{
			InternalResultsResponse<Contato> internalResponse = getSiteBAC().refreshContatos(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ContatoResponse fetchAllContatos(FetchAllRequest request)
	{
		ContatoResponse response = new ContatoResponse();

		try
		{
			InternalResultsResponse<Contato> internalResponse = getSiteBAC().fetchAllContatos(new Contato());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IContatoWS#fetchContatoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ContatoResponse fetchContatoById(FetchByIdRequest request)
	{
		ContatoResponse response = new ContatoResponse();

		try
		{
			InternalResultsResponse<Contato> internalResponse = new InternalResultsResponse<Contato>();

			internalResponse = getSiteBAC().fetchContatoById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ContatoResponse fetchContatosByRequest(ContatoInquiryRequest request)
	{
		ContatoResponse response = new ContatoResponse();

		try
		{
			InternalResultsResponse<Contato> internalResponse = getSiteBAC().fetchContatosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### ORDEMSERVICO ####======================================
	@Override
	public OrdemServicoResponse insertOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();

		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getSiteBAC().insertOrdemServico(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public OrdemServicoResponse updateOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();

		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getSiteBAC().updateOrdemServico(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public OrdemServicoResponse deleteOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();

		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getSiteBAC().deleteOrdemServico(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public OrdemServicoResponse refreshOrdemServicos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrdemServicoResponse response = new OrdemServicoResponse();

		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getSiteBAC().refreshOrdemServicos(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public OrdemServicoResponse fetchAllOrdemServicos(FetchAllRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();

		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getSiteBAC().fetchAllOrdemServicos(new OrdemServico());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IOrdemServicoWS#fetchOrdemServicoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public OrdemServicoResponse fetchOrdemServicoById(FetchByIdRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();

		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = new InternalResultsResponse<OrdemServico>();

			internalResponse = getSiteBAC().fetchOrdemServicoById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public OrdemServicoResponse fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();

		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getSiteBAC().fetchOrdemServicosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### PLANO ####======================================
	@Override
	public PlanoResponse insertPlano(PlanoMaintenanceRequest request)
	{
		PlanoResponse response = new PlanoResponse();

		try
		{
			InternalResultsResponse<Plano> internalResponse = getSiteBAC().insertPlano(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public PlanoResponse updatePlano(PlanoMaintenanceRequest request)
	{
		PlanoResponse response = new PlanoResponse();

		try
		{
			InternalResultsResponse<Plano> internalResponse = getSiteBAC().updatePlano(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public PlanoResponse deletePlano(PlanoMaintenanceRequest request)
	{
		PlanoResponse response = new PlanoResponse();

		try
		{
			InternalResultsResponse<Plano> internalResponse = getSiteBAC().deletePlano(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public PlanoResponse refreshPlanos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PlanoResponse response = new PlanoResponse();

		try
		{
			InternalResultsResponse<Plano> internalResponse = getSiteBAC().refreshPlanos(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public PlanoResponse fetchAllPlanos(FetchAllRequest request)
	{
		PlanoResponse response = new PlanoResponse();

		try
		{
			InternalResultsResponse<Plano> internalResponse = getSiteBAC().fetchAllPlanos(new Plano());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IPlanoWS#fetchPlanoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public PlanoResponse fetchPlanoById(FetchByIdRequest request)
	{
		PlanoResponse response = new PlanoResponse();

		try
		{
			InternalResultsResponse<Plano> internalResponse = new InternalResultsResponse<Plano>();

			internalResponse = getSiteBAC().fetchPlanoById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public PlanoResponse fetchPlanosByRequest(PlanoInquiryRequest request)
	{
		PlanoResponse response = new PlanoResponse();

		try
		{
			InternalResultsResponse<Plano> internalResponse = getSiteBAC().fetchPlanosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}
}
