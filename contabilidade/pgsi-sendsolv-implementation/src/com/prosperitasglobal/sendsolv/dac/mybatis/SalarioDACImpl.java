package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class SalarioDACImpl extends SqlSessionDaoSupport implements ISalarioDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "SalarioMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateSalario";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_UPDATE_EMAIL. */
	private static final String CONTACT_STMT_UPDATE_EMAIL = CONTACT_NAMESPACE + "updateEmail";

	/** The Constant CONTACT_STMT_UPDATE_ADDRESS. */
	private static final String CONTACT_STMT_UPDATE_ADDRESS = CONTACT_NAMESPACE + "updateAddress";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessSalario";

	/** The Constant CONTACT_STMT_DELETE_PERSON_CONTACT. */
	private static final String CONTACT_STMT_DELETE_PERSON_CONTACT = CONTACT_NAMESPACE + "deletePersonSalario";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertSalario";

	/** The Constant CONTACT_STMT_INSERT_PHONE. */
	private static final String CONTACT_STMT_INSERT_PHONE = CONTACT_NAMESPACE + "insertPhone";

	/** The Constant CONTACT_STMT_INSERT_EMAIL. */
	private static final String CONTACT_STMT_INSERT_EMAIL = CONTACT_NAMESPACE + "insertEmail";

	/** The Constant CONTACT_STMT_INSERT_ADDRESS. */
	private static final String CONTACT_STMT_INSERT_ADDRESS = CONTACT_NAMESPACE + "insertAddress";

	/** The Constant CONTACT_STMT_FETCH_BY_BUSINESS_ID. */
	private static final String CONTACT_STMT_FETCH_BY_BUSINESS_ID = CONTACT_NAMESPACE + "fetchSalariosByBusinessId";

	/** The Constant CONTACT_STMT_FETCH_BY_PERSON_ID. */
	private static final String CONTACT_STMT_FETCH_BY_PERSON_ID = CONTACT_NAMESPACE + "fetchSalariosByPersonId";

	/** The Constant CONTACT_STMT_FETCH_BY_ID. */
	private static final String CONTACT_STMT_FETCH_BY_ID = CONTACT_NAMESPACE + "fetchSalariosById";

	/** The Constant CONTACT_STMT_FETCH_EMAIL_VERSION. */
	private static final String CONTACT_STMT_FETCH_EMAIL_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberEmail";

	/** The Constant CONTACT_STMT_FETCH_PHONE_VERSION. */
	private static final String CONTACT_STMT_FETCH_PHONE_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberPhone";

	/** The Constant CONTACT_STMT_FETCH_ADDRESS_VERSION. */
	private static final String CONTACT_STMT_FETCH_ADDRESS_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberAddress";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SalarioDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertSalario(com.prosperitasglobal.cbof.model.Salario,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertSalario(Salario salario, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root salario data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, salario, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, salario, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ISalarioDAC#deleteBusinessSalario(com.prosperitasglobal.cbof.model.Salario,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteBusinessSalario(Salario salario, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, salario, response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ISalarioDAC#deletePersonSalario(com.prosperitasglobal.cbof.model.Salario,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deletePersonSalario(Salario salario, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_PERSON_CONTACT, salario, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateSalario(com.prosperitasglobal.cbof.model.Salario,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateSalario(Salario salario, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(salario.getModelAction())
				&& (salario.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, salario, response);

			if (updateCount == 1)
			{
				salario.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchSalarioByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<Salario> fetchSalarioByParent(Integer parentId, BusinessTypeEnum parentType)
	{
		InternalResultsResponse<Salario> response = new InternalResultsResponse<Salario>();

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchSalarioById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Salario> fetchSalarioById(Integer id)
	{
		InternalResultsResponse<Salario> response = new InternalResultsResponse<Salario>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ISalarioDAC#maintainSalarioAssociations(java.util.List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer maintainSalarioAssociations(List<Salario> salarioList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Salarios
		if (ValidationUtil.isNullOrEmpty(salarioList))
		{
			return count;
		}
		// For Each Salario...
		for (Salario salario : salarioList)
		{
			// Make sure we set the parent key
			salario.setParentKey(parentId);

			if (ValidationUtil.isNull(salario.getModelAction()))
			{
				continue;
			}
			switch (salario.getModelAction())
			{
				case INSERT:
					count += insertSalario(salario, associateStatement, response);
					break;
				case UPDATE:
					count += updateSalario(salario, response);
					break;
				case DELETE:
					count += deletePersonSalario(salario, response);
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
