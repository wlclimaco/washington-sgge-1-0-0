package com.sensus.lc.base.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.model.LightStatusEnum;
import com.sensus.lc.light.model.SearchLight;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class LightSelectionRequest.
 */
public class LightSelectionRequest extends LightingControlRequest
{

	/** The is monitored. */
	private Boolean monitored = false;

	/** The search light. */
	private SearchLight searchLight;

	/** The pagination all selected. */
	private Boolean paginationAllSelected;

	/** The selection pagination ids. */
	private List<Integer> selectionPaginationIds = new ArrayList<Integer>();

	/** The unselection pagination ids. */
	private List<Integer> unselectionPaginationIds = new ArrayList<Integer>();

	/** The current light status. */
	private boolean currentLightStatus = true;

	/** The bottom left lat. */
	private Double bottomLeftLat;

	/** The bottom left lon. */
	private Double bottomLeftLon;

	/** The top right lat. */
	private Double topRightLat;

	/** The top right lon. */
	private Double topRightLon;

	/** The max light count. */
	private Integer maxLightCount;

	/**
	 * Instantiates a new light selection request.
	 */
	public LightSelectionRequest()
	{
	}

	/**
	 * Instantiates a new light selection request.
	 *
	 * @param userContext the user context
	 */
	public LightSelectionRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new light selection request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LightSelectionRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Gets the checks if is monitored.
	 *
	 * @return the checks if is monitored
	 */
	public Boolean isMonitored()
	{
		return monitored;
	}

	/**
	 * Sets the checks if is monitored.
	 *
	 * @param isMonitored the new checks if is monitored
	 */
	public void setIsMonitored(Boolean isMonitored)
	{
		monitored = isMonitored;
	}

	/**
	 * Gets the search light.
	 *
	 * @return the search light
	 */
	public SearchLight getSearchLight()
	{
		if (searchLight == null)
		{
			searchLight = new SearchLight();
		}
		return searchLight;
	}

	/**
	 * Sets the search light.
	 *
	 * @param searchLight the new search light
	 */
	public void setSearchLight(SearchLight searchLight)
	{
		this.searchLight = searchLight;
	}

	/**
	 * Gets the pagination all selected.
	 *
	 * @return the pagination all selected
	 */
	public Boolean getPaginationAllSelected()
	{
		if (paginationAllSelected == null)
		{
			return false;
		}
		return paginationAllSelected;
	}

	/**
	 * Sets the pagination all selected.
	 *
	 * @param paginationAllSelected the new pagination all selected
	 */
	public void setPaginationAllSelected(Boolean paginationAllSelected)
	{
		this.paginationAllSelected = paginationAllSelected;
	}

	/**
	 * Gets the selection pagination ids.
	 *
	 * @return the selection pagination ids
	 */
	public List<Integer> getSelectionPaginationIds()
	{
		return selectionPaginationIds;
	}

	/**
	 * Sets the selection pagination ids.
	 *
	 * @param selectionPaginationIds the new selection pagination ids
	 */
	public void setSelectionPaginationIds(List<Integer> selectionPaginationIds)
	{
		this.selectionPaginationIds = selectionPaginationIds;
	}

	/**
	 * Gets the unselection pagination ids.
	 *
	 * @return the unselection pagination ids
	 */
	public List<Integer> getUnselectionPaginationIds()
	{
		return unselectionPaginationIds;
	}

	/**
	 * Sets the unselection pagination ids.
	 *
	 * @param unselectionPaginationIds the new unselection pagination ids
	 */
	public void setUnselectionPaginationIds(List<Integer> unselectionPaginationIds)
	{
		if (unselectionPaginationIds == null)
		{
			return;
		}

		if ((this.unselectionPaginationIds == null) || this.unselectionPaginationIds.isEmpty())
		{
			this.unselectionPaginationIds = unselectionPaginationIds;
			return;
		}
		this.unselectionPaginationIds.addAll(unselectionPaginationIds);
	}

