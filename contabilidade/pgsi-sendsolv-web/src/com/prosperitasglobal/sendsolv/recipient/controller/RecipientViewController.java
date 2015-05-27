package com.prosperitasglobal.sendsolv.recipient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class RecipientViewController.
 *
 * @author Flavio Tosta, Washington Costa
 */
@Controller
@RequestMapping("/recipient")
public class RecipientViewController extends RecipientBaseController
{
	//
	// /** The URL mapping constants. */
	// private static final String FETCH_LIST = "";
	//
	// /** The Constant FETCH_ADD. */
	// private static final String FETCH_ADD = "/add";
	//
	// /** The Constant FETCH_EDIT. */
	// private static final String FETCH_EDIT = "/edit";
	//
	// /** The Constant FETCH_RECIPIENT_BY_MEMBER. */
	// private static final String FETCH_RECIPIENT_BY_MEMBER = "/fetchRecipientByMember";
	//
	// /** The Constant FETCH_VIEW_INFO. */
	// private static final String FETCH_VIEW_INFO = "/view/info";
	//
	// /** The Constant FETCH_VIEW. */
	// private static final String FETCH_VIEW = "/view";
	//
	// /** The Constant EDIT_VIEW. */
	// private static final String EDIT_VIEW = "/editView";
	//
	// /** The view mapping constants . */
	// private static final String VIEW_RECIPIENT_MAIN = "/recipient/recipient_main";
	//
	// /** The Constant VIEW_MEMBER_ADD. */
	// private static final String VIEW_MEMBER_ADD = "/recipient/recipient_create";
	//
	// /** The Constant VIEW_RECIPIENT_EDIT. */
	// private static final String VIEW_RECIPIENT_EDIT = "/recipient/recipient_form";
	//
	// /** The Constant VIEW_RECIPIENT_VIEW. */
	// private static final String VIEW_RECIPIENT_VIEW = "/recipient/recipient_view";
	//
	// /** The Constant VIEW_RECIPIENT_DIALOG_ADD. */
	// private static final String VIEW_RECIPIENT_DIALOG_ADD = "/recipient/recipient_dialog_create";
	//
	// /** The Constant VIEW_RECIPIENT_BY_MEMBER. */
	// private static final String VIEW_RECIPIENT_BY_MEMBER = "recipient/recipientByMember_main";
	//
	// /** The Constant RECIPIENT_ID. */
	// private static final String RECIPIENT_ID = "recipientId";
	//
	// /** The Constant MEMBER_ID. */
	// private static final String MEMBER_ID = "memberId";
	//
	// /** The Constant TAB. */
	// private static final String TAB = "tab";
	//
	// /** The Constant START_PAGE_NUMBER. */
	// private static final int START_PAGE_NUMBER = 0;
	//
	// /** The Constant INITIAL_PAGE_SIZE. */
	// private static final int INITIAL_PAGE_SIZE = 25;
	//
	// /** The Constant LOG. */
	// private static final Logger LOG = LoggerFactory.getLogger(RecipientViewController.class);
	//
	// /** The Constant CONTROLLER_EXCEPTION_MSG. */
	// private static final String CONTROLLER_EXCEPTION_MSG = "RecipientViewController";
	//
	// /** The Constant VIEW_RECIPIENT_INFO. */
	// private static final String VIEW_RECIPIENT_INFO = "/recipient/recipient_info";
	//
	// private static final String RECIPIENT = "RECIPIENT";
	// private static final String FILTERS = "filters";
	//
	// /** The filter factory. */
	// private FilterFactory filterFactory;
	//
	// /**
	// * @return the filterFactory
	// */
	// public FilterFactory getFilterFactory()
	// {
	// return filterFactory;
	// }
	//
	// /**
	// * @param filterFactory the filterFactory to set
	// */
	// @Resource
	// public void setFilterFactory(FilterFactory filterFactory)
	// {
	// this.filterFactory = filterFactory;
	// }
	//
	// /**
	// * Load list.
	// *
	// * @param request the request
	// * @return the model and view
	// */
	// @RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	// public ModelAndView loadList(HttpServletRequest request)
	// {
	// ModelAndView modelAndView = new ModelAndView(VIEW_RECIPIENT_MAIN);
	//
	// RecipientInquiryRequest recipientInquiryRequest = new RecipientInquiryRequest();
	//
	// fillInquiryRequest(recipientInquiryRequest);
	//
	// try
	// {
	// modelAndView.addObject(RESPONSE, getMapper()
	// .writeValueAsString(fetchRecipientByRequest(recipientInquiryRequest)));
	//
	// FiltersResponse filtersResponse = new FiltersResponse();
	// getFilterFactory().configureFilter(RECIPIENT, null, filtersResponse);
	//
	// modelAndView.addObject(FILTERS, getMapper().writeValueAsString(filtersResponse));
	// }
	//
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// modelAndView.addObject(RESPONSE, null);
	// }
	// }
	//
	// return modelAndView;
	// }
	//
	// /**
	// * Load list.
	// *
	// * @param memberId the member id
	// * @param request the request
	// * @return the model and view
	// */
	// @RequestMapping(value = FETCH_RECIPIENT_BY_MEMBER, method = RequestMethod.GET)
	// public ModelAndView loadListRecipientByMember(@RequestParam(value = MEMBER_ID, required = false) Integer
	// memberId,
	// HttpServletRequest request)
	// {
	// ModelAndView modelAndView = new ModelAndView(VIEW_RECIPIENT_BY_MEMBER);
	//
	// RecipientInquiryRequest recipientInquiryRequest = new RecipientInquiryRequest();
	//
	// fillInquiryRequest(recipientInquiryRequest);
	//
	// if (memberId != null)
	// {
	// RecipientCriteria criteria = new RecipientCriteria();
	// criteria.setMemberId(memberId);
	// recipientInquiryRequest.setCriteria(criteria);
	// }
	//
	// try
	// {
	// modelAndView.addObject(RESPONSE, getMapper()
	// .writeValueAsString(fetchRecipientByRequest(recipientInquiryRequest)));
	// }
	//
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// modelAndView.addObject(RESPONSE, null);
	// }
	// }
	//
	// return modelAndView;
	// }
	//
	// /**
	// * Fill inquiry request.
	// *
	// * @param recipientInquiryRequest the recipient inquiry request
	// * @return the recipient inquiry request
	// */
	// public RecipientInquiryRequest fillInquiryRequest(RecipientInquiryRequest recipientInquiryRequest)
	// {
	//
	// recipientInquiryRequest.setStartPage(START_PAGE_NUMBER);
	// recipientInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
	// recipientInquiryRequest.setPreQueryCount(true);
	// recipientInquiryRequest.addSortExpressions(new SortExpression("participantId",
	// Direction.Ascending));
	//
	// return recipientInquiryRequest;
	// }
	//
	// /**
	// * Load add.
	// *
	// * @param recipientId the recipient id
	// * @return the model and view
	// */
	// @RequestMapping(value = {FETCH_ADD}, method = RequestMethod.GET)
	// public ModelAndView loadAdd(@RequestParam(value = RECIPIENT_ID, required = false) Integer recipientId,
	// HttpServletRequest request)
	// {
	//
	// return recipientEditMAV(recipientId, VIEW_MEMBER_ADD, true, request);
	// }
	//
	// /**
	// * Load update.
	// *
	// * @param recipientId the recipient id
	// * @return the model and view
	// */
	// @RequestMapping(value = {FETCH_EDIT}, method = RequestMethod.GET)
	// public ModelAndView loadUpdate(@RequestParam(value = RECIPIENT_ID, required = false) Integer recipientId,
	// HttpServletRequest request)
	// {
	//
	// return recipientEditMAV(recipientId, VIEW_RECIPIENT_EDIT, true, request);
	// }
	//
	// /**
	// * Load view.
	// *
	// * @param recipientId the recipient id
	// * @param tab the tab
	// * @param request the request
	// * @return the model and view
	// */
	// @RequestMapping(value = {FETCH_VIEW}, method = RequestMethod.GET)
	// public ModelAndView loadView(
	// @RequestParam(value = RECIPIENT_ID, required = true) Integer recipientId,
	// @RequestParam(value = TAB, required = false) String tab,
	// HttpServletRequest request)
	// {
	// return recipientEditMAV(recipientId, VIEW_RECIPIENT_VIEW, false, request);
	// }
	//
	// /**
	// * Load tab.
	// *
	// * @param recipientId the recipient id
	// * @param request the request
	// * @return the model and view
	// */
	// @RequestMapping(value = {FETCH_VIEW_INFO}, method = RequestMethod.GET)
	// public ModelAndView loadTab(@RequestParam(value = RECIPIENT_ID, required = true) Integer recipientId,
	// HttpServletRequest request)
	// {
	// return recipientEditMAV(recipientId, VIEW_RECIPIENT_INFO, false, request);
	// }
	//
	// /**
	// * Load view update.
	// *
	// * @param recipientId the recipient id
	// * @param request the request
	// * @return the model and view
	// */
	// @RequestMapping(value = {EDIT_VIEW}, method = RequestMethod.GET)
	// public ModelAndView loadViewUpdate(@RequestParam(value = RECIPIENT_ID, required = false) Integer recipientId,
	// HttpServletRequest request)
	// {
	//
	// return recipientEditMAV(recipientId, VIEW_RECIPIENT_DIALOG_ADD, true, request);
	// }

}
