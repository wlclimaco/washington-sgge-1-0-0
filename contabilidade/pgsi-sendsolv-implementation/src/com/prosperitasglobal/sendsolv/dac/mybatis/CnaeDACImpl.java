package com.prosperitasglobal.cbof.dac.mybatis;

import java.util.List;
import java.util.logging.Logger;

import com.prosperitasglobal.sendsolv.dac.ICnaeDAC;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class CnaeDACImpl extends SqlSessionDaoSupport implements ICnaeDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "CnaeMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateCnae";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_UPDATE_EMAIL. */
	private static final String CONTACT_STMT_UPDATE_EMAIL = CONTACT_NAMESPACE + "updateEmail";

	/** The Constant CONTACT_STMT_UPDATE_ADDRESS. */
	private static final String CONTACT_STMT_UPDATE_ADDRESS = CONTACT_NAMESPACE + "updateAddress";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessCnae";

	/** The Constant CONTACT_STMT_DELETE_PERSON_CONTACT. */
	private static final String CONTACT_STMT_DELETE_PERSON_CONTACT = CONTACT_NAMESPACE + "deletePersonCnae";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertCnae";

	/** The Constant CONTACT_STMT_INSERT_PHONE. */
	private static final String CONTACT_STMT_INSERT_PHONE = CONTACT_NAMESPACE + "insertPhone";

	/** The Constant CONTACT_STMT_INSERT_EMAIL. */
	private static final String CONTACT_STMT_INSERT_EMAIL = CONTACT_NAMESPACE + "insertEmail";

	/** The Constant CONTACT_STMT_INSERT_ADDRESS. */
	private static final String CONTACT_STMT_INSERT_ADDRESS = CONTACT_NAMESPACE + "insertAddress";

	/** The Constant CONTACT_STMT_FETCH_BY_BUSINESS_ID. */
	private static final String CONTACT_STMT_FETCH_BY_BUSINESS_ID = CONTACT_NAMESPACE + "fetchCnaesByBusinessId";

	/** The Constant CONTACT_STMT_FETCH_BY_PERSON_ID. */
	private static final String CONTACT_STMT_FETCH_BY_PERSON_ID = CONTACT_NAMESPACE + "fetchCnaesByPersonId";

	/** The Constant CONTACT_STMT_FETCH_BY_ID. */
	private static final String CONTACT_STMT_FETCH_BY_ID = CONTACT_NAMESPACE + "fetchCnaesById";

	/** The Constant CONTACT_STMT_FETCH_EMAIL_VERSION. */
	private static final String CONTACT_STMT_FETCH_EMAIL_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberEmail";

	/** The Constant CONTACT_STMT_FETCH_PHONE_VERSION. */
	private static final String CONTACT_STMT_FETCH_PHONE_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberPhone";

	/** The Constant CONTACT_STMT_FETCH_ADDRESS_VERSION. */
	private static final String CONTACT_STMT_FETCH_ADDRESS_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberAddress";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CnaeDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertCnae(com.prosperitasglobal.cbof.model.Cnae,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertCnae(Cnae cnae, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root cnae data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, cnae, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, cnae, response);

		// Next insert the sub-type
		switch (cnae.getCnaeType())
		{
			case OTHER:
			case PHONE_WORK:
			case MOBILE:
				insertCount +=
						QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT_PHONE, cnae, response);
				break;
			case EMAIL_PERSONAL:
			case EMAIL_WORK:
				insertCount +=
						QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT_EMAIL, cnae, response);
				break;
			case POSTAL_HOME:
			case POSTAL_WORK:
				insertCount +=
						QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT_ADDRESS, cnae, response);
				break;
			default:
				break;
		}

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICnaeDAC#deleteBusinessCnae(com.prosperitasglobal.cbof.model.Cnae,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteBusinessCnae(Cnae cnae, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, cnae, response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICnaeDAC#deletePersonCnae(com.prosperitasglobal.cbof.model.Cnae,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deletePersonCnae(Cnae cnae, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_PERSON_CONTACT, cnae, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateCnae(com.prosperitasglobal.cbof.model.Cnae,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateCnae(Cnae cnae, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(cnae.getModelAction())
				&& (cnae.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, cnae, response);

			if (updateCount == 1)
			{
				cnae.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		// Next update the sub-type
		switch (cnae.getCnaeType())
		{
			case OTHER:
			case PHONE_WORK:
			case MOBILE:
				updateCount +=
						QATMyBatisDacHelper.doUpdateOL(getSqlSession(), CONTACT_STMT_UPDATE_PHONE, cnae,
								CONTACT_STMT_FETCH_PHONE_VERSION, response);
				break;
			case EMAIL_PERSONAL:
			case EMAIL_WORK:
				updateCount +=
						QATMyBatisDacHelper.doUpdateOL(getSqlSession(), CONTACT_STMT_UPDATE_EMAIL, cnae,
								CONTACT_STMT_FETCH_EMAIL_VERSION, response);
				break;
			case POSTAL_HOME:
			case POSTAL_WORK:
				updateCount +=
						QATMyBatisDacHelper.doUpdateOL(getSqlSession(), CONTACT_STMT_UPDATE_ADDRESS, cnae,
								CONTACT_STMT_FETCH_ADDRESS_VERSION, response);
				break;
			default:
				break;
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchCnaeByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<Cnae> fetchCnaeByParent(Integer parentId, BusinessTypeEnum parentType)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		switch (parentType)
		{
			case ORGANIZATION:
				QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_BUSINESS_ID, parentId,
						response);
				break;

			case LOCATION:
				QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_BUSINESS_ID, parentId,
						response);
				break;

			case MEMBER:
				QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_PERSON_ID, parentId,
						response);
				break;
			case UNKNOWN:
				break;
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("parentType for Cnae missing!");
				}
				break;

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchCnaeById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Cnae> fetchCnaeById(Integer id)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICnaeDAC#maintainCnaeAssociations(java.util.List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer maintainCnaeAssociations(List<Cnae> cnaeList, Integer parentId, String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Cnaes
		if (ValidationUtil.isNullOrEmpty(cnaeList))
		{
			return count;
		}
		// For Each Cnae...
		for (Cnae cnae : cnaeList)
		{
			// Make sure we set the parent key
			cnae.setParentKey(parentId);

			if (ValidationUtil.isNull(cnae.getModelAction()))
			{
				continue;
			}
			switch (cnae.getModelAction())
			{
				case INSERT:
					count += insertCnae(cnae, associateStatement, response);
					break;
				case UPDATE:
					count += updateCnae(cnae, response);
					break;
				case DELETE:
					count += deletePersonCnae(cnae, response);
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
