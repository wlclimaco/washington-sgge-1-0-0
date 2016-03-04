package com.qat.samples.sysmgmt.util.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.util.Note;
import com.qat.samples.sysmgmt.util.dac.INoteDAC;

/**
 * The Class NoteDACImpl.
 */
public class NoteDACImpl extends SqlSessionDaoSupport implements INoteDAC
{

	/** The Constant NOTE_NAMESPACE. */
	private static final String NOTE_NAMESPACE = "NoteMap.";

	/** The Constant NOTE_STMT_FETCH_BY_ID. */
	private static final String NOTE_STMT_FETCH_BY_ID = NOTE_NAMESPACE + "fetchNoteById";

	/** The Constant NOTE_STMT_FETCH_BY_BUSINESS_ID. */
	private static final String NOTE_STMT_FETCH_BY_BUSINESS_ID = NOTE_NAMESPACE + "fetchNoteByBusinessId";

	/** The Constant NOTE_STMT_FETCH_BY_PERSON_ID. */
	private static final String NOTE_STMT_FETCH_BY_PERSON_ID = NOTE_NAMESPACE + "fetchNoteByPersonId";

	/** The Constant NOTE_STMT_INSERT. */
	private static final String NOTE_STMT_INSERT = NOTE_NAMESPACE + "insertNote";

	/** The Constant NOTE_STMT_INSERT_MONEY_TRANSFER. */
	private static final String NOTE_STMT_INSERT_MONEY_TRANSFER = NOTE_NAMESPACE + "insertMoneyTransferNote";

	/** The Constant NOTE_STMT_INSERT_MONEY_TRANSFER_BATCH. */
	private static final String NOTE_STMT_INSERT_MONEY_TRANSFER_BATCH = NOTE_NAMESPACE + "insertMoneyTransferBatchNote";

	/** The Constant NOTE_STMT_UPDATE. */
	private static final String NOTE_STMT_UPDATE = NOTE_NAMESPACE + "updateNote";

	/** The Constant NOTE_STMT_DELETE. */
	private static final String NOTE_STMT_DELETE = NOTE_NAMESPACE + "deleteNote";

	/** The Constant BUSINESS_STMT_ASSOC_NOTE. */
	private static final String BUSINESS_STMT_ASSOC_NOTE = "associateBusinessWithNote";

	/** The Constant PERSON_STMT_ASSOC_NOTE. */
	private static final String PERSON_STMT_ASSOC_NOTE = "associatePersonWithNote";

	/** The Constant MEMBER_TRANSFER_STMT_ASSOC_NOTE. */
	private static final String MONEY_TRANSFER_STMT_ASSOC_NOTE = "associateMoneyTransferWithNote";

	/** The Constant MEMBER_TRANSFER_BATCH_STMT_ASSOC_NOTE. */
	private static final String MONEY_TRANSFER_BATCH_STMT_ASSOC_NOTE = "associateMoneyTransferBatchWithNote";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(NoteDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.INoteDAC#fetchNoteById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Note> fetchNoteById(Integer id)
	{
		InternalResultsResponse<Note> response = new InternalResultsResponse<Note>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), NOTE_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.INoteDAC#insertNote(com.prosperitasglobal.sendsolve.model.Note,
	 * java.lang.String)
	 */
	@Override
	public Integer insertNote(Note note)
	{

		Integer insertCount = 0;
		InternalResultsResponse<Note> response = new InternalResultsResponse<Note>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), NOTE_STMT_INSERT, note, response);

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was inserted then add the Banco to the result.
		if (insertCount > 0)
		{
			response.addResult(note);
		}

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.INoteDAC#updateNote(com.prosperitasglobal.sendsolve.model.Note)
	 */
	@Override
	public Integer updateNote(Note note)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Note> response = new InternalResultsResponse<Note>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(note.getModelAction())
				&& (note.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), NOTE_STMT_UPDATE, note,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(note);
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.INoteDAC#deleteNote(com.prosperitasglobal.sendsolve.model.Note)
	 */
	@Override
	public Integer deleteNote(Note note)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), NOTE_STMT_DELETE, note, response);
		if (response.isInError())
		{
			return null;
		}
		else
		{
			return 1;
		}
	}

}
