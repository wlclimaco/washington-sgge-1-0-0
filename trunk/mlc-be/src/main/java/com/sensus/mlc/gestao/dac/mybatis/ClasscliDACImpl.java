package com.sensus.mlc.gestao.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DuplicateKeyException;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.gestao.dac.IClassclienteDAC;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.Classcliente;
import com.sensus.mlc.gestao.model.request.ClassclienteRequest;
import com.sensus.mlc.gestao.model.request.InquiryClassclienteRequest;
import com.sensus.mlc.gestao.model.response.ClassclienteResponse;
import com.sensus.mlc.tag.model.TagOrderByEnum;

/**
 * The Class ClassclienteDACImpl.
 */
public class ClasscliDACImpl extends SqlSessionDaoSupport implements IClassclienteDAC
{

	/** The Constant CLASSCLI_NAMESPACE. */
	private static final String CLASSCLI_NAMESPACE = "Classcliente.";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = CLASSCLI_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_CLASSCLI. */
	private static final String DELETE_SMART_POINT_FROM_CLASSCLI = CLASSCLI_NAMESPACE + "deleteSmartPointFromClasscliente";

	/** The Constant FETCH_CLASSCLI_NAME_BY_ID. */
	private static final String FETCH_CLASSCLI_NAME_BY_ID = CLASSCLI_NAMESPACE + "fetchClassclienteNameById";

	/** The Constant UPDATE_AUTO_CLASSCLI_CLASSCLI. */
	private static final String UPDATE_AUTO_CLASSCLI_CLASSCLI = CLASSCLI_NAMESPACE + "updateAutoClassclienteClasscliente";

	/** The Constant DELETE_CLASSCLI. */
	private static final String DELETE_CLASSCLI = CLASSCLI_NAMESPACE + "deleteClasscliente";

	/** The Constant FETCH_CLASSCLIS_BY_LIGHT_ID. */
	private static final String FETCH_CLASSCLIS_BY_LIGHT_ID = CLASSCLI_NAMESPACE + "fetchClassclientesByLightId";

	/** The Constant FETCH_CLASSCLIS_BY_SMART_POINT_ID. */
	private static final String FETCH_CLASSCLIS_BY_SMART_POINT_ID = CLASSCLI_NAMESPACE + "fetchClassclientesBySmartPointId";

	/** The Constant INSERT_CLASSCLI. */
	private static final String INSERT_CLASSCLI = CLASSCLI_NAMESPACE + "insertClasscliente";

	/** The Constant INSERT_SMART_POINT_TO_CLASSCLI. */
	private static final String INSERT_SMART_POINT_TO_CLASSCLI = CLASSCLI_NAMESPACE + "insertSmartPointToClasscliente";

	/** The Constant FETCH_LIGHTS_BELONG. */
	private static final String FETCH_LIGHTS_BELONG_CLASSCLI = CLASSCLI_NAMESPACE + "fetchLightsBelongClasscliente";

	/** The Constant FETCH_ALL_CLASSCLIS. */
	private static final String FETCH_ALL_CLASSCLIS = CLASSCLI_NAMESPACE + "fetchAllClassclientes";

	/** The Constant CLASSCLIID. */
	private static final String CLASSCLI_ID = "classcliId";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant CLASSCLINAME. */
	private static final String CLASSCLINAME = "classcliname";

	/** The Constant AUTOCLASSCLI. */
	private static final String AUTOCLASSCLI = "autoclasscli";

	/** The Constant AUTOCLASSCLI. */
	private static final String ADDRESS_RELATED = "address_related";

	/** The Constant CREATEUSER. */
	private static final String CREATEUSER = "createuser";

	/** The Constant LIGHT_ID. */
	private static final String LIGHT_ID = "lightid";

	/** The Constant START_PAGE. */
	private static final String START_PAGE = "startPage";

	/** The Constant ORDERBY. */
	private static final String ORDERBY = "orderBy";

	/** The Constant UNSELECTION_PAGINATION_IDS. */
	private static final String UNSELECTION_PAGINATION_IDS = "unselectionPaginationIds";

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE6 = 5;

	private static final String UPDATE_CLASSCLI = null;

	private static final String SENSUS_MLC_CLASSCLIVALIDATOR_CLASSCLI_ALREADY_EXISTS = null;

	private static final String INSERT_AUDITORIA = null;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.classcli.dao.IClassclienteDAO#fetchAllClassclientes(com.sensus.mlc.classcli.model.request.InquiryClassclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Classcliente> fetchAllClasscliente(InquiryClassclienteRequest inquiryClassclienteRequest)
	{
		InternalResultsResponse<Classcliente> response = new InternalResultsResponse<Classcliente>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryClassclienteRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryClassclienteRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryClassclienteRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryClassclienteRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryClassclienteRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryClassclienteRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryClassclienteRequest.getClasscliente()))
		{
			paramMap.put(CLASSCLI_ID, inquiryClassclienteRequest.getClasscliente().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_CLASSCLIS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.classcli.dao.IClassclienteDAO#insertClasscliente(com.sensus.mlc.classcli.model.request.ClassclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Classcliente> insertClasscliente(ClassclienteRequest classcliRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Classcliente classcli = classcliRequest.getClasscliente();

		classcli.setCodclascli((Integer)doQueryForObject(getSqlSession(), INSERT_CLASSCLI, classcliRequest));
		classcli.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, classcliRequest));
		InternalResultsResponse<Classcliente> response = new InternalResultsResponse<Classcliente>();
		response.addResult(classcli);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.classcli.dao.IClassclienteDAO#deleteClasscliente(com.sensus.mlc.classcli.model.request.ClassclienteRequest)
	 */
	@Override
	public InternalResponse deleteClasscliente(ClassclienteRequest classcliRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_CLASSCLI, classcliRequest.getClasscliente(), response);
		return response;
	}


	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.classcli.dao.IClassclienteDAO#fetchClassclienteById(com.sensus.mlc.classcli.model.request.ClassclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Classcliente> fetchClassclienteById(ClassclienteRequest classcliRequest)
	{
		InternalResultsResponse<Classcliente> response = new InternalResultsResponse<Classcliente>();
		doQueryForList(getSqlSession(), FETCH_CLASSCLI_BY_ID, classcliRequest.getClasscliente(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.classcli.dac.IClassclienteDAC#updateClasscliente(com.sensus.mlc.classcli.model.request.ClassclienteRequest)
	 */
	@Override
	public InternalResponse updateClasscliente(ClassclienteRequest classcliRequest)
	{
		InternalResponse response = new InternalResponse();
		try
		{
			doQueryForObject(getSqlSession(), UPDATE_CLASSCLI, classcliRequest);
		}
		catch (DuplicateKeyException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_CLASSCLIVALIDATOR_CLASSCLI_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

	@Override
	public InternalResponse generateFileCSV(
			InquiryClassclienteRequest inquiryClassclienteRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassclienteResponse fetchAllClassclienteTypes(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassclienteResponse fetchAllClassclienteFilial(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}


