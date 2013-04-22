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
import com.sensus.mlc.lfitclfiscal.dac.ILfitclfiscalDAC;
import com.sensus.mlc.lfitclfiscal.model.Lfitclfiscal;
import com.sensus.mlc.lfitclfiscal.model.LfitclfiscalOrderByEnum;
import com.sensus.mlc.lfitclfiscal.model.request.InquiryLfitclfiscalRequest;
import com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest;

/** 
 * The Class LfitclfiscalDACImpl.
 */ 
public class LfitclfiscalDACImpl extends SqlSessionDaoSupport implements ILfitclfiscalDAC
{ 

	/** The Constant LFITCLFISCAL_NAMESPACE. */ 
	private static final String LFITCLFISCAL_NAMESPACE = "Lfitclfiscal.";

	/** The Constant FETCH_LFITCLFISCAL_BY_ID. */ 
	private static final String FETCH_LFITCLFISCAL_BY_ID = LFITCLFISCAL_NAMESPACE + "fetchLfitclfiscalById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFITCLFISCAL_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFITCLFISCAL. */ 
	private static final String DELETE_SMART_POINT_FROM_LFITCLFISCAL = LFITCLFISCAL_NAMESPACE + "deleteSmartPointFromLfitclfiscal";

	/** The Constant FETCH_LFITCLFISCAL_NAME_BY_ID. */ 
	private static final String FETCH_LFITCLFISCAL_NAME_BY_ID = LFITCLFISCAL_NAMESPACE + "fetchLfitclfiscalNameById";

	/** The Constant UPDATE_AUTO_LFITCLFISCAL_LFITCLFISCAL. */ 
	private static final String UPDATE_AUTO_LFITCLFISCAL_LFITCLFISCAL = LFITCLFISCAL_NAMESPACE + "updateAutoLfitclfiscalLfitclfiscal";

	/** The Constant DELETE_LFITCLFISCAL. */ 
	private static final String DELETE_LFITCLFISCAL = LFITCLFISCAL_NAMESPACE + "deleteLfitclfiscal";

	/** The Constant FETCH_LFITCLFISCALS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFITCLFISCALS_BY_LIGHT_ID = LFITCLFISCAL_NAMESPACE + "fetchLfitclfiscalsByLightId";

	/** The Constant FETCH_LFITCLFISCALS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFITCLFISCALS_BY_SMART_POINT_ID = LFITCLFISCAL_NAMESPACE + "fetchLfitclfiscalsBySmartPointId";

	/** The Constant INSERT_LFITCLFISCAL. */ 
	private static final String INSERT_LFITCLFISCAL = LFITCLFISCAL_NAMESPACE + "insertLfitclfiscal";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFITCLFISCAL = LFITCLFISCAL_NAMESPACE + "updateLfitclfiscal";
private static final String SENSUS_MLC_LFITCLFISCAL_VALIDATOR_LFITCLFISCAL_ALREADY_EXISTS = error.update.lfitclfiscal;
 
	/** The Constant INSERT_SMART_POINT_TO_LFITCLFISCAL. */ 
	private static final String INSERT_SMART_POINT_TO_LFITCLFISCAL = LFITCLFISCAL_NAMESPACE + "insertSmartPointToLfitclfiscal";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFITCLFISCAL = LFITCLFISCAL_NAMESPACE + "fetchLightsBelongLfitclfiscal";

	/** The Constant FETCH_ALL_LFITCLFISCALS. */ 
	private static final String FETCH_ALL_LFITCLFISCALS = LFITCLFISCAL_NAMESPACE + "fetchAllLfitclfiscals";

	/** The Constant LFITCLFISCALID. */ 
	private static final String LFITCLFISCAL_ID = "lfitclfiscalId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFITCLFISCALNAME. */ 
	private static final String LFITCLFISCALNAME = "lfitclfiscalname";

	/** The Constant AUTOLFITCLFISCAL. */ 
	private static final String AUTOLFITCLFISCAL = "autolfitclfiscal";

	/** The Constant AUTOLFITCLFISCAL. */ 
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
	 * @see com.sensus.mlc.lfitclfiscal.dao.ILfitclfiscalDAO#fetchAllLfitclfiscals(com.sensus.mlc.lfitclfiscal.model.request.InquiryLfitclfiscalRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfitclfiscal> fetchAllLfitclfiscal(InquiryLfitclfiscalRequest inquiryLfitclfiscalRequest)
	{
		InternalResultsResponse<Lfitclfiscal> response = new InternalResultsResponse<Lfitclfiscal>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfitclfiscalRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfitclfiscalRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfitclfiscalRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfitclfiscalRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfitclfiscalRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfitclfiscalRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfitclfiscalRequest.getLfitclfiscal()))
		{
			paramMap.put(LFITCLFISCAL_ID, inquiryLfitclfiscalRequest.getLfitclfiscal().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFITCLFISCALS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfitclfiscal.dao.ILfitclfiscalDAO#insertLfitclfiscal(com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfitclfiscal> insertLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfitclfiscal lfitclfiscal = lfitclfiscalRequest.getLfitclfiscal();

		lfitclfiscal.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFITCLFISCAL, lfitclfiscalRequest));
   lfitclfiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfitclfiscalRequest));
		InternalResultsResponse<Lfitclfiscal> response = new InternalResultsResponse<Lfitclfiscal>();
		response.addResult(lfitclfiscal);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitclfiscal.dao.ILfitclfiscalDAO#deleteLfitclfiscal(com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest)
	 */ 
	@Override
	public InternalResponse deleteLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFITCLFISCAL, lfitclfiscalRequest.getLfitclfiscal(), response);
   lfitclfiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfitclfiscalRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfitclfiscal.dac.ILfitclfiscalDAC#updateLfitclfiscal(com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfitclfiscal> updateLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest)
	{
		InternalResultsResponse<Lfitclfiscal> response = new InternalResultsResponse<Lfitclfiscal>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFITCLFISCAL, lfitclfiscalRequest);
   lfitclfiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfitclfiscalRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFITCLFISCAL_VALIDATOR_LFITCLFISCAL_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


