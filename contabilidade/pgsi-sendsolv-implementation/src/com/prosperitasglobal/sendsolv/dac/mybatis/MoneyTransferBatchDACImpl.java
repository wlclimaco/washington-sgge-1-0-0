package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.sendsolv.dac.IMoneyTransferBatchDAC;
import com.prosperitasglobal.sendsolv.dac.IMoneyTransferDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatus;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * The Class MoneyTransferDACImpl.
 */
public class MoneyTransferBatchDACImpl extends SqlSessionDaoSupport implements IMoneyTransferBatchDAC
{
	/*
	 * Spring Injected Properties
	 */
	/** The implementation of the {@link IMoneyTransferDAC}. Will be injected by Spring. */
	private IMoneyTransferDAC moneyTransferDAC;

	/** The valid sort fields for a money transfer batch inquiry. Will be injected by Spring. */
	private Map<String, String> moneyTransferBatchInquiryValidSortFields;

	/** The implementation of the {@link INoteDAC}. Will be injected by Spring. */
	private INoteDAC noteDAC;

	/*
	 * SQL Statements
	 */
	/** The MoneyTransferBatchMap name space. */
	private static final String MONEY_TRANSFER_BATCH_NAMESPACE = "MoneyTransferBatchMap.";

	/** The MoneyTransferBatchMap.fetchMoneyTransferBatchById SQL statement. */
	private static final String MONEY_TRANSFER_BATCH_STMT_FETCH_BY_ID = MONEY_TRANSFER_BATCH_NAMESPACE
			+ "fetchMoneyTransferBatchById";

	/** The MoneyTransferBatchMap.fetchMoneyTransferBatchRowCount SQL statement. */
	private static final String MONEY_TRANSFER_BATCH_STMT_FETCH_ROW_COUNT = MONEY_TRANSFER_BATCH_NAMESPACE
			+ "fetchMoneyTransferBatchRowCount";

	/** The MoneyTransferBatchMap.fetchMoneyTransferBatchByRequest SQL statement. */
	private static final String MONEY_TRANSFER_BATCH_STMT_FETCH_BY_REQUEST = MONEY_TRANSFER_BATCH_NAMESPACE
			+ "fetchMoneyTransferBatchByRequest";

	/** The MoneyTransferBatchMap.fetchMoneyTransferBatchVersionNumber SQL statement. */
	private static final String MONEY_TRANSFER_BATCH_STMT_FETCH_VERSION = MONEY_TRANSFER_BATCH_NAMESPACE
			+ "fetchMoneyTransferBatchVersionNumber";

	/** The MoneyTransferBatchMap.insertMoneyTransferBatch SQL statement. */
	private static final String MONEY_TRANSFER_BATCH_STMT_INSERT = MONEY_TRANSFER_BATCH_NAMESPACE
			+ "insertMoneyTransferBatch";

	/** The MoneyTransferBatchMap.updateMoneyTransferBatch SQL statement. */
	private static final String MONEY_TRANSFER_BATCH_STMT_UPDATE = MONEY_TRANSFER_BATCH_NAMESPACE
			+ "updateMoneyTransferBatch";

	/** The MoneyTransferBatchMap.deleteMoneyTransferBatch SQL statement. */
	private static final String MONEY_TRANSFER_BATCH_STMT_DELETE = MONEY_TRANSFER_BATCH_NAMESPACE
			+ "deleteMoneyTransferBatch";

	/** The MoneyTransferBatchStatusMap name space. */
	private static final String MONEY_TRANSFER_BATCH_STATUS_NAMESPACE = "MoneyTransferBatchStatusMap.";

	/** The MoneyTransferBatchStatusMap.insertMoneyTransferBatchStatus SQL statement. */
	private static final String MONEY_TRANSFER_BATCH_STATUS_STMT_INSERT = MONEY_TRANSFER_BATCH_STATUS_NAMESPACE
			+ "insertMoneyTransferBatchStatus";

	/** The MoneyTransferBatchStatusMap.updateMoneyTransferBatchStatus SQL statement. */
	private static final String MONEY_TRANSFER_BATCH_STATUS_STMT_UPDATE = MONEY_TRANSFER_BATCH_STATUS_NAMESPACE
			+ "updateMoneyTransferBatchStatus";

	/** The MoneyTransferBatchStatusMap.deleteMoneyTransferBatchStatus SQL statement. */
	private static final String MONEY_TRANSFER_BATCH_STATUS_STMT_DELETE = MONEY_TRANSFER_BATCH_STATUS_NAMESPACE
			+ "deleteMoneyTransferBatchStatus";

