package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class ProdutoViewController.
 */
@Controller
@RequestMapping("/produto")
public class ProdutoViewController extends ProdutoBaseController
{

	/** The Constant PROCEDURE_MVC_BAS_RETURN. */
	private static final String PROCEDURE_MVC_BAS_RETURN = "/produto/produto_mvc_bas";

	private static final String FOTO_MVC_BAS_RETURN = "/fotos/fotos_mvc_bas";

	private static final String CADASTRO_MVC_BAS_RETURN = "/produto/cadProduto";

	private static final String DIALOG_MVC_BAS_RETURN = "/produto/produto_dialog";

	private static final String EMBALAGEM_MVC_BAS_RETURN = "/cadastro/embalagem_mvc_bas";

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
		ModelAndView modelAndView = new ModelAndView(PROCEDURE_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/cadastroProdutosByRequestBAS", method = RequestMethod.GET)
	public ModelAndView cadastroProdutosByRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(CADASTRO_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/produtosDialogByRequestBAS", method = RequestMethod.GET)
	public ModelAndView produtosDialogByRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(DIALOG_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchMarcasByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchmarcasbyRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(PROCEDURE_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchMenusbyRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(PROCEDURE_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchTriMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchTriMenusbyRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(PROCEDURE_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchSubMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchSubMenusbyRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(PROCEDURE_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchUniMedsByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchUniMedsbyRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(PROCEDURE_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchProdutoByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchProdutobyRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(PROCEDURE_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchFotoByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchFotobyRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(FOTO_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchEmbalagemByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchEmbalagembyRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(EMBALAGEM_MVC_BAS_RETURN);
		return modelAndView;
	}
}
