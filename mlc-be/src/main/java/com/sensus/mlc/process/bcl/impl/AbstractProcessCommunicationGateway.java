package com.sensus.mlc.process.bcl.impl;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;
import static com.sensus.mlc.base.util.LCDateUtil.convertStringToDate;
import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.base.util.RemoteUserContext;
import com.sensus.mlc.base.util.RemoteUserContextHolder;
import com.sensus.mlc.mlcserver.client.MlcServerClient;
import com.sensus.mlc.mlcserver.type.BlinkLevel;
import com.sensus.mlc.mlcserver.type.DimmedSmartpoint;
import com.sensus.mlc.mlcserver.type.LightLevel;
import com.sensus.mlc.mlcserver.type.ObjectFactory;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.process.bcl.IProcessCommunicationGateway;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.bcl.IScheduleBCL;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.OverrideEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class AbstractGatewayProcessCommunication.
 */
public abstract class AbstractProcessCommunicationGateway implements IProcessCommunicationGateway
{
	private static final int OVERRIDE_EXPIRATION = 65535;

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(ProcessBCLImpl.class);

	/** The Constant SEVEN. */
	private static final Integer SEVEN = 7;

	/** One minute in milliseconds. */
	private static final long ONE_MINUTE = 60000;

	/** The supported actions. */
	private EnumSet<LCActionTypeEnum> supportedActions;

	/** The lc help. */
	private LCHelp lcHelp; // injected by Spring through setter

	/** The factory. */
	private ObjectFactory factory; // injected by Spring through setter

	/** The mlc gateway ws. */
	private MlcServerClient mlcGatewayWs; // injected by Spring through setter

	/** The system id. */
	private String systemId; // injected by Spring

	/** The system pwd. */
	private String systemPwd; // injected by Spring

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter

	/** The smart point processor bcl. */
	private ISmartPointProcessorBCL smartPointProcessorBCL; // injected by Spring through setter

	/** The schedule bcl. */
	private IScheduleBCL scheduleBCL; // injected by Spring through setter

	/**
	 * Gets the supported actions.
	 *
	 * @return the supported actions
	 */
	public EnumSet<LCActionTypeEnum> getSupportedActions()
	{
		return supportedActions;
	}

	/**
	 * Sets the supported actions.
	 *
	 * @param supportedActions the new supported actions
	 */
	public void setSupportedActions(EnumSet<LCActionTypeEnum> supportedActions)
	{
		this.supportedActions = supportedActions;
	}

	/**
	 * Gets the lc help.
	 *
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return lcHelp;
	}

	/**
	 * Sets the lc help.
	 *
	 * @param lcHelp the new lc help
	 */
	public void setLcHelp(LCHelp lcHelp)
	{
		this.lcHelp = lcHelp;
	}

	/**
	 * Gets the mlc gateway ws.
	 *
	 * @return the mlc gateway ws
	 */
	public MlcServerClient getMlcGatewayWs()
	{
		return mlcGatewayWs;
	}

	/**
	 * Sets the mlc gateway ws.
	 *
	 * @param mlcGatewayWs the new mlc gateway ws
	 */
	public void setMlcGatewayWs(MlcServerClient mlcGatewayWs)
	{
		this.mlcGatewayWs = mlcGatewayWs;
	}

	/**
	 * Gets the factory.
	 *
	 * @return the factory
	 */
	public ObjectFactory getFactory()
	{
		return factory;
	}

	/**
	 * Sets the factory.
	 *
	 * @param factory the new factory
	 */
	public void setFactory(ObjectFactory factory)
	{
		this.factory = factory;
	}

	/**
	 * Gets the system id.
	 *
	 * @return the system id
	 */
	public String getSystemId()
	{
		return systemId;
	}

	/**
	 * Sets the system id.
	 *
	 * @param systemId the new system id
	 */
	public void setSystemId(String systemId)
	{
		this.systemId = systemId;
	}

	/**
	 * Gets the system pwd.
	 *
	 * @return the system pwd
	 */
	public String getSystemPwd()
	{
		return systemPwd;
	}

	/**
	 * Sets the system pwd.
	 *
	 * @param systemPwd the new system pwd
	 */
	public void setSystemPwd(String systemPwd)
	{
		this.systemPwd = systemPwd;
	}

	/**
	 * Gets the smart point accessor bcl.
	 *
	 * @return the smart point accessor bcl
	 */
	public ISmartPointAccessorBCL getSmartPointAccessorBCL()
	{
		return smartPointAccessorBCL;
	}

