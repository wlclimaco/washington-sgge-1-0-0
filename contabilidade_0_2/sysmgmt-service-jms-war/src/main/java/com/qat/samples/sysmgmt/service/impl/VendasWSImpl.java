/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IVendasBAC;
import com.qat.samples.sysmgmt.model.Vendas;
import com.qat.samples.sysmgmt.model.response.VendasResponse;
import com.qat.samples.sysmgmt.service.IVendasWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * VendasWS used to provide WS interface. Delegates all calls to the IVendasBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class VendasWSImpl implements IVendasWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.vendasjmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.vendasjmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = VendasWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(VendasWSImpl.class);
	private IVendasBAC vendasBAC;
	/**
	 * @return the vendasBAC which is expected to provide the implementation.
	 */
	public IVendasBAC getVendasBAC()
	{	
		return vendasBAC;
	}

//===================================### NOTAFISCALSAIDA ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link INotaFiscalSaidaBAC}.
	 *
	 * @param notafiscalsaidaBAC the notafiscalsaidaBAC to set.
	 */
	public void setNotaFiscalSaidaBAC(INotaFiscalSaidaBAC notafiscalsaidaBAC)
	{
		this.notafiscalsaidaBAC = notafiscalsaidaBAC;
	}
	
	/**
	 * Delegates call to {@link INotaFiscalSaidaBAC}
	 *
	 * @param request a NotaFiscalSaidaRequest
	 * @return NotaFiscalSaidaResponse
	 */
	@Override
	public NotaFiscalSaidaResponse insertNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getNotaFiscalSaidaBAC().insertNotaFiscalSaida(request);
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
	 * Delegates call to {@link INotaFiscalSaidaBAC}
	 *
	 * @param request a NotaFiscalSaidaRequest
	 * @return NotaFiscalSaidaResponse
	 */
	@Override
	public NotaFiscalSaidaResponse updateNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getNotaFiscalSaidaBAC().updateNotaFiscalSaida(request);
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
	 * Delegates call to {@link INotaFiscalSaidaBAC}
	 *
	 * @param request a NotaFiscalSaidaRequest
	 * @return NotaFiscalSaidaResponse
	 */
	@Override
	public NotaFiscalSaidaResponse deleteNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getNotaFiscalSaidaBAC().deleteNotaFiscalSaida(request);
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
	 * Delegates call to {@link INotaFiscalSaidaBAC}
	 *
	 * @param request a NotaFiscalSaidaRequest
	 * @return NotaFiscalSaidaResponse
	 */
	@Override
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidaById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getNotaFiscalSaidaBAC().fetchNotaFiscalSaidaById(request);
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
	 * Delegates call to {@link INotaFiscalSaidaBAC}
	 *
	 * @param request a NotaFiscalSaidaRequest
	 * @return NotaFiscalSaidaResponse
	 */
	@Override
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidasByRequest(NotaFiscalSaidaInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getNotaFiscalSaidaBAC().fetchNotaFiscalSaidasByRequest(request);
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
	 * Delegates call to {@link INotaFiscalSaidaBAC}
	 *
	 * @param request a NotaFiscalSaidaRequest
	 * @return NotaFiscalSaidaResponse
	 */
	@Override
	public NotaFiscalSaidaResponse refreshNotaFiscalSaidas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getNotaFiscalSaidaBAC().refreshNotaFiscalSaidas(request);
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
	 * Delegates call to {@link INotaFiscalSaidaBAC}
	 *
	 * @param request a NotaFiscalSaidaRequest
	 * @return NotaFiscalSaidaResponse
	 */
	@Override
	public NotaFiscalSaidaResponse fetchAllNotaFiscalSaidas(FetchAllRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getNotaFiscalSaidaBAC().fetchAllNotaFiscalSaidas();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### ORCAMENTO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IOrcamentoBAC}.
	 *
	 * @param orcamentoBAC the orcamentoBAC to set.
	 */
	public void setOrcamentoBAC(IOrcamentoBAC orcamentoBAC)
	{
		this.orcamentoBAC = orcamentoBAC;
	}
	
	/**
	 * Delegates call to {@link IOrcamentoBAC}
	 *
	 * @param request a OrcamentoRequest
	 * @return OrcamentoResponse
	 */
	@Override
	public OrcamentoResponse insertOrcamento(OrcamentoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrcamentoResponse response = new OrcamentoResponse();
		try
		{
			InternalResultsResponse<Orcamento> internalResponse = getOrcamentoBAC().insertOrcamento(request);
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
	 * Delegates call to {@link IOrcamentoBAC}
	 *
	 * @param request a OrcamentoRequest
	 * @return OrcamentoResponse
	 */
	@Override
	public OrcamentoResponse updateOrcamento(OrcamentoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrcamentoResponse response = new OrcamentoResponse();
		try
		{
			InternalResultsResponse<Orcamento> internalResponse = getOrcamentoBAC().updateOrcamento(request);
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
	 * Delegates call to {@link IOrcamentoBAC}
	 *
	 * @param request a OrcamentoRequest
	 * @return OrcamentoResponse
	 */
	@Override
	public OrcamentoResponse deleteOrcamento(OrcamentoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrcamentoResponse response = new OrcamentoResponse();
		try
		{
			InternalResultsResponse<Orcamento> internalResponse = getOrcamentoBAC().deleteOrcamento(request);
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
	 * Delegates call to {@link IOrcamentoBAC}
	 *
	 * @param request a OrcamentoRequest
	 * @return OrcamentoResponse
	 */
	@Override
	public OrcamentoResponse fetchOrcamentoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrcamentoResponse response = new OrcamentoResponse();
		try
		{
			InternalResultsResponse<Orcamento> internalResponse = getOrcamentoBAC().fetchOrcamentoById(request);
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
	 * Delegates call to {@link IOrcamentoBAC}
	 *
	 * @param request a OrcamentoRequest
	 * @return OrcamentoResponse
	 */
	@Override
	public OrcamentoResponse fetchOrcamentosByRequest(OrcamentoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrcamentoResponse response = new OrcamentoResponse();
		try
		{
			InternalResultsResponse<Orcamento> internalResponse = getOrcamentoBAC().fetchOrcamentosByRequest(request);
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
	 * Delegates call to {@link IOrcamentoBAC}
	 *
	 * @param request a OrcamentoRequest
	 * @return OrcamentoResponse
	 */
	@Override
	public OrcamentoResponse refreshOrcamentos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		OrcamentoResponse response = new OrcamentoResponse();
		try
		{
			InternalResultsResponse<Orcamento> internalResponse = getOrcamentoBAC().refreshOrcamentos(request);
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
	 * Delegates call to {@link IOrcamentoBAC}
	 *
	 * @param request a OrcamentoRequest
	 * @return OrcamentoResponse
	 */
	@Override
	public OrcamentoResponse fetchAllOrcamentos(FetchAllRequest request)
	{
		OrcamentoResponse response = new OrcamentoResponse();
		try
		{
			InternalResultsResponse<Orcamento> internalResponse = getOrcamentoBAC().fetchAllOrcamentos();
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
}
