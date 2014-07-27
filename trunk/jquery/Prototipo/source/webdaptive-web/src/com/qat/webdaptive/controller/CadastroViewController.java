package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.EmbalagemInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
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

	// ================ MENU ========================
	private static final String MOBILE = "mobile";
	private static final String CADASTRO_RESPONSE = "cadastroResponse";
	private static final String MENU_MVC_RETURN = "/cadastro/counties_mvc";
	private static final String MENU_MVC_M_RETURN = "/mobile/cadastro/menu_mvc.m";
	// ==============================================

	// ================ SUBMENU ========================
	private static final String SUBMENU_MVC_RETURN = "/cadastro/counties_mvc";
	private static final String SUBMENU_MVC_M_RETURN = "/mobile/cadastro/submenu_mvc.m";
	// ==============================================

	// ================ TRIMENU ========================
	private static final String TRIMENU_MVC_RETURN = "/cadastro/counties_mvc";
	private static final String TRIMENU_MVC_M_RETURN = "/mobile/cadastro/trimenu_mvc.m";
	// ==============================================

	// ================ MARCA ========================
	private static final String MARCA_MVC_RETURN = "/cadastro/counties_mvc";
	private static final String MARCA_MVC_M_RETURN = "/mobile/cadastro/marca_mvc.m";
	// ==============================================

	// ================ UNIMED ========================
	private static final String EMBALAGEM_RESPONSE = "embalagemResponse";
	private static final String UNIMED_MVC_RETURN = "/cadastro/counties_mvc";
	private static final String UNIMED_MVC_M_RETURN = "/mobile/cadastro/unimed_mvc.m";
	// ==============================================

	// ================ EMBALAGEM ========================
	private static final String EMBALAGEM_MVC_RETURN = "/cadastro/counties_mvc";
	private static final String EMBALAGEM_MVC_M_RETURN = "/mobile/cadastro/embalagem_mvc.m";
	// ==============================================

	// ================ PRODUTO ========================
	private static final String PRODUTO_RESPONSE = "produtoResponse";
	private static final String PRODUTO_MVC_RETURN = "/cadastro/counties_mvc";
	private static final String PRODUTO_MVC_M_RETURN = "/mobile/cadastro/produto_mvc.m";
	// =====================================================
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant TWENTY. */
	private static final int TWENTY = 20;

	@RequestMapping(value = "/fetchMenus", method = RequestMethod.GET)
	public ModelAndView fetchAllMenusMVC(@RequestParam String view)
	{
		// if mobile send back mobile view
		if (view.equalsIgnoreCase(MOBILE))
		{
			CadastroInquiryRequest request = new CadastroInquiryRequest();
			request.setCadastro(new Cadastro(0, TableTypeEnum.MENU));
			return new ModelAndView(MENU_MVC_M_RETURN, CADASTRO_RESPONSE, cadastroBEFetchAll(true, request));
		}
		return null;
	}

	@RequestMapping(value = "/fetchSubMenus", method = RequestMethod.GET)
	public ModelAndView fetchAllSubMenusMVC(@RequestParam String view)
	{
		// if mobile send back mobile view
		if (view.equalsIgnoreCase(MOBILE))
		{
			CadastroInquiryRequest request = new CadastroInquiryRequest();
			request.setCadastro(new Cadastro(0, TableTypeEnum.SUBMENU));
			return new ModelAndView(SUBMENU_MVC_M_RETURN, CADASTRO_RESPONSE, cadastroBEFetchAll(true, request));
		}
		return null;
	}

	@RequestMapping(value = "/fetchTriMenus", method = RequestMethod.GET)
	public ModelAndView fetchAllTriMenusMVC(@RequestParam String view)
	{
		// if mobile send back mobile view
		if (view.equalsIgnoreCase(MOBILE))
		{
			CadastroInquiryRequest request = new CadastroInquiryRequest();
			request.setCadastro(new Cadastro(0, TableTypeEnum.TRIMENU));
			return new ModelAndView(TRIMENU_MVC_M_RETURN, CADASTRO_RESPONSE, cadastroBEFetchAll(true, request));
		}
		return null;
	}

	@RequestMapping(value = "/fetchMarcas", method = RequestMethod.GET)
	public ModelAndView fetchAllMarcasMVC(@RequestParam String view)
	{
		// if mobile send back mobile view
		if (view.equalsIgnoreCase(MOBILE))
		{
			CadastroInquiryRequest request = new CadastroInquiryRequest();
			request.setCadastro(new Cadastro(0, TableTypeEnum.MARCA));
			return new ModelAndView(MARCA_MVC_M_RETURN, CADASTRO_RESPONSE, cadastroBEFetchAll(true, request));
		}
		return null;
	}

	@RequestMapping(value = "/fetchUniMeds", method = RequestMethod.GET)
	public ModelAndView fetchAllUniMedsMVC(@RequestParam String view)
	{
		// if mobile send back mobile view
		if (view.equalsIgnoreCase(MOBILE))
		{
			EmbalagemInquiryRequest request = new EmbalagemInquiryRequest();
			return new ModelAndView(UNIMED_MVC_M_RETURN, EMBALAGEM_RESPONSE, uniMedBEFetchAll(true, request));
		}
		return null;
	}

	@RequestMapping(value = "/fetchEmbalagems", method = RequestMethod.GET)
	public ModelAndView fetchAllEmbalagemsMVC(@RequestParam String view)
	{
		// if mobile send back mobile view
		if (view.equalsIgnoreCase(MOBILE))
		{
			EmbalagemInquiryRequest request = new EmbalagemInquiryRequest();
			return new ModelAndView(EMBALAGEM_MVC_M_RETURN, EMBALAGEM_RESPONSE, embalagemBEFetchAll(true, request));
		}
		return null;
	}

	@RequestMapping(value = "/fetchProdutos", method = RequestMethod.GET)
	public ModelAndView fetchAllProdutosMVC(@RequestParam String view)
	{
		// if mobile send back mobile view
		if (view.equalsIgnoreCase(MOBILE))
		{
			ProdutoInquiryRequest request = new ProdutoInquiryRequest();
			return new ModelAndView(PRODUTO_MVC_M_RETURN, PRODUTO_RESPONSE, produtoBEFetchAll(true, request));
		}
		return null;
	}

	/**
	 * Fetch proceduresby request bas.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "/fetcMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetcMenusByRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(MENU_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchSubMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchSubMenusByRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(SUBMENU_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchTriMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchTriMenusByRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(TRIMENU_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchUniMEdByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchUniMEdByRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(UNIMED_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchMarcasByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchMarcasByRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(MARCA_MVC_BAS_RETURN);
		return modelAndView;
	}
}
