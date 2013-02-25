package com.sensus.mlc.filial.dac.mybatis;


import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getParametersToFetchAllLights;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.filial.dac.IFilialDAC;
import com.sensus.mlc.filial.model.Filial;
import com.sensus.mlc.filial.model.request.FilialRequest;
import com.sensus.mlc.filial.model.request.InquiryFilialRequest;
import com.sensus.mlc.filial.model.response.FilialResponse;

/**
 * The Class ActionDACImpl.
 *
 * @author - QAT Brazil.
 *
 */
public class FilialDACImpl extends SqlSessionDaoSupport implements IFilialDAC
{

	/** The Constant PARAMSIZE7. */

	/** The Constant PARAMSIZE1. */

	/** The Constant TAG_NAMESPACE. */
	private static final String FILIAL_NAMESPACE = "Filial.";

	/** The Constant FETCH_ALL_TAGS. */
	private static final String FETCH_ALL_FILIAL = FILIAL_NAMESPACE + "fetchAllFiliais";

	private static final Integer PARAMSIZE1 = 1;

	/** The Constant PARAMSIZE6. */
	private static final Integer PARAMSIZE6 = 6;

	/** The Constant PARAMSIZE6. */
	private static final Integer PARAMSIZE32 = 32;

	/** The Constant PARAMSIZE7. */
	private static final Integer PARAMSIZE7 = 7;

	/** The Constant TOTAL_COLUMN. */
	private static final Integer TOTAL_COLUMN = 5;

	/** The Constant COLUMN_0. */
	private static final Integer COLUMN_0 = 0;

	/** The Constant COLUMN_1. */
	private static final Integer COLUMN_1 = 1;

	/** The Constant COLUMN_2. */
	private static final Integer COLUMN_2 = 2;

	/** The Constant COLUMN_3. */
	private static final Integer COLUMN_3 = 3;

	/** The Constant COLUMN_4. */
	private static final Integer COLUMN_4 = 4;

	/** The Constant ACTION_NAME_HEADER. */
	private static final String ACTION_NAME_HEADER = "Action Name";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = FILIAL_NAMESPACE + "PaginationTotalRows";

	/** The Constant ORDERBY. */
	private static final String ORDERBY = "orderBy";

	/** The Constant GROUPID. */
	private static final String EMPRESA_ID = "codEmp";

	/** The Constant START_PAGE. */
	private static final String START_PAGE = "startPage";

	/** The Constant ACTION_TYPE_HEADER. */
	private static final String ACTION_TYPE_HEADER = "Action Type";

	/** The Constant MODIFIED_BY_HEADER. */
	private static final String MODIFIED_BY_HEADER = "Modified By";

	/** The Constant DATE_MODIFIED_HEADER. */
	private static final String DATE_MODIFIED_HEADER = "Date Modified";

	/** The Constant LOCKED_HEADER. */
	private static final String LOCKED_HEADER = "Locked";

	private static final String TENANT_ID = "tenantId";

	/** The Constant ORDER_BY. */
	private static final String ORDER_BY = "order_by";

	/** The Constant SORT_BY. */
	private static final String SORT_BY = "sort_by";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "page_size";

	/** The Constant START_ROW. */
	private static final String START_ROW = "start_row";

	/** The Constant ACTION_NAME. */
	private static final String ACTION_NAME = "action_name";

	/** The Constant ACTION_TYPES. */
	private static final String ACTION_TYPES = "action_types";

	/** The Constant USERS. */
	private static final String USERS = "users";

	/** The Constant ACTION_ID. */
	private static final String ACTION_ID = "action_id";

	/** The Constant ACTION_TYPE. */
	private static final String ACTION_TYPE = "action_type";

	/** The Constant ACTION_DESCRIPTION. */
	private static final String ACTION_DESCRIPTION = "action_description";

	/** The Constant START_STEP. */
	private static final String START_STEP = "start_step";

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "create_user";

	/** The Constant STEP_LIST. */
	private static final String STEP_LIST = "step_list";

	/** The Constant DEMAND_RESET_STEP_LIST. */
	private static final String DEMAND_RESET_STEP_LIST = "1,2,3,4";

	/** The Constant UNSELECTION_PAGINATION_IDS. */
	private static final String UNSELECTION_PAGINATION_IDS = "unselectionPaginationIds";

	/** The Constant INSERT_SMART_POINT_TO_TAG. */
	private static final String INSERT_SMART_POINT_TO_TAG = FILIAL_NAMESPACE + "insertSmartPointToTag";

	/** The Constant INSERT_TAG. */
	private static final String INSERT_FILIAL = FILIAL_NAMESPACE + "insertFilial";

	private static final String INSERT_ENDERECO = FILIAL_NAMESPACE + "insertEndereco";

