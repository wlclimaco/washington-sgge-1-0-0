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
import com.sensus.mlc.lfmodnota.dac.ILfmodnotaDAC;
import com.sensus.mlc.lfmodnota.model.Lfmodnota;
import com.sensus.mlc.lfmodnota.model.LfmodnotaOrderByEnum;
import com.sensus.mlc.lfmodnota.model.request.InquiryLfmodnotaRequest;
import com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest;

/** 
 * The Class LfmodnotaDACImpl.
 */ 
public class LfmodnotaDACImpl extends SqlSessionDaoSupport implements ILfmodnotaDAC
{ 

	/** The Constant LFMODNOTA_NAMESPACE. */ 
	private static final String LFMODNOTA_NAMESPACE = "Lfmodnota.";

	/** The Constant FETCH_LFMODNOTA_BY_ID. */ 
	private static final String FETCH_LFMODNOTA_BY_ID = LFMODNOTA_NAMESPACE + "fetchLfmodnotaById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFMODNOTA_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFMODNOTA. */ 
	private static final String DELETE_SMART_POINT_FROM_LFMODNOTA = LFMODNOTA_NAMESPACE + "deleteSmartPointFromLfmodnota";

	/** The Constant FETCH_LFMODNOTA_NAME_BY_ID. */ 
	private static final String FETCH_LFMODNOTA_NAME_BY_ID = LFMODNOTA_NAMESPACE + "fetchLfmodnotaNameById";

	/** The Constant UPDATE_AUTO_LFMODNOTA_LFMODNOTA. */ 
	private static final String UPDATE_AUTO_LFMODNOTA_LFMODNOTA = LFMODNOTA_NAMESPACE + "updateAutoLfmodnotaLfmodnota";

	/** The Constant DELETE_LFMODNOTA. */ 
	private static final String DELETE_LFMODNOTA = LFMODNOTA_NAMESPACE + "deleteLfmodnota";

	/** The Constant FETCH_LFMODNOTAS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFMODNOTAS_BY_LIGHT_ID = LFMODNOTA_NAMESPACE + "fetchLfmodnotasByLightId";

	/** The Constant FETCH_LFMODNOTAS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFMODNOTAS_BY_SMART_POINT_ID = LFMODNOTA_NAMESPACE + "fetchLfmodnotasBySmartPointId";

	/** The Constant INSERT_LFMODNOTA. */ 
	private static final String INSERT_LFMODNOTA = LFMODNOTA_NAMESPACE + "insertLfmodnota";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFMODNOTA = LFMODNOTA_NAMESPACE + "updateLfmodnota";
private static final String SENSUS_MLC_LFMODNOTA_VALIDATOR_LFMODNOTA_ALREADY_EXISTS = error.update.lfmodnota;
 
	/** The Constant INSERT_SMART_POINT_TO_LFMODNOTA. */ 
	private static final String INSERT_SMART_POINT_TO_LFMODNOTA = LFMODNOTA_NAMESPACE + "insertSmartPointToLfmodnota";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFMODNOTA = LFMODNOTA_NAMESPACE + "fetchLightsBelongLfmodnota";

	/** The Constant FETCH_ALL_LFMODNOTAS. */ 
	private static final String FETCH_ALL_LFMODNOTAS = LFMODNOTA_NAMESPACE + "fetchAllLfmodnotas";

	/** The Constant LFMODNOTAID. */ 
	private static final String LFMODNOTA_ID = "lfmodnotaId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFMODNOTANAME. */ 
	private static final String LFMODNOTANAME = "lfmodnotaname";

	/** The Constant AUTOLFMODNOTA. */ 
	private static final String AUTOLFMODNOTA = "autolfmodnota";

	/** The Constant AUTOLFMODNOTA. */ 
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
	 * @see com.sensus.mlc.lfmodnota.dao.ILfmodnotaDAO#fetchAllLfmodnotas(com.sensus.mlc.lfmodnota.model.request.InquiryLfmodnotaRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfmodnota> fetchAllLfmodnota(InquiryLfmodnotaRequest inquiryLfmodnotaRequest)
	{
		InternalResultsResponse<Lfmodnota> response = new InternalResultsResponse<Lfmodnota>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfmodnotaRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfmodnotaRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfmodnotaRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfmodnotaRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfmodnotaRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfmodnotaRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfmodnotaRequest.getLfmodnota()))
		{
			paramMap.put(LFMODNOTA_ID, inquiryLfmodnotaRequest.getLfmodnota().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFMODNOTAS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfmodnota.dao.ILfmodnotaDAO#insertLfmodnota(com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfmodnota> insertLfmodnota(LfmodnotaRequest lfmodnotaRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfmodnota lfmodnota = lfmodnotaRequest.getLfmodnota();

		lfmodnota.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFMODNOTA, lfmodnotaRequest));
   lfmodnota.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfmodnotaRequest));
		InternalResultsResponse<Lfmodnota> response = new InternalResultsResponse<Lfmodnota>();
		response.addResult(lfmodnota);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmodnota.dao.ILfmodnotaDAO#deleteLfmodnota(com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest)
	 */ 
	@Override
	public InternalResponse deleteLfmodnota(LfmodnotaRequest lfmodnotaRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFMODNOTA, lfmodnotaRequest.getLfmodnota(), response);
   lfmodnota.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfmodnotaRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfmodnota.dac.ILfmodnotaDAC#updateLfmodnota(com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfmodnota> updateLfmodnota(LfmodnotaRequest lfmodnotaRequest)
	{
		InternalResultsResponse<Lfmodnota> response = new InternalResultsResponse<Lfmodnota>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFMODNOTA, lfmodnotaRequest);
   lfmodnota.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfmodnotaRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFMODNOTA_VALIDATOR_LFMODNOTA_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


