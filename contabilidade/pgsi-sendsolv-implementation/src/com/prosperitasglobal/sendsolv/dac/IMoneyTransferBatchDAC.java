package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatus;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IMoneyTransferDAC covers operations for Money Transfers.
 */
public interface IMoneyTransferBatchDAC
{
	/**
	 * Delete a money transfer batch.
	 *
	 * @param moneyTransferBatch The money transfer batch to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 */
	public InternalResponse deleteMoneyTransferBatch(MoneyTransferBatch moneyTransferBatch);

	/**
	 * Fetch a money transfer batch by id.
	 *
	 * @param id The id of the money transfer batch to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link MoneyTransferBatch} along with
	 *         information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<MoneyTransferBatch> fetchMoneyTransferBatchById(Integer id);

	/**
	 * Fetch all money transfer batch matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link MoneyTransferBatch}'s along with
	 *         information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<MoneyTransferBatch> fetchMoneyTransferBatchByRequest(
			MoneyTransferBatchInquiryRequest request);

	/**
	 * Insert a money transfer batch.
	 *
	 * @param moneyTransferBatch The money transfer batch to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 */
	public InternalResponse insertMoneyTransferBatch(MoneyTransferBatch moneyTransferBatch);

	/**
	 * Update a money transfer batch.
	 *
	 * @param moneyTransferBatch The money transfer batch to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 */
	public InternalResponse updateMoneyTransferBatch(MoneyTransferBatch moneyTransferBatch);

	/**
	 * Insert a money transfer batch status.
	 *
	 * @param moneyTransferBatchStatus The money transfer batch status to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 */
	public InternalResponse insertMoneyTransferBatchStatus(MoneyTransferBatchStatus moneyTransferBatchStatus);

}
