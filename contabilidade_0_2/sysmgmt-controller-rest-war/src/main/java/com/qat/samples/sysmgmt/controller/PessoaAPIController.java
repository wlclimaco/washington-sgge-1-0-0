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
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.response.AdvogadoResponse;
import com.qat.samples.sysmgmt.bac.Pessoa.IPessoaBAC;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.response.MedicoResponse;
import com.qat.samples.sysmgmt.clinica.model.response.PacienteResponse;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.response.InquilinoResponse;
import com.qat.samples.sysmgmt.condominio.model.response.SindicoResponse;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.response.FuncionarioResponse;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;
import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.response.ClienteResponse;
import com.qat.samples.sysmgmt.pessoa.model.response.FornecedorResponse;
import com.qat.samples.sysmgmt.pessoa.model.response.TransportadorResponse;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class PessoaAPIController.
 */
@Controller
@RequestMapping("/pessoa/api")
public class PessoaAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.pessoacontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PessoaAPIController.class);

	/** The pessoa bac. */
	private IPessoaBAC pessoaBAC; // injected by @Resource

	/**
	 * Gets the pessoa bac.
	 *
	 * @return the pessoa bac
	 */
	public IPessoaBAC getPessoaBAC() {
		return pessoaBAC;
	}

	/**
	 * Sets the pessoa bac.
	 *
	 * @param pessoaBAC
	 *            the new pessoa bac
	 */
	@Resource
	public void setPessoaBAC(IPessoaBAC pessoaBAC) {
		this.pessoaBAC = pessoaBAC;
	}

	// ===================================### ADVOGADO
	// ####======================================
	/**
	 * Refresh advogados.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the advogado response
	 */
	@RequestMapping(value = "/advogado/refresh", method = RequestMethod.GET)
	@ResponseBody
	public AdvogadoResponse refreshAdvogados(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		AdvogadoResponse advogadoResponse = new AdvogadoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Advogado> internalResponse = getPessoaBAC().refreshAdvogados(request);
			ResponseHandler.handleOperationStatusAndMessages(advogadoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, advogadoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return advogadoResponse;

	}

	/**
	 * Fetch advogado paged.
	 *
	 * @param request
	 *            the request
	 * @return the advogado response
	 */
	@RequestMapping(value = "/advogado/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public AdvogadoResponse fetchAdvogadoPaged(@RequestBody AdvogadoInquiryRequest request) {
		AdvogadoResponse advogadoResponse = new AdvogadoResponse();
		try {
			InternalResultsResponse<Advogado> internalResponse = getPessoaBAC().fetchAdvogadosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(advogadoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, advogadoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return advogadoResponse;
	}

	/**
	 * Insert advogado.
	 *
	 * @param request
	 *            the request
	 * @return the advogado response
	 */
	@RequestMapping(value = "/advogado/insert", method = RequestMethod.POST)
	@ResponseBody
	public AdvogadoResponse insertAdvogado(@RequestBody AdvogadoMaintenanceRequest request) {
		AdvogadoResponse advogadoResponse = new AdvogadoResponse();
		try {
			InternalResultsResponse<Advogado> internalResponse = getPessoaBAC().insertAdvogado(request);
			ResponseHandler.handleOperationStatusAndMessages(advogadoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, advogadoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return advogadoResponse;
	}

	/**
	 * Update advogado.
	 *
	 * @param request
	 *            the request
	 * @return the advogado response
	 */
	@RequestMapping(value = "/advogado/update", method = RequestMethod.POST)
	@ResponseBody
	public AdvogadoResponse updateAdvogado(@RequestBody AdvogadoMaintenanceRequest request) {
		AdvogadoResponse advogadoResponse = new AdvogadoResponse();
		try {
			InternalResultsResponse<Advogado> internalResponse = getPessoaBAC().updateAdvogado(request);
			ResponseHandler.handleOperationStatusAndMessages(advogadoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, advogadoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return advogadoResponse;
	}

	/**
	 * Delete advogado.
	 *
	 * @param request
	 *            the request
	 * @return the advogado response
	 */
	@RequestMapping(value = "/advogado/delete", method = RequestMethod.POST)
	@ResponseBody
	public AdvogadoResponse deleteAdvogado(@RequestBody AdvogadoMaintenanceRequest request) {
		AdvogadoResponse advogadoResponse = new AdvogadoResponse();

		try {
			InternalResultsResponse<Advogado> internalResponse = getPessoaBAC().deleteAdvogado(request);
			ResponseHandler.handleOperationStatusAndMessages(advogadoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, advogadoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return advogadoResponse;

	}

	// ===================================### CLIENTE
	// ####======================================
	/**
	 * Refresh clientes.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the cliente response
	 */
	@RequestMapping(value = "/cliente/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ClienteResponse refreshClientes(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ClienteResponse clienteResponse = new ClienteResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Cliente> internalResponse = getPessoaBAC().refreshClientes(request);
			ResponseHandler.handleOperationStatusAndMessages(clienteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, clienteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return clienteResponse;

	}

	/**
	 * Fetch cliente paged.
	 *
	 * @param request
	 *            the request
	 * @return the cliente response
	 */
	@RequestMapping(value = "/cliente/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ClienteResponse fetchClientePaged(@RequestBody ClienteInquiryRequest request) {
		ClienteResponse clienteResponse = new ClienteResponse();
		try {
			InternalResultsResponse<Cliente> internalResponse = getPessoaBAC().fetchClientesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(clienteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, clienteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return clienteResponse;
	}

	/**
	 * Insert cliente.
	 *
	 * @param request
	 *            the request
	 * @return the cliente response
	 */
	@RequestMapping(value = "/cliente/insert", method = RequestMethod.POST)
	@ResponseBody
	public ClienteResponse insertCliente(@RequestBody ClienteMaintenanceRequest request) {
		ClienteResponse clienteResponse = new ClienteResponse();
		try {
			InternalResultsResponse<Cliente> internalResponse = getPessoaBAC().insertCliente(request);
			ResponseHandler.handleOperationStatusAndMessages(clienteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, clienteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return clienteResponse;
	}

	/**
	 * Update cliente.
	 *
	 * @param request
	 *            the request
	 * @return the cliente response
	 */
	@RequestMapping(value = "/cliente/update", method = RequestMethod.POST)
	@ResponseBody
	public ClienteResponse updateCliente(@RequestBody ClienteMaintenanceRequest request) {
		ClienteResponse clienteResponse = new ClienteResponse();
		try {
			InternalResultsResponse<Cliente> internalResponse = getPessoaBAC().updateCliente(request);
			ResponseHandler.handleOperationStatusAndMessages(clienteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, clienteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return clienteResponse;
	}

	/**
	 * Delete cliente.
	 *
	 * @param request
	 *            the request
	 * @return the cliente response
	 */
	@RequestMapping(value = "/cliente/delete", method = RequestMethod.POST)
	@ResponseBody
	public ClienteResponse deleteCliente(@RequestBody ClienteMaintenanceRequest request) {
		ClienteResponse clienteResponse = new ClienteResponse();

		try {
			InternalResultsResponse<Cliente> internalResponse = getPessoaBAC().deleteCliente(request);
			ResponseHandler.handleOperationStatusAndMessages(clienteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, clienteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return clienteResponse;

	}

	// ===================================### FORNECEDOR
	// ####======================================
	/**
	 * Refresh fornecedors.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the fornecedor response
	 */
	@RequestMapping(value = "/fornecedor/refresh", method = RequestMethod.GET)
	@ResponseBody
	public FornecedorResponse refreshFornecedors(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		FornecedorResponse fornecedorResponse = new FornecedorResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Fornecedor> internalResponse = getPessoaBAC().refreshFornecedors(request);
			ResponseHandler.handleOperationStatusAndMessages(fornecedorResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, fornecedorResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return fornecedorResponse;

	}

	/**
	 * Fetch fornecedor paged.
	 *
	 * @param request
	 *            the request
	 * @return the fornecedor response
	 */
	@RequestMapping(value = "/fornecedor/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public FornecedorResponse fetchFornecedorPaged(@RequestBody FornecedorInquiryRequest request) {
		FornecedorResponse fornecedorResponse = new FornecedorResponse();
		try {
			InternalResultsResponse<Fornecedor> internalResponse = getPessoaBAC().fetchFornecedorsByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(fornecedorResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, fornecedorResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return fornecedorResponse;
	}

	/**
	 * Insert fornecedor.
	 *
	 * @param request
	 *            the request
	 * @return the fornecedor response
	 */
	@RequestMapping(value = "/fornecedor/insert", method = RequestMethod.POST)
	@ResponseBody
	public FornecedorResponse insertFornecedor(@RequestBody FornecedorMaintenanceRequest request) {
		FornecedorResponse fornecedorResponse = new FornecedorResponse();
		try {
			InternalResultsResponse<Fornecedor> internalResponse = getPessoaBAC().insertFornecedor(request);
			ResponseHandler.handleOperationStatusAndMessages(fornecedorResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, fornecedorResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return fornecedorResponse;
	}

	/**
	 * Update fornecedor.
	 *
	 * @param request
	 *            the request
	 * @return the fornecedor response
	 */
	@RequestMapping(value = "/fornecedor/update", method = RequestMethod.POST)
	@ResponseBody
	public FornecedorResponse updateFornecedor(@RequestBody FornecedorMaintenanceRequest request) {
		FornecedorResponse fornecedorResponse = new FornecedorResponse();
		try {
			InternalResultsResponse<Fornecedor> internalResponse = getPessoaBAC().updateFornecedor(request);
			ResponseHandler.handleOperationStatusAndMessages(fornecedorResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, fornecedorResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return fornecedorResponse;
	}

	/**
	 * Delete fornecedor.
	 *
	 * @param request
	 *            the request
	 * @return the fornecedor response
	 */
	@RequestMapping(value = "/fornecedor/delete", method = RequestMethod.POST)
	@ResponseBody
	public FornecedorResponse deleteFornecedor(@RequestBody FornecedorMaintenanceRequest request) {
		FornecedorResponse fornecedorResponse = new FornecedorResponse();

		try {
			InternalResultsResponse<Fornecedor> internalResponse = getPessoaBAC().deleteFornecedor(request);
			ResponseHandler.handleOperationStatusAndMessages(fornecedorResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, fornecedorResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return fornecedorResponse;

	}

	// ===================================### TRANSPORTADOR
	// ####======================================
	/**
	 * Refresh transportadors.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the transportador response
	 */
	@RequestMapping(value = "/transportador/refresh", method = RequestMethod.GET)
	@ResponseBody
	public TransportadorResponse refreshTransportadors(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		TransportadorResponse transportadorResponse = new TransportadorResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Transportador> internalResponse = getPessoaBAC().refreshTransportadors(request);
			ResponseHandler.handleOperationStatusAndMessages(transportadorResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, transportadorResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return transportadorResponse;

	}

	/**
	 * Fetch transportador paged.
	 *
	 * @param request
	 *            the request
	 * @return the transportador response
	 */
	@RequestMapping(value = "/transportador/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public TransportadorResponse fetchTransportadorPaged(@RequestBody TransportadorInquiryRequest request) {
		TransportadorResponse transportadorResponse = new TransportadorResponse();
		try {
			InternalResultsResponse<Transportador> internalResponse = getPessoaBAC()
					.fetchTransportadorsByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(transportadorResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, transportadorResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return transportadorResponse;
	}

	/**
	 * Insert transportador.
	 *
	 * @param request
	 *            the request
	 * @return the transportador response
	 */
	@RequestMapping(value = "/transportador/insert", method = RequestMethod.POST)
	@ResponseBody
	public TransportadorResponse insertTransportador(@RequestBody TransportadorMaintenanceRequest request) {
		TransportadorResponse transportadorResponse = new TransportadorResponse();
		try {
			InternalResultsResponse<Transportador> internalResponse = getPessoaBAC().insertTransportador(request);
			ResponseHandler.handleOperationStatusAndMessages(transportadorResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, transportadorResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return transportadorResponse;
	}

	/**
	 * Update transportador.
	 *
	 * @param request
	 *            the request
	 * @return the transportador response
	 */
	@RequestMapping(value = "/transportador/update", method = RequestMethod.POST)
	@ResponseBody
	public TransportadorResponse updateTransportador(@RequestBody TransportadorMaintenanceRequest request) {
		TransportadorResponse transportadorResponse = new TransportadorResponse();
		try {
			InternalResultsResponse<Transportador> internalResponse = getPessoaBAC().updateTransportador(request);
			ResponseHandler.handleOperationStatusAndMessages(transportadorResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, transportadorResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return transportadorResponse;
	}

	/**
	 * Delete transportador.
	 *
	 * @param request
	 *            the request
	 * @return the transportador response
	 */
	@RequestMapping(value = "/transportador/delete", method = RequestMethod.POST)
	@ResponseBody
	public TransportadorResponse deleteTransportador(@RequestBody TransportadorMaintenanceRequest request) {
		TransportadorResponse transportadorResponse = new TransportadorResponse();

		try {
			InternalResultsResponse<Transportador> internalResponse = getPessoaBAC().deleteTransportador(request);
			ResponseHandler.handleOperationStatusAndMessages(transportadorResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, transportadorResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return transportadorResponse;

	}

	// ===================================### MEDICO
	// ####======================================
	/**
	 * Refresh medicos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the medico response
	 */
	@RequestMapping(value = "/medico/refresh", method = RequestMethod.GET)
	@ResponseBody
	public MedicoResponse refreshMedicos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		MedicoResponse medicoResponse = new MedicoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Medico> internalResponse = getPessoaBAC().refreshMedicos(request);
			ResponseHandler.handleOperationStatusAndMessages(medicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, medicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return medicoResponse;

	}

	/**
	 * Fetch medico paged.
	 *
	 * @param request
	 *            the request
	 * @return the medico response
	 */
	@RequestMapping(value = "/medico/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public MedicoResponse fetchMedicoPaged(@RequestBody MedicoInquiryRequest request) {
		MedicoResponse medicoResponse = new MedicoResponse();
		try {
			InternalResultsResponse<Medico> internalResponse = getPessoaBAC().fetchMedicosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(medicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, medicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return medicoResponse;
	}

	/**
	 * Insert medico.
	 *
	 * @param request
	 *            the request
	 * @return the medico response
	 */
	@RequestMapping(value = "/medico/insert", method = RequestMethod.POST)
	@ResponseBody
	public MedicoResponse insertMedico(@RequestBody MedicoMaintenanceRequest request) {
		MedicoResponse medicoResponse = new MedicoResponse();
		try {
			InternalResultsResponse<Medico> internalResponse = getPessoaBAC().insertMedico(request);
			ResponseHandler.handleOperationStatusAndMessages(medicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, medicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return medicoResponse;
	}

	/**
	 * Update medico.
	 *
	 * @param request
	 *            the request
	 * @return the medico response
	 */
	@RequestMapping(value = "/medico/update", method = RequestMethod.POST)
	@ResponseBody
	public MedicoResponse updateMedico(@RequestBody MedicoMaintenanceRequest request) {
		MedicoResponse medicoResponse = new MedicoResponse();
		try {
			InternalResultsResponse<Medico> internalResponse = getPessoaBAC().updateMedico(request);
			ResponseHandler.handleOperationStatusAndMessages(medicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, medicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return medicoResponse;
	}

	/**
	 * Delete medico.
	 *
	 * @param request
	 *            the request
	 * @return the medico response
	 */
	@RequestMapping(value = "/medico/delete", method = RequestMethod.POST)
	@ResponseBody
	public MedicoResponse deleteMedico(@RequestBody MedicoMaintenanceRequest request) {
		MedicoResponse medicoResponse = new MedicoResponse();

		try {
			InternalResultsResponse<Medico> internalResponse = getPessoaBAC().deleteMedico(request);
			ResponseHandler.handleOperationStatusAndMessages(medicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, medicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return medicoResponse;

	}

	// ===================================### PACIENTE
	// ####======================================
	/**
	 * Refresh pacientes.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the paciente response
	 */
	@RequestMapping(value = "/paciente/refresh", method = RequestMethod.GET)
	@ResponseBody
	public PacienteResponse refreshPacientes(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		PacienteResponse pacienteResponse = new PacienteResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Paciente> internalResponse = getPessoaBAC().refreshPacientes(request);
			ResponseHandler.handleOperationStatusAndMessages(pacienteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, pacienteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return pacienteResponse;

	}

	/**
	 * Fetch paciente paged.
	 *
	 * @param request
	 *            the request
	 * @return the paciente response
	 */
	@RequestMapping(value = "/paciente/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public PacienteResponse fetchPacientePaged(@RequestBody PacienteInquiryRequest request) {
		PacienteResponse pacienteResponse = new PacienteResponse();
		try {
			InternalResultsResponse<Paciente> internalResponse = getPessoaBAC().fetchPacientesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(pacienteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, pacienteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return pacienteResponse;
	}

	/**
	 * Insert paciente.
	 *
	 * @param request
	 *            the request
	 * @return the paciente response
	 */
	@RequestMapping(value = "/paciente/insert", method = RequestMethod.POST)
	@ResponseBody
	public PacienteResponse insertPaciente(@RequestBody PacienteMaintenanceRequest request) {
		PacienteResponse pacienteResponse = new PacienteResponse();
		try {
			InternalResultsResponse<Paciente> internalResponse = getPessoaBAC().insertPaciente(request);
			ResponseHandler.handleOperationStatusAndMessages(pacienteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, pacienteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return pacienteResponse;
	}

	/**
	 * Update paciente.
	 *
	 * @param request
	 *            the request
	 * @return the paciente response
	 */
	@RequestMapping(value = "/paciente/update", method = RequestMethod.POST)
	@ResponseBody
	public PacienteResponse updatePaciente(@RequestBody PacienteMaintenanceRequest request) {
		PacienteResponse pacienteResponse = new PacienteResponse();
		try {
			InternalResultsResponse<Paciente> internalResponse = getPessoaBAC().updatePaciente(request);
			ResponseHandler.handleOperationStatusAndMessages(pacienteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, pacienteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return pacienteResponse;
	}

	/**
	 * Delete paciente.
	 *
	 * @param request
	 *            the request
	 * @return the paciente response
	 */
	@RequestMapping(value = "/paciente/delete", method = RequestMethod.POST)
	@ResponseBody
	public PacienteResponse deletePaciente(@RequestBody PacienteMaintenanceRequest request) {
		PacienteResponse pacienteResponse = new PacienteResponse();

		try {
			InternalResultsResponse<Paciente> internalResponse = getPessoaBAC().deletePaciente(request);
			ResponseHandler.handleOperationStatusAndMessages(pacienteResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, pacienteResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return pacienteResponse;

	}

	// ===================================### SINDICO
	// ####======================================
	/**
	 * Refresh sindicos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the sindico response
	 */
	@RequestMapping(value = "/sindico/refresh", method = RequestMethod.GET)
	@ResponseBody
	public SindicoResponse refreshSindicos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		SindicoResponse sindicoResponse = new SindicoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Sindico> internalResponse = getPessoaBAC().refreshSindicos(request);
			ResponseHandler.handleOperationStatusAndMessages(sindicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, sindicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return sindicoResponse;

	}

	/**
	 * Fetch sindico paged.
	 *
	 * @param request
	 *            the request
	 * @return the sindico response
	 */
	@RequestMapping(value = "/sindico/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public SindicoResponse fetchSindicoPaged(@RequestBody SindicoInquiryRequest request) {
		SindicoResponse sindicoResponse = new SindicoResponse();
		try {
			InternalResultsResponse<Sindico> internalResponse = getPessoaBAC().fetchSindicosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(sindicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, sindicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return sindicoResponse;
	}

	/**
	 * Insert sindico.
	 *
	 * @param request
	 *            the request
	 * @return the sindico response
	 */
	@RequestMapping(value = "/sindico/insert", method = RequestMethod.POST)
	@ResponseBody
	public SindicoResponse insertSindico(@RequestBody SindicoMaintenanceRequest request) {
		SindicoResponse sindicoResponse = new SindicoResponse();
		try {
			InternalResultsResponse<Sindico> internalResponse = getPessoaBAC().insertSindico(request);
			ResponseHandler.handleOperationStatusAndMessages(sindicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, sindicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return sindicoResponse;
	}

	/**
	 * Update sindico.
	 *
	 * @param request
	 *            the request
	 * @return the sindico response
	 */
	@RequestMapping(value = "/sindico/update", method = RequestMethod.POST)
	@ResponseBody
	public SindicoResponse updateSindico(@RequestBody SindicoMaintenanceRequest request) {
		SindicoResponse sindicoResponse = new SindicoResponse();
		try {
			InternalResultsResponse<Sindico> internalResponse = getPessoaBAC().updateSindico(request);
			ResponseHandler.handleOperationStatusAndMessages(sindicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, sindicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return sindicoResponse;
	}

	/**
	 * Delete sindico.
	 *
	 * @param request
	 *            the request
	 * @return the sindico response
	 */
	@RequestMapping(value = "/sindico/delete", method = RequestMethod.POST)
	@ResponseBody
	public SindicoResponse deleteSindico(@RequestBody SindicoMaintenanceRequest request) {
		SindicoResponse sindicoResponse = new SindicoResponse();

		try {
			InternalResultsResponse<Sindico> internalResponse = getPessoaBAC().deleteSindico(request);
			ResponseHandler.handleOperationStatusAndMessages(sindicoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, sindicoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return sindicoResponse;

	}

	// ===================================### INQUILINO
	// ####======================================
	/**
	 * Refresh inquilinos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the inquilino response
	 */
	@RequestMapping(value = "/inquilino/refresh", method = RequestMethod.GET)
	@ResponseBody
	public InquilinoResponse refreshInquilinos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		InquilinoResponse inquilinoResponse = new InquilinoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Inquilino> internalResponse = getPessoaBAC().refreshInquilinos(request);
			ResponseHandler.handleOperationStatusAndMessages(inquilinoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, inquilinoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return inquilinoResponse;

	}

	/**
	 * Fetch inquilino paged.
	 *
	 * @param request
	 *            the request
	 * @return the inquilino response
	 */
	@RequestMapping(value = "/inquilino/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public InquilinoResponse fetchInquilinoPaged(@RequestBody InquilinoInquiryRequest request) {
		InquilinoResponse inquilinoResponse = new InquilinoResponse();
		try {
			InternalResultsResponse<Inquilino> internalResponse = getPessoaBAC().fetchInquilinosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(inquilinoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, inquilinoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return inquilinoResponse;
	}

	/**
	 * Insert inquilino.
	 *
	 * @param request
	 *            the request
	 * @return the inquilino response
	 */
	@RequestMapping(value = "/inquilino/insert", method = RequestMethod.POST)
	@ResponseBody
	public InquilinoResponse insertInquilino(@RequestBody InquilinoMaintenanceRequest request) {
		InquilinoResponse inquilinoResponse = new InquilinoResponse();
		try {
			InternalResultsResponse<Inquilino> internalResponse = getPessoaBAC().insertInquilino(request);
			ResponseHandler.handleOperationStatusAndMessages(inquilinoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, inquilinoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return inquilinoResponse;
	}

	/**
	 * Update inquilino.
	 *
	 * @param request
	 *            the request
	 * @return the inquilino response
	 */
	@RequestMapping(value = "/inquilino/update", method = RequestMethod.POST)
	@ResponseBody
	public InquilinoResponse updateInquilino(@RequestBody InquilinoMaintenanceRequest request) {
		InquilinoResponse inquilinoResponse = new InquilinoResponse();
		try {
			InternalResultsResponse<Inquilino> internalResponse = getPessoaBAC().updateInquilino(request);
			ResponseHandler.handleOperationStatusAndMessages(inquilinoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, inquilinoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return inquilinoResponse;
	}

	/**
	 * Delete inquilino.
	 *
	 * @param request
	 *            the request
	 * @return the inquilino response
	 */
	@RequestMapping(value = "/inquilino/delete", method = RequestMethod.POST)
	@ResponseBody
	public InquilinoResponse deleteInquilino(@RequestBody InquilinoMaintenanceRequest request) {
		InquilinoResponse inquilinoResponse = new InquilinoResponse();

		try {
			InternalResultsResponse<Inquilino> internalResponse = getPessoaBAC().deleteInquilino(request);
			ResponseHandler.handleOperationStatusAndMessages(inquilinoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, inquilinoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return inquilinoResponse;

	}

	// ===================================### FUNCIONARIO
	// ####======================================
	/**
	 * Refresh funcionarios.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the funcionario response
	 */
	@RequestMapping(value = "/funcionario/refresh", method = RequestMethod.GET)
	@ResponseBody
	public FuncionarioResponse refreshFuncionarios(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		FuncionarioResponse funcionarioResponse = new FuncionarioResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Funcionario> internalResponse = getPessoaBAC().refreshFuncionarios(request);
			ResponseHandler.handleOperationStatusAndMessages(funcionarioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, funcionarioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return funcionarioResponse;

	}

	/**
	 * Fetch funcionario paged.
	 *
	 * @param request
	 *            the request
	 * @return the funcionario response
	 */
	@RequestMapping(value = "/funcionario/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public FuncionarioResponse fetchFuncionarioPaged(@RequestBody FuncionarioInquiryRequest request) {
		FuncionarioResponse funcionarioResponse = new FuncionarioResponse();
		try {
			InternalResultsResponse<Funcionario> internalResponse = getPessoaBAC().fetchFuncionariosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(funcionarioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, funcionarioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return funcionarioResponse;
	}

	/**
	 * Insert funcionario.
	 *
	 * @param request
	 *            the request
	 * @return the funcionario response
	 */
	@RequestMapping(value = "/funcionario/insert", method = RequestMethod.POST)
	@ResponseBody
	public FuncionarioResponse insertFuncionario(@RequestBody FuncionarioMaintenanceRequest request) {
		FuncionarioResponse funcionarioResponse = new FuncionarioResponse();
		try {
			InternalResultsResponse<Funcionario> internalResponse = getPessoaBAC().insertFuncionario(request);
			ResponseHandler.handleOperationStatusAndMessages(funcionarioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, funcionarioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return funcionarioResponse;
	}

	/**
	 * Update funcionario.
	 *
	 * @param request
	 *            the request
	 * @return the funcionario response
	 */
	@RequestMapping(value = "/funcionario/update", method = RequestMethod.POST)
	@ResponseBody
	public FuncionarioResponse updateFuncionario(@RequestBody FuncionarioMaintenanceRequest request) {
		FuncionarioResponse funcionarioResponse = new FuncionarioResponse();
		try {
			InternalResultsResponse<Funcionario> internalResponse = getPessoaBAC().updateFuncionario(request);
			ResponseHandler.handleOperationStatusAndMessages(funcionarioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, funcionarioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return funcionarioResponse;
	}

	/**
	 * Delete funcionario.
	 *
	 * @param request
	 *            the request
	 * @return the funcionario response
	 */
	@RequestMapping(value = "/funcionario/delete", method = RequestMethod.POST)
	@ResponseBody
	public FuncionarioResponse deleteFuncionario(@RequestBody FuncionarioMaintenanceRequest request) {
		FuncionarioResponse funcionarioResponse = new FuncionarioResponse();

		try {
			InternalResultsResponse<Funcionario> internalResponse = getPessoaBAC().deleteFuncionario(request);
			ResponseHandler.handleOperationStatusAndMessages(funcionarioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, funcionarioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return funcionarioResponse;

	}

}
