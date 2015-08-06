package com.prosperitasglobal.sendsolv.fornecedor.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IPessoaBAI;
import com.prosperitasglobal.sendsolv.model.BancoPessoa;
import com.prosperitasglobal.sendsolv.model.CfopPessoa;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Contato;
import com.prosperitasglobal.sendsolv.model.ContatoItens;
import com.prosperitasglobal.sendsolv.model.ContatoTypeEnum;
import com.prosperitasglobal.sendsolv.model.Documento;
import com.prosperitasglobal.sendsolv.model.Email;
import com.prosperitasglobal.sendsolv.model.Endereco;
import com.prosperitasglobal.sendsolv.model.Estado;
import com.prosperitasglobal.sendsolv.model.Fornecedor;
import com.prosperitasglobal.sendsolv.model.PessoaTypeEnum;
import com.prosperitasglobal.sendsolv.model.Produto;
import com.prosperitasglobal.sendsolv.model.ProdutoPessoa;
import com.prosperitasglobal.sendsolv.model.Profissao;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.Telefone;
import com.prosperitasglobal.sendsolv.model.request.FornecedorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.FornecedorResponse;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.validation.ValidationUtil;

public class FornecedorBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FornecedorBaseController.class);

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
	 * Fornecedor edit mav.
	 *
	 * @param pessoaId the pessoa id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView fornecedorEditMAV(Integer fornecedorId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(fornecedorId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchFornecedorById(new FetchByIdRequest(fornecedorId))));

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
	 * Fetch fornecedor by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the fornecedor response
	 */
	public FornecedorResponse fetchFornecedorByRequest(FornecedorInquiryRequest pagedInquiryRequest)
	{

		FornecedorResponse fornecedorResponse = new FornecedorResponse();
		try
		{

			fornecedorResponse = getPessoaBAI().fetchFornecedorByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return fornecedorResponse;
	}

	/**
	 * Fetch fornecedor by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the fornecedor response
	 */
	public FornecedorResponse fetchFornecedorById(FetchByIdRequest fetchByIdRequest)
	{

		FornecedorResponse fornecedorResponse = new FornecedorResponse();
		try
		{

			fornecedorResponse = getPessoaBAI().fetchFornecedorById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return fornecedorResponse;
	}

	public FornecedorResponse insert(FornecedorMaintenanceRequest clienteRequest)
	{
		FornecedorResponse clienteResponse = new FornecedorResponse();

		try
		{

			clienteRequest.setFornecedor(insertMockFornecedor(PersistanceActionEnum.INSERT));
			clienteRequest.getFornecedor().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			clienteResponse = getPessoaBAI().insertFornecedor(clienteRequest);
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
		email.setModelAction(modelAction);
		emailList.add(email);

		email = new Email();
		email.setId(2);
		email.setEmail("wlclimaco@yahoo.com.br");
		email.setModelAction(modelAction);
		emailList.add(email);

		email = new Email();
		email.setId(3);
		email.setEmail("wlclimaco@hotmail.com");
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
		telefone.setNumero("91782776");
		telefoneList.add(telefone);

		telefone = new Telefone();
		telefone.setModelAction(modelAction);
		telefone.setId(1);
		telefone.setDdd("34");
		telefone.setNumero("91782776");
		telefoneList.add(telefone);

		telefone = new Telefone();
		telefone.setModelAction(modelAction);
		telefone.setId(1);
		telefone.setDdd("34");
		telefone.setNumero("91782776");
		telefoneList.add(telefone);

		return telefoneList;
	}

	public List<Documento> insertDocumento(PersistanceActionEnum modelAction)
	{
		List<Documento> documentoList = new ArrayList<Documento>();
		Documento documento = new Documento();
		documento.setModelAction(modelAction);
		documento.setId(1);
		documento.setNumero("111111111000001");
		documentoList.add(documento);

		documento = new Documento();
		documento.setModelAction(modelAction);
		documento.setId(1);
		documento.setNumero("111111111000001");
		documentoList.add(documento);

		documento = new Documento();
		documento.setModelAction(modelAction);
		documento.setId(1);
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
		note.setTabelaEnum(TabelaEnum.FORNECEDOR);
		noteList.add(note);

		note = new Note();
		note.setModelAction(modelAction);
		note.setId(2);
		note.setNoteText("Texto Texto Texto Texto Texto Texto Texto Texto Texto ");
		note.setTabelaEnum(TabelaEnum.FORNECEDOR);
		noteList.add(note);
		noteList.add(note);

		note = new Note();
		note.setModelAction(modelAction);
		note.setId(3);
		note.setNoteText("Texto Texto Texto Texto Texto Texto Texto Texto Texto ");
		note.setTabelaEnum(TabelaEnum.FORNECEDOR);
		noteList.add(note);
		noteList.add(note);

		return noteList;
	}

	public List<Contato> insertContato(PersistanceActionEnum modelAction)
	{
		List<Contato> contatoList = new ArrayList<Contato>();

		ContatoItens contatoItens = new ContatoItens();
		contatoItens.setId(1);
		contatoItens.setNoteList(insertNote(modelAction));

		Date a = new Date();

		Contato contato = new Contato();
		contato.setModelAction(modelAction);
		contato.setId(1);
		a = new Date();
		contato.setDataContato(a.getTime());
		contato.setMotivo(ContatoTypeEnum.COBRANCA);
		contato.setContatoItensList(new ArrayList<ContatoItens>());
		contato.getContatoItensList().add(contatoItens);
		contatoList.add(contato);

		return contatoList;
	}

	public Fornecedor insertMockFornecedor(PersistanceActionEnum modelAction)
	{
		Fornecedor fornecedor = new Fornecedor();

		fornecedor.setId(1);
		fornecedor.setEmprId(1);
		fornecedor.setNome("Damiao jose junior");
		fornecedor.setNomePai("Damiao Jose Pai");
		fornecedor.setNomeMae("Sonia Mae");
		fornecedor.setNomeConjugue("Vinicios Felisberto");
		fornecedor.setModelAction(modelAction);
		fornecedor.setEstadoCivil(1);
		Date a = new Date();
		fornecedor.setDatanasc(a.getTime() - 100000000);
		fornecedor.setPessoaTypeEnum(PessoaTypeEnum.FORNECEDOR);
		fornecedor.setSexo(1);
		fornecedor.setEnderecos(insertEndereco(modelAction));
		fornecedor.setDocumentos(insertDocumento(modelAction));
		fornecedor.setEmails(insertEmail(modelAction));
		fornecedor.setTelefones(insertTelefone(modelAction));
		fornecedor.setNotes(insertNote(modelAction));
		fornecedor.setBancos(new ArrayList<BancoPessoa>());
		fornecedor.getBancos().add(new BancoPessoa(1, PersistanceActionEnum.NONE));
		fornecedor.setContatoList(insertContato(modelAction));
		fornecedor.setListCfops(new ArrayList<CfopPessoa>());
		fornecedor.getListCfops().add(new CfopPessoa(1, PersistanceActionEnum.NONE));
		fornecedor.setListProdutos(new ArrayList<ProdutoPessoa>());
		fornecedor.getListProdutos().add(new ProdutoPessoa(new Produto(1)));
		fornecedor.getListProdutos().add(new ProdutoPessoa(new Produto(2)));
		fornecedor.getListProdutos().add(new ProdutoPessoa(new Produto(3)));
		return fornecedor;

	}
}
