package com.sensus.mlc.wui.cliente.unittest.action;


import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.Request;
import com.sensus.common.model.ResultsSetInfo;
import com.sensus.mlc.cliente.bcf.IClienteBCF;
import com.sensus.mlc.cliente.model.Cliente;
import com.sensus.mlc.cliente.model.ClienteColumnEnum;
import com.sensus.mlc.cliente.model.ClienteFilterEnum;
import com.sensus.mlc.cliente.model.reponse.ClienteResponse;
import com.sensus.mlc.cliente.model.reponse.InquiryClienteResponse;
import com.sensus.mlc.cliente.model.request.ClienteRequest;
import com.sensus.mlc.cliente.model.request.InquiryClienteRequest;
import com.sensus.mlc.empresa.model.response.EmpresaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;


/**
 * The Class ScheduleBCFMockImpl.
 * 
 * @ahthor Washington Costa
 */
public class ClienteBCFMockImpl extends BaseMockImpl implements IClienteBCF
{

	/** The Constant FILE_NAME. */
	public static final String FILE_NAME = "C:\\QATEclipseWorkSpace\\epm-wui\\WebContent\\File.csv";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant ZERO. */
	private static final int TWENTY = 20;

	/** The page size. */
	private Integer pageSize = 100;
	
	/** The Constant ONE_HUNDRED. */
	public static final Integer ONE_HUNDRED = 100;
	
	/** The Constant KABOOM. */
	public static final String KABOOM = "Kaboom";

	/** The Constant SMARTPOINT_COUNT. */
	public static final Integer CLIENTE_COUNT = 100;
	/**
	 * Gets the page size.
	 * 
	 * @return the page size
	 */
	public Integer getPageSize()
	{
		return pageSize;
	}

