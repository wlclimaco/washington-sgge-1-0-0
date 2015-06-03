package com.prosperitasglobal.sendsolv.fornecedor.controller;

import java.util.logging.Logger;

import javax.annotation.Resource;

import com.prosperitasglobal.sendsolv.filter.FilterFactory;
import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorViewController extends FornecedorBaseController
{
	/** The URL mapping constants. */
	private static final String FETCH_LIST = "";

	/** The Constant FETCH_ADD. */
	private static final String FETCH_ADD = "/add";

	/** The Constant FETCH_EDIT. */
	private static final String FETCH_EDIT = "/edit";

	/** The Constant FETCH_VIEW. */
	private static final String FETCH_VIEW_TABS = "/view";

	/** The Constant FETCH_VIEW_INFO. */
	private static final String FETCH_VIEW = "/view/info";

	/** The Constant EDIT_VIEW. */
	private static final String EDIT_VIEW = "/editView";

	/** The Constant FETCH_ORGANIZATION_BYFORNECEDOR. */
	private static final String FETCH_ORGANIZATION_BYFORNECEDOR = "fetchOrganizationByfornecedor";

	/** The view mapping constants . */
	private static final String VIEW_FORNECEDOR_MAIN = "/fornecedor/fornecedor_main";

	/** The Constant VIEW_FORNECEDOR_ADD. */
	private static final String VIEW_FORNECEDOR_ADD = "/fornecedor/fornecedor_create";

	/** The Constant VIEW_FORNECEDOR_VIEW. */
	private static final String VIEW_FORNECEDOR_VIEW = "/fornecedor/fornecedor_view";

	private static final String VIEW_FORNECEDOR_TABS = "/fornecedor/fornecedor_tabs";

	/** The Constant VIEW_FORNECEDOR_DIALOG_ADD. */
	private static final String VIEW_FORNECEDOR_DIALOG_ADD = "/fornecedor/fornecedor_dialog_create";

	/** The Constant ORGANIZATION_BY_FORNECEDOR_MAIN. */
	private static final String ORGANIZATION_BY_FORNECEDOR_MAIN = "/organization/organizationByfornecedor_main";

	/** The PagedInquiryRequest Constants. */
	private static final int START_PAGE_NUMBER = 0;

	/** The Constant INITIAL_PAGE_SIZE. */
	private static final int INITIAL_PAGE_SIZE = 25;

	/** The Constant FORNECEDOR_ID. */
	private static final String FORNECEDOR_ID = "fornecedorId";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FornecedorViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "LocationViewController";

	private static final String BUSINESS = "BUSINESS";
	private static final String FILTERS = "filters";

	/** The filter factory. */
	private FilterFactory filterFactory;

	/**
	 * @return the filterFactory
	 */
	public FilterFactory getFilterFactory()
	{
		return filterFactory;
	}

	/**
	 * @param filterFactory the filterFactory to set
	 */
	@Resource
	public void setFilterFactory(FilterFactory filterFactory)
	{
		this.filterFactory = filterFactory;
	}

	/**
	 * Load list views.
	 *
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_FORNECEDOR_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		FornecedorInquiryRequest pagedInquiryRequest = new FornecedorInquiryRequest();
		pagedInquiryRequest.setStartPage(START_PAGE_NUMBER);
		pagedInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
		pagedInquiryRequest.setPreQueryCount(true);
		pagedInquiryRequest.addSortExpressions(new SortExpression("ID",
				Direction.Ascending));

		try
		{

			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchEmpresaByRequest(pagedInquiryRequest)));

			FiltersResponse filtersResponse = new FiltersResponse();
			getFilterFactory().configureFilter(BUSINESS, null, filtersResponse);

			modelAndView.addObject(FILTERS, getMapper().writeValueAsString(filtersResponse));
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return modelAndView;
	}

	/**
	 * Load add/edit view.
	 *
	 * @param fornecedorId the fornecedor id
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_ADD, FETCH_EDIT}, method = RequestMethod.GET)
	public ModelAndView loadUpdate(@RequestParam(value = FORNECEDOR_ID, required = false) Integer fornecedorId,
			HttpServletRequest request)
	{

		return fornecedorEditMAV(fornecedorId, VIEW_FORNECEDOR_ADD, true, request);
	}

	@RequestMapping(value = {FETCH_VIEW_TABS}, method = RequestMethod.GET)
	public ModelAndView loadTabs(@RequestParam(value = FORNECEDOR_ID, required = true) Integer fornecedorId,
			HttpServletRequest request)
	{
		return new ModelAndView(VIEW_FORNECEDOR_TABS);
	}

	/**
	 * Load fornecedor view.
	 *
	 * @param fornecedorId the fornecedor id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadView(@RequestParam(value = FORNECEDOR_ID, required = true) Integer fornecedorId,
			HttpServletRequest request)
	{
		return fornecedorEditMAV(fornecedorId, VIEW_FORNECEDOR_VIEW, true, request);
	}

	/**
	 * Load view update.
	 *
	 * @param fornecedorId the fornecedor id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {EDIT_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadViewUpdate(@RequestParam(value = FORNECEDOR_ID, required = false) Integer fornecedorId,
			HttpServletRequest request)
	{

		return fornecedorEditMAV(fornecedorId, VIEW_FORNECEDOR_DIALOG_ADD, true, request);
	}

}
