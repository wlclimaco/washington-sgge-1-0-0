package com.sensus.mlc.wui.almoxarifado;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.almoxarifado.bcf.IAlmoxarifadoBCF;
import com.sensus.mlc.almoxarifado.model.request.AlmoxarifadoRequest;
import com.sensus.mlc.almoxarifado.model.request.InquiryAlmoxarifadoRequest;
import com.sensus.mlc.almoxarifado.model.response.AlmoxarifadoResponse;
import com.sensus.mlc.almoxarifado.model.response.InquiryAlmoxarifadoResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/**
 * @author QATEmployee
 *
 */
@Controller
@RequestMapping("/api/almoxarifado")
public class AlmoxarifadoAPIController extends BaseController
{

	/*
	 * URLs Mapping
	 */
	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/fetch";

	private static final String MAP_FETCHALL = "/fetchall";

	/** The Constant MAP_DELETE. */
	private static final String MAP_DELETE = "/delete";

	/** The Constant MAP_INSERT. */
	private static final String MAP_INSERT = "/insert";

	/** The Constant MAP_INSERT. */
	private static final String MAP_UPDATE = "/update";


	private IAlmoxarifadoBCF almoxarifadoBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "TagAPIController";



	public IAlmoxarifadoBCF getAlmoxarifadoBCF() {
		return almoxarifadoBCF;
	}

	public void setAlmoxarifadoBCF(IAlmoxarifadoBCF almoxarifadoBCF) {
		this.almoxarifadoBCF = almoxarifadoBCF;
	}






	@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST)
	@ResponseBody
	public Response insert(@RequestBody AlmoxarifadoRequest almoxarifadoRequest, HttpServletRequest request)
	{

		AlmoxarifadoResponse response = new AlmoxarifadoResponse();
		try
		{
			setUserContext(almoxarifadoRequest, request);

			response = getAlmoxarifadoBCF().insertAlmoxarifado(almoxarifadoRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}



	/**
	 * Update filial.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */

	@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public Response updateAlmoxarifado(@RequestBody AlmoxarifadoRequest almoxarifadoRequest, HttpServletRequest request)
	{

		AlmoxarifadoResponse response = new AlmoxarifadoResponse();
		try
		{
			setUserContext(almoxarifadoRequest, request);

			response = getAlmoxarifadoBCF().updateAlmoxarifado(almoxarifadoRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}


	/**
	 * Delete filial.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */

	@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public Response deleteAlmoxarifado(@RequestBody AlmoxarifadoRequest almoxarifadoRequest, HttpServletRequest request)
	{

		AlmoxarifadoResponse response = new AlmoxarifadoResponse();
		try
		{
			setUserContext(almoxarifadoRequest, request);

			response = getAlmoxarifadoBCF().deleteAlmoxarifado(almoxarifadoRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}

	/**
	 * Fetch all filial.
	 *
	 * @param inquiryfilialRequest the inquiryfilial request
	 * @return the inquiry filial response
	 */

	@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody InquiryAlmoxarifadoRequest inquiryAlmoxarifadoRequest, HttpServletRequest request)
	{

		InquiryAlmoxarifadoResponse response = new InquiryAlmoxarifadoResponse();
		try
		{
			setUserContext(inquiryAlmoxarifadoRequest, request);

			response = getAlmoxarifadoBCF().fetchAllAlmoxarifado(inquiryAlmoxarifadoRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}

	/**
	 * Fetch filial by id.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */

	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody AlmoxarifadoRequest almoxarifadoRequest, HttpServletRequest request)
	{

		AlmoxarifadoResponse response = new AlmoxarifadoResponse();
		try
		{
			setUserContext(almoxarifadoRequest, request);

			response = getAlmoxarifadoBCF().fetchAlmoxarifadoById(almoxarifadoRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}



}
