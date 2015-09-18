package com.qat.samples.complexmo.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.complexmo.model.State;

public class StateRequest extends Request
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
		return "StateRequest [getState()=" + getState() + ", getUserContext()=" + getUserContext() + "]";
	}

}
