package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.ServicoResponse;

/**
 * The Class ServicoAPIController.
 */
@Controller
@RequestMapping("/site/api")
public class SiteAPIController extends SiteBaseController
{

	/**
	 * Insert bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/insertBAS", method = RequestMethod.POST)
	@ResponseBody
	public ServicoResponse insertBAS(@RequestBody ServicoMaintenanceRequest request)
	{
		return maintainServico(request, PersistanceActionEnum.INSERT);
	}

	/**
	 * Update bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/updateBAS", method = RequestMethod.POST)
	@ResponseBody
	public ServicoResponse updateBAS(@RequestBody ServicoMaintenanceRequest request)
	{
		return maintainServico(request, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Delete bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/deleteBAS", method = RequestMethod.POST)
	@ResponseBody
	public ServicoResponse deleteBAS(@RequestBody ServicoMaintenanceRequest request)
	{
		return maintainServico(request, PersistanceActionEnum.DELETE);
	}

	/**
	 * Fetch by request bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/fetchByRequestBAS", method = RequestMethod.POST)
	@ResponseBody
	public ServicoResponse fetchByRequestBAS(@RequestBody PagedInquiryRequest request)
	{
		return supermercadoFetchByRequest(request);
	}
}
