package com.sensus.dm.controller.note.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.note.NoteAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class NoteAPIControllerTest.
 */
public class NoteAPIControllerTest extends AbstractTestBase
{
	/** The Constant INSERT. */
	private static final String INSERT = "/api/note/insertNote";

	/** The Constant DELETE. */
	private static final String DELETE = "/api/note/deleteNote";

	/**
	 * Gets the device mock.
	 * 
	 * @return the device mock
	 */
	private NoteBCFMockImpl getNoteMock()
	{
		return (NoteBCFMockImpl)SensusAppContext.getApplicationContext().getBean(NoteAPIController.class)
				.getNoteBCF();
	}

	/**
	 * Insert note.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insertNote() throws Exception
	{
		setData("{\"notes\":[{\"flexNetId\":1001,\"text\":\"testNote\"}]}");

		// Success Situation
		getNoteMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT)
				.andExpect(jsonPath("$.notes", hasSize(1)))
				.andExpect(jsonPath("$.notes[0].text", comparesEqualTo("testNote")))
				.andExpect(jsonPath("$.notes[0].flexNetId", comparesEqualTo(1001)));

		// Failure Situation
		getNoteMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getNoteMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(INSERT).andExpect(jsonPath("$.notes", nullValue()));

		// Exception Situation
		getNoteMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Delete note.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void deleteNote() throws Exception
	{

		setData("{\"notes\":[{\"flexNetId\":1001,\"id\":1}]}");

		// Success Situation
		getNoteMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT)
				.andExpect(jsonPath("$.notes", hasSize(1)))
				.andExpect(jsonPath("$.notes[0].id", comparesEqualTo(1)))
				.andExpect(jsonPath("$.notes[0].flexNetId", comparesEqualTo(1001)));

		// Failure Situation
		getNoteMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getNoteMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(DELETE).andExpect(jsonPath("$.notes", nullValue()));

		// Exception Situation
		getNoteMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}
