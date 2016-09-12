package com.qat.samples.sysmgmt.condominio.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.condominio.model.Avisos;
import com.qat.samples.sysmgmt.entidade.model.Transaction;

public class TransactionResponse extends InquiryResponse
{

	/** Attributes */
	private List<Transaction> transactionList;

	/**
	 * The Constructor.
	 */
	public TransactionResponse()
	{

	}



	public List<Transaction> getTransactionList() {
		return transactionList;
	}



	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}



	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setTransactionList((List<Transaction>)coll);
	}


	@Override
	public String toString() {
		return "TransactionResponse [getTransactionList()=" + getTransactionList() + ", toString()=" + super.toString()
				+ "]";
	}



}