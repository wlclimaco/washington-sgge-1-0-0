package com.sensus.mlc.wui.systemsettings.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.mlc.wui.base.action.SettingsAction;

/**
 * Loads system-settings-related application settings into a JavaScript file via a Freemarker Template.
 * 
 * @author Raphael Constantino
 * 
 */

public class SystemSettingAction extends SettingsAction
{
	/** CONSTANTS **/

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5219729378889315824L;

	/**
	 * The logger for this action.
	 */
	private static transient Log LOG = LogFactory.getLog(SystemSettingAction.class);

	/** The tag page action. */
	private String tagPageAction;

	/** The profile page action. */
	private String profilePageAction;

	/** The abort include. */
	private String abortInclude;

	/** The summary include. */
	private String summaryInclude;

	/** The delete tag include. */
	private String deleteTagInclude;

	/** The auto existing group include. */
	private String autoExistingGroupInclude;

	/** The auto no group include. */
	private String autoNoGroupInclude;

	/** The suspend auto group include. */
	private String suspendAutoGroupInclude;

	/** The search tag url. */
	private String searchTagUrl;

	/** The update auto group. */
	private String updateAutoGroup;

	/** The exist group with tag name. */
	private String existGroupWithTagName;

	/** The create group. */
	private String createGroup;

	/** The delete tag url. */
	private String deleteTagUrl;

	/** The add tag. */
	private String addTag;

	/** The process page action. */
	private String processPageAction;

	/** The search process url. */
	private String searchProcessUrl;

	/** The abort process url. */
	private String abortProcessUrl;

	/** The retry process url. */
	private String retryProcessUrl;

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

	/** The delete file. */
	private String deleteFile;

	/** The download file. */
	private String downloadFile;

	/** The save profile settings. */
	private String saveProfileSettings;

	/** The smartpoint url. */
	private String smartpointUrl;

	/** The load wattage list. */
	private String loadWattageList;

	/** The generate summary file csv. */
	private String generateSummaryFileCSV;

	/** The unitk wh. */
	private String unitkWh;

	/** The unit m wh. */
	private String unitMWh;

	/** The unit g wh. */
	private String unitGWh;

	/** The unit t wh. */
	private String unitTWh;

	/** The unit p wh. */
	private String unitPWh;

