package com.sensus.dm.common.property.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.request.PropertyRequest;

/**
 * The Interface IPropertyBCL.
 * 
 * @author QAT Brazil.
 * 
 */
public interface IPropertyBCL
{

	/**
	 * Upsert property.
	 * 
	 * @param propertyRequest the property request
	 * @return the internal response
	 */
	InternalResponse upsertProperty(PropertyRequest propertyRequest);

	/**
	 * Delete property provider.
	 * 
	 * @param propertyRequest the property request
	 * @return the internal response
	 */
	InternalResponse deletePropertyProvider(PropertyRequest propertyRequest);

	/**
	 * Delete property.
	 * 
	 * @param propertyRequest the property request
	 * @return the internal response
	 */
	InternalResponse deleteProperty(PropertyRequest propertyRequest);

	/**
	 * Fetch property.
	 * 
	 * @param propertyRequest the property request
	 * @return the internal results response
	 */
	InternalResultsResponse<Property> fetchProperty(PropertyRequest propertyRequest);

}
