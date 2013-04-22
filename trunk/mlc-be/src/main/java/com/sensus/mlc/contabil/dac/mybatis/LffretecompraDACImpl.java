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
import com.sensus.mlc.lffretecompra.dac.ILffretecompraDAC;
import com.sensus.mlc.lffretecompra.model.Lffretecompra;
import com.sensus.mlc.lffretecompra.model.LffretecompraOrderByEnum;
import com.sensus.mlc.lffretecompra.model.request.InquiryLffretecompraRequest;
import com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest;

/** 
 * The Class LffretecompraDACImpl.
 */ 
public class LffretecompraDACImpl extends SqlSessionDaoSupport implements ILffretecompraDAC
{ 

	/** The Constant LFFRETECOMPRA_NAMESPACE. */ 
	private static final String LFFRETECOMPRA_NAMESPACE = "Lffretecompra.";

	/** The Constant FETCH_LFFRETECOMPRA_BY_ID. */ 
	private static final String FETCH_LFFRETECOMPRA_BY_ID = LFFRETECOMPRA_NAMESPACE + "fetchLffretecompraById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFFRETECOMPRA_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFFRETECOMPRA. */ 
	private static final String DELETE_SMART_POINT_FROM_LFFRETECOMPRA = LFFRETECOMPRA_NAMESPACE + "deleteSmartPointFromLffretecompra";

	/** The Constant FETCH_LFFRETECOMPRA_NAME_BY_ID. */ 
	private static final String FETCH_LFFRETECOMPRA_NAME_BY_ID = LFFRETECOMPRA_NAMESPACE + "fetchLffretecompraNameById";

	/** The Constant UPDATE_AUTO_LFFRETECOMPRA_LFFRETECOMPRA. */ 
	private static final String UPDATE_AUTO_LFFRETECOMPRA_LFFRETECOMPRA = LFFRETECOMPRA_NAMESPACE + "updateAutoLffretecompraLffretecompra";

	/** The Constant DELETE_LFFRETECOMPRA. */ 
	private static final String DELETE_LFFRETECOMPRA = LFFRETECOMPRA_NAMESPACE + "deleteLffretecompra";

	/** The Constant FETCH_LFFRETECOMPRAS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFFRETECOMPRAS_BY_LIGHT_ID = LFFRETECOMPRA_NAMESPACE + "fetchLffretecomprasByLightId";

	/** The Constant FETCH_LFFRETECOMPRAS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFFRETECOMPRAS_BY_SMART_POINT_ID = LFFRETECOMPRA_NAMESPACE + "fetchLffretecomprasBySmartPointId";

	/** The Constant INSERT_LFFRETECOMPRA. */ 
	private static final String INSERT_LFFRETECOMPRA = LFFRETECOMPRA_NAMESPACE + "insertLffretecompra";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFFRETECOMPRA = LFFRETECOMPRA_NAMESPACE + "updateLffretecompra";
private static final String SENSUS_MLC_LFFRETECOMPRA_VALIDATOR_LFFRETECOMPRA_ALREADY_EXISTS = error.update.lffretecompra;
 
	/** The Constant INSERT_SMART_POINT_TO_LFFRETECOMPRA. */ 
	private static final String INSERT_SMART_POINT_TO_LFFRETECOMPRA = LFFRETECOMPRA_NAMESPACE + "insertSmartPointToLffretecompra";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFFRETECOMPRA = LFFRETECOMPRA_NAMESPACE + "fetchLightsBelongLffretecompra";

	/** The Constant FETCH_ALL_LFFRETECOMPRAS. */ 
	private static final String FETCH_ALL_LFFRETECOMPRAS = LFFRETECOMPRA_NAMESPACE + "fetchAllLffretecompras";

	/** The Constant LFFRETECOMPRAID. */ 
	private static final String LFFRETECOMPRA_ID = "lffretecompraId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFFRETECOMPRANAME. */ 
	private static final String LFFRETECOMPRANAME = "lffretecompraname";

	/** The Constant AUTOLFFRETECOMPRA. */ 
	private static final String AUTOLFFRETECOMPRA = "autolffretecompra";

	/** The Constant AUTOLFFRETECOMPRA. */ 
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
	 * @see com.sensus.mlc.lffretecompra.dao.ILffretecompraDAO#fetchAllLffretecompras(com.sensus.mlc.lffretecompra.model.request.InquiryLffretecompraRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lffretecompra> fetchAllLffretecompra(InquiryLffretecompraRequest inquiryLffretecompraRequest)
	{
		InternalResultsResponse<Lffretecompra> response = new InternalResultsResponse<Lffretecompra>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLffretecompraRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLffretecompraRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLffretecompraRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLffretecompraRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLffretecompraRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLffretecompraRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLffretecompraRequest.getLffretecompra()))
		{
			paramMap.put(LFFRETECOMPRA_ID, inquiryLffretecompraRequest.getLffretecompra().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFFRETECOMPRAS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lffretecompra.dao.ILffretecompraDAO#insertLffretecompra(com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lffretecompra> insertLffretecompra(LffretecompraRequest lffretecompraRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lffretecompra lffretecompra = lffretecompraRequest.getLffretecompra();

		lffretecompra.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFFRETECOMPRA, lffretecompraRequest));
   lffretecompra.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lffretecompraRequest));
		InternalResultsResponse<Lffretecompra> response = new InternalResultsResponse<Lffretecompra>();
		response.addResult(lffretecompra);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffretecompra.dao.ILffretecompraDAO#deleteLffretecompra(com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest)
	 */ 
	@Override
	public InternalResponse deleteLffretecompra(LffretecompraRequest lffretecompraRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFFRETECOMPRA, lffretecompraRequest.getLffretecompra(), response);
   lffretecompra.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lffretecompraRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lffretecompra.dac.ILffretecompraDAC#updateLffretecompra(com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lffretecompra> updateLffretecompra(LffretecompraRequest lffretecompraRequest)
	{
		InternalResultsResponse<Lffretecompra> response = new InternalResultsResponse<Lffretecompra>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFFRETECOMPRA, lffretecompraRequest);
   lffretecompra.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lffretecompraRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFFRETECOMPRA_VALIDATOR_LFFRETECOMPRA_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


