package com.sensus.mlc.wui.empresa.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sensus.common.model.Response;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.empresa.bcf.IEmpresaBCF;
import com.sensus.mlc.empresa.model.Empresa;
import com.sensus.mlc.empresa.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.empresa.model.response.InquiryEmpresaResponse;
import com.sensus.mlc.filial.bcf.IFilialBCF;
import com.sensus.mlc.filial.model.Filial;
import com.sensus.mlc.filial.model.request.InquiryFilialRequest;
import com.sensus.mlc.filial.model.response.InquiryFilialResponse;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.SearchJsonResult;
import com.sensus.mlc.wui.base.util.Constants;


/**
 * The Class SmartpointSearchAjaxAction is called when table is rendering.
 * 
 * @author Raphael Constantino
 */

@SuppressWarnings("serial")
public class EmpresaSearchAjaxAction extends ActionBase
{
	// Constants

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

	/** The Constant SIX. */
	private static final int SIX = 6;

	/** The Constant SEVEN. */
	private static final int SEVEN = 7;

	/** The Constant EIGHT. */
	private static final int EIGHT = 8;

	/** The Constant NINE. */
	private static final int NINE = 9;

	/** The Constant PIPE. */
	private static final String PIPE = "[|]";

	/** The Constant QUERY. */
	private static final String QUERY = "query";

	/** The Constant REPEATS. */
	private static final String REPEATS = "repeats";

	/** The Constant ACTION TYPE. */
	private static final String ACTION_TYPE = "action_type";

	/** The Constant USERS. */
	private static final String USERS = "users";

	// Variables

	/** The schedule bcf. */
	private IEmpresaBCF empresaBCF;
	
	private IFilialBCF filialBCF;
	
	public Integer codemp;
	
	/** The inquiry tag request. */
	private InquiryEmpresaRequest inquiryEmpresaRequest;

	/** The response. */
	private Response response;



	/** The search json result. */
	private SearchJsonResult searchResult;

	// Methods



//	public String searchGraphic()
//	{
//		setSearchResult(new SearchJsonResult());
//
//		try
//		{
//			InquiryScheduleRequest inquiryScheduleRequest = new InquiryScheduleRequest();
//
//			// ADD user context to request
//			BERequestContextUtil.enableContext(inquiryScheduleRequest);
//
//			/** Response **/
//			InquiryScheduleResponse inquiryScheduleResponse =
//					getScheduleBCF().fetchAllSchedule(inquiryScheduleRequest);
//
//			if (ResultUtil.setMessages(getSearchResult(), inquiryScheduleResponse))
//			{
//				List<String[]> records = new ArrayList<String[]>();
//
//				if (!ValidationUtil.isNull(inquiryScheduleResponse.getSchedules()))
//				{
//					for (Schedule schedule : inquiryScheduleResponse.getSchedules())
//					{
//						records.add(getGraphicItems(schedule));
//					}
//
//					getSearchResult().setAaData(records.toArray(new String[records.size()][]));
//
//					// set total amount of rows for pagination
//					if (!ValidationUtil.isNull(inquiryScheduleResponse.getResultsSetInfo()))
//					{
//						getSearchResult().setiTotalDisplayRecords(
//								inquiryScheduleResponse.getResultsSetInfo().getTotalRowsAvailable());
//					}
//				}
//			}
//		}
//		catch (Exception e)
//		{
//			getSearchResult().setResult(Constants.JSON_FAIL);
//		}
//		return SUCCESS;
//	}

