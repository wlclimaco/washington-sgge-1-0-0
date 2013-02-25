package com.sensus.mlc.wui.schedule.action;

import com.sensus.mlc.wui.base.action.SettingsAction;

/**
 * Loads schedule-related application settings into a JavaScript file via a Freemarker Template. This one is used both
 * by the main schedule page and the create schedule page.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
/**
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class ScheduleSettingsAction extends SettingsAction
{
	/**
	 * The url for searching schedules.
	 */
	private String searchUrl;

	/**
	 * Url for link to main SmartPoint page.
	 */
	private String smartpointUrl;

	/**
	 * Url for link to Create Schedule page.
	 */
	private String createUrl;

	/** The update schedule url. */
	private String updateScheduleUrl;

	/**
	 * Url for link to Main Schedule page.
	 */
	private String scheduleUrl;

	/**
	 * The minimum sunrise/sunset values.
	 */
	private String sunriseOffsetMin;

	/**
	 * The maximum sunrise/sunset values.
	 */
	private String sunriseOffsetMax;

	/** The delete schedule include. */
	private String deleteScheduleInclude;

	/** The delete schedule url. */
	private String deleteScheduleUrl;

	/** The initiate delete schedule. */
	private String initiateDeleteSchedule;

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
			LOG.debug("Loading Schedule Page Settings");
		}
		super.initSettings();
		setScheduleUrl(getSettings().getProperty("schedule.url.schedule"));
		setSearchUrl(getSettings().getProperty("schedule.url.search"));
		setSmartpointUrl(getSettings().getProperty("schedule.url.smartpoint"));
		setCreateUrl(getSettings().getProperty("schedule.url.create"));
		setUpdateScheduleUrl(getSettings().getProperty("schedule.url.update"));
		setSunriseOffsetMin(getSettings().getProperty("mlc.sunrise.offset.min"));
		setSunriseOffsetMax(getSettings().getProperty("mlc.sunrise.offset.max"));
		setDeleteScheduleInclude(getSettings().getProperty("schedule.include.deleteschedule"));
		setDeleteScheduleUrl(getSettings().getProperty("schedule.url.deleteschedule"));
		setInitiateDeleteSchedule(getSettings().getProperty("schedule.url.initiatedeleteschedule"));
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
		setDeleteFile(getSettings().getProperty("longrunningprocess.url.deleteFile"));
		setSaveProfileSettings(getSettings().getProperty("profile.url.saveprofilesettings"));
		setGenerateSummaryFileCSV(getSettings().getProperty("systemsettings.url.generateSummaryFileCSV"));

		return SUCCESS;
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
	 * Gets the smartpoint url.
	 * 
	 * @return the smartpointAction
	 */
	public String getSmartpointUrl()
	{
		return smartpointUrl;
	}

	/**
	 * Sets the smartpoint url.
	 * 
	 * @param smartpointAction the smartpointAction to set
	 */
	public void setSmartpointUrl(String smartpointAction)
	{
		smartpointUrl = smartpointAction;
	}

	/**
	 * Sets the creates the url.
	 * 
	 * @param createUrl the createUrl to set
	 */
	public void setCreateUrl(String createUrl)
	{
		this.createUrl = createUrl;
	}

	/**
	 * Gets the creates the url.
	 * 
	 * @return the createUrl
	 */
	public String getCreateUrl()
	{
		return createUrl;
	}

	/**
	 * Sets the schedule url.
	 * 
	 * @param scheduleUrl the scheduleUrl to set
	 */
	public void setScheduleUrl(String scheduleUrl)
	{
		this.scheduleUrl = scheduleUrl;
	}

	/**
	 * Gets the schedule url.
	 * 
	 * @return the scheduleUrl
	 */
	public String getScheduleUrl()
	{
		return scheduleUrl;
	}

	/**
	 * Gets the sunrise offset min.
	 * 
	 * @return the sunriseOffsetMin
	 */
	public String getSunriseOffsetMin()
	{
		return sunriseOffsetMin;
	}

	/**
	 * Sets the sunrise offset min.
	 * 
	 * @param sunriseOffsetMin the sunriseOffsetMin to set
	 */
	public void setSunriseOffsetMin(String sunriseOffsetMin)
	{
		this.sunriseOffsetMin = sunriseOffsetMin;
	}

	/**
	 * Gets the sunrise offset max.
	 * 
	 * @return the sunriseOffsetMax
	 */
	public String getSunriseOffsetMax()
	{
		return sunriseOffsetMax;
	}

	/**
	 * Sets the sunrise offset max.
	 * 
	 * @param sunriseOffsetMax the sunriseOffsetMax to set
	 */
	public void setSunriseOffsetMax(String sunriseOffsetMax)
	{
		this.sunriseOffsetMax = sunriseOffsetMax;
	}

	/**
	 * Sets the delete schedule include.
	 * 
	 * @param deleteScheduleInclude the new delete schedule include
	 */
	public void setDeleteScheduleInclude(String deleteScheduleInclude)
	{
		this.deleteScheduleInclude = deleteScheduleInclude;
	}

	/**
	 * Gets the delete schedule include.
	 * 
	 * @return the delete schedule include
	 */
	public String getDeleteScheduleInclude()
	{
		return deleteScheduleInclude;
	}

	/**
	 * Gets the delete schedule url.
	 * 
	 * @return the delete schedule url
	 */
	public String getDeleteScheduleUrl()
	{
		return deleteScheduleUrl;
	}

	/**
	 * Sets the delete schedule url.
	 * 
	 * @param deleteScheduleUrl the new delete schedule url
	 */
	public void setDeleteScheduleUrl(String deleteScheduleUrl)
	{
		this.deleteScheduleUrl = deleteScheduleUrl;
	}

	/**
	 * Gets the initiate delete schedule.
	 * 
	 * @return the initiate delete schedule
	 */
	public String getInitiateDeleteSchedule()
	{
		return initiateDeleteSchedule;
	}

	/**
	 * Sets the initiate delete schedule.
	 * 
	 * @param initiateDeleteSchedule the new initiate delete schedule
	 */
	public void setInitiateDeleteSchedule(String initiateDeleteSchedule)
	{
		this.initiateDeleteSchedule = initiateDeleteSchedule;
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
	 * Sets the update schedule url.
	 * 
	 * @param updateScheduleUrl the new update schedule url
	 */
	public void setUpdateScheduleUrl(String updateScheduleUrl)
	{
		this.updateScheduleUrl = updateScheduleUrl;
	}

	/**
	 * Gets the update schedule url.
	 * 
	 * @return the update schedule url
	 */
	public String getUpdateScheduleUrl()
	{
		return updateScheduleUrl;
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
