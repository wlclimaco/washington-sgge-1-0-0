package com.prosperitasglobal.sendsolv.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferResponse;
import com.prosperitasglobal.sendsolv.model.response.PayerResponse;

/**
 * The PaymentAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/transaction")
public class TransactionAPIController extends PaymentBaseController
{

	private static final String FETCH_BATCH_ID = "/fetchBatchId";
	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/transactions";

	/**
	 * Fetch all Locations.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public MoneyTransferResponse fetchAll(@RequestBody MoneyTransferInquiryRequest pagedInquiryRequest)
	{

		return fetchTransactionsByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_BATCH_ID, method = RequestMethod.POST)
	@ResponseBody
	public MoneyTransferBatchResponse fetchAll(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchBatchesById(fetchByIdRequest);

	}

	@RequestMapping(value = "transactionByID", method = RequestMethod.POST)
	@ResponseBody
	public MoneyTransferResponse fetchByID(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchMoneyTransferById(fetchByIdRequest);

	}

	@RequestMapping(value = "fetchPayer", method = RequestMethod.POST)
	@ResponseBody
	public PayerResponse fetchPayer(@RequestBody PayerInquiryRequest request)
	{

		return fetchPayerByRequest(request);

	}

}
