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
import com.sensus.mlc.endereco.dac.IEnderecoDAC;
import com.sensus.mlc.endereco.model.Endereco;
import com.sensus.mlc.endereco.model.EnderecoOrderByEnum;
import com.sensus.mlc.endereco.model.request.InquiryEnderecoRequest;
import com.sensus.mlc.endereco.model.request.EnderecoRequest;

/** 
 * The Class EnderecoDACImpl.
 */ 
public class EnderecoDACImpl extends SqlSessionDaoSupport implements IEnderecoDAC
{ 

	/** The Constant ENDERECO_NAMESPACE. */ 
	private static final String ENDERECO_NAMESPACE = "Endereco.";

	/** The Constant FETCH_ENDERECO_BY_ID. */ 
	private static final String FETCH_ENDERECO_BY_ID = ENDERECO_NAMESPACE + "fetchEnderecoById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = ENDERECO_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_ENDERECO. */ 
	private static final String DELETE_SMART_POINT_FROM_ENDERECO = ENDERECO_NAMESPACE + "deleteSmartPointFromEndereco";

	/** The Constant FETCH_ENDERECO_BY_ID. */ 
	private static final String FETCH_ENDERECO_BY_ID = ENDERECO_NAMESPACE + "fetchEnderecoById";

	/** The Constant FETCH_ENDERECO_NAME_BY_ID. */ 
	private static final String FETCH_ENDERECO_NAME_BY_ID = ENDERECO_NAMESPACE + "fetchEnderecoNameById";

	/** The Constant UPDATE_AUTO_ENDERECO_ENDERECO. */ 
	private static final String UPDATE_AUTO_ENDERECO_ENDERECO = ENDERECO_NAMESPACE + "updateAutoEnderecoEndereco";

	/** The Constant DELETE_ENDERECO. */ 
	private static final String DELETE_ENDERECO = ENDERECO_NAMESPACE + "deleteEndereco";

	/** The Constant FETCH_ENDERECOS_BY_LIGHT_ID. */ 
	private static final String FETCH_ENDERECOS_BY_LIGHT_ID = ENDERECO_NAMESPACE + "fetchEnderecosByLightId";

	/** The Constant FETCH_ENDERECOS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_ENDERECOS_BY_SMART_POINT_ID = ENDERECO_NAMESPACE + "fetchEnderecosBySmartPointId";

	/** The Constant INSERT_ENDERECO. */ 
	private static final String INSERT_ENDERECO = ENDERECO_NAMESPACE + "insertEndereco";

	/** The Constant INSERT_SMART_POINT_TO_ENDERECO. */ 
	private static final String INSERT_SMART_POINT_TO_ENDERECO = ENDERECO_NAMESPACE + "insertSmartPointToEndereco";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_ENDERECO = ENDERECO_NAMESPACE + "fetchLightsBelongEndereco";

	/** The Constant FETCH_ALL_ENDERECOS. */ 
	private static final String FETCH_ALL_ENDERECOS = ENDERECO_NAMESPACE + "fetchAllEnderecos";

	/** The Constant ENDERECOID. */ 
	private static final String ENDERECO_ID = "enderecoId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant ENDERECONAME. */ 
	private static final String ENDERECONAME = "endereconame";

	/** The Constant AUTOENDERECO. */ 
	private static final String AUTOENDERECO = "autoendereco";

	/** The Constant AUTOENDERECO. */ 
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
	 * @see com.sensus.mlc.endereco.dao.IEnderecoDAO#fetchAllEnderecos(com.sensus.mlc.endereco.model.request.InquiryEnderecoRequest)
	 */ 
	@Override
	public InternalResultsResponse<Endereco> fetchAllEndereco(InquiryEnderecoRequest inquiryEnderecoRequest)
	{
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryEnderecoRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryEnderecoRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryEnderecoRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryEnderecoRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryEnderecoRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryEnderecoRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryEnderecoRequest.getEnderecos()))
		{
			paramMap.put(ENDERECO_ID, inquiryEnderecoRequest.getEnderecos().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_ENDERECOS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.endereco.dao.IEnderecoDAO#fetchEnderecoById(com.sensus.mlc.endereco.model.Endereco
	 */ 
	@Override
	public InternalResultsResponse<Endereco> fetchEnderecoByName(EnderecoRequest enderecoRequest)
	{ 
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();
		doQueryForList(getSqlSession(), FETCH_ENDERECO_BY_ID, enderecoRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.endereco.dao.IEnderecoDAO#insertEndereco(com.sensus.mlc.endereco.model.request.EnderecoRequest)
	 */ 
	@Override
	public InternalResultsResponse<Endereco> insertEndereco(EnderecoRequest enderecoRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Endereco endereco = enderecoRequest.getEndereco();

		endereco.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_ENDERECO, enderecoRequest));
   endereco.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, enderecoRequest));
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();
		response.addResult(endereco);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.endereco.dao.IEnderecoDAO#deleteEndereco(com.sensus.mlc.endereco.model.request.EnderecoRequest)
	 */ 
	@Override
	public InternalResponse deleteEndereco(EnderecoRequest enderecoRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_ENDERECO, enderecoRequest.getEndereco(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.endereco.dao.IEnderecoDAO#fetchEnderecoById(com.sensus.mlc.endereco.model.request.EnderecoRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Endereco> fetchEnderecoById(EnderecoRequest enderecoRequest) 
	{
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();
		doQueryForList(getSqlSession(), FETCH_ENDERECO_BY_ID, enderecoRequest.getEndereco(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.endereco.dac.IEnderecoDAC#fetchEnderecoNameById(com.sensus.mlc.endereco.model.request.EnderecoRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Endereco> fetchEnderecoNameById(EnderecoRequest enderecoRequest)
	{
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();
		doQueryForList(getSqlSession(), FETCH_ENDERECO_NAME_BY_ID, enderecoRequest.getEndereco(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.endereco.dac.IEnderecoDAC#updateEndereco(com.sensus.mlc.endereco.model.request.EnderecoRequest)
	 */ 
	@Override 
	public InternalResponse updateEndereco(EnderecoRequest enderecoRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_ENDERECO, enderecoRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_ENDERECOVALIDATOR_ENDERECO_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


