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
import com.sensus.mlc.gestao.dac.IBairroDAC;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.Bairro;
import com.sensus.mlc.gestao.model.request.BairroRequest;
import com.sensus.mlc.gestao.model.request.InquiryBairroRequest;
import com.sensus.mlc.gestao.model.response.BairroResponse;
import com.sensus.mlc.tag.model.TagOrderByEnum;

/**
 * The Class BairroDACImpl.
 */
public class BairroDACImpl extends SqlSessionDaoSupport implements IBairroDAC
{

	/** The Constant BAIRRO_NAMESPACE. */
	private static final String BAIRRO_NAMESPACE = "Bairro.";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = BAIRRO_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_BAIRRO. */
	private static final String DELETE_SMART_POINT_FROM_BAIRRO = BAIRRO_NAMESPACE + "deleteSmartPointFromBairro";

	/** The Constant FETCH_BAIRRO_NAME_BY_ID. */
	private static final String FETCH_BAIRRO_NAME_BY_ID = BAIRRO_NAMESPACE + "fetchBairroNameById";

	/** The Constant UPDATE_AUTO_BAIRRO_BAIRRO. */
	private static final String UPDATE_AUTO_BAIRRO_BAIRRO = BAIRRO_NAMESPACE + "updateAutoBairroBairro";

	/** The Constant DELETE_BAIRRO. */
	private static final String DELETE_BAIRRO = BAIRRO_NAMESPACE + "deleteBairro";

	/** The Constant FETCH_BAIRROS_BY_LIGHT_ID. */
	private static final String FETCH_BAIRROS_BY_LIGHT_ID = BAIRRO_NAMESPACE + "fetchBairrosByLightId";

	/** The Constant FETCH_BAIRROS_BY_SMART_POINT_ID. */
	private static final String FETCH_BAIRROS_BY_SMART_POINT_ID = BAIRRO_NAMESPACE + "fetchBairrosBySmartPointId";

	/** The Constant INSERT_BAIRRO. */
	private static final String INSERT_BAIRRO = BAIRRO_NAMESPACE + "insertBairro";

	/** The Constant INSERT_SMART_POINT_TO_BAIRRO. */
	private static final String INSERT_SMART_POINT_TO_BAIRRO = BAIRRO_NAMESPACE + "insertSmartPointToBairro";

	/** The Constant FETCH_LIGHTS_BELONG. */
	private static final String FETCH_LIGHTS_BELONG_BAIRRO = BAIRRO_NAMESPACE + "fetchLightsBelongBairro";

	/** The Constant FETCH_ALL_BAIRROS. */
	private static final String FETCH_ALL_BAIRROS = BAIRRO_NAMESPACE + "fetchAllBairros";

	/** The Constant BAIRROID. */
	private static final String BAIRRO_ID = "bairroId";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant BAIRRONAME. */
	private static final String BAIRRONAME = "bairroname";

	/** The Constant AUTOBAIRRO. */
	private static final String AUTOBAIRRO = "autobairro";

	/** The Constant AUTOBAIRRO. */
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

	private static final String UPDATE_BAIRRO = null;

	private static final String SENSUS_MLC_BAIRROVALIDATOR_BAIRRO_ALREADY_EXISTS = null;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.dao.IBairroDAO#fetchAllBairros(com.sensus.mlc.bairro.model.request.InquiryBairroRequest)
	 */
	@Override
	public InternalResultsResponse<Bairro> fetchAllBairro(InquiryBairroRequest inquiryBairroRequest)
	{
		InternalResultsResponse<Bairro> response = new InternalResultsResponse<Bairro>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryBairroRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryBairroRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryBairroRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryBairroRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryBairroRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryBairroRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryBairroRequest.getBairro()))
		{
			paramMap.put(BAIRRO_ID, inquiryBairroRequest.getBairro().get(0).getCodbairro());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_BAIRROS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.dao.IBairroDAO#insertBairro(com.sensus.mlc.bairro.model.request.BairroRequest)
	 */
	@Override
	public InternalResultsResponse<Bairro> insertBairro(BairroRequest bairroRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Bairro bairro = bairroRequest.getBairro();

		bairro.setCodbairro((Integer)doQueryForObject(getSqlSession(), INSERT_BAIRRO, bairroRequest));
        bairro.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_BAIRRO, bairroRequest));
		InternalResultsResponse<Bairro> response = new InternalResultsResponse<Bairro>();
		response.addResult(bairro);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.dao.IBairroDAO#deleteBairro(com.sensus.mlc.bairro.model.request.BairroRequest)
	 */
	@Override
	public InternalResponse deleteBairro(BairroRequest bairroRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_BAIRRO, bairroRequest.getBairro(), response);
		return response;
	}


	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.dao.IBairroDAO#fetchBairroById(com.sensus.mlc.bairro.model.request.BairroRequest)
	 */
	@Override
	public InternalResultsResponse<Bairro> fetchBairroById(BairroRequest bairroRequest)
	{
		InternalResultsResponse<Bairro> response = new InternalResultsResponse<Bairro>();
		doQueryForList(getSqlSession(), FETCH_BAIRRO_NAME_BY_ID, bairroRequest.getBairro(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.dac.IBairroDAC#updateBairro(com.sensus.mlc.bairro.model.request.BairroRequest)
	 */
	@Override
	public InternalResponse updateBairro(BairroRequest bairroRequest)
	{
		InternalResponse response = new InternalResponse();
		try
		{
			doQueryForObject(getSqlSession(), UPDATE_BAIRRO, bairroRequest);
		}
		catch (DuplicateKeyException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_BAIRROVALIDATOR_BAIRRO_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

	@Override
	public InternalResponse generateFileCSV(
			InquiryBairroRequest inquiryBairroRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BairroResponse fetchAllBairroTypes(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BairroResponse fetchAllBairroFilial(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}


