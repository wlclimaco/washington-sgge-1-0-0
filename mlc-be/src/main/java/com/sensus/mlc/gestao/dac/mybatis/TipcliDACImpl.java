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
import com.sensus.mlc.tipcli.dac.ITipcliDAC;
import com.sensus.mlc.tipcli.model.Tipcli;
import com.sensus.mlc.tipcli.model.TipcliOrderByEnum;
import com.sensus.mlc.tipcli.model.request.InquiryTipcliRequest;
import com.sensus.mlc.tipcli.model.request.TipcliRequest;

/** 
 * The Class TipcliDACImpl.
 */ 
public class TipcliDACImpl extends SqlSessionDaoSupport implements ITipcliDAC
{ 

	/** The Constant TIPCLI_NAMESPACE. */ 
	private static final String TIPCLI_NAMESPACE = "Tipcli.";

	/** The Constant FETCH_TIPCLI_BY_ID. */ 
	private static final String FETCH_TIPCLI_BY_ID = TIPCLI_NAMESPACE + "fetchTipcliById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = TIPCLI_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_TIPCLI. */ 
	private static final String DELETE_SMART_POINT_FROM_TIPCLI = TIPCLI_NAMESPACE + "deleteSmartPointFromTipcli";

	/** The Constant FETCH_TIPCLI_BY_ID. */ 
	private static final String FETCH_TIPCLI_BY_ID = TIPCLI_NAMESPACE + "fetchTipcliById";

	/** The Constant FETCH_TIPCLI_NAME_BY_ID. */ 
	private static final String FETCH_TIPCLI_NAME_BY_ID = TIPCLI_NAMESPACE + "fetchTipcliNameById";

	/** The Constant UPDATE_AUTO_TIPCLI_TIPCLI. */ 
	private static final String UPDATE_AUTO_TIPCLI_TIPCLI = TIPCLI_NAMESPACE + "updateAutoTipcliTipcli";

	/** The Constant DELETE_TIPCLI. */ 
	private static final String DELETE_TIPCLI = TIPCLI_NAMESPACE + "deleteTipcli";

	/** The Constant FETCH_TIPCLIS_BY_LIGHT_ID. */ 
	private static final String FETCH_TIPCLIS_BY_LIGHT_ID = TIPCLI_NAMESPACE + "fetchTipclisByLightId";

	/** The Constant FETCH_TIPCLIS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_TIPCLIS_BY_SMART_POINT_ID = TIPCLI_NAMESPACE + "fetchTipclisBySmartPointId";

	/** The Constant INSERT_TIPCLI. */ 
	private static final String INSERT_TIPCLI = TIPCLI_NAMESPACE + "insertTipcli";

	/** The Constant INSERT_SMART_POINT_TO_TIPCLI. */ 
	private static final String INSERT_SMART_POINT_TO_TIPCLI = TIPCLI_NAMESPACE + "insertSmartPointToTipcli";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_TIPCLI = TIPCLI_NAMESPACE + "fetchLightsBelongTipcli";

	/** The Constant FETCH_ALL_TIPCLIS. */ 
	private static final String FETCH_ALL_TIPCLIS = TIPCLI_NAMESPACE + "fetchAllTipclis";

	/** The Constant TIPCLIID. */ 
	private static final String TIPCLI_ID = "tipcliId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant TIPCLINAME. */ 
	private static final String TIPCLINAME = "tipcliname";

	/** The Constant AUTOTIPCLI. */ 
	private static final String AUTOTIPCLI = "autotipcli";

	/** The Constant AUTOTIPCLI. */ 
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
	 * @see com.sensus.mlc.tipcli.dao.ITipcliDAO#fetchAllTipclis(com.sensus.mlc.tipcli.model.request.InquiryTipcliRequest)
	 */ 
	@Override
	public InternalResultsResponse<Tipcli> fetchAllTipcli(InquiryTipcliRequest inquiryTipcliRequest)
	{
		InternalResultsResponse<Tipcli> response = new InternalResultsResponse<Tipcli>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryTipcliRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryTipcliRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryTipcliRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryTipcliRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryTipcliRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryTipcliRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryTipcliRequest.getTipclis()))
		{
			paramMap.put(TIPCLI_ID, inquiryTipcliRequest.getTipclis().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_TIPCLIS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.tipcli.dao.ITipcliDAO#fetchTipcliById(com.sensus.mlc.tipcli.model.Tipcli
	 */ 
	@Override
	public InternalResultsResponse<Tipcli> fetchTipcliByName(TipcliRequest tipcliRequest)
	{ 
		InternalResultsResponse<Tipcli> response = new InternalResultsResponse<Tipcli>();
		doQueryForList(getSqlSession(), FETCH_TIPCLI_BY_ID, tipcliRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tipcli.dao.ITipcliDAO#insertTipcli(com.sensus.mlc.tipcli.model.request.TipcliRequest)
	 */ 
	@Override
	public InternalResultsResponse<Tipcli> insertTipcli(TipcliRequest tipcliRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Tipcli tipcli = tipcliRequest.getTipcli();

		tipcli.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_TIPCLI, tipcliRequest));
   tipcli.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, tipcliRequest));
		InternalResultsResponse<Tipcli> response = new InternalResultsResponse<Tipcli>();
		response.addResult(tipcli);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.tipcli.dao.ITipcliDAO#deleteTipcli(com.sensus.mlc.tipcli.model.request.TipcliRequest)
	 */ 
	@Override
	public InternalResponse deleteTipcli(TipcliRequest tipcliRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_TIPCLI, tipcliRequest.getTipcli(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tipcli.dao.ITipcliDAO#fetchTipcliById(com.sensus.mlc.tipcli.model.request.TipcliRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Tipcli> fetchTipcliById(TipcliRequest tipcliRequest) 
	{
		InternalResultsResponse<Tipcli> response = new InternalResultsResponse<Tipcli>();
		doQueryForList(getSqlSession(), FETCH_TIPCLI_BY_ID, tipcliRequest.getTipcli(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.tipcli.dac.ITipcliDAC#fetchTipcliNameById(com.sensus.mlc.tipcli.model.request.TipcliRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Tipcli> fetchTipcliNameById(TipcliRequest tipcliRequest)
	{
		InternalResultsResponse<Tipcli> response = new InternalResultsResponse<Tipcli>();
		doQueryForList(getSqlSession(), FETCH_TIPCLI_NAME_BY_ID, tipcliRequest.getTipcli(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tipcli.dac.ITipcliDAC#updateTipcli(com.sensus.mlc.tipcli.model.request.TipcliRequest)
	 */ 
	@Override 
	public InternalResponse updateTipcli(TipcliRequest tipcliRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_TIPCLI, tipcliRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_TIPCLIVALIDATOR_TIPCLI_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


