package com.sensus.dm.controller.export;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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
import com.sensus.dm.common.process.bcf.IProcessCSVBCF;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.controller.base.BaseController;

@Controller
@RequestMapping("/export")
public class ExportFileController extends BaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ExportFileController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ExportFileController";

	/** The Constant DOWNLOAD_CSV_FILE. */
	private static final String DOWNLOAD_CSV_FILE = "/downloadCsvFile";

	/** The Constant FILE_NAME_SPLIT. */
	private static final String FILE_NAME_SPLIT = "/";

	/** The Constant FILE_DOWNLOADED. */
	public static final String FILE_DOWNLOADED = "File Downloaded";

	/** The keep file. */
	private Boolean keepFile;

	/** The process CSV BCF. */
	private IProcessCSVBCF processCsvBCF;

	/**
	 * Gets the process CSV BCF.
	 * 
	 * @return the process CSV BCF
	 */
	public IProcessCSVBCF getProcessCsvBCF()
	{
		return processCsvBCF;
	}

	/**
	 * Sets the process CSV BCF.
	 * 
	 * @param processCsvBCF the new process CSV BCF
	 */
	@Resource
	public void setProcessCsvBCF(IProcessCSVBCF processCsvBCF)
	{
		this.processCsvBCF = processCsvBCF;
	}

	/**
	 * Gets the keep file.
	 * 
	 * @return the keep file
	 */
	public Boolean getKeepFile()
	{
		return keepFile;
	}

	/**
	 * Sets the keep file.
	 * 
	 * @param keepFile the new keep file
	 */
	public void setKeepFile(Boolean keepFile)
	{
		this.keepFile = keepFile;
	}

	/**
	 * Download CSV file.
	 * 
	 * @param processId the process id
	 * @param fileName the file name
	 * @param updateCSVDownloaded the update CSV Downloaded
	 * @param response the response
	 * @return the response
	 */
	@RequestMapping(value = DOWNLOAD_CSV_FILE, method = RequestMethod.GET)
	@ResponseBody
	public Response downloadCSVFile(@RequestParam(value = "id", required = true) Integer processId,
			@RequestParam(value = "fileName", required = true) String fileName,
			@RequestParam(value = "updateCSVDownloaded", required = false) Boolean updateCSVDownloaded,
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
				response.setHeader("Content-Disposition", "attachment; filename=" + name[name.length - 1]);
				response.setBufferSize(1024);
				FileCopyUtils.copy(new FileInputStream(fileName), response.getOutputStream());

				// TODO This keep file was being used at export unreachable file, remove this clause, and its attribute,
				// getter and setter if it is not necessary anymore
				if (ValidationUtil.isNull(getKeepFile()) || !getKeepFile())
				{
					File file = new File(fileName);
					file.delete();

					if (!ValidationUtil.isNull(updateCSVDownloaded) && updateCSVDownloaded)
					{
						processResponse = updateCSVDownloaded(processId);
					}
				}
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;
	}

	/**
	 * Update CSV Downloaded.
	 * 
	 * @param processId the process id
	 * @return the string
	 */
	private ProcessResponse updateCSVDownloaded(Integer processId)
	{
		ProcessResponse response = new ProcessResponse();
		try
		{
			ProcessRequest request = new ProcessRequest();

			// ADD user context to request
			addUserContextToRequest(request);

			Property property = new Property();
			property.setPropertyName(PropertyEnum.FILE_DOWNLOADED.getValue());
			property.setPropertyValue(FILE_DOWNLOADED);

			List<Property> properties = new ArrayList<Property>();
			properties.add(property);

			DMProcess process = new DMProcess();
			process.setId(processId);
			process.setProperties(properties);

			request.addProcessAsFirstElement(process);

			response = getProcessCsvBCF().updateCSVDownloaded(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

}
