package com.prosperitasglobal.sendsolv.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchDTO;
import com.qat.framework.model.response.InquiryResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MoneyTransferBatchDTOResponse.
 */
public class MoneyTransferBatchDTOResponse extends InquiryResponse
{
	/** The list of money transfer batch DTOs. */
	private List<MoneyTransferBatchDTO> moneyTransferBatchDTOList;

	/**
	 * Get the list of {@link MoneyTransferBatchDTO}. If the list is <code>null</code>, an empty list will be returned.
	 *
	 * @return The {@link MoneyTransferBatchDTO} list.
	 */
	public List<MoneyTransferBatchDTO> getMoneyTransferBatchDTOList()
	{
		if (ValidationUtil.isNull(moneyTransferBatchDTOList))
		{
			setMoneyTransferBatchDTOList(new ArrayList<MoneyTransferBatchDTO>());
		}

		return moneyTransferBatchDTOList;
	}

	/**
	 * Set the list of {@link MoneyTransferBatchDTO}.
	 *
	 * @param moneyTransferBatchDTOList The {@link MoneyTransferBatchDTO} list to set.
	 */
	public void setMoneyTransferBatchDTOList(List<MoneyTransferBatchDTO> moneyTransferBatchDTOList)
	{
		this.moneyTransferBatchDTOList = moneyTransferBatchDTOList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setMoneyTransferBatchDTOList((List<MoneyTransferBatchDTO>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferBatchDTOResponse [getMoneyTransferBatchDTOList()=" + getMoneyTransferBatchDTOList()
				+ ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
