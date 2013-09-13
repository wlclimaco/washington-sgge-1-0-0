package com.sensus.dm.elec.action.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class ActionResponse.
 * 
 * @author - QAT Brazil.
 */
public class ActionResponse extends Response
{
	/** The actions. */
	private List<DMAction> actions;

	/** The action types. */
	private List<ActionType> actionTypes;

	/**
	 * Gets the actions.
	 * 
	 * @return the actions
	 */
	public List<DMAction> getActions()
	{
		return actions;
	}

	/**
	 * Sets the actions.
	 * 
	 * @param actions the new actions
	 */
	public void setActions(List<DMAction> actions)
	{
		this.actions = actions;
	}

	/**
	 * Gets the action types.
	 * 
	 * @return the action types
	 */
	public List<ActionType> getActionTypes()
	{
		return actionTypes;
	}

	/**
	 * Sets the action types.
	 * 
	 * @param actionTypes the new action types
	 */
	public void setActionTypes(List<ActionType> actionTypes)
	{
		this.actionTypes = actionTypes;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ActionResponse [getActions()=" + getActions() + ", getActionTypes()=" + getActionTypes()
				+ ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}
}
