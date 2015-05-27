package com.prosperitasglobal.sendsolv.filter.impl;

import org.springframework.stereotype.Component;

import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.qat.framework.model.UserContext;

/**
 * The Class FilterTags.
 */
@Component
public class FilterTransactionStatus extends AbstractFilterBase
{

	@Override
	public boolean isAssignableFrom(String filter)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		// TODO Auto-generated method stub

	}
	// /** The Constant TAGS. */
	// private static final String TRANSFERSTATUS = "TRANSFERSTATUS";
	//
	// /*
	// * (non-Javadoc)
	// * @see com.prosperitasglobal.sendsolv.filter.model.IFilter#isAssignableFrom(java.lang.String)
	// */
	// @Override
	// public boolean isAssignableFrom(String filter)
	// {
	// return TRANSFERSTATUS.equals(filter);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.prosperitasglobal.sendsolv.filter.model.IFilter#createFilter(com.qat.framework.model.UserContext,
	// * com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse, java.lang.Object[])
	// */
	// @Override
	// public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	// {
	//
	// {
	// List<List<String>> records = new ArrayList<List<String>>();
	//
	// records.add(Arrays.asList(MoneyTransferStatusEnum.PENDING_APPROVAL.getValue().toString(),
	// MoneyTransferStatusEnum.PENDING_APPROVAL.getLabelKey()));
	// records.add(Arrays.asList(MoneyTransferStatusEnum.APPROVED.getValue().toString(),
	// MoneyTransferStatusEnum.APPROVED.getLabelKey()));
	// records.add(Arrays.asList(MoneyTransferStatusEnum.ORDER_SUBMITTED.getValue().toString(),
	// MoneyTransferStatusEnum.ORDER_SUBMITTED.getLabelKey()));
	// records.add(Arrays.asList(MoneyTransferStatusEnum.PENDING_ACH.getValue().toString(),
	// MoneyTransferStatusEnum.PENDING_ACH.getLabelKey()));
	// records.add(Arrays.asList(MoneyTransferStatusEnum.EXCEPTION.getValue().toString(),
	// MoneyTransferStatusEnum.EXCEPTION.getLabelKey()));
	// records.add(Arrays.asList(MoneyTransferStatusEnum.ON_HOLD.getValue().toString(),
	// MoneyTransferStatusEnum.ON_HOLD.getLabelKey()));
	// records.add(Arrays.asList(MoneyTransferStatusEnum.NOT_PAID.getValue().toString(),
	// MoneyTransferStatusEnum.NOT_PAID.getLabelKey()));
	// records.add(Arrays.asList(MoneyTransferStatusEnum.MODIFICATION_SUBMITTED.getValue().toString(),
	// MoneyTransferStatusEnum.MODIFICATION_SUBMITTED.getLabelKey()));
	// records.add(Arrays.asList(MoneyTransferStatusEnum.CANCELLATION_SUBMITTED.getValue().toString(),
	// MoneyTransferStatusEnum.CANCELLATION_SUBMITTED.getLabelKey()));
	// records.add(Arrays.asList(MoneyTransferStatusEnum.REJECTED.getValue().toString(),
	// MoneyTransferStatusEnum.REJECTED.getLabelKey()));
	// records.add(Arrays.asList(MoneyTransferStatusEnum.SEIZED.getValue().toString(),
	// MoneyTransferStatusEnum.SEIZED.getLabelKey()));
	// records.add(Arrays.asList(MoneyTransferStatusEnum.CANCELLED.getValue().toString(),
	// MoneyTransferStatusEnum.CANCELLED.getLabelKey()));
	// records.add(Arrays.asList(MoneyTransferStatusEnum.PAID.getValue().toString(),
	// MoneyTransferStatusEnum.PAID.getLabelKey()));
	//
	// filtersResponse.addFilter(TRANSFERSTATUS.toLowerCase(), records);
	// }
	// }
}
