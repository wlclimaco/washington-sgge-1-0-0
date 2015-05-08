package com.prosperitasglobal.sendsolv.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.qat.framework.model.response.InquiryResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MoneyTransferBatchResponse.
 */
public class MoneyTransferBatchResponse extends InquiryResponse
{
	/** The list of money transfer batches. */
	private List<MoneyTransferBatch> moneyTransferBatchList;

	/**
	 * Get the list of {@link MoneyTransferBatch}es. If the list is <code>null</code>, an empty list will be returned.
	 *
	 * @return The {@link MoneyTransferBatch} list.
	 */
	public List<MoneyTransferBatch> getMoneyTransferBatchList()
	{
		if (ValidationUtil.isNull(moneyTransferBatchList))
		{
			setMoneyTransferBatchList(new ArrayList<MoneyTransferBatch>());
		}

		return moneyTransferBatchList;
	}

	/**
	 * Set the list of {@link MoneyTransferBatch}es.
	 *
	 * @param moneyTransferBatchList The {@link MoneyTransferBatch} list to set.
	 */
	public void setMoneyTransferBatchList(List<MoneyTransferBatch> moneyTransferBatchList)
	{
		this.moneyTransferBatchList = moneyTransferBatchList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setMoneyTransferBatchList((List<MoneyTransferBatch>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferBatchResponse [getMoneyTransferBatchList()=" + getMoneyTransferBatchList()
				+ ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}
}
