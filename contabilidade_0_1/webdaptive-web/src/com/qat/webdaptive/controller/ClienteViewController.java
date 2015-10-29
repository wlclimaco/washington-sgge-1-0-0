package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class ProcedureViewController.
 */
@Controller
@RequestMapping("/cliente")
public class ClienteViewController extends ClienteBaseController
{

	// /** The Constant PROCEDURE_MVC_BAS_RETURN. */
	// private static final String PROCEDURE_MVC_BAS_RETURN = "/cliente/cliente_mvc_bas";
	//
	// private static final String CADASTRO_MVC_BAS_RETURN = "/cliente/cadCli";
	//
	// /** The Constant ZERO. */
	// private static final int ZERO = 0;
	//
	// /** The Constant TWENTY. */
	// private static final int TWENTY = 20;
	//
	// // ================ MENU ========================
	// private static final String MOBILE = "mobile";
	// private static final String CLIENTE_RESPONSE = "clienteResponse";
	// private static final String CLIENTE_MVC_RETURN = "/cliente/cliente_mvc";
	// private static final String CLIENTE_MVC_M_RETURN = "/mobile/cliente/cliente_mvc.m";
	//
	// // ==============================================
	//
	// /**
	// * Fetch clientesby request bas.
	// *
	// * @return the model and view
	// */
	// @RequestMapping(value = "/fetchClientesByRequestBAS", method = RequestMethod.GET)
	// public ModelAndView fetchProceduresbyRequestBAS()
	// {
	// PagedInquiryRequest request = new PagedInquiryRequest();
	// // we will be paging so set the default values
	// request.setStartPage(ZERO);
	// request.setPageSize(TWENTY);
	// request.setPreQueryCount(true);
	// return clienteMAV(request, PROCEDURE_MVC_BAS_RETURN);
	// }
	//
	// @RequestMapping(value = "/cadastroClientesByRequestBAS", method = RequestMethod.GET)
	// public ModelAndView cadastroClientesByRequestBAS()
	// {
	// PagedInquiryRequest request = new PagedInquiryRequest();
	// // we will be paging so set the default values
	// request.setStartPage(ZERO);
	// request.setPageSize(TWENTY);
	// request.setPreQueryCount(true);
	// return cadastroMAV(request, CADASTRO_MVC_BAS_RETURN);
	// }
	//
	// @RequestMapping(value = "/fetchClientes", method = RequestMethod.GET)
	// public ModelAndView fetchAllSupermercadosMVC(@RequestParam String view)
	// {
	// // if mobile send back mobile view
	// if (view.equalsIgnoreCase(MOBILE))
	// {
	// return new ModelAndView(CLIENTE_MVC_M_RETURN, CLIENTE_RESPONSE, clienteBEFetchAll(true,
	// new FetchAllRequest()));
	// }
	// return null;
	// }

}
