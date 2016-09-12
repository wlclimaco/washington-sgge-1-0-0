package com.qat.samples.sysmgmt.condominio.model.request;

import com.qat.samples.sysmgmt.entidade.model.Transaction;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class TransactionMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Transaction transaction;

	/**
	 * The Constructor.
	 */
	public TransactionMaintenanceRequest()
	{

	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "TransactionMaintenanceRequest [getTransaction()=" + getTransaction() + ", toString()="
				+ super.toString() + "]";
	}



}