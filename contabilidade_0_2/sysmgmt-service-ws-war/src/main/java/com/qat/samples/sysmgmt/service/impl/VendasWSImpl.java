/** create by system gera-java version 1.0.0 28/04/2016 14:31 : 5*/

package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Vendas.IVendasBAC;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.response.NotaFiscalSaidaResponse;
import com.qat.samples.sysmgmt.nf.model.response.OrcamentoResponse;
import com.qat.samples.sysmgmt.nfe.model.NFNota;
import com.qat.samples.sysmgmt.nfe.request.NFNotaInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.response.OrdemServicoResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class VendasWSImpl implements com.qat.samples.sysmgmt.service.IVendasWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.vendaswsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.vendaswsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = VendasWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(VendasWSImpl.class);

	/** The vendas BAC. */
	private IVendasBAC vendasBAC; // injected by Spring through setter

	/**
	 * Spring Sets the vendas BAC.
	 *
	 * @param vendasBAC the new vendas BAC
	 */
	public void setVendasBAC(IVendasBAC vendasBAC)
	{
		this.vendasBAC = vendasBAC;
	}

	/**
	 * Gets the vendas bac.
	 *
	 * @return the vendas bac
	 */
	public IVendasBAC getVendasBAC()
	{
		return vendasBAC;
	}


//===================================### NOTAFISCALSAIDA ####======================================
	@Override
	public NotaFiscalSaidaResponse insertNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();

		try
		{
			InternalResultsResponse<NFNota> internalResponse = getVendasBAC().insertNotaFiscalSaida(request);
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
	public NotaFiscalSaidaResponse updateNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();

		try
		{
			InternalResultsResponse<NFNota> internalResponse = getVendasBAC().updateNotaFiscalSaida(request);
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
	public NotaFiscalSaidaResponse deleteNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();

		try
		{
			InternalResultsResponse<NFNota> internalResponse = getVendasBAC().deleteNotaFiscalSaida(request);
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
	public NotaFiscalSaidaResponse refreshNotaFiscalSaidas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();

		try
		{
			InternalResultsResponse<NFNota> internalResponse = getVendasBAC().refreshNotaFiscalSaidas(request);
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
	public NotaFiscalSaidaResponse fetchAllNotaFiscalSaidas(FetchAllRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();

		try
		{
			InternalResultsResponse<NFNota> internalResponse = getVendasBAC().fetchAllNotaFiscalSaidas(new NFNota());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.INotaFiscalSaidaWS#fetchNotaFiscalSaidaById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidaById(FetchByIdRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();

		try
		{
			InternalResultsResponse<NFNota> internalResponse = new InternalResultsResponse<NFNota>();

			internalResponse = getVendasBAC().fetchNotaFiscalSaidaById(request);
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
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidasByRequest(NFNotaInquiryRequest request)
	{
		NotaFiscalSaidaResponse response = new NotaFiscalSaidaResponse();

		try
		{
			InternalResultsResponse<NFNota> internalResponse = getVendasBAC().fetchNotaFiscalSaidasByRequest(request);
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().insertOrdemServico(request);
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().updateOrdemServico(request);
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().deleteOrdemServico(request);
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().refreshOrdemServicos(request);
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().fetchAllOrdemServicos(new OrdemServico());
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

			internalResponse = getVendasBAC().fetchOrdemServicoById(request);
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
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().fetchOrdemServicosByRequest(request);
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
	public OrcamentoResponse insertOrcamento(OrcamentoMaintenanceRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrcamentoResponse updateOrcamento(OrcamentoMaintenanceRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrcamentoResponse deleteOrcamento(OrcamentoMaintenanceRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrcamentoResponse refreshOrcamentos(RefreshRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrcamentoResponse fetchAllOrcamentos(FetchAllRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrcamentoResponse fetchOrcamentoById(FetchByIdRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrcamentoResponse fetchOrcamentosByRequest(OrcamentoInquiryRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
