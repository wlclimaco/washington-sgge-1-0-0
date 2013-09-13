package com.sensus.common.model.response;

import java.util.Collection;

/**
 * This class is used as the return type for an "inquiry" type response. Review the properties to determine what comes
 * "built-into" this class. Also see the {@link Response} type from which this class extends.<br/>
 * Typically a subclass will be created extending from this class adding a type-safe list/collection for the actual
 * results set objects.
 */
public class InquiryResponse extends Response
{

	private ResultsSetInfo resultsSetInfo = null;

	/**
	 * Gets the results set info. Note this may be null if there is no additional information about the results.
	 * 
	 * @return the results set info
	 */
	public ResultsSetInfo getResultsSetInfo()
	{
		return resultsSetInfo;
	}

	/**
	 * Sets the results set info. Typically this is set by the BAS/BAI by simple copying the object an
	 * {@link InternalResultsResponse} instance.
	 * 
	 * @param resultsSetInfo the new results set info
	 */
	public void setResultsSetInfo(ResultsSetInfo resultsSetInfo)
	{
		this.resultsSetInfo = resultsSetInfo;
	}

	/**
	 * When appropriate override this method so that the framework can auto-populate the sub-classes collection
	 * of results.
	 * 
	 * @param coll A collection of objects to be added to the sub-classes collection.
	 */
	@SuppressWarnings({"rawtypes"})
	public void addResults(Collection coll)
	{

	}

}
