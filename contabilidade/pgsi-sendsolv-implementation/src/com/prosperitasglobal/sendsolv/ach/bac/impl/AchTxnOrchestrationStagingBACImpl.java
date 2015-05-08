package com.prosperitasglobal.sendsolv.ach.bac.impl;

import com.prosperitasglobal.sendsolv.dac.IMoneyTransferDAC;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * Implementation extension of the {@link AchTxnOrchestrationBACImpl} implementation. This implementation is identical
 * to the {@link AchTxnOrchestrationBACImpl} with the exception being this implementation will store the results of the
 * operations to the database before return the response.
 */
public class AchTxnOrchestrationStagingBACImpl extends AchTxnOrchestrationBACImpl
{
	private IMoneyTransferDAC moneyTransferDAC;

	/**
	 * Get the implementation of the {@link IMoneyTransferDAC} interface. Injected by Spring.
	 *
	 * @return The implementation.
	 */
	public IMoneyTransferDAC getMoneyTransferDAC()
	{
		return moneyTransferDAC;
	}

	/**
	 * Set the implementation of the {@link IMoneyTransferDAC} interface. Injected by Spring.
	 *
	 * @param moneyTransferDAC The implementation to set.
	 */
	public void setMoneyTransferDAC(IMoneyTransferDAC moneyTransferDAC)
	{
		this.moneyTransferDAC = moneyTransferDAC;
	}

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
	@Override
	public InternalResultsResponse<MoneyTransfer> createCancelMoneyTransferTransaction(
			MoneyTransferMaintenanceRequest request)
	{
		InternalResultsResponse<MoneyTransfer> response = super.createCancelMoneyTransferTransaction(request);

		if (response.isInError())
		{
			return response;
		}

		InternalResponse updateResponse = getMoneyTransferDAC().updateMoneyTransfer(response.getFirstResult());

		if (updateResponse.isInError())
		{
			response.merge(updateResponse);
		}

		return response;
	}

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
	@Override
	public InternalResultsResponse<MoneyTransfer> createModifyMoneyTransferTransaction(
			MoneyTransferMaintenanceRequest request)
	{
		InternalResultsResponse<MoneyTransfer> response = super.createModifyMoneyTransferTransaction(request);

		if (response.isInError())
		{
			return response;
		}

		InternalResponse updateResponse = getMoneyTransferDAC().updateMoneyTransfer(response.getFirstResult());

		if (updateResponse.isInError())
		{
			response.merge(updateResponse);
		}

		return response;
	}

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
	@Override
	public InternalResultsResponse<MoneyTransfer> createTransferMoneyTransferTransaction(
			MoneyTransferMaintenanceRequest request)
	{
		InternalResultsResponse<MoneyTransfer> response = super.createTransferMoneyTransferTransaction(request);

		if (response.isInError())
		{
			return response;
		}

		InternalResponse updateResponse = getMoneyTransferDAC().updateMoneyTransfer(response.getFirstResult());

		if (updateResponse.isInError())
		{
			response.merge(updateResponse);
		}

		return response;
	}

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
	@Override
	public InternalResultsResponse<MoneyTransfer> submitMoneyTransferTransaction(
			MoneyTransferMaintenanceRequest request)
	{
		InternalResultsResponse<MoneyTransfer> response = super.submitMoneyTransferTransaction(request);

		if (response.isInError())
		{
			return response;
		}

		InternalResponse updateResponse = getMoneyTransferDAC().updateMoneyTransfer(response.getFirstResult());

		if (updateResponse.isInError())
		{
			response.merge(updateResponse);
		}

		return response;
	}
}
