package com.prosperitasglobal.sendsolv.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.prosperitasglobal.sendsolv.model.criteria.MemberCriteria;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;

/**
 * The Class MemberViewController.
 *
 * @author Flavio Tosta, Washington Costa
 */
@Controller
@RequestMapping("/member")
public class MemberViewController extends MemberBaseController
{

	/** The Constant TRANSFER_TRANSFER_SETTING_FORM. */
	private static final String TRANSFER_TRANSFER_SETTING_FORM = "transfer/transferSetting_form";

	/** The Constant DIALOG_CREATE. */
	private static final String DIALOG_CREATE = "dialogCreate";

	/** The Constant TRANSFER_ID. */
	private static final String TRANSFER_ID = "transferId";

	/** The Constant ADD_TRANSFER. */
	private static final String ADD_TRANSFER = "addTransfer";

	/** The Constant ADD_TRANSFER_DIALOG. */
	private static final String ADD_TRANSFER_DIALOG = "addTransferDialog";

	/** The Constant FETCH_MEMBER_BY_RECIPIENT. */
	private static final String FETCH_MEMBER_BY_RECIPIENT = "/fetchMemberByRecipient";

	/** The URL mapping constants. */
	private static final String FETCH_LIST = "";

	/** The Constant FETCH_ADD. */
	private static final String FETCH_ADD = "/add";

	/** The Constant FETCH_EDIT. */
	private static final String FETCH_EDIT = "/edit";

	/** The Constant FETCH_VIEW. */
	private static final String FETCH_VIEW = "/view";

	/** The Constant FETCH_VIEW_INFO. */
	private static final String FETCH_VIEW_INFO = "/view/info";

	/** The Constant EDIT_VIEW. */
	private static final String EDIT_VIEW = "/editView";

	/** The view mapping constants . */
	private static final String VIEW_MEMBER_MAIN = "/member/member_main";

	/** The Constant VIEW_MEMBER_BY_RECIPIENT. */
	private static final String VIEW_MEMBER_BY_RECIPIENT = "/member/memberByRecipient_main";

	/** The Constant VIEW_MEMBER_ADD. */
	private static final String VIEW_MEMBER_ADD = "/member/member_create";

	/** The Constant VIEW_MEMBER_FORM. */
	private static final String VIEW_MEMBER_FORM = "/member/member_form";

	/** The Constant VIEW_MEMBER_VIEW. */
	private static final String VIEW_MEMBER_VIEW = "/member/member_view";

	/** The Constant VIEW_PERSON_VIEW. */
	private static final String VIEW_PERSON_VIEW = "/person/person_view";

	/** The Constant VIEW_MEMBER_DIALOG_ADD. */
	private static final String VIEW_MEMBER_DIALOG_ADD = "/member/member_dialog_create";

	/** The view Transfer mapping constants . */
	private static final String VIEW_TRANSFER_DIALOG = "/transfer/transferSetting_update_dialog";

	/** The Constant VIEW_TRANSFER_ADD. */
	private static final String VIEW_TRANSFER_ADD = "/transfer/transfer_create";

	/** The Constant START_PAGE_NUMBER. */
	private static final int START_PAGE_NUMBER = 0;

	/** The Constant INITIAL_PAGE_SIZE. */
	private static final int INITIAL_PAGE_SIZE = 25;

	/** The Constant MEMBER_ID. */
	private static final String MEMBER_ID = "memberId";

	/** The Constant TAB. */
	private static final String TAB = "tab";

	/** The Constant PARTICIPANT_ID. */
	private static final String PARTICIPANT_ID = "participantId";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MemberViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "MemberViewController";

	/** The Constant RECIPIENT_ID. */
	private static final String RECIPIENT_ID = "recipientId";
	private static final String EMPLOYER = "EMPLOYER";
	private static final String MEMBERS = "MEMBER";
	private static final String FILTERS = "filters";

