package com.qat.samples.complexmo.model.response;

import com.qat.framework.model.response.Response;
import com.qat.samples.complexmo.model.State;

public class StateResponse extends Response
{

	private State state;

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	@Override
	public String toString()
	{
		return "StateResponse [getState()=" + getState() + ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