	/**
	 * Settings.
	 * 
	 * @return the string
	 */
	public String settings()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Loading System Settings Page Settings");
		}
		super.initSettings();

		setTagPageAction(getSettings().getProperty("systemsettings.url.tagPageAction"));
		setProfilePageAction(getSettings().getProperty("systemsettings.url.profilePageAction"));
		setAbortInclude(getSettings().getProperty("systemsettings.url.abortInclude"));
		setSummaryInclude(getSettings().getProperty("systemsettings.url.summaryInclude"));
		setDeleteTagInclude(getSettings().getProperty("systemsettings.url.deleteTagInclude"));
		setAutoExistingGroupInclude(getSettings().getProperty("systemsettings.url.autoExistingGroupInclude"));
		setAutoNoGroupInclude(getSettings().getProperty("systemsettings.url.autoNoGroupInclude"));
		setSuspendAutoGroupInclude(getSettings().getProperty("systemsettings.url.suspendAutoGroupInclude"));
		setSearchTagUrl(getSettings().getProperty("systemsettings.url.searchTagUrl"));
		setSmartpointUrl(getSettings().getProperty("schedule.url.smartpoint"));
		setUpdateAutoGroup(getSettings().getProperty("systemsettings.url.updateAutoGroup"));
		setExistGroupWithTagName(getSettings().getProperty("systemsettings.url.existGroupWithTagName"));
		setCreateGroup(getSettings().getProperty("systemsettings.url.createGroup"));
		setDeleteTagUrl(getSettings().getProperty("systemsettings.url.deleteTagUrl"));
		setAddTag(getSettings().getProperty("systemsettings.url.addTag"));
		setProcessPageAction(getSettings().getProperty("systemsettings.url.processPageAction"));
		setSearchProcessUrl(getSettings().getProperty("systemsettings.url.searchProcessUrl"));
		setAbortProcessUrl(getSettings().getProperty("systemsettings.url.abortProcessUrl"));
		setRetryProcessUrl(getSettings().getProperty("systemsettings.url.retryProcessUrl"));
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
		setDeleteFile(getSettings().getProperty("process.url.deleteFile"));
		setSaveProfileSettings(getSettings().getProperty("systemsettings.url.saveprofilesettings"));
		setLoadWattageList(getSettings().getProperty("systemsettings.url.loadWattageList"));
		setGenerateSummaryFileCSV(getSettings().getProperty("systemsettings.url.generateSummaryFileCSV"));
		setUnitkWh(getSettings().getProperty("profile.url.kWh"));
		setUnitMWh(getSettings().getProperty("profile.url.mWh"));
		setUnitGWh(getSettings().getProperty("profile.url.gWh"));
		setUnitTWh(getSettings().getProperty("profile.url.tWh"));
		setUnitPWh(getSettings().getProperty("profile.url.pWh"));

		return SUCCESS;

	}

	/**
	 * Gets the load wattage list.
	 * 
	 * @return the load wattage list
	 */
	public String getLoadWattageList()
	{
		return loadWattageList;
	}

	/**
	 * Sets the load wattage list.
	 * 
	 * @param loadWattageList the new load wattage list
	 */
	public void setLoadWattageList(String loadWattageList)
	{
		this.loadWattageList = loadWattageList;
	}

	/**
	 * Gets the abort include.
	 * 
	 * @return the abort include
	 */
	public String getAbortInclude()
	{
		return abortInclude;
	}

	/**
	 * Sets the abort include.
	 * 
	 * @param abortInclude the new abort include
	 */
	public void setAbortInclude(String abortInclude)
	{
		this.abortInclude = abortInclude;
	}

	/**
	 * Gets the summary include.
	 * 
	 * @return the summary include
	 */
	public String getSummaryInclude()
	{
		return summaryInclude;
	}

	/**
	 * Sets the summary include.
	 * 
	 * @param summaryInclude the new summary include
	 */
	public void setSummaryInclude(String summaryInclude)
	{
		this.summaryInclude = summaryInclude;
	}

	/**
	 * Gets the tag page action.
	 * 
	 * @return the tag page action
	 */
	public String getTagPageAction()
	{
		return tagPageAction;
	}

	/**
	 * Sets the tag page action.
	 * 
	 * @param tagPageAction the new tag page action
	 */
	public void setTagPageAction(String tagPageAction)
	{
		this.tagPageAction = tagPageAction;
	}

	/**
	 * Gets the delete tag include.
	 * 
	 * @return the delete tag include
	 */
	public String getDeleteTagInclude()
	{
		return deleteTagInclude;
	}

	/**
	 * Sets the delete tag include.
	 * 
	 * @param deleteTagInclude the new delete tag include
	 */
	public void setDeleteTagInclude(String deleteTagInclude)
	{
		this.deleteTagInclude = deleteTagInclude;
	}

	/**
	 * Gets the auto existing group include.
	 * 
	 * @return the auto existing group include
	 */
	public String getAutoExistingGroupInclude()
	{
		return autoExistingGroupInclude;
	}

	/**
	 * Sets the auto existing group include.
	 * 
	 * @param autoExistingGroupInclude the new auto existing group include
	 */
	public void setAutoExistingGroupInclude(String autoExistingGroupInclude)
	{
		this.autoExistingGroupInclude = autoExistingGroupInclude;
	}

	/**
	 * Gets the auto no group include.
	 * 
	 * @return the auto no group include
	 */
	public String getAutoNoGroupInclude()
	{
		return autoNoGroupInclude;
	}

	/**
	 * Sets the auto no group include.
	 * 
	 * @param autoNoGroupInclude the new auto no group include
	 */
	public void setAutoNoGroupInclude(String autoNoGroupInclude)
	{
		this.autoNoGroupInclude = autoNoGroupInclude;
	}

	/**
	 * Gets the suspend auto group include.
	 * 
	 * @return the suspend auto group include
	 */
	public String getSuspendAutoGroupInclude()
	{
		return suspendAutoGroupInclude;
	}

	/**
	 * Sets the suspend auto group include.
	 * 
	 * @param suspendAutoGroupInclude the new suspend auto group include
	 */
	public void setSuspendAutoGroupInclude(String suspendAutoGroupInclude)
	{
		this.suspendAutoGroupInclude = suspendAutoGroupInclude;
	}

	/**
	 * Gets the search tag url.
	 * 
	 * @return the search tag url
	 */
	public String getSearchTagUrl()
	{
		return searchTagUrl;
	}

	/**
	 * Sets the search tag url.
	 * 
	 * @param searchTagUrl the new search tag url
	 */
	public void setSearchTagUrl(String searchTagUrl)
	{
		this.searchTagUrl = searchTagUrl;
	}

	/**
	 * Gets the update auto group.
	 * 
	 * @return the update auto group
	 */
	public String getUpdateAutoGroup()
	{
		return updateAutoGroup;
	}

	/**
	 * Sets the update auto group.
	 * 
	 * @param updateAutoGroup the new update auto group
	 */
	public void setUpdateAutoGroup(String updateAutoGroup)
	{
		this.updateAutoGroup = updateAutoGroup;
	}

	/**
	 * Gets the exist group with tag name.
	 * 
	 * @return the exist group with tag name
	 */
	public String getExistGroupWithTagName()
	{
		return existGroupWithTagName;
	}

	/**
	 * Sets the exist group with tag name.
	 * 
	 * @param existGroupWithTagName the new exist group with tag name
	 */
	public void setExistGroupWithTagName(String existGroupWithTagName)
	{
		this.existGroupWithTagName = existGroupWithTagName;
	}

	/**
	 * Gets the creates the group.
	 * 
	 * @return the creates the group
	 */
	public String getCreateGroup()
	{
		return createGroup;
	}

	/**
	 * Sets the creates the group.
	 * 
	 * @param createGroup the new creates the group
	 */
	public void setCreateGroup(String createGroup)
	{
		this.createGroup = createGroup;
	}

	/**
	 * Gets the delete tag url.
	 * 
	 * @return the delete tag url
	 */
	public String getDeleteTagUrl()
	{
		return deleteTagUrl;
	}

	/**
	 * Sets the delete tag url.
	 * 
	 * @param deleteTagUrl the new delete tag url
	 */
	public void setDeleteTagUrl(String deleteTagUrl)
	{
		this.deleteTagUrl = deleteTagUrl;
	}

	/**
	 * Gets the adds the tag.
	 * 
	 * @return the adds the tag
	 */
	public String getAddTag()
	{
		return addTag;
	}

	/**
	 * Sets the adds the tag.
	 * 
	 * @param addTag the new adds the tag
	 */
	public void setAddTag(String addTag)
	{
		this.addTag = addTag;
	}

	/**
	 * Gets the process page action.
	 * 
	 * @return the process page action
	 */
	public String getProcessPageAction()
	{
		return processPageAction;
	}

	/**
	 * Sets the process page action.
	 * 
	 * @param processPageAction the new process page action
	 */
	public void setProcessPageAction(String processPageAction)
	{
		this.processPageAction = processPageAction;
	}

	/**
	 * Sets the search process url.
	 * 
	 * @param searchProcessUrl the new search process url
	 */
	public void setSearchProcessUrl(String searchProcessUrl)
	{
		this.searchProcessUrl = searchProcessUrl;
	}

	/**
	 * Gets the search process url.
	 * 
	 * @return the search process url
	 */
	public String getSearchProcessUrl()
	{
		return searchProcessUrl;
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
	 * Gets the retry process url.
	 * 
	 * @return the retry process url
	 */
	public String getRetryProcessUrl()
	{
		return retryProcessUrl;
	}

	/**
	 * Sets the retry process url.
	 * 
	 * @param retryProcessUrl the new retry process url
	 */
	public void setRetryProcessUrl(String retryProcessUrl)
	{
		this.retryProcessUrl = retryProcessUrl;
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
	 * Gets the long running process recent.
	 * 
	 * @return the long running process recent
	 */
	public String getLongRunningProcessRecent()
	{
		return longRunningProcessRecent;
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
	 * Gets the long running process remove all.
	 * 
	 * @return the long running process remove all
	 */
	public String getLongRunningProcessRemoveAll()
	{
		return longRunningProcessRemoveAll;
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
	 * Sets the profile page action.
	 * 
	 * @param profilePageAction the profilePageAction to set
	 */
	public void setProfilePageAction(String profilePageAction)
	{
		this.profilePageAction = profilePageAction;
	}

	/**
	 * Gets the profile page action.
	 * 
	 * @return the profilePageAction
	 */
	public String getProfilePageAction()
	{
		return profilePageAction;
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
	 * Sets the download file.
	 * 
	 * @param downloadFile the new download file
	 */
	public void setDownloadFile(String downloadFile)
	{
		this.downloadFile = downloadFile;
	}

	/**
	 * Gets the download file.
	 * 
	 * @return the download file
	 */
	public String getDownloadFile()
	{
		return downloadFile;
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
	 * Sets the unitk wh.
	 * 
	 * @param unitkWh the new unitk wh
	 */
	public void setUnitkWh(String unitkWh)
	{
		this.unitkWh = unitkWh;
	}

	/**
	 * Gets the unitk wh.
	 * 
	 * @return the unitk wh
	 */
	public String getUnitkWh()
	{
		return unitkWh;
	}

	/**
	 * Gets the unit m wh.
	 * 
	 * @return the unit m wh
	 */
	public String getUnitMWh()
	{
		return unitMWh;
	}

	/**
	 * Sets the unit m wh.
	 * 
	 * @param unitMWh the new unit m wh
	 */
	public void setUnitMWh(String unitMWh)
	{
		this.unitMWh = unitMWh;
	}

	/**
	 * Gets the unit g wh.
	 * 
	 * @return the unit g wh
	 */
	public String getUnitGWh()
	{
		return unitGWh;
	}

	/**
	 * Sets the unit g wh.
	 * 
	 * @param unitGWh the new unit g wh
	 */
	public void setUnitGWh(String unitGWh)
	{
		this.unitGWh = unitGWh;
	}

	/**
	 * Gets the unit t wh.
	 * 
	 * @return the unit t wh
	 */
	public String getUnitTWh()
	{
		return unitTWh;
	}

	/**
	 * Sets the unit t wh.
	 * 
	 * @param unitTWh the new unit t wh
	 */
	public void setUnitTWh(String unitTWh)
	{
		this.unitTWh = unitTWh;
	}

	/**
	 * Gets the unit p wh.
	 * 
	 * @return the unit p wh
	 */
	public String getUnitPWh()
	{
		return unitPWh;
	}

	/**
	 * Sets the unit p wh.
	 * 
	 * @param unitPWh the new unit p wh
	 */
	public void setUnitPWh(String unitPWh)
	{
		this.unitPWh = unitPWh;
	}

	/**
	 * Sets the smartpoint url.
	 * 
	 * @param smartpointUrl the smartpointUrl to set
	 */
	public void setSmartpointUrl(String smartpointUrl)
	{
		this.smartpointUrl = smartpointUrl;
	}

	/**
	 * Gets the smartpoint url.
	 * 
	 * @return the smartpointUrl
	 */
	public String getSmartpointUrl()
	{
		return smartpointUrl;
	}

}
