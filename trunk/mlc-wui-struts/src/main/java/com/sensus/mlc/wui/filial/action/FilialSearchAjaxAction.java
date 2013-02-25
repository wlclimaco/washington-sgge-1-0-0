package com.sensus.mlc.wui.filial.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.sensus.common.util.SensusStringUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.SearchJsonResult;
import com.sensus.mlc.wui.base.util.ActionPaginationUtil;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.CustomizeAjaxAction;
import com.sensus.mlc.wui.base.util.ResultUtil;




@SuppressWarnings("serial")
public class FilialSearchAjaxAction extends ActionBase
{

	/** ************************ CONSTANTS ***********************. */

	/** The Constant CITY. */
	private static final String CITY = "city";

	/** The Constant COLUMN. */
	private static final String COLUMN = "Columns";

	/** The Constant COMMA. */
	private static final String COMMA = ",";

	/** The Constant END. */
	private static final String END = "end";

	/** The Constant ERROR_NOT_SEARCH_SMARTPOINT. */
	private static final String ERROR_NOT_SEARCH_SMARTPOINT = "Could not search SmartPoints";

	/** The Constant METER_ID. */
	private static final String METER_ID = "METER_ID";

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "FLEXNET_ID";

	/** The Constant TOKEN. */
	private static final String PIPE = "[|]";

	/** The Constant QUERY. */
	private static final String QUERY = "query";

	/** The Constant START. */
	private static final String START = "start";

	/** The Constant STREET. */
	private static final String STREET = "street";

	/** The Constant STATUS. */
	private static final String STATUS = "status";

	/** The Constant ZIP. */
	private static final String ZIP = "zip";

	/** ************************ PROPERTIES ***********************. */

	/** The file name. */
	private String fileName;

	/** The has latitude. */
	private Boolean hasLatitude = false;

	/** The has longitude. */
	private Boolean hasLongitude = false;

	/** The process id. */
	private Integer processId;

	/** The smartpoint search result. */
	private SearchJsonResult smartpointSearchResult;

	/**
	 * Gets the checks for latitude.
	 * 
	 * @return the checks for latitude
	 */
	public Boolean getHasLatitude()
	{
		return hasLatitude;
	}

	/**
	 * Sets the checks for latitude.
	 * 
	 * @param hasLatitude the new checks for latitude
	 */
	public void setHasLatitude(Boolean hasLatitude)
	{
		this.hasLatitude = hasLatitude;
	}

	/**
	 * Gets the checks for longitude.
	 * 
	 * @return the checks for longitude
	 */
	public Boolean getHasLongitude()
	{
		return hasLongitude;
	}

	/**
	 * Sets the checks for longitude.
	 * 
	 * @param hasLongitude the new checks for longitude
	 */
	public void setHasLongitude(Boolean hasLongitude)
	{
		this.hasLongitude = hasLongitude;
	}

	/**
	 * Search.
	 * 
	 * @return the string
	 */

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
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

}
