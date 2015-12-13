package com.qat.samples.sysmgmt.produto.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.produto.dac.IImagemDAC;
import com.qat.samples.sysmgmt.util.Imagem;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class ImagemDACImpl extends SqlSessionDaoSupport implements IImagemDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "ImagemMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateImagem";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_UPDATE_EMAIL. */
	private static final String CONTACT_STMT_UPDATE_EMAIL = CONTACT_NAMESPACE + "updateEmail";

	/** The Constant CONTACT_STMT_UPDATE_ADDRESS. */
	private static final String CONTACT_STMT_UPDATE_ADDRESS = CONTACT_NAMESPACE + "updateAddress";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessImagem";

	/** The Constant CONTACT_STMT_DELETE_PERSON_CONTACT. */
	private static final String CONTACT_STMT_DELETE_PERSON_CONTACT = CONTACT_NAMESPACE + "deletePersonImagem";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertImagem";

	/** The Constant CONTACT_STMT_INSERT_PHONE. */
	private static final String CONTACT_STMT_INSERT_PHONE = CONTACT_NAMESPACE + "insertPhone";

	/** The Constant CONTACT_STMT_INSERT_EMAIL. */
	private static final String CONTACT_STMT_INSERT_EMAIL = CONTACT_NAMESPACE + "insertEmail";

	/** The Constant CONTACT_STMT_INSERT_ADDRESS. */
	private static final String CONTACT_STMT_INSERT_ADDRESS = CONTACT_NAMESPACE + "insertAddress";

	/** The Constant CONTACT_STMT_FETCH_BY_BUSINESS_ID. */
	private static final String CONTACT_STMT_FETCH_BY_BUSINESS_ID = CONTACT_NAMESPACE + "fetchImagemsByBusinessId";

	/** The Constant CONTACT_STMT_FETCH_BY_PERSON_ID. */
	private static final String CONTACT_STMT_FETCH_BY_PERSON_ID = CONTACT_NAMESPACE + "fetchImagemsByPersonId";

	/** The Constant CONTACT_STMT_FETCH_BY_ID. */
	private static final String CONTACT_STMT_FETCH_BY_ID = CONTACT_NAMESPACE + "fetchImagemsById";

	/** The Constant CONTACT_STMT_FETCH_EMAIL_VERSION. */
	private static final String CONTACT_STMT_FETCH_EMAIL_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberEmail";

	/** The Constant CONTACT_STMT_FETCH_PHONE_VERSION. */
	private static final String CONTACT_STMT_FETCH_PHONE_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberPhone";

	/** The Constant CONTACT_STMT_FETCH_ADDRESS_VERSION. */
	private static final String CONTACT_STMT_FETCH_ADDRESS_VERSION = CONTACT_NAMESPACE + "fetchVersionNumberAddress";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ImagemDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertImagem(com.prosperitasglobal.cbof.model.Imagem
	 * ,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertImagem(Imagem tabPreco, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root tabPreco data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, tabPreco, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IImagemDAC#deleteBusinessImagem(com.prosperitasglobal.cbof.model.Imagem,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteImagem(Imagem tabPreco, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, tabPreco, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateImagem(com.prosperitasglobal.cbof.model.Imagem
	 * ,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateImagem(Imagem tabPreco, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(tabPreco.getModelAction())
				&& (tabPreco.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, tabPreco, response);

			if (updateCount == 1)
			{
				tabPreco.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		updateCount +=
				QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_PHONE, tabPreco, response);

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IImagemDAC#maintainImagemAssociations(java.util.List,
	 * java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	public Integer maintainImagemAssociations(List<Imagem> tabPrecoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Imagems
		if (ValidationUtil.isNullOrEmpty(tabPrecoList))
		{
			return count;
		}
		// For Each Imagem...
		for (Imagem tabPreco : tabPrecoList)
		{
			// Make sure we set the parent key
			tabPreco.setParentId(parentId);

			if (ValidationUtil.isNull(tabPreco.getModelAction()))
			{
				continue;
			}
			switch (tabPreco.getModelAction())
			{
				case INSERT:
					count += insertImagem(tabPreco, associateStatement, response);
					break;
				case UPDATE:
					count += updateImagem(tabPreco, response);
					break;
				case DELETE:
					count += deleteImagem(tabPreco, response);
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
	public InternalResultsResponse<Imagem> fetchImagemById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
