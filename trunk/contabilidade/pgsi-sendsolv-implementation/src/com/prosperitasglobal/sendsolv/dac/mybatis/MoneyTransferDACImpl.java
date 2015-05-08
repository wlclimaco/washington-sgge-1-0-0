package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.sendsolv.dac.IMoneyTransferDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MoneyTransferDACImpl.
 */
public class MoneyTransferDACImpl extends SqlSessionDaoSupport implements IMoneyTransferDAC
{
	/*
	 * Spring Injected Properties
	 */
	/** The valid sort fields for a money transfer inquiry. Will be injected by Spring. */
	private Map<String, String> moneyTransferInquiryValidSortFields;

	/** The implementation of the {@link INoteDAC}. Will be injected by Spring. */
	private INoteDAC noteDAC;

	/*
	 * SQL Statements
	 */
	/** The MoneyTransferMap name space. */
	private static final String MONEY_TRANSFER_NAMESPACE = "MoneyTransferMap.";

	/** The MoneyTransferMap.fetchMoneyTransferById SQL statement. */
	private static final String MONEY_TRANSFER_STMT_FETCH_BY_ID = MONEY_TRANSFER_NAMESPACE + "fetchMoneyTransferById";

	/** The MoneyTransferMap.fetchMoneyTransferRowCount SQL statement. */
	private static final String MONEY_TRANSFER_STMT_FETCH_ROW_COUNT = MONEY_TRANSFER_NAMESPACE
			+ "fetchMoneyTransferRowCount";

	/** The MoneyTransferMap.fetchMoneyTransferByRequest SQL statement. */
	private static final String MONEY_TRANSFER_STMT_FETCH_BY_REQUEST = MONEY_TRANSFER_NAMESPACE
			+ "fetchMoneyTransferByRequest";

	/** The MoneyTransferMap.fetchMoneyTransferVersionNumber SQL statement. */
	private static final String MONEY_TRANSFER_STMT_FETCH_VERSION = MONEY_TRANSFER_NAMESPACE
			+ "fetchMoneyTransferVersionNumber";

	/** The MoneyTransferMap.insertMoneyTransfer SQL statement. */
	private static final String MONEY_TRANSFER_STMT_INSERT = MONEY_TRANSFER_NAMESPACE + "insertMoneyTransfer";

	/** The MoneyTransferMap.updateMoneyTransfer SQL statement. */
	private static final String MONEY_TRANSFER_STMT_UPDATE = MONEY_TRANSFER_NAMESPACE + "updateMoneyTransfer";

	/** The MoneyTransferMap.deleteMoneyTransfer SQL statement. */
	private static final String MONEY_TRANSFER_STMT_DELETE = MONEY_TRANSFER_NAMESPACE + "deleteMoneyTransfer";

	/** The MoneyTransferStatusMap name space. */
	private static final String MONEY_TRANSFER_STATUS_NAMESPACE = "MoneyTransferStatusMap.";

	/** The MoneyTransferStatusMap.insertMoneyTransferStatus SQL statement. */
	private static final String MONEY_TRANSFER_STATUS_STMT_INSERT = MONEY_TRANSFER_STATUS_NAMESPACE
			+ "insertMoneyTransferStatus";

	/** The MoneyTransferStatusMap.updateMoneyTransferStatus SQL statement. */
	private static final String MONEY_TRANSFER_STATUS_STMT_UPDATE = MONEY_TRANSFER_STATUS_NAMESPACE
			+ "updateMoneyTransferStatus";

	/** The MoneyTransferStatusMap.deleteMoneyTransferStatus SQL statement. */
	private static final String MONEY_TRANSFER_STATUS_STMT_DELETE = MONEY_TRANSFER_STATUS_NAMESPACE
			+ "deleteMoneyTransferStatus";

	/** The MoneyTransferTransactionMap name space. */
	private static final String MONEY_TRANSFER_TRANSACTION_NAMESPACE = "MoneyTransferTransactionMap.";

	/** The MoneyTransferStatusMap.insertMoneyTransferTransaction SQL statement. */
	private static final String MONEY_TRANSFER_TRANSACTION_STMT_INSERT = MONEY_TRANSFER_TRANSACTION_NAMESPACE
			+ "insertMoneyTransferTransaction";

