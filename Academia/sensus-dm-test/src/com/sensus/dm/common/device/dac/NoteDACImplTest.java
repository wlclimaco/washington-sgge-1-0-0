package com.sensus.dm.common.device.dac;

import static com.sensus.dm.common.util.TestBaseUtil.assertResponse;
import static com.sensus.dm.common.util.TestBaseUtil.assertResultResponse;

import org.junit.Test;

import com.sensus.cbof.model.Note;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.NoteRequest;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class NoteDACImplTest.
 * 
 * @author QAT Global.
 */
public class NoteDACImplTest extends AbstractTestBaseDAC
{

	/**
	 * Test insert meter note.
	 */
	@Test
	public void testInsertMeterNote()
	{
		// Success situation

		NoteRequest noteRequest = TestBaseUtil.createNoteRequest();

		noteRequest.addNote(TestBaseUtil.createNote());

		InternalResultsResponse<Note> response = getNoteDAC().insertMeterNote(noteRequest);

		assertResultResponse(response);
	}

	/**
	 * Test delete meter note.
	 */
	@Test
	public void testDeleteMeterNote()
	{
		// Success situation

		NoteRequest noteRequest = TestBaseUtil.createNoteRequest();

		noteRequest.addNote(TestBaseUtil.createNote());

		InternalResponse response = getNoteDAC().insertMeterNote(noteRequest);

		response = getNoteDAC().deleteMeterNote(noteRequest);

		assertResponse(response);
	}

	/**
	 * Test fetch meter note.
	 */
	@Test
	public void testFetchMeterNote()
	{
		// Success situation

		NoteRequest noteRequest = TestBaseUtil.createNoteRequest();

		noteRequest.addNote(TestBaseUtil.createNote());

		getNoteDAC().insertMeterNote(noteRequest);

		InternalResultsResponse<Note> response = getNoteDAC().fetchDeviceNotes(noteRequest);

		assertResultResponse(response);
	}

}
