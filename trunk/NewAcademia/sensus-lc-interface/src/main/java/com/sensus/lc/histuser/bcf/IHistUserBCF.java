package com.sensus.lc.histuser.bcf;

import com.sensus.lc.histuser.model.request.HistUserRequest;
import com.sensus.lc.histuser.model.request.InquiryHistUserRequest;
import com.sensus.lc.histuser.model.response.HistUserResponse;
import com.sensus.lc.histuser.model.response.InquiryHistUserResponse;

/**
 * The Interface IHistUserBCF.
 * 
 * @author Washington.
 * 
 */
public interface IHistUserBCF
{

	/**
	 * Fetch all academias.
	 * 
	 * @param inquiryHistUserRequest the inquiry academia request
	 * @return the inquiry academia response
	 */
	InquiryHistUserResponse fetchAllHistUsers(InquiryHistUserRequest inquiryHistUserRequest);

	/**
	 * Fetch all academia by user.
	 * 
	 * @param inquiryHistUserRequest the inquiry academia request
	 * @return the inquiry academia response
	 */
	InquiryHistUserResponse fetchAllHistUserByUser(InquiryHistUserRequest inquiryHistUserRequest);

	/**
	 * Fetch academia by id.
	 * 
	 * @param inquiryHistUserRequest the inquiry academia request
	 * @return the academia response
	 */
	HistUserResponse fetchHistUserById(InquiryHistUserRequest inquiryHistUserRequest);

	/**
	 * Insert hist user.
	 * 
	 * @param histUseraRequest the hist usera request
	 * @return the hist user response
	 */
	HistUserResponse insertHistUser(HistUserRequest histUseraRequest);

}
