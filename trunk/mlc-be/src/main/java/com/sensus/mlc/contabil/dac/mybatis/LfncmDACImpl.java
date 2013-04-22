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
import com.sensus.mlc.lfncm.dac.ILfncmDAC;
import com.sensus.mlc.lfncm.model.Lfncm;
import com.sensus.mlc.lfncm.model.LfncmOrderByEnum;
import com.sensus.mlc.lfncm.model.request.InquiryLfncmRequest;
import com.sensus.mlc.lfncm.model.request.LfncmRequest;

/** 
 * The Class LfncmDACImpl.
 */ 
public class LfncmDACImpl extends SqlSessionDaoSupport implements ILfncmDAC
{ 

	/** The Constant LFNCM_NAMESPACE. */ 
	private static final String LFNCM_NAMESPACE = "Lfncm.";

	/** The Constant FETCH_LFNCM_BY_ID. */ 
	private static final String FETCH_LFNCM_BY_ID = LFNCM_NAMESPACE + "fetchLfncmById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFNCM_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFNCM. */ 
	private static final String DELETE_SMART_POINT_FROM_LFNCM = LFNCM_NAMESPACE + "deleteSmartPointFromLfncm";

	/** The Constant FETCH_LFNCM_NAME_BY_ID. */ 
	private static final String FETCH_LFNCM_NAME_BY_ID = LFNCM_NAMESPACE + "fetchLfncmNameById";

	/** The Constant UPDATE_AUTO_LFNCM_LFNCM. */ 
	private static final String UPDATE_AUTO_LFNCM_LFNCM = LFNCM_NAMESPACE + "updateAutoLfncmLfncm";

	/** The Constant DELETE_LFNCM. */ 
	private static final String DELETE_LFNCM = LFNCM_NAMESPACE + "deleteLfncm";

	/** The Constant FETCH_LFNCMS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFNCMS_BY_LIGHT_ID = LFNCM_NAMESPACE + "fetchLfncmsByLightId";

	/** The Constant FETCH_LFNCMS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFNCMS_BY_SMART_POINT_ID = LFNCM_NAMESPACE + "fetchLfncmsBySmartPointId";

	/** The Constant INSERT_LFNCM. */ 
	private static final String INSERT_LFNCM = LFNCM_NAMESPACE + "insertLfncm";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFNCM = LFNCM_NAMESPACE + "updateLfncm";
private static final String SENSUS_MLC_LFNCM_VALIDATOR_LFNCM_ALREADY_EXISTS = error.update.lfncm;
 
	/** The Constant INSERT_SMART_POINT_TO_LFNCM. */ 
	private static final String INSERT_SMART_POINT_TO_LFNCM = LFNCM_NAMESPACE + "insertSmartPointToLfncm";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFNCM = LFNCM_NAMESPACE + "fetchLightsBelongLfncm";

	/** The Constant FETCH_ALL_LFNCMS. */ 
	private static final String FETCH_ALL_LFNCMS = LFNCM_NAMESPACE + "fetchAllLfncms";

	/** The Constant LFNCMID. */ 
	private static final String LFNCM_ID = "lfncmId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFNCMNAME. */ 
	private static final String LFNCMNAME = "lfncmname";

	/** The Constant AUTOLFNCM. */ 
	private static final String AUTOLFNCM = "autolfncm";

	/** The Constant AUTOLFNCM. */ 
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
	 * @see com.sensus.mlc.lfncm.dao.ILfncmDAO#fetchAllLfncms(com.sensus.mlc.lfncm.model.request.InquiryLfncmRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfncm> fetchAllLfncm(InquiryLfncmRequest inquiryLfncmRequest)
	{
		InternalResultsResponse<Lfncm> response = new InternalResultsResponse<Lfncm>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfncmRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfncmRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfncmRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfncmRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfncmRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfncmRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfncmRequest.getLfncm()))
		{
			paramMap.put(LFNCM_ID, inquiryLfncmRequest.getLfncm().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFNCMS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfncm.dao.ILfncmDAO#insertLfncm(com.sensus.mlc.lfncm.model.request.LfncmRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfncm> insertLfncm(LfncmRequest lfncmRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfncm lfncm = lfncmRequest.getLfncm();

		lfncm.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFNCM, lfncmRequest));
   lfncm.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfncmRequest));
		InternalResultsResponse<Lfncm> response = new InternalResultsResponse<Lfncm>();
		response.addResult(lfncm);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfncm.dao.ILfncmDAO#deleteLfncm(com.sensus.mlc.lfncm.model.request.LfncmRequest)
	 */ 
	@Override
	public InternalResponse deleteLfncm(LfncmRequest lfncmRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFNCM, lfncmRequest.getLfncm(), response);
   lfncm.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfncmRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfncm.dac.ILfncmDAC#updateLfncm(com.sensus.mlc.lfncm.model.request.LfncmRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfncm> updateLfncm(LfncmRequest lfncmRequest)
	{
		InternalResultsResponse<Lfncm> response = new InternalResultsResponse<Lfncm>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFNCM, lfncmRequest);
   lfncm.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfncmRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFNCM_VALIDATOR_LFNCM_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


