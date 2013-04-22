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
import com.sensus.mlc.lfnatoper.dac.ILfnatoperDAC;
import com.sensus.mlc.lfnatoper.model.Lfnatoper;
import com.sensus.mlc.lfnatoper.model.LfnatoperOrderByEnum;
import com.sensus.mlc.lfnatoper.model.request.InquiryLfnatoperRequest;
import com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest;

/** 
 * The Class LfnatoperDACImpl.
 */ 
public class LfnatoperDACImpl extends SqlSessionDaoSupport implements ILfnatoperDAC
{ 

	/** The Constant LFNATOPER_NAMESPACE. */ 
	private static final String LFNATOPER_NAMESPACE = "Lfnatoper.";

	/** The Constant FETCH_LFNATOPER_BY_ID. */ 
	private static final String FETCH_LFNATOPER_BY_ID = LFNATOPER_NAMESPACE + "fetchLfnatoperById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFNATOPER_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFNATOPER. */ 
	private static final String DELETE_SMART_POINT_FROM_LFNATOPER = LFNATOPER_NAMESPACE + "deleteSmartPointFromLfnatoper";

	/** The Constant FETCH_LFNATOPER_NAME_BY_ID. */ 
	private static final String FETCH_LFNATOPER_NAME_BY_ID = LFNATOPER_NAMESPACE + "fetchLfnatoperNameById";

	/** The Constant UPDATE_AUTO_LFNATOPER_LFNATOPER. */ 
	private static final String UPDATE_AUTO_LFNATOPER_LFNATOPER = LFNATOPER_NAMESPACE + "updateAutoLfnatoperLfnatoper";

	/** The Constant DELETE_LFNATOPER. */ 
	private static final String DELETE_LFNATOPER = LFNATOPER_NAMESPACE + "deleteLfnatoper";

	/** The Constant FETCH_LFNATOPERS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFNATOPERS_BY_LIGHT_ID = LFNATOPER_NAMESPACE + "fetchLfnatopersByLightId";

	/** The Constant FETCH_LFNATOPERS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFNATOPERS_BY_SMART_POINT_ID = LFNATOPER_NAMESPACE + "fetchLfnatopersBySmartPointId";

	/** The Constant INSERT_LFNATOPER. */ 
	private static final String INSERT_LFNATOPER = LFNATOPER_NAMESPACE + "insertLfnatoper";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFNATOPER = LFNATOPER_NAMESPACE + "updateLfnatoper";
private static final String SENSUS_MLC_LFNATOPER_VALIDATOR_LFNATOPER_ALREADY_EXISTS = error.update.lfnatoper;
 
	/** The Constant INSERT_SMART_POINT_TO_LFNATOPER. */ 
	private static final String INSERT_SMART_POINT_TO_LFNATOPER = LFNATOPER_NAMESPACE + "insertSmartPointToLfnatoper";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFNATOPER = LFNATOPER_NAMESPACE + "fetchLightsBelongLfnatoper";

	/** The Constant FETCH_ALL_LFNATOPERS. */ 
	private static final String FETCH_ALL_LFNATOPERS = LFNATOPER_NAMESPACE + "fetchAllLfnatopers";

	/** The Constant LFNATOPERID. */ 
	private static final String LFNATOPER_ID = "lfnatoperId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFNATOPERNAME. */ 
	private static final String LFNATOPERNAME = "lfnatopername";

	/** The Constant AUTOLFNATOPER. */ 
	private static final String AUTOLFNATOPER = "autolfnatoper";

	/** The Constant AUTOLFNATOPER. */ 
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
	 * @see com.sensus.mlc.lfnatoper.dao.ILfnatoperDAO#fetchAllLfnatopers(com.sensus.mlc.lfnatoper.model.request.InquiryLfnatoperRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfnatoper> fetchAllLfnatoper(InquiryLfnatoperRequest inquiryLfnatoperRequest)
	{
		InternalResultsResponse<Lfnatoper> response = new InternalResultsResponse<Lfnatoper>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfnatoperRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfnatoperRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfnatoperRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfnatoperRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfnatoperRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfnatoperRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfnatoperRequest.getLfnatoper()))
		{
			paramMap.put(LFNATOPER_ID, inquiryLfnatoperRequest.getLfnatoper().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFNATOPERS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfnatoper.dao.ILfnatoperDAO#insertLfnatoper(com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfnatoper> insertLfnatoper(LfnatoperRequest lfnatoperRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfnatoper lfnatoper = lfnatoperRequest.getLfnatoper();

		lfnatoper.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFNATOPER, lfnatoperRequest));
   lfnatoper.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfnatoperRequest));
		InternalResultsResponse<Lfnatoper> response = new InternalResultsResponse<Lfnatoper>();
		response.addResult(lfnatoper);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfnatoper.dao.ILfnatoperDAO#deleteLfnatoper(com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest)
	 */ 
	@Override
	public InternalResponse deleteLfnatoper(LfnatoperRequest lfnatoperRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFNATOPER, lfnatoperRequest.getLfnatoper(), response);
   lfnatoper.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfnatoperRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfnatoper.dac.ILfnatoperDAC#updateLfnatoper(com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfnatoper> updateLfnatoper(LfnatoperRequest lfnatoperRequest)
	{
		InternalResultsResponse<Lfnatoper> response = new InternalResultsResponse<Lfnatoper>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFNATOPER, lfnatoperRequest);
   lfnatoper.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfnatoperRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFNATOPER_VALIDATOR_LFNATOPER_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


