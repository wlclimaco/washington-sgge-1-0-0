package com.sensus.dm.common.device.dac.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.Message;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.dac.ICustomSearchDAC;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.property.model.Property;

/**
 * The Class CustomSearchDACImpl.
 * 
 * @author QAT Brazil.
 * 
 */
public class CustomSearchDACImpl extends SqlSessionDaoSupport implements ICustomSearchDAC
{

	/** The Constant SENSUS_EPM_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_ALREADY_EXISTS. */
	private static final String SENSUS_EPM_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_ALREADY_EXISTS =
			"sensus.epm.customsearchvalidator.customsearch.already.exists";

	/** The Constant SORT_EXPRESSION. */
	private static final String SORT_EXPRESSION = "sortExpression";

	/** The Constant ORDER_BY. */
	private static final String ORDER_BY = "order_by";

	/** The Constant CUSTOM_SEARCH. */
	private static final String CUSTOM_SEARCH = "CustomSearch";

	/** The Constant GROUPSET_ID. */
	private static final String GROUPSET_ID = "groupset_id";

	/** The Constant GROUP_NAME. */
	private static final String GROUP_NAME = "group_name";

	/** The Constant GROUP_DESC. */
	private static final String GROUP_DESC = "group_desc";

	/** The Constant CUSTOM_SEARCH_DESCRIPTION. */
	private static final String CUSTOM_SEARCH_CODE = "12";

	/** The Constant GROUP_TYPE. */
	private static final String GROUP_TYPE = "group_type";

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "create_user";

	/** The Constant MODIFIED_USER. */
	private static final String SERVICE_TYPE = "service_type";

	/** The Constant CUSTOM_SEARCH_SERVICE_GROUP_TYPE. */
	private static final String CUSTOM_SEARCH_SERVICE_GROUP_TYPE = "service_group_type_id";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE5 = 5;

	/** The Constant PARAMSIZE6. */
	private static final Integer PARAMSIZE6 = 6;

	/** The Constant PARAMSIZE8. */
	private static final Integer PARAMSIZE8 = 8;

	/** The Constant PROPERTY_NAME. */
	private static final String PROPERTY_NAME = "property_name";

	/** The Constant COLUMN_FILTER. */
	private static final String COLUMN_FILTER = "column_filter";

	/** The Constant DISPLAY_ORDER. */
	private static final String DISPLAY_ORDER = "display_order";

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "customer_id";

	/** The Constant INSERT_TYPE. */
	private static final String INSERT_TYPE = "insert_type";

	/** The Constant CUSTOM_SEARCH_MAP. */
	private static final String CUSTOM_SEARCH_MAP = "CustomSearchMap.";

	/** The Constant CUSTOM_SEARCH_ID. */
	private static final String CUSTOM_SEARCH_ID = "CustomSearchId";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = CUSTOM_SEARCH_MAP + "fetchAll";

	/** The Constant FETCH_PAGINATION_TOTAL_ROWS. */
	private static final String FETCH_PAGINATION_TOTAL_ROWS = CUSTOM_SEARCH_MAP + "fetchPaginationTotalRows";

	/** The Constant FETCH_ALL_COLUMN_FILTER. */
	private static final String FETCH_ALL_COLUMN_FILTER = CUSTOM_SEARCH_MAP + "fetchAllColumnFilter";

	/** The Constant GROUP_MAP. */
	private static final String GROUP_MAP = "GroupMap.";

	/** The Constant DELETE. */
	private static final String DELETE = GROUP_MAP + "deleteGroup";

	/** The Constant INSERT. */
	private static final String INSERT = GROUP_MAP + "insertGroup";

	/** The Constant FETCH_COUNT_NAME. */
	private static final String FETCH_COUNT_NAME = GROUP_MAP + "countGroupByName";

