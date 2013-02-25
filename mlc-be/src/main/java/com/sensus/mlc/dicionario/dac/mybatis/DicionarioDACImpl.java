package com.sensus.mlc.dicionario.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.SensusModel;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.dicionario.dac.IDicionarioDAC;
import com.sensus.mlc.dicionario.model.Tela;
import com.sensus.mlc.dicionario.model.request.TelaRequest;
import com.sensus.mlc.dicionario.model.request.InquiryTelaRequest;
import com.sensus.mlc.empresa.model.Empresa;

/**
 * The Class GroupDACImpl.
 *
 * @author Gustavo Aragao - QAT.
 */
public class DicionarioDACImpl extends SqlSessionDaoSupport implements IDicionarioDAC
{

	/** The Constant PARAMSIZE7. */

	/** The Constant PARAMSIZE1. */

	/** The Constant TAG_NAMESPACE. */
	private static final String TAG_NAMESPACE = "Dicionario.";

	/** The Constant FETCH_ALL_TAGS. */
	private static final String FETCH_ALL_EMPRESAS = TAG_NAMESPACE + "fetchTelaById";

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
	private static final String PAGINATION_TOTAL_ROWS = TAG_NAMESPACE + "PaginationTotalRows";

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
	private static final String INSERT_SMART_POINT_TO_TAG = TAG_NAMESPACE + "insertSmartPointToTag";

	/** The Constant INSERT_TAG. */
	private static final String INSERT_TAG = TAG_NAMESPACE + "insertTag";

	private static final String CODEMP = "CODEMP";
	private static final String RAZEMP = "RAZEMP";
	private static final String NOMEEMP = "NOMEEMP";
	private static final String CNPJEMP = "CNPJEMP";
	private static final String INSCEMP = "INSCEMP";
	private static final String ENDEMP = "ENDEMP";
	private static final String NUMEMP = "NUMEMP";
	private static final String COMPLEMP = "COMPLEMP";
	private static final String BAIREMP = "BAIREMP";
	private static final String CEPEMP = "CEPEMP";
	private static final String CIDEMP = "CIDEMP";
	private static final String UFEMP = "UFEMP";
	private static final String DDDEMP = "DDDEMP";
	private static final String FONEEMP = "FONEEMP";
	private static final String FAXEMP = "FAXEMP";
	private static final String EMAILEMP = "EMAILEMP";
	private static final String WWWEMP = "WWWEMP";
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

	private static final String CODTELA = "codTela";

	/**
	 * Prepare data to write file.
	 *
	 * @param actionList the action list
	 * @return the string[][]
	 */
	private String[][] preapreDataToWriteFile(List<Empresa> actionList)
	{
		String[][] excelData = new String[actionList.size() + 1][TOTAL_COLUMN];

		// HEADER
		excelData[0][COLUMN_0] = ACTION_NAME_HEADER;
		excelData[0][COLUMN_1] = ACTION_TYPE_HEADER;
		excelData[0][COLUMN_2] = MODIFIED_BY_HEADER;
		excelData[0][COLUMN_3] = DATE_MODIFIED_HEADER;
		excelData[0][COLUMN_4] = LOCKED_HEADER;

//		for (int i = 1; i <= actionList.size(); i++)
//		{
//			Empresa empresa = actionList.get(i - 1);
//
//			excelData[i][COLUMN_0] = empresa.getCodemp().toString();
//
//			excelData[i][COLUMN_1] = null;
//			if (!ValidationUtil.isNull(empresa.getNomeemp()))
//			{
//				excelData[i][COLUMN_1] = empresa.getRazemp();
//			}
//
//			excelData[i][COLUMN_2] = empresa.getCnpjemp();
//
//			excelData[i][COLUMN_3] = null;
//			if (!ValidationUtil.isNull(empresa.getInscemp()))
//			{
//				excelData[i][COLUMN_3] = empresa.getInscemp();
//			}
//
//			excelData[i][COLUMN_4] = null;
//			if (!ValidationUtil.isNull(empresa.getCodmunic()))
//			{
//				excelData[i][COLUMN_4] = empresa.getWwwemp();
//			}
//		}

		return excelData;
	}

	@Override
	public InternalResultsResponse<Tela> insertTela(TelaRequest dicionarioRequest)
	{
		Date date = new Date();
		Map<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE32);
		Tela tela = (Tela) dicionarioRequest.getTela();
		paramMap.put(CODEMP, tela.getCodTela());

		// Define user from context.
		((SensusModel) dicionarioRequest.getTela()).setCreateUser(dicionarioRequest.getUserContext().getUserId());

		paramMap.put(UNSELECTION_PAGINATION_IDS, null);

		if (!ValidationUtil.isNullOrEmpty(dicionarioRequest.getUnselectionPaginationIds()))
		{
			paramMap.put(UNSELECTION_PAGINATION_IDS, dicionarioRequest.getUnselectionPaginationIds());
		}

		tela.setCodTela((Integer)doQueryForObject(getSqlSession(), INSERT_TAG, paramMap));
		InternalResultsResponse<Tela> response = new InternalResultsResponse<Tela>();
		response.addResult(tela);
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Tela> fetchAllTela(InquiryTelaRequest inquiryDicionarioRequest)
	{
		InternalResultsResponse<Tela> response = new InternalResultsResponse<Tela>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID, inquiryDicionarioRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryDicionarioRequest.getPageSize());
		paramMap.put(START_ROW, inquiryDicionarioRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryDicionarioRequest.getStartPage());
//		paramMap.put(ORDERBY, TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryDicionarioRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryDicionarioRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryDicionarioRequest.getTela()))
		{
			paramMap.put(EMPRESA_ID, inquiryDicionarioRequest.getTela().get(0).getCodTela());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_EMPRESAS, paramMap, response);

//		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
//				PAGINATION_TOTAL_ROWS, paramMap);

	//	response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	@Override
	public InternalResultsResponse<Tela> fetchTelaById(TelaRequest telaRequest)
	{
		InternalResultsResponse<Tela> response = new InternalResultsResponse<Tela>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(CODTELA, telaRequest.getTela().get(0).getCodTela());
		doQueryForList(getSqlSession(), "Dicionario.fetchTelaById", paramMap, response);
		return response;
	}



	@Override
	public InternalResultsResponse<Tela> updateTela(
			TelaRequest dicionarioRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteTela(TelaRequest telaRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}
