package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;

/**
 * The Class CidadeViewController.
 */
@Controller
@RequestMapping("/os")
public class OrdemServicoViewController extends SiteBaseController
{

	/** The Constant PROCEDURE_MVC_BAS_RETURN. */
	private static final String PROCEDURE_MVC_BAS_RETURN = "/ordemServico/ordemServico_main";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant TWENTY. */
	private static final int TWENTY = 20;

	/**
	 * Fetch cidadesby request bas.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "/fetchOrdemServicoByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchRegimebyRequestBAS()
	{
		OrdemServicoInquiryRequest request = new OrdemServicoInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return ordemServicoMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

}
