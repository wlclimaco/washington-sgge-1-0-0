package com.prosperitasglobal.sendsolv.callingcard.model;

import java.math.BigDecimal;

import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.ProductPlan;
import com.qat.framework.model.QATModel;

/**
 * This class is a representation of a Calling Card. A Calling card is assigned to a {@link Member} once the
 * {@link Member} is activated.
 * A Calling has its balance increased by a certain amount when a successful {@link MoneyTransfer} is created. The
 * amount is determined by the {@link ProductPlan} associated to that {@link MoneyTransfer}.
 */
@SuppressWarnings("serial")
public class CallingCardInfo extends QATModel
{
	private Integer personId;

	private Integer callingCardId;

	private String lotCode;

	private String callingCardNumber;

	/** The amount to add/delete. */
	private BigDecimal amount;

	private BigDecimal balance;

	public Integer getPersonId()
	{
		return personId;
	}

	public void setPersonId(Integer personId)
	{
		this.personId = personId;
	}

	public Integer getCallingCardId()
	{
		return callingCardId;
	}

	public void setCallingCardId(Integer callingCardId)
	{
		this.callingCardId = callingCardId;
	}

	public String getLotCode()
	{
		return lotCode;
	}

	public void setLotCode(String lotCode)
	{
		this.lotCode = lotCode;
	}

	public String getCallingCardNumber()
	{
		return callingCardNumber;
	}

	public void setCallingCardNumber(String callingCardNumber)
	{
		this.callingCardNumber = callingCardNumber;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public BigDecimal getBalance()
	{
		return balance;
	}

	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}

	@Override
	public String toString()
	{
		return "CallingCardInfo [getPersonId()=" + getPersonId() + ", getCallingCardId()=" + getCallingCardId()
				+ ", getLotCode()=" + getLotCode() + ", getCallingCardNumber()=" + getCallingCardNumber()
				+ ", getAmount()=" + getAmount() + ", getBalance()=" + getBalance() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + "]";
	}

}
