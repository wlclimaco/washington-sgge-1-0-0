package com.sensus.mlc.wui.cliente.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.sensus.common.util.SensusStringUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.cliente.bcf.IClienteBCF;
import com.sensus.mlc.cliente.model.Cliente;
import com.sensus.mlc.cliente.model.ClienteColumnEnum;
import com.sensus.mlc.cliente.model.reponse.InquiryClienteResponse;
import com.sensus.mlc.cliente.model.request.InquiryClienteRequest;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.SearchJsonResult;
import com.sensus.mlc.wui.base.util.ActionPaginationUtil;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.CustomizeAjaxAction;
import com.sensus.mlc.wui.base.util.ResultUtil;


/**
 * The Class SmartpointSearchAjaxAction is called when table is rendering.
 * 
 * @author Washington Costa
 */

@SuppressWarnings("serial")
public class ClienteSearchAjaxAction extends ActionBase
{
	// Constants
	
	/** The Constant ERROR_NOT_SEARCH_SMARTPOINT. */
	private static final String ERROR_NOT_SEARCH_SMARTPOINT = "Could not search SmartPoints";
	
	/** The Constant COMMA. */
	private static final String COMMA = ",";
	
	/** The Constant COLUMN. */
	private static final String COLUMN = "Columns";
	
	/** The Constant METER_ID. */
	private static final String METER_ID = "METER_ID";

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "FLEXNET_ID";
	
	/** The Constant START. */
	private static final String START = "start";
	
	/** The Constant END. */
	private static final String END = "end";
	
	/** The Constant STATUS. */
	private static final String STATUS = "status";
	
	/** The Constant CITY. */
	private static final String CITY = "city";

	/** The Constant STREET. */
	private static final String STREET = "street";
	
	/** The Constant ZIP. */
	private static final String ZIP = "zip";
	
	/** The smart point bcf. */
	private IClienteBCF clienteBCF;

//	/** The Constant ZERO. */
//	private static final int ZERO = 0;
//
//	/** The Constant ONE. */
//	private static final int ONE = 1;
//
//	/** The Constant TWO. */
//	private static final int TWO = 2;
//
//	/** The Constant THREE. */
//	private static final int THREE = 3;
//
//	/** The Constant FOUR. */
//	private static final int FOUR = 4;
//
//	/** The Constant FIVE. */
//	private static final int FIVE = 5;
//
//	/** The Constant SIX. */
//	private static final int SIX = 6;
//
//	/** The Constant SEVEN. */
//	private static final int SEVEN = 7;
//
//	/** The Constant EIGHT. */
//	private static final int EIGHT = 8;
//
//	/** The Constant NINE. */
//	private static final int NINE = 9;

	/** The Constant PIPE. */
	private static final String PIPE = "[|]";

	/** The Constant QUERY. */
	private static final String QUERY = "query";

//	/** The Constant REPEATS. */
//	private static final String REPEATS = "repeats";
//
//	/** The Constant ACTION TYPE. */
//	private static final String ACTION_TYPE = "action_type";
//
//	/** The Constant USERS. */
//	private static final String USERS = "users";
//
//	// Variables
//
//	/** The schedule bcf. */
//	private IEmpresaBCF empresaBCF;
//	
//	private IFilialBCF filialBCF;
	
	public Integer codemp;


//	/** The search json result. */
//	private SearchJsonResult searchResult;
	
	/** The smartpoint search result. */
	private SearchJsonResult clienteSearchResult;

