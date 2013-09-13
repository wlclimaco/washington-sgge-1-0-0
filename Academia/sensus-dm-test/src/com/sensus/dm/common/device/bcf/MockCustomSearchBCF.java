package com.sensus.dm.common.device.bcf;

import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.response.ColumnFilterResponse;
import com.sensus.dm.common.device.model.response.CustomSearchResponse;
import com.sensus.dm.common.device.model.response.InquiryCustomSearchResponse;
import com.sensus.dm.common.util.AbstractMockBase;

public class MockCustomSearchBCF extends AbstractMockBase implements ICustomSearchBCF
{

	@Override
	public CustomSearchResponse insertCustomSearch(CustomSearchRequest request)
	{
		return new CustomSearchResponse();
	}

	@Override
	public CustomSearchResponse deleteCustomSearch(CustomSearchRequest request)
	{
		return new CustomSearchResponse();
	}

	@Override
	public InquiryCustomSearchResponse fetchAllCustomSearch(InquiryCustomSearchRequest inquiryCustomSearchRequest)
	{
		return new InquiryCustomSearchResponse();
	}

	@Override
	public ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		return new ColumnFilterResponse();
	}

	@Override
	public ColumnFilterResponse updateColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		return new ColumnFilterResponse();
	}

}
