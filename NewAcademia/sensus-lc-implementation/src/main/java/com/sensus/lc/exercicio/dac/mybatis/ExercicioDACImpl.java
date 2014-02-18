package com.sensus.lc.exercicio.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.exercicio.dac.IExercicioDAC;
import com.sensus.lc.exercicio.model.Exercicio;
import com.sensus.lc.exercicio.model.request.ExercicioRequest;
import com.sensus.lc.exercicio.model.request.InquiryExercicioRequest;

/**
 * The Class ExercicioDACImpl.
 * 
 * @author Washington
 */
public class ExercicioDACImpl extends SqlSessionDaoSupport implements IExercicioDAC
{
	private static final String FETCH_ALL_EXERCICIO_BY_USER = "fetchAllExercicioByUser";

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant EXERCICIO_TYPE_VALUE. */
	private static final Integer EXERCICIO_TYPE_VALUE = 1;

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

	/** The Constant EXERCICIO_MAP. */
	private static final String EXERCICIO_MAP = "Exercicio.";

	/** The Constant DELETE_EXERCICIO. */
	private static final String DELETE_EXERCICIO = EXERCICIO_MAP + "deleteExercicio";

	/** The Constant FETCH_ALL_EXERCICIOS. */
	private static final String FETCH_ALL_EXERCICIOS = EXERCICIO_MAP + "fetchAllExercicios";

	/** The Constant FETCH_DEVICES_BY_EXERCICIOS. */
	private static final String FETCH_DEVICES_BY_EXERCICIOS = EXERCICIO_MAP + "fetchDevicesByName";

	/** The Constant INSERT_EXERCICIO. */
	private static final String INSERT_EXERCICIO = EXERCICIO_MAP + "insertExercicio";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = EXERCICIO_MAP + "paginationTotalRows";

	/** The Constant UPDATE_EXERCICIO. */
	private static final String UPDATE_EXERCICIO = EXERCICIO_MAP + "updateExercicio";

