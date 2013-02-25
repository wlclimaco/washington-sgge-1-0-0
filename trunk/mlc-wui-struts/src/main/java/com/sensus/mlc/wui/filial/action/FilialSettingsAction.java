package com.sensus.mlc.wui.filial.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.mlc.wui.base.action.SettingsAction;



/**
 * Struts Action for injecting SmartPoint-related application properties into JavaScript via Freemarker.
 * 
 * @author Raphael Constantino
 * 
 */

public class FilialSettingsAction extends SettingsAction
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1735439655152265099L;

	/** ************************ CONSTANTS ***********************. */

	/** The logger for this class. */
	private static transient Log LOG = LogFactory.getLog(FilialSettingsAction.class);

	/** ************************ PROPERTIES ***********************. */

	/** The callback url for the "addToGroup" action. */
	private String addToGroupInclude;

	/** The callback url for the "addToGroup" action. */
	private String addToGroupUrl;

	/** The add to schedule include. */
	private String addToScheduleInclude;

	/** The add to schedule url. */
	private String addToScheduleUrl;

	/** The add tag. */
	private String addTag;

	/** The add tag include. */
	private String addTagInclude;

	/** The generate file csv. */
	private String generateFileCSV;

	/** The insert csv process. */
	private String insertCSVProcess;

	/** The openDialogSearchSaveAction. */
	private String openDialogSearchSaveAction;

	/** The url of the include for the "removeFromGroup" action. */
	private String removeFromGroupInclude;

	/** The callback url for the "removeFromGroup" action. */
	private String removeFromGroupUrl;

	/** The remove tag include. */
	private String removeTagInclude;

	/** The remove tag. */
	private String removeTag;

	/** The remove from schedule include. */
	private String removeFromScheduleInclude;

	/** The remove from schedule url. */
	private String removeFromScheduleUrl;

	/** The search save. */
	private String searchSave;

	/** The search field. */
	private String searchField;

	/** The update smart points status. */
	private String updateSmartPointsStatus;

	private String propertyActionType;

	/**
	 * Reads the settings from the application settings and populates the response parameters.
	 * 
	 * @return always "SUCCESS"
	 */
	public String settings()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Loading Smartpoint Page Settings");
		}
		super.initSettings();

		setAddToGroupInclude(getSettings().getProperty("smartpoint.include.addtogroup"));
		setRemoveFromGroupInclude(getSettings().getProperty("smartpoint.include.removefromgroup"));

		setAddToGroupUrl(getSettings().getProperty("smartpoint.url.addtogroup"));
		setRemoveFromGroupUrl(getSettings().getProperty("smartpoint.url.removefromgroup"));

		setAddTagInclude(getSettings().getProperty("smartpoint.include.addtotag"));
		setRemoveTagInclude(getSettings().getProperty("smartpoint.include.removefromtag"));

		setAddTag(getSettings().getProperty("smartpoint.url.addfromtag"));
		setRemoveTag(getSettings().getProperty("smartpoint.url.removetotag"));

		setOpenDialogSearchSaveAction(getSettings().getProperty("smartpoint.include.saveCustomSearch"));

		setSearchSave(getSettings().getProperty("smartpoint.url.saveSearch"));

		setAddToScheduleInclude(getSettings().getProperty("smartpoint.include.addtoeventschedule"));
		setRemoveFromScheduleInclude(getSettings().getProperty("smartpoint.include.removefromeventschedule"));

		setAddToScheduleUrl(getSettings().getProperty("smartpoint.url.addtoeventschedule"));
		setRemoveFromScheduleUrl(getSettings().getProperty("smartpoint.url.removefromeventschedule"));

		setUpdateSmartPointsStatus(getSettings().getProperty("smartpoint.url.updateSmartPointsStatus"));

		setGenerateFileCSV(getSettings().getProperty("smartpoint.url.generateFileCSV"));
		setInsertCSVProcess(getSettings().getProperty("smartpoint.url.insertCSVProcess"));

		setPropertyActionType(getSettings().getProperty("smartpoint.url.propertyActionType"));

		return SUCCESS;
	}

	/**
	 * Gets the adds the to group include.
	 * 
	 * @return the addToGroupInclude
	 */
	public String getAddToGroupInclude()
	{
		return addToGroupInclude;
	}

	/**
	 * Sets the adds the to group include.
	 * 
	 * @param addToGroupInclude the addToGroupInclude to set
	 */
	public void setAddToGroupInclude(String addToGroupInclude)
	{
		this.addToGroupInclude = addToGroupInclude;
	}

	/**
	 * Gets the removes the from group include.
	 * 
	 * @return the removeFromGroupInclude
	 */
	public String getRemoveFromGroupInclude()
	{
		return removeFromGroupInclude;
	}

	/**
	 * Sets the removes the from group include.
	 * 
	 * @param removeFromGroupInclude the removeFromGroupInclude to set
	 */
	public void setRemoveFromGroupInclude(String removeFromGroupInclude)
	{
		this.removeFromGroupInclude = removeFromGroupInclude;
	}

	/**
	 * Gets the adds the to group url.
	 * 
	 * @return the addToGroupUrl
	 */
	public String getAddToGroupUrl()
	{
		return addToGroupUrl;
	}

	/**
	 * Sets the adds the to group url.
	 * 
	 * @param addToGroupUrl the addToGroupUrl to set
	 */
	public void setAddToGroupUrl(String addToGroupUrl)
	{
		this.addToGroupUrl = addToGroupUrl;
	}

	/**
	 * Gets the removes the from group url.
	 * 
	 * @return the removeFromGroupUrl
	 */
	public String getRemoveFromGroupUrl()
	{
		return removeFromGroupUrl;
	}

	/**
	 * Sets the removes the from group url.
	 * 
	 * @param removeFromGroupUrl the removeFromGroupUrl to set
	 */
	public void setRemoveFromGroupUrl(String removeFromGroupUrl)
	{
		this.removeFromGroupUrl = removeFromGroupUrl;
	}

	/**
	 * Gets the adds the tag include.
	 * 
	 * @return the addTagInclude
	 */
	public String getAddTagInclude()
	{
		return addTagInclude;
	}

	/**
	 * Sets the adds the tag include.
	 * 
	 * @param addTagInclude the addTagInclude to set
	 */
	public void setAddTagInclude(String addTagInclude)
	{
		this.addTagInclude = addTagInclude;
	}

	/**
	 * Gets the removes the tag include.
	 * 
	 * @return the removeTagInclude
	 */
	public String getRemoveTagInclude()
	{
		return removeTagInclude;
	}

	/**
	 * Sets the removes the tag include.
	 * 
	 * @param removeTagInclude the removeTagInclude to set
	 */
	public void setRemoveTagInclude(String removeTagInclude)
	{
		this.removeTagInclude = removeTagInclude;
	}

	/**
	 * Gets the adds the tag.
	 * 
	 * @return the addTag
	 */
	public String getAddTag()
	{
		return addTag;
	}

	/**
	 * Sets the adds the tag.
	 * 
	 * @param addTag the addTag to set
	 */
	public void setAddTag(String addTag)
	{
		this.addTag = addTag;
	}

	/**
	 * Gets the removes the tag.
	 * 
	 * @return the removeTag
	 */
	public String getRemoveTag()
	{
		return removeTag;
	}

	/**
	 * Sets the removes the tag.
	 * 
	 * @param removeTag the removeTag to set
	 */
	public void setRemoveTag(String removeTag)
	{
		this.removeTag = removeTag;
	}

	/**
	 * Gets the open dialog search save action.
	 * 
	 * @return the openDialogSearchSaveAction
	 */
	public String getOpenDialogSearchSaveAction()
	{
		return openDialogSearchSaveAction;
	}

	/**
	 * Sets the open dialog search save action.
	 * 
	 * @param openDialogSearchSaveAction the openDialogSearchSaveAction to set
	 */
	public void setOpenDialogSearchSaveAction(String openDialogSearchSaveAction)
	{
		this.openDialogSearchSaveAction = openDialogSearchSaveAction;
	}

	/**
	 * Gets the search save.
	 * 
	 * @return the searchSave
	 */
	public String getSearchSave()
	{
		return searchSave;
	}

	/**
	 * Sets the search save.
	 * 
	 * @param searchSave the searchSave to set
	 */
	public void setSearchSave(String searchSave)
	{
		this.searchSave = searchSave;
	}

	/**
	 * Gets the search field.
	 * 
	 * @return the searchField
	 */
	public String getSearchField()
	{
		return searchField;
	}

	/**
	 * Sets the search field.
	 * 
	 * @param searchField the searchField to set
	 */
	public void setSearchField(String searchField)
	{
		this.searchField = searchField;
	}

	/**
	 * Gets the adds the to schedule include.
	 * 
	 * @return the addToScheduleInclude
	 */
	public String getAddToScheduleInclude()
	{
		return addToScheduleInclude;
	}

	/**
	 * Sets the adds the to schedule include.
	 * 
	 * @param addToScheduleInclude the addToScheduleInclude to set
	 */
	public void setAddToScheduleInclude(String addToScheduleInclude)
	{
		this.addToScheduleInclude = addToScheduleInclude;
	}

	/**
	 * Gets the removes the from schedule include.
	 * 
	 * @return the removeFromScheduleInclude
	 */
	public String getRemoveFromScheduleInclude()
	{
		return removeFromScheduleInclude;
	}

	/**
	 * Sets the removes the from schedule include.
	 * 
	 * @param removeFromScheduleInclude the removeFromScheduleInclude to set
	 */
	public void setRemoveFromScheduleInclude(String removeFromScheduleInclude)
	{
		this.removeFromScheduleInclude = removeFromScheduleInclude;
	}

	/**
	 * Gets the adds the to schedule url.
	 * 
	 * @return the addToScheduleUrl
	 */
	public String getAddToScheduleUrl()
	{
		return addToScheduleUrl;
	}

	/**
	 * Sets the adds the to schedule url.
	 * 
	 * @param addToScheduleUrl the addToScheduleUrl to set
	 */
	public void setAddToScheduleUrl(String addToScheduleUrl)
	{
		this.addToScheduleUrl = addToScheduleUrl;
	}

	/**
	 * Gets the removes the from schedule url.
	 * 
	 * @return the removeFromScheduleUrl
	 */
	public String getRemoveFromScheduleUrl()
	{
		return removeFromScheduleUrl;
	}

	/**
	 * Sets the removes the from schedule url.
	 * 
	 * @param removeFromScheduleUrl the removeFromScheduleUrl to set
	 */
	public void setRemoveFromScheduleUrl(String removeFromScheduleUrl)
	{
		this.removeFromScheduleUrl = removeFromScheduleUrl;
	}

	/**
	 * Gets the update smart points status.
	 * 
	 * @return the updateSmartPointsStatus
	 */
	public String getUpdateSmartPointsStatus()
	{
		return updateSmartPointsStatus;
	}

	/**
	 * Sets the update smart points status.
	 * 
	 * @param updateSmartPointsStatus the updateSmartPointsStatus to set
	 */
	public void setUpdateSmartPointsStatus(String updateSmartPointsStatus)
	{
		this.updateSmartPointsStatus = updateSmartPointsStatus;
	}

	/**
	 * Gets the generate file csv.
	 * 
	 * @return the generateFileCSV
	 */
	public String getGenerateFileCSV()
	{
		return generateFileCSV;
	}

	/**
	 * Sets the generate file csv.
	 * 
	 * @param generateFileCSV the generateFileCSV to set
	 */
	public void setGenerateFileCSV(String generateFileCSV)
	{
		this.generateFileCSV = generateFileCSV;
	}

	/**
	 * Gets the insert csv process.
	 * 
	 * @return the insert csv process
	 */
	public String getInsertCSVProcess()
	{
		return insertCSVProcess;
	}

	/**
	 * Sets the insert csv process.
	 * 
	 * @param insertCSVProcess the new insert csv process
	 */
	public void setInsertCSVProcess(String insertCSVProcess)
	{
		this.insertCSVProcess = insertCSVProcess;
	}

	/**
	 * @return the propertyActionType
	 */
	public String getPropertyActionType()
	{
		return propertyActionType;
	}

	/**
	 * @param propertyActionType the propertyActionType to set
	 */
	public void setPropertyActionType(String propertyActionType)
	{
		this.propertyActionType = propertyActionType;
	}

}