	/**
	 * Can custom search be inserted.
	 * 
	 * @param customSearchRequest the custom search request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<Boolean> canCustomSearchBeInserted(CustomSearchRequest customSearchRequest)
	{
		InternalResultsResponse<Boolean> response = new InternalResultsResponse<Boolean>();

		if (isCustomSearchNameUnique(customSearchRequest))
		{
			response.getResultsList().add(Boolean.TRUE);
			return response;
		}

		response.getResultsList().add(Boolean.FALSE);

		response.getMessageInfoList().add(new MessageInfo(SENSUS_EPM_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_ALREADY_EXISTS,
				Message.MessageSeverity.Error,
				Message.MessageLevel.FieldValidation));

		return response;
	}

	/**
	 * Fetch all custom search.
	 * 
	 * @param inquiryCustomSearchRequest the inquiry custom search request
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<CustomSearch> fetchAllCustomSearch(
			InquiryCustomSearchRequest inquiryCustomSearchRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		paramMap.put(CUSTOMER_ID, inquiryCustomSearchRequest.getTenant().getKey());
		paramMap.put(GROUP_TYPE, CUSTOM_SEARCH_CODE);
		paramMap.put(CUSTOM_SEARCH_SERVICE_GROUP_TYPE, inquiryCustomSearchRequest.getServiceTypeEnum()
				.getServiceGroupType());

		if (!ValidationUtil.isNullOrEmpty(inquiryCustomSearchRequest.getSortExpressions()))
		{
			paramMap.put(SORT_EXPRESSION, inquiryCustomSearchRequest.getSortExpressions().get(0).getField()
					+ " "
					+ inquiryCustomSearchRequest.getSortExpressions().get(0).getDirectionValue());
		}

		if (!ValidationUtil.isNull(inquiryCustomSearchRequest.getCustomSearch()))
		{
			paramMap.put(CUSTOM_SEARCH_ID, inquiryCustomSearchRequest.getCustomSearch().getId());
		}

		if (!ValidationUtil.isNullOrZero(inquiryCustomSearchRequest.getPageSize()))
		{
			paramMap.put(PAGE_SIZE, inquiryCustomSearchRequest.getPageSize());
			paramMap.put(START_ROW, inquiryCustomSearchRequest.getStartRow());
		}

		List<CustomSearch> result =
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL, paramMap);

		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();
		response.getResultsList().addAll(result);

		response.getResultsSetInfo().setTotalRowsAvailable(
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(),
						FETCH_PAGINATION_TOTAL_ROWS, paramMap));

		return response;
	}

	/**
	 * Delete custom search.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	@Override
	public InternalResponse deleteCustomSearch(CustomSearchRequest request)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(GROUPSET_ID, request.getCustomSearch().getId());

		Integer result =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE, paramMap);

		InternalResponse response = new InternalResponse();

		if (result == 0)
		{
			response.setStatus(Status.NoRowsRemovedError);
		}

		return response;
	}

	/**
	 * Insert custom search.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE8);
		paramMap.put(GROUP_NAME, request.getCustomSearch().getName());
		paramMap.put(GROUP_DESC, request.getCustomSearch().getDescription());
		paramMap.put(CREATE_USER, request.getUserContext().getUserId());
		paramMap.put(GROUP_TYPE, CUSTOM_SEARCH_CODE);
		paramMap.put(SERVICE_TYPE, request.getServiceTypeEnum().getServiceGroupType());
		paramMap.put(CUSTOMER_ID, request.getTenant().getKey());
		paramMap.put(INSERT_TYPE, CUSTOM_SEARCH);

		Integer customSearchId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(),
						INSERT, paramMap);
		request.getCustomSearch().setId(customSearchId);

		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();
		response.getResultsList().add(request.getCustomSearch());

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.dac.IElectricMeterDAC#fetchAllColumnFilter(com.sensus.dm.elec.device.model.request.
	 * ColumnFilterRequest
	 * )
	 */
	/**
	 * Fetch all column filter.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Property> fetchAllColumnFilter(ColumnFilterRequest request)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);

		paramMap.put(COLUMN_FILTER, request.getPropertyProviderType());

		if (!ValidationUtil.isNullOrEmpty(request.getProperties()))
		{
			List<String> propertyNames = new ArrayList<String>();
			for (Property property : request.getProperties())
			{
				propertyNames.add(property.getPropertyName());
			}
			paramMap.put(PROPERTY_NAME, propertyNames);
		}

		paramMap.put(ORDER_BY, DISPLAY_ORDER);

		List<Property> properties =
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_COLUMN_FILTER, paramMap);

		InternalResultsResponse<Property> response = new InternalResultsResponse<Property>();
		response.getResultsList().addAll(properties);

		return response;
	}

	/**
	 * Checks if is custom search name unique.
	 * 
	 * @param customSearchRequest the custom search request
	 * @return the boolean
	 */
	private Boolean isCustomSearchNameUnique(CustomSearchRequest customSearchRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(GROUP_NAME, customSearchRequest.getCustomSearch().getName());
		paramMap.put(GROUP_TYPE, CUSTOM_SEARCH_CODE);
		paramMap.put(SERVICE_TYPE, customSearchRequest.getServiceTypeEnum().getServiceGroupType());
		paramMap.put(CUSTOMER_ID, customSearchRequest.getTenant().getKey());

		Integer totalRows = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(),
				FETCH_COUNT_NAME, paramMap);

		return ValidationUtil.isNullOrZero(totalRows);
	}

}
