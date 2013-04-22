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
import com.sensus.mlc.lftabicms.txt.dac.ILftabicms.txtDAC;
import com.sensus.mlc.lftabicms.txt.model.Lftabicms.txt;
import com.sensus.mlc.lftabicms.txt.model.Lftabicms.txtOrderByEnum;
import com.sensus.mlc.lftabicms.txt.model.request.InquiryLftabicms.txtRequest;
import com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest;

/** 
 * The Class Lftabicms.txtDACImpl.
 */ 
public class Lftabicms.txtDACImpl extends SqlSessionDaoSupport implements ILftabicms.txtDAC
{ 

	/** The Constant LFTABICMS.TXT_NAMESPACE. */ 
	private static final String LFTABICMS.TXT_NAMESPACE = "Lftabicms.txt.";

	/** The Constant FETCH_LFTABICMS.TXT_BY_ID. */ 
	private static final String FETCH_LFTABICMS.TXT_BY_ID = LFTABICMS.TXT_NAMESPACE + "fetchLftabicms.txtById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFTABICMS.TXT_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFTABICMS.TXT. */ 
	private static final String DELETE_SMART_POINT_FROM_LFTABICMS.TXT = LFTABICMS.TXT_NAMESPACE + "deleteSmartPointFromLftabicms.txt";

	/** The Constant FETCH_LFTABICMS.TXT_NAME_BY_ID. */ 
	private static final String FETCH_LFTABICMS.TXT_NAME_BY_ID = LFTABICMS.TXT_NAMESPACE + "fetchLftabicms.txtNameById";

	/** The Constant UPDATE_AUTO_LFTABICMS.TXT_LFTABICMS.TXT. */ 
	private static final String UPDATE_AUTO_LFTABICMS.TXT_LFTABICMS.TXT = LFTABICMS.TXT_NAMESPACE + "updateAutoLftabicms.txtLftabicms.txt";

	/** The Constant DELETE_LFTABICMS.TXT. */ 
	private static final String DELETE_LFTABICMS.TXT = LFTABICMS.TXT_NAMESPACE + "deleteLftabicms.txt";

	/** The Constant FETCH_LFTABICMS.TXTS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFTABICMS.TXTS_BY_LIGHT_ID = LFTABICMS.TXT_NAMESPACE + "fetchLftabicms.txtsByLightId";

	/** The Constant FETCH_LFTABICMS.TXTS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFTABICMS.TXTS_BY_SMART_POINT_ID = LFTABICMS.TXT_NAMESPACE + "fetchLftabicms.txtsBySmartPointId";

	/** The Constant INSERT_LFTABICMS.TXT. */ 
	private static final String INSERT_LFTABICMS.TXT = LFTABICMS.TXT_NAMESPACE + "insertLftabicms.txt";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFTABICMS.TXT = LFTABICMS.TXT_NAMESPACE + "updateLftabicms.txt";
private static final String SENSUS_MLC_LFTABICMS.TXT_VALIDATOR_LFTABICMS.TXT_ALREADY_EXISTS = error.update.lftabicms.txt;
 
	/** The Constant INSERT_SMART_POINT_TO_LFTABICMS.TXT. */ 
	private static final String INSERT_SMART_POINT_TO_LFTABICMS.TXT = LFTABICMS.TXT_NAMESPACE + "insertSmartPointToLftabicms.txt";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFTABICMS.TXT = LFTABICMS.TXT_NAMESPACE + "fetchLightsBelongLftabicms.txt";

	/** The Constant FETCH_ALL_LFTABICMS.TXTS. */ 
	private static final String FETCH_ALL_LFTABICMS.TXTS = LFTABICMS.TXT_NAMESPACE + "fetchAllLftabicms.txts";

	/** The Constant LFTABICMS.TXTID. */ 
	private static final String LFTABICMS.TXT_ID = "lftabicms.txtId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFTABICMS.TXTNAME. */ 
	private static final String LFTABICMS.TXTNAME = "lftabicms.txtname";

	/** The Constant AUTOLFTABICMS.TXT. */ 
	private static final String AUTOLFTABICMS.TXT = "autolftabicms.txt";

	/** The Constant AUTOLFTABICMS.TXT. */ 
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
	 * @see com.sensus.mlc.lftabicms.txt.dao.ILftabicms.txtDAO#fetchAllLftabicms.txts(com.sensus.mlc.lftabicms.txt.model.request.InquiryLftabicms.txtRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lftabicms.txt> fetchAllLftabicms.txt(InquiryLftabicms.txtRequest inquiryLftabicms.txtRequest)
	{
		InternalResultsResponse<Lftabicms.txt> response = new InternalResultsResponse<Lftabicms.txt>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLftabicms.txtRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLftabicms.txtRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLftabicms.txtRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLftabicms.txtRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLftabicms.txtRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLftabicms.txtRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLftabicms.txtRequest.getLftabicms.txt()))
		{
			paramMap.put(LFTABICMS.TXT_ID, inquiryLftabicms.txtRequest.getLftabicms.txt().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFTABICMS.TXTS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lftabicms.txt.dao.ILftabicms.txtDAO#insertLftabicms.txt(com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lftabicms.txt> insertLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lftabicms.txt lftabicms.txt = lftabicms.txtRequest.getLftabicms.txt();

		lftabicms.txt.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFTABICMS.TXT, lftabicms.txtRequest));
   lftabicms.txt.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lftabicms.txtRequest));
		InternalResultsResponse<Lftabicms.txt> response = new InternalResultsResponse<Lftabicms.txt>();
		response.addResult(lftabicms.txt);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftabicms.txt.dao.ILftabicms.txtDAO#deleteLftabicms.txt(com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest)
	 */ 
	@Override
	public InternalResponse deleteLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFTABICMS.TXT, lftabicms.txtRequest.getLftabicms.txt(), response);
   lftabicms.txt.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lftabicms.txtRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lftabicms.txt.dac.ILftabicms.txtDAC#updateLftabicms.txt(com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lftabicms.txt> updateLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest)
	{
		InternalResultsResponse<Lftabicms.txt> response = new InternalResultsResponse<Lftabicms.txt>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFTABICMS.TXT, lftabicms.txtRequest);
   lftabicms.txt.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lftabicms.txtRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFTABICMS.TXT_VALIDATOR_LFTABICMS.TXT_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


