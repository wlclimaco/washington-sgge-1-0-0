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
import com.sensus.mlc.lfmensagem.dac.ILfmensagemDAC;
import com.sensus.mlc.lfmensagem.model.Lfmensagem;
import com.sensus.mlc.lfmensagem.model.LfmensagemOrderByEnum;
import com.sensus.mlc.lfmensagem.model.request.InquiryLfmensagemRequest;
import com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest;

/** 
 * The Class LfmensagemDACImpl.
 */ 
public class LfmensagemDACImpl extends SqlSessionDaoSupport implements ILfmensagemDAC
{ 

	/** The Constant LFMENSAGEM_NAMESPACE. */ 
	private static final String LFMENSAGEM_NAMESPACE = "Lfmensagem.";

	/** The Constant FETCH_LFMENSAGEM_BY_ID. */ 
	private static final String FETCH_LFMENSAGEM_BY_ID = LFMENSAGEM_NAMESPACE + "fetchLfmensagemById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFMENSAGEM_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFMENSAGEM. */ 
	private static final String DELETE_SMART_POINT_FROM_LFMENSAGEM = LFMENSAGEM_NAMESPACE + "deleteSmartPointFromLfmensagem";

	/** The Constant FETCH_LFMENSAGEM_NAME_BY_ID. */ 
	private static final String FETCH_LFMENSAGEM_NAME_BY_ID = LFMENSAGEM_NAMESPACE + "fetchLfmensagemNameById";

	/** The Constant UPDATE_AUTO_LFMENSAGEM_LFMENSAGEM. */ 
	private static final String UPDATE_AUTO_LFMENSAGEM_LFMENSAGEM = LFMENSAGEM_NAMESPACE + "updateAutoLfmensagemLfmensagem";

	/** The Constant DELETE_LFMENSAGEM. */ 
	private static final String DELETE_LFMENSAGEM = LFMENSAGEM_NAMESPACE + "deleteLfmensagem";

	/** The Constant FETCH_LFMENSAGEMS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFMENSAGEMS_BY_LIGHT_ID = LFMENSAGEM_NAMESPACE + "fetchLfmensagemsByLightId";

	/** The Constant FETCH_LFMENSAGEMS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFMENSAGEMS_BY_SMART_POINT_ID = LFMENSAGEM_NAMESPACE + "fetchLfmensagemsBySmartPointId";

	/** The Constant INSERT_LFMENSAGEM. */ 
	private static final String INSERT_LFMENSAGEM = LFMENSAGEM_NAMESPACE + "insertLfmensagem";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFMENSAGEM = LFMENSAGEM_NAMESPACE + "updateLfmensagem";
private static final String SENSUS_MLC_LFMENSAGEM_VALIDATOR_LFMENSAGEM_ALREADY_EXISTS = error.update.lfmensagem;
 
	/** The Constant INSERT_SMART_POINT_TO_LFMENSAGEM. */ 
	private static final String INSERT_SMART_POINT_TO_LFMENSAGEM = LFMENSAGEM_NAMESPACE + "insertSmartPointToLfmensagem";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFMENSAGEM = LFMENSAGEM_NAMESPACE + "fetchLightsBelongLfmensagem";

	/** The Constant FETCH_ALL_LFMENSAGEMS. */ 
	private static final String FETCH_ALL_LFMENSAGEMS = LFMENSAGEM_NAMESPACE + "fetchAllLfmensagems";

	/** The Constant LFMENSAGEMID. */ 
	private static final String LFMENSAGEM_ID = "lfmensagemId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFMENSAGEMNAME. */ 
	private static final String LFMENSAGEMNAME = "lfmensagemname";

	/** The Constant AUTOLFMENSAGEM. */ 
	private static final String AUTOLFMENSAGEM = "autolfmensagem";

	/** The Constant AUTOLFMENSAGEM. */ 
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
	 * @see com.sensus.mlc.lfmensagem.dao.ILfmensagemDAO#fetchAllLfmensagems(com.sensus.mlc.lfmensagem.model.request.InquiryLfmensagemRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfmensagem> fetchAllLfmensagem(InquiryLfmensagemRequest inquiryLfmensagemRequest)
	{
		InternalResultsResponse<Lfmensagem> response = new InternalResultsResponse<Lfmensagem>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfmensagemRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfmensagemRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfmensagemRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfmensagemRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfmensagemRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfmensagemRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfmensagemRequest.getLfmensagem()))
		{
			paramMap.put(LFMENSAGEM_ID, inquiryLfmensagemRequest.getLfmensagem().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFMENSAGEMS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfmensagem.dao.ILfmensagemDAO#insertLfmensagem(com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfmensagem> insertLfmensagem(LfmensagemRequest lfmensagemRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfmensagem lfmensagem = lfmensagemRequest.getLfmensagem();

		lfmensagem.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFMENSAGEM, lfmensagemRequest));
   lfmensagem.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfmensagemRequest));
		InternalResultsResponse<Lfmensagem> response = new InternalResultsResponse<Lfmensagem>();
		response.addResult(lfmensagem);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfmensagem.dao.ILfmensagemDAO#deleteLfmensagem(com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest)
	 */ 
	@Override
	public InternalResponse deleteLfmensagem(LfmensagemRequest lfmensagemRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFMENSAGEM, lfmensagemRequest.getLfmensagem(), response);
   lfmensagem.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfmensagemRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfmensagem.dac.ILfmensagemDAC#updateLfmensagem(com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfmensagem> updateLfmensagem(LfmensagemRequest lfmensagemRequest)
	{
		InternalResultsResponse<Lfmensagem> response = new InternalResultsResponse<Lfmensagem>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFMENSAGEM, lfmensagemRequest);
   lfmensagem.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfmensagemRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFMENSAGEM_VALIDATOR_LFMENSAGEM_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


