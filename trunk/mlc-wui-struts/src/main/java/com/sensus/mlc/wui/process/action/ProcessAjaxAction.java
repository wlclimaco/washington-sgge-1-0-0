package com.sensus.mlc.wui.process.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.sensus.common.model.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.JsonResult;
import com.sensus.mlc.wui.base.model.SearchJsonResult;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.ResultUtil;

/**
 * Struts action for Process-related Ajax callbacks. This action primarily supports the actions available from the
 * Action menu list.
 * 
 * @author Raphael Constantino
 * 
 */

public class ProcessAjaxAction extends ActionBase
{

	/** CONSTANTS *. */

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1;

	/** The Constant FILE_NAME_SPLIT. */
	private static final String FILE_NAME_SPLIT = "//";

	/** The Constant ERROR_CSV_FILE. */
	private static final String ERROR_CSV_FILE = "Error generating Summary CSV File";

	/** The long running process bcf. */
	private transient IProcessBCF processBCF;

	/** The long running process search result. */
	private SearchJsonResult processSearchResult;

	/** The long running process remove result. */
	private JsonResult result;

	/** The id. */
	private Integer id;

	/** The list ids. */
	private Integer[] listIds;

	/** The input stream. */
	private transient InputStream inputStream;

	/** The csv name. */
	private String csvName;

	/** The file name. */
	private String fileName;

	/** The is sucess. */
	private Boolean isSucess;

	/** The update csv downloaded. */
	private Boolean updateCSVDownloaded;

	/** The fail value. */
	private Integer failValue;

	/** The is monitored. */
	private boolean isMonitored;

	/** The process request. */
	private ProcessRequest processRequest;

	/** The response. */
	private Response response;

	/**
	 * Check long running process.
	 * 
	 * @return the string
	 */
	public String checkLongRunningProcess()
	{
		try
		{
			ProcessResponse response = getProcessBCF().fetchCountMonitoredProcesses(getProcessRequest());
			setResponse(response);

		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Could not search Long Running Processes", ex);
			}
		}

