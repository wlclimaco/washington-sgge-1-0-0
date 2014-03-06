package com.sensus.lc.histuser.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.histuser.model.HistUser;
import com.sensus.lc.histuser.model.request.HistUserRequest;
import com.sensus.lc.histuser.model.request.InquiryHistUserRequest;

/**
 * The Interface IGroupBCL.
 */
public interface IHistUserBCL
{

	/**
	 * Fetch all groups.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<HistUser> fetchAllHistUsers(InquiryHistUserRequest inquiryHistUserRequest);

	/**
	 * Fetch group by id.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<HistUser> fetchHistUserById(InquiryHistUserRequest inquiryHistUserRequest);

	/**
	 * Fetch group by id list.
	 * 
	 * @param groupRequest the group request
	 * @return the group response
	 */
	InternalResultsResponse<HistUser> fetchHistUsersByUser(InquiryHistUserRequest histUserRequest);

	/**
	 * Insert group.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResponse insertHistUser(HistUserRequest histUserRequest);

}
