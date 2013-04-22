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
import com.sensus.mlc.lftipofisccli.dac.ILftipofisccliDAC;
import com.sensus.mlc.lftipofisccli.model.Lftipofisccli;
import com.sensus.mlc.lftipofisccli.model.LftipofisccliOrderByEnum;
import com.sensus.mlc.lftipofisccli.model.request.InquiryLftipofisccliRequest;
import com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest;

/** 
 * The Class LftipofisccliDACImpl.
 */ 
public class LftipofisccliDACImpl extends SqlSessionDaoSupport implements ILftipofisccliDAC
{ 

	/** The Constant LFTIPOFISCCLI_NAMESPACE. */ 
	private static final String LFTIPOFISCCLI_NAMESPACE = "Lftipofisccli.";

	/** The Constant FETCH_LFTIPOFISCCLI_BY_ID. */ 
	private static final String FETCH_LFTIPOFISCCLI_BY_ID = LFTIPOFISCCLI_NAMESPACE + "fetchLftipofisccliById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFTIPOFISCCLI_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFTIPOFISCCLI. */ 
	private static final String DELETE_SMART_POINT_FROM_LFTIPOFISCCLI = LFTIPOFISCCLI_NAMESPACE + "deleteSmartPointFromLftipofisccli";

	/** The Constant FETCH_LFTIPOFISCCLI_NAME_BY_ID. */ 
	private static final String FETCH_LFTIPOFISCCLI_NAME_BY_ID = LFTIPOFISCCLI_NAMESPACE + "fetchLftipofisccliNameById";

	/** The Constant UPDATE_AUTO_LFTIPOFISCCLI_LFTIPOFISCCLI. */ 
	private static final String UPDATE_AUTO_LFTIPOFISCCLI_LFTIPOFISCCLI = LFTIPOFISCCLI_NAMESPACE + "updateAutoLftipofisccliLftipofisccli";

	/** The Constant DELETE_LFTIPOFISCCLI. */ 
	private static final String DELETE_LFTIPOFISCCLI = LFTIPOFISCCLI_NAMESPACE + "deleteLftipofisccli";

	/** The Constant FETCH_LFTIPOFISCCLIS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFTIPOFISCCLIS_BY_LIGHT_ID = LFTIPOFISCCLI_NAMESPACE + "fetchLftipofiscclisByLightId";

	/** The Constant FETCH_LFTIPOFISCCLIS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFTIPOFISCCLIS_BY_SMART_POINT_ID = LFTIPOFISCCLI_NAMESPACE + "fetchLftipofiscclisBySmartPointId";

	/** The Constant INSERT_LFTIPOFISCCLI. */ 
	private static final String INSERT_LFTIPOFISCCLI = LFTIPOFISCCLI_NAMESPACE + "insertLftipofisccli";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFTIPOFISCCLI = LFTIPOFISCCLI_NAMESPACE + "updateLftipofisccli";
private static final String SENSUS_MLC_LFTIPOFISCCLI_VALIDATOR_LFTIPOFISCCLI_ALREADY_EXISTS = error.update.lftipofisccli;
 
	/** The Constant INSERT_SMART_POINT_TO_LFTIPOFISCCLI. */ 
	private static final String INSERT_SMART_POINT_TO_LFTIPOFISCCLI = LFTIPOFISCCLI_NAMESPACE + "insertSmartPointToLftipofisccli";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFTIPOFISCCLI = LFTIPOFISCCLI_NAMESPACE + "fetchLightsBelongLftipofisccli";

	/** The Constant FETCH_ALL_LFTIPOFISCCLIS. */ 
	private static final String FETCH_ALL_LFTIPOFISCCLIS = LFTIPOFISCCLI_NAMESPACE + "fetchAllLftipofiscclis";

	/** The Constant LFTIPOFISCCLIID. */ 
	private static final String LFTIPOFISCCLI_ID = "lftipofisccliId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFTIPOFISCCLINAME. */ 
	private static final String LFTIPOFISCCLINAME = "lftipofisccliname";

	/** The Constant AUTOLFTIPOFISCCLI. */ 
	private static final String AUTOLFTIPOFISCCLI = "autolftipofisccli";

	/** The Constant AUTOLFTIPOFISCCLI. */ 
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
	 * @see com.sensus.mlc.lftipofisccli.dao.ILftipofisccliDAO#fetchAllLftipofiscclis(com.sensus.mlc.lftipofisccli.model.request.InquiryLftipofisccliRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lftipofisccli> fetchAllLftipofisccli(InquiryLftipofisccliRequest inquiryLftipofisccliRequest)
	{
		InternalResultsResponse<Lftipofisccli> response = new InternalResultsResponse<Lftipofisccli>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLftipofisccliRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLftipofisccliRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLftipofisccliRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLftipofisccliRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLftipofisccliRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLftipofisccliRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLftipofisccliRequest.getLftipofisccli()))
		{
			paramMap.put(LFTIPOFISCCLI_ID, inquiryLftipofisccliRequest.getLftipofisccli().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFTIPOFISCCLIS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lftipofisccli.dao.ILftipofisccliDAO#insertLftipofisccli(com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lftipofisccli> insertLftipofisccli(LftipofisccliRequest lftipofisccliRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lftipofisccli lftipofisccli = lftipofisccliRequest.getLftipofisccli();

		lftipofisccli.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFTIPOFISCCLI, lftipofisccliRequest));
   lftipofisccli.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lftipofisccliRequest));
		InternalResultsResponse<Lftipofisccli> response = new InternalResultsResponse<Lftipofisccli>();
		response.addResult(lftipofisccli);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftipofisccli.dao.ILftipofisccliDAO#deleteLftipofisccli(com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest)
	 */ 
	@Override
	public InternalResponse deleteLftipofisccli(LftipofisccliRequest lftipofisccliRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFTIPOFISCCLI, lftipofisccliRequest.getLftipofisccli(), response);
   lftipofisccli.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lftipofisccliRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lftipofisccli.dac.ILftipofisccliDAC#updateLftipofisccli(com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lftipofisccli> updateLftipofisccli(LftipofisccliRequest lftipofisccliRequest)
	{
		InternalResultsResponse<Lftipofisccli> response = new InternalResultsResponse<Lftipofisccli>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFTIPOFISCCLI, lftipofisccliRequest);
   lftipofisccli.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lftipofisccliRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFTIPOFISCCLI_VALIDATOR_LFTIPOFISCCLI_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


