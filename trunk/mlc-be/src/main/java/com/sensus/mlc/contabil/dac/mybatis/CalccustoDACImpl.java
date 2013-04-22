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
import com.sensus.mlc.calccusto.dac.ICalccustoDAC;
import com.sensus.mlc.calccusto.model.Calccusto;
import com.sensus.mlc.calccusto.model.CalccustoOrderByEnum;
import com.sensus.mlc.calccusto.model.request.InquiryCalccustoRequest;
import com.sensus.mlc.calccusto.model.request.CalccustoRequest;

/** 
 * The Class CalccustoDACImpl.
 */ 
public class CalccustoDACImpl extends SqlSessionDaoSupport implements ICalccustoDAC
{ 

	/** The Constant CALCCUSTO_NAMESPACE. */ 
	private static final String CALCCUSTO_NAMESPACE = "Calccusto.";

	/** The Constant FETCH_CALCCUSTO_BY_ID. */ 
	private static final String FETCH_CALCCUSTO_BY_ID = CALCCUSTO_NAMESPACE + "fetchCalccustoById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = CALCCUSTO_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_CALCCUSTO. */ 
	private static final String DELETE_SMART_POINT_FROM_CALCCUSTO = CALCCUSTO_NAMESPACE + "deleteSmartPointFromCalccusto";

	/** The Constant FETCH_CALCCUSTO_NAME_BY_ID. */ 
	private static final String FETCH_CALCCUSTO_NAME_BY_ID = CALCCUSTO_NAMESPACE + "fetchCalccustoNameById";

	/** The Constant UPDATE_AUTO_CALCCUSTO_CALCCUSTO. */ 
	private static final String UPDATE_AUTO_CALCCUSTO_CALCCUSTO = CALCCUSTO_NAMESPACE + "updateAutoCalccustoCalccusto";

	/** The Constant DELETE_CALCCUSTO. */ 
	private static final String DELETE_CALCCUSTO = CALCCUSTO_NAMESPACE + "deleteCalccusto";

	/** The Constant FETCH_CALCCUSTOS_BY_LIGHT_ID. */ 
	private static final String FETCH_CALCCUSTOS_BY_LIGHT_ID = CALCCUSTO_NAMESPACE + "fetchCalccustosByLightId";

	/** The Constant FETCH_CALCCUSTOS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_CALCCUSTOS_BY_SMART_POINT_ID = CALCCUSTO_NAMESPACE + "fetchCalccustosBySmartPointId";

	/** The Constant INSERT_CALCCUSTO. */ 
	private static final String INSERT_CALCCUSTO = CALCCUSTO_NAMESPACE + "insertCalccusto";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_CALCCUSTO = CALCCUSTO_NAMESPACE + "updateCalccusto";
private static final String SENSUS_MLC_CALCCUSTO_VALIDATOR_CALCCUSTO_ALREADY_EXISTS = error.update.calccusto;
 
	/** The Constant INSERT_SMART_POINT_TO_CALCCUSTO. */ 
	private static final String INSERT_SMART_POINT_TO_CALCCUSTO = CALCCUSTO_NAMESPACE + "insertSmartPointToCalccusto";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_CALCCUSTO = CALCCUSTO_NAMESPACE + "fetchLightsBelongCalccusto";

	/** The Constant FETCH_ALL_CALCCUSTOS. */ 
	private static final String FETCH_ALL_CALCCUSTOS = CALCCUSTO_NAMESPACE + "fetchAllCalccustos";

	/** The Constant CALCCUSTOID. */ 
	private static final String CALCCUSTO_ID = "calccustoId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant CALCCUSTONAME. */ 
	private static final String CALCCUSTONAME = "calccustoname";

	/** The Constant AUTOCALCCUSTO. */ 
	private static final String AUTOCALCCUSTO = "autocalccusto";

	/** The Constant AUTOCALCCUSTO. */ 
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
	 * @see com.sensus.mlc.calccusto.dao.ICalccustoDAO#fetchAllCalccustos(com.sensus.mlc.calccusto.model.request.InquiryCalccustoRequest)
	 */ 
	@Override
	public InternalResultsResponse<Calccusto> fetchAllCalccusto(InquiryCalccustoRequest inquiryCalccustoRequest)
	{
		InternalResultsResponse<Calccusto> response = new InternalResultsResponse<Calccusto>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryCalccustoRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryCalccustoRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryCalccustoRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryCalccustoRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryCalccustoRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryCalccustoRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryCalccustoRequest.getCalccusto()))
		{
			paramMap.put(CALCCUSTO_ID, inquiryCalccustoRequest.getCalccusto().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_CALCCUSTOS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.calccusto.dao.ICalccustoDAO#insertCalccusto(com.sensus.mlc.calccusto.model.request.CalccustoRequest)
	 */ 
	@Override
	public InternalResultsResponse<Calccusto> insertCalccusto(CalccustoRequest calccustoRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Calccusto calccusto = calccustoRequest.getCalccusto();

		calccusto.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_CALCCUSTO, calccustoRequest));
   calccusto.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, calccustoRequest));
		InternalResultsResponse<Calccusto> response = new InternalResultsResponse<Calccusto>();
		response.addResult(calccusto);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.calccusto.dao.ICalccustoDAO#deleteCalccusto(com.sensus.mlc.calccusto.model.request.CalccustoRequest)
	 */ 
	@Override
	public InternalResponse deleteCalccusto(CalccustoRequest calccustoRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_CALCCUSTO, calccustoRequest.getCalccusto(), response);
   calccusto.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , calccustoRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.calccusto.dac.ICalccustoDAC#updateCalccusto(com.sensus.mlc.calccusto.model.request.CalccustoRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Calccusto> updateCalccusto(CalccustoRequest calccustoRequest)
	{
		InternalResultsResponse<Calccusto> response = new InternalResultsResponse<Calccusto>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_CALCCUSTO, calccustoRequest);
   calccusto.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, calccustoRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_CALCCUSTO_VALIDATOR_CALCCUSTO_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


