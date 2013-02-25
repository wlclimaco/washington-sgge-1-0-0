package com.sensus.mlc.wui.filial.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Request;
import com.sensus.common.scheduler.action.Action;
import com.sensus.common.scheduler.model.Schedule;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.response.InquiryGroupResponse;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.wui.base.action.LayoutBase;
import com.sensus.mlc.wui.base.model.IdValuePair;
import com.sensus.mlc.wui.base.util.ResultUtil;


@SuppressWarnings("serial")
public class FilialPageAction extends LayoutBase
{

	/** ************************ CONSTANTS ***********************. */

	/** The key for the default "loading error" droplist prompt. */
	public static final String DEFAULT_ERROR_KEY = "widgets.combobox.errorprompt2";

	/** The default "empty" value for dropLists. */
	private static final String DEFAULT_VALUE = "0";

	/** The Constant GROUP_LIST_ERROR. */
	private static final String GROUP_LIST_ERROR = "Error loading Group List";

	/** The logger for this class. */
	private static final Log LOG = LogFactory.getLog(FilialPageAction.class);

	/** The Constant TAG_LIST_ERROR. */
	private static final String TAG_LIST_ERROR = "Error loading Tag List";

	/** ************************ PROPERTIES ***********************. */


	/** The action type value. */
	private String actionTypeValue;

	/** The Group BCF object. */
	private IGroupBCF groupBCF;

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

	/** The Tag BCF object. */
	private ITagBCF tagBCF;

	/**
	 * Gets the group list.
	 * 
	 * @return the group list
	 */


	/**
	 * Gets the group bcf.
	 * 
	 * @return the group bcf
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the new group bcf
	 */
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Gets the tag bcf.
	 * 
	 * @return the tag bcf
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	/**
	 * Sets the tag bcf.
	 * 
	 * @param tagBCF the new tag bcf
	 */
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
	}

	/**
	 * Gets the schedule bcf.
	 * 
	 * @return the schedule bcf
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the schedule bcf.
	 * 
	 * @param scheduleBCF the new schedule bcf
	 */
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Gets the action type value.
	 * 
	 * @return the actionTypeValue
	 */
	public String getActionTypeValue()
	{
		return actionTypeValue;
	}

	/**
	 * Sets the action type value.
	 * 
	 * @param actionTypeValue the actionTypeValue to set
	 */
	public void setActionTypeValue(String actionTypeValue)
	{
		this.actionTypeValue = actionTypeValue;
	}



}