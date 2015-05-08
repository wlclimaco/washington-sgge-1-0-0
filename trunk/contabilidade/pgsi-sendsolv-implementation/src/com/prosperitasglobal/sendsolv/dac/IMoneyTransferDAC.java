package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IMoneyTransferDAC covers operations for Money Transfers.
 */
public interface IMoneyTransferDAC
{
	/**
	 * Delete a money transfer.
	 *
	 * @param moneyTransfer The money transfer to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 */
	public InternalResponse deleteMoneyTransfer(MoneyTransfer moneyTransfer);

	/**
	 * Fetch a money transfer by id.
	 *
	 * @param id The id of the money transfer to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link MoneyTransfer} along with
	 *         information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<MoneyTransfer> fetchMoneyTransferById(Integer id);

	/**
	 * Fetch all money transfer matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link MoneyTransfer}'s along with
	 *         information about the success/failure of the fetch.
	 */
	public InternalResultsResponse<MoneyTransfer> fetchMoneyTransferByRequest(
			MoneyTransferInquiryRequest request);

	/**
	 * Insert a money transfer.
	 *
	 * @param moneyTransfer The money transfer to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 */
	public InternalResponse insertMoneyTransfer(MoneyTransfer moneyTransfer);

	/**
	 * Update a money transfer and then fetch it and return it.
	 *
	 * @param moneyTransfer The money transfer to update.
	 * @return The {@link InternalResultsResponse} containing information about the success/failure of the update.
	 */
	public InternalResultsResponse<MoneyTransfer> updateFetchMoneyTransfer(MoneyTransfer moneyTransfer);

	/**
	 * Update a money transfer.
	 *
	 * @param moneyTransfer The money transfer to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 */
	public InternalResponse updateMoneyTransfer(MoneyTransfer moneyTransfer);

	/**
	 * Insert a money transfer status.
	 *
	 * @param moneyTransferStatus The money transfer status to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 */
	public InternalResponse insertMoneyTransferStatus(MoneyTransferStatus moneyTransferStatus);
}
