package com.qat.samples.complexmo.bai.impl;

import com.qat.framework.model.Message;
import com.qat.samples.complexmo.bai.IStateBAI;
import com.qat.samples.complexmo.dac.IStateDAC;
import com.qat.samples.complexmo.model.request.StateRequest;
import com.qat.samples.complexmo.model.response.StateResponse;

/**
 * The Class EntityStateBAIImpl.
 */
public class StateBAIImpl implements IStateBAI
{

	/** The state DAC. */
	private IStateDAC stateDAC;

	/**
	 * Gets the state DAC.
	 * 
	 * @return the state DAC
	 */
	public IStateDAC getStateDAC()
	{
		return stateDAC;
	}

	/**
	 * Sets the state DAC.
	 * 
	 * @param value the new state DAC
	 */
	public void setStateDAC(IStateDAC value)
	{
		stateDAC = value;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.complexmo.bai.IEntityStateBAI#fetchStateByCode(com.qat.samples.complexmo.model.request
	 * .StateRequest)
	 */
	@Override
	public StateResponse fetchStateByCode(StateRequest request)
	{
		StateResponse response = new StateResponse();
		try
		{
			response.setState(getStateDAC().fetchStateByCode(request.getState().getCode()));
		}
		catch (Exception ex)
		{
			Message message = new Message("We are hosed", Message.MessageSeverity.Fatal, Message.MessageLevel.Object);
			message.getMessageInfo().setTraceInfo(ex.toString());
			response.addMessage(message);
			response.setOperationSuccess(false);
		}
		return response;
	}

}
