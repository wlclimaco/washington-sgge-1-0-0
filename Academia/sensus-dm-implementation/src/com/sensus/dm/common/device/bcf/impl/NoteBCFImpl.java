package com.sensus.dm.common.device.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Note;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.device.bcf.INoteBCF;
import com.sensus.dm.common.device.bcl.INoteBCL;
import com.sensus.dm.common.device.model.request.NoteRequest;
import com.sensus.dm.common.device.model.response.NoteResponse;

/**
 * The Class NoteBCFImpl.
 * 
 * @author QAT Global.
 */
public class NoteBCFImpl implements INoteBCF
{
	/** The Constant DEFAULT_DEVICE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DEVICE_BCF_EXCEPTION_MSG = "sensus.epm.devicebcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(NoteBCFImpl.class);

	/** The note validation controller. */
	private ValidationController noteValidationController; // injected by Spring through setter

	/** The note bcl. */
	private INoteBCL noteBCL; // injected by Spring through setter

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
	public void setNoteBCL(INoteBCL noteBCL)
	{
		this.noteBCL = noteBCL;
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
	 * @param noteValidationController the new note validation controller
	 */
	public void setNoteValidationController(ValidationController noteValidationController)
	{
		this.noteValidationController = noteValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.INoteBCF#insertMeterNote(com.sensus.dm.common.device.model.request.NoteRequest)
	 */
	@Override
	public NoteResponse insertMeterNote(NoteRequest noteRequest)
	{
		NoteResponse response = new NoteResponse();
		InternalResultsResponse<Note> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.INSERT_METER_NOTES);

			context.putObjectToBeValidated(Note.class.getSimpleName(), noteRequest.getFirstNote());

			if (getNoteValidationController().validate(context))
			{
				internalResponse = getNoteBCL().insertMeterNote(noteRequest);
				response.setNotes(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.INoteBCF#deleteMeterNote(com.sensus.dm.common.device.model.request.NoteRequest)
	 */
	@Override
	public NoteResponse deleteMeterNote(NoteRequest request)
	{
		NoteResponse response = new NoteResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.DELETE_METER_NOTES);

			context.putObjectToBeValidated(Note.class.getSimpleName(), request.getFirstNote());

			if (getNoteValidationController().validate(context))
			{
				internalResponse = getNoteBCL().deleteMeterNote(request);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.INoteBCF#fetchDeviceNotes(com.sensus.dm.common.device.model.request.NoteRequest)
	 */
	@Override
	public NoteResponse fetchDeviceNotes(NoteRequest request)
	{
		NoteResponse response = new NoteResponse();
		InternalResultsResponse<Note> internalResponse = null;
		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_DEVICE_NOTES);

			context.putObjectToBeValidated(Note.class.getSimpleName(), request.getFirstNote());

			if (getNoteValidationController().validate(context))
			{
				internalResponse = getNoteBCL().fetchDeviceNotes(request);
				response.setNotes(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}
}
