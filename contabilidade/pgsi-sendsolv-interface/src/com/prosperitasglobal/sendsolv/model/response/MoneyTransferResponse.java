package com.prosperitasglobal.sendsolv.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.qat.framework.model.response.InquiryResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MoneyTransferResponse.
 */
public class MoneyTransferResponse extends InquiryResponse
{
	/** The list of money transfers. */
	private List<MoneyTransfer> moneyTransferList;

	/**
	 * Get the list of {@link MoneyTransfer}s. If the list is <code>null</code>, an empty list will be returned.
	 *
	 * @return The {@link MoneyTransfer} list.
	 */
	public List<MoneyTransfer> getMoneyTransferList()
	{
		if (ValidationUtil.isNull(moneyTransferList))
		{
			setMoneyTransferList(new ArrayList<MoneyTransfer>());
		}

		return moneyTransferList;
	}

	/**
	 * Set the list of {@link MoneyTransfer}s.
	 *
	 * @param moneyTransferList The {@link MoneyTransfer} list to set.
	 */
	public void setMoneyTransferList(List<MoneyTransfer> moneyTransferList)
	{
		this.moneyTransferList = moneyTransferList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setMoneyTransferList((List<MoneyTransfer>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferResponse [getMoneyTransferList()=" + getMoneyTransferList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}
}
