package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.samples.sysmgmt.entidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.request.EstadoMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.response.EstadoResponse;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.response.CidadeResponse;

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
	public CidadeResponse fetchByRequestBAS(@RequestBody CidadeInquiryRequest request)
	{
		return cidadeFetchByRequest(request);
	}
	@RequestMapping(value = "/estado/fetch", method = RequestMethod.POST)
	@ResponseBody
	public EstadoResponse fetchEstadoByRequestBAS(@RequestBody EstadoInquiryRequest request)
	{
		return estadoFetchByRequest(request);
	}


	//=============
	/**
	 * Refresh bas.
	 *
	 * @param request the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/estado/refreshBAS", method = RequestMethod.POST)
	@ResponseBody
	public EstadoResponse ErefreshBAS(@RequestBody EstadoInquiryRequest request)
	{
		return estadoFetchByRequest(request);
	}

	/**
	 * Insert bas.
	 *
	 * @param request the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/estado/insertBAS", method = RequestMethod.POST)
	@ResponseBody
	public EstadoResponse EinsertBAS(@RequestBody EstadoMaintenanceRequest request)
	{
		return maintainEstados(request, PersistanceActionEnum.INSERT);
	}

	/**
	 * Update bas.
	 *
	 * @param request the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/estado/updateBAS", method = RequestMethod.POST)
	@ResponseBody
	public EstadoResponse EupdateBAS(@RequestBody EstadoMaintenanceRequest request)
	{
		return maintainEstados(request, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Delete bas.
	 *
	 * @param request the request
	 * @return the cidade response
	 */
	@RequestMapping(value = "/estado/deleteBAS", method = RequestMethod.POST)
	@ResponseBody
	public EstadoResponse edeleteBAS(@RequestBody EstadoMaintenanceRequest request)
	{
		return maintainEstados(request, PersistanceActionEnum.DELETE);
	}

}
