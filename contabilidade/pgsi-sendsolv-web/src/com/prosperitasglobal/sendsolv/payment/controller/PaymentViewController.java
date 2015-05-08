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
import com.prosperitasglobal.controller.delegate.ListD;
import com.prosperitasglobal.sendsolv.model.OrganizationOrderByEnum;
import com.prosperitasglobal.sendsolv.model.criteria.MoneyTransferBatchCriteria;
import com.prosperitasglobal.sendsolv.model.criteria.MoneyTransferCriteria;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchResponse;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;

/**
 * The PaymentViewController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */

@Controller
@RequestMapping("/payment")
public class PaymentViewController extends PaymentBaseController
{

	private static final String PRODUCT_PLAN = "productPlan";

	private static final String LOCATION_ID = "locationId";

	/** The Constant BATCHES_ID. */
	private static final String BATCHES_ID = "batchesId";

	/** The Constant BATCHES_VIEW. */
	private static final String BATCHES_VIEW = "batches_view";

	/** The Constant BATCHES. */
	private static final String BATCHES = "batches";

	/** The Constant TAB. */
	private static final String TAB = "tab";

	/** The URL mapping constants. */
	private static final String FETCH_LIST = "/upcoming_pay_dates";

	/** The Constant VIEW_UPCOMING_MAIN. */
	private static final String VIEW_UPCOMING_MAIN = "/payments/payments_upcoming_pay_dates_main";

	/** The view mapping constants . */
	private static final String TABS_BATCH_MAIN = "/payments/batches_tabs";

	/** The view mapping constants . */
	private static final String VIEW_BATCH = "/payments/batches_view";

	/** The PagedInquiryRequest Constants. */
	private static final int START_PAGE_NUMBER = 0;

	/** The Constant INITIAL_PAGE_SIZE. */
	private static final int INITIAL_PAGE_SIZE = 25;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PaymentViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "LocationViewController";

	/** Response Objects */
	private static final String BATCH = "batch_response";
	private static final String MONEY_TRANSFERS = "money_transfer_response";
	private static final String LOCATION_RESPONSE = "location_response";

	private static final String DIALOG_CREATE_TRANSACTION = "/createOneOffTransaction";
	private static final String DIALOG_CREATE_TRANSACTION_VIEW = "/transaction/transaction_create";

	/**
	 * Load list views.
	 *
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_UPCOMING_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		PagedInquiryRequest pagedInquiryRequest = new PagedInquiryRequest();
		pagedInquiryRequest.setStartPage(START_PAGE_NUMBER);
		pagedInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
		pagedInquiryRequest.setPreQueryCount(true);
		pagedInquiryRequest.addSortExpressions(new SortExpression(OrganizationOrderByEnum.NAME_COLUMN.getValue(),
				Direction.Ascending));

		try
		{

			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchLocationByRequest(pagedInquiryRequest)));
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
	 * Load batch tabs.
	 *
	 * @param tab the tab
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = BATCHES, method = RequestMethod.GET)
	public ModelAndView loadBatchTabs(
			@RequestParam(value = TAB, required = false) String tab,
			HttpServletRequest request)
	{

		return new ModelAndView(TABS_BATCH_MAIN);

	}

	/**
	 * Load view batch.
	 *
	 * @param batchesId the batches id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {BATCHES_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadViewBatch(@RequestParam(value = BATCHES_ID, required = false) Integer batchesId,
			HttpServletRequest request)
	{

		return batchesEditMAV(VIEW_BATCH, batchesId, true);
	}

	/**
	 * Load view spreads.
	 *
	 * @param batchesId the batches id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {"spreadsView"}, method = RequestMethod.GET)
	public ModelAndView loadViewSpreads(@RequestParam(value = BATCHES_ID, required = true) Integer batchesId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("/payments/batch_payer_spread");

		try
		{
			modelAndView.addObject(BATCH,
					getMapper().writeValueAsString(fetchBatchesById(new FetchByIdRequest(batchesId))));

			MoneyTransferInquiryRequest moneyTransferRequest = new MoneyTransferInquiryRequest();

			moneyTransferRequest.setStartPage(START_PAGE_NUMBER);
			moneyTransferRequest.setPageSize(INITIAL_PAGE_SIZE);
			moneyTransferRequest.setPreQueryCount(true);

			MoneyTransferCriteria criteria = new MoneyTransferCriteria();
			criteria.setMoneyTransferBatchId(batchesId);

			moneyTransferRequest.setCriteria(criteria);

			modelAndView.addObject(
					MONEY_TRANSFERS,
					getMapper().writeValueAsString(
							getMoneyTransferBAI().fetchMoneyTransferByRequest(moneyTransferRequest)));
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(BATCH, null);
				modelAndView.addObject(MONEY_TRANSFERS, null);
			}
		}

		return modelAndView;
	}

	@RequestMapping(value = {DIALOG_CREATE_TRANSACTION}, method = RequestMethod.GET)
	public ModelAndView loadCreateTransaction(
			@RequestParam(value = LOCATION_ID, required = true) Integer locationId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(DIALOG_CREATE_TRANSACTION_VIEW);
		MoneyTransferBatchInquiryRequest inquiryRequest = new MoneyTransferBatchInquiryRequest();
		inquiryRequest.setUserContext(fetchUserContext(request));
		MoneyTransferBatchCriteria criteria = new MoneyTransferBatchCriteria();

		criteria.setLocationId(locationId);
		inquiryRequest.setCriteria(criteria);

		try
		{
			MoneyTransferBatchResponse batchResponse =
					getMoneyTransferBAI().fetchMoneyTransferBatchByRequest(inquiryRequest);

			LocationResponse locationResponse = getLocationBAI().fetchLocationById(new FetchByIdRequest(locationId));

			modelAndView.addObject(BATCH, getMapper().writeValueAsString(batchResponse));
			modelAndView.addObject(LOCATION_RESPONSE, getMapper().writeValueAsString(locationResponse));
			modelAndView.addObject(
					PRODUCT_PLAN,
					getMapper().writeValueAsString(
							ListD.fetchProductPlan(getProductPlanBAI(), fetchUserContext(request), locationId)));

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return modelAndView;
	}
}
