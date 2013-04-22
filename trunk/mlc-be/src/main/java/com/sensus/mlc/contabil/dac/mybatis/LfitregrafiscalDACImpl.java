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
import com.sensus.mlc.lfitregrafiscal.dac.ILfitregrafiscalDAC;
import com.sensus.mlc.lfitregrafiscal.model.Lfitregrafiscal;
import com.sensus.mlc.lfitregrafiscal.model.LfitregrafiscalOrderByEnum;
import com.sensus.mlc.lfitregrafiscal.model.request.InquiryLfitregrafiscalRequest;
import com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest;

/** 
 * The Class LfitregrafiscalDACImpl.
 */ 
public class LfitregrafiscalDACImpl extends SqlSessionDaoSupport implements ILfitregrafiscalDAC
{ 

	/** The Constant LFITREGRAFISCAL_NAMESPACE. */ 
	private static final String LFITREGRAFISCAL_NAMESPACE = "Lfitregrafiscal.";

	/** The Constant FETCH_LFITREGRAFISCAL_BY_ID. */ 
	private static final String FETCH_LFITREGRAFISCAL_BY_ID = LFITREGRAFISCAL_NAMESPACE + "fetchLfitregrafiscalById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFITREGRAFISCAL_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFITREGRAFISCAL. */ 
	private static final String DELETE_SMART_POINT_FROM_LFITREGRAFISCAL = LFITREGRAFISCAL_NAMESPACE + "deleteSmartPointFromLfitregrafiscal";

	/** The Constant FETCH_LFITREGRAFISCAL_NAME_BY_ID. */ 
	private static final String FETCH_LFITREGRAFISCAL_NAME_BY_ID = LFITREGRAFISCAL_NAMESPACE + "fetchLfitregrafiscalNameById";

	/** The Constant UPDATE_AUTO_LFITREGRAFISCAL_LFITREGRAFISCAL. */ 
	private static final String UPDATE_AUTO_LFITREGRAFISCAL_LFITREGRAFISCAL = LFITREGRAFISCAL_NAMESPACE + "updateAutoLfitregrafiscalLfitregrafiscal";

	/** The Constant DELETE_LFITREGRAFISCAL. */ 
	private static final String DELETE_LFITREGRAFISCAL = LFITREGRAFISCAL_NAMESPACE + "deleteLfitregrafiscal";

	/** The Constant FETCH_LFITREGRAFISCALS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFITREGRAFISCALS_BY_LIGHT_ID = LFITREGRAFISCAL_NAMESPACE + "fetchLfitregrafiscalsByLightId";

	/** The Constant FETCH_LFITREGRAFISCALS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFITREGRAFISCALS_BY_SMART_POINT_ID = LFITREGRAFISCAL_NAMESPACE + "fetchLfitregrafiscalsBySmartPointId";

	/** The Constant INSERT_LFITREGRAFISCAL. */ 
	private static final String INSERT_LFITREGRAFISCAL = LFITREGRAFISCAL_NAMESPACE + "insertLfitregrafiscal";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFITREGRAFISCAL = LFITREGRAFISCAL_NAMESPACE + "updateLfitregrafiscal";
private static final String SENSUS_MLC_LFITREGRAFISCAL_VALIDATOR_LFITREGRAFISCAL_ALREADY_EXISTS = error.update.lfitregrafiscal;
 
	/** The Constant INSERT_SMART_POINT_TO_LFITREGRAFISCAL. */ 
	private static final String INSERT_SMART_POINT_TO_LFITREGRAFISCAL = LFITREGRAFISCAL_NAMESPACE + "insertSmartPointToLfitregrafiscal";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFITREGRAFISCAL = LFITREGRAFISCAL_NAMESPACE + "fetchLightsBelongLfitregrafiscal";

	/** The Constant FETCH_ALL_LFITREGRAFISCALS. */ 
	private static final String FETCH_ALL_LFITREGRAFISCALS = LFITREGRAFISCAL_NAMESPACE + "fetchAllLfitregrafiscals";

	/** The Constant LFITREGRAFISCALID. */ 
	private static final String LFITREGRAFISCAL_ID = "lfitregrafiscalId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFITREGRAFISCALNAME. */ 
	private static final String LFITREGRAFISCALNAME = "lfitregrafiscalname";

	/** The Constant AUTOLFITREGRAFISCAL. */ 
	private static final String AUTOLFITREGRAFISCAL = "autolfitregrafiscal";

	/** The Constant AUTOLFITREGRAFISCAL. */ 
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
	 * @see com.sensus.mlc.lfitregrafiscal.dao.ILfitregrafiscalDAO#fetchAllLfitregrafiscals(com.sensus.mlc.lfitregrafiscal.model.request.InquiryLfitregrafiscalRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfitregrafiscal> fetchAllLfitregrafiscal(InquiryLfitregrafiscalRequest inquiryLfitregrafiscalRequest)
	{
		InternalResultsResponse<Lfitregrafiscal> response = new InternalResultsResponse<Lfitregrafiscal>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfitregrafiscalRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfitregrafiscalRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfitregrafiscalRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfitregrafiscalRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfitregrafiscalRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfitregrafiscalRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfitregrafiscalRequest.getLfitregrafiscal()))
		{
			paramMap.put(LFITREGRAFISCAL_ID, inquiryLfitregrafiscalRequest.getLfitregrafiscal().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFITREGRAFISCALS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfitregrafiscal.dao.ILfitregrafiscalDAO#insertLfitregrafiscal(com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfitregrafiscal> insertLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfitregrafiscal lfitregrafiscal = lfitregrafiscalRequest.getLfitregrafiscal();

		lfitregrafiscal.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFITREGRAFISCAL, lfitregrafiscalRequest));
   lfitregrafiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfitregrafiscalRequest));
		InternalResultsResponse<Lfitregrafiscal> response = new InternalResultsResponse<Lfitregrafiscal>();
		response.addResult(lfitregrafiscal);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitregrafiscal.dao.ILfitregrafiscalDAO#deleteLfitregrafiscal(com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest)
	 */ 
	@Override
	public InternalResponse deleteLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFITREGRAFISCAL, lfitregrafiscalRequest.getLfitregrafiscal(), response);
   lfitregrafiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfitregrafiscalRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfitregrafiscal.dac.ILfitregrafiscalDAC#updateLfitregrafiscal(com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfitregrafiscal> updateLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest)
	{
		InternalResultsResponse<Lfitregrafiscal> response = new InternalResultsResponse<Lfitregrafiscal>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFITREGRAFISCAL, lfitregrafiscalRequest);
   lfitregrafiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfitregrafiscalRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFITREGRAFISCAL_VALIDATOR_LFITREGRAFISCAL_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


