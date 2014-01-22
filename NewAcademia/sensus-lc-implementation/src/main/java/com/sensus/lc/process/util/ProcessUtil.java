package com.sensus.lc.process.util;

import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.sensus.common.model.UserContext;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.request.LightSelectionRequest;
import com.sensus.lc.base.util.LCActionUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.ProcessStatusReasonEnum;
import com.sensus.lc.process.model.request.ProcessRequest;

public final class ProcessUtil
{

	private static final int FIVE = 5;
	private static final int THREE = 3;

	/** The Constant SENSUS_MLC_MLC_ACTION. */
	private static final String SENSUS_MLC_MLC_ACTION = ".*sensus.mlc.mlc_action.*";
	private static final String SENSUS_MLC_ALERT = ".*sensus.mlc.alert.*";
	private static final String SENSUS_PROCESS = "sensus.mlc.mlc_action.label.";
	private static final String UNLOCKED = ".*unlocked.*";
	private static final String LOCKED = ".*locked.*";
	private static final String IS_COMPLETED = "sensus.mlc.mlc_action.completed";
	private static final String PROCESSING = "sensus.mlc.mlc_action.processing";

	/**
	 * Instantiates a new process util.
	 */
	private ProcessUtil()
	{

	}

	/**
	 * Generate process.
	 * 
	 * @param isMonitored the is monitored
	 * @param action the action
	 * @param processItemAmount the process item amount
	 * @return the process
	 */
	public static Process generateProcess(Boolean isMonitored, LCAction action, Integer processItemAmount)
	{
		return generateProcess(false, isMonitored, action, processItemAmount);
	}

	/**
	 * Generate process.
	 * 
	 * @param isComplete the is complete
	 * @param isMonitored the is monitored
	 * @param action the action
	 * @param processItemAmount the process item amount
	 * @return the process
	 */
	public static Process generateProcess(Boolean isComplete, Boolean isMonitored, LCAction action,
			Integer processItemAmount)
	{
		Process process = new Process();
		process.setStartTime(LCDateUtil.getNewDateUTC());
		process.setIsMonitoredInstance(isMonitored);
		process.setIsParent(true);
		process.setIsProcessComplete(isComplete);
		process.setLcAction(action);
		process.getLcAction().setDescription(LCActionUtil.generateActionDescription(process));
		process.setDescription(LCActionUtil.generateDescription(process, processItemAmount));
		process.setProcessItems(new ArrayList<ProcessItem>());

		if (!isNullOrZero(processItemAmount))
		{
			process.setProcessItems(null);
		}

		return process;
	}

	/**
	 * Generate process.
	 * 
	 * @param isMonitored the is monitored
	 * @param action the action
	 * @param lightList the light list
	 * @return the process
	 */
	public static Process generateProcess(Boolean isMonitored, LCAction action, List<Light> lightList)
	{
		return generateProcess(isMonitored, action, lightList, ProcessItemStatusEnum.PENDING);
	}

	/**
	 * Generate process.
	 * 
	 * @param isMonitored the is monitored
	 * @param action the action
	 * @param lightList the light list
	 * @param processItemStatus the process item status
	 * @return the process
	 */
	public static Process generateProcess(Boolean isMonitored, LCAction action, List<Light> lightList,
			ProcessItemStatusEnum processItemStatus)
	{
		List<ProcessItem> processItems = new ArrayList<ProcessItem>();
		int processItemAmount = 0;

		if (!isNullOrEmpty(lightList))
		{
			for (Light light : lightList)
			{
				ProcessItem processItem = new ProcessItem();
				processItem.setLight(light);
				processItem.setProcessItemStatusEnum(processItemStatus);
				processItems.add(processItem);
			}

			processItemAmount = processItems.size();
		}

		Process process = generateProcess(isMonitored, action, processItemAmount);
		process.setProcessItems(processItems);
		return process;
	}

