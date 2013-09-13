package com.sensus.dm.common.property.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class MockPropertyDAC.
 * 
 * @author QAT Global.
 */
public class MockPropertyDAC extends AbstractMockBase implements IPropertyDAC
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.dac.IPropertyDAC#fetchProperty(com.sensus.dm.common.property.model.request.
	 * PropertyRequest)
	 */
	@Override
	public InternalResultsResponse<Property> fetchProperty(PropertyRequest propertyRequest)
	{
		return new InternalResultsResponse<Property>(TestBaseUtil.createPropertyList());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.dac.IPropertyDAC#upsertProperty(com.sensus.dm.common.property.model.request.
	 * PropertyRequest)
	 */
	@Override
	public InternalResponse upsertProperty(PropertyRequest propertyRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.dac.IPropertyDAC#deleteProperty(com.sensus.dm.common.property.model.request.
	 * PropertyRequest)
	 */
	@Override
	public InternalResponse deleteProperty(PropertyRequest propertyRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.dac.IPropertyDAC#deletePropertyProvider(com.sensus.dm.common.property.model.request.
	 * PropertyRequest
	 * )
	 */
	@Override
	public InternalResponse deletePropertyProvider(PropertyRequest propertyRequest)
	{
		return new InternalResponse();
	}

}
