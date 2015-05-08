package com.prosperitasglobal.sendsolv.integration.twilio.model;

import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class MoneyTransferSummary extends QATModel
{
	private Integer moneyTransferId;
	private Integer personId;
	private String firstName;
	private String lastName;

	/** The external number from an external automated clearing house. */
	private String confirmationNumber;

	public Integer getPersonId()
	{
		return personId;
	}

	public void setPersonId(Integer personId)
	{
		this.personId = personId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getConfirmationNumber()
	{
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber)
	{
		this.confirmationNumber = confirmationNumber;
	}

	public Integer getMoneyTransferId()
	{
		return moneyTransferId;
	}

	public void setMoneyTransferId(Integer moneyTransferId)
	{
		this.moneyTransferId = moneyTransferId;
	}

	@Override
	public String toString()
	{
		return "MoneyTransferSummary [getPersonId()=" + getPersonId() + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getConfirmationNumber()=" + getConfirmationNumber()
				+ ", getMoneyTransferId()=" + getMoneyTransferId() + ", toString()=" + super.toString() + "]";
	}
}
