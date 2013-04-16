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
import com.sensus.mlc.gestao.dac.IUfDAC;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.Uf;
import com.sensus.mlc.gestao.model.request.InquiryUfRequest;
import com.sensus.mlc.gestao.model.request.UfRequest;
import com.sensus.mlc.gestao.model.response.UfResponse;
import com.sensus.mlc.tag.model.TagOrderByEnum;

/**
 * The Class UfDACImpl.
 */
public class UfDACImpl extends SqlSessionDaoSupport implements IUfDAC
{

	/** The Constant UF_NAMESPACE. */
	private static final String UF_NAMESPACE = "Uf.";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = UF_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_UF. */
	private static final String DELETE_SMART_POINT_FROM_UF = UF_NAMESPACE + "deleteSmartPointFromUf";

	/** The Constant FETCH_UF_BY_ID. */
	private static final String FETCH_UF_BY_ID = UF_NAMESPACE + "fetchUfById";

	/** The Constant FETCH_UF_NAME_BY_ID. */
	private static final String FETCH_UF_NAME_BY_ID = UF_NAMESPACE + "fetchUfNameById";

	/** The Constant UPDATE_AUTO_UF_UF. */
	private static final String UPDATE_AUTO_UF_UF = UF_NAMESPACE + "updateAutoUfUf";

	/** The Constant DELETE_UF. */
	private static final String DELETE_UF = UF_NAMESPACE + "deleteUf";

	/** The Constant FETCH_UFS_BY_LIGHT_ID. */
	private static final String FETCH_UFS_BY_LIGHT_ID = UF_NAMESPACE + "fetchUfsByLightId";

	/** The Constant FETCH_UFS_BY_SMART_POINT_ID. */
	private static final String FETCH_UFS_BY_SMART_POINT_ID = UF_NAMESPACE + "fetchUfsBySmartPointId";

	/** The Constant INSERT_UF. */
	private static final String INSERT_UF = UF_NAMESPACE + "insertUf";

	/** The Constant INSERT_SMART_POINT_TO_UF. */
	private static final String INSERT_SMART_POINT_TO_UF = UF_NAMESPACE + "insertSmartPointToUf";

	/** The Constant FETCH_LIGHTS_BELONG. */
	private static final String FETCH_LIGHTS_BELONG_UF = UF_NAMESPACE + "fetchLightsBelongUf";

	/** The Constant FETCH_ALL_UFS. */
	private static final String FETCH_ALL_UFS = UF_NAMESPACE + "fetchAllUfs";

	/** The Constant UFID. */
	private static final String UF_ID = "ufId";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant UFNAME. */
	private static final String UFNAME = "ufname";

	/** The Constant AUTOUF. */
	private static final String AUTOUF = "autouf";

	/** The Constant AUTOUF. */
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

	private static final String UPDATE_UF = null;

	private static final String SENSUS_MLC_UFVALIDATOR_UF_ALREADY_EXISTS = null;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.dao.IUfDAO#fetchAllUfs(com.sensus.mlc.uf.model.request.InquiryUfRequest)
	 */
	@Override
	public InternalResultsResponse<Uf> fetchAllUf(InquiryUfRequest inquiryUfRequest)
	{
		InternalResultsResponse<Uf> response = new InternalResultsResponse<Uf>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryUfRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryUfRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryUfRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryUfRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryUfRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryUfRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryUfRequest.getUf()))
		{
			paramMap.put(UF_ID, inquiryUfRequest.getUf().get(0).getCoduf());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_UFS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.dao.IUfDAO#insertUf(com.sensus.mlc.uf.model.request.UfRequest)
	 */
	@Override
	public InternalResultsResponse<Uf> insertUf(UfRequest ufRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Uf uf = ufRequest.getUf();

		uf.setCoduf((Integer)doQueryForObject(getSqlSession(), INSERT_UF, ufRequest));
        uf.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, ufRequest));
		InternalResultsResponse<Uf> response = new InternalResultsResponse<Uf>();
		response.addResult(uf);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.dao.IUfDAO#deleteUf(com.sensus.mlc.uf.model.request.UfRequest)
	 */
	@Override
	public InternalResponse deleteUf(UfRequest ufRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_UF, ufRequest.getUf(), response);
		return response;
	}


	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.dao.IUfDAO#fetchUfById(com.sensus.mlc.uf.model.request.UfRequest)
	 */
	@Override
	public InternalResultsResponse<Uf> fetchUfById(UfRequest ufRequest)
	{
		InternalResultsResponse<Uf> response = new InternalResultsResponse<Uf>();
		doQueryForList(getSqlSession(), FETCH_UF_BY_ID, ufRequest.getUf(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.dac.IUfDAC#updateUf(com.sensus.mlc.uf.model.request.UfRequest)
	 */
	@Override
	public InternalResultsResponse<Uf> updateUf(UfRequest ufRequest)
	{
		InternalResultsResponse<Uf> response = new InternalResultsResponse<Uf>();
		try
		{
			doQueryForObject(getSqlSession(), UPDATE_UF, ufRequest);
		}
		catch (DuplicateKeyException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_UFVALIDATOR_UF_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

	@Override
	public InternalResponse generateFileCSV(InquiryUfRequest inquiryUfRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UfResponse fetchAllUfTypes(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UfResponse fetchAllUfFilial(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}


