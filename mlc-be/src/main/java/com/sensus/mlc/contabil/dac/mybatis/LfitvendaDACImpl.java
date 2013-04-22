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
import com.sensus.mlc.lfitvenda.dac.ILfitvendaDAC;
import com.sensus.mlc.lfitvenda.model.Lfitvenda;
import com.sensus.mlc.lfitvenda.model.LfitvendaOrderByEnum;
import com.sensus.mlc.lfitvenda.model.request.InquiryLfitvendaRequest;
import com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest;

/** 
 * The Class LfitvendaDACImpl.
 */ 
public class LfitvendaDACImpl extends SqlSessionDaoSupport implements ILfitvendaDAC
{ 

	/** The Constant LFITVENDA_NAMESPACE. */ 
	private static final String LFITVENDA_NAMESPACE = "Lfitvenda.";

	/** The Constant FETCH_LFITVENDA_BY_ID. */ 
	private static final String FETCH_LFITVENDA_BY_ID = LFITVENDA_NAMESPACE + "fetchLfitvendaById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFITVENDA_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFITVENDA. */ 
	private static final String DELETE_SMART_POINT_FROM_LFITVENDA = LFITVENDA_NAMESPACE + "deleteSmartPointFromLfitvenda";

	/** The Constant FETCH_LFITVENDA_NAME_BY_ID. */ 
	private static final String FETCH_LFITVENDA_NAME_BY_ID = LFITVENDA_NAMESPACE + "fetchLfitvendaNameById";

	/** The Constant UPDATE_AUTO_LFITVENDA_LFITVENDA. */ 
	private static final String UPDATE_AUTO_LFITVENDA_LFITVENDA = LFITVENDA_NAMESPACE + "updateAutoLfitvendaLfitvenda";

	/** The Constant DELETE_LFITVENDA. */ 
	private static final String DELETE_LFITVENDA = LFITVENDA_NAMESPACE + "deleteLfitvenda";

	/** The Constant FETCH_LFITVENDAS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFITVENDAS_BY_LIGHT_ID = LFITVENDA_NAMESPACE + "fetchLfitvendasByLightId";

	/** The Constant FETCH_LFITVENDAS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFITVENDAS_BY_SMART_POINT_ID = LFITVENDA_NAMESPACE + "fetchLfitvendasBySmartPointId";

	/** The Constant INSERT_LFITVENDA. */ 
	private static final String INSERT_LFITVENDA = LFITVENDA_NAMESPACE + "insertLfitvenda";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFITVENDA = LFITVENDA_NAMESPACE + "updateLfitvenda";
private static final String SENSUS_MLC_LFITVENDA_VALIDATOR_LFITVENDA_ALREADY_EXISTS = error.update.lfitvenda;
 
	/** The Constant INSERT_SMART_POINT_TO_LFITVENDA. */ 
	private static final String INSERT_SMART_POINT_TO_LFITVENDA = LFITVENDA_NAMESPACE + "insertSmartPointToLfitvenda";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFITVENDA = LFITVENDA_NAMESPACE + "fetchLightsBelongLfitvenda";

	/** The Constant FETCH_ALL_LFITVENDAS. */ 
	private static final String FETCH_ALL_LFITVENDAS = LFITVENDA_NAMESPACE + "fetchAllLfitvendas";

	/** The Constant LFITVENDAID. */ 
	private static final String LFITVENDA_ID = "lfitvendaId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFITVENDANAME. */ 
	private static final String LFITVENDANAME = "lfitvendaname";

	/** The Constant AUTOLFITVENDA. */ 
	private static final String AUTOLFITVENDA = "autolfitvenda";

	/** The Constant AUTOLFITVENDA. */ 
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
	 * @see com.sensus.mlc.lfitvenda.dao.ILfitvendaDAO#fetchAllLfitvendas(com.sensus.mlc.lfitvenda.model.request.InquiryLfitvendaRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfitvenda> fetchAllLfitvenda(InquiryLfitvendaRequest inquiryLfitvendaRequest)
	{
		InternalResultsResponse<Lfitvenda> response = new InternalResultsResponse<Lfitvenda>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfitvendaRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfitvendaRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfitvendaRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfitvendaRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfitvendaRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfitvendaRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfitvendaRequest.getLfitvenda()))
		{
			paramMap.put(LFITVENDA_ID, inquiryLfitvendaRequest.getLfitvenda().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFITVENDAS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfitvenda.dao.ILfitvendaDAO#insertLfitvenda(com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfitvenda> insertLfitvenda(LfitvendaRequest lfitvendaRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfitvenda lfitvenda = lfitvendaRequest.getLfitvenda();

		lfitvenda.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFITVENDA, lfitvendaRequest));
   lfitvenda.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfitvendaRequest));
		InternalResultsResponse<Lfitvenda> response = new InternalResultsResponse<Lfitvenda>();
		response.addResult(lfitvenda);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfitvenda.dao.ILfitvendaDAO#deleteLfitvenda(com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest)
	 */ 
	@Override
	public InternalResponse deleteLfitvenda(LfitvendaRequest lfitvendaRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFITVENDA, lfitvendaRequest.getLfitvenda(), response);
   lfitvenda.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfitvendaRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfitvenda.dac.ILfitvendaDAC#updateLfitvenda(com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfitvenda> updateLfitvenda(LfitvendaRequest lfitvendaRequest)
	{
		InternalResultsResponse<Lfitvenda> response = new InternalResultsResponse<Lfitvenda>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFITVENDA, lfitvendaRequest);
   lfitvenda.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfitvendaRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFITVENDA_VALIDATOR_LFITVENDA_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


