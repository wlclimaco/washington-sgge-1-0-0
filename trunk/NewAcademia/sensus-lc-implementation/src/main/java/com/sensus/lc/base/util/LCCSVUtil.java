package com.sensus.lc.base.util;

import static com.sensus.lc.base.util.LCDateUtil.getNewDateUTC;

import java.util.List;
import java.util.Locale;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.csv.CSVUtil;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.model.request.CSVRequest;
import com.sensus.lc.light.model.response.CSVInternalResponse;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.request.ProcessRequest;

/**
 * Helper utility for all things CSV within SLC.
 */
public final class LCCSVUtil
{

	/** The Constant SENSUS_SLC_EXPORT_CSV_ERROR. */
	private static final String SENSUS_LC_EXPORT_CSV_ERROR = "sensus.common.csv.exception";
	private static final String SENSUS_LC_PROCESSVALIDATOR_PROCESS_NOT_FOUND =
			"sensus.mlc.processvalidator.process.not.found";
	private static final int FIVE = 5;
	private static final int THREE = 3;

	/**
	 * Required since this class is final.
	 */
	private LCCSVUtil()
	{
	}

	/**
	 * Processing a request includes setting the locale based on the request, exporting the data and then handling the
	 * return/response.
	 * 
	 * @param request The original request.
	 * @param response The response which will be inflated with errors and eventually returned.
	 * @param objectsToExport The instances of object to be exported.
	 * @param columnsToExport The columns to export
	 * @return The same instance of CSVInternalResponse that was passed in as a parameter updated based on processing
	 *         results.
	 */
	public static CSVInternalResponse processCSVRequest(CSVRequest request, CSVInternalResponse response,
			List objectsToExport, List<CSVColumn> columnsToExport)
	{

		Locale locale = null;
		if (!ValidationUtil.isNull(request.getUserContext()))
		{
			locale =
					new Locale(request.getUserContext().getLocaleString().substring(0, 2), request.getUserContext()
							.getLocaleString().substring(
									THREE, FIVE));
		}

		if (!CSVUtil.exportFile(columnsToExport, request.getFileName(), objectsToExport, locale))
		{
			response.setStatus(Status.SystemError);
			response.getMessageInfoList().add(MessageInfo.createFieldValidationError(SENSUS_LC_EXPORT_CSV_ERROR));
		}

		return response;
	}

	/**
	 * Handle response by updating the process to complete.
	 * 
	 * @param request The original CSV request.
	 * @param response The response which will be inflated with errors, if needed, and eventually returned.
	 * @param processBCL An instance of the process BCL so we can uipdate the process based on the request process-id.
	 * @return The same instance of CSVInternalResponse that was passed in as a parameter updated based on processing
	 *         results.
	 */
	public static CSVInternalResponse handleCSVResponse(CSVRequest request, CSVInternalResponse response,
			IProcessBCL processBCL)
	{
		ProcessRequest processRequest = new ProcessRequest(request.getUserContext());
		processRequest.setProcess(new Process(request.getProcessId()));

		InternalResultsResponse<Process> responseProcess = processBCL.fetchProcessById(processRequest);

		if (responseProcess.isInError() || ValidationUtil.isNullOrEmpty(responseProcess.getResultsList()))
		{
			response.setStatus(Status.SystemError);
			response.addObjectErrorMessage(SENSUS_LC_PROCESSVALIDATOR_PROCESS_NOT_FOUND,
					new Object[] {request.getProcessId()});
			return response;
		}

		// update process as completed
		processRequest.getProcessList().clear();
		processRequest.setProcess(responseProcess.getFirstResult());
		processRequest.getProcess().setIsProcessComplete(true);
		processRequest.getProcess().setEndTime(getNewDateUTC());

		InternalResponse processResponse = processBCL.updateProcess(processRequest);

		if (processResponse.isInError())
		{
			response.merge(processResponse);
			return response;
		}

		return response;
	}

}
