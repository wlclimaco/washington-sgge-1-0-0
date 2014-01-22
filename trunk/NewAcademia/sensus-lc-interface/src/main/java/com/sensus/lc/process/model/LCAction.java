package com.sensus.lc.process.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * The Class MLCAction.
 */
@SuppressWarnings("serial")
public class LCAction extends SensusModel
{

	/** The description. */
	private String description;

	/** The action type. */
	private LCActionTypeEnum actionType;

	// FIXME - In the future the line below will be removed (property change)
	/** The action parameters. */
	private List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();

	private Integer processId;

	private Integer sheduleId;

	private Integer groupId;

	private Integer tagId;

	private String lightIntensity;

	private String groupName;

	private String tagName;

	private String lightDetailType;

	private PartNumberConfiguration partNumbertConfiguration1;
	private PartNumberConfiguration partNumbertConfiguration2;
	private PartNumberConfiguration partNumbertConfiguration3;
	private PartNumberConfiguration partNumbertConfiguration4;
	private PartNumberConfiguration partNumbertConfiguration5;
	private PartNumberConfiguration partNumbertConfiguration6;

	private String dimOnDelay;

	/** The action category list. */
	private List<LCActionCategoryEnum> actionCategoryList = new ArrayList<LCActionCategoryEnum>();

	/**
	 * Instantiates a new lC action.
	 */
	public LCAction()
	{

	}

	/**
	 * Instantiates a new lC action.
	 * 
	 * @param actionTypeParam the action type param
	 */
	public LCAction(LCActionTypeEnum actionTypeParam)
	{
		setActionType(actionTypeParam);
	}

	/**
	 * Sets the action type.
	 * 
	 * @param actionType the new action type
	 */
	public void setActionType(LCActionTypeEnum actionType)
	{
		this.actionType = actionType;
	}

	/**
	 * Gets the action type.
	 * 
	 * @return the action type
	 */
	public LCActionTypeEnum getActionType()
	{
		return actionType;
	}

	/**
	 * Sets the actionType type value.
	 * 
	 * @param actionTypeValue the new action type value
	 */
	public void setActionTypeValue(Integer actionTypeValue)
	{
		actionType = LCActionTypeEnum.enumForValue(actionTypeValue);
	}

