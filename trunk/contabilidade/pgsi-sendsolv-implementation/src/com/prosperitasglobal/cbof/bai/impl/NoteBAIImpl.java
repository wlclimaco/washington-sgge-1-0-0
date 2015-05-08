package com.prosperitasglobal.cbof.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.bai.INoteBAI;
import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.request.NoteMaintenanceRequest;
import com.prosperitasglobal.cbof.model.response.NoteResponse;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;

/**
 * The Class NoteBAIImpl.
 */
public class NoteBAIImpl implements INoteBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = NoteBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(NoteBAIImpl.class);

	/** The note dac. */
	private INoteDAC noteDAC;

	/** The note validation controller. */
	private ValidationController noteValidationController;

	/**
	 * Gets the note dac.
	 *
	 * @return the note dac
	 */
	public INoteDAC getNoteDAC()
	{
		return noteDAC;
	}

	/**
	 * Sets the note dac.
	 *
	 * @param noteDAC the note dac
	 */
	public void setNoteDAC(INoteDAC noteDAC)
	{
		this.noteDAC = noteDAC;
	}

	/**
	 * Gets the note validation controller.
	 *
	 * @return the note validation controller
	 */
	public ValidationController getNoteValidationController()
	{
		return noteValidationController;
	}

	/**
	 * Sets the note validation controller.
	 *
	 * @param noteValidationController the note validation controller
	 */
	public void setNoteValidationController(ValidationController noteValidationController)
	{
		this.noteValidationController = noteValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.bai.INoteBAI#insertNote(com.prosperitasglobal.sendsolve.model.request.
	 * NoteMaintenanceRequest)
	 */
	@Override
	public NoteResponse insertNote(NoteMaintenanceRequest request)
	{
		NoteResponse response = new NoteResponse();
		try
		{
			response = processNote(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.bai.INoteBAI#updateNote(com.prosperitasglobal.sendsolve.model.request.
	 * NoteMaintenanceRequest)
	 */
	@Override
	public NoteResponse updateNote(NoteMaintenanceRequest request)
	{
		NoteResponse response = new NoteResponse();
		try
		{
			response = processNote(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.bai.INoteBAI#deleteNote(com.prosperitasglobal.sendsolve.model.request.
	 * NoteMaintenanceRequest)
	 */
	@Override
	public NoteResponse deleteNote(NoteMaintenanceRequest request)
	{
		NoteResponse response = new NoteResponse();
		try
		{
			response = processNote(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Process note.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the note response
	 */
	private NoteResponse processNote(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			NoteMaintenanceRequest request)
	{
		NoteResponse response = new NoteResponse();
		InternalResponse internalResponse = null;

		// Validate
		ValidationContext context =
				new ValidationContext(Note.class.getSimpleName(), request.getNote(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getNoteValidationController().validate(context))
		{
			internalResponse = doPersistanceNote(request, persistType);
		}

		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		return response;
	}

	/**
	 * Do persistance note.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceNote(NoteMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getNoteDAC().insertNote(request.getNote());

			case UPDATE:
				return getNoteDAC().updateNote(request.getNote());

			case DELETE:
				return getNoteDAC().deleteNote(request.getNote());
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType for Note missing!");
				}
				break;
		}

		return null;
	}

}
