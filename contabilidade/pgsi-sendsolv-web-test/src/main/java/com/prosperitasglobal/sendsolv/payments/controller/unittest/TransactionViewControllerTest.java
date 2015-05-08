package com.prosperitasglobal.sendsolv.payments.controller.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferResponse;

/**
 * The Class LocationViewControllerTest.
 */
public class TransactionViewControllerTest extends AbstractTestBase
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TransactionViewControllerTest.class);

	/** The Constant UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE. */
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE =
			"Unexpected exception while testing response";

	/** The money transfer bai. */
	private IMoneyTransferBAI moneyTransferBAI;

	/**
	 * Gets the money transfer bai.
	 *
	 * @return the money transfer bai
	 */
	public IMoneyTransferBAI getMoneyTransferBAI()
	{
		return moneyTransferBAI;
	}

	/**
	 * Sets the money transfer bai.
	 *
	 * @param moneyTransferBAI the new money transfer bai
	 */
	@Resource
	public void setMoneyTransferBAI(IMoneyTransferBAI moneyTransferBAI)
	{
		this.moneyTransferBAI = moneyTransferBAI;
	}

	/** The exception. */
	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * Test load dialog transaction details.
	 */
	@Test
	public void testLoadDialogTransactionDetails()
	{
		MoneyTransferResponse response = new MoneyTransferResponse();

		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferByRequest(
						Matchers.any(MoneyTransferInquiryRequest.class)))
						.thenReturn(response);

		Mockito.calls(1);

		try
		{
			performTestGet("/transaction/transaction_detail_dialog?transactionId=1")
			.andExpect(view().name(containsString("/transaction/transaction_dialog")))
			.andExpect((model().size(1)))
			.andExpect(model().attributeExists("response"));
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}
	}

	/**
	 * Test load view transactions.
	 */
	@Test
	public void testLoadViewTransactions()
	{
		MoneyTransferBatchResponse response = new MoneyTransferBatchResponse();

		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferBatchById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		Mockito.calls(1);

		try
		{
			performTestGet("/transaction/view?transactionId=1&batchId=10")
			.andExpect(view().name(containsString("/transaction/transaction_view")))
			.andExpect((model().size(2)))
			.andExpect(model().attributeExists("response"));
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}
	}

	/**
	 * Test fecth all.
	 */
	@Test
	public void testFecthAll()
	{
		MoneyTransferBatchResponse response = new MoneyTransferBatchResponse();

		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferBatchById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		Mockito.calls(1);

		try
		{
			performTestGet("/transaction/")
			.andExpect(view().name(containsString("/transaction/transaction_tabs")))
			.andExpect((model().size(2)))
			.andExpect(model().attributeExists("response"));
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}
	}

	/**
	 * Load list member.
	 */
	@Test
	public void loadListMember()
	{
		MoneyTransferResponse response = new MoneyTransferResponse();
		List<MoneyTransfer> list = new ArrayList<MoneyTransfer>();
		list.add(new MoneyTransfer());
		response.setMoneyTransferList(list);

		Mockito.when(
				getMoneyTransferBAI().fetchMoneyTransferByRequest(
						Matchers.any(MoneyTransferInquiryRequest.class)))
						.thenReturn(response);
		try
		{
			performTestGet("/transaction/member")
					.andExpect(view().name(containsString("/transaction/member_transaction_main"))).andExpect(
							(model().size(1))).andExpect(model().attributeExists("response"));

			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}
	}
}
