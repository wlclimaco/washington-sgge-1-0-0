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
import com.sensus.mlc.gestao.dac.IEmbalagensDAC;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.Embalagens;
import com.sensus.mlc.gestao.model.request.EmbalagensRequest;
import com.sensus.mlc.gestao.model.request.InquiryEmbalagensRequest;
import com.sensus.mlc.gestao.model.response.EmbalagensResponse;
import com.sensus.mlc.tag.model.TagOrderByEnum;

/**
 * The Class EmbalagensDACImpl.
 */
public class EmbalagensDACImpl extends SqlSessionDaoSupport implements IEmbalagensDAC
{

	/** The Constant EMBALAGENS_NAMESPACE. */
	private static final String EMBALAGENS_NAMESPACE = "Embalagens.";

	/** The Constant FETCH_EMBALAGENS_BY_ID. */
	private static final String FETCH_EMBALAGENS_BY_ID = EMBALAGENS_NAMESPACE + "fetchEmbalagensById";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = EMBALAGENS_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_EMBALAGENS. */
	private static final String DELETE_SMART_POINT_FROM_EMBALAGENS = EMBALAGENS_NAMESPACE + "deleteSmartPointFromEmbalagens";

	/** The Constant FETCH_EMBALAGENS_NAME_BY_ID. */
	private static final String FETCH_EMBALAGENS_NAME_BY_ID = EMBALAGENS_NAMESPACE + "fetchEmbalagensNameById";

	/** The Constant UPDATE_AUTO_EMBALAGENS_EMBALAGENS. */
	private static final String UPDATE_AUTO_EMBALAGENS_EMBALAGENS = EMBALAGENS_NAMESPACE + "updateAutoEmbalagensEmbalagens";

	/** The Constant DELETE_EMBALAGENS. */
	private static final String DELETE_EMBALAGENS = EMBALAGENS_NAMESPACE + "deleteEmbalagens";

	/** The Constant FETCH_EMBALAGENSS_BY_LIGHT_ID. */
	private static final String FETCH_EMBALAGENSS_BY_LIGHT_ID = EMBALAGENS_NAMESPACE + "fetchEmbalagenssByLightId";

	/** The Constant FETCH_EMBALAGENSS_BY_SMART_POINT_ID. */
	private static final String FETCH_EMBALAGENSS_BY_SMART_POINT_ID = EMBALAGENS_NAMESPACE + "fetchEmbalagenssBySmartPointId";

	/** The Constant INSERT_EMBALAGENS. */
	private static final String INSERT_EMBALAGENS = EMBALAGENS_NAMESPACE + "insertEmbalagens";

	/** The Constant INSERT_SMART_POINT_TO_EMBALAGENS. */
	private static final String INSERT_SMART_POINT_TO_EMBALAGENS = EMBALAGENS_NAMESPACE + "insertSmartPointToEmbalagens";

	/** The Constant FETCH_LIGHTS_BELONG. */
	private static final String FETCH_LIGHTS_BELONG_EMBALAGENS = EMBALAGENS_NAMESPACE + "fetchLightsBelongEmbalagens";

	/** The Constant FETCH_ALL_EMBALAGENSS. */
	private static final String FETCH_ALL_EMBALAGENSS = EMBALAGENS_NAMESPACE + "fetchAllEmbalagenss";

	/** The Constant EMBALAGENSID. */
	private static final String EMBALAGENS_ID = "embalagensId";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant EMBALAGENSNAME. */
	private static final String EMBALAGENSNAME = "embalagensname";

	/** The Constant AUTOEMBALAGENS. */
	private static final String AUTOEMBALAGENS = "autoembalagens";

	/** The Constant AUTOEMBALAGENS. */
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

	private static final String UPDATE_EMBALAGENS = null;

	private static final String SENSUS_MLC_EMBALAGENSVALIDATOR_EMBALAGENS_ALREADY_EXISTS = null;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.dao.IEmbalagensDAO#fetchAllEmbalagenss(com.sensus.mlc.embalagens.model.request.InquiryEmbalagensRequest)
	 */
	@Override
	public InternalResultsResponse<Embalagens> fetchAllEmbalagens(InquiryEmbalagensRequest inquiryEmbalagensRequest)
	{
		InternalResultsResponse<Embalagens> response = new InternalResultsResponse<Embalagens>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryEmbalagensRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryEmbalagensRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryEmbalagensRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryEmbalagensRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryEmbalagensRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryEmbalagensRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryEmbalagensRequest.getEmbalagens()))
		{
			paramMap.put(EMBALAGENS_ID, inquiryEmbalagensRequest.getEmbalagens().get(0).getCdembala());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_EMBALAGENSS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.dao.IEmbalagensDAO#insertEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */
	@Override
	public InternalResultsResponse<Embalagens> insertEmbalagens(EmbalagensRequest embalagensRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Embalagens embalagens = embalagensRequest.getEmbalagens();

		embalagens.setCdembala((Integer)doQueryForObject(getSqlSession(), INSERT_EMBALAGENS, embalagensRequest));
		embalagens.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, embalagensRequest));
		InternalResultsResponse<Embalagens> response = new InternalResultsResponse<Embalagens>();
		response.addResult(embalagens);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.dao.IEmbalagensDAO#deleteEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */
	@Override
	public InternalResponse deleteEmbalagens(EmbalagensRequest embalagensRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_EMBALAGENS, embalagensRequest.getEmbalagens(), response);
		return response;
	}


	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.dao.IEmbalagensDAO#fetchEmbalagensById(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */
	@Override
	public InternalResultsResponse<Embalagens> fetchEmbalagensById(EmbalagensRequest embalagensRequest)
	{
		InternalResultsResponse<Embalagens> response = new InternalResultsResponse<Embalagens>();
		doQueryForList(getSqlSession(), FETCH_EMBALAGENS_BY_ID, embalagensRequest.getEmbalagens(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.dac.IEmbalagensDAC#updateEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */
	@Override
	public InternalResultsResponse<Embalagens> updateEmbalagens(EmbalagensRequest embalagensRequest)
	{
		InternalResultsResponse<Embalagens> response = new InternalResultsResponse<Embalagens>();
		try
		{
			doQueryForObject(getSqlSession(), UPDATE_EMBALAGENS, embalagensRequest);
		}
		catch (DuplicateKeyException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_EMBALAGENSVALIDATOR_EMBALAGENS_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

	@Override
	public InternalResponse generateFileCSV(
			InquiryEmbalagensRequest inquiryEmbalagensRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmbalagensResponse fetchAllEmbalagensTypes(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmbalagensResponse fetchAllEmbalagensFilial(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}


