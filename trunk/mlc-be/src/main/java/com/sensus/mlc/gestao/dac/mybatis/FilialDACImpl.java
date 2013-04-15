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
import com.sensus.mlc.filial.dac.IFilialDAC;
import com.sensus.mlc.filial.model.Filial;
import com.sensus.mlc.filial.model.FilialOrderByEnum;
import com.sensus.mlc.filial.model.request.InquiryFilialRequest;
import com.sensus.mlc.filial.model.request.FilialRequest;

/** 
 * The Class FilialDACImpl.
 */ 
public class FilialDACImpl extends SqlSessionDaoSupport implements IFilialDAC
{ 

	/** The Constant FILIAL_NAMESPACE. */ 
	private static final String FILIAL_NAMESPACE = "Filial.";

	/** The Constant FETCH_FILIAL_BY_ID. */ 
	private static final String FETCH_FILIAL_BY_ID = FILIAL_NAMESPACE + "fetchFilialById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = FILIAL_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_FILIAL. */ 
	private static final String DELETE_SMART_POINT_FROM_FILIAL = FILIAL_NAMESPACE + "deleteSmartPointFromFilial";

	/** The Constant FETCH_FILIAL_BY_ID. */ 
	private static final String FETCH_FILIAL_BY_ID = FILIAL_NAMESPACE + "fetchFilialById";

	/** The Constant FETCH_FILIAL_NAME_BY_ID. */ 
	private static final String FETCH_FILIAL_NAME_BY_ID = FILIAL_NAMESPACE + "fetchFilialNameById";

	/** The Constant UPDATE_AUTO_FILIAL_FILIAL. */ 
	private static final String UPDATE_AUTO_FILIAL_FILIAL = FILIAL_NAMESPACE + "updateAutoFilialFilial";

	/** The Constant DELETE_FILIAL. */ 
	private static final String DELETE_FILIAL = FILIAL_NAMESPACE + "deleteFilial";

	/** The Constant FETCH_FILIALS_BY_LIGHT_ID. */ 
	private static final String FETCH_FILIALS_BY_LIGHT_ID = FILIAL_NAMESPACE + "fetchFilialsByLightId";

	/** The Constant FETCH_FILIALS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_FILIALS_BY_SMART_POINT_ID = FILIAL_NAMESPACE + "fetchFilialsBySmartPointId";

	/** The Constant INSERT_FILIAL. */ 
	private static final String INSERT_FILIAL = FILIAL_NAMESPACE + "insertFilial";

	/** The Constant INSERT_SMART_POINT_TO_FILIAL. */ 
	private static final String INSERT_SMART_POINT_TO_FILIAL = FILIAL_NAMESPACE + "insertSmartPointToFilial";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_FILIAL = FILIAL_NAMESPACE + "fetchLightsBelongFilial";

	/** The Constant FETCH_ALL_FILIALS. */ 
	private static final String FETCH_ALL_FILIALS = FILIAL_NAMESPACE + "fetchAllFilials";

	/** The Constant FILIALID. */ 
	private static final String FILIAL_ID = "filialId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant FILIALNAME. */ 
	private static final String FILIALNAME = "filialname";

	/** The Constant AUTOFILIAL. */ 
	private static final String AUTOFILIAL = "autofilial";

	/** The Constant AUTOFILIAL. */ 
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
	 * @see com.sensus.mlc.filial.dao.IFilialDAO#fetchAllFilials(com.sensus.mlc.filial.model.request.InquiryFilialRequest)
	 */ 
	@Override
	public InternalResultsResponse<Filial> fetchAllFilial(InquiryFilialRequest inquiryFilialRequest)
	{
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryFilialRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryFilialRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryFilialRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryFilialRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryFilialRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryFilialRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryFilialRequest.getFilials()))
		{
			paramMap.put(FILIAL_ID, inquiryFilialRequest.getFilials().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_FILIALS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.filial.dao.IFilialDAO#fetchFilialById(com.sensus.mlc.filial.model.Filial
	 */ 
	@Override
	public InternalResultsResponse<Filial> fetchFilialByName(FilialRequest filialRequest)
	{ 
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
		doQueryForList(getSqlSession(), FETCH_FILIAL_BY_ID, filialRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.filial.dao.IFilialDAO#insertFilial(com.sensus.mlc.filial.model.request.FilialRequest)
	 */ 
	@Override
	public InternalResultsResponse<Filial> insertFilial(FilialRequest filialRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Filial filial = filialRequest.getFilial();

		filial.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_FILIAL, filialRequest));
   filial.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, filialRequest));
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
		response.addResult(filial);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.filial.dao.IFilialDAO#deleteFilial(com.sensus.mlc.filial.model.request.FilialRequest)
	 */ 
	@Override
	public InternalResponse deleteFilial(FilialRequest filialRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_FILIAL, filialRequest.getFilial(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.filial.dao.IFilialDAO#fetchFilialById(com.sensus.mlc.filial.model.request.FilialRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Filial> fetchFilialById(FilialRequest filialRequest) 
	{
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
		doQueryForList(getSqlSession(), FETCH_FILIAL_BY_ID, filialRequest.getFilial(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.filial.dac.IFilialDAC#fetchFilialNameById(com.sensus.mlc.filial.model.request.FilialRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Filial> fetchFilialNameById(FilialRequest filialRequest)
	{
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
		doQueryForList(getSqlSession(), FETCH_FILIAL_NAME_BY_ID, filialRequest.getFilial(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.filial.dac.IFilialDAC#updateFilial(com.sensus.mlc.filial.model.request.FilialRequest)
	 */ 
	@Override 
	public InternalResponse updateFilial(FilialRequest filialRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_FILIAL, filialRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_FILIALVALIDATOR_FILIAL_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


