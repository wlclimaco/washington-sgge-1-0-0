package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IContatoDAC;
import com.prosperitasglobal.sendsolv.model.Contato;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class ContatoDACImpl extends SqlSessionDaoSupport implements IContatoDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "ContatoMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateContato";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessContato";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertContato";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ContatoDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertContato(com.prosperitasglobal.cbof.model.Contato
	 * ,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertContato(Contato contato, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root contato data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, contato, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateContato(com.prosperitasglobal.cbof.model.Contato
	 * ,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateContato(Contato contato, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(contato.getModelAction())
				&& (contato.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, contato, response);

			if (updateCount == 1)
			{
				contato.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		updateCount +=
				QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_PHONE, contato, response);

		return updateCount;
	}

	public Integer maintainContatoAssociations(List<Contato> contatoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Contatos
		if (ValidationUtil.isNullOrEmpty(contatoList))
		{
			return count;
		}
		// For Each Contato...
		for (Contato contato : contatoList)
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
					count += insertContato(contato, associateStatement, response);
					break;
				case UPDATE:
					count += updateContato(contato, response);
					break;
				case DELETE:
					count += deleteContato(contato, response);
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
	public InternalResultsResponse<Contato> fetchContatoById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteContato(Contato contato, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, contato, response);
	}
}
