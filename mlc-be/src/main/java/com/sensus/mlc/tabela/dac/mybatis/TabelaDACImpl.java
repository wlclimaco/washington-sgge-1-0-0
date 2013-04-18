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
import com.sensus.mlc.tabela.dac.ITabelaDAC;
import com.sensus.mlc.tabela.model.Tabela;
import com.sensus.mlc.tabela.model.TabelaOrderByEnum;
import com.sensus.mlc.tabela.model.request.InquiryTabelaRequest;
import com.sensus.mlc.tabela.model.request.TabelaRequest;

/** 
 * The Class TabelaDACImpl.
 */ 
public class TabelaDACImpl extends SqlSessionDaoSupport implements ITabelaDAC
{ 

	/** The Constant TABELA_NAMESPACE. */ 
	private static final String TABELA_NAMESPACE = "Tabela.";

	/** The Constant FETCH_TABELA_BY_ID. */ 
	private static final String FETCH_TABELA_BY_ID = TABELA_NAMESPACE + "fetchTabelaById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = TABELA_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_TABELA. */ 
	private static final String DELETE_SMART_POINT_FROM_TABELA = TABELA_NAMESPACE + "deleteSmartPointFromTabela";

	/** The Constant FETCH_TABELA_NAME_BY_ID. */ 
	private static final String FETCH_TABELA_NAME_BY_ID = TABELA_NAMESPACE + "fetchTabelaNameById";

	/** The Constant UPDATE_AUTO_TABELA_TABELA. */ 
	private static final String UPDATE_AUTO_TABELA_TABELA = TABELA_NAMESPACE + "updateAutoTabelaTabela";

	/** The Constant DELETE_TABELA. */ 
	private static final String DELETE_TABELA = TABELA_NAMESPACE + "deleteTabela";

	/** The Constant FETCH_TABELAS_BY_LIGHT_ID. */ 
	private static final String FETCH_TABELAS_BY_LIGHT_ID = TABELA_NAMESPACE + "fetchTabelasByLightId";

	/** The Constant FETCH_TABELAS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_TABELAS_BY_SMART_POINT_ID = TABELA_NAMESPACE + "fetchTabelasBySmartPointId";

	/** The Constant INSERT_TABELA. */ 
	private static final String INSERT_TABELA = TABELA_NAMESPACE + "insertTabela";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_TABELA = TABELA_NAMESPACE + "updateTabela";
private static final String SENSUS_MLC_TABELA_VALIDATOR_TABELA_ALREADY_EXISTS = error.update.tabela;
 
	/** The Constant INSERT_SMART_POINT_TO_TABELA. */ 
	private static final String INSERT_SMART_POINT_TO_TABELA = TABELA_NAMESPACE + "insertSmartPointToTabela";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_TABELA = TABELA_NAMESPACE + "fetchLightsBelongTabela";

	/** The Constant FETCH_ALL_TABELAS. */ 
	private static final String FETCH_ALL_TABELAS = TABELA_NAMESPACE + "fetchAllTabelas";

	/** The Constant TABELAID. */ 
	private static final String TABELA_ID = "tabelaId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant TABELANAME. */ 
	private static final String TABELANAME = "tabelaname";

	/** The Constant AUTOTABELA. */ 
	private static final String AUTOTABELA = "autotabela";

	/** The Constant AUTOTABELA. */ 
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
	 * @see com.sensus.mlc.tabela.dao.ITabelaDAO#fetchAllTabelas(com.sensus.mlc.tabela.model.request.InquiryTabelaRequest)
	 */ 
	@Override
	public InternalResultsResponse<Tabela> fetchAllTabela(InquiryTabelaRequest inquiryTabelaRequest)
	{
		InternalResultsResponse<Tabela> response = new InternalResultsResponse<Tabela>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryTabelaRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryTabelaRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryTabelaRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryTabelaRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryTabelaRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryTabelaRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryTabelaRequest.getTabela()))
		{
			paramMap.put(TABELA_ID, inquiryTabelaRequest.getTabela().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_TABELAS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tabela.dao.ITabelaDAO#insertTabela(com.sensus.mlc.tabela.model.request.TabelaRequest)
	 */ 
	@Override
	public InternalResultsResponse<Tabela> insertTabela(TabelaRequest tabelaRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Tabela tabela = tabelaRequest.getTabela();

		tabela.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_TABELA, tabelaRequest));
   tabela.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, tabelaRequest));
		InternalResultsResponse<Tabela> response = new InternalResultsResponse<Tabela>();
		response.addResult(tabela);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.tabela.dao.ITabelaDAO#deleteTabela(com.sensus.mlc.tabela.model.request.TabelaRequest)
	 */ 
	@Override
	public InternalResponse deleteTabela(TabelaRequest tabelaRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_TABELA, tabelaRequest.getTabela(), response);
   tabela.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , tabelaRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tabela.dac.ITabelaDAC#updateTabela(com.sensus.mlc.tabela.model.request.TabelaRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Tabela> updateTabela(TabelaRequest tabelaRequest)
	{
		InternalResultsResponse<Tabela> response = new InternalResultsResponse<Tabela>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_TABELA, tabelaRequest);
   tabela.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, tabelaRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_TABELA_VALIDATOR_TABELA_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


