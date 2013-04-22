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
import com.sensus.mlc.lfregrafiscal.dac.ILfregrafiscalDAC;
import com.sensus.mlc.lfregrafiscal.model.Lfregrafiscal;
import com.sensus.mlc.lfregrafiscal.model.LfregrafiscalOrderByEnum;
import com.sensus.mlc.lfregrafiscal.model.request.InquiryLfregrafiscalRequest;
import com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest;

/** 
 * The Class LfregrafiscalDACImpl.
 */ 
public class LfregrafiscalDACImpl extends SqlSessionDaoSupport implements ILfregrafiscalDAC
{ 

	/** The Constant LFREGRAFISCAL_NAMESPACE. */ 
	private static final String LFREGRAFISCAL_NAMESPACE = "Lfregrafiscal.";

	/** The Constant FETCH_LFREGRAFISCAL_BY_ID. */ 
	private static final String FETCH_LFREGRAFISCAL_BY_ID = LFREGRAFISCAL_NAMESPACE + "fetchLfregrafiscalById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFREGRAFISCAL_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFREGRAFISCAL. */ 
	private static final String DELETE_SMART_POINT_FROM_LFREGRAFISCAL = LFREGRAFISCAL_NAMESPACE + "deleteSmartPointFromLfregrafiscal";

	/** The Constant FETCH_LFREGRAFISCAL_NAME_BY_ID. */ 
	private static final String FETCH_LFREGRAFISCAL_NAME_BY_ID = LFREGRAFISCAL_NAMESPACE + "fetchLfregrafiscalNameById";

	/** The Constant UPDATE_AUTO_LFREGRAFISCAL_LFREGRAFISCAL. */ 
	private static final String UPDATE_AUTO_LFREGRAFISCAL_LFREGRAFISCAL = LFREGRAFISCAL_NAMESPACE + "updateAutoLfregrafiscalLfregrafiscal";

	/** The Constant DELETE_LFREGRAFISCAL. */ 
	private static final String DELETE_LFREGRAFISCAL = LFREGRAFISCAL_NAMESPACE + "deleteLfregrafiscal";

	/** The Constant FETCH_LFREGRAFISCALS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFREGRAFISCALS_BY_LIGHT_ID = LFREGRAFISCAL_NAMESPACE + "fetchLfregrafiscalsByLightId";

	/** The Constant FETCH_LFREGRAFISCALS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFREGRAFISCALS_BY_SMART_POINT_ID = LFREGRAFISCAL_NAMESPACE + "fetchLfregrafiscalsBySmartPointId";

	/** The Constant INSERT_LFREGRAFISCAL. */ 
	private static final String INSERT_LFREGRAFISCAL = LFREGRAFISCAL_NAMESPACE + "insertLfregrafiscal";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFREGRAFISCAL = LFREGRAFISCAL_NAMESPACE + "updateLfregrafiscal";
private static final String SENSUS_MLC_LFREGRAFISCAL_VALIDATOR_LFREGRAFISCAL_ALREADY_EXISTS = error.update.lfregrafiscal;
 
	/** The Constant INSERT_SMART_POINT_TO_LFREGRAFISCAL. */ 
	private static final String INSERT_SMART_POINT_TO_LFREGRAFISCAL = LFREGRAFISCAL_NAMESPACE + "insertSmartPointToLfregrafiscal";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFREGRAFISCAL = LFREGRAFISCAL_NAMESPACE + "fetchLightsBelongLfregrafiscal";

	/** The Constant FETCH_ALL_LFREGRAFISCALS. */ 
	private static final String FETCH_ALL_LFREGRAFISCALS = LFREGRAFISCAL_NAMESPACE + "fetchAllLfregrafiscals";

	/** The Constant LFREGRAFISCALID. */ 
	private static final String LFREGRAFISCAL_ID = "lfregrafiscalId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFREGRAFISCALNAME. */ 
	private static final String LFREGRAFISCALNAME = "lfregrafiscalname";

	/** The Constant AUTOLFREGRAFISCAL. */ 
	private static final String AUTOLFREGRAFISCAL = "autolfregrafiscal";

	/** The Constant AUTOLFREGRAFISCAL. */ 
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
	 * @see com.sensus.mlc.lfregrafiscal.dao.ILfregrafiscalDAO#fetchAllLfregrafiscals(com.sensus.mlc.lfregrafiscal.model.request.InquiryLfregrafiscalRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfregrafiscal> fetchAllLfregrafiscal(InquiryLfregrafiscalRequest inquiryLfregrafiscalRequest)
	{
		InternalResultsResponse<Lfregrafiscal> response = new InternalResultsResponse<Lfregrafiscal>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfregrafiscalRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfregrafiscalRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfregrafiscalRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfregrafiscalRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfregrafiscalRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfregrafiscalRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfregrafiscalRequest.getLfregrafiscal()))
		{
			paramMap.put(LFREGRAFISCAL_ID, inquiryLfregrafiscalRequest.getLfregrafiscal().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFREGRAFISCALS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfregrafiscal.dao.ILfregrafiscalDAO#insertLfregrafiscal(com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfregrafiscal> insertLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfregrafiscal lfregrafiscal = lfregrafiscalRequest.getLfregrafiscal();

		lfregrafiscal.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFREGRAFISCAL, lfregrafiscalRequest));
   lfregrafiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfregrafiscalRequest));
		InternalResultsResponse<Lfregrafiscal> response = new InternalResultsResponse<Lfregrafiscal>();
		response.addResult(lfregrafiscal);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfregrafiscal.dao.ILfregrafiscalDAO#deleteLfregrafiscal(com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest)
	 */ 
	@Override
	public InternalResponse deleteLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFREGRAFISCAL, lfregrafiscalRequest.getLfregrafiscal(), response);
   lfregrafiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfregrafiscalRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfregrafiscal.dac.ILfregrafiscalDAC#updateLfregrafiscal(com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfregrafiscal> updateLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest)
	{
		InternalResultsResponse<Lfregrafiscal> response = new InternalResultsResponse<Lfregrafiscal>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFREGRAFISCAL, lfregrafiscalRequest);
   lfregrafiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfregrafiscalRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFREGRAFISCAL_VALIDATOR_LFREGRAFISCAL_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


