package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.convenio.Convenio;
import com.qat.samples.sysmgmt.convenio.ConvenioPessoa;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IConvenioDAC;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class ConvenioDACImpl extends SqlSessionDaoSupport implements IConvenioDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "ConvenioMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateConvenio";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessConvenio";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertConvenio";

	/** The Constant CONTACT_STMT_FETCH_BY_ID. */
	private static final String CONTACT_STMT_FETCH_BY_ID = CONTACT_NAMESPACE + "fetchConveniosById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConvenioDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertConvenio(com.prosperitasglobal.cbof.model.Convenio,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertConvenio(Convenio convenio, String string, InternalResultsResponse<?> response)
	{
		response = new InternalResultsResponse<Convenio>();
		Integer insertCount = 0;
		// First insert the root convenio data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, convenio, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IConvenioDAC#deleteBusinessConvenio(com.prosperitasglobal.cbof.model.Convenio,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteConvenio(Convenio convenio, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, convenio, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateConvenio(com.prosperitasglobal.cbof.model.Convenio,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateConvenio(Convenio convenio, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;
		response = new InternalResultsResponse<Convenio>();
		// First update the root if necessary.
		if (!ValidationUtil.isNull(convenio.getModelAction())
				&& (convenio.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, convenio, response);

			if (updateCount == 1)
			{
				convenio.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchConvenioById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Convenio> fetchConvenioById(Integer id)
	{
		InternalResultsResponse<Convenio> response = new InternalResultsResponse<Convenio>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	@Override
	public Integer insertConvenioPessoa(ConvenioPessoa convenio, String string, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		response = new InternalResultsResponse<ConvenioPessoa>();
		// First insert the root convenio data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "ConvenioMap.insertConvenioPessoa", convenio, response);

		return insertCount;
	}

	@Override
	public Integer deleteConvenioPessoa(ConvenioPessoa convenio, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), "ConvenioMap.deleteConvenioPessoa", convenio,
				response);
	}

	@Override
	public Integer updateConvenioPessoa(ConvenioPessoa convenio, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;
		response = new InternalResultsResponse<ConvenioPessoa>();
		// First update the root if necessary.
		if (!ValidationUtil.isNull(convenio.getModelAction())
				&& (convenio.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "ConvenioMap.updateConvenioPessoa", convenio,
							response);

			if (updateCount == 1)
			{
				convenio.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public InternalResultsResponse<Convenio> fetchConvenioByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
