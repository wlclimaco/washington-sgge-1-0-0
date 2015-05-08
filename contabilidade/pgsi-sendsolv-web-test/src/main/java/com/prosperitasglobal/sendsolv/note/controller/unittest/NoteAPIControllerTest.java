package com.prosperitasglobal.sendsolv.note.controller.unittest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.bai.INoteBAI;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.request.NoteMaintenanceRequest;
import com.prosperitasglobal.cbof.model.response.NoteResponse;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;

public class NoteAPIControllerTest extends AbstractTestBase
{

	private static final String CODE = "code";

	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private static final Logger LOG = LoggerFactory.getLogger(NoteAPIControllerTest.class);

	@Rule
	public ExpectedException exception = ExpectedException.none();

	/** The organization bai. */
	private INoteBAI noteBAI;

	public INoteBAI getNoteBAI()
	{
		return noteBAI;
	}

	@Resource
	public void setNoteBAI(INoteBAI noteBAI)
	{
		this.noteBAI = noteBAI;
	}

	@Test
	public void edit()
	{

		// Mock Response 1
		NoteResponse response = new NoteResponse();
		response.setNoteList(new ArrayList<Note>());

		Note note = new Note();
		note.setId(1);
		note.setParentKey(1000);
		note.setParentKeyType(BusinessTypeEnum.ORGANIZATION);
		note.setNoteText("noteText");

		response.getNoteList().add(note);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getNoteBAI().updateNote(
						Matchers.any(NoteMaintenanceRequest.class)))
						.thenReturn(response);

		try
		{

			setData("{\"note\":{\"id\":null,\"noteText\":\"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,\",\"parentKey\":7872,\"parentKeyValue\":1,\"modelAction\":\"UPDATE\"}}");

			performTest("/api/note/edit").andExpect(
					jsonPath("$.noteList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new NoteResponse();
			response.setOperationSuccess(true);
			response.setNoteList(new ArrayList<Note>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getNoteBAI().updateNote(
							Matchers.any(NoteMaintenanceRequest.class)))
							.thenReturn(response);

			performTest("/api/note/edit").andExpect(
					jsonPath("$.noteList", hasSize(0))).andExpect(
							jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

			// ============================= Exception ========================================
			response = new NoteResponse();
			response.setOperationSuccess(false);
			response.addMessage(CODE, MessageSeverity.Fatal, MessageLevel.Field);
			// When BAI is invoked with any request, return response 2
			// When BAI is invoked with any request, return response 1
			Mockito.when(
					getNoteBAI().updateNote(
							Matchers.any(NoteMaintenanceRequest.class)))
							.thenReturn(response);

			performTest("/api/note/edit").andExpect(
					jsonPath("$.messageList", hasSize(1))).andExpect(
							jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.when(
					getNoteBAI().updateNote(
							Matchers.any(NoteMaintenanceRequest.class))).thenThrow(new RuntimeException());

			performTest("/api/note/edit");

			Mockito.calls(1);

			Mockito.verify(getNoteBAI().updateNote(
					Matchers.any(NoteMaintenanceRequest.class)));

			Mockito.reset(getNoteBAI().updateNote(
					Matchers.any(NoteMaintenanceRequest.class)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void Insert()
	{
		// Mock Response 1
		NoteResponse response = new NoteResponse();
		response.setNoteList(new ArrayList<Note>());

		Note note = new Note();
		note.setId(1);
		note.setParentKey(1000);
		note.setParentKeyType(BusinessTypeEnum.ORGANIZATION);
		note.setNoteText("noteText");

		response.getNoteList().add(note);

		// When BAI is invoked with any request, return response 1
		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getNoteBAI().insertNote(
						Matchers.any(NoteMaintenanceRequest.class)))
						.thenReturn(response);
		try
		{

			setData("{\"note\":{\"id\":null,\"noteText\":\"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,\",\"parentKey\":7872,\"parentKeyValue\":1,\"modelAction\":\"INSERT\"}}");

			performTest("/api/note/insert").andExpect(
					jsonPath("$.noteList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new NoteResponse();
			response.setOperationSuccess(true);
			response.setNoteList(new ArrayList<Note>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getNoteBAI().insertNote(
							Matchers.any(NoteMaintenanceRequest.class)))
					.thenReturn(response);

			performTest("/api/note/insert").andExpect(
					jsonPath("$.noteList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

			// ============================= Exception ========================================
			response = new NoteResponse();
			response.setOperationSuccess(false);
			response.addMessage(CODE, MessageSeverity.Fatal, MessageLevel.Field);
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getNoteBAI().insertNote(
							Matchers.any(NoteMaintenanceRequest.class)))
					.thenReturn(response);

			performTest("/api/note/insert").andExpect(
					jsonPath("$.messageList", hasSize(1))).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.when(
					getNoteBAI().insertNote(
							Matchers.any(NoteMaintenanceRequest.class))).thenThrow(new RuntimeException());

			performTest("/api/note/insert");

			Mockito.calls(1);

			Mockito.verify(getNoteBAI().insertNote(
					Matchers.any(NoteMaintenanceRequest.class)));

			Mockito.reset(getNoteBAI().insertNote(
					Matchers.any(NoteMaintenanceRequest.class)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void delete()
	{

		// Mock Response 1
		NoteResponse response = new NoteResponse();
		response.setNoteList(new ArrayList<Note>());

		Note note = new Note();
		note.setId(1);
		note.setParentKey(1000);
		note.setParentKeyType(BusinessTypeEnum.ORGANIZATION);
		note.setNoteText("noteText");

		response.getNoteList().add(note);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getNoteBAI().deleteNote(
						Matchers.any(NoteMaintenanceRequest.class)))
				.thenReturn(response);

		try
		{

			setData("{\"note\":{\"id\":null,\"noteText\":\"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,\",\"parentKey\":7872,\"parentKeyValue\":1,\"modelAction\":\"DELETE\"}}");

			performTest("/api/note/delete").andExpect(
					jsonPath("$.noteList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new NoteResponse();
			response.setOperationSuccess(true);
			response.setNoteList(new ArrayList<Note>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getNoteBAI().deleteNote(
							Matchers.any(NoteMaintenanceRequest.class)))
					.thenReturn(response);

			performTest("/api/note/delete").andExpect(
					jsonPath("$.noteList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

			// ============================= Exception ========================================
			response = new NoteResponse();
			response.setOperationSuccess(false);
			response.addMessage(CODE, MessageSeverity.Fatal, MessageLevel.Field);
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getNoteBAI().deleteNote(
							Matchers.any(NoteMaintenanceRequest.class)))
					.thenReturn(response);

			performTest("/api/note/delete").andExpect(
					jsonPath("$.messageList", hasSize(1))).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.when(
					getNoteBAI().deleteNote(
							Matchers.any(NoteMaintenanceRequest.class))).thenThrow(new RuntimeException());

			performTest("/api/note/delete");

			Mockito.calls(1);

			Mockito.verify(getNoteBAI().deleteNote(
					Matchers.any(NoteMaintenanceRequest.class)));

			Mockito.reset(getNoteBAI().deleteNote(
					Matchers.any(NoteMaintenanceRequest.class)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

}
