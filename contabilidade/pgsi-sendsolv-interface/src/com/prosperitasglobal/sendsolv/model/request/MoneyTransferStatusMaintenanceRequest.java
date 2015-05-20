package com.prosperitasglobal.sendsolv.model.request;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;

/**
 * The Class MoneyTransferStatusMaintenanceRequest
 */
public class MoneyTransferStatusMaintenanceRequest extends MaintenanceRequest
{
	/** The list os status that will be inserted for each {@link com.prosperitasglobal.sendsolv.model.MoneyTransfer}. */
	private List<MoneyTransferStatus> moneyTransferStatusList;

	/** The comment/note related with the status. */
	private String note;

	/**
	 * @return the moneyTransferStatusList
	 */
	public List<MoneyTransferStatus> getMoneyTransferStatusList()
	{
		return moneyTransferStatusList;
	}

	/**
	 * @param moneyTransferStatusList the moneyTransferStatusList to set
	 */
	public void setMoneyTransferStatusList(List<MoneyTransferStatus> moneyTransferStatusList)
	{
		this.moneyTransferStatusList = moneyTransferStatusList;
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
		return "MoneyTransferStatusMaintenanceRequest [getMoneyTransferStatusList()=" + getMoneyTransferStatusList()
				+ ", getNote()=" + getNote() + ", getUserContext()=" + getUserContext() + "]";
	}

}
