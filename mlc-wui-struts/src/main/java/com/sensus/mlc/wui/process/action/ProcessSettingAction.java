/*
 * 
 */
package com.sensus.mlc.wui.process.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.mlc.wui.base.action.SettingsAction;
import com.sensus.mlc.wui.group.action.GroupSettingsAction;

/**
 * The Class ProcessSettingAction.
 */
public class ProcessSettingAction extends SettingsAction
{

	/** CONSTANTS **/

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7969907294732111538L;

	/**
	 * The logger for this action.
	 */
	private static transient Log LOG = LogFactory.getLog(GroupSettingsAction.class);

	/** The process page action. */
	private String processPageAction;

	/** The search process url. */
	private String searchProcessUrl;

	/** The abort include. */
	private String abortInclude;

	/** The summary include. */
	private String summaryInclude;

	/** The abort process url. */
	private String abortProcessUrl;

	/** The retry process url. */
	private String retryProcessUrl;

	/** The search url lrp. */
	private String searchUrlLRP;

	/** The long running process dialog. */
	private String longRunningProcessDialog;

	/** The Long running process dialog remove. */
	private String longRunningProcessDialogRemove;

	/** The Long running process dialog abort. */
	private String longRunningProcessDialogAbort;

	/** The Long running process remove. */
	private String longRunningProcessRemove;

	/** The Long running process abort. */
	private String longRunningProcessAbort;

	/** The long running process recent. */
	private String longRunningProcessRecent;

	/** The remove all longrunningprocess. */
	private String longRunningProcessRemoveAll;

	/** The long running process retry unreachable. */
	private String longRunningProcessRetryUnreachable;

	/** The check long running process. */
	private String checkLongRunningProcess;

	/** The check rni. */
	private String checkRni;

	/** The insert process. */
	private String insertProcess;

	/** The generate file. */
	private String generateFile;

	/** The delete file. */
	private String deleteFile;

	/** The generate summary file csv. */
	private String generateSummaryFileCSV;

	/** The save profile settings. */
	private String saveProfileSettings;

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

		setProcessPageAction(getSettings().getProperty("systemsettings.url.processPageAction"));
		setAbortInclude(getSettings().getProperty("systemsettings.url.abortInclude"));
		setSummaryInclude(getSettings().getProperty("systemsettings.url.summaryInclude"));
		setAbortProcessUrl(getSettings().getProperty("systemsettings.url.abortProcessUrl"));
		setInsertProcess(getSettings().getProperty("process.url.insertProcess"));
		setGenerateFile(getSettings().getProperty("process.url.generateFile"));
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
		setLongRunningProcessRemoveAll(getSettings().getProperty("longrunningprocess.url.longRunningProcessRemoveAll"));
		setLongRunningProcessRetryUnreachable(getSettings().getProperty(
				"longrunningprocess.url.retryUnreachable"));
		setCheckLongRunningProcess(getSettings().getProperty("longrunningprocess.url.checkLongRunningProcess"));
		setCheckRni(getSettings().getProperty("longrunningprocess.url.checkRni"));
		setDeleteFile(getSettings().getProperty("process.url.deleteFile"));
		setGenerateSummaryFileCSV(getSettings().getProperty("systemsettings.url.generateSummaryFileCSV"));
		setSearchProcessUrl(getSettings().getProperty("systemsettings.url.searchProcessUrl"));
		setSaveProfileSettings(getSettings().getProperty("profile.url.saveprofilesettings"));

		return SUCCESS;
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
	 * Gets the search process url.
	 * 
	 * @return the search process url
	 */
	public String getSearchProcessUrl()
	{
		return searchProcessUrl;
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
	 * Gets the delete file.
	 * 
	 * @return the delete file
	 */
	public String getDeleteFile()
	{
		return deleteFile;
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
	 * Gets the generate summary file csv.
	 * 
	 * @return the generate summary file csv
	 */
	public String getGenerateSummaryFileCSV()
	{
		return generateSummaryFileCSV;
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
	 * @return the insertProcess
	 */
	public String getInsertProcess()
	{
		return insertProcess;
	}

	/**
	 * @param insertProcess the insertProcess to set
	 */
	public void setInsertProcess(String insertProcess)
	{
		this.insertProcess = insertProcess;
	}

	/**
	 * @return the generateFile
	 */
	public String getGenerateFile()
	{
		return generateFile;
	}

	/**
	 * @param generateFile the generateFile to set
	 */
	public void setGenerateFile(String generateFile)
	{
		this.generateFile = generateFile;
	}

}
