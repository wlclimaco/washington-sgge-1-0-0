package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.qat.framework.model.request.Request;

/**
 * This request is used when {@link com.prosperitasglobal.sendsolv.model.MoneyTransfer}s are to be created from
 * {@link TransferSetting}'s. Specify the {@link TransferSetting} that will be used to create the {@link MoneyTransfer}
 * objects.
 */
public class MoneyTransferCreateRequest extends Request
{
	/** The money transfer batch which will have a Money Transfer created. */
	private MoneyTransferBatch moneyTransferBatch;

	/** The transfer setting which will have a Money Transfer created. */
	private TransferSetting transferSetting;

	/**
	 * The Constructor.
	 */
	public MoneyTransferCreateRequest()
	{
		super();
	}

	/**
	 * Gets the money transfer batch which will have a Money Transfer created.
	 *
	 * @return The money transfer batch.
	 */
	public MoneyTransferBatch getMoneyTransferBatch()
	{
		return moneyTransferBatch;
	}

	/**
	 * Sets the money transfer batch which will have a Money Transfer created.
	 *
	 * @param moneyTransferBatch The money transfer batch.
	 */
	public void setMoneyTransferBatch(MoneyTransferBatch moneyTransferBatch)
	{
		this.moneyTransferBatch = moneyTransferBatch;
	}

	/**
	 * Gets the transfer setting which will have a Money Transfer created.
	 *
	 * @return The transfer setting.
	 */
	public TransferSetting getTransferSetting()
	{
		return transferSetting;
	}

	/**
	 * Sets the transfer setting which will have a Money Transfer created.
	 *
	 * @param transferSetting The transfer setting.
	 */
	public void setTransferSetting(TransferSetting transferSetting)
	{
		this.transferSetting = transferSetting;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferCreateRequest [getMoneyTransferBatch()=" + getMoneyTransferBatch()
				+ ", getTransferSetting()=" + getTransferSetting() + ", getUserContext()=" + getUserContext() + "]";
	}

}