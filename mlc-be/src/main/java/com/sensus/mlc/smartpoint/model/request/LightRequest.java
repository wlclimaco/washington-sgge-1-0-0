package com.sensus.mlc.smartpoint.model.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;
import com.sensus.mlc.smartpoint.model.LightDetailDataTypeEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.OverrideEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class LightRequest.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public class LightRequest extends LightSelectionRequest
{
	/** The light. */
	private List<Light> lights = new ArrayList<Light>();

	/** The protect. */
	private Boolean protect;

	/** The status messages. */
	private List<StatusMessage> statusMessages = new ArrayList<StatusMessage>();

	/** The file name. */
	private String fileName;

	/** The light detail data type. */
	private List<LightDetailDataTypeEnum> lightDetailDataType = new ArrayList<LightDetailDataTypeEnum>();

	/** The percentage. */
	private Integer percentage;

	/** The blink status. */
	private LightBlinkEnum lightBlinkEnum;

	/** The Override ***. */
	private OverrideEnum overrideEnum;

	/** The override per date. */
	private Date overridePerDate;

	/** The light status enum. */
	private LightStatusEnum lightStatusEnum;

	/** The is clear override. */
	private Boolean isClearOverride;

	/**
	 * Instantiates a new light request.
	 */
	public LightRequest()
	{
	}

	/**
	 * Creates a new light request object.
	 * 
	 * @param userContext the user context
	 */
	public LightRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new light request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LightRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Creates a new light request object.
	 * 
	 * @param userContext the user context
	 * @param lights the lights
	 */
	public LightRequest(UserContext userContext, List<Light> lightsValues)
	{
		super(userContext);
		setLights(lightsValues);
	}

	/**
	 * Instantiates a new light request.
	 * 
	 * @param userContext the user context
	 * @param light the light
	 */
	public LightRequest(UserContext userContext, Light light)
	{
		super(userContext);
		addLight(light);
	}

	/**
	 * Gets the light List.
	 * 
	 * @return the light List
	 */
	public List<Light> getLights()
	{
		return lights;
	}

	/**
	 * Sets the light list.
	 * 
	 * @param lightList the new lights
	 */
	public void setLights(List<Light> lightList)
	{
		lights = lightList;
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
	 * Gets the status messages.
	 * 
	 * @return the status messages
	 */
	public List<StatusMessage> getStatusMessages()
	{
		return statusMessages;
	}

	/**
	 * Gets the first message.
	 * 
	 * @return the first message
	 */
	public StatusMessage getFirstMessage()
	{
		if (!getStatusMessages().isEmpty())
		{
			return getStatusMessages().get(0);
		}
		return null;
	}

	/**
	 * Sets the status messages.
	 * 
	 * @param statusMessages the new status messages
	 */
	public void setStatusMessages(List<StatusMessage> statusMessages)
	{
		this.statusMessages = statusMessages;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the light detail data type.
	 * 
	 * @return the light detail data type
	 */
	public List<LightDetailDataTypeEnum> getLightDetailDataType()
	{
		return lightDetailDataType;
	}

	/**
	 * Sets the light detail data type.
	 * 
	 * @param lightDetailDataType the new light detail data type
	 */
	public void setLightDetailDataType(List<LightDetailDataTypeEnum> lightDetailDataType)
	{
		this.lightDetailDataType = lightDetailDataType;
	}

	/**
	 * Gets the percentage.
	 * 
	 * @return the percentage
	 */
	public Integer getPercentage()
	{
		return percentage;
	}

	/**
	 * Sets the percentage.
	 * 
	 * @param percentage the new percentage
	 */
	public void setPercentage(Integer percentage)
	{
		this.percentage = percentage;
	}

	/**
	 * Get the first light in the List.
	 * 
	 * @return the light
	 */
	public Light getFirstLight()
	{
		if (!getLights().isEmpty())
		{
			return getLights().get(0);
		}

		return null;
	}

	/**
	 * Add a light to the List.
	 * 
	 * @param lightObject the new light
	 */
	public void addLight(Light lightObject)
	{
		if (getLights() == null)
		{
			setLights(new ArrayList<Light>());
		}

		getLights().add(lightObject);
	}

	/**
	 * Gets the light blink enum.
	 * 
	 * @return the light blink enum
	 */
	public LightBlinkEnum getLightBlinkEnum()
	{
		return lightBlinkEnum;
	}

	/**
	 * Sets the light blink enum.
	 * 
	 * @param lightBlinkEnum the new light blink enum
	 */
	public void setLightBlinkEnum(LightBlinkEnum lightBlinkEnum)
	{
		this.lightBlinkEnum = lightBlinkEnum;
	}

	/**
	 * Gets the override enum.
	 * 
	 * @return the override enum
	 */
	public OverrideEnum getOverrideEnum()
	{
		return overrideEnum;
	}

	/**
	 * Sets the override enum.
	 * 
	 * @param overrideEnum the new override enum
	 */
	public void setOverrideEnum(OverrideEnum overrideEnum)
	{
		this.overrideEnum = overrideEnum;
	}

	/**
	 * Gets the override per date.
	 * 
	 * @return the override per date
	 */
	public Date getOverridePerDate()
	{
		return overridePerDate;
	}

	/**
	 * Sets the override per date.
	 * 
	 * @param overridePerDate the new override per date
	 */
	public void setOverridePerDate(Date overridePerDate)
	{
		this.overridePerDate = overridePerDate;
	}

	/**
	 * Gets the light status enum.
	 * 
	 * @return the light status enum
	 */
	public LightStatusEnum getLightStatusEnum()
	{
		return lightStatusEnum;
	}

	/**
	 * Sets the light status enum.
	 * 
	 * @param lightStatusEnum the new light status enum
	 */
	public void setLightStatusEnum(LightStatusEnum lightStatusEnum)
	{
		this.lightStatusEnum = lightStatusEnum;
	}

	/**
	 * Sets the light status enum value.
	 * 
	 * @param lightStatus the new light status enum value
	 */
	public void setLightStatusEnumValue(Integer lightStatus)
	{
		lightStatusEnum = LightStatusEnum.enumForValue(lightStatus);
	}

	/**
	 * Gets the light status enum value.
	 * 
	 * @return the light status enum value
	 */
	public Integer getLightStatusEnumValue()
	{
		return lightStatusEnum.getValue();
	}

	/**
	 * Gets the checks if is clear override.
	 * 
	 * @return the checks if is clear override
	 */
	public Boolean getIsClearOverride()
	{
		return isClearOverride;
	}

	/**
	 * Sets the checks if is clear override.
	 * 
	 * @param isClearOverride the new checks if is clear override
	 */
	public void setIsClearOverride(Boolean isClearOverride)
	{
		this.isClearOverride = isClearOverride;
	}

	@Override
	public String toString()
	{
		return "LightRequest [getLights()=" + getLights() + ", getProtect()=" + getProtect() + ", getStatusMessages()="
				+ getStatusMessages() + ", getFileName()=" + getFileName() + ", getLightDetailDataType()="
				+ getLightDetailDataType() + ", getPercentage()=" + getPercentage() + ", getFirstLight()="
				+ getFirstLight() + ", getLightBlinkEnum()=" + getLightBlinkEnum() + ", getOverrideEnum()="
				+ getOverrideEnum() + ", getOverridePerDate()=" + getOverridePerDate() + ", getLightStatusEnum()="
				+ getLightStatusEnum() + ", getLightStatusEnumValue()=" + getLightStatusEnumValue()
				+ ", getIsClearOverride()=" + getIsClearOverride() + "]";
	}
}
