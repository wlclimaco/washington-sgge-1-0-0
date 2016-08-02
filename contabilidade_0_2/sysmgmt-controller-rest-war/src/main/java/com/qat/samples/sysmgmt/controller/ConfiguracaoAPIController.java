/** create by system gera-java version 1.0.0 02/08/2016 16:52 : 21*/

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
import com.qat.samples.sysmgmt.bac.Configuracao.IConfiguracaoBAC;
import com.qat.samples.sysmgmt.entidade.model.Boleto;
import com.qat.samples.sysmgmt.entidade.model.ConfigAlertas;
import com.qat.samples.sysmgmt.entidade.model.ConfigCarne;
import com.qat.samples.sysmgmt.entidade.model.ConfigEntrada;
import com.qat.samples.sysmgmt.entidade.model.ConfigFiscal;
import com.qat.samples.sysmgmt.entidade.model.ConfigGeral;
import com.qat.samples.sysmgmt.entidade.model.ConfigProduto;
import com.qat.samples.sysmgmt.entidade.model.ConfigSMTP;
import com.qat.samples.sysmgmt.entidade.model.ConfigVendas;
import com.qat.samples.sysmgmt.entidade.model.Configuracao;
import com.qat.samples.sysmgmt.entidade.model.ConfiguracaoNFe;
import com.qat.samples.sysmgmt.entidade.model.request.BoletoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigAlertasMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigCarneMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigEntradaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigFiscalMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigGeralMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigSMTPMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigVendasMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfiguracaoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfiguracaoNFeMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.response.BoletoResponse;
import com.qat.samples.sysmgmt.entidade.model.response.ConfigAlertasResponse;
import com.qat.samples.sysmgmt.entidade.model.response.ConfigCarneResponse;
import com.qat.samples.sysmgmt.entidade.model.response.ConfigEntradaResponse;
import com.qat.samples.sysmgmt.entidade.model.response.ConfigFiscalResponse;
import com.qat.samples.sysmgmt.entidade.model.response.ConfigGeralResponse;
import com.qat.samples.sysmgmt.entidade.model.response.ConfigProdutoResponse;
import com.qat.samples.sysmgmt.entidade.model.response.ConfigSMTPResponse;
import com.qat.samples.sysmgmt.entidade.model.response.ConfigVendasResponse;
import com.qat.samples.sysmgmt.entidade.model.response.ConfiguracaoNFeResponse;
import com.qat.samples.sysmgmt.entidade.model.response.ConfiguracaoResponse;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class ConfiguracaoAPIController.
 */
