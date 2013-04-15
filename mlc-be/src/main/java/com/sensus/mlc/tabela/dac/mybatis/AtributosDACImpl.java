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
import com.sensus.mlc.atributos.dac.IAtributosDAC;
import com.sensus.mlc.atributos.model.Atributos;
import com.sensus.mlc.atributos.model.AtributosOrderByEnum;
import com.sensus.mlc.atributos.model.request.InquiryAtributosRequest;
import com.sensus.mlc.atributos.model.request.AtributosRequest;

/** 
 * The Class AtributosDACImpl.
 */ 
public class AtributosDACImpl extends SqlSessionDaoSupport implements IAtributosDAC
{ 

	/** The Constant ATRIBUTOS_NAMESPACE. */ 
	private static final String ATRIBUTOS_NAMESPACE = "Atributos.";

	/** The Constant FETCH_ATRIBUTOS_BY_ID. */ 
	private static final String FETCH_ATRIBUTOS_BY_ID = ATRIBUTOS_NAMESPACE + "fetchAtributosById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = ATRIBUTOS_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_ATRIBUTOS. */ 
	private static final String DELETE_SMART_POINT_FROM_ATRIBUTOS = ATRIBUTOS_NAMESPACE + "deleteSmartPointFromAtributos";

	/** The Constant FETCH_ATRIBUTOS_BY_ID. */ 
	private static final String FETCH_ATRIBUTOS_BY_ID = ATRIBUTOS_NAMESPACE + "fetchAtributosById";

	/** The Constant FETCH_ATRIBUTOS_NAME_BY_ID. */ 
	private static final String FETCH_ATRIBUTOS_NAME_BY_ID = ATRIBUTOS_NAMESPACE + "fetchAtributosNameById";

	/** The Constant UPDATE_AUTO_ATRIBUTOS_ATRIBUTOS. */ 
	private static final String UPDATE_AUTO_ATRIBUTOS_ATRIBUTOS = ATRIBUTOS_NAMESPACE + "updateAutoAtributosAtributos";

	/** The Constant DELETE_ATRIBUTOS. */ 
	private static final String DELETE_ATRIBUTOS = ATRIBUTOS_NAMESPACE + "deleteAtributos";

	/** The Constant FETCH_ATRIBUTOSS_BY_LIGHT_ID. */ 
	private static final String FETCH_ATRIBUTOSS_BY_LIGHT_ID = ATRIBUTOS_NAMESPACE + "fetchAtributossByLightId";

	/** The Constant FETCH_ATRIBUTOSS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_ATRIBUTOSS_BY_SMART_POINT_ID = ATRIBUTOS_NAMESPACE + "fetchAtributossBySmartPointId";

	/** The Constant INSERT_ATRIBUTOS. */ 
	private static final String INSERT_ATRIBUTOS = ATRIBUTOS_NAMESPACE + "insertAtributos";

	/** The Constant INSERT_SMART_POINT_TO_ATRIBUTOS. */ 
	private static final String INSERT_SMART_POINT_TO_ATRIBUTOS = ATRIBUTOS_NAMESPACE + "insertSmartPointToAtributos";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_ATRIBUTOS = ATRIBUTOS_NAMESPACE + "fetchLightsBelongAtributos";

	/** The Constant FETCH_ALL_ATRIBUTOSS. */ 
	private static final String FETCH_ALL_ATRIBUTOSS = ATRIBUTOS_NAMESPACE + "fetchAllAtributoss";

	/** The Constant ATRIBUTOSID. */ 
	private static final String ATRIBUTOS_ID = "atributosId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant ATRIBUTOSNAME. */ 
	private static final String ATRIBUTOSNAME = "atributosname";

	/** The Constant AUTOATRIBUTOS. */ 
	private static final String AUTOATRIBUTOS = "autoatributos";

	/** The Constant AUTOATRIBUTOS. */ 
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
	 * @see com.sensus.mlc.atributos.dao.IAtributosDAO#fetchAllAtributoss(com.sensus.mlc.atributos.model.request.InquiryAtributosRequest)
	 */ 
	@Override
	public InternalResultsResponse<Atributos> fetchAllAtributos(InquiryAtributosRequest inquiryAtributosRequest)
	{
		InternalResultsResponse<Atributos> response = new InternalResultsResponse<Atributos>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryAtributosRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryAtributosRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryAtributosRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryAtributosRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryAtributosRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryAtributosRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryAtributosRequest.getAtributoss()))
		{
			paramMap.put(ATRIBUTOS_ID, inquiryAtributosRequest.getAtributoss().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_ATRIBUTOSS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.atributos.dao.IAtributosDAO#fetchAtributosById(com.sensus.mlc.atributos.model.Atributos
	 */ 
	@Override
	public InternalResultsResponse<Atributos> fetchAtributosByName(AtributosRequest atributosRequest)
	{ 
		InternalResultsResponse<Atributos> response = new InternalResultsResponse<Atributos>();
		doQueryForList(getSqlSession(), FETCH_ATRIBUTOS_BY_ID, atributosRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.atributos.dao.IAtributosDAO#insertAtributos(com.sensus.mlc.atributos.model.request.AtributosRequest)
	 */ 
	@Override
	public InternalResultsResponse<Atributos> insertAtributos(AtributosRequest atributosRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Atributos atributos = atributosRequest.getAtributos();

		atributos.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_ATRIBUTOS, atributosRequest));
   atributos.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, atributosRequest));
		InternalResultsResponse<Atributos> response = new InternalResultsResponse<Atributos>();
		response.addResult(atributos);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.atributos.dao.IAtributosDAO#deleteAtributos(com.sensus.mlc.atributos.model.request.AtributosRequest)
	 */ 
	@Override
	public InternalResponse deleteAtributos(AtributosRequest atributosRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_ATRIBUTOS, atributosRequest.getAtributos(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.atributos.dao.IAtributosDAO#fetchAtributosById(com.sensus.mlc.atributos.model.request.AtributosRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Atributos> fetchAtributosById(AtributosRequest atributosRequest) 
	{
		InternalResultsResponse<Atributos> response = new InternalResultsResponse<Atributos>();
		doQueryForList(getSqlSession(), FETCH_ATRIBUTOS_BY_ID, atributosRequest.getAtributos(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.atributos.dac.IAtributosDAC#fetchAtributosNameById(com.sensus.mlc.atributos.model.request.AtributosRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Atributos> fetchAtributosNameById(AtributosRequest atributosRequest)
	{
		InternalResultsResponse<Atributos> response = new InternalResultsResponse<Atributos>();
		doQueryForList(getSqlSession(), FETCH_ATRIBUTOS_NAME_BY_ID, atributosRequest.getAtributos(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.atributos.dac.IAtributosDAC#updateAtributos(com.sensus.mlc.atributos.model.request.AtributosRequest)
	 */ 
	@Override 
	public InternalResponse updateAtributos(AtributosRequest atributosRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_ATRIBUTOS, atributosRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_ATRIBUTOSVALIDATOR_ATRIBUTOS_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


