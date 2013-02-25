package com.sensus.mlc.wui.process.model;

import java.util.List;
import java.util.Map;

import com.sensus.mlc.wui.smartpoint.model.ViewLight;

/**
 * The Class SummaryView.
 */
public class SummaryProcessView
{

	/** The lightProtected. */
	private Integer lightProtected;

	/** The syncFailure. */
	private Integer syncFailure;

	/** The asyncFailure. */
	private Integer asyncFailure;

	/** The ABORTED. */
	private Integer aborted;

	/** The lightDeactivated. */
	private Integer lightDeactivated;

	/** The lightMaintenance. */
	private Integer lightMaintenance;

	/** The success. */
	private Integer success;

	/** The slcfailure. */
	private Integer slcfailure;

	/** The description. */
	private String description;

	/** The unreachableIds. */
	private List<Integer> unreachableIds;

	/** The view light. */
	private List<ViewLight> viewLight;

	/** The total lights. */
	private Integer totalLights;

	/** The failure. */
	private Map<String, String> failure;

	/** The action name group. */
	private String actionNameGroup;

	/** The action type. */
	private String actionType;

	/** The action name. */
	private String actionName;

	/** The actionTypeEnum. */
	private String actionTypeEnum;

	/**
	 * Gets the success.
	 * 
	 * @return the success
	 */
	public Integer getSuccess()
	{
		return success;
	}

	/**
	 * Sets the success.
	 * 
	 * @param success the new success
	 */
	public void setSuccess(Integer success)
	{
		this.success = success;
	}

	/**
	 * Gets the failure.
	 * 
	 * @return the failure
	 */
	public Map<String, String> getFailure()
	{
		return failure;
	}

	/**
	 * Sets the failure.
	 * 
	 * @param failure the failure
	 */
	public void setFailure(Map<String, String> failure)
	{
		this.failure = failure;
	}

	/**
	 * Gets the action name group.
	 * 
	 * @return the action name group
	 */
	public String getActionNameGroup()
	{
		return actionNameGroup;
	}

	/**
	 * Sets the action name group.
	 * 
	 * @param actionNameGroup the new action name group
	 */
	public void setActionNameGroup(String actionNameGroup)
	{
		this.actionNameGroup = actionNameGroup;
	}

	/**
	 * Gets the action type.
	 * 
	 * @return the action type
	 */
	public String getActionType()
	{
		return actionType;
	}

	/**
	 * Sets the action type.
	 * 
	 * @param actionType the new action type
	 */
	public void setActionType(String actionType)
	{
		this.actionType = actionType;
	}

	/**
	 * Gets the action name.
	 * 
	 * @return the action name
	 */
	public String getActionName()
	{
		return actionName;
	}

	/**
	 * Sets the action name.
	 * 
	 * @param actionName the new action name
	 */
	public void setActionName(String actionName)
	{
		this.actionName = actionName;
	}

	/**
	 * Gets the action type enum.
	 * 
	 * @return the action type enum
	 */
	public String getActionTypeEnum()
	{
		return actionTypeEnum;
	}

	/**
	 * Sets the action type enum.
	 * 
	 * @param actionTypeEnum the new action type enum
	 */
	public void setActionTypeEnum(String actionTypeEnum)
	{
		this.actionTypeEnum = actionTypeEnum;
	}

	/**
	 * Gets the epmfailure.
	 * 
	 * @return the epmfailure
	 */
	public Integer getSlcfailure()
	{
		return slcfailure;
	}

