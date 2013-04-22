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
import com.sensus.mlc.lfseqserie.dac.ILfseqserieDAC;
import com.sensus.mlc.lfseqserie.model.Lfseqserie;
import com.sensus.mlc.lfseqserie.model.LfseqserieOrderByEnum;
import com.sensus.mlc.lfseqserie.model.request.InquiryLfseqserieRequest;
import com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest;

/** 
 * The Class LfseqserieDACImpl.
 */ 
public class LfseqserieDACImpl extends SqlSessionDaoSupport implements ILfseqserieDAC
{ 

	/** The Constant LFSEQSERIE_NAMESPACE. */ 
	private static final String LFSEQSERIE_NAMESPACE = "Lfseqserie.";

	/** The Constant FETCH_LFSEQSERIE_BY_ID. */ 
	private static final String FETCH_LFSEQSERIE_BY_ID = LFSEQSERIE_NAMESPACE + "fetchLfseqserieById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = LFSEQSERIE_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_LFSEQSERIE. */ 
	private static final String DELETE_SMART_POINT_FROM_LFSEQSERIE = LFSEQSERIE_NAMESPACE + "deleteSmartPointFromLfseqserie";

	/** The Constant FETCH_LFSEQSERIE_NAME_BY_ID. */ 
	private static final String FETCH_LFSEQSERIE_NAME_BY_ID = LFSEQSERIE_NAMESPACE + "fetchLfseqserieNameById";

	/** The Constant UPDATE_AUTO_LFSEQSERIE_LFSEQSERIE. */ 
	private static final String UPDATE_AUTO_LFSEQSERIE_LFSEQSERIE = LFSEQSERIE_NAMESPACE + "updateAutoLfseqserieLfseqserie";

	/** The Constant DELETE_LFSEQSERIE. */ 
	private static final String DELETE_LFSEQSERIE = LFSEQSERIE_NAMESPACE + "deleteLfseqserie";

	/** The Constant FETCH_LFSEQSERIES_BY_LIGHT_ID. */ 
	private static final String FETCH_LFSEQSERIES_BY_LIGHT_ID = LFSEQSERIE_NAMESPACE + "fetchLfseqseriesByLightId";

	/** The Constant FETCH_LFSEQSERIES_BY_SMART_POINT_ID. */ 
	private static final String FETCH_LFSEQSERIES_BY_SMART_POINT_ID = LFSEQSERIE_NAMESPACE + "fetchLfseqseriesBySmartPointId";

	/** The Constant INSERT_LFSEQSERIE. */ 
	private static final String INSERT_LFSEQSERIE = LFSEQSERIE_NAMESPACE + "insertLfseqserie";

private static final String INSERT_AUDITORIA = Auditoria.insertAuditoria;
private static final String UPDATE_LFSEQSERIE = LFSEQSERIE_NAMESPACE + "updateLfseqserie";
private static final String SENSUS_MLC_LFSEQSERIE_VALIDATOR_LFSEQSERIE_ALREADY_EXISTS = error.update.lfseqserie;
 
	/** The Constant INSERT_SMART_POINT_TO_LFSEQSERIE. */ 
	private static final String INSERT_SMART_POINT_TO_LFSEQSERIE = LFSEQSERIE_NAMESPACE + "insertSmartPointToLfseqserie";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_LFSEQSERIE = LFSEQSERIE_NAMESPACE + "fetchLightsBelongLfseqserie";

	/** The Constant FETCH_ALL_LFSEQSERIES. */ 
	private static final String FETCH_ALL_LFSEQSERIES = LFSEQSERIE_NAMESPACE + "fetchAllLfseqseries";

	/** The Constant LFSEQSERIEID. */ 
	private static final String LFSEQSERIE_ID = "lfseqserieId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant LFSEQSERIENAME. */ 
	private static final String LFSEQSERIENAME = "lfseqseriename";

	/** The Constant AUTOLFSEQSERIE. */ 
	private static final String AUTOLFSEQSERIE = "autolfseqserie";

	/** The Constant AUTOLFSEQSERIE. */ 
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
	 * @see com.sensus.mlc.lfseqserie.dao.ILfseqserieDAO#fetchAllLfseqseries(com.sensus.mlc.lfseqserie.model.request.InquiryLfseqserieRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfseqserie> fetchAllLfseqserie(InquiryLfseqserieRequest inquiryLfseqserieRequest)
	{
		InternalResultsResponse<Lfseqserie> response = new InternalResultsResponse<Lfseqserie>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryLfseqserieRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryLfseqserieRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryLfseqserieRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryLfseqserieRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryLfseqserieRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryLfseqserieRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLfseqserieRequest.getLfseqserie()))
		{
			paramMap.put(LFSEQSERIE_ID, inquiryLfseqserieRequest.getLfseqserie().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LFSEQSERIES, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfseqserie.dao.ILfseqserieDAO#insertLfseqserie(com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest)
	 */ 
	@Override
	public InternalResultsResponse<Lfseqserie> insertLfseqserie(LfseqserieRequest lfseqserieRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Lfseqserie lfseqserie = lfseqserieRequest.getLfseqserie();

		lfseqserie.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_LFSEQSERIE, lfseqserieRequest));
   lfseqserie.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfseqserieRequest));
		InternalResultsResponse<Lfseqserie> response = new InternalResultsResponse<Lfseqserie>();
		response.addResult(lfseqserie);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.lfseqserie.dao.ILfseqserieDAO#deleteLfseqserie(com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest)
	 */ 
	@Override
	public InternalResponse deleteLfseqserie(LfseqserieRequest lfseqserieRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_LFSEQSERIE, lfseqserieRequest.getLfseqserie(), response);
   lfseqserie.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA , lfseqserieRequest));
		return response;
	}


 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.lfseqserie.dac.ILfseqserieDAC#updateLfseqserie(com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Lfseqserie> updateLfseqserie(LfseqserieRequest lfseqserieRequest)
	{
		InternalResultsResponse<Lfseqserie> response = new InternalResultsResponse<Lfseqserie>();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_LFSEQSERIE, lfseqserieRequest);
   lfseqserie.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, lfseqserieRequest));
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_LFSEQSERIE_VALIDATOR_LFSEQSERIE_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


