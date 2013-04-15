package com.sensus.mlc.gestao.dac.mybatis;

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
import com.sensus.mlc.cnae.dac.ICnaeDAC;
import com.sensus.mlc.cnae.model.Cnae;
import com.sensus.mlc.cnae.model.CnaeOrderByEnum;
import com.sensus.mlc.cnae.model.request.InquiryCnaeRequest;
import com.sensus.mlc.cnae.model.request.CnaeRequest;

/** 
 * The Class CnaeDACImpl.
 */ 
public class CnaeDACImpl extends SqlSessionDaoSupport implements ICnaeDAC
{ 

	/** The Constant CNAE_NAMESPACE. */ 
	private static final String CNAE_NAMESPACE = "Cnae.";

	/** The Constant FETCH_CNAE_BY_ID. */ 
	private static final String FETCH_CNAE_BY_ID = CNAE_NAMESPACE + "fetchCnaeById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = CNAE_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_CNAE. */ 
	private static final String DELETE_SMART_POINT_FROM_CNAE = CNAE_NAMESPACE + "deleteSmartPointFromCnae";

	/** The Constant FETCH_CNAE_BY_ID. */ 
	private static final String FETCH_CNAE_BY_ID = CNAE_NAMESPACE + "fetchCnaeById";

	/** The Constant FETCH_CNAE_NAME_BY_ID. */ 
	private static final String FETCH_CNAE_NAME_BY_ID = CNAE_NAMESPACE + "fetchCnaeNameById";

	/** The Constant UPDATE_AUTO_CNAE_CNAE. */ 
	private static final String UPDATE_AUTO_CNAE_CNAE = CNAE_NAMESPACE + "updateAutoCnaeCnae";

	/** The Constant DELETE_CNAE. */ 
	private static final String DELETE_CNAE = CNAE_NAMESPACE + "deleteCnae";

	/** The Constant FETCH_CNAES_BY_LIGHT_ID. */ 
	private static final String FETCH_CNAES_BY_LIGHT_ID = CNAE_NAMESPACE + "fetchCnaesByLightId";

	/** The Constant FETCH_CNAES_BY_SMART_POINT_ID. */ 
	private static final String FETCH_CNAES_BY_SMART_POINT_ID = CNAE_NAMESPACE + "fetchCnaesBySmartPointId";

	/** The Constant INSERT_CNAE. */ 
	private static final String INSERT_CNAE = CNAE_NAMESPACE + "insertCnae";

	/** The Constant INSERT_SMART_POINT_TO_CNAE. */ 
	private static final String INSERT_SMART_POINT_TO_CNAE = CNAE_NAMESPACE + "insertSmartPointToCnae";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_CNAE = CNAE_NAMESPACE + "fetchLightsBelongCnae";

	/** The Constant FETCH_ALL_CNAES. */ 
	private static final String FETCH_ALL_CNAES = CNAE_NAMESPACE + "fetchAllCnaes";

	/** The Constant CNAEID. */ 
	private static final String CNAE_ID = "cnaeId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant CNAENAME. */ 
	private static final String CNAENAME = "cnaename";

	/** The Constant AUTOCNAE. */ 
	private static final String AUTOCNAE = "autocnae";

	/** The Constant AUTOCNAE. */ 
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
	 * @see com.sensus.mlc.cnae.dao.ICnaeDAO#fetchAllCnaes(com.sensus.mlc.cnae.model.request.InquiryCnaeRequest)
	 */ 
	@Override
	public InternalResultsResponse<Cnae> fetchAllCnae(InquiryCnaeRequest inquiryCnaeRequest)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryCnaeRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryCnaeRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryCnaeRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryCnaeRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryCnaeRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryCnaeRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryCnaeRequest.getCnaes()))
		{
			paramMap.put(CNAE_ID, inquiryCnaeRequest.getCnaes().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_CNAES, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.cnae.dao.ICnaeDAO#fetchCnaeById(com.sensus.mlc.cnae.model.Cnae
	 */ 
	@Override
	public InternalResultsResponse<Cnae> fetchCnaeByName(CnaeRequest cnaeRequest)
	{ 
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		doQueryForList(getSqlSession(), FETCH_CNAE_BY_ID, cnaeRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.cnae.dao.ICnaeDAO#insertCnae(com.sensus.mlc.cnae.model.request.CnaeRequest)
	 */ 
	@Override
	public InternalResultsResponse<Cnae> insertCnae(CnaeRequest cnaeRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Cnae cnae = cnaeRequest.getCnae();

		cnae.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_CNAE, cnaeRequest));
   cnae.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, cnaeRequest));
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		response.addResult(cnae);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.cnae.dao.ICnaeDAO#deleteCnae(com.sensus.mlc.cnae.model.request.CnaeRequest)
	 */ 
	@Override
	public InternalResponse deleteCnae(CnaeRequest cnaeRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_CNAE, cnaeRequest.getCnae(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.cnae.dao.ICnaeDAO#fetchCnaeById(com.sensus.mlc.cnae.model.request.CnaeRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Cnae> fetchCnaeById(CnaeRequest cnaeRequest) 
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		doQueryForList(getSqlSession(), FETCH_CNAE_BY_ID, cnaeRequest.getCnae(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.cnae.dac.ICnaeDAC#fetchCnaeNameById(com.sensus.mlc.cnae.model.request.CnaeRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Cnae> fetchCnaeNameById(CnaeRequest cnaeRequest)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		doQueryForList(getSqlSession(), FETCH_CNAE_NAME_BY_ID, cnaeRequest.getCnae(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.cnae.dac.ICnaeDAC#updateCnae(com.sensus.mlc.cnae.model.request.CnaeRequest)
	 */ 
	@Override 
	public InternalResponse updateCnae(CnaeRequest cnaeRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_CNAE, cnaeRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_CNAEVALIDATOR_CNAE_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


