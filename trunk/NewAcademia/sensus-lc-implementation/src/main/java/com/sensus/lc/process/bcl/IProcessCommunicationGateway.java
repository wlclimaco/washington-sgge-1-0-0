package com.sensus.lc.process.bcl;

import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;

/**
 * The Interface IGatewayProcessCommunication.
 */
public interface IProcessCommunicationGateway
{

	/**
	 * Send action.
	 * 
	 * @param processRequest the process request
	 * @return the mlc gateway response
	 */
	MlcGatewayResponse sendAction(ProcessRequest processRequest);

	/**
	 * Checks if is supported action.
	 * 
	 * @param action the action
	 * @return the boolean
	 */
	Boolean isSupportedAction(LCActionTypeEnum action);
}
