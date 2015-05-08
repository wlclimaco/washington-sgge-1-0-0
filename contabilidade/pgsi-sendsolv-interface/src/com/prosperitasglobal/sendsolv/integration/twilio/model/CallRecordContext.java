package com.prosperitasglobal.sendsolv.integration.twilio.model;

import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.qat.framework.model.QATModel;

/**
 * The Class CallRecordContext is used to store {@link MoneyTransfer.Id}
 */
@SuppressWarnings("serial")
public class CallRecordContext extends QATModel
{

	/** The parent {@link CallRecord} id. */
	private Integer parentId;

	/** The ivr option. */
	private Integer ivrOption;

	/** The money transfer id. */
	private Integer moneyTransferId;

	private String recipientFirstName;

	private String recipientLastName;

	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	public Integer getParentId()
	{
		return parentId;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentId the new parent id
	 */
	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	/**
	 * Gets the ivr option.
	 *
	 * @return the ivr option
	 */
	public Integer getIvrOption()
	{
		return ivrOption;
	}

	/**
	 * Sets the ivr option.
	 *
	 * @param ivrOption the new ivr option
	 */
	public void setIvrOption(Integer ivrOption)
	{
		this.ivrOption = ivrOption;
	}

	/**
	 * Gets the money transfer id.
	 *
	 * @return the money transfer id
	 */
	public Integer getMoneyTransferId()
	{
		return moneyTransferId;
	}

	/**
	 * Sets the money transfer id.
	 *
	 * @param moneyTransferId the new money transfer id
	 */
	public void setMoneyTransferId(Integer moneyTransferId)
	{
		this.moneyTransferId = moneyTransferId;
	}

	public String getRecipientFirstName()
	{
		return recipientFirstName;
	}

	public void setRecipientFirstName(String recipientFirstName)
	{
		this.recipientFirstName = recipientFirstName;
	}

	public String getRecipientLastName()
	{
		return recipientLastName;
	}

	public void setRecipientLastName(String recipientLastName)
	{
		this.recipientLastName = recipientLastName;
	}

	@Override
	public String toString()
	{
		return "CallRecordContext [getParentId()=" + getParentId() + ", getIvrOption()=" + getIvrOption()
				+ ", getMoneyTransferId()=" + getMoneyTransferId() + ", getRecipientFirstName()="
				+ getRecipientFirstName() + ", getRecipientLastName()=" + getRecipientLastName() + ", toString()="
				+ super.toString() + "]";
	}
}
