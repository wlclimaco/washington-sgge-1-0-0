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
import com.sensus.mlc.unimed.dac.IUnimedDAC;
import com.sensus.mlc.unimed.model.Unimed;
import com.sensus.mlc.unimed.model.UnimedOrderByEnum;
import com.sensus.mlc.unimed.model.request.InquiryUnimedRequest;
import com.sensus.mlc.unimed.model.request.UnimedRequest;

/** 
 * The Class UnimedDACImpl.
 */ 
public class UnimedDACImpl extends SqlSessionDaoSupport implements IUnimedDAC
{ 

	/** The Constant UNIMED_NAMESPACE. */ 
	private static final String UNIMED_NAMESPACE = "Unimed.";

	/** The Constant FETCH_UNIMED_BY_ID. */ 
	private static final String FETCH_UNIMED_BY_ID = UNIMED_NAMESPACE + "fetchUnimedById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = UNIMED_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_UNIMED. */ 
	private static final String DELETE_SMART_POINT_FROM_UNIMED = UNIMED_NAMESPACE + "deleteSmartPointFromUnimed";

	/** The Constant FETCH_UNIMED_BY_ID. */ 
	private static final String FETCH_UNIMED_BY_ID = UNIMED_NAMESPACE + "fetchUnimedById";

	/** The Constant FETCH_UNIMED_NAME_BY_ID. */ 
	private static final String FETCH_UNIMED_NAME_BY_ID = UNIMED_NAMESPACE + "fetchUnimedNameById";

	/** The Constant UPDATE_AUTO_UNIMED_UNIMED. */ 
	private static final String UPDATE_AUTO_UNIMED_UNIMED = UNIMED_NAMESPACE + "updateAutoUnimedUnimed";

	/** The Constant DELETE_UNIMED. */ 
	private static final String DELETE_UNIMED = UNIMED_NAMESPACE + "deleteUnimed";

	/** The Constant FETCH_UNIMEDS_BY_LIGHT_ID. */ 
	private static final String FETCH_UNIMEDS_BY_LIGHT_ID = UNIMED_NAMESPACE + "fetchUnimedsByLightId";

	/** The Constant FETCH_UNIMEDS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_UNIMEDS_BY_SMART_POINT_ID = UNIMED_NAMESPACE + "fetchUnimedsBySmartPointId";

	/** The Constant INSERT_UNIMED. */ 
	private static final String INSERT_UNIMED = UNIMED_NAMESPACE + "insertUnimed";

	/** The Constant INSERT_SMART_POINT_TO_UNIMED. */ 
	private static final String INSERT_SMART_POINT_TO_UNIMED = UNIMED_NAMESPACE + "insertSmartPointToUnimed";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_UNIMED = UNIMED_NAMESPACE + "fetchLightsBelongUnimed";

	/** The Constant FETCH_ALL_UNIMEDS. */ 
	private static final String FETCH_ALL_UNIMEDS = UNIMED_NAMESPACE + "fetchAllUnimeds";

	/** The Constant UNIMEDID. */ 
	private static final String UNIMED_ID = "unimedId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant UNIMEDNAME. */ 
	private static final String UNIMEDNAME = "unimedname";

	/** The Constant AUTOUNIMED. */ 
	private static final String AUTOUNIMED = "autounimed";

	/** The Constant AUTOUNIMED. */ 
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
	 * @see com.sensus.mlc.unimed.dao.IUnimedDAO#fetchAllUnimeds(com.sensus.mlc.unimed.model.request.InquiryUnimedRequest)
	 */ 
	@Override
	public InternalResultsResponse<Unimed> fetchAllUnimed(InquiryUnimedRequest inquiryUnimedRequest)
	{
		InternalResultsResponse<Unimed> response = new InternalResultsResponse<Unimed>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryUnimedRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryUnimedRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryUnimedRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryUnimedRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryUnimedRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryUnimedRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryUnimedRequest.getUnimeds()))
		{
			paramMap.put(UNIMED_ID, inquiryUnimedRequest.getUnimeds().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_UNIMEDS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.unimed.dao.IUnimedDAO#fetchUnimedById(com.sensus.mlc.unimed.model.Unimed
	 */ 
	@Override
	public InternalResultsResponse<Unimed> fetchUnimedByName(UnimedRequest unimedRequest)
	{ 
		InternalResultsResponse<Unimed> response = new InternalResultsResponse<Unimed>();
		doQueryForList(getSqlSession(), FETCH_UNIMED_BY_ID, unimedRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.unimed.dao.IUnimedDAO#insertUnimed(com.sensus.mlc.unimed.model.request.UnimedRequest)
	 */ 
	@Override
	public InternalResultsResponse<Unimed> insertUnimed(UnimedRequest unimedRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Unimed unimed = unimedRequest.getUnimed();

		unimed.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_UNIMED, unimedRequest));
   unimed.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, unimedRequest));
		InternalResultsResponse<Unimed> response = new InternalResultsResponse<Unimed>();
		response.addResult(unimed);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.unimed.dao.IUnimedDAO#deleteUnimed(com.sensus.mlc.unimed.model.request.UnimedRequest)
	 */ 
	@Override
	public InternalResponse deleteUnimed(UnimedRequest unimedRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_UNIMED, unimedRequest.getUnimed(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.unimed.dao.IUnimedDAO#fetchUnimedById(com.sensus.mlc.unimed.model.request.UnimedRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Unimed> fetchUnimedById(UnimedRequest unimedRequest) 
	{
		InternalResultsResponse<Unimed> response = new InternalResultsResponse<Unimed>();
		doQueryForList(getSqlSession(), FETCH_UNIMED_BY_ID, unimedRequest.getUnimed(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.unimed.dac.IUnimedDAC#fetchUnimedNameById(com.sensus.mlc.unimed.model.request.UnimedRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Unimed> fetchUnimedNameById(UnimedRequest unimedRequest)
	{
		InternalResultsResponse<Unimed> response = new InternalResultsResponse<Unimed>();
		doQueryForList(getSqlSession(), FETCH_UNIMED_NAME_BY_ID, unimedRequest.getUnimed(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.unimed.dac.IUnimedDAC#updateUnimed(com.sensus.mlc.unimed.model.request.UnimedRequest)
	 */ 
	@Override 
	public InternalResponse updateUnimed(UnimedRequest unimedRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_UNIMED, unimedRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_UNIMEDVALIDATOR_UNIMED_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


