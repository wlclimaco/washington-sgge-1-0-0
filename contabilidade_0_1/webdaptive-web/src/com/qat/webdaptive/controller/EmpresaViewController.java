package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;

/**
 * The Class CidadeViewController.
 */
@Controller
@RequestMapping("/empresa")
public class EmpresaViewController extends EntidadeBaseController
{

	/** The Constant PROCEDURE_MVC_BAS_RETURN. */
	private static final String PROCEDURE_MVC_BAS_RETURN = "/empresa/empresa_main";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant TWENTY. */
	private static final int TWENTY = 20;

	/**
	 * Fetch cidadesby request bas.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "/fetchEmpresasByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchEmpresabyRequestBAS()
	{
		EmpresaInquiryRequest request = new EmpresaInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return empresaMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

}
