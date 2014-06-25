package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.supermercado.model.request.SupermercadoMaintenanceRequest;
import com.qat.samples.sysmgmt.supermercado.model.response.SupermercadoResponse;

/**
 * The Class SupermercadoAPIController.
 */
@Controller
@RequestMapping("/supermercado/api")
public class SupermercadoAPIController extends SupermercadoBaseController
{

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
}
