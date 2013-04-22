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
import com.sensus.mlc.lfncmnbm.dac.ILfncmnbmDAC;
import com.sensus.mlc.lfncmnbm.model.Lfncmnbm;
import com.sensus.mlc.lfncmnbm.model.LfncmnbmOrderByEnum;
import com.sensus.mlc.lfncmnbm.model.request.InquiryLfncmnbmRequest;
import com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest;

/** 
 * The Class LfncmnbmDACImpl.
 */ 
public class LfncmnbmDACImpl extends SqlSessionDaoSupport implements ILfncmnbmDAC
{ 

	/** The Constant LFNCMNBM_NAMESPACE. */ 
	private static final String LFNCMNBM_NAMESPACE = "Lfncmnbm.";

	/** The Constant FETCH_LFNCMNBM_BY_ID. */ 
	private static final String FETCH_LFNCMNBM_BY_ID = LFNCMNBM_NAMESPACE + "fetchLfncmnbmById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFNCMNBM_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFNCMNBM. */ 
	private static final String DELETE_SMART_POINT_FROM_LFNCMNBM = LFNCMNBM_NAMESPACE + "deleteSmartPointFromLfncmnbm";

	/** The Constant FETCH_LFNCMNBM_NAME_BY_ID. */ 
	private static final String FETCH_LFNCMNBM_NAME_BY_ID = LFNCMNBM_NAMESPACE + "fetchLfncmnbmNameById";

	/** The Constant UPDATE_AUTO_LFNCMNBM_LFNCMNBM. */ 
	private static final String UPDATE_AUTO_LFNCMNBM_LFNCMNBM = LFNCMNBM_NAMESPACE + "updateAutoLfncmnbmLfncmnbm";

	/** The Constant DELETE_LFNCMNBM. */ 
	private static final String DELETE_LFNCMNBM = LFNCMNBM_NAMESPACE + "deleteLfncmnbm";

	/** The Constant FETCH_LFNCMNBMS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFNCMNBMS_BY_LIGHT_ID = LFNCMNBM_NAMESPACE + "fetchLfncmnbmsByLightId";

	/** The Constant FETCH_LFNCMNBMS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFNCMNBMS_BY_SMART_POINT_ID = LFNCMNBM_NAMESPACE + "fetchLfncmnbmsBySmartPointId";

	/** The Constant INSERT_LFNCMNBM. */ 
	private static final String INSERT_LFNCMNBM = LFNCMNBM_NAMESPACE + "insertLfncmnbm";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFNCMNBM = LFNCMNBM_NAMESPACE + "updateLfncmnbm";
private static final String SENSUS_MLC_LFNCMNBM_VALIDATOR_LFNCMNBM_ALREADY_EXISTS = error.update.lfncmnbm;
 
	/** The Constant INSERT_SMART_POINT_TO_LFNCMNBM. */ 
	private static final String INSERT_SMART_POINT_TO_LFNCMNBM = LFNCMNBM_NAMESPACE + "insertSmartPointToLfncmnbm";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFNCMNBM = LFNCMNBM_NAMESPACE + "fetchLightsBelongLfncmnbm";

	/** The Constant FETCH_ALL_LFNCMNBMS. */ 
	private static final String FETCH_ALL_LFNCMNBMS = LFNCMNBM_NAMESPACE + "fetchAllLfncmnbms";

	/** The Constant LFNCMNBMID. */ 
	private static final String LFNCMNBM_ID = "lfncmnbmId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFNCMNBMNAME. */ 
	private static final String LFNCMNBMNAME = "lfncmnbmname";

	/** The Constant AUTOLFNCMNBM. */ 
	private static final String AUTOLFNCMNBM = "autolfncmnbm";

	/** The Constant AUTOLFNCMNBM. */ 
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
	 * @see com.sensus.mlc.lfncmnbm.dao.ILfncmnbmDAO#fetchAllLfncmnbms(com.sensus.mlc.lfncmnbm.model.request.InquiryLfncmnbmRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfncmnbm> fetchAllLfncmnbm(InquiryLfncmnbmRequest inquiryLfncmnbmRequest)
	{
		InternalResultsResponse<Lfncmnbm> response = new InternalResultsResponse<Lfncmnbm>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfncmnbmRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfncmnbmRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfncmnbmRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfncmnbmRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfncmnbmRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfncmnbmRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfncmnbmRequest.getLfncmnbm()))
		{
			paramMap.put(LFNCMNBM_ID, inquiryLfncmnbmRequest.getLfncmnbm().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFNCMNBMS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfncmnbm.dao.ILfncmnbmDAO#insertLfncmnbm(com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfncmnbm> insertLfncmnbm(LfncmnbmRequest lfncmnbmRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfncmnbm lfncmnbm = lfncmnbmRequest.getLfncmnbm();

		lfncmnbm.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFNCMNBM, lfncmnbmRequest));
   lfncmnbm.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfncmnbmRequest));
		InternalResultsResponse<Lfncmnbm> response = new InternalResultsResponse<Lfncmnbm>();
		response.addResult(lfncmnbm);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfncmnbm.dao.ILfncmnbmDAO#deleteLfncmnbm(com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest)
	 */ 
	@Override
	public InternalResponse deleteLfncmnbm(LfncmnbmRequest lfncmnbmRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFNCMNBM, lfncmnbmRequest.getLfncmnbm(), response);
   lfncmnbm.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfncmnbmRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfncmnbm.dac.ILfncmnbmDAC#updateLfncmnbm(com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfncmnbm> updateLfncmnbm(LfncmnbmRequest lfncmnbmRequest)
	{
		InternalResultsResponse<Lfncmnbm> response = new InternalResultsResponse<Lfncmnbm>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFNCMNBM, lfncmnbmRequest);
   lfncmnbm.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfncmnbmRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFNCMNBM_VALIDATOR_LFNCMNBM_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


