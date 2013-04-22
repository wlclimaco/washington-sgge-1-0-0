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
import com.sensus.mlc.lfitcompra.dac.ILfitcompraDAC;
import com.sensus.mlc.lfitcompra.model.Lfitcompra;
import com.sensus.mlc.lfitcompra.model.LfitcompraOrderByEnum;
import com.sensus.mlc.lfitcompra.model.request.InquiryLfitcompraRequest;
import com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest;

/** 
 * The Class LfitcompraDACImpl.
 */ 
public class LfitcompraDACImpl extends SqlSessionDaoSupport implements ILfitcompraDAC
{ 

	/** The Constant LFITCOMPRA_NAMESPACE. */ 
	private static final String LFITCOMPRA_NAMESPACE = "Lfitcompra.";

	/** The Constant FETCH_LFITCOMPRA_BY_ID. */ 
	private static final String FETCH_LFITCOMPRA_BY_ID = LFITCOMPRA_NAMESPACE + "fetchLfitcompraById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFITCOMPRA_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFITCOMPRA. */ 
	private static final String DELETE_SMART_POINT_FROM_LFITCOMPRA = LFITCOMPRA_NAMESPACE + "deleteSmartPointFromLfitcompra";

	/** The Constant FETCH_LFITCOMPRA_NAME_BY_ID. */ 
	private static final String FETCH_LFITCOMPRA_NAME_BY_ID = LFITCOMPRA_NAMESPACE + "fetchLfitcompraNameById";

	/** The Constant UPDATE_AUTO_LFITCOMPRA_LFITCOMPRA. */ 
	private static final String UPDATE_AUTO_LFITCOMPRA_LFITCOMPRA = LFITCOMPRA_NAMESPACE + "updateAutoLfitcompraLfitcompra";

	/** The Constant DELETE_LFITCOMPRA. */ 
	private static final String DELETE_LFITCOMPRA = LFITCOMPRA_NAMESPACE + "deleteLfitcompra";

	/** The Constant FETCH_LFITCOMPRAS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFITCOMPRAS_BY_LIGHT_ID = LFITCOMPRA_NAMESPACE + "fetchLfitcomprasByLightId";

	/** The Constant FETCH_LFITCOMPRAS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFITCOMPRAS_BY_SMART_POINT_ID = LFITCOMPRA_NAMESPACE + "fetchLfitcomprasBySmartPointId";

	/** The Constant INSERT_LFITCOMPRA. */ 
	private static final String INSERT_LFITCOMPRA = LFITCOMPRA_NAMESPACE + "insertLfitcompra";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFITCOMPRA = LFITCOMPRA_NAMESPACE + "updateLfitcompra";
private static final String SENSUS_MLC_LFITCOMPRA_VALIDATOR_LFITCOMPRA_ALREADY_EXISTS = error.update.lfitcompra;
 
	/** The Constant INSERT_SMART_POINT_TO_LFITCOMPRA. */ 
	private static final String INSERT_SMART_POINT_TO_LFITCOMPRA = LFITCOMPRA_NAMESPACE + "insertSmartPointToLfitcompra";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFITCOMPRA = LFITCOMPRA_NAMESPACE + "fetchLightsBelongLfitcompra";

	/** The Constant FETCH_ALL_LFITCOMPRAS. */ 
	private static final String FETCH_ALL_LFITCOMPRAS = LFITCOMPRA_NAMESPACE + "fetchAllLfitcompras";

	/** The Constant LFITCOMPRAID. */ 
	private static final String LFITCOMPRA_ID = "lfitcompraId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFITCOMPRANAME. */ 
	private static final String LFITCOMPRANAME = "lfitcompraname";

	/** The Constant AUTOLFITCOMPRA. */ 
	private static final String AUTOLFITCOMPRA = "autolfitcompra";

	/** The Constant AUTOLFITCOMPRA. */ 
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
	 * @see com.sensus.mlc.lfitcompra.dao.ILfitcompraDAO#fetchAllLfitcompras(com.sensus.mlc.lfitcompra.model.request.InquiryLfitcompraRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfitcompra> fetchAllLfitcompra(InquiryLfitcompraRequest inquiryLfitcompraRequest)
	{
		InternalResultsResponse<Lfitcompra> response = new InternalResultsResponse<Lfitcompra>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfitcompraRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfitcompraRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfitcompraRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfitcompraRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfitcompraRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfitcompraRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfitcompraRequest.getLfitcompra()))
		{
			paramMap.put(LFITCOMPRA_ID, inquiryLfitcompraRequest.getLfitcompra().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFITCOMPRAS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfitcompra.dao.ILfitcompraDAO#insertLfitcompra(com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfitcompra> insertLfitcompra(LfitcompraRequest lfitcompraRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfitcompra lfitcompra = lfitcompraRequest.getLfitcompra();

		lfitcompra.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFITCOMPRA, lfitcompraRequest));
   lfitcompra.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfitcompraRequest));
		InternalResultsResponse<Lfitcompra> response = new InternalResultsResponse<Lfitcompra>();
		response.addResult(lfitcompra);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitcompra.dao.ILfitcompraDAO#deleteLfitcompra(com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest)
	 */ 
	@Override
	public InternalResponse deleteLfitcompra(LfitcompraRequest lfitcompraRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFITCOMPRA, lfitcompraRequest.getLfitcompra(), response);
   lfitcompra.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfitcompraRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfitcompra.dac.ILfitcompraDAC#updateLfitcompra(com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfitcompra> updateLfitcompra(LfitcompraRequest lfitcompraRequest)
	{
		InternalResultsResponse<Lfitcompra> response = new InternalResultsResponse<Lfitcompra>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFITCOMPRA, lfitcompraRequest);
   lfitcompra.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfitcompraRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFITCOMPRA_VALIDATOR_LFITCOMPRA_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


