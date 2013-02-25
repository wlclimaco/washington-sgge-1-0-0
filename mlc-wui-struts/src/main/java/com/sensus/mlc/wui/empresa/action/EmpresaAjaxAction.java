package com.sensus.mlc.wui.empresa.action;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchResult;

import com.sensus.common.model.Request;
import com.sensus.common.model.Response;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.empresa.bcf.IEmpresaBCF;
import com.sensus.mlc.empresa.model.Empresa;
import com.sensus.mlc.empresa.model.request.EmpresaRequest;
import com.sensus.mlc.empresa.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.empresa.model.response.EmpresaResponse;
import com.sensus.mlc.filial.model.Filial;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.JsonResult;
import com.sensus.mlc.wui.base.model.SearchJsonResult;


// TODO: Auto-generated Javadoc
/**
 * Action for searching saved.
 * 
 * @author Weslley Silva
 */

/**
 * @author QATEmployee
 * 
 */
@SuppressWarnings("serial")
public class EmpresaAjaxAction extends ActionBase
{

	/** The SmartPoint BCF object. */
	private IEmpresaBCF empresaBCF;

	/** ************************ CONSTANTS ***********************. */

	/** The Constant CITY. */
	private static final String CITY = "city";

	/** The Constant CONFIG_FILTER. */
	public static final String CONFIG_FILTER = "configFilter";

	/** The Constant END. */
	public static final String END = "end";

	/** The Constant EVENT_FILTER. */
	public static final String EVENT_FILTER = "eventFilter";

	/** The Constant ERROR_SAVING_SEARCH. */
	public static final String ERROR_SAVING_SEARCH = "Error saving search";

	/** The Constant GROUP_FILETR. */
	public static final String GROUP_FILETR = "groupFilter";

	/** The Constant LAMP_TYPE_FILTER. */
	public static final String LAMP_TYPE_FILTER = "lampTypeFilter";

	/** The Constant LAMP_WATTAGE. */
	public static final String LAMP_WATTAGE = "lampWattage";

	/** The Constant NAME. */
	public static final String NAME = "NAME";

	/** The Constant OFFSET_FILTER. */
	public static final String OFFSET_FILTER = "offsetFilter";

	/** The Constant ERROR_DELETING_SCHEDULE. */
	private static final String ERROR_ACTION_ERROR = "Error deleting schedule";

	/** The Constant PIPE. */
	private static final String PIPE = "[|]";

	/** The Constant SIX. */
	public static final Integer SIX = 6;

	/** The Constant START. */
	private static final String START = "start";

	/** The Constant STATUS. */
	public static final String STATUS = "status";

	/** The Constant STREET. */
	private static final String STREET = "street";

	/** The Constant STATUS_FILTER. */
	public static final String STATUS_FILTER = "statusFilter";

	/** The Constant TAG_FILTER. */
	public static final String TAG_FILTER = "tagFilter";

	/** The Constant TYPE_FILTER. */
	public static final String TYPE_FILTER = "typeFilter";

	/** The Constant TOKEN. */
	public static final String TOKEN = ",";

	/** The Constant VALUE_FILTER. */
	public static final String VALUE_FILTER = "valueFilter";

	/** The Constant ZIP. */
	private static final String ZIP = "zip";



	/** ************************ PROPERTIES ***********************. */
	/** The city. */
	private String city;

	/** The columns. */
	private List<String> columns;

	/** The columns save. */
	private List<String> columnsSave;

	/** The end. */
	private String end;

	/** The filters. */
	private List<String> filters;

	/** The filter url. */
	private Integer[][] filterUrl;

	/** The firmware version. */
	private String firmwareVersion;

	/** The groups. */
	private String groups;

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The group id. */
	private Integer groupId;

	/** The group name. */
	private String groupName;

	/** The ids list. */
	private List<Integer> idsList = new ArrayList<Integer>();

	/** The is all rows. */
	private Boolean isAllRows = false;

	/** The is monitored. */
	private Boolean isMonitored;

	/** The meter id. */
	private Integer meterId;

	/** The query. */
	private String query;

	/** The action id. */
	private Integer actionId;

	/** The json result. */
	private JsonResult result;

	/** The search json result. */
	private SearchJsonResult searchResult;

	/** The object returned to the caller. */
	private JsonResult savedSearchResult;

	/** The search name. */
	private String searchName;

	/** The search description. */
	private String searchDescription;

	/** The smartpoint ids. */
	private List<Integer> smartpointIds = new ArrayList<Integer>();

	/** The json result. */
	private JsonResult smartpointResult;
	
	/** The json result. */
	private SearchResult empresaResult;

	/** The response. */
	private Response response;
	
	/** The cod emp. */
	private Integer codEmp;
	
	/** The raz soc emp. */
	private String razSocEmp;
	
	/** The nom fant. */
	private String nomFant;
	
	/** The cnpj. */
	private String cnpj;
	
	/** The ins est. */
	private String insEst;			
	
	/** The ins mun. */
	private String insMun;
	
	/** The cep. */
	private String cep;
	
	/** The logradouro. */
	private String logradouro;
	
	/** The numero. */
	private Integer numero;
	
	/** The complemento. */
	private String complemento;
	
	/** The bairro. */
	private String bairro;
	
	/** The cidade. */
	private String cidade;
	
	/** The uf. */
	private String uf;
	
	/** The pagina web. */
	private String paginaWEB;
	
	/** The telefone. */
	private String telefone;
	
	/** The fax. */
	private String fax;
	
	/** The email. */
	private String email;
	
	/** The email n fe. */
	private String emailNFe;
	
	/** The c ean. */
	private String cEAN;
	
	/** The c pais. */
	private String cPais;
	
	/** The i ss. */
	private Float iSS;
	
	/** The contato. */
	private String contato;
	
	/** The intalmfil. */
	private String intalmfil;
	
	
	/** The status. */
	private String status;

	/** The start. */
	private String start;

	/** The street. */
	private String street;

	/** The tag id. */
	private Integer tagId;

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/** The tag name. */
	private String tagName;
	
	/** The codempr. */
	private Integer codempr;
	
	/** The codfil. */
	private Integer codfil;

	/** The tags. */
	private String tags;
	
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant TWO. */
	private static final int TWO = 2;

	/** The Constant THREE. */
	private static final int THREE = 3;

	/** The Constant FOUR. */
	private static final int FOUR = 4;

	/** The Constant FIVE. */
	private static final int FIVE = 5;

	/** The Constant SEVEN. */
	private static final int SEVEN = 7;

	/** The Constant EIGHT. */
	private static final int EIGHT = 8;

	/** The Constant NINE. */
	private static final int NINE = 9;
	
	/** The Constant NINE. */
	private static final int ELEVEN = 11;
	
	/** The Constant NINE. */
	private static final int TEN = 10;
	
	/** The Constant NINE. */
	private static final int TWELVEN = 12;
	
	/** The Constant NINE. */
	private static final int THIRTEEN = 13;
	
	/** The Constant NINE. */
	private static final int FOURTEEN = 14;
	
	/** The Constant NINE. */
	
