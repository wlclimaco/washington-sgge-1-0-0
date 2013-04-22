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
import com.sensus.mlc.lfserie.dac.ILfserieDAC;
import com.sensus.mlc.lfserie.model.Lfserie;
import com.sensus.mlc.lfserie.model.LfserieOrderByEnum;
import com.sensus.mlc.lfserie.model.request.InquiryLfserieRequest;
import com.sensus.mlc.lfserie.model.request.LfserieRequest;

/** 
 * The Class LfserieDACImpl.
 */ 
public class LfserieDACImpl extends SqlSessionDaoSupport implements ILfserieDAC
{ 

	/** The Constant LFSERIE_NAMESPACE. */ 
	private static final String LFSERIE_NAMESPACE = "Lfserie.";

	/** The Constant FETCH_LFSERIE_BY_ID. */ 
	private static final String FETCH_LFSERIE_BY_ID = LFSERIE_NAMESPACE + "fetchLfserieById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFSERIE_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFSERIE. */ 
	private static final String DELETE_SMART_POINT_FROM_LFSERIE = LFSERIE_NAMESPACE + "deleteSmartPointFromLfserie";

	/** The Constant FETCH_LFSERIE_NAME_BY_ID. */ 
	private static final String FETCH_LFSERIE_NAME_BY_ID = LFSERIE_NAMESPACE + "fetchLfserieNameById";

	/** The Constant UPDATE_AUTO_LFSERIE_LFSERIE. */ 
	private static final String UPDATE_AUTO_LFSERIE_LFSERIE = LFSERIE_NAMESPACE + "updateAutoLfserieLfserie";

	/** The Constant DELETE_LFSERIE. */ 
	private static final String DELETE_LFSERIE = LFSERIE_NAMESPACE + "deleteLfserie";

	/** The Constant FETCH_LFSERIES_BY_LIGHT_ID. */ 
	private static final String FETCH_LFSERIES_BY_LIGHT_ID = LFSERIE_NAMESPACE + "fetchLfseriesByLightId";

	/** The Constant FETCH_LFSERIES_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFSERIES_BY_SMART_POINT_ID = LFSERIE_NAMESPACE + "fetchLfseriesBySmartPointId";

	/** The Constant INSERT_LFSERIE. */ 
	private static final String INSERT_LFSERIE = LFSERIE_NAMESPACE + "insertLfserie";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFSERIE = LFSERIE_NAMESPACE + "updateLfserie";
private static final String SENSUS_MLC_LFSERIE_VALIDATOR_LFSERIE_ALREADY_EXISTS = error.update.lfserie;
 
	/** The Constant INSERT_SMART_POINT_TO_LFSERIE. */ 
	private static final String INSERT_SMART_POINT_TO_LFSERIE = LFSERIE_NAMESPACE + "insertSmartPointToLfserie";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFSERIE = LFSERIE_NAMESPACE + "fetchLightsBelongLfserie";

	/** The Constant FETCH_ALL_LFSERIES. */ 
	private static final String FETCH_ALL_LFSERIES = LFSERIE_NAMESPACE + "fetchAllLfseries";

	/** The Constant LFSERIEID. */ 
	private static final String LFSERIE_ID = "lfserieId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFSERIENAME. */ 
	private static final String LFSERIENAME = "lfseriename";

	/** The Constant AUTOLFSERIE. */ 
	private static final String AUTOLFSERIE = "autolfserie";

	/** The Constant AUTOLFSERIE. */ 
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
	 * @see com.sensus.mlc.lfserie.dao.ILfserieDAO#fetchAllLfseries(com.sensus.mlc.lfserie.model.request.InquiryLfserieRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfserie> fetchAllLfserie(InquiryLfserieRequest inquiryLfserieRequest)
	{
		InternalResultsResponse<Lfserie> response = new InternalResultsResponse<Lfserie>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfserieRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfserieRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfserieRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfserieRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfserieRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfserieRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfserieRequest.getLfserie()))
		{
			paramMap.put(LFSERIE_ID, inquiryLfserieRequest.getLfserie().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFSERIES, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfserie.dao.ILfserieDAO#insertLfserie(com.sensus.mlc.lfserie.model.request.LfserieRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfserie> insertLfserie(LfserieRequest lfserieRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfserie lfserie = lfserieRequest.getLfserie();

		lfserie.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFSERIE, lfserieRequest));
   lfserie.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfserieRequest));
		InternalResultsResponse<Lfserie> response = new InternalResultsResponse<Lfserie>();
		response.addResult(lfserie);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfserie.dao.ILfserieDAO#deleteLfserie(com.sensus.mlc.lfserie.model.request.LfserieRequest)
	 */ 
	@Override
	public InternalResponse deleteLfserie(LfserieRequest lfserieRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFSERIE, lfserieRequest.getLfserie(), response);
   lfserie.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfserieRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfserie.dac.ILfserieDAC#updateLfserie(com.sensus.mlc.lfserie.model.request.LfserieRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfserie> updateLfserie(LfserieRequest lfserieRequest)
	{
		InternalResultsResponse<Lfserie> response = new InternalResultsResponse<Lfserie>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFSERIE, lfserieRequest);
   lfserie.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfserieRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFSERIE_VALIDATOR_LFSERIE_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


