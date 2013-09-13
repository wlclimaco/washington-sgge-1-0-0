package com.sensus.dm.common.device.bcl;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.Column;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.DeviceFilterEnum;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.response.ColumnFilterResponse;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class MockCustomSearchBCL.
 */
public class MockCustomSearchBCL extends AbstractMockBase implements ICustomSearchBCL
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.ICustomSearchBCL#insertCustomSearch(com.sensus.dm.elec.device.model.request.
	 * CustomSearchRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request)
	{
		return customSearchResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.ICustomSearchBCL#deleteCustomSearch(com.sensus.dm.elec.device.model.request.
	 * CustomSearchRequest
	 * )
	 */
	@Override
	public InternalResponse deleteCustomSearch(CustomSearchRequest request)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.ICustomSearchBCL#fetchAllCustomSearch(com.sensus.dm.elec.device.model.request.
	 * InquiryCustomSearchRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> fetchAllCustomSearch(InquiryCustomSearchRequest request)
	{
		return customSearchResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcl.ICustomSearchBCL#updateColumnFilters(com.sensus.dm.elec.device.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse updateColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.ICustomSearchBCL#fetchAllColumnFilters(com.sensus.dm.elec.device.model.request.
	 * ColumnFilterRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		InternalResultsResponse<ColumnFilterResponse> response = new InternalResultsResponse<ColumnFilterResponse>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			ColumnFilterResponse columnFilterResponse = new ColumnFilterResponse();
			List<Column> columns = new ArrayList<Column>();
			columns.add(new Column(DeviceColumnEnum.FLEXNET_ID));
			columnFilterResponse.setListColumn(columns);
			columnFilterResponse.setFilters(new ArrayList<DeviceFilterEnum>());
			response.addResult(columnFilterResponse);
			return response;
		}

		return (InternalResultsResponse<ColumnFilterResponse>)verifyOtherSituations();

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcl.ICustomSearchBCL#fetchAllColumnFilter(com.sensus.dm.elec.device.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResultsResponse<Property> fetchAllColumnFilter(ColumnFilterRequest request)
	{
		return new InternalResultsResponse<Property>();
	}

	/**
	 * Custom search results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<CustomSearch> customSearchResultsResponse()
	{
		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createCustomSearch(DeviceTypeEnum.ELECTRIC_METER));
			return response;
		}

		return (InternalResultsResponse<CustomSearch>)verifyOtherSituations();
	}
}