@Controller
@RequestMapping("/configuracao/api")
public class ConfiguracaoAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.configuracaocontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ConfiguracaoAPIController.class);

	/** The configuracao bac. */
	private IConfiguracaoBAC configuracaoBAC; // injected by @Resource

	/**
	 * Gets the configuracao bac.
	 *
	 * @return the configuracao bac
	 */
	public IConfiguracaoBAC getConfiguracaoBAC() {
		return configuracaoBAC;
	}

	/**
	 * Sets the configuracao bac.
	 *
	 * @param configuracaoBAC
	 *            the new configuracao bac
	 */
	@Resource
	public void setConfiguracaoBAC(IConfiguracaoBAC configuracaoBAC) {
		this.configuracaoBAC = configuracaoBAC;
	}

	// ===================================### CONFIGURACAO
	// ####======================================
	/**
	 * Refresh configuracaos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the configuracao response
	 */
	@RequestMapping(value = "/configuracao/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ConfiguracaoResponse refreshConfiguracaos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ConfiguracaoResponse configuracaoResponse = new ConfiguracaoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Configuracao> internalResponse = getConfiguracaoBAC().refreshConfiguracaos(request);
			ResponseHandler.handleOperationStatusAndMessages(configuracaoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configuracaoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configuracaoResponse;

	}

	/**
	 * Fetch configuracao paged.
	 *
	 * @param request
	 *            the request
	 * @return the configuracao response
	 */
	@RequestMapping(value = "/configuracao/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ConfiguracaoResponse fetchConfiguracaoPaged(@RequestBody PagedInquiryRequest request) {
		ConfiguracaoResponse configuracaoResponse = new ConfiguracaoResponse();
		try {
			InternalResultsResponse<Configuracao> internalResponse = getConfiguracaoBAC()
					.fetchConfiguracaosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(configuracaoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configuracaoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configuracaoResponse;
	}

	/**
	 * Insert configuracao.
	 *
	 * @param request
	 *            the request
	 * @return the configuracao response
	 */
	@RequestMapping(value = "/configuracao/insert", method = RequestMethod.POST)
	@ResponseBody
	public ConfiguracaoResponse insertConfiguracao(@RequestBody ConfiguracaoMaintenanceRequest request) {
		ConfiguracaoResponse configuracaoResponse = new ConfiguracaoResponse();
		try {
			InternalResultsResponse<Configuracao> internalResponse = getConfiguracaoBAC().insertConfiguracao(request);
			ResponseHandler.handleOperationStatusAndMessages(configuracaoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configuracaoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configuracaoResponse;
	}

	/**
	 * Update configuracao.
	 *
	 * @param request
	 *            the request
	 * @return the configuracao response
	 */
	@RequestMapping(value = "/configuracao/update", method = RequestMethod.POST)
	@ResponseBody
	public ConfiguracaoResponse updateConfiguracao(@RequestBody ConfiguracaoMaintenanceRequest request) {
		ConfiguracaoResponse configuracaoResponse = new ConfiguracaoResponse();
		try {
			InternalResultsResponse<Configuracao> internalResponse = getConfiguracaoBAC().updateConfiguracao(request);
			ResponseHandler.handleOperationStatusAndMessages(configuracaoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configuracaoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configuracaoResponse;
	}

	/**
	 * Delete configuracao.
	 *
	 * @param request
	 *            the request
	 * @return the configuracao response
	 */
	@RequestMapping(value = "/configuracao/delete", method = RequestMethod.POST)
	@ResponseBody
	public ConfiguracaoResponse deleteConfiguracao(@RequestBody ConfiguracaoMaintenanceRequest request) {
		ConfiguracaoResponse configuracaoResponse = new ConfiguracaoResponse();

		try {
			InternalResultsResponse<Configuracao> internalResponse = getConfiguracaoBAC().deleteConfiguracao(request);
			ResponseHandler.handleOperationStatusAndMessages(configuracaoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configuracaoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configuracaoResponse;

	}

	// ===================================### BOLETO
	// ####======================================
	/**
	 * Refresh boletos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the boleto response
	 */
	@RequestMapping(value = "/boleto/refresh", method = RequestMethod.GET)
	@ResponseBody
	public BoletoResponse refreshBoletos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		BoletoResponse boletoResponse = new BoletoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Boleto> internalResponse = getConfiguracaoBAC().refreshBoletos(request);
			ResponseHandler.handleOperationStatusAndMessages(boletoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, boletoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return boletoResponse;

	}

	/**
	 * Fetch boleto paged.
	 *
	 * @param request
	 *            the request
	 * @return the boleto response
	 */
	@RequestMapping(value = "/boleto/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public BoletoResponse fetchBoletoPaged(@RequestBody PagedInquiryRequest request) {
		BoletoResponse boletoResponse = new BoletoResponse();
		try {
			InternalResultsResponse<Boleto> internalResponse = getConfiguracaoBAC().fetchBoletosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(boletoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, boletoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return boletoResponse;
	}

	/**
	 * Insert boleto.
	 *
	 * @param request
	 *            the request
	 * @return the boleto response
	 */
	@RequestMapping(value = "/boleto/insert", method = RequestMethod.POST)
	@ResponseBody
	public BoletoResponse insertBoleto(@RequestBody BoletoMaintenanceRequest request) {
		BoletoResponse boletoResponse = new BoletoResponse();
		try {
			InternalResultsResponse<Boleto> internalResponse = getConfiguracaoBAC().insertBoleto(request);
			ResponseHandler.handleOperationStatusAndMessages(boletoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, boletoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return boletoResponse;
	}

	/**
	 * Update boleto.
	 *
	 * @param request
	 *            the request
	 * @return the boleto response
	 */
	@RequestMapping(value = "/boleto/update", method = RequestMethod.POST)
	@ResponseBody
	public BoletoResponse updateBoleto(@RequestBody BoletoMaintenanceRequest request) {
		BoletoResponse boletoResponse = new BoletoResponse();
		try {
			InternalResultsResponse<Boleto> internalResponse = getConfiguracaoBAC().updateBoleto(request);
			ResponseHandler.handleOperationStatusAndMessages(boletoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, boletoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return boletoResponse;
	}

	/**
	 * Delete boleto.
	 *
	 * @param request
	 *            the request
	 * @return the boleto response
	 */
	@RequestMapping(value = "/boleto/delete", method = RequestMethod.POST)
	@ResponseBody
	public BoletoResponse deleteBoleto(@RequestBody BoletoMaintenanceRequest request) {
		BoletoResponse boletoResponse = new BoletoResponse();

		try {
			InternalResultsResponse<Boleto> internalResponse = getConfiguracaoBAC().deleteBoleto(request);
			ResponseHandler.handleOperationStatusAndMessages(boletoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, boletoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return boletoResponse;

	}

	// ===================================### CONFIGCARNE
	// ####======================================
	/**
	 * Refresh configcarnes.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the configcarne response
	 */
	@RequestMapping(value = "/configCarne/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ConfigCarneResponse refreshConfigCarnes(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ConfigCarneResponse configcarneResponse = new ConfigCarneResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ConfigCarne> internalResponse = getConfiguracaoBAC().refreshConfigCarnes(request);
			ResponseHandler.handleOperationStatusAndMessages(configcarneResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configcarneResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configcarneResponse;

	}

	/**
	 * Fetch configcarne paged.
	 *
	 * @param request
	 *            the request
	 * @return the configcarne response
	 */
	@RequestMapping(value = "/configCarne/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ConfigCarneResponse fetchConfigCarnePaged(@RequestBody PagedInquiryRequest request) {
		ConfigCarneResponse configcarneResponse = new ConfigCarneResponse();
		try {
			InternalResultsResponse<ConfigCarne> internalResponse = getConfiguracaoBAC()
					.fetchConfigCarnesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(configcarneResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configcarneResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configcarneResponse;
	}

	/**
	 * Insert configcarne.
	 *
	 * @param request
	 *            the request
	 * @return the configcarne response
	 */
	@RequestMapping(value = "/configCarne/insert", method = RequestMethod.POST)
	@ResponseBody
	public ConfigCarneResponse insertConfigCarne(@RequestBody ConfigCarneMaintenanceRequest request) {
		ConfigCarneResponse configcarneResponse = new ConfigCarneResponse();
		try {
			InternalResultsResponse<ConfigCarne> internalResponse = getConfiguracaoBAC().insertConfigCarne(request);
			ResponseHandler.handleOperationStatusAndMessages(configcarneResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configcarneResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configcarneResponse;
	}

	/**
	 * Update configcarne.
	 *
	 * @param request
	 *            the request
	 * @return the configcarne response
	 */
	@RequestMapping(value = "/configCarne/update", method = RequestMethod.POST)
	@ResponseBody
	public ConfigCarneResponse updateConfigCarne(@RequestBody ConfigCarneMaintenanceRequest request) {
		ConfigCarneResponse configcarneResponse = new ConfigCarneResponse();
		try {
			InternalResultsResponse<ConfigCarne> internalResponse = getConfiguracaoBAC().updateConfigCarne(request);
			ResponseHandler.handleOperationStatusAndMessages(configcarneResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configcarneResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configcarneResponse;
	}

	/**
	 * Delete configcarne.
	 *
	 * @param request
	 *            the request
	 * @return the configcarne response
	 */
	@RequestMapping(value = "/configCarne/delete", method = RequestMethod.POST)
	@ResponseBody
	public ConfigCarneResponse deleteConfigCarne(@RequestBody ConfigCarneMaintenanceRequest request) {
		ConfigCarneResponse configcarneResponse = new ConfigCarneResponse();

		try {
			InternalResultsResponse<ConfigCarne> internalResponse = getConfiguracaoBAC().deleteConfigCarne(request);
			ResponseHandler.handleOperationStatusAndMessages(configcarneResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configcarneResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configcarneResponse;

	}

	// ===================================### CONFIGENTRADA
	// ####======================================
	/**
	 * Refresh configentradas.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the configentrada response
	 */
	@RequestMapping(value = "/configEntrada/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ConfigEntradaResponse refreshConfigEntradas(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ConfigEntradaResponse configentradaResponse = new ConfigEntradaResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ConfigEntrada> internalResponse = getConfiguracaoBAC()
					.refreshConfigEntradas(request);
			ResponseHandler.handleOperationStatusAndMessages(configentradaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configentradaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configentradaResponse;

	}

	/**
	 * Fetch configentrada paged.
	 *
	 * @param request
	 *            the request
	 * @return the configentrada response
	 */
	@RequestMapping(value = "/configEntrada/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ConfigEntradaResponse fetchConfigEntradaPaged(@RequestBody PagedInquiryRequest request) {
		ConfigEntradaResponse configentradaResponse = new ConfigEntradaResponse();
		try {
			InternalResultsResponse<ConfigEntrada> internalResponse = getConfiguracaoBAC()
					.fetchConfigEntradasByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(configentradaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configentradaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configentradaResponse;
	}

	/**
	 * Insert configentrada.
	 *
	 * @param request
	 *            the request
	 * @return the configentrada response
	 */
	@RequestMapping(value = "/configEntrada/insert", method = RequestMethod.POST)
	@ResponseBody
	public ConfigEntradaResponse insertConfigEntrada(@RequestBody ConfigEntradaMaintenanceRequest request) {
		ConfigEntradaResponse configentradaResponse = new ConfigEntradaResponse();
		try {
			InternalResultsResponse<ConfigEntrada> internalResponse = getConfiguracaoBAC().insertConfigEntrada(request);
			ResponseHandler.handleOperationStatusAndMessages(configentradaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configentradaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configentradaResponse;
	}

	/**
	 * Update configentrada.
	 *
	 * @param request
	 *            the request
	 * @return the configentrada response
	 */
	@RequestMapping(value = "/configEntrada/update", method = RequestMethod.POST)
	@ResponseBody
	public ConfigEntradaResponse updateConfigEntrada(@RequestBody ConfigEntradaMaintenanceRequest request) {
		ConfigEntradaResponse configentradaResponse = new ConfigEntradaResponse();
		try {
			InternalResultsResponse<ConfigEntrada> internalResponse = getConfiguracaoBAC().updateConfigEntrada(request);
			ResponseHandler.handleOperationStatusAndMessages(configentradaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configentradaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configentradaResponse;
	}

	/**
	 * Delete configentrada.
	 *
	 * @param request
	 *            the request
	 * @return the configentrada response
	 */
	@RequestMapping(value = "/configEntrada/delete", method = RequestMethod.POST)
	@ResponseBody
	public ConfigEntradaResponse deleteConfigEntrada(@RequestBody ConfigEntradaMaintenanceRequest request) {
		ConfigEntradaResponse configentradaResponse = new ConfigEntradaResponse();

		try {
			InternalResultsResponse<ConfigEntrada> internalResponse = getConfiguracaoBAC().deleteConfigEntrada(request);
			ResponseHandler.handleOperationStatusAndMessages(configentradaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configentradaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configentradaResponse;

	}

	// ===================================### CONFIGFISCAL
	// ####======================================
	/**
	 * Refresh configfiscals.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the configfiscal response
	 */
	@RequestMapping(value = "/configFiscal/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ConfigFiscalResponse refreshConfigFiscals(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ConfigFiscalResponse configfiscalResponse = new ConfigFiscalResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ConfigFiscal> internalResponse = getConfiguracaoBAC().refreshConfigFiscals(request);
			ResponseHandler.handleOperationStatusAndMessages(configfiscalResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configfiscalResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configfiscalResponse;

	}

	/**
	 * Fetch configfiscal paged.
	 *
	 * @param request
	 *            the request
	 * @return the configfiscal response
	 */
	@RequestMapping(value = "/configFiscal/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ConfigFiscalResponse fetchConfigFiscalPaged(@RequestBody PagedInquiryRequest request) {
		ConfigFiscalResponse configfiscalResponse = new ConfigFiscalResponse();
		try {
			InternalResultsResponse<ConfigFiscal> internalResponse = getConfiguracaoBAC()
					.fetchConfigFiscalsByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(configfiscalResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configfiscalResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configfiscalResponse;
	}

	/**
	 * Insert configfiscal.
	 *
	 * @param request
	 *            the request
	 * @return the configfiscal response
	 */
	@RequestMapping(value = "/configFiscal/insert", method = RequestMethod.POST)
	@ResponseBody
	public ConfigFiscalResponse insertConfigFiscal(@RequestBody ConfigFiscalMaintenanceRequest request) {
		ConfigFiscalResponse configfiscalResponse = new ConfigFiscalResponse();
		try {
			InternalResultsResponse<ConfigFiscal> internalResponse = getConfiguracaoBAC().insertConfigFiscal(request);
			ResponseHandler.handleOperationStatusAndMessages(configfiscalResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configfiscalResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configfiscalResponse;
	}

	/**
	 * Update configfiscal.
	 *
	 * @param request
	 *            the request
	 * @return the configfiscal response
	 */
	@RequestMapping(value = "/configFiscal/update", method = RequestMethod.POST)
	@ResponseBody
	public ConfigFiscalResponse updateConfigFiscal(@RequestBody ConfigFiscalMaintenanceRequest request) {
		ConfigFiscalResponse configfiscalResponse = new ConfigFiscalResponse();
		try {
			InternalResultsResponse<ConfigFiscal> internalResponse = getConfiguracaoBAC().updateConfigFiscal(request);
			ResponseHandler.handleOperationStatusAndMessages(configfiscalResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configfiscalResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configfiscalResponse;
	}

	/**
	 * Delete configfiscal.
	 *
	 * @param request
	 *            the request
	 * @return the configfiscal response
	 */
	@RequestMapping(value = "/configFiscal/delete", method = RequestMethod.POST)
	@ResponseBody
	public ConfigFiscalResponse deleteConfigFiscal(@RequestBody ConfigFiscalMaintenanceRequest request) {
		ConfigFiscalResponse configfiscalResponse = new ConfigFiscalResponse();

		try {
			InternalResultsResponse<ConfigFiscal> internalResponse = getConfiguracaoBAC().deleteConfigFiscal(request);
			ResponseHandler.handleOperationStatusAndMessages(configfiscalResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configfiscalResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configfiscalResponse;

	}

	// ===================================### CONFIGALERTAS
	// ####======================================
	/**
	 * Refresh configalertass.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the configalertas response
	 */
	@RequestMapping(value = "/configAlertas/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ConfigAlertasResponse refreshConfigAlertass(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ConfigAlertasResponse configalertasResponse = new ConfigAlertasResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ConfigAlertas> internalResponse = getConfiguracaoBAC()
					.refreshConfigAlertass(request);
			ResponseHandler.handleOperationStatusAndMessages(configalertasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configalertasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configalertasResponse;

	}

	/**
	 * Fetch configalertas paged.
	 *
	 * @param request
	 *            the request
	 * @return the configalertas response
	 */
	@RequestMapping(value = "/configAlertas/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ConfigAlertasResponse fetchConfigAlertasPaged(@RequestBody PagedInquiryRequest request) {
		ConfigAlertasResponse configalertasResponse = new ConfigAlertasResponse();
		try {
			InternalResultsResponse<ConfigAlertas> internalResponse = getConfiguracaoBAC()
					.fetchConfigAlertassByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(configalertasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configalertasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configalertasResponse;
	}

	/**
	 * Insert configalertas.
	 *
	 * @param request
	 *            the request
	 * @return the configalertas response
	 */
	@RequestMapping(value = "/configAlertas/insert", method = RequestMethod.POST)
	@ResponseBody
	public ConfigAlertasResponse insertConfigAlertas(@RequestBody ConfigAlertasMaintenanceRequest request) {
		ConfigAlertasResponse configalertasResponse = new ConfigAlertasResponse();
		try {
			InternalResultsResponse<ConfigAlertas> internalResponse = getConfiguracaoBAC().insertConfigAlertas(request);
			ResponseHandler.handleOperationStatusAndMessages(configalertasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configalertasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configalertasResponse;
	}

	/**
	 * Update configalertas.
	 *
	 * @param request
	 *            the request
	 * @return the configalertas response
	 */
	@RequestMapping(value = "/configAlertas/update", method = RequestMethod.POST)
	@ResponseBody
	public ConfigAlertasResponse updateConfigAlertas(@RequestBody ConfigAlertasMaintenanceRequest request) {
		ConfigAlertasResponse configalertasResponse = new ConfigAlertasResponse();
		try {
			InternalResultsResponse<ConfigAlertas> internalResponse = getConfiguracaoBAC().updateConfigAlertas(request);
			ResponseHandler.handleOperationStatusAndMessages(configalertasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configalertasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configalertasResponse;
	}

	/**
	 * Delete configalertas.
	 *
	 * @param request
	 *            the request
	 * @return the configalertas response
	 */
	@RequestMapping(value = "/configAlertas/delete", method = RequestMethod.POST)
	@ResponseBody
	public ConfigAlertasResponse deleteConfigAlertas(@RequestBody ConfigAlertasMaintenanceRequest request) {
		ConfigAlertasResponse configalertasResponse = new ConfigAlertasResponse();

		try {
			InternalResultsResponse<ConfigAlertas> internalResponse = getConfiguracaoBAC().deleteConfigAlertas(request);
			ResponseHandler.handleOperationStatusAndMessages(configalertasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configalertasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configalertasResponse;

	}

	// ===================================### CONFIGGERAL
	// ####======================================
	/**
	 * Refresh configgerals.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the configgeral response
	 */
	@RequestMapping(value = "/configGeral/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ConfigGeralResponse refreshConfigGerals(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ConfigGeralResponse configgeralResponse = new ConfigGeralResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ConfigGeral> internalResponse = getConfiguracaoBAC().refreshConfigGerals(request);
			ResponseHandler.handleOperationStatusAndMessages(configgeralResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configgeralResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configgeralResponse;

	}

	/**
	 * Fetch configgeral paged.
	 *
	 * @param request
	 *            the request
	 * @return the configgeral response
	 */
	@RequestMapping(value = "/configGeral/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ConfigGeralResponse fetchConfigGeralPaged(@RequestBody PagedInquiryRequest request) {
		ConfigGeralResponse configgeralResponse = new ConfigGeralResponse();
		try {
			InternalResultsResponse<ConfigGeral> internalResponse = getConfiguracaoBAC()
					.fetchConfigGeralsByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(configgeralResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configgeralResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configgeralResponse;
	}

	/**
	 * Insert configgeral.
	 *
	 * @param request
	 *            the request
	 * @return the configgeral response
	 */
	@RequestMapping(value = "/configGeral/insert", method = RequestMethod.POST)
	@ResponseBody
	public ConfigGeralResponse insertConfigGeral(@RequestBody ConfigGeralMaintenanceRequest request) {
		ConfigGeralResponse configgeralResponse = new ConfigGeralResponse();
		try {
			InternalResultsResponse<ConfigGeral> internalResponse = getConfiguracaoBAC().insertConfigGeral(request);
			ResponseHandler.handleOperationStatusAndMessages(configgeralResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configgeralResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configgeralResponse;
	}

	/**
	 * Update configgeral.
	 *
	 * @param request
	 *            the request
	 * @return the configgeral response
	 */
	@RequestMapping(value = "/configGeral/update", method = RequestMethod.POST)
	@ResponseBody
	public ConfigGeralResponse updateConfigGeral(@RequestBody ConfigGeralMaintenanceRequest request) {
		ConfigGeralResponse configgeralResponse = new ConfigGeralResponse();
		try {
			InternalResultsResponse<ConfigGeral> internalResponse = getConfiguracaoBAC().updateConfigGeral(request);
			ResponseHandler.handleOperationStatusAndMessages(configgeralResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configgeralResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configgeralResponse;
	}

	/**
	 * Delete configgeral.
	 *
	 * @param request
	 *            the request
	 * @return the configgeral response
	 */
	@RequestMapping(value = "/configGeral/delete", method = RequestMethod.POST)
	@ResponseBody
	public ConfigGeralResponse deleteConfigGeral(@RequestBody ConfigGeralMaintenanceRequest request) {
		ConfigGeralResponse configgeralResponse = new ConfigGeralResponse();

		try {
			InternalResultsResponse<ConfigGeral> internalResponse = getConfiguracaoBAC().deleteConfigGeral(request);
			ResponseHandler.handleOperationStatusAndMessages(configgeralResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configgeralResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configgeralResponse;

	}

	// ===================================### CONFIGPRODUTO
	// ####======================================
	/**
	 * Refresh configprodutos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the configproduto response
	 */
	@RequestMapping(value = "/configProduto/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ConfigProdutoResponse refreshConfigProdutos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ConfigProdutoResponse configprodutoResponse = new ConfigProdutoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ConfigProduto> internalResponse = getConfiguracaoBAC()
					.refreshConfigProdutos(request);
			ResponseHandler.handleOperationStatusAndMessages(configprodutoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configprodutoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configprodutoResponse;

	}

	/**
	 * Fetch configproduto paged.
	 *
	 * @param request
	 *            the request
	 * @return the configproduto response
	 */
	@RequestMapping(value = "/configProduto/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ConfigProdutoResponse fetchConfigProdutoPaged(@RequestBody PagedInquiryRequest request) {
		ConfigProdutoResponse configprodutoResponse = new ConfigProdutoResponse();
		try {
			InternalResultsResponse<ConfigProduto> internalResponse = getConfiguracaoBAC()
					.fetchConfigProdutosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(configprodutoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configprodutoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configprodutoResponse;
	}

	/**
	 * Insert configproduto.
	 *
	 * @param request
	 *            the request
	 * @return the configproduto response
	 */
	@RequestMapping(value = "/configProduto/insert", method = RequestMethod.POST)
	@ResponseBody
	public ConfigProdutoResponse insertConfigProduto(@RequestBody ConfigProdutoMaintenanceRequest request) {
		ConfigProdutoResponse configprodutoResponse = new ConfigProdutoResponse();
		try {
			InternalResultsResponse<ConfigProduto> internalResponse = getConfiguracaoBAC().insertConfigProduto(request);
			ResponseHandler.handleOperationStatusAndMessages(configprodutoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configprodutoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configprodutoResponse;
	}

	/**
	 * Update configproduto.
	 *
	 * @param request
	 *            the request
	 * @return the configproduto response
	 */
	@RequestMapping(value = "/configProduto/update", method = RequestMethod.POST)
	@ResponseBody
	public ConfigProdutoResponse updateConfigProduto(@RequestBody ConfigProdutoMaintenanceRequest request) {
		ConfigProdutoResponse configprodutoResponse = new ConfigProdutoResponse();
		try {
			InternalResultsResponse<ConfigProduto> internalResponse = getConfiguracaoBAC().updateConfigProduto(request);
			ResponseHandler.handleOperationStatusAndMessages(configprodutoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configprodutoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configprodutoResponse;
	}

	/**
	 * Delete configproduto.
	 *
	 * @param request
	 *            the request
	 * @return the configproduto response
	 */
	@RequestMapping(value = "/configProduto/delete", method = RequestMethod.POST)
	@ResponseBody
	public ConfigProdutoResponse deleteConfigProduto(@RequestBody ConfigProdutoMaintenanceRequest request) {
		ConfigProdutoResponse configprodutoResponse = new ConfigProdutoResponse();

		try {
			InternalResultsResponse<ConfigProduto> internalResponse = getConfiguracaoBAC().deleteConfigProduto(request);
			ResponseHandler.handleOperationStatusAndMessages(configprodutoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configprodutoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configprodutoResponse;

	}

	// ===================================### CONFIGSMTP
	// ####======================================
	/**
	 * Refresh configsmtps.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the configsmtp response
	 */
	@RequestMapping(value = "/configSMTP/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ConfigSMTPResponse refreshConfigSMTPs(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ConfigSMTPResponse configsmtpResponse = new ConfigSMTPResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ConfigSMTP> internalResponse = getConfiguracaoBAC().refreshConfigSMTPs(request);
			ResponseHandler.handleOperationStatusAndMessages(configsmtpResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configsmtpResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configsmtpResponse;

	}

	/**
	 * Fetch configsmtp paged.
	 *
	 * @param request
	 *            the request
	 * @return the configsmtp response
	 */
	@RequestMapping(value = "/configSMTP/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ConfigSMTPResponse fetchConfigSMTPPaged(@RequestBody PagedInquiryRequest request) {
		ConfigSMTPResponse configsmtpResponse = new ConfigSMTPResponse();
		try {
			InternalResultsResponse<ConfigSMTP> internalResponse = getConfiguracaoBAC()
					.fetchConfigSMTPsByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(configsmtpResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configsmtpResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configsmtpResponse;
	}

	/**
	 * Insert configsmtp.
	 *
	 * @param request
	 *            the request
	 * @return the configsmtp response
	 */
	@RequestMapping(value = "/configSMTP/insert", method = RequestMethod.POST)
	@ResponseBody
	public ConfigSMTPResponse insertConfigSMTP(@RequestBody ConfigSMTPMaintenanceRequest request) {
		ConfigSMTPResponse configsmtpResponse = new ConfigSMTPResponse();
		try {
			InternalResultsResponse<ConfigSMTP> internalResponse = getConfiguracaoBAC().insertConfigSMTP(request);
			ResponseHandler.handleOperationStatusAndMessages(configsmtpResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configsmtpResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configsmtpResponse;
	}

	/**
	 * Update configsmtp.
	 *
	 * @param request
	 *            the request
	 * @return the configsmtp response
	 */
	@RequestMapping(value = "/configSMTP/update", method = RequestMethod.POST)
	@ResponseBody
	public ConfigSMTPResponse updateConfigSMTP(@RequestBody ConfigSMTPMaintenanceRequest request) {
		ConfigSMTPResponse configsmtpResponse = new ConfigSMTPResponse();
		try {
			InternalResultsResponse<ConfigSMTP> internalResponse = getConfiguracaoBAC().updateConfigSMTP(request);
			ResponseHandler.handleOperationStatusAndMessages(configsmtpResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configsmtpResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configsmtpResponse;
	}

	/**
	 * Delete configsmtp.
	 *
	 * @param request
	 *            the request
	 * @return the configsmtp response
	 */
	@RequestMapping(value = "/configSMTP/delete", method = RequestMethod.POST)
	@ResponseBody
	public ConfigSMTPResponse deleteConfigSMTP(@RequestBody ConfigSMTPMaintenanceRequest request) {
		ConfigSMTPResponse configsmtpResponse = new ConfigSMTPResponse();

		try {
			InternalResultsResponse<ConfigSMTP> internalResponse = getConfiguracaoBAC().deleteConfigSMTP(request);
			ResponseHandler.handleOperationStatusAndMessages(configsmtpResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configsmtpResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configsmtpResponse;

	}

	// ===================================### CONFIGURACAONFE
	// ####======================================
	/**
	 * Refresh configuracaonfes.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the configuracaonfe response
	 */
	@RequestMapping(value = "/configuracaoNFe/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ConfiguracaoNFeResponse refreshConfiguracaoNFes(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ConfiguracaoNFeResponse configuracaonfeResponse = new ConfiguracaoNFeResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ConfiguracaoNFe> internalResponse = getConfiguracaoBAC()
					.refreshConfiguracaoNFes(request);
			ResponseHandler.handleOperationStatusAndMessages(configuracaonfeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configuracaonfeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configuracaonfeResponse;

	}

	/**
	 * Fetch configuracaonfe paged.
	 *
	 * @param request
	 *            the request
	 * @return the configuracaonfe response
	 */
	@RequestMapping(value = "/configuracaoNFe/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ConfiguracaoNFeResponse fetchConfiguracaoNFePaged(@RequestBody PagedInquiryRequest request) {
		ConfiguracaoNFeResponse configuracaonfeResponse = new ConfiguracaoNFeResponse();
		try {
			InternalResultsResponse<ConfiguracaoNFe> internalResponse = getConfiguracaoBAC()
					.fetchConfiguracaoNFesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(configuracaonfeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configuracaonfeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configuracaonfeResponse;
	}

	/**
	 * Insert configuracaonfe.
	 *
	 * @param request
	 *            the request
	 * @return the configuracaonfe response
	 */
	@RequestMapping(value = "/configuracaoNFe/insert", method = RequestMethod.POST)
	@ResponseBody
	public ConfiguracaoNFeResponse insertConfiguracaoNFe(@RequestBody ConfiguracaoNFeMaintenanceRequest request) {
		ConfiguracaoNFeResponse configuracaonfeResponse = new ConfiguracaoNFeResponse();
		try {
			InternalResultsResponse<ConfiguracaoNFe> internalResponse = getConfiguracaoBAC()
					.insertConfiguracaoNFe(request);
			ResponseHandler.handleOperationStatusAndMessages(configuracaonfeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configuracaonfeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configuracaonfeResponse;
	}

	/**
	 * Update configuracaonfe.
	 *
	 * @param request
	 *            the request
	 * @return the configuracaonfe response
	 */
	@RequestMapping(value = "/configuracaoNFe/update", method = RequestMethod.POST)
	@ResponseBody
	public ConfiguracaoNFeResponse updateConfiguracaoNFe(@RequestBody ConfiguracaoNFeMaintenanceRequest request) {
		ConfiguracaoNFeResponse configuracaonfeResponse = new ConfiguracaoNFeResponse();
		try {
			InternalResultsResponse<ConfiguracaoNFe> internalResponse = getConfiguracaoBAC()
					.updateConfiguracaoNFe(request);
			ResponseHandler.handleOperationStatusAndMessages(configuracaonfeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configuracaonfeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configuracaonfeResponse;
	}

	/**
	 * Delete configuracaonfe.
	 *
	 * @param request
	 *            the request
	 * @return the configuracaonfe response
	 */
	@RequestMapping(value = "/configuracaoNFe/delete", method = RequestMethod.POST)
	@ResponseBody
	public ConfiguracaoNFeResponse deleteConfiguracaoNFe(@RequestBody ConfiguracaoNFeMaintenanceRequest request) {
		ConfiguracaoNFeResponse configuracaonfeResponse = new ConfiguracaoNFeResponse();

		try {
			InternalResultsResponse<ConfiguracaoNFe> internalResponse = getConfiguracaoBAC()
					.deleteConfiguracaoNFe(request);
			ResponseHandler.handleOperationStatusAndMessages(configuracaonfeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configuracaonfeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configuracaonfeResponse;

	}

	// ===================================### CONFIGVENDAS
	// ####======================================
	/**
	 * Refresh configvendass.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the configvendas response
	 */
	@RequestMapping(value = "/configVendas/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ConfigVendasResponse refreshConfigVendass(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ConfigVendasResponse configvendasResponse = new ConfigVendasResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<ConfigVendas> internalResponse = getConfiguracaoBAC().refreshConfigVendass(request);
			ResponseHandler.handleOperationStatusAndMessages(configvendasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configvendasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configvendasResponse;

	}

	/**
	 * Fetch configvendas paged.
	 *
	 * @param request
	 *            the request
	 * @return the configvendas response
	 */
	@RequestMapping(value = "/configVendas/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ConfigVendasResponse fetchConfigVendasPaged(@RequestBody PagedInquiryRequest request) {
		ConfigVendasResponse configvendasResponse = new ConfigVendasResponse();
		try {
			InternalResultsResponse<ConfigVendas> internalResponse = getConfiguracaoBAC()
					.fetchConfigVendassByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(configvendasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configvendasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configvendasResponse;
	}

	/**
	 * Insert configvendas.
	 *
	 * @param request
	 *            the request
	 * @return the configvendas response
	 */
	@RequestMapping(value = "/configVendas/insert", method = RequestMethod.POST)
	@ResponseBody
	public ConfigVendasResponse insertConfigVendas(@RequestBody ConfigVendasMaintenanceRequest request) {
		ConfigVendasResponse configvendasResponse = new ConfigVendasResponse();
		try {
			InternalResultsResponse<ConfigVendas> internalResponse = getConfiguracaoBAC().insertConfigVendas(request);
			ResponseHandler.handleOperationStatusAndMessages(configvendasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configvendasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configvendasResponse;
	}

	/**
	 * Update configvendas.
	 *
	 * @param request
	 *            the request
	 * @return the configvendas response
	 */
	@RequestMapping(value = "/configVendas/update", method = RequestMethod.POST)
	@ResponseBody
	public ConfigVendasResponse updateConfigVendas(@RequestBody ConfigVendasMaintenanceRequest request) {
		ConfigVendasResponse configvendasResponse = new ConfigVendasResponse();
		try {
			InternalResultsResponse<ConfigVendas> internalResponse = getConfiguracaoBAC().updateConfigVendas(request);
			ResponseHandler.handleOperationStatusAndMessages(configvendasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configvendasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configvendasResponse;
	}

	/**
	 * Delete configvendas.
	 *
	 * @param request
	 *            the request
	 * @return the configvendas response
	 */
	@RequestMapping(value = "/configVendas/delete", method = RequestMethod.POST)
	@ResponseBody
	public ConfigVendasResponse deleteConfigVendas(@RequestBody ConfigVendasMaintenanceRequest request) {
		ConfigVendasResponse configvendasResponse = new ConfigVendasResponse();

		try {
			InternalResultsResponse<ConfigVendas> internalResponse = getConfiguracaoBAC().deleteConfigVendas(request);
			ResponseHandler.handleOperationStatusAndMessages(configvendasResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, configvendasResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return configvendasResponse;

	}

}
