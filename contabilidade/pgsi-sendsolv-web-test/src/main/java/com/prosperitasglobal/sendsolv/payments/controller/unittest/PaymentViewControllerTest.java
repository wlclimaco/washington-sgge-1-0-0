package com.prosperitasglobal.sendsolv.payments.controller.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI;
import com.prosperitasglobal.sendsolv.bai.IProductPlanBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.BusinessProductPlanResponse;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferResponse;

/**
 * The Class LocationViewControllerTest.
 */
public class PaymentViewControllerTest extends AbstractTestBase
{
	/** The URL mapping constants. */
	private static final String FETCH_LIST = "/payment/upcoming_pay_dates";

	private static final String BATCHES = "/payment/batches";

	private static final String BATCHES_VIEW = "/payment/batches_view";

	/** The Constant INITIAL_LOAD. */
	private static final String INITIAL_LOAD = "?initialLoad=false";

	/** The view mapping constants . */
	private static final String VIEW_LOCATION_MAIN = "/payments/payments_upcoming_pay_dates_main";

	private static final String TABS_BATCH_MAIN = "/payments/batches_tabs";

	private static final String VIEW_BATCH = "/payments/batches_view";

	/** The Response constants. */
	public static final String COUNTRIES = "countries";

	public static final String RESPONSE = "response";

	/** The Constant NUMBER_OF_EMPLOYEES. */
	public static final String NUMBER_OF_EMPLOYEES = "number_of_employees";

	/** The Constant NUMBER_OF_MIGRANT_WORKERS. */
	public static final String NUMBER_OF_MIGRANT_WORKERS = "number_of_migrant_workers";

	/** The Constant STATUS. */
	public static final String STATUS = "status";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PaymentAPIControllerTest.class);

	/** The Constant UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE */
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE =
			"Unexpected exception while testing response";

	/** The location bai. */
	private ILocationBAI locationBAI;

	/** The money transfer bai. */
	private IMoneyTransferBAI moneyTransferBAI;

	/** The Product Plan bai */
	private IProductPlanBAI productPlanBAI;

	/**
	 * @return the productPlanBAI
	 */
	public IProductPlanBAI getProductPlanBAI()
	{
		return productPlanBAI;
	}

	/**
	 * @param productPlanBAI the productPlanBAI to set
	 */
	@Resource
	public void setProductPlanBAI(IProductPlanBAI productPlanBAI)
	{
		this.productPlanBAI = productPlanBAI;
	}

	public IMoneyTransferBAI getMoneyTransferBAI()
	{
		return moneyTransferBAI;
	}

	@Resource
	public void setMoneyTransferBAI(IMoneyTransferBAI moneyTransferBAI)
	{
		this.moneyTransferBAI = moneyTransferBAI;
	}

	/** The exception. */
	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Load location list.
	 */
	@Test
	public void loadListTest()
	{

		// Mock Organization Response
		LocationResponse response = new LocationResponse();
		response.setLocationList(new ArrayList<Location>());
		for (int i = 0; i < 5; i++)
		{
			Location location = new Location();
			location.setBusinessType(BusinessTypeEnum.LOCATION);
			location.setId(i);
			response.getLocationList().add(location);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLocationBAI().fetchLocationByRequest(
						Matchers.any(PagedInquiryRequest.class)))
						.thenReturn(response);
		try
		{
			performTestGet(FETCH_LIST).andExpect(view().name(containsString(VIEW_LOCATION_MAIN))).andExpect(
					(model().size(1))).andExpect(model().attributeExists("response"));

			// Check for success, model
			performTestGet(FETCH_LIST + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_LOCATION_MAIN)))
					.andExpect(model().size(1));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}

	}

	@Test
	public void loadBatchTabs()
	{
		MoneyTransferBatchResponse response = new MoneyTransferBatchResponse();
		MoneyTransferBatch batch;

		for (int i = 0; i < 5; i++)
		{
			batch = new MoneyTransferBatch();
			batch.setId(i);
		}

		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferBatchByRequest(
						Matchers.any(MoneyTransferBatchInquiryRequest.class)))
						.thenReturn(response);

		try
		{
			performTestGet(
					BATCHES
							+ "?tab=PENDING_APPROVAL|SPREAD_REVIEW|READY_FOR_RELEASE|ACH_EXCEPTIONS|ACH_PROCESSING|EXPIRED|BATCH_PROCESSED|CANCELLED")
					.andExpect(view().name(containsString(TABS_BATCH_MAIN)));

			performTestGet(
					BATCHES
							+ "?tab=TAB")
					.andExpect(view().name(containsString(TABS_BATCH_MAIN)));

			performTestGet(BATCHES).andExpect(view().name(containsString(TABS_BATCH_MAIN)));

			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}

	}

	@Test
	public void loadViewBatch()
	{
		MoneyTransferBatchResponse response = new MoneyTransferBatchResponse();
		MoneyTransferBatch batch = new MoneyTransferBatch();
		batch.setId(1);

		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferBatchById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		Mockito.calls(1);

		try
		{
			performTestGet(BATCHES_VIEW + "?batchesId=1").andExpect(view().name(containsString(VIEW_BATCH)))
					.andExpect((model().size(2))).andExpect(model().attributeExists("response"));

			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}
	}

	@Test
	public void loadViewSpreads()
	{
		MoneyTransferResponse response = new MoneyTransferResponse();
		MoneyTransfer transfer;

		for (int i = 0; i < 10; i++)
		{
			transfer = new MoneyTransfer();
			transfer.setId(i);
			response.getMoneyTransferList().add(transfer);
		}

		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferByRequest(
						Matchers.any(MoneyTransferInquiryRequest.class)))
						.thenReturn(response);

		try
		{
			performTestGet("/payment/spreadsView?batchesId=1")
			.andExpect(view().name(containsString("/payments/batch_payer_spread")))
			.andExpect((model().size(2)))
			.andExpect(model().attributeExists("batch_response"))
			.andExpect(model().attributeExists("money_transfer_response"));

			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}

		Mockito.calls(1);
	}

	@Test
	public void loadCreateTransaction()
	{
		MoneyTransferBatchResponse batchResponse = new MoneyTransferBatchResponse();
		MoneyTransferBatch batch;

		for (int i = 0; i < 5; i++)
		{
			batch = new MoneyTransferBatch();
			batch.setId(i);
		}

		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferBatchByRequest(
						Matchers.any(MoneyTransferBatchInquiryRequest.class)))
				.thenReturn(batchResponse);

		BusinessProductPlanResponse productPlanResponse = new BusinessProductPlanResponse();

		BusinessProductPlan productPlan = new BusinessProductPlan();

		for (int i = 0; i < 5; i++)
		{
			productPlan = new BusinessProductPlan();
			productPlan.setId(i);
			productPlan.setName("NAME");
		}

		Mockito.when(
				getProductPlanBAI().fetchBusinessProductPlanByRequest(
						Matchers.any(BusinessProductPlanInquiryRequest.class)))
				.thenReturn(productPlanResponse);

		try
		{
			performTestGet("/payment/createOneOffTransaction?locationId=1")
					.andExpect(view().name(containsString("/transaction/transaction_create")))
					.andExpect((model().size(2)))
					.andExpect(model().attributeExists("batch_response"))
					.andExpect(model().attributeExists("location_response"));

			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}

		Mockito.calls(1);

	}
}
