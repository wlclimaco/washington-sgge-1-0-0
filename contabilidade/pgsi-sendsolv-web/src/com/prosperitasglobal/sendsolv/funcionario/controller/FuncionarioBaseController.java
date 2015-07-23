package com.prosperitasglobal.sendsolv.funcionario.controller;

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
import com.prosperitasglobal.sendsolv.bai.IFuncionarioBAI;
import com.prosperitasglobal.sendsolv.bai.IPessoaBAI;
import com.prosperitasglobal.sendsolv.model.BancoPessoa;
import com.prosperitasglobal.sendsolv.model.BeneficioPessoa;
import com.prosperitasglobal.sendsolv.model.Beneficios;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Contato;
import com.prosperitasglobal.sendsolv.model.ContatoItens;
import com.prosperitasglobal.sendsolv.model.ContatoTypeEnum;
import com.prosperitasglobal.sendsolv.model.Documento;
import com.prosperitasglobal.sendsolv.model.Email;
import com.prosperitasglobal.sendsolv.model.Endereco;
import com.prosperitasglobal.sendsolv.model.Estado;
import com.prosperitasglobal.sendsolv.model.EventoPessoa;
import com.prosperitasglobal.sendsolv.model.Eventos;
import com.prosperitasglobal.sendsolv.model.Funcionario;
import com.prosperitasglobal.sendsolv.model.HorarioFunc;
import com.prosperitasglobal.sendsolv.model.PessoaTypeEnum;
import com.prosperitasglobal.sendsolv.model.Profissao;
import com.prosperitasglobal.sendsolv.model.Salario;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.Telefone;
import com.prosperitasglobal.sendsolv.model.request.BeneficiosInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ConvenioInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EventoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.HoraFuncInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.BeneficiosResponse;
import com.prosperitasglobal.sendsolv.model.response.ConvenioResponse;
import com.prosperitasglobal.sendsolv.model.response.EventoResponse;
import com.prosperitasglobal.sendsolv.model.response.FuncionarioResponse;
import com.prosperitasglobal.sendsolv.model.response.HorarioFuncResponse;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class FuncionarioBaseController.
 */

/**
 * @author Flavio Tosta.
 *
 */
