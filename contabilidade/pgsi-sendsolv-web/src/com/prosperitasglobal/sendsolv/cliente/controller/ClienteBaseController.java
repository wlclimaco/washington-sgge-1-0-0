package com.prosperitasglobal.sendsolv.cliente.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.prosperitasglobal.controller.delegate.UtilControllerD;

public class ClienteBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ClienteBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "PessoaBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Pessoa BAI. */
	private IPessoaBAI pessoaBAI;

	/**
	 * Gets the pessoa bai.
	 *
	 * @return the pessoa bai
	 */
	public IPessoaBAI getPessoaBAI()
	{
		return pessoaBAI;
	}

	/**
	 * Sets the pessoa bai.
	 *
	 * @param pessoaBAI the pessoa bai
	 */
	@Resource
	public void setPessoaBAI(IPessoaBAI pessoaBAI)
	{
		this.pessoaBAI = pessoaBAI;
	}

	/**
	 * Cliente edit mav.
	 *
	 * @param pessoaId the pessoa id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView clienteEditMAV(Integer clienteId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(clienteId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchClienteById(new FetchByIdRequest(clienteId))));

				return modelAndView;
			}

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return modelAndView;
	}

	/**
	 * Fetch cliente by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the cliente response
	 */
	public ClienteResponse fetchClienteByRequest(ClienteInquiryRequest pagedInquiryRequest)
	{

		ClienteResponse clienteResponse = new ClienteResponse();
		try
		{

			clienteResponse = getPessoaBAI().fetchClienteByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return clienteResponse;
	}

	/**
	 * Fetch cliente by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the cliente response
	 */
	public ClienteResponse fetchClienteById(FetchByIdRequest fetchByIdRequest)
	{

		ClienteResponse clienteResponse = new ClienteResponse();
		try
		{

			clienteResponse = getPessoaBAI().fetchClienteById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return clienteResponse;
	}

	public ClienteResponse edit(ClienteMaintenanceRequest clienteRequest)
	{
		ClienteResponse clienteResponse = new ClienteResponse();
		try
		{

			clienteResponse = getPessoaBAI().updateCliente(clienteRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			clienteResponse = null;
		}
		return clienteResponse;

	}

	public ClienteResponse delete(ClienteMaintenanceRequest clienteRequest)
	{
		ClienteResponse clienteResponse = new ClienteResponse();
		try
		{

			clienteResponse = getPessoaBAI().deleteCliente(clienteRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			clienteResponse = null;
		}

		return clienteResponse;

	}

	public ClienteResponse insert(ClienteMaintenanceRequest clienteRequest)
	{
		ClienteResponse clienteResponse = new ClienteResponse();

		try
		{

			clienteRequest.setCliente(insertCliente(PersistanceActionEnum.INSERT));
			clienteRequest.getCliente().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			clienteResponse = getPessoaBAI().insertCliente(clienteRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			clienteResponse = null;
		}

		return clienteResponse;

	}

	public List<Endereco> insertEndereco(PersistanceActionEnum modelAction)
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
		enderecoList.add(endereco);
		return enderecoList;
	}

	public List<Email> insertEmail(PersistanceActionEnum modelAction)
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

	public List<Profissao> insertProfissao(PersistanceActionEnum modelAction)
	{

		List<Profissao> profissaoList = new ArrayList<Profissao>();
		Profissao profissao = new Profissao();
		profissao.setId(1);
		profissao.setProfissao("Operado de caixa");
		profissao.setRenda(new Double(787.89));
		Date a = new Date();
		profissao.setDataAdmissao(a.getTime());
		profissaoList.add(profissao);

		profissao = new Profissao();
		profissao.setId(2);
		profissao.setProfissao("Operado de caixa 2");
		profissao.setRenda(new Double(820.89));
		a = new Date();
		profissao.setDataAdmissao(a.getTime() - 10000000);
		profissaoList.add(profissao);

		profissao = new Profissao();
		profissao.setId(3);
		profissao.setProfissao("Operado de caixa 3");
		profissao.setRenda(new Double(820.89));
		a = new Date();
		profissao.setDataAdmissao(a.getTime() - 20000000);
		profissaoList.add(profissao);

		profissao = new Profissao();
		profissao.setId(4);
		profissao.setProfissao("Operado de caixa 4");
		profissao.setRenda(new Double(920.89));
		a = new Date();
		profissao.setDataAdmissao(a.getTime() - 30000000);
		profissaoList.add(profissao);

		return profissaoList;
	}

	public List<Telefone> insertTelefone(PersistanceActionEnum modelAction)
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

	public List<Documento> insertDocumento(PersistanceActionEnum modelAction)
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

	public List<Note> insertNote(PersistanceActionEnum modelAction)
	{
		List<Note> noteList = new ArrayList<Note>();
		Note note = new Note();
		note.setModelAction(modelAction);
		note.setId(1);
		note.setNoteText("Texto Texto Texto Texto Texto Texto Texto Texto Texto ");
		note.setTabelaEnum(TabelaEnum.Cliente);
		noteList.add(note);

		note = new Documento();
		note.setModelAction(modelAction);
		note.setId(2);
		note.setNoteText("Texto Texto Texto Texto Texto Texto Texto Texto Texto ");
		note.setTabelaEnum(TabelaEnum.Cliente);
		noteList.add(note);
		noteList.add(note);

		note = new Documento();
		note.setModelAction(modelAction);
		note.setId(3);
		note.setNoteText("Texto Texto Texto Texto Texto Texto Texto Texto Texto ");
		note.setTabelaEnum(TabelaEnum.Cliente);
		noteList.add(note);
		noteList.add(note);

		return noteList;
	}

	public List<Contato> insertContato(PersistanceActionEnum modelAction)
	{
		List<Contato> contatoList = new ArrayList<Contato>();

		ContatoItens contatoItens = new ContatoItens();
		contatoItens.setId();
		contatoItens.setNoteList();
		contatoItens.setMotivo(ContatoTypeEnum.COBRANCA);
		a = new Date();
		contatoItens.setDataContato(a.getTime());
		contatoItens.setNomeContato("Maria de lourdes");

		Contato contato = new Contato();
		contato.setModelAction(modelAction);
		contato.setId(1);
		a = new Date();
		contato.setDataContato(a.getTime());
		contato.setContatoItensList(new ArrayList<ContatoItens>());
		contato.getContatoItensList().add(contatoItens);
		contatoList.add(contato);

		return contatoList;
	}

	public Empresa insertMockCliente(PersistanceActionEnum modelAction)
	{
		Cliente cliente = new Cliente();

		cliente.setId(1);
		cliente.setNome("Damiao jose junior");
		cliente.setNomePai("Damiao Jose Pai");
		cliente.setNomeMae("Sonia Mae");
		cliente.setNomeConjugue("Vinicios Felisberto");
		cliente.setModelAction(modelAction);
		cliente.setEstadoCivil(1);
		a = new Date();
		cliente.setDatanasc(a.getTime() - 100000000);
		cliente.setPessoaTypeEnum(PessoaTypeEnum.Cliente);
		cliente.setSexo(1);
		cliente.setEnderecos(insertEndereco(modelAction));
		cliente.setDocumentos(insertDocumento(modelAction));
		cliente.setEmails(insertEmail(modelAction));
		cliente.setTelefones(insertTelefone(modelAction));
		cliente.setNotes(insertNote(modelAction));
		cliente.setBancos(new ArrayList<Banco>());
		cliente.getBancos().add(new Banco(1));
		cliente.setcontatoList(insertContato(modelAction));
		cliente.setprofissao(insertProfissao(modelAction));
		cliente.setConvenioList(new ArrayList<Convenio>());
		cliente.getConvenioList().add(new Convenio(1));

		return cliente;

	}
}
