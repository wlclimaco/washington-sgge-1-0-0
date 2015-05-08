package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a Data Transfer Object. The goal of this class is be a container to the {@link MoneyTransferBatchDTO} ,
 * {@link MoneyTransferBatch} and also to {@link MoneyTransferAutoApprovalDTO}
 */
public class MoneyTransferBatchDTO
{
	/** The funding summary. */
	private FundingSummaryDTO fundingSummary;

	/** The money transfer batch. */
	private MoneyTransferBatch moneyTransferBatch;

	/**
	 * @return the fundingSummary
	 */
	public FundingSummaryDTO getFundingSummary()
	{
		return fundingSummary;
	}

	/**
	 * @param fundingSummary the fundingSummary to set
	 */
	public void setFundingSummary(FundingSummaryDTO fundingSummary)
	{
		this.fundingSummary = fundingSummary;
	}

	/**
	 * @return the moneyTransferBatch
	 */
	public MoneyTransferBatch getMoneyTransferBatch()
	{
		return moneyTransferBatch;
	}

	/**
	 * @param moneyTransferBatch the moneyTransferBatch to set
	 */
	public void setMoneyTransferBatch(MoneyTransferBatch moneyTransferBatch)
	{
		this.moneyTransferBatch = moneyTransferBatch;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferBatchDTO [getFundingSummary()=" + getFundingSummary() + ", getMoneyTransferBatch()="
				+ getMoneyTransferBatch() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
