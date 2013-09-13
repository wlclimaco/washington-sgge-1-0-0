package com.sensus.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SensusModel;
import com.sensus.common.model.SensusModelOL;
import com.sensus.common.model.request.InquiryRequest;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.query.SearchString;
import com.sensus.common.query.SearchString.SearchType;

/**
 * The QATDACHelper class is a utility type class with various helper methods to be used by all DAC implementation
 * classes. Use of this helper class enables the implementation of standard methods for handling all DBMS type
 * operations that occur at the DAC layer. The business area DAC-Impl class should use these methods instead of invoking
 * operations directly using the Spring SqlSession instance.<br/>
 * A note on rollback:<br\>
 * If you need to manually invoke a rollback from the DAC layer then through a
 * {@link com.sensus.common.exception.SensusDacException} exception.
 */
public final class SensusMyBatisDacHelper
{
	private static final String DATABASE_WILDCARD = "%";

	/** Out oracle constant. */
	private static final String OUT = "out";

	/** Out oracle p_return constant. */
	private static final String PRETURN = "p_return";


	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SensusMyBatisDacHelper.class);

	/**
	 * This helper method provides common update logic that supports not only updating the database but also allows for
	 * optimistic locking errors. The following details the steps taken by this method.
	 * <ol>
	 * <li>Execute the given mybatis update statement.
	 * <li>If the count of rows updated is ZERO then we need to determine if this is an optimistic locking condition or
	 * a the keys for the table row have changed. Both of these conditions would result in the the update count returned
	 * being ZERO.
	 * <li>To determine what has changed, the version number or the keys, the fetchVersionNumber statement is executed.
	 * This is a simple SQL statement typically named "fetchVersionNumber" which retrieves the version-number from the
	 * table. This statement uses the same "where" clause as the where clause used in the Update statement with the
	 * exclusion of the "version" field. The following is a sample of the update where clause:<br/>
	 * <code>
	 * WHERE id = #id# AND version = #version#
	 * </code> <br/>
	 * Followed by the "fetchVersionNumber" statement: <code> <br/>
	 * &lt;select id="fetchVersionNumber" parameterClass="Person" resultClass="int"> <br/>
	 *     SELECT version FROM
	 * PERSON per WHERE per.id = #id# <br/>
	 * &lt;/select>
	 * </code>
	 * <li>The values are compared and the status of the response is set accordingly. Either an OptimisticLockingError
	 * or NoRowsUpdatedError status will be set on the response.
	 * <li>A message is logged for both conditions.
	 * <li>If the condition is as a result of the key fields changing, NoRowsUpdatedError statue, then the consumer of
	 * this method should also log information about the error and include information about the key fields.
	 * <li>If the update was successful then now further actions are taken by this helper method and the update count,
	 * typically a value of 1, will be returned to the consumer.
	 * </ol>
	 *
	 * @param sqlSession The mybatis sqlSession instance.
	 * @param statementName The mybatis statement id to use for the update.
	 * @param model The model instance to be updated into the DBMS.
	 * @param fetchVersioNumberStatementName If the update fails this mybatis statement is then used to retrieve the
	 *            version number for the row.
	 * @param resp An {@link InternalResponse} object instance
	 *
	 * @return The update count. This will be Zero if the update fails.
	 */
	public static int doUpdateOL(SqlSession sqlSession, String statementName, SensusModelOL model,
			String fetchVersioNumberStatementName, InternalResponse resp)
	{
		int updateCount = sqlSession.update(statementName, model);

		// If update count is zero then we might have a problem with the WHERE clause
		// or an optimistic locking situation.
		if (updateCount == 0)
		{
			String logMsg = null;

			// Is the statement included ?
			if (fetchVersioNumberStatementName != null)
			{
				boolean match = checkVersion(sqlSession, fetchVersioNumberStatementName, model, resp);

				// If they match then there is most likely a key problem where the key values have changed which
				// caused the previous statement to return zero rows updated.
				if (match)
				{
					logMsg =
							"Update statement["
									+ statementName
									+ "] failed to update any rows with object type["
									+ model.getClass().getName()
									+ "] version number matches so it looks like the keys have changed or something caused no rows to be updated.";

					resp.setStatus(InternalResponse.Status.NoRowsUpdatedError);
				}
				// If the version number is not the same than an optimistic locking error has occurred.
				// So we'll communicate back to the client an optimistic error.
				else if (resp.getStatus() == InternalResponse.Status.OptimisticLockingError)

				{
					logMsg = "Update statement[" + statementName + "] failed to update any rows with object type["
							+ model.getClass().getName() + "] Model object version=" + model.getVersion()
							+ "] DBMS version=" + resp.getStatusContextMap().get("currentversionnumber").toString()
							+ "]";
				}
				else
				// We couldn't look-up the version number.
				{
					logMsg = "Failed to verify version number using statement name[" + fetchVersioNumberStatementName
							+ "] for object type[" + model.getClass().getName() + "] typcially this indicates that "
							+ "the keys have changes for the table row.";
				}

			}
			else
			{
				resp.setStatus(InternalResponse.Status.NoRowsUpdatedError);

				logMsg = "Update statement[" + statementName + "] failed to update any rows with object type["
						+ model.getClass().getName() + "] and no mybatis version number fetch statement was specified.";
			}

			LOG.error(logMsg);
		}

		return updateCount;
	}

	/**
	 * Used to check if a given model object version number matches the value on the database.<br/>
	 * Using the given parameters values the statement is executed and the version number is returned.<br/>
	 * If the version number fails to be returned then a InternalResponse.Status.VersionNotFoundError status
	 * will be set in the response object. If the version number is found it is compared again the parameter version
	 * number.<br/>
	 * If they match this method simple returns true.<br/>
	 * If they don't match then this method sets the response status to
	 * InternalResponse.Status.OptimisticLockingError and returns false.<br/>
	 * When there is optimistic locking error the version number from the database is posted into the response context
	 * using "currentversionnumber" as the map key.
	 *
	 * @param sqlSession The SqlSession to use for statement execution.
	 * @param fetchVersioNumberStatementName The mybatis statement which will return the version number.
	 * @param model The QATModelOL instance contains key property values and version information.
	 * @param resp The response to be populated based on the results.
	 * @return true, If successful indicating the versions match.
	 */
	public static boolean checkVersion(SqlSession sqlSession,
			String fetchVersioNumberStatementName, SensusModelOL model, InternalResponse resp)
	{
		// Try and get the version number for this root model object.
		Integer currentVersionNumber = (Integer)SensusMyBatisDacHelper.doQueryForObject(sqlSession,
				fetchVersioNumberStatementName, model);

		// If the version number is null than the statement failed to return anything
		// which is probably a problem since this may indicate that the root object/row does not exist.
		if (currentVersionNumber == null)
		{
			resp.setStatus(InternalResponse.Status.VersionNotFoundError);
			return false;
		}
		// If they don't match then someone/thing updated the root since we last read the data
		// and that is an optimistic locking error.
		else if (model.getVersion().equals(currentVersionNumber) == false)
		{
			resp.setStatus(InternalResponse.Status.OptimisticLockingError);
			resp.addStatusContextObject("currentversionnumber", currentVersionNumber);
			return false;
		}

		return true;
	}

	/**
	 * This helper method is used to update a model object in the DBMS.<br/>
	 * This method is different from the {@link #doUpdateOL(SqlSession,String,SensusModelOL,String,InternalResponse)
	 * doUpdateOL * } method in that
	 * this method does not handle or support optimistic locking conditions.
	 * <ol>
	 * <li>Execute the given mybatis update statement.
	 * <li>If the results of the statement execution indicate that no rows where updated then the NoRowsRemovedError
	 * status is set upon the response object.
	 * <li>Return the count of rows updated.
	 * </ol>
	 *
	 * @param sqlSession The mybatis SqlSession instance.
	 * @param statementName The mybatis statement id to use for the update
	 * @param model The model object to be update
	 * @param resp An {@link InternalResponse} object instance
	 *
	 * @return The count of rows updated.
	 */
	public static int doUpdate(SqlSession sqlSession, String statementName, SensusModel model,
			InternalResponse resp)
	{
		int updateCount = sqlSession.update(statementName, model);

		if (updateCount == 0)
		{
			if (model instanceof SensusModelOL)
			{
				String logMsg =
						"Update statement["
								+ statementName
								+ "] failed to update any rows with object type["
								+ model.getClass().getName()
								+ "] which is an instance of QATModelOL.  YOU SHOULD BE USING THE doUpdateOL METHOD INSTEAD !!!";
				LOG.error(logMsg);
			}

			resp.setStatus(InternalResponse.Status.NoRowsUpdatedError);
		}

		return updateCount;
	}

	/**
	 * This helper method is used to update into the DBMS via HashMap.<br/>
	 * <ol>
	 * <li>Execute the given mybatis update statement.
	 * <li>If the results of the statement execution indicate that no rows where updated then the NoRowsRemovedError
	 * status is set upon the response object.
	 * <li>Return the count of rows updated.
	 * </ol>
	 *
	 * @param sqlSession The mybatis SqlSession instance.
	 * @param statementName The mybatis statement id to use for the update
	 * @param hashMap The HashMap values to use for the update
	 * @param resp An {@link InternalResponse} object instance
	 *
	 * @return The count of rows updated.
	 */
	public static int doUpdate(SqlSession sqlSession, String statementName, HashMap<?, ?> hashMap,
			InternalResponse resp)
	{
		int updateCount = sqlSession.update(statementName, hashMap);

		if (updateCount == 0)
		{
			resp.setStatus(InternalResponse.Status.NoRowsUpdatedError);
		}

		return updateCount;
	}

	/**
	 * This helper method is used insert a model object into the DBMS.<br/>
	 * The following details the steps taken by this method.
	 * <ol>
	 * <li>Execute the given mybatis insert statement.
	 * <li>Return the object results from the insert method invocation.
	 * </ol>
	 *
	 * @param sqlSession The mybatis SqlSession instance.
	 * @param statementName The mybatis statement id to use for the insert
	 * @param model The model object to be inserted
	 * @param resp An {@link InternalResponse} object instance
	 *
	 * @return the int
	 */
	public static int doInsert(SqlSession sqlSession, String statementName, SensusModel model,
			InternalResponse resp)
	{
		// Do the insert
		int insertCount = sqlSession.insert(statementName, model);

		if (insertCount == 0)
		{
			resp.setStatus(InternalResponse.Status.NoRowsInsertedError);
		}

		return insertCount;
	}

	/**
	 * This helper method is used insert data into the DBMS via HashMap.<br/>
	 * The following details the steps taken by this method.
	 * <ol>
	 * <li>Execute the given mybatis insert statement.
	 * <li>Return the object results from the insert method invocation.
	 * </ol>
	 *
	 * @param sqlSession The mybatis SqlSession instance.
	 * @param statementName The mybatis statement id to use for the insert
	 * @param hashMap The HashMap to be used for the insert
	 * @param resp An {@link InternalResponse} object instance
	 *
	 * @return The object returned from the insert statement. The actual value depends on the insert statement executed.
	 */
	public static Object doInsert(SqlSession sqlSession, String statementName, HashMap<?, ?> hashMap,
			InternalResponse resp)
	{
		// Do the insert
		Object obj = sqlSession.insert(statementName, hashMap);

		return obj;
	}

	/**
	 * Execute a sql statement that performs a delete. Note there is no parameter object passed into the statement which
	 * means only the statementName will be executed.<br/>
	 * <b>Also note,</b> there is no special logic for model objects supporting/requiring optimistic locking. See
	 * {@link #doRemoveOL(SqlSession, String, SensusModelOL, String, InternalResponse)} for more information
	 * about
	 * removing model objects.<br/>
	 *
	 * @param sqlSession the sql map client template
	 * @param statementName the statement name
	 * @param resp the resp
	 *
	 * @return the int
	 */
	public static int doRemove(SqlSession sqlSession, String statementName, InternalResponse resp)
	{
		// Do the delete
		int deleteCount = sqlSession.delete(statementName);

		if (deleteCount == 0)
		{
			resp.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return deleteCount;
	}

	/**
	 * Similar to the other Remove methods however, this method supports the use of optimistic locking when the
	 * statement fails to delete any rows. If the delete count is zero than similar to the
	 * {@link #doUpdateOL(SqlSession, String, SensusModelOL, String ,InternalResponse )} method a
	 * check will be
	 * performed to determine if the version number of the key values on the table have changed.
	 *
	 * @param sqlSession The mybatis SqlSession instance.
	 * @param statementName The mybatis statement id to use for the delete.
	 * @param model The model instance to be used as a parameter into the statement.
	 * @param fetchVersioNumberStatementName If the delete fails this mybatis statement is then used to retrieve the
	 *            version number for the row.
	 *
	 * @return delete count from the SQL statement execution.
	 */
	public static int doRemoveOL(SqlSession sqlSession, String statementName, SensusModelOL model,
			String fetchVersioNumberStatementName, InternalResponse resp)
	{
		// Do the delete
		int deleteCount = sqlSession.delete(statementName, model);

		// If update count is zero then we might have a problem with the WHERE clause
		// or an optimistic locking situation.
		if (deleteCount == 0)
		{
			String logMsg = null;

			if (fetchVersioNumberStatementName != null)
			{
				boolean match = checkVersion(sqlSession, fetchVersioNumberStatementName, model, resp);

				if (match)
				{
					logMsg = "Remove statement[" + statementName + "] failed to remove any rows with object type["
							+ model.getClass().getName()
							+ "] version number matches so it looks like the keys have changed.";

					resp.setStatus(InternalResponse.Status.NoRowsRemovedError);
				}
				else if (resp.getStatus() == InternalResponse.Status.OptimisticLockingError)
				{
					logMsg = "Remove statement[" + statementName + "] failed to delete any rows with object type["
							+ model.getClass().getName() + "] Model object version=" + model.getVersion()
							+ "] DBMS version=" + resp.getStatusContextMap().get("currentversionnumber").toString()
							+ "]";
				}
				else
				// We couldn't look-up the version number.
				{
					logMsg = "Failed to verify version number using statement name[" + fetchVersioNumberStatementName
							+ "] for object type[" + model.getClass().getName() + "]";
				}
			}
			else
			{
				resp.setStatus(InternalResponse.Status.NoRowsRemovedError);

				logMsg = "Remove statement[" + statementName + "] failed to delete any rows with object type["
						+ model.getClass().getName() + "] and no mybatis version number fetch statement was specified.";
			}

			LOG.error(logMsg);
		}

		return deleteCount;
	}

	/**
	 * This helper method is used delete a model object from the DBMS.<br/>
	 * This method is different from the {@link #doRemoveOL(SqlSession, String, SensusModelOL, String, InternalResponse )}
	 * method in that this
	 * method does not handle or support optimistic locking conditions. This method performs the following steps:
	 * <ol>
	 * <li>Execute the given mybatis remove statement.
	 * <li>If the results of the statement execution indicate that no rows where deleted then the NoRowsRemovedError
	 * status is set upon the response object.
	 * <li>Return the count of rows deleted.
	 * </ol>
	 *
	 * @param sqlSession The mybatis SqlSession instance.
	 * @param statementName The mybatis statement id to use for the delete
	 * @param model The model object to be used as a parameter for the statement
	 * @param resp An {@link InternalResponse} object instance
	 *
	 * @return The count of rows removed.
	 */
	public static int doRemove(SqlSession sqlSession, String statementName, SensusModel model,
			InternalResponse resp)
	{
		// Do the delete
		int deleteCount = sqlSession.delete(statementName, model);

		if (deleteCount == 0)
		{
			if (model instanceof SensusModelOL)
			{
				String logMsg =
						"Remove statement["
								+ statementName
								+ "] failed to remove any rows with object type["
								+ model.getClass().getName()
								+ "] which is an instance of QATModelOL.  YOU SHOULD BE USING THE doRemoveOL METHOD INSTEAD !!!";
				LOG.error(logMsg);
			}

			resp.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return deleteCount;
	}

	/**
	 * This helper method is used delete data from the DBMS via HashMap<br/>
	 * <li>Execute the given mybatis remove statement. <li>If the results of the statement execution indicate that no
	 * rows where deleted then the NoRowsRemovedError status is set upon the response object. <li>Return the count of
	 * rows deleted. </ol>
	 *
	 * @param sqlSession The mybatis SqlSession instance.
	 * @param statementName The mybatis statement id to use for the delete
	 * @param hashMap The HashMap to be used as a parameter for the statement
	 * @param resp An {@link InternalResponse} object instance
	 *
	 * @return The count of rows removed.
	 */
	public static int doRemove(SqlSession sqlSession, String statementName, HashMap<?, ?> hashMap,
			InternalResponse resp)
	{
		// Do the delete
		int deleteCount = sqlSession.delete(statementName, hashMap);

		if (deleteCount == 0)
		{
			resp.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return deleteCount;
	}

	/**
	 * Query for a list of objects.
	 *
	 * @param sqlSession A SqlSession instance.
	 * @param statementName The mybatis statement name
	 * @param resp An instance of an InternalResponse object.
	 *
	 * @return An instance of a InternalResponse object. This is the same instance as the one passed in. If the object
	 *         is not found then the response collection will be empty and a NoRowsFoundErroor will be posted to the
	 *         response.
	 */
	@SuppressWarnings("unchecked")
	public static InternalResultsResponse doQueryForList(SqlSession sqlSession,
			String statementName, InternalResultsResponse resp)
	{
		List list = sqlSession.selectList(statementName);

		if (list.size() > 0)
		{
			resp.getResultsList().addAll(list);
		}
		else
		{
			resp.setStatus(InternalResponse.Status.NoRowsFoundError);
		}

		return resp;
	}

	/**
	 * Query for a list of objects and pass into the statement and object parameter.
	 *
	 * @param sqlSession A SqlSession instance.
	 * @param statementName The mybatis statement name
	 * @param parameter The object parameter to be used within the statement execution.
	 * @param resp An instance of an InternalResponse object.
	 *
	 * @return An instance of a InternalResponse object. This is the same instance as the one passed in. If the object
	 *         is not found then the response collection will be empty and a NoRowsFoundErroor will be posted to the
	 *         response.
	 */
	@SuppressWarnings("unchecked")
	public static InternalResultsResponse doQueryForList(SqlSession sqlSession,
			String statementName, Object parameter, InternalResultsResponse resp)
	{
		List list = sqlSession.selectList(statementName, parameter);

		if (list.size() > 0)
		{
			resp.getResultsList().addAll(list);
		}
		else
		{
			resp.setStatus(InternalResponse.Status.NoRowsFoundError);
		}

		return resp;
	}

	/**
	 * Do query for list.
	 *
	 * @param sqlSession the sql map client template
	 * @param statementName the statement name
	 * @param parameter the HashMap parameter
	 * @return the list
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static List doQueryForList(SqlSession sqlSession, String statementName, HashMap parameter)
	{
		List result = sqlSession.selectList(statementName, parameter);

		if (((result == null) || (result.size() < 1)) && parameter.containsKey(OUT))
		{
			result = (List)parameter.get(OUT);
		}
		return result;
	}

	/**
	 * Query for a single object.
	 *
	 * @param sqlSession A SqlSession instance.
	 * @param statementName The mybatis statement name
	 * @param resp An instance of an InternalResponse object.
	 *
	 * @return An instance of a InternalResponse object. This is the same instance as the one passed in. If the object
	 *         is not found then the response collection will be empty and a NoRowsFoundErroor will be posted to the
	 *         response.
	 */
	@SuppressWarnings("unchecked")
	public static InternalResultsResponse<Object> doQueryForObject(SqlSession sqlSession,
			String statementName, InternalResultsResponse<Object> resp)
	{
		Object obj = sqlSession.selectOne(statementName);

		if (obj != null)
		{
			resp.addResult(obj);
		}
		else
		{
			resp.setStatus(InternalResponse.Status.NoRowsFoundError);
		}

		return resp;
	}

	/**
	 * Query for a single object with the given parameter.
	 *
	 * @param sqlSession A SqlSession instance.
	 * @param statementName The mybatis statement name
	 * @param parameter The object parameter to be used within the statement execution.
	 * @param resp An instance of an InternalResponse object.
	 *
	 * @return An instance of a InternalResponse object. This is the same instance as the one passed in. If the object
	 *         is not found then the response collection will be empty and a NoRowsFoundErroor will be posted to the
	 *         response.
	 */
	@SuppressWarnings("unchecked")
	public static InternalResultsResponse<Object> doQueryForObject(SqlSession sqlSession,
			String statementName, Object parameter, InternalResultsResponse<Object> resp)
	{
		Object obj = sqlSession.selectOne(statementName, parameter);

		if (obj != null)
		{
			resp.addResult(obj);
		}
		else
		{
			resp.setStatus(InternalResponse.Status.NoRowsFoundError);
		}

		return resp;
	}

	/**
	 * This queryForObject method enables a consumer to execute an mybatis statement and simple return the object from
	 * the operation. No automatic error handling or anything else is done. This method should only be used when the
	 * consumer is not making use of an InternalResponse object and simply wants the results from the query. <br/>
	 * A good example of this is some sort of "select count" statement.
	 *
	 * @param sqlSession the sql map client template
	 * @param statementName the statement name
	 * @param parameter the parameter
	 *
	 * @return the object
	 */
	public static Object doQueryForObject(SqlSession sqlSession, String statementName, Object parameter)
	{
		return sqlSession.selectOne(statementName, parameter);
	}

	/**
	 * Do query for object.
	 *
	 * @param sqlSession the sql map client template
	 * @param statementName the statement name
	 * @return the object
	 */
	public static Object doQueryForObject(SqlSession sqlSession, String statementName)
	{
		return sqlSession.selectOne(statementName);
	}

	/**
	 * This queryForObject method enables a consumer to execute an mybatis statement and simple return the object from
	 * the operation. No automatic error handling or anything else is done. This method should only be used when the
	 * consumer is not making use of an InternalResponse object and simply wants the results from the query. <br/>
	 * A good example of this is some sort of "select count" statement.
	 *
	 * For Oracle stored procedures use "out" as parameter value for cursor, or "preturn" as parameter value for
	 * Integer ids.
	 *
	 * @param sqlSession the sql map client template
	 * @param statementName the statement name
	 * @param parameter the HashMap parameter
	 *
	 * @return the object
	 * @throws SQLException
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static Object doQueryForObject(SqlSession sqlSession, String statementName, HashMap parameter)
	{
		Object result = sqlSession.selectOne(statementName, parameter);

		/** Treat OUT parameter */
		if ((result == null) && parameter.containsKey(OUT))
		{
			if (parameter.containsKey(PRETURN) &&
					(parameter.get(PRETURN) != null))
			{
				return parameter.get(PRETURN);
			}
			else
			{
				List resultObject = (ArrayList)parameter.get(OUT);

				if ((resultObject != null) && (resultObject.size() > 0))
				{
					return resultObject.get(0);
				}
			}
		}
		else
		{
			if ((result == null) && parameter.containsKey(PRETURN))
			{
				Object resultObject = parameter.get(PRETURN);
				return resultObject;
			}
		}
		return result;
	}

	/**
	 * Do query for list.
	 *
	 * @param sqlSession the sql map client template
	 * @param statementName the statement name
	 * @param parameter the parameter
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public static List doQueryForList(SqlSession sqlSession, String statementName, Object parameter)
	{
		return sqlSession.selectList(statementName, parameter);
	}

	/**
	 * Do query for list.
	 *
	 * @param sqlSession the sql map client template
	 * @param statementName the statement name
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public static List doQueryForList(SqlSession sqlSession, String statementName)
	{
		return sqlSession.selectList(statementName);
	}

	public static void initializeDefaultPaging(InquiryRequest inquiryRequest)
	{
		// Default the page size if for some reason the page size is zero than default it to 20
		if (inquiryRequest.getPageSize() == 0)
		{
			inquiryRequest.setPageSize(20);
		}

		// If the start-row is set and the end-row is not than fix the end-row.
		if ((inquiryRequest.getStartRow() > 0)
				&& ((inquiryRequest.getEndRow() == 0) || (inquiryRequest.getEndRow() <= inquiryRequest.getStartRow())))
		{
			inquiryRequest.setEndRow(inquiryRequest.getStartRow() + inquiryRequest.getPageSize());
		}

		// If the end row is not set then default it to the page size.
		// Set up for row based paging.
		if (inquiryRequest.getEndRow() == 0)
		{
			inquiryRequest.setEndRow(inquiryRequest.getPageSize());
		}
	}

	// [start] Helper methods for Dynamic Search String object preparation

	/**
	 * Escapes characters that will be used in a "Like" query. Characters that are escaped are: '%', '_' and '\'.
	 * The '%' & '_' are wild card characters, the \ is escaped as it will be used as the escape character. If a null is
	 * passed in a null will be returned, if the String does not contain any special characters a new String will be
	 * returned that will be equal to the passed in String. If one or more special characters are included in the
	 * passed in text they will all be escaped.
	 * <p/>
	 * Note the query that uses this value must denote the character that is used as the escape character by including
	 * an escape clause associated with each "Like" condition with escaped test. Example:
	 * <p/>
	 * <code>
	 * COUNTY_DESC LIKE ?    ESCAPE '\'
	 * </code>
	 *
	 * @param text The text to be escaped.
	 * @return The escaped text, or null if null was passed in.
	 */
	public static String escapeSpecialCharacters(String text)
	{
		if (StringUtils.isBlank(text))
		{
			return text;
		}
		return text.replaceAll("(%|_|\\\\)", "\\\\$1");
	}

	/**
	 * Will escape any special characters and suffix the text with a % sign.
	 * See the {@link #escapeSpecialCharacters(String)} method for additional details around escaping
	 * characters and the impact on the underlying queries.
	 *
	 * @param text The text that will be escaped and suffixed with the % wild card character.
	 * @return The text escaped and suffixed with the % wild card character. Null will be returned if a null is passed
	 *         in. An empty string will NOT be suffixed with the wild card character.
	 * @see #escapeSpecialCharacters(String)
	 */
	public static String prepareStartsWithCriteria(String text)
	{
		if (StringUtils.isBlank(text))
		{
			return text;
		}
		return escapeSpecialCharacters(text) + DATABASE_WILDCARD;
	}

	/**
	 * Will escape any special characters as well as prefix and suffix the text with a % sign.
	 * See the {@link #escapeSpecialCharacters(String)} method for additional details around escaping
	 * characters and the impact on the underlying queries.
	 *
	 * @param text The text that will be escaped and suffixed with the % wild card character.
	 * @return The text escaped and suffixed with the % wild card character. Null will be returned if a null is passed
	 *         in. An empty string will NOT be suffixed with the wild card character.
	 * @see #escapeSpecialCharacters(String)
	 */
	public static String prepareContainsCriteria(String text)
	{
		if (StringUtils.isBlank(text))
		{
			return text;
		}
		return DATABASE_WILDCARD + escapeSpecialCharacters(text) + DATABASE_WILDCARD;
	}

	/**
	 * Will escape any special characters and prefix the text with a % sign.
	 * See the {@link #escapeSpecialCharacters(String)} method for additional details around escaping
	 * characters and the impact on the underlying queries.
	 *
	 * @param text The text that will be escaped and prefixed with the % wild card character.
	 * @return The text escaped and prefixed with the % wild card character. Null will be returned if a null is passed
	 *         in. An empty string will NOT be prefixed with the wild card character.
	 * @see #escapeSpecialCharacters(String)
	 */
	public static String prepareEndsWithCriteria(String text)
	{
		if (StringUtils.isBlank(text))
		{
			return text;
		}
		return DATABASE_WILDCARD + escapeSpecialCharacters(text);
	}

	/**
	 * Uses the metadata that is part of the {@link SearchString} to update the {@link SearchString#getWorkingValue()}
	 * that can than be used in the query.
	 * <p/>
	 * Processing Rules:<br/>
	 * <ul>
	 * <li>No Action is taken if the searchString or searchString's value is null</li>
	 * <li>If a case insensitive search is desired, the working value will be upper cased prior to subsequent edits.</li>
	 * <li>If a {@link SearchType#STARTS_WITH} search is specified the working value will be updated according to the
	 * {@link #prepareStartsWithCriteria(String)} method.</li>
	 * <li>If a {@link SearchType#ENDS_WITH} search is specified the working value will be updated according to the
	 * {@link #prepareEndsWithCriteria(String)} method.</li>
	 * <li>If a {@link SearchType#CONTAINS} search is specified the working value will be updated according to the
	 * {@link #prepareContainsCriteria(String)} method.</li>
	 * <li>No special logic, other than the case sensitivity, is performed when an {@link SearchType#EXACT_MATCH} is
	 * requested. It is assumed that an equal condition will be used in the sql query. If an equal condition is not
	 * used, unexpected results could occur if the search value contains special characters that are not escaped.</li>
	 * </ul>
	 *
	 * @param searchString The DynamicSearchString that will be used and updated.
	 */
	public static void prepareDynamicSearchString(SearchString searchString)
	{
		if ((searchString == null) || StringUtils.isBlank(searchString.getSearchValue()))
		{
			return;
		}
		searchString.setWorkingValue(searchString.getSearchValue());
		if (!searchString.isCaseSensitive())
		{
			searchString.setWorkingValue(searchString.getSearchValue().toUpperCase());
		}

		if (searchString.getSearchType() == SearchType.STARTS_WITH)
		{
			searchString.setWorkingValue(prepareStartsWithCriteria(searchString.getWorkingValue()));
			return;
		}
		if (searchString.getSearchType() == SearchType.ENDS_WITH)
		{
			searchString.setWorkingValue(prepareEndsWithCriteria(searchString.getWorkingValue()));
			return;
		}
		if (searchString.getSearchType() == SearchType.CONTAINS)
		{
			searchString.setWorkingValue(prepareContainsCriteria(searchString.getWorkingValue()));
			return;
		}
	}

	// [end] Helper methods for Dynamic Search String object preparation
}
