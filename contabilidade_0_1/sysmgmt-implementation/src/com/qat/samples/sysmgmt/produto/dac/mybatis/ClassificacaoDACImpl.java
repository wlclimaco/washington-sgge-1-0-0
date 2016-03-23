package com.qat.samples.sysmgmt.produto.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.fiscal.Classificacao;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.dac.IClassificacaoDAC;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class ClassificacaoDACImpl extends SqlSessionDaoSupport implements IClassificacaoDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "ClassificacaoMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateClassificacao";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_UPDATE_EMAIL. */
	private static final String CONTACT_STMT_UPDATE_EMAIL = CONTACT_NAMESPACE + "updateEmail";

	/** The Constant CONTACT_STMT_UPDATE_ADDRESS. */
	private static final String CONTACT_STMT_UPDATE_ADDRESS = CONTACT_NAMESPACE + "updateAddress";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE
			+ "deleteBusinessClassificacao";

	/** The Constant CONTACT_STMT_DELETE_PERSON_CONTACT. */
	private static final String CONTACT_STMT_DELETE_PERSON_CONTACT = CONTACT_NAMESPACE + "deletePersonClassificacao";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertClassificacao";

	/** The Constant CONTACT_STMT_INSERT_PHONE. */
	private static final String CONTACT_STMT_INSERT_PHONE = CONTACT_NAMESPACE + "insertPhone";

	/** The Constant CONTACT_STMT_INSERT_EMAIL. */
	private static final String CONTACT_STMT_INSERT_EMAIL = CONTACT_NAMESPACE + "insertEmail";

	/** The Constant CONTACT_STMT_INSERT_ADDRESS. */
	private static final String CONTACT_STMT_INSERT_ADDRESS = CONTACT_NAMESPACE + "insertAddress";

	/** The Constant CONTACT_STMT_FETCH_BY_BUSINESS_ID. */
	private static final String CONTACT_STMT_FETCH_BY_BUSINESS_ID = CONTACT_NAMESPACE
			+ "fetchClassificacaosByBusinessId";

	/** The Constant CONTACT_STMT_FETCH_BY_PERSON_ID. */
	private static final String CONTACT_STMT_FETCH_BY_PERSON_ID = CONTACT_NAMESPACE + "fetchClassificacaosByPersonId";

	/** The Constant CONTACT_STMT_FETCH_BY_ID. */
	private static final String CONTACT_STMT_FETCH_BY_ID = CONTACT_NAMESPACE + "fetchClassificacaosById";

	/** The Constant CONTACT_STMT_FETCH_EMAIL_VERSION. */
	private static final String CONTACT_STMT_FETCH_EMAIL_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberEmail";

	/** The Constant CONTACT_STMT_FETCH_PHONE_VERSION. */
	private static final String CONTACT_STMT_FETCH_PHONE_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberPhone";

	/** The Constant CONTACT_STMT_FETCH_ADDRESS_VERSION. */
	private static final String CONTACT_STMT_FETCH_ADDRESS_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberAddress";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ClassificacaoDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertClassificacao(com.prosperitasglobal.cbof.model.
	 * Classificacao
	 * ,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertClassificacao(Classificacao classificacao, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root classificacao data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, classificacao, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateClassificacao(com.prosperitasglobal.cbof.model.
	 * Classificacao
	 * ,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateClassificacao(Classificacao classificacao, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(classificacao.getModelAction())
				&& (classificacao.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, classificacao, response);

			if (updateCount == 1)
			{
				classificacao.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		updateCount +=
				QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_PHONE, classificacao, response);

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IClassificacaoDAC#maintainClassificacaoAssociations(java.util.List,
	 * java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	public Integer maintainClassificacaoAssociations(List<Classificacao> classificacaoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Classificacaos
		if (ValidationUtil.isNullOrEmpty(classificacaoList))
		{
			return count;
		}
		// For Each Classificacao...
		for (Classificacao classificacao : classificacaoList)
		{
			// Make sure we set the parent key
			classificacao.setParentId(parentId);

			if (ValidationUtil.isNull(classificacao.getModelAction()))
			{
				continue;
			}
			switch (classificacao.getModelAction())
			{
				case INSERT:
					count += insertClassificacao(classificacao, associateStatement, response);
					break;
				case UPDATE:
					count += updateClassificacao(classificacao, response);
					break;
				case DELETE:
					count += deleteClassificacao(classificacao, response);
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
	public Integer deleteClassificacao(Classificacao classificacao, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, classificacao,
				response);
	}

	@Override
	public InternalResultsResponse<Classificacao> fetchClassificacaoById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Classificacao> fetchClassificacaoById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Classificacao> fetchClassificacaoByRequest(ClassificacaoInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
