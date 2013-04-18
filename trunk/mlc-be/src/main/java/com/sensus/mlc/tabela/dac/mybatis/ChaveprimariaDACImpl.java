package com.sensus.mlc.tabela.dac.mybatis;

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
import com.sensus.mlc.chaveprimaria.dac.IChaveprimariaDAC;
import com.sensus.mlc.chaveprimaria.model.Chaveprimaria;
import com.sensus.mlc.chaveprimaria.model.ChaveprimariaOrderByEnum;
import com.sensus.mlc.chaveprimaria.model.request.InquiryChaveprimariaRequest;
import com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest;

/** 
 * The Class ChaveprimariaDACImpl.
 */ 
public class ChaveprimariaDACImpl extends SqlSessionDaoSupport implements IChaveprimariaDAC
{ 

	/** The Constant CHAVEPRIMARIA_NAMESPACE. */ 
	private static final String CHAVEPRIMARIA_NAMESPACE = "Chaveprimaria.";

	/** The Constant FETCH_CHAVEPRIMARIA_BY_ID. */ 
	private static final String FETCH_CHAVEPRIMARIA_BY_ID = CHAVEPRIMARIA_NAMESPACE + "fetchChaveprimariaById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = CHAVEPRIMARIA_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_CHAVEPRIMARIA. */ 
	private static final String DELETE_SMART_POINT_FROM_CHAVEPRIMARIA = CHAVEPRIMARIA_NAMESPACE + "deleteSmartPointFromChaveprimaria";

	/** The Constant FETCH_CHAVEPRIMARIA_NAME_BY_ID. */ 
	private static final String FETCH_CHAVEPRIMARIA_NAME_BY_ID = CHAVEPRIMARIA_NAMESPACE + "fetchChaveprimariaNameById";

	/** The Constant UPDATE_AUTO_CHAVEPRIMARIA_CHAVEPRIMARIA. */ 
	private static final String UPDATE_AUTO_CHAVEPRIMARIA_CHAVEPRIMARIA = CHAVEPRIMARIA_NAMESPACE + "updateAutoChaveprimariaChaveprimaria";

	/** The Constant DELETE_CHAVEPRIMARIA. */ 
	private static final String DELETE_CHAVEPRIMARIA = CHAVEPRIMARIA_NAMESPACE + "deleteChaveprimaria";

	/** The Constant FETCH_CHAVEPRIMARIAS_BY_LIGHT_ID. */ 
	private static final String FETCH_CHAVEPRIMARIAS_BY_LIGHT_ID = CHAVEPRIMARIA_NAMESPACE + "fetchChaveprimariasByLightId";

	/** The Constant FETCH_CHAVEPRIMARIAS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_CHAVEPRIMARIAS_BY_SMART_POINT_ID = CHAVEPRIMARIA_NAMESPACE + "fetchChaveprimariasBySmartPointId";

	/** The Constant INSERT_CHAVEPRIMARIA. */ 
	private static final String INSERT_CHAVEPRIMARIA = CHAVEPRIMARIA_NAMESPACE + "insertChaveprimaria";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_CHAVEPRIMARIA = CHAVEPRIMARIA_NAMESPACE + "updateChaveprimaria";
private static final String SENSUS_MLC_CHAVEPRIMARIA_VALIDATOR_CHAVEPRIMARIA_ALREADY_EXISTS = error.update.chaveprimaria;
 
	/** The Constant INSERT_SMART_POINT_TO_CHAVEPRIMARIA. */ 
	private static final String INSERT_SMART_POINT_TO_CHAVEPRIMARIA = CHAVEPRIMARIA_NAMESPACE + "insertSmartPointToChaveprimaria";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_CHAVEPRIMARIA = CHAVEPRIMARIA_NAMESPACE + "fetchLightsBelongChaveprimaria";

	/** The Constant FETCH_ALL_CHAVEPRIMARIAS. */ 
	private static final String FETCH_ALL_CHAVEPRIMARIAS = CHAVEPRIMARIA_NAMESPACE + "fetchAllChaveprimarias";

	/** The Constant CHAVEPRIMARIAID. */ 
	private static final String CHAVEPRIMARIA_ID = "chaveprimariaId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant CHAVEPRIMARIANAME. */ 
	private static final String CHAVEPRIMARIANAME = "chaveprimarianame";

	/** The Constant AUTOCHAVEPRIMARIA. */ 
	private static final String AUTOCHAVEPRIMARIA = "autochaveprimaria";

	/** The Constant AUTOCHAVEPRIMARIA. */ 
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
	 * @see com.sensus.mlc.chaveprimaria.dao.IChaveprimariaDAO#fetchAllChaveprimarias(com.sensus.mlc.chaveprimaria.model.request.InquiryChaveprimariaRequest)
	 */ 
	@Override
	public InternalResultsResponse<Chaveprimaria> fetchAllChaveprimaria(InquiryChaveprimariaRequest inquiryChaveprimariaRequest)
	{
		InternalResultsResponse<Chaveprimaria> response = new InternalResultsResponse<Chaveprimaria>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryChaveprimariaRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryChaveprimariaRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryChaveprimariaRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryChaveprimariaRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryChaveprimariaRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryChaveprimariaRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryChaveprimariaRequest.getChaveprimaria()))
		{
			paramMap.put(CHAVEPRIMARIA_ID, inquiryChaveprimariaRequest.getChaveprimaria().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_CHAVEPRIMARIAS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.chaveprimaria.dao.IChaveprimariaDAO#insertChaveprimaria(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)
	 */ 
	@Override
	public InternalResultsResponse<Chaveprimaria> insertChaveprimaria(ChaveprimariaRequest chaveprimariaRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Chaveprimaria chaveprimaria = chaveprimariaRequest.getChaveprimaria();

		chaveprimaria.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_CHAVEPRIMARIA, chaveprimariaRequest));
   chaveprimaria.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, chaveprimariaRequest));
		InternalResultsResponse<Chaveprimaria> response = new InternalResultsResponse<Chaveprimaria>();
		response.addResult(chaveprimaria);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveprimaria.dao.IChaveprimariaDAO#deleteChaveprimaria(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)
	 */ 
	@Override
	public InternalResponse deleteChaveprimaria(ChaveprimariaRequest chaveprimariaRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_CHAVEPRIMARIA, chaveprimariaRequest.getChaveprimaria(), response);
   chaveprimaria.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , chaveprimariaRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.chaveprimaria.dac.IChaveprimariaDAC#updateChaveprimaria(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Chaveprimaria> updateChaveprimaria(ChaveprimariaRequest chaveprimariaRequest)
	{
		InternalResultsResponse<Chaveprimaria> response = new InternalResultsResponse<Chaveprimaria>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_CHAVEPRIMARIA, chaveprimariaRequest);
   chaveprimaria.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, chaveprimariaRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_CHAVEPRIMARIA_VALIDATOR_CHAVEPRIMARIA_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


