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
import com.qat.samples.sysmgmt.bac.Site.ISiteBAC;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.ContatoItens;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoMaintenanceRequest;
import com.qat.samples.sysmgmt.contato.model.response.ContatoResponse;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServicoItens;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.response.OrdemServicoResponse;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.PlanoResponse;
import com.qat.samples.sysmgmt.produto.model.response.ServicoResponse;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.site.model.request.SiteInquiryRequest;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.response.SiteResponse;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class SiteAPIController.
 */
@Controller
@RequestMapping("/site/api")
public class SiteAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.sitecontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SiteAPIController.class);

	/** The site bac. */
	private ISiteBAC siteBAC; // injected by @Resource

	/**
	 * Gets the site bac.
	 *
	 * @return the site bac
	 */
	public ISiteBAC getSiteBAC() {
		return siteBAC;
	}

	/**
	 * Sets the site bac.
	 *
	 * @param siteBAC
	 *            the new site bac
	 */
	@Resource
	public void setSiteBAC(ISiteBAC siteBAC) {
		this.siteBAC = siteBAC;
	}

	// ===================================### SERVICO
	// ####======================================
	/**
	 * Refresh servicos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the servico response
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ServicoResponse refreshServicos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ServicoResponse servicoResponse = new ServicoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Servico> internalResponse = getSiteBAC().refreshServicos(request);
			ResponseHandler.handleOperationStatusAndMessages(servicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, servicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return servicoResponse;

	}

	/**
	 * Fetch servico paged.
	 *
	 * @param request
	 *            the request
	 * @return the servico response
	 */
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ServicoResponse fetchServicoPaged(@RequestBody ServicoInquiryRequest request) {
		ServicoResponse servicoResponse = new ServicoResponse();
		try {
			InternalResultsResponse<Servico> internalResponse = getSiteBAC().fetchServicosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(servicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, servicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return servicoResponse;
	}

	/**
	 * Insert servico.
	 *
	 * @param request
	 *            the request
	 * @return the servico response
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public ServicoResponse insertServico(@RequestBody ServicoMaintenanceRequest request) {
		ServicoResponse servicoResponse = new ServicoResponse();
		try {
			InternalResultsResponse<Servico> internalResponse = getSiteBAC().insertServico(request);
			ResponseHandler.handleOperationStatusAndMessages(servicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, servicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return servicoResponse;
	}

	/**
	 * Update servico.
	 *
	 * @param request
	 *            the request
	 * @return the servico response
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ServicoResponse updateServico(@RequestBody ServicoMaintenanceRequest request) {
		ServicoResponse servicoResponse = new ServicoResponse();
		try {
			InternalResultsResponse<Servico> internalResponse = getSiteBAC().updateServico(request);
			ResponseHandler.handleOperationStatusAndMessages(servicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, servicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return servicoResponse;
	}

	/**
	 * Delete servico.
	 *
	 * @param request
	 *            the request
	 * @return the servico response
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ServicoResponse deleteServico(@RequestBody ServicoMaintenanceRequest request) {
		ServicoResponse servicoResponse = new ServicoResponse();

		try {
			InternalResultsResponse<Servico> internalResponse = getSiteBAC().deleteServico(request);
			ResponseHandler.handleOperationStatusAndMessages(servicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, servicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return servicoResponse;

	}

	// ===================================### SERVICOBYPLANO
	// ####======================================
	/**
	 * Refresh servicobyplanos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the servicobyplano response
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ServicoByPlanoResponse refreshServicoByPlanos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ServicoByPlanoResponse servicobyplanoResponse = new ServicoByPlanoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ServicoByPlano> internalResponse = getSiteBAC().refreshServicoByPlanos(request);
			ResponseHandler.handleOperationStatusAndMessages(servicobyplanoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, servicobyplanoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return servicobyplanoResponse;

	}

	/**
	 * Fetch servicobyplano paged.
	 *
	 * @param request
	 *            the request
	 * @return the servicobyplano response
	 */
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ServicoByPlanoResponse fetchServicoByPlanoPaged(@RequestBody ServicoByPlanoInquiryRequest request) {
		ServicoByPlanoResponse servicobyplanoResponse = new ServicoByPlanoResponse();
		try {
			InternalResultsResponse<ServicoByPlano> internalResponse = getSiteBAC()
					.fetchServicoByPlanosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(servicobyplanoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, servicobyplanoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return servicobyplanoResponse;
	}

	/**
	 * Insert servicobyplano.
	 *
	 * @param request
	 *            the request
	 * @return the servicobyplano response
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public ServicoByPlanoResponse insertServicoByPlano(@RequestBody ServicoByPlanoMaintenanceRequest request) {
		ServicoByPlanoResponse servicobyplanoResponse = new ServicoByPlanoResponse();
		try {
			InternalResultsResponse<ServicoByPlano> internalResponse = getSiteBAC().insertServicoByPlano(request);
			ResponseHandler.handleOperationStatusAndMessages(servicobyplanoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, servicobyplanoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return servicobyplanoResponse;
	}

	/**
	 * Update servicobyplano.
	 *
	 * @param request
	 *            the request
	 * @return the servicobyplano response
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ServicoByPlanoResponse updateServicoByPlano(@RequestBody ServicoByPlanoMaintenanceRequest request) {
		ServicoByPlanoResponse servicobyplanoResponse = new ServicoByPlanoResponse();
		try {
			InternalResultsResponse<ServicoByPlano> internalResponse = getSiteBAC().updateServicoByPlano(request);
			ResponseHandler.handleOperationStatusAndMessages(servicobyplanoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, servicobyplanoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return servicobyplanoResponse;
	}

	/**
	 * Delete servicobyplano.
	 *
	 * @param request
	 *            the request
	 * @return the servicobyplano response
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ServicoByPlanoResponse deleteServicoByPlano(@RequestBody ServicoByPlanoMaintenanceRequest request) {
		ServicoByPlanoResponse servicobyplanoResponse = new ServicoByPlanoResponse();

		try {
			InternalResultsResponse<ServicoByPlano> internalResponse = getSiteBAC().deleteServicoByPlano(request);
			ResponseHandler.handleOperationStatusAndMessages(servicobyplanoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, servicobyplanoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return servicobyplanoResponse;

	}

	// ===================================### SITE
	// ####======================================
	/**
	 * Refresh sites.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the site response
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	@ResponseBody
	public SiteResponse refreshSites(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		SiteResponse siteResponse = new SiteResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Site> internalResponse = getSiteBAC().refreshSites(request);
			ResponseHandler.handleOperationStatusAndMessages(siteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, siteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return siteResponse;

	}

	/**
	 * Fetch site paged.
	 *
	 * @param request
	 *            the request
	 * @return the site response
	 */
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public SiteResponse fetchSitePaged(@RequestBody SiteInquiryRequest request) {
		SiteResponse siteResponse = new SiteResponse();
		try {
			InternalResultsResponse<Site> internalResponse = getSiteBAC().fetchSitesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(siteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, siteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return siteResponse;
	}

	/**
	 * Insert site.
	 *
	 * @param request
	 *            the request
	 * @return the site response
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public SiteResponse insertSite(@RequestBody SiteMaintenanceRequest request) {
		SiteResponse siteResponse = new SiteResponse();
		try {
			InternalResultsResponse<Site> internalResponse = getSiteBAC().insertSite(request);
			ResponseHandler.handleOperationStatusAndMessages(siteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, siteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return siteResponse;
	}

	/**
	 * Update site.
	 *
	 * @param request
	 *            the request
	 * @return the site response
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public SiteResponse updateSite(@RequestBody SiteMaintenanceRequest request) {
		SiteResponse siteResponse = new SiteResponse();
		try {
			InternalResultsResponse<Site> internalResponse = getSiteBAC().updateSite(request);
			ResponseHandler.handleOperationStatusAndMessages(siteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, siteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return siteResponse;
	}

	/**
	 * Delete site.
	 *
	 * @param request
	 *            the request
	 * @return the site response
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public SiteResponse deleteSite(@RequestBody SiteMaintenanceRequest request) {
		SiteResponse siteResponse = new SiteResponse();

		try {
			InternalResultsResponse<Site> internalResponse = getSiteBAC().deleteSite(request);
			ResponseHandler.handleOperationStatusAndMessages(siteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, siteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return siteResponse;

	}

	// ===================================### CONTATO
	// ####======================================
	/**
	 * Refresh contatos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the contato response
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ContatoResponse refreshContatos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ContatoResponse contatoResponse = new ContatoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Contato> internalResponse = getSiteBAC().refreshContatos(request);
			ResponseHandler.handleOperationStatusAndMessages(contatoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contatoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contatoResponse;

	}

	/**
	 * Fetch contato paged.
	 *
	 * @param request
	 *            the request
	 * @return the contato response
	 */
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ContatoResponse fetchContatoPaged(@RequestBody ContatoInquiryRequest request) {
		ContatoResponse contatoResponse = new ContatoResponse();
		try {
			InternalResultsResponse<Contato> internalResponse = getSiteBAC().fetchContatosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(contatoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contatoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contatoResponse;
	}

	/**
	 * Insert contato.
	 *
	 * @param request
	 *            the request
	 * @return the contato response
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public ContatoResponse insertContato(@RequestBody ContatoMaintenanceRequest request) {
		ContatoResponse contatoResponse = new ContatoResponse();
		try {
			InternalResultsResponse<Contato> internalResponse = getSiteBAC().insertContato(request);
			ResponseHandler.handleOperationStatusAndMessages(contatoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contatoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contatoResponse;
	}

	/**
	 * Update contato.
	 *
	 * @param request
	 *            the request
	 * @return the contato response
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ContatoResponse updateContato(@RequestBody ContatoMaintenanceRequest request) {
		ContatoResponse contatoResponse = new ContatoResponse();
		try {
			InternalResultsResponse<Contato> internalResponse = getSiteBAC().updateContato(request);
			ResponseHandler.handleOperationStatusAndMessages(contatoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contatoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contatoResponse;
	}

	/**
	 * Delete contato.
	 *
	 * @param request
	 *            the request
	 * @return the contato response
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ContatoResponse deleteContato(@RequestBody ContatoMaintenanceRequest request) {
		ContatoResponse contatoResponse = new ContatoResponse();

		try {
			InternalResultsResponse<Contato> internalResponse = getSiteBAC().deleteContato(request);
			ResponseHandler.handleOperationStatusAndMessages(contatoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contatoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contatoResponse;

	}

	// ===================================### CONTATOITENS
	// ####======================================
	/**
	 * Refresh contatoitenss.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the contatoitens response
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ContatoItensResponse refreshContatoItenss(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ContatoItensResponse contatoitensResponse = new ContatoItensResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ContatoItens> internalResponse = getSiteBAC().refreshContatoItenss(request);
			ResponseHandler.handleOperationStatusAndMessages(contatoitensResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contatoitensResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contatoitensResponse;

	}

	/**
	 * Fetch contatoitens paged.
	 *
	 * @param request
	 *            the request
	 * @return the contatoitens response
	 */
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ContatoItensResponse fetchContatoItensPaged(@RequestBody ContatoItensInquiryRequest request) {
		ContatoItensResponse contatoitensResponse = new ContatoItensResponse();
		try {
			InternalResultsResponse<ContatoItens> internalResponse = getSiteBAC().fetchContatoItenssByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(contatoitensResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contatoitensResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contatoitensResponse;
	}

	/**
	 * Insert contatoitens.
	 *
	 * @param request
	 *            the request
	 * @return the contatoitens response
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public ContatoItensResponse insertContatoItens(@RequestBody ContatoItensMaintenanceRequest request) {
		ContatoItensResponse contatoitensResponse = new ContatoItensResponse();
		try {
			InternalResultsResponse<ContatoItens> internalResponse = getSiteBAC().insertContatoItens(request);
			ResponseHandler.handleOperationStatusAndMessages(contatoitensResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contatoitensResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contatoitensResponse;
	}

	/**
	 * Update contatoitens.
	 *
	 * @param request
	 *            the request
	 * @return the contatoitens response
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ContatoItensResponse updateContatoItens(@RequestBody ContatoItensMaintenanceRequest request) {
		ContatoItensResponse contatoitensResponse = new ContatoItensResponse();
		try {
			InternalResultsResponse<ContatoItens> internalResponse = getSiteBAC().updateContatoItens(request);
			ResponseHandler.handleOperationStatusAndMessages(contatoitensResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contatoitensResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contatoitensResponse;
	}

	/**
	 * Delete contatoitens.
	 *
	 * @param request
	 *            the request
	 * @return the contatoitens response
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ContatoItensResponse deleteContatoItens(@RequestBody ContatoItensMaintenanceRequest request) {
		ContatoItensResponse contatoitensResponse = new ContatoItensResponse();

		try {
			InternalResultsResponse<ContatoItens> internalResponse = getSiteBAC().deleteContatoItens(request);
			ResponseHandler.handleOperationStatusAndMessages(contatoitensResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, contatoitensResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return contatoitensResponse;

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
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	@ResponseBody
	public OrdemServicoResponse refreshOrdemServicos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<OrdemServico> internalResponse = getSiteBAC().refreshOrdemServicos(request);
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
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse fetchOrdemServicoPaged(@RequestBody OrdemServicoInquiryRequest request) {
		OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();
		try {
			InternalResultsResponse<OrdemServico> internalResponse = getSiteBAC().fetchOrdemServicosByRequest(request);
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
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse insertOrdemServico(@RequestBody OrdemServicoMaintenanceRequest request) {
		OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();
		try {
			InternalResultsResponse<OrdemServico> internalResponse = getSiteBAC().insertOrdemServico(request);
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse updateOrdemServico(@RequestBody OrdemServicoMaintenanceRequest request) {
		OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();
		try {
			InternalResultsResponse<OrdemServico> internalResponse = getSiteBAC().updateOrdemServico(request);
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse deleteOrdemServico(@RequestBody OrdemServicoMaintenanceRequest request) {
		OrdemServicoResponse ordemservicoResponse = new OrdemServicoResponse();

		try {
			InternalResultsResponse<OrdemServico> internalResponse = getSiteBAC().deleteOrdemServico(request);
			ResponseHandler.handleOperationStatusAndMessages(ordemservicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, ordemservicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return ordemservicoResponse;

	}

	// ===================================### ORDEMSERVICOITENS
	// ####======================================
	/**
	 * Refresh ordemservicoitenss.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the ordemservicoitens response
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	@ResponseBody
	public OrdemServicoItensResponse refreshOrdemServicoItenss(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		OrdemServicoItensResponse ordemservicoitensResponse = new OrdemServicoItensResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<OrdemServicoItens> internalResponse = getSiteBAC()
					.refreshOrdemServicoItenss(request);
			ResponseHandler.handleOperationStatusAndMessages(ordemservicoitensResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, ordemservicoitensResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return ordemservicoitensResponse;

	}

	/**
	 * Fetch ordemservicoitens paged.
	 *
	 * @param request
	 *            the request
	 * @return the ordemservicoitens response
	 */
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoItensResponse fetchOrdemServicoItensPaged(@RequestBody OrdemServicoItensInquiryRequest request) {
		OrdemServicoItensResponse ordemservicoitensResponse = new OrdemServicoItensResponse();
		try {
			InternalResultsResponse<OrdemServicoItens> internalResponse = getSiteBAC()
					.fetchOrdemServicoItenssByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(ordemservicoitensResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, ordemservicoitensResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return ordemservicoitensResponse;
	}

	/**
	 * Insert ordemservicoitens.
	 *
	 * @param request
	 *            the request
	 * @return the ordemservicoitens response
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoItensResponse insertOrdemServicoItens(@RequestBody OrdemServicoItensMaintenanceRequest request) {
		OrdemServicoItensResponse ordemservicoitensResponse = new OrdemServicoItensResponse();
		try {
			InternalResultsResponse<OrdemServicoItens> internalResponse = getSiteBAC().insertOrdemServicoItens(request);
			ResponseHandler.handleOperationStatusAndMessages(ordemservicoitensResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, ordemservicoitensResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return ordemservicoitensResponse;
	}

	/**
	 * Update ordemservicoitens.
	 *
	 * @param request
	 *            the request
	 * @return the ordemservicoitens response
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoItensResponse updateOrdemServicoItens(@RequestBody OrdemServicoItensMaintenanceRequest request) {
		OrdemServicoItensResponse ordemservicoitensResponse = new OrdemServicoItensResponse();
		try {
			InternalResultsResponse<OrdemServicoItens> internalResponse = getSiteBAC().updateOrdemServicoItens(request);
			ResponseHandler.handleOperationStatusAndMessages(ordemservicoitensResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, ordemservicoitensResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return ordemservicoitensResponse;
	}

	/**
	 * Delete ordemservicoitens.
	 *
	 * @param request
	 *            the request
	 * @return the ordemservicoitens response
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoItensResponse deleteOrdemServicoItens(@RequestBody OrdemServicoItensMaintenanceRequest request) {
		OrdemServicoItensResponse ordemservicoitensResponse = new OrdemServicoItensResponse();

		try {
			InternalResultsResponse<OrdemServicoItens> internalResponse = getSiteBAC().deleteOrdemServicoItens(request);
			ResponseHandler.handleOperationStatusAndMessages(ordemservicoitensResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, ordemservicoitensResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return ordemservicoitensResponse;

	}

	// ===================================### PLANO
	// ####======================================
	/**
	 * Refresh planos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the plano response
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	@ResponseBody
	public PlanoResponse refreshPlanos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		PlanoResponse planoResponse = new PlanoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Plano> internalResponse = getSiteBAC().refreshPlanos(request);
			ResponseHandler.handleOperationStatusAndMessages(planoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, planoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return planoResponse;

	}

	/**
	 * Fetch plano paged.
	 *
	 * @param request
	 *            the request
	 * @return the plano response
	 */
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public PlanoResponse fetchPlanoPaged(@RequestBody PlanoInquiryRequest request) {
		PlanoResponse planoResponse = new PlanoResponse();
		try {
			InternalResultsResponse<Plano> internalResponse = getSiteBAC().fetchPlanosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(planoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, planoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return planoResponse;
	}

	/**
	 * Insert plano.
	 *
	 * @param request
	 *            the request
	 * @return the plano response
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public PlanoResponse insertPlano(@RequestBody PlanoMaintenanceRequest request) {
		PlanoResponse planoResponse = new PlanoResponse();
		try {
			InternalResultsResponse<Plano> internalResponse = getSiteBAC().insertPlano(request);
			ResponseHandler.handleOperationStatusAndMessages(planoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, planoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return planoResponse;
	}

	/**
	 * Update plano.
	 *
	 * @param request
	 *            the request
	 * @return the plano response
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public PlanoResponse updatePlano(@RequestBody PlanoMaintenanceRequest request) {
		PlanoResponse planoResponse = new PlanoResponse();
		try {
			InternalResultsResponse<Plano> internalResponse = getSiteBAC().updatePlano(request);
			ResponseHandler.handleOperationStatusAndMessages(planoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, planoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return planoResponse;
	}

	/**
	 * Delete plano.
	 *
	 * @param request
	 *            the request
	 * @return the plano response
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public PlanoResponse deletePlano(@RequestBody PlanoMaintenanceRequest request) {
		PlanoResponse planoResponse = new PlanoResponse();

		try {
			InternalResultsResponse<Plano> internalResponse = getSiteBAC().deletePlano(request);
			ResponseHandler.handleOperationStatusAndMessages(planoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, planoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return planoResponse;

	}

}
