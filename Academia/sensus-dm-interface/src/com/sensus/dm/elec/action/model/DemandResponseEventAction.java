package com.sensus.dm.elec.action.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class DemandResponseEventAction.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class DemandResponseEventAction extends DemandResponseAction
{
	/** The heating. */
	private Integer heating;

	/** The cooling. */
	private Integer cooling;

	/** The duty cycle rate. */
	private Integer dutyCycleRate;

	/** The load adjustment. */
	private Integer loadAdjustment;

	/** The criticality level. */
	private Integer criticalityLevel;

	/** The randomize start. */
	private Boolean randomizeStart;

	/** The randomize end. */
	private Boolean randomizeEnd;

	/** The device classes. */
	private List<String> deviceClasses;

	/** The full_ participation. */
	private Integer fullParticipation;

	/** The partial_participation. */
	private Integer partialParticipation;

	/** The opt_out. */
	private Integer optOut;

	/**
	 * Instantiates a new demand response event action.
	 */
	public DemandResponseEventAction()
	{
		super(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT));
	}

	/**
	 * Instantiates a new demand response event action.
	 * 
	 * @param id the id
	 */
	public DemandResponseEventAction(Integer id)
	{
		this();
		setId(id);
	}

	/**
	 * Instantiates a new demand response event action.
	 * 
	 * @param onDemand the on demand
	 * @param isMonitored the is monitored
	 * @param actionTime the action time
	 */
	public DemandResponseEventAction(Boolean onDemand, Boolean isMonitored, Date actionTime)
	{
		this();
		setOnDemand(onDemand);
		setIsMonitored(isMonitored);
		setActionTime(actionTime);
	}

	/**
	 * Instantiates a new demand response event action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 * @param actionTime the action time
	 */
	public DemandResponseEventAction(Integer id, Boolean isMonitored, Boolean onDemand, Date actionTime)
	{
		this(isMonitored, onDemand, actionTime);
		setId(id);
	}

	/**
	 * Instantiates a new demand response event action.
	 * 
	 * @param action the action view
	 */
	public DemandResponseEventAction(DMAction action)
	{
		this();
		setId(action.getId());
		setCreateDate(action.getCreateDate());
		setCreateUser(action.getCreateUser());
		setDevices(action.getDevices());
		setGroups(action.getGroups());
		setActionTime(action.getActionTime());
		setIsMonitored(action.getIsMonitored());
		setProcessId(action.getProcessId());
		setTags(action.getTags());
		setTotalDevices(action.getTotalDevices());
	}

	/**
	 * Gets the heating.
	 * 
	 * @return the heating
	 */
	public Integer getHeating()
	{
		return heating;
	}

	/**
	 * Sets the heating.
	 * 
	 * @param heating the new heating
	 */
	public void setHeating(Integer heating)
	{
		this.heating = heating;
	}

	/**
	 * Gets the cooling.
	 * 
	 * @return the cooling
	 */
	public Integer getCooling()
	{
		return cooling;
	}

	/**
	 * Sets the cooling.
	 * 
	 * @param cooling the new cooling
	 */
	public void setCooling(Integer cooling)
	{
		this.cooling = cooling;
	}

	/**
	 * Gets the duty cycle rate.
	 * 
	 * @return the duty cycle rate
	 */
	public Integer getDutyCycleRate()
	{
		return dutyCycleRate;
	}

	/**
	 * Sets the duty cycle rate.
	 * 
	 * @param dutyCycleRate the new duty cycle rate
	 */
	public void setDutyCycleRate(Integer dutyCycleRate)
	{
		this.dutyCycleRate = dutyCycleRate;
	}

	/**
	 * Gets the load adjustment.
	 * 
	 * @return the load adjustment
	 */
	public Integer getLoadAdjustment()
	{
		return loadAdjustment;
	}

	/**
	 * Sets the load adjustment.
	 * 
	 * @param loadAdjustment the new load adjustment
	 */
	public void setLoadAdjustment(Integer loadAdjustment)
	{
		this.loadAdjustment = loadAdjustment;
	}

	/**
	 * Gets the criticality level.
	 * 
	 * @return the criticality level
	 */
	public Integer getCriticalityLevel()
	{
		return criticalityLevel;
	}

	/**
	 * Sets the criticality level.
	 * 
	 * @param criticalityLevel the new criticality level
	 */
	public void setCriticalityLevel(Integer criticalityLevel)
	{
		this.criticalityLevel = criticalityLevel;
	}

	/**
	 * Gets the randomize start.
	 * 
	 * @return the randomize start
	 */
	public Boolean getRandomizeStart()
	{
		return randomizeStart;
	}

	/**
	 * Sets the randomize start.
	 * 
	 * @param randomizeStart the new randomize start
	 */
	public void setRandomizeStart(Boolean randomizeStart)
	{
		this.randomizeStart = randomizeStart;
	}

	/**
	 * Gets the randomize end.
	 * 
	 * @return the randomize end
	 */
	public Boolean getRandomizeEnd()
	{
		return randomizeEnd;
	}

	/**
	 * Sets the randomize end.
	 * 
	 * @param randomizeEnd the new randomize end
	 */
	public void setRandomizeEnd(Boolean randomizeEnd)
	{
		this.randomizeEnd = randomizeEnd;
	}

	/**
	 * Gets the device classes.
	 * 
	 * @return the device classes
	 */
	public List<String> getDeviceClasses()
	{
		return deviceClasses;
	}

	/**
	 * Sets the device classes.
	 * 
	 * @param deviceClasses the new device classes
	 */
	public void setDeviceClasses(List<String> deviceClasses)
	{
		this.deviceClasses = deviceClasses;
	}

	/**
	 * Adds the device class.
	 * 
	 * @param deviceClass the device class
	 */
	public void addDeviceClass(String deviceClass)
	{
		if (getDeviceClasses() == null)
		{
			setDeviceClasses(new ArrayList<String>());
		}

		getDeviceClasses().add(deviceClass);
	}

	/**
	 * Gets the full participation.
	 * 
	 * @return the full participation
	 */
	public Integer getFullParticipation()
	{
		return fullParticipation;
	}

	/**
	 * Sets the full participation.
	 * 
	 * @param fullParticipation the new full participation
	 */
	public void setFullParticipation(Integer fullParticipation)
	{
		this.fullParticipation = fullParticipation;
	}

	/**
	 * Gets the partial participation.
	 * 
	 * @return the partial participation
	 */
	public Integer getPartialParticipation()
	{
		return partialParticipation;
	}

	/**
	 * Sets the partial participation.
	 * 
	 * @param partialParticipation the new partial participation
	 */
	public void setPartialParticipation(Integer partialParticipation)
	{
		this.partialParticipation = partialParticipation;
	}

	/**
	 * Gets the opt out.
	 * 
	 * @return the opt out
	 */
	public Integer getOptOut()
	{
		return optOut;
	}

	/**
	 * Sets the opt out.
	 * 
	 * @param optOut the new opt out
	 */
	public void setOptOut(Integer optOut)
	{
		this.optOut = optOut;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.model.DemandResponseAction#toString()
	 */
	@Override
	public String toString()
	{
		return "DemandResponseEventAction [getHeating()=" + getHeating() + ", getCooling()=" + getCooling()
				+ ", getDutyCycleRate()=" + getDutyCycleRate() + ", getLoadAdjustment()=" + getLoadAdjustment()
				+ ", getCriticalityLevel()=" + getCriticalityLevel() + ", getRandomizeStart()=" + getRandomizeStart()
				+ ", getRandomizeEnd()=" + getRandomizeEnd() + ", getDeviceClasses()=" + getDeviceClasses()
				+ ", getFullParticipation()=" + getFullParticipation() + ", getPartialParticipation()="
				+ getPartialParticipation() + ", getOptOut()=" + getOptOut() + ", toString()=" + super.toString() + "]";
	}
}
