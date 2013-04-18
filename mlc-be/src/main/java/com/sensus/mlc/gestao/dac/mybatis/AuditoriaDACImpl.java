package com.sensus.mlc.gestao.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DuplicateKeyException;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.gestao.dac.IAuditoriaDAC;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.request.AuditoriaRequest;
import com.sensus.mlc.gestao.model.request.InquiryAuditoriaRequest;
import com.sensus.mlc.gestao.model.response.AuditoriaResponse;
import com.sensus.mlc.tag.model.TagOrderByEnum;

/**
 * The Class AuditoriaDACImpl.
 */
public class AuditoriaDACImpl extends SqlSessionDaoSupport implements
		IAuditoriaDAC {

	/** The Constant AUDITORIA_NAMESPACE. */
	private static final String AUDITORIA_NAMESPACE = "Auditoria.";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = AUDITORIA_NAMESPACE
			+ "PaginationTotalRows";

	/** The Constant DELETE_AUDITORIA. */
	private static final String DELETE_AUDITORIA = AUDITORIA_NAMESPACE
			+ "deleteAuditoria";

	/** The Constant INSERT_AUDITORIA. */
	private static final String INSERT_AUDITORIA = AUDITORIA_NAMESPACE
			+ "insertAuditoria";

	private static final String UPDATE_AUDITORIA = AUDITORIA_NAMESPACE
			+ "updateAuditoria";
	private static final String SENSUS_MLC_AUDITORIA_VALIDATOR_AUDITORIA_ALREADY_EXISTS = "error.update.auditoria";

	/** The Constant FETCH_ALL_AUDITORIAS. */
	private static final String FETCH_ALL_AUDITORIAS = AUDITORIA_NAMESPACE
			+ "fetchAllAuditorias";

	/** The Constant AUDITORIAID. */
	private static final String AUDITORIA_ID = "auditoriaId";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant START_PAGE. */
	private static final String START_PAGE = "startPage";

	/** The Constant ORDERBY. */
	private static final String ORDERBY = "orderBy";

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE6 = 5;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.sensus.mlc.auditoria.dao.IAuditoriaDAO#fetchAllAuditorias(com.sensus
	 * .mlc.auditoria.model.request.InquiryAuditoriaRequest)
	 */
	@Override
	public InternalResultsResponse<Auditoria> fetchAllAuditoria(
			InquiryAuditoriaRequest inquiryAuditoriaRequest) {
		InternalResultsResponse<Auditoria> response = new InternalResultsResponse<Auditoria>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(
				PARAMSIZE6);
		paramMap.put(TENANT_ID, inquiryAuditoriaRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryAuditoriaRequest.getPageSize());
		paramMap.put(START_ROW, inquiryAuditoriaRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryAuditoriaRequest.getStartPage());
		paramMap.put(ORDERBY, TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryAuditoriaRequest
				.getSortExpressions())) {
			paramMap.put(ORDERBY, inquiryAuditoriaRequest.getSortExpressions()
					.get(0));
		}

		if (!ValidationUtil.isNull(inquiryAuditoriaRequest.getAuditoria())) {
			paramMap.put(AUDITORIA_ID, inquiryAuditoriaRequest.getAuditoria()
					.get(0).getCodaltins());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_AUDITORIAS, paramMap,
				response);

		Integer totalRows = (Integer) doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.sensus.mlc.auditoria.dao.IAuditoriaDAO#insertAuditoria(com.sensus
	 * .mlc.auditoria.model.request.AuditoriaRequest)
	 */
	@Override
	public InternalResultsResponse<Auditoria> insertAuditoria(
			AuditoriaRequest auditoriaRequest) {
		Auditoria auditoria = auditoriaRequest.getAuditoria();

		auditoria.setCodaltins((Integer) doQueryForObject(getSqlSession(),
				INSERT_AUDITORIA, auditoriaRequest));
		InternalResultsResponse<Auditoria> response = new InternalResultsResponse<Auditoria>();
		response.addResult(auditoria);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.sensus.mlc.auditoria.dao.IAuditoriaDAO#deleteAuditoria(com.sensus
	 * .mlc.auditoria.model.request.AuditoriaRequest)
	 */
	@Override
	public InternalResponse deleteAuditoria(AuditoriaRequest auditoriaRequest) {
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_AUDITORIA,
				auditoriaRequest.getAuditoria(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.sensus.mlc.auditoria.dac.IAuditoriaDAC#updateAuditoria(com.sensus
	 * .mlc.auditoria.model.request.AuditoriaRequest)
	 */
	@Override
	public InternalResultsResponse<Auditoria> updateAuditoria(
			AuditoriaRequest auditoriaRequest) {
		InternalResultsResponse<Auditoria> response = new InternalResultsResponse<Auditoria>();
		try {
			doQueryForObject(getSqlSession(), UPDATE_AUDITORIA,
					auditoriaRequest);
		} catch (DuplicateKeyException ex) {
			response.setStatus(Status.ExceptionError);
			response.addMessage(
					SENSUS_MLC_AUDITORIA_VALIDATOR_AUDITORIA_ALREADY_EXISTS,
					MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Auditoria> fetchAuditoriaById(
			AuditoriaRequest auditoriaRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse generateFileCSV(
			InquiryAuditoriaRequest inquiryAuditoriaRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuditoriaResponse fetchAllAuditoriaTypes(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuditoriaResponse fetchAllAuditoriaFilial(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}
