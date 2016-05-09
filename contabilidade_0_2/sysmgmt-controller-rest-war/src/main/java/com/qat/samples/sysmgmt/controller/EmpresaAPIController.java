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
import com.qat.samples.sysmgmt.condominio.model.response.CondominioResponse;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.response.DepositoResponse;
import com.qat.samples.sysmgmt.entidade.model.response.EmpresaResponse;
import com.qat.samples.sysmgmt.entidade.model.response.FilialResponse;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.response.UsuarioResponse;

/**
 * The Class EmpresaAPIController.
 */
@Controller
@RequestMapping("/empresa/api")
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
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
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
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
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
	 * Insert empresa.
	 *
	 * @param request
	 *            the request
	 * @return the empresa response
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
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
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
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
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
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
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
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
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
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
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
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
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
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
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
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
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
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
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
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
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
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
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
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
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
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
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
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
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
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
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
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
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
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
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
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
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
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

}
