package com.sensus.lc.controller.export;

import java.io.File;
import java.io.FileInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.BaseController;
import com.sensus.lc.controller.light.LightAPIController;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.process.bcf.IProcessBCF;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.model.response.ProcessResponse;

/**
 * The Class ExportFileController.
 */
@Controller
@RequestMapping("/export")
public class ExportFileController extends BaseController
{

	/** The Constant BUFFER_VALUE. */
	private static final int BUFFER_VALUE = 1024;

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ExportFileController";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant DOWNLOAD_CSV_FILE. */
	private static final String DOWNLOAD_CSV_FILE = "/downloadCsvFile";

	/** The Constant FILE_NAME_SPLIT. */
	private static final String FILE_NAME_SPLIT = "/";

	/** The Constant FILE_DOWNLOADED. */
	public static final String FILE_DOWNLOADED = "File Downloaded";

	/** The keep file. */
	private Boolean keepFile;

	/** The process bcf. */
	private IProcessBCF processBCF;

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
	 * @param processBCF
	 *            the new process bcf
	 */
	@Resource
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Gets the keep file.
	 * 
	 * @return the keepFile
	 */
	public Boolean getKeepFile()
	{
		return keepFile;
	}

	/**
	 * Sets the keep file.
	 * 
	 * @param keepFile
	 *            the keepFile to set
	 */
	public void setKeepFile(Boolean keepFile)
	{
		this.keepFile = keepFile;
	}

	/**
	 * Download csv file.
	 * 
	 * @param processId
	 *            the process id
	 * @param fileName
	 *            the file name
	 * @param updateCSVDownloaded
	 *            the update csv downloaded
	 * @param response
	 *            the response
	 * @return the response
	 */
	@RequestMapping(value = DOWNLOAD_CSV_FILE, method = RequestMethod.GET)
	@ResponseBody
	public Response downloadCSVFile(
			@RequestParam(value = "id", required = true) Integer processId,
			@RequestParam(value = "fileName", required = true) String fileName,
			@RequestParam(value = "updateCSVDownloaded", required = false) Boolean updateCSVDownloaded,
			HttpServletRequest request,
			HttpServletResponse response)
	{
		ProcessResponse processResponse = new ProcessResponse();
		try
		{
			String[] name = null;
			if (fileName.contains(FILE_NAME_SPLIT))
			{
				name = fileName.split(FILE_NAME_SPLIT);
			}
			if (!ValidationUtil.isNull(name))
			{
				response.setContentType("text/csv");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + name[name.length - 1]);
				response.setBufferSize(BUFFER_VALUE);
				FileCopyUtils.copy(new FileInputStream(fileName),
						response.getOutputStream());

				if (ValidationUtil.isNull(getKeepFile()) || !getKeepFile())
				{
					File file = new File(fileName);
					file.delete();

					if (!ValidationUtil.isNull(updateCSVDownloaded)
							&& updateCSVDownloaded)
					{
						processResponse = updateCSVDownloaded(processId, fileName, request);
					}
				}
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e,
					DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;
	}

	/**
	 * Update csv downloaded.
	 * 
	 * @param processId the process id
	 * @param fileName the file name
	 * @return the process response
	 */
	private ProcessResponse updateCSVDownloaded(Integer processId, String fileName, HttpServletRequest request)
	{
		ProcessResponse response = new ProcessResponse();
		try
		{

			ProcessRequest processRequest = new ProcessRequest(getUserContext(request));

			Process process = new Process();
			process.setId(processId);

			LCActionParameter lcActionParameter = new LCActionParameter();
			lcActionParameter.setProperty(PropertyEnum.FILE_NAME);
			lcActionParameter.setActionValue(fileName);

			LCAction action = new LCAction(LCActionTypeEnum.GENERATE_CSV_FILE);
			action.getActionParameters().add(lcActionParameter);

			process.setLcAction(action);

			processRequest.setProcess(process);
			response = getProcessBCF().updateCSVDownloaded(processRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e,
					DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

}