	/**
	 * Sets the page size.
	 * 
	 * @param pageSize the new page size
	 */
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

//	@Override
//	public EmpresaResponse insertEmpresa(EmpresaRequest empresaRequest) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public EmpresaResponse updateEmpresa(EmpresaRequest empresaRequest) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public EmpresaResponse deleteEmpresa(EmpresaRequest empresaRequest) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public InquiryEmpresaResponse fetchAllEmpresa(
//			InquiryEmpresaRequest inquiryempresaRequest) {
//		
//		InquiryEmpresaResponse response = new InquiryEmpresaResponse();
//		if (MODE_SUCCESS.equals(getMode()))
//		{
//			response.setEmpresa(new ArrayList<Empresa>());
//
//			pageSize = inquiryempresaRequest.getPageSize();
//
//			if (!ValidationUtil.isNull(inquiryempresaRequest.getBaseSearch())
//					&& !ValidationUtil.isNull(inquiryempresaRequest.getBaseSearch().getSearchText()))
//			{
//				pageSize -= 5;
//			}
//
//
//
//				Empresa empresa = new Empresa();
//				empresa.setCodemp(1);
//				empresa.setBairemp("Mangueiras");
//				empresa.setNomeemp("Nome empresa");
//				empresa.setRazemp("Razao da empresa");
//				empresa.setCepemp("38082-243");
//				empresa.setCidemp("Uberaba");
//				empresa.setCnpjemp("1234567890/123");
//				empresa.setCodeanemp("codeanemp");
//				empresa.setCodmunic("123456");
//				empresa.setCodpais(55);
//				empresa.setNomecontemp("Washington");
//				empresa.setCodpaisemp("55");
//				empresa.setComplemp("complemp");
//				empresa.setCreateDate(new Date());
//				empresa.setCreateUser("createUser");
//				empresa.setDddemp("dddemp");
//				empresa.setInscemp("inscemp");				
//				response.getEmpresa().add(empresa);
//				
//				empresa = new Empresa();
//				empresa.setCodemp(2);
//				empresa.setBairemp("Mangueiras");
//				empresa.setNomeemp("Nome empresa");
//				empresa.setRazemp("Razao da empresa");
//				empresa.setCepemp("38082-243");
//				empresa.setCidemp("Uberaba");
//				empresa.setCnpjemp("1234567890/123");
//				empresa.setCodeanemp("codeanemp");
//				empresa.setCodmunic("123456");
//				empresa.setCodpais(55);
//				empresa.setNomecontemp("Washington");
//				empresa.setCodpaisemp("55");
//				empresa.setComplemp("complemp");
//				empresa.setCreateDate(new Date());
//				empresa.setCreateUser("createUser");
//				empresa.setDddemp("dddemp");
//				empresa.setInscemp("inscemp");				
//				response.getEmpresa().add(empresa);
//				
//				
//				
//				ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
//				resultsSetInfo.setTotalRowsAvailable(ONE_HUNDRED);
//				response.setResultsSetInfo(resultsSetInfo);
//			
//			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
//			return response;
//		}
//		else if (MODE_EMPTY.equals(getMode()))
//		{
//			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
//			return response;
//		}
//		else if (MODE_FAILURE.equals(getMode()))
//		{
//			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
//			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
//			response.setOperationSuccess(false);
//			return response;
//		}
//		throw new RuntimeException(KABOOM);
//	}
//
//	@Override
//	public EmpresaResponse fetchEmpresaById(EmpresaRequest empresaRequest) {
//		EmpresaResponse empresaResponse = new EmpresaResponse();
//		
//		List<Empresa> empresas = new ArrayList<Empresa>();
//
//		if ((empresaRequest.getEmpresa() == null) && (empresaRequest.getEmpresa().getCepemp() == null))
//		{
//			empresaResponse.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
//			empresaResponse.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
//			empresaResponse.setOperationSuccess(false);
//			return empresaResponse;
//		}
//		if (MODE_SUCCESS.equals(getMode()))
//		{
//
//			
//				Empresa empresa = new Empresa();
//				empresa.setCodemp(1);
//				empresa.setBairemp("Mangueiras");
//				empresa.setNomeemp("Nome empresa");
//				empresa.setRazemp("Razao da empresa");
//				empresa.setCepemp("38082-243");
//				empresa.setCidemp("Uberaba");
//				empresa.setCnpjemp("1234567890/123");
//				empresa.setCodeanemp("codeanemp");
//				empresa.setCodmunic("123456");
//				empresa.setCodpais(55);
//				empresa.setNomecontemp("Washington");
//				empresa.setCodpaisemp("55");
//				empresa.setComplemp("complemp");
//				empresa.setCreateDate(new Date());
//				empresa.setCreateUser("createUser");
//				empresa.setDddemp("dddemp");
//				empresa.setInscemp("inscemp");				
//				empresas.add(empresa);
//				empresaResponse.setEmpresa(empresas);
//
//				empresaResponse.setOperationSuccess(true);
//				empresaResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
//
//			return empresaResponse;
//		}
//		else if (MODE_EMPTY.equals(getMode()))
//		{
//			return empresaResponse;
//		}
//		throw new RuntimeException(KABOOM);
//	}
//
//	@Override
//	public ProcessResponse insertCSVProcess(InquiryEmpresaRequest empresaRequest) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public EmpresaResponse fetchAllEmpresaTypes(Request request) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	@Override
//	public ProcessResponse generateFileCSV(
//			InquiryEmpresaRequest inquiryEmpresaRequest) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public EmpresaResponse fetchAllEmpresaFilial(
//			InquiryEmpresaRequest inquiryEmpresaRequest) {
//		EmpresaResponse empresaResponse = new EmpresaResponse();
//		
//		List<Empresa> empresas = new ArrayList<Empresa>();
//		
//		List<Filial> filiais = new ArrayList<Filial>();
//
//		if ((inquiryEmpresaRequest.getEmpresas().get(0).getCodemp() == null) && (inquiryEmpresaRequest.getEmpresas().get(0) == null))
//		{
//			empresaResponse.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
//			empresaResponse.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
//			empresaResponse.setOperationSuccess(false);
//			return empresaResponse;
//		}
//		if (MODE_SUCCESS.equals(getMode()))
//		{
//
//			
//				Empresa empresa = new Empresa();
//				empresa.setCodemp(1);
//				empresa.setBairemp("Mangueiras");
//				empresa.setNomeemp("Nome empresa");
//				empresa.setRazemp("Razao da empresa");
//				empresa.setCepemp("38082-243");
//				empresa.setCidemp("Uberaba");
//				empresa.setCnpjemp("1234567890/123");
//				empresa.setCodeanemp("codeanemp");
//				empresa.setCodmunic("123456");
//				empresa.setCodpais(55);
//				empresa.setNomecontemp("Washington");
//				empresa.setCodpaisemp("55");
//				empresa.setComplemp("complemp");
//				empresa.setCreateDate(new Date());
//				empresa.setCreateUser("createUser");
//				empresa.setDddemp("dddemp");
//				empresa.setInscemp("inscemp");	
//				Filial filial = new Filial();
//				
//				filial.setCodemp(1);
//				filial.setNomefilial("NOME FANTASIA 001");
//				filial.setRazfilial("RAZAO TESTE 001");
//				filial.setCnpjfilial("CNPJ");
//				filial.setInscfilial("INSC ESTADUAL");
//				filial.setCodmunic("INSCRICAO MUNICIPAL");
//				filial.setCepfilial("CEP");
//				filial.setEndfilial("ENDERECO");
//				filial.setNumfilial(34);
//				filial.setCnaefilial("CNAE");
//				filial.setComplfilial("COMPLEMENTO");
//				filial.setBairfilial("BAIRRO");
//				filial.setDddfilial("DDD");
//				filial.setFonefilial("FONE");
//				filial.setFaxfilial("FAX");
//				filial.setEmailfilial("EMAIL");
//				filial.setWwwfilial("SITE");
//				filial.setCoddistfilial(2);
//				filial.setSedeMatriz(new Boolean(true));
//				filiais.add(filial);
//				empresa.setListFilial(filiais);		
//				empresas.add(empresa);
//				empresaResponse.setEmpresa(empresas);
//
//				empresaResponse.setOperationSuccess(true);
//				empresaResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
//
//			return empresaResponse;
//		}
//		else if (MODE_EMPTY.equals(getMode()))
//		{
//			return empresaResponse;
//		}
//		throw new RuntimeException(KABOOM);
//	}