	/**
	 * Gets the action type value.
	 * 
	 * @return the action type value
	 */
	public Integer getActionTypeValue()
	{
		return actionType.getValue();
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
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Gets the action category list.
	 * 
	 * @return the action category list
	 */
	public List<LCActionCategoryEnum> getActionCategoryList()
	{
		return actionCategoryList;
	}

	/**
	 * Sets the action category list.
	 * 
	 * @param actionCategoryList the new action category list
	 */
	public void setActionCategoryList(List<LCActionCategoryEnum> actionCategoryList)
	{
		this.actionCategoryList = actionCategoryList;
	}

	/**
	 * @return the sheduleId
	 */
	public Integer getSheduleId()
	{
		return sheduleId;
	}

	/**
	 * @param sheduleId the sheduleId to set
	 */
	public void setSheduleId(Integer sheduleId)
	{
		this.sheduleId = sheduleId;
	}

	/**
	 * @return the groupId
	 */
	public Integer getGroupId()
	{
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Integer groupId)
	{
		this.groupId = groupId;
	}

	/**
	 * @return the tagId
	 */
	public Integer getTagId()
	{
		return tagId;
	}

	/**
	 * @param tagId the tagId to set
	 */
	public void setTagId(Integer tagId)
	{
		this.tagId = tagId;
	}

	/**
	 * @return the lightIntensity
	 */
	public String getLightIntensity()
	{
		return lightIntensity;
	}

	/**
	 * @param lightIntensity the lightIntensity to set
	 */
	public void setLightIntensity(String lightIntensity)
	{
		this.lightIntensity = lightIntensity;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName()
	{
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	/**
	 * @return the tagName
	 */
	public String getTagName()
	{
		return tagName;
	}

	/**
	 * @param tagName the tagName to set
	 */
	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}

	/**
	 * @return the lightDetailType
	 */
	public String getLightDetailType()
	{
		return lightDetailType;
	}

	/**
	 * @param lightDetailType the lightDetailType to set
	 */
	public void setLightDetailType(String lightDetailType)
	{
		this.lightDetailType = lightDetailType;
	}

	/**
	 * @return the partNumbertConfiguration1
	 */
	public PartNumberConfiguration getPartNumbertConfiguration1()
	{
		return partNumbertConfiguration1;
	}

	/**
	 * @param partNumbertConfiguration1 the partNumbertConfiguration1 to set
	 */
	public void setPartNumbertConfiguration1(PartNumberConfiguration partNumbertConfiguration1)
	{
		this.partNumbertConfiguration1 = partNumbertConfiguration1;
	}

	/**
	 * @return the partNumbertConfiguration2
	 */
	public PartNumberConfiguration getPartNumbertConfiguration2()
	{
		return partNumbertConfiguration2;
	}

	/**
	 * @param partNumbertConfiguration2 the partNumbertConfiguration2 to set
	 */
	public void setPartNumbertConfiguration2(PartNumberConfiguration partNumbertConfiguration2)
	{
		this.partNumbertConfiguration2 = partNumbertConfiguration2;
	}

	/**
	 * @return the partNumbertConfiguration3
	 */
	public PartNumberConfiguration getPartNumbertConfiguration3()
	{
		return partNumbertConfiguration3;
	}

	/**
	 * @param partNumbertConfiguration3 the partNumbertConfiguration3 to set
	 */
	public void setPartNumbertConfiguration3(PartNumberConfiguration partNumbertConfiguration3)
	{
		this.partNumbertConfiguration3 = partNumbertConfiguration3;
	}

	/**
	 * @return the partNumbertConfiguration4
	 */
	public PartNumberConfiguration getPartNumbertConfiguration4()
	{
		return partNumbertConfiguration4;
	}

	/**
	 * @param partNumbertConfiguration4 the partNumbertConfiguration4 to set
	 */
	public void setPartNumbertConfiguration4(PartNumberConfiguration partNumbertConfiguration4)
	{
		this.partNumbertConfiguration4 = partNumbertConfiguration4;
	}

	/**
	 * @return the partNumbertConfiguration5
	 */
	public PartNumberConfiguration getPartNumbertConfiguration5()
	{
		return partNumbertConfiguration5;
	}

	/**
	 * @param partNumbertConfiguration5 the partNumbertConfiguration5 to set
	 */
	public void setPartNumbertConfiguration5(PartNumberConfiguration partNumbertConfiguration5)
	{
		this.partNumbertConfiguration5 = partNumbertConfiguration5;
	}

	/**
	 * @return the partNumbertConfiguration6
	 */
	public PartNumberConfiguration getPartNumbertConfiguration6()
	{
		return partNumbertConfiguration6;
	}

	/**
	 * @param partNumbertConfiguration6 the partNumbertConfiguration6 to set
	 */
	public void setPartNumbertConfiguration6(PartNumberConfiguration partNumbertConfiguration6)
	{
		this.partNumbertConfiguration6 = partNumbertConfiguration6;
	}

	/**
	 * @return the dimOnDelay
	 */
	public String getDimOnDelay()
	{
		return dimOnDelay;
	}

	/**
	 * @param dimOnDelay the dimOnDelay to set
	 */
	public void setDimOnDelay(String dimOnDelay)
	{
		this.dimOnDelay = dimOnDelay;
	}

	/**
	 * @return the actionParameters
	 */
	public List<LCActionParameter> getActionParameters()
	{
		return actionParameters;
	}

	/**
	 * @param actionParameters the actionParameters to set
	 */
	public void setActionParameters(List<LCActionParameter> actionParameters)
	{
		this.actionParameters = actionParameters;
	}

	/**
	 * Adds the action parameter.
	 * 
	 * @param actionParameter the action parameter
	 */
	public void addActionParameter(LCActionParameter actionParameter)
	{
		getActionParameters().add(actionParameter);
	}

	/**
	 * @return the processId
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "LCAction [getActionType()=" + getActionType() + ", getActionTypeValue()=" + getActionTypeValue()
				+ ", getDescription()=" + getDescription() + ", getActionCategoryList()=" + getActionCategoryList()
				+ ", getSheduleId()=" + getSheduleId() + ", getGroupId()=" + getGroupId() + ", getTagId()="
				+ getTagId() + ", getLightIntensity()=" + getLightIntensity() + ", getGroupName()=" + getGroupName()
				+ ", getTagName()=" + getTagName() + ", getLightDetailType()=" + getLightDetailType()
				+ ", getPartNumbertConfiguration1()=" + getPartNumbertConfiguration1()
				+ ", getPartNumbertConfiguration2()=" + getPartNumbertConfiguration2()
				+ ", getPartNumbertConfiguration3()=" + getPartNumbertConfiguration3()
				+ ", getPartNumbertConfiguration4()=" + getPartNumbertConfiguration4()
				+ ", getPartNumbertConfiguration5()=" + getPartNumbertConfiguration5()
				+ ", getPartNumbertConfiguration6()=" + getPartNumbertConfiguration6() + ", getDimOnDelay()="
				+ getDimOnDelay() + ", getActionParameters()=" + getActionParameters() + ", getProcessId()="
				+ getProcessId() + ", toString()=" + super.toString() + "]";
	}

}