	private static final String CODEMP = "CODEMP";
	private static final String RAZEMP = "RAZEMP";
	private static final String NOMEEMP = "NOMEEMP";
	private static final String CNPJEMP = "CNPJEMP";
	private static final String INSCEMP = "INSCEMP";
	private static final String CODEND = "CODEND";
	private static final String CODEANEMP = "CODEANEMP";
	private static final String NOMECONTEMP = "NOMECONTEMP";
	private static final String MULTIALMOXEMP = "MULTIALMOXEMP";
	private static final String FOTOEMP = "FOTOEMP";
	private static final String CODMUNIC = "CODMUNIC";
	private static final String SIGLAUF = "SIGLAUF";
	private static final String CODPAIS = "CODPAIS";
	private static final String PERCISSEMP = "PERCISSEMP";
	private static final String CODPAISEMP = "CODPAISEMP";
	private static final String DTINS = "DTINS";
	private static final String HINS = "HINS";
	private static final String IDUSUINS = "IDUSUINS";
	private static final String DTALT = "DTALT";
	private static final String HALT = "HALT";
	private static final String IDUSUALT = "IDUSUALT";

	/** The Constant SENSUS_EPM_ACTIONVALIDATOR_ACTION_IS_SCHEDULED. */
	private static final String SENSUS_EPM_ACTIONVALIDATOR_ACTION_IS_SCHEDULED =
			"sensus.epm.actionvalidator.action.is_scheduled";

	/** The Constant ADD_ACTION_FAILED. */
	private static final String ADD_ACTION_FAILED = "sensus.epm.actionbclimpl.add.action.failed";

	/**
	 * Prepare data to write file.
	 *
	 * @param actionList the action list
	 * @return the string[][]
	 */
	private String[][] preapreDataToWriteFile(List<Filial> actionList)
	{
		String[][] excelData = new String[actionList.size() + 1][TOTAL_COLUMN];

		// HEADER
		excelData[0][COLUMN_0] = ACTION_NAME_HEADER;
		excelData[0][COLUMN_1] = ACTION_TYPE_HEADER;
		excelData[0][COLUMN_2] = MODIFIED_BY_HEADER;
		excelData[0][COLUMN_3] = DATE_MODIFIED_HEADER;
		excelData[0][COLUMN_4] = LOCKED_HEADER;

		for (int i = 1; i <= actionList.size(); i++)
		{
			Filial filial = actionList.get(i - 1);

			excelData[i][COLUMN_0] = filial.getCodemp().toString();

			excelData[i][COLUMN_1] = null;
			if (!ValidationUtil.isNull(filial.getNomefilial()))
			{
				excelData[i][COLUMN_1] = filial.getRazfilial();
			}

			excelData[i][COLUMN_2] = filial.getCnpjfilial();

			excelData[i][COLUMN_3] = null;
			if (!ValidationUtil.isNull(filial.getInscfilial()))
			{
				excelData[i][COLUMN_3] = filial.getInscmunfilial();
			}

			excelData[i][COLUMN_4] = null;
			if (!ValidationUtil.isNull(filial.getCodmunic()))
			{
				excelData[i][COLUMN_4] = filial.getCodend().toString();
			}
		}

		return excelData;
	}

	@Override
	public InternalResultsResponse<Filial> insertFilial(FilialRequest filialRequest)
	{
		Filial filial = filialRequest.getFilial();

		// Define user from context.
		filialRequest.getFilial().setCreateUser(filialRequest.getUserContext().getUserId());

		filial.getCodend().setCodend( ((Integer)doQueryForObject(getSqlSession(), INSERT_ENDERECO, filialRequest.getFilial())));

		filial.setCodfilial((Integer)doQueryForObject(getSqlSession(), INSERT_FILIAL, filialRequest.getFilial()));

		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
		response.addResult(filial);
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Filial> fetchAllFilial(InquiryFilialRequest inquiryfilialRequest)
	{
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID, inquiryfilialRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryfilialRequest.getPageSize());
		paramMap.put(START_ROW, inquiryfilialRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryfilialRequest.getStartPage());
//		paramMap.put(ORDERBY, TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryfilialRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryfilialRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryfilialRequest.getFilial()))
		{
			paramMap.put(EMPRESA_ID, inquiryfilialRequest.getFilial().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_FILIAL, paramMap, response);

//		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
//				PAGINATION_TOTAL_ROWS, paramMap);

	//	response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	@Override
	public InternalResultsResponse<Filial> fetchFilialById(FilialRequest filialRequest)
	{
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();

		// response.getResultsList().addAll(
		// getSqlSession().selectList("FilialMap.fetchFilialById", filialRequest.getFilial()));

		return response;
	}

	@Override
	public InternalResponse generateFileCSV(InquiryFilialRequest inquiryFilialRequest)
	{
		InternalResponse response = new InternalResponse();

		preapreDataToWriteFile(inquiryFilialRequest.getFilial());

		// if (GenerateFileCSV.generateCSVFile(inquiryFilialRequest.getFileName(), excelData))
		// {
		// response.setStatus(Status.OperationSuccess);
		// }
		// else
		// {
		// response.setStatus(Status.ExceptionError);
		// }

		return response;
	}

	@Override
	public FilialResponse fetchAllFilialTypes(Request request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Filial> updateFilial(
			FilialRequest filialRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteFilial(FilialRequest filialRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilialResponse fetchAllEmpresaFilial(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}