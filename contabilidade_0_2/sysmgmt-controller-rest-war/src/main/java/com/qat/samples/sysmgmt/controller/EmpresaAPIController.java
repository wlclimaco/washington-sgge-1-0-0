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
import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.response.AdvocaciaResponse;
import com.qat.samples.sysmgmt.bac.Empresa.IEmpresaBAC;
import com.qat.samples.sysmgmt.clinica.model.Clinica;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.response.ClinicaResponse;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.TransactionInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.response.CondominioResponse;
import com.qat.samples.sysmgmt.condominio.model.response.TransactionResponse;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Pagina;
import com.qat.samples.sysmgmt.entidade.model.Role;
import com.qat.samples.sysmgmt.entidade.model.Transaction;
import com.qat.samples.sysmgmt.entidade.model.UserRoles;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.response.DepositoResponse;
import com.qat.samples.sysmgmt.entidade.model.response.EmpresaResponse;
import com.qat.samples.sysmgmt.entidade.model.response.EnderecoResponse;
import com.qat.samples.sysmgmt.entidade.model.response.FilialResponse;
import com.qat.samples.sysmgmt.entidade.model.response.NoteResponse;
import com.qat.samples.sysmgmt.entidade.model.response.PaginaResponse;
import com.qat.samples.sysmgmt.entidade.model.response.RoleResponse;
import com.qat.samples.sysmgmt.entidade.model.response.StatusResponse;
import com.qat.samples.sysmgmt.entidade.model.response.UserRolesResponse;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.Note;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.NoteMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.PaginaMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.RoleMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.StatusMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.UserRolesMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.response.DoisValoresResponse;
import com.qat.samples.sysmgmt.util.model.response.UsuarioResponse;

/**
 * The Class EmpresaAPIController.
 */
