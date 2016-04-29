/** create by system gera-java version 1.0.0 28/04/2016 20:5 : 32*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Vendas.IVendasBAC;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.response.NotaFiscalSaidaResponse;
import com.qat.samples.sysmgmt.nf.model.response.OrcamentoResponse;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.response.OrdemServicoResponse;
import com.qat.samples.sysmgmt.service.IVendasWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
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
	/**
	 * Spring injection uses this method to inject an implementation of {@link IVendasBAC}.
	 *
	 * @param vendasBAC the vendasBAC to set.
	 */
	public void setVendasBAC(IVendasBAC vendasBAC)
	{
		this.vendasBAC = vendasBAC;
	}


//===================================### NOTAFISCALSAIDA ####======================================

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
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getVendasBAC().insertNotaFiscalSaida(request);
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
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getVendasBAC().updateNotaFiscalSaida(request);
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
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getVendasBAC().deleteNotaFiscalSaida(request);
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
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getVendasBAC().fetchNotaFiscalSaidaById(request);
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
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidasByRequest(NotaFiscalInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getVendasBAC().fetchNotaFiscalSaidasByRequest(request);
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
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getVendasBAC().refreshNotaFiscalSaidas(request);
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
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getVendasBAC().fetchAllNotaFiscalSaidas(new NotaFiscalSaida());
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
			InternalResultsResponse<Orcamento> internalResponse = getVendasBAC().insertOrcamento(request);
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
			InternalResultsResponse<Orcamento> internalResponse = getVendasBAC().updateOrcamento(request);
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
			InternalResultsResponse<Orcamento> internalResponse = getVendasBAC().deleteOrcamento(request);
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
			InternalResultsResponse<Orcamento> internalResponse = getVendasBAC().fetchOrcamentoById(request);
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
			InternalResultsResponse<Orcamento> internalResponse = getVendasBAC().fetchOrcamentosByRequest(request);
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
			InternalResultsResponse<Orcamento> internalResponse = getVendasBAC().refreshOrcamentos(request);
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
			InternalResultsResponse<Orcamento> internalResponse = getVendasBAC().fetchAllOrcamentos(new Orcamento());
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().insertOrdemServico(request);
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().updateOrdemServico(request);
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().deleteOrdemServico(request);
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().fetchOrdemServicoById(request);
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().fetchOrdemServicosByRequest(request);
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().refreshOrdemServicos(request);
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().fetchAllOrdemServicos(new OrdemServico());
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
