package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IFormaPagamentoDAC;
import com.prosperitasglobal.sendsolv.model.FormaPg;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class FormaPgDACImpl extends SqlSessionDaoSupport implements IFormaPagamentoDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "FormaPgMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateFormaPg";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_DELETE_PERSON_CONTACT. */
	private static final String CONTACT_STMT_DELETE_PERSON_CONTACT = CONTACT_NAMESPACE + "deletePersonFormaPg";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertFormaPg";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FormaPgDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IFormaPgDAC#maintainFormaPgAssociations(java.util.List,
	 * java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	public Integer maintainFormaPgAssociations(List<FormaPg> contatoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain FormaPgs
		if (ValidationUtil.isNullOrEmpty(contatoList))
		{
			return count;
		}
		// For Each FormaPg...
		for (FormaPg contato : contatoList)
		{
			// Make sure we set the parent key
			contato.setParentId(parentId);

			if (ValidationUtil.isNull(contato.getModelAction()))
			{
				continue;
			}
			switch (contato.getModelAction())
			{
				case INSERT:
					count += insertFormaPg(contato, associateStatement, response);
					break;
				case UPDATE:
					count += updateFormaPg(contato, response);
					break;
				case DELETE:
					count += deleteFormaPg(contato, response);
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

	@Override
	public Integer updateFormaPg(FormaPg formaPg, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(formaPg.getModelAction())
				&& (formaPg.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, formaPg, response);

			if (updateCount == 1)
			{
				formaPg.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		updateCount +=
				QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_PHONE, formaPg, response);

		return updateCount;
	}

	@Override
	public Integer insertFormaPg(FormaPg formaPg, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root contato data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, formaPg, response);

		return insertCount;
	}

	@Override
	public Integer deleteFormaPg(FormaPg formaPg, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_PERSON_CONTACT, formaPg, response);
	}

	@Override
	public InternalResultsResponse<FormaPg> fetchFormaPgById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
