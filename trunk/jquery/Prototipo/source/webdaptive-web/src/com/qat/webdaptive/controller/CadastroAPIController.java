package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.CadastroMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.CadastroResponse;

/**
 * The Class CadastroAPIController.
 */
@Controller
@RequestMapping("/cadastro/api")
public class CadastroAPIController extends CadastroBaseController
{

	/**
	 * Refresh bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/refreshBAS", method = RequestMethod.POST)
	@ResponseBody
	public CadastroResponse refreshBAS(@RequestBody RefreshRequest request)
	{
		return refreshCadastro(request);
	}

	/**
	 * Insert bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/insertBAS", method = RequestMethod.POST)
	@ResponseBody
	public CadastroResponse insertBAS(@RequestBody CadastroMaintenanceRequest request)
	{
		return maintainCadastro(request, PersistanceActionEnum.INSERT);
	}

	/**
	 * Update bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/updateBAS", method = RequestMethod.POST)
	@ResponseBody
	public CadastroResponse updateBAS(@RequestBody CadastroMaintenanceRequest request)
	{
		return maintainCadastro(request, PersistanceActionEnum.UPDATE);
	}

	/**
	 * Delete bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/deleteBAS", method = RequestMethod.POST)
	@ResponseBody
	public CadastroResponse deleteBAS(@RequestBody CadastroMaintenanceRequest request)
	{
		return maintainCadastro(request, PersistanceActionEnum.DELETE);
	}

	/**
	 * Fetch by request bas.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	@RequestMapping(value = "/fetchByRequestBAS", method = RequestMethod.POST)
	@ResponseBody
	public CadastroResponse fetchByRequestBAS(@RequestBody CadastroInquiryRequest request)
	{
		return cadastroFetchByRequest(request);
	}
}
