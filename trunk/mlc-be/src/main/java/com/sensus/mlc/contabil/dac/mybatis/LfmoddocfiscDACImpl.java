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
import com.sensus.mlc.lfmoddocfisc.dac.ILfmoddocfiscDAC;
import com.sensus.mlc.lfmoddocfisc.model.Lfmoddocfisc;
import com.sensus.mlc.lfmoddocfisc.model.LfmoddocfiscOrderByEnum;
import com.sensus.mlc.lfmoddocfisc.model.request.InquiryLfmoddocfiscRequest;
import com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest;

/** 
 * The Class LfmoddocfiscDACImpl.
 */ 
public class LfmoddocfiscDACImpl extends SqlSessionDaoSupport implements ILfmoddocfiscDAC
{ 

	/** The Constant LFMODDOCFISC_NAMESPACE. */ 
	private static final String LFMODDOCFISC_NAMESPACE = "Lfmoddocfisc.";

	/** The Constant FETCH_LFMODDOCFISC_BY_ID. */ 
	private static final String FETCH_LFMODDOCFISC_BY_ID = LFMODDOCFISC_NAMESPACE + "fetchLfmoddocfiscById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFMODDOCFISC_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFMODDOCFISC. */ 
	private static final String DELETE_SMART_POINT_FROM_LFMODDOCFISC = LFMODDOCFISC_NAMESPACE + "deleteSmartPointFromLfmoddocfisc";

	/** The Constant FETCH_LFMODDOCFISC_NAME_BY_ID. */ 
	private static final String FETCH_LFMODDOCFISC_NAME_BY_ID = LFMODDOCFISC_NAMESPACE + "fetchLfmoddocfiscNameById";

	/** The Constant UPDATE_AUTO_LFMODDOCFISC_LFMODDOCFISC. */ 
	private static final String UPDATE_AUTO_LFMODDOCFISC_LFMODDOCFISC = LFMODDOCFISC_NAMESPACE + "updateAutoLfmoddocfiscLfmoddocfisc";

	/** The Constant DELETE_LFMODDOCFISC. */ 
	private static final String DELETE_LFMODDOCFISC = LFMODDOCFISC_NAMESPACE + "deleteLfmoddocfisc";

	/** The Constant FETCH_LFMODDOCFISCS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFMODDOCFISCS_BY_LIGHT_ID = LFMODDOCFISC_NAMESPACE + "fetchLfmoddocfiscsByLightId";

	/** The Constant FETCH_LFMODDOCFISCS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFMODDOCFISCS_BY_SMART_POINT_ID = LFMODDOCFISC_NAMESPACE + "fetchLfmoddocfiscsBySmartPointId";

	/** The Constant INSERT_LFMODDOCFISC. */ 
	private static final String INSERT_LFMODDOCFISC = LFMODDOCFISC_NAMESPACE + "insertLfmoddocfisc";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFMODDOCFISC = LFMODDOCFISC_NAMESPACE + "updateLfmoddocfisc";
private static final String SENSUS_MLC_LFMODDOCFISC_VALIDATOR_LFMODDOCFISC_ALREADY_EXISTS = error.update.lfmoddocfisc;
 
	/** The Constant INSERT_SMART_POINT_TO_LFMODDOCFISC. */ 
	private static final String INSERT_SMART_POINT_TO_LFMODDOCFISC = LFMODDOCFISC_NAMESPACE + "insertSmartPointToLfmoddocfisc";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFMODDOCFISC = LFMODDOCFISC_NAMESPACE + "fetchLightsBelongLfmoddocfisc";

	/** The Constant FETCH_ALL_LFMODDOCFISCS. */ 
	private static final String FETCH_ALL_LFMODDOCFISCS = LFMODDOCFISC_NAMESPACE + "fetchAllLfmoddocfiscs";

	/** The Constant LFMODDOCFISCID. */ 
	private static final String LFMODDOCFISC_ID = "lfmoddocfiscId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFMODDOCFISCNAME. */ 
	private static final String LFMODDOCFISCNAME = "lfmoddocfiscname";

	/** The Constant AUTOLFMODDOCFISC. */ 
	private static final String AUTOLFMODDOCFISC = "autolfmoddocfisc";

	/** The Constant AUTOLFMODDOCFISC. */ 
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
	 * @see com.sensus.mlc.lfmoddocfisc.dao.ILfmoddocfiscDAO#fetchAllLfmoddocfiscs(com.sensus.mlc.lfmoddocfisc.model.request.InquiryLfmoddocfiscRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfmoddocfisc> fetchAllLfmoddocfisc(InquiryLfmoddocfiscRequest inquiryLfmoddocfiscRequest)
	{
		InternalResultsResponse<Lfmoddocfisc> response = new InternalResultsResponse<Lfmoddocfisc>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfmoddocfiscRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfmoddocfiscRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfmoddocfiscRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfmoddocfiscRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfmoddocfiscRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfmoddocfiscRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfmoddocfiscRequest.getLfmoddocfisc()))
		{
			paramMap.put(LFMODDOCFISC_ID, inquiryLfmoddocfiscRequest.getLfmoddocfisc().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFMODDOCFISCS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfmoddocfisc.dao.ILfmoddocfiscDAO#insertLfmoddocfisc(com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfmoddocfisc> insertLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfmoddocfisc lfmoddocfisc = lfmoddocfiscRequest.getLfmoddocfisc();

		lfmoddocfisc.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFMODDOCFISC, lfmoddocfiscRequest));
   lfmoddocfisc.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfmoddocfiscRequest));
		InternalResultsResponse<Lfmoddocfisc> response = new InternalResultsResponse<Lfmoddocfisc>();
		response.addResult(lfmoddocfisc);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmoddocfisc.dao.ILfmoddocfiscDAO#deleteLfmoddocfisc(com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest)
	 */ 
	@Override
	public InternalResponse deleteLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFMODDOCFISC, lfmoddocfiscRequest.getLfmoddocfisc(), response);
   lfmoddocfisc.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfmoddocfiscRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfmoddocfisc.dac.ILfmoddocfiscDAC#updateLfmoddocfisc(com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfmoddocfisc> updateLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest)
	{
		InternalResultsResponse<Lfmoddocfisc> response = new InternalResultsResponse<Lfmoddocfisc>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFMODDOCFISC, lfmoddocfiscRequest);
   lfmoddocfisc.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfmoddocfiscRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFMODDOCFISC_VALIDATOR_LFMODDOCFISC_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


