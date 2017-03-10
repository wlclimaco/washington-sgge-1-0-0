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
import com.qat.samples.sysmgmt.agencia.model.Agencia;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaMaintenanceRequest;
import com.qat.samples.sysmgmt.agencia.model.response.AgenciaResponse;
import com.qat.samples.sysmgmt.bac.Financeiro.IFinanceiroBAC;
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.banco.model.request.BancoMaintenanceRequest;
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.dp.model.response.BancoResponse;
import com.qat.samples.sysmgmt.dp.model.response.ContaResponse;
import com.qat.samples.sysmgmt.dp.model.response.FormaPgResponse;
import com.qat.samples.sysmgmt.financeiro.model.BaixaTitulo;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.financeiro.model.request.BaixaTituloInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.BaixaTituloMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.response.BaixaTituloResponse;
import com.qat.samples.sysmgmt.financeiro.model.response.CondPagResponse;
import com.qat.samples.sysmgmt.financeiro.model.response.ContaCorrenteResponse;
import com.qat.samples.sysmgmt.financeiro.model.response.ContasPagarResponse;
import com.qat.samples.sysmgmt.financeiro.model.response.ContasReceberResponse;
import com.qat.samples.sysmgmt.nf.model.response.CaixaResponse;
import com.qat.samples.sysmgmt.pessoa.model.request.ContaInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class FinanceiroAPIController.
 */
