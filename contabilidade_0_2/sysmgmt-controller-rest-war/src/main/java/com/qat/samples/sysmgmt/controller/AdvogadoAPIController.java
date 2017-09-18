/** create by system gera-java version 1.0.0 09/05/2016 16:45 : 52*/

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
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.ProcessoCliente;
import com.qat.samples.sysmgmt.advocacia.request.CompromissoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.EspecialidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.response.ProcessoClientesResponse;
import com.qat.samples.sysmgmt.advocacia.response.ProcessoResponse;
import com.qat.samples.sysmgmt.arquivo.model.Arquivo;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoInquiryRequest;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoMaintenanceRequest;
import com.qat.samples.sysmgmt.arquivo.model.response.ArquivoResponse;
import com.qat.samples.sysmgmt.bac.Advogado.IAdvocaciaBAC;
import com.qat.samples.sysmgmt.clinica.model.Especialidade;
import com.qat.samples.sysmgmt.clinica.model.response.CompromissoResponse;
import com.qat.samples.sysmgmt.clinica.model.response.EspecialidadeResponse;
import com.qat.samples.sysmgmt.util.model.Compromisso;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class AdvogadoAPIController.
 */
@Controller
@RequestMapping("/advocacia/api")
public class AdvogadoAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.advogadocontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AdvogadoAPIController.class);

	/** The advogado bac. */
	private IAdvocaciaBAC advocaciaBAC; // injected by @Resource

	public IAdvocaciaBAC getAdvocaciaBAC() {
		return advocaciaBAC;
	}

	@Resource
	public void setAdvocaciaBAC(IAdvocaciaBAC advocaciaBAC) {
		this.advocaciaBAC = advocaciaBAC;
	}

	// ===================================### ESPECIALIDADE
	// ####======================================
	/**
	 * Refresh especialidades.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the especialidade response
	 */
	@RequestMapping(value = "/especialidade/refresh", method = RequestMethod.GET)
	@ResponseBody
	public EspecialidadeResponse refreshEspecialidades(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		EspecialidadeResponse especialidadeResponse = new EspecialidadeResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Especialidade> internalResponse = getAdvocaciaBAC().refreshEspecialidades(request);
			ResponseHandler.handleOperationStatusAndMessages(especialidadeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, especialidadeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return especialidadeResponse;

	}

	/**
	 * Fetch especialidade paged.
	 *
	 * @param request
	 *            the request
	 * @return the especialidade response
	 */
	@RequestMapping(value = "/especialidade/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public EspecialidadeResponse fetchEspecialidadePaged(@RequestBody PagedInquiryRequest request) {
		EspecialidadeResponse especialidadeResponse = new EspecialidadeResponse();
		try {
			InternalResultsResponse<Especialidade> internalResponse = getAdvocaciaBAC()
					.fetchEspecialidadesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(especialidadeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, especialidadeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return especialidadeResponse;
	}

	/**
	 * Insert especialidade.
	 *
	 * @param request
	 *            the request
	 * @return the especialidade response
	 */
	@RequestMapping(value = "/especialidade/insert", method = RequestMethod.POST)
	@ResponseBody
	public EspecialidadeResponse insertEspecialidade(@RequestBody EspecialidadeMaintenanceRequest request) {
		EspecialidadeResponse especialidadeResponse = new EspecialidadeResponse();
		try {
			InternalResultsResponse<Especialidade> internalResponse = getAdvocaciaBAC().insertEspecialidade(request);
			ResponseHandler.handleOperationStatusAndMessages(especialidadeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, especialidadeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return especialidadeResponse;
	}

	/**
	 * Update especialidade.
	 *
	 * @param request
	 *            the request
	 * @return the especialidade response
	 */
	@RequestMapping(value = "/especialidade/update", method = RequestMethod.POST)
	@ResponseBody
	public EspecialidadeResponse updateEspecialidade(@RequestBody EspecialidadeMaintenanceRequest request) {
		EspecialidadeResponse especialidadeResponse = new EspecialidadeResponse();
		try {
			InternalResultsResponse<Especialidade> internalResponse = getAdvocaciaBAC().updateEspecialidade(request);
			ResponseHandler.handleOperationStatusAndMessages(especialidadeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, especialidadeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return especialidadeResponse;
	}

	/**
	 * Delete especialidade.
	 *
	 * @param request
	 *            the request
	 * @return the especialidade response
	 */
	@RequestMapping(value = "/especialidade/delete", method = RequestMethod.POST)
	@ResponseBody
	public EspecialidadeResponse deleteEspecialidade(@RequestBody EspecialidadeMaintenanceRequest request) {
		EspecialidadeResponse especialidadeResponse = new EspecialidadeResponse();

		try {
			InternalResultsResponse<Especialidade> internalResponse = getAdvocaciaBAC().deleteEspecialidade(request);
			ResponseHandler.handleOperationStatusAndMessages(especialidadeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, especialidadeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return especialidadeResponse;

	}

	// ===================================### COMPROMISSO
	// ####======================================
	/**
	 * Refresh compromissos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the compromisso response
	 */
	@RequestMapping(value = "/compromisso/refresh", method = RequestMethod.GET)
	@ResponseBody
	public CompromissoResponse refreshCompromissos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		CompromissoResponse compromissoResponse = new CompromissoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Compromisso> internalResponse = getAdvocaciaBAC().refreshCompromissos(request);
			ResponseHandler.handleOperationStatusAndMessages(compromissoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, compromissoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return compromissoResponse;

	}

	/**
	 * Fetch compromisso paged.
	 *
	 * @param request
	 *            the request
	 * @return the compromisso response
	 */
	@RequestMapping(value = "/compromisso/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public CompromissoResponse fetchCompromissoPaged(@RequestBody PagedInquiryRequest request) {
		CompromissoResponse compromissoResponse = new CompromissoResponse();
		try {
			InternalResultsResponse<Compromisso> internalResponse = getAdvocaciaBAC()
					.fetchCompromissosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(compromissoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, compromissoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return compromissoResponse;
	}

	/**
	 * Insert compromisso.
	 *
	 * @param request
	 *            the request
	 * @return the compromisso response
	 */
	@RequestMapping(value = "/compromisso/insert", method = RequestMethod.POST)
	@ResponseBody
	public CompromissoResponse insertCompromisso(@RequestBody CompromissoMaintenanceRequest request) {
		CompromissoResponse compromissoResponse = new CompromissoResponse();
		try {
			InternalResultsResponse<Compromisso> internalResponse = getAdvocaciaBAC().insertCompromisso(request);
			ResponseHandler.handleOperationStatusAndMessages(compromissoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, compromissoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return compromissoResponse;
	}

	/**
	 * Update compromisso.
	 *
	 * @param request
	 *            the request
	 * @return the compromisso response
	 */
	@RequestMapping(value = "/compromisso/update", method = RequestMethod.POST)
	@ResponseBody
	public CompromissoResponse updateCompromisso(@RequestBody CompromissoMaintenanceRequest request) {
		CompromissoResponse compromissoResponse = new CompromissoResponse();
		try {
			InternalResultsResponse<Compromisso> internalResponse = getAdvocaciaBAC().updateCompromisso(request);
			ResponseHandler.handleOperationStatusAndMessages(compromissoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, compromissoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return compromissoResponse;
	}

	/**
	 * Delete compromisso.
	 *
	 * @param request
	 *            the request
	 * @return the compromisso response
	 */
	@RequestMapping(value = "/compromisso/delete", method = RequestMethod.POST)
	@ResponseBody
	public CompromissoResponse deleteCompromisso(@RequestBody CompromissoMaintenanceRequest request) {
		CompromissoResponse compromissoResponse = new CompromissoResponse();

		try {
			InternalResultsResponse<Compromisso> internalResponse = getAdvocaciaBAC().deleteCompromisso(request);
			ResponseHandler.handleOperationStatusAndMessages(compromissoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, compromissoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return compromissoResponse;

	}

	// ===================================### PROCESSO
	// ####======================================
	/**
	 * Refresh processos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the processo response
	 */
	@RequestMapping(value = "/processo/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ProcessoResponse refreshProcessos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ProcessoResponse processoResponse = new ProcessoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Processo> internalResponse = getAdvocaciaBAC().refreshProcessos(request);
			ResponseHandler.handleOperationStatusAndMessages(processoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, processoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return processoResponse;

	}

	/**
	 * Fetch processo paged.
	 *
	 * @param request
	 *            the request
	 * @return the processo response
	 */
	@RequestMapping(value = "/processo/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ProcessoResponse fetchProcessoPaged(@RequestBody ProcessoInquiryRequest request) {
		ProcessoResponse processoResponse = new ProcessoResponse();
		try {
			InternalResultsResponse<Processo> internalResponse = getAdvocaciaBAC().fetchProcessosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(processoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, processoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return processoResponse;
	}

	/**
	 * Insert processo.
	 *
	 * @param request
	 *            the request
	 * @return the processo response
	 */
	@RequestMapping(value = "/processo/insert", method = RequestMethod.POST)
	@ResponseBody
	public ProcessoResponse insertProcesso(@RequestBody ProcessoMaintenanceRequest request) {
		ProcessoResponse processoResponse = new ProcessoResponse();
		try {
			InternalResultsResponse<Processo> internalResponse = getAdvocaciaBAC().insertProcesso(request);
			ResponseHandler.handleOperationStatusAndMessages(processoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, processoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return processoResponse;
	}

	/**
	 * Update processo.
	 *
	 * @param request
	 *            the request
	 * @return the processo response
	 */
	@RequestMapping(value = "/processo/update", method = RequestMethod.POST)
	@ResponseBody
	public ProcessoResponse updateProcesso(@RequestBody ProcessoMaintenanceRequest request) {
		ProcessoResponse processoResponse = new ProcessoResponse();
		try {
			InternalResultsResponse<Processo> internalResponse = getAdvocaciaBAC().updateProcesso(request);
			ResponseHandler.handleOperationStatusAndMessages(processoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, processoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return processoResponse;
	}

	/**
	 * Delete processo.
	 *
	 * @param request
	 *            the request
	 * @return the processo response
	 */
	@RequestMapping(value = "/processo/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessoResponse deleteProcesso(@RequestBody ProcessoMaintenanceRequest request) {
		ProcessoResponse processoResponse = new ProcessoResponse();

		try {
			InternalResultsResponse<Processo> internalResponse = getAdvocaciaBAC().deleteProcesso(request);
			ResponseHandler.handleOperationStatusAndMessages(processoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, processoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return processoResponse;

	}

	// ===================================### ARQUIVO
	// ####======================================
	/**
	 * Refresh arquivos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the arquivo response
	 */
	@RequestMapping(value = "/arquivo/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ArquivoResponse refreshArquivos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ArquivoResponse arquivoResponse = new ArquivoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Arquivo> internalResponse = getAdvocaciaBAC().refreshArquivos(request);
			ResponseHandler.handleOperationStatusAndMessages(arquivoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, arquivoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return arquivoResponse;

	}

	/**
	 * Fetch arquivo paged.
	 *
	 * @param request
	 *            the request
	 * @return the arquivo response
	 */
	@RequestMapping(value = "/arquivo/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ArquivoResponse fetchArquivoPaged(@RequestBody ArquivoInquiryRequest request) {
		ArquivoResponse arquivoResponse = new ArquivoResponse();
		try {
			InternalResultsResponse<Arquivo> internalResponse = getAdvocaciaBAC().fetchArquivosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(arquivoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, arquivoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return arquivoResponse;
	}

	/**
	 * Insert arquivo.
	 *
	 * @param request
	 *            the request
	 * @return the arquivo response
	 */
	@RequestMapping(value = "/arquivo/insert", method = RequestMethod.POST)
	@ResponseBody
	public ArquivoResponse insertArquivo(@RequestBody ArquivoMaintenanceRequest request) {
		ArquivoResponse arquivoResponse = new ArquivoResponse();
		try {
			InternalResultsResponse<Arquivo> internalResponse = getAdvocaciaBAC().insertArquivo(request);
			ResponseHandler.handleOperationStatusAndMessages(arquivoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, arquivoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return arquivoResponse;
	}

	/**
	 * Update arquivo.
	 *
	 * @param request
	 *            the request
	 * @return the arquivo response
	 */
	@RequestMapping(value = "/arquivo/update", method = RequestMethod.POST)
	@ResponseBody
	public ArquivoResponse updateArquivo(@RequestBody ArquivoMaintenanceRequest request) {
		ArquivoResponse arquivoResponse = new ArquivoResponse();
		try {
			InternalResultsResponse<Arquivo> internalResponse = getAdvocaciaBAC().updateArquivo(request);
			ResponseHandler.handleOperationStatusAndMessages(arquivoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, arquivoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return arquivoResponse;
	}

	/**
	 * Delete arquivo.
	 *
	 * @param request
	 *            the request
	 * @return the arquivo response
	 */
	@RequestMapping(value = "/arquivo/delete", method = RequestMethod.POST)
	@ResponseBody
	public ArquivoResponse deleteArquivo(@RequestBody ArquivoMaintenanceRequest request) {
		ArquivoResponse arquivoResponse = new ArquivoResponse();

		try {
			InternalResultsResponse<Arquivo> internalResponse = getAdvocaciaBAC().deleteArquivo(request);
			ResponseHandler.handleOperationStatusAndMessages(arquivoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, arquivoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return arquivoResponse;

	}

	// ===================================### PROCESSOCLIENTE
	// ####======================================

	/**
	 * Fetch processocliente paged.
	 *
	 * @param request
	 *            the request
	 * @return the processocliente response
	 */
	@RequestMapping(value = "/processoCliente/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ProcessoClientesResponse fetchProcessoClientePaged(@RequestBody PagedInquiryRequest request) {
		ProcessoClientesResponse processoclienteResponse = new ProcessoClientesResponse();
		try {
			InternalResultsResponse<ProcessoCliente> internalResponse = getAdvocaciaBAC()
					.fetchProcessoClientesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(processoclienteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, processoclienteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return processoclienteResponse;
	}

}
