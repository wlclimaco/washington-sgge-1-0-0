package com.prosperitasglobal.sendsolv.pessoa.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.sendsolv.filter.FilterFactory;
import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;

/**
 * The LocationViewController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */

@Controller
@RequestMapping("/pessoa")
public class PessoaViewController extends PessoaBaseController
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

	/** The Constant FETCH_ORGANIZATION_BYEMPRESA. */
	private static final String FETCH_ORGANIZATION_BYEMPRESA = "fetchOrganizationBylocation";

	/** The view mapping constants . */
	private static final String VIEW_EMPRESA_MAIN = "/empresa/empresa_main";

	/** The Constant VIEW_EMPRESA_ADD. */
	private static final String VIEW_EMPRESA_ADD = "/empresa/empresa_create";

	/** The Constant VIEW_EMPRESA_VIEW. */
	private static final String VIEW_EMPRESA_VIEW = "/empresa/empresa_view";

	private static final String VIEW_EMPRESA_TABS = "/empresa/empresa_tabs";

	/** The Constant VIEW_EMPRESA_DIALOG_ADD. */
	private static final String VIEW_EMPRESA_DIALOG_ADD = "/empresa/empresa_dialog_create";

	/** The Constant ORGANIZATION_BY_EMPRESA_MAIN. */
	private static final String ORGANIZATION_BY_EMPRESA_MAIN = "/organization/organizationBylocation_main";

	/** The PagedInquiryRequest Constants. */
	private static final int START_PAGE_NUMBER = 0;

	/** The Constant INITIAL_PAGE_SIZE. */
	private static final int INITIAL_PAGE_SIZE = 25;

	/** The Constant EMPRESA_ID. */
	private static final String EMPRESA_ID = "locationId";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PessoaViewController.class);

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
		ModelAndView modelAndView = new ModelAndView(VIEW_EMPRESA_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		PagedInquiryRequest pagedInquiryRequest = new PagedInquiryRequest();
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
	 * @param locationId the location id
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_ADD, FETCH_EDIT}, method = RequestMethod.GET)
	public ModelAndView loadUpdate(@RequestParam(value = EMPRESA_ID, required = false) Integer locationId,
			HttpServletRequest request)
	{

		return locationEditMAV(locationId, VIEW_EMPRESA_ADD, true, request);
	}

	@RequestMapping(value = {FETCH_VIEW_TABS}, method = RequestMethod.GET)
	public ModelAndView loadTabs(@RequestParam(value = EMPRESA_ID, required = true) Integer locationId,
			HttpServletRequest request)
	{
		return new ModelAndView(VIEW_EMPRESA_TABS);
	}

	/**
	 * Load location view.
	 *
	 * @param locationId the location id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadView(@RequestParam(value = EMPRESA_ID, required = true) Integer locationId,
			HttpServletRequest request)
	{
		return locationEditMAV(locationId, VIEW_EMPRESA_VIEW, true, request);
	}

	/**
	 * Load view update.
	 *
	 * @param locationId the location id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {EDIT_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadViewUpdate(@RequestParam(value = EMPRESA_ID, required = false) Integer locationId,
			HttpServletRequest request)
	{

		return locationEditMAV(locationId, VIEW_EMPRESA_DIALOG_ADD, true, request);
	}

}