	/**
	 * Creates the process request.
	 * 
	 * @param request the request
	 * @param process the process
	 * @return the process request
	 */
	public static ProcessRequest createProcessRequest(LightSelectionRequest request, Process process)
	{
		ProcessRequest processRequest = new ProcessRequest(request.getUserContext());
		processRequest.setProcess(process);
		processRequest.setSearchLight(request.getSearchLight());
		processRequest.setSelectionPaginationIds(request.getSelectionPaginationIds());
		processRequest.setUnselectionPaginationIds(request.getUnselectionPaginationIds());
		processRequest.setPaginationAllSelected(request.getPaginationAllSelected());
		processRequest.setMonitored(request.isMonitored());
		processRequest.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);
		return processRequest;
	}

	/**
	 * Creates the process item with failure.
	 * 
	 * @param lights the lights
	 * @param itemStatus the item status
	 * @param statusReason the status reason
	 * @return the list
	 */
	public static List<ProcessItem> createProcessItemWithFailure(
			List<Light> lights,
			ProcessItemStatusEnum itemStatus,
			ProcessStatusReasonEnum statusReason)
	{
		if (ValidationUtil.isNullOrEmpty(lights))
		{
			return new ArrayList<ProcessItem>();
		}

		List<ProcessItem> processItemList = new ArrayList<ProcessItem>();
		for (Light light : lights)
		{
			processItemList.add(new ProcessItem(light, itemStatus, statusReason));
		}

		return processItemList;
	}

	/**
	 * Translate message.
	 * 
	 * @param response the response
	 * @param userContext the user context
	 */
	public static void translateMessage(List<Process> response, UserContext userContext)
	{
		if (ValidationUtil.isNull(userContext) || ValidationUtil.isNullOrEmpty(userContext.getLocaleString()))
		{
			return;
		}

		Locale locale =
				new Locale(userContext.getLocaleString().substring(0, 2), userContext.getLocaleString().substring(
						THREE, FIVE));

		for (Process process : response)
		{
			process.setStatus(SensusMessageUtil.getMessage(PROCESSING, null, null, locale));
			if (!ValidationUtil.isNull(process.getIsProcessComplete()) && process.getIsProcessComplete())
			{
				process.setStatus(SensusMessageUtil.getMessage(IS_COMPLETED, null, null, locale));
			}

			if (!ValidationUtil.isNull(process.getLcAction().getActionType()))
			{
				String actionDescription =
						SENSUS_PROCESS + String.valueOf(process.getLcAction().getActionType()).toLowerCase();

				if (process.getLcAction().getDescription().matches(SENSUS_MLC_MLC_ACTION))
				{
					actionDescription = process.getLcAction().getDescription();
				}

				process.getLcAction().setDescription(
						SensusMessageUtil.getMessage(actionDescription, null, null, locale));
			}

			if (!checkDescriptionValue(process.getDescription()))
			{
				continue;
			}

			Object[] parameters = StringUtils.splitByWholeSeparator(process.getParameterValue(), ",");

			if (!ValidationUtil.isNull(parameters)
					&& (process.getDescription().matches(LOCKED) || process.getDescription().matches(UNLOCKED)))
			{
				Object[] protectedParam = new Object[1];
				protectedParam[0] = parameters[0];

				parameters[0] = SensusMessageUtil.getMessage(String.valueOf(protectedParam[0]), null, null, locale);
			}

			process.setDescription(SensusMessageUtil.getMessage(process.getDescription(), parameters, null, locale));

		}
	}

	/**
	 * Check description value.
	 * 
	 * @param description the description
	 * @return the boolean
	 */
	private static Boolean checkDescriptionValue(String description)
	{
		if (ValidationUtil.isNull(description))
		{
			return false;
		}
		if (description.matches(SENSUS_MLC_ALERT))
		{
			return true;
		}
		if (description.matches(SENSUS_MLC_MLC_ACTION))
		{
			return true;
		}
		return false;
	}
}
