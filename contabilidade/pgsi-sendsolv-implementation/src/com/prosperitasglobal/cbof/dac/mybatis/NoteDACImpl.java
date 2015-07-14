package com.prosperitasglobal.cbof.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Note;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class NoteDACImpl.
 */
public class NoteDACImpl extends SqlSessionDaoSupport implements INoteDAC
{

	/** The Constant NOTE_NAMESPACE. */
	private static final String NOTE_NAMESPACE = "noteMap.";

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
	 * @see com.prosperitasglobal.cbof.dac.INoteDAC#fetchNoteByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolve.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<Note> fetchNoteByParent(Integer parentId, BusinessTypeEnum parentType)
	{
		InternalResultsResponse<Note> response = new InternalResultsResponse<Note>();

		switch (parentType)
		{
			case ORGANIZATION:
			case LOCATION:
				QATMyBatisDacHelper.doQueryForList(getSqlSession(), NOTE_STMT_FETCH_BY_BUSINESS_ID, parentId,
						response);
				break;
			case MEMBER:
			case RECIPIENT:
				QATMyBatisDacHelper.doQueryForList(getSqlSession(), NOTE_STMT_FETCH_BY_PERSON_ID, parentId,
						response);
				break;
			case UNKNOWN:
				break;
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("parentType for Note missing!");
				}
				break;
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.INoteDAC#insertNote(com.prosperitasglobal.sendsolve.model.Note,
	 * java.lang.String)
	 */
	@Override
	public InternalResultsResponse<Note> insertNote(Note note)
	{
		InternalResultsResponse<Note> response = new InternalResultsResponse<Note>();

		// switch (note.getParentKeyType())
		// {
		// case ORGANIZATION:
		// case LOCATION:
		// insertNote(note, BUSINESS_STMT_ASSOC_NOTE, response, true);
		// break;
		// case MEMBER:
		// case RECIPIENT:
		// case LIAISON:
		// insertNote(note, PERSON_STMT_ASSOC_NOTE, response, true);
		// break;
		// case MONEY_TRANSFER:
		// insertNote(note, MONEY_TRANSFER_STMT_ASSOC_NOTE, response, true);
		// break;
		// case MONEY_TRANSFER_BATCH:
		// insertNote(note, MONEY_TRANSFER_BATCH_STMT_ASSOC_NOTE, response, true);
		// break;
		// default:
		// insertNote(note, null, response, true);
		// break;
		// }

		return response;
	}

	/**
	 * Insert note.
	 *
	 * @param note the note
	 * @param statementName the statement name
	 * @param response the response
	 * @param addToResponse the add to response
	 * @return the integer
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	private Integer insertNote(Note note, String statementName, InternalResultsResponse response, boolean addToResponse)
	{
		// First insert the note data
		Integer insertCount = 0;

		// switch (note.getParentKeyType())
		// {
		// case ORGANIZATION:
		// case LOCATION:
		// case MEMBER:
		// case LIAISON:
		// case RECIPIENT:
		// insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), NOTE_STMT_INSERT, note, response);
		// break;
		// case MONEY_TRANSFER:
		// insertCount =
		// QATMyBatisDacHelper.doInsert(getSqlSession(), NOTE_STMT_INSERT_MONEY_TRANSFER, note, response);
		// break;
		// case MONEY_TRANSFER_BATCH:
		// insertCount =
		// QATMyBatisDacHelper.doInsert(getSqlSession(), NOTE_STMT_INSERT_MONEY_TRANSFER_BATCH, note,
		// response);
		// break;
		// default:
		// insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), NOTE_STMT_INSERT, note, response);
		// break;
		// }

		if (!response.isInError())
		{
			// Associate with parent using statement name passed as parameter
			QATMyBatisDacHelper.doInsert(getSqlSession(), NOTE_NAMESPACE + statementName, note, response);

			if (!response.isInError() && addToResponse)
			{
				response.addResult(note);
			}
		}

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.INoteDAC#updateNote(com.prosperitasglobal.sendsolve.model.Note)
	 */
	@Override
	public InternalResultsResponse<Note> updateNote(Note note)
	{
		InternalResultsResponse<Note> response = new InternalResultsResponse<Note>();

		updateNote(note, response);

		return response;
	}

	/**
	 * Update note.
	 *
	 * @param note the note
	 * @param response the response
	 * @return the integer
	 */
	private Integer updateNote(Note note, InternalResultsResponse<Note> response)
	{
		// First insert the note data
		Integer updateCount = 0;

		updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), NOTE_STMT_UPDATE, note, response);

		if (!response.isInError())
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
	public InternalResponse deleteNote(Note note)
	{
		InternalResponse response = new InternalResponse();

		QATMyBatisDacHelper.doRemove(getSqlSession(), NOTE_STMT_DELETE, note, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.INoteDAC#maintainNoteAssociations(java.util.List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Integer maintainNoteAssociations(List<Note> noteList, Integer parentId, String associateStatement,
			InternalResultsResponse response)
	{
		Integer count = 0;
		// First Maintain Contacts
		if (ValidationUtil.isNullOrEmpty(noteList))
		{
			return count;
		}
		InternalResultsResponse<Note> result = new InternalResultsResponse<Note>();

		// For Each Contact...
		for (Note note : noteList)
		{
			// Make sure we set the parent key
			note.setParentId(parentId);

			if (ValidationUtil.isNull(note.getModelAction()))
			{
				continue;
			}
			switch (note.getModelAction())
			{
				case INSERT:
					count = insertNote(note, associateStatement, result, false);
					response.merge(result);
					break;
				case UPDATE:
					count = updateNote(note, result);
					response.merge(result);
					break;
				case DELETE:
					InternalResponse deleteResponse = deleteNote(note);
					if (!deleteResponse.isInError())
					{
						count++;
					}
					response.merge(deleteResponse);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Organization missing!");
					}
					break;
			}
		}
		return count;
	}
}