	/**
	 * Creates the inquiry schedule request.
	 * 
	 * @param parameters the parameters
	 * @param baseSearch the base search
	 * @return the inquiry schedule request
	 */
//	private InquiryEmpresaRequest createInquiryScheduleRequest(Map<String, String[]> parameters,
//			BaseSearch baseSearch)
//	{
//		InquiryEmpresaRequest inquiryEmpresaRequest = new InquiryEmpresaRequest();
//
//		// ADD user context to request
//		BERequestContextUtil.enableContext(inquiryEmpresaRequest);
//
//		inquiryEmpresaRequest.setBaseSearch(baseSearch);
//		inquiryEmpresaRequest.setPageSize(ActionPaginationUtil.getPageDisplaySize(parameters));
//		inquiryEmpresaRequest.setStartRow(ActionPaginationUtil.getCurrentDisplayStartIndex(parameters));
//		inquiryEmpresaRequest.setEndRow(ActionPaginationUtil.getTotalRowsRecord(parameters));
//		inquiryEmpresaRequest.getSortExpressions().add(
//				ActionPaginationUtil.getSortExpressionScheduledEvent(parameters));
//
//		return inquiryEmpresaRequest;
//	}
//
//	private InquiryFilialRequest createInquiryFilial(Map<String, String[]> parameters,BaseSearch baseSearch)
//	{
//		InquiryFilialRequest inquiryFillialaRequest = new InquiryFilialRequest();
//
//		// ADD user context to request
//		BERequestContextUtil.enableContext(inquiryFillialaRequest);
//
//		inquiryFillialaRequest.setBaseSearch(baseSearch);
//		inquiryFillialaRequest.setPageSize(ActionPaginationUtil.getPageDisplaySize(parameters));
//		inquiryFillialaRequest.setStartRow(ActionPaginationUtil.getCurrentDisplayStartIndex(parameters));
//		inquiryFillialaRequest.setEndRow(ActionPaginationUtil.getTotalRowsRecord(parameters));
//		inquiryFillialaRequest.getSortExpressions().add(
//				ActionPaginationUtil.getSortExpressionScheduledEvent(parameters));
//
//		return inquiryFillialaRequest;
//	}
//	
//	/**
//	 * Create filter.
//	 * 
//	 * @param parameters the parameters
//	 * @return the search scheduled event
//	 */
//	private BaseSearch createFilter(Map<String, String[]> parameters)
//	{
//		BaseSearch baseSearch = new BaseSearch();
//
//		String text = null;
//
//		/** Search Events */
//
//		if (!ValidationUtil.isNull(parameters.get(QUERY)))
//		{
//			text = parameters.get(QUERY)[0].split(PIPE)[1];
//		}
//
//		baseSearch.setSearchText(text);
//
//		/** Action Types */
//		List<ActionTypeEnum> actionTypes = new ArrayList<ActionTypeEnum>();
//
//		if (!ValidationUtil.isNull(parameters.get(ACTION_TYPE)))
//		{
//			for (String actionType : parameters.get(ACTION_TYPE)[0].split(PIPE))
//			{
//				actionTypes.add(ActionTypeEnum.enumForValue(actionType));
//			}
//		}
//
//		baseSearch.setActionTypes(actionTypes);
//
//		/** Repeats */
//		List<FrequencyEnum> repeats = new ArrayList<FrequencyEnum>();
//
//		if (!ValidationUtil.isNull(parameters.get(REPEATS)))
//		{
//			for (String repeat : parameters.get(REPEATS)[0].split(PIPE))
//			{
//				repeats.add(FrequencyEnum.enumForValue(repeat));
//			}
//		}
//
//		baseSearch.setRepeats(repeats);
//
//		/** Users */
//		List<UserContext> users = new ArrayList<UserContext>();
//
//		if (!ValidationUtil.isNull(parameters.get(USERS)))
//		{
//			for (String userName : parameters.get(USERS)[0].split(PIPE))
//			{
//				UserContext user = new UserContext();
//				user.setUserId(userName);
//
//				users.add(user);
//			}
//		}
//
//		baseSearch.setUsers(users);
//
//		return baseSearch;
//	}

	/**
	 * Gets the table columns.
	 * 
	 * @param schedule the scheduled event
	 * @return the table columns
	 */
	private String[] getTableColumns(Empresa empresa)
	{
		String[] records = new String[NINE];

		records[ZERO] = String.valueOf(empresa.getCodemp());
		records[ONE] = empresa.getNomeemp();
		records[TWO] = empresa.getRazemp();
		records[THREE] = empresa.getCnpjemp();
		records[FOUR] = empresa.getInscemp();
		records[FIVE] = empresa.getCodmunic();
		records[SIX] = empresa.getCepemp();
		records[SEVEN] = empresa.getNomecontemp();

		return records;
	}
	
	private String[] getTableColumnsFilial(Filial filial)
	{
		String[] records = new String[NINE];
		
		records[ZERO] = String.valueOf(filial.getCodemp());
		records[ONE] = String.valueOf(filial.getCodfilial());
		records[TWO] = filial.getNomefilial();
		records[THREE] = filial.getRazfilial();
		records[FOUR] = filial.getCnpjfilial();
		records[FIVE] = filial.getInscfilial();
		records[SIX] = filial.getCodmunic();
		records[SEVEN] = "";

		return records;
	}

	/**
	 * Fetch scheduled event.
	 * 
	 * @return the string
	 * 
	 * 
	 */

