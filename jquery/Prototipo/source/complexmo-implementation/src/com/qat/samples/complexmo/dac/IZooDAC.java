package com.qat.samples.complexmo.dac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.complexmo.model.Zoo;
import com.qat.samples.complexmo.model.request.ZooFancyRequest;
import com.qat.samples.complexmo.model.request.ZooRequest;

/**
 * The Interface IZooDAC.
 */
public interface IZooDAC
{

	/**
	 * Gets the zoo by request.
	 * 
	 * @param zooRequest the zoo request
	 * @return the internal results response
	 */
	InternalResultsResponse<Zoo> fetchZooByRequest(ZooRequest zooRequest);

	/**
	 * Fetch zoo using a fancy request.
	 * 
	 * @param zooRequest the zoo request
	 * @return the internal results response
	 */
	InternalResultsResponse<Zoo> fetchZooByFancyRequest(ZooFancyRequest zooRequest);

	/**
	 * Gets the zoo by id.
	 * 
	 * @param zoo the zoo object
	 * @return the zoo object
	 */
	Zoo fetchZooById(Zoo zoo);

	/**
	 * Gets the all zoos.
	 * 
	 * @return the all zoos
	 */
	InternalResultsResponse<Zoo> fetchAllZoos();

	/**
	 * Maintain zoo.
	 * 
	 * @param zoo the zoo
	 * 
	 * @return the internal results response
	 */
	InternalResultsResponse<Zoo> updateZoo(Zoo zoo);

	/**
	 * Insert zoo.
	 * 
	 * @param zoo the zoo
	 * @return the internal results response
	 */
	InternalResultsResponse<Zoo> insertZoo(Zoo zoo);

	/**
	 * Delete zoo.
	 * 
	 * @param zoo the zoo
	 * @return the internal response
	 */
	InternalResponse deleteZoo(Zoo zoo);

}