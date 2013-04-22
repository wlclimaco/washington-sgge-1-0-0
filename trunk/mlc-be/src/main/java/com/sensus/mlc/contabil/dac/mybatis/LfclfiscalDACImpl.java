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
import com.sensus.mlc.lfclfiscal.dac.ILfclfiscalDAC;
import com.sensus.mlc.lfclfiscal.model.Lfclfiscal;
import com.sensus.mlc.lfclfiscal.model.LfclfiscalOrderByEnum;
import com.sensus.mlc.lfclfiscal.model.request.InquiryLfclfiscalRequest;
import com.sensus.mlc.lfclfiscal.model.request.LfclfiscalRequest;

/** 
 * The Class LfclfiscalDACImpl.
 */ 
public class LfclfiscalDACImpl extends SqlSessionDaoSupport implements ILfclfiscalDAC
{ 

	/** The Constant LFCLFISCAL_NAMESPACE. */ 
	private static final String LFCLFISCAL_NAMESPACE = "Lfclfiscal.";

	/** The Constant FETCH_LFCLFISCAL_BY_ID. */ 
	private static final String FETCH_LFCLFISCAL_BY_ID = LFCLFISCAL_NAMESPACE + "fetchLfclfiscalById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFCLFISCAL_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFCLFISCAL. */ 
	private static final String DELETE_SMART_POINT_FROM_LFCLFISCAL = LFCLFISCAL_NAMESPACE + "deleteSmartPointFromLfclfiscal";

	/** The Constant FETCH_LFCLFISCAL_NAME_BY_ID. */ 
	private static final String FETCH_LFCLFISCAL_NAME_BY_ID = LFCLFISCAL_NAMESPACE + "fetchLfclfiscalNameById";

	/** The Constant UPDATE_AUTO_LFCLFISCAL_LFCLFISCAL. */ 
	private static final String UPDATE_AUTO_LFCLFISCAL_LFCLFISCAL = LFCLFISCAL_NAMESPACE + "updateAutoLfclfiscalLfclfiscal";

	/** The Constant DELETE_LFCLFISCAL. */ 
	private static final String DELETE_LFCLFISCAL = LFCLFISCAL_NAMESPACE + "deleteLfclfiscal";

	/** The Constant FETCH_LFCLFISCALS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFCLFISCALS_BY_LIGHT_ID = LFCLFISCAL_NAMESPACE + "fetchLfclfiscalsByLightId";

	/** The Constant FETCH_LFCLFISCALS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFCLFISCALS_BY_SMART_POINT_ID = LFCLFISCAL_NAMESPACE + "fetchLfclfiscalsBySmartPointId";

	/** The Constant INSERT_LFCLFISCAL. */ 
	private static final String INSERT_LFCLFISCAL = LFCLFISCAL_NAMESPACE + "insertLfclfiscal";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFCLFISCAL = LFCLFISCAL_NAMESPACE + "updateLfclfiscal";
private static final String SENSUS_MLC_LFCLFISCAL_VALIDATOR_LFCLFISCAL_ALREADY_EXISTS = error.update.lfclfiscal;
 
	/** The Constant INSERT_SMART_POINT_TO_LFCLFISCAL. */ 
	private static final String INSERT_SMART_POINT_TO_LFCLFISCAL = LFCLFISCAL_NAMESPACE + "insertSmartPointToLfclfiscal";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFCLFISCAL = LFCLFISCAL_NAMESPACE + "fetchLightsBelongLfclfiscal";

	/** The Constant FETCH_ALL_LFCLFISCALS. */ 
	private static final String FETCH_ALL_LFCLFISCALS = LFCLFISCAL_NAMESPACE + "fetchAllLfclfiscals";

	/** The Constant LFCLFISCALID. */ 
	private static final String LFCLFISCAL_ID = "lfclfiscalId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFCLFISCALNAME. */ 
	private static final String LFCLFISCALNAME = "lfclfiscalname";

	/** The Constant AUTOLFCLFISCAL. */ 
	private static final String AUTOLFCLFISCAL = "autolfclfiscal";

	/** The Constant AUTOLFCLFISCAL. */ 
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
	 * @see com.sensus.mlc.lfclfiscal.dao.ILfclfiscalDAO#fetchAllLfclfiscals(com.sensus.mlc.lfclfiscal.model.request.InquiryLfclfiscalRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfclfiscal> fetchAllLfclfiscal(InquiryLfclfiscalRequest inquiryLfclfiscalRequest)
	{
		InternalResultsResponse<Lfclfiscal> response = new InternalResultsResponse<Lfclfiscal>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfclfiscalRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfclfiscalRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfclfiscalRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfclfiscalRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfclfiscalRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfclfiscalRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfclfiscalRequest.getLfclfiscal()))
		{
			paramMap.put(LFCLFISCAL_ID, inquiryLfclfiscalRequest.getLfclfiscal().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFCLFISCALS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfclfiscal.dao.ILfclfiscalDAO#insertLfclfiscal(com.sensus.mlc.lfclfiscal.model.request.LfclfiscalRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfclfiscal> insertLfclfiscal(LfclfiscalRequest lfclfiscalRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfclfiscal lfclfiscal = lfclfiscalRequest.getLfclfiscal();

		lfclfiscal.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFCLFISCAL, lfclfiscalRequest));
   lfclfiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfclfiscalRequest));
		InternalResultsResponse<Lfclfiscal> response = new InternalResultsResponse<Lfclfiscal>();
		response.addResult(lfclfiscal);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfclfiscal.dao.ILfclfiscalDAO#deleteLfclfiscal(com.sensus.mlc.lfclfiscal.model.request.LfclfiscalRequest)
	 */ 
	@Override
	public InternalResponse deleteLfclfiscal(LfclfiscalRequest lfclfiscalRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFCLFISCAL, lfclfiscalRequest.getLfclfiscal(), response);
   lfclfiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfclfiscalRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfclfiscal.dac.ILfclfiscalDAC#updateLfclfiscal(com.sensus.mlc.lfclfiscal.model.request.LfclfiscalRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfclfiscal> updateLfclfiscal(LfclfiscalRequest lfclfiscalRequest)
	{
		InternalResultsResponse<Lfclfiscal> response = new InternalResultsResponse<Lfclfiscal>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFCLFISCAL, lfclfiscalRequest);
   lfclfiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfclfiscalRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFCLFISCAL_VALIDATOR_LFCLFISCAL_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


