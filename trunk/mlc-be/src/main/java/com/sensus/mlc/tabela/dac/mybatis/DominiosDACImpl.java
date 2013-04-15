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
import com.sensus.mlc.dominios.dac.IDominiosDAC;
import com.sensus.mlc.dominios.model.Dominios;
import com.sensus.mlc.dominios.model.DominiosOrderByEnum;
import com.sensus.mlc.dominios.model.request.InquiryDominiosRequest;
import com.sensus.mlc.dominios.model.request.DominiosRequest;

/** 
 * The Class DominiosDACImpl.
 */ 
public class DominiosDACImpl extends SqlSessionDaoSupport implements IDominiosDAC
{ 

	/** The Constant DOMINIOS_NAMESPACE. */ 
	private static final String DOMINIOS_NAMESPACE = "Dominios.";

	/** The Constant FETCH_DOMINIOS_BY_ID. */ 
	private static final String FETCH_DOMINIOS_BY_ID = DOMINIOS_NAMESPACE + "fetchDominiosById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = DOMINIOS_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_DOMINIOS. */ 
	private static final String DELETE_SMART_POINT_FROM_DOMINIOS = DOMINIOS_NAMESPACE + "deleteSmartPointFromDominios";

	/** The Constant FETCH_DOMINIOS_BY_ID. */ 
	private static final String FETCH_DOMINIOS_BY_ID = DOMINIOS_NAMESPACE + "fetchDominiosById";

	/** The Constant FETCH_DOMINIOS_NAME_BY_ID. */ 
	private static final String FETCH_DOMINIOS_NAME_BY_ID = DOMINIOS_NAMESPACE + "fetchDominiosNameById";

	/** The Constant UPDATE_AUTO_DOMINIOS_DOMINIOS. */ 
	private static final String UPDATE_AUTO_DOMINIOS_DOMINIOS = DOMINIOS_NAMESPACE + "updateAutoDominiosDominios";

	/** The Constant DELETE_DOMINIOS. */ 
	private static final String DELETE_DOMINIOS = DOMINIOS_NAMESPACE + "deleteDominios";

	/** The Constant FETCH_DOMINIOSS_BY_LIGHT_ID. */ 
	private static final String FETCH_DOMINIOSS_BY_LIGHT_ID = DOMINIOS_NAMESPACE + "fetchDominiossByLightId";

	/** The Constant FETCH_DOMINIOSS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_DOMINIOSS_BY_SMART_POINT_ID = DOMINIOS_NAMESPACE + "fetchDominiossBySmartPointId";

	/** The Constant INSERT_DOMINIOS. */ 
	private static final String INSERT_DOMINIOS = DOMINIOS_NAMESPACE + "insertDominios";

	/** The Constant INSERT_SMART_POINT_TO_DOMINIOS. */ 
	private static final String INSERT_SMART_POINT_TO_DOMINIOS = DOMINIOS_NAMESPACE + "insertSmartPointToDominios";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_DOMINIOS = DOMINIOS_NAMESPACE + "fetchLightsBelongDominios";

	/** The Constant FETCH_ALL_DOMINIOSS. */ 
	private static final String FETCH_ALL_DOMINIOSS = DOMINIOS_NAMESPACE + "fetchAllDominioss";

	/** The Constant DOMINIOSID. */ 
	private static final String DOMINIOS_ID = "dominiosId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant DOMINIOSNAME. */ 
	private static final String DOMINIOSNAME = "dominiosname";

	/** The Constant AUTODOMINIOS. */ 
	private static final String AUTODOMINIOS = "autodominios";

	/** The Constant AUTODOMINIOS. */ 
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
	 * @see com.sensus.mlc.dominios.dao.IDominiosDAO#fetchAllDominioss(com.sensus.mlc.dominios.model.request.InquiryDominiosRequest)
	 */ 
	@Override
	public InternalResultsResponse<Dominios> fetchAllDominios(InquiryDominiosRequest inquiryDominiosRequest)
	{
		InternalResultsResponse<Dominios> response = new InternalResultsResponse<Dominios>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryDominiosRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryDominiosRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryDominiosRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryDominiosRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryDominiosRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryDominiosRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryDominiosRequest.getDominioss()))
		{
			paramMap.put(DOMINIOS_ID, inquiryDominiosRequest.getDominioss().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_DOMINIOSS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.dominios.dao.IDominiosDAO#fetchDominiosById(com.sensus.mlc.dominios.model.Dominios
	 */ 
	@Override
	public InternalResultsResponse<Dominios> fetchDominiosByName(DominiosRequest dominiosRequest)
	{ 
		InternalResultsResponse<Dominios> response = new InternalResultsResponse<Dominios>();
		doQueryForList(getSqlSession(), FETCH_DOMINIOS_BY_ID, dominiosRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.dominios.dao.IDominiosDAO#insertDominios(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */ 
	@Override
	public InternalResultsResponse<Dominios> insertDominios(DominiosRequest dominiosRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Dominios dominios = dominiosRequest.getDominios();

		dominios.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_DOMINIOS, dominiosRequest));
   dominios.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, dominiosRequest));
		InternalResultsResponse<Dominios> response = new InternalResultsResponse<Dominios>();
		response.addResult(dominios);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.dominios.dao.IDominiosDAO#deleteDominios(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */ 
	@Override
	public InternalResponse deleteDominios(DominiosRequest dominiosRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_DOMINIOS, dominiosRequest.getDominios(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.dominios.dao.IDominiosDAO#fetchDominiosById(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Dominios> fetchDominiosById(DominiosRequest dominiosRequest) 
	{
		InternalResultsResponse<Dominios> response = new InternalResultsResponse<Dominios>();
		doQueryForList(getSqlSession(), FETCH_DOMINIOS_BY_ID, dominiosRequest.getDominios(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.dominios.dac.IDominiosDAC#fetchDominiosNameById(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Dominios> fetchDominiosNameById(DominiosRequest dominiosRequest)
	{
		InternalResultsResponse<Dominios> response = new InternalResultsResponse<Dominios>();
		doQueryForList(getSqlSession(), FETCH_DOMINIOS_NAME_BY_ID, dominiosRequest.getDominios(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.dominios.dac.IDominiosDAC#updateDominios(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */ 
	@Override 
	public InternalResponse updateDominios(DominiosRequest dominiosRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_DOMINIOS, dominiosRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_DOMINIOSVALIDATOR_DOMINIOS_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


