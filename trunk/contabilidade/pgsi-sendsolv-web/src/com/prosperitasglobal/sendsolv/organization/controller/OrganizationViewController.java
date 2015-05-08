package com.prosperitasglobal.sendsolv.organization.controller;

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
import com.prosperitasglobal.sendsolv.model.OrganizationOrderByEnum;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.OrganizationResponse;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;

/**
 * The OrganizationViewController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */

@Controller
@RequestMapping("/organization")
public class OrganizationViewController extends OrganizationBaseController
{
	private static final String EDIT_VIEW = "editView";
	/** The URL mapping constants. */
	private static final String FETCH_LIST = "";
	private static final String FETCH_ADD = "/add";
	private static final String FETCH_EDIT = "/edit";
	private static final String FETCH_VIEW = "/view";
	private static final String FETCH_VIEW_INFO = "/view/info";

	/** The view mapping constants . */
	private static final String VIEW_ORGANIZATION_MAIN = "/organization/organization_main";
	private static final String VIEW_ORGANIZATION_ADD = "/organization/organization_create";
	private static final String VIEW_ORGANIZATION_DIALOG_ADD = "/organization/organization_dialog_create";
	private static final String VIEW_ORGANIZATION_VIEW = "/organization/organization_view";
	private static final String VIEW_BUSINESS_VIEW = "/business/business_view";

	private static final String ORGANIZATION_ID = "organizationId";

	private static final String TAB = "tab";

	/** The PagedInquiryRequest Constants. */
	private static final int START_PAGE_NUMBER = 0;
	private static final int INITIAL_PAGE_SIZE = 25;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(OrganizationViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "OrganizationViewController";
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
	 * @param request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_ORGANIZATION_MAIN);

		PagedInquiryRequest pagedInquiryRequest = new PagedInquiryRequest();
		pagedInquiryRequest.setStartPage(START_PAGE_NUMBER);
		pagedInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
		pagedInquiryRequest.setPreQueryCount(true);
		pagedInquiryRequest.addSortExpressions(new SortExpression(OrganizationOrderByEnum.NAME_COLUMN.getValue(),
				Direction.Ascending));

		try
		{
			OrganizationResponse organizationResponse =
					getOrganizationBAI().fetchOrganizationByRequest(pagedInquiryRequest);

			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(organizationResponse));

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
	 * @param request
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_ADD, FETCH_EDIT}, method = RequestMethod.GET)
	public ModelAndView loadUpdate(@RequestParam(value = ORGANIZATION_ID, required = false) Integer organizationId,
			HttpServletRequest request)
	{

		return organizationEditMAV(organizationId, VIEW_ORGANIZATION_ADD, request);
	}

	/**
	 * Load edit view.
	 *
	 * @param request
	 * @return the model and view
	 */
	@RequestMapping(value = {EDIT_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadViewUpdate(@RequestParam(value = ORGANIZATION_ID, required = false) Integer organizationId,
			HttpServletRequest request)
	{
		return organizationEditMAV(organizationId, VIEW_ORGANIZATION_DIALOG_ADD, request);
	}

	/**
	 * Load main view page.
	 *
	 * @param request
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadView(
			@RequestParam(value = ORGANIZATION_ID, required = true) Integer organizationId,
			@RequestParam(value = TAB, required = false) String tab,
			HttpServletRequest request)
	{

		return organizationEditMAV(organizationId, VIEW_ORGANIZATION_VIEW, request);

	}

	/**
	 * Load info view page.
	 *
	 * @param organizationId
	 * @param request
	 * @return
	 */

	@RequestMapping(value = {FETCH_VIEW_INFO}, method = RequestMethod.GET)
	public ModelAndView loadTab(@RequestParam(value = ORGANIZATION_ID, required = true) Integer organizationId,
			HttpServletRequest request)
	{
		return organizationEditMAV(organizationId, VIEW_BUSINESS_VIEW, request);
	}
}
