package com.sensus.mlc.wui.base.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.mlc.wui.smartpoint.action.SmartpointSettingsAction;

/**
 * The Class SlcSettingsAction.
 */
public class SlcSettingsAction extends SettingsAction
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1735439655152265099L;

	/** The app root. */
	private String appRoot;

	/** The max resolution. */
	private String maxResolution;

	/** The map units. */
	private String mapUnits;

	/** The long running process time. */
	private String longRunningProcessTime;

	/** The check rni time. */
	private String checkRniTime;

	/** The page size. */
	private Integer pageSize;

	/** The page size include. */
	private String pageSizeInclude;

	/** The page size show dialog. */
	private Integer pageSizeShowDialog;

	/** The polygon dialog. */
	private Integer polygonDialog;

	/** The check long running process. */
	private String checkLongRunningProcess;

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

	/** The check rni. */
	private String checkRni;

	/** The long running process remove all. */
	private String longRunningProcessRemoveAll;

	/** The epm version. */
	private String slcVersion;

	/** The epm build version. */
	private String slcBuildVersion;

	/** The user id. */
	private String userId;

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
		setSlcVersion(getSettings().getProperty("mlc.version"));
		setSlcBuildVersion(getSettings().getProperty("mlc.build.version"));
		setLongRunningProcessTime(getSettings().getProperty(
				"longrunningprocess.url.longRunningProcessTime"));
		setCheckRniTime(getSettings().getProperty("longrunningprocess.url.checkRniTime"));
		setPageSizeInclude(getSettings().getProperty("smartpoint.url.pageSizeInclude"));
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
		setLongRunningProcessRemoveAll(getSettings().getProperty(
				"longrunningprocess.url.longRunningProcessRemoveAll"));
		setCheckRni(getSettings().getProperty("longrunningprocess.url.checkRni"));

		return SUCCESS;
	}

	/**
	 * @return the maxResolution
	 */
	@Override
	public String getMaxResolution()
	{
		return maxResolution;
	}

	/**
	 * @param maxResolution the maxResolution to set
	 */
	@Override
	public void setMaxResolution(String maxResolution)
	{
		this.maxResolution = maxResolution;
	}

	/**
	 * @return the longRunningProcessTime
	 */
	public String getLongRunningProcessTime()
	{
		return longRunningProcessTime;
	}

	/**
	 * @param longRunningProcessTime the longRunningProcessTime to set
	 */
	public void setLongRunningProcessTime(String longRunningProcessTime)
	{
		this.longRunningProcessTime = longRunningProcessTime;
	}

	/**
	 * @return the checkRniTime
	 */
	public String getCheckRniTime()
	{
		return checkRniTime;
	}

	/**
	 * @param checkRniTime the checkRniTime to set
	 */
	public void setCheckRniTime(String checkRniTime)
	{
		this.checkRniTime = checkRniTime;
	}

	/**
	 * @return the appRoot
	 */
	public String getAppRoot()
	{
		return appRoot;
	}

	/**
	 * @param appRoot the appRoot to set
	 */
	public void setAppRoot(String appRoot)
	{
		this.appRoot = appRoot;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize()
	{
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageSizeInclude
	 */
	public String getPageSizeInclude()
	{
		return pageSizeInclude;
	}

	/**
	 * @param pageSizeInclude the pageSizeInclude to set
	 */
	public void setPageSizeInclude(String pageSizeInclude)
	{
		this.pageSizeInclude = pageSizeInclude;
	}

	/**
	 * @return the pageSizeShowDialog
	 */
	public Integer getPageSizeShowDialog()
	{
		return pageSizeShowDialog;
	}

	/**
	 * @param pageSizeShowDialog the pageSizeShowDialog to set
	 */
	public void setPageSizeShowDialog(Integer pageSizeShowDialog)
	{
		this.pageSizeShowDialog = pageSizeShowDialog;
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
	 * @return the slcVersion
	 */
	public String getSlcVersion()
	{
		return slcVersion;
	}

	/**
	 * @param slcVersion the slcVersion to set
	 */
	public void setSlcVersion(String slcVersion)
	{
		this.slcVersion = slcVersion;
	}

	/**
	 * @return the slcBuildVersion
	 */
	public String getSlcBuildVersion()
	{
		return slcBuildVersion;
	}

	/**
	 * @param slcBuildVersion the slcBuildVersion to set
	 */
	public void setSlcBuildVersion(String slcBuildVersion)
	{
		this.slcBuildVersion = slcBuildVersion;
	}

	/**
	 * @return the userId
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * @return the polygonDialog
	 */
	public Integer getPolygonDialog()
	{
		return polygonDialog;
	}

	/**
	 * @param polygonDialog the polygonDialog to set
	 */
	public void setPolygonDialog(Integer polygonDialog)
	{
		this.polygonDialog = polygonDialog;
	}
}