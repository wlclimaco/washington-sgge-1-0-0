package com.sensus.lc.communication.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.lc.communication.model.request.EmailRequest;

/**
 * Interface for Email Service.
 * 
 * @author QAT Global.
 */
public interface IEmailServiceBCL
{

	/**
	 * Send email.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse sendEmail(EmailRequest request);

}
