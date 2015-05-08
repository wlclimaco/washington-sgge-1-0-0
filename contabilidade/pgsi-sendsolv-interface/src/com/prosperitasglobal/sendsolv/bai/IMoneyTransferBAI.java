package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
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
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferAutoApprovalResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchDTOResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferResponse;
import com.qat.framework.model.response.MaintenanceResponse;

/**
 * The Interface IMoneyTransferBAI.
 */
public interface IMoneyTransferBAI
{
	/**
	 * Fetch {@link com.prosperitasglobal.sendsolv.model.MoneyTransfer} by id.
	 *
	 * @param request The request.
	 * @return The {@link MoneyTransferResponse} containing the
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransfer} object if found.
	 */
	public MoneyTransferResponse fetchMoneyTransferById(FetchByIdRequest request);

	/**
	 * Fetch {@link com.prosperitasglobal.sendsolv.model.MoneyTransferBatch} by id.
	 *
	 * @param request The request.
	 * @return The {@link MoneyTransferBatchResponse} containing the
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferBatch} object if found.
	 */
	public MoneyTransferBatchResponse fetchMoneyTransferBatchById(FetchByIdRequest request);

	/**
	 * Fetch {@link com.prosperitasglobal.sendsolv.model.MoneyTransfer}s by request.
	 *
	 * @param request The request.
	 * @return The {@link MoneyTransferResponse} containing {@link com.prosperitasglobal.sendsolv.model.MoneyTransfer}
	 *         objects if found.
	 */
	public MoneyTransferResponse fetchMoneyTransferByRequest(MoneyTransferInquiryRequest request);

	/**
	 * Fetch {@link com.prosperitasglobal.sendsolv.model.MoneyTransferBatch}es by request.
	 *
	 * @param request The request.
	 * @return The {@link MoneyTransferBatchResponse} containing
	 *         {@link com.prosperitasglobal.sendsolv.model.MoneyTransferBatch} objects if found.
	 */
	public MoneyTransferBatchResponse fetchMoneyTransferBatchByRequest(MoneyTransferBatchInquiryRequest request);

	/**
	 * Fetch a list of {@Link MoneyTransfer}'s IDs that will be approved (thru the auto approval from UI).
	 *
	 * @param request the request
	 * @return the money transfer auto approval response
	 */
	public MoneyTransferAutoApprovalResponse fetchMoneyTransferAutoApprovalByTransferSetting(
			MoneyTransferAutoApprovalRequest request);

	/**
	 * Update {@link com.prosperitasglobal.sendsolv.model.MoneyTransfer}.
	 *
	 * @param request The maintenance request.
	 * @return The {@link MaintenanceResponse} containing information about whether the update was successful or not.
	 */
	public MaintenanceResponse updateMoneyTransfer(MoneyTransferMaintenanceRequest request);

	/**
	 * Update {@link com.prosperitasglobal.sendsolv.model.MoneyTransferBatch}.
	 *
	 * @param request The maintenance request.
	 * @return The {@link MaintenanceResponse} containing information about whether the update was successful or not.
	 */
	public MaintenanceResponse updateMoneyTransferBatch(MoneyTransferBatchMaintenanceRequest request);

	/**
	 * Insert {@link MoneyTransferBatchStatus}.
	 *
	 * @param request The maintenance request, and it can contains a list of statuses.
	 * @return The {@link MaintenanceResponse} containing information about whether the insert was successful or not.
	 */
	public MaintenanceResponse insertMoneyTransferBatchStatus(MoneyTransferBatchStatusMaintenanceRequest request);

	/**
	 * Insert {@link MoneyTransferStatus}.
	 *
	 * @param request The maintenance request, and it can contains a list of statuses for each
	 *            {@link com.prosperitasglobal.sendsolv.model.MoneyTransfer} .
	 * @return The {@link MaintenanceResponse} containing information about whether the insert was successful or not.
	 */
	public MaintenanceResponse insertMoneyTransferStatus(MoneyTransferStatusMaintenanceRequest request);

	/**
	 * Fetch {@link MoneyTransferBatchDTO} by id.
	 *
	 * @param request The request.
	 * @return The {@link MoneyTransferBatchResponse} containing the {@link MoneyTransferBatchDTO} object if found.
	 */
	public MoneyTransferBatchDTOResponse fetchMoneyTransferBatchWithSummaryById(FetchByIdRequest request);

	/**
	 * Insert {@link MoneyTransfer}.
	 *
	 * @param request The money transfer create request {@link com.prosperitasglobal.sendsolv.model.MoneyTransfer} .
	 * @return The {@link MoneyTransferResponse} containing information about whether the insert was successful or
	 *         not.
	 */
	public MoneyTransferResponse insertMoneyTransfer(MoneyTransferCreateRequest request);

	/**
	 * Insert {@link MoneyTransfer}.
	 *
	 * @param request The money transfer batch create request {@link com.prosperitasglobal.sendsolv.model.MoneyTransfer}
	 *            .
	 * @return The {@link MoneyTransferBatchResponse} containing information about whether the insert was successful or
	 *         not.
	 */
	public MoneyTransferBatchResponse insertMoneyTransferBatch(MoneyTransferBatchCreateRequest request);

}
