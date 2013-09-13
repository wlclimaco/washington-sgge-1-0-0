package com.sensus.dm.common.property.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.request.PropertyRequest;

/**
 * The Interface IPropertyDAC.
 * 
 * @author - QAT Brazil
 */

public interface IPropertyDAC
{

	/**
	 * Retrieves properties given a property provider and optionally property name.
	 * 
	 * @param propertyRequest the settings request
	 * @return the internal results response
	 */
	InternalResultsResponse<Property> fetchProperty(PropertyRequest propertyRequest);

	/**
	 * Update/Insert a property.
	 * 
	 * @param propertyRequest the settings request
	 * @return the internal response
	 */
	InternalResponse upsertProperty(PropertyRequest propertyRequest);

	/**
	 * Deletes a property.
	 * 
	 * @param propertyRequest the settings request
	 * @return the internal response
	 */
	InternalResponse deleteProperty(PropertyRequest propertyRequest);

	/**
	 * Deletes a property provider (and all his properties).
	 * 
	 * @param propertyRequest the settings request
	 * @return the internal response
	 */
	InternalResponse deletePropertyProvider(PropertyRequest propertyRequest);

}
