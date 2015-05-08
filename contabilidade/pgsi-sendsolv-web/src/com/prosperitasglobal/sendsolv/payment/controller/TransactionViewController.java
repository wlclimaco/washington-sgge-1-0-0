package com.prosperitasglobal.sendsolv.payment.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.OrganizationOrderByEnum;
import com.prosperitasglobal.sendsolv.model.criteria.MoneyTransferCriteria;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;

/**
 * The PaymentViewController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */

@Controller
@RequestMapping("/transaction")
public class TransactionViewController extends TransactionBaseController
{

	/** The VIEW mapping constants */
	private static final String TRANSACTION_PAGE_VIEW = "/transaction/transaction_view";
	private static final String TRANSACTION_TRANSACTION_TABS = "/transaction/transaction_tabs";
	private static final String TRANSACTION_MEMBER = "/transaction/member_transaction_main";

	private static final String TRANSACTION_DIALOG = "/transaction/transaction_dialog";
	private static final String FETCH_LIST = "";

	/** The URL mapping constants. */
	private static final String TRANSACTION_DETAIL = "/transaction_detail_dialog";
	private static final String TRANSACTION_VIEW = "/view";

	/** The PagedInquiryRequest Constants. */
	private static final int START_PAGE_NUMBER = 0;

	/** The Constant INITIAL_PAGE_SIZE. */
	private static final int INITIAL_PAGE_SIZE = 25;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TransactionViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "LocationViewController";

	private static final String FILTER_ACTION_TYPE = "TRANSFERSTATUS";
	private static final String EMPLOYER = "EMPLOYER";
	private static final String MEMBERS = "MEMBER";
	private static final String TRANSACTION = "TRANSACTION";

	private static final String TRANSACTION_ID = "transactionId";
	private static final String FILTERS = "filters";

	@RequestMapping(value = "member", method = RequestMethod.GET)
	public ModelAndView loadListMember(@RequestParam(value = "memberId", required = false) Integer memberId,
			HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView(TRANSACTION_MEMBER);

		MoneyTransferInquiryRequest moneyTransferInquiry = new MoneyTransferInquiryRequest();
		moneyTransferInquiry.setStartPage(START_PAGE_NUMBER);
		moneyTransferInquiry.setPageSize(INITIAL_PAGE_SIZE);
		moneyTransferInquiry.setPreQueryCount(true);
		moneyTransferInquiry.addSortExpressions(new SortExpression("status",
				Direction.Ascending));
		MoneyTransferCriteria criteria = new MoneyTransferCriteria();
		criteria.setMember(new Member(memberId));
		moneyTransferInquiry.setCriteria(criteria);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchTransactionsByRequest(moneyTransferInquiry)));
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
	 * Load list views.
	 *
	 * @param request the request
	 * @return the model and view
	 */

	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(TRANSACTION_TRANSACTION_TABS);

		MoneyTransferInquiryRequest moneyTransferInquiry = new MoneyTransferInquiryRequest();
		moneyTransferInquiry.setStartPage(START_PAGE_NUMBER);
		moneyTransferInquiry.setPageSize(INITIAL_PAGE_SIZE);
		moneyTransferInquiry.setPreQueryCount(true);
		moneyTransferInquiry.addSortExpressions(new SortExpression(OrganizationOrderByEnum.NAME_COLUMN.getValue(),
				Direction.Ascending));

		try
		{
			// Filters
			FiltersResponse filtersResponse = new FiltersResponse();
			getFilterFactory().configureFilter(FILTER_ACTION_TYPE, null, filtersResponse);
			getFilterFactory().configureFilter(EMPLOYER, null, filtersResponse);
			getFilterFactory().configureFilter(MEMBERS, null, filtersResponse);
			getFilterFactory().configureFilter(TRANSACTION, null, filtersResponse);

			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchTransactionsByRequest(moneyTransferInquiry)));
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

	@RequestMapping(value = {TRANSACTION_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadViewAddTransferDialogCreate(
			@RequestParam(value = TRANSACTION_ID, required = false) Integer transactionId, Integer batchId,
			HttpServletRequest request)
	{

		return batchesEditMAV(TRANSACTION_PAGE_VIEW, batchId, false);
	}

	/**
	 * Load transaction detail view dialog.
	 *
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = TRANSACTION_DETAIL, method = RequestMethod.GET)
	public ModelAndView loadDialog(@RequestParam(value = TRANSACTION_ID, required = true) Integer transactionId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(TRANSACTION_DIALOG);

		try
		{

			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchMoneyTransferById(new FetchByIdRequest(transactionId))));
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

}
