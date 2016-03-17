package com.qat.samples.sysmgmt.util.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.util.Email;
import com.qat.samples.sysmgmt.util.dac.IEmailDAC;

/**
 * The Class EmailDACImpl.
 */
public class EmailDACImpl extends SqlSessionDaoSupport implements IEmailDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "EmailMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateEmail";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessEmail";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertEmail";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmailDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertEmail(com.prosperitasglobal.cbof.model.Email,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertEmail(Email cnae, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root cnae data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, cnae, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IEmailDAC#deleteBusinessEmail(com.prosperitasglobal.cbof.model.Email,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteEmail(Email cnae, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, cnae, response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IEmailDAC#deletePersonEmail(com.prosperitasglobal.cbof.model.Email,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateEmail(com.prosperitasglobal.cbof.model.Email,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateEmail(Email cnae, InternalResultsResponse<?> response)
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

		return updateCount;
	}

}
