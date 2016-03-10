package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

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
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.condpag.FormaPg;
import com.qat.samples.sysmgmt.condpag.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.conta.Conta;
import com.qat.samples.sysmgmt.convenio.Convenio;
import com.qat.samples.sysmgmt.dp.Eventos;
import com.qat.samples.sysmgmt.dp.HorarioFunc;
import com.qat.samples.sysmgmt.dp.Profissao;
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

		Date a = new Date();
		funcionario.setNome("TESTE0001");
		funcionario.setNomePai("nomePai");
		funcionario.setEmprId(9);
		funcionario.setNomeMae("nomeMae");
		funcionario.setNomeConjugue("nomeConjugue");
		funcionario.setEstadoCivil(0);
		funcionario.setTipoPessoa(1);
		funcionario.setDatanasc(a.getTime());
		funcionario.setFoto("foto");
		funcionario.setMatricula("matricula");
		funcionario.setDataAdm(a.getTime());
		funcionario.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		funcionario.setSexo(1);
		funcionario.setModelAction(PersistanceActionEnum.INSERT);
		// funcionario.setDocumentos:[{
		// documentoTypeEnumValue:1,
		// numero : "123456789",
		// data : qat.pages.pessoa.nowDate(),
		// modelAction : "INSERT",
		// estado :{id:1}
		// }],
		// enderecos:[{
		// logradouro : "Logradouro",
		// cep :"CEP",
		// bairro : "BAIRRO",
		// numero : "NUMERO",
		// complemento :"COMPLEMENTO",
		// enderecoTypeValue : 1,
		// modelAction : "INSERT",
		// cidade : {id:1},
		// }],
		// emails : [{email : "email001@gmail.com",modelAction : "INSERT",emailTypeEnumValue:1},{email :
		// "email002@gmail.com",modelAction : "INSERT",emailTypeEnumValue:2},{email : "email003@gmail.com",modelAction :
		// "INSERT",emailTypeEnumValue:3}],
		// telefones:[{ddd :"034",numero:"3315-8065",modelAction : "INSERT",telefoneTypeEnumValue : 1},{ddd
		// :"034",numero:"3315-8065",modelAction : "INSERT",telefoneTypeEnumValue : 2}],
		// bancos:[{
		// id:0,
		// modelAction : "INSERT",
		// numCont:"123456789",
		// saldo:1.99,
		// bancoId :{
		// id:0,
		// modelAction : "INSERT",
		// nome:"BANCO DO BRASIL",
		// },
		// agenciaId:{
		// id:0,
		// modelAction : "INSERT",
		// nome:"AGENCIA NOME",
		// enderecos:[{
		// logradouro : "Logradouro",
		// cep :"CEP",
		// bairro : "BAIRRO",
		// numero : "NUMERO",
		// complemento :"COMPLEMENTO",
		// enderecoTypeValue : 1,
		// modelAction : "INSERT",
		// cidade : {id:1},
		// }],
		// emails : [{email : "email001@gmail.com",modelAction : "INSERT",emailTypeEnumValue:1},{email :
		// "email002@gmail.com",modelAction : "INSERT",emailTypeEnumValue:2},{email : "email003@gmail.com",modelAction :
		// "INSERT",emailTypeEnumValue:3}],
		// telefones:[{ddd :"034",numero:"3315-8065",modelAction : "INSERT",telefoneTypeEnumValue : 1},{ddd
		// :"034",numero:"3315-8065",modelAction : "INSERT",telefoneTypeEnumValue : 2}],
		// gerente:"gerento",
		// responsavelConta:"responsavelConta",
		// numeroConta:"123456"
		// }
		// }],
		// formaPagamentoList:[{}],
		// condPagList:[{}],
		// contatoList:[{}],
		// salarios:[{
		// id :0,
		// data:qat.pages.pessoa.nowDate(),
		// valor:1999.99,
		// modelAction : "INSERT",
		// profissao : {
		// modelAction : "INSERT"
		// }
		// }],
		//
		// horarios:[{}],
		// beneficios:{
		// id:0,
		// benefId : {
		// id : 0,
		// nome : "VALE TRANSPORTE",
		// codigo : "0001",
		// descricao :"DESCRICAO",
		// valor :1.99,
		// porcentagem: 10,
		// tipo: "1" ,
		// createUser:"system",
		// createDateUTC:qat.pages.pessoa.nowDate(),
		// modifyUser:"",
		// modifyDateUTC:0,
		// modelAction: "INSERT"
		// },
		// idFunc : 1,
		// createUser : "system",
		// createDateUTC:qat.pages.pessoa.nowDate(),
		// modifyUser:"",
		// modifyDateUTC:0,
		// modelAction:"INSERT"
		// },
		// eventosList:[{
		// id:0,
		// idEvent : {
		//
		// isMensal : true,
		// isSistema : true,
		// noteText:"NOTE TEXT",
		// id : 0,
		// nome : "VALE TRANSPORTE",
		// codigo : "0001",
		// descricao :"DESCRICAO",
		// valor :1.99,
		// porcentagem: 10,
		// tipo: "1" ,
		// createUser:"system",
		// createDateUTC:qat.pages.pessoa.nowDate(),
		// modifyUser:"",
		// modifyDateUTC:0,
		// modelAction: "INSERT"
		// },
		// idFunc : 1,
		// createUser : "system",
		// createDateUTC:qat.pages.pessoa.nowDate(),
		// modifyUser:"",
		// modifyDateUTC:0,
		// modelAction:"INSERT"
		// }]
		// }
		// Drug drug = new Drug("T123456", "1");
		// DrugPrice drugPrice = new DrugPrice("T123456", "F", new BigDecimal(10.00), new Date());
		// drug.setDrugPrices(Arrays.asList(drugPrice));

		InternalResultsResponse<Funcionario> funcionarioResponse = getPessoaDAC().insertFuncionario(funcionario);
		assertEquals(funcionarioResponse, null);
		getPessoaDAC().insertFuncionario(funcionario);

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
	}
}
