package com.prosperitasglobal.sendsolv.model.request;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatus;

/**
 * The Class MoneyTransferBatchStatusMaintenanceRequest
 */
public class MoneyTransferBatchStatusMaintenanceRequest extends MaintenanceRequest
{
	/** The list of status that will be inserted. */
	private List<MoneyTransferBatchStatus> moneyTransferBatchStatusList;

	/** The comment/note related with the status. */
	private String note;

	/**
	 * Get the money transfer batch status list.
	 *
	 * @return the moneyTransferBatchStatusList
	 */
	public List<MoneyTransferBatchStatus> getMoneyTransferBatchStatusList()
	{
		return moneyTransferBatchStatusList;
	}

	/**
	 * Set the money transfer batch status list.
	 *
	 * @param moneyTransferBatchStatusList the moneyTransferBatchStatusList to set
	 */
	public void setMoneyTransferBatchStatusList(List<MoneyTransferBatchStatus> moneyTransferBatchStatusList)
	{
		this.moneyTransferBatchStatusList = moneyTransferBatchStatusList;
	}

	/**
	 * @return the note
	 */
	public String getNote()
	{
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note)
	{
		this.note = note;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferBatchStatusMaintenanceRequest [getMoneyTransferBatchStatusList()="
				+ getMoneyTransferBatchStatusList() + ", getNote()=" + getNote() + ", getUserContext()="
				+ getUserContext() + "]";
	}

}
