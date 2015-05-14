package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.dac.IContasReceberDAC;
import com.prosperitasglobal.sendsolv.model.ContaReceber;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class ContasReceberDACImpl extends SqlSessionDaoSupport implements IContasReceberDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "ContasReceberMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateContasReceber";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_UPDATE_EMAIL. */
	private static final String CONTACT_STMT_UPDATE_EMAIL = CONTACT_NAMESPACE + "updateEmail";

	/** The Constant CONTACT_STMT_UPDATE_ADDRESS. */
	private static final String CONTACT_STMT_UPDATE_ADDRESS = CONTACT_NAMESPACE + "updateAddress";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE
			+ "deleteBusinessContasReceber";

	/** The Constant CONTACT_STMT_DELETE_PERSON_CONTACT. */
	private static final String CONTACT_STMT_DELETE_PERSON_CONTACT = CONTACT_NAMESPACE + "deletePersonContasReceber";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertContasReceber";

	/** The Constant CONTACT_STMT_INSERT_PHONE. */
	private static final String CONTACT_STMT_INSERT_PHONE = CONTACT_NAMESPACE + "insertPhone";

	/** The Constant CONTACT_STMT_INSERT_EMAIL. */
	private static final String CONTACT_STMT_INSERT_EMAIL = CONTACT_NAMESPACE + "insertEmail";

	/** The Constant CONTACT_STMT_INSERT_ADDRESS. */
	private static final String CONTACT_STMT_INSERT_ADDRESS = CONTACT_NAMESPACE + "insertAddress";

	/** The Constant CONTACT_STMT_FETCH_BY_BUSINESS_ID. */
	private static final String CONTACT_STMT_FETCH_BY_BUSINESS_ID = CONTACT_NAMESPACE
			+ "fetchContasRecebersByBusinessId";

	/** The Constant CONTACT_STMT_FETCH_BY_PERSON_ID. */
	private static final String CONTACT_STMT_FETCH_BY_PERSON_ID = CONTACT_NAMESPACE + "fetchContasRecebersByPersonId";

	/** The Constant CONTACT_STMT_FETCH_BY_ID. */
	private static final String CONTACT_STMT_FETCH_BY_ID = CONTACT_NAMESPACE + "fetchContasRecebersById";

	/** The Constant CONTACT_STMT_FETCH_EMAIL_VERSION. */
	private static final String CONTACT_STMT_FETCH_EMAIL_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberEmail";

	/** The Constant CONTACT_STMT_FETCH_PHONE_VERSION. */
	private static final String CONTACT_STMT_FETCH_PHONE_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberPhone";

	/** The Constant CONTACT_STMT_FETCH_ADDRESS_VERSION. */
	private static final String CONTACT_STMT_FETCH_ADDRESS_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberAddress";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ContasReceberDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertContasReceber(com.prosperitasglobal.cbof.model.
	 * ContasReceber,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertContasReceber(ContaReceber contasReceber, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root contasReceber data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, contasReceber, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, contasReceber, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IContasReceberDAC#deleteBusinessContasReceber(com.prosperitasglobal.cbof.model.
	 * ContasReceber,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteBusinessContasReceber(ContaReceber contasReceber, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, contasReceber,
				response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IContasReceberDAC#deletePersonContasReceber(com.prosperitasglobal.cbof.model.
	 * ContasReceber,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deletePersonContasReceber(ContaReceber contasReceber, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_PERSON_CONTACT, contasReceber,
				response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateContasReceber(com.prosperitasglobal.cbof.model.
	 * ContasReceber,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateContasReceber(ContaReceber contasReceber, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(contasReceber.getModelAction())
				&& (contasReceber.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, contasReceber, response);

			if (updateCount == 1)
			{
				contasReceber.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchContasReceberByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<ContaReceber> fetchContasReceberByParent(Integer parentId,
			BusinessTypeEnum parentType)
	{
		InternalResultsResponse<ContaReceber> response = new InternalResultsResponse<ContaReceber>();

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchContasReceberById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<ContaReceber> fetchContasReceberById(Integer id)
	{
		InternalResultsResponse<ContaReceber> response = new InternalResultsResponse<ContaReceber>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IContasReceberDAC#maintainContasReceberAssociations(java.util.List,
	 * java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer maintainContasReceberAssociations(List<ContaReceber> contasReceberList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain ContasRecebers
		if (ValidationUtil.isNullOrEmpty(contasReceberList))
		{
			return count;
		}

		return count;
	}
}
