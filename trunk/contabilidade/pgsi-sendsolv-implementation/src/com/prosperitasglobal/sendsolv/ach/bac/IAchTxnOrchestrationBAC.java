package com.prosperitasglobal.sendsolv.ach.bac;

import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * Interface for specifying the operations involved in orchestrating a {@link MoneyTransfer} and the communication with
 * the automated clearing house.
 */
public interface IAchTxnOrchestrationBAC
{
	/**
	 * This method will create a new {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} using the
	 * {@link MoneyTransfer} data as the source for the information. The new
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} will be attached to a new
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} with a status of
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum#CANCELLATION_SUBMITTED}.
	 *
	 * @param request The {@link MoneyTransferMaintenanceRequest} containing the {@link MoneyTransfer} that will be the
	 *            basis for creating the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction}.
	 * @return The {@link InternalResultsResponse} containing the {@link MoneyTransfer} found in the
	 *         {@link MoneyTransferMaintenanceRequest}. The {@link MoneyTransfer} will have a new
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} added with a status
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum#CANCELLATION_SUBMITTED} and it will
	 *         contain the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction}.
	 */
	public InternalResultsResponse<MoneyTransfer> createCancelMoneyTransferTransaction(
			MoneyTransferMaintenanceRequest request);

	/**
	 * This method will create a new {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} using the
	 * {@link MoneyTransfer} data as the source for the information. The new
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} will be attached to a new
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} with a status of
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum#MODIFICATION_SUBMITTED}.
	 *
	 * @param request The {@link MoneyTransferMaintenanceRequest} containing the {@link MoneyTransfer} that will be the
	 *            basis for creating the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction}.
	 * @return The {@link InternalResultsResponse} containing the {@link MoneyTransfer} found in the
	 *         {@link MoneyTransferMaintenanceRequest}. The {@link MoneyTransfer} will have a new
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} added with a status
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum#MODIFICATION_SUBMITTED} and it will
	 *         contain the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction}.
	 */
	public InternalResultsResponse<MoneyTransfer> createModifyMoneyTransferTransaction(
			MoneyTransferMaintenanceRequest request);

	/**
	 * This method will create a new {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} using the
	 * {@link MoneyTransfer} data as the source for the information. The new
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} will be attached to a new
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} with a status of
	 * {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum#ORDER_SUBMITTED}.
	 *
	 * @param request The {@link MoneyTransferMaintenanceRequest} containing the {@link MoneyTransfer} that will be the
	 *            basis for creating the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction}.
	 * @return The {@link InternalResultsResponse} containing the {@link MoneyTransfer} found in the
	 *         {@link MoneyTransferMaintenanceRequest}. The {@link MoneyTransfer} will have a new
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} added with a status
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum#ORDER_SUBMITTED} and it will contain
	 *         the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction}.
	 */
	public InternalResultsResponse<MoneyTransfer> createTransferMoneyTransferTransaction(
			MoneyTransferMaintenanceRequest request);

	/**
	 * This method will submit the {@link com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction} found on the
	 * most current {@link com.prosperitasglobal.sendsolv.model.MoneyTransferStatus} of the {@link MoneyTransfer} found
	 * in the request.
	 *
	 * @param request The {@link MoneyTransferMaintenanceRequest} containing the {@link MoneyTransfer} that will be the
	 *            submitted to the automated clearing house for processing.
	 * @return The {@link InternalResultsResponse} containing the {@link MoneyTransfer} found in the
	 *         {@link MoneyTransferMaintenanceRequest}. The {@link MoneyTransfer} will be updated with the results of
	 *         the automated clearing house processing. To check if the system had problems, check the
	 *         {@link InternalResultsResponse}. To check the results from the automated clearing house processing, use
	 *         method {@link MoneyTransfer#getCurrentStatus()} to get the most current status. On that status,
	 *         interrogate the {@link com.prosperitasglobal.sendsolv.model.response.MoneyTransferProcessingResponse}.
	 *         That will contain the results of the automated clearing house processing.
	 * @see InternalResultsResponse
	 * @see com.prosperitasglobal.sendsolv.model.response.MoneyTransferProcessingResponse
	 */
	public InternalResultsResponse<MoneyTransfer> submitMoneyTransferTransaction(
			MoneyTransferMaintenanceRequest request);
}
