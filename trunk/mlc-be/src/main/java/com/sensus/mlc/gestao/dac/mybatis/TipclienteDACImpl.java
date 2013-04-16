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
import com.sensus.mlc.gestao.dac.ITipclienteDAC;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.Tipcliente;
import com.sensus.mlc.gestao.model.request.InquiryTipclienteRequest;
import com.sensus.mlc.gestao.model.request.TipclienteRequest;
import com.sensus.mlc.gestao.model.response.TipclienteResponse;
import com.sensus.mlc.tag.model.TagOrderByEnum;

/**
 * The Class TipclienteDACImpl.
 */
public class TipclienteDACImpl extends SqlSessionDaoSupport implements ITipclienteDAC
{

	/** The Constant TIPCLI_NAMESPACE. */
	private static final String TIPCLI_NAMESPACE = "Tipcliente.";


	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = TIPCLI_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_TIPCLI. */
	private static final String DELETE_SMART_POINT_FROM_TIPCLI = TIPCLI_NAMESPACE + "deleteSmartPointFromTipcliente";

	/** The Constant FETCH_TIPCLI_BY_ID. */
	private static final String FETCH_TIPCLI_BY_ID = TIPCLI_NAMESPACE + "fetchTipclienteById";

	/** The Constant FETCH_TIPCLI_NAME_BY_ID. */
	private static final String FETCH_TIPCLI_NAME_BY_ID = TIPCLI_NAMESPACE + "fetchTipclienteNameById";

	/** The Constant UPDATE_AUTO_TIPCLI_TIPCLI. */
	private static final String UPDATE_AUTO_TIPCLI_TIPCLI = TIPCLI_NAMESPACE + "updateAutoTipclienteTipcliente";

	/** The Constant DELETE_TIPCLI. */
	private static final String DELETE_TIPCLI = TIPCLI_NAMESPACE + "deleteTipcliente";

	/** The Constant FETCH_TIPCLIS_BY_LIGHT_ID. */
	private static final String FETCH_TIPCLIS_BY_LIGHT_ID = TIPCLI_NAMESPACE + "fetchTipclientesByLightId";

	/** The Constant FETCH_TIPCLIS_BY_SMART_POINT_ID. */
	private static final String FETCH_TIPCLIS_BY_SMART_POINT_ID = TIPCLI_NAMESPACE + "fetchTipclientesBySmartPointId";

	/** The Constant INSERT_TIPCLI. */
	private static final String INSERT_TIPCLI = TIPCLI_NAMESPACE + "insertTipcliente";

	/** The Constant INSERT_SMART_POINT_TO_TIPCLI. */
	private static final String INSERT_SMART_POINT_TO_TIPCLI = TIPCLI_NAMESPACE + "insertSmartPointToTipcliente";

	/** The Constant FETCH_LIGHTS_BELONG. */
	private static final String FETCH_LIGHTS_BELONG_TIPCLI = TIPCLI_NAMESPACE + "fetchLightsBelongTipcliente";

	/** The Constant FETCH_ALL_TIPCLIS. */
	private static final String FETCH_ALL_TIPCLIS = TIPCLI_NAMESPACE + "fetchAllTipclientes";

	/** The Constant TIPCLIID. */
	private static final String TIPCLI_ID = "tipcliId";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant TIPCLINAME. */
	private static final String TIPCLINAME = "tipcliname";

	/** The Constant AUTOTIPCLI. */
	private static final String AUTOTIPCLI = "autotipcli";

	/** The Constant AUTOTIPCLI. */
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


	private static final String INSERT_AUDITORIA = null;


	private static final String UPDATE_TIPCLI = null;


	private static final String SENSUS_MLC_TIPCLIVALIDATOR_TIPCLI_ALREADY_EXISTS = null;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tipcli.dao.ITipclienteDAO#fetchAllTipclientes(com.sensus.mlc.tipcli.model.request.InquiryTipclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Tipcliente> fetchAllTipcliente(InquiryTipclienteRequest inquiryTipclienteRequest)
	{
		InternalResultsResponse<Tipcliente> response = new InternalResultsResponse<Tipcliente>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryTipclienteRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryTipclienteRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryTipclienteRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryTipclienteRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryTipclienteRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryTipclienteRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryTipclienteRequest.getTipcliente()))
		{
			paramMap.put(TIPCLI_ID, inquiryTipclienteRequest.getTipcliente().get(0).getCodtipocli());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_TIPCLIS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tipcli.dao.ITipclienteDAO#insertTipcliente(com.sensus.mlc.tipcli.model.request.TipclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Tipcliente> insertTipcliente(TipclienteRequest tipcliRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Tipcliente tipcli = tipcliRequest.getTipcliente();

		tipcli.setCodtipocli((Integer)doQueryForObject(getSqlSession(), INSERT_TIPCLI, tipcliRequest));
        tipcli.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, tipcliRequest));
		InternalResultsResponse<Tipcliente> response = new InternalResultsResponse<Tipcliente>();
		response.addResult(tipcli);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tipcli.dao.ITipclienteDAO#deleteTipcliente(com.sensus.mlc.tipcli.model.request.TipclienteRequest)
	 */
	@Override
	public InternalResponse deleteTipcliente(TipclienteRequest tipcliRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_TIPCLI, tipcliRequest.getTipcliente(), response);
		return response;
	}


	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tipcli.dao.ITipclienteDAO#fetchTipclienteById(com.sensus.mlc.tipcli.model.request.TipclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Tipcliente> fetchTipclienteById(TipclienteRequest tipcliRequest)
	{
		InternalResultsResponse<Tipcliente> response = new InternalResultsResponse<Tipcliente>();
		doQueryForList(getSqlSession(), FETCH_TIPCLI_BY_ID, tipcliRequest.getTipcliente(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tipcli.dac.ITipclienteDAC#updateTipcliente(com.sensus.mlc.tipcli.model.request.TipclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Tipcliente> updateTipcliente(TipclienteRequest tipcliRequest)
	{
		InternalResultsResponse<Tipcliente> response = new InternalResultsResponse<Tipcliente>();
		try
		{
			doQueryForObject(getSqlSession(), UPDATE_TIPCLI, tipcliRequest);
		}
		catch (DuplicateKeyException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_TIPCLIVALIDATOR_TIPCLI_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

	@Override
	public InternalResponse generateFileCSV(
			InquiryTipclienteRequest inquiryTipclienteRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipclienteResponse fetchAllTipclienteTypes(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipclienteResponse fetchAllTipclienteFilial(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}


