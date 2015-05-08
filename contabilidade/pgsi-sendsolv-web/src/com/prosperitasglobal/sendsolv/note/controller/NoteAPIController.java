package com.prosperitasglobal.sendsolv.note.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.bai.INoteBAI;
import com.prosperitasglobal.cbof.model.request.NoteMaintenanceRequest;
import com.prosperitasglobal.cbof.model.response.NoteResponse;
import com.prosperitasglobal.sendsolv.common.controller.BaseController;

/**
 * The OrganizationAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */

@Controller
@RequestMapping("/api/note")
public class NoteAPIController extends BaseController
{
	/** The URL mapping constants. */
	private static final String DELETE = "/delete";

	/** The Constant EDIT. */
	private static final String EDIT = "/edit";

	/** The Constant INSERT. */
	private static final String INSERT = "/insert";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(NoteAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "NoteAPIController";

	/** The organization bai. */
	private INoteBAI noteBAI;

	/**
	 * Gets the note bai.
	 *
	 * @return the note bai
	 */
	public INoteBAI getNoteBAI()
	{
		return noteBAI;
	}

	/**
	 * Sets the note bai.
	 *
	 * @param noteBAI the note bai
	 */
	@Resource
	public void setNoteBAI(INoteBAI noteBAI)
	{
		this.noteBAI = noteBAI;
	}

	/**
	 * Edit one Organization.
	 *
	 * @param noteRequest the note request
	 * @return the response
	 */
	@RequestMapping(value = EDIT, method = RequestMethod.POST)
	@ResponseBody
	public NoteResponse edit(@RequestBody NoteMaintenanceRequest noteRequest)
	{
		NoteResponse noteResponse = new NoteResponse();
		try
		{

			noteResponse = getNoteBAI().updateNote(noteRequest);
		}

		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			noteResponse = null;
		}
		return noteResponse;

	}

	/**
	 * Delete one Organization.
	 *
	 * @param noteRequest the note request
	 * @return the response
	 */
	@RequestMapping(value = DELETE, method = RequestMethod.POST)
	@ResponseBody
	public NoteResponse delete(@RequestBody NoteMaintenanceRequest noteRequest)
	{
		NoteResponse noteResponse = new NoteResponse();
		try
		{

			noteResponse = getNoteBAI().deleteNote(noteRequest);
		}

		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			noteResponse = null;
		}
		return noteResponse;

	}

	/**
	 * Insert One Organization.
	 *
	 * @param noteRequest the note request
	 * @return the response
	 */
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	@ResponseBody
	public NoteResponse insert(@RequestBody NoteMaintenanceRequest noteRequest)
	{
		NoteResponse noteResponse = new NoteResponse();
		try
		{

			noteResponse = getNoteBAI().insertNote(noteRequest);
		}

		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			noteResponse = null;
		}
		return noteResponse;
	}

}
