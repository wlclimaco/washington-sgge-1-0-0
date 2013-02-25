package com.sensus.mlc.wui.user.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.mlc.wui.base.action.SettingsAction;

/**
 * Loads group-related application settings into a JavaScript file via a Freemarker Template.
 * 
 * @author Vinicius Scalon Ferreira
 * 
 */
public class UserSettingsAction extends SettingsAction
{

	/** CONSTANTS **/

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7969907294732111538L;

	/**
	 * The logger for this action.
	 */
	private static transient Log LOG = LogFactory.getLog(UserSettingsAction.class);

	/** The search. */
	private String search;

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

	/** The generate summary file csv. */
	private String generateSummaryFileCSV;

	/** The delete user. */
	private String deleteUser;

	/** The create user. */
	private String createUser;

	/** The update user page. */
	private String updateUser;

	/** The fill user page. */
	private String fillUserPage;

	/** The fill groups. */
	private String fillGroups;

	/** The fetch light by user to map. */
	private String fetchLightByUserToMap;

	/** The fetch lights by group. */
	private String fetchLightsByGroup;

	/**
	 * Load the settings from the application settings.
	 * 
	 * @return always "SUCCESS"
	 */
	public String settings()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Loading User Page Settings");
		}
		super.initSettings();
		setSearch(getSettings().getProperty("user.url.search"));
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
		setGenerateSummaryFileCSV(getSettings().getProperty("systemsettings.url.generateSummaryFileCSV"));
		setDeleteUser(getSettings().getProperty("user.url.deleteUser"));
		setCreateUser(getSettings().getProperty("user.url.createUser"));
		setUpdateUser(getSettings().getProperty("user.url.updateUser"));
		setFillUserPage(getSettings().getProperty("user.url.fillUserPage"));
		setFetchLightByUserToMap(getSettings().getProperty("user.url.fetchLightByUserToMap"));
		setFillGroups(getSettings().getProperty("group.url.search"));
		setFetchLightsByGroup(getSettings().getProperty("user.url.fetchLightsByGroup"));

		return SUCCESS;
	}

	/**
	 * @return the searchUrlLRP
	 */
	public String getSearchUrlLRP()
	{
		return searchUrlLRP;
	}

	/**
	 * @param searchUrlLRP the searchUrlLRP to set
	 */
	public void setSearchUrlLRP(String searchUrlLRP)
	{
		this.searchUrlLRP = searchUrlLRP;
	}

	/**
	 * @return the longRunningProcessDialog
	 */
	public String getLongRunningProcessDialog()
	{
		return longRunningProcessDialog;
	}

	/**
	 * @param longRunningProcessDialog the longRunningProcessDialog to set
	 */
	public void setLongRunningProcessDialog(String longRunningProcessDialog)
	{
		this.longRunningProcessDialog = longRunningProcessDialog;
	}

	/**
	 * @return the longRunningProcessRecent
	 */
	public String getLongRunningProcessRecent()
	{
		return longRunningProcessRecent;
	}

	/**
	 * @param longRunningProcessRecent the longRunningProcessRecent to set
	 */
	public void setLongRunningProcessRecent(String longRunningProcessRecent)
	{
		this.longRunningProcessRecent = longRunningProcessRecent;
	}

	/**
	 * @return the longRunningProcessDialogRemove
	 */
	public String getLongRunningProcessDialogRemove()
	{
		return longRunningProcessDialogRemove;
	}

	/**
	 * @param longRunningProcessDialogRemove the longRunningProcessDialogRemove to set
	 */
	public void setLongRunningProcessDialogRemove(String longRunningProcessDialogRemove)
	{
		this.longRunningProcessDialogRemove = longRunningProcessDialogRemove;
	}

	/**
	 * @return the longRunningProcessDialogAbort
	 */
	public String getLongRunningProcessDialogAbort()
	{
		return longRunningProcessDialogAbort;
	}

	/**
	 * @param longRunningProcessDialogAbort the longRunningProcessDialogAbort to set
	 */
	public void setLongRunningProcessDialogAbort(String longRunningProcessDialogAbort)
	{
		this.longRunningProcessDialogAbort = longRunningProcessDialogAbort;
	}

	/**
	 * @return the longRunningProcessRemove
	 */
	public String getLongRunningProcessRemove()
	{
		return longRunningProcessRemove;
	}

	/**
	 * @param longRunningProcessRemove the longRunningProcessRemove to set
	 */
	public void setLongRunningProcessRemove(String longRunningProcessRemove)
	{
		this.longRunningProcessRemove = longRunningProcessRemove;
	}

	/**
	 * @return the longRunningProcessAbort
	 */
	public String getLongRunningProcessAbort()
	{
		return longRunningProcessAbort;
	}

	/**
	 * @param longRunningProcessAbort the longRunningProcessAbort to set
	 */
	public void setLongRunningProcessAbort(String longRunningProcessAbort)
	{
		this.longRunningProcessAbort = longRunningProcessAbort;
	}

	/**
	 * @return the longRunningProcessRetryUnreachable
	 */
	public String getLongRunningProcessRetryUnreachable()
	{
		return longRunningProcessRetryUnreachable;
	}

	/**
	 * @param longRunningProcessRetryUnreachable the longRunningProcessRetryUnreachable to set
	 */
	public void setLongRunningProcessRetryUnreachable(String longRunningProcessRetryUnreachable)
	{
		this.longRunningProcessRetryUnreachable = longRunningProcessRetryUnreachable;
	}

	/**
	 * @return the checkLongRunningProcess
	 */
	public String getCheckLongRunningProcess()
	{
		return checkLongRunningProcess;
	}

	/**
	 * @param checkLongRunningProcess the checkLongRunningProcess to set
	 */
	public void setCheckLongRunningProcess(String checkLongRunningProcess)
	{
		this.checkLongRunningProcess = checkLongRunningProcess;
	}

	/**
	 * @return the longRunningProcessRemoveAll
	 */
	public String getLongRunningProcessRemoveAll()
	{
		return longRunningProcessRemoveAll;
	}

	/**
	 * @param longRunningProcessRemoveAll the longRunningProcessRemoveAll to set
	 */
	public void setLongRunningProcessRemoveAll(String longRunningProcessRemoveAll)
	{
		this.longRunningProcessRemoveAll = longRunningProcessRemoveAll;
	}

	/**
	 * @return the checkRni
	 */
	public String getCheckRni()
	{
		return checkRni;
	}

	/**
	 * @param checkRni the checkRni to set
	 */
	public void setCheckRni(String checkRni)
	{
		this.checkRni = checkRni;
	}

	/**
	 * @return the saveProfileSettings
	 */
	public String getSaveProfileSettings()
	{
		return saveProfileSettings;
	}

	/**
	 * @param saveProfileSettings the saveProfileSettings to set
	 */
	public void setSaveProfileSettings(String saveProfileSettings)
	{
		this.saveProfileSettings = saveProfileSettings;
	}

	/**
	 * @return the generateSummaryFileCSV
	 */
	public String getGenerateSummaryFileCSV()
	{
		return generateSummaryFileCSV;
	}

	/**
	 * @param generateSummaryFileCSV the generateSummaryFileCSV to set
	 */
	public void setGenerateSummaryFileCSV(String generateSummaryFileCSV)
	{
		this.generateSummaryFileCSV = generateSummaryFileCSV;
	}

	/**
	 * @return the search
	 */
	public String getSearch()
	{
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(String search)
	{
		this.search = search;
	}

	/**
	 * @return the deleteUser
	 */
	public String getDeleteUser()
	{
		return deleteUser;
	}

	/**
	 * @param deleteUser the deleteUser to set
	 */
	public void setDeleteUser(String deleteUser)
	{
		this.deleteUser = deleteUser;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser()
	{
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser)
	{
		this.createUser = createUser;
	}

	/**
	 * @return the updateUserPage
	 */
	public String getUpdateUser()
	{
		return updateUser;
	}

	/**
	 * @param updateUserPage the updateUserPage to set
	 */
	public void setUpdateUser(String updateUser)
	{
		this.updateUser = updateUser;
	}

	/**
	 * @return the fillUserPage
	 */
	public String getFillUserPage()
	{
		return fillUserPage;
	}

	/**
	 * @param fillUserPage the fillUserPage to set
	 */
	public void setFillUserPage(String fillUserPage)
	{
		this.fillUserPage = fillUserPage;
	}

	/**
	 * @return the fillGroups
	 */
	public String getFillGroups()
	{
		return fillGroups;
	}

	/**
	 * @param fillGroups the fillGroups to set
	 */
	public void setFillGroups(String fillGroups)
	{
		this.fillGroups = fillGroups;
	}

	/**
	 * @return the fetchLightByUserToMap
	 */
	public String getFetchLightByUserToMap()
	{
		return fetchLightByUserToMap;
	}

	/**
	 * @param fetchLightByUserToMap the fetchLightByUserToMap to set
	 */
	public void setFetchLightByUserToMap(String fetchLightByUserToMap)
	{
		this.fetchLightByUserToMap = fetchLightByUserToMap;
	}

	/**
	 * @return the fetchLightsByGroup
	 */
	public String getFetchLightsByGroup()
	{
		return fetchLightsByGroup;
	}

	/**
	 * @param fetchLightsByGroup the fetchLightsByGroup to set
	 */
	public void setFetchLightsByGroup(String fetchLightsByGroup)
	{
		this.fetchLightsByGroup = fetchLightsByGroup;
	}

}
