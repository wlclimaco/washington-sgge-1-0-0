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
import com.sensus.mlc.lfitcalccusto.dac.ILfitcalccustoDAC;
import com.sensus.mlc.lfitcalccusto.model.Lfitcalccusto;
import com.sensus.mlc.lfitcalccusto.model.LfitcalccustoOrderByEnum;
import com.sensus.mlc.lfitcalccusto.model.request.InquiryLfitcalccustoRequest;
import com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest;

/** 
 * The Class LfitcalccustoDACImpl.
 */ 
public class LfitcalccustoDACImpl extends SqlSessionDaoSupport implements ILfitcalccustoDAC
{ 

	/** The Constant LFITCALCCUSTO_NAMESPACE. */ 
	private static final String LFITCALCCUSTO_NAMESPACE = "Lfitcalccusto.";

	/** The Constant FETCH_LFITCALCCUSTO_BY_ID. */ 
	private static final String FETCH_LFITCALCCUSTO_BY_ID = LFITCALCCUSTO_NAMESPACE + "fetchLfitcalccustoById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFITCALCCUSTO_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFITCALCCUSTO. */ 
	private static final String DELETE_SMART_POINT_FROM_LFITCALCCUSTO = LFITCALCCUSTO_NAMESPACE + "deleteSmartPointFromLfitcalccusto";

	/** The Constant FETCH_LFITCALCCUSTO_NAME_BY_ID. */ 
	private static final String FETCH_LFITCALCCUSTO_NAME_BY_ID = LFITCALCCUSTO_NAMESPACE + "fetchLfitcalccustoNameById";

	/** The Constant UPDATE_AUTO_LFITCALCCUSTO_LFITCALCCUSTO. */ 
	private static final String UPDATE_AUTO_LFITCALCCUSTO_LFITCALCCUSTO = LFITCALCCUSTO_NAMESPACE + "updateAutoLfitcalccustoLfitcalccusto";

	/** The Constant DELETE_LFITCALCCUSTO. */ 
	private static final String DELETE_LFITCALCCUSTO = LFITCALCCUSTO_NAMESPACE + "deleteLfitcalccusto";

	/** The Constant FETCH_LFITCALCCUSTOS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFITCALCCUSTOS_BY_LIGHT_ID = LFITCALCCUSTO_NAMESPACE + "fetchLfitcalccustosByLightId";

	/** The Constant FETCH_LFITCALCCUSTOS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFITCALCCUSTOS_BY_SMART_POINT_ID = LFITCALCCUSTO_NAMESPACE + "fetchLfitcalccustosBySmartPointId";

	/** The Constant INSERT_LFITCALCCUSTO. */ 
	private static final String INSERT_LFITCALCCUSTO = LFITCALCCUSTO_NAMESPACE + "insertLfitcalccusto";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFITCALCCUSTO = LFITCALCCUSTO_NAMESPACE + "updateLfitcalccusto";
private static final String SENSUS_MLC_LFITCALCCUSTO_VALIDATOR_LFITCALCCUSTO_ALREADY_EXISTS = error.update.lfitcalccusto;
 
	/** The Constant INSERT_SMART_POINT_TO_LFITCALCCUSTO. */ 
	private static final String INSERT_SMART_POINT_TO_LFITCALCCUSTO = LFITCALCCUSTO_NAMESPACE + "insertSmartPointToLfitcalccusto";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFITCALCCUSTO = LFITCALCCUSTO_NAMESPACE + "fetchLightsBelongLfitcalccusto";

	/** The Constant FETCH_ALL_LFITCALCCUSTOS. */ 
	private static final String FETCH_ALL_LFITCALCCUSTOS = LFITCALCCUSTO_NAMESPACE + "fetchAllLfitcalccustos";

	/** The Constant LFITCALCCUSTOID. */ 
	private static final String LFITCALCCUSTO_ID = "lfitcalccustoId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFITCALCCUSTONAME. */ 
	private static final String LFITCALCCUSTONAME = "lfitcalccustoname";

	/** The Constant AUTOLFITCALCCUSTO. */ 
	private static final String AUTOLFITCALCCUSTO = "autolfitcalccusto";

	/** The Constant AUTOLFITCALCCUSTO. */ 
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
	 * @see com.sensus.mlc.lfitcalccusto.dao.ILfitcalccustoDAO#fetchAllLfitcalccustos(com.sensus.mlc.lfitcalccusto.model.request.InquiryLfitcalccustoRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfitcalccusto> fetchAllLfitcalccusto(InquiryLfitcalccustoRequest inquiryLfitcalccustoRequest)
	{
		InternalResultsResponse<Lfitcalccusto> response = new InternalResultsResponse<Lfitcalccusto>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfitcalccustoRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfitcalccustoRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfitcalccustoRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfitcalccustoRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfitcalccustoRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfitcalccustoRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfitcalccustoRequest.getLfitcalccusto()))
		{
			paramMap.put(LFITCALCCUSTO_ID, inquiryLfitcalccustoRequest.getLfitcalccusto().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFITCALCCUSTOS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfitcalccusto.dao.ILfitcalccustoDAO#insertLfitcalccusto(com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfitcalccusto> insertLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfitcalccusto lfitcalccusto = lfitcalccustoRequest.getLfitcalccusto();

		lfitcalccusto.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFITCALCCUSTO, lfitcalccustoRequest));
   lfitcalccusto.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfitcalccustoRequest));
		InternalResultsResponse<Lfitcalccusto> response = new InternalResultsResponse<Lfitcalccusto>();
		response.addResult(lfitcalccusto);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitcalccusto.dao.ILfitcalccustoDAO#deleteLfitcalccusto(com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest)
	 */ 
	@Override
	public InternalResponse deleteLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFITCALCCUSTO, lfitcalccustoRequest.getLfitcalccusto(), response);
   lfitcalccusto.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfitcalccustoRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfitcalccusto.dac.ILfitcalccustoDAC#updateLfitcalccusto(com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfitcalccusto> updateLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest)
	{
		InternalResultsResponse<Lfitcalccusto> response = new InternalResultsResponse<Lfitcalccusto>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFITCALCCUSTO, lfitcalccustoRequest);
   lfitcalccusto.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfitcalccustoRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFITCALCCUSTO_VALIDATOR_LFITCALCCUSTO_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


