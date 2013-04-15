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
import com.sensus.mlc.pais.dac.IPaisDAC;
import com.sensus.mlc.pais.model.Pais;
import com.sensus.mlc.pais.model.PaisOrderByEnum;
import com.sensus.mlc.pais.model.request.InquiryPaisRequest;
import com.sensus.mlc.pais.model.request.PaisRequest;

/** 
 * The Class PaisDACImpl.
 */ 
public class PaisDACImpl extends SqlSessionDaoSupport implements IPaisDAC
{ 

	/** The Constant PAIS_NAMESPACE. */ 
	private static final String PAIS_NAMESPACE = "Pais.";

	/** The Constant FETCH_PAIS_BY_ID. */ 
	private static final String FETCH_PAIS_BY_ID = PAIS_NAMESPACE + "fetchPaisById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = PAIS_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_PAIS. */ 
	private static final String DELETE_SMART_POINT_FROM_PAIS = PAIS_NAMESPACE + "deleteSmartPointFromPais";

	/** The Constant FETCH_PAIS_BY_ID. */ 
	private static final String FETCH_PAIS_BY_ID = PAIS_NAMESPACE + "fetchPaisById";

	/** The Constant FETCH_PAIS_NAME_BY_ID. */ 
	private static final String FETCH_PAIS_NAME_BY_ID = PAIS_NAMESPACE + "fetchPaisNameById";

	/** The Constant UPDATE_AUTO_PAIS_PAIS. */ 
	private static final String UPDATE_AUTO_PAIS_PAIS = PAIS_NAMESPACE + "updateAutoPaisPais";

	/** The Constant DELETE_PAIS. */ 
	private static final String DELETE_PAIS = PAIS_NAMESPACE + "deletePais";

	/** The Constant FETCH_PAISS_BY_LIGHT_ID. */ 
	private static final String FETCH_PAISS_BY_LIGHT_ID = PAIS_NAMESPACE + "fetchPaissByLightId";

	/** The Constant FETCH_PAISS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_PAISS_BY_SMART_POINT_ID = PAIS_NAMESPACE + "fetchPaissBySmartPointId";

	/** The Constant INSERT_PAIS. */ 
	private static final String INSERT_PAIS = PAIS_NAMESPACE + "insertPais";

	/** The Constant INSERT_SMART_POINT_TO_PAIS. */ 
	private static final String INSERT_SMART_POINT_TO_PAIS = PAIS_NAMESPACE + "insertSmartPointToPais";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_PAIS = PAIS_NAMESPACE + "fetchLightsBelongPais";

	/** The Constant FETCH_ALL_PAISS. */ 
	private static final String FETCH_ALL_PAISS = PAIS_NAMESPACE + "fetchAllPaiss";

	/** The Constant PAISID. */ 
	private static final String PAIS_ID = "paisId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant PAISNAME. */ 
	private static final String PAISNAME = "paisname";

	/** The Constant AUTOPAIS. */ 
	private static final String AUTOPAIS = "autopais";

	/** The Constant AUTOPAIS. */ 
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
	 * @see com.sensus.mlc.pais.dao.IPaisDAO#fetchAllPaiss(com.sensus.mlc.pais.model.request.InquiryPaisRequest)
	 */ 
	@Override
	public InternalResultsResponse<Pais> fetchAllPais(InquiryPaisRequest inquiryPaisRequest)
	{
		InternalResultsResponse<Pais> response = new InternalResultsResponse<Pais>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryPaisRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryPaisRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryPaisRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryPaisRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryPaisRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryPaisRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryPaisRequest.getPaiss()))
		{
			paramMap.put(PAIS_ID, inquiryPaisRequest.getPaiss().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_PAISS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.pais.dao.IPaisDAO#fetchPaisById(com.sensus.mlc.pais.model.Pais
	 */ 
	@Override
	public InternalResultsResponse<Pais> fetchPaisByName(PaisRequest paisRequest)
	{ 
		InternalResultsResponse<Pais> response = new InternalResultsResponse<Pais>();
		doQueryForList(getSqlSession(), FETCH_PAIS_BY_ID, paisRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.pais.dao.IPaisDAO#insertPais(com.sensus.mlc.pais.model.request.PaisRequest)
	 */ 
	@Override
	public InternalResultsResponse<Pais> insertPais(PaisRequest paisRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Pais pais = paisRequest.getPais();

		pais.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_PAIS, paisRequest));
   pais.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, paisRequest));
		InternalResultsResponse<Pais> response = new InternalResultsResponse<Pais>();
		response.addResult(pais);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.pais.dao.IPaisDAO#deletePais(com.sensus.mlc.pais.model.request.PaisRequest)
	 */ 
	@Override
	public InternalResponse deletePais(PaisRequest paisRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_PAIS, paisRequest.getPais(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.pais.dao.IPaisDAO#fetchPaisById(com.sensus.mlc.pais.model.request.PaisRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Pais> fetchPaisById(PaisRequest paisRequest) 
	{
		InternalResultsResponse<Pais> response = new InternalResultsResponse<Pais>();
		doQueryForList(getSqlSession(), FETCH_PAIS_BY_ID, paisRequest.getPais(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.pais.dac.IPaisDAC#fetchPaisNameById(com.sensus.mlc.pais.model.request.PaisRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Pais> fetchPaisNameById(PaisRequest paisRequest)
	{
		InternalResultsResponse<Pais> response = new InternalResultsResponse<Pais>();
		doQueryForList(getSqlSession(), FETCH_PAIS_NAME_BY_ID, paisRequest.getPais(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.pais.dac.IPaisDAC#updatePais(com.sensus.mlc.pais.model.request.PaisRequest)
	 */ 
	@Override 
	public InternalResponse updatePais(PaisRequest paisRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_PAIS, paisRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_PAISVALIDATOR_PAIS_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


