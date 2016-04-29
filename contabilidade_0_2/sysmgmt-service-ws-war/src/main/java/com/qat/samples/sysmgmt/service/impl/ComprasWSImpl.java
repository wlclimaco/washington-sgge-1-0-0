/** create by system gera-java version 1.0.0 28/04/2016 14:29 : 20*/

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
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class ComprasWSImpl implements com.qat.samples.sysmgmt.service.IComprasWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.compraswsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.compraswsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ComprasWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ComprasWSImpl.class);

	/** The compras BAC. */
	private IComprasBAC comprasBAC; // injected by Spring through setter

	/**
	 * Spring Sets the compras BAC.
	 *
	 * @param comprasBAC the new compras BAC
	 */
	public void setComprasBAC(IComprasBAC comprasBAC)
	{
		this.comprasBAC = comprasBAC;
	}

	/**
	 * Gets the compras bac.
	 *
	 * @return the compras bac
	 */
	public IComprasBAC getComprasBAC()
	{
		return comprasBAC;
	}


//===================================### NOTAFISCALENTRADA ####======================================
	@Override
	public NotaFiscalEntradaResponse insertNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();

		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().insertNotaFiscalEntrada(request);
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
	public NotaFiscalEntradaResponse updateNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();

		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().updateNotaFiscalEntrada(request);
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
	public NotaFiscalEntradaResponse deleteNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();

		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().deleteNotaFiscalEntrada(request);
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
	public NotaFiscalEntradaResponse refreshNotaFiscalEntradas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();

		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().refreshNotaFiscalEntradas(request);
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
	public NotaFiscalEntradaResponse fetchAllNotaFiscalEntradas(FetchAllRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();

		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().fetchAllNotaFiscalEntradas(new NotaFiscalEntrada());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.INotaFiscalEntradaWS#fetchNotaFiscalEntradaById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradaById(FetchByIdRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();

		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = new InternalResultsResponse<NotaFiscalEntrada>();

			internalResponse = getComprasBAC().fetchNotaFiscalEntradaById(request);
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
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradasByRequest(NotaFiscalInquiryRequest request)
	{
		NotaFiscalEntradaResponse response = new NotaFiscalEntradaResponse();

		try
		{
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC().fetchNotaFiscalEntradasByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### PEDIDOCOMPRAS ####======================================
	@Override
	public PedidoComprasResponse insertPedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();

		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().insertPedidoCompras(request);
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
	public PedidoComprasResponse updatePedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();

		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().updatePedidoCompras(request);
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
	public PedidoComprasResponse deletePedidoCompras(PedidoComprasMaintenanceRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();

		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().deletePedidoCompras(request);
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
	public PedidoComprasResponse refreshPedidoComprass(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		PedidoComprasResponse response = new PedidoComprasResponse();

		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().refreshPedidoComprass(request);
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
	public PedidoComprasResponse fetchAllPedidoComprass(FetchAllRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();

		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().fetchAllPedidoComprass(new PedidoCompras());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IPedidoComprasWS#fetchPedidoComprasById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public PedidoComprasResponse fetchPedidoComprasById(FetchByIdRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();

		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = new InternalResultsResponse<PedidoCompras>();

			internalResponse = getComprasBAC().fetchPedidoComprasById(request);
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
	public PedidoComprasResponse fetchPedidoComprassByRequest(PedidoComprasInquiryRequest request)
	{
		PedidoComprasResponse response = new PedidoComprasResponse();

		try
		{
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().fetchPedidoComprassByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### COTACAO ####======================================
	@Override
	public CotacaoResponse insertCotacao(CotacaoMaintenanceRequest request)
	{
		CotacaoResponse response = new CotacaoResponse();

		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().insertCotacao(request);
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
	public CotacaoResponse updateCotacao(CotacaoMaintenanceRequest request)
	{
		CotacaoResponse response = new CotacaoResponse();

		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().updateCotacao(request);
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
	public CotacaoResponse deleteCotacao(CotacaoMaintenanceRequest request)
	{
		CotacaoResponse response = new CotacaoResponse();

		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().deleteCotacao(request);
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
	public CotacaoResponse refreshCotacaos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CotacaoResponse response = new CotacaoResponse();

		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().refreshCotacaos(request);
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
	public CotacaoResponse fetchAllCotacaos(FetchAllRequest request)
	{
		CotacaoResponse response = new CotacaoResponse();

		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().fetchAllCotacaos(new Cotacao());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.ICotacaoWS#fetchCotacaoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public CotacaoResponse fetchCotacaoById(FetchByIdRequest request)
	{
		CotacaoResponse response = new CotacaoResponse();

		try
		{
			InternalResultsResponse<Cotacao> internalResponse = new InternalResultsResponse<Cotacao>();

			internalResponse = getComprasBAC().fetchCotacaoById(request);
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
	public CotacaoResponse fetchCotacaosByRequest(CotacaoInquiryRequest request)
	{
		CotacaoResponse response = new CotacaoResponse();

		try
		{
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().fetchCotacaosByRequest(request);
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