	/**
	 * Checks if is current light status.
	 *
	 * @return true, if is current light status
	 */
	public boolean isCurrentLightStatus()
	{
		return currentLightStatus;
	}

	/**
	 * Sets the current light status.
	 *
	 * @param currentLightStatus the new current light status
	 */
	public void setCurrentLightStatus(boolean currentLightStatus)
	{
		this.currentLightStatus = currentLightStatus;
	}

	/**
	 * Gets the bottom left lat.
	 *
	 * @return the bottom left lat
	 */
	public Double getBottomLeftLat()
	{
		return bottomLeftLat;
	}

	/**
	 * Sets the bottom left lat.
	 *
	 * @param bottomLeftLat the new bottom left lat
	 */
	public void setBottomLeftLat(Double bottomLeftLat)
	{
		this.bottomLeftLat = bottomLeftLat;
	}

	/**
	 * Gets the bottom left lon.
	 *
	 * @return the bottom left lon
	 */
	public Double getBottomLeftLon()
	{
		return bottomLeftLon;
	}

	/**
	 * Sets the bottom left lon.
	 *
	 * @param bottomLeftLon the new bottom left lon
	 */
	public void setBottomLeftLon(Double bottomLeftLon)
	{
		this.bottomLeftLon = bottomLeftLon;
	}

	/**
	 * Gets the top right lat.
	 *
	 * @return the top right lat
	 */
	public Double getTopRightLat()
	{
		return topRightLat;
	}

	/**
	 * Sets the top right lat.
	 *
	 * @param topRightLat the new top right lat
	 */
	public void setTopRightLat(Double topRightLat)
	{
		this.topRightLat = topRightLat;
	}

	/**
	 * Gets the top right lon.
	 *
	 * @return the top right lon
	 */
	public Double getTopRightLon()
	{
		return topRightLon;
	}

	/**
	 * Sets the top right lon.
	 *
	 * @param topRightLon the new top right lon
	 */
	public void setTopRightLon(Double topRightLon)
	{
		this.topRightLon = topRightLon;
	}

	/**
	 * Gets the max light count.
	 *
	 * @return the max light count
	 */
	public Integer getMaxLightCount()
	{
		return maxLightCount;
	}

	/**
	 * Sets the max light count.
	 *
	 * @param maxLightCount the new max light count
	 */
	public void setMaxLightCount(Integer maxLightCount)
	{
		this.maxLightCount = maxLightCount;
	}

	/**
	 * Checks if is deactivated.
	 *
	 * @param statusList the status list
	 * @return the boolean
	 */
	public Boolean isDeactivatedFilter()
	{
		if (ValidationUtil.isNull(getSearchLight()) || ValidationUtil.isNullOrEmpty(getSearchLight().getStatusList()))
		{
			return false;
		}

		for (LightStatusEnum lightStatusEnum : getSearchLight().getStatusList())
		{
			if (LightStatusEnum.DEACTIVATED.equals(lightStatusEnum))
			{
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LightSelectionRequest [isMonitored()=" + isMonitored() + ", getSearchLight()=" + getSearchLight()
				+ ", getPaginationAllSelected()=" + getPaginationAllSelected() + ", getSelectionPaginationIds()="
				+ getSelectionPaginationIds() + ", getUnselectionPaginationIds()=" + getUnselectionPaginationIds()
				+ ", isCurrentLightStatus()=" + isCurrentLightStatus() + ", getBottomLeftLat()=" + getBottomLeftLat()
				+ ", getBottomLeftLon()=" + getBottomLeftLon() + ", getTopRightLat()=" + getTopRightLat()
				+ ", getTopRightLon()=" + getTopRightLon() + ", getMaxLightCount()=" + getMaxLightCount()
				+ ", isDeactivatedFilter()=" + isDeactivatedFilter() + ", toString()=" + super.toString() + "]";
	}

}
