package com.sensus.mlc.wui.empresa;

import java.util.Map;

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
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.empresa.bcf.IEmpresaBCF;
import com.sensus.mlc.empresa.model.request.EmpresaRequest;
import com.sensus.mlc.empresa.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.empresa.model.response.EmpresaResponse;
import com.sensus.mlc.empresa.model.response.InquiryEmpresaResponse;
import com.sensus.mlc.wui.BaseController;

/**
 * @author QATEmployee
 *
 */
@Controller
@RequestMapping("/api/empresa")
public class EmpresaAPIController extends BaseController
{

	/*
	 * URLs Mapping
	 */
	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCHALL = "/fetchAll";

	private static final String MAP_FETCH = "/fetch";

	/** The Constant MAP_DELETE. */
	private static final String MAP_DELETE = "/delete";

	/** The Constant MAP_INSERT. */
	private static final String MAP_INSERT = "/insert";

	private static final String MAP_UPDATE = "/update";


	private IEmpresaBCF empresaBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EmpresaAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "EmpresaAPIController";



	public IEmpresaBCF getEmpresaBCF() {
		return empresaBCF;
	}
	@Resource
	public void setEmpresaBCF(IEmpresaBCF empresaBCF) {
		this.empresaBCF = empresaBCF;
	}


	/**
	 * Fetch.
	 *
	 * @param inquiryTagRequest the inquiry tag request
	 * @param request the request
	 * @return the inquiry tag response
	 */
	@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchall(@RequestBody InquiryEmpresaRequest inquiryEmpresaRequest, HttpServletRequest request)
	{

		InquiryEmpresaResponse response = new InquiryEmpresaResponse();
		try
		{
			setUserContext(inquiryEmpresaRequest, request);

			response = getEmpresaBCF().fetchAllEmpresa(inquiryEmpresaRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}

	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody EmpresaRequest empresaRequest, HttpServletRequest request,String type)
	{

		EmpresaResponse response = new EmpresaResponse();
		try
		{

			setUserContext(empresaRequest, request);
			if(type.equals("byId")){
				response = getEmpresaBCF().fetchEmpresaById(empresaRequest);
			}else if(type.equals("EmpresaFilial")){
				response = getEmpresaBCF().fetchAllEmpresaTypes(empresaRequest);
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}


	/**
	 * Delete tag.
	 *
	 * @param tagRequest the tag request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public Response delete(@RequestBody EmpresaRequest empresaRequest, HttpServletRequest request)
	{

		EmpresaResponse response = new EmpresaResponse();
		try
		{
			setUserContext(empresaRequest, request);

			response = getEmpresaBCF().deleteEmpresa(empresaRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});

		}

		return response;
	}

	/**
	 * Adds the tag.
	 *
	 * @param tagRequest the tag request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST)
	@ResponseBody
	public Response addempresa(@RequestBody EmpresaRequest empresaRequest, HttpServletRequest request)
	{
		EmpresaResponse response = new EmpresaResponse();
		try
		{
			setUserContext(empresaRequest, request);

			response = getEmpresaBCF().insertEmpresa(empresaRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});

		}

		return response;
	}

	@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public Response updateEmpresa (@RequestBody EmpresaRequest empresaRequest, HttpServletRequest request)
	{
		EmpresaResponse response = new EmpresaResponse();
		try
		{
			setUserContext(empresaRequest, request);

			response = getEmpresaBCF().updateEmpresa(empresaRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});

		}

		return response;
	}



}
