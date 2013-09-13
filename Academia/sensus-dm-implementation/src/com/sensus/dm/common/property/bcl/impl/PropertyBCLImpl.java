package com.sensus.dm.common.property.bcl.impl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.bcl.IPropertyBCL;
import com.sensus.dm.common.property.dac.IPropertyDAC;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.request.PropertyRequest;

/**
 * The Class PropertyBCLImpl.
 * 
 * @author QAT Brazil.
 * 
 */
public class PropertyBCLImpl implements IPropertyBCL
{

	/** The property dac. */
	private IPropertyDAC propertyDAC;

	/**
	 * Gets the property dac.
	 * 
	 * @return the property dac
	 */
	public IPropertyDAC getPropertyDAC()
	{
		return propertyDAC;
	}

	/**
	 * Sets the property dac.
	 * 
	 * @param propertyDAC the new property dac
	 */
	public void setPropertyDAC(IPropertyDAC propertyDAC)
	{
		this.propertyDAC = propertyDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.bcl.IPropertyBCL#upsertProperty(com.sensus.dm.common.property.model.request.PropertyRequest)
	 */
	@Override
	public InternalResponse upsertProperty(PropertyRequest propertyRequest)
	{
		return getPropertyDAC().upsertProperty(propertyRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.bcl.IPropertyBCL#deleteProperty(com.sensus.dm.common.property.model.request.PropertyRequest)
	 */
	@Override
	public InternalResponse deleteProperty(PropertyRequest propertyRequest)
	{
		return getPropertyDAC().deleteProperty(propertyRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.bcl.IPropertyBCL#deletePropertyProvider(com.sensus.dm.common.property.model.request.PropertyRequest
	 * )
	 */
	@Override
	public InternalResponse deletePropertyProvider(PropertyRequest propertyRequest)
	{
		return getPropertyDAC().deletePropertyProvider(propertyRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.bcl.IPropertyBCL#fetchProperty(com.sensus.dm.common.property.model.request.PropertyRequest)
	 */
	@Override
	public InternalResultsResponse<Property> fetchProperty(PropertyRequest propertyRequest)
	{
		return getPropertyDAC().fetchProperty(propertyRequest);
	}

}
