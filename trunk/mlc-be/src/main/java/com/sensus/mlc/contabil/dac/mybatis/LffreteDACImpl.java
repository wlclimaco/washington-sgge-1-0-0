package com.sensus.mlc.contabil.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getParametersToFetchAllLights;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.util.LCPropertiesExtractorUtil;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.lffrete.dac.ILffreteDAC;
import com.sensus.mlc.lffrete.model.Lffrete;
import com.sensus.mlc.lffrete.model.LffreteOrderByEnum;
import com.sensus.mlc.lffrete.model.request.InquiryLffreteRequest;
import com.sensus.mlc.lffrete.model.request.LffreteRequest;

/** 
 * The Class LffreteDACImpl.
 */ 
public class LffreteDACImpl extends SqlSessionDaoSupport implements ILffreteDAC
{ 

	/** The Constant LFFRETE_NAMESPACE. */ 
	private static final String LFFRETE_NAMESPACE = "Lffrete.";

	/** The Constant FETCH_LFFRETE_BY_ID. */ 
	private static final String FETCH_LFFRETE_BY_ID = LFFRETE_NAMESPACE + "fetchLffreteById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFFRETE_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFFRETE. */ 
	private static final String DELETE_SMART_POINT_FROM_LFFRETE = LFFRETE_NAMESPACE + "deleteSmartPointFromLffrete";

	/** The Constant FETCH_LFFRETE_NAME_BY_ID. */ 
	private static final String FETCH_LFFRETE_NAME_BY_ID = LFFRETE_NAMESPACE + "fetchLffreteNameById";

	/** The Constant UPDATE_AUTO_LFFRETE_LFFRETE. */ 
	private static final String UPDATE_AUTO_LFFRETE_LFFRETE = LFFRETE_NAMESPACE + "updateAutoLffreteLffrete";

	/** The Constant DELETE_LFFRETE. */ 
	private static final String DELETE_LFFRETE = LFFRETE_NAMESPACE + "deleteLffrete";

	/** The Constant FETCH_LFFRETES_BY_LIGHT_ID. */ 
	private static final String FETCH_LFFRETES_BY_LIGHT_ID = LFFRETE_NAMESPACE + "fetchLffretesByLightId";

	/** The Constant FETCH_LFFRETES_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFFRETES_BY_SMART_POINT_ID = LFFRETE_NAMESPACE + "fetchLffretesBySmartPointId";

	/** The Constant INSERT_LFFRETE. */ 
	private static final String INSERT_LFFRETE = LFFRETE_NAMESPACE + "insertLffrete";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFFRETE = LFFRETE_NAMESPACE + "updateLffrete";
private static final String SENSUS_MLC_LFFRETE_VALIDATOR_LFFRETE_ALREADY_EXISTS = error.update.lffrete;
 
	/** The Constant INSERT_SMART_POINT_TO_LFFRETE. */ 
	private static final String INSERT_SMART_POINT_TO_LFFRETE = LFFRETE_NAMESPACE + "insertSmartPointToLffrete";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFFRETE = LFFRETE_NAMESPACE + "fetchLightsBelongLffrete";

	/** The Constant FETCH_ALL_LFFRETES. */ 
	private static final String FETCH_ALL_LFFRETES = LFFRETE_NAMESPACE + "fetchAllLffretes";

	/** The Constant LFFRETEID. */ 
	private static final String LFFRETE_ID = "lffreteId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFFRETENAME. */ 
	private static final String LFFRETENAME = "lffretename";

	/** The Constant AUTOLFFRETE. */ 
	private static final String AUTOLFFRETE = "autolffrete";

	/** The Constant AUTOLFFRETE. */ 
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

	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lffrete.dao.ILffreteDAO#fetchAllLffretes(com.sensus.mlc.lffrete.model.request.InquiryLffreteRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lffrete> fetchAllLffrete(InquiryLffreteRequest inquiryLffreteRequest)
	{
		InternalResultsResponse<Lffrete> response = new InternalResultsResponse<Lffrete>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLffreteRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLffreteRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLffreteRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLffreteRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLffreteRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLffreteRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLffreteRequest.getLffrete()))
		{
			paramMap.put(LFFRETE_ID, inquiryLffreteRequest.getLffrete().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFFRETES, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lffrete.dao.ILffreteDAO#insertLffrete(com.sensus.mlc.lffrete.model.request.LffreteRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lffrete> insertLffrete(LffreteRequest lffreteRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lffrete lffrete = lffreteRequest.getLffrete();

		lffrete.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFFRETE, lffreteRequest));
   lffrete.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lffreteRequest));
		InternalResultsResponse<Lffrete> response = new InternalResultsResponse<Lffrete>();
		response.addResult(lffrete);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffrete.dao.ILffreteDAO#deleteLffrete(com.sensus.mlc.lffrete.model.request.LffreteRequest)
	 */ 
	@Override
	public InternalResponse deleteLffrete(LffreteRequest lffreteRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFFRETE, lffreteRequest.getLffrete(), response);
   lffrete.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lffreteRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lffrete.dac.ILffreteDAC#updateLffrete(com.sensus.mlc.lffrete.model.request.LffreteRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lffrete> updateLffrete(LffreteRequest lffreteRequest)
	{
		InternalResultsResponse<Lffrete> response = new InternalResultsResponse<Lffrete>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFFRETE, lffreteRequest);
   lffrete.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lffreteRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFFRETE_VALIDATOR_LFFRETE_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


