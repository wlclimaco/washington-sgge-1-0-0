package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferCreateRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class MoneyTransferStagingBACImpl. This class extends {@link MoneyTransferBACImpl} so that the create* methods
 * will also store the object to the database.
 */
public class MoneyTransferStagingBACImpl extends MoneyTransferBACImpl
{
	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#createMoneyTransferBatch(com.prosperitasglobal.sendsolv.
	 * model.request.LocationMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<MoneyTransferBatch> createMoneyTransferBatch(MoneyTransferBatchCreateRequest request)
	{
		InternalResultsResponse<MoneyTransferBatch> response = super.createMoneyTransferBatch(request);

		if (response.isInError())
		{
			return response;
		}

		InternalResponse insertResponse =
				getMoneyTransferBatchDAC().insertMoneyTransferBatch(response.getFirstResult());
		response.merge(insertResponse);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#createMoneyTransferBatch(com.prosperitasglobal.sendsolv.
	 * model.request.LocationMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<MoneyTransfer> createMoneyTransfer(MoneyTransferCreateRequest request)
	{
		InternalResultsResponse<MoneyTransfer> response = super.createMoneyTransfer(request);

		if (response.isInError())
		{
			return response;
		}

		InternalResponse insertResponse = getMoneyTransferDAC().insertMoneyTransfer(response.getFirstResult());
		response.merge(insertResponse);
		return response;
	}
}
