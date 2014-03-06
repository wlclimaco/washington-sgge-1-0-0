package com.sensus.lc.histuser.dac;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.histuser.model.HistUser;
import com.sensus.lc.histuser.model.request.HistUserRequest;
import com.sensus.lc.histuser.model.request.InquiryHistUserRequest;

/**
 * The Interface IGroupDAC.
 */
public interface IHistUserDAC
{

	/**
	 * Insert group.
	 * 
	 * @param histUserRequest the hist user request
	 * @return the internal results response
	 */
	InternalResultsResponse<HistUser> insertHistUser(HistUserRequest histUserRequest);

	/**
	 * Fetch group by id.
	 * 
	 * @param histUserRequest the hist user request
	 * @return the internal results response
	 */
	InternalResultsResponse<HistUser> fetchHistUserById(InquiryHistUserRequest histUserRequest);

	/**
	 * Fetch hist user by user.
	 * 
	 * @param histUserRequest the hist user request
	 * @return the internal results response
	 */
	InternalResultsResponse<HistUser> fetchHistUserByUser(InquiryHistUserRequest histUserRequest);

	/**
	 * Fetch all hist user.
	 * 
	 * @param histUserRequest the hist user request
	 * @return the internal results response
	 */
	InternalResultsResponse<HistUser> fetchAllHistUsers(InquiryHistUserRequest histUserRequest);

}