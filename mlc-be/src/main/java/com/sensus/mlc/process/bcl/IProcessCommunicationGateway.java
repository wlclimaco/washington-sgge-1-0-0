package com.sensus.mlc.process.bcl;

import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;

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
