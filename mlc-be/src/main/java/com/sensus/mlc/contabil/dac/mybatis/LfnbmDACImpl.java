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
import com.sensus.mlc.lfnbm.dac.ILfnbmDAC;
import com.sensus.mlc.lfnbm.model.Lfnbm;
import com.sensus.mlc.lfnbm.model.LfnbmOrderByEnum;
import com.sensus.mlc.lfnbm.model.request.InquiryLfnbmRequest;
import com.sensus.mlc.lfnbm.model.request.LfnbmRequest;

/** 
 * The Class LfnbmDACImpl.
 */ 
public class LfnbmDACImpl extends SqlSessionDaoSupport implements ILfnbmDAC
{ 

	/** The Constant LFNBM_NAMESPACE. */ 
	private static final String LFNBM_NAMESPACE = "Lfnbm.";

	/** The Constant FETCH_LFNBM_BY_ID. */ 
	private static final String FETCH_LFNBM_BY_ID = LFNBM_NAMESPACE + "fetchLfnbmById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFNBM_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFNBM. */ 
	private static final String DELETE_SMART_POINT_FROM_LFNBM = LFNBM_NAMESPACE + "deleteSmartPointFromLfnbm";

	/** The Constant FETCH_LFNBM_NAME_BY_ID. */ 
	private static final String FETCH_LFNBM_NAME_BY_ID = LFNBM_NAMESPACE + "fetchLfnbmNameById";

	/** The Constant UPDATE_AUTO_LFNBM_LFNBM. */ 
	private static final String UPDATE_AUTO_LFNBM_LFNBM = LFNBM_NAMESPACE + "updateAutoLfnbmLfnbm";

	/** The Constant DELETE_LFNBM. */ 
	private static final String DELETE_LFNBM = LFNBM_NAMESPACE + "deleteLfnbm";

	/** The Constant FETCH_LFNBMS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFNBMS_BY_LIGHT_ID = LFNBM_NAMESPACE + "fetchLfnbmsByLightId";

	/** The Constant FETCH_LFNBMS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFNBMS_BY_SMART_POINT_ID = LFNBM_NAMESPACE + "fetchLfnbmsBySmartPointId";

	/** The Constant INSERT_LFNBM. */ 
	private static final String INSERT_LFNBM = LFNBM_NAMESPACE + "insertLfnbm";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFNBM = LFNBM_NAMESPACE + "updateLfnbm";
private static final String SENSUS_MLC_LFNBM_VALIDATOR_LFNBM_ALREADY_EXISTS = error.update.lfnbm;
 
	/** The Constant INSERT_SMART_POINT_TO_LFNBM. */ 
	private static final String INSERT_SMART_POINT_TO_LFNBM = LFNBM_NAMESPACE + "insertSmartPointToLfnbm";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFNBM = LFNBM_NAMESPACE + "fetchLightsBelongLfnbm";

	/** The Constant FETCH_ALL_LFNBMS. */ 
	private static final String FETCH_ALL_LFNBMS = LFNBM_NAMESPACE + "fetchAllLfnbms";

	/** The Constant LFNBMID. */ 
	private static final String LFNBM_ID = "lfnbmId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFNBMNAME. */ 
	private static final String LFNBMNAME = "lfnbmname";

	/** The Constant AUTOLFNBM. */ 
	private static final String AUTOLFNBM = "autolfnbm";

	/** The Constant AUTOLFNBM. */ 
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
	 * @see com.sensus.mlc.lfnbm.dao.ILfnbmDAO#fetchAllLfnbms(com.sensus.mlc.lfnbm.model.request.InquiryLfnbmRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfnbm> fetchAllLfnbm(InquiryLfnbmRequest inquiryLfnbmRequest)
	{
		InternalResultsResponse<Lfnbm> response = new InternalResultsResponse<Lfnbm>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfnbmRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfnbmRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfnbmRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfnbmRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfnbmRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfnbmRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfnbmRequest.getLfnbm()))
		{
			paramMap.put(LFNBM_ID, inquiryLfnbmRequest.getLfnbm().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFNBMS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfnbm.dao.ILfnbmDAO#insertLfnbm(com.sensus.mlc.lfnbm.model.request.LfnbmRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfnbm> insertLfnbm(LfnbmRequest lfnbmRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfnbm lfnbm = lfnbmRequest.getLfnbm();

		lfnbm.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFNBM, lfnbmRequest));
   lfnbm.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfnbmRequest));
		InternalResultsResponse<Lfnbm> response = new InternalResultsResponse<Lfnbm>();
		response.addResult(lfnbm);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfnbm.dao.ILfnbmDAO#deleteLfnbm(com.sensus.mlc.lfnbm.model.request.LfnbmRequest)
	 */ 
	@Override
	public InternalResponse deleteLfnbm(LfnbmRequest lfnbmRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFNBM, lfnbmRequest.getLfnbm(), response);
   lfnbm.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfnbmRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfnbm.dac.ILfnbmDAC#updateLfnbm(com.sensus.mlc.lfnbm.model.request.LfnbmRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfnbm> updateLfnbm(LfnbmRequest lfnbmRequest)
	{
		InternalResultsResponse<Lfnbm> response = new InternalResultsResponse<Lfnbm>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFNBM, lfnbmRequest);
   lfnbm.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfnbmRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFNBM_VALIDATOR_LFNBM_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


