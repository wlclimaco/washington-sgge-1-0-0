package com.sensus.mlc.tag.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * * The Model Object Tag.
 *
 * @author - Gustavo Aragao - QAT Brazil
 */
@SuppressWarnings("serial")
public class Tag extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The smart points. */
	private List<Light> lights = new ArrayList<Light>();

	/** The auto group. */
	private Boolean autoGroup;

	/** The addressRelated. */
	private Boolean addressRelated;

	/** The total smartpoints. */
	private Integer totalSmartpoints;

	/** The date. */
	private Date date;

	/**
	 * Instantiates a new tag.
	 */
	public Tag()
	{
	}

	/**
	 * Instantiates a new tag.
	 *
	 * @param tagId the tag id
	 * @param tagName the tag name
	 */
	public Tag(Integer tagId, String tagName)
	{
		setId(tagId);
		setName(tagName);
	}

	/**
	 * Instantiates a new tag.
	 *
	 * @param tagId the tag id
	 */
	public Tag(Integer tagId)
	{
		setId(tagId);
	}

	/**
	 * Instantiates a new tag.
	 *
	 * @param tagName the tag name
	 */
	public Tag(String tagName)
	{
		setName(tagName);
	}

	/**
	 * Instantiates a new tag.
	 *
	 * @param tagId the tag id
	 * @param nameParam the name param
	 * @param descriptionParam the description param
	 * @param lightsParam the lights param
	 * @param autoGroupParam the auto group param
	 */
	public Tag(Integer tagId, String nameParam, String descriptionParam, List<Light> lightsParam, Boolean autoGroupParam)
	{
		setId(tagId);
		setName(nameParam);
		setLights(lightsParam);
		setAutoGroup(autoGroupParam);
	}

	/**
	 * Gets the getTagId.
	 *
	 * @return the tagId
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param tagId the new id of tag
	 */
	public void setId(Integer tagId)
	{
		id = tagId;
	}

	/**
	 * Gets the name.
	 *
	 * @return name the tag name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param nameString the new name
	 */
	public void setName(String nameString)
	{
		name = nameString;
	}

	/**
	 * Gets the SmartPoint.
	 *
	 * @return the list of smart points
	 */
	public List<Light> getLights()
	{
		return lights;
	}

	/**
	 * Sets the SmartPoint.
	 *
	 * @param lightList the new lights
	 */
	public void setLights(List<Light> lightList)
	{
		lights = lightList;
	}

	/**
	 * Sets the smart point.
	 *
	 * @param light the new light
	 */
	public void setLight(Light light)
	{
		getLights().add(light);
	}

	/**
	 * Checks if is auto group.
	 *
	 * @return the boolean
	 */
	public Boolean isAutoGroup()
	{
		if (autoGroup == null)
		{
			return false;
		}
		return autoGroup;
	}

	/**
	 * Sets the auto group.
	 *
	 * @param autoGroup the new auto group
	 */
	public void setAutoGroup(Boolean autoGroup)
	{
		this.autoGroup = autoGroup;
	}

	/**
	 * Sets the total smartpoints.
	 *
	 * @param totalSmartpoints the new total smartpoints
	 */
	public void setTotalSmartpoints(Integer totalSmartpoints)
	{
		this.totalSmartpoints = totalSmartpoints;
	}

	/**
	 * Gets the total smartpoints.
	 *
	 * @return the total smartpoints
	 */
	public Integer getTotalSmartpoints()
	{
		return totalSmartpoints;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	@JSON(format = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public Date getDate()
	{
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * Gets the address related.
	 *
	 * @return the address related
	 */
	public Boolean getAddressRelated()
	{
		return addressRelated;
	}

	/**
	 * Sets the address related.
	 *
	 * @param addressRelated the new address related
	 */
	public void setAddressRelated(Boolean addressRelated)
	{
		this.addressRelated = addressRelated;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Tag [getId()=" + getId() + ", getName()=" + getName() + ", getLights()=" + getLights()
				+ ", isAutoGroup()=" + isAutoGroup() + ", getTotalSmartpoints()=" + getTotalSmartpoints()
				+ ", getDate()=" + getDate() + ", getAddressRelated()=" + getAddressRelated() + ", getAutoGroup()="
				+ isAutoGroup() + ", isAutoGroup()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