	/**
	 * Sets the smart point accessor bcl.
	 *
	 * @param smartPointAccessorBCL the new smart point accessor bcl
	 */
	public void setSmartPointAccessorBCL(ISmartPointAccessorBCL smartPointAccessorBCL)
	{
		this.smartPointAccessorBCL = smartPointAccessorBCL;
	}

	/**
	 * Gets the smart point processor bcl.
	 *
	 * @return the smart point processor bcl
	 */
	public ISmartPointProcessorBCL getSmartPointProcessorBCL()
	{
		return smartPointProcessorBCL;
	}

	/**
	 * Sets the smart point processor bcl.
	 *
	 * @param smartPointProcessorBCL the new smart point processor bcl
	 */
	public void setSmartPointProcessorBCL(ISmartPointProcessorBCL smartPointProcessorBCL)
	{
		this.smartPointProcessorBCL = smartPointProcessorBCL;
	}

	/**
	 * Gets the schedule bcl.
	 *
	 * @return the schedule bcl
	 */
	public IScheduleBCL getScheduleBCL()
	{
		return scheduleBCL;
	}

	/**
	 * Sets the schedule bcl.
	 *
	 * @param scheduleBCL the new schedule bcl
	 */
	public void setScheduleBCL(IScheduleBCL scheduleBCL)
	{
		this.scheduleBCL = scheduleBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessCommunicationGateway#isSupportedAction(com.sensus.mlc.process.model.
	 * LCActionTypeEnum)
	 */
	@Override
	public Boolean isSupportedAction(LCActionTypeEnum action)
	{
		return getSupportedActions().contains(action);
	}

	/**
	 * Checks if is failure.
	 *
	 * @param item the item
	 * @param process the process
	 * @return true, if is failure
	 */
	protected boolean isFailure(ProcessItem item, Process process)
	{
		LCActionTypeEnum actionType = process.getLcAction().getActionType();
		if ((actionType == LCActionTypeEnum.SETUP_DIMMING_CONFIGURATION)
				|| (actionType == LCActionTypeEnum.GET_LIGHT_STATUS))
		{
			return false;
		}

		Light light = item.getLight();
		if (isNull(light))
		{
			return true;
		}

		StatusMessage currentStatusMessage = light.getCurrentStatusMessage();
		if (isNull(currentStatusMessage) || isNull(light.getProtect()))
		{
			return false;
		}

		if (light.getProtect())
		{
			item.setProcessItemStatusEnum(ProcessItemStatusEnum.MLCFAILURE);
			item.setProcessStatusReasonEnum(ProcessStatusReasonEnum.LIGHT_PROTECTED);
			return true;
		}

		if ((currentStatusMessage.getLightStatusEnum() == LightStatusEnum.DEACTIVATED)
				&& (actionType != LCActionTypeEnum.UPDATE_LIGHT_STATUS))
		{
			item.setProcessItemStatusEnum(ProcessItemStatusEnum.MLCFAILURE);
			item.setProcessStatusReasonEnum(ProcessStatusReasonEnum.LIGHT_DEACTIVATED);
			return true;
		}

		if ((currentStatusMessage.getLightStatusEnum() == LightStatusEnum.MAINTENANCE)
				&& (actionType != LCActionTypeEnum.UPDATE_LIGHT_STATUS))
		{
			item.setProcessItemStatusEnum(ProcessItemStatusEnum.MLCFAILURE);
			item.setProcessStatusReasonEnum(ProcessStatusReasonEnum.LIGHT_IN_MAINTENANCE);
			return true;
		}

		return false;
	}

	/**
	 * Call this before the Web Service is invoked.
	 * Sets the userid and password in the thread local <code>RemoteUserContext</code>.
	 * These credentials will be put into a SOAP
	 * header by a WS client interceptor.
	 * Currently reading Uid/Pwd from UserContext Object
	 *
	 * @param tenant the new remote user credentials
	 */
	protected void setRemoteUserCredentials(Tenant tenant)
	{
		RemoteUserContext remoteUserCtx = RemoteUserContextHolder.getContext();
		if (remoteUserCtx == null)
		{
			remoteUserCtx = new RemoteUserContext();
		}
		remoteUserCtx.setUserName(getSystemId());
		remoteUserCtx.setPassword(getSystemPwd());
		RemoteUserContextHolder.setContext(remoteUserCtx);
		getMlcGatewayWs().getWsTemplate().setDefaultUri(tenant.getGatewayURL());
	}

