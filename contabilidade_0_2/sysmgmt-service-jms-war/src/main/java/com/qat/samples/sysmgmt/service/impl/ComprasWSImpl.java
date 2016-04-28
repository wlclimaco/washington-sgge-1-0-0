/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IComprasBAC;
import com.qat.samples.sysmgmt.model.Compras;
import com.qat.samples.sysmgmt.model.response.ComprasResponse;
import com.qat.samples.sysmgmt.service.IComprasWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
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

//===================================### NOTAFISCALENTRADA ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link INotaFiscalEntradaBAC}.
	 *
	 * @param notafiscalentradaBAC the notafiscalentradaBAC to set.
	 */
	public void setNotaFiscalEntradaBAC(INotaFiscalEntradaBAC notafiscalentradaBAC)
	{
		this.notafiscalentradaBAC = notafiscalentradaBAC;
	}
	
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
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getNotaFiscalEntradaBAC().insertNotaFiscalEntrada(request);
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
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getNotaFiscalEntradaBAC().updateNotaFiscalEntrada(request);
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
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getNotaFiscalEntradaBAC().deleteNotaFiscalEntrada(request);
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
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getNotaFiscalEntradaBAC().fetchNotaFiscalEntradaById(request);
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
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradasByRequest(NotaFiscalEntradaInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();
		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getNotaFiscalEntradaBAC().fetchNotaFiscalEntradasByRequest(request);
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
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getNotaFiscalEntradaBAC().refreshNotaFiscalEntradas(request);
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
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getNotaFiscalEntradaBAC().fetchAllNotaFiscalEntradas();
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
	 * Spring injection uses this method to inject an implementation of {@link IPedidoComprasBAC}.
	 *
	 * @param pedidocomprasBAC the pedidocomprasBAC to set.
	 */
	public void setPedidoComprasBAC(IPedidoComprasBAC pedidocomprasBAC)
	{
		this.pedidocomprasBAC = pedidocomprasBAC;
	}
	
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
			InternalResultsResponse<PedidoCompras> internalResponse = getPedidoComprasBAC().insertPedidoCompras(request);
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
			InternalResultsResponse<PedidoCompras> internalResponse = getPedidoComprasBAC().updatePedidoCompras(request);
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
			InternalResultsResponse<PedidoCompras> internalResponse = getPedidoComprasBAC().deletePedidoCompras(request);
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
			InternalResultsResponse<PedidoCompras> internalResponse = getPedidoComprasBAC().fetchPedidoComprasById(request);
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
			InternalResultsResponse<PedidoCompras> internalResponse = getPedidoComprasBAC().fetchPedidoComprassByRequest(request);
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
			InternalResultsResponse<PedidoCompras> internalResponse = getPedidoComprasBAC().refreshPedidoComprass(request);
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
			InternalResultsResponse<PedidoCompras> internalResponse = getPedidoComprasBAC().fetchAllPedidoComprass();
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
	 * Spring injection uses this method to inject an implementation of {@link ICotacaoBAC}.
	 *
	 * @param cotacaoBAC the cotacaoBAC to set.
	 */
	public void setCotacaoBAC(ICotacaoBAC cotacaoBAC)
	{
		this.cotacaoBAC = cotacaoBAC;
	}
	
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
			InternalResultsResponse<Cotacao> internalResponse = getCotacaoBAC().insertCotacao(request);
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
			InternalResultsResponse<Cotacao> internalResponse = getCotacaoBAC().updateCotacao(request);
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
			InternalResultsResponse<Cotacao> internalResponse = getCotacaoBAC().deleteCotacao(request);
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
			InternalResultsResponse<Cotacao> internalResponse = getCotacaoBAC().fetchCotacaoById(request);
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
			InternalResultsResponse<Cotacao> internalResponse = getCotacaoBAC().fetchCotacaosByRequest(request);
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
			InternalResultsResponse<Cotacao> internalResponse = getCotacaoBAC().refreshCotacaos(request);
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
			InternalResultsResponse<Cotacao> internalResponse = getCotacaoBAC().fetchAllCotacaos();
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
