package com.sensus.lc.controller.export.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.export.ExportFileController;
import com.sensus.lc.controller.process.unittest.ProcessBCFMockImpl;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;

public class ExportFileControllerTest extends AbstractTestBase
{

	@Test
	public void downloadCSVFile() throws Exception
	{

		setData("{}");

		File exportFile = File.createTempFile("csvtmp", ".csv");
		String path = exportFile.getPath();
		exportFile.deleteOnExit();

		String urlTest =
				"/export/downloadCsvFile?id=1&fileName=" + path + "&updateCSVDownloaded=true";

		urlTest = StringUtils.replace(urlTest, "\\", "/");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTestGet(urlTest).andExpect(content().string(notNullValue()));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTestGet(urlTest).andExpect(content().string(containsString("")));

	}

	private ProcessBCFMockImpl getProcessMock()
	{
		return (ProcessBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileController.class).getProcessBCF();
	}
}
