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
import com.sensus.mlc.classcli.dac.IClasscliDAC;
import com.sensus.mlc.classcli.model.Classcli;
import com.sensus.mlc.classcli.model.ClasscliOrderByEnum;
import com.sensus.mlc.classcli.model.request.InquiryClasscliRequest;
import com.sensus.mlc.classcli.model.request.ClasscliRequest;

/** 
 * The Class ClasscliDACImpl.
 */ 
public class ClasscliDACImpl extends SqlSessionDaoSupport implements IClasscliDAC
{ 

	/** The Constant CLASSCLI_NAMESPACE. */ 
	private static final String CLASSCLI_NAMESPACE = "Classcli.";

	/** The Constant FETCH_CLASSCLI_BY_ID. */ 
	private static final String FETCH_CLASSCLI_BY_ID = CLASSCLI_NAMESPACE + "fetchClasscliById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = CLASSCLI_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_CLASSCLI. */ 
	private static final String DELETE_SMART_POINT_FROM_CLASSCLI = CLASSCLI_NAMESPACE + "deleteSmartPointFromClasscli";

	/** The Constant FETCH_CLASSCLI_BY_ID. */ 
	private static final String FETCH_CLASSCLI_BY_ID = CLASSCLI_NAMESPACE + "fetchClasscliById";

	/** The Constant FETCH_CLASSCLI_NAME_BY_ID. */ 
	private static final String FETCH_CLASSCLI_NAME_BY_ID = CLASSCLI_NAMESPACE + "fetchClasscliNameById";

	/** The Constant UPDATE_AUTO_CLASSCLI_CLASSCLI. */ 
	private static final String UPDATE_AUTO_CLASSCLI_CLASSCLI = CLASSCLI_NAMESPACE + "updateAutoClasscliClasscli";

	/** The Constant DELETE_CLASSCLI. */ 
	private static final String DELETE_CLASSCLI = CLASSCLI_NAMESPACE + "deleteClasscli";

	/** The Constant FETCH_CLASSCLIS_BY_LIGHT_ID. */ 
	private static final String FETCH_CLASSCLIS_BY_LIGHT_ID = CLASSCLI_NAMESPACE + "fetchClassclisByLightId";

	/** The Constant FETCH_CLASSCLIS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_CLASSCLIS_BY_SMART_POINT_ID = CLASSCLI_NAMESPACE + "fetchClassclisBySmartPointId";

	/** The Constant INSERT_CLASSCLI. */ 
	private static final String INSERT_CLASSCLI = CLASSCLI_NAMESPACE + "insertClasscli";

	/** The Constant INSERT_SMART_POINT_TO_CLASSCLI. */ 
	private static final String INSERT_SMART_POINT_TO_CLASSCLI = CLASSCLI_NAMESPACE + "insertSmartPointToClasscli";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_CLASSCLI = CLASSCLI_NAMESPACE + "fetchLightsBelongClasscli";

	/** The Constant FETCH_ALL_CLASSCLIS. */ 
	private static final String FETCH_ALL_CLASSCLIS = CLASSCLI_NAMESPACE + "fetchAllClassclis";

	/** The Constant CLASSCLIID. */ 
	private static final String CLASSCLI_ID = "classcliId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant CLASSCLINAME. */ 
	private static final String CLASSCLINAME = "classcliname";

	/** The Constant AUTOCLASSCLI. */ 
	private static final String AUTOCLASSCLI = "autoclasscli";

	/** The Constant AUTOCLASSCLI. */ 
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
	 * @see com.sensus.mlc.classcli.dao.IClasscliDAO#fetchAllClassclis(com.sensus.mlc.classcli.model.request.InquiryClasscliRequest)
	 */ 
	@Override
	public InternalResultsResponse<Classcli> fetchAllClasscli(InquiryClasscliRequest inquiryClasscliRequest)
	{
		InternalResultsResponse<Classcli> response = new InternalResultsResponse<Classcli>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryClasscliRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryClasscliRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryClasscliRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryClasscliRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryClasscliRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryClasscliRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryClasscliRequest.getClassclis()))
		{
			paramMap.put(CLASSCLI_ID, inquiryClasscliRequest.getClassclis().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_CLASSCLIS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.classcli.dao.IClasscliDAO#fetchClasscliById(com.sensus.mlc.classcli.model.Classcli
	 */ 
	@Override
	public InternalResultsResponse<Classcli> fetchClasscliByName(ClasscliRequest classcliRequest)
	{ 
		InternalResultsResponse<Classcli> response = new InternalResultsResponse<Classcli>();
		doQueryForList(getSqlSession(), FETCH_CLASSCLI_BY_ID, classcliRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.classcli.dao.IClasscliDAO#insertClasscli(com.sensus.mlc.classcli.model.request.ClasscliRequest)
	 */ 
	@Override
	public InternalResultsResponse<Classcli> insertClasscli(ClasscliRequest classcliRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Classcli classcli = classcliRequest.getClasscli();

		classcli.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_CLASSCLI, classcliRequest));
   classcli.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, classcliRequest));
		InternalResultsResponse<Classcli> response = new InternalResultsResponse<Classcli>();
		response.addResult(classcli);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.classcli.dao.IClasscliDAO#deleteClasscli(com.sensus.mlc.classcli.model.request.ClasscliRequest)
	 */ 
	@Override
	public InternalResponse deleteClasscli(ClasscliRequest classcliRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_CLASSCLI, classcliRequest.getClasscli(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.classcli.dao.IClasscliDAO#fetchClasscliById(com.sensus.mlc.classcli.model.request.ClasscliRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Classcli> fetchClasscliById(ClasscliRequest classcliRequest) 
	{
		InternalResultsResponse<Classcli> response = new InternalResultsResponse<Classcli>();
		doQueryForList(getSqlSession(), FETCH_CLASSCLI_BY_ID, classcliRequest.getClasscli(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.classcli.dac.IClasscliDAC#fetchClasscliNameById(com.sensus.mlc.classcli.model.request.ClasscliRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Classcli> fetchClasscliNameById(ClasscliRequest classcliRequest)
	{
		InternalResultsResponse<Classcli> response = new InternalResultsResponse<Classcli>();
		doQueryForList(getSqlSession(), FETCH_CLASSCLI_NAME_BY_ID, classcliRequest.getClasscli(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.classcli.dac.IClasscliDAC#updateClasscli(com.sensus.mlc.classcli.model.request.ClasscliRequest)
	 */ 
	@Override 
	public InternalResponse updateClasscli(ClasscliRequest classcliRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_CLASSCLI, classcliRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_CLASSCLIVALIDATOR_CLASSCLI_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