	/** The Constant FETCH_SCHEDULE_BY_ID. */
	private static final String FETCH_SCHEDULE_BY_ID = EXERCICIO_MAP + "fetchDevicesByExercicios";

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
	 * com.sensus.dm.common.exercicio.dac.IExercicioDAC#deleteExercicio(com.sensus.dm.common.exercicio.model.request.
	 * ExercicioRequest)
	 */
	@Override
	public InternalResponse deleteExercicio(ExercicioRequest exercicioRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(CDACAD, exercicioRequest.getFirstExercicio().getCdexerc());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_EXERCICIO, paramMap);

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
	 * com.sensus.dm.common.exercicio.dac.IExercicioDAC#insertExercicio(com.sensus.dm.common.exercicio.model.request.
	 * ExercicioRequest)
	 */
	@Override
	public InternalResultsResponse<Exercicio> insertExercicio(ExercicioRequest exercicioRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);
		//
		// paramMap.put("academ", exercicioRequest.getExercicio().getAcadem());
		// paramMap.put("lograd", exercicioRequest.getExercicio().getLograd());
		// paramMap.put("numen", exercicioRequest.getExercicio().getNumen());
		// paramMap.put("bairr", exercicioRequest.getExercicio().getBairr());
		// paramMap.put("cidade", exercicioRequest.getExercicio().getCidade());
		// paramMap.put("cep", exercicioRequest.getExercicio().getCep());
		// paramMap.put("telef", exercicioRequest.getExercicio().getTelef());
		// paramMap.put("dataini", exercicioRequest.getExercicio().getDataini());
		// paramMap.put("dataFin", exercicioRequest.getExercicio().getDatafin());
		// paramMap.put("createDate", exercicioRequest.getExercicio().getCreatedate());
		// paramMap.put("createUser", exercicioRequest.getExercicio().getCreateuser());
		// paramMap.put("tenantid", exercicioRequest.getExercicio().getTenantid());
		// paramMap.put("userid", exercicioRequest.getExercicio().getUserid());
		// paramMap.put("atual", exercicioRequest.getExercicio().getAtual());

		Integer exercicioId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_EXERCICIO, paramMap);

		InternalResultsResponse<Exercicio> response = new InternalResultsResponse<Exercicio>();

		if (!ValidationUtil.isNull(exercicioId))
		{
			// exercicioRequest.getExercicio().setCdacad(exercicioId);
			// response.addResult(exercicioRequest.getExercicio());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.exercicio.dac.IExercicioDAC#updateExercicio(com.sensus.dm.common.exercicio.model.request.
	 * ExercicioRequest)
	 */
	@Override
	public InternalResultsResponse<Exercicio> updateExercicio(ExercicioRequest exercicioRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE11);

		// paramMap.put("cdacad", exercicioRequest.getExercicio().getCdacad());
		// paramMap.put("academ", exercicioRequest.getExercicio().getAcadem());
		// paramMap.put("lograd", exercicioRequest.getExercicio().getLograd());
		// paramMap.put("numen", exercicioRequest.getExercicio().getNumen());
		// paramMap.put("bairr", exercicioRequest.getExercicio().getBairr());
		// paramMap.put("cidade", exercicioRequest.getExercicio().getCidade());
		// paramMap.put("cep", exercicioRequest.getExercicio().getCep());
		// paramMap.put("telef", exercicioRequest.getExercicio().getTelef());
		// paramMap.put("dataini", exercicioRequest.getExercicio().getDataini());
		// paramMap.put("dataFin", exercicioRequest.getExercicio().getDatafin());
		// paramMap.put("createDate", exercicioRequest.getExercicio().getCreatedate());
		// paramMap.put("createUser", exercicioRequest.getExercicio().getCreateuser());
		// paramMap.put("tenantid", exercicioRequest.getExercicio().getTenantid());
		// paramMap.put("userid", exercicioRequest.getExercicio().getUserid());
		// paramMap.put("atual", exercicioRequest.getExercicio().getAtual());

		String result = (String)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_EXERCICIO, paramMap);

		InternalResultsResponse<Exercicio> response = new InternalResultsResponse<Exercicio>();

		if (ValidationUtil.isNull(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add((Exercicio)exercicioRequest.getExercicios());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.exercicio.dac.IExercicioDAC#fetchAllExercicios(com.sensus.dm.common.exercicio.model.request.
	 * InquiryExercicioRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Exercicio> fetchAllExercicios(InquiryExercicioRequest inquiryExercicioRequest)
	{
		InternalResultsResponse<Exercicio> response = new InternalResultsResponse<Exercicio>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(SELECTION_PAGINATION_IDS, inquiryExercicioRequest.getSelectionPaginationIds());
		paramMap.put(PAGINATION_ALL_SELECTED, inquiryExercicioRequest.getPaginationAllSelected());
		paramMap.put(TENANTID, inquiryExercicioRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryExercicioRequest.getPageSize());
		paramMap.put(START_PAGE, inquiryExercicioRequest.getStartPage());
		paramMap.put(START_ROW, inquiryExercicioRequest.getStartRow());
		paramMap.put(ORDERBY, "cdacad");

		if (!ValidationUtil.isNullOrEmpty(inquiryExercicioRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, "cdacad");
		}

		doQueryForList(getSqlSession(), FETCH_ALL_EXERCICIOS, paramMap, response);
		// Integer totalRows =
		// (Integer)doQueryForObject(getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryExercicioRequest);
		// response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;

	}

	@Override
	public InternalResultsResponse<Exercicio> fetchExerciciosByName(ExercicioRequest exercicioRequest)
	{
		InternalResultsResponse<Exercicio> response = new InternalResultsResponse<Exercicio>();
		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_DEVICES_BY_EXERCICIOS, exercicioRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Exercicio> fetchExercicioById(InquiryExercicioRequest inquiryExercicioRequest)
	{
		InternalResultsResponse<Exercicio> response = new InternalResultsResponse<Exercicio>();
		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_SCHEDULE_BY_ID, inquiryExercicioRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Exercicio> fetchAllExercicioByUser(InquiryExercicioRequest inquiryExercicioRequest)
	{
		InternalResultsResponse<Exercicio> response = new InternalResultsResponse<Exercicio>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(SELECTION_PAGINATION_IDS, inquiryExercicioRequest.getSelectionPaginationIds());
		paramMap.put(PAGINATION_ALL_SELECTED, inquiryExercicioRequest.getPaginationAllSelected());
		paramMap.put(TENANTID, inquiryExercicioRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryExercicioRequest.getPageSize());
		paramMap.put(START_PAGE, inquiryExercicioRequest.getStartPage());
		paramMap.put(START_ROW, inquiryExercicioRequest.getStartRow());
		// paramMap.put(USER, inquiryExercicioRequest.getExercicios().get(0).getCreateuser());
		paramMap.put(ORDERBY, "cdacad");

		if (!ValidationUtil.isNullOrEmpty(inquiryExercicioRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, "cdacad");
		}

		doQueryForList(getSqlSession(), FETCH_ALL_EXERCICIO_BY_USER, paramMap, response);
		Integer totalRows =
				(Integer)doQueryForObject(getSqlSession(), "PaginationTotalRowsByUser", paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;

	}

}
