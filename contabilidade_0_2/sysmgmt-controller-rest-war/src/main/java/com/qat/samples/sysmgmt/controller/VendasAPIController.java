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
import com.qat.samples.sysmgmt.bac.Nfe.INFeBAC;
import com.qat.samples.sysmgmt.bac.Vendas.IVendasBAC;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.response.NotaFiscalSaidaResponse;
import com.qat.samples.sysmgmt.nf.model.response.OrcamentoResponse;
import com.qat.samples.sysmgmt.nfe.model.NFNota;
import com.qat.samples.sysmgmt.nfe.request.NFNotaMaintenanceRequest;
import com.qat.samples.sysmgmt.nfe.response.NFNotaResponse;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.response.OrdemServicoResponse;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class VendasAPIController.
 */
@Controller
@RequestMapping("/vendas/api")
public class VendasAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.vendascontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(VendasAPIController.class);

	/** The vendas bac. */
	private IVendasBAC vendasBAC; // injected by @Resource

	private INFeBAC nfeBAC;

	/**
	 * Gets the vendas bac.
	 *
	 * @return the vendas bac
	 */
	public IVendasBAC getVendasBAC() {
		return vendasBAC;
	}

	/**
	 * Sets the vendas bac.
	 *
	 * @param vendasBAC
	 *            the new vendas bac
	 */
	@Resource
	public void setVendasBAC(IVendasBAC vendasBAC) {
		this.vendasBAC = vendasBAC;
	}

	public INFeBAC getNfeBAC() {
		return nfeBAC;
	}

	@Resource
	public void setNfeBAC(INFeBAC nfeBAC) {
		this.nfeBAC = nfeBAC;
	}

	// ===================================### NOTAFISCALSAIDA
	// ####======================================
	/**
	 * Refresh notafiscalsaidas.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the notafiscalsaida response
	 */
	@RequestMapping(value = "/nfSaidas/refresh", method = RequestMethod.GET)
	@ResponseBody
	public NotaFiscalSaidaResponse refreshNotaFiscalSaidas(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		NotaFiscalSaidaResponse notafiscalsaidaResponse = new NotaFiscalSaidaResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getVendasBAC().refreshNotaFiscalSaidas(request);
			ResponseHandler.handleOperationStatusAndMessages(notafiscalsaidaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, notafiscalsaidaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return notafiscalsaidaResponse;

	}

	/**
	 * Fetch notafiscalsaida paged.
	 *
	 * @param request
	 *            the request
	 * @return the notafiscalsaida response
	 */
	@RequestMapping(value = "/nfSaidas/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidaPaged(@RequestBody NotaFiscalInquiryRequest request) {
		NotaFiscalSaidaResponse notafiscalsaidaResponse = new NotaFiscalSaidaResponse();
		try {
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getVendasBAC()
					.fetchNotaFiscalSaidasByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(notafiscalsaidaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, notafiscalsaidaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return notafiscalsaidaResponse;
	}

	/**
	 * Insert notafiscalsaida.
	 *
	 * @param request
	 *            the request
	 * @return the notafiscalsaida response
	 */
	@RequestMapping(value = "/nfSaidas/insert", method = RequestMethod.POST)
	@ResponseBody
	public NFNotaResponse insertNotaFiscalSaida(@RequestBody NFNotaMaintenanceRequest request) {
		NFNotaResponse notafiscalsaidaResponse = new NFNotaResponse();
		try {
			InternalResultsResponse<NFNota> internalResponse = getNfeBAC().insertNFNota(request);
			ResponseHandler.handleOperationStatusAndMessages(notafiscalsaidaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, notafiscalsaidaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return notafiscalsaidaResponse;
	}

	/**
	 * Update notafiscalsaida.
	 *
	 * @param request
	 *            the request
	 * @return the notafiscalsaida response
	 */
	@RequestMapping(value = "/nfSaidas/update", method = RequestMethod.POST)
	@ResponseBody
	public NotaFiscalSaidaResponse updateNotaFiscalSaida(@RequestBody NotaFiscalSaidaMaintenanceRequest request) {
		NotaFiscalSaidaResponse notafiscalsaidaResponse = new NotaFiscalSaidaResponse();
		try {
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getVendasBAC().updateNotaFiscalSaida(request);
			ResponseHandler.handleOperationStatusAndMessages(notafiscalsaidaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, notafiscalsaidaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return notafiscalsaidaResponse;
	}

	/**
	 * Delete notafiscalsaida.
	 *
	 * @param request
	 *            the request
	 * @return the notafiscalsaida response
	 */
	@RequestMapping(value = "/nfSaidas/delete", method = RequestMethod.POST)
	@ResponseBody
	public NotaFiscalSaidaResponse deleteNotaFiscalSaida(@RequestBody NotaFiscalSaidaMaintenanceRequest request) {
		NotaFiscalSaidaResponse notafiscalsaidaResponse = new NotaFiscalSaidaResponse();

		try {
			InternalResultsResponse<NotaFiscalSaida> internalResponse = getVendasBAC().deleteNotaFiscalSaida(request);
			ResponseHandler.handleOperationStatusAndMessages(notafiscalsaidaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, notafiscalsaidaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return notafiscalsaidaResponse;

	}

	// ===================================### ORCAMENTO
	// ####======================================
	/**
	 * Refresh orcamentos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the orcamento response
	 */
	@RequestMapping(value = "/orcamento/refresh", method = RequestMethod.GET)
	@ResponseBody
	public OrcamentoResponse refreshOrcamentos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		OrcamentoResponse orcamentoResponse = new OrcamentoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Orcamento> internalResponse = getVendasBAC().refreshOrcamentos(request);
			ResponseHandler.handleOperationStatusAndMessages(orcamentoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, orcamentoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return orcamentoResponse;

	}

	/**
	 * Fetch orcamento paged.
	 *
	 * @param request
	 *            the request
	 * @return the orcamento response
	 */
	@RequestMapping(value = "/orcamento/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public OrcamentoResponse fetchOrcamentoPaged(@RequestBody OrcamentoInquiryRequest request) {
		OrcamentoResponse orcamentoResponse = new OrcamentoResponse();
		try {
			InternalResultsResponse<Orcamento> internalResponse = getVendasBAC().fetchOrcamentosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(orcamentoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, orcamentoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return orcamentoResponse;
	}

	/**
	 * Insert orcamento.
	 *
	 * @param request
	 *            the request
	 * @return the orcamento response
	 */
	@RequestMapping(value = "/orcamento/insert", method = RequestMethod.POST)
	@ResponseBody
	public OrcamentoResponse insertOrcamento(@RequestBody OrcamentoMaintenanceRequest request) {
		OrcamentoResponse orcamentoResponse = new OrcamentoResponse();
		try {
			InternalResultsResponse<Orcamento> internalResponse = getVendasBAC().insertOrcamento(request);
			ResponseHandler.handleOperationStatusAndMessages(orcamentoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, orcamentoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return orcamentoResponse;
	}

	/**
	 * Update orcamento.
	 *
	 * @param request
	 *            the request
	 * @return the orcamento response
	 */
	@RequestMapping(value = "/orcamento/update", method = RequestMethod.POST)
	@ResponseBody
	public OrcamentoResponse updateOrcamento(@RequestBody OrcamentoMaintenanceRequest request) {
		OrcamentoResponse orcamentoResponse = new OrcamentoResponse();
		try {
			InternalResultsResponse<Orcamento> internalResponse = getVendasBAC().updateOrcamento(request);
			ResponseHandler.handleOperationStatusAndMessages(orcamentoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, orcamentoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return orcamentoResponse;
	}

	/**
	 * Delete orcamento.
	 *
	 * @param request
	 *            the request
	 * @return the orcamento response
	 */
	@RequestMapping(value = "/orcamento/delete", method = RequestMethod.POST)
	@ResponseBody
	public OrcamentoResponse deleteOrcamento(@RequestBody OrcamentoMaintenanceRequest request) {
		OrcamentoResponse orcamentoResponse = new OrcamentoResponse();

		try {
			InternalResultsResponse<Orcamento> internalResponse = getVendasBAC().deleteOrcamento(request);
			ResponseHandler.handleOperationStatusAndMessages(orcamentoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, orcamentoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return orcamentoResponse;

	}

	// ===================================### ORDEMSERVICO
	// ####======================================
	/**
	 * Refresh ordemservicos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the ordemservico response
	 */
	@RequestMapping(value = "/ordemServico/refresh", method = RequestMethod.GET)
	@ResponseBody
	public OrdemServicoResponse refreshOrdemServicos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().refreshOrdemServicos(request);
			ResponseHandler.handleOperationStatusAndMessages(ordemservicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, ordemservicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return ordemservicoResponse;

	}

	/**
	 * Fetch ordemservico paged.
	 *
	 * @param request
	 *            the request
	 * @return the ordemservico response
	 */
	@RequestMapping(value = "/ordemServico/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse fetchOrdemServicoPaged(@RequestBody OrdemServicoInquiryRequest request) {
		OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();
		try {
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC()
					.fetchOrdemServicosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(ordemservicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, ordemservicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return ordemservicoResponse;
	}

	/**
	 * Insert ordemservico.
	 *
	 * @param request
	 *            the request
	 * @return the ordemservico response
	 */
	@RequestMapping(value = "/ordemServico/insert", method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse insertOrdemServico(@RequestBody OrdemServicoMaintenanceRequest request) {
		OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();
		try {
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().insertOrdemServico(request);
			ResponseHandler.handleOperationStatusAndMessages(ordemservicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, ordemservicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return ordemservicoResponse;
	}

	/**
	 * Update ordemservico.
	 *
	 * @param request
	 *            the request
	 * @return the ordemservico response
	 */
	@RequestMapping(value = "/ordemServico/update", method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse updateOrdemServico(@RequestBody OrdemServicoMaintenanceRequest request) {
		OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();
		try {
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().updateOrdemServico(request);
			ResponseHandler.handleOperationStatusAndMessages(ordemservicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, ordemservicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return ordemservicoResponse;
	}

	/**
	 * Delete ordemservico.
	 *
	 * @param request
	 *            the request
	 * @return the ordemservico response
	 */
	@RequestMapping(value = "/ordemServico/delete", method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse deleteOrdemServico(@RequestBody OrdemServicoMaintenanceRequest request) {
		OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();

		try {
			InternalResultsResponse<OrdemServico> internalResponse = getVendasBAC().deleteOrdemServico(request);
			ResponseHandler.handleOperationStatusAndMessages(ordemservicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, ordemservicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return ordemservicoResponse;

	}

}