	/**
	 * Load list.
	 *
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_MEMBER_MAIN);

		try
		{
			FiltersResponse filtersResponse = new FiltersResponse();
			getFilterFactory().configureFilter(EMPLOYER, null, filtersResponse);
			getFilterFactory().configureFilter(MEMBERS, null, filtersResponse);
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
	 * Load list member by recipient.
	 *
	 * @param recipientId the recipient id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_MEMBER_BY_RECIPIENT, method = RequestMethod.GET)
	public ModelAndView loadListMemberByRecipient(
			@RequestParam(value = RECIPIENT_ID, required = false) Integer recipientId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_MEMBER_BY_RECIPIENT);

		MemberInquiryRequest memberInquiryRequest = new MemberInquiryRequest();

		fillInquiryRequest(memberInquiryRequest);

		if (recipientId != null)
		{
			MemberCriteria criteria = new MemberCriteria();
			criteria.setRecipientId(recipientId);
			memberInquiryRequest.setCriteria(criteria);
		}

		try
		{

			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchMemberByRequest(memberInquiryRequest)));

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
	 * Fill inquiry request.
	 *
	 * @param memberInquiryRequest the member inquiry request
	 * @return the recipient inquiry request
	 */
	public MemberInquiryRequest fillInquiryRequest(MemberInquiryRequest memberInquiryRequest)
	{

		memberInquiryRequest.setStartPage(START_PAGE_NUMBER);
		memberInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
		memberInquiryRequest.setPreQueryCount(true);
		memberInquiryRequest.addSortExpressions(new SortExpression(PARTICIPANT_ID,
				Direction.Ascending));

		return memberInquiryRequest;
	}

	/**
	 * Load add.
	 *
	 * @param memberId the member id
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_ADD}, method = RequestMethod.GET)
	public ModelAndView loadAdd(@RequestParam(value = MEMBER_ID, required = false) Integer memberId,
			HttpServletRequest request)
	{

		return memberEditMAV(memberId, VIEW_MEMBER_ADD, true, request);
	}

	/**
	 * Load update.
	 *
	 * @param memberId the member id
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_EDIT}, method = RequestMethod.GET)
	public ModelAndView loadUpdate(@RequestParam(value = MEMBER_ID, required = false) Integer memberId,
			HttpServletRequest request)
	{

		return memberEditMAV(memberId, VIEW_MEMBER_FORM, true, request);
	}

	/**
	 * Load view.
	 *
	 * @param memberId the member id
	 * @param tab the tab
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadView(
			@RequestParam(value = MEMBER_ID, required = true) Integer memberId,
			@RequestParam(value = TAB, required = false) String tab,
			HttpServletRequest request)
	{
		return memberEditMAV(memberId, VIEW_MEMBER_VIEW, false, request);
	}

	/**
	 * Load view.
	 *
	 * @param memberId the member id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_VIEW_INFO}, method = RequestMethod.GET)
	public ModelAndView loadTab(@RequestParam(value = MEMBER_ID, required = true) Integer memberId,
			HttpServletRequest request)
	{
		return memberEditMAV(memberId, VIEW_PERSON_VIEW, false, request);
	}

	/**
	 * Load view update.
	 *
	 * @param memberId the member id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {EDIT_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadViewUpdate(@RequestParam(value = MEMBER_ID, required = false) Integer memberId,
			HttpServletRequest request)
	{

		return memberEditMAV(memberId, VIEW_MEMBER_DIALOG_ADD, true, request);
	}

	/**
	 * Load view add transfer.
	 *
	 * @param memberId the member id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {ADD_TRANSFER}, method = RequestMethod.GET)
	public ModelAndView loadViewAddTransfer(@RequestParam(value = MEMBER_ID, required = false) Integer memberId,
			HttpServletRequest request)
	{

		return transferEditMAV(memberId, VIEW_TRANSFER_ADD, true, request);
	}

	/**
	 * Load view add transfer dialog.
	 *
	 * @param transferId the transfer id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {ADD_TRANSFER_DIALOG}, method = RequestMethod.GET)
	public ModelAndView loadViewAddTransferDialog(
			@RequestParam(value = TRANSFER_ID, required = false) Integer transferId,
			HttpServletRequest request)
	{

		return transferEditMAV(transferId, VIEW_TRANSFER_DIALOG, false, request);
	}

	/**
	 * Load view add transfer dialog create.
	 *
	 * @param memberId the member id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {DIALOG_CREATE}, method = RequestMethod.GET)
	public ModelAndView loadViewAddTransferDialogCreate(
			@RequestParam(value = MEMBER_ID, required = false) Integer memberId,
			HttpServletRequest request)
	{

		return memberEditMAV(memberId, TRANSFER_TRANSFER_SETTING_FORM, true, request);
	}
}
