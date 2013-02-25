package com.sensus.mlc.wui.group.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.mlc.wui.base.action.SettingsAction;

/**
 * Loads group-related application settings into a JavaScript file via a Freemarker Template.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class GroupSettingsAction extends SettingsAction
{

	/** CONSTANTS **/

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7969907294732111538L;

	/**
	 * The logger for this action.
	 */
	private static transient Log LOG = LogFactory.getLog(GroupSettingsAction.class);

	/**
	 * Url for link to Main Group page.
	 */
	private String groupUrl;

	/**
	 * The URL for group search callbacks.
	 */
	private String searchUrl;

	/**
	 * Url for link to main SmartPoint page.
	 */
	private String smartpointUrl;

	/** The create group url. */
	private String createGroupUrl;

	/** The update group url. */
	private String updateGroupUrl;

	/** The initiate delete group include. */
	private String initiateDeleteGroupInclude;

	/** The initiate delete group url. */
	private String initiateDeleteGroupUrl;

	/** The delete group url. */
	private String deleteGroupUrl;

	/** The search url lrp. */
	private String searchUrlLRP;

	/** The long running process dialog. */
	private String longRunningProcessDialog;

	/** The long running process recent. */
	private String longRunningProcessRecent;

	/** The Long running process dialog remove. */
	private String longRunningProcessDialogRemove;

	/** The Long running process dialog abort. */
	private String longRunningProcessDialogAbort;

	/** The Long running process remove. */
	private String longRunningProcessRemove;

	/** The Long running process abort. */
	private String longRunningProcessAbort;

	/** The long running process retry unreachable. */
	private String longRunningProcessRetryUnreachable;

	/** The check long running process. */
	private String checkLongRunningProcess;

	/** The remove all longrunningprocess. */
	private String longRunningProcessRemoveAll;

	/** The check rni. */
	private String checkRni;

	/** The save profile settings. */
	private String saveProfileSettings;

	/** The delete file. */
	private String deleteFile;

	/** The generate summary file csv. */
	private String generateSummaryFileCSV;

	/**
	 * Load the settings from the application settings.
	 * 
	 * @return always "SUCCESS"
	 */
	public String settings()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Loading Group Page Settings");
		}
		super.initSettings();
		setGroupUrl(getSettings().getProperty("group.url.group"));
		setSearchUrl(getSettings().getProperty("group.url.search"));
		setSmartpointUrl(getSettings().getProperty("schedule.url.smartpoint"));
		setCreateGroupUrl(getSettings().getProperty("group.url.creategroup"));
		setUpdateGroupUrl(getSettings().getProperty("group.url.updategroup"));
		setInitiateDeleteGroupInclude(getSettings().getProperty("group.include.initiatedeletegroup"));
		setInitiateDeleteGroupUrl(getSettings().getProperty("group.url.initiatedeletegroup"));
		setDeleteGroupUrl(getSettings().getProperty("group.url.deletegroup"));
		setSearchUrlLRP(getSettings().getProperty("longrunningprocess.url.search"));
		setLongRunningProcessDialog(getSettings().getProperty("smartpoint.include.longRunningProcessDialog"));
		setLongRunningProcessDialogRemove(getSettings().getProperty(
				"longrunningprocess.include.longRunningProcessRemove"));
		setLongRunningProcessDialogAbort(getSettings()
				.getProperty("longrunningprocess.include.longRunningProcessAbort"));
		setLongRunningProcessRemove(getSettings().getProperty("longrunningprocess.longRunningProcessRemove"));
		setLongRunningProcessAbort(getSettings().getProperty("longrunningprocess.longRunningProcessAbort"));
		setLongRunningProcessRecent(getSettings().getProperty("smartpoint.include.longRunningProcessRecent"));
		setCheckLongRunningProcess(getSettings().getProperty("longrunningprocess.url.checkLongRunningProcess"));
		setLongRunningProcessRetryUnreachable(getSettings().getProperty(
				"longrunningprocess.url.retryUnreachable"));
		setLongRunningProcessRemoveAll(getSettings().getProperty("longrunningprocess.url.longRunningProcessRemoveAll"));
		setCheckRni(getSettings().getProperty("longrunningprocess.url.checkRni"));
		setSaveProfileSettings(getSettings().getProperty("profile.url.saveprofilesettings"));
		setDeleteFile(getSettings().getProperty("longrunningprocess.url.deleteFile"));
		setGenerateSummaryFileCSV(getSettings().getProperty("systemsettings.url.generateSummaryFileCSV"));

		return SUCCESS;
	}

	/**
	 * Gets the creates the group url.
	 * 
	 * @return the creates the group url
	 */
	public String getCreateGroupUrl()
	{
		return createGroupUrl;
	}

	/**
	 * Sets the creates the group url.
	 * 
	 * @param createGroupUrl the new creates the group url
	 */
	public void setCreateGroupUrl(String createGroupUrl)
	{
		this.createGroupUrl = createGroupUrl;
	}

	/**
	 * Gets the update group url.
	 * 
	 * @return the update group url
	 */
	public String getUpdateGroupUrl()
	{
		return updateGroupUrl;
	}

	/**
	 * Sets the update group url.
	 * 
	 * @param updateGroupUrl the new update group url
	 */
	public void setUpdateGroupUrl(String updateGroupUrl)
	{
		this.updateGroupUrl = updateGroupUrl;
	}

	/**
	 * Gets the group url.
	 * 
	 * @return the group url
	 */
	public String getGroupUrl()
	{
		return groupUrl;
	}

	/**
	 * Sets the group url.
	 * 
	 * @param groupUrl the new group url
	 */
	public void setGroupUrl(String groupUrl)
	{
		this.groupUrl = groupUrl;
	}

	/**
	 * Gets the smartpoint url.
	 * 
	 * @return the smartpoint url
	 */
	public String getSmartpointUrl()
	{
		return smartpointUrl;
	}

	/**
	 * Sets the smartpoint url.
	 * 
	 * @param smartpointUrl the new smartpoint url
	 */
	public void setSmartpointUrl(String smartpointUrl)
	{
		this.smartpointUrl = smartpointUrl;
	}

	/**
	 * Gets the delete group url.
	 * 
	 * @return the delete group url
	 */
	public String getDeleteGroupUrl()
	{
		return deleteGroupUrl;
	}

	/**
	 * Sets the delete group url.
	 * 
	 * @param deleteGroupUrl the new delete group url
	 */
	public void setDeleteGroupUrl(String deleteGroupUrl)
	{
		this.deleteGroupUrl = deleteGroupUrl;
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
	 * Gets the long running process dialog remove.
	 * 
	 * @return the long running process dialog remove
	 */
	public String getLongRunningProcessDialogRemove()
	{
		return longRunningProcessDialogRemove;
	}

	/**
	 * Sets the long running process dialog remove.
	 * 
	 * @param longRunningProcessDialogRemove the new long running process dialog remove
	 */
	public void setLongRunningProcessDialogRemove(String longRunningProcessDialogRemove)
	{
		this.longRunningProcessDialogRemove = longRunningProcessDialogRemove;
	}

	/**
	 * Gets the long running process dialog abort.
	 * 
	 * @return the long running process dialog abort
	 */
	public String getLongRunningProcessDialogAbort()
	{
		return longRunningProcessDialogAbort;
	}

	/**
	 * Sets the long running process dialog abort.
	 * 
	 * @param longRunningProcessDialogAbort the new long running process dialog abort
	 */
	public void setLongRunningProcessDialogAbort(String longRunningProcessDialogAbort)
	{
		this.longRunningProcessDialogAbort = longRunningProcessDialogAbort;
	}

	/**
	 * Gets the long running process remove.
	 * 
	 * @return the long running process remove
	 */
	public String getLongRunningProcessRemove()
	{
		return longRunningProcessRemove;
	}

	/**
	 * Sets the long running process remove.
	 * 
	 * @param longRunningProcessRemove the new long running process remove
	 */
	public void setLongRunningProcessRemove(String longRunningProcessRemove)
	{
		this.longRunningProcessRemove = longRunningProcessRemove;
	}

	/**
	 * Gets the long running process abort.
	 * 
	 * @return the long running process abort
	 */
	public String getLongRunningProcessAbort()
	{
		return longRunningProcessAbort;
	}

	/**
	 * Sets the long running process abort.
	 * 
	 * @param longRunningProcessAbort the new long running process abort
	 */
	public void setLongRunningProcessAbort(String longRunningProcessAbort)
	{
		this.longRunningProcessAbort = longRunningProcessAbort;
	}

	/**
	 * Gets the long running process retry unreachable.
	 * 
	 * @return the long running process retry unreachable
	 */
	public String getLongRunningProcessRetryUnreachable()
	{
		return longRunningProcessRetryUnreachable;
	}

	/**
	 * Sets the long running process retry unreachable.
	 * 
	 * @param longRunningProcessRetryUnreachable the new long running process retry unreachable
	 */
	public void setLongRunningProcessRetryUnreachable(String longRunningProcessRetryUnreachable)
	{
		this.longRunningProcessRetryUnreachable = longRunningProcessRetryUnreachable;
	}

	/**
	 * Gets the long running process dialog.
	 * 
	 * @return the long running process dialog
	 */
	public String getLongRunningProcessDialog()
	{
		return longRunningProcessDialog;
	}

	/**
	 * Sets the long running process dialog.
	 * 
	 * @param longRunningProcessDialog the new long running process dialog
	 */
	public void setLongRunningProcessDialog(String longRunningProcessDialog)
	{
		this.longRunningProcessDialog = longRunningProcessDialog;
	}

	/**
	 * Gets the check long running process.
	 * 
	 * @return the check long running process
	 */
	public String getCheckLongRunningProcess()
	{
		return checkLongRunningProcess;
	}

	/**
	 * Sets the check long running process.
	 * 
	 * @param checkLongRunningProcess the new check long running process
	 */
	public void setCheckLongRunningProcess(String checkLongRunningProcess)
	{
		this.checkLongRunningProcess = checkLongRunningProcess;
	}

	/**
	 * Gets the initiate delete group include.
	 * 
	 * @return the initiate delete group include
	 */
	public String getInitiateDeleteGroupInclude()
	{
		return initiateDeleteGroupInclude;
	}

	/**
	 * Sets the initiate delete group include.
	 * 
	 * @param initiateDeleteGroupInclude the new initiate delete group include
	 */
	public void setInitiateDeleteGroupInclude(String initiateDeleteGroupInclude)
	{
		this.initiateDeleteGroupInclude = initiateDeleteGroupInclude;
	}

	/**
	 * Gets the initiate delete group url.
	 * 
	 * @return the initiate delete group url
	 */
	public String getInitiateDeleteGroupUrl()
	{
		return initiateDeleteGroupUrl;
	}

	/**
	 * Sets the initiate delete group url.
	 * 
	 * @param initiateDeleteGroupUrl the new initiate delete group url
	 */
	public void setInitiateDeleteGroupUrl(String initiateDeleteGroupUrl)
	{
		this.initiateDeleteGroupUrl = initiateDeleteGroupUrl;
	}

	/**
	 * Sets the search url.
	 * 
	 * @param searchUrl the new search url
	 */
	public void setSearchUrl(String searchUrl)
	{
		this.searchUrl = searchUrl;
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
	 * Sets the long running process remove all.
	 * 
	 * @param longRunningProcessRemoveAll the new long running process remove all
	 */
	public void setLongRunningProcessRemoveAll(String longRunningProcessRemoveAll)
	{
		this.longRunningProcessRemoveAll = longRunningProcessRemoveAll;
	}

	/**
	 * Gets the long running process remove all.
	 * 
	 * @return the long running process remove all
	 */
	public String getLongRunningProcessRemoveAll()
	{
		return longRunningProcessRemoveAll;
	}

	/**
	 * Sets the long running process recent.
	 * 
	 * @param longRunningProcessRecent the new long running process recent
	 */
	public void setLongRunningProcessRecent(String longRunningProcessRecent)
	{
		this.longRunningProcessRecent = longRunningProcessRecent;
	}

	/**
	 * Gets the long running process recent.
	 * 
	 * @return the long running process recent
	 */
	public String getLongRunningProcessRecent()
	{
		return longRunningProcessRecent;
	}

	/**
	 * Gets the check rni.
	 * 
	 * @return the check rni
	 */
	public String getCheckRni()
	{
		return checkRni;
	}

	/**
	 * Sets the check rni.
	 * 
	 * @param checkRni the new check rni
	 */
	public void setCheckRni(String checkRni)
	{
		this.checkRni = checkRni;
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

}
