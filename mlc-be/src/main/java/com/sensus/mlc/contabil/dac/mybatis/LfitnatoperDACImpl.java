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
import com.sensus.mlc.lfitnatoper.dac.ILfitnatoperDAC;
import com.sensus.mlc.lfitnatoper.model.Lfitnatoper;
import com.sensus.mlc.lfitnatoper.model.LfitnatoperOrderByEnum;
import com.sensus.mlc.lfitnatoper.model.request.InquiryLfitnatoperRequest;
import com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest;

/** 
 * The Class LfitnatoperDACImpl.
 */ 
public class LfitnatoperDACImpl extends SqlSessionDaoSupport implements ILfitnatoperDAC
{ 

	/** The Constant LFITNATOPER_NAMESPACE. */ 
	private static final String LFITNATOPER_NAMESPACE = "Lfitnatoper.";

	/** The Constant FETCH_LFITNATOPER_BY_ID. */ 
	private static final String FETCH_LFITNATOPER_BY_ID = LFITNATOPER_NAMESPACE + "fetchLfitnatoperById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFITNATOPER_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFITNATOPER. */ 
	private static final String DELETE_SMART_POINT_FROM_LFITNATOPER = LFITNATOPER_NAMESPACE + "deleteSmartPointFromLfitnatoper";

	/** The Constant FETCH_LFITNATOPER_NAME_BY_ID. */ 
	private static final String FETCH_LFITNATOPER_NAME_BY_ID = LFITNATOPER_NAMESPACE + "fetchLfitnatoperNameById";

	/** The Constant UPDATE_AUTO_LFITNATOPER_LFITNATOPER. */ 
	private static final String UPDATE_AUTO_LFITNATOPER_LFITNATOPER = LFITNATOPER_NAMESPACE + "updateAutoLfitnatoperLfitnatoper";

	/** The Constant DELETE_LFITNATOPER. */ 
	private static final String DELETE_LFITNATOPER = LFITNATOPER_NAMESPACE + "deleteLfitnatoper";

	/** The Constant FETCH_LFITNATOPERS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFITNATOPERS_BY_LIGHT_ID = LFITNATOPER_NAMESPACE + "fetchLfitnatopersByLightId";

	/** The Constant FETCH_LFITNATOPERS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFITNATOPERS_BY_SMART_POINT_ID = LFITNATOPER_NAMESPACE + "fetchLfitnatopersBySmartPointId";

	/** The Constant INSERT_LFITNATOPER. */ 
	private static final String INSERT_LFITNATOPER = LFITNATOPER_NAMESPACE + "insertLfitnatoper";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFITNATOPER = LFITNATOPER_NAMESPACE + "updateLfitnatoper";
private static final String SENSUS_MLC_LFITNATOPER_VALIDATOR_LFITNATOPER_ALREADY_EXISTS = error.update.lfitnatoper;
 
	/** The Constant INSERT_SMART_POINT_TO_LFITNATOPER. */ 
	private static final String INSERT_SMART_POINT_TO_LFITNATOPER = LFITNATOPER_NAMESPACE + "insertSmartPointToLfitnatoper";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFITNATOPER = LFITNATOPER_NAMESPACE + "fetchLightsBelongLfitnatoper";

	/** The Constant FETCH_ALL_LFITNATOPERS. */ 
	private static final String FETCH_ALL_LFITNATOPERS = LFITNATOPER_NAMESPACE + "fetchAllLfitnatopers";

	/** The Constant LFITNATOPERID. */ 
	private static final String LFITNATOPER_ID = "lfitnatoperId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFITNATOPERNAME. */ 
	private static final String LFITNATOPERNAME = "lfitnatopername";

	/** The Constant AUTOLFITNATOPER. */ 
	private static final String AUTOLFITNATOPER = "autolfitnatoper";

	/** The Constant AUTOLFITNATOPER. */ 
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
	 * @see com.sensus.mlc.lfitnatoper.dao.ILfitnatoperDAO#fetchAllLfitnatopers(com.sensus.mlc.lfitnatoper.model.request.InquiryLfitnatoperRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfitnatoper> fetchAllLfitnatoper(InquiryLfitnatoperRequest inquiryLfitnatoperRequest)
	{
		InternalResultsResponse<Lfitnatoper> response = new InternalResultsResponse<Lfitnatoper>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfitnatoperRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfitnatoperRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfitnatoperRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfitnatoperRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfitnatoperRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfitnatoperRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfitnatoperRequest.getLfitnatoper()))
		{
			paramMap.put(LFITNATOPER_ID, inquiryLfitnatoperRequest.getLfitnatoper().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFITNATOPERS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfitnatoper.dao.ILfitnatoperDAO#insertLfitnatoper(com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfitnatoper> insertLfitnatoper(LfitnatoperRequest lfitnatoperRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfitnatoper lfitnatoper = lfitnatoperRequest.getLfitnatoper();

		lfitnatoper.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFITNATOPER, lfitnatoperRequest));
   lfitnatoper.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfitnatoperRequest));
		InternalResultsResponse<Lfitnatoper> response = new InternalResultsResponse<Lfitnatoper>();
		response.addResult(lfitnatoper);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitnatoper.dao.ILfitnatoperDAO#deleteLfitnatoper(com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest)
	 */ 
	@Override
	public InternalResponse deleteLfitnatoper(LfitnatoperRequest lfitnatoperRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFITNATOPER, lfitnatoperRequest.getLfitnatoper(), response);
   lfitnatoper.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfitnatoperRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfitnatoper.dac.ILfitnatoperDAC#updateLfitnatoper(com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfitnatoper> updateLfitnatoper(LfitnatoperRequest lfitnatoperRequest)
	{
		InternalResultsResponse<Lfitnatoper> response = new InternalResultsResponse<Lfitnatoper>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFITNATOPER, lfitnatoperRequest);
   lfitnatoper.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfitnatoperRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFITNATOPER_VALIDATOR_LFITNATOPER_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