	/**
	 * Generate ws smart point list.
	 *
	 * @param process the process
	 * @param tenant the tenant
	 * @return the list
	 */
	protected List<Smartpoint> generateWsSmartPointList(Process process, Tenant tenant)
	{
		if (isNull(process.getProcessItems()))
		{
			return null;
		}

		List<Smartpoint> wsSmartPointList = new ArrayList<Smartpoint>();
		for (ProcessItem item : process.getProcessItems())
		{
			if (isFailure(item, process))
			{
				continue;
			}
			Smartpoint wsSmartPoint = new Smartpoint();
			wsSmartPoint.setRniId(item.getLight().getRniId());
			wsSmartPoint.setCustomerID(tenant.getRniCode());
			wsSmartPointList.add(wsSmartPoint);
		}

		return wsSmartPointList;
	}

	/**
	 * Generate ws dimmed smart point list.
	 *
	 * @param process the process
	 * @param tenant the tenant
	 * @return the list
	 * @throws ParseException the parse exception
	 */
	protected List<DimmedSmartpoint> generateWsDimmedSmartPointList(Process process, Tenant tenant)
	{
		List<DimmedSmartpoint> wsDimmedSmartpointList = new ArrayList<DimmedSmartpoint>();
		for (ProcessItem item : process.getProcessItems())
		{
			if (isFailure(item, process))
			{
				continue;
			}

			DimmedSmartpoint wsDimmedSmartPoint = new DimmedSmartpoint();
			wsDimmedSmartPoint.setRniId(item.getLight().getRniId());
			wsDimmedSmartPoint.setCustomerID(tenant.getRniCode());

			LCAction lcAction = process.getLcAction();
			LCActionTypeEnum actionType = lcAction.getActionType();

			// FIXME - properties
			LCActionParameter parameter = new LCActionParameter();
			//LCActionParameter parameter = lcAction.getActionParameterByPosition(1);

			if ((actionType == LCActionTypeEnum.SET_INTENSITY_BY_GRP) && !isNull(parameter)
					&& !isNullOrEmpty(parameter.getActionValue()))
			{
				setLightIntensity(item, Integer.parseInt(parameter.getActionValue()));
			}

			if (((actionType == LCActionTypeEnum.TURN_OFF) || (actionType == LCActionTypeEnum.TURN_ON) || (actionType == LCActionTypeEnum.DIM))
					|| (actionType == LCActionTypeEnum.SET_BLINK_BY_LIGHT)
					|| (actionType == LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT))
			{
				for (LCActionParameter actionParameter : lcAction.getActionParameters())
				{
					setParameterDimmedSmartPoint(wsDimmedSmartPoint, item, actionParameter);
				}
			}

			wsDimmedSmartpointList.add(wsDimmedSmartPoint);
		}
		return wsDimmedSmartpointList;
	}

	/**
	 * Sets the light intensity.
	 *
	 * @param item the item
	 * @param percentage the percentage
	 */
	protected void setLightIntensity(ProcessItem item, Integer percentage)
	{
		Light light = item.getLight();
		if (isNull(light))
		{
			return;
		}

		if (isNullOrZero(percentage))
		{
			light.setLightIntensityEnum(LightIntensityEnum.LEVEL_0);
			return;
		}

		light.setLightIntensityEnum(getSmartPointAccessorBCL().fetchLightIntensityByLight(light.getId(),
				percentage));
	}

