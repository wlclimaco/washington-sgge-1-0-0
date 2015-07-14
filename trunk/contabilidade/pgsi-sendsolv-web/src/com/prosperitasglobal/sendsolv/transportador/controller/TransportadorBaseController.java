package com.prosperitasglobal.sendsolv.transportador.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.prosperitasglobal.controller.delegate.UtilControllerD;

public class TransportadorBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TransportadorBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "PessoaBaseController";

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
	 * Transportador edit mav.
	 *
	 * @param pessoaId the pessoa id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView transportadorEditMAV(Integer transportadorId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(transportadorId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchTransportadorById(new FetchByIdRequest(transportadorId))));

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
	 * Fetch transportador by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the transportador response
	 */
	public TransportadorResponse fetchTransportadorByRequest(TransportadorInquiryRequest pagedInquiryRequest)
	{

		TransportadorResponse transportadorResponse = new TransportadorResponse();
		try
		{

			transportadorResponse = getPessoaBAI().fetchTransportadorByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return transportadorResponse;
	}

	/**
	 * Fetch transportador by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the transportador response
	 */
	public TransportadorResponse fetchTransportadorById(FetchByIdRequest fetchByIdRequest)
	{

		TransportadorResponse transportadorResponse = new TransportadorResponse();
		try
		{

			transportadorResponse = getPessoaBAI().fetchTransportadorById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return transportadorResponse;
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

	public Transportador insertMockTransportador(PersistanceActionEnum modelAction)
	{
		Transportador transportador = new Transportador();

		transportador.setId(1);
		transportador.setNome("Damiao jose junior");
		transportador.setNomePai("Damiao Jose Pai");
		transportador.setNomeMae("Sonia Mae");
		transportador.setNomeConjugue("Vinicios Felisberto");
		transportador.setModelAction(modelAction);
		transportador.setEstadoCivil(1);
		a = new Date();
		transportador.setDatanasc(a.getTime() - 100000000);
		transportador.setPessoaTypeEnum(PessoaTypeEnum.Cliente);
		transportador.setSexo(1);
		transportador.setEnderecos(insertEndereco(modelAction));
		transportador.setDocumentos(insertDocumento(modelAction));
		transportador.setEmails(insertEmail(modelAction));
		transportador.setTelefones(insertTelefone(modelAction));
		transportador.setNotes(insertNote(modelAction));
		transportador.setBancos(new ArrayList<Banco>());
		transportador.getBancos().add(new Banco(1));
		transportador.setcontatoList(insertContato(modelAction));
		transportador.setListCfops(new ArrayList<Cfop>());
		transportador.getListCfops().add(new Cfop(1, PersistanceActionEnum.NONE));
		transportador.getListProdutos(new ArrayList<Produto>());
		transportador.getListProdutos().add(new Produto(1));
		transportador.getListProdutos().add(new Produto(2));
		transportador.getListProdutos().add(new Produto(3));
		return transportador;

	}
}
