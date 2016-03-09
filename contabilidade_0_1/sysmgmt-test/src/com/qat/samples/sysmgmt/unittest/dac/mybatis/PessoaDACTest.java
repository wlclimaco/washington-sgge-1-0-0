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
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.Funcionario;
import com.qat.samples.sysmgmt.pessoa.PessoaTypeEnum;
import com.qat.samples.sysmgmt.pessoa.dac.IPessoaDAC;

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
	public void testFetchProceduresByRequest() throws Exception
	{
		// check for valid and precount
		FuncionarioInquiryRequest request = new FuncionarioInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Funcionario> response = getPessoaDAC().fetchFuncionarioByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
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
