package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import com.prosperitasglobal.sendsolv.dac.IFieldDAC;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class FieldDACImpl extends SqlSessionDaoSupport implements IFieldDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "FieldMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateField";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_UPDATE_EMAIL. */
	private static final String CONTACT_STMT_UPDATE_EMAIL = CONTACT_NAMESPACE + "updateEmail";

	/** The Constant CONTACT_STMT_UPDATE_ADDRESS. */
	private static final String CONTACT_STMT_UPDATE_ADDRESS = CONTACT_NAMESPACE + "updateAddress";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessField";

	/** The Constant CONTACT_STMT_DELETE_PERSON_CONTACT. */
	private static final String CONTACT_STMT_DELETE_PERSON_CONTACT = CONTACT_NAMESPACE + "deletePersonField";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertField";

	/** The Constant CONTACT_STMT_INSERT_PHONE. */
	private static final String CONTACT_STMT_INSERT_PHONE = CONTACT_NAMESPACE + "insertPhone";

	/** The Constant CONTACT_STMT_INSERT_EMAIL. */
	private static final String CONTACT_STMT_INSERT_EMAIL = CONTACT_NAMESPACE + "insertEmail";

	/** The Constant CONTACT_STMT_INSERT_ADDRESS. */
	private static final String CONTACT_STMT_INSERT_ADDRESS = CONTACT_NAMESPACE + "insertAddress";

	/** The Constant CONTACT_STMT_FETCH_BY_BUSINESS_ID. */
	private static final String CONTACT_STMT_FETCH_BY_BUSINESS_ID = CONTACT_NAMESPACE + "fetchFieldsByBusinessId";

	/** The Constant CONTACT_STMT_FETCH_BY_PERSON_ID. */
	private static final String CONTACT_STMT_FETCH_BY_PERSON_ID = CONTACT_NAMESPACE + "fetchFieldsByPersonId";

	/** The Constant CONTACT_STMT_FETCH_BY_ID. */
	private static final String CONTACT_STMT_FETCH_BY_ID = CONTACT_NAMESPACE + "fetchFieldsById";

	/** The Constant CONTACT_STMT_FETCH_EMAIL_VERSION. */
	private static final String CONTACT_STMT_FETCH_EMAIL_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberEmail";

	/** The Constant CONTACT_STMT_FETCH_PHONE_VERSION. */
	private static final String CONTACT_STMT_FETCH_PHONE_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberPhone";

	/** The Constant CONTACT_STMT_FETCH_ADDRESS_VERSION. */
	private static final String CONTACT_STMT_FETCH_ADDRESS_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberAddress";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FieldDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertField(com.prosperitasglobal.cbof.model.Field,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertField(Field field, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root field data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, field, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, field, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IFieldDAC#deleteBusinessField(com.prosperitasglobal.cbof.model.Field,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteBusinessField(Field field, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, field, response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IFieldDAC#deletePersonField(com.prosperitasglobal.cbof.model.Field,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deletePersonField(Field field, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_PERSON_CONTACT, field, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateField(com.prosperitasglobal.cbof.model.Field,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateField(Field field, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(field.getModelAction())
				&& (field.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, field, response);

			if (updateCount == 1)
			{
				field.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchFieldByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<Field> fetchFieldByParent(Integer parentId, BusinessTypeEnum parentType)
	{
		InternalResultsResponse<Field> response = new InternalResultsResponse<Field>();

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchFieldById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Field> fetchFieldById(Integer id)
	{
		InternalResultsResponse<Field> response = new InternalResultsResponse<Field>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IFieldDAC#maintainFieldAssociations(java.util.List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer maintainFieldAssociations(List<Field> fieldList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Fields
		if (ValidationUtil.isNullOrEmpty(fieldList))
		{
			return count;
		}
		// For Each Field...
		for (Field field : fieldList)
		{
			// Make sure we set the parent key
			field.setParentKey(parentId);

			if (ValidationUtil.isNull(field.getModelAction()))
			{
				continue;
			}
			switch (field.getModelAction())
			{
				case INSERT:
					count += insertField(field, associateStatement, response);
					break;
				case UPDATE:
					count += updateField(field, response);
					break;
				case DELETE:
					count += deletePersonField(field, response);
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
