package com.sensus.dm.elec.action.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class InquiryActionResponse.
 * 
 * @author - QAT Brazil.
 */
public class InquiryActionResponse extends InquiryResponse
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
	 * @return the actionTypes
	 */
	public List<ActionType> getActionTypes()
	{
		return actionTypes;
	}

	/**
	 * @param actionTypes the actionTypes to set
	 */
	public void setActionTypes(List<ActionType> actionTypes)
	{
		this.actionTypes = actionTypes;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setActions(new ArrayList<DMAction>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryActionResponse [getActions()=" + getActions()
				+ ", getActionTypes()="
				+ getActionTypes() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()="
				+ getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()="
				+ getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}
