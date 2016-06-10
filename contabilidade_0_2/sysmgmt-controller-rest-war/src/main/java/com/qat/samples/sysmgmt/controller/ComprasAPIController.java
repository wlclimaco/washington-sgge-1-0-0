/** create by system gera-java version 1.0.0 09/05/2016 16:51 : 47*/

package com.qat.samples.sysmgmt.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class ComprasAPIController.
 */
@Controller
@RequestMapping("/compras/api")
public class ComprasAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.comprascontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ComprasAPIController.class);

	/** The compras bac. */
	private IComprasBAC comprasBAC; // injected by @Resource

	/**
	 * Gets the compras bac.
	 *
	 * @return the compras bac
	 */
	public IComprasBAC getComprasBAC() {
		return comprasBAC;
	}

	/**
	 * Sets the compras bac.
	 *
	 * @param comprasBAC
	 *            the new compras bac
	 */
	@Resource
	public void setComprasBAC(IComprasBAC comprasBAC) {
		this.comprasBAC = comprasBAC;
	}

	// ===================================### NOTAFISCALENTRADA
	// ####======================================
	/**
	 * Refresh notafiscalentradas.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the notafiscalentrada response
	 */
	@RequestMapping(value = "/notaFiscal/refresh", method = RequestMethod.GET)
	@ResponseBody
	public NotaFiscalEntradaResponse refreshNotaFiscalEntradas(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		NotaFiscalEntradaResponse notafiscalentradaResponse = new NotaFiscalEntradaResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC()
					.refreshNotaFiscalEntradas(request);
			ResponseHandler.handleOperationStatusAndMessages(notafiscalentradaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, notafiscalentradaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return notafiscalentradaResponse;

	}

	/**
	 * Fetch notafiscalentrada paged.
	 *
	 * @param request
	 *            the request
	 * @return the notafiscalentrada response
	 */
	@RequestMapping(value = "/notaFiscal/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradaPaged(@RequestBody NotaFiscalInquiryRequest request) {
		NotaFiscalEntradaResponse notafiscalentradaResponse = new NotaFiscalEntradaResponse();
		try {
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC()
					.fetchNotaFiscalEntradasByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(notafiscalentradaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, notafiscalentradaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return notafiscalentradaResponse;
	}

	/**
	 * Insert notafiscalentrada.
	 *
	 * @param request
	 *            the request
	 * @return the notafiscalentrada response
	 */
	@RequestMapping(value = "/notaFiscal/insert", method = RequestMethod.POST)
	@ResponseBody
	public NotaFiscalEntradaResponse insertNotaFiscalEntrada(@RequestBody NotaFiscalEntradaMaintenanceRequest request) {
		NotaFiscalEntradaResponse notafiscalentradaResponse = new NotaFiscalEntradaResponse();
		try {
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC()
					.insertNotaFiscalEntrada(request);
			ResponseHandler.handleOperationStatusAndMessages(notafiscalentradaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, notafiscalentradaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return notafiscalentradaResponse;
	}

	/**
	 * Update notafiscalentrada.
	 *
	 * @param request
	 *            the request
	 * @return the notafiscalentrada response
	 */
	@RequestMapping(value = "/notaFiscal/update", method = RequestMethod.POST)
	@ResponseBody
	public NotaFiscalEntradaResponse updateNotaFiscalEntrada(@RequestBody NotaFiscalEntradaMaintenanceRequest request) {
		NotaFiscalEntradaResponse notafiscalentradaResponse = new NotaFiscalEntradaResponse();
		try {
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC()
					.updateNotaFiscalEntrada(request);
			ResponseHandler.handleOperationStatusAndMessages(notafiscalentradaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, notafiscalentradaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return notafiscalentradaResponse;
	}

	/**
	 * Delete notafiscalentrada.
	 *
	 * @param request
	 *            the request
	 * @return the notafiscalentrada response
	 */
	@RequestMapping(value = "/notaFiscal/delete", method = RequestMethod.POST)
	@ResponseBody
	public NotaFiscalEntradaResponse deleteNotaFiscalEntrada(@RequestBody NotaFiscalEntradaMaintenanceRequest request) {
		NotaFiscalEntradaResponse notafiscalentradaResponse = new NotaFiscalEntradaResponse();

		try {
			InternalResultsResponse<NotaFiscalEntrada> internalResponse = getComprasBAC()
					.deleteNotaFiscalEntrada(request);
			ResponseHandler.handleOperationStatusAndMessages(notafiscalentradaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, notafiscalentradaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return notafiscalentradaResponse;

	}

	// ===================================### PEDIDOCOMPRAS
	// ####======================================
	/**
	 * Refresh pedidocomprass.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the pedidocompras response
	 */
	@RequestMapping(value = "/pedidoCompras/refresh", method = RequestMethod.GET)
	@ResponseBody
	public PedidoComprasResponse refreshPedidoComprass(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		PedidoComprasResponse pedidocomprasResponse = new PedidoComprasResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().refreshPedidoComprass(request);
			ResponseHandler.handleOperationStatusAndMessages(pedidocomprasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, pedidocomprasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return pedidocomprasResponse;

	}

	/**
	 * Fetch pedidocompras paged.
	 *
	 * @param request
	 *            the request
	 * @return the pedidocompras response
	 */
	@RequestMapping(value = "/pedidoCompras/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public PedidoComprasResponse fetchPedidoComprasPaged(@RequestBody PedidoComprasInquiryRequest request) {
		PedidoComprasResponse pedidocomprasResponse = new PedidoComprasResponse();
		try {
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC()
					.fetchPedidoComprassByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(pedidocomprasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, pedidocomprasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return pedidocomprasResponse;
	}

	/**
	 * Insert pedidocompras.
	 *
	 * @param request
	 *            the request
	 * @return the pedidocompras response
	 */
	@RequestMapping(value = "/pedidoCompras/insert", method = RequestMethod.POST)
	@ResponseBody
	public PedidoComprasResponse insertPedidoCompras(@RequestBody PedidoComprasMaintenanceRequest request) {
		PedidoComprasResponse pedidocomprasResponse = new PedidoComprasResponse();
		try {
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().insertPedidoCompras(request);
			ResponseHandler.handleOperationStatusAndMessages(pedidocomprasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, pedidocomprasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return pedidocomprasResponse;
	}

	/**
	 * Update pedidocompras.
	 *
	 * @param request
	 *            the request
	 * @return the pedidocompras response
	 */
	@RequestMapping(value = "/pedidoCompras/update", method = RequestMethod.POST)
	@ResponseBody
	public PedidoComprasResponse updatePedidoCompras(@RequestBody PedidoComprasMaintenanceRequest request) {
		PedidoComprasResponse pedidocomprasResponse = new PedidoComprasResponse();
		try {
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().updatePedidoCompras(request);
			ResponseHandler.handleOperationStatusAndMessages(pedidocomprasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, pedidocomprasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return pedidocomprasResponse;
	}

	/**
	 * Delete pedidocompras.
	 *
	 * @param request
	 *            the request
	 * @return the pedidocompras response
	 */
	@RequestMapping(value = "/pedidoCompras/delete", method = RequestMethod.POST)
	@ResponseBody
	public PedidoComprasResponse deletePedidoCompras(@RequestBody PedidoComprasMaintenanceRequest request) {
		PedidoComprasResponse pedidocomprasResponse = new PedidoComprasResponse();

		try {
			InternalResultsResponse<PedidoCompras> internalResponse = getComprasBAC().deletePedidoCompras(request);
			ResponseHandler.handleOperationStatusAndMessages(pedidocomprasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, pedidocomprasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return pedidocomprasResponse;

	}

	// ===================================### COTACAO
	// ####======================================
	/**
	 * Refresh cotacaos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the cotacao response
	 */
	@RequestMapping(value = "/cotacao/refresh", method = RequestMethod.GET)
	@ResponseBody
	public CotacaoResponse refreshCotacaos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		CotacaoResponse cotacaoResponse = new CotacaoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().refreshCotacaos(request);
			ResponseHandler.handleOperationStatusAndMessages(cotacaoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cotacaoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cotacaoResponse;

	}

	/**
	 * Fetch cotacao paged.
	 *
	 * @param request
	 *            the request
	 * @return the cotacao response
	 */
	@RequestMapping(value = "/cotacao/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public CotacaoResponse fetchCotacaoPaged(@RequestBody CotacaoInquiryRequest request) {
		CotacaoResponse cotacaoResponse = new CotacaoResponse();
		try {
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().fetchCotacaosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(cotacaoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cotacaoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cotacaoResponse;
	}

	/**
	 * Insert cotacao.
	 *
	 * @param request
	 *            the request
	 * @return the cotacao response
	 */
	@RequestMapping(value = "/cotacao/insert", method = RequestMethod.POST)
	@ResponseBody
	public CotacaoResponse insertCotacao(@RequestBody CotacaoMaintenanceRequest request) {
		CotacaoResponse cotacaoResponse = new CotacaoResponse();
		try {
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().insertCotacao(request);
			ResponseHandler.handleOperationStatusAndMessages(cotacaoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cotacaoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cotacaoResponse;
	}

	/**
	 * Update cotacao.
	 *
	 * @param request
	 *            the request
	 * @return the cotacao response
	 */
	@RequestMapping(value = "/cotacao/update", method = RequestMethod.POST)
	@ResponseBody
	public CotacaoResponse updateCotacao(@RequestBody CotacaoMaintenanceRequest request) {
		CotacaoResponse cotacaoResponse = new CotacaoResponse();
		try {
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().updateCotacao(request);
			ResponseHandler.handleOperationStatusAndMessages(cotacaoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cotacaoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cotacaoResponse;
	}

	/**
	 * Delete cotacao.
	 *
	 * @param request
	 *            the request
	 * @return the cotacao response
	 */
	@RequestMapping(value = "/cotacao/delete", method = RequestMethod.POST)
	@ResponseBody
	public CotacaoResponse deleteCotacao(@RequestBody CotacaoMaintenanceRequest request) {
		CotacaoResponse cotacaoResponse = new CotacaoResponse();

		try {
			InternalResultsResponse<Cotacao> internalResponse = getComprasBAC().deleteCotacao(request);
			ResponseHandler.handleOperationStatusAndMessages(cotacaoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cotacaoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cotacaoResponse;

	}

}
