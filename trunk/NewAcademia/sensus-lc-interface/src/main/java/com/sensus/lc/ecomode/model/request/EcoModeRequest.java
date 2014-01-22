package com.sensus.lc.ecomode.model.request;

import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.model.request.LightSelectionRequest;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.tag.model.Tag;

/**
 * The Class EcoModeRequest.
 */
public class EcoModeRequest extends LightSelectionRequest
{
	/** Attributes. */
	private List<Light> lights = new ArrayList<Light>();
	private List<Tag> tags = new ArrayList<Tag>();
	private File ecoModeCSVImport;
	private int invalidEcoModeAmount = 0;
	private boolean internalProcessing;
	private boolean containFileHeader;
	private boolean reprocessLight = false;

	/**
	 * Instantiates a new eco mode request.
	 */
	public EcoModeRequest()
	{
	}

	/**
	 * Instantiates a new eco mode request.
	 * 
	 * @param userContext the user context
	 */
	public EcoModeRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the lights.
	 * 
	 * @return the lights
	 */
	public List<Light> getLights()
	{
		return lights;
	}

	/**
	 * Sets the lights.
	 * 
	 * @param lights the new lights
	 */
	public void setLights(List<Light> lights)
	{
		this.lights = lights;
	}

	public void addLight(Light light)
	{
		if (isNullOrEmpty(getLights()))
		{
			setLights(new ArrayList<Light>());
		}

		getLights().add(light);
	}

	public Light getFirstLight()
	{
		if (isNullOrEmpty(getLights()))
		{
			return null;
		}

		return getLights().get(0);
	}

	/**
	 * Gets the tags.
	 * 
	 * @return the tags
	 */
	public List<Tag> getTags()
	{
		return tags;
	}

	/**
	 * Sets the tags.
	 * 
	 * @param tags the new tags
	 */
	public void setTags(List<Tag> tags)
	{
		this.tags = tags;
	}

	/**
	 * Gets the eco mode csv import.
	 * 
	 * @return the eco mode csv import
	 */
	public File getEcoModeCSVImport()
	{
		return ecoModeCSVImport;
	}

	/**
	 * Sets the eco mode csv import.
	 * 
	 * @param ecoModeCSVImport the new eco mode csv import
	 */
	public void setEcoModeCSVImport(File ecoModeCSVImport)
	{
		this.ecoModeCSVImport = ecoModeCSVImport;
	}

	/**
	 * Gets the lights with problem.
	 * 
	 * @return the lights with problem
	 */
	public int getInvalidEcoModeAmount()
	{
		return invalidEcoModeAmount;
	}

	/**
	 * Sets the invalid eco mode amount.
	 * 
	 * @param invalidEcoModeAmount the new invalid eco mode amount
	 */
	public void setInvalidEcoModeAmount(Integer invalidEcoModeAmount)
	{
		this.invalidEcoModeAmount = invalidEcoModeAmount;
	}

	/**
	 * Adds the invalid eco mode amount.
	 * 
	 * @param invalidEcoModeAmount the invalid eco mode amount
	 */
	public void addInvalidEcoModeAmount(Integer amount)
	{
		invalidEcoModeAmount += amount;
	}

	/**
	 * Checks if is internal processing.
	 * 
	 * @return true, if is internal processing
	 */
	public boolean isInternalProcessing()
	{
		return internalProcessing;
	}

	/**
	 * Sets the internal processing.
	 * 
	 * @param internalProcessing the new internal processing
	 */
	public void setInternalProcessing(boolean internalProcessing)
	{
		this.internalProcessing = internalProcessing;
	}

	/**
	 * Checks if is contain file header.
	 * 
	 * @return true, if is contain file header
	 */
	public boolean isContainFileHeader()
	{
		return containFileHeader;
	}

	/**
	 * Sets the contain file header.
	 * 
	 * @param containFileHeader the new contain file header
	 */
	public void setContainFileHeader(boolean containFileHeader)
	{
		this.containFileHeader = containFileHeader;
	}

	/**
	 * Checks if is reprocess lights.
	 * 
	 * @return true, if is reprocess lights
	 */
	public boolean isReprocessLight()
	{
		return reprocessLight;
	}

	/**
	 * Sets the reprocess lights.
	 * 
	 * @param reprocessLight the new reprocess lights
	 */
	public void setReprocessLight(boolean reprocessLight)
	{
		this.reprocessLight = reprocessLight;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "EcoModeRequest [getLights()=" + getLights() + ", getFirstLight()=" + getFirstLight() + ", getTags()="
				+ getTags() + ", getEcoModeCSVImport()=" + getEcoModeCSVImport() + ", getInvalidEcoModeAmount()="
				+ getInvalidEcoModeAmount() + ", isInternalProcessing()=" + isInternalProcessing()
				+ ", isContainFileHeader()=" + isContainFileHeader() + ", isReprocessLights()=" + isReprocessLight()
				+ ", toString()=" + super.toString() + "]";
	}
}
