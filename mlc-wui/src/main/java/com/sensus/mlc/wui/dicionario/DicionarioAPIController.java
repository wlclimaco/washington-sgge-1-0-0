package com.sensus.mlc.wui.dicionario;

import javax.annotation.Resource;
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
import com.sensus.mlc.dicionario.bcf.IDicionarioBCF;
import com.sensus.mlc.dicionario.model.request.InquiryTelaRequest;
import com.sensus.mlc.dicionario.model.request.TelaRequest;
import com.sensus.mlc.dicionario.model.response.InquiryTelaResponse;
import com.sensus.mlc.dicionario.model.response.TelaResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/**
 * @author QATEmployee
 *
 */
@Controller
@RequestMapping("/api/dicionario")
public class DicionarioAPIController extends BaseController
{

	/*
	 * URLs Mapping
	 */
	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/fetch";

	private static final String MAP_FETCHALL = "/fetchAll";

	/** The Constant MAP_DELETE. */
	private static final String MAP_DELETE = "/delete";

	/** The Constant MAP_INSERT. */
	private static final String MAP_INSERT = "/insert";

	/** The Constant MAP_UPDATE_AUTO_GROUP. */
	private static final String MAP_UPDATE = "/update";

	private IDicionarioBCF dicionarioBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "TagAPIController";




	public IDicionarioBCF getDicionarioBCF() {
		return dicionarioBCF;
	}
	@Resource
	public void setDicionarioBCF(IDicionarioBCF dicionarioBCF) {
		this.dicionarioBCF = dicionarioBCF;
	}




	/**
	 * Insert dicionario.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the group response
	 */

	@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST)
	@ResponseBody
	public Response insertTela(@RequestBody TelaRequest dicionarioRequest, HttpServletRequest request)
	{

		TelaResponse response = new TelaResponse();
		try
		{
			setUserContext(dicionarioRequest, request);

			response = getDicionarioBCF().insertTela(dicionarioRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}

	/**
	 * Update dicionario.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the group response
	 */

	@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public Response updateTela(@RequestBody TelaRequest dicionarioRequest, HttpServletRequest request)
	{

		TelaResponse response = new TelaResponse();
		try
		{
			setUserContext(dicionarioRequest, request);

			response = getDicionarioBCF().updateTela(dicionarioRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}

	/**
	 * Delete dicionario.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the Dicionario response
	 */

	@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public Response deleteTela(@RequestBody TelaRequest dicionarioRequest, HttpServletRequest request)
	{

		TelaResponse response = new TelaResponse();
		try
		{
			setUserContext(dicionarioRequest, request);

			response = getDicionarioBCF().deleteTela(dicionarioRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}

	/**
	 * Fetch all dicionarios.
	 *
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the inquiry dicionario response
	 */

	@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchAllTelas(@RequestBody InquiryTelaRequest inquiryDicionarioRequest, HttpServletRequest request)
	{

		InquiryTelaResponse response = new InquiryTelaResponse();
		try
		{
			setUserContext(inquiryDicionarioRequest, request);

			response = getDicionarioBCF().fetchAllTelas(inquiryDicionarioRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}


	/**
	 * Fetch dicionario by id.
	 *
	 * @param DicionarioRequest the Dicionario request
	 * @return the Dicionario response
	 */

	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchTelaById(@RequestBody TelaRequest inquiryDicionarioRequest, HttpServletRequest request)
	{

		Response response = new Response();
		try
		{
			setUserContext(inquiryDicionarioRequest, request);

			response = getDicionarioBCF().fetchTelaById(inquiryDicionarioRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}
}
