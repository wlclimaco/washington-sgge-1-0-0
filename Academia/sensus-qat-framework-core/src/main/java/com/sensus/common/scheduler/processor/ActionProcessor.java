package com.sensus.common.scheduler.processor;

import com.sensus.common.scheduler.action.Action;

/**
 * The Class ActionProcessor.
 * 
 * @author QAT Brazil
 */
public class ActionProcessor
{

	/** The action. */
	private Action action;

	/**
	 * Instantiates a new action processor.
	 */
	public ActionProcessor()
	{
	}

	/**
	 * Execute.
	 */
	public void execute()
	{

	}

	/**
	 * Gets the action.
	 * 
	 * @return the action
	 */
	public Action getAction()
	{
		return action;
	}

	/**
	 * Sets the action.
	 * 
	 * @param action the new action
	 */
	public void setAction(Action action)
	{
		this.action = action;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ActionProcessor [getAction()=" + getAction() + "]";
	}

}
