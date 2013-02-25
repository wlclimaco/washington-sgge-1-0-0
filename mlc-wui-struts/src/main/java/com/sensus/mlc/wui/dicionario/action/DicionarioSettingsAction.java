package com.sensus.mlc.wui.dicionario.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.mlc.wui.base.action.SettingsAction;
import com.sensus.mlc.wui.smartpoint.action.SmartpointSettingsAction;



public class DicionarioSettingsAction extends SettingsAction
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1735439655152265099L;

	/**
	 * The url for the search callback.
	 */
	private String searchUrl;

	/** The search url lrp. */
	private String searchUrlLRP;

	/**
	 * The url of the include for the "addToGroup" action.
	 */
	private String addToGroupInclude;

	/**
	 * The callback url for the "addToGroup" action.
	 */
	private String addToGroupUrl;

	/** The abort process url. */
	private String abortProcessUrl;

	/**
	 * The url of the include for the "removeFromGroup" action.
	 */
	private String removeFromGroupInclude;

	/**
	 * The callback url for the "removeFromGroup" action.
	 */
	private String removeFromGroupUrl;

	/** The control light list. */
	private String controlLightList;

	/** The control light. */
	private String controlLight;

	/** The switch light url. */
	private String controlLightUrl;

	/**
	 * The url of the include for the "addToSchedule" action.
	 */
	private String addToScheduleEventInclude;

	/** The add to schedule offset include. */
	private String addToScheduleOffsetInclude;

	/**
	 * The callback url for the "addToSchedule" action.
	 */
	private String addToEventScheduleUrl;

	/** The add to offset schedule url. */
	private String addToOffsetScheduleUrl;

	/**
	 * The url of the include for the "removeFromSchedule" action.
	 */
	private String removeFromEventScheduleInclude;

	/**
	 * The callback url for the "removeFromSchedule" action.
	 */
	private String removeFromEventScheduleUrl;

	/** The remove from offset schedule url. */
	private String removeFromOffsetScheduleUrl;

	/**
	 * The callback url for the "addGroup" action.
	 */
	private String addGroupUrl;

	/** The update light status. */
	private String forceLightStatus;

	/** The check updated light result. */
	private String checkUpdatedLight;

	/** The delete file. */
	private String deleteFile;

	/** The remove schedule. */
	private String removeSchedule;

	/** The apply schedule. */
	private String applySchedule;

	/** The update list light protected. */
	private String updateListLightProtected;

	/** The clear smart points alerts. */
	private String clearSmartPointsAlerts;

	/** The update smart points status. */
	private String updateSmartPointsStatus;

	/** The edit light status. */
	private String editLightStatus;

	/** The save search include. */
	private String saveSearchInclude;

	/** The save search. */
	private String saveSearch;

	/** The processing summary. */
	private String processingSummary;

	/** The update Light Protected. */
	private String updateLightProtected;

	/** The reset values. */
	private String resetValues;

	/** The update light status. */
	private String updateLightStatus;

	/** The clear status. */
	private String clearStatus;

	/** The clear all. */
	private String clearAll;

	/** The insert smartpoint to tag. */
	private String insertSmartpointToTag;

	/** The remove smartpoint to tag. */
	private String removeSmartpointToTag;

	/** The add tag include. */
	private String addTagInclude;

	/** The add tag. */
	private String addTag;

	/** The remove tag include. */
	private String removeTagInclude;

	/** The remove tag. */
	private String removeTag;

	/** The update light property. */
	private String updateLightProperty;

	/** The generate file. */
	private String generateFile;

	/** The generate file history. */
	private String generateFileHistory;

	/** The save profile settings. */
	private String saveProfileSettings;

	/** The generate summary file csv. */
	private String generateSummaryFileCSV;

	/** The insert process. */
	private String insertProcess;

	/** The search smartpoint history. */
	private String searchSmartpointHistory;

	/** The edit light status include. */
	private String editLightStatusInclude;

	/** The insert tag. */
	private String insertTag;

	/** The get smartpoint info. */
	private String getSmartpointInfo;

	/** The alert details. */
	private String alertDetails;

	/**
	 * The logger for this class.
	 */
	private static transient Log LOG = LogFactory.getLog(SmartpointSettingsAction.class);

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
		setSearchUrl(getSettings().getProperty("dicionario.url.search"));
		setSearchUrlLRP(getSettings().getProperty("longrunningprocess.url.search"));
		setAbortProcessUrl(getSettings().getProperty("smartpoint.url.abortProcessUrl"));
		setAddToGroupInclude(getSettings().getProperty("smartpoint.include.addtogroup"));
		setAddToGroupUrl(getSettings().getProperty("smartpoint.url.addtogroup"));
		setAddToScheduleEventInclude(getSettings().getProperty("smartpoint.include.addtoeventschedule"));
		setAddToScheduleOffsetInclude(getSettings().getProperty("smartpoint.include.addtooffsetschedule"));
		setAddToEventScheduleUrl(getSettings().getProperty("smartpoint.url.addtoeventschedule"));
		setAddToOffsetScheduleUrl(getSettings().getProperty("smartpoint.url.addtooffsetschedule"));
		setRemoveFromEventScheduleInclude(getSettings().getProperty("smartpoint.include.removefromeventschedule"));
		setRemoveFromEventScheduleUrl(getSettings().getProperty("smartpoint.url.removefromeventschedule"));
		setRemoveFromOffsetScheduleUrl(getSettings().getProperty("smartpoint.url.removefromoffsetschedule"));
		setRemoveFromGroupInclude(getSettings().getProperty("smartpoint.include.removefromgroup"));
		setRemoveFromGroupUrl(getSettings().getProperty("smartpoint.url.removefromgroup"));
		setControlLight(getSettings().getProperty("smartpoint.include.controllight"));
		setControlLightList(getSettings().getProperty("smartpoint.include.controllightlist"));
		setControlLightUrl(getSettings().getProperty("smartpoint.url.controllight"));
		setGetSmartpointInfo(getSettings().getProperty("smartpoint.url.getSmartpointInfo"));
		setAddGroupUrl(getSettings().getProperty("group.url.addgroup"));
		setForceLightStatus(getSettings().getProperty("smartpoint.forceLightStatus"));
		setCheckUpdatedLight(getSettings().getProperty("smartpoint.checkUpdatedLight"));
		setDeleteFile(getSettings().getProperty("longrunningprocess.url.deleteFile"));
		setGenerateSummaryFileCSV(getSettings().getProperty("systemsettings.url.generateSummaryFileCSV"));
		setRemoveSchedule(getSettings().getProperty("smartpoint.removeSchedule"));
		setApplySchedule(getSettings().getProperty("smartpoint.applySchedule"));
		setUpdateListLightProtected(getSettings().getProperty("smartpoint.updateListLightProtected"));
		setSaveSearchInclude(getSettings().getProperty("smartpoint.include.saveSearch"));
		setSaveSearch(getSettings().getProperty("smartpoint.saveSearch"));
		setProcessingSummary(getSettings().getProperty("smartpoint.include.processingSummary"));
		setUpdateLightProtected(getSettings().getProperty("smartpoint.updateLightProtected"));
		setClearStatus(getSettings().getProperty("smartpoint.clearStatus"));
		setClearAll(getSettings().getProperty("smartpoint.clearAll"));
		setInsertSmartpointToTag(getSettings().getProperty("smartpoint.insertSmartpointToTag"));
		setRemoveSmartpointToTag(getSettings().getProperty("smartpoint.removeSmartpointToTag"));
		setAddTagInclude(getSettings().getProperty("smartpoint.include.addtag"));
		setAddTag(getSettings().getProperty("smartpoint.url.addTag"));
		setRemoveTagInclude(getSettings().getProperty("smartpoint.include.removetag"));
		setRemoveTag(getSettings().getProperty("smartpoint.url.removeTag"));
		setClearSmartPointsAlerts(getSettings().getProperty("smartpoint.url.clearSmartPointsAlerts"));
		setUpdateSmartPointsStatus(getSettings().getProperty("smartpoint.url.updateSmartPointsStatus"));
		setUpdateLightProperty(getSettings().getProperty("smartpoint.url.updateLightProperty"));
		setGenerateFile(getSettings().getProperty("smartpoint.url.generateFile"));
		setGenerateFileHistory(getSettings().getProperty("smartpoint.url.generateFileHistory"));
		setInsertProcess(getSettings().getProperty("smartpoint.url.insertProcess"));
		setSaveProfileSettings(getSettings().getProperty("profile.url.saveprofilesettings"));
		setSearchSmartpointHistory(getSettings().getProperty("smartpoint.url.searchSmartpointHistory"));
		setEditLightStatus(getSettings().getProperty("smartpoint.url.editLightStatus"));
		setResetValues(getSettings().getProperty("smartpoint.url.resetValues"));
		setUpdateLightStatus(getSettings().getProperty("smartpoint.url.updateLightStatus"));
		setEditLightStatusInclude(getSettings().getProperty("smartpoint.url.editLightStatusInclude"));
		setInsertTag(getSettings().getProperty("systemsettings.url.addTag"));
		setAlertDetails(getSettings().getProperty("smartpoint.include.alertDetails"));

		return SUCCESS;
	}

	/**
	 * Sets the search url.
	 * 
	 * @param searchUrlIn the new search url
	 */
	public void setSearchUrl(String searchUrlIn)
	{
		searchUrl = searchUrlIn;
	}

	/**
	 * Gets the search url.
	 * 
	 * @return the search url
	 */
	public String getSearchUrl()
	{
		return searchUrl;
	}

	/**
	 * Gets the removes the from group url.
	 * 
	 * @return the removeFromGroupInclude
	 */
	public String getRemoveFromGroupUrl()
	{
		return removeFromGroupUrl;
	}

	/**
	 * Sets the removes the from group url.
	 * 
	 * @param removeFromGroupIncludeIn the removeFromGroupInclude to set
	 */
	public void setRemoveFromGroupUrl(String removeFromGroupIncludeIn)
	{
		removeFromGroupUrl = removeFromGroupIncludeIn;
	}

	/**
	 * Gets the adds the to schedule include.
	 * 
	 * @return the addToScheduleInclude
	 */
	public String getAddToScheduleEventInclude()
	{
		return addToScheduleEventInclude;
	}

	/**
	 * Sets the adds the to schedule include.
	 * 
	 * @param addToScheduleEventIncludeIn the new adds the to schedule event include
	 */
	public void setAddToScheduleEventInclude(String addToScheduleEventIncludeIn)
	{
		addToScheduleEventInclude = addToScheduleEventIncludeIn;
	}

	/**
	 * Gets the adds the to schedule url.
	 * 
	 * @return the addToEventScheduleUrl
	 */
	public String getAddToEventScheduleUrl()
	{
		return addToEventScheduleUrl;
	}

	/**
	 * Sets the adds the to schedule url.
	 * 
	 * @param addToEventScheduleUrlIn the addToEventScheduleUrl to set
	 */
	public void setAddToEventScheduleUrl(String addToEventScheduleUrlIn)
	{
		addToEventScheduleUrl = addToEventScheduleUrlIn;
	}

	/**
	 * Gets the removes the from schedule include.
	 * 
	 * @return the removeFromEventScheduleInclude
	 */
	public String getRemoveFromEventScheduleInclude()
	{
		return removeFromEventScheduleInclude;
	}

	/**
	 * Sets the removes the from schedule include.
	 * 
	 * @param removeFromEventScheduleIncludeIn the removeFromEventScheduleInclude to set
	 */
	public void setRemoveFromEventScheduleInclude(String removeFromEventScheduleIncludeIn)
	{
		removeFromEventScheduleInclude = removeFromEventScheduleIncludeIn;
	}

	/**
	 * Gets the removes the from event schedule url.
	 * 
	 * @return the removes the from event schedule url
	 */
	public String getRemoveFromEventScheduleUrl()
	{
		return removeFromEventScheduleUrl;
	}

	/**
	 * Sets the removes the from event schedule url.
	 * 
	 * @param removeFromEventScheduleUrl the new removes the from event schedule url
	 */
	public void setRemoveFromEventScheduleUrl(String removeFromEventScheduleUrl)
	{
		this.removeFromEventScheduleUrl = removeFromEventScheduleUrl;
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
	 * @param addToGroupIncludeIn the addToGroupInclude to set
	 */
	public void setAddToGroupInclude(String addToGroupIncludeIn)
	{
		addToGroupInclude = addToGroupIncludeIn;
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
	 * @param addToGroupUrlIn the addToGroupUrl to set
	 */
	public void setAddToGroupUrl(String addToGroupUrlIn)
	{
		addToGroupUrl = addToGroupUrlIn;
	}

	/**
	 * Gets the adds the group url.
	 * 
	 * @return the addGroupUrl
	 */
	public String getAddGroupUrl()
	{
		return addGroupUrl;
	}

	/**
	 * Sets the adds the group url.
	 * 
	 * @param addGroupUrlIn the addGroupUrl to set
	 */
	public void setAddGroupUrl(String addGroupUrlIn)
	{
		addGroupUrl = addGroupUrlIn;
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
	 * @param removeFromGroupIncludeIn the removeFromGroupInclude to set
	 */
	public void setRemoveFromGroupInclude(String removeFromGroupIncludeIn)
	{
		removeFromGroupInclude = removeFromGroupIncludeIn;
	}

	/**
	 * Sets the check updated light result.
	 * 
	 * @param checkUpdatedLight the new check updated light result
	 */
	public void setCheckUpdatedLight(String checkUpdatedLight)
	{
		this.checkUpdatedLight = checkUpdatedLight;
	}

	/**
	 * Gets the check updated light result.
	 * 
	 * @return the check updated light result
	 */
	public String getCheckUpdatedLight()
	{
		return checkUpdatedLight;
	}

	/**
	 * Gets the search url lrp.
	 * 
	 * @return the search url lrp
	 */
	public String getSearchUrlLRP()
	{
		return searchUrlLRP;
	}

	/**
	 * Sets the search url lrp.
	 * 
	 * @param searchUrlLRP the new search url lrp
	 */
	public void setSearchUrlLRP(String searchUrlLRP)
	{
		this.searchUrlLRP = searchUrlLRP;
	}

	/**
	 * Sets the force light status result.
	 * 
	 * @param forceLightStatus the new force light status
	 */
	public void setForceLightStatus(String forceLightStatus)
	{
		this.forceLightStatus = forceLightStatus;
	}

	/**
	 * Gets the force light status result.
	 * 
	 * @return the force light status result
	 */
	public String getForceLightStatus()
	{
		return forceLightStatus;
	}

	/*
	 * Sets the adds the to schedule offset include.
	 * @param addToScheduleOffsetInclude the new adds the to schedule offset include
	 */
	/**
	 * Sets the adds the to schedule offset include.
	 * 
	 * @param addToScheduleOffsetInclude the new adds the to schedule offset include
	 */
	public void setAddToScheduleOffsetInclude(String addToScheduleOffsetInclude)
	{
		this.addToScheduleOffsetInclude = addToScheduleOffsetInclude;
	}

	/**
	 * Gets the adds the to schedule offset include.
	 * 
	 * @return the adds the to schedule offset include
	 */
	public String getAddToScheduleOffsetInclude()
	{
		return addToScheduleOffsetInclude;
	}

	/**
	 * Sets the adds the to offset schedule url.
	 * 
	 * @param addToOffsetScheduleUrl the new adds the to offset schedule url
	 */
	public void setAddToOffsetScheduleUrl(String addToOffsetScheduleUrl)
	{
		this.addToOffsetScheduleUrl = addToOffsetScheduleUrl;
	}

	/**
	 * Gets the adds the to offset schedule url.
	 * 
	 * @return the adds the to offset schedule url
	 */
	public String getAddToOffsetScheduleUrl()
	{
		return addToOffsetScheduleUrl;
	}

	/**
	 * Sets the removes the from offset schedule url.
	 * 
	 * @param removeFromOffsetScheduleUrl the new removes the from offset schedule url
	 */
	public void setRemoveFromOffsetScheduleUrl(String removeFromOffsetScheduleUrl)
	{
		this.removeFromOffsetScheduleUrl = removeFromOffsetScheduleUrl;
	}

	/**
	 * Gets the removes the from offset schedule url.
	 * 
	 * @return the removes the from offset schedule url
	 */
	public String getRemoveFromOffsetScheduleUrl()
	{
		return removeFromOffsetScheduleUrl;
	}

	/**
	 * Sets the removes the schedule.
	 * 
	 * @param removeSchedule the removeSchedule to set
	 */
	public void setRemoveSchedule(String removeSchedule)
	{
		this.removeSchedule = removeSchedule;
	}

	/**
	 * Gets the removes the schedule.
	 * 
	 * @return the removeSchedule
	 */
	public String getRemoveSchedule()
	{
		return removeSchedule;
	}

	/**
	 * Sets the apply schedule.
	 * 
	 * @param applySchedule the applySchedule to set
	 */
	public void setApplySchedule(String applySchedule)
	{
		this.applySchedule = applySchedule;
	}

	/**
	 * Gets the apply schedule.
	 * 
	 * @return the applySchedule
	 */
	public String getApplySchedule()
	{
		return applySchedule;
	}

	/**
	 * Gets the update list light protected.
	 * 
	 * @return the update list light protected
	 */
	public String getUpdateListLightProtected()
	{
		return updateListLightProtected;
	}

	/**
	 * Sets the update list light protected.
	 * 
	 * @param updateListLightProtected the new update list light protected
	 */
	public void setUpdateListLightProtected(String updateListLightProtected)
	{
		this.updateListLightProtected = updateListLightProtected;
	}

	/**
	 * Sets the processing summary.
	 * 
	 * @param processingSummary the processingSummary to set
	 */
	public void setProcessingSummary(String processingSummary)
	{
		this.processingSummary = processingSummary;
	}

	/**
	 * Gets the processing summary.
	 * 
	 * @return the processingSummary
	 */
	public String getProcessingSummary()
	{
		return processingSummary;
	}

	/**
	 * Sets the clear status.
	 * 
	 * @param clearStatus the clearStatus to set
	 */
	public void setClearStatus(String clearStatus)
	{
		this.clearStatus = clearStatus;
	}

	/**
	 * Gets the clear status.
	 * 
	 * @return the clearStatus
	 */
	public String getClearStatus()
	{
		return clearStatus;
	}

	/*
	 * Sets the clear all.
	 * @param clearAll the clearAll to set
	 */
	/**
	 * Sets the clear all.
	 * 
	 * @param clearAll the new clear all
	 */
	public void setClearAll(String clearAll)
	{
		this.clearAll = clearAll;
	}

	/**
	 * Gets the clear all.
	 * 
	 * @return the clearAll
	 */
	public String getClearAll()
	{
		return clearAll;
	}

	/**
	 * Sets the insert smartpoint to tag.
	 * 
	 * @param insertSmartpointToTag the insertSmartpointToTag to set
	 */
	public void setInsertSmartpointToTag(String insertSmartpointToTag)
	{
		this.insertSmartpointToTag = insertSmartpointToTag;
	}

	/**
	 * Gets the insert smartpoint to tag.
	 * 
	 * @return the insertSmartpointToTag
	 */
	public String getInsertSmartpointToTag()
	{
		return insertSmartpointToTag;
	}

	/**
	 * Sets the update light protected.
	 * 
	 * @param updateLightProtected the updateLightProtected to set
	 */
	public void setUpdateLightProtected(String updateLightProtected)
	{
		this.updateLightProtected = updateLightProtected;
	}

	/**
	 * Gets the update light protected.
	 * 
	 * @return the updateLightProtected
	 */
	public String getUpdateLightProtected()
	{
		return updateLightProtected;
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
	 * Gets the removes the tag.
	 * 
	 * @return the removeTag
	 */
	public String getRemoveTag()
	{
		return removeTag;
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
	 * Gets the adds the tag.
	 * 
	 * @return the addTag
	 */
	public String getAddTag()
	{
		return addTag;
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
	 * Gets the adds the tag include.
	 * 
	 * @return the addTagInclude
	 */
	public String getAddTagInclude()
	{
		return addTagInclude;
	}

	/**
	 * Gets the removes the tag include.
	 * 
	 * @return the removes the tag include
	 */
	public String getRemoveTagInclude()
	{
		return removeTagInclude;
	}

	/**
	 * Sets the removes the tag include.
	 * 
	 * @param removeTagInclude the new removes the tag include
	 */
	public void setRemoveTagInclude(String removeTagInclude)
	{
		this.removeTagInclude = removeTagInclude;
	}

	/**
	 * Sets the removes the smartpoint to tag.
	 * 
	 * @param removeSmartpointToTag the new removes the smartpoint to tag
	 */
	public void setRemoveSmartpointToTag(String removeSmartpointToTag)
	{
		this.removeSmartpointToTag = removeSmartpointToTag;
	}

	/**
	 * Gets the removes the smartpoint to tag.
	 * 
	 * @return the removes the smartpoint to tag
	 */
	public String getRemoveSmartpointToTag()
	{
		return removeSmartpointToTag;
	}

	/**
	 * Gets the control light list.
	 * 
	 * @return the control light list
	 */
	public String getControlLightList()
	{
		return controlLightList;
	}

	/**
	 * Sets the control light list.
	 * 
	 * @param controlLightList the new control light list
	 */
	public void setControlLightList(String controlLightList)
	{
		this.controlLightList = controlLightList;
	}

	/**
	 * Gets the control light.
	 * 
	 * @return the control light
	 */
	public String getControlLight()
	{
		return controlLight;
	}

	/**
	 * Sets the control light.
	 * 
	 * @param controlLight the new control light
	 */
	public void setControlLight(String controlLight)
	{
		this.controlLight = controlLight;
	}

	/**
	 * Sets the clear smart points alerts.
	 * 
	 * @param clearSmartPointsAlerts the new clear smart points alerts
	 */
	public void setClearSmartPointsAlerts(String clearSmartPointsAlerts)
	{
		this.clearSmartPointsAlerts = clearSmartPointsAlerts;
	}

	/**
	 * Gets the clear smart points alerts.
	 * 
	 * @return the clear smart points alerts
	 */
	public String getClearSmartPointsAlerts()
	{
		return clearSmartPointsAlerts;
	}

	/**
	 * Sets the update smart points status.
	 * 
	 * @param updateSmartPointsStatus the new update smart points status
	 */
	public void setUpdateSmartPointsStatus(String updateSmartPointsStatus)
	{
		this.updateSmartPointsStatus = updateSmartPointsStatus;
	}

	/**
	 * Gets the update smart points status.
	 * 
	 * @return the update smart points status
	 */
	public String getUpdateSmartPointsStatus()
	{
		return updateSmartPointsStatus;
	}

	/**
	 * Gets the save search.
	 * 
	 * @return the save search
	 */
	public String getSaveSearch()
	{
		return saveSearch;
	}

	/**
	 * Sets the save search.
	 * 
	 * @param saveSearch the new save search
	 */
	public void setSaveSearch(String saveSearch)
	{
		this.saveSearch = saveSearch;
	}

	/**
	 * Gets the save search include.
	 * 
	 * @return the save search include
	 */
	public String getSaveSearchInclude()
	{
		return saveSearchInclude;
	}

	/**
	 * Sets the save search include.
	 * 
	 * @param saveSearchInclude the new save search include
	 */
	public void setSaveSearchInclude(String saveSearchInclude)
	{
		this.saveSearchInclude = saveSearchInclude;
	}

	/**
	 * Sets the update light property.
	 * 
	 * @param updateLightProperty the updateLightProperty to set
	 */
	public void setUpdateLightProperty(String updateLightProperty)
	{
		this.updateLightProperty = updateLightProperty;
	}

	/**
	 * Gets the update light property.
	 * 
	 * @return the updateLightProperty
	 */
	public String getUpdateLightProperty()
	{
		return updateLightProperty;
	}

	/**
	 * Sets the generate file.
	 * 
	 * @param generateFile the new generate file
	 */
	public void setGenerateFile(String generateFile)
	{
		this.generateFile = generateFile;
	}

	/**
	 * Gets the generate file.
	 * 
	 * @return the generate file
	 */
	public String getGenerateFile()
	{
		return generateFile;
	}

	/**
	 * Sets the save profile settings.
	 * 
	 * @param saveProfileSettings the saveProfileSettings to set
	 */
	public void setSaveProfileSettings(String saveProfileSettings)
	{
		this.saveProfileSettings = saveProfileSettings;
	}

	/**
	 * Gets the save profile settings.
	 * 
	 * @return the saveProfileSettings
	 */
	public String getSaveProfileSettings()
	{
		return saveProfileSettings;
	}

	/**
	 * Sets the delete file.
	 * 
	 * @param deleteFile the new delete file
	 */
	public void setDeleteFile(String deleteFile)
	{
		this.deleteFile = deleteFile;
	}

	/**
	 * Gets the delete file.
	 * 
	 * @return the delete file
	 */
	public String getDeleteFile()
	{
		return deleteFile;
	}

	/**
	 * Sets the generate summary file csv.
	 * 
	 * @param generateSummaryFileCSV the new generate summary file csv
	 */
	public void setGenerateSummaryFileCSV(String generateSummaryFileCSV)
	{
		this.generateSummaryFileCSV = generateSummaryFileCSV;
	}

	/**
	 * Gets the generate summary file csv.
	 * 
	 * @return the generate summary file csv
	 */
	public String getGenerateSummaryFileCSV()
	{
		return generateSummaryFileCSV;
	}

	/**
	 * Gets the search smartpoint history.
	 * 
	 * @return the searchSmartpointHistory
	 */
	public String getSearchSmartpointHistory()
	{
		return searchSmartpointHistory;
	}

	/**
	 * Sets the search smartpoint history.
	 * 
	 * @param searchSmartpointHistory the searchSmartpointHistory to set
	 */
	public void setSearchSmartpointHistory(String searchSmartpointHistory)
	{
		this.searchSmartpointHistory = searchSmartpointHistory;
	}

	/**
	 * Gets the edits the light status.
	 * 
	 * @return the editLightStatus
	 */
	public String getEditLightStatus()
	{
		return editLightStatus;
	}

	/**
	 * Sets the edits the light status.
	 * 
	 * @param editLightStatus the editLightStatus to set
	 */
	public void setEditLightStatus(String editLightStatus)
	{
		this.editLightStatus = editLightStatus;
	}

	/**
	 * Gets the reset values.
	 * 
	 * @return the resetValues
	 */
	public String getResetValues()
	{
		return resetValues;
	}

	/**
	 * Sets the reset values.
	 * 
	 * @param resetValues the resetValues to set
	 */
	public void setResetValues(String resetValues)
	{
		this.resetValues = resetValues;
	}

	/**
	 * Gets the edits the light status include.
	 * 
	 * @return the editLightStatusInclude
	 */
	public String getEditLightStatusInclude()
	{
		return editLightStatusInclude;
	}

	/**
	 * Sets the edits the light status include.
	 * 
	 * @param editLightStatusInclude the editLightStatusInclude to set
	 */
	public void setEditLightStatusInclude(String editLightStatusInclude)
	{
		this.editLightStatusInclude = editLightStatusInclude;
	}

	/**
	 * @return the updateLightStatus
	 */
	public String getUpdateLightStatus()
	{
		return updateLightStatus;
	}

	/**
	 * @param updateLightStatus the updateLightStatus to set
	 */
	public void setUpdateLightStatus(String updateLightStatus)
	{
		this.updateLightStatus = updateLightStatus;
	}

	/**
	 * Gets the abort process url.
	 * 
	 * @return the abort process url
	 */
	public String getAbortProcessUrl()
	{
		return abortProcessUrl;
	}

	/**
	 * Sets the abort process url.
	 * 
	 * @param abortProcessUrl the new abort process url
	 */
	public void setAbortProcessUrl(String abortProcessUrl)
	{
		this.abortProcessUrl = abortProcessUrl;
	}

	/**
	 * Gets the insert process.
	 * 
	 * @return the insertProcess
	 */
	public String getInsertProcess()
	{
		return insertProcess;
	}

	/**
	 * Sets the insert process.
	 * 
	 * @param insertProcess the insertProcess to set
	 */
	public void setInsertProcess(String insertProcess)
	{
		this.insertProcess = insertProcess;
	}

	/**
	 * Gets the insert tag.
	 * 
	 * @return the insertTag
	 */
	public String getInsertTag()
	{
		return insertTag;
	}

	/**
	 * Sets the insert tag.
	 * 
	 * @param insertTag the insertTag to set
	 */
	public void setInsertTag(String insertTag)
	{
		this.insertTag = insertTag;
	}

	/**
	 * @return the getSmartpointInfo
	 */
	public String getGetSmartpointInfo()
	{
		return getSmartpointInfo;
	}

	/**
	 * @param getSmartpointInfo the getSmartpointInfo to set
	 */
	public void setGetSmartpointInfo(String getSmartpointInfo)
	{
		this.getSmartpointInfo = getSmartpointInfo;
	}

	/**
	 * @return the controlLightUrl
	 */
	public String getControlLightUrl()
	{
		return controlLightUrl;
	}

	/**
	 * @param controlLightUrl the controlLightUrl to set
	 */
	public void setControlLightUrl(String controlLightUrl)
	{
		this.controlLightUrl = controlLightUrl;
	}

	/**
	 * @return the alertDetails
	 */
	public String getAlertDetails()
	{
		return alertDetails;
	}

	/**
	 * @param alertDetails the alertDetails to set
	 */
	public void setAlertDetails(String alertDetails)
	{
		this.alertDetails = alertDetails;
	}

	/**
	 * @return the generateFileHistory
	 */
	public String getGenerateFileHistory()
	{
		return generateFileHistory;
	}

	/**
	 * @param generateFileHistory the generateFileHistory to set
	 */
	public void setGenerateFileHistory(String generateFileHistory)
	{
		this.generateFileHistory = generateFileHistory;
	}
}
