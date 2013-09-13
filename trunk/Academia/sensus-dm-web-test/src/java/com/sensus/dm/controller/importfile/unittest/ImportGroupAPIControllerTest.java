package com.sensus.dm.controller.importfile.unittest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.group.unittest.GroupBCFMockImpl;
import com.sensus.dm.controller.importfile.ImportGroupAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;

/**
 * The Class ImportGroupAPIControllerTest.
 */
public class ImportGroupAPIControllerTest extends AbstractTestBase
{

	/** The Constant UPLOAD. */
	private static final String UPLOAD = "/group/upload/";

	/**
	 * Gets the group mock.
	 * 
	 * @return the group mock
	 */
	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext().getBean(ImportGroupAPIController.class)
				.getGroupBCF();
	}

	/**
	 * Upload.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void upload() throws Exception
	{
		/*
		 * Success Situation
		 * Insert group with file and upload Type equal "Device Id"
		 */
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);

		// Open file
		File file = generateTestFile();

		MockMultipartHttpServletRequestBuilder mockRequestBuilder = fileUpload(
				UPLOAD, new Object[] {});

		MockMultipartFile mockMultipartFile = new MockMultipartFile("upload",
				"FileName.csv", "multipart/form-data",
				FileUtils.readFileToByteArray(file));

		mockRequestBuilder.file(mockMultipartFile);
		mockRequestBuilder.param("name", "Group Name");
		mockRequestBuilder.param("description", "Group Description");
		mockRequestBuilder.param("type", "4");
		mockRequestBuilder.param("uploadType", "Device ID");
		mockRequestBuilder.param("monitored", "true");

		getMockMvc().perform(
				mockRequestBuilder.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(
						status().isOk());

		/*
		 * Success Situation
		 * Insert group with Device List and upload Type equal "Network Address"
		 */
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);

		mockRequestBuilder = fileUpload(UPLOAD, new Object[] {});

		mockMultipartFile = new MockMultipartFile("upload",
				"FileName.csv", "multipart/form-data",
				FileUtils.readFileToByteArray(file));

		mockRequestBuilder.file(mockMultipartFile);
		mockRequestBuilder.param("name", "Group Name");
		mockRequestBuilder.param("description", "values");
		mockRequestBuilder.param("type", "4");
		mockRequestBuilder.param("uploadType", "Network Address");
		mockRequestBuilder.param("upload", "");
		mockRequestBuilder.param("deviceList", "1001,1002");

		getMockMvc().perform(
				mockRequestBuilder.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(
						status().isOk());

		/*
		 * Success Situation
		 * Update group with Device List and upload Type equal "Network Address"
		 */
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);

		mockRequestBuilder = fileUpload(UPLOAD, new Object[] {});

		mockMultipartFile = new MockMultipartFile("upload",
				"FileName.csv", "multipart/form-data",
				FileUtils.readFileToByteArray(file));

		mockRequestBuilder.file(mockMultipartFile);
		mockRequestBuilder.param("id", "0");
		mockRequestBuilder.param("name", "Group Name");
		mockRequestBuilder.param("groupOldName", "Group Old Name");
		mockRequestBuilder.param("description", "values");
		mockRequestBuilder.param("type", "4");
		mockRequestBuilder.param("uploadType", "Network Address");
		mockRequestBuilder.param("deviceList", "1001,1002");
		mockRequestBuilder.param("groupAction", "2");

		getMockMvc().perform(
				mockRequestBuilder.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(
						status().isOk());

		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				mockRequestBuilder.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(
						status().isOk());

		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		getMockMvc().perform(
				mockRequestBuilder.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(
						status().isOk());

		file.delete();
	}

	/**
	 * Generate test file.
	 * 
	 * @return the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private File generateTestFile() throws IOException
	{
		File file = new File(
				"C:/fileTest.csv");
		BufferedWriter br =
				new BufferedWriter(new FileWriter(file));
		br.write("test");
		br.close();

		return file;
	}

}
