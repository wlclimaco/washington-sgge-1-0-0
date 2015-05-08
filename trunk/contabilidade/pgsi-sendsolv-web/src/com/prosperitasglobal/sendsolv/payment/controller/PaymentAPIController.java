package com.prosperitasglobal.sendsolv.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferAutoApprovalRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferAutoApprovalResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchDTOResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferResponse;
import com.qat.framework.model.response.MaintenanceResponse;

/**
 * The PaymentAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/payment")
public class PaymentAPIController extends PaymentBaseController
{

	/** The Constant INSERT_TRANSFER_STATUS. */
	private static final String INSERT_TRANSFER_STATUS = "insertTransferStatus";

	/** The Constant BATCH_BY_ID. */
	private static final String BATCH_BY_ID = "batch_by_id";

	/** The Constant FETCH_BATCH_ID. */
	private static final String FETCH_BATCH_ID = "fetch_transfer_batch_Id";

	/** The Constant FETCH_ALL_BATCHES. */
	private static final String FETCH_ALL_BATCHES = "fetchAllBatches";

	/** The Constant UPDATE_BATCH. */
	private static final String UPDATE_BATCH = "update";
	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/upcoming_pay_dates";

	/** The Constant UPDATE_MONEY_TRANSFER. */
	private static final String UPDATE_MONEY_TRANSFER = "/updateMoneyTransfer";

	/** The Constant INSERT_BATCH. */
	private static final String INSERT_BATCH = "/insertMoneyTransferBatch";

	/** The Constant INSERT_MONEY_TRANSFER. */
	private static final String INSERT_MONEY_TRANSFER = "/insertMoneyTransfer";

	/**
	 * Fetch all Locations.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public LocationResponse fetchAll(@RequestBody PagedInquiryRequest pagedInquiryRequest)
	{

		return fetchLocationByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch all.
	 *
	 * @param moneyTransferBatchRequest the money transfer batch request
	 * @return the money transfer batch response
	 */
	@RequestMapping(value = FETCH_ALL_BATCHES, method = RequestMethod.POST)
	@ResponseBody
	public MoneyTransferBatchResponse fetchAll(@RequestBody MoneyTransferBatchInquiryRequest moneyTransferBatchRequest)
	{

		return fetchBatchesByRequest(moneyTransferBatchRequest);

	}

	/**
	 * Fetch all transfer.
	 *
	 * @param moneyTransferBatchRequest the money transfer batch request
	 * @return the money transfer response
	 */
	@RequestMapping(value = FETCH_BATCH_ID, method = RequestMethod.POST)
	@ResponseBody
	public MoneyTransferResponse fetchAllTransfer(@RequestBody MoneyTransferInquiryRequest moneyTransferBatchRequest)
	{

		return fetchTransferBatchesByRequest(moneyTransferBatchRequest);

	}

	/**
	 * Fetch batch by id.
	 *
	 * @param moneyTransferBatchRequest the money transfer batch request
	 * @return the money transfer batch dto response
	 */
	@RequestMapping(value = BATCH_BY_ID, method = RequestMethod.POST)
	@ResponseBody
	public MoneyTransferBatchDTOResponse fetchBatchByID(@RequestBody FetchByIdRequest moneyTransferBatchRequest)
	{

		return fecthMoneyTransferBatchWithSummaryById(moneyTransferBatchRequest);

	}

	/**
	 * Update money transfer by request.
	 *
	 * @param moneyTransferRequest the money transfer request
	 * @return the maintenance response
	 */
	@RequestMapping(value = UPDATE_MONEY_TRANSFER, method = RequestMethod.POST)
	@ResponseBody
	public MaintenanceResponse updateMoneyTransferByRequest(
			@RequestBody MoneyTransferMaintenanceRequest moneyTransferRequest)
	{

		return updateMoneyTransfer(moneyTransferRequest);

	}

	/**
	 * Update batch.
	 *
	 * @param moneyTransferBatchRequest the money transfer batch request
	 * @return the maintenance response
	 */
	@RequestMapping(value = UPDATE_BATCH, method = RequestMethod.POST)
	@ResponseBody
	public MaintenanceResponse updateBatch(
			@RequestBody MoneyTransferBatchMaintenanceRequest moneyTransferBatchRequest)
	{

		return update(moneyTransferBatchRequest);

	}

	/**
	 * Insert transfer status.
	 *
	 * @param moneyTransferBatchRequest the money transfer batch request
	 * @return the maintenance response
	 */
	@RequestMapping(value = INSERT_TRANSFER_STATUS, method = RequestMethod.POST)
	@ResponseBody
	public MaintenanceResponse insertTransferStatus(
			@RequestBody MoneyTransferStatusMaintenanceRequest moneyTransferBatchRequest)
	{

		return insertMoneyTransferStatus(moneyTransferBatchRequest);

	}

	/**
	 * Creates the one off transaction.
	 *
	 * @param moneyTransferBatchCreateRequest the money transfer batch create request
	 * @return the money transfer batch response
	 */
	@RequestMapping(value = INSERT_BATCH, method = RequestMethod.POST)
	@ResponseBody
	public MoneyTransferBatchResponse insertBatch(
			@RequestBody MoneyTransferBatchCreateRequest moneyTransferBatchCreateRequest)
	{
		MoneyTransferBatchResponse response = new MoneyTransferBatchResponse();

		try
		{
			response = getMoneyTransferBAI().insertMoneyTransferBatch(moneyTransferBatchCreateRequest);
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	/**
	 * Creates the one off transaction.
	 *
	 * @param moneyTransferCreateRequest the money transfer create request
	 * @return the money transfer response
	 */
	@RequestMapping(value = INSERT_MONEY_TRANSFER, method = RequestMethod.POST)
	@ResponseBody
	public MoneyTransferResponse createOneOffTransaction(
			@RequestBody MoneyTransferCreateRequest moneyTransferCreateRequest)
	{
		MoneyTransferResponse response = new MoneyTransferResponse();

		try
		{
			response = getMoneyTransferBAI().insertMoneyTransfer(moneyTransferCreateRequest);
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	@RequestMapping(value = "MoneyTransferAutoApproval", method = RequestMethod.POST)
	@ResponseBody
	public MoneyTransferAutoApprovalResponse MoneyTransferAutoApproval(
			@RequestBody MoneyTransferAutoApprovalRequest moneyTransferCreateRequest)
	{
		MoneyTransferAutoApprovalResponse response = new MoneyTransferAutoApprovalResponse();

		try
		{
			response =
					getMoneyTransferBAI().fetchMoneyTransferAutoApprovalByTransferSetting(moneyTransferCreateRequest);
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}
}