	/**
	 * Sets the parameter dimmed smart point.
	 *
	 * @param wsDimmedSmartPoint the ws dimmed smart point
	 * @param item the item
	 * @param actionParameter the action parameter
	 */
	protected void setParameterDimmedSmartPoint(DimmedSmartpoint wsDimmedSmartPoint, ProcessItem item,
			LCActionParameter actionParameter)
	{
		if (actionParameter.getProperty().equals(PropertyEnum.LIGHT_INTENSITY)
				&& !isNull(actionParameter.getActionValue()))
		{
			setLightIntensity(item, Integer.parseInt(actionParameter.getActionValue()));
			wsDimmedSmartPoint.setLightLevel(LightLevel.valueOf(item.getLight().getLightIntensityEnum().toString()));
		}
		else if (actionParameter.getProperty().equals(PropertyEnum.LIGHT_INTENSITY))
		{
			wsDimmedSmartPoint.setLightLevel(LightLevel.valueOf(LightIntensityEnum.LEVEL_7.toString()));
		}

		if (actionParameter.getProperty().equals(PropertyEnum.LIGHT_BLINK))
		{
			setLightBlink(item, wsDimmedSmartPoint, Integer.parseInt(actionParameter.getActionValue()));
		}

		if (actionParameter.getProperty().equals(PropertyEnum.OVERRIDE))
		{
			if (OverrideEnum.PERMANENT.getValue().equals(Integer.parseInt(actionParameter.getActionValue())))
			{
				wsDimmedSmartPoint.setOverrideTime(OVERRIDE_EXPIRATION);
				wsDimmedSmartPoint.setOverrideTerminationEvent(NumberUtils.INTEGER_ZERO);
				item.getLight().setOverrideEnumValue(Integer.parseInt(actionParameter.getActionValue()));
			}

			if (OverrideEnum.SCHEDULED.getValue().equals(Integer.parseInt(actionParameter.getActionValue())))
			{
				wsDimmedSmartPoint.setOverrideTime(OVERRIDE_EXPIRATION);
				wsDimmedSmartPoint.setOverrideTerminationEvent(SEVEN);
				item.getLight().setOverrideEnumValue(Integer.parseInt(actionParameter.getActionValue()));
			}

			if (OverrideEnum.NONE.getValue().equals(Integer.parseInt(actionParameter.getActionValue())))
			{
				wsDimmedSmartPoint.setOverrideTime(NumberUtils.INTEGER_ZERO);
				wsDimmedSmartPoint.setOverrideTerminationEvent(SEVEN);
				item.getLight().setOverrideEnumValue(Integer.parseInt(actionParameter.getActionValue()));
			}
		}

		if (actionParameter.getProperty().equals(PropertyEnum.OVERRIDE_PER_DATE))
		{
			Date endDate = convertStringToDate(actionParameter.getActionValue());

			int second = new Long((endDate.getTime() - getNewDateUTC().getTime()) / ONE_MINUTE).intValue();

			wsDimmedSmartPoint.setOverrideTime(second);
			wsDimmedSmartPoint.setOverrideTerminationEvent(NumberUtils.INTEGER_ZERO);
			item.getLight().setOverridePerDate(endDate);
		}
	}

	/**
	 * Sets the light blink.
	 *
	 * @param item the item
	 * @param wsDimmedSmartPoint the ws dimmed smart point
	 * @param level the level
	 */
	protected void setLightBlink(ProcessItem item, DimmedSmartpoint wsDimmedSmartPoint, Integer level)
	{
		Light light = item.getLight();
		if (isNull(light))
		{
			return;
		}

		if (isNullOrZero(level) || LightBlinkEnum.NONE.getValue().equals(level))
		{
			wsDimmedSmartPoint.setBlinkLevel(BlinkLevel.NONE);
			light.setLightBlinkEnum(LightBlinkEnum.NONE);
			return;
		}

		if (LightBlinkEnum.SLOW.getValue().equals(level))
		{
			wsDimmedSmartPoint.setBlinkLevel(BlinkLevel.SLOW);
			light.setLightBlinkEnum(LightBlinkEnum.SLOW);
			return;
		}

		if (LightBlinkEnum.FAST.getValue().equals(level))
		{
			wsDimmedSmartPoint.setBlinkLevel(BlinkLevel.FAST);
			light.setLightBlinkEnum(LightBlinkEnum.FAST);
		}
	}

	/**
	 * Gets the blink level.
	 *
	 * @param level the level
	 * @return the blink level
	 */
	protected BlinkLevel getBlinkLevel(Integer level)
	{
		if (LightBlinkEnum.SLOW.getValue().equals(level))
		{
			return BlinkLevel.SLOW;
		}

		if (LightBlinkEnum.FAST.getValue().equals(level))
		{
			return BlinkLevel.FAST;
		}

		return BlinkLevel.NONE;
	}

	/**
	 * Gets the schedule id by process.
	 *
	 * @param process the process
	 * @return the schedule id by process
	 */
	protected Integer getScheduleIdByProcess(Process process)
	{
		for (LCActionParameter actionParameter : process.getLcAction().getActionParameters())
		{
			if ((actionParameter.getProperty() == PropertyEnum.SCHEDULE_ID)
					&& NumberUtils.isDigits(actionParameter.getActionValue()))
			{
				return Integer.parseInt(actionParameter.getActionValue());
			}
		}
		return null;
	}

	/**
	 * Persist log.
	 *
	 * @param processRequest the process request
	 * @param wsSmartPointList the ws smart point list
	 */
	protected <T> void persistLog(ProcessRequest processRequest, List<T> wsSmartPointList)
	{
		Process process = processRequest.getProcess();

		StringBuilder message = new StringBuilder("RNI success call: process correlationId: ");
		message.append(process.getRniCorrelationId()).append(", customerId: ")
				.append(processRequest.getTenant().getRniCode())
				.append(", smartPointIds: ");

		if (!isNullOrEmpty(wsSmartPointList))
		{
			for (T smartpoint : wsSmartPointList)
			{
				message.append(((Smartpoint)smartpoint).getRniId()).append(" ");
			}
		}

		if (LOG.isInfoEnabled())
		{
			LOG.info(message.toString());
		}
	}
}
