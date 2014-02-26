package com.sensus.lc.comum.dac.mybati;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.academia.dac.IAcademiaDAC;
import com.sensus.lc.academia.model.Academia;
import com.sensus.lc.academia.model.request.AcademiaRequest;
import com.sensus.lc.academia.model.request.InquiryAcademiaRequest;

/**
 * The Class AcademiaDACImpl.
 * 
 * @author Washington
 */
public class AcademiaDACImpl extends SqlSessionDaoSupport implements IAcademiaDAC
{
	private static final String FETCH_ALL_ACADEMIA_BY_USER = "fetchAllAcademiaByUser";

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant ACADEMIA_TYPE_VALUE. */
	private static final Integer ACADEMIA_TYPE_VALUE = 1;

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	/** The Constant PARAMSIZE5. */
	private static final int PARAMSIZE5 = 5;

	/** The Constant PARAMSIZE6. */
	private static final Integer PARAMSIZE6 = 6;

	/** The Constant PARAMSIZE10. */
	private static final Integer PARAMSIZE10 = 10;

	private static final Integer PARAMSIZE11 = 11;

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant TENANTID. */
	private static final String TENANTID = "tenantid";

	/** The Constant SELECTED_IDS. */
	private static final String PAGINATION_ALL_SELECTED = "paginationAllSelected";

	/** The Constant SELECTION_PAGINATION_IDS. */
	private static final String SELECTION_PAGINATION_IDS = "selectionPaginationIds";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant ORDERBY. */
	private static final String ORDERBY = "orderBy";

	/** The Constant START_PAGE. */
	private static final String START_PAGE = "startPage";

	/** The Constant MAX_LIGHT_COUNT. */
	private static final String MAX_LIGHT_COUNT = "maxLightCount";

	/** The Constant CDACAD. */
	private static final String CDACAD = "cdacad";
	/** The Constant ACADEM. */
	private static final String ACADEM = "academ";
	/** The Constant LOGRAD. */
	private static final String LOGRAD = "lograd";
	/** The Constant NUMEN. */
	private static final String NUMEN = "numen";
	/** The Constant BAIRR. */
	private static final String BAIRR = "bairr";
	/** The Constant CIDADE. */
	private static final String CIDADE = "cidade";
	/** The Constant CEP. */
	private static final String CEP = "cep";
	/** The Constant TELEF. */
	private static final String TELEF = "telef";
	/** The Constant LATLOG. */
	private static final String LATLOG = "latlog";

	private static final String USER = "user";

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant ACADEMIA_MAP. */
	private static final String ACADEMIA_MAP = "Academia.";

	/** The Constant DELETE_ACADEMIA. */
	private static final String DELETE_ACADEMIA = ACADEMIA_MAP + "deleteAcademia";

	/** The Constant FETCH_ALL_ACADEMIAS. */
	private static final String FETCH_ALL_ACADEMIAS = ACADEMIA_MAP + "fetchAllAcademias";

	/** The Constant FETCH_DEVICES_BY_ACADEMIAS. */
	private static final String FETCH_DEVICES_BY_ACADEMIAS = ACADEMIA_MAP + "fetchDevicesByName";

	/** The Constant INSERT_ACADEMIA. */
	private static final String INSERT_ACADEMIA = ACADEMIA_MAP + "insertAcademia";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = ACADEMIA_MAP + "paginationTotalRows";

	/** The Constant UPDATE_ACADEMIA. */
	private static final String UPDATE_ACADEMIA = ACADEMIA_MAP + "updateAcademia";

	/** The Constant FETCH_SCHEDULE_BY_ID. */
	private static final String FETCH_SCHEDULE_BY_ID = ACADEMIA_MAP + "fetchDevicesByAcademias";

	private static final String CUSTOMER_ID = null;

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.dac.IAcademiaDAC#deleteAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public InternalResponse deleteAcademia(AcademiaRequest academiaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(CDACAD, academiaRequest.getFirstAcademia().getCdacad());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_ACADEMIA, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.dac.IAcademiaDAC#insertAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public InternalResultsResponse<Academia> insertAcademia(AcademiaRequest academiaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put("academ", academiaRequest.getAcademia().getAcadem());
		paramMap.put("lograd", academiaRequest.getAcademia().getLograd());
		paramMap.put("numen", academiaRequest.getAcademia().getNumen());
		paramMap.put("bairr", academiaRequest.getAcademia().getBairr());
		paramMap.put("cidade", academiaRequest.getAcademia().getCidade());
		paramMap.put("cep", academiaRequest.getAcademia().getCep());
		paramMap.put("telef", academiaRequest.getAcademia().getTelef());
		paramMap.put("dataini", academiaRequest.getAcademia().getDataini());
		paramMap.put("dataFin", academiaRequest.getAcademia().getDatafin());
		paramMap.put("createDate", academiaRequest.getAcademia().getCreatedate());
		paramMap.put("createUser", academiaRequest.getAcademia().getCreateuser());
		paramMap.put("tenantid", academiaRequest.getAcademia().getTenantid());
		paramMap.put("userid", academiaRequest.getAcademia().getUserid());
		paramMap.put("atual", academiaRequest.getAcademia().getAtual());

