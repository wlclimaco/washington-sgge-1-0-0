package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;
import com.qat.samples.sysmgmt.util.TableTypeEnum;

/**
 * The Class SupermercadoViewController.
 */
@Controller
@RequestMapping("/cadastro")
public class CadastroViewController extends CadastroBaseController
{

	/** The Constant SUPERMERCADO_MVC_BAS_RETURN. */
	private static final String MENU_MVC_BAS_RETURN = "/cadastro/menu_mvc_bas";

	private static final String SUBMENU_MVC_BAS_RETURN = "/cadastro/submenu_mvc_bas";

	private static final String TRIMENU_MVC_BAS_RETURN = "/cadastro/trimenu_mvc_bas";

	private static final String UNIMED_MVC_BAS_RETURN = "/cadastro/unimed_mvc_bas";

	private static final String MARCA_MVC_BAS_RETURN = "/cadastro/marca_mvc_bas";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant TWENTY. */
	private static final int TWENTY = 20;

	/**
	 * Fetch proceduresby request bas.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "/fetcMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetcMenusByRequestBAS()
	{
		CadastroInquiryRequest request = new CadastroInquiryRequest();
		// we will be paging so set the default values
		Cadastro cadastro = new Cadastro(TableTypeEnum.MENU);
		request.setCadastro(cadastro);
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return cadastroMAV(request, MENU_MVC_BAS_RETURN);
	}

	@RequestMapping(value = "/fetchSubMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchSubMenusByRequestBAS()
	{
		CadastroInquiryRequest request = new CadastroInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		Cadastro cadastro = new Cadastro(TableTypeEnum.SUBMENU);
		request.setCadastro(cadastro);
		return cadastroMAV(request, SUBMENU_MVC_BAS_RETURN);
	}

	@RequestMapping(value = "/fetchTriMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchTriMenusByRequestBAS()
	{
		CadastroInquiryRequest request = new CadastroInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		Cadastro cadastro = new Cadastro(TableTypeEnum.TRIMENU);
		request.setCadastro(cadastro);
		return cadastroMAV(request, TRIMENU_MVC_BAS_RETURN);
	}

	@RequestMapping(value = "/fetchUniMEdByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchUniMEdByRequestBAS()
	{
		CadastroInquiryRequest request = new CadastroInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		Cadastro cadastro = new Cadastro(TableTypeEnum.UNIMED);
		request.setCadastro(cadastro);
		return cadastroMAV(request, UNIMED_MVC_BAS_RETURN);
	}

	@RequestMapping(value = "/fetchMarcasByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchMarcasByRequestBAS()
	{
		CadastroInquiryRequest request = new CadastroInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		Cadastro cadastro = new Cadastro(TableTypeEnum.MARCA);
		request.setCadastro(cadastro);
		return cadastroMAV(request, MARCA_MVC_BAS_RETURN);
	}
}
