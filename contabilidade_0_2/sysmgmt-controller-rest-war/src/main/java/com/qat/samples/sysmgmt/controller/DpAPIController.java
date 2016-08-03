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
import com.qat.samples.sysmgmt.bac.Dp.IDpBAC;
import com.qat.samples.sysmgmt.beneficios.model.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosMaintenanceRequest;
import com.qat.samples.sysmgmt.beneficios.model.response.BeneficiosResponse;
import com.qat.samples.sysmgmt.dp.model.Eventos;
import com.qat.samples.sysmgmt.dp.model.HorarioFunc;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.EventosMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.response.EventoResponse;
import com.qat.samples.sysmgmt.dp.model.response.HorarioFuncResponse;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class DpAPIController.
 */
@Controller
@RequestMapping("/dp/api")
public class DpAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.dpcontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DpAPIController.class);

	/** The dp bac. */
	private IDpBAC dpBAC; // injected by @Resource

	/**
	 * Gets the dp bac.
	 *
	 * @return the dp bac
	 */
	public IDpBAC getDpBAC() {
		return dpBAC;
	}

	/**
	 * Sets the dp bac.
	 *
	 * @param dpBAC
	 *            the new dp bac
	 */
	@Resource
	public void setDpBAC(IDpBAC dpBAC) {
		this.dpBAC = dpBAC;
	}

	// ===================================### EVENTOS
	// ####======================================
	/**
	 * Refresh eventoss.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the eventos response
	 */
	@RequestMapping(value = "/eventos/refresh", method = RequestMethod.GET)
	@ResponseBody
	public EventoResponse refreshEventoss(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		EventoResponse eventosResponse = new EventoResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().refreshEventoss(request);
			ResponseHandler.handleOperationStatusAndMessages(eventosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, eventosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return eventosResponse;

	}

	/**
	 * Fetch eventos paged.
	 *
	 * @param request
	 *            the request
	 * @return the eventos response
	 */
	@RequestMapping(value = "/eventos/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public EventoResponse fetchEventosPaged(@RequestBody EventoInquiryRequest request) {
		EventoResponse eventosResponse = new EventoResponse();
		try {
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().fetchEventossByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(eventosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, eventosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return eventosResponse;
	}

	/**
	 * Insert eventos.
	 *
	 * @param request
	 *            the request
	 * @return the eventos response
	 */
	@RequestMapping(value = "/eventos/insert", method = RequestMethod.POST)
	@ResponseBody
	public EventoResponse insertEventos(@RequestBody EventosMaintenanceRequest request) {
		EventoResponse eventosResponse = new EventoResponse();
		try {
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().insertEventos(request);
			ResponseHandler.handleOperationStatusAndMessages(eventosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, eventosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return eventosResponse;
	}

	/**
	 * Update eventos.
	 *
	 * @param request
	 *            the request
	 * @return the eventos response
	 */
	@RequestMapping(value = "/eventos/update", method = RequestMethod.POST)
	@ResponseBody
	public EventoResponse updateEventos(@RequestBody EventosMaintenanceRequest request) {
		EventoResponse eventosResponse = new EventoResponse();
		try {
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().updateEventos(request);
			ResponseHandler.handleOperationStatusAndMessages(eventosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, eventosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return eventosResponse;
	}

	/**
	 * Delete eventos.
	 *
	 * @param request
	 *            the request
	 * @return the eventos response
	 */
	@RequestMapping(value = "/eventos/delete", method = RequestMethod.POST)
	@ResponseBody
	public EventoResponse deleteEventos(@RequestBody EventosMaintenanceRequest request) {
		EventoResponse eventosResponse = new EventoResponse();

		try {
			InternalResultsResponse<Eventos> internalResponse = getDpBAC().deleteEventos(request);
			ResponseHandler.handleOperationStatusAndMessages(eventosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, eventosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return eventosResponse;

	}

	// ===================================### BENEFICIOS
	// ####======================================
	/**
	 * Refresh beneficioss.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the beneficios response
	 */
	@RequestMapping(value = "/beneficios/refresh", method = RequestMethod.GET)
	@ResponseBody
	public BeneficiosResponse refreshBeneficioss(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		BeneficiosResponse beneficiosResponse = new BeneficiosResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().refreshBeneficioss(request);
			ResponseHandler.handleOperationStatusAndMessages(beneficiosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, beneficiosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return beneficiosResponse;

	}

	/**
	 * Fetch beneficios paged.
	 *
	 * @param request
	 *            the request
	 * @return the beneficios response
	 */
	@RequestMapping(value = "/beneficios/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public BeneficiosResponse fetchBeneficiosPaged(@RequestBody BeneficiosInquiryRequest request) {
		BeneficiosResponse beneficiosResponse = new BeneficiosResponse();
		try {
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().fetchBeneficiossByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(beneficiosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, beneficiosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return beneficiosResponse;
	}

	/**
	 * Insert beneficios.
	 *
	 * @param request
	 *            the request
	 * @return the beneficios response
	 */
	@RequestMapping(value = "/beneficios/insert", method = RequestMethod.POST)
	@ResponseBody
	public BeneficiosResponse insertBeneficios(@RequestBody BeneficiosMaintenanceRequest request) {
		BeneficiosResponse beneficiosResponse = new BeneficiosResponse();
		try {
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().insertBeneficios(request);
			ResponseHandler.handleOperationStatusAndMessages(beneficiosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, beneficiosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return beneficiosResponse;
	}

	/**
	 * Update beneficios.
	 *
	 * @param request
	 *            the request
	 * @return the beneficios response
	 */
	@RequestMapping(value = "/beneficios/update", method = RequestMethod.POST)
	@ResponseBody
	public BeneficiosResponse updateBeneficios(@RequestBody BeneficiosMaintenanceRequest request) {
		BeneficiosResponse beneficiosResponse = new BeneficiosResponse();
		try {
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().updateBeneficios(request);
			ResponseHandler.handleOperationStatusAndMessages(beneficiosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, beneficiosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return beneficiosResponse;
	}

	/**
	 * Delete beneficios.
	 *
	 * @param request
	 *            the request
	 * @return the beneficios response
	 */
	@RequestMapping(value = "/beneficios/delete", method = RequestMethod.POST)
	@ResponseBody
	public BeneficiosResponse deleteBeneficios(@RequestBody BeneficiosMaintenanceRequest request) {
		BeneficiosResponse beneficiosResponse = new BeneficiosResponse();

		try {
			InternalResultsResponse<Beneficios> internalResponse = getDpBAC().deleteBeneficios(request);
			ResponseHandler.handleOperationStatusAndMessages(beneficiosResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, beneficiosResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return beneficiosResponse;

	}

	// ===================================### HORAFUNC
	// ####======================================
	/**
	 * Refresh horafuncs.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the horafunc response
	 */
	@RequestMapping(value = "/horaFunc/refresh", method = RequestMethod.GET)
	@ResponseBody
	public HorarioFuncResponse refreshHorarioFuncs(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		HorarioFuncResponse horafuncResponse = new HorarioFuncResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<HorarioFunc> internalResponse = getDpBAC().refreshHoraFuncs(request);
			ResponseHandler.handleOperationStatusAndMessages(horafuncResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, horafuncResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return horafuncResponse;

	}

	/**
	 * Fetch horafunc paged.
	 *
	 * @param request
	 *            the request
	 * @return the horafunc response
	 */
	@RequestMapping(value = "/horaFunc/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public HorarioFuncResponse fetchHorarioFuncPaged(@RequestBody HoraFuncInquiryRequest request) {
		HorarioFuncResponse horafuncResponse = new HorarioFuncResponse();
		try {
			InternalResultsResponse<HorarioFunc> internalResponse = getDpBAC().fetchHoraFuncsByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(horafuncResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, horafuncResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return horafuncResponse;
	}

	/**
	 * Insert horafunc.
	 *
	 * @param request
	 *            the request
	 * @return the horafunc response
	 */
	@RequestMapping(value = "/horaFunc/insert", method = RequestMethod.POST)
	@ResponseBody
	public HorarioFuncResponse insertHorarioFunc(@RequestBody HoraFuncMaintenanceRequest request) {
		HorarioFuncResponse horafuncResponse = new HorarioFuncResponse();
		try {
			InternalResultsResponse<HorarioFunc> internalResponse = getDpBAC().insertHoraFunc(request);
			ResponseHandler.handleOperationStatusAndMessages(horafuncResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, horafuncResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return horafuncResponse;
	}

	/**
	 * Update horafunc.
	 *
	 * @param request
	 *            the request
	 * @return the horafunc response
	 */
	@RequestMapping(value = "/horaFunc/update", method = RequestMethod.POST)
	@ResponseBody
	public HorarioFuncResponse updateHorarioFunc(@RequestBody HoraFuncMaintenanceRequest request) {
		HorarioFuncResponse horafuncResponse = new HorarioFuncResponse();
		try {
			InternalResultsResponse<HorarioFunc> internalResponse = getDpBAC().updateHoraFunc(request);
			ResponseHandler.handleOperationStatusAndMessages(horafuncResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, horafuncResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return horafuncResponse;
	}

	/**
	 * Delete horafunc.
	 *
	 * @param request
	 *            the request
	 * @return the horafunc response
	 */
	@RequestMapping(value = "/horaFunc/delete", method = RequestMethod.POST)
	@ResponseBody
	public HorarioFuncResponse deleteHorarioFunc(@RequestBody HoraFuncMaintenanceRequest request) {
		HorarioFuncResponse horafuncResponse = new HorarioFuncResponse();

		try {
			InternalResultsResponse<HorarioFunc> internalResponse = getDpBAC().deleteHoraFunc(request);
			ResponseHandler.handleOperationStatusAndMessages(horafuncResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, horafuncResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return horafuncResponse;

	}

}