	/**
	 * Sets the epmfailure.
	 * 
	 * @param slcfailure the new slcfailure
	 */
	public void setSlcfailure(Integer slcfailure)
	{
		this.slcfailure = slcfailure;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the unreachable ids.
	 * 
	 * @return the unreachable ids
	 */
	public List<Integer> getUnreachableIds()
	{
		return unreachableIds;
	}

	/**
	 * Gets the light protected.
	 * 
	 * @return the lightProtected
	 */
	public Integer getLightProtected()
	{
		return lightProtected;
	}

	/**
	 * Sets the light protected.
	 * 
	 * @param lightProtected the new light protected
	 */
	public void setLightProtected(Integer lightProtected)
	{
		this.lightProtected = lightProtected;
	}

	/**
	 * Gets the sync failure.
	 * 
	 * @return the syncFailure
	 */
	public Integer getSyncFailure()
	{
		return syncFailure;
	}

	/**
	 * Sets the sync failure.
	 * 
	 * @param syncFailure the syncFailure to set
	 */
	public void setSyncFailure(Integer syncFailure)
	{
		this.syncFailure = syncFailure;
	}

	/**
	 * Gets the async failure.
	 * 
	 * @return the asyncFailure
	 */
	public Integer getAsyncFailure()
	{
		return asyncFailure;
	}

	/**
	 * Sets the async failure.
	 * 
	 * @param asyncFailure the asyncFailure to set
	 */
	public void setAsyncFailure(Integer asyncFailure)
	{
		this.asyncFailure = asyncFailure;
	}

	/**
	 * Gets the aborted.
	 * 
	 * @return the aBORTED
	 */
	public Integer getAborted()
	{
		return aborted;
	}

	/**
	 * Sets the aborted.
	 * 
	 * @param aborted the new aborted
	 */
	public void setAborted(Integer aborted)
	{
		this.aborted = aborted;
	}

	/**
	 * Gets the light deactivated.
	 * 
	 * @return the lightDeactivated
	 */
	public Integer getLightDeactivated()
	{
		return lightDeactivated;
	}

	/**
	 * Sets the light deactivated.
	 * 
	 * @param lightDeactivated the lightDeactivated to set
	 */
	public void setLightDeactivated(Integer lightDeactivated)
	{
		this.lightDeactivated = lightDeactivated;
	}

	/**
	 * Gets the light maintenance.
	 * 
	 * @return the lightMaintenance
	 */
	public Integer getLightMaintenance()
	{
		return lightMaintenance;
	}

	/**
	 * Sets the light maintenance.
	 * 
	 * @param lightMaintenance the lightMaintenance to set
	 */
	public void setLightMaintenance(Integer lightMaintenance)
	{
		this.lightMaintenance = lightMaintenance;
	}

	/**
	 * Sets the unreachable ids.
	 * 
	 * @param unreachableIds the new unreachable ids
	 */
	public void setUnreachableIds(List<Integer> unreachableIds)
	{
		this.unreachableIds = unreachableIds;
	}

	/**
	 * Gets the view light.
	 * 
	 * @return the viewLight
	 */
	public List<ViewLight> getViewLight()
	{
		return viewLight;
	}

	/**
	 * Sets the view light.
	 * 
	 * @param viewLight the viewLight to set
	 */
	public void setViewLight(List<ViewLight> viewLight)
	{
		this.viewLight = viewLight;
	}

	/**
	 * Gets the total lights.
	 * 
	 * @return the totalLights
	 */
	public Integer getTotalLights()
	{
		return totalLights;
	}

	/**
	 * Sets the total lights.
	 * 
	 * @param totalLights the totalLights to set
	 */
	public void setTotalLights(Integer totalLights)
	{
		this.totalLights = totalLights;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SummaryProcessView [getSuccess()=" + getSuccess()
				+ ", getFailure()=" + getFailure() + ", getActionNameGroup()="
				+ getActionNameGroup() + ", getActionType()=" + getActionType()
				+ ", getActionName()=" + getActionName()
				+ ", getActionTypeEnum()=" + getActionTypeEnum()
				+ ", getSlcfailure()=" + getSlcfailure()
				+ ", getDescription()=" + getDescription()
				+ ", getUnreachableIds()=" + getUnreachableIds()
				+ ", getLightProtected()=" + getLightProtected()
				+ ", getSyncFailure()=" + getSyncFailure()
				+ ", getAsyncFailure()=" + getAsyncFailure()
				+ ", getAborted()=" + getAborted() + ", getLightDeactivated()="
				+ getLightDeactivated() + ", getLightMaintenance()="
				+ getLightMaintenance() + ", getViewLight()=" + getViewLight()
				+ ", getTotalLights()=" + getTotalLights() + "]";
	}

}