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
import com.sensus.mlc.lflivrofiscal.dac.ILflivrofiscalDAC;
import com.sensus.mlc.lflivrofiscal.model.Lflivrofiscal;
import com.sensus.mlc.lflivrofiscal.model.LflivrofiscalOrderByEnum;
import com.sensus.mlc.lflivrofiscal.model.request.InquiryLflivrofiscalRequest;
import com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest;

/** 
 * The Class LflivrofiscalDACImpl.
 */ 
public class LflivrofiscalDACImpl extends SqlSessionDaoSupport implements ILflivrofiscalDAC
{ 

	/** The Constant LFLIVROFISCAL_NAMESPACE. */ 
	private static final String LFLIVROFISCAL_NAMESPACE = "Lflivrofiscal.";

	/** The Constant FETCH_LFLIVROFISCAL_BY_ID. */ 
	private static final String FETCH_LFLIVROFISCAL_BY_ID = LFLIVROFISCAL_NAMESPACE + "fetchLflivrofiscalById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFLIVROFISCAL_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFLIVROFISCAL. */ 
	private static final String DELETE_SMART_POINT_FROM_LFLIVROFISCAL = LFLIVROFISCAL_NAMESPACE + "deleteSmartPointFromLflivrofiscal";

	/** The Constant FETCH_LFLIVROFISCAL_NAME_BY_ID. */ 
	private static final String FETCH_LFLIVROFISCAL_NAME_BY_ID = LFLIVROFISCAL_NAMESPACE + "fetchLflivrofiscalNameById";

	/** The Constant UPDATE_AUTO_LFLIVROFISCAL_LFLIVROFISCAL. */ 
	private static final String UPDATE_AUTO_LFLIVROFISCAL_LFLIVROFISCAL = LFLIVROFISCAL_NAMESPACE + "updateAutoLflivrofiscalLflivrofiscal";

	/** The Constant DELETE_LFLIVROFISCAL. */ 
	private static final String DELETE_LFLIVROFISCAL = LFLIVROFISCAL_NAMESPACE + "deleteLflivrofiscal";

	/** The Constant FETCH_LFLIVROFISCALS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFLIVROFISCALS_BY_LIGHT_ID = LFLIVROFISCAL_NAMESPACE + "fetchLflivrofiscalsByLightId";

	/** The Constant FETCH_LFLIVROFISCALS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFLIVROFISCALS_BY_SMART_POINT_ID = LFLIVROFISCAL_NAMESPACE + "fetchLflivrofiscalsBySmartPointId";

	/** The Constant INSERT_LFLIVROFISCAL. */ 
	private static final String INSERT_LFLIVROFISCAL = LFLIVROFISCAL_NAMESPACE + "insertLflivrofiscal";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFLIVROFISCAL = LFLIVROFISCAL_NAMESPACE + "updateLflivrofiscal";
private static final String SENSUS_MLC_LFLIVROFISCAL_VALIDATOR_LFLIVROFISCAL_ALREADY_EXISTS = error.update.lflivrofiscal;
 
	/** The Constant INSERT_SMART_POINT_TO_LFLIVROFISCAL. */ 
	private static final String INSERT_SMART_POINT_TO_LFLIVROFISCAL = LFLIVROFISCAL_NAMESPACE + "insertSmartPointToLflivrofiscal";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFLIVROFISCAL = LFLIVROFISCAL_NAMESPACE + "fetchLightsBelongLflivrofiscal";

	/** The Constant FETCH_ALL_LFLIVROFISCALS. */ 
	private static final String FETCH_ALL_LFLIVROFISCALS = LFLIVROFISCAL_NAMESPACE + "fetchAllLflivrofiscals";

	/** The Constant LFLIVROFISCALID. */ 
	private static final String LFLIVROFISCAL_ID = "lflivrofiscalId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFLIVROFISCALNAME. */ 
	private static final String LFLIVROFISCALNAME = "lflivrofiscalname";

	/** The Constant AUTOLFLIVROFISCAL. */ 
	private static final String AUTOLFLIVROFISCAL = "autolflivrofiscal";

	/** The Constant AUTOLFLIVROFISCAL. */ 
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
	 * @see com.sensus.mlc.lflivrofiscal.dao.ILflivrofiscalDAO#fetchAllLflivrofiscals(com.sensus.mlc.lflivrofiscal.model.request.InquiryLflivrofiscalRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lflivrofiscal> fetchAllLflivrofiscal(InquiryLflivrofiscalRequest inquiryLflivrofiscalRequest)
	{
		InternalResultsResponse<Lflivrofiscal> response = new InternalResultsResponse<Lflivrofiscal>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLflivrofiscalRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLflivrofiscalRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLflivrofiscalRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLflivrofiscalRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLflivrofiscalRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLflivrofiscalRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLflivrofiscalRequest.getLflivrofiscal()))
		{
			paramMap.put(LFLIVROFISCAL_ID, inquiryLflivrofiscalRequest.getLflivrofiscal().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFLIVROFISCALS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lflivrofiscal.dao.ILflivrofiscalDAO#insertLflivrofiscal(com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lflivrofiscal> insertLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lflivrofiscal lflivrofiscal = lflivrofiscalRequest.getLflivrofiscal();

		lflivrofiscal.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFLIVROFISCAL, lflivrofiscalRequest));
   lflivrofiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lflivrofiscalRequest));
		InternalResultsResponse<Lflivrofiscal> response = new InternalResultsResponse<Lflivrofiscal>();
		response.addResult(lflivrofiscal);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lflivrofiscal.dao.ILflivrofiscalDAO#deleteLflivrofiscal(com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest)
	 */ 
	@Override
	public InternalResponse deleteLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFLIVROFISCAL, lflivrofiscalRequest.getLflivrofiscal(), response);
   lflivrofiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lflivrofiscalRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lflivrofiscal.dac.ILflivrofiscalDAC#updateLflivrofiscal(com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lflivrofiscal> updateLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest)
	{
		InternalResultsResponse<Lflivrofiscal> response = new InternalResultsResponse<Lflivrofiscal>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFLIVROFISCAL, lflivrofiscalRequest);
   lflivrofiscal.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lflivrofiscalRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFLIVROFISCAL_VALIDATOR_LFLIVROFISCAL_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


