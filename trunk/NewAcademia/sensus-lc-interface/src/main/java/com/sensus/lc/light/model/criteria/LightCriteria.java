package com.sensus.lc.light.model.criteria;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.query.SearchDate;
import com.sensus.lc.ecomode.model.EcoModeFilterEnum;
import com.sensus.lc.light.model.BlinkStatusEnum;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.LightTypeEnum;
import com.sensus.lc.light.model.OverrideEnum;

/**
 * Contains Light specific search criteria.<br>
 * Typically the properties listed in this class relate directly to the Light table. Other properties related to
 * associations are found within the encapsulating request type.<br>
 * Since the criteria can very greatly some type of hierarchy or order must exist to insure consistency.
 * In other words some properties will take priority over other properties. For example if an "id" type property is
 * not-null it will take priority over a simple "name" type property and during the generation of the query the "id"
 * property will be used and the "name" property will not.<br>
 * So it's important to document here and understand this processing priority.
 * <ol>
 * <li>LightIdList - List of light-id's will always take priority over everything else.
 * <li>???
 * </ol>
 *
 */
public class LightCriteria
{
	/**
	 * Attributes.
	 */
	private List<Integer> lightIdList = new ArrayList<Integer>(); // Priority 1
	private List<Integer> notInlightIdList = new ArrayList<Integer>();
	private GeoCodeCriteria geoCodeCriteria;
	private List<LifeCycleStateEnum> lifeCycleStateList = new ArrayList<LifeCycleStateEnum>();
	private Boolean protect;
	private BlinkStatusEnum lightBlink;
	private OverrideEnum override;
	private LightTypeEnum lightType;
	private SearchDate overridePerDate;
	private SearchDate modifyDate;
	private String intensity;
	private SearchTerm poleId;
	private SearchTerm flexnetId;
	private List<EcoModeFilterEnum> ecomodeFilter = new ArrayList<EcoModeFilterEnum>();

	/**
	 * Gets the not inlight id list.
	 *
	 * @return the notInlightIdList
	 */
	public List<Integer> getNotInlightIdList()
	{
		return notInlightIdList;
	}

	/**
	 * Sets the not inlight id list.
	 *
	 * @param notInlightIdList the notInlightIdList to set
	 */
	public void setNotInlightIdList(List<Integer> notInlightIdList)
	{
		this.notInlightIdList = notInlightIdList;
	}

	/**
	 * Gets the flexnet id.
	 *
	 * @return the flexnetId
	 */
	public SearchTerm getFlexnetId()
	{
		return flexnetId;
	}

	/**
	 * Sets the flexnet id.
	 *
	 * @param flexnetId the flexnetId to set
	 */
	public void setFlexnetId(SearchTerm flexnetId)
	{
		this.flexnetId = flexnetId;
	}

	/**
	 * Gets the light id list.
	 *
	 * @return the light id list
	 */
	public List<Integer> getLightIdList()
	{
		return lightIdList;
	}

	/**
	 * Sets the light id list.
	 *
	 * @param lightIdList the new light id list
	 */
	public void setLightIdList(List<Integer> lightIdList)
	{
		this.lightIdList = lightIdList;
	}

	/**
	 * Gets the geo code criteria.
	 *
	 * @return the geo code criteria
	 */
	public GeoCodeCriteria getGeoCodeCriteria()
	{
		return geoCodeCriteria;
	}

	/**
	 * Sets the geo code criteria.
	 *
	 * @param geoCodeCriteria the new geo code criteria
	 */
	public void setGeoCodeCriteria(GeoCodeCriteria geoCodeCriteria)
	{
		this.geoCodeCriteria = geoCodeCriteria;
	}

	/**
	 * Gets the life cycle state list.
	 *
	 * @return the life cycle state list
	 */
	public List<LifeCycleStateEnum> getLifeCycleStateList()
	{
		return lifeCycleStateList;
	}

	/**
	 * Sets the life cycle state list.
	 *
	 * @param lifeCycleStateList the new life cycle state list
	 */
	public void setLifeCycleStateList(List<LifeCycleStateEnum> lifeCycleStateList)
	{
		this.lifeCycleStateList = lifeCycleStateList;
	}

	/**
	 * Adds the life cycle state.
	 *
	 * @param lifeCycleState the life cycle state
	 */
	public void addLifeCycleState(LifeCycleStateEnum lifeCycleState)
	{
		getLifeCycleStateList().add(lifeCycleState);
	}

	/**
	 * Gets the light blink.
	 *
	 * @return the light blink
	 */
	public BlinkStatusEnum getLightBlink()
	{
		return lightBlink;
	}

	/**
	 * Sets the light blink.
	 *
	 * @param lightBlink the new light blink
	 */
	public void setLightBlink(BlinkStatusEnum lightBlink)
	{
		this.lightBlink = lightBlink;
	}

	/**
	 * Gets the override.
	 *
	 * @return the override
	 */
	public OverrideEnum getOverride()
	{
		return override;
	}

