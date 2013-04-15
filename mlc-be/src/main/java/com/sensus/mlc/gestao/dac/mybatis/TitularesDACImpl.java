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
import com.sensus.mlc.titulares.dac.ITitularesDAC;
import com.sensus.mlc.titulares.model.Titulares;
import com.sensus.mlc.titulares.model.TitularesOrderByEnum;
import com.sensus.mlc.titulares.model.request.InquiryTitularesRequest;
import com.sensus.mlc.titulares.model.request.TitularesRequest;

/** 
 * The Class TitularesDACImpl.
 */ 
public class TitularesDACImpl extends SqlSessionDaoSupport implements ITitularesDAC
{ 

	/** The Constant TITULARES_NAMESPACE. */ 
	private static final String TITULARES_NAMESPACE = "Titulares.";

	/** The Constant FETCH_TITULARES_BY_ID. */ 
	private static final String FETCH_TITULARES_BY_ID = TITULARES_NAMESPACE + "fetchTitularesById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = TITULARES_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_TITULARES. */ 
	private static final String DELETE_SMART_POINT_FROM_TITULARES = TITULARES_NAMESPACE + "deleteSmartPointFromTitulares";

	/** The Constant FETCH_TITULARES_BY_ID. */ 
	private static final String FETCH_TITULARES_BY_ID = TITULARES_NAMESPACE + "fetchTitularesById";

	/** The Constant FETCH_TITULARES_NAME_BY_ID. */ 
	private static final String FETCH_TITULARES_NAME_BY_ID = TITULARES_NAMESPACE + "fetchTitularesNameById";

	/** The Constant UPDATE_AUTO_TITULARES_TITULARES. */ 
	private static final String UPDATE_AUTO_TITULARES_TITULARES = TITULARES_NAMESPACE + "updateAutoTitularesTitulares";

	/** The Constant DELETE_TITULARES. */ 
	private static final String DELETE_TITULARES = TITULARES_NAMESPACE + "deleteTitulares";

	/** The Constant FETCH_TITULARESS_BY_LIGHT_ID. */ 
	private static final String FETCH_TITULARESS_BY_LIGHT_ID = TITULARES_NAMESPACE + "fetchTitularessByLightId";

	/** The Constant FETCH_TITULARESS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_TITULARESS_BY_SMART_POINT_ID = TITULARES_NAMESPACE + "fetchTitularessBySmartPointId";

	/** The Constant INSERT_TITULARES. */ 
	private static final String INSERT_TITULARES = TITULARES_NAMESPACE + "insertTitulares";

	/** The Constant INSERT_SMART_POINT_TO_TITULARES. */ 
	private static final String INSERT_SMART_POINT_TO_TITULARES = TITULARES_NAMESPACE + "insertSmartPointToTitulares";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_TITULARES = TITULARES_NAMESPACE + "fetchLightsBelongTitulares";

	/** The Constant FETCH_ALL_TITULARESS. */ 
	private static final String FETCH_ALL_TITULARESS = TITULARES_NAMESPACE + "fetchAllTitularess";

	/** The Constant TITULARESID. */ 
	private static final String TITULARES_ID = "titularesId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant TITULARESNAME. */ 
	private static final String TITULARESNAME = "titularesname";

	/** The Constant AUTOTITULARES. */ 
	private static final String AUTOTITULARES = "autotitulares";

	/** The Constant AUTOTITULARES. */ 
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
	 * @see com.sensus.mlc.titulares.dao.ITitularesDAO#fetchAllTitularess(com.sensus.mlc.titulares.model.request.InquiryTitularesRequest)
	 */ 
	@Override
	public InternalResultsResponse<Titulares> fetchAllTitulares(InquiryTitularesRequest inquiryTitularesRequest)
	{
		InternalResultsResponse<Titulares> response = new InternalResultsResponse<Titulares>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryTitularesRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryTitularesRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryTitularesRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryTitularesRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryTitularesRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryTitularesRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryTitularesRequest.getTitularess()))
		{
			paramMap.put(TITULARES_ID, inquiryTitularesRequest.getTitularess().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_TITULARESS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.titulares.dao.ITitularesDAO#fetchTitularesById(com.sensus.mlc.titulares.model.Titulares
	 */ 
	@Override
	public InternalResultsResponse<Titulares> fetchTitularesByName(TitularesRequest titularesRequest)
	{ 
		InternalResultsResponse<Titulares> response = new InternalResultsResponse<Titulares>();
		doQueryForList(getSqlSession(), FETCH_TITULARES_BY_ID, titularesRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.titulares.dao.ITitularesDAO#insertTitulares(com.sensus.mlc.titulares.model.request.TitularesRequest)
	 */ 
	@Override
	public InternalResultsResponse<Titulares> insertTitulares(TitularesRequest titularesRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Titulares titulares = titularesRequest.getTitulares();

		titulares.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_TITULARES, titularesRequest));
   titulares.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, titularesRequest));
		InternalResultsResponse<Titulares> response = new InternalResultsResponse<Titulares>();
		response.addResult(titulares);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.titulares.dao.ITitularesDAO#deleteTitulares(com.sensus.mlc.titulares.model.request.TitularesRequest)
	 */ 
	@Override
	public InternalResponse deleteTitulares(TitularesRequest titularesRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_TITULARES, titularesRequest.getTitulares(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.titulares.dao.ITitularesDAO#fetchTitularesById(com.sensus.mlc.titulares.model.request.TitularesRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Titulares> fetchTitularesById(TitularesRequest titularesRequest) 
	{
		InternalResultsResponse<Titulares> response = new InternalResultsResponse<Titulares>();
		doQueryForList(getSqlSession(), FETCH_TITULARES_BY_ID, titularesRequest.getTitulares(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.titulares.dac.ITitularesDAC#fetchTitularesNameById(com.sensus.mlc.titulares.model.request.TitularesRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Titulares> fetchTitularesNameById(TitularesRequest titularesRequest)
	{
		InternalResultsResponse<Titulares> response = new InternalResultsResponse<Titulares>();
		doQueryForList(getSqlSession(), FETCH_TITULARES_NAME_BY_ID, titularesRequest.getTitulares(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.titulares.dac.ITitularesDAC#updateTitulares(com.sensus.mlc.titulares.model.request.TitularesRequest)
	 */ 
	@Override 
	public InternalResponse updateTitulares(TitularesRequest titularesRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_TITULARES, titularesRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_TITULARESVALIDATOR_TITULARES_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


