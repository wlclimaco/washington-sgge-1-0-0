package com.sensus.dm.controller.export.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.export.ExportFileController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class ExportFileControllerTest.
 */
public class ExportFileControllerTest extends AbstractTestBase
{

	/** The Constant DOWNLOAD_CSV_FILE. */
	private static final String DOWNLOAD_CSV_FILE =
			"/export/downloadCsvFile/?id=1&fileName=/AppLogs/csv/rod2013-02-0506.28.07.068.csv&updateCSVDownloaded=true";

	/**
	 * Gets the process csv mock.
	 * 
	 * @return the process csv mock
	 */
	private ProcessCsvBCFMockImpl getProcessCsvMock()
	{
		return (ProcessCsvBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileController.class).getProcessCsvBCF();
	}

	/**
	 * Download csv file.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void downloadCSVFile() throws Exception
	{
		// Exception situation - File Not Found Exception
		getProcessCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTestGet(DOWNLOAD_CSV_FILE).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// Success situation
		generateTestFile();
		getProcessCsvMock().setMode(ModeEnum.MODE_SUCCESS);
		performTestGet(DOWNLOAD_CSV_FILE).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		generateTestFile();
		getProcessCsvMock().setMode(ModeEnum.MODE_FAILURE);
		performTestGet(DOWNLOAD_CSV_FILE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation - BE Exception
		generateTestFile();
		getProcessCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTestGet(DOWNLOAD_CSV_FILE).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate test file.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void generateTestFile() throws IOException
	{
		BufferedWriter br =
				new BufferedWriter(new FileWriter(new File("C:/AppLogs/csv/rod2013-02-0506.28.07.068.csv")));
		br.close();
	}

}
