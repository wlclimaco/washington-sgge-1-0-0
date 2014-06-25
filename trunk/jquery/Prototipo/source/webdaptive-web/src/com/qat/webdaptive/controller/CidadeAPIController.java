package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.samples.sysmgmt.cidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.cidade.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Class CidadeAPIController.
 */
@Controller
@RequestMapping("/cidade/api")
public class CidadeAPIController extends CidadeBaseController
{

	/**
	 * Refresh bas.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/refreshBAS", method = RequestMethod.POST)
	@ResponseBody
	public CidadeResponse refreshBAS(@RequestBody RefreshRequest request)
	{
		return refreshCidades(request);
	}

	/**
	 * Insert bas.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/insertBAS", method = RequestMethod.POST)
	@ResponseBody
	public CidadeResponse insertBAS(@RequestBody CidadeMaintenanceRequest request)
	{
		return maintainCidades(request, PersistanceActionEnum.INSERT);
	}

	/**
	 * Update bas.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/updateBAS", method = RequestMethod.POST)
	@ResponseBody
	public CidadeResponse updateBAS(@RequestBody CidadeMaintenanceRequest request)
	{
		return maintainCidades(request, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Delete bas.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/deleteBAS", method = RequestMethod.POST)
	@ResponseBody
	public CidadeResponse deleteBAS(@RequestBody CidadeMaintenanceRequest request)
	{
		return maintainCidades(request, PersistanceActionEnum.DELETE);
	}

	/**
	 * Fetch by request bas.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/fetchByRequestBAS", method = RequestMethod.POST)
	@ResponseBody
	public CidadeResponse fetchByRequestBAS(@RequestBody PagedInquiryRequest request)
	{
		return cidadeFetchByRequest(request);
	}
}
