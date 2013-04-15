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
import com.sensus.mlc.chaveestrangeira.dac.IChaveestrangeiraDAC;
import com.sensus.mlc.chaveestrangeira.model.Chaveestrangeira;
import com.sensus.mlc.chaveestrangeira.model.ChaveestrangeiraOrderByEnum;
import com.sensus.mlc.chaveestrangeira.model.request.InquiryChaveestrangeiraRequest;
import com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest;

/** 
 * The Class ChaveestrangeiraDACImpl.
 */ 
public class ChaveestrangeiraDACImpl extends SqlSessionDaoSupport implements IChaveestrangeiraDAC
{ 

	/** The Constant CHAVEESTRANGEIRA_NAMESPACE. */ 
	private static final String CHAVEESTRANGEIRA_NAMESPACE = "Chaveestrangeira.";

	/** The Constant FETCH_CHAVEESTRANGEIRA_BY_ID. */ 
	private static final String FETCH_CHAVEESTRANGEIRA_BY_ID = CHAVEESTRANGEIRA_NAMESPACE + "fetchChaveestrangeiraById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = CHAVEESTRANGEIRA_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_CHAVEESTRANGEIRA. */ 
	private static final String DELETE_SMART_POINT_FROM_CHAVEESTRANGEIRA = CHAVEESTRANGEIRA_NAMESPACE + "deleteSmartPointFromChaveestrangeira";

	/** The Constant FETCH_CHAVEESTRANGEIRA_BY_ID. */ 
	private static final String FETCH_CHAVEESTRANGEIRA_BY_ID = CHAVEESTRANGEIRA_NAMESPACE + "fetchChaveestrangeiraById";

	/** The Constant FETCH_CHAVEESTRANGEIRA_NAME_BY_ID. */ 
	private static final String FETCH_CHAVEESTRANGEIRA_NAME_BY_ID = CHAVEESTRANGEIRA_NAMESPACE + "fetchChaveestrangeiraNameById";

	/** The Constant UPDATE_AUTO_CHAVEESTRANGEIRA_CHAVEESTRANGEIRA. */ 
	private static final String UPDATE_AUTO_CHAVEESTRANGEIRA_CHAVEESTRANGEIRA = CHAVEESTRANGEIRA_NAMESPACE + "updateAutoChaveestrangeiraChaveestrangeira";

	/** The Constant DELETE_CHAVEESTRANGEIRA. */ 
	private static final String DELETE_CHAVEESTRANGEIRA = CHAVEESTRANGEIRA_NAMESPACE + "deleteChaveestrangeira";

	/** The Constant FETCH_CHAVEESTRANGEIRAS_BY_LIGHT_ID. */ 
	private static final String FETCH_CHAVEESTRANGEIRAS_BY_LIGHT_ID = CHAVEESTRANGEIRA_NAMESPACE + "fetchChaveestrangeirasByLightId";

	/** The Constant FETCH_CHAVEESTRANGEIRAS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_CHAVEESTRANGEIRAS_BY_SMART_POINT_ID = CHAVEESTRANGEIRA_NAMESPACE + "fetchChaveestrangeirasBySmartPointId";

	/** The Constant INSERT_CHAVEESTRANGEIRA. */ 
	private static final String INSERT_CHAVEESTRANGEIRA = CHAVEESTRANGEIRA_NAMESPACE + "insertChaveestrangeira";

	/** The Constant INSERT_SMART_POINT_TO_CHAVEESTRANGEIRA. */ 
	private static final String INSERT_SMART_POINT_TO_CHAVEESTRANGEIRA = CHAVEESTRANGEIRA_NAMESPACE + "insertSmartPointToChaveestrangeira";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_CHAVEESTRANGEIRA = CHAVEESTRANGEIRA_NAMESPACE + "fetchLightsBelongChaveestrangeira";

	/** The Constant FETCH_ALL_CHAVEESTRANGEIRAS. */ 
	private static final String FETCH_ALL_CHAVEESTRANGEIRAS = CHAVEESTRANGEIRA_NAMESPACE + "fetchAllChaveestrangeiras";

	/** The Constant CHAVEESTRANGEIRAID. */ 
	private static final String CHAVEESTRANGEIRA_ID = "chaveestrangeiraId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant CHAVEESTRANGEIRANAME. */ 
	private static final String CHAVEESTRANGEIRANAME = "chaveestrangeiraname";

