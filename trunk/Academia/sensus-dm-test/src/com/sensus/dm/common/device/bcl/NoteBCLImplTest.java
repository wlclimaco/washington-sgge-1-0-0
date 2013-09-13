package com.sensus.dm.common.device.bcl;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Note;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.dac.INoteDAC;
import com.sensus.dm.common.device.model.request.NoteRequest;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class NoteBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/common/device/notebclimpltest.xml"})
public class NoteBCLImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The note bcl. */
	private INoteBCL noteBCL;

	/**
	 * Gets the note bcl.
	 * 
	 * @return the note bcl
	 */
	public INoteBCL getNoteBCL()
	{
		return noteBCL;
	}

	/**
	 * Sets the note bcl.
	 * 
	 * @param noteBCL the new note bcl
	 */
	@Resource(name = "noteBCLTarget")
	public void setNoteBCL(INoteBCL noteBCL)
	{
		this.noteBCL = noteBCL;
	}

	/**
	 * Sets the device area.
	 */
	public void setNoteArea()
	{
		setArea(getNoteBCL(), EPMAreaEnum.NOTE);
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
		resetMocksData(getNoteBCL());
		setNoteArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test insert meter note.
	 */
	@Test
	public void testInsertMeterNote()
	{
		// Success situation

		NoteRequest request = TestBaseUtil.createNoteRequest();

		InternalResultsResponse<Note> response = getNoteBCL().insertMeterNote(request);

		TestBaseUtil.assertResultResponse(response);

		resetMocksToNoteArea();

		// Error situation

		this.setSituation(getNoteBCL(), SituationsEnum.ERROR, INoteDAC.class);
		response = getNoteBCL().insertMeterNote(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test delete meter note.
	 */
	@Test
	public void testDeleteMeterNote()
	{
		// Success situation

		NoteRequest request = TestBaseUtil.createNoteRequest();

		InternalResponse response = getNoteBCL().deleteMeterNote(request);

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch device notes.
	 */
	@Test
	public void testFetchDeviceNotes()
	{
		NoteRequest request = TestBaseUtil.createNoteRequest();

		InternalResultsResponse<Note> response = getNoteBCL().fetchDeviceNotes(request);

		TestBaseUtil.assertResponse(response);
	}

}
