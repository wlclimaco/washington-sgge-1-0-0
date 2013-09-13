package com.sensus.dm.controller.note;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.dm.common.device.bcf.INoteBCF;
import com.sensus.dm.common.device.model.request.NoteRequest;
import com.sensus.dm.common.device.model.response.NoteResponse;
import com.sensus.dm.controller.base.BaseController;

/**
 * The Class NoteAPIController.
 */
@Controller
@RequestMapping("/api/note")
public class NoteAPIController extends BaseController
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(NoteAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "NoteAPIController";

	/** The smart point bcf. */
	private INoteBCF noteBCF;

	/**
	 * @return the noteBCF
	 */
	public INoteBCF getNoteBCF()
	{
		return noteBCF;
	}

	/**
	 * @param noteBCF the noteBCF to set
	 */
	@Resource
	public void setNoteBCF(INoteBCF noteBCF)
	{
		this.noteBCF = noteBCF;
	}

	/**
	 * Insert note.
	 * 
	 * @param request the request
	 * @return the map
	 */
	@RequestMapping(value = "/insertNote", method = RequestMethod.POST)
	@ResponseBody
	public Response insertNote(@RequestBody NoteRequest noteRequest)
	{

		NoteResponse noteResponse = new NoteResponse();

		try
		{
			// ADD user context to request
			addUserContextToRequest(noteRequest);

			// Insert
			noteResponse = getNoteBCF().insertMeterNote(noteRequest);

			// Whether Success do fetch to reload list
			if (noteResponse.isOperationSuccess())
			{
				return getNoteBCF().fetchDeviceNotes(noteRequest);
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, noteResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return noteResponse;
	}

	/**
	 * Delete note.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = "/deleteNote", method = RequestMethod.POST)
	@ResponseBody
	public Response deleteNote(@RequestBody NoteRequest noteRequest)
	{
		NoteResponse noteResponse = new NoteResponse();

		try
		{

			// ADD user context to request
			addUserContextToRequest(noteRequest);

			noteResponse = getNoteBCF().deleteMeterNote(noteRequest);

			// Whether Success do fetch to reload list
			if (noteResponse.isOperationSuccess())
			{
				return getNoteBCF().fetchDeviceNotes(noteRequest);
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, noteResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return noteResponse;
	}
}