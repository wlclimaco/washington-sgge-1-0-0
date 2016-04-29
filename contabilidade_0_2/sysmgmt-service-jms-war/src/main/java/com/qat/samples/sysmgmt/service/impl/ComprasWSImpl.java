/** create by system gera-java version 1.0.0 28/04/2016 20:5 : 32*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Compras.IComprasBAC;
import com.qat.samples.sysmgmt.cotacao.model.Cotacao;
import com.qat.samples.sysmgmt.cotacao.request.CotacaoInquiryRequest;
import com.qat.samples.sysmgmt.cotacao.request.CotacaoMaintenanceRequest;
import com.qat.samples.sysmgmt.cotacao.response.CotacaoResponse;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.response.NotaFiscalEntradaResponse;
import com.qat.samples.sysmgmt.nf.model.response.PedidoComprasResponse;
import com.qat.samples.sysmgmt.service.IComprasWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * ComprasWS used to provide WS interface. Delegates all calls to the IComprasBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class ComprasWSImpl implements IComprasWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.comprasjmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.comprasjmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ComprasWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ComprasWSImpl.class);
	private IComprasBAC comprasBAC;
	/**
	 * @return the comprasBAC which is expected to provide the implementation.
	 */
	public IComprasBAC getComprasBAC()
	{
		return comprasBAC;
	}
	/**
	 * Spring injection uses this method to inject an implementation of {@link IComprasBAC}.
	 *
	 * @param comprasBAC the comprasBAC to set.
	 */
	public void setComprasBAC(IComprasBAC comprasBAC)
	{
		this.comprasBAC = comprasBAC;
	}


