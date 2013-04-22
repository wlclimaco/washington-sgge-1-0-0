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
import com.sensus.mlc.lftrattrib.dac.ILftrattribDAC;
import com.sensus.mlc.lftrattrib.model.Lftrattrib;
import com.sensus.mlc.lftrattrib.model.LftrattribOrderByEnum;
import com.sensus.mlc.lftrattrib.model.request.InquiryLftrattribRequest;
import com.sensus.mlc.lftrattrib.model.request.LftrattribRequest;

/** 
 * The Class LftrattribDACImpl.
 */ 
public class LftrattribDACImpl extends SqlSessionDaoSupport implements ILftrattribDAC
{ 

	/** The Constant LFTRATTRIB_NAMESPACE. */ 
	private static final String LFTRATTRIB_NAMESPACE = "Lftrattrib.";

	/** The Constant FETCH_LFTRATTRIB_BY_ID. */ 
	private static final String FETCH_LFTRATTRIB_BY_ID = LFTRATTRIB_NAMESPACE + "fetchLftrattribById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFTRATTRIB_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFTRATTRIB. */ 
	private static final String DELETE_SMART_POINT_FROM_LFTRATTRIB = LFTRATTRIB_NAMESPACE + "deleteSmartPointFromLftrattrib";

	/** The Constant FETCH_LFTRATTRIB_NAME_BY_ID. */ 
	private static final String FETCH_LFTRATTRIB_NAME_BY_ID = LFTRATTRIB_NAMESPACE + "fetchLftrattribNameById";

	/** The Constant UPDATE_AUTO_LFTRATTRIB_LFTRATTRIB. */ 
	private static final String UPDATE_AUTO_LFTRATTRIB_LFTRATTRIB = LFTRATTRIB_NAMESPACE + "updateAutoLftrattribLftrattrib";

	/** The Constant DELETE_LFTRATTRIB. */ 
	private static final String DELETE_LFTRATTRIB = LFTRATTRIB_NAMESPACE + "deleteLftrattrib";

	/** The Constant FETCH_LFTRATTRIBS_BY_LIGHT_ID. */ 
	private static final String FETCH_LFTRATTRIBS_BY_LIGHT_ID = LFTRATTRIB_NAMESPACE + "fetchLftrattribsByLightId";

	/** The Constant FETCH_LFTRATTRIBS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFTRATTRIBS_BY_SMART_POINT_ID = LFTRATTRIB_NAMESPACE + "fetchLftrattribsBySmartPointId";

	/** The Constant INSERT_LFTRATTRIB. */ 
	private static final String INSERT_LFTRATTRIB = LFTRATTRIB_NAMESPACE + "insertLftrattrib";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFTRATTRIB = LFTRATTRIB_NAMESPACE + "updateLftrattrib";
private static final String SENSUS_MLC_LFTRATTRIB_VALIDATOR_LFTRATTRIB_ALREADY_EXISTS = error.update.lftrattrib;
 
	/** The Constant INSERT_SMART_POINT_TO_LFTRATTRIB. */ 
	private static final String INSERT_SMART_POINT_TO_LFTRATTRIB = LFTRATTRIB_NAMESPACE + "insertSmartPointToLftrattrib";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFTRATTRIB = LFTRATTRIB_NAMESPACE + "fetchLightsBelongLftrattrib";

	/** The Constant FETCH_ALL_LFTRATTRIBS. */ 
	private static final String FETCH_ALL_LFTRATTRIBS = LFTRATTRIB_NAMESPACE + "fetchAllLftrattribs";

	/** The Constant LFTRATTRIBID. */ 
	private static final String LFTRATTRIB_ID = "lftrattribId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFTRATTRIBNAME. */ 
	private static final String LFTRATTRIBNAME = "lftrattribname";

	/** The Constant AUTOLFTRATTRIB. */ 
	private static final String AUTOLFTRATTRIB = "autolftrattrib";

	/** The Constant AUTOLFTRATTRIB. */ 
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
	 * @see com.sensus.mlc.lftrattrib.dao.ILftrattribDAO#fetchAllLftrattribs(com.sensus.mlc.lftrattrib.model.request.InquiryLftrattribRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lftrattrib> fetchAllLftrattrib(InquiryLftrattribRequest inquiryLftrattribRequest)
	{
		InternalResultsResponse<Lftrattrib> response = new InternalResultsResponse<Lftrattrib>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLftrattribRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLftrattribRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLftrattribRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLftrattribRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLftrattribRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLftrattribRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLftrattribRequest.getLftrattrib()))
		{
			paramMap.put(LFTRATTRIB_ID, inquiryLftrattribRequest.getLftrattrib().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFTRATTRIBS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lftrattrib.dao.ILftrattribDAO#insertLftrattrib(com.sensus.mlc.lftrattrib.model.request.LftrattribRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lftrattrib> insertLftrattrib(LftrattribRequest lftrattribRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lftrattrib lftrattrib = lftrattribRequest.getLftrattrib();

		lftrattrib.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFTRATTRIB, lftrattribRequest));
   lftrattrib.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lftrattribRequest));
		InternalResultsResponse<Lftrattrib> response = new InternalResultsResponse<Lftrattrib>();
		response.addResult(lftrattrib);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lftrattrib.dao.ILftrattribDAO#deleteLftrattrib(com.sensus.mlc.lftrattrib.model.request.LftrattribRequest)
	 */ 
	@Override
	public InternalResponse deleteLftrattrib(LftrattribRequest lftrattribRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFTRATTRIB, lftrattribRequest.getLftrattrib(), response);
   lftrattrib.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lftrattribRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lftrattrib.dac.ILftrattribDAC#updateLftrattrib(com.sensus.mlc.lftrattrib.model.request.LftrattribRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lftrattrib> updateLftrattrib(LftrattribRequest lftrattribRequest)
	{
		InternalResultsResponse<Lftrattrib> response = new InternalResultsResponse<Lftrattrib>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFTRATTRIB, lftrattribRequest);
   lftrattrib.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lftrattribRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFTRATTRIB_VALIDATOR_LFTRATTRIB_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


