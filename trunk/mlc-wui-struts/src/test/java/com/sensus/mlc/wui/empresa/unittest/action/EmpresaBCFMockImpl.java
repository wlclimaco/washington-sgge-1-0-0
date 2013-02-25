package com.sensus.mlc.wui.empresa.unittest.action;




import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.Request;
import com.sensus.common.model.ResultsSetInfo;
import com.sensus.mlc.almoxarifado.model.Almoxarifado;
import com.sensus.mlc.dicionario.model.Atributos;
import com.sensus.mlc.dicionario.model.Table;
import com.sensus.mlc.dicionario.model.Validation;
import com.sensus.mlc.empresa.bcf.IEmpresaBCF;
import com.sensus.mlc.empresa.model.Empresa;
import com.sensus.mlc.empresa.model.request.EmpresaRequest;
import com.sensus.mlc.empresa.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.empresa.model.response.EmpresaResponse;
import com.sensus.mlc.empresa.model.response.InquiryEmpresaResponse;
import com.sensus.mlc.filial.model.Filial;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

/**
 * The Class ScheduleBCFMockImpl.
 * 
 * @ahthor Eduardo Maia
 */
public class EmpresaBCFMockImpl extends BaseMockImpl implements IEmpresaBCF
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

	@Override
	public EmpresaResponse insertEmpresa(EmpresaRequest empresaRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaResponse updateEmpresa(EmpresaRequest empresaRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaResponse deleteEmpresa(EmpresaRequest empresaRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<Filial> createFilial(){
	
		List<Filial>  filiais = new ArrayList<Filial>();
		Filial filial = new Filial();
		filial.setCodemp(1);

		filial.setCodemp(1);
		filial.setCodfilial(1);
		filial.setRazfilial("Razao");
		filial.setNomefilial("NOME FILIAL");
		filial.setMzfilial("Sim");
		filial.setCnpjfilial("123456789");
		filial.setInscfilial("123456");
		filial.setEndfilial("ENDERECO");
		filial.setNumfilial(554);
		filial.setComplfilial("");
		filial.setBairfilial("BAirro");
		filial.setCepfilial("38082-243");
		filial.setCidfilial("");
		filial.setUffilial("");
		filial.setDddfilial("34");
		filial.setFonefilial("3333-3333");
		filial.setFaxfilial("3333-3333");
		filial.setEmailfilial("Email");
		filial.setWwwfilial("SITE");
		filial.setCoddistfilial(1);
		filial.setPercpisfilial(new Float(12.00));
		filial.setPerccofinsfilial(new Float(12.00));
		filial.setPercirfilial(new Float(12.00));
		filial.setPerccsocialfilial(new Float(12.00));
		filial.setPercsimplesfilial(new Float(12.00));
		filial.setCodmunic("");
		filial.setSiglauf("MG");
		filial.setCodpais(55);
		filial.setCodempuc(1);
		filial.setCodfilialuc(1);
		filial.setCodunifcod(1);
		filial.setInscmunfilial("");
		filial.setCnaefilial("");
		filial.setPercissfilial(new Float(12.00));
		filial.setContribipifilial("");
		filial.setTimbrefilial(null);
		filial.setPerfilfilial("");
		filial.setIndativfilial("");
		filial.setDtins(null);
		filial.setHins(null);
		filial.setIdusuins(1);
		filial.setDtalt(null);
		filial.setHalt(null);
		filial.setIdusualt("");
		filial.setCodempco(1);
		filial.setCodfilialco(1);
		filial.setCodfor(1);
		filial.setSuframa("");	
		filial.setSedeMatriz(false);
		filial.setCodemp(1);
		filial.setBairfilial("Mangueiras");
		filial.setNomefilial("Nome empresa");
		filial.setRazfilial("Razao da empresa");
		filial.setCepfilial("38082-243");
		filial.setCidfilial("Uberaba");
		filial.setCnpjfilial("1234567890/123");
		filial.setCodmunic("123456");
		filial.setCodpais(55);
		filial.setComplfilial("complemp");
		filial.setCreateDate(new Date());
		filial.setCreateUser("createUser");
		filial.setDddfilial("dddemp");
		filial.setInscfilial("inscemp");	
		filial.setListAlmoxarifados(createAlmoxarifado());
		
		filiais.add(filial);
		
		filial = new Filial();
		
		filial.setCodemp(2);
		filial.setCodfilial(1);
		filial.setRazfilial("Razao");
		filial.setNomefilial("NOME FILIAL");
		filial.setMzfilial("Sim");
		filial.setCnpjfilial("123456789");
		filial.setInscfilial("123456");
		filial.setEndfilial("ENDERECO");
		filial.setNumfilial(554);
		filial.setComplfilial("");
		filial.setBairfilial("BAirro");
		filial.setCepfilial("38082-243");
		filial.setCidfilial("");
		filial.setUffilial("");
		filial.setDddfilial("34");
		filial.setFonefilial("3333-3333");
		filial.setFaxfilial("3333-3333");
		filial.setEmailfilial("Email");
		filial.setWwwfilial("SITE");
		filial.setCoddistfilial(1);
		filial.setPercpisfilial(new Float(12.00));
		filial.setPerccofinsfilial(new Float(12.00));
		filial.setPercirfilial(new Float(12.00));
		filial.setPerccsocialfilial(new Float(12.00));
		filial.setPercsimplesfilial(new Float(12.00));
		filial.setCodmunic("");
		filial.setSiglauf("MG");
		filial.setCodpais(55);
		filial.setCodempuc(1);
		filial.setCodfilialuc(1);
		filial.setCodunifcod(1);
		filial.setInscmunfilial("");
		filial.setCnaefilial("");
		filial.setPercissfilial(new Float(12.00));
		filial.setContribipifilial("");
		filial.setTimbrefilial(null);
		filial.setPerfilfilial("");
		filial.setIndativfilial("");
		filial.setDtins(null);
		filial.setHins(null);
		filial.setIdusuins(1);
		filial.setDtalt(null);
		filial.setHalt(null);
		filial.setIdusualt("");
		filial.setCodempco(1);
		filial.setCodfilialco(1);
		filial.setCodfor(1);
		filial.setSuframa("");	
		filial.setSedeMatriz(false);
		filial.setCodemp(1);
		filial.setBairfilial("Mangueiras");
		filial.setNomefilial("Nome empresa");
		filial.setRazfilial("Razao da empresa");
		filial.setCepfilial("38082-243");
		filial.setCidfilial("Uberaba");
		filial.setCnpjfilial("1234567890/123");
		filial.setCodmunic("123456");
		filial.setCodpais(55);
		filial.setComplfilial("complemp");
		filial.setCreateDate(new Date());
		filial.setCreateUser("createUser");
		filial.setDddfilial("dddemp");
		filial.setInscfilial("inscemp");	
		filial.setListAlmoxarifados(createAlmoxarifado());
		
		filiais.add(filial);
		
		return filiais;
	}
	
	private List<Empresa> createEmpresa(){
		List<Empresa>  empresas = new ArrayList<Empresa>();
		Empresa empresa = new Empresa();
		
		empresa.setCodemp(1);
		empresa.setRazemp("Teste Razao");
		empresa.setNomeemp("Nome empresa");
		empresa.setCnpjemp("Cnpjemp");
		empresa.setInscemp("Inscemp");
		empresa.setEmdemp("Emdemp");
		empresa.setNumemp(999);
		empresa.setComplemp("Complemp");
		empresa.setBairemp("Bairemp");
		empresa.setCepemp("Cepemp");
		empresa.setCidemp("Cidemp");
		empresa.setUfemp("Ufemp");
		empresa.setDddemp("Dddemp");
		empresa.setFoneemp("Foneemp");
		empresa.setFaxemp("Faxemp");
		empresa.setEmailemp("Emailemp");
		empresa.setWwwemp("Wwwemp");
		empresa.setCodeanemp("Codeanemp");
		empresa.setNomecontemp("Nomecontemp");
		empresa.setMultialmoxemp("Multialmoxemp");
		empresa.setFotoemp(null);
		empresa.setCodmunic("Codmunic");
		empresa.setSiglauf("Siglauf");
		empresa.setCodpais(055);
		empresa.setPercissemp(new Float(2.2));
		empresa.setCodpaisemp("Codpaisemp");
		empresa.setDtins(null);
		empresa.setHins(null);
		empresa.setIdusuins(1);
		empresa.setDtalt(null);
		empresa.setHalt(null);
		empresa.setIdusualt("2");	
		empresa.setListFilial(createFilial());
		empresas.add(empresa);
		
		
		
		empresa = new Empresa();
		empresa.setCodemp(2);
		empresa.setRazemp("Teste Razao");
		empresa.setNomeemp("Nome empresa");
		empresa.setCnpjemp("Cnpjemp");
		empresa.setInscemp("Inscemp");
		empresa.setEmdemp("Emdemp");
		empresa.setNumemp(999);
		empresa.setComplemp("Complemp");
		empresa.setBairemp("Bairemp");
		empresa.setCepemp("Cepemp");
		empresa.setCidemp("Cidemp");
		empresa.setUfemp("Ufemp");
		empresa.setDddemp("Dddemp");
		empresa.setFoneemp("Foneemp");
		empresa.setFaxemp("Faxemp");
		empresa.setEmailemp("Emailemp");
		empresa.setWwwemp("Wwwemp");
		empresa.setCodeanemp("Codeanemp");
		empresa.setNomecontemp("Nomecontemp");
		empresa.setMultialmoxemp("Multialmoxemp");
		empresa.setFotoemp(null);
		empresa.setCodmunic("Codmunic");
		empresa.setSiglauf("Siglauf");
		empresa.setCodpais(055);
		empresa.setPercissemp(new Float(2.2));
		empresa.setCodpaisemp("Codpaisemp");
		empresa.setDtins(null);
		empresa.setHins(null);
		empresa.setIdusuins(1);
		empresa.setDtalt(null);
		empresa.setHalt(null);
		empresa.setIdusualt("2");	
		empresa.setListFilial(createFilial());
		empresas.add(empresa);
		return empresas;
	}
	
	private List<Almoxarifado> createAlmoxarifado(){
		
		List<Almoxarifado>  almoxarifados = new ArrayList<Almoxarifado>();
		
		Almoxarifado almoxarifado = new Almoxarifado(1,1, "Seco");
		almoxarifados.add(almoxarifado);
		
		almoxarifado = new Almoxarifado(1,1, "MOLHADO");
		almoxarifados.add(almoxarifado);
		return almoxarifados;
	}
	
	private Table createTable(){
		Table table = new Table();
		List<Atributos>  atributos = new ArrayList<Atributos>();
		table.setCodTable(1);
		table.setNome("Empresa/Filial");
		atributos.add(createTributos("Codemp",1));
		atributos.add(createTributos("Codfilial",2));//filial
		atributos.add(createTributos("Razemp",3));
		atributos.add(createTributos("Nomeemp",4));
		atributos.add(createTributos("Mzfilial",5)); //filial
		atributos.add(createTributos("Cnpjemp",6));
		atributos.add(createTributos("Inscemp",7));
		atributos.add(createTributos("End",8));
		atributos.add(createTributos("Num",9));
		atributos.add(createTributos("Comple",10));
		atributos.add(createTributos("Bair",11));
		atributos.add(createTributos("Cep",12));
		atributos.add(createTributos("Cid",13));
		atributos.add(createTributos("Uf",14));
		atributos.add(createTributos("Ddd",15));
		atributos.add(createTributos("Fone",16));
		atributos.add(createTributos("Fax",17));
		atributos.add(createTributos("Email",18));
		atributos.add(createTributos("Www",19));
		atributos.add(createTributos("Multialmox",20));
		table.setAtributos(atributos);
		
		return table;
	}
	private Atributos createTributos(String name,Integer id){
		
		Atributos atributo = new Atributos();				
		atributo.setId(id);
		atributo.setName(name);
		atributo.setForeignkeys(true);
		atributo.setPrimaryKey(false);
		atributo.setType("Integer");
		atributo.setTamanho(null);
		atributo.setForeignkeysTable(1);
		return atributo;
		
		
	}

	@Override
	public InquiryEmpresaResponse fetchAllEmpresa(
			InquiryEmpresaRequest inquiryempresaRequest) {
		
		InquiryEmpresaResponse response = new InquiryEmpresaResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setEmpresa(new ArrayList<Empresa>());

			pageSize = inquiryempresaRequest.getPageSize();

			response.setEmpresa(createEmpresa());
			response.setTable(createTable());
				
				ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
				resultsSetInfo.setTotalRowsAvailable(ONE_HUNDRED);
				response.setResultsSetInfo(resultsSetInfo);
			
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
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
		throw new RuntimeException(KABOOM);
	}

	@Override
	public EmpresaResponse fetchEmpresaById(EmpresaRequest empresaRequest) {
		EmpresaResponse empresaResponse = new EmpresaResponse();
		
		List<Empresa> empresas = new ArrayList<Empresa>();

		if ((empresaRequest.getEmpresa() == null) && (empresaRequest.getEmpresa().getCepemp() == null))
		{
			empresaResponse.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			empresaResponse.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			empresaResponse.setOperationSuccess(false);
			return empresaResponse;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{

			
				Empresa empresa = new Empresa();
				empresa.setCodemp(1);
				empresa.setBairemp("Mangueiras");
				empresa.setNomeemp("Nome empresa");
				empresa.setRazemp("Razao da empresa");
				empresa.setCepemp("38082-243");
				empresa.setCidemp("Uberaba");
				empresa.setCnpjemp("1234567890/123");
				empresa.setCodeanemp("codeanemp");
				empresa.setCodmunic("123456");
				empresa.setCodpais(55);
				empresa.setNomecontemp("Washington");
				empresa.setCodpaisemp("55");
				empresa.setComplemp("complemp");
				empresa.setCreateDate(new Date());
				empresa.setCreateUser("createUser");
				empresa.setDddemp("dddemp");
				empresa.setInscemp("inscemp");				
				empresas.add(empresa);
				empresaResponse.setEmpresa(empresas);

				empresaResponse.setOperationSuccess(true);
				empresaResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return empresaResponse;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			return empresaResponse;
		}
		throw new RuntimeException(KABOOM);
	}

	@Override
	public ProcessResponse insertCSVProcess(InquiryEmpresaRequest empresaRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaResponse fetchAllEmpresaTypes(Request request) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ProcessResponse generateFileCSV(
			InquiryEmpresaRequest inquiryEmpresaRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaResponse fetchAllEmpresaFilial(
			InquiryEmpresaRequest inquiryEmpresaRequest) {
		EmpresaResponse empresaResponse = new EmpresaResponse();
		
		List<Empresa> empresas = new ArrayList<Empresa>();
		
		List<Filial> filiais = new ArrayList<Filial>();

		if ((inquiryEmpresaRequest.getEmpresas().get(0).getCodemp() == null) && (inquiryEmpresaRequest.getEmpresas().get(0) == null))
		{
			empresaResponse.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			empresaResponse.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			empresaResponse.setOperationSuccess(false);
			return empresaResponse;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{

			
				Empresa empresa = new Empresa();
				empresa.setCodemp(1);
				empresa.setBairemp("Mangueiras");
				empresa.setNomeemp("Nome empresa");
				empresa.setRazemp("Razao da empresa");
				empresa.setCepemp("38082-243");
				empresa.setCidemp("Uberaba");
				empresa.setCnpjemp("1234567890/123");
				empresa.setCodeanemp("codeanemp");
				empresa.setCodmunic("123456");
				empresa.setCodpais(55);
				empresa.setNomecontemp("Washington");
				empresa.setCodpaisemp("55");
				empresa.setComplemp("complemp");
				empresa.setCreateDate(new Date());
				empresa.setCreateUser("createUser");
				empresa.setDddemp("dddemp");
				empresa.setInscemp("inscemp");	
				Filial filial = new Filial();
				
				filial.setCodemp(1);
				filial.setNomefilial("NOME FANTASIA 001");
				filial.setRazfilial("RAZAO TESTE 001");
				filial.setCnpjfilial("CNPJ");
				filial.setInscfilial("INSC ESTADUAL");
				filial.setCodmunic("INSCRICAO MUNICIPAL");
				filial.setCepfilial("CEP");
				filial.setEndfilial("ENDERECO");
				filial.setNumfilial(34);
				filial.setCnaefilial("CNAE");
				filial.setComplfilial("COMPLEMENTO");
				filial.setBairfilial("BAIRRO");
				filial.setDddfilial("DDD");
				filial.setFonefilial("FONE");
				filial.setFaxfilial("FAX");
				filial.setEmailfilial("EMAIL");
				filial.setWwwfilial("SITE");
				filial.setCoddistfilial(2);
				filial.setSedeMatriz(new Boolean(true));
				filiais.add(filial);
				empresa.setListFilial(filiais);		
				empresas.add(empresa);
				empresaResponse.setEmpresa(empresas);

				empresaResponse.setOperationSuccess(true);
				empresaResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return empresaResponse;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			return empresaResponse;
		}
		throw new RuntimeException(KABOOM);
	}



}