	private static final int FIFTEEN = 15;
	
	/** The Constant SEVENTEEN. */
	private static final int SEVENTEEN = 17;
	
	/** The Constant SIXTEEN. */
	private static final int SIXTEEN = 16;
	
	/** The Constant EIGHTEEN. */
	private static final int EIGHTEEN = 18;
	
	/** The Constant NINETEEN. */
	private static final int NINETEEN = 19;
	
	/** The Constant TWELVE. */
	private static final int TWELVE = 20;
	

	
	

	/**
	 * Insert empresa.
	 *
	 * @return the string
	 */
	public String insertEmpresa()
	{
		try
		{
		Empresa empresa = new Empresa();
		if(!ValidationUtil.isNullOrEmpty(getEnd())){
			empresa.setEmdemp(getEnd());
		}else {
			empresa.setEmdemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getBairro())){
			empresa.setBairemp(getBairro());
		}else {
			empresa.setBairemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getCep())){
			empresa.setCepemp(getCep());		
		}else {
			empresa.setCepemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getCidade())){
			empresa.setCidemp(getCidade());
		}else {
			empresa.setCidemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getCnpj())){
			empresa.setCnpjemp(getCnpj());
		}else {
			empresa.setCnpjemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getcEAN())){
			empresa.setCodeanemp(getcEAN());
		}else {
			empresa.setCodeanemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getInsMun())){
			empresa.setCodmunic(getInsMun());
		}else {
			empresa.setCodmunic("");
		}
		if(!ValidationUtil.isNull(getcPais())){
			empresa.setCodpais(getcPais());
		}else {
			empresa.setCodpais("");
		}
		if(!ValidationUtil.isNullOrEmpty(getComplemento())){
			empresa.setComplemp(getComplemento());
		}else {
			empresa.setComplemp("");
		}
		
		empresa.setDddemp("");
		
	    if(!ValidationUtil.isNullOrEmpty(getEmail())){
			empresa.setEmailemp(getEmail());
		}else {
			empresa.setEmailemp("");
		}
	    if(!ValidationUtil.isNullOrEmpty(getFax())){
			empresa.setFaxemp(getFax());
		}else {
			empresa.setFaxemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getTelefone())){
			empresa.setFoneemp(getTelefone());
		}else {
			empresa.setFoneemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getInsEst())){
			empresa.setInscemp(getInsEst());
		}else {
			empresa.setInscemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getIntalmfil())){
			empresa.setMultialmoxemp(getIntalmfil());
		}else {
			empresa.setMultialmoxemp("true");
		}
		if(!ValidationUtil.isNullOrEmpty(getContato())){
			empresa.setNomecontemp(getContato());
		}else {
			empresa.setNomecontemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getNomFant())){
			empresa.setNomeemp(getNomFant());
		}else {
			empresa.setNomeemp("");
		}
		if(!ValidationUtil.isNull(getNumero())){
			empresa.setNumemp(getNumero());
		}else {
			empresa.setNumemp(0);
		}
		if(!ValidationUtil.isNull(getiSS())){
			empresa.setPercissemp(getiSS());
		}else {
			empresa.setPercissemp(new Float(0.00));
		}
		if(!ValidationUtil.isNullOrEmpty(getRazSocEmp())){
			empresa.setRazemp(getRazSocEmp());
		}else {
			empresa.setRazemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getRazSocEmp())){
			empresa.setRazemp(getRazSocEmp());
		}else {
			empresa.setRazemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getUf())){
			empresa.setSiglauf(getUf());
		}else {
			empresa.setSiglauf("");
		}
		if(!ValidationUtil.isNullOrEmpty(getUf())){
			empresa.setUfemp(getUf());
		}else {
			empresa.setUfemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getPaginaWEB())){
			empresa.setWwwemp(getPaginaWEB());
		}else {
			empresa.setWwwemp("");
		}
		empresa.setFotoemp("");

		
		EmpresaRequest empresaRequest = new EmpresaRequest();
		empresaRequest.setEmpresa(empresa);
		
		
		
		UserContext contest= new UserContext();
		contest.setUserId("rod");
		contest.setTenant(new Tenant(1));
	//	getInquiryEmpresaRequest().setUserContext(getUserContext());
	//	BERequestContextUtil.enableContext(inquiryempresaRequest);
		empresaRequest.setUserContext(contest);
		EmpresaResponse empresaResponse = getEmpresaBCF().insertEmpresa(empresaRequest);
		setResponse(empresaResponse);
		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Could not search SmartPoints", ex);
			}
		}
		
		
		return SUCCESS;
	}

	/**
	 * Update empresa.
	 *
	 * @return the empresa response
	 */
	public String updateEmpresa()
	{
		EmpresaRequest empresaRequest = new EmpresaRequest();
		return SUCCESS;
	}

	/**
	 * Delete empresa.
	 *
	 * @return the empresa response
	 */
	public String deleteEmpresa()
	{
		EmpresaRequest empresaRequest = new EmpresaRequest();
		return SUCCESS;
	}

	/**
	 * Fetch empresa by id.
	 *
	 * @return the empresa response
	 */
	public String fetchEmpresaById()
	{
		EmpresaRequest empresaRequest = new EmpresaRequest();
		return SUCCESS;
	}

	/**
	 * Generate file csv.
	 *
	 * @return the inquiry empresa response
	 */
	public String generateFileCSV()
	{
		InquiryEmpresaRequest inquiryEmpresaRequest = new InquiryEmpresaRequest();
		return SUCCESS;
	}

	/**
	 * Insert csv process.
	 *
	 * @return the process response
	 */
	public String insertCSVProcess()
	{
		InquiryEmpresaRequest empresaRequest = new InquiryEmpresaRequest();
		return SUCCESS;
	}

	/**
	 * Fetch all empresa types.
	 * 
	 * @param request the request
	 * @return the empresa response
	 */
	public String fetchAllEmpresaTypes(Request request)
	{
		return null;
	}

	/**
	 * Fetch all empresa filial.
	 *
	 * @return the empresa response
	 */
	public String fetchAllEmpresaFilial()
	{
		InquiryEmpresaRequest empresaRequest = new InquiryEmpresaRequest();
		
		setSearchResult(new SearchJsonResult());
	try{	
		if((!ValidationUtil.isNullOrZero(getCodempr()))&&(!ValidationUtil.isNullOrZero(getCodfil()))){
			List<Empresa> empresaIds = new ArrayList<Empresa>();
			List<Filial> filialIds = new ArrayList<Filial>();
			List<String[]> records = new ArrayList<String[]>();
			Empresa empresa = new Empresa();
			Filial filial = new Filial();
			filial.setCodfilial(getCodfil());
			filialIds.add(filial);
			empresa.setCodemp(getCodempr());
			empresa.setListFilial(filialIds);
			empresaIds.add(empresa);
			empresaRequest.setEmpresas(empresaIds);
			
			EmpresaResponse empresaResponse = getEmpresaBCF().fetchAllEmpresaFilial(empresaRequest);
			
			for (Filial filials : empresaResponse.getEmpresa().get(0).getListFilial())
			{
			//	records.add(getTableColumns(filials));
			}

			getSearchResult().setAaData(records.toArray(new String[records.size()][]));


		}
		 }
		 catch (Exception e)
		 {
		 if (LOG.isErrorEnabled())
		 {
		 LOG.error("Error deleting tag", e);
		 }

		 }
		
		 return SUCCESS;
 }


	// /**
	// * Adds smart points to a given group.
	// *
	// * @return the string
	// */
	// public String addSmartPointsToGroup()
	// {
	//
	// setSmartpointResult(new JsonResult());
	//
	// try
	// {
	// DeviceRequest deviceRequest = new DeviceRequest();
	//
	// // ADD user context to request
	// BERequestContextUtil.enableContext(deviceRequest);
	//
	// Group group = new Group();
	// group.setId(getGroupId());
	// group.setName(getGroupName());
	// group.setModelAction(PersistanceActionEnum.INSERT); // review!
	//
	// deviceRequest.setGroup(group);
	//
	// List<Integer> meterIds = new ArrayList<Integer>();
	//
	// for (Integer smartPointId : getSmartpointIds())
	// {
	// meterIds.add(smartPointId);
	// }
	//
	// deviceRequest.setSelectionPaginationIds(meterIds);
	// deviceRequest.setPaginationAllSelected(getIsAllRows());
	// deviceRequest.setSearchDevice(new SearchMeter());
	//
	// DeviceResponse groupResponse = getGroupBCF().insertDeviceToGroup(deviceRequest);
	//
	// ResultUtil.setMessages(getSmartpointResult(), groupResponse);
	//
	// }
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error("Error deleting tag", e);
	// }
	// getSmartpointResult().setResult(Constants.JSON_FAIL);
	// }
	//
	// return SUCCESS;
	// }

	// /**
	// * Insert list smartpoint to tag.
	// *
	// * @return the string
	// */
	// public String insertListSmartpointToTag()
	// {
	// setSmartpointResult(new JsonResult());
	// try
	// {
	// DeviceRequest deviceRequest = new DeviceRequest();
	//
	// // ADD user context to request
	// BERequestContextUtil.enableContext(deviceRequest);
	//
	// List<Integer> idList = new ArrayList<Integer>();
	// if (!ValidationUtil.isNullOrEmpty(getSmartpointIds()))
	// {
	// for (Integer id : getSmartpointIds())
	// {
	// idList.add(id);
	// }
	// }
	//
	// Tag tag = new Tag();
	// if (!ValidationUtil.isNullOrZero(getTagId()))
	// {
	// tag.setId(getTagId());
	// }
	// else if (!ValidationUtil.isNullOrEmpty(getTagName()))
	// {
	// tag.setName(getTagName());
	// }
	//
	// deviceRequest.setTag(tag);
	// deviceRequest.setPaginationAllSelected(getIsAllRows());
	// deviceRequest.setSelectionPaginationIds(idList);
	//
	// if (!ValidationUtil.isNull(getFilterUrl()))
	// {
	// SearchMeter searchMeter = new SearchMeter();
	// deviceRequest.setSearchDevice(searchMeter);
	// }
	// else
	// {
	// deviceRequest.setSearchDevice(new SearchMeter());
	// }
	//
	// TagResponse tagResponse = getTagBCF().insertDeviceToTag(deviceRequest);
	//
	// ResultUtil.setMessages(getSmartpointResult(), tagResponse);
	// }
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error("Error adding SmartPoints to tag", e);
	// }
	// getSmartpointResult().setResult(Constants.JSON_FAIL);
	// }
	//
	// return SUCCESS;
	// }
	//
	// /**
	// * Remove list smartpoint to tag.
	// *
	// * @return the string
	// */
	// public String removeListSmartpointToTag()
	// {
	// setSmartpointResult(new JsonResult());
	// try
	// {
	// DeviceRequest deviceRequest = new DeviceRequest();
	//
	// // ADD user context to request
	// BERequestContextUtil.enableContext(deviceRequest);
	//
	// List<Integer> idList = new ArrayList<Integer>();
	// if (!ValidationUtil.isNullOrEmpty(getSmartpointIds()))
	// {
	// for (Integer lightId : getSmartpointIds())
	// {
	// idList.add(lightId);
	// }
	// }
	//
	// Tag tag = new Tag();
	// tag.setId(getTagId());
	//
	// deviceRequest.setTag(tag);
	// deviceRequest.setPaginationAllSelected(getIsAllRows());
	// deviceRequest.setSelectionPaginationIds(idList);
	//
	// if (!ValidationUtil.isNull(getFilterUrl()))
	// {
	// SearchMeter searchMeter = new SearchMeter();
	// deviceRequest.setSearchDevice(searchMeter);
	// }
	// else
	// {
	// deviceRequest.setSearchDevice(new SearchMeter());
	// }
	//
	// TagResponse tagResponse = getTagBCF().deleteDeviceFromTag(deviceRequest);
	//
	// ResultUtil.setMessages(getSmartpointResult(), tagResponse);
	// }
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error("Error remove SmartPoints from tag", e);
	// }
	// getSmartpointResult().setResult(Constants.JSON_FAIL);
	// }
	//
	// return SUCCESS;
	// }
	//
	// /**
	// * Removes meters from a group.
	// *
	// * @return always "SUCCESS"
	// */
	// public String removeMeter()
	// {
	// setResult(new JsonResult());
	// try
	// {
	// DeviceRequest request = new DeviceRequest();
	//
	// // ADD user context to request
	// BERequestContextUtil.enableContext(request);
	//
	// request.setGroup(new Group());
	// request.getGroup().setModelAction(PersistanceActionEnum.DELETE);
	// request.getGroup().setId(getGroupId());
	//
	// List<Meter> metersInGroup = new ArrayList<Meter>();
	// List<Integer> idList = new ArrayList<Integer>();
	//
	// for (Integer id : getSmartpointIds())
	// {
	// Meter meter = new Meter();
	// meter.setFlexNetId(id);
	// idList.add(id);
	// metersInGroup.add(meter);
	// }
	//
	// request.getGroup().setMeters(metersInGroup);
	// request.setPaginationAllSelected(getIsAllRows());
	// request.setSelectionPaginationIds(idList);
	//
	// if (!ValidationUtil.isNull(getFilterUrl()))
	// {
	// SearchMeter searchMeter = new SearchMeter();
	// request.setSearchDevice(searchMeter);
	// }
	// else
	// {
	// request.setSearchDevice(new SearchMeter());
	// }
	//
	// DeviceResponse response = getGroupBCF().deleteDeviceFromGroup(request);
	// ResultUtil.setMessages(getResult(), response);
	// }
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error("Error removing SmartPoints from group", e);
	// }
	// getResult().setResult(Constants.JSON_FAIL);
	// }
	// return SUCCESS;
	// }
	//
	// /**
	// * Save search definition.
	// *
	// * @return the string
	// */
	// public String saveSearch()
	// {
	// setSavedSearchResult(new JsonResult());
	//
	// try
	// {
	// Map<String, String[]> parameters = this.getParameters();
	//
	// CustomSearch customSearch = new CustomSearch();
	//
	// customSearch.setName(getSearchName());
	// customSearch.setDescription(getSearchDescription());
	// customSearch.setModelAction(PersistanceActionEnum.INSERT);
	//
	// // Set Filter
	// getMeterValues(customSearch, parameters);
	// customSearch.setSearchParameters(getParametersCustomSearch(parameters).getSearchParameters());
	// customSearch.setSortExpression(ActionPaginationUtil.getSmartpointSortExpression(parameters, null));
	//
	// List<Column> listColumns = new ArrayList<Column>();
	//
	// /** * check if there are COLUMNS **/
	// if (!ValidationUtil.isNull(getColumnsSave()))
	// {
	// for (String propertyColumn : getColumnsSave())
	// {
	// if (!ValidationUtil.isNullOrEmpty(propertyColumn))
	// {
	// Column column = new Column();
	// column.setColumnEnum(MeterColumnEnum.valueOf(propertyColumn));
	// listColumns.add(column);
	// }
	// }
	// }
	//
	// List<MeterFilterEnum> listFilters = new ArrayList<MeterFilterEnum>();
	//
	// /** * check if there are FILTERS **/
	// if (!ValidationUtil.isNull(getFilters()))
	// {
	// for (String filter : getFilters())
	// {
	// if (!ValidationUtil.isNullOrEmpty(filter) && !filter.equalsIgnoreCase("custom_filters"))
	// {
	// listFilters.add(MeterFilterEnum.valueOf(filter.toUpperCase()));
	// }
	// }
	// }
	//
	// CustomSearchRequest customSearchRequest = new CustomSearchRequest();
	//
	// // ADD user context to request
	// BERequestContextUtil.enableContext(customSearchRequest);
	//
	// customSearch.setListColumn(listColumns);
	// customSearch.setListFilters(listFilters);
	//
	// customSearchRequest.setCustomSearch(customSearch);
	//
	// CustomSearchResponse response = getDeviceBCF().insertCustomSearch(customSearchRequest);
	//
	// if (ResultUtil.setMessages(getSavedSearchResult(), response))
	// {
	// Integer[] customSearchId = {response.getCustomSearches().get(0).getId()};
	// String[] customSearchName = {response.getCustomSearches().get(0).getName()};
	//
	// getSavedSearchResult().setIntResult(customSearchId);
	// getSavedSearchResult().setStringResult(customSearchName);
	// }
	// else
	// {
	// return ERROR;
	// }
	//
	// }
	// catch (Exception e)
	// {
	//
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error(ERROR_SAVING_SEARCH, e);
	// }
	// getSavedSearchResult().setResult(Constants.JSON_FAIL);
	//
	// return ERROR;
	// }
	// return SUCCESS;
	// }
	//
	// /**
	// * Gets the custom search.
	// *
	// * @param parameters the parameters
	// * @return the custom search
	// */
	// private CustomSearch getParametersCustomSearch(Map<String, String[]> parameters)
	// {
	// // Add Search Text
	// CustomSearch customSearch = fillListParametersSearch(parameters.get("query"));
	//
	// List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();
	//
	// // Add Groups
	// fillListParameters(parameters.get("groups"), "Group", searchParameters, MeterFilterEnum.GROUPS);
	//
	// // Add Lifecycle States
	// fillListParameters(parameters.get("lifecycle_state"), "Lifecycle_State", searchParameters,
	// MeterFilterEnum.LIFECYCLE_STATE);
	//
	// // Add Tags
	// fillListParameters(parameters.get("tag"), "Tag", searchParameters, MeterFilterEnum.TAG);
	//
	// // Add Device Type
	// fillListParameters(parameters.get("device_type"), "Device_Type", searchParameters, MeterFilterEnum.DEVICE_TYPE);
	// // Add Installed Date Started
	// fillListParameters(parameters.get(START), "Installed Date Start", searchParameters,
	// MeterFilterEnum.INSTALL_DATE_START);
	//
	// // Add Installed Date End
	// fillListParameters(parameters.get(END), "Installed Date End", searchParameters,
	// MeterFilterEnum.INSTALL_DATE_END);
	//
	// if (!ValidationUtil.isNull(parameters.get(START)) || !ValidationUtil.isNull(parameters.get(END)))
	// {
	//
	// Map<String, Object> session = ActionContext.getContext().getSession();
	// UserLocaleSettings userLocaleSettings = (UserLocaleSettings)session.get("userLocaleSettings");
	//
	// String[] parameter = new String[1];
	//
	// parameter[0] = EPMConvertUtil.getDateFormat(userLocaleSettings.getDateFormatMask());
	//
	// fillListParameters(parameter, "Date Format", searchParameters,
	// MeterFilterEnum.DATE_FORMAT);
	//
	// }
	//
	// // Add Processes
	// fillListParameters(parameters.get("processId"), "Process Id", searchParameters, MeterFilterEnum.PROCESSES);
	//
	// // Add Firmware Version Major
	// fillListParameters(parameters.get("major"), "Firmware Version Major", searchParameters,
	// MeterFilterEnum.MAJOR_VERSION);
	//
	// // Add Firmware Version Minor
	// fillListParameters(parameters.get("minor"), "Firmware Version Minor", searchParameters,
	// MeterFilterEnum.MINOR_VERSION);
	//
	// // Add Firmware Version Patch
	// fillListParameters(parameters.get("patch"), "Firmware Version Patch", searchParameters,
	// MeterFilterEnum.PATCH_VERSION);
	//
	// // Add Firmware Version Engineering
	// fillListParameters(parameters.get("engineering"), "Firmware Version Engineering", searchParameters,
	// MeterFilterEnum.ENGINEERING_VERSION);
	//
	// // Add Encryption Supported
	// fillListParameters(parameters.get("encryption_supported"), "Encryption Supported", searchParameters,
	// MeterFilterEnum.ENCRYPTION_SUPPORTED);
	//
	// // Add Encryption Status
	// fillListParameters(parameters.get("encryption_status"), "Encryption Status", searchParameters,
	// MeterFilterEnum.ENCRYPTION_STATUS);
	//
	// // Add Connection Status
	// fillListParameters(parameters.get("connection_status"), "Connection Status", searchParameters,
	// MeterFilterEnum.CONNECTION_STATUS);
	//
	// // Add Remote Disconnect
	// fillListParameters(parameters.get("remote_disconnect"), "Remote Disconnect", searchParameters,
	// MeterFilterEnum.REMOTE_DISCONNECT);
	//
	// customSearch.setSearchParameters(searchParameters);
	//
	// // Add Status List
	// customSearch.setStatusList(getStatusList(parameters));
	//
	// return customSearch;
	// }
	//
	// /**
	// * Fill list parameters search.
	// *
	// * @param parameter the parameter
	// * @return the custom search
	// */
	// public CustomSearch fillListParametersSearch(String[] parameter)
	// {
	// CustomSearch customSearch = new CustomSearch();
	// try
	// {
	// if (!ValidationUtil.isNull(parameter))
	// {
	// Meter meter = new Meter();
	//
	// String[] search = parameter[0].split(PIPE);
	//
	// /** * Meter ID **/
	// if (search[0].equals("m_id"))
	// {
	// meter.setMeterId(search[1]);
	// customSearch.setSearchType(SearchTypeEnum.METER_ID);
	// }
	// else
	// /** * FlexNet ID **/
	// if (search[0].equals("fn_id"))
	// {
	// meter.setFlexNetId(Integer.parseInt(search[1]));
	// customSearch.setSearchType(SearchTypeEnum.FLEXNET_ID);
	// }
	//
	// customSearch.setDevice(meter);
	// customSearch.setSearchText(search[1]);
	//
	// }
	// }
	// catch (Exception e)
	// {
	// if (LOG.isDebugEnabled())
	// {
	// LOG.debug(String.format("Error parsing search from '%s'",
	// parameter[0]), e);
	// }
	// }
	//
	// return customSearch;
	// }
	//
	// /**
	// * Fill list parameters.
	// *
	// * @param parameter the parameter
	// * @param type the type
	// * @param searchParameters the search parameters
	// * @param filterType the filter type
	// */
	// public void fillListParameters(String[] parameter, String type, List<SearchParameter> searchParameters,
	// MeterFilterEnum filterType)
	// {
	// try
	// {
	// if (!ValidationUtil.isNull(parameter))
	// {
	// for (String p : parameter[0].split(PIPE))
	// {
	// if (!ValidationUtil.isNullOrEmpty(p))
	// {
	// if (!p.equals(PIPE))
	// {
	// SearchParameter searchParameter = new SearchParameter();
	// searchParameter.setValue(p);
	// searchParameter.setFilterEnum(filterType);
	// searchParameters.add(searchParameter);
	// }
	// }
	// }
	// }
	// }
	// catch (Exception e)
	// {
	// if (LOG.isDebugEnabled())
	// {
	// LOG.debug(String.format("Error parsing " + type + " from '%s'",
	// parameter[0]), e);
	// }
	// }
	// }
	//
	// public String typeActionProperty()
	// {
	// setSearchResult(new SearchJsonResult());
	//
	// String[] propertyValue = new String[ONE];
	//
	// try
	// {
	//
	// ActionRequest actionRequest = new ActionRequest();
	//
	// // ADD user context to request
	// BERequestContextUtil.enableContext(actionRequest);
	//
	// Action action = new Action();
	//
	// action.setId(getActionId());
	//
	// actionRequest.setAction(action);
	//
	// ActionResponse actionResponse =
	// getActionBCF().fetchActionById(actionRequest);
	//
	// ResultUtil.setMessages(getSearchResult(), actionResponse);
	//
	// List<String[]> records = new ArrayList<String[]>();
	//
	// if (!ValidationUtil.isNullOrEmpty(actionResponse.getActions().get(0).getProperties().get(0)
	// .getPropertyValue()))
	// {
	// propertyValue[0] = actionResponse.getActions().get(0).getProperties().get(0).getPropertyValue();
	// }
	// records.add(getGraphicItems(actionResponse.getActions().get(0)));
	// getSearchResult().setAaData(records.toArray(new String[records.size()][]));
	// }
	// catch (Exception e)
	// {
	// getSearchResult().setResult(Constants.JSON_FAIL);
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error(ERROR_ACTION_ERROR, e);
	// }
	// return ERROR;
	// }
	// return SUCCESS;
	// }
	//
	// /**
	// * Gets the status list.
	// *
	// * @param parameters the parameters
	// * @return the status list
	// */
	// private List<MeterStatusEnum> getStatusList(Map<String, String[]> parameters)
	// {
	// List<MeterStatusEnum> statusList = new ArrayList<MeterStatusEnum>();
	// try
	// {
	// if (!ValidationUtil.isNull(parameters.get(STATUS)))
	// {
	// for (String s : parameters.get(STATUS)[0].split(PIPE))
	// {
	// if (!ValidationUtil.isNullOrEmpty(s))
	// {
	// switch (Integer.valueOf(s))
	// {
	// case 0:
	// statusList.add(MeterStatusEnum.ARMED);
	// break;
	// case 1:
	// statusList.add(MeterStatusEnum.DISCONNECTED);
	// break;
	// case 2:
	// statusList.add(MeterStatusEnum.CONNECTED);
	// break;
	// default:
	// break;
	// }
	// }
	// }
	// }
	// return statusList;
	// }
	// catch (Exception e)
	// {
	// if (LOG.isDebugEnabled())
	// {
	// LOG.debug(String.format("Could not parse Status from '%s'",
	// parameters.get(STATUS)[0]), e);
	// }
	// return null;
	// }
	// }
	//
	// /**
	// * Gets the meter values.
	// *
	// * @param customSearch the custom search
	// * @param parameters the parameters
	// * @return the meter values
	// */
	// private void getMeterValues(CustomSearch customSearch, Map<String, String[]> parameters)
	// {
	//
	// Meter meter = null;
	//
	// if (ValidationUtil.isNull(customSearch.getDevice()))
	// {
	// meter = new Meter();
	// }
	// else
	// {
	// meter = (Meter)customSearch.getDevice();
	// }
	//
	// if (!ValidationUtil.isNull(parameters.get(STREET)))
	// {
	// meter.setAddress(parameters.get(STREET)[0]);
	// }
	//
	// if (!ValidationUtil.isNull(parameters.get(CITY)))
	// {
	// meter.setCity(parameters.get(CITY)[0]);
	// }
	//
	// if (!ValidationUtil.isNull(parameters.get(ZIP)))
	// {
	// meter.setZip(parameters.get(ZIP)[0]);
	// }
	//
	// customSearch.setDevice(meter);
	// }
	//
	// /**
	// * Insert column filter.
	// *
	// * @return the string
	// */
	// public String insertColumnFilter()
	// {
	// setSmartpointResult(new JsonResult());
	//
	// try
	// {
	// ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();
	//
	// columnFilterRequest.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);
	//
	// // ADD user context to request
	// BERequestContextUtil.enableContext(columnFilterRequest);
	//
	// /** * check if there are FILTERS **/
	// if (!ValidationUtil.isNull(getFilters()))
	// {
	// List<MeterFilterEnum> filtersEnum = new ArrayList<MeterFilterEnum>();
	//
	// for (String filter : getFilters())
	// {
	// if (!ValidationUtil.isNullOrEmpty(filter))
	// {
	// filtersEnum.add(MeterFilterEnum.valueOf(filter.toUpperCase()));
	// }
	// }
	//
	// columnFilterRequest.setFilters(filtersEnum);
	// }
	//
	// /** * check if there are COLUMNS **/
	// if (!ValidationUtil.isNull(getColumns()))
	// {
	// List<Column> listColumns = new ArrayList<Column>();
	// for (String propertyColumn : getColumns())
	// {
	// if (!ValidationUtil.isNullOrEmpty(propertyColumn))
	// {
	// Column column = new Column();
	// column.setColumnEnum(MeterColumnEnum.valueOf(propertyColumn));
	// listColumns.add(column);
	// }
	// }
	// columnFilterRequest.setListColumn(listColumns);
	// }
	//
	// ColumnFilterResponse columnFilterResponse = getDeviceBCF().insertColumnFilters(columnFilterRequest);
	// ResultUtil.setMessages(getSmartpointResult(), columnFilterResponse);
	// }
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error("Error inserting SmartPoints Columns and filters", e);
	// }
	// getSmartpointResult().setResult(Constants.JSON_FAIL);
	// }
	//
	// return SUCCESS;
	// }
	//
	// /**
	// * Adds meters to a group.
	// *
	// * @return always "SUCCESS"
	// */
	// public String addMeters()
	// {
	// setResult(new JsonResult());
	// try
	// {
	// DeviceRequest request = new DeviceRequest();
	//
	// // ADD user context to request
	// BERequestContextUtil.enableContext(request);
	//
	// request.setGroup(new Group());
	// request.getGroup().setModelAction(PersistanceActionEnum.INSERT);
	// request.getGroup().setId(getGroupId());
	// List<Meter> smartPointsInGroup = new ArrayList<Meter>();
	//
	// List<Integer> idList = new ArrayList<Integer>();
	// if (!ValidationUtil.isNull(getSmartpointIds()))
	// {
	// for (Integer id : getSmartpointIds())
	// {
	// Meter smartpoint = new Meter();
	// smartpoint.setFlexNetId(id);
	// idList.add(id);
	// smartPointsInGroup.add(smartpoint);
	// }
	// }
	// request.getGroup().setMeters(smartPointsInGroup);
	// request.setPaginationAllSelected(getIsAllRows());
	// request.setSelectionPaginationIds(idList);
	//
	// if (!ValidationUtil.isNull(getFilterUrl()))
	// {
	// SearchMeter searchMeter = new SearchMeter();
	// request.setSearchDevice(searchMeter);
	// }
	// else
	// {
	// request.setSearchDevice(new SearchMeter());
	// }
	//
	// DeviceResponse response = getGroupBCF().insertDeviceToGroup(request);
	// ResultUtil.setMessages(getResult(), response);
	// }
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error("Error adding Meters to group", e);
	// }
	// getResult().setResult(Constants.JSON_FAIL);
	// }
	// return SUCCESS;
	// }
	//
	// /**
	// * Gets the network device bcf.
	// *
	// * @return the network device bcf
	// */
	// public IDeviceBCF getDeviceBCF()
	// {
	// return deviceBCF;
	// }
	//
	// /**
	// * Sets the network device bcf.
	// *
	// * @param deviceBCF the new device bcf
	// */
	// public void setDeviceBCF(IDeviceBCF deviceBCF)
	// {
	// this.deviceBCF = deviceBCF;
	// }
	//
	// /**
	// * Gets the saved search result.
	// *
	// * @return the saved search result
	// */
	// public JsonResult getSavedSearchResult()
	// {
	// return savedSearchResult;
	// }
	//
	// /**
	// * Sets the saved search result.
	// *
	// * @param savedSearchResult the new saved search result
	// */
	// public void setSavedSearchResult(JsonResult savedSearchResult)
	// {
	// this.savedSearchResult = savedSearchResult;
	// }
	//
	// /**
	// * Gets the group bcf.
	// *
	// * @return the group bcf
	// */
	// public IGroupBCF getGroupBCF()
	// {
	// return groupBCF;
	// }
	//
	// /**
	// * Sets the group bcf.
	// *
	// * @param groupBCF the new group bcf
	// */
	// public void setGroupBCF(IGroupBCF groupBCF)
	// {
	// this.groupBCF = groupBCF;
	// }
	//
	// /**
	// * Gets the meter id.
	// *
	// * @return the meter id
	// */
	// public Integer getMeterId()
	// {
	// return meterId;
	// }
	//
	// /**
	// * Sets the meter id.
	// *
	// * @param meterId the new meter id
	// */
	// public void setMeterId(Integer meterId)
	// {
	// this.meterId = meterId;
	// }
	//
	// /**
	// * Gets the smartpoint ids.
	// *
	// * @return the smartpoint ids
	// */
	// public List<Integer> getSmartpointIds()
	// {
	// return smartpointIds;
	// }
	//
	// /**
	// * Sets the smartpoint ids.
	// *
	// * @param smartpointIds the new smartpoint ids
	// */
	// public void setSmartpointIds(List<Integer> smartpointIds)
	// {
	// this.smartpointIds = smartpointIds;
	// }
	//
	// /**
	// * Gets the group id.
	// *
	// * @return the group id
	// */
	// public Integer getGroupId()
	// {
	// return groupId;
	// }
	//
	// /**
	// * Sets the group id.
	// *
	// * @param groupId the new group id
	// */
	// public void setGroupId(Integer groupId)
	// {
	// this.groupId = groupId;
	// }
	//
	// /**
	// * Gets the group name.
	// *
	// * @return the group name
	// */
	// public String getGroupName()
	// {
	// return groupName;
	// }
	//
	// /**
	// * Sets the group name.
	// *
	// * @param groupName the new group name
	// */
	// public void setGroupName(String groupName)
	// {
	// this.groupName = groupName;
	// }
	//
	// /**
	// * Sets the smartpoint result.
	// *
	// * @param smartpointResult the new smartpoint result
	// */
	// public void setSmartpointResult(JsonResult smartpointResult)
	// {
	// this.smartpointResult = smartpointResult;
	// }
	//
	// /**
	// * Gets the smartpoint result.
	// *
	// * @return the smartpoint result
	// */
	// public JsonResult getSmartpointResult()
	// {
	// return smartpointResult;
	// }
	//
	// /**
	// * Gets the tag bcf.
	// *
	// * @return the tagBCF
	// */
	// public ITagBCF getTagBCF()
	// {
	// return tagBCF;
	// }
	//
	// /**
	// * Sets the tag bcf.
	// *
	// * @param tagBCF the tagBCF to set
	// */
	// public void setTagBCF(ITagBCF tagBCF)
	// {
	// this.tagBCF = tagBCF;
	// }
	//
	// /**
	// * Sets the tag name.
	// *
	// * @param tagName the new tag name
	// */
	// public void setTagName(String tagName)
	// {
	// this.tagName = tagName;
	// }
	//
	// /**
	// * Gets the tag name.
	// *
	// * @return the tag name
	// */
	// public String getTagName()
	// {
	// return tagName;
	// }
	//
	// /**
	// * Sets the tag id.
	// *
	// * @param tagId the tagId to set
	// */
	// public void setTagId(Integer tagId)
	// {
	// this.tagId = tagId;
	// }
	//
	// /**
	// * Gets the tag id.
	// *
	// * @return the tagId
	// */
	// public Integer getTagId()
	// {
	// return tagId;
	// }
	//
	// /**
	// * Sets the filter url.
	// *
	// * @param filterUrl the new filter url
	// */
	// public void setFilterUrl(Integer[][] filterUrl)
	// {
	// this.filterUrl = filterUrl;
	// }
	//
	// /**
	// * Gets the filter url.
	// *
	// * @return the filter url
	// */
	// public Integer[][] getFilterUrl()
	// {
	// return filterUrl;
	// }
	//
	// /**
	// * Gets the checks if is all rows.
	// *
	// * @return the checks if is all rows
	// */
	// public Boolean getIsAllRows()
	// {
	// return isAllRows;
	// }
	//
	// /**
	// * Sets the checks if is all rows.
	// *
	// * @param isAllRows the new checks if is all rows
	// */
	// public void setIsAllRows(Boolean isAllRows)
	// {
	// this.isAllRows = isAllRows;
	// }
	//
	// /**
	// * Gets the checks if is monitored.
	// *
	// * @return the isMonitored
	// */
	// public Boolean getIsMonitored()
	// {
	// return isMonitored;
	// }
	//
	// /**
	// * Sets the checks if is monitored.
	// *
	// * @param isMonitored the isMonitored to set
	// */
	// public void setIsMonitored(Boolean isMonitored)
	// {
	// this.isMonitored = isMonitored;
	// }
	//
	// /**
	// * Gets the search name.
	// *
	// * @return the search name
	// */
	// public String getSearchName()
	// {
	// return searchName;
	// }
	//
	// /**
	// * Sets the search name.
	// *
	// * @param searchName the new search name
	// */
	// public void setSearchName(String searchName)
	// {
	// this.searchName = searchName;
	// }
	//
	// /**
	// * Gets the search description.
	// *
	// * @return the search description
	// */
	// public String getSearchDescription()
	// {
	// return searchDescription;
	// }
	//
	// /**
	// * Sets the search description.
	// *
	// * @param searchDescription the new search description
	// */
	// public void setSearchDescription(String searchDescription)
	// {
	// this.searchDescription = searchDescription;
	// }
	//
	// /**
	// * Gets the query.
	// *
	// * @return the query
	// */
	// public String getQuery()
	// {
	// return query;
	// }
	//
	// /**
	// * Sets the query.
	// *
	// * @param query the new query
	// */
	// public void setQuery(String query)
	// {
	// this.query = query;
	// }
	//
	// /**
	// * Gets the start.
	// *
	// * @return the start
	// */
	// public String getStart()
	// {
	// return start;
	// }
	//
	// /**
	// * Sets the start.
	// *
	// * @param start the new start
	// */
	// public void setStart(String start)
	// {
	// this.start = start;
	// }
	//
	// /**
	// * Gets the end.
	// *
	// * @return the end
	// */
	// public String getEnd()
	// {
	// return end;
	// }
	//
	// /**
	// * Sets the end.
	// *
	// * @param end the new end
	// */
	// public void setEnd(String end)
	// {
	// this.end = end;
	// }
	//
	// /**
	// * Gets the groups.
	// *
	// * @return the groups
	// */
	// public String getGroups()
	// {
	// return groups;
	// }
	//
	// /**
	// * Sets the groups.
	// *
	// * @param groups the new groups
	// */
	// public void setGroups(String groups)
	// {
	// this.groups = groups;
	// }
	//
	// /**
	// * Gets the tags.
	// *
	// * @return the tags
	// */
	// public String getTags()
	// {
	// return tags;
	// }
	//
	// /**
	// * Sets the tags.
	// *
	// * @param tags the new tags
	// */
	// public void setTags(String tags)
	// {
	// this.tags = tags;
	// }
	//
	// /**
	// * Gets the firmware version.
	// *
	// * @return the firmware version
	// */
	// public String getFirmwareVersion()
	// {
	// return firmwareVersion;
	// }
	//
	// /**
	// * Sets the firmware version.
	// *
	// * @param sFirmwareVersion the new firmware version
	// */
	// public void setFirmwareVersion(String sFirmwareVersion)
	// {
	// firmwareVersion = sFirmwareVersion;
	// }
	//
	// /**
	// * Gets the street.
	// *
	// * @return the street
	// */
	// public String getStreet()
	// {
	// return street;
	// }
	//
	// /**
	// * Sets the street.
	// *
	// * @param street the new street
	// */
	// public void setStreet(String street)
	// {
	// this.street = street;
	// }
	//
	// /**
	// * Gets the city.
	// *
	// * @return the city
	// */
	// public String getCity()
	// {
	// return city;
	// }
	//
	// /**
	// * Sets the city.
	// *
	// * @param city the new city
	// */
	// public void setCity(String city)
	// {
	// this.city = city;
	// }
	//
	// /**
	// * Gets the status.
	// *
	// * @return the status
	// */
	// public String getStatus()
	// {
	// return status;
	// }
	//
	// /**
	// * Sets the status.
	// *
	// * @param status the new status
	// */
	// public void setStatus(String status)
	// {
	// this.status = status;
	// }
	//
	// /**
	// * Gets the columns.
	// *
	// * @return the columns
	// */
	// public List<String> getColumns()
	// {
	// return columns;
	// }
	//
	// /**
	// * Sets the columns.
	// *
	// * @param columns the new columns
	// */
	// public void setColumns(List<String> columns)
	// {
	// this.columns = columns;
	// }
	//
	// /**
	// * Gets the filters.
	// *
	// * @return the filters
	// */
	// public List<String> getFilters()
	// {
	// return filters;
	// }
	//
	// /**
	// * Sets the filters.
	// *
	// * @param filters the new filters
	// */
	// public void setFilters(List<String> filters)
	// {
	// this.filters = filters;
	// }
	//
	// /**
	// * Gets the columns save.
	// *
	// * @return the columns save
	// */
	// public List<String> getColumnsSave()
	// {
	// return columnsSave;
	// }
	//
	// /**
	// * Sets the columns save.
	// *
	// * @param columnsSave the new columns save
	// */
	// public void setColumnsSave(List<String> columnsSave)
	// {
	// this.columnsSave = columnsSave;
	// }
	//
	// /**
	// * Gets the result.
	// *
	// * @return the result
	// */
	// public JsonResult getResult()
	// {
	// return result;
	// }
	//
	// /**
	// * Sets the result.
	// *
	// * @param result the new result
	// */
	// public void setResult(JsonResult result)
	// {
	// this.result = result;
	// }
	//
	// /**
	// * Gets the ids list.
	// *
	// * @return the ids list
	// */
	// public List<Integer> getIdsList()
	// {
	// return idsList;
	// }
	//
	// /**
	// * Sets the ids list.
	// *
	// * @param idsList the new ids list
	// */
	// public void setIdsList(List<Integer> idsList)
	// {
	// this.idsList = idsList;
	// }
	//
	// /**
	// * @return the searchResult
	// */
	// public SearchJsonResult getSearchResult()
	// {
	// return searchResult;
	// }
	//
	// /**
	// * @param searchResult the searchResult to set
	// */
	// public void setSearchResult(SearchJsonResult searchResult)
	// {
	// this.searchResult = searchResult;
	// }
	//
	// /**
	// * @return the actionBCF
	// */
	// public IActionBCF getActionBCF()
	// {
	// return actionBCF;
	// }
	//
	// /**
	// * @param actionBCF the actionBCF to set
	// */
	// public void setActionBCF(IActionBCF actionBCF)
	// {
	// this.actionBCF = actionBCF;
	// }
	//
	// /**
	// * @return the actionId
	// */
	// public Integer getActionId()
	// {
	// return actionId;
	// }
	//
	// /**
	// * @param actionId the actionId to set
	// */
	// public void setActionId(Integer actionId)
	// {
	// this.actionId = actionId;
	// }

	/**
	 * Gets the search result.
	 *
	 * @return the search result
	 */
	public SearchJsonResult getSearchResult() {
		return searchResult;
	}

	/**
	 * Sets the search result.
	 *
	 * @param searchResult the new search result
	 */
	public void setSearchResult(SearchJsonResult searchResult) {
		this.searchResult = searchResult;
	}

	/**
	 * Gets the empresa bcf.
	 *
	 * @return the empresa bcf
	 */
	public IEmpresaBCF getEmpresaBCF()
	{
		return empresaBCF;
	}

	/**
	 * Sets the empresa bcf.
	 *
	 * @param empresaBCF the new empresa bcf
	 */
	public void setEmpresaBCF(IEmpresaBCF empresaBCF)
	{
		this.empresaBCF = empresaBCF;
	}

	/**
	 * Gets the codempr.
	 *
	 * @return the codempr
	 */
	public Integer getCodempr() {
		return codempr;
	}

	/**
	 * Sets the codempr.
	 *
	 * @param codempr the new codempr
	 */
	public void setCodempr(Integer codempr) {
		this.codempr = codempr;
	}

	/**
	 * Gets the codfil.
	 *
	 * @return the codfil
	 */
	public Integer getCodfil() {
		return codfil;
	}

	/**
	 * Sets the codfil.
	 *
	 * @param codfil the new codfil
	 */
	public void setCodfil(Integer codfil) {
		this.codfil = codfil;
	}

	/**
	 * Gets the empresa result.
	 *
	 * @return the empresa result
	 */
	public SearchResult getEmpresaResult() {
		return empresaResult;
	}

	/**
	 * Sets the empresa result.
	 *
	 * @param empresaResult the new empresa result
	 */
	public void setEmpresaResult(SearchResult empresaResult) {
		this.empresaResult = empresaResult;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the end.
	 *
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * Sets the end.
	 *
	 * @param end the new end
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * Gets the cod emp.
	 *
	 * @return the cod emp
	 */
	public Integer getCodEmp() {
		return codEmp;
	}

	/**
	 * Sets the cod emp.
	 *
	 * @param codEmp the new cod emp
	 */
	public void setCodEmp(Integer codEmp) {
		this.codEmp = codEmp;
	}

	/**
	 * Gets the raz soc emp.
	 *
	 * @return the raz soc emp
	 */
	public String getRazSocEmp() {
		return razSocEmp;
	}

	/**
	 * Sets the raz soc emp.
	 *
	 * @param razSocEmp the new raz soc emp
	 */
	public void setRazSocEmp(String razSocEmp) {
		this.razSocEmp = razSocEmp;
	}

	/**
	 * Gets the nom fant.
	 *
	 * @return the nom fant
	 */
	public String getNomFant() {
		return nomFant;
	}

	/**
	 * Sets the nom fant.
	 *
	 * @param nomFant the new nom fant
	 */
	public void setNomFant(String nomFant) {
		this.nomFant = nomFant;
	}

	/**
	 * Gets the cnpj.
	 *
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * Sets the cnpj.
	 *
	 * @param cnpj the new cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * Gets the ins est.
	 *
	 * @return the ins est
	 */
	public String getInsEst() {
		return insEst;
	}

	/**
	 * Sets the ins est.
	 *
	 * @param insEst the new ins est
	 */
	public void setInsEst(String insEst) {
		this.insEst = insEst;
	}

	/**
	 * Gets the ins mun.
	 *
	 * @return the ins mun
	 */
	public String getInsMun() {
		return insMun;
	}

	/**
	 * Sets the ins mun.
	 *
	 * @param insMun the new ins mun
	 */
	public void setInsMun(String insMun) {
		this.insMun = insMun;
	}

	/**
	 * Gets the cep.
	 *
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Sets the cep.
	 *
	 * @param cep the new cep
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * Gets the logradouro.
	 *
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * Sets the logradouro.
	 *
	 * @param logradouro the new logradouro
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * Gets the complemento.
	 *
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Sets the complemento.
	 *
	 * @param complemento the new complemento
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Gets the bairro.
	 *
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Sets the bairro.
	 *
	 * @param bairro the new bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Gets the cidade.
	 *
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Sets the cidade.
	 *
	 * @param cidade the new cidade
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * Gets the uf.
	 *
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * Sets the uf.
	 *
	 * @param uf the new uf
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * Gets the pagina web.
	 *
	 * @return the pagina web
	 */
	public String getPaginaWEB() {
		return paginaWEB;
	}

	/**
	 * Sets the pagina web.
	 *
	 * @param paginaWEB the new pagina web
	 */
	public void setPaginaWEB(String paginaWEB) {
		this.paginaWEB = paginaWEB;
	}

	/**
	 * Gets the telefone.
	 *
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Sets the telefone.
	 *
	 * @param telefone the new telefone
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Gets the fax.
	 *
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * Sets the fax.
	 *
	 * @param fax the new fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the email n fe.
	 *
	 * @return the email n fe
	 */
	public String getEmailNFe() {
		return emailNFe;
	}

	/**
	 * Sets the email n fe.
	 *
	 * @param emailNFe the new email n fe
	 */
	public void setEmailNFe(String emailNFe) {
		this.emailNFe = emailNFe;
	}

	/**
	 * Gets the c ean.
	 *
	 * @return the c ean
	 */
	public String getcEAN() {
		return cEAN;
	}

	/**
	 * Sets the c ean.
	 *
	 * @param cEAN the new c ean
	 */
	public void setcEAN(String cEAN) {
		this.cEAN = cEAN;
	}

	/**
	 * Gets the c pais.
	 *
	 * @return the c pais
	 */
	public String getcPais() {
		return cPais;
	}

	/**
	 * Sets the c pais.
	 *
	 * @param cPais the new c pais
	 */
	public void setcPais(String cPais) {
		this.cPais = cPais;
	}

	/**
	 * Gets the i ss.
	 *
	 * @return the i ss
	 */
	public Float getiSS() {
		return iSS;
	}

	/**
	 * Sets the i ss.
	 *
	 * @param iSS the new i ss
	 */
	public void setiSS(Float iSS) {
		this.iSS = iSS;
	}

	/**
	 * Gets the contato.
	 *
	 * @return the contato
	 */
	public String getContato() {
		return contato;
	}

	/**
	 * Sets the contato.
	 *
	 * @param contato the new contato
	 */
	public void setContato(String contato) {
		this.contato = contato;
	}

	/**
	 * Gets the intalmfil.
	 *
	 * @return the intalmfil
	 */
	public String getIntalmfil() {
		return intalmfil;
	}

	/**
	 * Sets the intalmfil.
	 *
	 * @param intalmfil the new intalmfil
	 */
	public void setIntalmfil(String intalmfil) {
		this.intalmfil = intalmfil;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}



}