	/**
	 * Delete a {@link MoneyTransferBatchStatus} object.
	 *
	 * @param moneyTransferBatchStatus The {@link MoneyTransferBatchStatus} object to delete.
	 * @param response The {@link InternalResponse} object that is currently being used for the delete. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful deletes were performed.
	 */
	private int deleteMoneyTransferBatchStatus(MoneyTransferBatchStatus moneyTransferBatchStatus,
			InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doRemove(getSqlSession(), MONEY_TRANSFER_BATCH_STATUS_STMT_DELETE,
						moneyTransferBatchStatus, response);

		if (count == 1)
		{
			moneyTransferBatchStatus.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Insert a {@link MoneyTransferBatchStatus} object.
	 *
	 * @param moneyTransferBatchStatus The {@link MoneyTransferBatchStatus} object to insert.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful inserts were performed.
	 */
	private int insertMoneyTransferBatchStatus(MoneyTransferBatchStatus moneyTransferBatchStatus,
			InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doInsert(getSqlSession(), MONEY_TRANSFER_BATCH_STATUS_STMT_INSERT,
						moneyTransferBatchStatus, response);

		if (count == 1)
		{
			moneyTransferBatchStatus.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Maintain the money transfer batch associations.
	 *
	 * @param moneyTransferBatch The {@link MoneyTransferBatch}.
	 * @param response The response.
	 * @return The number of actions performed.
	 */
	private int maintainMoneyTransferBatchAssociations(MoneyTransferBatch moneyTransferBatch, InternalResponse response)
	{
		int count = maintainMoneyTransferBatchStatusList(moneyTransferBatch, response);
		count += maintainMoneyTransferBatchNoteList(moneyTransferBatch, response);
		count += maintainMoneyTransferBatchMoneyTransferList(moneyTransferBatch, response);
		return count;
	}

	/**
	 * Maintain the money transfer batch money transfers.
	 *
	 * @param moneyTransferBatch The {@link MoneyTransferBatch}.
	 * @param response The response.
	 * @return The number of actions performed.
	 */
	private int maintainMoneyTransferBatchMoneyTransferList(MoneyTransferBatch moneyTransferBatch,
			InternalResponse response)
	{
		int count = 0;

		// For Each Money Transfer...
		for (MoneyTransfer moneyTransfer : moneyTransferBatch.getMoneyTransferList())
		{
			// Make sure we set the parent key
			moneyTransfer.setMoneyTransferBatchId(moneyTransferBatch.getId());
			InternalResponse moneyTransferResponse = new InternalResponse();

			switch (moneyTransfer.getModelAction())
			{
				case UPDATE:
					moneyTransferResponse = getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer);
					break;
				case INSERT:
					moneyTransferResponse = getMoneyTransferDAC().insertMoneyTransfer(moneyTransfer);
					break;
				case DELETE:
					moneyTransferResponse = getMoneyTransferDAC().deleteMoneyTransfer(moneyTransfer);
					break;
				default:
					break;
			}

			if (moneyTransferResponse.isInError())
			{
				response.merge(moneyTransferResponse);
				break;
			}

			count += 1;
		}

		return count;
	}

	/**
	 * Maintain the money transfer batch notes.
	 *
	 * @param moneyTransferBatch The {@link MoneyTransferBatch}.
	 * @param response The response.
	 * @return The number of actions performed.
	 */
	private int maintainMoneyTransferBatchNoteList(MoneyTransferBatch moneyTransferBatch, InternalResponse response)
	{
		int count = 0;

		// For Each Note...
		for (Note note : moneyTransferBatch.getNoteList())
		{
			// Make sure we set the parent key
			note.setParentKey(moneyTransferBatch.getId());
			InternalResponse noteResponse = new InternalResponse();

			switch (note.getModelAction())
			{
				case UPDATE:
					noteResponse = getNoteDAC().updateNote(note);
					break;
				case INSERT:
					noteResponse = getNoteDAC().insertNote(note);
					break;
				case DELETE:
					noteResponse = getNoteDAC().deleteNote(note);
					break;
				default:
					break;
			}

			if (noteResponse.isInError())
			{
				response.merge(noteResponse);
				break;
			}

			count += 1;
		}

		return count;
	}

	/**
	 * Maintain the money transfer batch status.
	 *
	 * @param moneyTransferBatch The {@link MoneyTransferBatch}.
	 * @param response The response.
	 * @return The number of actions performed.
	 */
	private int maintainMoneyTransferBatchStatusList(MoneyTransferBatch moneyTransferBatch, InternalResponse response)
	{
		int count = 0;

		// For Each Status...
		for (MoneyTransferBatchStatus moneyTransferBatchStatus : moneyTransferBatch.getStatusList())
		{
			// Make sure we set the parent key
			moneyTransferBatchStatus.setMoneyTransferBatchId(moneyTransferBatch.getId());

			switch (moneyTransferBatchStatus.getModelAction())
			{
				case UPDATE:
					count += updateMoneyTransferBatchStatus(moneyTransferBatchStatus, response);
					break;
				case INSERT:
					count += insertMoneyTransferBatchStatus(moneyTransferBatchStatus, response);
					break;
				case DELETE:
					count += deleteMoneyTransferBatchStatus(moneyTransferBatchStatus, response);
					break;
				default:
					break;
			}
		}

		return count;
	}

	/**
	 * Update a {@link MoneyTransferBatchStatus} object.
	 *
	 * @param moneyTransferBatchStatus The {@link MoneyTransferBatchStatus} object to update.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful updates were performed.
	 */
	private int updateMoneyTransferBatchStatus(MoneyTransferBatchStatus moneyTransferBatchStatus,
			InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doUpdate(getSqlSession(), MONEY_TRANSFER_BATCH_STATUS_STMT_UPDATE,
						moneyTransferBatchStatus, response);

		if (count == 1)
		{
			moneyTransferBatchStatus.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Get the implementation of the {@link IMoneyTransferDAC} to use. Injected by Spring.
	 *
	 * @return The implementation of the {@link IMoneyTransferDAC}.
	 */
	public IMoneyTransferDAC getMoneyTransferDAC()
	{
		return moneyTransferDAC;
	}

	/**
	 * Set the implementation of the {@link IMoneyTransferDAC} to use. Injected by Spring.
	 *
	 * @param moneyTransferDAC The implementation of the {@link IMoneyTransferDAC} interface.
	 */
	public void setMoneyTransferDAC(IMoneyTransferDAC moneyTransferDAC)
	{
		this.moneyTransferDAC = moneyTransferDAC;
	}

	/**
	 * Get the valid sort fields for the money transfer batch inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the money transfer batch inquiry.
	 */
	public Map<String, String> getMoneyTransferBatchInquiryValidSortFields()
	{
		return moneyTransferBatchInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the money transfer batch inquiry. Attribute injected by Spring.
	 *
	 * @param moneyTransferBatchInquiryValidSortFields The valid sort fields for the money transfer batch inquiry to
	 *            set.
	 */
	public void setMoneyTransferBatchInquiryValidSortFields(
			Map<String, String> moneyTransferBatchInquiryValidSortFields)
	{
		this.moneyTransferBatchInquiryValidSortFields = moneyTransferBatchInquiryValidSortFields;
	}

	/**
	 * Get the implementation of the {@link INoteDAC} to use. Injected by Spring.
	 *
	 * @return The implementation of the {@link INoteDAC}.
	 */
	public INoteDAC getNoteDAC()
	{
		return noteDAC;
	}

	/**
	 * Set the implementation of the {@link INoteDAC}. Injected by Spring.
	 *
	 * @param noteDAC The implementation of the {@link INoteDAC} interface.
	 */
	public void setNoteDAC(INoteDAC noteDAC)
	{
		this.noteDAC = noteDAC;
	}

	/**
	 * Delete a {@link MoneyTransferBatch} from the SendSolv system, along with all of its composite associated
	 * objects.
	 * <p>
	 * The delete action will enforce the Optimistic Locking of the row so that an incorrect delete isn't performed. If
	 * the correct version is being deleted, it will delete the {@link MoneyTransferBatch} and all of its associated
	 * tables. A cascade delete has been setup on the database.
	 *
	 * @param moneyTransferBatch The money transfer batch to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 * @see QATMyBatisDacHelper#doRemoveOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse deleteMoneyTransferBatch(MoneyTransferBatch moneyTransferBatch)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemoveOL(getSqlSession(), MONEY_TRANSFER_BATCH_STMT_DELETE, moneyTransferBatch,
				MONEY_TRANSFER_BATCH_STMT_FETCH_VERSION, response);
		return response;
	}

	/**
	 * Fetch a money transfer batch by id.
	 *
	 * @param id The id of the money transfer batch to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link MoneyTransferBatch} along with
	 *         information about the success/failure of the fetch.
	 * @see QATMyBatisDacHelper#doQueryForList(org.apache.ibatis.session.SqlSession, String, Object,
	 *      InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<MoneyTransferBatch> fetchMoneyTransferBatchById(Integer id)
	{
		InternalResultsResponse<MoneyTransferBatch> response = new InternalResultsResponse<MoneyTransferBatch>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), MONEY_TRANSFER_BATCH_STMT_FETCH_BY_ID, id, response);
		return response;
	}

	/**
	 * Fetch all money transfer batches matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link MoneyTransferBatch}es along with
	 *         information about the success/failure of the fetch.
	 * @see PagedResultsDACD#fetchObjectsByRequest(org.apache.ibatis.session.SqlSession,
	 *      com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest, String, String, InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<MoneyTransferBatch> fetchMoneyTransferBatchByRequest(
			MoneyTransferBatchInquiryRequest request)
	{
		InternalResultsResponse<MoneyTransferBatch> response = new InternalResultsResponse<MoneyTransferBatch>();

		/*
		 * Helper method to translation from the "user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getMoneyTransferBatchInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, MONEY_TRANSFER_BATCH_STMT_FETCH_ROW_COUNT,
				MONEY_TRANSFER_BATCH_STMT_FETCH_BY_REQUEST, response);

		return response;
	}

	/**
	 * Insert a {@link MoneyTransferBatch} into the SendSolv system, along with all of its composite associated objects
	 * where the model action attribute has been marked.
	 * <p>
	 * Be careful with the associates. If the model action is not marked as an insert, the system will attempt to
	 * perform the action specified. This could lead to an abnormal database method being applied to the object. A worst
	 * case here, is that the model action is not marked at all, and thus the associated object won't be inserted.
	 * <p>
	 * The insert will first attempt the insert of the {@link MoneyTransferBatch}. If that insert is successful, then
	 * all of the composite associations will be looked at and have their model action performed. Before the action is
	 * performed on them, the parent key will be updated to keep them in sync. If an error is taken during any of the
	 * actions, the method will stop and return with the error. It is assumed that the transaction is being maintained
	 * elsewhere, and that it will be rolled back upon getting an error.
	 *
	 * @param moneyTransferBatch The money transfer batch to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 * @see QATMyBatisDacHelper#doInsert(org.apache.ibatis.session.SqlSession, String, com.qat.framework.model.QATModel,
	 *      InternalResponse)
	 */
	@Override
	public InternalResponse insertMoneyTransferBatch(MoneyTransferBatch moneyTransferBatch)
	{
		Integer insertCount = 0;
		InternalResponse response = new InternalResponse();

		// First insert the root
		// If successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), MONEY_TRANSFER_BATCH_STMT_INSERT, moneyTransferBatch,
						response);

		if (response.isInError())
		{
			return response;
		}

		insertCount += maintainMoneyTransferBatchAssociations(moneyTransferBatch, response);

		if (!response.isInError() && (insertCount > 0))
		{
			moneyTransferBatch.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	/**
	 * Update a {@link MoneyTransferBatch} in the SendSolv system, along with all of its composite associated objects
	 * where the model action attribute has been marked.
	 * <p>
	 * Be careful with the associates. The system will attempt to perform the action specified. This could lead to an
	 * abnormal database method being applied to the object. Make sure that the correct model action has been specified
	 * for all of the composite associated objects.
	 * <p>
	 * The update action will enforce the Optimistic Locking of the row, along with Optimistic locking on all of the
	 * composite associated objects. Pay close attention to the version number of the object that has been marked for an
	 * action.
	 *
	 * @param moneyTransferBatch The money transfer batch to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 * @see QATMyBatisDacHelper#doUpdateOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse updateMoneyTransferBatch(MoneyTransferBatch moneyTransferBatch)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		if (PersistanceActionEnum.UPDATE == moneyTransferBatch.getModelAction())
		{
			// First update the root
			updateCount =
					QATMyBatisDacHelper.doUpdateOL(getSqlSession(), MONEY_TRANSFER_BATCH_STMT_UPDATE,
							moneyTransferBatch,
							MONEY_TRANSFER_BATCH_STMT_FETCH_VERSION, response);

			if (response.isInError())
			{
				return response;
			}
		}

		updateCount += maintainMoneyTransferBatchAssociations(moneyTransferBatch, response);

		if (!response.isInError() && (updateCount > 0))
		{
			moneyTransferBatch.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IMoneyTransferBatchDAC#insertMoneyTransferBatchStatus(com.prosperitasglobal
	 * .sendsolv.model.MoneyTransferBatchStatus)
	 */
	@Override
	public InternalResponse insertMoneyTransferBatchStatus(MoneyTransferBatchStatus moneyTransferBatchStatus)
	{
		InternalResponse response = new InternalResponse();
		insertMoneyTransferBatchStatus(moneyTransferBatchStatus, response);

		return response;
	}
}
