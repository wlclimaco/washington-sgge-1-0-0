package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.agencia.Agencia;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.banco.Banco;
import com.qat.samples.sysmgmt.banco.BancoPessoa;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.BeneficioPessoa;
import com.qat.samples.sysmgmt.beneficios.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.cfop.CfopPessoa;
import com.qat.samples.sysmgmt.condpag.CondPagPessoa;
import com.qat.samples.sysmgmt.condpag.FormaPg;
import com.qat.samples.sysmgmt.condpag.FormaPgPessoa;
import com.qat.samples.sysmgmt.condpag.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.conta.Conta;
import com.qat.samples.sysmgmt.contato.Contato;
import com.qat.samples.sysmgmt.convenio.Convenio;
import com.qat.samples.sysmgmt.convenio.ConvenioPessoa;
import com.qat.samples.sysmgmt.dp.EventoPessoa;
import com.qat.samples.sysmgmt.dp.Eventos;
import com.qat.samples.sysmgmt.dp.HorarioFunc;
import com.qat.samples.sysmgmt.dp.Profissao;
import com.qat.samples.sysmgmt.dp.Salario;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.ProfissaoInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.Cliente;
import com.qat.samples.sysmgmt.pessoa.Contador;
import com.qat.samples.sysmgmt.pessoa.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.Funcionario;
import com.qat.samples.sysmgmt.pessoa.PessoaTypeEnum;
import com.qat.samples.sysmgmt.pessoa.Transportador;
import com.qat.samples.sysmgmt.pessoa.dac.IPessoaDAC;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContaInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.ProdutoPessoa;
import com.qat.samples.sysmgmt.util.Documento;
import com.qat.samples.sysmgmt.util.Email;
import com.qat.samples.sysmgmt.util.Endereco;
import com.qat.samples.sysmgmt.util.Note;
import com.qat.samples.sysmgmt.util.Telefone;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class PessoaDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(PessoaDACTest.class);
	private IPessoaDAC pessoaDAC; // injected by Spring through setter @resource

	// below

	public IPessoaDAC getPessoaDAC()
	{
		return pessoaDAC;
	}

	@Resource
	public void setPessoaDAC(IPessoaDAC pessoaDAC)
	{
		this.pessoaDAC = pessoaDAC;
	}

	@Test
	public void testInsertFunction() throws Exception
	{

		Funcionario funcionario = new Funcionario();
		funcionario = insertFuncionario(PersistanceActionEnum.INSERT);
		Date a = new Date();

		InternalResultsResponse<Funcionario> funcionarioResponse = getPessoaDAC().insertFuncionario(funcionario);
		assertEquals(funcionarioResponse, null);
		getPessoaDAC().insertFuncionario(funcionario);

	}

	@Test
	public void testInsertClientes() throws Exception
	{

		Cliente funcionario = new Cliente();
		funcionario = insertCliente(PersistanceActionEnum.INSERT);
		Date a = new Date();

		InternalResultsResponse<Cliente> funcionarioResponse = getPessoaDAC().insertCliente(funcionario);
		assertEquals(funcionarioResponse, null);
		getPessoaDAC().insertCliente(funcionario);

	}

	@Test
	public void testFetchFuncionariosByRequest() throws Exception
	{
		// check for valid and precount
		FuncionarioInquiryRequest request = new FuncionarioInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Funcionario> response = getPessoaDAC().fetchFuncionarioByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchClientesByRequest() throws Exception
	{
		// check for valid and precount
		ClienteInquiryRequest request = new ClienteInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Cliente> response = getPessoaDAC().fetchClienteByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchFornecedorsByRequest() throws Exception
	{
		// check for valid and precount
		FornecedorInquiryRequest request = new FornecedorInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Fornecedor> response = getPessoaDAC().fetchFornecedorByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchTransportadorsByRequest() throws Exception
	{
		// check for valid and precount
		TransportadorInquiryRequest request = new TransportadorInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Transportador> response = getPessoaDAC().fetchTransportadorByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchContadorsByRequest() throws Exception
	{
		// check for valid and precount
		ContadorInquiryRequest request = new ContadorInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Contador> response = getPessoaDAC().fetchContadorByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchAgenciaByRequest() throws Exception
	{
		// check for valid and precount
		AgenciaInquiryRequest request = new AgenciaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Agencia> response = getPessoaDAC().fetchAgenciaByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchBancoByRequest() throws Exception
	{
		// check for valid and precount
		BancoInquiryRequest request = new BancoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Banco> response = getPessoaDAC().fetchBancoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchBeneficiosRequest() throws Exception
	{
		// check for valid and precount
		BeneficiosInquiryRequest request = new BeneficiosInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Beneficios> response = getPessoaDAC().fetchBeneficiosRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchContaByRequest() throws Exception
	{
		// check for valid and precount
		ContaInquiryRequest request = new ContaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Conta> response = getPessoaDAC().fetchContaByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchConveniosByRequest() throws Exception
	{
		// check for valid and precount
		ConvenioInquiryRequest request = new ConvenioInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Convenio> response = getPessoaDAC().fetchConvenioByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchEventosByRequest() throws Exception
	{
		// check for valid and precount
		EventoInquiryRequest request = new EventoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Eventos> response = getPessoaDAC().fetchEventosRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchFormaPgByRequest() throws Exception
	{
		// check for valid and precount
		FormaPgInquiryRequest request = new FormaPgInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<FormaPg> response = getPessoaDAC().fetchFormaPgByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchHorarioFuncsByRequest() throws Exception
	{
		// check for valid and precount
		HoraFuncInquiryRequest request = new HoraFuncInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<HorarioFunc> response = getPessoaDAC().fetchHorarioFuncsRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchProfissaoByRequest() throws Exception
	{
		// check for valid and precount
		ProfissaoInquiryRequest request = new ProfissaoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Profissao> response = getPessoaDAC().fetchProfissaoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertPessoa.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertBanco.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertBeneficios.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertCondPag.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertHorario.sql", false);
	}

	public List<Endereco> enderecoList(PersistanceActionEnum action)
	{
		List<Endereco> enderecoList = new ArrayList<Endereco>();
		Endereco endereco = new Endereco();
		endereco.setModelAction(action);

		enderecoList.add(endereco);
		return enderecoList;

	}

	public List<Documento> documentoList(PersistanceActionEnum action)
	{
		List<Documento> documentoList = new ArrayList<Documento>();
		Documento documento = new Documento();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<Email> emailList(PersistanceActionEnum action)
	{
		List<Email> documentoList = new ArrayList<Email>();
		Email documento = new Email();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<Telefone> telefoneList(PersistanceActionEnum action)
	{
		List<Telefone> documentoList = new ArrayList<Telefone>();
		Telefone documento = new Telefone();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<BancoPessoa> bancoList(PersistanceActionEnum action)
	{
		List<BancoPessoa> documentoList = new ArrayList<BancoPessoa>();
		BancoPessoa documento = new BancoPessoa();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<FormaPgPessoa> formaPagamentoList(PersistanceActionEnum action)
	{
		List<FormaPgPessoa> documentoList = new ArrayList<FormaPgPessoa>();
		FormaPgPessoa documento = new FormaPgPessoa();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<CondPagPessoa> condPagList(PersistanceActionEnum action)
	{
		List<CondPagPessoa> documentoList = new ArrayList<CondPagPessoa>();
		CondPagPessoa documento = new CondPagPessoa();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<Contato> contatoList(PersistanceActionEnum action)
	{
		List<Contato> documentoList = new ArrayList<Contato>();
		Contato documento = new Contato();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<Salario> salarioList(PersistanceActionEnum action)
	{
		List<Salario> salarioList = new ArrayList<Salario>();
		Salario salario = new Salario();
		salario.setModelAction(action);

		salarioList.add(salario);
		return salarioList;

	}

	public List<HorarioFunc> horarioList(PersistanceActionEnum action)
	{
		List<HorarioFunc> documentoList = new ArrayList<HorarioFunc>();
		HorarioFunc documento = new HorarioFunc();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<BeneficioPessoa> beneficioList(PersistanceActionEnum action)
	{
		List<BeneficioPessoa> documentoList = new ArrayList<BeneficioPessoa>();
		BeneficioPessoa documento = new BeneficioPessoa();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<EventoPessoa> eventosList(PersistanceActionEnum action)
	{
		List<EventoPessoa> documentoList = new ArrayList<EventoPessoa>();
		EventoPessoa documento = new EventoPessoa();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<Profissao> profissaoList(PersistanceActionEnum action)
	{
		List<Profissao> documentoList = new ArrayList<Profissao>();
		Profissao documento = new Profissao();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<ConvenioPessoa> convenioList(PersistanceActionEnum action)
	{
		List<ConvenioPessoa> documentoList = new ArrayList<ConvenioPessoa>();
		ConvenioPessoa documento = new ConvenioPessoa();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<CfopPessoa> cfopList(PersistanceActionEnum action)
	{
		List<CfopPessoa> documentoList = new ArrayList<CfopPessoa>();
		CfopPessoa documento = new CfopPessoa();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<ProdutoPessoa> produtoList(PersistanceActionEnum action)
	{
		List<ProdutoPessoa> documentoList = new ArrayList<ProdutoPessoa>();
		ProdutoPessoa documento = new ProdutoPessoa();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<Note> noteList(PersistanceActionEnum action)
	{
		List<Note> documentoList = new ArrayList<Note>();
		Note documento = new Note();
		documento.setModelAction(action);

		documentoList.add(documento);
		return documentoList;

	}

	public Funcionario insertFuncionario(PersistanceActionEnum action)
	{
		Funcionario pessoa = new Funcionario();
		Date a = new Date();
		pessoa.setMatricula("matricula");
		pessoa.setDataAdm(a.getTime());
		pessoa.setNome("TESTE0001");
		pessoa.setNomePai("nomePai");
		pessoa.setEmprId(9);
		pessoa.setNomeMae("nomeMae");
		pessoa.setNomeConjugue("nomeConjugue");
		pessoa.setEstadoCivil(0);
		pessoa.setTipoPessoa(1);
		pessoa.setDatanasc(a.getTime());
		pessoa.setFoto("foto");
		pessoa.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		pessoa.setSexo(1);
		pessoa.setModelAction(action);
		pessoa.setDocumentos(documentoList(action));
		pessoa.setTelefones(telefoneList(action));
		pessoa.setEmails(emailList(action));
		pessoa.setEnderecos(enderecoList(action));
		pessoa.setBancos(bancoList(action));
		pessoa.setNotes(noteList(action));
		pessoa.setContatoList(contatoList(action));
		pessoa.setFormaPagamentoList(formaPagamentoList(action));
		pessoa.setCondPagList(condPagList(action));

		return pessoa;
	}

	public Cliente insertCliente(PersistanceActionEnum action)
	{
		Cliente pessoa = new Cliente();
		Date a = new Date();
		pessoa.setNome("TESTE0001");
		pessoa.setNomePai("nomePai");
		pessoa.setEmprId(9);
		pessoa.setNomeMae("nomeMae");
		pessoa.setNomeConjugue("nomeConjugue");
		pessoa.setEstadoCivil(0);
		pessoa.setTipoPessoa(1);
		pessoa.setDatanasc(a.getTime());
		pessoa.setFoto("foto");
		pessoa.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		pessoa.setSexo(1);
		pessoa.setModelAction(action);
		pessoa.setDocumentos(documentoList(action));
		pessoa.setTelefones(telefoneList(action));
		pessoa.setEmails(emailList(action));
		pessoa.setEnderecos(enderecoList(action));
		pessoa.setBancos(bancoList(action));
		pessoa.setNotes(noteList(action));
		pessoa.setContatoList(contatoList(action));
		pessoa.setFormaPagamentoList(formaPagamentoList(action));
		pessoa.setCondPagList(condPagList(action));

		return pessoa;
	}

	public Fornecedor insertFornecedor(PersistanceActionEnum action)
	{
		Fornecedor pessoa = new Fornecedor();
		Date a = new Date();
		pessoa.setNome("TESTE0001");
		pessoa.setNomePai("nomePai");
		pessoa.setEmprId(9);
		pessoa.setNomeMae("nomeMae");
		pessoa.setNomeConjugue("nomeConjugue");
		pessoa.setEstadoCivil(0);
		pessoa.setTipoPessoa(1);
		pessoa.setDatanasc(a.getTime());
		pessoa.setFoto("foto");
		pessoa.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		pessoa.setSexo(1);
		pessoa.setModelAction(action);
		pessoa.setDocumentos(documentoList(action));
		pessoa.setTelefones(telefoneList(action));
		pessoa.setEmails(emailList(action));
		pessoa.setEnderecos(enderecoList(action));
		pessoa.setBancos(bancoList(action));
		pessoa.setNotes(noteList(action));
		pessoa.setContatoList(contatoList(action));
		pessoa.setFormaPagamentoList(formaPagamentoList(action));
		pessoa.setCondPagList(condPagList(action));

		return pessoa;
	}

	public Transportador insertTransportador(PersistanceActionEnum action)
	{
		Transportador pessoa = new Transportador();
		Date a = new Date();
		pessoa.setNome("TESTE0001");
		pessoa.setNomePai("nomePai");
		pessoa.setEmprId(9);
		pessoa.setNomeMae("nomeMae");
		pessoa.setNomeConjugue("nomeConjugue");
		pessoa.setEstadoCivil(0);
		pessoa.setTipoPessoa(1);
		pessoa.setDatanasc(a.getTime());
		pessoa.setFoto("foto");
		pessoa.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		pessoa.setSexo(1);
		pessoa.setModelAction(action);
		pessoa.setDocumentos(documentoList(action));
		pessoa.setTelefones(telefoneList(action));
		pessoa.setEmails(emailList(action));
		pessoa.setEnderecos(enderecoList(action));
		pessoa.setBancos(bancoList(action));
		pessoa.setNotes(noteList(action));
		pessoa.setContatoList(contatoList(action));
		pessoa.setFormaPagamentoList(formaPagamentoList(action));
		pessoa.setCondPagList(condPagList(action));

		return pessoa;
	}

	public Contador insertContador(PersistanceActionEnum action)
	{
		Contador pessoa = new Contador();
		Date a = new Date();
		pessoa.setNome("TESTE0001");
		pessoa.setNomePai("nomePai");
		pessoa.setEmprId(9);
		pessoa.setNomeMae("nomeMae");
		pessoa.setNomeConjugue("nomeConjugue");
		pessoa.setEstadoCivil(0);
		pessoa.setTipoPessoa(1);
		pessoa.setDatanasc(a.getTime());
		pessoa.setFoto("foto");
		pessoa.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		pessoa.setSexo(1);
		pessoa.setModelAction(action);
		pessoa.setDocumentos(documentoList(action));
		pessoa.setTelefones(telefoneList(action));
		pessoa.setEmails(emailList(action));
		pessoa.setEnderecos(enderecoList(action));
		pessoa.setBancos(bancoList(action));
		pessoa.setNotes(noteList(action));
		pessoa.setContatoList(contatoList(action));
		pessoa.setFormaPagamentoList(formaPagamentoList(action));
		pessoa.setCondPagList(condPagList(action));

		return pessoa;
	}
}
