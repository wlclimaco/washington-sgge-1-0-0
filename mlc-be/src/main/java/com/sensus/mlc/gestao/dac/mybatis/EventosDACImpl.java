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
import com.sensus.mlc.gestao.dac.IEventosDAC;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.Eventos;
import com.sensus.mlc.gestao.model.request.EventosRequest;
import com.sensus.mlc.gestao.model.request.InquiryEventosRequest;
import com.sensus.mlc.gestao.model.response.EventosResponse;
import com.sensus.mlc.tag.model.TagOrderByEnum;

/**
 * The Class EventosDACImpl.
 */
public class EventosDACImpl extends SqlSessionDaoSupport implements IEventosDAC
{

	/** The Constant EVENTOS_NAMESPACE. */
	private static final String EVENTOS_NAMESPACE = "Eventos.";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = EVENTOS_NAMESPACE + "PaginationTotalRows";

	/** The Constant FETCH_EVENTOS_BY_ID. */
	private static final String FETCH_EVENTOS_BY_ID = EVENTOS_NAMESPACE + "fetchEventosById";

	/** The Constant DELETE_EVENTOS. */
	private static final String DELETE_EVENTOS = EVENTOS_NAMESPACE + "deleteEventos";

	/** The Constant INSERT_EVENTOS. */
	private static final String INSERT_EVENTOS = EVENTOS_NAMESPACE + "insertEventos";

	/** The Constant FETCH_ALL_EVENTOSS. */
	private static final String FETCH_ALL_EVENTOSS = EVENTOS_NAMESPACE + "fetchAllEventoss";

	/** The Constant EVENTOSID. */
	private static final String EVENTOS_ID = "eventosId";

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

	private static final String INSERT_AUDITORIA = null;

	private static final String UPDATE_EVENTOS = null;

	private static final String SENSUS_MLC_EVENTOS_VALIDATOR_EVENTOS_ALREADY_EXISTS = null;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.dao.IEventosDAO#fetchAllEventoss(com.sensus.mlc.eventos.model.request.InquiryEventosRequest)
	 */
	@Override
	public InternalResultsResponse<Eventos> fetchAllEventos(InquiryEventosRequest inquiryEventosRequest)
	{
		InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryEventosRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryEventosRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryEventosRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryEventosRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryEventosRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryEventosRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryEventosRequest.getEventos()))
		{
			paramMap.put(EVENTOS_ID, inquiryEventosRequest.getEventos().get(0).getCdevento());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_EVENTOSS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}


	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.dao.IEventosDAO#insertEventos(com.sensus.mlc.eventos.model.request.EventosRequest)
	 */
	@Override
	public InternalResultsResponse<Eventos> insertEventos(EventosRequest eventosRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Eventos eventos = eventosRequest.getEventos();

		eventos.setCdevento((Integer)doQueryForObject(getSqlSession(), INSERT_EVENTOS, eventosRequest));
		eventos.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_AUDITORIA, eventosRequest));
		InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
		response.addResult(eventos);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.dao.IEventosDAO#deleteEventos(com.sensus.mlc.eventos.model.request.EventosRequest)
	 */
	@Override
	public InternalResponse deleteEventos(EventosRequest eventosRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_EVENTOS, eventosRequest.getEventos(), response);
		return response;
	}


	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.dao.IEventosDAO#fetchEventosById(com.sensus.mlc.eventos.model.request.EventosRequest)
	 */
	@Override
	public InternalResultsResponse<Eventos> fetchEventosById(EventosRequest eventosRequest)
	{
		InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
		doQueryForList(getSqlSession(), FETCH_EVENTOS_BY_ID, eventosRequest.getEventos(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.dac.IEventosDAC#updateEventos(com.sensus.mlc.eventos.model.request.EventosRequest)
	 */
	@Override
	public InternalResultsResponse<Eventos> updateEventos(EventosRequest eventosRequest)
	{
		InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
		try
		{
			doQueryForObject(getSqlSession(), UPDATE_EVENTOS, eventosRequest);
		}
		catch (DuplicateKeyException ex)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_EVENTOS_VALIDATOR_EVENTOS_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

	@Override
	public InternalResponse generateFileCSV(
			InquiryEventosRequest inquiryEventosRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventosResponse fetchAllEventosTypes(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventosResponse fetchAllEventosFilial(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}


