/** create by system gera-java version 1.0.0 09/05/2016 16:51 : 47*/
//@Grapes(@Grab("com.github.fabito:busca-cep-java-client:1.7"))
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
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoMaintenanceRequest;
import com.qat.samples.sysmgmt.contato.model.response.ContatoResponse;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
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
	 * Fetch servico paged.
	 *
	 * @param request
	 *            the request
	 * @return the servico response
	 */
	@RequestMapping(value = "/servico/fetchPage", method = RequestMethod.POST)
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
	@RequestMapping(value = "/servico/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/servico/update", method = RequestMethod.POST)
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
	@RequestMapping(value = "/servico/delete", method = RequestMethod.POST)
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
	@RequestMapping(value = "/contato/refresh", method = RequestMethod.GET)
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
	@RequestMapping(value = "/contato/fetchPage", method = RequestMethod.POST)
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
	@RequestMapping(value = "/contato/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/contato/update", method = RequestMethod.POST)
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
	@RequestMapping(value = "/contato/delete", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ordemServico/fetchPage", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ordemServico/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ordemServico/update", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ordemServico/delete", method = RequestMethod.POST)
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
	@RequestMapping(value = "/plano/refresh", method = RequestMethod.GET)
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
	@RequestMapping(value = "/plano/fetchPage", method = RequestMethod.POST)
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
	@RequestMapping(value = "/plano/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/plano/update", method = RequestMethod.POST)
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
	@RequestMapping(value = "/plano/delete", method = RequestMethod.POST)
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