public class FuncionarioBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FuncionarioBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "FuncionarioBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Funcionario BAI. */
	private IPessoaBAI pessoaBAI;

	private IFuncionarioBAI funcionarioBAI;

	/**
	 * @return the pessoaBAI
	 */
	public IPessoaBAI getPessoaBAI()
	{
		return pessoaBAI;
	}

	/**
	 * @param pessoaBAI the pessoaBAI to set
	 */
	@Resource
	public void setPessoaBAI(IPessoaBAI pessoaBAI)
	{
		this.pessoaBAI = pessoaBAI;
	}

	/**
	 * @param funcionarioBAI the funcionarioBAI to set
	 */
	@Resource
	public void setFuncionarioBAI(IFuncionarioBAI funcionarioBAI)
	{
		this.funcionarioBAI = funcionarioBAI;
	}

	/**
	 * @return the funcionarioBAI
	 */
	public IFuncionarioBAI getFuncionarioBAI()
	{
		return funcionarioBAI;
	}

	/**
	 * Funcionario edit mav.
	 *
	 * @param locationId the location id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView locationEditMAV(Integer locationId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(locationId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchFuncionarioById(new FetchByIdRequest(locationId))));

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
	 * Fetch location by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the location response
	 */
	public FuncionarioResponse fetchFuncionarioByRequest(FuncionarioInquiryRequest pagedInquiryRequest)
	{

		FuncionarioResponse locationResponse = new FuncionarioResponse();
		try
		{

			locationResponse = getFuncionarioBAI().fetchFuncionarioByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	// Beneficios
	public BeneficiosResponse fetchBeneficiosByRequest(BeneficiosInquiryRequest pagedInquiryRequest)
	{

		BeneficiosResponse locationResponse = new BeneficiosResponse();
		try
		{

			locationResponse = getFuncionarioBAI().fetchBeneficiosByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	// convenio
	public ConvenioResponse fetchConvenioByRequest(ConvenioInquiryRequest pagedInquiryRequest)
	{

		ConvenioResponse locationResponse = new ConvenioResponse();
		try
		{

			locationResponse = getPessoaBAI().fetchConvenioByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	// eventos
	public EventoResponse fetchEventoByRequest(EventoInquiryRequest pagedInquiryRequest)
	{

		EventoResponse locationResponse = new EventoResponse();
		try
		{

			locationResponse = getFuncionarioBAI().fetchEventoByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	// pontoFunc
	public HorarioFuncResponse fetchHorarioFuncByRequest(HoraFuncInquiryRequest pagedInquiryRequest)
	{

		HorarioFuncResponse locationResponse = new HorarioFuncResponse();
		try
		{

			locationResponse = getPessoaBAI().fetchHorarioFuncsRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	/**
	 * Fetch location by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the location response
	 */
	public FuncionarioResponse fetchFuncionarioById(FetchByIdRequest fetchByIdRequest)
	{

		FuncionarioResponse locationResponse = new FuncionarioResponse();
		try
		{

			locationResponse = getFuncionarioBAI().fetchFuncionarioById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	public FuncionarioResponse insert(FuncionarioMaintenanceRequest locationRequest)
	{
		FuncionarioResponse locationResponse = new FuncionarioResponse();

		try
		{
			locationRequest.setFuncionario(insertMockCliente(PersistanceActionEnum.INSERT));
			locationRequest.getFuncionario().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			locationResponse = getFuncionarioBAI().insertFuncionario(locationRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

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
		telefone.setNumero("91782776");
		telefone.setDescricao("Casa");
		telefoneList.add(telefone);

		telefone = new Telefone();
		telefone.setModelAction(modelAction);
		telefone.setId(1);
		telefone.setDdd("34");
		telefone.setNumero("91782776");
		telefone.setDescricao("Trabalho");
		telefoneList.add(telefone);

		telefone = new Telefone();
		telefone.setModelAction(modelAction);
		telefone.setId(1);
		telefone.setDdd("34");
		telefone.setNumero("91782776");
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
		note.setTabelaEnum(TabelaEnum.FUNCIONARIO);
		noteList.add(note);

		note = new Note();
		note.setModelAction(modelAction);
		note.setId(2);
		note.setNoteText("Texto Texto Texto Texto Texto Texto Texto Texto Texto ");
		note.setTabelaEnum(TabelaEnum.FUNCIONARIO);
		noteList.add(note);
		noteList.add(note);

		note = new Note();
		note.setModelAction(modelAction);
		note.setId(3);
		note.setNoteText("Texto Texto Texto Texto Texto Texto Texto Texto Texto ");
		note.setTabelaEnum(TabelaEnum.FUNCIONARIO);
		noteList.add(note);
		noteList.add(note);

		return noteList;
	}

	public List<Contato> insertContato(PersistanceActionEnum modelAction)
	{
		List<Contato> contatoList = new ArrayList<Contato>();

		ContatoItens contatoItens = new ContatoItens();
		contatoItens.setId(21);
		contatoItens.setNoteList(insertNote(modelAction));
		Date a = new Date();

		Contato contato = new Contato();
		contato.setModelAction(modelAction);
		contato.setId(1);
		a = new Date();
		contato.setMotivo(ContatoTypeEnum.COBRANCA);
		contato.setDataContato(a.getTime());
		contato.setContatoItensList(new ArrayList<ContatoItens>());
		contato.getContatoItensList().add(contatoItens);
		contatoList.add(contato);

		return contatoList;
	}

	public List<Salario> insertSalario(PersistanceActionEnum modelAction)
	{
		List<Salario> salarioList = new ArrayList<Salario>();
		Date a = new Date();
		Salario salario = new Salario(1, a.getTime(), new Double(1000.99));
		salarioList.add(salario);
		salario = new Salario(1, a.getTime(), new Double(1000.99));
		salarioList.add(salario);
		salario = new Salario(1, a.getTime(), new Double(1000.99));
		salarioList.add(salario);
		salario = new Salario(1, a.getTime(), new Double(1000.99));
		salarioList.add(salario);

		return salarioList;
	}

	public List<HorarioFunc> insertHorarioFunc(PersistanceActionEnum modelAction)
	{
		List<HorarioFunc> horarioFuncList = new ArrayList<HorarioFunc>();
		for (Integer i = 0; i < 30; i++)
		{
			HorarioFunc horaFunc = new HorarioFunc();
			horaFunc.setId(i);
			Date a = new Date();
			horaFunc.setData(a.getTime() - (i * 1000000));
			horaFunc.setHorarioEntr(a.getTime() - (i * 100000));
			horaFunc.setHorarioSair(a.getTime() - (i * 10000));
			horaFunc.setTipo("Entrada");
			horaFunc.setModelAction(modelAction);
			horarioFuncList.add(horaFunc);

		}
		return horarioFuncList;
	}

	public List<BeneficioPessoa> insertBeneficios(PersistanceActionEnum modelAction)
	{
		List<BeneficioPessoa> beneficioList = new ArrayList<BeneficioPessoa>();
		Date a = new Date();
		BeneficioPessoa beneficio = new BeneficioPessoa();

		beneficio.setId(1);
		// beneficio.setDataList(new ArrayList<BeneficioMesApp>());
		// beneficio.getDataList().add(new BeneficioMesApp());
		beneficio.setParentId(1);
		beneficio.setBenefId(new Beneficios(1));
		beneficio.setModelAction(PersistanceActionEnum.NONE);
		beneficioList.add(beneficio);

		beneficio = new BeneficioPessoa();
		beneficio.setModelAction(PersistanceActionEnum.NONE);
		beneficio.setId(1);
		beneficio.setBenefId(new Beneficios(2));
		beneficioList.add(beneficio);

		return beneficioList;
	}

	public List<EventoPessoa> insertEventos(PersistanceActionEnum modelAction)
	{
		List<EventoPessoa> eventosList = new ArrayList<EventoPessoa>();
		Date a = new Date();
		EventoPessoa evento = new EventoPessoa();
		evento.setId(1);

		evento.setParentId(1);
		evento.setIdEvent(new Eventos(1));
		evento.setModelAction(PersistanceActionEnum.NONE);
		eventosList.add(evento);

		evento = new EventoPessoa();
		evento.setIdEvent(new Eventos(2));
		evento.setModelAction(PersistanceActionEnum.NONE);
		evento.setId(1);
		eventosList.add(evento);

		return eventosList;
	}

	public Funcionario insertMockCliente(PersistanceActionEnum modelAction)
	{
		Funcionario funcionario = new Funcionario();

		funcionario.setId(1);
		funcionario.setEmprId(1);
		funcionario.setModelAction(modelAction);
		funcionario.setNome("Damiao jose junior");
		funcionario.setNomePai("Damiao Jose Pai");
		funcionario.setNomeMae("Sonia Mae");
		funcionario.setNomeConjugue("Vinicios Felisberto");
		funcionario.setMatricula("8426");
		funcionario.setEstadoCivil(1);
		funcionario.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		Date a = new Date();
		funcionario.setDatanasc(a.getTime() - 100000000);
		funcionario.setDataAdm(a.getTime() - 200000000);
		funcionario.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		funcionario.setSexo(1);
		funcionario.setEnderecos(insertEndereco(modelAction));
		funcionario.setDocumentos(insertDocumento(modelAction));
		funcionario.setEmails(insertEmail(modelAction));
		funcionario.setTelefones(insertTelefone(modelAction));
		funcionario.setNotes(insertNote(modelAction));
		funcionario.setBancos(new ArrayList<BancoPessoa>());
		funcionario.getBancos().add(new BancoPessoa(1, PersistanceActionEnum.NONE));
		funcionario.getBancos().add(new BancoPessoa(2, PersistanceActionEnum.NONE));
		funcionario.setSalarios(insertSalario(modelAction));
		funcionario.setHorarios(insertHorarioFunc(modelAction));
		funcionario.setBeneficios(insertBeneficios(modelAction));
		funcionario.setEventosList(insertEventos(modelAction));

		return funcionario;

	}
}