//===================================### NOTAFISCALENTRADA ####======================================

	/**
	 * Delegates call to {@link INotaFiscalEntradaBAC}
	 *
	 * @param request a NotaFiscalEntradaRequest
	 * @return NotaFiscalEntradaResponse
	 */
	@Override
	public NotaFiscalEntradaResponse insertNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().insertNotaFiscalEntrada(request);
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
	 * Delegates call to {@link INotaFiscalEntradaBAC}
	 *
	 * @param request a NotaFiscalEntradaRequest
	 * @return NotaFiscalEntradaResponse
	 */
	@Override
	public NotaFiscalEntradaResponse updateNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().updateNotaFiscalEntrada(request);
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
	 * Delegates call to {@link INotaFiscalEntradaBAC}
	 *
	 * @param request a NotaFiscalEntradaRequest
	 * @return NotaFiscalEntradaResponse
	 */
	@Override
	public NotaFiscalEntradaResponse deleteNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().deleteNotaFiscalEntrada(request);
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
	 * Delegates call to {@link INotaFiscalEntradaBAC}
	 *
	 * @param request a NotaFiscalEntradaRequest
	 * @return NotaFiscalEntradaResponse
	 */
	@Override
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradaById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().fetchNotaFiscalEntradaById(request);
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
	 * Delegates call to {@link INotaFiscalEntradaBAC}
	 *
	 * @param request a NotaFiscalEntradaRequest
	 * @return NotaFiscalEntradaResponse
	 */
	@Override
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradasByRequest(NotaFiscalInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().fetchNotaFiscalEntradasByRequest(request);
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
	 * Delegates call to {@link INotaFiscalEntradaBAC}
	 *
	 * @param request a NotaFiscalEntradaRequest
	 * @return NotaFiscalEntradaResponse
	 */
	@Override
	public NotaFiscalEntradaResponse refreshNotaFiscalEntradas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().refreshNotaFiscalEntradas(request);
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
	 * Delegates call to {@link INotaFiscalEntradaBAC}
	 *
	 * @param request a NotaFiscalEntradaRequest
	 * @return NotaFiscalEntradaResponse
	 */
	@Override
	public NotaFiscalEntradaResponse fetchAllNotaFiscalEntradas(FetchAllRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().fetchAllNotaFiscalEntradas(new NotaFiscalEntrada());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### PEDIDOCOMPRAS ####======================================

	/**
	 * Delegates call to {@link IPedidoComprasBAC}
	 *
	 * @param request a PedidoComprasRequest
	 * @return PedidoComprasResponse
	 */
	@Override
	public PedidoComprasResponse insertPedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PedidoComprasResponse response = new PedidoComprasResponse();
		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().insertPedidoCompras(request);
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
	 * Delegates call to {@link IPedidoComprasBAC}
	 *
	 * @param request a PedidoComprasRequest
	 * @return PedidoComprasResponse
	 */
	@Override
	public PedidoComprasResponse updatePedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PedidoComprasResponse response = new PedidoComprasResponse();
		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().updatePedidoCompras(request);
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
	 * Delegates call to {@link IPedidoComprasBAC}
	 *
	 * @param request a PedidoComprasRequest
	 * @return PedidoComprasResponse
	 */
	@Override
	public PedidoComprasResponse deletePedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PedidoComprasResponse response = new PedidoComprasResponse();
		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().deletePedidoCompras(request);
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
	 * Delegates call to {@link IPedidoComprasBAC}
	 *
	 * @param request a PedidoComprasRequest
	 * @return PedidoComprasResponse
	 */
	@Override
	public PedidoComprasResponse fetchPedidoComprasById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PedidoComprasResponse response = new PedidoComprasResponse();
		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().fetchPedidoComprasById(request);
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
	 * Delegates call to {@link IPedidoComprasBAC}
	 *
	 * @param request a PedidoComprasRequest
	 * @return PedidoComprasResponse
	 */
	@Override
	public PedidoComprasResponse fetchPedidoComprassByRequest(PedidoComprasInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PedidoComprasResponse response = new PedidoComprasResponse();
		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().fetchPedidoComprassByRequest(request);
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
	 * Delegates call to {@link IPedidoComprasBAC}
	 *
	 * @param request a PedidoComprasRequest
	 * @return PedidoComprasResponse
	 */
	@Override
	public PedidoComprasResponse refreshPedidoComprass(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PedidoComprasResponse response = new PedidoComprasResponse();
		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().refreshPedidoComprass(request);
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
	 * Delegates call to {@link IPedidoComprasBAC}
	 *
	 * @param request a PedidoComprasRequest
	 * @return PedidoComprasResponse
	 */
	@Override
	public PedidoComprasResponse fetchAllPedidoComprass(FetchAllRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();
		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().fetchAllPedidoComprass(new PedidoCompras());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### COTACAO ####======================================

	/**
	 * Delegates call to {@link ICotacaoBAC}
	 *
	 * @param request a CotacaoRequest
	 * @return CotacaoResponse
	 */
	@Override
	public CotacaoResponse insertCotacao(CotacaoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CotacaoResponse response = new CotacaoResponse();
		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().insertCotacao(request);
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
	 * Delegates call to {@link ICotacaoBAC}
	 *
	 * @param request a CotacaoRequest
	 * @return CotacaoResponse
	 */
	@Override
	public CotacaoResponse updateCotacao(CotacaoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CotacaoResponse response = new CotacaoResponse();
		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().updateCotacao(request);
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
	 * Delegates call to {@link ICotacaoBAC}
	 *
	 * @param request a CotacaoRequest
	 * @return CotacaoResponse
	 */
	@Override
	public CotacaoResponse deleteCotacao(CotacaoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CotacaoResponse response = new CotacaoResponse();
		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().deleteCotacao(request);
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
	 * Delegates call to {@link ICotacaoBAC}
	 *
	 * @param request a CotacaoRequest
	 * @return CotacaoResponse
	 */
	@Override
	public CotacaoResponse fetchCotacaoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CotacaoResponse response = new CotacaoResponse();
		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().fetchCotacaoById(request);
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
	 * Delegates call to {@link ICotacaoBAC}
	 *
	 * @param request a CotacaoRequest
	 * @return CotacaoResponse
	 */
	@Override
	public CotacaoResponse fetchCotacaosByRequest(CotacaoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CotacaoResponse response = new CotacaoResponse();
		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().fetchCotacaosByRequest(request);
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
	 * Delegates call to {@link ICotacaoBAC}
	 *
	 * @param request a CotacaoRequest
	 * @return CotacaoResponse
	 */
	@Override
	public CotacaoResponse refreshCotacaos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CotacaoResponse response = new CotacaoResponse();
		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().refreshCotacaos(request);
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
	 * Delegates call to {@link ICotacaoBAC}
	 *
	 * @param request a CotacaoRequest
	 * @return CotacaoResponse
	 */
	@Override
	public CotacaoResponse fetchAllCotacaos(FetchAllRequest request)
	{
		CotacaoResponse response = new CotacaoResponse();
		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().fetchAllCotacaos(new Cotacao());
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
