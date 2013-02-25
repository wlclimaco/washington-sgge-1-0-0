package com.sensus.mlc.endereco.dac.mybatis;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;


import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;
import static com.sensus.mlc.base.util.LCConvertUtil.convertAllowedGroupIdsToStringList;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getParametersToFetchAllLights;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DuplicateKeyException;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.endereco.dac.IEnderecoDAC;
import com.sensus.mlc.endereco.model.Endereco;
import com.sensus.mlc.endereco.model.request.EnderecoRequest;
import com.sensus.mlc.endereco.model.request.InquiryEnderecoRequest;
import com.sensus.mlc.endereco.model.response.EnderecoResponse;
import com.sensus.mlc.group.dac.IGroupDAC;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.GroupOrderByEnum;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class ActionDACImpl.
 *
 * @author - QAT Brazil.
 *
 */
public class EnderecoDACImpl extends SqlSessionDaoSupport implements IEnderecoDAC
{

	/** The Constant PARAMSIZE7. */

	/** The Constant PARAMSIZE1. */

	/** The Constant TAG_NAMESPACE. */
	private static final String TAG_NAMESPACE = "Endereco.";

	/** The Constant FETCH_ALL_TAGS. */
	private static final String FETCH_ALL_EMPRESAS = TAG_NAMESPACE + "fetchAllEnderecos";

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

	private static final String CODEND ="CODEND" ;
	private static final String ENDERECO="ENDERECO";
	private static final String NUMERO="NUMERO";
	private static final String COMPLEMENTO="COMPLEMENTO";
	private static final String BAIRRO ="BAIRRO" ;
	private static final String CEP ="CEP" ;
	private static final String CIDADE ="CIDADE" ;
	private static final String UF ="UF" ;
	private static final String DDD ="DDD" ;
	private static final String FONE1 ="FONE1" ;
	private static final String FONE2 ="FONE2" ;
	private static final String EMAIL ="EMAIL" ;
	private static final String SITE ="SITE" ;
	private static final String CEL1 ="CEL1" ;
	private static final String CEL2 ="CEL2" ;
	private static final String CODMUNIC = "CODMUNIC";
	private static final String SIGLAUF = "SIGLAUF";
	private static final String CODPAIS = "CODPAIS";
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


	@Override
	public InternalResultsResponse<Endereco> insertEndereco(EnderecoRequest enderecoRequest)
	{
		Date date = new Date();
		Map<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE32);
		Endereco endereco = enderecoRequest.getEndereco();
		paramMap.put(CODEND, enderecoRequest.getEndereco().getCodend());
		paramMap.put(ENDERECO, enderecoRequest.getEndereco().getEndereco());
		paramMap.put(NUMERO, enderecoRequest.getEndereco().getNumero());
		paramMap.put(COMPLEMENTO, enderecoRequest.getEndereco().getComplemento());
		paramMap.put(BAIRRO, enderecoRequest.getEndereco().getBairro());
		paramMap.put(CEP, enderecoRequest.getEndereco().getCep());
		paramMap.put(CIDADE, enderecoRequest.getEndereco().getCidade());
		paramMap.put(UF, enderecoRequest.getEndereco().getUf());
		paramMap.put(DDD, enderecoRequest.getEndereco().getDdd());
		paramMap.put(FONE1, enderecoRequest.getEndereco().getFone1());
		paramMap.put(FONE2, enderecoRequest.getEndereco().getFone2());
		paramMap.put(EMAIL, enderecoRequest.getEndereco().getEmail());
		paramMap.put(SITE, enderecoRequest.getEndereco().getSite());
		paramMap.put(CEL1, enderecoRequest.getEndereco().getCel1());
		paramMap.put(CEL2, enderecoRequest.getEndereco().getCel2());
		paramMap.put(CODPAIS, enderecoRequest.getEndereco().getCodpais());
		paramMap.put(CODMUNIC, enderecoRequest.getEndereco().getCodmunic());
		paramMap.put(SIGLAUF, enderecoRequest.getEndereco().getSiglauf());
		paramMap.put(DTINS, enderecoRequest.getEndereco().getDtins());
		paramMap.put(HINS, enderecoRequest.getEndereco().getHins());
		paramMap.put(IDUSUINS, enderecoRequest.getEndereco().getIdusuins());
		paramMap.put(DTALT, enderecoRequest.getEndereco().getDtalt());
		paramMap.put(HALT, enderecoRequest.getEndereco().getHalt());
		paramMap.put(IDUSUALT, enderecoRequest.getEndereco().getIdusualt());

		// Define user from context.
		enderecoRequest.getEndereco().setCreateUser(enderecoRequest.getUserContext().getUserId());

		paramMap.put(UNSELECTION_PAGINATION_IDS, null);

		if (!ValidationUtil.isNullOrEmpty(enderecoRequest.getUnselectionPaginationIds()))
		{
			paramMap.put(UNSELECTION_PAGINATION_IDS, enderecoRequest.getUnselectionPaginationIds());
		}

		endereco.setCodend((Integer)doQueryForObject(getSqlSession(), INSERT_TAG, paramMap));
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();
		response.addResult(endereco);
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Endereco> fetchAllEndereco(InquiryEnderecoRequest inquiryenderecoRequest)
	{
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID, inquiryenderecoRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryenderecoRequest.getPageSize());
		paramMap.put(START_ROW, inquiryenderecoRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryenderecoRequest.getStartPage());
//		paramMap.put(ORDERBY, TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryenderecoRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryenderecoRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryenderecoRequest.getEnderecos()))
		{
			paramMap.put(EMPRESA_ID, inquiryenderecoRequest.getEnderecos().get(0).getCodend());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_EMPRESAS, paramMap, response);

//		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
//				PAGINATION_TOTAL_ROWS, paramMap);

	//	response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	@Override
	public InternalResultsResponse<Endereco> fetchEnderecoById(EnderecoRequest enderecoRequest)
	{
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();

		// response.getResultsList().addAll(
		// getSqlSession().selectList("EnderecoMap.fetchEnderecoById", enderecoRequest.getEndereco()));

		return response;
	}

	@Override
	public InternalResponse generateFileCSV(InquiryEnderecoRequest inquiryEnderecoRequest)
	{
		InternalResponse response = new InternalResponse();

		//preapreDataToWriteFile(inquiryEnderecoRequest.getEnderecos());

		// if (GenerateFileCSV.generateCSVFile(inquiryEnderecoRequest.getFileName(), excelData))
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
	public InternalResultsResponse<Endereco> updateEndereco(
			EnderecoRequest enderecoRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteEndereco(EnderecoRequest enderecoRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnderecoResponse fetchAllEnderecoTypes(InquiryEnderecoRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnderecoResponse fetchAllEnderecoFilial(
			InquiryEnderecoRequest request) {
		// TODO Auto-generated method stub
		return null;
	}


}