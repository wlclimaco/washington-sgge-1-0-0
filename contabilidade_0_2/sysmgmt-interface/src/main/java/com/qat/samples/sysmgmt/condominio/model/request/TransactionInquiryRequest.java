package com.qat.samples.sysmgmt.condominio.model.request;

import com.qat.samples.sysmgmt.entidade.model.Transaction;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class TransactionInquiryRequest extends PagedInquiryRequest
{

	private Transaction transaction;



	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public TransactionInquiryRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TransactionInquiryRequest [getTransaction()=" + getTransaction() + ", toString()=" + super.toString()
				+ "]";
	}

}