	@Override
	public EmpresaResponse insertCliente(ClienteRequest clienteRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaResponse updateCliente(ClienteRequest clienteRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaResponse deleteCliente(ClienteRequest clienteRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static Column addColumns(ClienteColumnEnum meterColumnEnum)
	{
		Column column = new Column();
		ClienteColumnEnum columnProperty = meterColumnEnum;
	//	column.setColumnEnum(columnProperty);
		return column;
	}

	@Override
	public InquiryClienteResponse fetchAllCliente(
			InquiryClienteRequest inquiryClienteRequest) {
		InquiryClienteResponse response = new InquiryClienteResponse();

		Integer pageSize = CLIENTE_COUNT;
		Integer totalRows = CLIENTE_COUNT;

		if (inquiryClienteRequest.getPageSize() > 0)
		{
			pageSize = inquiryClienteRequest.getPageSize();
		}

		Integer startRow = inquiryClienteRequest.getStartRow() + 1;
		Cliente cliente = new Cliente();
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setClientes(new ArrayList<Cliente>());
			for (int i = startRow; i < (startRow + pageSize); i++)
			{
				cliente = new Cliente();

				cliente.setCodemp(3+i);
				cliente.setCodfilial(1+i);
				
				cliente.setCodcli(i);
				cliente.setCodempcc(123+i);
				cliente.setCodfilialcc(123+i);
				cliente.setCodclascli(123+i);
				cliente.setCodempvd(123+i);
				cliente.setCodfilialvd(123+i); 
				cliente.setCodvend(123+i);
				cliente.setCodemptc(123+i); 
				cliente.setCodfilialtc(123+i);
				cliente.setCodtipocob(123+i);
				cliente.setCodemppg(123+i);
				cliente.setCodfilialpg(123+i);
				cliente.setCodplanopag(123+i);
				cliente.setCodemptn(123+i);
				cliente.setCodfilialtn(123+i);
				cliente.setCodtran(123+i);
				cliente.setCodempbo(123+i);
				cliente.setCodfilialbo(123+i);
				cliente.setRazcli("1234567");
				cliente.setCodbanco("123");
				cliente.setCodempsr(123+i);
				cliente.setCodfilialsr(123+i);
				cliente.setCodcli(123+i);
				cliente.setNomecli("ddd");				
				cliente.setCodempti(123+i);
				cliente.setCodfilialti(123+i);
				cliente.setCodtipocli(123+i);
				cliente.setPessoacli("F");
				cliente.setAtivocli("A");
				cliente.setCnpjcli("123456");
				cliente.setInsccli("654321");
				cliente.setCpfcli("123455");
				cliente.setRgcli("543213");
				cliente.setSspcli("123456");
				cliente.setEndcli("3456");
				cliente.setNumcli(16);
				cliente.setComplcli("tttt");
				cliente.setBaircli("bairro");
				cliente.setCidcli("cidade");
				cliente.setUfcli("er");
				cliente.setCepcli("1234");
				cliente.setDddcli("43");
				cliente.setFonecli("333333");
				cliente.setRamalcli("33333");
				cliente.setDddfaxcli("33333");
				cliente.setFaxcli("333333");
				cliente.setEmailcli("333333");
				cliente.setContcli("333333");
				cliente.setEndcob("");
				cliente.setNumcob(23);
				cliente.setComplcob("dfds");
				cliente.setBaircob("dsfdsf");
				cliente.setCidcob("fsdfd");
				cliente.setUfcob("hgjhgj");
				cliente.setCepcob("hjhgj");
				cliente.setDddfonecob("hjhj");
				cliente.setFonecob("ytrytry");
				cliente.setDddfaxcob("hfghghf");
				cliente.setFaxcob("fhfghgfh");
				cliente.setEndent("dfdsfds");
				cliente.setNument(12);
				cliente.setComplent("fdsdf");
				cliente.setBairent("dfdsf");
				cliente.setCident("dfsdf");
				cliente.setUfent("dsfsdf");
				cliente.setCepent("dfdfdsfdsfsd");
				cliente.setDddfoneent("dfdsfsdf");
				cliente.setFoneent("dsfsdfsd");
				cliente.setDddfaxent("dfdsfdsf");
				cliente.setFaxent("dfdsf");
				cliente.setObscli("sdfsdfds");
				cliente.setAgenciacli("dfdsfdsfds");
				cliente.setCodemppq(5);
				cliente.setCodfilialpq(6);
				cliente.setCodpesq(7);
				cliente.setIncracli("fgdfg");
				cliente.setCodtpcred(56546);
				cliente.setCodfilialtr(56456);
				cliente.setCodemptr(56546);
				cliente.setNirfcli("sdsadas");
				cliente.setCodfisccli(43);
				cliente.setCodempfc(44);
				cliente.setCodfilialfc(555);
				cliente.setNatcli("fdsfsdf");
				cliente.setUfnatcli("dfdsfsdf");
				cliente.setTemporescli("dsfsdfsdf");
				cliente.setCodpais(1);
				cliente.setApelidocli("fdsfdsf");
				cliente.setCodempec(34);
				cliente.setCodfilialec(44);
				cliente.setCodtbec(44);
				cliente.setCodittbec(444);
				cliente.setSitecli("eeeeeee");
				cliente.setCodcontdeb(444);
				cliente.setCodcontcred(2222); 
				
				response.getClientes().add(cliente);

			}

			// Add Additional Column
			List<Column> additionalColumns = new ArrayList<Column>();
			additionalColumns.add(addColumns(ClienteColumnEnum.CIDCLI));
			response.setAdditionalColumns(additionalColumns);

			// Add Columns
			List<Column> listColumns = new ArrayList<Column>();

			listColumns.add(addColumns(ClienteColumnEnum.AGENCIACLI));
			listColumns.add(addColumns(ClienteColumnEnum.APELIDOCLI));
			listColumns.add(addColumns(ClienteColumnEnum.ATIVOCLI));
			listColumns.add(addColumns(ClienteColumnEnum.BAIRCLI));
			listColumns.add(addColumns(ClienteColumnEnum.BAIRCOB));
			listColumns.add(addColumns(ClienteColumnEnum.CEPCLI));
			listColumns.add(addColumns(ClienteColumnEnum.CIDCOB));
			listColumns.add(addColumns(ClienteColumnEnum.CODEMP));

			response.setListColumn(listColumns);

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			ResultsSetInfo resultSetInfo = new ResultsSetInfo();
			resultSetInfo.setTotalRowsAvailable(totalRows);

			response.setResultsSetInfo(resultSetInfo);

			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public ClienteResponse fetchClienteById(ClienteRequest clienteRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse generateFileCSV(
			InquiryClienteRequest inquiryClienteRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse insertCSVProcess(InquiryClienteRequest clienteRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteResponse fetchAllClienteTypes(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Sets the column.
	 * 
	 * @param propertyEnum the property enum
	 * @param text the text
	 * @return the column
	 */
	private Column setColumn(ClienteColumnEnum propertyEnum, String text)
	{
		Column column = new Column();
//		column.setColumnEnum(propertyEnum);
		column.setFieldName(text);
		column.setOrdered(true);
		return column;
	}
	
	/* (non-Javadoc)
	 * @see com.sensus.epm.cliente.bcf.IClienteBCF#fetchAllColumnFilters(com.sensus.epm.device.model.request.ColumnFilterRequest)
	 */
	@Override
	public ColumnFilterResponse fetchAllColumnFilters(
			ColumnFilterRequest columnFilterRequest) {
		ColumnFilterResponse columnFilterResponse = new ColumnFilterResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			List<Column> columns = new ArrayList<Column>();

//			columns.add(setColumn(ClienteColumnEnum.CODEMP, "Cod Empresa"));
//			columns.add(setColumn(ClienteColumnEnum.CODFILIAL, "Meter ID"));
//			columns.add(setColumn(ClienteColumnEnum.CODCLI, "Device Type"));
//			columns.add(setColumn(ClienteColumnEnum.CODCLASCLI, "Date Added"));
//			columns.add(setColumn(ClienteColumnEnum.CNPJCLI, "City"));
//			columns.add(setColumn(ClienteColumnEnum.INSCCLI, "Map It"));
//			columns.add(setColumn(ClienteColumnEnum.INCRACLI, ""));
//			columns.add(setColumn(ClienteColumnEnum.CODEMPFC, "State"));
//			columns.add(setColumn(ClienteColumnEnum.CODCONTCRED, "States"));
//			
			columns.add(setColumn(ClienteColumnEnum.CODEMP,  "commons.pages.codemp"));
			columns.add(setColumn(ClienteColumnEnum.CODFILIAL,  "commons.pages.codfilial"));
			columns.add(setColumn(ClienteColumnEnum.CODCLI,  "commons.pages.codcli"));
			columns.add(setColumn(ClienteColumnEnum.CODEMPCC,  "commons.pages.codempcc"));
			columns.add(setColumn(ClienteColumnEnum.CNPJCLI,  "commons.pages.cnpjcli"));
			columns.add(setColumn(ClienteColumnEnum.RAZCLI,  "commons.pages.razcli"));
			columns.add(setColumn(ClienteColumnEnum.ATIVOCLI,  "commons.pages.ativocli"));
			columns.add(setColumn(ClienteColumnEnum.PESSOACLI,  "commons.pages.pessoacli"));
			
			
			
			columnFilterResponse.setListColumn(columns);
			
			List<Column> allColumns = new ArrayList<Column>();
			
			allColumns.add(setColumn(ClienteColumnEnum.CODFILIALVD,  "commons.pages.codfilialvd"));
			allColumns.add(setColumn(ClienteColumnEnum.CODVEND,  "commons.pages.codvend"));
			allColumns.add(setColumn(ClienteColumnEnum.CODEMPTC,  "commons.pages.codemptc"));
			allColumns.add(setColumn(ClienteColumnEnum.CODFILIALTC,  "commons.pages.codfilialtc"));
			allColumns.add(setColumn(ClienteColumnEnum.CODTIPOCOB,  "commons.pages.codtipocob"));
			allColumns.add(setColumn(ClienteColumnEnum.CODEMPPG,  "commons.pages.codemppg"));
			allColumns.add(setColumn(ClienteColumnEnum.CODFILIALPG,  "commons.pages.codfilialpg"));
			allColumns.add(setColumn(ClienteColumnEnum.CODPLANOPAG,  "commons.pages.codplanopag"));
			allColumns.add(setColumn(ClienteColumnEnum.CODEMPTN,  "commons.pages.codemptn"));
			allColumns.add(setColumn(ClienteColumnEnum.CODFILIALTN,  "commons.pages.codfilialtn"));
			allColumns.add(setColumn(ClienteColumnEnum.CODTRAN,  "commons.pages.codtran"));
			allColumns.add(setColumn(ClienteColumnEnum.CODEMPBO,  "commons.pages.codempbo"));
			allColumns.add(setColumn(ClienteColumnEnum.RAZCLI,  "commons.pages.razcli"));
			allColumns.add(setColumn(ClienteColumnEnum.CODBANCO,  "commons.pages.codbanco"));
			allColumns.add(setColumn(ClienteColumnEnum.CODEMPSR,  "commons.pages.codempsr"));
			allColumns.add(setColumn(ClienteColumnEnum.CODFILIALSR,  "commons.pages.codfilialsr"));
			allColumns.add(setColumn(ClienteColumnEnum.CODSETOR,  "commons.pages.codsetor"));
			allColumns.add(setColumn(ClienteColumnEnum.NOMECLI,  "commons.pages.nomecli"));
			allColumns.add(setColumn(ClienteColumnEnum.CODEMPTI,  "commons.pages.codempti"));
			allColumns.add(setColumn(ClienteColumnEnum.CODFILIALTI,  "commons.pages.codfilialti"));
			allColumns.add(setColumn(ClienteColumnEnum.CODTIPOCLI,  "commons.pages.codtipocli"));
			allColumns.add(setColumn(ClienteColumnEnum.DATACLI,  "commons.pages.datacli"));
			allColumns.add(setColumn(ClienteColumnEnum.CODEMPVD,  "commons.pages.codempvd"));// Device		
			allColumns.add(setColumn(ClienteColumnEnum.CODFILIALCC,  "commons.pages.codfilialcc"));// "commons.pages.address"
			allColumns.add(setColumn(ClienteColumnEnum.INSCCLI,  "commons.pages.insccli"));
			allColumns.add(setColumn(ClienteColumnEnum.CPFCLI,  "commons.pages.cpfcli"));
			allColumns.add(setColumn(ClienteColumnEnum.RGCLI,  "commons.pages.rgcli"));
			allColumns.add(setColumn(ClienteColumnEnum.SSPCLI,  "commons.pages.sspcli"));
			allColumns.add(setColumn(ClienteColumnEnum.ENDCLI,  "commons.pages.endcli"));
			allColumns.add(setColumn(ClienteColumnEnum.NUMCLI,  "commons.pages.numcli"));
			allColumns.add(setColumn(ClienteColumnEnum.COMPLCLI,  "commons.pages.complcli"));
			allColumns.add(setColumn(ClienteColumnEnum.BAIRCLI,  "commons.pages.baircli"));
			allColumns.add(setColumn(ClienteColumnEnum.CIDCLI,  "commons.pages.cidcli"));
			allColumns.add(setColumn(ClienteColumnEnum.UFCLI,  "commons.pages.ufcli"));
			allColumns.add(setColumn(ClienteColumnEnum.CEPCLI,  "commons.pages.cepcli"));
			allColumns.add(setColumn(ClienteColumnEnum.DDDCLI,  "commons.pages.dddcli"));
			allColumns.add(setColumn(ClienteColumnEnum.FONECLI,  "commons.pages.fonecli"));
			allColumns.add(setColumn(ClienteColumnEnum.RAMALCLI,  "commons.pages.ramalcli"));
			allColumns.add(setColumn(ClienteColumnEnum.DDDFAXCLI,  "commons.pages.dddfaxcli"));
			allColumns.add(setColumn(ClienteColumnEnum.FAXCLI,  "commons.pages.faxcli"));
			allColumns.add(setColumn(ClienteColumnEnum.EMAILCLI,  "commons.pages.emailcli"));
			allColumns.add(setColumn(ClienteColumnEnum.CONTCLI,  "commons.pages.contcli"));
			allColumns.add(setColumn(ClienteColumnEnum.ENDCOB,  "commons.pages.endcob"));
			allColumns.add(setColumn(ClienteColumnEnum.NUMCOB,  "commons.pages.numcob"));
			allColumns.add(setColumn(ClienteColumnEnum.COMPLCOB,  "commons.pages.complcob"));
			allColumns.add(setColumn(ClienteColumnEnum.BAIRCOB,  "commons.pages.baircob"));
			allColumns.add(setColumn(ClienteColumnEnum.CIDCOB,  "commons.pages.cidcob"));
			allColumns.add(setColumn(ClienteColumnEnum.UFCOB,  "commons.pages.ufcob"));
			allColumns.add(setColumn(ClienteColumnEnum.CEPCOB,  "commons.pages.cepcob"));
			allColumns.add(setColumn(ClienteColumnEnum.DDDFONECOB,  "commons.pages.dddfonecob"));
			allColumns.add(setColumn(ClienteColumnEnum.FONECOB,  "commons.pages.fonecob"));
			allColumns.add(setColumn(ClienteColumnEnum.DDDFAXCOB,  "commons.pages.dddfaxcob"));
			allColumns.add(setColumn(ClienteColumnEnum.FAXCOB,  "commons.pages.faxcob"));
			allColumns.add(setColumn(ClienteColumnEnum.ENDENT,  "commons.pages.endent"));
			allColumns.add(setColumn(ClienteColumnEnum.NUMENT,  "commons.pages.nument"));
			allColumns.add(setColumn(ClienteColumnEnum.COMPLENT,  "commons.pages.complent"));
			allColumns.add(setColumn(ClienteColumnEnum.BAIRENT,  "commons.pages.bairent"));
			allColumns.add(setColumn(ClienteColumnEnum.CIDENT,  "commons.pages.cident"));
			allColumns.add(setColumn(ClienteColumnEnum.UFENT,  "commons.pages.ufent"));
			allColumns.add(setColumn(ClienteColumnEnum.CEPENT,  "commons.pages.cepent"));
			allColumns.add(setColumn(ClienteColumnEnum.DDDFONEENT,  "commons.pages.dddfoneent"));
			allColumns.add(setColumn(ClienteColumnEnum.FONEENT,  "commons.pages.foneent"));
			allColumns.add(setColumn(ClienteColumnEnum.DDDFAXENT,  "commons.pages.dddfaxent"));
			allColumns.add(setColumn(ClienteColumnEnum.FAXENT,  "commons.pages.faxent"));
			allColumns.add(setColumn(ClienteColumnEnum.OBSCLI,  "commons.pages.obscli"));
			allColumns.add(setColumn(ClienteColumnEnum.AGENCIACLI,  "commons.pages.agenciacli"));
			allColumns.add(setColumn(ClienteColumnEnum.CODEMPPQ,  "commons.pages.codemppq"));
			allColumns.add(setColumn(ClienteColumnEnum.CODFILIALPQ,  "commons.pages.codfilialpq"));
			allColumns.add(setColumn(ClienteColumnEnum.CODPESQ,  "commons.pages.codpesq"));
			allColumns.add(setColumn(ClienteColumnEnum.INCRACLI,  "commons.pages.incracli"));
			allColumns.add(setColumn(ClienteColumnEnum.CODTPCRED,  "commons.pages.codtpcred"));
			allColumns.add(setColumn(ClienteColumnEnum.CODFILIALTR,  "commons.pages.codfilialtr"));
			allColumns.add(setColumn(ClienteColumnEnum.CODEMPTR,  "commons.pages.codemptr"));
			allColumns.add(setColumn(ClienteColumnEnum.DTINITR,  "commons.pages.dtinitr"));
			allColumns.add(setColumn(ClienteColumnEnum.DTVENCTOTR,  "commons.pages.dtvenctotr"));
			allColumns.add(setColumn(ClienteColumnEnum.NIRFCLI,  "commons.pages.nirfcli"));
			allColumns.add(setColumn(ClienteColumnEnum.CODFISCCLI,  "commons.pages.codfisccli"));
			allColumns.add(setColumn(ClienteColumnEnum.CODEMPFC,  "commons.pages.codempfc"));
			allColumns.add(setColumn(ClienteColumnEnum.CODFILIALFC,  "commons.pages.codfilialfc"));
			allColumns.add(setColumn(ClienteColumnEnum.NATCLI,  "commons.pages.natcli"));
			allColumns.add(setColumn(ClienteColumnEnum.UFNATCLI,  "commons.pages.ufnatcli"));
			allColumns.add(setColumn(ClienteColumnEnum.TEMPORESCLI,  "commons.pages.temporescli"));
			allColumns.add(setColumn(ClienteColumnEnum.CODPAIS,  "commons.pages.codpais"));
			allColumns.add(setColumn(ClienteColumnEnum.APELIDOCLI,  "commons.pages.apelidocli"));
			allColumns.add(setColumn(ClienteColumnEnum.CODEMPEC,  "commons.pages.codempec"));
			allColumns.add(setColumn(ClienteColumnEnum.CODFILIALEC,  "commons.pages.codfilialec"));
			allColumns.add(setColumn(ClienteColumnEnum.CODTBEC,  "commons.pages.codtbec"));
			allColumns.add(setColumn(ClienteColumnEnum.CODITTBEC,  "commons.pages.codittbec"));
			allColumns.add(setColumn(ClienteColumnEnum.SITECLI,  "commons.pages.sitecli"));
			allColumns.add(setColumn(ClienteColumnEnum.CODCONTDEB,  "commons.pages.codcontdeb"));
			allColumns.add(setColumn(ClienteColumnEnum.CODCONTCRED,  "commons.pages.codcontcred"));
			allColumns.add(setColumn(ClienteColumnEnum.CODCLASCLI,  "commons.pages.codclascli"));// Device

			
			
			
			


//
//			List<Column> allColumns = new ArrayList<Column>();
//			allColumns.add(setColumn(ClienteColumnEnum.CODPAIS, "CODPAIS"));
//			allColumns.add(setColumn(ClienteColumnEnum.ATIVOCLI, "ATIVOCLI"));
//			allColumns.add(setColumn(ClienteColumnEnum.CEPCOB, "CEPCOB"));
//			allColumns.add(setColumn(ClienteColumnEnum.CODBANCO, "Last Read Units"));
//			allColumns.add(setColumn(ClienteColumnEnum.CODEMPVD, "Last Read Time"));
//			allColumns.add(setColumn(ClienteColumnEnum.CODPLANOPAG, "Connection Status"));
//			allColumns.add(setColumn(ClienteColumnEnum.CODSETOR, "Remote Disconnect"));
//			allColumns.add(setColumn(ClienteColumnEnum.CODEMPPG, "TOU Status"));
//			allColumns.add(setColumn(ClienteColumnEnum.DATACLI, "State"));
//			allColumns.add(setColumn(ClienteColumnEnum.TEMPORESCLI, "Meter ID"));
//			allColumns.add(setColumn(ClienteColumnEnum.RAZCLI, "FlexNet ID"));
//			allColumns.add(setColumn(ClienteColumnEnum.RGCLI, "Date Added"));
//			allColumns.add(setColumn(ClienteColumnEnum.SITECLI, "City"));
//			allColumns.add(setColumn(ClienteColumnEnum.PESSOACLI, ""));
//			allColumns.add(setColumn(ClienteColumnEnum.DDDCLI, "Encryption Support"));
//			allColumns.add(setColumn(ClienteColumnEnum.DTVENCTOTR, "Latitude"));
//			allColumns.add(setColumn(ClienteColumnEnum.EMAILCLI, "Longitude"));
//			allColumns.add(setColumn(ClienteColumnEnum.APELIDOCLI, "Device Type"));
//			allColumns.add(setColumn(ClienteColumnEnum.AGENCIACLI, "Device Type"));
			columnFilterResponse.setAdditionalColumns(allColumns);

			List<ClienteFilterEnum> filters = new ArrayList<ClienteFilterEnum>();
			filters.add(ClienteFilterEnum.CODEMP);
			filters.add(ClienteFilterEnum.CODFILIAL);
			filters.add(ClienteFilterEnum.CODCLI);

			List<ClienteFilterEnum> allFilters = new ArrayList<ClienteFilterEnum>();
			allFilters.add(ClienteFilterEnum.CODCLASCLI);
			allFilters.add(ClienteFilterEnum.CNPJCLI);
			allFilters.add(ClienteFilterEnum.INSCCLI);
			allFilters.add(ClienteFilterEnum.INCRACLI);
			allFilters.add(ClienteFilterEnum.CODEMPFC);
			allFilters.add(ClienteFilterEnum.CODPAIS);
			allFilters.add(ClienteFilterEnum.ATIVOCLI);
			allFilters.add(ClienteFilterEnum.CEPCOB);
			allFilters.add(ClienteFilterEnum.CODEMPVD);
			allFilters.add(ClienteFilterEnum.CODPLANOPAG);

//			columnFilterResponse.setClienteFilters(filters);
//			columnFilterResponse.setAdditionalClienteFilters(allFilters);

			return columnFilterResponse;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			columnFilterResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return columnFilterResponse;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			columnFilterResponse.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			columnFilterResponse.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			columnFilterResponse.setOperationSuccess(false);

			return columnFilterResponse;
		}
		throw new RuntimeException("Kaboom");
	}




}
