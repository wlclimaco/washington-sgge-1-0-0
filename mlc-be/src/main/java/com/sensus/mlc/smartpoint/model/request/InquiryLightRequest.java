package com.sensus.mlc.smartpoint.model.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.MLCSortExpression;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.process.model.ProcessFilter;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SmartpointMiddleMap;

/**
 * The Class InquiryLightRequest.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public class InquiryLightRequest extends InquiryPaginationRequest
{
	/** MLC Sort Expression. */
	private MLCSortExpression sortExpression;

	/** The process filter. */
	private ProcessFilter processFilter;

	/** The selection pagination ids. */
	private List<Light> lights = new ArrayList<Light>();

	/** The lights to csv. */
	private List<HashMap<String, String>> lightsToCSV;

	/** The smartpoint middle map. */
	private SmartpointMiddleMap smartpointMiddleMap;

	/** The geo code trunc. */
	private Integer geoCodeTrunc;

	/** The latitude trunc. */
	private Double latitudeTrunc;

	/** The longitude trunc. */
	private Double longitudeTrunc;

	/**
	 * Instantiates a new inquiry light request.
	 */
	public InquiryLightRequest()
	{
	}

	/**
	 * Instantiates a new inquiry light request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLightRequest(UserContext userContext)
	{
		super(userContext);

	}

	/**
	 * Gets the search.
	 * 
	 * @return the search
	 */
	public SearchLight getSearch()
	{
		return getSearchLight();
	}

	/**
	 * Sets the search.
	 * 
	 * @param searchValue the new search
	 */
	public void setSearch(SearchLight searchValue)
	{
		setSearchLight(searchValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#getSortExpression()
	 */
	@Override
	public String getSortExpression()
	{
		if (sortExpression == null)
		{
			return null;
		}
		return String.valueOf(sortExpression);
	}

	/**
	 * Gets the mlc sort expression.
	 * 
	 * @return the mlc sort expression
	 */
	public MLCSortExpression getMlcSortExpression()
	{
		return sortExpression;
	}

	/**
	 * Sets the sort expression.
	 * 
	 * @param sortExpression the new sort expression
	 */
	public void setSortExpression(MLCSortExpression sortExpression)
	{
		this.sortExpression = sortExpression;
	}

	/**
	 * Gets the process filter.
	 * 
	 * @return the process filter
	 */
	public ProcessFilter getProcessFilter()
	{
		return processFilter;
	}

	/**
	 * Sets the process filter.
	 * 
	 * @param processFilter the new process filter
	 */
	public void setProcessFilter(ProcessFilter processFilter)
	{
		this.processFilter = processFilter;
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

	/**
	 * Sets the light.
	 * 
	 * @param light the new light
	 */
	public void setLight(Light light)
	{
		getLights().add(light);
	}

	/**
	 * Gets the lights to csv.
	 * 
	 * @return the lights to csv
	 */
	public List<HashMap<String, String>> getLightsToCSV()
	{
		return lightsToCSV;
	}

	/**
	 * Sets the lights to csv.
	 * 
	 * @param lightsToCSV the lights to csv
	 */
	public void setLightsToCSV(List<HashMap<String, String>> lightsToCSV)
	{
		this.lightsToCSV = lightsToCSV;
	}

	/**
	 * Gets the smartpoint middle map.
	 * 
	 * @return the smartpoint middle map
	 */
	public SmartpointMiddleMap getSmartpointMiddleMap()
	{
		return smartpointMiddleMap;
	}

	/**
	 * Sets the smartpoint middle map.
	 * 
	 * @param smartpointMiddleMap the new smartpoint middle map
	 */
	public void setSmartpointMiddleMap(SmartpointMiddleMap smartpointMiddleMap)
	{
		this.smartpointMiddleMap = smartpointMiddleMap;
	}

	/**
	 * Gets the geo code trunc.
	 * 
	 * @return the geo code trunc
	 */
	public Integer getGeoCodeTrunc()
	{
		return geoCodeTrunc;
	}

	/**
	 * Sets the geo code trunc.
	 * 
	 * @param geoCodeTrunc the new geo code trunc
	 */
	public void setGeoCodeTrunc(Integer geoCodeTrunc)
	{
		this.geoCodeTrunc = geoCodeTrunc;
	}

	/**
	 * Gets the latitude trunc.
	 * 
	 * @return the latitude trunc
	 */
	public Double getLatitudeTrunc()
	{
		return latitudeTrunc;
	}

	/**
	 * Sets the latitude trunc.
	 * 
	 * @param latitudeTrunc the new latitude trunc
	 */
	public void setLatitudeTrunc(Double latitudeTrunc)
	{
		this.latitudeTrunc = latitudeTrunc;
	}

	/**
	 * Gets the longitude trunc.
	 * 
	 * @return the longitude trunc
	 */
	public Double getLongitudeTrunc()
	{
		return longitudeTrunc;
	}

	/**
	 * Sets the longitude trunc.
	 * 
	 * @param longitudeTrunc the new longitude trunc
	 */
	public void setLongitudeTrunc(Double longitudeTrunc)
	{
		this.longitudeTrunc = longitudeTrunc;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLightRequest [getSearch()=" + getSearch() + ", getSortExpression()=" + getSortExpression()
				+ ", getMlcSortExpression()=" + getMlcSortExpression() + ", getProcessFilter()=" + getProcessFilter()
				+ ", getLights()=" + getLights() + ", getLightsToCSV()=" + getLightsToCSV()
				+ ", getSmartpointMiddleMap()=" + getSmartpointMiddleMap() + ", getGeoCodeTrunc()=" + getGeoCodeTrunc()
				+ ", getLatitudeTrunc()=" + getLatitudeTrunc() + ", getLongitudeTrunc()=" + getLongitudeTrunc()
				+ ", getFileName()=" + getFileName() + ", getProcessId()=" + getProcessId() + ", getInitialDate()="
				+ getInitialDate() + ", getEndDate()=" + getEndDate() + ", getStartRow()=" + getStartRow()
				+ ", getEndRow()=" + getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()="
				+ getStartPage() + ", getSortExpressions()=" + getSortExpressions() + ", isPreQueryCount()="
				+ isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount() + ", getListColumn()="
				+ getListColumn() + ", isMonitored()=" + isMonitored() + ", getSearchLight()=" + getSearchLight()
				+ ", getPaginationAllSelected()=" + getPaginationAllSelected() + ", getSelectionPaginationIds()="
				+ getSelectionPaginationIds() + ", getUnselectionPaginationIds()=" + getUnselectionPaginationIds()
				+ ", isCurrentLightStatus()=" + isCurrentLightStatus() + ", getBottomLeftLat()=" + getBottomLeftLat()
				+ ", getBottomLeftLon()=" + getBottomLeftLon() + ", getTopRightLat()=" + getTopRightLat()
				+ ", getTopRightLon()=" + getTopRightLon() + ", getMaxSmartpointCount()=" + getMaxSmartpointCount()
				+ ", getTenant()=" + getTenant() + ", getAllowedGroupIdList()=" + getAllowedGroupIdList()
				+ ", getStringAllowedGroups()=" + getStringAllowedGroups() + ", getTimezone()=" + getTimezone()
				+ ", getDatePattern()=" + getDatePattern() + ", getAction()=" + getAction() + ", getUserContext()="
				+ getUserContext() + "]";
	}
}