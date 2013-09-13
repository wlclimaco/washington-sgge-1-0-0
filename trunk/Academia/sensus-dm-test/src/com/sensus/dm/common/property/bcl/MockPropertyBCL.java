package com.sensus.dm.common.property.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;

/**
 * The Class MockPropertyBCL.
 * 
 * @author QAT Global.
 */
public class MockPropertyBCL extends AbstractMockBase implements IPropertyBCL
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.bcl.IPropertyBCL#upsertProperty(com.sensus.dm.common.property.model.request.
	 * PropertyRequest
	 * )
	 */
	@Override
	public InternalResponse upsertProperty(PropertyRequest propertyRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResponseError();
		}

		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.bcl.IPropertyBCL#deletePropertyProvider(com.sensus.dm.common.property.model.request
	 * .
	 * PropertyRequest)
	 */
	@Override
	public InternalResponse deletePropertyProvider(PropertyRequest propertyRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResponseError();
		}

		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.bcl.IPropertyBCL#deleteProperty(com.sensus.dm.common.property.model.request.
	 * PropertyRequest
	 * )
	 */
	@Override
	public InternalResponse deleteProperty(PropertyRequest propertyRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResponseError();
		}

		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.bcl.IPropertyBCL#fetchProperty(com.sensus.dm.common.property.model.request.
	 * PropertyRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Property> fetchProperty(PropertyRequest propertyRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResultsResponse<Property>();
	}
}