	/**
	 * Fetch all empresa.
	 * 
	 * @param inquiryempresaRequest the inquiryempresa request
	 * @return the inquiry empresa response
	 */
	public String fetchAllEmpresa()
	{
		try
		{
			InquiryEmpresaRequest inquiryempresaRequest = new InquiryEmpresaRequest();
			
			UserContext contest= new UserContext();
			contest.setUserId("rod");
			contest.setTenant(new Tenant(1));
		//	getInquiryEmpresaRequest().setUserContext(getUserContext());
		//	BERequestContextUtil.enableContext(inquiryempresaRequest);
			inquiryempresaRequest.setUserContext(contest);
			InquiryEmpresaResponse inquiryEmpresaResponse = getEmpresaBCF().fetchAllEmpresa(inquiryempresaRequest);
			setResponse(inquiryEmpresaResponse);
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
	
	public String searchFilial()
	{
		setSearchResult(new SearchJsonResult());

		try
		{
			Map<String, String[]> parameters = getParameters();

			/** Call method to prepare the filter */
//			BaseSearch searchScheduledEvent = createFilter(parameters);

			/** Request **/
			InquiryFilialRequest inquiryFilialRequest =
					new InquiryFilialRequest();

			// ADD user context to request
//			BERequestContextUtil.enableContext(inquiryFilialRequest);

			Filial filiald = new Filial();
			filiald.setCodemp(getCodemp());
			
			List<Filial> filials =  new ArrayList<Filial>(); ;
			 
			filials.add(filiald);
			
			inquiryFilialRequest.setFilial(filials);
	//		setFilial().set(0).setCodemp(getCodemp());
			
			/** Response **/
			InquiryFilialResponse inquiryFIlialResponse =
					getFilialBCF().fetchAllFilialEmpresa(inquiryFilialRequest);


				List<String[]> records = new ArrayList<String[]>();

				if (!ValidationUtil.isNull(inquiryFIlialResponse.getFilial()))
				{
					for (Filial filial : inquiryFIlialResponse.getFilial())
					{
						records.add(getTableColumnsFilial(filial));
					}

					getSearchResult().setAaData(records.toArray(new String[records.size()][]));

					// set total amount of rows for pagination
					if (!ValidationUtil.isNull(inquiryFIlialResponse.getResultsSetInfo()))
					{
						getSearchResult().setiTotalDisplayRecords(
								inquiryFIlialResponse.getResultsSetInfo().getTotalRowsAvailable());
					}
				}

		}
		catch (Exception e)
		{
			getSearchResult().setResult(Constants.JSON_FAIL);
		}
		return SUCCESS;
	}
	
	public String searchEmpresa()
	{
		setSearchResult(new SearchJsonResult());

		try
		{
			Map<String, String[]> parameters = getParameters();

			/** Call method to prepare the filter */
//			BaseSearch searchScheduledEvent = createFilter(parameters);

			/** Request **/
			InquiryEmpresaRequest inquiryempresaRequest = new InquiryEmpresaRequest();
	//				createInquiryScheduleRequest(parameters, searchScheduledEvent);

			// ADD user context to request
//			BERequestContextUtil.enableContext(inquiryempresaRequest);

			/** Response **/
			InquiryEmpresaResponse inquiryEmpresaResponse =
					getEmpresaBCF().fetchAllEmpresa(inquiryempresaRequest);


				List<String[]> records = new ArrayList<String[]>();

				if (!ValidationUtil.isNull(inquiryEmpresaResponse.getEmpresa()))
				{
					for (Empresa empresa : inquiryEmpresaResponse.getEmpresa())
					{
						records.add(getTableColumns(empresa));
					}

					getSearchResult().setAaData(records.toArray(new String[records.size()][]));

					// set total amount of rows for pagination
					if (!ValidationUtil.isNull(inquiryEmpresaResponse.getResultsSetInfo()))
					{
						getSearchResult().setiTotalDisplayRecords(
								inquiryEmpresaResponse.getResultsSetInfo().getTotalRowsAvailable());
					}
				}
		}
		catch (Exception e)
		{
			getSearchResult().setResult(Constants.JSON_FAIL);
		}
		return SUCCESS;
	}

	// Getters and Setters

	public SearchJsonResult getSearchResult()
	{
		return searchResult;
	}

	public void setSearchResult(SearchJsonResult searchResult)
	{
		this.searchResult = searchResult;
	}

	public IEmpresaBCF getEmpresaBCF()
	{
		return empresaBCF;
	}

	public void setEmpresaBCF(IEmpresaBCF empresaBCF)
	{
		this.empresaBCF = empresaBCF;
	}
	public Integer getCodemp() {
		return codemp;
	}

	public void setCodemp(Integer codemp) {
		this.codemp = codemp;
	}

	public IFilialBCF getFilialBCF() {
		return filialBCF;
	}

	public void setFilialBCF(IFilialBCF filialBCF) {
		this.filialBCF = filialBCF;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
	public InquiryEmpresaRequest getInquiryEmpresaRequest() {
		return inquiryEmpresaRequest;
	}

	public void setInquiryEmpresaRequest(InquiryEmpresaRequest inquiryEmpresaRequest) {
		this.inquiryEmpresaRequest = inquiryEmpresaRequest;
	}

}
