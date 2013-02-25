package com.sensus.mlc.wui.smartpoint.action;

import java.io.InputStream;

import com.sensus.common.model.Response;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.settings.bcf.ISettingsBCF;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF;
import com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.CustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tag.model.response.TagResponse;
import com.sensus.mlc.wui.base.action.ActionBase;

/**
 * Struts action for SmartPoint-related Ajax callbacks. This action primarily supports the actions available from the
 * Action menu list.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class SmartpointAjaxAction extends ActionBase
{

	/** CONSTANTS *. */
	/** The Constant ERROR_INSERT_COLUMN_FILTER. */
	private static final String ERROR_INSERT_COLUMN_FILTER = "Error inserting SmartPoints Columns and filters";

	/** The Constant ERROR_GETTING_LIGHT_INFO. */
	private static final String ERROR_GETTING_LIGHT_INFO = "Error getting light information";

	/** The Long Running Process created when the user force light status. */
	private Integer forceLightStatusLRPId;

	/** The smart point accessor bcf. */
	private ISmartPointAccessorBCF smartPointAccessorBCF;

	/** The smart point updater bcf. */
	private ISmartPointUpdaterBCF smartPointUpdaterBCF;

	/** The smart point processor bcf. */
	private ISmartPointProcessorBCF smartPointProcessorBCF;

	/** The settings bcf. */
	private ISettingsBCF settingsBCF;

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/** The file name. */
	private String fileName;

	/** The input stream. */
	private InputStream inputStream;

	/** The csv name. */
	private String csvName;

	/** The response. */
	private Response response;

	/** The column filter request. */
	private ColumnFilterRequest columnFilterRequest;

	/** The custom search request. */
	private CustomSearchRequest customSearchRequest;

	/** The inquiry light request. */
	private InquiryLightRequest inquiryLightRequest;

	/** The tag request. */
	private TagRequest tagRequest;

	/** The light request. */
	private LightRequest lightRequest;

	/**
	 * Update list light protected.
	 * 
	 * @return the string
	 */
	public String updateListLightProtected()
	{
		try
		{

			LightRequest lightReq = getLightRequest();
			LightResponse lightResponse = getSmartPointUpdaterBCF().updateLightProtected(lightReq);
			setResponse(lightResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error adding SmartPoints to protected", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Insert list smartpoint to tag.
	 * 
	 * @return the string
	 */
	public String insertListSmartpointToTag()
	{
		try
		{
			TagRequest tagReq = getTagRequest();
			TagResponse tagResponse = getTagBCF().insertSmartPointToTag(tagReq);

			setResponse(tagResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error adding SmartPoints to tag", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Remove list smartpoint to tag.
	 * 
	 * @return the string
	 */
	public String removeListSmartpointToTag()
	{
		try
		{
			TagRequest tagReq = getTagRequest();
			TagResponse tagResponse = getTagBCF().deleteSmartPointFromTag(tagReq);

			setResponse(tagResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error remove SmartPoints from tag", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Clear smart points alerts.
	 * 
	 * @return the string
	 */
	public String clearSmartPointsAlerts()
	{

		try
		{
			ProcessResponse processResponse = getSmartPointProcessorBCF().initiateDeleteAlert(getLightRequest());

			setResponse(processResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error clear SmartPoints Alerts", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Update smart points status.
	 * 
	 * @return the string
	 */
	public String updateSmartPointsStatus()
	{

		try
		{

			ProcessResponse processResponse = getSmartPointProcessorBCF().initiateGetLightStatus(getLightRequest());

			setResponse(processResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error update SmartPoints Status", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Update smart points status.
	 * 
	 * @return the string
	 */
	public String saveSearch()
	{
		try
		{
			CustomSearchRequest customSearchReq = getCustomSearchRequest();

			CustomSearchResponse customSearchResponse = getSmartPointUpdaterBCF().insertCustomSearch(customSearchReq);

			setResponse(customSearchResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error saving search", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Insert column filter.
	 * 
	 * @return the string
	 */
	public String insertColumnFilter()
	{
		try
		{
			ColumnFilterRequest columnFilterReq = getColumnFilterRequest();
			ColumnFilterResponse columnFilterResponse = getSettingsBCF().insertUserColumnFilter(columnFilterReq);

			setResponse(columnFilterResponse);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_INSERT_COLUMN_FILTER, e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Gets the light info.
	 * 
	 * @return the light info
	 */
	public String getLightInfo()
	{
		try
		{

			LightResponse resp = getSmartPointAccessorBCF().fetchLightById(getLightRequest());
			setResponse(resp);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_GETTING_LIGHT_INFO, e);
			}
		}

		return SUCCESS;

	}

	/**
	 * Update light status.
	 * 
	 * @return the string
	 */
	public String updateLightStatus()
	{

		ProcessResponse response = getSmartPointProcessorBCF().initiateUpdateLightStatus(getLightRequest());
		setResponse(response);

		return SUCCESS;
	}

	/**
	 * Fetch lights by group to map.
	 * 
	 * @return the string
	 */
	public String fetchLightsToMap()
	{

		try
		{

			InquiryLightResponse response = getSmartPointAccessorBCF().fetchSmartpointsToMap(getInquiryLightRequest());
			setResponse(response);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error getting lights by group to map", e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Gets the smart point accessor bcf.
	 * 
	 * @return the smart point accessor bcf
	 */
	public ISmartPointAccessorBCF getSmartPointAccessorBCF()
	{
		return smartPointAccessorBCF;
	}

	/**
	 * Sets the smart point accessor bcf.
	 * 
	 * @param smartPointAccessorBCF the new smart point accessor bcf
	 */
	public void setSmartPointAccessorBCF(ISmartPointAccessorBCF smartPointAccessorBCF)
	{
		this.smartPointAccessorBCF = smartPointAccessorBCF;
	}

	/**
	 * Gets the smart point processor bcf.
	 * 
	 * @return the smart point processor bcf
	 */
	public ISmartPointProcessorBCF getSmartPointProcessorBCF()
	{
		return smartPointProcessorBCF;
	}

	/**
	 * Sets the smart point processor bcf.
	 * 
	 * @param smartPointProcessorBCF the new smart point processor bcf
	 */
	public void setSmartPointProcessorBCF(ISmartPointProcessorBCF smartPointProcessorBCF)
	{
		this.smartPointProcessorBCF = smartPointProcessorBCF;
	}

	/**
	 * Gets the smart point updater bcf.
	 * 
	 * @return the smart point updater bcf
	 */
	public ISmartPointUpdaterBCF getSmartPointUpdaterBCF()
	{
		return smartPointUpdaterBCF;
	}

	/**
	 * Sets the smart point updater bcf.
	 * 
	 * @param smartPointUpdaterBCF the new smart point updater bcf
	 */
	public void setSmartPointUpdaterBCF(ISmartPointUpdaterBCF smartPointUpdaterBCF)
	{
		this.smartPointUpdaterBCF = smartPointUpdaterBCF;
	}

	/**
	 * Sets the tag bcf.
	 * 
	 * @param tagBCF the tagBCF to set
	 */
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
	}

	/**
	 * Gets the tag bcf.
	 * 
	 * @return the tagBCF
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	/**
	 * Gets the tag request.
	 * 
	 * @return the tagRequest
	 */
	public TagRequest getTagRequest()
	{
		return tagRequest;
	}

	/**
	 * Sets the tag request.
	 * 
	 * @param tagRequest the tagRequest to set
	 */
	public void setTagRequest(TagRequest tagRequest)
	{
		this.tagRequest = tagRequest;
	}

	/**
	 * Sets the force light status lrp id.
	 * 
	 * @param forceLightStatusLRPId the forceLightStatusLRPId to set
	 */
	public void setForceLightStatusLRPId(Integer forceLightStatusLRPId)
	{
		this.forceLightStatusLRPId = forceLightStatusLRPId;
	}

	/**
	 * Gets the force light status lrp id.
	 * 
	 * @return the forceLightStatusLRPId
	 */
	public Integer getForceLightStatusLRPId()
	{
		return forceLightStatusLRPId;
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
	 * Gets the input stream.
	 * 
	 * @return the input stream
	 */
	public InputStream getInputStream()
	{
		return inputStream;
	}

	/**
	 * Sets the input stream.
	 * 
	 * @param inputStream the new input stream
	 */
	public void setInputStream(InputStream inputStream)
	{
		this.inputStream = inputStream;
	}

	/**
	 * Sets the csv name.
	 * 
	 * @param csvName the new csv name
	 */
	public void setCsvName(String csvName)
	{
		this.csvName = csvName;
	}

	/**
	 * Gets the csv name.
	 * 
	 * @return the csv name
	 */
	public String getCsvName()
	{
		return csvName;
	}

	/**
	 * Gets the response.
	 * 
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * Sets the response.
	 * 
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

	/**
	 * Gets the column filter request.
	 * 
	 * @return the columnFilterRequest
	 */
	public ColumnFilterRequest getColumnFilterRequest()
	{
		return columnFilterRequest;
	}

	/**
	 * Sets the column filter request.
	 * 
	 * @param columnFilterRequest the columnFilterRequest to set
	 */
	public void setColumnFilterRequest(ColumnFilterRequest columnFilterRequest)
	{
		this.columnFilterRequest = columnFilterRequest;
	}

	/**
	 * Gets the custom search request.
	 * 
	 * @return the customSearchRequest
	 */
	public CustomSearchRequest getCustomSearchRequest()
	{
		return customSearchRequest;
	}

	/**
	 * Sets the custom search request.
	 * 
	 * @param customSearchRequest the customSearchRequest to set
	 */
	public void setCustomSearchRequest(CustomSearchRequest customSearchRequest)
	{
		this.customSearchRequest = customSearchRequest;
	}

	/**
	 * Gets the light request.
	 * 
	 * @return the lightRequest
	 */
	public LightRequest getLightRequest()
	{
		lightRequest.setUserContext(getUserContext());
		return lightRequest;
	}

	/**
	 * Sets the light request.
	 * 
	 * @param lightRequest the lightRequest to set
	 */
	public void setLightRequest(LightRequest lightRequest)
	{
		this.lightRequest = lightRequest;
	}

	/**
	 * Gets the settings bcf.
	 * 
	 * @return the settingsBCF
	 */
	public ISettingsBCF getSettingsBCF()
	{
		return settingsBCF;
	}

	/**
	 * Sets the settings bcf.
	 * 
	 * @param settingsBCF the settingsBCF to set
	 */
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * @return the inquiryLightRequest
	 */
	public InquiryLightRequest getInquiryLightRequest()
	{
		return inquiryLightRequest;
	}

	/**
	 * @param inquiryLightRequest the inquiryLightRequest to set
	 */
	public void setInquiryLightRequest(InquiryLightRequest inquiryLightRequest)
	{
		this.inquiryLightRequest = inquiryLightRequest;
	}

}
