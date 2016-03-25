package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.banco.Banco;
import com.qat.samples.sysmgmt.dp.HorarioFunc;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IHoraFuncDAC;

/**
 * The Class BancoDACImpl.
 */
public class HoraFuncDACImpl extends SqlSessionDaoSupport implements IHoraFuncDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "HoraFuncMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchHoraFuncRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllHoraFuncsByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchHoraFuncById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertHoraFunc";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateHoraFuncWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateHoraFunc";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteHoraFuncById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateHoraFuncDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateHoraFuncWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteHoraFuncDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HoraFuncDACImpl.class);

	/** The valid sort fields for an banco inquiry. Will be injected by Spring. */
	private Map<String, String> bancoInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the banco inquiry. Attribute injected by Spring.
	 * 
	 * @return The valid sort fields for the banco inquiry.
	 */
	public Map<String, String> getHoraFuncInquiryValidSortFields()
	{
		return bancoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the banco inquiry. Attribute injected by Spring.
	 * 
	 * @param bancoInquiryValidSortFields The valid sort fields for the banco inquiry to set.
	 */
	public void setHoraFuncInquiryValidSortFields(Map<String, String> bancoInquiryValidSortFields)
	{
		this.bancoInquiryValidSortFields = bancoInquiryValidSortFields;
	}

	/**
	 * Maintain banco associations.
	 * 
	 * @param banco the banco
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainBancoAssociations(Banco banco,
			InternalResultsResponse<Banco> response)
	{
		Integer count = 0;

		return count;
	}

	@Override
	public Integer updateHorarioFunc(HorarioFunc horarioFunc, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;
		InternalResultsResponse<HorarioFunc> response1 = new InternalResultsResponse<HorarioFunc>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(horarioFunc.getModelAction())
				&& (horarioFunc.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, horarioFunc,
							response1);
		}

		if (response1.isInError())
		{
			return null;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response1.addResult(horarioFunc);
		}

		return updateCount;
	}

	@Override
	public Integer insertHorarioFunc(HorarioFunc horarioFunc, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		InternalResultsResponse<HorarioFunc> response1 = new InternalResultsResponse<HorarioFunc>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, horarioFunc, response1);

		if (response1.isInError())
		{
			return null;
		}
		// Next traverse the object graph and "maintain" the associations
		// insertCount += maintainBancoAssociations(horarioFunc, response1);

		// Finally, if something was inserted then add the Banco to the result.
		if (insertCount > 0)
		{
			response1.addResult(horarioFunc);
		}

		return insertCount;
	}

	@Override
	public Integer deleteHorarioFunc(HorarioFunc horarioFunc, InternalResultsResponse<?> response)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<HorarioFunc> fetchHorarioFuncById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<HorarioFunc> fetchHorarioFuncByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