		Integer academiaId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_ACADEMIA, paramMap);

		InternalResultsResponse<Academia> response = new InternalResultsResponse<Academia>();

		if (!ValidationUtil.isNull(academiaId))
		{
			academiaRequest.getAcademia().setCdacad(academiaId);
			response.addResult(academiaRequest.getAcademia());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.dac.IAcademiaDAC#updateAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public InternalResultsResponse<Academia> updateAcademia(AcademiaRequest academiaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE11);

		paramMap.put("cdacad", academiaRequest.getAcademia().getCdacad());
		paramMap.put("academ", academiaRequest.getAcademia().getAcadem());
		paramMap.put("lograd", academiaRequest.getAcademia().getLograd());
		paramMap.put("numen", academiaRequest.getAcademia().getNumen());
		paramMap.put("bairr", academiaRequest.getAcademia().getBairr());
		paramMap.put("cidade", academiaRequest.getAcademia().getCidade());
		paramMap.put("cep", academiaRequest.getAcademia().getCep());
		paramMap.put("telef", academiaRequest.getAcademia().getTelef());
		paramMap.put("dataini", academiaRequest.getAcademia().getDataini());
		paramMap.put("dataFin", academiaRequest.getAcademia().getDatafin());
		paramMap.put("createDate", academiaRequest.getAcademia().getCreatedate());
		paramMap.put("createUser", academiaRequest.getAcademia().getCreateuser());
		paramMap.put("tenantid", academiaRequest.getAcademia().getTenantid());
		paramMap.put("userid", academiaRequest.getAcademia().getUserid());
		paramMap.put("atual", academiaRequest.getAcademia().getAtual());

		String result = (String)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_ACADEMIA, paramMap);

		InternalResultsResponse<Academia> response = new InternalResultsResponse<Academia>();

		if (ValidationUtil.isNull(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add(academiaRequest.getAcademia());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.academia.dac.IAcademiaDAC#fetchAllAcademias(com.sensus.dm.common.academia.model.request.
	 * InquiryAcademiaRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Academia> fetchAllAcademias(InquiryAcademiaRequest inquiryAcademiaRequest)
	{
		InternalResultsResponse<Academia> response = new InternalResultsResponse<Academia>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(SELECTION_PAGINATION_IDS, inquiryAcademiaRequest.getSelectionPaginationIds());
		paramMap.put(PAGINATION_ALL_SELECTED, inquiryAcademiaRequest.getPaginationAllSelected());
		paramMap.put(TENANTID, inquiryAcademiaRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryAcademiaRequest.getPageSize());
		paramMap.put(START_PAGE, inquiryAcademiaRequest.getStartPage());
		paramMap.put(START_ROW, inquiryAcademiaRequest.getStartRow());
		paramMap.put(ORDERBY, "cdacad");

		if (!ValidationUtil.isNullOrEmpty(inquiryAcademiaRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, "cdacad");
		}

		doQueryForList(getSqlSession(), FETCH_ALL_ACADEMIAS, paramMap, response);
		// Integer totalRows =
		// (Integer)doQueryForObject(getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryAcademiaRequest);
		// response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;

	}

	@Override
	public InternalResultsResponse<Academia> fetchAcademiasByName(AcademiaRequest academiaRequest)
	{
		InternalResultsResponse<Academia> response = new InternalResultsResponse<Academia>();
		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_DEVICES_BY_ACADEMIAS, academiaRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Academia> fetchAcademiaById(InquiryAcademiaRequest inquiryAcademiaRequest)
	{
		InternalResultsResponse<Academia> response = new InternalResultsResponse<Academia>();
		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_SCHEDULE_BY_ID, inquiryAcademiaRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Academia> fetchAllAcademiaByUser(InquiryAcademiaRequest inquiryAcademiaRequest)
	{
		InternalResultsResponse<Academia> response = new InternalResultsResponse<Academia>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(SELECTION_PAGINATION_IDS, inquiryAcademiaRequest.getSelectionPaginationIds());
		paramMap.put(PAGINATION_ALL_SELECTED, inquiryAcademiaRequest.getPaginationAllSelected());
		paramMap.put(TENANTID, inquiryAcademiaRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryAcademiaRequest.getPageSize());
		paramMap.put(START_PAGE, inquiryAcademiaRequest.getStartPage());
		paramMap.put(START_ROW, inquiryAcademiaRequest.getStartRow());
		paramMap.put(USER, inquiryAcademiaRequest.getAcademias().get(0).getCreateuser());
		paramMap.put(ORDERBY, "cdacad");

		if (!ValidationUtil.isNullOrEmpty(inquiryAcademiaRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, "cdacad");
		}

		doQueryForList(getSqlSession(), FETCH_ALL_ACADEMIA_BY_USER, paramMap, response);
		Integer totalRows =
				(Integer)doQueryForObject(getSqlSession(), "PaginationTotalRowsByUser", paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;

	}

}
