package com.sensus.dm.common.base.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.action.model.ActionType;

/**
 * The Class DefaultActions.
 * 
 * @author QAT Global.
 */
public class DefaultActionsPermissions
{
	/** The actions. */
	private List<ActionType> actions;

	/**
	 * Instantiates a new default actions permissions.
	 */
	public DefaultActionsPermissions()
	{

	}

	/**
	 * Instantiates a new default actions permissions.
	 * 
	 * @param actionTypeListParam the actions
	 */
	public DefaultActionsPermissions(List<ActionType> actionTypeListParam)
	{
		setActions(actionTypeListParam);
	}

	/**
	 * Gets the actions.
	 * 
	 * @return the actions
	 */
	public List<ActionType> getActions()
	{
		return actions;
	}

	/**
	 * Sets the actions.
	 * 
	 * @param actions the new actions
	 */
	public void setActions(List<ActionType> actions)
	{
		this.actions = actions;
	}

	/**
	 * Adds the action.
	 * 
	 * @param actionType the action type
	 */
	public void addAction(ActionType actionType)
	{
		if (getActions() == null)
		{
			setActions(new ArrayList<ActionType>());
		}

		getActions().add(actionType);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DefaultActionsPermissions [getActions()=" + getActions() + "]";
	}
}