	/** The Constant AUTOCHAVEESTRANGEIRA. */ 
	private static final String AUTOCHAVEESTRANGEIRA = "autochaveestrangeira";

	/** The Constant AUTOCHAVEESTRANGEIRA. */ 
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
	 * @see 
com.sensus.mlc.chaveestrangeira.dao.IChaveestrangeiraDAO#fetchAllChaveestrangeiras(com.sensus.mlc.chaveestrangeira.model.request.InquiryChaveestrangeiraRequest)
	 */ 
	@Override
	public InternalResultsResponse<Chaveestrangeira> fetchAllChaveestrangeira(InquiryChaveestrangeiraRequest inquiryChaveestrangeiraRequest)
	{
		InternalResultsResponse<Chaveestrangeira> response = new InternalResultsResponse<Chaveestrangeira>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryChaveestrangeiraRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryChaveestrangeiraRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryChaveestrangeiraRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryChaveestrangeiraRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryChaveestrangeiraRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryChaveestrangeiraRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryChaveestrangeiraRequest.getChaveestrangeiras()))
		{
			paramMap.put(CHAVEESTRANGEIRA_ID, inquiryChaveestrangeiraRequest.getChaveestrangeiras().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_CHAVEESTRANGEIRAS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveestrangeira.dao.IChaveestrangeiraDAO#fetchChaveestrangeiraById(com.sensus.mlc.chaveestrangeira.model.Chaveestrangeira
	 */ 
	@Override
	public InternalResultsResponse<Chaveestrangeira> fetchChaveestrangeiraByName(ChaveestrangeiraRequest chaveestrangeiraRequest)
	{ 
		InternalResultsResponse<Chaveestrangeira> response = new InternalResultsResponse<Chaveestrangeira>();
		doQueryForList(getSqlSession(), FETCH_CHAVEESTRANGEIRA_BY_ID, chaveestrangeiraRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.chaveestrangeira.dao.IChaveestrangeiraDAO#insertChaveestrangeira(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)
	 */ 
	@Override
	public InternalResultsResponse<Chaveestrangeira> insertChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Chaveestrangeira chaveestrangeira = chaveestrangeiraRequest.getChaveestrangeira();

		chaveestrangeira.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_CHAVEESTRANGEIRA, chaveestrangeiraRequest));
   chaveestrangeira.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, chaveestrangeiraRequest));
		InternalResultsResponse<Chaveestrangeira> response = new InternalResultsResponse<Chaveestrangeira>();
		response.addResult(chaveestrangeira);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.chaveestrangeira.dao.IChaveestrangeiraDAO#deleteChaveestrangeira(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)
	 */ 
	@Override
	public InternalResponse deleteChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_CHAVEESTRANGEIRA, chaveestrangeiraRequest.getChaveestrangeira(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.chaveestrangeira.dao.IChaveestrangeiraDAO#fetchChaveestrangeiraById(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Chaveestrangeira> fetchChaveestrangeiraById(ChaveestrangeiraRequest chaveestrangeiraRequest) 
	{
		InternalResultsResponse<Chaveestrangeira> response = new InternalResultsResponse<Chaveestrangeira>();
		doQueryForList(getSqlSession(), FETCH_CHAVEESTRANGEIRA_BY_ID, chaveestrangeiraRequest.getChaveestrangeira(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see 
com.sensus.mlc.chaveestrangeira.dac.IChaveestrangeiraDAC#fetchChaveestrangeiraNameById(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Chaveestrangeira> fetchChaveestrangeiraNameById(ChaveestrangeiraRequest chaveestrangeiraRequest)
	{
		InternalResultsResponse<Chaveestrangeira> response = new InternalResultsResponse<Chaveestrangeira>();
		doQueryForList(getSqlSession(), FETCH_CHAVEESTRANGEIRA_NAME_BY_ID, chaveestrangeiraRequest.getChaveestrangeira(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.chaveestrangeira.dac.IChaveestrangeiraDAC#updateChaveestrangeira(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)
	 */ 
	@Override 
	public InternalResponse updateChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_CHAVEESTRANGEIRA, chaveestrangeiraRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_CHAVEESTRANGEIRAVALIDATOR_CHAVEESTRANGEIRA_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


