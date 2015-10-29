package com.qat.webdaptive.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATAppContext;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.supermercado.bas.ISupermercadoBAS;
import com.qat.samples.sysmgmt.supermercado.model.request.SupermercadoMaintenanceRequest;
import com.qat.samples.sysmgmt.supermercado.model.response.SupermercadoResponse;

/**
 * The Class SupermercadoAPIController.
 */
@Controller
@RequestMapping("/supermercado/api")
public class SupermercadoAPIController extends SupermercadoBaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SupermercadoBaseController.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.supermercado.defaultexception";

	/** The Constant SUPERMERCADO_RESPONSE. */
	private static final String SUPERMERCADO_RESPONSE = "supermercadoResponse";

	/**
	 * Refresh bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/refreshBAS", method = RequestMethod.POST)
	@ResponseBody
	public SupermercadoResponse refreshBAS(@RequestBody RefreshRequest request)
	{
		return refreshSupermercado(request);
	}

	/**
	 * Insert bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/insertBAS", method = RequestMethod.POST)
	@ResponseBody
	public SupermercadoResponse insertBAS(@RequestBody SupermercadoMaintenanceRequest request)
	{
		return maintainSupermercado(request, PersistanceActionEnum.INSERT);
	}

	/**
	 * Update bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/updateBAS", method = RequestMethod.POST)
	@ResponseBody
	public SupermercadoResponse updateBAS(@RequestBody SupermercadoMaintenanceRequest request)
	{
		return maintainSupermercado(request, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Delete bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/deleteBAS", method = RequestMethod.POST)
	@ResponseBody
	public SupermercadoResponse deleteBAS(@RequestBody SupermercadoMaintenanceRequest request)
	{
		return maintainSupermercado(request, PersistanceActionEnum.DELETE);
	}

	/**
	 * Fetch by request bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/fetchByRequestBAS", method = RequestMethod.POST)
	@ResponseBody
	public SupermercadoResponse fetchByRequestBAS(@RequestBody PagedInquiryRequest request)
	{
		return supermercadoFetchByRequest(request);
	}

	@RequestMapping(value = "/fetchById", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView fetchById(@RequestBody FetchByIdRequest request)
	{

		ModelAndView modelAndView = new ModelAndView("cadSup");
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			modelAndView.addObject(SUPERMERCADO_RESPONSE,
					mapper.writeValueAsString(supermercadoFetchById(request)));
		}
		catch (Exception ex)
		{
			LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
			modelAndView.addObject(SUPERMERCADO_RESPONSE, null);
		}
		return modelAndView;
	}

	protected SupermercadoResponse supermercadoFetchById(FetchByIdRequest request)
	{
		SupermercadoResponse response = new SupermercadoResponse();
		try
		{
			ISupermercadoBAS client = (ISupermercadoBAS)QATAppContext.getBean("supermercadoBASClientTarget");
			response = client.fetchSupermercadoById(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}
}
