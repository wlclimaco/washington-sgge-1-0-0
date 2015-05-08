package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.qat.framework.validation.ValidationUtil;

/**
 * The Class TransferParticipant.
 *
 * @author abarros
 * @version 1.0
 * @created 05-Sep-2014 11:17:45 AM
 */
@SuppressWarnings("serial")
public class TransferParticipant extends Person
{

	/** The transfer setting list. */
	private List<TransferSetting> transferSettingList;

	/**
	 * The Constructor.
	 */
	public TransferParticipant()
	{

	}

	/**
	 * Gets the transfer setting list.
	 *
	 * @return the transfer setting list
	 */
	public List<TransferSetting> getTransferSettingList()
	{
		if (ValidationUtil.isNull(transferSettingList))
		{
			setTransferSettingList(new ArrayList<TransferSetting>());
		}

		return transferSettingList;
	}

	/**
	 * Sets the transfer setting list.
	 *
	 * @param transferSettingList the transfer setting list
	 */
	public void setTransferSettingList(List<TransferSetting> transferSettingList)
	{
		this.transferSettingList = transferSettingList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TransferParticipant [getTransferSettingList()=" + getTransferSettingList() + ", toString()="
				+ super.toString() + "]";
	}
}