@Controller
@RequestMapping("/financeiro/api")
public class FinanceiroAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.financeirocontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FinanceiroAPIController.class);

	/** The financeiro bac. */
	private IFinanceiroBAC financeiroBAC; // injected by @Resource

	/**
	 * Gets the financeiro bac.
	 *
	 * @return the financeiro bac
	 */
	public IFinanceiroBAC getFinanceiroBAC() {
		return financeiroBAC;
	}

	/**
	 * Sets the financeiro bac.
	 *
	 * @param financeiroBAC
	 *            the new financeiro bac
	 */
	@Resource
	public void setFinanceiroBAC(IFinanceiroBAC financeiroBAC) {
		this.financeiroBAC = financeiroBAC;
	}

	// ===================================### CONTASPAGAR
	// ####======================================
	/**
	 * Refresh contaspagars.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the contaspagar response
	 */
	@RequestMapping(value = "/contasPagar/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ContasPagarResponse refreshContasPagars(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ContasPagarResponse contaspagarResponse = new ContasPagarResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().refreshContasPagars(request);
			ResponseHandler.handleOperationStatusAndMessages(contaspagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contaspagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contaspagarResponse;

	}

	/**
	 * Fetch contaspagar paged.
	 *
	 * @param request
	 *            the request
	 * @return the contaspagar response
	 */
	@RequestMapping(value = "/contasPagar/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ContasPagarResponse fetchContasPagarPaged(@RequestBody ContasPagarInquiryRequest request) {
		ContasPagarResponse contaspagarResponse = new ContasPagarResponse();
		try {
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC()
					.fetchContasPagarsByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(contaspagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contaspagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contaspagarResponse;
	}

	/**
	 * Insert contaspagar.
	 *
	 * @param request
	 *            the request
	 * @return the contaspagar response
	 */
	@RequestMapping(value = "/contasPagar/insert", method = RequestMethod.POST)
	@ResponseBody
	public ContasPagarResponse insertContasPagar(@RequestBody ContasPagarMaintenanceRequest request) {
		ContasPagarResponse contaspagarResponse = new ContasPagarResponse();
		try {
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().insertContasPagar(request);
			ResponseHandler.handleOperationStatusAndMessages(contaspagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contaspagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contaspagarResponse;
	}

	/**
	 * Update contaspagar.
	 *
	 * @param request
	 *            the request
	 * @return the contaspagar response
	 */
	@RequestMapping(value = "/contasPagar/update", method = RequestMethod.POST)
	@ResponseBody
	public ContasPagarResponse updateContasPagar(@RequestBody ContasPagarMaintenanceRequest request) {
		ContasPagarResponse contaspagarResponse = new ContasPagarResponse();
		try {
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().updateContasPagar(request);
			ResponseHandler.handleOperationStatusAndMessages(contaspagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contaspagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contaspagarResponse;
	}

	/**
	 * Delete contaspagar.
	 *
	 * @param request
	 *            the request
	 * @return the contaspagar response
	 */
	@RequestMapping(value = "/contasPagar/delete", method = RequestMethod.POST)
	@ResponseBody
	public ContasPagarResponse deleteContasPagar(@RequestBody ContasPagarMaintenanceRequest request) {
		ContasPagarResponse contaspagarResponse = new ContasPagarResponse();

		try {
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().deleteContasPagar(request);
			ResponseHandler.handleOperationStatusAndMessages(contaspagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contaspagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contaspagarResponse;

	}

	// ===================================### CONTASRECEBER
	// ####======================================
	/**
	 * Refresh contasrecebers.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the contasreceber response
	 */
	@RequestMapping(value = "/contasReceber/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ContasReceberResponse refreshContasRecebers(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ContasReceberResponse contasreceberResponse = new ContasReceberResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().refreshContasRecebers(request);
			ResponseHandler.handleOperationStatusAndMessages(contasreceberResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contasreceberResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contasreceberResponse;

	}

	/**
	 * Fetch contasreceber paged.
	 *
	 * @param request
	 *            the request
	 * @return the contasreceber response
	 */
	@RequestMapping(value = "/contasReceber/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ContasReceberResponse fetchContasReceberPaged(@RequestBody ContasReceberInquiryRequest request) {
		ContasReceberResponse contasreceberResponse = new ContasReceberResponse();
		try {
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC()
					.fetchContasRecebersByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(contasreceberResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contasreceberResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contasreceberResponse;
	}

	/**
	 * Insert contasreceber.
	 *
	 * @param request
	 *            the request
	 * @return the contasreceber response
	 */
	@RequestMapping(value = "/contasReceber/insert", method = RequestMethod.POST)
	@ResponseBody
	public ContasReceberResponse insertContasReceber(@RequestBody ContasReceberMaintenanceRequest request) {
		ContasReceberResponse contasreceberResponse = new ContasReceberResponse();
		try {
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().insertContasReceber(request);
			ResponseHandler.handleOperationStatusAndMessages(contasreceberResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contasreceberResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contasreceberResponse;
	}

	/**
	 * Update contasreceber.
	 *
	 * @param request
	 *            the request
	 * @return the contasreceber response
	 */
	@RequestMapping(value = "/contasReceber/update", method = RequestMethod.POST)
	@ResponseBody
	public ContasReceberResponse updateContasReceber(@RequestBody ContasReceberMaintenanceRequest request) {
		ContasReceberResponse contasreceberResponse = new ContasReceberResponse();
		try {
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().updateContasReceber(request);
			ResponseHandler.handleOperationStatusAndMessages(contasreceberResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contasreceberResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contasreceberResponse;
	}

	/**
	 * Delete contasreceber.
	 *
	 * @param request
	 *            the request
	 * @return the contasreceber response
	 */
	@RequestMapping(value = "/contasReceber/delete", method = RequestMethod.POST)
	@ResponseBody
	public ContasReceberResponse deleteContasReceber(@RequestBody ContasReceberMaintenanceRequest request) {
		ContasReceberResponse contasreceberResponse = new ContasReceberResponse();

		try {
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().deleteContasReceber(request);
			ResponseHandler.handleOperationStatusAndMessages(contasreceberResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contasreceberResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contasreceberResponse;

	}

	// ===================================### CONDPAG
	// ####======================================
	/**
	 * Refresh condpags.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the condpag response
	 */
	@RequestMapping(value = "/condPag/refresh", method = RequestMethod.GET)
	@ResponseBody
	public CondPagResponse refreshCondPags(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		CondPagResponse condpagResponse = new CondPagResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().refreshCondPags(request);
			ResponseHandler.handleOperationStatusAndMessages(condpagResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, condpagResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return condpagResponse;

	}

	/**
	 * Fetch condpag paged.
	 *
	 * @param request
	 *            the request
	 * @return the condpag response
	 */
	@RequestMapping(value = "/condPag/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public CondPagResponse fetchCondPagPaged(@RequestBody CondPagInquiryRequest request) {
		CondPagResponse condpagResponse = new CondPagResponse();
		try {
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().fetchCondPagsByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(condpagResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, condpagResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return condpagResponse;
	}

	/**
	 * Insert condpag.
	 *
	 * @param request
	 *            the request
	 * @return the condpag response
	 */
	@RequestMapping(value = "/condPag/insert", method = RequestMethod.POST)
	@ResponseBody
	public CondPagResponse insertCondPag(@RequestBody CondPagMaintenanceRequest request) {
		CondPagResponse condpagResponse = new CondPagResponse();
		try {
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().insertCondPag(request);
			ResponseHandler.handleOperationStatusAndMessages(condpagResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, condpagResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return condpagResponse;
	}

	/**
	 * Update condpag.
	 *
	 * @param request
	 *            the request
	 * @return the condpag response
	 */
	@RequestMapping(value = "/condPag/update", method = RequestMethod.POST)
	@ResponseBody
	public CondPagResponse updateCondPag(@RequestBody CondPagMaintenanceRequest request) {
		CondPagResponse condpagResponse = new CondPagResponse();
		try {
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().updateCondPag(request);
			ResponseHandler.handleOperationStatusAndMessages(condpagResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, condpagResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return condpagResponse;
	}

	/**
	 * Delete condpag.
	 *
	 * @param request
	 *            the request
	 * @return the condpag response
	 */
	@RequestMapping(value = "/condPag/delete", method = RequestMethod.POST)
	@ResponseBody
	public CondPagResponse deleteCondPag(@RequestBody CondPagMaintenanceRequest request) {
		CondPagResponse condpagResponse = new CondPagResponse();

		try {
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().deleteCondPag(request);
			ResponseHandler.handleOperationStatusAndMessages(condpagResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, condpagResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return condpagResponse;

	}

	// ===================================### FORMAPG
	// ####======================================
	/**
	 * Refresh formapgs.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the formapg response
	 */
	@RequestMapping(value = "/formaPg/refresh", method = RequestMethod.GET)
	@ResponseBody
	public FormaPgResponse refreshFormaPgs(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		FormaPgResponse formapgResponse = new FormaPgResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().refreshFormaPgs(request);
			ResponseHandler.handleOperationStatusAndMessages(formapgResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, formapgResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return formapgResponse;

	}

	/**
	 * Fetch formapg paged.
	 *
	 * @param request
	 *            the request
	 * @return the formapg response
	 */
	@RequestMapping(value = "/formaPg/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public FormaPgResponse fetchFormaPgPaged(@RequestBody FormaPgInquiryRequest request) {
		FormaPgResponse formapgResponse = new FormaPgResponse();
		try {
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().fetchFormaPgsByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(formapgResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, formapgResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return formapgResponse;
	}

	/**
	 * Insert formapg.
	 *
	 * @param request
	 *            the request
	 * @return the formapg response
	 */
	@RequestMapping(value = "/formaPg/insert", method = RequestMethod.POST)
	@ResponseBody
	public FormaPgResponse insertFormaPg(@RequestBody FormaPgMaintenanceRequest request) {
		FormaPgResponse formapgResponse = new FormaPgResponse();
		try {
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().insertFormaPg(request);
			ResponseHandler.handleOperationStatusAndMessages(formapgResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, formapgResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return formapgResponse;
	}

	/**
	 * Update formapg.
	 *
	 * @param request
	 *            the request
	 * @return the formapg response
	 */
	@RequestMapping(value = "/formaPg/update", method = RequestMethod.POST)
	@ResponseBody
	public FormaPgResponse updateFormaPg(@RequestBody FormaPgMaintenanceRequest request) {
		FormaPgResponse formapgResponse = new FormaPgResponse();
		try {
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().updateFormaPg(request);
			ResponseHandler.handleOperationStatusAndMessages(formapgResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, formapgResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return formapgResponse;
	}

	/**
	 * Delete formapg.
	 *
	 * @param request
	 *            the request
	 * @return the formapg response
	 */
	@RequestMapping(value = "/formaPg/delete", method = RequestMethod.POST)
	@ResponseBody
	public FormaPgResponse deleteFormaPg(@RequestBody FormaPgMaintenanceRequest request) {
		FormaPgResponse formapgResponse = new FormaPgResponse();

		try {
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().deleteFormaPg(request);
			ResponseHandler.handleOperationStatusAndMessages(formapgResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, formapgResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return formapgResponse;

	}

	// ===================================### BANCO
	// ####======================================
	/**
	 * Refresh bancos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the banco response
	 */
	@RequestMapping(value = "/banco/refresh", method = RequestMethod.GET)
	@ResponseBody
	public BancoResponse refreshBancos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		BancoResponse bancoResponse = new BancoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().refreshBancos(request);
			ResponseHandler.handleOperationStatusAndMessages(bancoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, bancoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return bancoResponse;

	}

	/**
	 * Fetch banco paged.
	 *
	 * @param request
	 *            the request
	 * @return the banco response
	 */
	@RequestMapping(value = "/banco/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public BancoResponse fetchBancoPaged(@RequestBody BancoInquiryRequest request) {
		BancoResponse bancoResponse = new BancoResponse();
		try {
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().fetchBancosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(bancoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, bancoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return bancoResponse;
	}

	/**
	 * Insert banco.
	 *
	 * @param request
	 *            the request
	 * @return the banco response
	 */
	@RequestMapping(value = "/banco/insert", method = RequestMethod.POST)
	@ResponseBody
	public BancoResponse insertBanco(@RequestBody BancoMaintenanceRequest request) {
		BancoResponse bancoResponse = new BancoResponse();
		try {
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().insertBanco(request);
			ResponseHandler.handleOperationStatusAndMessages(bancoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, bancoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return bancoResponse;
	}

	/**
	 * Update banco.
	 *
	 * @param request
	 *            the request
	 * @return the banco response
	 */
	@RequestMapping(value = "/banco/update", method = RequestMethod.POST)
	@ResponseBody
	public BancoResponse updateBanco(@RequestBody BancoMaintenanceRequest request) {
		BancoResponse bancoResponse = new BancoResponse();
		try {
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().updateBanco(request);
			ResponseHandler.handleOperationStatusAndMessages(bancoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, bancoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return bancoResponse;
	}

	/**
	 * Delete banco.
	 *
	 * @param request
	 *            the request
	 * @return the banco response
	 */
	@RequestMapping(value = "/banco/delete", method = RequestMethod.POST)
	@ResponseBody
	public BancoResponse deleteBanco(@RequestBody BancoMaintenanceRequest request) {
		BancoResponse bancoResponse = new BancoResponse();

		try {
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().deleteBanco(request);
			ResponseHandler.handleOperationStatusAndMessages(bancoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, bancoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return bancoResponse;

	}

	// ===================================### CONTACORRENTE
	// ####======================================
	/**
	 * Refresh contacorrentes.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the contacorrente response
	 */
	@RequestMapping(value = "/contaCorrente/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ContaCorrenteResponse refreshContaCorrentes(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ContaCorrenteResponse contacorrenteResponse = new ContaCorrenteResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().refreshContaCorrentes(request);
			ResponseHandler.handleOperationStatusAndMessages(contacorrenteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contacorrenteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contacorrenteResponse;

	}

	/**
	 * Fetch contacorrente paged.
	 *
	 * @param request
	 *            the request
	 * @return the contacorrente response
	 */
	@RequestMapping(value = "/contaCorrente/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ContaCorrenteResponse fetchContaCorrentePaged(@RequestBody ContaCorrenteInquiryRequest request) {
		ContaCorrenteResponse contacorrenteResponse = new ContaCorrenteResponse();
		try {
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC()
					.fetchContaCorrentesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(contacorrenteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contacorrenteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contacorrenteResponse;
	}

	/**
	 * Insert contacorrente.
	 *
	 * @param request
	 *            the request
	 * @return the contacorrente response
	 */
	@RequestMapping(value = "/contaCorrente/insert", method = RequestMethod.POST)
	@ResponseBody
	public ContaCorrenteResponse insertContaCorrente(@RequestBody ContaCorrenteMaintenanceRequest request) {
		ContaCorrenteResponse contacorrenteResponse = new ContaCorrenteResponse();
		try {
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().insertContaCorrente(request);
			ResponseHandler.handleOperationStatusAndMessages(contacorrenteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contacorrenteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contacorrenteResponse;
	}

	/**
	 * Update contacorrente.
	 *
	 * @param request
	 *            the request
	 * @return the contacorrente response
	 */
	@RequestMapping(value = "/contaCorrente/update", method = RequestMethod.POST)
	@ResponseBody
	public ContaCorrenteResponse updateContaCorrente(@RequestBody ContaCorrenteMaintenanceRequest request) {
		ContaCorrenteResponse contacorrenteResponse = new ContaCorrenteResponse();
		try {
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().updateContaCorrente(request);
			ResponseHandler.handleOperationStatusAndMessages(contacorrenteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contacorrenteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contacorrenteResponse;
	}

	/**
	 * Delete contacorrente.
	 *
	 * @param request
	 *            the request
	 * @return the contacorrente response
	 */
	@RequestMapping(value = "/contaCorrente/delete", method = RequestMethod.POST)
	@ResponseBody
	public ContaCorrenteResponse deleteContaCorrente(@RequestBody ContaCorrenteMaintenanceRequest request) {
		ContaCorrenteResponse contacorrenteResponse = new ContaCorrenteResponse();

		try {
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().deleteContaCorrente(request);
			ResponseHandler.handleOperationStatusAndMessages(contacorrenteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contacorrenteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contacorrenteResponse;

	}

	// ===================================### CAIXA
	// ####======================================
	/**
	 * Refresh caixas.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the caixa response
	 */
	@RequestMapping(value = "/caixa/refresh", method = RequestMethod.GET)
	@ResponseBody
	public CaixaResponse refreshCaixas(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		CaixaResponse caixaResponse = new CaixaResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().refreshCaixas(request);
			ResponseHandler.handleOperationStatusAndMessages(caixaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, caixaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return caixaResponse;

	}

	/**
	 * Fetch caixa paged.
	 *
	 * @param request
	 *            the request
	 * @return the caixa response
	 */
	@RequestMapping(value = "/caixa/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public CaixaResponse fetchCaixaPaged(@RequestBody CaixaInquiryRequest request) {
		CaixaResponse caixaResponse = new CaixaResponse();
		try {
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().fetchCaixasByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(caixaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, caixaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return caixaResponse;
	}

	/**
	 * Insert caixa.
	 *
	 * @param request
	 *            the request
	 * @return the caixa response
	 */
	@RequestMapping(value = "/caixa/insert", method = RequestMethod.POST)
	@ResponseBody
	public CaixaResponse insertCaixa(@RequestBody CaixaMaintenanceRequest request) {
		CaixaResponse caixaResponse = new CaixaResponse();
		try {
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().insertCaixa(request);
			ResponseHandler.handleOperationStatusAndMessages(caixaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, caixaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return caixaResponse;
	}

	/**
	 * Update caixa.
	 *
	 * @param request
	 *            the request
	 * @return the caixa response
	 */
	@RequestMapping(value = "/caixa/update", method = RequestMethod.POST)
	@ResponseBody
	public CaixaResponse updateCaixa(@RequestBody CaixaMaintenanceRequest request) {
		CaixaResponse caixaResponse = new CaixaResponse();
		try {
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().updateCaixa(request);
			ResponseHandler.handleOperationStatusAndMessages(caixaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, caixaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return caixaResponse;
	}

	/**
	 * Delete caixa.
	 *
	 * @param request
	 *            the request
	 * @return the caixa response
	 */
	@RequestMapping(value = "/caixa/delete", method = RequestMethod.POST)
	@ResponseBody
	public CaixaResponse deleteCaixa(@RequestBody CaixaMaintenanceRequest request) {
		CaixaResponse caixaResponse = new CaixaResponse();

		try {
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().deleteCaixa(request);
			ResponseHandler.handleOperationStatusAndMessages(caixaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, caixaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return caixaResponse;

	}

	// ===================================### CONDPAG
	// ####======================================
	/**
	 * Refresh condpags.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the condpag response
	 */
	@RequestMapping(value = "/agencia/refresh", method = RequestMethod.GET)
	@ResponseBody
	public AgenciaResponse refreshAgencias(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		AgenciaResponse agenciaResponse = new AgenciaResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Agencia> internalResponse = getFinanceiroBAC().refreshAgencias(request);
			ResponseHandler.handleOperationStatusAndMessages(agenciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, agenciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return agenciaResponse;

	}

	/**
	 * Fetch agencia paged.
	 *
	 * @param request
	 *            the request
	 * @return the agencia response
	 */
	@RequestMapping(value = "/agencia/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public AgenciaResponse fetchAgenciaPaged(@RequestBody AgenciaInquiryRequest request) {
		AgenciaResponse agenciaResponse = new AgenciaResponse();
		try {
			InternalResultsResponse<Agencia> internalResponse = getFinanceiroBAC().fetchAgenciasByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(agenciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, agenciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return agenciaResponse;
	}

	/**
	 * Insert agencia.
	 *
	 * @param request
	 *            the request
	 * @return the agencia response
	 */
	@RequestMapping(value = "/agencia/insert", method = RequestMethod.POST)
	@ResponseBody
	public AgenciaResponse insertAgencia(@RequestBody AgenciaMaintenanceRequest request) {
		AgenciaResponse agenciaResponse = new AgenciaResponse();
		try {
			InternalResultsResponse<Agencia> internalResponse = getFinanceiroBAC().insertAgencia(request);
			ResponseHandler.handleOperationStatusAndMessages(agenciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, agenciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return agenciaResponse;
	}

	/**
	 * Update agencia.
	 *
	 * @param request
	 *            the request
	 * @return the agencia response
	 */
	@RequestMapping(value = "/agencia/update", method = RequestMethod.POST)
	@ResponseBody
	public AgenciaResponse updateAgencia(@RequestBody AgenciaMaintenanceRequest request) {
		AgenciaResponse agenciaResponse = new AgenciaResponse();
		try {
			InternalResultsResponse<Agencia> internalResponse = getFinanceiroBAC().updateAgencia(request);
			ResponseHandler.handleOperationStatusAndMessages(agenciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, agenciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return agenciaResponse;
	}

	/**
	 * Delete agencia.
	 *
	 * @param request
	 *            the request
	 * @return the agencia response
	 */
	@RequestMapping(value = "/agencia/delete", method = RequestMethod.POST)
	@ResponseBody
	public AgenciaResponse deleteAgencia(@RequestBody AgenciaMaintenanceRequest request) {
		AgenciaResponse agenciaResponse = new AgenciaResponse();

		try {
			InternalResultsResponse<Agencia> internalResponse = getFinanceiroBAC().deleteAgencia(request);
			ResponseHandler.handleOperationStatusAndMessages(agenciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, agenciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return agenciaResponse;

	}

	// ===================================### CONTAS
	// ####======================================
	/**
	 * Refresh contaspagars.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the contaspagar response
	 */
	@RequestMapping(value = "/conta/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ContaResponse refreshContas(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ContaResponse contaspagarResponse = new ContaResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Conta> internalResponse = getFinanceiroBAC().refreshContas(request);
			ResponseHandler.handleOperationStatusAndMessages(contaspagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contaspagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contaspagarResponse;

	}

	/**
	 * Fetch contaspagar paged.
	 *
	 * @param request
	 *            the request
	 * @return the contaspagar response
	 */
	@RequestMapping(value = "/conta/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ContaResponse fetchContaPaged(@RequestBody ContaInquiryRequest request) {
		ContaResponse contaspagarResponse = new ContaResponse();
		try {
			InternalResultsResponse<Conta> internalResponse = getFinanceiroBAC().fetchContasByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(contaspagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contaspagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contaspagarResponse;
	}

	/**
	 * Insert contaspagar.
	 *
	 * @param request
	 *            the request
	 * @return the contaspagar response
	 */
	@RequestMapping(value = "/conta/insert", method = RequestMethod.POST)
	@ResponseBody
	public ContaResponse insertConta(@RequestBody ContaMaintenanceRequest request) {
		ContaResponse contaspagarResponse = new ContaResponse();
		try {
			InternalResultsResponse<Conta> internalResponse = getFinanceiroBAC().insertConta(request);
			ResponseHandler.handleOperationStatusAndMessages(contaspagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contaspagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contaspagarResponse;
	}

	/**
	 * Update contaspagar.
	 *
	 * @param request
	 *            the request
	 * @return the contaspagar response
	 */
	@RequestMapping(value = "/conta/update", method = RequestMethod.POST)
	@ResponseBody
	public ContaResponse updateConta(@RequestBody ContaMaintenanceRequest request) {
		ContaResponse contaspagarResponse = new ContaResponse();
		try {
			InternalResultsResponse<Conta> internalResponse = getFinanceiroBAC().updateConta(request);
			ResponseHandler.handleOperationStatusAndMessages(contaspagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contaspagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contaspagarResponse;
	}

	/**
	 * Delete contaspagar.
	 *
	 * @param request
	 *            the request
	 * @return the contaspagar response
	 */
	@RequestMapping(value = "/conta/delete", method = RequestMethod.POST)
	@ResponseBody
	public ContaResponse deleteConta(@RequestBody ContaMaintenanceRequest request) {
		ContaResponse contaspagarResponse = new ContaResponse();

		try {
			InternalResultsResponse<Conta> internalResponse = getFinanceiroBAC().deleteConta(request);
			ResponseHandler.handleOperationStatusAndMessages(contaspagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contaspagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contaspagarResponse;

	}

	// ===================================### CONTAS
	// ####======================================
	/**
	 * Refresh contaspagars.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the contaspagar response
	 */
	@RequestMapping(value = "/baixaTitulo/refresh", method = RequestMethod.GET)
	@ResponseBody
	public BaixaTituloResponse refreshBaixaTitulos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		BaixaTituloResponse baixaTitulospagarResponse = new BaixaTituloResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<BaixaTitulo> internalResponse = getFinanceiroBAC().refreshBaixaTitulos(request);
			ResponseHandler.handleOperationStatusAndMessages(baixaTitulospagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, baixaTitulospagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return baixaTitulospagarResponse;

	}

	/**
	 * Fetch baixaTitulospagar paged.
	 *
	 * @param request
	 *            the request
	 * @return the baixaTitulospagar response
	 */
	@RequestMapping(value = "/baixaTitulo/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public BaixaTituloResponse fetchBaixaTituloPaged(@RequestBody BaixaTituloInquiryRequest request) {
		BaixaTituloResponse baixaTitulospagarResponse = new BaixaTituloResponse();
		try {
			InternalResultsResponse<BaixaTitulo> internalResponse = getFinanceiroBAC()
					.fetchBaixaTitulosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(baixaTitulospagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, baixaTitulospagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return baixaTitulospagarResponse;
	}

	/**
	 * Insert baixaTitulospagar.
	 *
	 * @param request
	 *            the request
	 * @return the baixaTitulospagar response
	 */
	@RequestMapping(value = "/baixaTitulo/insert", method = RequestMethod.POST)
	@ResponseBody
	public BaixaTituloResponse insertBaixaTitulo(@RequestBody BaixaTituloMaintenanceRequest request) {
		BaixaTituloResponse baixaTitulospagarResponse = new BaixaTituloResponse();
		try {
			InternalResultsResponse<BaixaTitulo> internalResponse = getFinanceiroBAC().insertBaixaTitulo(request);
			ResponseHandler.handleOperationStatusAndMessages(baixaTitulospagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, baixaTitulospagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return baixaTitulospagarResponse;
	}

	/**
	 * Update baixaTitulospagar.
	 *
	 * @param request
	 *            the request
	 * @return the baixaTitulospagar response
	 */
	@RequestMapping(value = "/baixaTitulo/update", method = RequestMethod.POST)
	@ResponseBody
	public BaixaTituloResponse updateBaixaTitulo(@RequestBody BaixaTituloMaintenanceRequest request) {
		BaixaTituloResponse baixaTitulospagarResponse = new BaixaTituloResponse();
		try {
			InternalResultsResponse<BaixaTitulo> internalResponse = getFinanceiroBAC().updateBaixaTitulo(request);
			ResponseHandler.handleOperationStatusAndMessages(baixaTitulospagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, baixaTitulospagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return baixaTitulospagarResponse;
	}

	/**
	 * Delete baixaTitulospagar.
	 *
	 * @param request
	 *            the request
	 * @return the baixaTitulospagar response
	 */
	@RequestMapping(value = "/baixaTitulo/delete", method = RequestMethod.POST)
	@ResponseBody
	public BaixaTituloResponse deleteBaixaTitulo(@RequestBody BaixaTituloMaintenanceRequest request) {
		BaixaTituloResponse baixaTitulospagarResponse = new BaixaTituloResponse();

		try {
			InternalResultsResponse<BaixaTitulo> internalResponse = getFinanceiroBAC().deleteBaixaTitulo(request);
			ResponseHandler.handleOperationStatusAndMessages(baixaTitulospagarResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, baixaTitulospagarResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return baixaTitulospagarResponse;

	}

}