	/** The MoneyTransferStatusMap.updateMoneyTransferTransaction SQL statement. */
	private static final String MONEY_TRANSFER_TRANSACTION_STMT_UPDATE = MONEY_TRANSFER_TRANSACTION_NAMESPACE
			+ "updateMoneyTransferTransaction";

	/** The MoneyTransferStatusMap.deleteMoneyTransferTransaction SQL statement. */
	private static final String MONEY_TRANSFER_TRANSACTION_STMT_DELETE = MONEY_TRANSFER_TRANSACTION_NAMESPACE
			+ "deleteMoneyTransferTransaction";

	/**
	 * Delete a {@link MoneyTransferStatus} object.
	 *
	 * @param moneyTransferStatus The {@link MoneyTransferStatus} object to delete.
	 * @param response The {@link InternalResponse} object that is currently being used for the delete. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful deletes were performed.
	 */
	private int deleteMoneyTransferStatus(MoneyTransferStatus moneyTransferStatus, InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doRemove(getSqlSession(), MONEY_TRANSFER_STATUS_STMT_DELETE, moneyTransferStatus,
						response);

		if (count == 1)
		{
			moneyTransferStatus.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Delete a {@link MoneyTransferTransaction} object.
	 *
	 * @param moneyTransferTransaction The {@link MoneyTransferTransaction} object to delete.
	 * @param response The {@link InternalResponse} object that is currently being used for the delete. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful deletes were performed.
	 */
	private int deleteMoneyTransferTransaction(MoneyTransferTransaction moneyTransferTransaction,
			InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doRemove(getSqlSession(), MONEY_TRANSFER_TRANSACTION_STMT_DELETE,
						moneyTransferTransaction,
						response);

		if (count == 1)
		{
			moneyTransferTransaction.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Insert a {@link MoneyTransferStatus} object.
	 *
	 * @param moneyTransferStatus The {@link MoneyTransferStatus} object to insert.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful inserts were performed.
	 */
	private int insertMoneyTransferStatus(MoneyTransferStatus moneyTransferStatus, InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doInsert(getSqlSession(), MONEY_TRANSFER_STATUS_STMT_INSERT, moneyTransferStatus,
						response);

		if (count == 1)
		{
			moneyTransferStatus.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Insert a {@link MoneyTransferTransaction} object.
	 *
	 * @param moneyTransferTransaction The {@link MoneyTransferTransaction} object to insert.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful inserts were performed.
	 */
	private int insertMoneyTransferTransaction(MoneyTransferTransaction moneyTransferTransaction,
			InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doInsert(getSqlSession(), MONEY_TRANSFER_TRANSACTION_STMT_INSERT,
						moneyTransferTransaction, response);

		if (count == 1)
		{
			moneyTransferTransaction.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Maintain the money transfer associations.
	 *
	 * @param moneyTransfer The {@link MoneyTransfer}.
	 * @param response The response.
	 * @return The number of actions performed.
	 */
	private int maintainMoneyTransferAssociations(MoneyTransfer moneyTransfer, InternalResponse response)
	{
		int count = maintainMoneyTransferStatusList(moneyTransfer, response);
		count += maintainMoneyTransferNoteList(moneyTransfer, response);
		return count;
	}

	/**
	 * Maintain the money transfer notes.
	 *
	 * @param moneyTransfer The {@link MoneyTransfer}.
	 * @param response The response.
	 * @return The number of actions performed.
	 */
	private int maintainMoneyTransferNoteList(MoneyTransfer moneyTransfer, InternalResponse response)
	{
		int count = 0;

		// For Each Note...
		for (Note note : moneyTransfer.getNoteList())
		{
			// Make sure we set the parent key
			note.setParentKey(moneyTransfer.getId());
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
	 * Maintain the money transfer status associations.
	 *
	 * @param moneyTransferStatus The {@link MoneyTransferStatus}.
	 * @param response The response.
	 * @return The number of actions performed.
	 */
	private int maintainMoneyTransferStatusAssociations(MoneyTransferStatus moneyTransferStatus,
			InternalResponse response)
	{
		int count = 0;

		/* First maintain the money transfer transaction. */
		if (!ValidationUtil.isNull(moneyTransferStatus.getMoneyTransferTransaction()))
		{
			MoneyTransferTransaction moneyTransferTransaction = moneyTransferStatus.getMoneyTransferTransaction();

			// Make sure we set the parent key
			moneyTransferTransaction.setMoneyTransferStatusId(moneyTransferStatus.getId());

			switch (moneyTransferTransaction.getModelAction())
			{
				case UPDATE:
					count += updateMoneyTransferTransaction(moneyTransferTransaction, response);
					break;
				case INSERT:
					count += insertMoneyTransferTransaction(moneyTransferTransaction, response);
					break;
				case DELETE:
					count += deleteMoneyTransferTransaction(moneyTransferTransaction, response);
					break;
				default:
					break;
			}
		}

		return count;
	}

	/**
	 * Maintain the money transfer statuses.
	 *
	 * @param moneyTransfer The {@link MoneyTransfer}.
	 * @param response The response.
	 * @return The number of actions performed.
	 */
	private int maintainMoneyTransferStatusList(MoneyTransfer moneyTransfer, InternalResponse response)
	{
		int count = 0;

		// For Each Status...
		for (MoneyTransferStatus moneyTransferStatus : moneyTransfer.getStatusList())
		{
			// Make sure we set the parent key
			moneyTransferStatus.setMoneyTransferId(moneyTransfer.getId());

			switch (moneyTransferStatus.getModelAction())
			{
				case UPDATE:
					count += updateMoneyTransferStatus(moneyTransferStatus, response);
					break;
				case INSERT:
					count += insertMoneyTransferStatus(moneyTransferStatus, response);
					break;
				case DELETE:
					count += deleteMoneyTransferStatus(moneyTransferStatus, response);
					break;
				default:
					break;
			}

			count += maintainMoneyTransferStatusAssociations(moneyTransferStatus, response);
		}

		return count;
	}

	/**
	 * Update a {@link MoneyTransferStatus} object.
	 *
	 * @param moneyTransferStatus The {@link MoneyTransferStatus} object to update.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful updates were performed.
	 */
	private int updateMoneyTransferStatus(MoneyTransferStatus moneyTransferStatus, InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doUpdate(getSqlSession(), MONEY_TRANSFER_STATUS_STMT_UPDATE, moneyTransferStatus,
						response);

		if (count == 1)
		{
			moneyTransferStatus.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Update a {@link MoneyTransferTransaction} object.
	 *
	 * @param moneyTransferTransaction The {@link MoneyTransferTransaction} object to update.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful updates were performed.
	 */
	private int updateMoneyTransferTransaction(MoneyTransferTransaction moneyTransferTransaction,
			InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doUpdate(getSqlSession(), MONEY_TRANSFER_TRANSACTION_STMT_UPDATE,
						moneyTransferTransaction,
						response);

		if (count == 1)
		{
			moneyTransferTransaction.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Get the valid sort fields for the money transfer inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the money transfer inquiry.
	 */
	public Map<String, String> getMoneyTransferInquiryValidSortFields()
	{
		return moneyTransferInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the money transfer inquiry. Attribute injected by Spring.
	 *
	 * @param moneyTransferInquiryValidSortFields The valid sort fields for the money transfer inquiry to set.
	 */
	public void setMoneyTransferInquiryValidSortFields(Map<String, String> moneyTransferInquiryValidSortFields)
	{
		this.moneyTransferInquiryValidSortFields = moneyTransferInquiryValidSortFields;
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
	 * Delete a {@link MoneyTransfer} from the SendSolv system, along with all of its composite associated
	 * objects.
	 * <p>
	 * The delete action will enforce the Optimistic Locking of the row so that an incorrect delete isn't performed. If
	 * the correct version is being deleted, it will delete the {@link MoneyTransfer} and all of its associated tables.
	 * A cascade delete has been setup on the database.
	 *
	 * @param moneyTransfer The money transfer to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 * @see QATMyBatisDacHelper#doRemoveOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse deleteMoneyTransfer(MoneyTransfer moneyTransfer)
	{
		InternalResponse response = new InternalResponse();

		QATMyBatisDacHelper.doRemoveOL(getSqlSession(), MONEY_TRANSFER_STMT_DELETE, moneyTransfer,
				MONEY_TRANSFER_STMT_FETCH_VERSION, response);

		return response;
	}

	/**
	 * Fetch a money transfer by id.
	 *
	 * @param id The id of the money transfer to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link MoneyTransfer} along with
	 *         information about the success/failure of the fetch.
	 * @see QATMyBatisDacHelper#doQueryForList(org.apache.ibatis.session.SqlSession, String, Object,
	 *      InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<MoneyTransfer> fetchMoneyTransferById(Integer id)
	{
		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), MONEY_TRANSFER_STMT_FETCH_BY_ID, id, response);
		return response;
	}

	/**
	 * Fetch all money transfers matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link MoneyTransfer}s along with
	 *         information about the success/failure of the fetch.
	 * @see PagedResultsDACD#fetchObjectsByRequest(org.apache.ibatis.session.SqlSession,
	 *      com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest, String, String, InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<MoneyTransfer> fetchMoneyTransferByRequest(MoneyTransferInquiryRequest request)
	{
		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();

		/*
		 * Helper method to translation from the "user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getMoneyTransferInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, MONEY_TRANSFER_STMT_FETCH_ROW_COUNT,
				MONEY_TRANSFER_STMT_FETCH_BY_REQUEST, response);

		return response;
	}

	/**
	 * Insert a {@link MoneyTransfer} into the SendSolv system, along with all of its composite associated objects
	 * where the model action attribute has been marked.
	 * <p>
	 * Be careful with the associates. If the model action is not marked as an insert, the system will attempt to
	 * perform the action specified. This could lead to an abnormal database method being applied to the object. A worst
	 * case here, is that the model action is not marked at all, and thus the associated object won't be inserted.
	 * <p>
	 * The insert will first attempt the insert of the {@link MoneyTransfer}. If that insert is successful, then all of
	 * the composite associations will be looked at and have their model action performed. Before the action is
	 * performed on them, the parent key will be updated to keep them in sync. If an error is taken during any of the
	 * actions, the method will stop and return with the error. It is assumed that the transaction is being maintained
	 * elsewhere, and that it will be rolled back upon getting an error.
	 *
	 * @param moneyTransfer The money transfer to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 * @see QATMyBatisDacHelper#doInsert(org.apache.ibatis.session.SqlSession, String, com.qat.framework.model.QATModel,
	 *      InternalResponse)
	 */
	@Override
	public InternalResponse insertMoneyTransfer(MoneyTransfer moneyTransfer)
	{
		Integer insertCount = 0;
		InternalResponse response = new InternalResponse();

		// First insert the root
		// If successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), MONEY_TRANSFER_STMT_INSERT, moneyTransfer,
						response);

		if (response.isInError())
		{
			return response;
		}

		insertCount += maintainMoneyTransferAssociations(moneyTransfer, response);

		if (!response.isInError() && (insertCount > 0))
		{
			moneyTransfer.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	/**
	 * Update a money transfer and then fetch it and return it.
	 *
	 * @param moneyTransfer The money transfer to update.
	 * @return The {@link InternalResultsResponse} containing information about the success/failure of the update.
	 */
	@Override
	public InternalResultsResponse<MoneyTransfer> updateFetchMoneyTransfer(MoneyTransfer moneyTransfer)
	{
		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();
		InternalResponse updateResponse = updateMoneyTransfer(moneyTransfer);

		if (updateResponse.isInError())
		{
			response.merge(updateResponse);
			return response;
		}

		return fetchMoneyTransferById(moneyTransfer.getId());
	}

	/**
	 * Update a {@link MoneyTransfer} in the SendSolv system, along with all of its composite associated objects
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
	 * @param moneyTransfer The money transfer to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 * @see QATMyBatisDacHelper#doUpdateOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse updateMoneyTransfer(MoneyTransfer moneyTransfer)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		if (PersistanceActionEnum.UPDATE == moneyTransfer.getModelAction())
		{
			// First update the root
			updateCount =
					QATMyBatisDacHelper.doUpdateOL(getSqlSession(), MONEY_TRANSFER_STMT_UPDATE, moneyTransfer,
							MONEY_TRANSFER_STMT_FETCH_VERSION, response);

			if (response.isInError())
			{
				return response;
			}
		}

		updateCount += maintainMoneyTransferAssociations(moneyTransfer, response);

		if (!response.isInError() && (updateCount > 0))
		{
			moneyTransfer.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	@Override
	public InternalResponse insertMoneyTransferStatus(MoneyTransferStatus moneyTransferStatus)
	{
		InternalResponse response = new InternalResponse();
		insertMoneyTransferStatus(moneyTransferStatus, response);

		return response;
	}

}
