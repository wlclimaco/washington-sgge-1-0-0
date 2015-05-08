package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.BtsConfirmationNumber;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * Interface specifies the methods available for working between the SendSolv system and the datastore which contains
 * the last BTS confirmation number for a specific prefix number. Its sole purpose is to define the methods needed for
 * getting the next available BTS confirmation number for the prefix number.
 */
public interface IBtsConfirmationNumberDAC
{
	/**
	 * Fetch the next available BTS confirmation number for the BTS prefix number in the SendSolv system.
	 *
	 * @param btsPrefixNumber The BTS assigned prefix number.
	 * @return The {@link InternalResultsResponse} containing the next available unique {@link BtsConfirmationNumber}.
	 */
	public InternalResultsResponse<BtsConfirmationNumber> fetchNextBtsConfirmationNumber(Integer btsPrefixNumber);
}
