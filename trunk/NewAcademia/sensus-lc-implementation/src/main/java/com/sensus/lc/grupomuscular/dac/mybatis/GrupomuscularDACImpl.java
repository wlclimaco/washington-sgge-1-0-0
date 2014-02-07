package com.sensus.lc.grupomuscular.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.grupomuscular.dac.IGrupomuscularDAC;
import com.sensus.lc.grupomuscular.model.Grupomuscular;
import com.sensus.lc.grupomuscular.model.request.GrupomuscularRequest;
import com.sensus.lc.grupomuscular.model.request.InquiryGrupomuscularRequest;

/**
 * The Class GrupomuscularDACImpl.
 * 
 * @author Washington
 */
public class GrupomuscularDACImpl extends SqlSessionDaoSupport implements IGrupomuscularDAC
{

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant GRUPOMUSCULAR_TYPE_VALUE. */
	private static final Integer GRUPOMUSCULAR_TYPE_VALUE = 1;

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

	/** The Constant GRUPOMUSCULAR_MAP. */
	private static final String GRUPOMUSCULAR_MAP = "Grupomuscular.";

	/** The Constant DELETE_GRUPOMUSCULAR. */
	private static final String DELETE_GRUPOMUSCULAR = GRUPOMUSCULAR_MAP + "deleteGrupomuscular";

	private static final String FETCH_ALL_GRUPOMUSCULAR_BY_USER = GRUPOMUSCULAR_MAP + "fetchAllGrupomuscularByUser";

	/** The Constant FETCH_ALL_GRUPOMUSCULARS. */
	private static final String FETCH_ALL_GRUPOMUSCULARS = GRUPOMUSCULAR_MAP + "fetchAllGrupomusculars";

	/** The Constant FETCH_DEVICES_BY_GRUPOMUSCULARS. */
	private static final String FETCH_DEVICES_BY_GRUPOMUSCULARS = GRUPOMUSCULAR_MAP + "fetchDevicesByName";

	/** The Constant INSERT_GRUPOMUSCULAR. */
	private static final String INSERT_GRUPOMUSCULAR = GRUPOMUSCULAR_MAP + "insertGrupomuscular";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = GRUPOMUSCULAR_MAP + "paginationTotalRows";

	/** The Constant UPDATE_GRUPOMUSCULAR. */
	private static final String UPDATE_GRUPOMUSCULAR = GRUPOMUSCULAR_MAP + "updateGrupomuscular";

	/** The Constant FETCH_SCHEDULE_BY_ID. */
	private static final String FETCH_SCHEDULE_BY_ID = GRUPOMUSCULAR_MAP + "fetchDevicesByGrupomusculars";

