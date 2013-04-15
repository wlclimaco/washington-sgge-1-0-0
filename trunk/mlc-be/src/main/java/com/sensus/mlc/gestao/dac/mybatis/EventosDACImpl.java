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
import com.sensus.mlc.eventos.dac.IEventosDAC;
import com.sensus.mlc.eventos.model.Eventos;
import com.sensus.mlc.eventos.model.EventosOrderByEnum;
import com.sensus.mlc.eventos.model.request.InquiryEventosRequest;
import com.sensus.mlc.eventos.model.request.EventosRequest;

/** 
 * The Class EventosDACImpl.
 */ 
public class EventosDACImpl extends SqlSessionDaoSupport implements IEventosDAC
{ 

	/** The Constant EVENTOS_NAMESPACE. */ 
	private static final String EVENTOS_NAMESPACE = "Eventos.";

	/** The Constant FETCH_EVENTOS_BY_ID. */ 
	private static final String FETCH_EVENTOS_BY_ID = EVENTOS_NAMESPACE + "fetchEventosById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = EVENTOS_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_EVENTOS. */ 
	private static final String DELETE_SMART_POINT_FROM_EVENTOS = EVENTOS_NAMESPACE + "deleteSmartPointFromEventos";

	/** The Constant FETCH_EVENTOS_BY_ID. */ 
	private static final String FETCH_EVENTOS_BY_ID = EVENTOS_NAMESPACE + "fetchEventosById";

	/** The Constant FETCH_EVENTOS_NAME_BY_ID. */ 
	private static final String FETCH_EVENTOS_NAME_BY_ID = EVENTOS_NAMESPACE + "fetchEventosNameById";

	/** The Constant UPDATE_AUTO_EVENTOS_EVENTOS. */ 
	private static final String UPDATE_AUTO_EVENTOS_EVENTOS = EVENTOS_NAMESPACE + "updateAutoEventosEventos";

	/** The Constant DELETE_EVENTOS. */ 
	private static final String DELETE_EVENTOS = EVENTOS_NAMESPACE + "deleteEventos";

	/** The Constant FETCH_EVENTOSS_BY_LIGHT_ID. */ 
	private static final String FETCH_EVENTOSS_BY_LIGHT_ID = EVENTOS_NAMESPACE + "fetchEventossByLightId";

	/** The Constant FETCH_EVENTOSS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_EVENTOSS_BY_SMART_POINT_ID = EVENTOS_NAMESPACE + "fetchEventossBySmartPointId";

	/** The Constant INSERT_EVENTOS. */ 
	private static final String INSERT_EVENTOS = EVENTOS_NAMESPACE + "insertEventos";

	/** The Constant INSERT_SMART_POINT_TO_EVENTOS. */ 
	private static final String INSERT_SMART_POINT_TO_EVENTOS = EVENTOS_NAMESPACE + "insertSmartPointToEventos";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_EVENTOS = EVENTOS_NAMESPACE + "fetchLightsBelongEventos";

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

	/** The Constant EVENTOSNAME. */ 
	private static final String EVENTOSNAME = "eventosname";

	/** The Constant AUTOEVENTOS. */ 
	private static final String AUTOEVENTOS = "autoeventos";

	/** The Constant AUTOEVENTOS. */ 
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

		if (!ValidationUtil.isNull(inquiryEventosRequest.getEventoss()))
		{
			paramMap.put(EVENTOS_ID, inquiryEventosRequest.getEventoss().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_EVENTOSS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.eventos.dao.IEventosDAO#fetchEventosById(com.sensus.mlc.eventos.model.Eventos
	 */ 
	@Override
	public InternalResultsResponse<Eventos> fetchEventosByName(EventosRequest eventosRequest)
	{ 
		InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
		doQueryForList(getSqlSession(), FETCH_EVENTOS_BY_ID, eventosRequest, response);
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

		eventos.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_EVENTOS, eventosRequest));
   eventos.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, eventosRequest));
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
	 * @see com.sensus.mlc.eventos.dac.IEventosDAC#fetchEventosNameById(com.sensus.mlc.eventos.model.request.EventosRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Eventos> fetchEventosNameById(EventosRequest eventosRequest)
	{
		InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
		doQueryForList(getSqlSession(), FETCH_EVENTOS_NAME_BY_ID, eventosRequest.getEventos(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.dac.IEventosDAC#updateEventos(com.sensus.mlc.eventos.model.request.EventosRequest)
	 */ 
	@Override 
	public InternalResponse updateEventos(EventosRequest eventosRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_EVENTOS, eventosRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_EVENTOSVALIDATOR_EVENTOS_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


