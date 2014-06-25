package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoViewController.
 */
@Controller
@RequestMapping("/produto")
public class ProdutoViewController extends ProdutoBaseController
{

	/** The Constant PROCEDURE_MVC_BAS_RETURN. */
	private static final String PROCEDURE_MVC_BAS_RETURN = "/produtos/produtos_mvc_bas";

	private static final String FOTO_MVC_BAS_RETURN = "/fotos/fotos_mvc_bas";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant TWENTY. */
	private static final int TWENTY = 20;

	/**
	 * Fetch produtosby request bas.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "/fetchProdutosByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchProdutosbyRequestBAS()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return produtoMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

	@RequestMapping(value = "/fetchMarcasByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchmarcasbyRequestBAS()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return produtoMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

	@RequestMapping(value = "/fetchMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchMenusbyRequestBAS()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return produtoMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

	@RequestMapping(value = "/fetchTriMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchTriMenusbyRequestBAS()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return produtoMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

	@RequestMapping(value = "/fetchSubMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchSubMenusbyRequestBAS()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return produtoMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

	@RequestMapping(value = "/fetchUniMedsByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchUniMedsbyRequestBAS()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return produtoMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

	@RequestMapping(value = "/fetchProdutoByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchProdutobyRequestBAS()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return produtoMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

	@RequestMapping(value = "/fetchFotoByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchFotobyRequestBAS()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return produtoMAV(request, FOTO_MVC_BAS_RETURN);
	}
}
