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
import com.sensus.mlc.municipio.dac.IMunicipioDAC;
import com.sensus.mlc.municipio.model.Municipio;
import com.sensus.mlc.municipio.model.MunicipioOrderByEnum;
import com.sensus.mlc.municipio.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.municipio.model.request.MunicipioRequest;

/** 
 * The Class MunicipioDACImpl.
 */ 
public class MunicipioDACImpl extends SqlSessionDaoSupport implements IMunicipioDAC
{ 

	/** The Constant MUNICIPIO_NAMESPACE. */ 
	private static final String MUNICIPIO_NAMESPACE = "Municipio.";

	/** The Constant FETCH_MUNICIPIO_BY_ID. */ 
	private static final String FETCH_MUNICIPIO_BY_ID = MUNICIPIO_NAMESPACE + "fetchMunicipioById";

	/** The Constant PAGINATION_TOTAL_ROWS. */ 
	private static final String PAGINATION_TOTAL_ROWS = MUNICIPIO_NAMESPACE + "PaginationTotalRows";

	/** The Constant DELETE_SMART_POINT_FROM_MUNICIPIO. */ 
	private static final String DELETE_SMART_POINT_FROM_MUNICIPIO = MUNICIPIO_NAMESPACE + "deleteSmartPointFromMunicipio";

	/** The Constant FETCH_MUNICIPIO_BY_ID. */ 
	private static final String FETCH_MUNICIPIO_BY_ID = MUNICIPIO_NAMESPACE + "fetchMunicipioById";

	/** The Constant FETCH_MUNICIPIO_NAME_BY_ID. */ 
	private static final String FETCH_MUNICIPIO_NAME_BY_ID = MUNICIPIO_NAMESPACE + "fetchMunicipioNameById";

	/** The Constant UPDATE_AUTO_MUNICIPIO_MUNICIPIO. */ 
	private static final String UPDATE_AUTO_MUNICIPIO_MUNICIPIO = MUNICIPIO_NAMESPACE + "updateAutoMunicipioMunicipio";

	/** The Constant DELETE_MUNICIPIO. */ 
	private static final String DELETE_MUNICIPIO = MUNICIPIO_NAMESPACE + "deleteMunicipio";

	/** The Constant FETCH_MUNICIPIOS_BY_LIGHT_ID. */ 
	private static final String FETCH_MUNICIPIOS_BY_LIGHT_ID = MUNICIPIO_NAMESPACE + "fetchMunicipiosByLightId";

	/** The Constant FETCH_MUNICIPIOS_BY_SMART_POINT_ID. */ 
	private static final String FETCH_MUNICIPIOS_BY_SMART_POINT_ID = MUNICIPIO_NAMESPACE + "fetchMunicipiosBySmartPointId";

	/** The Constant INSERT_MUNICIPIO. */ 
	private static final String INSERT_MUNICIPIO = MUNICIPIO_NAMESPACE + "insertMunicipio";

	/** The Constant INSERT_SMART_POINT_TO_MUNICIPIO. */ 
	private static final String INSERT_SMART_POINT_TO_MUNICIPIO = MUNICIPIO_NAMESPACE + "insertSmartPointToMunicipio";

	/** The Constant FETCH_LIGHTS_BELONG. */ 
	private static final String FETCH_LIGHTS_BELONG_MUNICIPIO = MUNICIPIO_NAMESPACE + "fetchLightsBelongMunicipio";

	/** The Constant FETCH_ALL_MUNICIPIOS. */ 
	private static final String FETCH_ALL_MUNICIPIOS = MUNICIPIO_NAMESPACE + "fetchAllMunicipios";

	/** The Constant MUNICIPIOID. */ 
	private static final String MUNICIPIO_ID = "municipioId";

	/** The Constant TENANT_ID. */ 
	private static final String TENANT_ID = "tenantId";

	/** The Constant PAGE_SIZE. */ 
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */ 
	private static final String START_ROW = "startRow";

	/** The Constant MUNICIPIONAME. */ 
	private static final String MUNICIPIONAME = "municipioname";

	/** The Constant AUTOMUNICIPIO. */ 
	private static final String AUTOMUNICIPIO = "automunicipio";