		return SUCCESS;
	}

	/**
	 * Check rni.
	 * 
	 * @return the string
	 */
	public String checkRni()
	{
		try
		{

			ProcessResponse processResponse = getProcessBCF().fetchRniLinkStatus(getProcessRequest());
			setResponse(processResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error check rni status", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Removes the long running process.
	 * 
	 * @return the string
	 */
	public String removeLongRunningProcess()
	{
		try
		{
			// Call BCF Layer
			ProcessResponse response = getProcessBCF().unmonitorProcess(getProcessRequest());
			setResponse(response);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error remove longrunningprocess process", e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Retry unreachable.
	 * 
	 * @return the string
	 */
	public String retryUnreachable()
	{

		try
		{

			ProcessResponse response = getProcessBCF().retryProcess(getProcessRequest());

			setResponse(response);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error retry unreachable process", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Abort the long running process.
	 * 
	 * @return the string
	 */
	public String abortProcessUrl()
	{
		try
		{
			ProcessResponse processResponse = getProcessBCF().abortProcess(getProcessRequest());
			setResponse(processResponse);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error abort process", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Removes the all selected.
	 * 
	 * @return the string
	 */
	public String removeAllSelected()
	{
		try
		{
			ProcessResponse response = getProcessBCF().unmonitorProcess(getProcessRequest());
			setResponse(response);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error remove all selected process", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Generate summary file csv.
	 * 
	 * @return the string
	 */
	public String generateSummaryFileCSV()
	{

		// setProcessSearchResult(new SearchJsonResult());

		try
		{
			/*
			 * ProcessRequest request = new ProcessRequest(getUserContext());
			 * Process process = new Process();
			 * process.setId(getId());
			 * ProcessItem processItem = new ProcessItem();
			 * if (getIsSucess())
			 * {
			 * processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);
			 * }
			 * else if (!getIsSucess())
			 * {
			 * processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.MLCFAILURE);
			 * if (!ValidationUtil.isNullOrZero(getFailValue()))
			 * {
			 * processItem.setProcessStatusReasonEnumValue(getFailValue());
			 * }
			 * }
			 * List<ProcessItem> listProcessItem = new ArrayList<ProcessItem>();
			 * listProcessItem.add(processItem);
			 * process.setProcessItems(listProcessItem);
			 * request.setProcess(process);
			 */
			ProcessResponse response = getProcessBCF().generateSummaryFileCSV(getProcessRequest());

			setResponse(response);

			// if (response.isOperationSuccess())
			// {
			// getProcessSearchResult().setResultValue(response.getFileName());
			// }

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_CSV_FILE, e);
			}
			getProcessSearchResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;

	}

	/**
	 * Delete file.
	 * 
	 * @return the string
	 */
	public String deleteFile()
	{
		setResult(new JsonResult());
		File file = new File(getFileName());
		Boolean resultBoolean = file.delete();

		if (getUpdateCSVDownloaded())
		{
			updateCSVDownloaded();
		}

		getResult().setResult(resultBoolean.toString());
		return SUCCESS;
	}

	/**
	 * Update monitor process.
	 * 
	 * @return the string
	 */
	public String updateMonitorProcess()
	{

		ProcessResponse response = getProcessBCF().updateProcess(getProcessRequest());

		setResponse(response);

		return SUCCESS;

	}

	/**
	 * Update csv downloaded.
	 * 
	 * @return the string
	 */
	public String updateCSVDownloaded()
	{
		ProcessRequest request = new ProcessRequest(getUserContext());

		Process process = new Process();
		process.setId(getId());

		LCActionParameter lcActionParameter = new LCActionParameter();
		lcActionParameter.setProperty(PropertyEnum.FILE_NAME);
		lcActionParameter.setActionValue(getFileName());

		LCAction action = new LCAction(LCActionTypeEnum.GENERATE_CSV_FILE);
		action.getActionParameters().add(lcActionParameter);

		process.setLcAction(action);

		request.setProcess(process);
		ProcessResponse response = getProcessBCF().updateCSVDownloaded(request);

		ResultUtil.setMessages(getProcessSearchResult(), response);

		return SUCCESS;
	}

	/**
	 * Download csv file.
	 * 
	 * @return the string
	 */
	public String downloadCSVFile()
	{
		setProcessSearchResult(new SearchJsonResult());
		try
		{
			if (!(getFileName().indexOf("..") > 0))
			{
				setInputStream(new FileInputStream(getFileName()));
				String[] name = getFileName().split(FILE_NAME_SPLIT);
				Integer nameIndex = name.length - 1;
				setCsvName(name[nameIndex]);
				deleteFile();
			}
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_CSV_FILE, e);
			}
			getProcessSearchResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;

	}

	/**
	 * Gets the process bcf.
	 * 
	 * @return the process bcf
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the process bcf.
	 * 
	 * @param processBCF the new process bcf
	 */
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Gets the process search result.
	 * 
	 * @return the process search result
	 */
	public SearchJsonResult getProcessSearchResult()
	{
		return processSearchResult;
	}

	/**
	 * Gets the result.
	 * 
	 * @return the result
	 */
	public JsonResult getResult()
	{
		return result;
	}

	/**
	 * Sets the result.
	 * 
	 * @param result the new result
	 */
	public void setResult(JsonResult result)
	{
		this.result = result;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the list ids.
	 * 
	 * @return the list ids
	 */
	public Integer[] getListIds()
	{
		return listIds;
	}

	/**
	 * Sets the list ids.
	 * 
	 * @param listIds the new list ids
	 */
	public void setListIds(Integer[] listIds)
	{
		this.listIds = listIds;
	}

	/**
	 * Sets the process search result.
	 * 
	 * @param processSearchResult the new process search result
	 */
	public void setProcessSearchResult(SearchJsonResult processSearchResult)
	{
		this.processSearchResult = processSearchResult;
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
	 * Gets the csv name.
	 * 
	 * @return the csv name
	 */
	public String getCsvName()
	{
		return csvName;
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
	 * Gets the checks if is sucess.
	 * 
	 * @return the checks if is sucess
	 */
	public Boolean getIsSucess()
	{
		return isSucess;
	}

	/**
	 * Sets the checks if is sucess.
	 * 
	 * @param isSucess the new checks if is sucess
	 */
	public void setIsSucess(Boolean isSucess)
	{
		this.isSucess = isSucess;
	}

	/**
	 * Gets the fail value.
	 * 
	 * @return the fail value
	 */
	public Integer getFailValue()
	{
		return failValue;
	}

	/**
	 * Sets the fail value.
	 * 
	 * @param failValue the new fail value
	 */
	public void setFailValue(Integer failValue)
	{
		this.failValue = failValue;
	}

	/**
	 * Sets the update csv downloaded.
	 * 
	 * @param updateCSVDownloaded the new update csv downloaded
	 */
	public void setUpdateCSVDownloaded(Boolean updateCSVDownloaded)
	{
		this.updateCSVDownloaded = updateCSVDownloaded;
	}

	/**
	 * Gets the update csv downloaded.
	 * 
	 * @return the update csv downloaded
	 */
	public Boolean getUpdateCSVDownloaded()
	{
		if (ValidationUtil.isNull(updateCSVDownloaded))
		{
			return false;
		}

		return updateCSVDownloaded;
	}

	/**
	 * Gets the process request.
	 * 
	 * @return the processRequest
	 */
	public ProcessRequest getProcessRequest()
	{
		processRequest.setUserContext(getUserContext());
		return processRequest;
	}

	/**
	 * Sets the process request.
	 * 
	 * @param processRequest the processRequest to set
	 */
	public void setProcessRequest(ProcessRequest processRequest)
	{

		this.processRequest = processRequest;
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
	 * @return the isMonitored
	 */
	public boolean getIsMonitored()
	{
		return isMonitored;
	}

	/**
	 * @param isMonitored the isMonitored to set
	 */
	public void setIsMonitored(boolean isMonitored)
	{
		this.isMonitored = isMonitored;
	}

}