	/**
	 * Sets the override.
	 *
	 * @param override the new override
	 */
	public void setOverride(OverrideEnum override)
	{
		this.override = override;
	}

	/**
	 * Gets the light type.
	 *
	 * @return the light type
	 */
	public LightTypeEnum getLightType()
	{
		return lightType;
	}

	/**
	 * Gets the light type value.
	 *
	 * @return the light type value
	 */
	public Integer getLightTypeValue()
	{
		if (lightType != null)
		{
			return lightType.getValue();
		}
		return null;
	}

	/**
	 * Sets the light type.
	 *
	 * @param lightType the new light type
	 */
	public void setLightType(LightTypeEnum lightType)
	{
		this.lightType = lightType;
	}

	/**
	 * Gets the override per date.
	 *
	 * @return the override per date
	 */
	public SearchDate getOverridePerDate()
	{
		return overridePerDate;
	}

	/**
	 * Sets the override per date.
	 *
	 * @param overridePerDate the new override per date
	 */
	public void setOverridePerDate(SearchDate overridePerDate)
	{
		this.overridePerDate = overridePerDate;
	}

	/**
	 * Gets the modify date.
	 *
	 * @return the modify date
	 */
	public SearchDate getModifyDate()
	{
		return modifyDate;
	}

	/**
	 * Sets the modify date.
	 *
	 * @param modifyDate the new modify date
	 */
	public void setModifyDate(SearchDate modifyDate)
	{
		this.modifyDate = modifyDate;
	}

	/**
	 * Gets the intensity.
	 *
	 * @return the intensity
	 */
	public String getIntensity()
	{
		return intensity;
	}

	/**
	 * Sets the intensity.
	 *
	 * @param intensity the new intensity
	 */
	public void setIntensity(String intensity)
	{
		this.intensity = intensity;
	}

	/**
	 * Gets the pole id.
	 *
	 * @return the pole id
	 */
	public SearchTerm getPoleId()
	{
		return poleId;
	}

	/**
	 * Sets the pole id.
	 *
	 * @param poleId the new pole id
	 */
	public void setPoleId(SearchTerm poleId)
	{
		this.poleId = poleId;
	}

	/**
	 * @return the protect
	 */
	public Boolean getProtect()
	{
		return protect;
	}

	/**
	 * @param protect the protect to set
	 */
	public void setProtect(Boolean protect)
	{
		this.protect = protect;
	}

	/**
	 * Gets the ecomode filter.
	 *
	 * @return the ecomode filter
	 */
	public List<EcoModeFilterEnum> getEcomodeFilter()
	{
		return ecomodeFilter;
	}

	/**
	 * Sets the ecomode filter.
	 *
	 * @param ecomodeFilter the new ecomode filter
	 */
	public void setEcomodeFilter(List<EcoModeFilterEnum> ecomodeFilter)
	{
		this.ecomodeFilter = ecomodeFilter;
	}

	/**
	 * Checks for filter.
	 *
	 * @return true, if successful
	 */
	public boolean hasSelectedLight()
	{
		return !isNullOrEmpty(getLightIdList()) ||
				!isNullOrEmpty(getNotInlightIdList());
	}

	/**
	 * Checks for filter.
	 *
	 * @return true, if successful
	 */
	public boolean hasFilter()
	{
		return hasSelectedLight() ||
				!isNullOrEmpty(getLifeCycleStateList()) ||
				!isNull(getProtect()) ||
				!isNull(getLightBlink()) ||
				!isNull(getOverride()) ||
				!isNull(getLightType()) ||
				!isNull(getOverridePerDate()) ||
				!isNull(getModifyDate()) ||
				!isNull(getIntensity()) ||
				(!isNull(getPoleId()) && !isNull(getPoleId().getValue())) ||
				(!isNull(getFlexnetId()) && !isNull(getFlexnetId().getValue())) ||
				(!isNull(getGeoCodeCriteria()) && getGeoCodeCriteria().hasFilter());
	}

	@Override
	public String toString()
	{
		return "LightCriteria [getNotInlightIdList()=" + getNotInlightIdList() + ", getFlexnetId()=" + getFlexnetId()
				+ ", getLightIdList()=" + getLightIdList() + ", getGeoCodeCriteria()=" + getGeoCodeCriteria()
				+ ", getLifeCycleStateList()=" + getLifeCycleStateList() + ", getLightBlink()=" + getLightBlink()
				+ ", getOverride()=" + getOverride() + ", getLightType()=" + getLightType() + ", getLightTypeValue()="
				+ getLightTypeValue() + ", getOverridePerDate()=" + getOverridePerDate() + ", getModifyDate()="
				+ getModifyDate() + ", getIntensity()=" + getIntensity() + ", getPoleId()=" + getPoleId()
				+ ", getProtect()=" + getProtect() + ", getEcomodeFilter()=" + getEcomodeFilter()
				+ ", hasSelectedLight()=" + hasSelectedLight() + ", hasFilter()=" + hasFilter() + ", toString()="
				+ super.toString() + "]";
	}
}
