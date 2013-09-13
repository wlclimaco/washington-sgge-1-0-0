package com.sensus.dm.common.action.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * The Class ActionType.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class ActionType extends SensusModel
{
	/** The action type enum. */
	private ActionTypeEnum actionTypeEnum;

	/** The protect. */
	private Boolean protect;

	/** The granted authority list. */
	private List<String> grantedAuthorityList;

	/**
	 * Instantiates a new action type.
	 */
	public ActionType()
	{
	}

	/**
	 * Instantiates a new action type.
	 * 
	 * @param actionTypeEnumParam the action type enum param
	 */
	public ActionType(ActionTypeEnum actionTypeEnumParam)
	{
		setActionTypeEnum(actionTypeEnumParam);
	}

	/**
	 * Gets the protect.
	 * 
	 * @return the protect
	 */
	public Boolean getProtect()
	{
		return protect;
	}

	/**
	 * Sets the protect.
	 * 
	 * @param protect the new protect
	 */
	public void setProtect(Boolean protect)
	{
		this.protect = protect;
	}

	/**
	 * Gets the action type enum.
	 * 
	 * @return the action type enum
	 */
	public ActionTypeEnum getActionTypeEnum()
	{
		return actionTypeEnum;
	}

	/**
	 * Sets the action type enum.
	 * 
	 * @param actionTypeEnumParam the new action type enum
	 */
	public void setActionTypeEnum(ActionTypeEnum actionTypeEnumParam)
	{
		actionTypeEnum = actionTypeEnumParam;
	}

	/**
	 * Gets the action type enum value.
	 * 
	 * @return the action type enum value
	 */
	public Integer getActionTypeEnumValue()
	{
		if (getActionTypeEnum() != null)
		{
			return getActionTypeEnum().getValue();
		}

		return null;
	}

	/**
	 * Gets the action type enum name value.
	 * 
	 * @return the action type enum name value
	 */
	public String getActionTypeEnumNameValue()
	{
		if (getActionTypeEnum() != null)
		{
			return getActionTypeEnum().getActionTypeName();
		}

		return null;
	}

	/**
	 * Gets the action category enum name value.
	 * 
	 * @return the action category enum name value
	 */
	public String getActionCategoryEnumNameValue()
	{
		if (getActionTypeEnum() != null)
		{
			return getActionTypeEnum().getActionCategoryEnum().getActionTypeCategoryName();
		}

		return null;
	}

	/**
	 * Sets the action type enum value.
	 * 
	 * @param actionTypeEnumParam the new action type enum value
	 */
	public void setActionTypeEnumValue(Integer actionTypeEnumParam)
	{
		setActionTypeEnum(ActionTypeEnum.enumForValue(actionTypeEnumParam));
	}

	/**
	 * Sets the action type enum description.
	 * 
	 * @param actionTypeEnumParam the new action type enum description
	 */
	public void setActionTypeEnumDescription(String actionTypeEnumParam)
	{
		setActionTypeEnum(ActionTypeEnum.enumForDescription(actionTypeEnumParam));
	}

	/**
	 * Gets the granted authority list.
	 * 
	 * @return the granted authority list
	 */
	public List<String> getGrantedAuthorityList()
	{
		return grantedAuthorityList;
	}

	/**
	 * Sets the granted authority list.
	 * 
	 * @param grantedAuthorityList the new granted authority list
	 */
	public void setGrantedAuthorityList(List<String> grantedAuthorityList)
	{
		this.grantedAuthorityList = grantedAuthorityList;
	}

	/**
	 * Adds the granted authority.
	 * 
	 * @param grantedAuthority the granted authority
	 */
	public void addGrantedAuthority(String grantedAuthority)
	{
		if (getGrantedAuthorityList() == null)
		{
			setGrantedAuthorityList(new ArrayList<String>());
		}

		getGrantedAuthorityList().add(grantedAuthority);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "ActionType [getProtect()=" + getProtect() + ", getActionTypeEnum()=" + getActionTypeEnum()
				+ ", getActionTypeEnumValue()=" + getActionTypeEnumValue() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate()
				+ ", getGrantedAuthorityList()=" + getGrantedAuthorityList() + "]";
	}
}
