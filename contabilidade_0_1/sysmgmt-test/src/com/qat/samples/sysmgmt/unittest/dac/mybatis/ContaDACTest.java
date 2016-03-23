package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ContaDACTest extends AbstractTransactionalJUnit4SpringContextTests
{
	//
	// private static final Logger LOG = LoggerFactory.getLogger(ContaDACTest.class);
	// private IContaDAC contaDAC; // injected by Spring through setter @resource
	//
	// // below
	//
	// public IContaDAC getContaDAC()
	// {
	// return contaDAC;
	// }
	//
	// @Resource
	// public void setContaDAC(IContaDAC contaDAC)
	// {
	// this.contaDAC = contaDAC;
	// }
	//
	// @Test
	// public void testupdateConta() throws Exception
	// {
	//
	// Conta funcionario = new Conta();
	// funcionario = insertConta(PersistanceActionEnum.INSERT);
	// InternalResultsResponse<Conta> response = new InternalResultsResponse<Conta>();
	// Integer a = getEntidadeDAC().insertConta(funcionario,"", response);
	//
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// funcionario = funcionarioResponse.getFirstResult();
	// funcionario.setModelAction(PersistanceActionEnum.UPDATE);
	// funcionario.setId(funcionarioResponse.getFirstResult().getId());
	// response = new InternalResultsResponse<Conta>();
	//
	// a = getEntidadeDAC().updateConta(funcionario, response);
	// assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
	//
	// }
	//
	// @Test
	// public void testinsertConta() throws Exception
	// {
	//
	// Conta funcionario = new Conta();
	// funcionario = insertConta(PersistanceActionEnum.INSERT);
	//
	// InternalResultsResponse<Conta> response = new InternalResultsResponse<Conta>();
	//
	// Integer a = getContaDAC().insertConta(funcionario, "INSERT", response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	//
	//
	// Conta funcionario = new Conta();
	// funcionario = insertConta(PersistanceActionEnum.INSERT);
	// InternalResultsResponse<Conta> response = new InternalResultsResponse<Conta>();
	//
	// Integer a = getEntidadeDAC().insertConta(funcionario, response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// // FetchByIdRequest request = new FetchByIdRequest();
	// // request.setFetchId(response.getFirstResult().getId());
	// InternalResultsResponse<Conta> responseA = getEntidadeDAC().fetchContaById(response.getFirstResult().getId());
	// assertTrue(responseA.getResultsList().size() == 1);
	// assertEquals(responseA.getStatus(), Status.OperationSuccess);
	//
	//
	// }
	//
	// @Test
	// public void testdeleteConta() throws Exception
	// {
	//
	// Conta funcionario = new Conta();
	// funcionario = insertConta(PersistanceActionEnum.INSERT);
	// InternalResultsResponse<Conta> response = new InternalResultsResponse<Conta>();
	// Integer a = getEntidadeDAC().insertConta(funcionario,response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// funcionario = response.getFirstResult();
	// response = new InternalResultsResponse<Conta>();
	// funcionario.setModelAction(PersistanceActionEnum.DELETE);
	// Integer b = getEntidadeDAC().deleteConta(funcionario,response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// //FetchByIdRequest request = new FetchByIdRequest();
	// // request.setFetchId(funcionarioResponse.getFirstResult().getId());
	// InternalResultsResponse<Classicacao> responseA =
	// getEntidadeDAC().fetchContaById(funcionarioResponse.getFirstResult().getId());
	// assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);
	//
	// }
	//
	// @Test
	// public void testfetchContaById() throws Exception
	// {
	// // check for valid and precount
	// FetchByIdRequest request = new FetchByIdRequest();
	// request.setFetchId(3);
	// InternalResultsResponse<Conta> response = getContaDAC().fetchContaById(request);
	// assertTrue(response.getResultsSetInfo().getPageSize() == 1);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// }
	//
	// @Test
	// public void testfetchContaById2() throws Exception
	// {
	// // check for valid and precount
	// FetchByIdRequest request = new FetchByIdRequest();
	// request.setFetchId(3);
	// InternalResultsResponse<Conta> response = getContaDAC().fetchContaById(1);
	// assertTrue(response.getResultsSetInfo().getPageSize() == 1);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// }
	//
	// @Test
	// public void testfetchContaByRequest() throws Exception
	// {
	// // check for valid and precount
	// ContaInquiryRequest request = new ContaInquiryRequest();
	// request.setPreQueryCount(true);
	// request.setStartPage(0);
	// request.setPageSize(4);
	// InternalResultsResponse<Conta> response = getContaDAC().fetchContaByRequest(request);
	// assertTrue(response.getResultsSetInfo().getPageSize() == 4);
	// assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	// }
	//
	// public Conta insertConta(PersistanceActionEnum action)
	// {
	// Conta exame = new Conta();
	// Date a = new Date();
	// exame.setId(1);
	// exame.setModelAction(action);
	// // exame.setNome("Nome");
	// // exame.setDataConta((int)a.getTime());
	// // exame.setMedicoResponsavel("Resposnsavel");
	// // exame.setLaboratorio("Laboratorio");
	//
	// return exame;
	// }
	//
	// @Before
	// public void setup()
	// {
	// executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertBanco.sql", false);
	// }
}