	private static final String CUSTOMER_ID = null;

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.dac.IGrupomuscularDAC#deleteGrupomuscular(com.sensus.dm.common.grupomuscular.model
	 * .request
	 * .
	 * GrupomuscularRequest)
	 */
	@Override
	public InternalResponse deleteGrupomuscular(GrupomuscularRequest grupomuscularRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(CDACAD, grupomuscularRequest.getFirstGrupomuscular().getCdgrmusc());

		Integer result =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_GRUPOMUSCULAR, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.dac.IGrupomuscularDAC#insertGrupomuscular(com.sensus.dm.common.grupomuscular.model
	 * .request
	 * .
	 * GrupomuscularRequest)
	 */
	@Override
	public InternalResultsResponse<Grupomuscular> insertGrupomuscular(GrupomuscularRequest grupomuscularRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put("cdGropoMusc", grupomuscularRequest.getGrupomusculars().get(0).getCdgrmusc());
		paramMap.put("nome", grupomuscularRequest.getGrupomusculars().get(0).getMusculo());
		paramMap.put("descricao", grupomuscularRequest.getGrupomusculars().get(0).getDsgrmusc());
		paramMap.put("create_date", grupomuscularRequest.getGrupomusculars().get(0).getCreatedate());
		paramMap.put("create_user", grupomuscularRequest.getGrupomusculars().get(0).getCreateuser());
		paramMap.put("tenant_id", grupomuscularRequest.getGrupomusculars().get(0).getTenantid());
		paramMap.put("userid", grupomuscularRequest.getGrupomusculars().get(0).getUserid());

		Integer grupomuscularId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_GRUPOMUSCULAR, paramMap);

		InternalResultsResponse<Grupomuscular> response = new InternalResultsResponse<Grupomuscular>();

		if (!ValidationUtil.isNull(grupomuscularId))
		{
			// grupomuscularRequest.getGrupomusculars().setgrupomuscularId);
			// response.addResult(grupomuscularRequest.getGrupomuscular());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.dac.IGrupomuscularDAC#updateGrupomuscular(com.sensus.dm.common.grupomuscular.model
	 * .request
	 * .
	 * GrupomuscularRequest)
	 */
	@Override
	public InternalResultsResponse<Grupomuscular> updateGrupomuscular(GrupomuscularRequest grupomuscularRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE11);

		paramMap.put("cdGropoMusc", grupomuscularRequest.getGrupomusculars().get(0).getCdgrmusc());
		paramMap.put("nome", grupomuscularRequest.getGrupomusculars().get(0).getMusculo());
		paramMap.put("descricao", grupomuscularRequest.getGrupomusculars().get(0).getDsgrmusc());
		paramMap.put("create_date", grupomuscularRequest.getGrupomusculars().get(0).getCreatedate());
		paramMap.put("create_user", grupomuscularRequest.getGrupomusculars().get(0).getCreateuser());
		paramMap.put("tenant_id", grupomuscularRequest.getGrupomusculars().get(0).getTenantid());
		paramMap.put("userid", grupomuscularRequest.getGrupomusculars().get(0).getUserid());

		String result =
				(String)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_GRUPOMUSCULAR, paramMap);

		InternalResultsResponse<Grupomuscular> response = new InternalResultsResponse<Grupomuscular>();

		if (ValidationUtil.isNull(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add((Grupomuscular)grupomuscularRequest.getGrupomusculars());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.dac.IGrupomuscularDAC#fetchAllGrupomusculars(com.sensus.dm.common.grupomuscular
	 * .model.
	 * request.
	 * InquiryGrupomuscularRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Grupomuscular> fetchAllGrupomusculars(
			InquiryGrupomuscularRequest inquiryGrupomuscularRequest)
	{
		InternalResultsResponse<Grupomuscular> response = new InternalResultsResponse<Grupomuscular>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(SELECTION_PAGINATION_IDS, inquiryGrupomuscularRequest.getSelectionPaginationIds());
		paramMap.put(PAGINATION_ALL_SELECTED, inquiryGrupomuscularRequest.getPaginationAllSelected());
		paramMap.put(TENANTID, inquiryGrupomuscularRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryGrupomuscularRequest.getPageSize());
		paramMap.put(START_PAGE, inquiryGrupomuscularRequest.getStartPage());
		paramMap.put(START_ROW, inquiryGrupomuscularRequest.getStartRow());
		paramMap.put(ORDERBY, "cdgrmusc");

		if (!ValidationUtil.isNullOrEmpty(inquiryGrupomuscularRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, "cdgrmusc");
		}

		doQueryForList(getSqlSession(), FETCH_ALL_GRUPOMUSCULARS, paramMap, response);
		// Integer totalRows =
		// (Integer)doQueryForObject(getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryGrupomuscularRequest);
		// response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;

	}

	@Override
	public InternalResultsResponse<Grupomuscular> fetchGrupomuscularsByName(GrupomuscularRequest grupomuscularRequest)
	{
		InternalResultsResponse<Grupomuscular> response = new InternalResultsResponse<Grupomuscular>();
		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_DEVICES_BY_GRUPOMUSCULARS,
						grupomuscularRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Grupomuscular> fetchGrupomuscularById(
			InquiryGrupomuscularRequest inquiryGrupomuscularRequest)
	{
		InternalResultsResponse<Grupomuscular> response = new InternalResultsResponse<Grupomuscular>();
		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_SCHEDULE_BY_ID,
						inquiryGrupomuscularRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Grupomuscular> fetchAllGrupomuscularByUser(
			InquiryGrupomuscularRequest inquiryGrupomuscularRequest)
	{
		InternalResultsResponse<Grupomuscular> response = new InternalResultsResponse<Grupomuscular>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(SELECTION_PAGINATION_IDS, inquiryGrupomuscularRequest.getSelectionPaginationIds());
		paramMap.put(PAGINATION_ALL_SELECTED, inquiryGrupomuscularRequest.getPaginationAllSelected());
		paramMap.put(TENANTID, inquiryGrupomuscularRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryGrupomuscularRequest.getPageSize());
		paramMap.put(START_PAGE, inquiryGrupomuscularRequest.getStartPage());
		paramMap.put(START_ROW, inquiryGrupomuscularRequest.getStartRow());
		paramMap.put(USER, inquiryGrupomuscularRequest.getGrupomusculars().get(0).getCreateuser());
		paramMap.put(ORDERBY, "cdGropoMusc");

		if (!ValidationUtil.isNullOrEmpty(inquiryGrupomuscularRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, "cdGropoMusc");
		}

		doQueryForList(getSqlSession(), FETCH_ALL_GRUPOMUSCULAR_BY_USER, paramMap, response);
		Integer totalRows =
				(Integer)doQueryForObject(getSqlSession(), "PaginationTotalRowsByUser", paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;

	}

}
