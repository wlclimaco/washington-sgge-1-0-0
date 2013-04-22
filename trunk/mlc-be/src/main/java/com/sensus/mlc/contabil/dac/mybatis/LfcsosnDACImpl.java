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
import com.sensus.mlc.lfcsosn.dac.ILfcsosnDAC;
import com.sensus.mlc.lfcsosn.model.Lfcsosn;
import com.sensus.mlc.lfcsosn.model.LfcsosnOrderByEnum;
import com.sensus.mlc.lfcsosn.model.request.InquiryLfcsosnRequest;
import com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest;

/** 
 * The Class LfcsosnDACImpl.
 */ 
public class LfcsosnDACImpl extends SqlSessionDaoSupport implements ILfcsosnDAC
{ 

	/** The Constant LFCSOSN_NAMESPACE. */ 
	private static final String LFCSOSN_NAMESPACE = "Lfcsosn.";

	/** The Constant FETCH_LFCSOSN_BY_ID. */ 
	private static final String FETCH_LFCSOSN_BY_ID = LFCSOSN_NAMESPACE + "fetchLfcsosnById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFCSOSN_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFCSOSN. */ 
	private static final String DELETE_SMART_POINT_FROM_LFCSOSN = LFCSOSN_NAMESPACE + "deleteSmartPointFromLfcsosn";

	/** The Constant FETCH_LFCSOSN_NAME_BY_ID. */ 
	private static final String FETCH_LFCSOSN_NAME_BY_ID = LFCSOSN_NAMESPACE + "fetchLfcsosnNameById";

	/** The Constant UPDATE_AUTO_LFCSOSN_LFCSOSN. */ 
	private static final String UPDATE_AUTO_LFCSOSN_LFCSOSN = LFCSOSN_NAMESPACE + "updateAutoLfcsosnLfcsosn";

	/** The Constant DELETE_LFCSOSN. */ 
	private static final String DELETE_LFCSOSN = LFCSOSN_NAMESPACE + "deleteLfcsosn";

	/** The Constant FETCH_LFCSOSNS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFCSOSNS_BY_LIGHT_ID = LFCSOSN_NAMESPACE + "fetchLfcsosnsByLightId";

	/** The Constant FETCH_LFCSOSNS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFCSOSNS_BY_SMART_POINT_ID = LFCSOSN_NAMESPACE + "fetchLfcsosnsBySmartPointId";

	/** The Constant INSERT_LFCSOSN. */ 
	private static final String INSERT_LFCSOSN = LFCSOSN_NAMESPACE + "insertLfcsosn";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFCSOSN = LFCSOSN_NAMESPACE + "updateLfcsosn";
private static final String SENSUS_MLC_LFCSOSN_VALIDATOR_LFCSOSN_ALREADY_EXISTS = error.update.lfcsosn;
 
	/** The Constant INSERT_SMART_POINT_TO_LFCSOSN. */ 
	private static final String INSERT_SMART_POINT_TO_LFCSOSN = LFCSOSN_NAMESPACE + "insertSmartPointToLfcsosn";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFCSOSN = LFCSOSN_NAMESPACE + "fetchLightsBelongLfcsosn";

	/** The Constant FETCH_ALL_LFCSOSNS. */ 
	private static final String FETCH_ALL_LFCSOSNS = LFCSOSN_NAMESPACE + "fetchAllLfcsosns";

	/** The Constant LFCSOSNID. */ 
	private static final String LFCSOSN_ID = "lfcsosnId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFCSOSNNAME. */ 
	private static final String LFCSOSNNAME = "lfcsosnname";

	/** The Constant AUTOLFCSOSN. */ 
	private static final String AUTOLFCSOSN = "autolfcsosn";

	/** The Constant AUTOLFCSOSN. */ 
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
	 * @see com.sensus.mlc.lfcsosn.dao.ILfcsosnDAO#fetchAllLfcsosns(com.sensus.mlc.lfcsosn.model.request.InquiryLfcsosnRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfcsosn> fetchAllLfcsosn(InquiryLfcsosnRequest inquiryLfcsosnRequest)
	{
		InternalResultsResponse<Lfcsosn> response = new InternalResultsResponse<Lfcsosn>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfcsosnRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfcsosnRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfcsosnRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfcsosnRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfcsosnRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfcsosnRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfcsosnRequest.getLfcsosn()))
		{
			paramMap.put(LFCSOSN_ID, inquiryLfcsosnRequest.getLfcsosn().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFCSOSNS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfcsosn.dao.ILfcsosnDAO#insertLfcsosn(com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfcsosn> insertLfcsosn(LfcsosnRequest lfcsosnRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfcsosn lfcsosn = lfcsosnRequest.getLfcsosn();

		lfcsosn.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFCSOSN, lfcsosnRequest));
   lfcsosn.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfcsosnRequest));
		InternalResultsResponse<Lfcsosn> response = new InternalResultsResponse<Lfcsosn>();
		response.addResult(lfcsosn);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfcsosn.dao.ILfcsosnDAO#deleteLfcsosn(com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest)
	 */ 
	@Override
	public InternalResponse deleteLfcsosn(LfcsosnRequest lfcsosnRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFCSOSN, lfcsosnRequest.getLfcsosn(), response);
   lfcsosn.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfcsosnRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfcsosn.dac.ILfcsosnDAC#updateLfcsosn(com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfcsosn> updateLfcsosn(LfcsosnRequest lfcsosnRequest)
	{
		InternalResultsResponse<Lfcsosn> response = new InternalResultsResponse<Lfcsosn>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFCSOSN, lfcsosnRequest);
   lfcsosn.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfcsosnRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFCSOSN_VALIDATOR_LFCSOSN_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


