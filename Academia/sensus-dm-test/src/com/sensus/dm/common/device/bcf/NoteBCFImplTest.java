package com.sensus.dm.common.device.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Note;
import com.sensus.dm.common.device.bcl.INoteBCL;
import com.sensus.dm.common.device.model.request.NoteRequest;
import com.sensus.dm.common.device.model.response.NoteResponse;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class NoteBCFImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/common/device/notebcfimpltest.xml"})
public class NoteBCFImplTest extends AbstractTestBaseBusiness
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant AMOUNT_REPEATS_67. */
	private static final int AMOUNT_REPEATS_67 = 67;

	/** The Constant SHOULD_HAVE_NO_MESSAGES. */
	private static final String SHOULD_HAVE_NO_MESSAGES = "should have no messages";

	/** The Constant SHOULD_BE_TRUE. */
	private static final String SHOULD_BE_TRUE = "should be true ";

	/** The Constant SHOULD_BRING_ONE_NOTE. */
	private static final String SHOULD_BRING_ONE_NOTE = "should bring one note ";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant NOTE_FLEXNET_ID_REQUIRED. */
	private static final String NOTE_FLEXNET_ID_REQUIRED = "sensus.epm.devicevalidator.meterflexnetid.required";

	/** The Constant TEXT_REQUIRED. */
	private static final String TEXT_REQUIRED = "sensus.epm.notevalidator.meternotetext.required";

	/** The Constant TEXT_INVALID. */
	private static final String TEXT_INVALID = "sensus.epm.notevalidator.note.text.invalid";

	/** The Constant NOTE_ID_REQUIRED. */
	private static final String NOTE_ID_REQUIRED = "sensus.epm.notevalidator.meternoteid.required";

	/** The Constant NOTE_REQUIRED. */
	private static final String NOTE_REQUIRED = "sensus.epm.notevalidator.note.required";

	/** The Constant DEFAULT_DEVICE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DEVICE_BCF_EXCEPTION_MSG = "sensus.epm.devicebcfimpl.defaultexception";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The note bcf. */
	private INoteBCF noteBCF; // injected by Spring through setter

	/**
	 * Gets the note bcf.
	 * 
	 * @return the note bcf
	 */
	public INoteBCF getNoteBCF()
	{
		return noteBCF;
	}

	/**
	 * Sets the note bcf.
	 * 
	 * @param noteBCF the new note bcf
	 */
	@Resource
	public void setNoteBCF(INoteBCF noteBCF)
	{
		this.noteBCF = noteBCF;
	}

	// Tests Settings:

	/**
	 * Sets the device area.
	 */
	public void setNoteArea()
	{
		setArea(getNoteBCF(), EPMAreaEnum.NOTE);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setNoteArea();
	}

	/**
	 * Removes the device area.
	 */
	@After
	public void resetMocksToNoteArea()
	{
		resetMocksData(getNoteBCF());
		setNoteArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests Settings:

	/**
	 * Test insert meter note.
	 */
	@Test
	public void testInsertMeterNote()
	{
		// Validation situation - flexnet id and post note text is required
		NoteRequest request = new NoteRequest(new Note());
		NoteResponse response = getNoteBCF().insertMeterNote(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, NOTE_FLEXNET_ID_REQUIRED, TEXT_REQUIRED);

		// Validation situation - Post Note exceeds max 1000 characters
		request =
				new NoteRequest(new Note(ELECTRIC_FLEXNET_ID, StringUtils.repeat("OVER 1000 CHARS", AMOUNT_REPEATS_67)));

		response = getNoteBCF().insertMeterNote(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, TEXT_INVALID, Arrays.asList(String.valueOf(ONE_THOUSAND)));

		// Success situation
		request = new NoteRequest(TestBaseUtil.createNote());
		response = getNoteBCF().insertMeterNote(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_NOTE, ONE, response.getNotes().size());

		// Error situation
		setSituation(getNoteBCF(), SituationsEnum.ERROR, INoteBCL.class);
		response = getNoteBCF().insertMeterNote(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToNoteArea();

		// Exception situation
		setSituation(getNoteBCF(), SituationsEnum.EXCEPTION, INoteBCL.class);
		response = getNoteBCF().insertMeterNote(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test delete meter note.
	 */
	@Test
	public void testDeleteMeterNote()
	{
		// Validation situation - note is required
		NoteRequest request = new NoteRequest();
		NoteResponse response = getNoteBCF().deleteMeterNote(request);

		assertFalse(response.isOperationSuccess());
		assertEquals(NOTE_REQUIRED, response.getMessageInfoList().get(ZERO).getCode());

		// Validation situation - note id is required
		request.addNote(new Note());

		response = getNoteBCF().deleteMeterNote(request);

		assertFalse(response.isOperationSuccess());
		assertEquals(NOTE_ID_REQUIRED, response.getMessageInfoList().get(ZERO).getCode());

		// Success situation
		request = new NoteRequest(new Note(ONE));

		response = getNoteBCF().deleteMeterNote(request);

		assertEquals(ZERO, response.getMessageInfoList().size());

		// Error situation
		setSituation(getNoteBCF(), SituationsEnum.ERROR, INoteBCL.class);
		response = getNoteBCF().deleteMeterNote(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToNoteArea();

		// Exception situation
		setSituation(getNoteBCF(), SituationsEnum.EXCEPTION, INoteBCL.class);
		response = getNoteBCF().deleteMeterNote(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all electric meters.
	 */
	@Test
	public void testFetchDeviceNotes()
	{
		// Validation situation - flexnet id text is required
		NoteRequest request = new NoteRequest(new Note());
		NoteResponse response = getNoteBCF().fetchDeviceNotes(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, NOTE_FLEXNET_ID_REQUIRED);

		// Success situation
		request = new NoteRequest(TestBaseUtil.createNote());

		response = getNoteBCF().fetchDeviceNotes(request);

		assertEquals(ZERO, response.getMessageInfoList().size());

		// Error situation
		setSituation(getNoteBCF(), SituationsEnum.ERROR, INoteBCL.class);
		response = getNoteBCF().fetchDeviceNotes(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToNoteArea();

		// Exception situation
		setSituation(getNoteBCF(), SituationsEnum.EXCEPTION, INoteBCL.class);
		response = getNoteBCF().fetchDeviceNotes(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}
}
