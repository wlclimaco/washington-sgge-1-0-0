package com.sensus.dm.common.base.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.action.model.ActionType;

/**
 * The Class Device Type Model.
 * 
 * @author QAT Global.
 */
public class DeviceTypeModelPermissions
{
	/** The actions. */
	private List<ActionType> actions;

	/** The ui modules. */
	private UiModulesPermissions uiModules;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new device type model permissions.
	 */
	public DeviceTypeModelPermissions()
	{

	}

	/**
	 * Instantiates a new device type model permissions.
	 * 
	 * @param actionTypeListParam the actions
	 * @param uiModulesParam the ui modules
	 * @param nameParam the name
	 */
	public DeviceTypeModelPermissions(List<ActionType> actionTypeListParam, UiModulesPermissions uiModulesParam,
			String nameParam)
	{
		setActions(actionTypeListParam);
		setUiModules(uiModulesParam);
		setName(nameParam);
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
	 * Gets the ui modules.
	 * 
	 * @return the ui modules
	 */
	public UiModulesPermissions getUiModules()
	{
		return uiModules;
	}

	/**
	 * Sets the ui modules.
	 * 
	 * @param uiModules the new ui modules
	 */
	public void setUiModules(UiModulesPermissions uiModules)
	{
		this.uiModules = uiModules;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Adds the actions.
	 * 
	 * @param actionType the action type
	 */
	public void addActions(ActionType actionType)
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
		return "DeviceTypeModelPermissions [getActions()=" + getActions() + ", getUiModules()=" + getUiModules()
				+ ", getName()=" + getName() + "]";
	}
}
