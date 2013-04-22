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
import com.sensus.mlc.lfsittrib.dac.ILfsittribDAC;
import com.sensus.mlc.lfsittrib.model.Lfsittrib;
import com.sensus.mlc.lfsittrib.model.LfsittribOrderByEnum;
import com.sensus.mlc.lfsittrib.model.request.InquiryLfsittribRequest;
import com.sensus.mlc.lfsittrib.model.request.LfsittribRequest;

/** 
 * The Class LfsittribDACImpl.
 */ 
public class LfsittribDACImpl extends SqlSessionDaoSupport implements ILfsittribDAC
{ 

	/** The Constant LFSITTRIB_NAMESPACE. */ 
	private static final String LFSITTRIB_NAMESPACE = "Lfsittrib.";

	/** The Constant FETCH_LFSITTRIB_BY_ID. */ 
	private static final String FETCH_LFSITTRIB_BY_ID = LFSITTRIB_NAMESPACE + "fetchLfsittribById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFSITTRIB_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFSITTRIB. */ 
	private static final String DELETE_SMART_POINT_FROM_LFSITTRIB = LFSITTRIB_NAMESPACE + "deleteSmartPointFromLfsittrib";

	/** The Constant FETCH_LFSITTRIB_NAME_BY_ID. */ 
	private static final String FETCH_LFSITTRIB_NAME_BY_ID = LFSITTRIB_NAMESPACE + "fetchLfsittribNameById";

	/** The Constant UPDATE_AUTO_LFSITTRIB_LFSITTRIB. */ 
	private static final String UPDATE_AUTO_LFSITTRIB_LFSITTRIB = LFSITTRIB_NAMESPACE + "updateAutoLfsittribLfsittrib";

	/** The Constant DELETE_LFSITTRIB. */ 
	private static final String DELETE_LFSITTRIB = LFSITTRIB_NAMESPACE + "deleteLfsittrib";

	/** The Constant FETCH_LFSITTRIBS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFSITTRIBS_BY_LIGHT_ID = LFSITTRIB_NAMESPACE + "fetchLfsittribsByLightId";

	/** The Constant FETCH_LFSITTRIBS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFSITTRIBS_BY_SMART_POINT_ID = LFSITTRIB_NAMESPACE + "fetchLfsittribsBySmartPointId";

	/** The Constant INSERT_LFSITTRIB. */ 
	private static final String INSERT_LFSITTRIB = LFSITTRIB_NAMESPACE + "insertLfsittrib";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFSITTRIB = LFSITTRIB_NAMESPACE + "updateLfsittrib";
private static final String SENSUS_MLC_LFSITTRIB_VALIDATOR_LFSITTRIB_ALREADY_EXISTS = error.update.lfsittrib;
 
	/** The Constant INSERT_SMART_POINT_TO_LFSITTRIB. */ 
	private static final String INSERT_SMART_POINT_TO_LFSITTRIB = LFSITTRIB_NAMESPACE + "insertSmartPointToLfsittrib";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFSITTRIB = LFSITTRIB_NAMESPACE + "fetchLightsBelongLfsittrib";

	/** The Constant FETCH_ALL_LFSITTRIBS. */ 
	private static final String FETCH_ALL_LFSITTRIBS = LFSITTRIB_NAMESPACE + "fetchAllLfsittribs";

	/** The Constant LFSITTRIBID. */ 
	private static final String LFSITTRIB_ID = "lfsittribId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFSITTRIBNAME. */ 
	private static final String LFSITTRIBNAME = "lfsittribname";

	/** The Constant AUTOLFSITTRIB. */ 
	private static final String AUTOLFSITTRIB = "autolfsittrib";

	/** The Constant AUTOLFSITTRIB. */ 
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
	 * @see com.sensus.mlc.lfsittrib.dao.ILfsittribDAO#fetchAllLfsittribs(com.sensus.mlc.lfsittrib.model.request.InquiryLfsittribRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfsittrib> fetchAllLfsittrib(InquiryLfsittribRequest inquiryLfsittribRequest)
	{
		InternalResultsResponse<Lfsittrib> response = new InternalResultsResponse<Lfsittrib>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfsittribRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfsittribRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfsittribRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfsittribRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfsittribRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfsittribRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfsittribRequest.getLfsittrib()))
		{
			paramMap.put(LFSITTRIB_ID, inquiryLfsittribRequest.getLfsittrib().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFSITTRIBS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfsittrib.dao.ILfsittribDAO#insertLfsittrib(com.sensus.mlc.lfsittrib.model.request.LfsittribRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfsittrib> insertLfsittrib(LfsittribRequest lfsittribRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfsittrib lfsittrib = lfsittribRequest.getLfsittrib();

		lfsittrib.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFSITTRIB, lfsittribRequest));
   lfsittrib.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfsittribRequest));
		InternalResultsResponse<Lfsittrib> response = new InternalResultsResponse<Lfsittrib>();
		response.addResult(lfsittrib);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfsittrib.dao.ILfsittribDAO#deleteLfsittrib(com.sensus.mlc.lfsittrib.model.request.LfsittribRequest)
	 */ 
	@Override
	public InternalResponse deleteLfsittrib(LfsittribRequest lfsittribRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFSITTRIB, lfsittribRequest.getLfsittrib(), response);
   lfsittrib.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfsittribRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfsittrib.dac.ILfsittribDAC#updateLfsittrib(com.sensus.mlc.lfsittrib.model.request.LfsittribRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfsittrib> updateLfsittrib(LfsittribRequest lfsittribRequest)
	{
		InternalResultsResponse<Lfsittrib> response = new InternalResultsResponse<Lfsittrib>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFSITTRIB, lfsittribRequest);
   lfsittrib.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfsittribRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFSITTRIB_VALIDATOR_LFSITTRIB_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


