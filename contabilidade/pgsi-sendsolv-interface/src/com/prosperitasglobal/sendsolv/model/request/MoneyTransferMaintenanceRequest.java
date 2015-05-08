package com.prosperitasglobal.sendsolv.model.request;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MoneyTransferMaintenanceRequest.
 */
public class MoneyTransferMaintenanceRequest extends MaintenanceRequest
{
	/** The money transfer list. */
	private List<MoneyTransfer> moneyTransferList;

	/**
	 * Get the money transfer.
	 *
	 * @return The money transfer.
	 */
	public MoneyTransfer getMoneyTransfer()
	{
		if (!ValidationUtil.isNullOrEmpty(moneyTransferList))
		{
			return moneyTransferList.get(0);
		}

		return null;
	}

	/**
	 * Set the money transfer.
	 *
	 * @param moneyTransfer The money transfer to set.
	 */
	public void setMoneyTransfer(MoneyTransfer moneyTransfer)
	{
		moneyTransferList = new ArrayList<MoneyTransfer>();
		moneyTransferList.add(moneyTransfer);
	}

	/**
	 * @return the moneyTransferList
	 */
	public List<MoneyTransfer> getMoneyTransferList()
	{
		return moneyTransferList;
	}

	/**
	 * @param moneyTransferList the moneyTransferList to set
	 */
	public void setMoneyTransferList(List<MoneyTransfer> moneyTransferList)
	{
		this.moneyTransferList = moneyTransferList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferMaintenanceRequest [getMoneyTransfer()=" + getMoneyTransfer()
				+ ", getMoneyTransferList()=" + getMoneyTransferList() + ", getUserContext()=" + getUserContext() + "]";
	}

}