	// Methods

	
	/**
	 * Search.
	 * 
	 * @return the string
	 */
	public String search()
	{
		setClienteSearchResult(new SearchJsonResult());
		try
		{
			Map<String, String[]> parameters = this.getParameters();

			String customizedColumns = parameters.get("aCustomizeColumn")[0];

			InquiryClienteRequest clienteRequest = new InquiryClienteRequest();

			InquiryClienteResponse response = getClienteBCF().fetchAllCliente(clienteRequest);
					


				List<String[]> records = new ArrayList<String[]>();

				// Transfer SmartPoints into Result object
				if (!ValidationUtil.isNullOrEmpty(response.getClientes()))
				{
					for (Cliente cliente : response.getClientes())
					{
						List<String> record = new ArrayList<String>();

						for (String column : customizedColumns.split(COMMA))
						{
							createResponse(record, column, cliente);
						}


						records.add(record.toArray(new String[record.size()]));
					}
				}

				getClienteSearchResult().setAaData(records.toArray(new String[records.size()][]));

				// set total amout of rows for pagination
				if (!ValidationUtil.isNull(response.getResultsSetInfo()))
				{
					getClienteSearchResult().setiTotalDisplayRecords(
							response.getResultsSetInfo().getTotalRowsAvailable());
				}

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_NOT_SEARCH_SMARTPOINT, e);
			}
			getClienteSearchResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Creates the response.
	 * 
	 * @param record the record
	 * @param column the column
	 * @param meter the meter
	 */
	private void createResponse(List<String> record, String column, Cliente cliente)
	{
		if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMP)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodemp()), ""));// Meter Id
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFILIAL)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfilial()), ""));// FlexNet ID
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodcli()), ""));// FlexNet ID
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMPCC)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodempcc()), ""));// Address
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFILIALCC)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfilialcc()), ""));// Address
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODCLASCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodclascli()), ""));// Device
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMPVD)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodempvd()), ""));// Device
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFILIALVD)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfilialvd()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODVEND)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodvend()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMPTC)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodemptc()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFILIALTC)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfilialtc()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODTIPOCOB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodtipocob()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMPPG)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodemppg()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFILIALPG)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfilialpg()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODPLANOPAG)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodplanopag()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMPTN)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodemptn()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFILIALTN)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfilialtn()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODTRAN)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodtran()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMPBO)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodempbo()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.RAZCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getRazcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODBANCO)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodbanco()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMPSR)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodempsr()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFILIALSR)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfilialsr()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODSETOR)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodsetor()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.NOMECLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getNomecli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMPTI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodempti()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFILIALTI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfilialti()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODTIPOCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodtipocli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.DATACLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getDatacli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.PESSOACLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getPessoacli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.ATIVOCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getAtivocli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CNPJCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCnpjcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.INSCCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getInsccli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CPFCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCpfcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.RGCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getRgcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.SSPCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getSspcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.ENDCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getEndcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.NUMCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getNumcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.COMPLCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getComplcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.BAIRCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getBaircli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CIDCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCidcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.UFCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getUfcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CEPCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCepcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.DDDCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getDddcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.FONECLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getFonecli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.RAMALCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getRamalcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.DDDFAXCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getDddfaxcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.FAXCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getFaxcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.EMAILCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getEmailcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CONTCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getContcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.ENDCOB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getEndcob()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.NUMCOB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getNumcob()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.COMPLCOB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getComplcob()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.BAIRCOB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getBaircob()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CIDCOB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCidcob()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.UFCOB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getUfcob()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CEPCOB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCepcob()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.DDDFONECOB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getDddfonecob()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.FONECOB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getFonecob()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.DDDFAXCOB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getDddfaxcob()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.FAXCOB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getFaxcob()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.ENDENT)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getEndent()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.NUMENT)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getNument()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.COMPLENT)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getComplent()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.BAIRENT)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getBairent()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CIDENT)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCident()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.UFENT)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getUfent()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CEPENT)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCepent()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.DDDFONEENT)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getDddfoneent()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.FONEENT)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getFoneent()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.DDDFAXENT)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getDddfaxent()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.FAXENT)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getFaxent()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.OBSCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getObscli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.AGENCIACLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getAgenciacli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMPPQ)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodemppq()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFILIALPQ)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfilialpq()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODPESQ)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodpesq()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.INCRACLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getIncracli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODTPCRED)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodtpcred()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFILIALTR)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfilialtr()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMPTR)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodemptr()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.DTINITR)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getDtinitr()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.DTVENCTOTR)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getDtvenctotr()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.NIRFCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getNirfcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFISCCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfisccli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMPFC)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodempfc()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFILIALFC)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfilialfc()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.NATCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getNatcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.UFNATCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getUfnatcli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.TEMPORESCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getTemporescli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODPAIS)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodpais()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.APELIDOCLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getApelidocli()), ""));
		}

		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODEMPEC)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodempec()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODFILIALEC)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodfilialec()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODTBEC)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodtbec()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODITTBEC)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodittbec()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.SITECLI)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getSitecli()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODCONTDEB)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodcontdeb()), ""));
		}
		else if (ClienteColumnEnum.valueOf(column) == ClienteColumnEnum.CODCONTCRED)
		{
			record.add(ValidationUtil.isNullOrEmpty(
					SensusStringUtil.createToString(cliente.getCodcontcred()), ""));
		}
	}
	


	public SearchJsonResult getClienteSearchResult() {
		return clienteSearchResult;
	}

	public void setClienteSearchResult(SearchJsonResult clienteSearchResult) {
		this.clienteSearchResult = clienteSearchResult;
	}

	public IClienteBCF getClienteBCF() {
		return clienteBCF;
	}

	public void setClienteBCF(IClienteBCF clienteBCF) {
		this.clienteBCF = clienteBCF;
	}
}
