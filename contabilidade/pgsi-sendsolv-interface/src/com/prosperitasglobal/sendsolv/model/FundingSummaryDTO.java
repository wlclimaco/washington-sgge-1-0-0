package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;

/**
 * This class is a Data Transfer Object. The goal of this class is bring to the FE the information related with the
 * Funding (Totals) and with that the FE does not need to the calculations on the client side.
 */
public class FundingSummaryDTO
{
	/** Sum of total amount of all transactions. */
	private BigDecimal batchTotal;

	/**
	 * Sum of total amount of all approved and on-hold transactions (i.e. the expected payroll amount). If the batch was
	 * just created, this number starts at the amount of On Hold transactions. The amount will increase in real-time as
	 * transactions are approved.
	 */
	private BigDecimal payrollAmount;

	/**
	 * Sum of total amount of all approved transactions (i.e. the amount that should be sent to the ACH and needs to be
	 * funded in foreign currency).
	 */
	private BigDecimal transferTotal;

	/**
	 * Sum of total amount of all approved transactions belonging to prefund payers. Show only for batches in Pending
	 * Approval and PGSi Prefunding.
	 */
	private BigDecimal prefunds;

	/**
	 * Sum of total amount of all submitted transactions belonging to postfund payers. Show only for batches in ACH
	 * Processing/ACH Errors and PGSi Postfunding.
	 */
	private BigDecimal postfunds;

	/**
	 * Many parameter constructor.
	 * 
	 * @param batchTotal The batch total.
	 * @param payrollAmount The payroll amount.
	 * @param transferTotal The transfer total.
	 * @param prefunds The prefunds.
	 * @param postfunds The postfunds.
	 */
	public FundingSummaryDTO(BigDecimal batchTotal, BigDecimal payrollAmount, BigDecimal transferTotal,
			BigDecimal prefunds, BigDecimal postfunds)
	{
		super();
		this.batchTotal = batchTotal;
		this.payrollAmount = payrollAmount;
		this.transferTotal = transferTotal;
		this.prefunds = prefunds;
		this.postfunds = postfunds;
	}

	/**
	 * @return the batchTotal
	 */
	public BigDecimal getBatchTotal()
	{
		return batchTotal;
	}

	/**
	 * @param batchTotal the batchTotal to set
	 */
	public void setBatchTotal(BigDecimal batchTotal)
	{
		this.batchTotal = batchTotal;
	}

	/**
	 * @return the payrollAmount
	 */
	public BigDecimal getPayrollAmount()
	{
		return payrollAmount;
	}

	/**
	 * @param payrollAmount the payrollAmount to set
	 */
	public void setPayrollAmount(BigDecimal payrollAmount)
	{
		this.payrollAmount = payrollAmount;
	}

	/**
	 * @return the transferTotal
	 */
	public BigDecimal getTransferTotal()
	{
		return transferTotal;
	}

	/**
	 * @param transferTotal the transferTotal to set
	 */
	public void setTransferTotal(BigDecimal transferTotal)
	{
		this.transferTotal = transferTotal;
	}

	/**
	 * @return the prefunds
	 */
	public BigDecimal getPrefunds()
	{
		return prefunds;
	}

	/**
	 * @param prefunds the prefunds to set
	 */
	public void setPrefunds(BigDecimal prefunds)
	{
		this.prefunds = prefunds;
	}

	/**
	 * @return the postfunds
	 */
	public BigDecimal getPostfunds()
	{
		return postfunds;
	}

	/**
	 * @param postfunds the postfunds to set
	 */
	public void setPostfunds(BigDecimal postfunds)
	{
		this.postfunds = postfunds;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FundingSummaryDTO [getBatchTotal()=" + getBatchTotal() + ", getPayrollAmount()=" + getPayrollAmount()
				+ ", getTransferTotal()=" + getTransferTotal() + ", getPrefunds()=" + getPrefunds()
				+ ", getPostfunds()=" + getPostfunds() + "]";
	}

}
