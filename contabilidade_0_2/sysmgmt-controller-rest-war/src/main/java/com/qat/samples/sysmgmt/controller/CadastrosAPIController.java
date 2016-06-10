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
import com.qat.samples.sysmgmt.bac.Cadastros.ICadastrosBAC;
import com.qat.samples.sysmgmt.convenio.model.Convenio;
import com.qat.samples.sysmgmt.dp.model.response.ConvenioResponse;
import com.qat.samples.sysmgmt.entidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.request.EstadoMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.response.EstadoResponse;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.Tarefa;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.ConvenioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.util.model.response.TarefaResponse;

/**
 * The Class CadastrosAPIController.
 */
@Controller
@RequestMapping("/cadastros/api")
public class CadastrosAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.cadastroscontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CadastrosAPIController.class);

	/** The cadastros bac. */
	private ICadastrosBAC cadastrosBAC; // injected by @Resource

	/**
	 * Gets the cadastros bac.
	 *
	 * @return the cadastros bac
	 */
	public ICadastrosBAC getCadastrosBAC() {
		return cadastrosBAC;
	}

	/**
	 * Sets the cadastros bac.
	 *
	 * @param cadastrosBAC
	 *            the new cadastros bac
	 */
	@Resource
	public void setCadastrosBAC(ICadastrosBAC cadastrosBAC) {
		this.cadastrosBAC = cadastrosBAC;
	}

	// ===================================### CONVENIO
	// ####======================================
	/**
	 * Refresh convenios.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the convenio response
	 */
	@RequestMapping(value = "/convenio/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ConvenioResponse refreshConvenios(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ConvenioResponse convenioResponse = new ConvenioResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().refreshConvenios(request);
			ResponseHandler.handleOperationStatusAndMessages(convenioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, convenioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return convenioResponse;

	}

	/**
	 * Fetch convenio paged.
	 *
	 * @param request
	 *            the request
	 * @return the convenio response
	 */
	@RequestMapping(value = "/convenio/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ConvenioResponse fetchConvenioPaged(@RequestBody ConvenioInquiryRequest request) {
		ConvenioResponse convenioResponse = new ConvenioResponse();
		try {
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().fetchConveniosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(convenioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, convenioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return convenioResponse;
	}

	/**
	 * Insert convenio.
	 *
	 * @param request
	 *            the request
	 * @return the convenio response
	 */
	@RequestMapping(value = "/convenio/insert", method = RequestMethod.POST)
	@ResponseBody
	public ConvenioResponse insertConvenio(@RequestBody ConvenioMaintenanceRequest request) {
		ConvenioResponse convenioResponse = new ConvenioResponse();
		try {
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().insertConvenio(request);
			ResponseHandler.handleOperationStatusAndMessages(convenioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, convenioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return convenioResponse;
	}

	/**
	 * Update convenio.
	 *
	 * @param request
	 *            the request
	 * @return the convenio response
	 */
	@RequestMapping(value = "/convenio/update", method = RequestMethod.POST)
	@ResponseBody
	public ConvenioResponse updateConvenio(@RequestBody ConvenioMaintenanceRequest request) {
		ConvenioResponse convenioResponse = new ConvenioResponse();
		try {
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().updateConvenio(request);
			ResponseHandler.handleOperationStatusAndMessages(convenioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, convenioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return convenioResponse;
	}

	/**
	 * Delete convenio.
	 *
	 * @param request
	 *            the request
	 * @return the convenio response
	 */
	@RequestMapping(value = "/convenio/delete", method = RequestMethod.POST)
	@ResponseBody
	public ConvenioResponse deleteConvenio(@RequestBody ConvenioMaintenanceRequest request) {
		ConvenioResponse convenioResponse = new ConvenioResponse();

		try {
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().deleteConvenio(request);
			ResponseHandler.handleOperationStatusAndMessages(convenioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, convenioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return convenioResponse;

	}

	// ===================================### CIDADE
	// ####======================================
	/**
	 * Refresh cidades.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the cidade response
	 */
	@RequestMapping(value = "/cidade/refresh", method = RequestMethod.GET)
	@ResponseBody
	public CidadeResponse refreshCidades(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		CidadeResponse cidadeResponse = new CidadeResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().refreshCidades(request);
			ResponseHandler.handleOperationStatusAndMessages(cidadeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cidadeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cidadeResponse;

	}

	/**
	 * Fetch cidade paged.
	 *
	 * @param request
	 *            the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/cidade/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public CidadeResponse fetchCidadePaged(@RequestBody CidadeInquiryRequest request) {
		CidadeResponse cidadeResponse = new CidadeResponse();
		try {
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().fetchCidadesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(cidadeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cidadeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cidadeResponse;
	}

	/**
	 * Insert cidade.
	 *
	 * @param request
	 *            the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/cidade/insert", method = RequestMethod.POST)
	@ResponseBody
	public CidadeResponse insertCidade(@RequestBody CidadeMaintenanceRequest request) {
		CidadeResponse cidadeResponse = new CidadeResponse();
		try {
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().insertCidade(request);
			ResponseHandler.handleOperationStatusAndMessages(cidadeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cidadeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cidadeResponse;
	}

	/**
	 * Update cidade.
	 *
	 * @param request
	 *            the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/cidade/update", method = RequestMethod.POST)
	@ResponseBody
	public CidadeResponse updateCidade(@RequestBody CidadeMaintenanceRequest request) {
		CidadeResponse cidadeResponse = new CidadeResponse();
		try {
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().updateCidade(request);
			ResponseHandler.handleOperationStatusAndMessages(cidadeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cidadeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cidadeResponse;
	}

	/**
	 * Delete cidade.
	 *
	 * @param request
	 *            the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/cidade/delete", method = RequestMethod.POST)
	@ResponseBody
	public CidadeResponse deleteCidade(@RequestBody CidadeMaintenanceRequest request) {
		CidadeResponse cidadeResponse = new CidadeResponse();

		try {
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().deleteCidade(request);
			ResponseHandler.handleOperationStatusAndMessages(cidadeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cidadeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cidadeResponse;

	}

	// ===================================### ESTADO
	// ####======================================
	/**
	 * Refresh estados.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the estado response
	 */
	@RequestMapping(value = "/estado/refresh", method = RequestMethod.GET)
	@ResponseBody
	public EstadoResponse refreshEstados(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		EstadoResponse estadoResponse = new EstadoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().refreshEstados(request);
			ResponseHandler.handleOperationStatusAndMessages(estadoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, estadoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return estadoResponse;

	}

	/**
	 * Fetch estado paged.
	 *
	 * @param request
	 *            the request
	 * @return the estado response
	 */
	@RequestMapping(value = "/estado/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public EstadoResponse fetchEstadoPaged(@RequestBody EstadoInquiryRequest request) {
		EstadoResponse estadoResponse = new EstadoResponse();
		try {
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().fetchEstadosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(estadoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, estadoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return estadoResponse;
	}

	/**
	 * Insert estado.
	 *
	 * @param request
	 *            the request
	 * @return the estado response
	 */
	@RequestMapping(value = "/estado/insert", method = RequestMethod.POST)
	@ResponseBody
	public EstadoResponse insertEstado(@RequestBody EstadoMaintenanceRequest request) {
		EstadoResponse estadoResponse = new EstadoResponse();
		try {
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().insertEstado(request);
			ResponseHandler.handleOperationStatusAndMessages(estadoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, estadoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return estadoResponse;
	}

	/**
	 * Update estado.
	 *
	 * @param request
	 *            the request
	 * @return the estado response
	 */
	@RequestMapping(value = "/estado/update", method = RequestMethod.POST)
	@ResponseBody
	public EstadoResponse updateEstado(@RequestBody EstadoMaintenanceRequest request) {
		EstadoResponse estadoResponse = new EstadoResponse();
		try {
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().updateEstado(request);
			ResponseHandler.handleOperationStatusAndMessages(estadoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, estadoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return estadoResponse;
	}

	/**
	 * Delete estado.
	 *
	 * @param request
	 *            the request
	 * @return the estado response
	 */
	@RequestMapping(value = "/estado/delete", method = RequestMethod.POST)
	@ResponseBody
	public EstadoResponse deleteEstado(@RequestBody EstadoMaintenanceRequest request) {
		EstadoResponse estadoResponse = new EstadoResponse();

		try {
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().deleteEstado(request);
			ResponseHandler.handleOperationStatusAndMessages(estadoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, estadoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return estadoResponse;

	}

	// ===================================### TAREFA
	// ####======================================
	/**
	 * Refresh tarefas.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the tarefa response
	 */
	@RequestMapping(value = "/tarefa/refresh", method = RequestMethod.GET)
	@ResponseBody
	public TarefaResponse refreshTarefas(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		TarefaResponse tarefaResponse = new TarefaResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().refreshTarefas(request);
			ResponseHandler.handleOperationStatusAndMessages(tarefaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, tarefaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return tarefaResponse;

	}

	/**
	 * Fetch tarefa paged.
	 *
	 * @param request
	 *            the request
	 * @return the tarefa response
	 */
	@RequestMapping(value = "/tarefa/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public TarefaResponse fetchTarefaPaged(@RequestBody TarefaInquiryRequest request) {
		TarefaResponse tarefaResponse = new TarefaResponse();
		try {
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().fetchTarefasByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(tarefaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, tarefaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return tarefaResponse;
	}

	/**
	 * Insert tarefa.
	 *
	 * @param request
	 *            the request
	 * @return the tarefa response
	 */
	@RequestMapping(value = "/tarefa/insert", method = RequestMethod.POST)
	@ResponseBody
	public TarefaResponse insertTarefa(@RequestBody TarefaMaintenanceRequest request) {
		TarefaResponse tarefaResponse = new TarefaResponse();
		try {
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().insertTarefa(request);
			ResponseHandler.handleOperationStatusAndMessages(tarefaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, tarefaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return tarefaResponse;
	}

	/**
	 * Update tarefa.
	 *
	 * @param request
	 *            the request
	 * @return the tarefa response
	 */
	@RequestMapping(value = "/tarefa/update", method = RequestMethod.POST)
	@ResponseBody
	public TarefaResponse updateTarefa(@RequestBody TarefaMaintenanceRequest request) {
		TarefaResponse tarefaResponse = new TarefaResponse();
		try {
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().updateTarefa(request);
			ResponseHandler.handleOperationStatusAndMessages(tarefaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, tarefaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return tarefaResponse;
	}

	/**
	 * Delete tarefa.
	 *
	 * @param request
	 *            the request
	 * @return the tarefa response
	 */
	@RequestMapping(value = "/tarefa/delete", method = RequestMethod.POST)
	@ResponseBody
	public TarefaResponse deleteTarefa(@RequestBody TarefaMaintenanceRequest request) {
		TarefaResponse tarefaResponse = new TarefaResponse();

		try {
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().deleteTarefa(request);
			ResponseHandler.handleOperationStatusAndMessages(tarefaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, tarefaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return tarefaResponse;

	}

}