	/** The Constant AUTOMUNICIPIO. */ 
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
	 * @see com.sensus.mlc.municipio.dao.IMunicipioDAO#fetchAllMunicipios(com.sensus.mlc.municipio.model.request.InquiryMunicipioRequest)
	 */ 
	@Override
	public InternalResultsResponse<Municipio> fetchAllMunicipio(InquiryMunicipioRequest inquiryMunicipioRequest)
	{
		InternalResultsResponse<Municipio> response = new InternalResultsResponse<Municipio>();
		HashMap<String, Object>  paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID,  inquiryMunicipioRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE,  inquiryMunicipioRequest.getPageSize());
		paramMap.put(START_ROW,  inquiryMunicipioRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryMunicipioRequest.getStartPage());
		paramMap.put(ORDERBY,    TagOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryMunicipioRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, inquiryMunicipioRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryMunicipioRequest.getMunicipios()))
		{
			paramMap.put(MUNICIPIO_ID, inquiryMunicipioRequest.getMunicipios().get(0).getCodemp());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_MUNICIPIOS, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.municipio.dao.IMunicipioDAO#fetchMunicipioById(com.sensus.mlc.municipio.model.Municipio
	 */ 
	@Override
	public InternalResultsResponse<Municipio> fetchMunicipioByName(MunicipioRequest municipioRequest)
	{ 
		InternalResultsResponse<Municipio> response = new InternalResultsResponse<Municipio>();
		doQueryForList(getSqlSession(), FETCH_MUNICIPIO_BY_ID, municipioRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.municipio.dao.IMunicipioDAO#insertMunicipio(com.sensus.mlc.municipio.model.request.MunicipioRequest)
	 */ 
	@Override
	public InternalResultsResponse<Municipio> insertMunicipio(MunicipioRequest municipioRequest) 
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		Municipio municipio = municipioRequest.getMunicipio();

		municipio.setCodEmp((Integer)doQueryForObject(getSqlSession(), INSERT_MUNICIPIO, municipioRequest));
   municipio.setListinsalt((List<Auditoria>)doQueryForObject(getSqlSession(), INSERT_EMPRESA, municipioRequest));
		InternalResultsResponse<Municipio> response = new InternalResultsResponse<Municipio>();
		response.addResult(municipio);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.municipio.dao.IMunicipioDAO#deleteMunicipio(com.sensus.mlc.municipio.model.request.MunicipioRequest)
	 */ 
	@Override
	public InternalResponse deleteMunicipio(MunicipioRequest municipioRequest)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_MUNICIPIO, municipioRequest.getMunicipio(), response);
		return response;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.municipio.dao.IMunicipioDAO#fetchMunicipioById(com.sensus.mlc.municipio.model.request.MunicipioRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Municipio> fetchMunicipioById(MunicipioRequest municipioRequest) 
	{
		InternalResultsResponse<Municipio> response = new InternalResultsResponse<Municipio>();
		doQueryForList(getSqlSession(), FETCH_MUNICIPIO_BY_ID, municipioRequest.getMunicipio(), response);
		return response;
	}

	/*
	 * (non-Javadoc) 
	 * @see com.sensus.mlc.municipio.dac.IMunicipioDAC#fetchMunicipioNameById(com.sensus.mlc.municipio.model.request.MunicipioRequest)
	 */ 
	@Override 
	public InternalResultsResponse<Municipio> fetchMunicipioNameById(MunicipioRequest municipioRequest)
	{
		InternalResultsResponse<Municipio> response = new InternalResultsResponse<Municipio>();
		doQueryForList(getSqlSession(), FETCH_MUNICIPIO_NAME_BY_ID, municipioRequest.getMunicipio(), response);
		return response;
	}
 
	/* 
	 * (non-Javadoc)
	 * @see com.sensus.mlc.municipio.dac.IMunicipioDAC#updateMunicipio(com.sensus.mlc.municipio.model.request.MunicipioRequest)
	 */ 
	@Override 
	public InternalResponse updateMunicipio(MunicipioRequest municipioRequest)
	{
		InternalResponse response = new InternalResponse();
		try 
		{ 
			doQueryForObject(getSqlSession(), UPDATE_MUNICIPIO, municipioRequest);
		}
		catch (DuplicateKeyException ex) 
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(SENSUS_MLC_MUNICIPIOVALIDATOR_MUNICIPIO_ALREADY_EXISTS, MessageSeverity.Info, MessageLevel.None);
		}

		return response;
	}

}


