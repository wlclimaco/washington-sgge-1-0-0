/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.ISiteBAC;
import com.qat.samples.sysmgmt.model.Site;
import com.qat.samples.sysmgmt.model.response.SiteResponse;
import com.qat.samples.sysmgmt.service.ISiteWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * SiteWS used to provide WS interface. Delegates all calls to the ISiteBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class SiteWSImpl implements ISiteWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.sitejmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.sitejmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = SiteWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SiteWSImpl.class);
	private ISiteBAC siteBAC;
	/**
	 * @return the siteBAC which is expected to provide the implementation.
	 */
	public ISiteBAC getSiteBAC()
	{	
		return siteBAC;
	}

//===================================### SITE ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link ISiteBAC}.
	 *
	 * @param siteBAC the siteBAC to set.
	 */
	public void setSiteBAC(ISiteBAC siteBAC)
	{
		this.siteBAC = siteBAC;
	}
	
	/**
	 * Delegates call to {@link ISiteBAC}
	 *
	 * @param request a SiteRequest
	 * @return SiteResponse
	 */
	@Override
	public SiteResponse insertSite(SiteMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SiteResponse response = new SiteResponse();
		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().insertSite(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link ISiteBAC}
	 *
	 * @param request a SiteRequest
	 * @return SiteResponse
	 */
	@Override
	public SiteResponse updateSite(SiteMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SiteResponse response = new SiteResponse();
		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().updateSite(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link ISiteBAC}
	 *
	 * @param request a SiteRequest
	 * @return SiteResponse
	 */
	@Override
	public SiteResponse deleteSite(SiteMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SiteResponse response = new SiteResponse();
		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().deleteSite(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link ISiteBAC}
	 *
	 * @param request a SiteRequest
	 * @return SiteResponse
	 */
	@Override
	public SiteResponse fetchSiteById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SiteResponse response = new SiteResponse();
		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().fetchSiteById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link ISiteBAC}
	 *
	 * @param request a SiteRequest
	 * @return SiteResponse
	 */
	@Override
	public SiteResponse fetchSitesByRequest(SiteInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SiteResponse response = new SiteResponse();
		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().fetchSitesByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link ISiteBAC}
	 *
	 * @param request a SiteRequest
	 * @return SiteResponse
	 */
	@Override
	public SiteResponse refreshSites(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SiteResponse response = new SiteResponse();
		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().refreshSites(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link ISiteBAC}
	 *
	 * @param request a SiteRequest
	 * @return SiteResponse
	 */
	@Override
	public SiteResponse fetchAllSites(FetchAllRequest request)
	{
		SiteResponse response = new SiteResponse();
		try
		{
			InternalResultsResponse<Site> internalResponse = getSiteBAC().fetchAllSites();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### CONTATO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IContatoBAC}.
	 *
	 * @param contatoBAC the contatoBAC to set.
	 */
	public void setContatoBAC(IContatoBAC contatoBAC)
	{
		this.contatoBAC = contatoBAC;
	}
	
	/**
	 * Delegates call to {@link IContatoBAC}
	 *
	 * @param request a ContatoRequest
	 * @return ContatoResponse
	 */
	@Override
	public ContatoResponse insertContato(ContatoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContatoResponse response = new ContatoResponse();
		try
		{
			InternalResultsResponse<Contato> internalResponse = getContatoBAC().insertContato(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IContatoBAC}
	 *
	 * @param request a ContatoRequest
	 * @return ContatoResponse
	 */
	@Override
	public ContatoResponse updateContato(ContatoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContatoResponse response = new ContatoResponse();
		try
		{
			InternalResultsResponse<Contato> internalResponse = getContatoBAC().updateContato(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IContatoBAC}
	 *
	 * @param request a ContatoRequest
	 * @return ContatoResponse
	 */
	@Override
	public ContatoResponse deleteContato(ContatoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContatoResponse response = new ContatoResponse();
		try
		{
			InternalResultsResponse<Contato> internalResponse = getContatoBAC().deleteContato(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IContatoBAC}
	 *
	 * @param request a ContatoRequest
	 * @return ContatoResponse
	 */
	@Override
	public ContatoResponse fetchContatoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContatoResponse response = new ContatoResponse();
		try
		{
			InternalResultsResponse<Contato> internalResponse = getContatoBAC().fetchContatoById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IContatoBAC}
	 *
	 * @param request a ContatoRequest
	 * @return ContatoResponse
	 */
	@Override
	public ContatoResponse fetchContatosByRequest(ContatoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContatoResponse response = new ContatoResponse();
		try
		{
			InternalResultsResponse<Contato> internalResponse = getContatoBAC().fetchContatosByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IContatoBAC}
	 *
	 * @param request a ContatoRequest
	 * @return ContatoResponse
	 */
	@Override
	public ContatoResponse refreshContatos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContatoResponse response = new ContatoResponse();
		try
		{
			InternalResultsResponse<Contato> internalResponse = getContatoBAC().refreshContatos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IContatoBAC}
	 *
	 * @param request a ContatoRequest
	 * @return ContatoResponse
	 */
	@Override
	public ContatoResponse fetchAllContatos(FetchAllRequest request)
	{
		ContatoResponse response = new ContatoResponse();
		try
		{
			InternalResultsResponse<Contato> internalResponse = getContatoBAC().fetchAllContatos();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### ORDEMSERVICO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IOrdemServicoBAC}.
	 *
	 * @param ordemservicoBAC the ordemservicoBAC to set.
	 */
	public void setOrdemServicoBAC(IOrdemServicoBAC ordemservicoBAC)
	{
		this.ordemservicoBAC = ordemservicoBAC;
	}
	
	/**
	 * Delegates call to {@link IOrdemServicoBAC}
	 *
	 * @param request a OrdemServicoRequest
	 * @return OrdemServicoResponse
	 */
	@Override
	public OrdemServicoResponse insertOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrdemServicoResponse response = new OrdemServicoResponse();
		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getOrdemServicoBAC().insertOrdemServico(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IOrdemServicoBAC}
	 *
	 * @param request a OrdemServicoRequest
	 * @return OrdemServicoResponse
	 */
	@Override
	public OrdemServicoResponse updateOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrdemServicoResponse response = new OrdemServicoResponse();
		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getOrdemServicoBAC().updateOrdemServico(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IOrdemServicoBAC}
	 *
	 * @param request a OrdemServicoRequest
	 * @return OrdemServicoResponse
	 */
	@Override
	public OrdemServicoResponse deleteOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrdemServicoResponse response = new OrdemServicoResponse();
		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getOrdemServicoBAC().deleteOrdemServico(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IOrdemServicoBAC}
	 *
	 * @param request a OrdemServicoRequest
	 * @return OrdemServicoResponse
	 */
	@Override
	public OrdemServicoResponse fetchOrdemServicoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrdemServicoResponse response = new OrdemServicoResponse();
		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getOrdemServicoBAC().fetchOrdemServicoById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IOrdemServicoBAC}
	 *
	 * @param request a OrdemServicoRequest
	 * @return OrdemServicoResponse
	 */
	@Override
	public OrdemServicoResponse fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrdemServicoResponse response = new OrdemServicoResponse();
		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getOrdemServicoBAC().fetchOrdemServicosByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IOrdemServicoBAC}
	 *
	 * @param request a OrdemServicoRequest
	 * @return OrdemServicoResponse
	 */
	@Override
	public OrdemServicoResponse refreshOrdemServicos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrdemServicoResponse response = new OrdemServicoResponse();
		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getOrdemServicoBAC().refreshOrdemServicos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IOrdemServicoBAC}
	 *
	 * @param request a OrdemServicoRequest
	 * @return OrdemServicoResponse
	 */
	@Override
	public OrdemServicoResponse fetchAllOrdemServicos(FetchAllRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();
		try
		{
			InternalResultsResponse<OrdemServico> internalResponse = getOrdemServicoBAC().fetchAllOrdemServicos();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### PLANO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IPlanoBAC}.
	 *
	 * @param planoBAC the planoBAC to set.
	 */
	public void setPlanoBAC(IPlanoBAC planoBAC)
	{
		this.planoBAC = planoBAC;
	}
	
	/**
	 * Delegates call to {@link IPlanoBAC}
	 *
	 * @param request a PlanoRequest
	 * @return PlanoResponse
	 */
	@Override
	public PlanoResponse insertPlano(PlanoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PlanoResponse response = new PlanoResponse();
		try
		{
			InternalResultsResponse<Plano> internalResponse = getPlanoBAC().insertPlano(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IPlanoBAC}
	 *
	 * @param request a PlanoRequest
	 * @return PlanoResponse
	 */
	@Override
	public PlanoResponse updatePlano(PlanoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PlanoResponse response = new PlanoResponse();
		try
		{
			InternalResultsResponse<Plano> internalResponse = getPlanoBAC().updatePlano(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IPlanoBAC}
	 *
	 * @param request a PlanoRequest
	 * @return PlanoResponse
	 */
	@Override
	public PlanoResponse deletePlano(PlanoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PlanoResponse response = new PlanoResponse();
		try
		{
			InternalResultsResponse<Plano> internalResponse = getPlanoBAC().deletePlano(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IPlanoBAC}
	 *
	 * @param request a PlanoRequest
	 * @return PlanoResponse
	 */
	@Override
	public PlanoResponse fetchPlanoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PlanoResponse response = new PlanoResponse();
		try
		{
			InternalResultsResponse<Plano> internalResponse = getPlanoBAC().fetchPlanoById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IPlanoBAC}
	 *
	 * @param request a PlanoRequest
	 * @return PlanoResponse
	 */
	@Override
	public PlanoResponse fetchPlanosByRequest(PlanoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PlanoResponse response = new PlanoResponse();
		try
		{
			InternalResultsResponse<Plano> internalResponse = getPlanoBAC().fetchPlanosByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IPlanoBAC}
	 *
	 * @param request a PlanoRequest
	 * @return PlanoResponse
	 */
	@Override
	public PlanoResponse refreshPlanos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PlanoResponse response = new PlanoResponse();
		try
		{
			InternalResultsResponse<Plano> internalResponse = getPlanoBAC().refreshPlanos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IPlanoBAC}
	 *
	 * @param request a PlanoRequest
	 * @return PlanoResponse
	 */
	@Override
	public PlanoResponse fetchAllPlanos(FetchAllRequest request)
	{
		PlanoResponse response = new PlanoResponse();
		try
		{
			InternalResultsResponse<Plano> internalResponse = getPlanoBAC().fetchAllPlanos();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
}
