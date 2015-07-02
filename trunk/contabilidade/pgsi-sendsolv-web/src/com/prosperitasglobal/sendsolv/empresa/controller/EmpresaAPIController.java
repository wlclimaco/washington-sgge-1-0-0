package com.prosperitasglobal.sendsolv.empresa.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/empresa")
public class EmpresaAPIController extends EmpresaBaseController
{

	/** The Constant FETCH_SIC_NAICS. */
	private static final String FETCH_SIC_NAICS = "fetchSicNaics";
	/** The Constant FETCH_ORGANIZATION_BYEMPRESA. */
	private static final String FETCH_ORGANIZATION_BYEMPRESA = "fetchOrganizationByempresa";
	/** The URL mapping constants. */
	private static final String DELETE_EMPRESA = "/delete";

	/** The Constant EDIT_EMPRESA. */
	private static final String EDIT_EMPRESA = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant INSERT_EMPRESA. */
	private static final String INSERT_EMPRESA = "/add";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";
	/** The Constant APPLY. */
	private static final String APPLY = "/applyStatus";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EmpresaAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "EmpresaAPIController";

	/**
	 * Fetch all Empresas.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse fetchAll(@RequestBody EmpresaInquiryRequest pagedInquiryRequest)
	{

		return fetchEmpresaByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the empresa response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchEmpresaById(fetchByIdRequest);

	}

	/**
	 * Edit one empresa.
	 *
	 * @param empresaRequest the empresa request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_EMPRESA, method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse editEmpresa(@RequestBody EmpresaMaintenanceRequest empresaRequest)
	{
		empresaRequest.setEmpresa(insertMockEmpresa(ModelAction.INSERT));
		return edit(empresaRequest);

	}

	/**
	 * Delete one empresa.
	 *
	 * @param empresaRequest the empresa request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_EMPRESA, method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse deleteEmpresa(@RequestBody EmpresaMaintenanceRequest empresaRequest)
	{
		empresaRequest.setEmpresa(insertMockEmpresa(ModelAction.INSERT));
		return delete(empresaRequest);

	}

	/**
	 * Insert one empresa.
	 *
	 * @param empresaRequest the empresa request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_EMPRESA, method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse insertEmpresa(@RequestBody EmpresaMaintenanceRequest empresaRequest)
	{
		empresaRequest.setEmpresa(insertMockEmpresa(ModelAction.INSERT));
		return insert(empresaRequest);

	}

	public List<Endereco> insertEndereco(ModelAction modelAction)
	{
		List<Endereco> enderecoList = new ArrayList<Endereco>();
		Endereco endereco = new Endereco();

		endereco.setModelAction(modelAction);
		endereco.setId(1);
		endereco.setLogradouro("R: Maria Conceição silva");
		endereco.setCidade(new Cidade(1));
		endereco.setEstado(new Estado(1));
		endereco.setBairro("Mangueiras");
		endereco.setNumero("686");
		endereco.setCep("38082-243");

		return enderecoList;
	}

	public List<Email> insertEmail(ModelAction modelAction)
	{

		List<Email> emailList = new ArrayList<Email>();
		Email email = new Email();
		email.setId(1);
		email.setEmail("wlclimaco@gmail.com");
		email.setDescription("Casa");
		email.setModelAction(modelAction);
		emailList.add(email);

		email = new Email();
		email.setId(2);
		email.setEmail("wlclimaco@yahoo.com.br");
		email.setDescription("Trabalho");
		email.setModelAction(modelAction);
		emailList.add(email);

		email = new Email();
		email.setId(3);
		email.setEmail("wlclimaco@hotmail.com");
		email.setDescription("Casa Joaquina");
		email.setModelAction(modelAction);
		emailList.add(email);

		return emailList;
	}

	public List<Cnae> insertCnae(ModelAction modelAction)
	{
		List<Cnae> cnaeList = new ArrayList<Cnae>();
		Cnae cnae = new Cnae();
		cnae.setModelAction("NONE");
		cnae.setId(1);
		cnaeList.add(cnae);

		cnae = new Cnae();
		cnae.setModelAction("NONE");
		cnae.setId(2);
		cnaeList.add(cnae);

		cnae = new Cnae();
		cnae.setModelAction("NONE");
		cnae.setId(3);
		cnaeList.add(cnae);

		return cnaeList;
	}

	public List<Telefone> insertTelefone(ModelAction modelAction)
	{
		List<Telefone> telefoneList = new ArrayList<Telefone>();
		Telefone telefone = new Telefone();
		telefone.setModelAction(modelAction);
		telefone.setId(1);
		telefone.setDdd("34");
		telefone.setTelefone("91782776");
		telefone.setDescricao("Casa");
		telefoneList.add(telefone);

		telefone = new Telefone();
		telefone.setModelAction(modelAction);
		telefone.setId(1);
		telefone.setDdd("34");
		telefone.setTelefone("91782776");
		telefone.setDescricao("Trabalho");
		telefoneList.add(telefone);

		telefone = new Telefone();
		telefone.setModelAction(modelAction);
		telefone.setId(1);
		telefone.setDdd("34");
		telefone.setTelefone("91782776");
		telefone.setDescricao("Celular");
		telefoneList.add(telefone);

		return telefoneList;
	}

	public List<Documento> insertDocumento(ModelAction modelAction)
	{
		List<Documento> documentoList = new ArrayList<Documento>();
		Documento documento = new Documento();
		documento.setModelAction(modelAction);
		documento.setId(1);
		documento.setDescription("CNPJ");
		documento.setNumero("111111111000001");
		documentoList.add(documento);

		documento = new Documento();
		documento.setModelAction(modelAction);
		documento.setId(1);
		documento.setDescription("IM");
		documento.setNumero("111111111000001");
		documentoList.add(documento);

		documento = new Documento();
		documento.setModelAction(modelAction);
		documento.setId(1);
		documento.setDescription("IE");
		documento.setNumero("111111111000001");
		documentoList.add(documento);

		return documentoList;
	}

	public List<Socio> insertDocumento(ModelAction modelAction)
	{
		List<Socio> socioList = new ArrayList<Socio>();
		Socio socio = new Socio();
		socio.setModelAction(modelAction;
		socio.setId(1);
		socio.setNome("Washington Luis");
		socio.setCota("1");
		socio.setPorcentagem("50");
		socioList.add(socio);

		socio = new Socio();
		socio.setModelAction(modelAction);
		socio.setId(1);
		socio.setNome("Washington Climaco");
		socio.setCota("1");
		socio.setPorcentagem("50");
		socioList.add(socio);

		return socioList;
	}

	public Empresa insertMockEmpresa(ModelAction modelAction)
	{
		Empresa empresa = new Empresa();

		empresa.setId(1);
		empresa.setModelAction(modelAction);
		empresa.setNome("Cosme e damiao Contabiliadade");
		empresa.setRegime(new Regime(1));
		empresa.setConfiguracao(new Configuracao(1));
		empresa.setEnderecos(insertEndereco(modelAction));
		empresa.setDocumentos(insertDocumento(modelAction));
		empresa.setEmails(insertEmail(modelAction));
		empresa.setTelefones(insertTelefone(modelAction));
		empresa.setSocios(insertSocio(modelAction));
		empresa.setCnaes(insertCnae(modelAction));

		return empresa;

	}
}
