package com.qat.samples.complexmo.model;

import java.util.Date;

import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class EFT extends QATModel
{
	private Integer parentKey = -1;
	private String bank;
	private String accountNumber;
	private Date effectiveStartDate;
	private Date effectiveEndDate;

	public String getBank()
	{
		return bank;
	}

	public Integer getParentKey()
	{
		return parentKey;
	}

	public void setParentKey(Integer parentKey)
	{
		this.parentKey = parentKey;
	}

	public void setBank(String bank)
	{
		this.bank = bank;
	}

	public String getAccountNumber()
	{
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
	}

	public Date getEffectiveStartDate()
	{
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate)
	{
		this.effectiveStartDate = effectiveStartDate;
	}

	public Date getEffectiveEndDate()
	{
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate)
	{
		this.effectiveEndDate = effectiveEndDate;
	}

	@Override
	public String toString()
	{
		return "EFT [getAccountNumber()=" + getAccountNumber() + ", getBank()=" + getBank() + ", getEffectiveToDate()="
				+ getEffectiveStartDate() + ", getEffectiveEndDate()="
				+ getEffectiveEndDate() + ", getParentKey()=" + getParentKey() + ", getModelAction()="
				+ getModelAction() + "]";
	}
}