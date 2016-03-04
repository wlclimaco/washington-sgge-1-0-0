package com.qat.webdaptive.controller;

import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;

/**
 * The Class CidadeViewController.
 */
@Controller
@RequestMapping("/funcionario")
public class FuncionarioViewController extends PessoaBaseController
{

	/** The Constant PROCEDURE_MVC_BAS_RETURN. */
	private static final String PROCEDURE_MVC_BAS_RETURN = "/funcionario/funcionario_main";

	/** The Constant EDIT_VIEW. */
	private static final String EDIT_VIEW = "/editView";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant TWENTY. */
	private static final int TWENTY = 20;

	private static final String EMPRESA_ID = "locationId";

	/** The Constant VIEW_EMPRESA_DIALOG_ADD. */
	private static final String VIEW_EMPRESA_DIALOG_ADD = "/funcionario/funcionario_dialog_create";

	/**
	 * Fetch cidadesby request bas.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "/fetchFuncionariosByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchFuncionariobyRequestBAS()
	{
		FuncionarioInquiryRequest request = new FuncionarioInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return funcionarioMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

	@RequestMapping(value = {EDIT_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadViewUpdate(@RequestParam(value = EMPRESA_ID, required = false) Integer locationId,
			HttpRequest request)
	{
		FuncionarioInquiryRequest requestFuncionario = new FuncionarioInquiryRequest();
		return funcionarioMAV(requestFuncionario, VIEW_EMPRESA_DIALOG_ADD);
	}

	@RequestMapping(value = "/addFuncionariosByRequestBAS", method = RequestMethod.GET)
	public ModelAndView addFuncionariosByRequestBAS()
	{

		return new ModelAndView("/empresa/empresa_create");
	}

}