@Controller
@RequestMapping("/entidade/api")
public class EmpresaAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.empresacontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EmpresaAPIController.class);

	/** The empresa bac. */
	private IEmpresaBAC empresaBAC; // injected by @Resource

	/**
	 * Gets the empresa bac.
	 *
	 * @return the empresa bac
	 */
	public IEmpresaBAC getEmpresaBAC() {
		return empresaBAC;
	}

	/**
	 * Sets the empresa bac.
	 *
	 * @param empresaBAC
	 *            the new empresa bac
	 */
	@Resource
	public void setEmpresaBAC(IEmpresaBAC empresaBAC) {
		this.empresaBAC = empresaBAC;
	}

	// ===================================### EMPRESA
	// ####======================================
	/**
	 * Refresh empresas.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the empresa response
	 */
	@RequestMapping(value = "/empresa/refresh", method = RequestMethod.GET)
	@ResponseBody
	public EmpresaResponse refreshEmpresas(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		EmpresaResponse empresaResponse = new EmpresaResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().refreshEmpresas(request);
			ResponseHandler.handleOperationStatusAndMessages(empresaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, empresaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return empresaResponse;

	}

	/**
	 * Fetch empresa paged.
	 *
	 * @param request
	 *            the request
	 * @return the empresa response
	 */
	@RequestMapping(value = "/empresa/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse fetchEmpresaPaged(@RequestBody EmpresaInquiryRequest request) {
		EmpresaResponse empresaResponse = new EmpresaResponse();
		try {
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().fetchEmpresasByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(empresaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, empresaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return empresaResponse;
	}

	/**
	 * Fetch empresa paged.
	 *
	 * @param request
	 *            the request
	 * @return the empresa response
	 */
	@RequestMapping(value = "/endereco/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public EnderecoResponse fetchEnderecoPaged(@RequestBody EmpresaInquiryRequest request) {
		EnderecoResponse empresaResponse = new EnderecoResponse();
		try {
			InternalResultsResponse<Endereco> internalResponse = getEmpresaBAC().fetchEnderecosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(empresaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, empresaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return empresaResponse;
	}

	/**
	 * Insert empresa.
	 *
	 * @param request
	 *            the request
	 * @return the empresa response
	 */
	@RequestMapping(value = "/empresa/insert", method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse insertEmpresa(@RequestBody EmpresaMaintenanceRequest request) {
		EmpresaResponse empresaResponse = new EmpresaResponse();
		try {
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().insertEmpresa(request);
			ResponseHandler.handleOperationStatusAndMessages(empresaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, empresaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return empresaResponse;
	}

	/**
	 * Update empresa.
	 *
	 * @param request
	 *            the request
	 * @return the empresa response
	 */
	@RequestMapping(value = "/empresa/update", method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse updateEmpresa(@RequestBody EmpresaMaintenanceRequest request) {
		EmpresaResponse empresaResponse = new EmpresaResponse();
		try {
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().updateEmpresa(request);
			ResponseHandler.handleOperationStatusAndMessages(empresaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, empresaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return empresaResponse;
	}

	/**
	 * Delete empresa.
	 *
	 * @param request
	 *            the request
	 * @return the empresa response
	 */
	@RequestMapping(value = "/empresa/delete", method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse deleteEmpresa(@RequestBody EmpresaMaintenanceRequest request) {
		EmpresaResponse empresaResponse = new EmpresaResponse();

		try {
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().deleteEmpresa(request);
			ResponseHandler.handleOperationStatusAndMessages(empresaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, empresaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return empresaResponse;

	}

	// ===================================### FILIAL
	// ####======================================
	/**
	 * Refresh filials.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the filial response
	 */
	@RequestMapping(value = "/filial/refresh", method = RequestMethod.GET)
	@ResponseBody
	public FilialResponse refreshFilials(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		FilialResponse filialResponse = new FilialResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Filial> internalResponse = getEmpresaBAC().refreshFilials(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;

	}

	/**
	 * Fetch filial paged.
	 *
	 * @param request
	 *            the request
	 * @return the filial response
	 */
	@RequestMapping(value = "/filial/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public FilialResponse fetchFilialPaged(@RequestBody FilialInquiryRequest request) {
		FilialResponse filialResponse = new FilialResponse();
		try {
			InternalResultsResponse<Filial> internalResponse = getEmpresaBAC().fetchFilialsByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;
	}

	/**
	 * Insert filial.
	 *
	 * @param request
	 *            the request
	 * @return the filial response
	 */
	@RequestMapping(value = "/filial/insert", method = RequestMethod.POST)
	@ResponseBody
	public FilialResponse insertFilial(@RequestBody FilialMaintenanceRequest request) {
		FilialResponse filialResponse = new FilialResponse();
		try {
			InternalResultsResponse<Filial> internalResponse = getEmpresaBAC().insertFilial(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;
	}

	/**
	 * Update filial.
	 *
	 * @param request
	 *            the request
	 * @return the filial response
	 */
	@RequestMapping(value = "/filial/update", method = RequestMethod.POST)
	@ResponseBody
	public FilialResponse updateFilial(@RequestBody FilialMaintenanceRequest request) {
		FilialResponse filialResponse = new FilialResponse();
		try {
			InternalResultsResponse<Filial> internalResponse = getEmpresaBAC().updateFilial(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;
	}

	/**
	 * Delete filial.
	 *
	 * @param request
	 *            the request
	 * @return the filial response
	 */
	@RequestMapping(value = "/filial/delete", method = RequestMethod.POST)
	@ResponseBody
	public FilialResponse deleteFilial(@RequestBody FilialMaintenanceRequest request) {
		FilialResponse filialResponse = new FilialResponse();

		try {
			InternalResultsResponse<Filial> internalResponse = getEmpresaBAC().deleteFilial(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;

	}

	// ===================================### DEPOSITO
	// ####======================================
	/**
	 * Refresh depositos.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the deposito response
	 */
	@RequestMapping(value = "/deposito/refresh", method = RequestMethod.GET)
	@ResponseBody
	public DepositoResponse refreshDepositos(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		DepositoResponse depositoResponse = new DepositoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Deposito> internalResponse = getEmpresaBAC().refreshDepositos(request);
			ResponseHandler.handleOperationStatusAndMessages(depositoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, depositoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return depositoResponse;

	}

	/**
	 * Fetch deposito paged.
	 *
	 * @param request
	 *            the request
	 * @return the deposito response
	 */
	@RequestMapping(value = "/deposito/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public DepositoResponse fetchDepositoPaged(@RequestBody DepositoInquiryRequest request) {
		DepositoResponse depositoResponse = new DepositoResponse();
		try {
			InternalResultsResponse<Deposito> internalResponse = getEmpresaBAC().fetchDepositosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(depositoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, depositoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return depositoResponse;
	}

	/**
	 * Insert deposito.
	 *
	 * @param request
	 *            the request
	 * @return the deposito response
	 */
	@RequestMapping(value = "/deposito/insert", method = RequestMethod.POST)
	@ResponseBody
	public DepositoResponse insertDeposito(@RequestBody DepositoMaintenanceRequest request) {
		DepositoResponse depositoResponse = new DepositoResponse();
		try {
			InternalResultsResponse<Deposito> internalResponse = getEmpresaBAC().insertDeposito(request);
			ResponseHandler.handleOperationStatusAndMessages(depositoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, depositoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return depositoResponse;
	}

	/**
	 * Update deposito.
	 *
	 * @param request
	 *            the request
	 * @return the deposito response
	 */
	@RequestMapping(value = "/deposito/update", method = RequestMethod.POST)
	@ResponseBody
	public DepositoResponse updateDeposito(@RequestBody DepositoMaintenanceRequest request) {
		DepositoResponse depositoResponse = new DepositoResponse();
		try {
			InternalResultsResponse<Deposito> internalResponse = getEmpresaBAC().updateDeposito(request);
			ResponseHandler.handleOperationStatusAndMessages(depositoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, depositoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return depositoResponse;
	}

	/**
	 * Delete deposito.
	 *
	 * @param request
	 *            the request
	 * @return the deposito response
	 */
	@RequestMapping(value = "/deposito/delete", method = RequestMethod.POST)
	@ResponseBody
	public DepositoResponse deleteDeposito(@RequestBody DepositoMaintenanceRequest request) {
		DepositoResponse depositoResponse = new DepositoResponse();

		try {
			InternalResultsResponse<Deposito> internalResponse = getEmpresaBAC().deleteDeposito(request);
			ResponseHandler.handleOperationStatusAndMessages(depositoResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, depositoResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return depositoResponse;

	}

	// ===================================### USUARIO
	// ####======================================
	/**
	 * Refresh usuarios.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the usuario response
	 */
	@RequestMapping(value = "/usuario/refresh", method = RequestMethod.GET)
	@ResponseBody
	public UsuarioResponse refreshUsuarios(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		UsuarioResponse usuarioResponse = new UsuarioResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Usuario> internalResponse = getEmpresaBAC().refreshUsuarios(request);
			ResponseHandler.handleOperationStatusAndMessages(usuarioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, usuarioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return usuarioResponse;

	}

	/**
	 * Fetch usuario paged.
	 *
	 * @param request
	 *            the request
	 * @return the usuario response
	 */
	@RequestMapping(value = "/usuario/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public UsuarioResponse fetchUsuarioPaged(@RequestBody UsuarioInquiryRequest request) {
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		try {
			InternalResultsResponse<Usuario> internalResponse = getEmpresaBAC().fetchUsuariosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(usuarioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, usuarioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return usuarioResponse;
	}

	/**
	 * Insert usuario.
	 *
	 * @param request
	 *            the request
	 * @return the usuario response
	 */
	@RequestMapping(value = "/usuario/insert", method = RequestMethod.POST)
	@ResponseBody
	public UsuarioResponse insertUsuario(@RequestBody UsuarioMaintenanceRequest request) {
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		try {
			InternalResultsResponse<Usuario> internalResponse = getEmpresaBAC().insertUsuario(request);
			ResponseHandler.handleOperationStatusAndMessages(usuarioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, usuarioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return usuarioResponse;
	}

	/**
	 * Update usuario.
	 *
	 * @param request
	 *            the request
	 * @return the usuario response
	 */
	@RequestMapping(value = "/usuario/update", method = RequestMethod.POST)
	@ResponseBody
	public UsuarioResponse updateUsuario(@RequestBody UsuarioMaintenanceRequest request) {
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		try {
			InternalResultsResponse<Usuario> internalResponse = getEmpresaBAC().updateUsuario(request);
			ResponseHandler.handleOperationStatusAndMessages(usuarioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, usuarioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return usuarioResponse;
	}

	/**
	 * Delete usuario.
	 *
	 * @param request
	 *            the request
	 * @return the usuario response
	 */
	@RequestMapping(value = "/usuario/delete", method = RequestMethod.POST)
	@ResponseBody
	public UsuarioResponse deleteUsuario(@RequestBody UsuarioMaintenanceRequest request) {
		UsuarioResponse usuarioResponse = new UsuarioResponse();

		try {
			InternalResultsResponse<Usuario> internalResponse = getEmpresaBAC().deleteUsuario(request);
			ResponseHandler.handleOperationStatusAndMessages(usuarioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, usuarioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return usuarioResponse;

	}

	// ===================================### ADVOCACIA
	// ####======================================
	/**
	 * Refresh advocacias.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the advocacia response
	 */
	@RequestMapping(value = "/advocacia/refresh", method = RequestMethod.GET)
	@ResponseBody
	public AdvocaciaResponse refreshAdvocacias(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		AdvocaciaResponse advocaciaResponse = new AdvocaciaResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Advocacia> internalResponse = getEmpresaBAC().refreshAdvocacias(request);
			ResponseHandler.handleOperationStatusAndMessages(advocaciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, advocaciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return advocaciaResponse;

	}

	/**
	 * Fetch advocacia paged.
	 *
	 * @param request
	 *            the request
	 * @return the advocacia response
	 */
	@RequestMapping(value = "/advocacia/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public AdvocaciaResponse fetchAdvocaciaPaged(@RequestBody AdvocaciaInquiryRequest request) {
		AdvocaciaResponse advocaciaResponse = new AdvocaciaResponse();
		try {
			InternalResultsResponse<Advocacia> internalResponse = getEmpresaBAC().fetchAdvocaciasByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(advocaciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, advocaciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return advocaciaResponse;
	}

	/**
	 * Insert advocacia.
	 *
	 * @param request
	 *            the request
	 * @return the advocacia response
	 */
	@RequestMapping(value = "/advocacia/insert", method = RequestMethod.POST)
	@ResponseBody
	public AdvocaciaResponse insertAdvocacia(@RequestBody AdvocaciaMaintenanceRequest request) {
		AdvocaciaResponse advocaciaResponse = new AdvocaciaResponse();
		try {
			InternalResultsResponse<Advocacia> internalResponse = getEmpresaBAC().insertAdvocacia(request);
			ResponseHandler.handleOperationStatusAndMessages(advocaciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, advocaciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return advocaciaResponse;
	}

	/**
	 * Update advocacia.
	 *
	 * @param request
	 *            the request
	 * @return the advocacia response
	 */
	@RequestMapping(value = "/advocacia/update", method = RequestMethod.POST)
	@ResponseBody
	public AdvocaciaResponse updateAdvocacia(@RequestBody AdvocaciaMaintenanceRequest request) {
		AdvocaciaResponse advocaciaResponse = new AdvocaciaResponse();
		try {
			InternalResultsResponse<Advocacia> internalResponse = getEmpresaBAC().updateAdvocacia(request);
			ResponseHandler.handleOperationStatusAndMessages(advocaciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, advocaciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return advocaciaResponse;
	}

	/**
	 * Delete advocacia.
	 *
	 * @param request
	 *            the request
	 * @return the advocacia response
	 */
	@RequestMapping(value = "/advocacia/delete", method = RequestMethod.POST)
	@ResponseBody
	public AdvocaciaResponse deleteAdvocacia(@RequestBody AdvocaciaMaintenanceRequest request) {
		AdvocaciaResponse advocaciaResponse = new AdvocaciaResponse();

		try {
			InternalResultsResponse<Advocacia> internalResponse = getEmpresaBAC().deleteAdvocacia(request);
			ResponseHandler.handleOperationStatusAndMessages(advocaciaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, advocaciaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return advocaciaResponse;

	}

	// ===================================### CLINICA
	// ####======================================
	/**
	 * Refresh clinicas.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the clinica response
	 */
	@RequestMapping(value = "/clinica/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ClinicaResponse refreshClinicas(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ClinicaResponse clinicaResponse = new ClinicaResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Clinica> internalResponse = getEmpresaBAC().refreshClinicas(request);
			ResponseHandler.handleOperationStatusAndMessages(clinicaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, clinicaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return clinicaResponse;

	}

	/**
	 * Fetch clinica paged.
	 *
	 * @param request
	 *            the request
	 * @return the clinica response
	 */
	@RequestMapping(value = "/clinica/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ClinicaResponse fetchClinicaPaged(@RequestBody ClinicaInquiryRequest request) {
		ClinicaResponse clinicaResponse = new ClinicaResponse();
		try {
			InternalResultsResponse<Clinica> internalResponse = getEmpresaBAC().fetchClinicasByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(clinicaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, clinicaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return clinicaResponse;
	}

	/**
	 * Insert clinica.
	 *
	 * @param request
	 *            the request
	 * @return the clinica response
	 */
	@RequestMapping(value = "/clinica/insert", method = RequestMethod.POST)
	@ResponseBody
	public ClinicaResponse insertClinica(@RequestBody ClinicaMaintenanceRequest request) {
		ClinicaResponse clinicaResponse = new ClinicaResponse();
		try {
			InternalResultsResponse<Clinica> internalResponse = getEmpresaBAC().insertClinica(request);
			ResponseHandler.handleOperationStatusAndMessages(clinicaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, clinicaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return clinicaResponse;
	}

	/**
	 * Update clinica.
	 *
	 * @param request
	 *            the request
	 * @return the clinica response
	 */
	@RequestMapping(value = "/clinica/update", method = RequestMethod.POST)
	@ResponseBody
	public ClinicaResponse updateClinica(@RequestBody ClinicaMaintenanceRequest request) {
		ClinicaResponse clinicaResponse = new ClinicaResponse();
		try {
			InternalResultsResponse<Clinica> internalResponse = getEmpresaBAC().updateClinica(request);
			ResponseHandler.handleOperationStatusAndMessages(clinicaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, clinicaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return clinicaResponse;
	}

	/**
	 * Delete clinica.
	 *
	 * @param request
	 *            the request
	 * @return the clinica response
	 */
	@RequestMapping(value = "/clinica/delete", method = RequestMethod.POST)
	@ResponseBody
	public ClinicaResponse deleteClinica(@RequestBody ClinicaMaintenanceRequest request) {
		ClinicaResponse clinicaResponse = new ClinicaResponse();

		try {
			InternalResultsResponse<Clinica> internalResponse = getEmpresaBAC().deleteClinica(request);
			ResponseHandler.handleOperationStatusAndMessages(clinicaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, clinicaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return clinicaResponse;

	}

	// ===================================### CONDOMINIO
	// ####======================================
	/**
	 * Refresh condominios.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the condominio response
	 */
	@RequestMapping(value = "/condominio/refresh", method = RequestMethod.GET)
	@ResponseBody
	public CondominioResponse refreshCondominios(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		CondominioResponse condominioResponse = new CondominioResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Condominio> internalResponse = getEmpresaBAC().refreshCondominios(request);
			ResponseHandler.handleOperationStatusAndMessages(condominioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, condominioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return condominioResponse;

	}

	/**
	 * Fetch condominio paged.
	 *
	 * @param request
	 *            the request
	 * @return the condominio response
	 */
	@RequestMapping(value = "/condominio/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public CondominioResponse fetchCondominioPaged(@RequestBody CondominioInquiryRequest request) {
		CondominioResponse condominioResponse = new CondominioResponse();
		try {
			InternalResultsResponse<Condominio> internalResponse = getEmpresaBAC().fetchCondominiosByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(condominioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, condominioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return condominioResponse;
	}

	/**
	 * Insert condominio.
	 *
	 * @param request
	 *            the request
	 * @return the condominio response
	 */
	@RequestMapping(value = "/condominio/insert", method = RequestMethod.POST)
	@ResponseBody
	public CondominioResponse insertCondominio(@RequestBody CondominioMaintenanceRequest request) {
		CondominioResponse condominioResponse = new CondominioResponse();
		try {
			InternalResultsResponse<Condominio> internalResponse = getEmpresaBAC().insertCondominio(request);
			ResponseHandler.handleOperationStatusAndMessages(condominioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, condominioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return condominioResponse;
	}

	/**
	 * Update condominio.
	 *
	 * @param request
	 *            the request
	 * @return the condominio response
	 */
	@RequestMapping(value = "/condominio/update", method = RequestMethod.POST)
	@ResponseBody
	public CondominioResponse updateCondominio(@RequestBody CondominioMaintenanceRequest request) {
		CondominioResponse condominioResponse = new CondominioResponse();
		try {
			InternalResultsResponse<Condominio> internalResponse = getEmpresaBAC().updateCondominio(request);
			ResponseHandler.handleOperationStatusAndMessages(condominioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, condominioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return condominioResponse;
	}

	/**
	 * Delete condominio.
	 *
	 * @param request
	 *            the request
	 * @return the condominio response
	 */
	@RequestMapping(value = "/condominio/delete", method = RequestMethod.POST)
	@ResponseBody
	public CondominioResponse deleteCondominio(@RequestBody CondominioMaintenanceRequest request) {
		CondominioResponse condominioResponse = new CondominioResponse();

		try {
			InternalResultsResponse<Condominio> internalResponse = getEmpresaBAC().deleteCondominio(request);
			ResponseHandler.handleOperationStatusAndMessages(condominioResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, condominioResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return condominioResponse;

	}

	/**
	 * Delete condominio.
	 *
	 * @param request
	 *            the request
	 * @return the condominio response
	 */
	@RequestMapping(value = "/transaction/fetch", method = RequestMethod.POST)
	@ResponseBody
	public TransactionResponse deleteCondominio(@RequestBody TransactionInquiryRequest request) {
		TransactionResponse transactionResponse = new TransactionResponse();

		try {
			InternalResultsResponse<Transaction> internalResponse = getEmpresaBAC().fetchTransactionById(request);
			ResponseHandler.handleOperationStatusAndMessages(transactionResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, transactionResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return transactionResponse;

	}

	// ===================================### USERROLES
	// ####======================================
	/**
	 * Refresh userroless.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the userroles response
	 */
	@RequestMapping(value = "/userRoles/refresh", method = RequestMethod.GET)
	@ResponseBody
	public UserRolesResponse refreshUserRoless(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		UserRolesResponse userrolesResponse = new UserRolesResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<UserRoles> internalResponse = getEmpresaBAC().refreshUserRoless(request);
			ResponseHandler.handleOperationStatusAndMessages(userrolesResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, userrolesResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return userrolesResponse;

	}

	/**
	 * Fetch userroles paged.
	 *
	 * @param request
	 *            the request
	 * @return the userroles response
	 */
	@RequestMapping(value = "/userRoles/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public UserRolesResponse fetchUserRolesPaged(@RequestBody PagedInquiryRequest request) {
		UserRolesResponse userrolesResponse = new UserRolesResponse();
		try {
			InternalResultsResponse<UserRoles> internalResponse = getEmpresaBAC().fetchUserRolessByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(userrolesResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, userrolesResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return userrolesResponse;
	}

	/**
	 * Insert userroles.
	 *
	 * @param request
	 *            the request
	 * @return the userroles response
	 */
	@RequestMapping(value = "/userRoles/insert", method = RequestMethod.POST)
	@ResponseBody
	public UserRolesResponse insertUserRoles(@RequestBody UserRolesMaintenanceRequest request) {
		UserRolesResponse userrolesResponse = new UserRolesResponse();
		try {
			InternalResultsResponse<UserRoles> internalResponse = getEmpresaBAC().insertUserRoles(request);
			ResponseHandler.handleOperationStatusAndMessages(userrolesResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, userrolesResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return userrolesResponse;
	}

	/**
	 * Update userroles.
	 *
	 * @param request
	 *            the request
	 * @return the userroles response
	 */
	@RequestMapping(value = "/userRoles/update", method = RequestMethod.POST)
	@ResponseBody
	public UserRolesResponse updateUserRoles(@RequestBody UserRolesMaintenanceRequest request) {
		UserRolesResponse userrolesResponse = new UserRolesResponse();
		try {
			InternalResultsResponse<UserRoles> internalResponse = getEmpresaBAC().updateUserRoles(request);
			ResponseHandler.handleOperationStatusAndMessages(userrolesResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, userrolesResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return userrolesResponse;
	}

	/**
	 * Delete userroles.
	 *
	 * @param request
	 *            the request
	 * @return the userroles response
	 */
	@RequestMapping(value = "/userRoles/delete", method = RequestMethod.POST)
	@ResponseBody
	public UserRolesResponse deleteUserRoles(@RequestBody UserRolesMaintenanceRequest request) {
		UserRolesResponse userrolesResponse = new UserRolesResponse();

		try {
			InternalResultsResponse<UserRoles> internalResponse = getEmpresaBAC().deleteUserRoles(request);
			ResponseHandler.handleOperationStatusAndMessages(userrolesResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, userrolesResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return userrolesResponse;

	}

	// ===================================### ROLE
	// ####======================================
	/**
	 * Refresh roles.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the role response
	 */
	@RequestMapping(value = "/role/refresh", method = RequestMethod.GET)
	@ResponseBody
	public RoleResponse refreshRoles(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		RoleResponse roleResponse = new RoleResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Role> internalResponse = getEmpresaBAC().refreshRoles(request);
			ResponseHandler.handleOperationStatusAndMessages(roleResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, roleResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return roleResponse;

	}

	/**
	 * Fetch role paged.
	 *
	 * @param request
	 *            the request
	 * @return the role response
	 */
	@RequestMapping(value = "/role/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public RoleResponse fetchRolePaged(@RequestBody PagedInquiryRequest request) {
		RoleResponse roleResponse = new RoleResponse();
		try {
			InternalResultsResponse<Role> internalResponse = getEmpresaBAC().fetchRolesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(roleResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, roleResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return roleResponse;
	}

	/**
	 * Insert role.
	 *
	 * @param request
	 *            the request
	 * @return the role response
	 */
	@RequestMapping(value = "/role/insert", method = RequestMethod.POST)
	@ResponseBody
	public RoleResponse insertRole(@RequestBody RoleMaintenanceRequest request) {
		RoleResponse roleResponse = new RoleResponse();
		try {
			InternalResultsResponse<Role> internalResponse = getEmpresaBAC().insertRole(request);
			ResponseHandler.handleOperationStatusAndMessages(roleResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, roleResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return roleResponse;
	}

	/**
	 * Update role.
	 *
	 * @param request
	 *            the request
	 * @return the role response
	 */
	@RequestMapping(value = "/role/update", method = RequestMethod.POST)
	@ResponseBody
	public RoleResponse updateRole(@RequestBody RoleMaintenanceRequest request) {
		RoleResponse roleResponse = new RoleResponse();
		try {
			InternalResultsResponse<Role> internalResponse = getEmpresaBAC().updateRole(request);
			ResponseHandler.handleOperationStatusAndMessages(roleResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, roleResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return roleResponse;
	}

	/**
	 * Delete role.
	 *
	 * @param request
	 *            the request
	 * @return the role response
	 */
	@RequestMapping(value = "/role/delete", method = RequestMethod.POST)
	@ResponseBody
	public RoleResponse deleteRole(@RequestBody RoleMaintenanceRequest request) {
		RoleResponse roleResponse = new RoleResponse();

		try {
			InternalResultsResponse<Role> internalResponse = getEmpresaBAC().deleteRole(request);
			ResponseHandler.handleOperationStatusAndMessages(roleResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, roleResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return roleResponse;

	}

	// ===================================### PAGINA
	// ####======================================
	/**
	 * Refresh paginas.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the pagina response
	 */
	@RequestMapping(value = "/pagina/refresh", method = RequestMethod.GET)
	@ResponseBody
	public PaginaResponse refreshPaginas(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		PaginaResponse paginaResponse = new PaginaResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Pagina> internalResponse = getEmpresaBAC().refreshPaginas(request);
			ResponseHandler.handleOperationStatusAndMessages(paginaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, paginaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return paginaResponse;

	}

	/**
	 * Fetch pagina paged.
	 *
	 * @param request
	 *            the request
	 * @return the pagina response
	 */
	@RequestMapping(value = "/pagina/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public PaginaResponse fetchPaginaPaged(@RequestBody PagedInquiryRequest request) {
		PaginaResponse paginaResponse = new PaginaResponse();
		try {
			InternalResultsResponse<Pagina> internalResponse = getEmpresaBAC().fetchPaginasByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(paginaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, paginaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return paginaResponse;
	}

	/**
	 * Insert pagina.
	 *
	 * @param request
	 *            the request
	 * @return the pagina response
	 */
	@RequestMapping(value = "/pagina/insert", method = RequestMethod.POST)
	@ResponseBody
	public PaginaResponse insertPagina(@RequestBody PaginaMaintenanceRequest request) {
		PaginaResponse paginaResponse = new PaginaResponse();
		try {
			InternalResultsResponse<Pagina> internalResponse = getEmpresaBAC().insertPagina(request);
			ResponseHandler.handleOperationStatusAndMessages(paginaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, paginaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return paginaResponse;
	}

	/**
	 * Update pagina.
	 *
	 * @param request
	 *            the request
	 * @return the pagina response
	 */
	@RequestMapping(value = "/pagina/update", method = RequestMethod.POST)
	@ResponseBody
	public PaginaResponse updatePagina(@RequestBody PaginaMaintenanceRequest request) {
		PaginaResponse paginaResponse = new PaginaResponse();
		try {
			InternalResultsResponse<Pagina> internalResponse = getEmpresaBAC().updatePagina(request);
			ResponseHandler.handleOperationStatusAndMessages(paginaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, paginaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return paginaResponse;
	}

	/**
	 * Delete pagina.
	 *
	 * @param request
	 *            the request
	 * @return the pagina response
	 */
	@RequestMapping(value = "/pagina/delete", method = RequestMethod.POST)
	@ResponseBody
	public PaginaResponse deletePagina(@RequestBody PaginaMaintenanceRequest request) {
		PaginaResponse paginaResponse = new PaginaResponse();

		try {
			InternalResultsResponse<Pagina> internalResponse = getEmpresaBAC().deletePagina(request);
			ResponseHandler.handleOperationStatusAndMessages(paginaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, paginaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return paginaResponse;

	}

	// // ===================================### VALIDACAO
	// // ####======================================
	// /**
	// * Refresh validacaos.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the validacao response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public ValidacaoResponse refreshValidacaos(@RequestParam("refreshInt")
	// Integer refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// ValidacaoResponse validacaoResponse = new ValidacaoResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<Validacao> internalResponse =
	// getEmpresaBAC().refreshValidacaos(request);
	// ResponseHandler.handleOperationStatusAndMessages(validacaoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, validacaoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return validacaoResponse;
	//
	// }
	//
	// /**
	// * Fetch validacao paged.
	// *
	// * @param request
	// * the request
	// * @return the validacao response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public ValidacaoResponse fetchValidacaoPaged(@RequestBody
	// ValidacaoInquiryRequest request) {
	// ValidacaoResponse validacaoResponse = new ValidacaoResponse();
	// try {
	// InternalResultsResponse<Validacao> internalResponse =
	// getEmpresaBAC().fetchValidacaosByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(validacaoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, validacaoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return validacaoResponse;
	// }
	//
	// /**
	// * Insert validacao.
	// *
	// * @param request
	// * the request
	// * @return the validacao response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public ValidacaoResponse insertValidacao(@RequestBody
	// ValidacaoMaintenanceRequest request) {
	// ValidacaoResponse validacaoResponse = new ValidacaoResponse();
	// try {
	// InternalResultsResponse<Validacao> internalResponse =
	// getEmpresaBAC().insertValidacao(request);
	// ResponseHandler.handleOperationStatusAndMessages(validacaoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, validacaoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return validacaoResponse;
	// }
	//
	// /**
	// * Update validacao.
	// *
	// * @param request
	// * the request
	// * @return the validacao response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public ValidacaoResponse updateValidacao(@RequestBody
	// ValidacaoMaintenanceRequest request) {
	// ValidacaoResponse validacaoResponse = new ValidacaoResponse();
	// try {
	// InternalResultsResponse<Validacao> internalResponse =
	// getEmpresaBAC().updateValidacao(request);
	// ResponseHandler.handleOperationStatusAndMessages(validacaoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, validacaoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return validacaoResponse;
	// }
	//
	// /**
	// * Delete validacao.
	// *
	// * @param request
	// * the request
	// * @return the validacao response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public ValidacaoResponse deleteValidacao(@RequestBody
	// ValidacaoMaintenanceRequest request) {
	// ValidacaoResponse validacaoResponse = new ValidacaoResponse();
	//
	// try {
	// InternalResultsResponse<Validacao> internalResponse =
	// getEmpresaBAC().deleteValidacao(request);
	// ResponseHandler.handleOperationStatusAndMessages(validacaoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, validacaoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return validacaoResponse;
	//
	// }
	//
	// // ===================================### FIELD
	// // ####======================================
	// /**
	// * Refresh fields.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the field response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public FieldResponse refreshFields(@RequestParam("refreshInt") Integer
	// refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// FieldResponse fieldResponse = new FieldResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<Field> internalResponse =
	// getEmpresaBAC().refreshFields(request);
	// ResponseHandler.handleOperationStatusAndMessages(fieldResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, fieldResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return fieldResponse;
	//
	// }
	//
	// /**
	// * Fetch field paged.
	// *
	// * @param request
	// * the request
	// * @return the field response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public FieldResponse fetchFieldPaged(@RequestBody FieldInquiryRequest
	// request) {
	// FieldResponse fieldResponse = new FieldResponse();
	// try {
	// InternalResultsResponse<Field> internalResponse =
	// getEmpresaBAC().fetchFieldsByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(fieldResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, fieldResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return fieldResponse;
	// }
	//
	// /**
	// * Insert field.
	// *
	// * @param request
	// * the request
	// * @return the field response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public FieldResponse insertField(@RequestBody FieldMaintenanceRequest
	// request) {
	// FieldResponse fieldResponse = new FieldResponse();
	// try {
	// InternalResultsResponse<Field> internalResponse =
	// getEmpresaBAC().insertField(request);
	// ResponseHandler.handleOperationStatusAndMessages(fieldResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, fieldResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return fieldResponse;
	// }
	//
	// /**
	// * Update field.
	// *
	// * @param request
	// * the request
	// * @return the field response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public FieldResponse updateField(@RequestBody FieldMaintenanceRequest
	// request) {
	// FieldResponse fieldResponse = new FieldResponse();
	// try {
	// InternalResultsResponse<Field> internalResponse =
	// getEmpresaBAC().updateField(request);
	// ResponseHandler.handleOperationStatusAndMessages(fieldResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, fieldResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return fieldResponse;
	// }
	//
	// /**
	// * Delete field.
	// *
	// * @param request
	// * the request
	// * @return the field response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public FieldResponse deleteField(@RequestBody FieldMaintenanceRequest
	// request) {
	// FieldResponse fieldResponse = new FieldResponse();
	//
	// try {
	// InternalResultsResponse<Field> internalResponse =
	// getEmpresaBAC().deleteField(request);
	// ResponseHandler.handleOperationStatusAndMessages(fieldResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, fieldResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return fieldResponse;
	//
	// }
	//
	// // ===================================### AJUDA
	// // ####======================================
	// /**
	// * Refresh ajudas.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the ajuda response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public AjudaResponse refreshAjudas(@RequestParam("refreshInt") Integer
	// refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// AjudaResponse ajudaResponse = new AjudaResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<Ajuda> internalResponse =
	// getEmpresaBAC().refreshAjudas(request);
	// ResponseHandler.handleOperationStatusAndMessages(ajudaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, ajudaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return ajudaResponse;
	//
	// }
	//
	// /**
	// * Fetch ajuda paged.
	// *
	// * @param request
	// * the request
	// * @return the ajuda response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public AjudaResponse fetchAjudaPaged(@RequestBody AjudaInquiryRequest
	// request) {
	// AjudaResponse ajudaResponse = new AjudaResponse();
	// try {
	// InternalResultsResponse<Ajuda> internalResponse =
	// getEmpresaBAC().fetchAjudasByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(ajudaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, ajudaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return ajudaResponse;
	// }
	//
	// /**
	// * Insert ajuda.
	// *
	// * @param request
	// * the request
	// * @return the ajuda response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public AjudaResponse insertAjuda(@RequestBody AjudaMaintenanceRequest
	// request) {
	// AjudaResponse ajudaResponse = new AjudaResponse();
	// try {
	// InternalResultsResponse<Ajuda> internalResponse =
	// getEmpresaBAC().insertAjuda(request);
	// ResponseHandler.handleOperationStatusAndMessages(ajudaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, ajudaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return ajudaResponse;
	// }
	//
	// /**
	// * Update ajuda.
	// *
	// * @param request
	// * the request
	// * @return the ajuda response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public AjudaResponse updateAjuda(@RequestBody AjudaMaintenanceRequest
	// request) {
	// AjudaResponse ajudaResponse = new AjudaResponse();
	// try {
	// InternalResultsResponse<Ajuda> internalResponse =
	// getEmpresaBAC().updateAjuda(request);
	// ResponseHandler.handleOperationStatusAndMessages(ajudaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, ajudaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return ajudaResponse;
	// }
	//
	// /**
	// * Delete ajuda.
	// *
	// * @param request
	// * the request
	// * @return the ajuda response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public AjudaResponse deleteAjuda(@RequestBody AjudaMaintenanceRequest
	// request) {
	// AjudaResponse ajudaResponse = new AjudaResponse();
	//
	// try {
	// InternalResultsResponse<Ajuda> internalResponse =
	// getEmpresaBAC().deleteAjuda(request);
	// ResponseHandler.handleOperationStatusAndMessages(ajudaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, ajudaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return ajudaResponse;
	//
	// }
	//
	// // ===================================### MENU
	// // ####======================================
	// /**
	// * Refresh menus.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the menu response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public MenuResponse refreshMenus(@RequestParam("refreshInt") Integer
	// refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// MenuResponse menuResponse = new MenuResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<Menu> internalResponse =
	// getEmpresaBAC().refreshMenus(request);
	// ResponseHandler.handleOperationStatusAndMessages(menuResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, menuResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return menuResponse;
	//
	// }
	//
	// /**
	// * Fetch menu paged.
	// *
	// * @param request
	// * the request
	// * @return the menu response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public MenuResponse fetchMenuPaged(@RequestBody MenuInquiryRequest
	// request) {
	// MenuResponse menuResponse = new MenuResponse();
	// try {
	// InternalResultsResponse<Menu> internalResponse =
	// getEmpresaBAC().fetchMenusByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(menuResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, menuResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return menuResponse;
	// }
	//
	// /**
	// * Insert menu.
	// *
	// * @param request
	// * the request
	// * @return the menu response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public MenuResponse insertMenu(@RequestBody MenuMaintenanceRequest
	// request) {
	// MenuResponse menuResponse = new MenuResponse();
	// try {
	// InternalResultsResponse<Menu> internalResponse =
	// getEmpresaBAC().insertMenu(request);
	// ResponseHandler.handleOperationStatusAndMessages(menuResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, menuResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return menuResponse;
	// }
	//
	// /**
	// * Update menu.
	// *
	// * @param request
	// * the request
	// * @return the menu response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public MenuResponse updateMenu(@RequestBody MenuMaintenanceRequest
	// request) {
	// MenuResponse menuResponse = new MenuResponse();
	// try {
	// InternalResultsResponse<Menu> internalResponse =
	// getEmpresaBAC().updateMenu(request);
	// ResponseHandler.handleOperationStatusAndMessages(menuResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, menuResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return menuResponse;
	// }
	//
	// /**
	// * Delete menu.
	// *
	// * @param request
	// * the request
	// * @return the menu response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public MenuResponse deleteMenu(@RequestBody MenuMaintenanceRequest
	// request) {
	// MenuResponse menuResponse = new MenuResponse();
	//
	// try {
	// InternalResultsResponse<Menu> internalResponse =
	// getEmpresaBAC().deleteMenu(request);
	// ResponseHandler.handleOperationStatusAndMessages(menuResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, menuResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return menuResponse;
	//
	// }

	/**
	 * Fetch empresa paged.
	 *
	 * @param request
	 *            the request
	 * @return the empresa response
	 */
	@RequestMapping(value = "/doisValores/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public DoisValoresResponse fetchDoisValoresPaged(@RequestBody DoisValoresInquiryRequest request) {
		DoisValoresResponse empresaResponse = new DoisValoresResponse();
		try {
			InternalResultsResponse<DoisValores> internalResponse = getEmpresaBAC().fetchDoisValoressByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(empresaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, empresaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return empresaResponse;
	}

	/**
	 * Insert empresa.
	 *
	 * @param request
	 *            the request
	 * @return the empresa response
	 */
	@RequestMapping(value = "/doisValores/insert", method = RequestMethod.POST)
	@ResponseBody
	public DoisValoresResponse insertDoisValores(@RequestBody DoisValoresMaintenanceRequest request) {
		DoisValoresResponse empresaResponse = new DoisValoresResponse();
		try {
			InternalResultsResponse<DoisValores> internalResponse = getEmpresaBAC().insertDoisValores(request);
			ResponseHandler.handleOperationStatusAndMessages(empresaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, empresaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return empresaResponse;
	}

	/**
	 * Update empresa.
	 *
	 * @param request
	 *            the request
	 * @return the empresa response
	 */
	@RequestMapping(value = "/doisValores/update", method = RequestMethod.POST)
	@ResponseBody
	public DoisValoresResponse updateDoisValores(@RequestBody DoisValoresMaintenanceRequest request) {
		DoisValoresResponse empresaResponse = new DoisValoresResponse();
		try {
			InternalResultsResponse<DoisValores> internalResponse = getEmpresaBAC().updateDoisValores(request);
			ResponseHandler.handleOperationStatusAndMessages(empresaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, empresaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return empresaResponse;
	}

	/**
	 * Delete empresa.
	 *
	 * @param request
	 *            the request
	 * @return the empresa response
	 */
	@RequestMapping(value = "/doisValores/delete", method = RequestMethod.POST)
	@ResponseBody
	public DoisValoresResponse deleteDoisValores(@RequestBody DoisValoresMaintenanceRequest request) {
		DoisValoresResponse empresaResponse = new DoisValoresResponse();

		try {
			InternalResultsResponse<DoisValores> internalResponse = getEmpresaBAC().deleteDoisValoresById(request);
			ResponseHandler.handleOperationStatusAndMessages(empresaResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, empresaResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return empresaResponse;

	}

	/**
	 * Fetch filial paged.
	 *
	 * @param request
	 *            the request
	 * @return the filial response
	 */
	@RequestMapping(value = "/note/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public NoteResponse fetchNotePaged(@RequestBody PagedInquiryRequest request) {
		NoteResponse filialResponse = new NoteResponse();
		try {
			InternalResultsResponse<Note> internalResponse = getEmpresaBAC().fetchNotesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;
	}

	/**
	 * Insert filial.
	 *
	 * @param request
	 *            the request
	 * @return the filial response
	 */
	@RequestMapping(value = "/note/insert", method = RequestMethod.POST)
	@ResponseBody
	public NoteResponse insertNote(@RequestBody NoteMaintenanceRequest request) {
		NoteResponse filialResponse = new NoteResponse();
		try {
			InternalResultsResponse<Note> internalResponse = getEmpresaBAC().insertNote(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;
	}

	/**
	 * Update filial.
	 *
	 * @param request
	 *            the request
	 * @return the filial response
	 */
	@RequestMapping(value = "/note/update", method = RequestMethod.POST)
	@ResponseBody
	public NoteResponse updateNote(@RequestBody NoteMaintenanceRequest request) {
		NoteResponse filialResponse = new NoteResponse();
		try {
			InternalResultsResponse<Note> internalResponse = getEmpresaBAC().updateNote(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;
	}

	/**
	 * Delete filial.
	 *
	 * @param request
	 *            the request
	 * @return the filial response
	 */
	@RequestMapping(value = "/note/delete", method = RequestMethod.POST)
	@ResponseBody
	public NoteResponse deleteNote(@RequestBody NoteMaintenanceRequest request) {
		NoteResponse filialResponse = new NoteResponse();

		try {
			InternalResultsResponse<Note> internalResponse = getEmpresaBAC().deleteNote(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;

	}

	/**
	 * Fetch filial paged.
	 *
	 * @param request
	 *            the request
	 * @return the filial response
	 */
	@RequestMapping(value = "/status/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public StatusResponse fetchStatusPaged(@RequestBody PagedInquiryRequest request) {
		StatusResponse filialResponse = new StatusResponse();
		try {
			InternalResultsResponse<Status> internalResponse = getEmpresaBAC().fetchStatussByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;
	}

	/**
	 * Insert filial.
	 *
	 * @param request
	 *            the request
	 * @return the filial response
	 */
	@RequestMapping(value = "/status/insert", method = RequestMethod.POST)
	@ResponseBody
	public StatusResponse insertStatus(@RequestBody StatusMaintenanceRequest request) {
		StatusResponse filialResponse = new StatusResponse();
		try {
			InternalResultsResponse<Status> internalResponse = getEmpresaBAC().insertStatus(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;
	}

	/**
	 * Update filial.
	 *
	 * @param request
	 *            the request
	 * @return the filial response
	 */
	@RequestMapping(value = "/status/update", method = RequestMethod.POST)
	@ResponseBody
	public StatusResponse updateStatus(@RequestBody StatusMaintenanceRequest request) {
		StatusResponse filialResponse = new StatusResponse();
		try {
			InternalResultsResponse<Status> internalResponse = getEmpresaBAC().updateStatus(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;
	}

	/**
	 * Delete filial.
	 *
	 * @param request
	 *            the request
	 * @return the filial response
	 */
	@RequestMapping(value = "/status/delete", method = RequestMethod.POST)
	@ResponseBody
	public StatusResponse deleteStatus(@RequestBody StatusMaintenanceRequest request) {
		StatusResponse filialResponse = new StatusResponse();

		try {
			InternalResultsResponse<Status> internalResponse = getEmpresaBAC().deleteStatus(request);
			ResponseHandler.handleOperationStatusAndMessages(filialResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, filialResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return filialResponse;

	}

}
