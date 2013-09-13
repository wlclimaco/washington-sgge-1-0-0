package com.sensus.dm.common.device.dac;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class MockCustomSearchDAC.
 * 
 * @author QAT Global.
 */
public class MockCustomSearchDAC extends AbstractMockBase implements ICustomSearchDAC
{

	/** The Constant STRING_ONE. */
	private static final String STRING_ONE = "1";

	/** The Constant SMARTPOINT_COLUMN. */
	private static final String SMARTPOINT_COLUMN = "SMARTPOINT_COLUMN";

	/** The Constant SMARTPOINT_FILTER. */
	private static final String SMARTPOINT_FILTER = "SMARTPOINT_FILTER";

	/** The Constant STRING_ZERO. */
	private static final String STRING_ZERO = "0";

	/** The Constant TEN. */
	private static final int TEN = 10;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.ICustomSearchDAC#canCustomSearchBeInserted(com.sensus.dm.elec.device.model.request.
	 * CustomSearchRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Boolean> canCustomSearchBeInserted(CustomSearchRequest customSearchRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResultsResponse<Boolean>(Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.ICustomSearchDAC#insertCustomSearch(com.sensus.dm.elec.device.model.request.
	 * CustomSearchRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		request.getCustomSearch().setId(TEN);

		return new InternalResultsResponse<CustomSearch>(request.getCustomSearch());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.ICustomSearchDAC#deleteCustomSearch(com.sensus.dm.elec.device.model.request.
	 * CustomSearchRequest
	 * )
	 */
	@Override
	public InternalResponse deleteCustomSearch(CustomSearchRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.ICustomSearchDAC#fetchAllCustomSearch(com.sensus.dm.elec.device.model.request.
	 * InquiryCustomSearchRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<CustomSearch> fetchAllCustomSearch(InquiryCustomSearchRequest request)
	{
		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(TestBaseUtil.createCustomSearch(DeviceTypeEnum.ELECTRIC_METER));
			response.addResult(TestBaseUtil.createCustomSearch(DeviceTypeEnum.LCM));
			response.addResult(TestBaseUtil.createCustomSearch(DeviceTypeEnum.HAN_DEVICE));
			response.addResult(TestBaseUtil.createCustomSearch(DeviceTypeEnum.WATER_METER));

			return response;
		}

		return (InternalResultsResponse<CustomSearch>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.dac.ICustomSearchDAC#fetchAllColumnFilter(com.sensus.dm.elec.device.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResultsResponse<Property> fetchAllColumnFilter(ColumnFilterRequest request)
	{
		InternalResultsResponse<Property> response = new InternalResultsResponse<Property>();

		if (!ValidationUtil.isNullOrEmpty(request.getPropertyProviderType())
				&& request.getPropertyProviderType().equals("EPM.User.Column fail"))
		{
			response.setStatus(Status.ExceptionError);
			return response;
		}

		if (!ValidationUtil.isNullOrEmpty(request.getPropertyProviderType()))
		{
			if (request.getPropertyProviderType().equals("EPM.Group 1"))
			{
				response.addResult(new Property("ORDER_BY", "name ASC", 0));
				response.addResult(new Property("ADDRESS", "121 ACME Street", 0));
				response.addResult(new Property("CITY", "121 ACME City", 0));
				response.addResult(new Property("ZIP", "123", 0));
				response.addResult(new Property("flexnet_id", "1001", 0));
				response.addResult(new Property("DEVICE_ID", "1001M", 0));
				response.addResult(new Property("TAG", STRING_ONE, 0));
				response.addResult(new Property("GROUP_ID", STRING_ONE, 0));
				response.addResult(new Property("DEVICE_TYPE", STRING_ONE, 0));
				response.addResult(new Property("DESCRIPTION", STRING_ONE, 0));
				response.addResult(new Property(SMARTPOINT_COLUMN, SMARTPOINT_COLUMN, 1));
				response.addResult(new Property(SMARTPOINT_FILTER, SMARTPOINT_FILTER, 1));
			}
			else if (request.getPropertyProviderType().equals("EPM.User.Column 99"))
			{
				response.addResult(new Property(SMARTPOINT_COLUMN, STRING_ZERO, 0));
				response.addResult(new Property(SMARTPOINT_FILTER, STRING_ZERO, 0));
			}

			return response;
		}

		return new InternalResultsResponse<Property>();
	}
}