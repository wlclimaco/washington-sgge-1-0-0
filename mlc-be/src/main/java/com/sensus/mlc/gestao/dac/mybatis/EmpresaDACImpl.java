package com.sensus.mlc.gestao.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DuplicateKeyException;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.gestao.dac.IEmpresaDAC;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.request.EmpresaRequest;
import com.sensus.mlc.gestao.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.gestao.model.response.EmpresaResponse;
import com.sensus.mlc.tag.model.TagOrderByEnum;

/**
 * The Class EmpresaDACImpl.
 */
public class EmpresaDACImpl extends SqlSessionDaoSupport implements IEmpresaDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "Empresa.";

	/** The Constant FETCH_EMPRESA_BY_NAME. */
	private static final String FETCH_EMPRESA_BY_NAME = EMPRESA_NAMESPACE + "fetchEmpresaByName";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = EMPRESA_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_EMPRESA. */
	private static final String DELETE_SMART_POINT_FROM_EMPRESA = EMPRESA_NAMESPACE + "deleteSmartPointFromEmpresa";

	/** The Constant FETCH_EMPRESA_BY_ID. */
	private static final String FETCH_EMPRESA_BY_ID = EMPRESA_NAMESPACE + "fetchEmpresaById";

	/** The Constant FETCH_EMPRESA_NAME_BY_ID. */
	private static final String FETCH_EMPRESA_NAME_BY_ID = EMPRESA_NAMESPACE + "fetchEmpresaNameById";

	/** The Constant UPDATE_AUTO_EMPRESA_EMPRESA. */
	private static final String UPDATE_AUTO_EMPRESA_EMPRESA = EMPRESA_NAMESPACE + "updateAutoEmpresaEmpresa";

	/** The Constant DELETE_EMPRESA. */
	private static final String DELETE_EMPRESA = EMPRESA_NAMESPACE + "deleteEmpresa";

	/** The Constant FETCH_EMPRESAS_BY_LIGHT_ID. */
	private static final String FETCH_EMPRESAS_BY_LIGHT_ID = EMPRESA_NAMESPACE + "fetchEmpresasByLightId";

	/** The Constant FETCH_EMPRESAS_BY_SMART_POINT_ID. */
	private static final String FETCH_EMPRESAS_BY_SMART_POINT_ID = EMPRESA_NAMESPACE + "fetchEmpresasBySmartPointId";

	/** The Constant INSERT_EMPRESA. */
	private static final String INSERT_EMPRESA = EMPRESA_NAMESPACE + "insertEmpresa";

	/** The Constant INSERT_SMART_POINT_TO_EMPRESA. */
	private static final String INSERT_SMART_POINT_TO_EMPRESA = EMPRESA_NAMESPACE + "insertSmartPointToEmpresa";

	/** The Constant FETCH_LIGHTS_BELONG. */
	private static final String FETCH_LIGHTS_BELONG_EMPRESA = EMPRESA_NAMESPACE + "fetchLightsBelongEmpresa";

	/** The Constant FETCH_ALL_EMPRESAS. */
	private static final String FETCH_ALL_EMPRESAS = EMPRESA_NAMESPACE + "fetchAllEmpresas";

	/** The Constant EMPRESAID. */
	private static final String EMPRESA_ID = "empresaId";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant EMPRESANAME. */
	private static final String EMPRESANAME = "empresaname";

	/** The Constant AUTOEMPRESA. */
	private static final String AUTOEMPRESA = "autoempresa";

	/** The Constant AUTOEMPRESA. */
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

	private static final String UPDATE_EMPRESA = null;

	private static final String SENSUS_MLC_EMPRESAVALIDATOR_EMPRESA_ALREADY_EXISTS = null;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.empresa.dao.IEmpresaDAO#fetchAllEmpresas(com.sensus.mlc.empresa.model.request.InquiryEmpresaRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchAllEmpresa(InquiryEmpresaRequest inquiryEmpresaRequest)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID, inquiryEmpresaRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryEmpresaRequest.getPageSize());
		paramMap.put(START_ROW, inquiryEmpresaRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryEmpresaRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryEmpresaRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryEmpresaRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryEmpresaRequest.getEmpresa()))
		{
			paramMap.put(EMPRESA_ID, inquiryEmpresaRequest.getEmpresa().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_EMPRESAS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}


	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.empresa.dao.IEmpresaDAO#insertEmpresa(com.sensus.mlc.empresa.model.request.EmpresaRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> insertEmpresa(EmpresaRequest empresaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Empresa empresa = empresaRequest.getEmpresa();


		empresa.setCodemp((Integer)doQueryForObject(getSqlSession(), INSERT_EMPRESA, empresaRequest));
		empresa.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, empresaRequest));
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		response.addResult(empresa);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.empresa.dao.IEmpresaDAO#deleteEmpresa(com.sensus.mlc.empresa.model.request.EmpresaRequest)
	 */
	@Override
	public InternalResponse deleteEmpresa(EmpresaRequest empresaRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_EMPRESA, empresaRequest.getEmpresa(), response);
		return response;
	}


	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.empresa.dao.IEmpresaDAO#fetchEmpresaById(com.sensus.mlc.empresa.model.request.EmpresaRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresaById(EmpresaRequest empresaRequest)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		doQueryForList(getSqlSession(), FETCH_EMPRESA_BY_ID, empresaRequest.getEmpresa(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.empresa.dac.IEmpresaDAC#updateEmpresa(com.sensus.mlc.empresa.model.request.EmpresaRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> updateEmpresa(EmpresaRequest empresaRequest)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		try
		{
			doQueryForObject(getSqlSession(), UPDATE_EMPRESA, empresaRequest);
		}
		catch (DuplicateKeyException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_EMPRESAVALIDATOR_EMPRESA_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

	@Override
	public InternalResponse generateFileCSV(
			InquiryEmpresaRequest inquiryEmpresaRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaResponse fetchAllEmpresaTypes(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaResponse fetchAllEmpresaFilial(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}


