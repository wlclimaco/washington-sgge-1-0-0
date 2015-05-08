package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchDTO;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferAutoApprovalRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferStatusMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IMoneyTransferBAC.
 */
public interface IMoneyTransferBAC
{
	/**
	 * Create a {@link MoneyTransfer} for the request.
	 *
	 * @param request the request
	 * @return the internal results response containing the {@link MoneyTransfer} created.
	 */
	public InternalResultsResponse<MoneyTransfer> createMoneyTransfer(MoneyTransferCreateRequest request);

	/**
	 * Create a {@link MoneyTransferBatch} for the request.
	 *
	 * @param request the request
	 * @return the internal results response containing the {@link MoneyTransferBatch} created.
	 */
	public InternalResultsResponse<MoneyTransferBatch> createMoneyTransferBatch(
			MoneyTransferBatchCreateRequest request);

	/**
	 * Delete money transfer.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteMoneyTransfer(MoneyTransferMaintenanceRequest request);

	/**
	 * Delete money transfer batch.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteMoneyTransferBatch(MoneyTransferBatchMaintenanceRequest request);

	/**
	 * Fetch money transfer by id.
	 *
	 * @param request the request
	 * @return the internal results response< money transfer>
	 */
	public InternalResultsResponse<MoneyTransfer> fetchMoneyTransferById(FetchByIdRequest request);

	/**
	 * Fetch money transfer batch by id.
	 *
	 * @param request the request
	 * @return the internal results response< money transfer batch>
	 */
	public InternalResultsResponse<MoneyTransferBatch> fetchMoneyTransferBatchById(FetchByIdRequest request);

	/**
	 * Fetch money transfer by request.
	 *
	 * @param request the request
	 * @return the internal results response< money transfer>
	 */
	public InternalResultsResponse<MoneyTransfer> fetchMoneyTransferByRequest(MoneyTransferInquiryRequest request);

	/**
	 * Fetch money transfer batch by request.
	 *
	 * @param request the request
	 * @return the internal results response< money transfer batch>
	 */
	public InternalResultsResponse<MoneyTransferBatch> fetchMoneyTransferBatchByRequest(
			MoneyTransferBatchInquiryRequest request);

	/**
	 * Insert money transfer.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse insertMoneyTransfer(MoneyTransferMaintenanceRequest request);

	/**
	 * Insert money transfer batch.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse insertMoneyTransferBatch(MoneyTransferBatchMaintenanceRequest request);

	/**
	 * Update money transfer.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse updateMoneyTransfer(MoneyTransferMaintenanceRequest request);

	/**
	 * Update money transfer batch.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse updateMoneyTransferBatch(MoneyTransferBatchMaintenanceRequest request);

	/**
	 * Insert {@link MoneyTransferBatchStatus}.
	 *
	 * @param request The maintenance request, and it can contains a list of statuses.
	 * @return The {@link InternalResponse} containing information about whether the insert was successful or not.
	 */
	public InternalResponse insertMoneyTransferBatchStatus(MoneyTransferBatchStatusMaintenanceRequest request);

	/**
	 * Insert {@link MoneyTransferStatus}.
	 *
	 * @param request The maintenance request, and it can contains a list of statuses for each {@link MoneyTransfer}.
	 * @return The {@link InternalResponse} containing information about whether the insert was successful or not.
	 */
	public InternalResponse insertMoneyTransferStatus(MoneyTransferStatusMaintenanceRequest request);

	/**
	 * Fetch money transfer batch DTO by id.
	 *
	 * @param request the request
	 * @return the internal results response< money transfer batch dto>
	 */
	public InternalResultsResponse<MoneyTransferBatchDTO> fetchMoneyTransferBatchWithSummaryById(
			FetchByIdRequest request);

	/**
	 * Fetch a list of {@Link MoneyTransfer}'s IDs that will be approved (thru the auto approval from UI).
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Integer> fetchMoneyTransferAutoApprovalByTransferSetting(
			MoneyTransferAutoApprovalRequest request);

	/**
	 * Creates the on off money transfer batch.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<MoneyTransferBatch> createOnOffMoneyTransferBatch(
			MoneyTransferBatchCreateRequest request);

	/**
	 * Creates the on off money transfer.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<MoneyTransfer> createOnOffMoneyTransfer(MoneyTransferCreateRequest request);

}
