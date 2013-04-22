package com.sensus.mlc.contabil.dac.mybatis;

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
import com.sensus.mlc.lffretevenda.dac.ILffretevendaDAC;
import com.sensus.mlc.lffretevenda.model.Lffretevenda;
import com.sensus.mlc.lffretevenda.model.LffretevendaOrderByEnum;
import com.sensus.mlc.lffretevenda.model.request.InquiryLffretevendaRequest;
import com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest;

/** 
 * The Class LffretevendaDACImpl.
 */ 
public class LffretevendaDACImpl extends SqlSessionDaoSupport implements ILffretevendaDAC
{ 

	/** The Constant LFFRETEVENDA_NAMESPACE. */ 
	private static final String LFFRETEVENDA_NAMESPACE = "Lffretevenda.";

	/** The Constant FETCH_LFFRETEVENDA_BY_ID. */ 
	private static final String FETCH_LFFRETEVENDA_BY_ID = LFFRETEVENDA_NAMESPACE + "fetchLffretevendaById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFFRETEVENDA_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFFRETEVENDA. */ 
	private static final String DELETE_SMART_POINT_FROM_LFFRETEVENDA = LFFRETEVENDA_NAMESPACE + "deleteSmartPointFromLffretevenda";

	/** The Constant FETCH_LFFRETEVENDA_NAME_BY_ID. */ 
	private static final String FETCH_LFFRETEVENDA_NAME_BY_ID = LFFRETEVENDA_NAMESPACE + "fetchLffretevendaNameById";

	/** The Constant UPDATE_AUTO_LFFRETEVENDA_LFFRETEVENDA. */ 
	private static final String UPDATE_AUTO_LFFRETEVENDA_LFFRETEVENDA = LFFRETEVENDA_NAMESPACE + "updateAutoLffretevendaLffretevenda";

	/** The Constant DELETE_LFFRETEVENDA. */ 
	private static final String DELETE_LFFRETEVENDA = LFFRETEVENDA_NAMESPACE + "deleteLffretevenda";

	/** The Constant FETCH_LFFRETEVENDAS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFFRETEVENDAS_BY_LIGHT_ID = LFFRETEVENDA_NAMESPACE + "fetchLffretevendasByLightId";

	/** The Constant FETCH_LFFRETEVENDAS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFFRETEVENDAS_BY_SMART_POINT_ID = LFFRETEVENDA_NAMESPACE + "fetchLffretevendasBySmartPointId";

	/** The Constant INSERT_LFFRETEVENDA. */ 
	private static final String INSERT_LFFRETEVENDA = LFFRETEVENDA_NAMESPACE + "insertLffretevenda";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFFRETEVENDA = LFFRETEVENDA_NAMESPACE + "updateLffretevenda";
private static final String SENSUS_MLC_LFFRETEVENDA_VALIDATOR_LFFRETEVENDA_ALREADY_EXISTS = error.update.lffretevenda;
 
	/** The Constant INSERT_SMART_POINT_TO_LFFRETEVENDA. */ 
	private static final String INSERT_SMART_POINT_TO_LFFRETEVENDA = LFFRETEVENDA_NAMESPACE + "insertSmartPointToLffretevenda";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFFRETEVENDA = LFFRETEVENDA_NAMESPACE + "fetchLightsBelongLffretevenda";

	/** The Constant FETCH_ALL_LFFRETEVENDAS. */ 
	private static final String FETCH_ALL_LFFRETEVENDAS = LFFRETEVENDA_NAMESPACE + "fetchAllLffretevendas";

	/** The Constant LFFRETEVENDAID. */ 
	private static final String LFFRETEVENDA_ID = "lffretevendaId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFFRETEVENDANAME. */ 
	private static final String LFFRETEVENDANAME = "lffretevendaname";

	/** The Constant AUTOLFFRETEVENDA. */ 
	private static final String AUTOLFFRETEVENDA = "autolffretevenda";

	/** The Constant AUTOLFFRETEVENDA. */ 
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
	 * @see com.sensus.mlc.lffretevenda.dao.ILffretevendaDAO#fetchAllLffretevendas(com.sensus.mlc.lffretevenda.model.request.InquiryLffretevendaRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lffretevenda> fetchAllLffretevenda(InquiryLffretevendaRequest inquiryLffretevendaRequest)
	{
		InternalResultsResponse<Lffretevenda> response = new InternalResultsResponse<Lffretevenda>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLffretevendaRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLffretevendaRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLffretevendaRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLffretevendaRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLffretevendaRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLffretevendaRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLffretevendaRequest.getLffretevenda()))
		{
			paramMap.put(LFFRETEVENDA_ID, inquiryLffretevendaRequest.getLffretevenda().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFFRETEVENDAS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lffretevenda.dao.ILffretevendaDAO#insertLffretevenda(com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lffretevenda> insertLffretevenda(LffretevendaRequest lffretevendaRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lffretevenda lffretevenda = lffretevendaRequest.getLffretevenda();

		lffretevenda.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFFRETEVENDA, lffretevendaRequest));
   lffretevenda.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lffretevendaRequest));
		InternalResultsResponse<Lffretevenda> response = new InternalResultsResponse<Lffretevenda>();
		response.addResult(lffretevenda);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lffretevenda.dao.ILffretevendaDAO#deleteLffretevenda(com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest)
	 */ 
	@Override
	public InternalResponse deleteLffretevenda(LffretevendaRequest lffretevendaRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFFRETEVENDA, lffretevendaRequest.getLffretevenda(), response);
   lffretevenda.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lffretevendaRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lffretevenda.dac.ILffretevendaDAC#updateLffretevenda(com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lffretevenda> updateLffretevenda(LffretevendaRequest lffretevendaRequest)
	{
		InternalResultsResponse<Lffretevenda> response = new InternalResultsResponse<Lffretevenda>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFFRETEVENDA, lffretevendaRequest);
   lffretevenda.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lffretevendaRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFFRETEVENDA_VALIDATOR_LFFRETEVENDA_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


