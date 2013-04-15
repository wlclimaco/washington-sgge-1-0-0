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
import com.sensus.mlc.embalagens.dac.IEmbalagensDAC;
import com.sensus.mlc.embalagens.model.Embalagens;
import com.sensus.mlc.embalagens.model.EmbalagensOrderByEnum;
import com.sensus.mlc.embalagens.model.request.InquiryEmbalagensRequest;
import com.sensus.mlc.embalagens.model.request.EmbalagensRequest;

/** 
 * The Class EmbalagensDACImpl.
 */ 
public class EmbalagensDACImpl extends SqlSessionDaoSupport implements IEmbalagensDAC
{ 

	/** The Constant EMBALAGENS_NAMESPACE. */ 
	private static final String EMBALAGENS_NAMESPACE = "Embalagens.";

	/** The Constant FETCH_EMBALAGENS_BY_ID. */ 
	private static final String FETCH_EMBALAGENS_BY_ID = EMBALAGENS_NAMESPACE + "fetchEmbalagensById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = EMBALAGENS_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_EMBALAGENS. */ 
	private static final String DELETE_SMART_POINT_FROM_EMBALAGENS = EMBALAGENS_NAMESPACE + "deleteSmartPointFromEmbalagens";

	/** The Constant FETCH_EMBALAGENS_BY_ID. */ 
	private static final String FETCH_EMBALAGENS_BY_ID = EMBALAGENS_NAMESPACE + "fetchEmbalagensById";

	/** The Constant FETCH_EMBALAGENS_NAME_BY_ID. */ 
	private static final String FETCH_EMBALAGENS_NAME_BY_ID = EMBALAGENS_NAMESPACE + "fetchEmbalagensNameById";

	/** The Constant UPDATE_AUTO_EMBALAGENS_EMBALAGENS. */ 
	private static final String UPDATE_AUTO_EMBALAGENS_EMBALAGENS = EMBALAGENS_NAMESPACE + "updateAutoEmbalagensEmbalagens";

	/** The Constant DELETE_EMBALAGENS. */ 
	private static final String DELETE_EMBALAGENS = EMBALAGENS_NAMESPACE + "deleteEmbalagens";

	/** The Constant FETCH_EMBALAGENSS_BY_LIGHT_ID. */ 
	private static final String FETCH_EMBALAGENSS_BY_LIGHT_ID = EMBALAGENS_NAMESPACE + "fetchEmbalagenssByLightId";

	/** The Constant FETCH_EMBALAGENSS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_EMBALAGENSS_BY_SMART_POINT_ID = EMBALAGENS_NAMESPACE + "fetchEmbalagenssBySmartPointId";

	/** The Constant INSERT_EMBALAGENS. */ 
	private static final String INSERT_EMBALAGENS = EMBALAGENS_NAMESPACE + "insertEmbalagens";

	/** The Constant INSERT_SMART_POINT_TO_EMBALAGENS. */ 
	private static final String INSERT_SMART_POINT_TO_EMBALAGENS = EMBALAGENS_NAMESPACE + "insertSmartPointToEmbalagens";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_EMBALAGENS = EMBALAGENS_NAMESPACE + "fetchLightsBelongEmbalagens";

	/** The Constant FETCH_ALL_EMBALAGENSS. */ 
	private static final String FETCH_ALL_EMBALAGENSS = EMBALAGENS_NAMESPACE + "fetchAllEmbalagenss";

	/** The Constant EMBALAGENSID. */ 
	private static final String EMBALAGENS_ID = "embalagensId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant EMBALAGENSNAME. */ 
	private static final String EMBALAGENSNAME = "embalagensname";

	/** The Constant AUTOEMBALAGENS. */ 
	private static final String AUTOEMBALAGENS = "autoembalagens";

	/** The Constant AUTOEMBALAGENS. */ 
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
	 * @see com.sensus.mlc.embalagens.dao.IEmbalagensDAO#fetchAllEmbalagenss(com.sensus.mlc.embalagens.model.request.InquiryEmbalagensRequest)
	 */ 
	@Override
	public InternalResultsResponse<Embalagens> fetchAllEmbalagens(InquiryEmbalagensRequest inquiryEmbalagensRequest)
	{
		InternalResultsResponse<Embalagens> response = new InternalResultsResponse<Embalagens>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryEmbalagensRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryEmbalagensRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryEmbalagensRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryEmbalagensRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryEmbalagensRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryEmbalagensRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryEmbalagensRequest.getEmbalagenss()))
		{
			paramMap.put(EMBALAGENS_ID, inquiryEmbalagensRequest.getEmbalagenss().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_EMBALAGENSS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.embalagens.dao.IEmbalagensDAO#fetchEmbalagensById(com.sensus.mlc.embalagens.model.Embalagens
	 */ 
	@Override
	public InternalResultsResponse<Embalagens> fetchEmbalagensByName(EmbalagensRequest embalagensRequest)
	{ 
		InternalResultsResponse<Embalagens> response = new InternalResultsResponse<Embalagens>();
		doQueryForList(getSqlSession(), FETCH_EMBALAGENS_BY_ID, embalagensRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.dao.IEmbalagensDAO#insertEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */ 
	@Override
	public InternalResultsResponse<Embalagens> insertEmbalagens(EmbalagensRequest embalagensRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Embalagens embalagens = embalagensRequest.getEmbalagens();

		embalagens.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_EMBALAGENS, embalagensRequest));
   embalagens.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, embalagensRequest));
		InternalResultsResponse<Embalagens> response = new InternalResultsResponse<Embalagens>();
		response.addResult(embalagens);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.embalagens.dao.IEmbalagensDAO#deleteEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */ 
	@Override
	public InternalResponse deleteEmbalagens(EmbalagensRequest embalagensRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_EMBALAGENS, embalagensRequest.getEmbalagens(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.dao.IEmbalagensDAO#fetchEmbalagensById(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Embalagens> fetchEmbalagensById(EmbalagensRequest embalagensRequest) 
	{
		InternalResultsResponse<Embalagens> response = new InternalResultsResponse<Embalagens>();
		doQueryForList(getSqlSession(), FETCH_EMBALAGENS_BY_ID, embalagensRequest.getEmbalagens(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.embalagens.dac.IEmbalagensDAC#fetchEmbalagensNameById(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Embalagens> fetchEmbalagensNameById(EmbalagensRequest embalagensRequest)
	{
		InternalResultsResponse<Embalagens> response = new InternalResultsResponse<Embalagens>();
		doQueryForList(getSqlSession(), FETCH_EMBALAGENS_NAME_BY_ID, embalagensRequest.getEmbalagens(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.dac.IEmbalagensDAC#updateEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */ 
	@Override 
	public InternalResponse updateEmbalagens(EmbalagensRequest embalagensRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_EMBALAGENS, embalagensRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_EMBALAGENSVALIDATOR_EMBALAGENS_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


