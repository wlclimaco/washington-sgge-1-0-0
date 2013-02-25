/*
 *
 */
package com.sensus.mlc.ecomode.model.request;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.tag.model.Tag;

/**
 * The Class InquiryEcoModeRequest.
 */
public class InquiryEcoModeRequest extends InquiryPaginationRequest
{

	/** The eco mode baseline. */
	private List<EcoModeBaseline> ecoModeBaselineList = new ArrayList<EcoModeBaseline>();

	/** The light consumptions. */
	private List<LightConsumption> lightConsumptions = new ArrayList<LightConsumption>();

	/** The tags. */
	private List<Tag> tags = new ArrayList<Tag>();

	/** The eco mode csv import. */
	private File ecoModeCSVImport;

	/** The light. */
	private Light light;

	/** The lights not updated amount. */
	private int invalidEcoModeAmount;

	/** The internal processing. */
	private boolean internalProcessing;

	/**
	 * Instantiates a new inquiry eco mode request.
	 */
	public InquiryEcoModeRequest()
	{
	}

	/**
	 * Instantiates a new inquiry eco mode request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryEcoModeRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the first eco mode baseline.
	 * 
	 * @return the first eco mode baseline
	 */
	public EcoModeBaseline getFirstEcoModeBaseline()
	{
		if (this.ecoModeBaselineList.isEmpty())
		{
			return null;
		}

		return this.ecoModeBaselineList.get(0);
	}

	/**
	 * Gets the eco mode baseline list.
	 * 
	 * @return the eco mode baseline list
	 */
	public List<EcoModeBaseline> getEcoModeBaselineList()
	{
		return this.ecoModeBaselineList;
	}

	/**
	 * Adds the eco mode baseline.
	 * 
	 * @param baseline the baseline
	 */
	public void addEcoModeBaseline(EcoModeBaseline baseline)
	{
		getEcoModeBaselineList().add(baseline);
	}

	/**
	 * Sets the eco mode baseline list.
	 * 
	 * @param ecoModeBaselineList the new eco mode baseline list
	 */
	public void setEcoModeBaselineList(List<EcoModeBaseline> ecoModeBaselineList)
	{
		this.ecoModeBaselineList = ecoModeBaselineList;
	}

	/**
	 * Gets the tags.
	 * 
	 * @return the tags
	 */
	public List<Tag> getTags()
	{
		return this.tags;
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
		return this.ecoModeCSVImport;
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
	 * Gets the light.
	 * 
	 * @return the light
	 */
	public Light getLight()
	{
		return this.light;
	}

	/**
	 * Sets the light.
	 * 
	 * @param light the new light
	 */
	public void setLight(Light light)
	{
		this.light = light;
	}

	/**
	 * Gets the light consumption.
	 * 
	 * @return the light consumption
	 */
	public LightConsumption getFirstLightConsumption()
	{
		if (!getLightConsumptions().isEmpty())
		{
			return getLightConsumptions().get(0);
		}
		return null;
	}

	/**
	 * Gets the light consumptions.
	 * 
	 * @return the light consumptions
	 */
	public List<LightConsumption> getLightConsumptions()
	{
		return this.lightConsumptions;
	}

	/**
	 * Sets the light consumptions.
	 * 
	 * @param lightConsumptions the new light consumptions
	 */
	public void setLightConsumptions(List<LightConsumption> lightConsumptions)
	{
		this.lightConsumptions = lightConsumptions;
	}

	/**
	 * Adds the light consumption.
	 * 
	 * @param lightConsumption the light consumption
	 */
	public void addLightConsumption(LightConsumption lightConsumption)
	{
		getLightConsumptions().add(lightConsumption);
	}

	/**
	 * Gets the lights with problem.
	 * 
	 * @return the lights with problem
	 */
	public int getInvalidEcoModeAmount()
	{
		return this.invalidEcoModeAmount;
	}

	/**
	 * Sets the invalid eco mode amount.
	 * 
	 * @param invalidEcoModeAmount the new invalid eco mode amount
	 */
	public void setInvalidEcoModeAmount(int invalidEcoModeAmount)
	{
		this.invalidEcoModeAmount = invalidEcoModeAmount;
	}

	/**
	 * Checks if is internal processing.
	 * 
	 * @return true, if is internal processing
	 */
	public boolean isInternalProcessing()
	{
		return this.internalProcessing;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryEcoModeRequest [getFirstEcoModeBaseline()=" + getFirstEcoModeBaseline()
				+ ", getEcoModeBaselineList()=" + getEcoModeBaselineList() + ", getTags()=" + getTags()
				+ ", getEcoModeCSVImport()=" + getEcoModeCSVImport() + ", getLight()=" + getLight()
				+ ", getFirstLightConsumption()=" + getFirstLightConsumption() + ", getLightConsumptions()="
				+ getLightConsumptions() + ", getInvalidEcoModeAmount()=" + getInvalidEcoModeAmount()
				+ ", isInternalProcessing()=" + isInternalProcessing() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", getInitialDate()=" + getInitialDate() + ", getEndDate()="
				+ getEndDate() + ", getStartRow()=" + getStartRow() + ", getEndRow()=" + getEndRow()
				+ ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage() + ", getSortExpressions()="
				+ getSortExpressions() + ", getSortExpression()=" + getSortExpression() + ", isPreQueryCount()="
				+ isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount() + ", getListColumn()="
				+ getListColumn() + ", isMonitored()=" + isMonitored() + ", getSearchLight()=" + getSearchLight()
				+ ", getPaginationAllSelected()=" + getPaginationAllSelected() + ", getSelectionPaginationIds()="
				+ getSelectionPaginationIds() + ", getUnselectionPaginationIds()=" + getUnselectionPaginationIds()
				+ ", isCurrentLightStatus()=" + isCurrentLightStatus() + ", getBottomLeftLat()=" + getBottomLeftLat()
				+ ", getBottomLeftLon()=" + getBottomLeftLon() + ", getTopRightLat()=" + getTopRightLat()
				+ ", getTopRightLon()=" + getTopRightLon() + ", getMaxSmartpointCount()=" + getMaxSmartpointCount()
				+ ", getTenant()=" + getTenant() + ", getAllowedGroupIdList()=" + getAllowedGroupIdList()
				+ ", getStringAllowedGroups()=" + getStringAllowedGroups() + ", getTimezone()=" + getTimezone()
				+ ", getDatePattern()=" + getDatePattern() + ", getUserContext()=" + getUserContext() + "]";
	}
}
