package com.sensus.lc.controller.importfile.unitest;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.server.request.MockMultipartHttpServletRequestBuilder;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.ecomode.unittest.EcoModeBCFMockImpl;
import com.sensus.lc.controller.importfile.ImportEcoModeController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;

public class ImportEcoModeControllerTest extends AbstractTestBase
{

	/** The Constant UPLOAD. */
	private static final String UPLOAD = "/ecomode/upload?uploadTag=1,2,3,4,5";

	private EcoModeBCFMockImpl getEcoModeMock()
	{
		return (EcoModeBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ImportEcoModeController.class).getEcoModeBCF();
	}

	/**
	 * Upload.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void upload() throws Exception
	{
		getEcoModeMock().setMode(ModeEnum.MODE_SUCCESS);

		// Open file
		File file = generateTestFile();

		MockMultipartHttpServletRequestBuilder mockRequestBuilder = fileUpload(
				UPLOAD, new Object[] {});

		MockMultipartFile mockMultipartFile = new MockMultipartFile("upload",
				"FileName.csv", "text/csv",
				FileUtils.readFileToByteArray(file));

		mockRequestBuilder.file(mockMultipartFile);
		mockRequestBuilder.param("uploadTag", "1");

		getMockMvc().perform(mockRequestBuilder).andExpect(
				status().isOk());

		getEcoModeMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(mockRequestBuilder).andExpect(
				status().isOk());

		getEcoModeMock().setMode(ModeEnum.MODE_EXCEPTION);
		getMockMvc().perform(mockRequestBuilder).andExpect(
				status().isOk());

		mockMultipartFile = new MockMultipartFile("upload",
				"FileName.csv", "multipart/form-data",
				FileUtils.readFileToByteArray(file));

		mockRequestBuilder.file(mockMultipartFile);

		getMockMvc().perform(mockRequestBuilder).andExpect(
				status().isOk());

		file.delete();
	}

	/**
	 * Generate test file.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private File generateTestFile() throws IOException
	{
		File file = new File(System.getProperty("java.io.tmpdir") + "fileTest.csv");
		BufferedWriter br = new BufferedWriter(new FileWriter(file));
		br.write("test");
		br.close();

		return file;
	}

}
