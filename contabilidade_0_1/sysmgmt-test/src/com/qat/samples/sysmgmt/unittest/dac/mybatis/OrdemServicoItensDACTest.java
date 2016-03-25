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
public class OrdemServicoItensDACTest extends AbstractTransactionalJUnit4SpringContextTests
{
	//
	//
	// private static final Logger LOG = LoggerFactory.getLogger(OrdemServicoItensDACTest.class);
	// private IOrdemServicoItensDAC ordemServicoItensDAC; // injected by Spring through setter @resource
	//
	// // below
	//
	// public IOrdemServicoItensDAC getOrdemServicoItensDAC()
	// {
	// return ordemServicoItensDAC;
	// }
	//
	// @Resource
	// public void setOrdemServicoItensDAC(IOrdemServicoItensDAC ordemServicoItensDAC)
	// {
	// this.ordemServicoItensDAC = ordemServicoItensDAC;
	// }
	//
	// @Test
	// public void testupdateOrdemServicoItens() throws Exception
	// {
	//
	// OrdemServicoItens funcionario = new OrdemServicoItens();
	// funcionario = insertOrdemServicoItens(PersistanceActionEnum.INSERT);
	// InternalResultsResponse<OrdemServicoItens> response = new InternalResultsResponse<OrdemServicoItens>();
	// Integer a = getOrdemServicoItensDAC().insertOrdemServicoItens(funcionario,"", response);
	//
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// funcionario = response.getFirstResult();
	// funcionario.setModelAction(PersistanceActionEnum.UPDATE);
	// funcionario.setId(response.getFirstResult().getId());
	// response = new InternalResultsResponse<OrdemServicoItens>();
	//
	// a = getOrdemServicoItensDAC().updateOrdemServicoItens(funcionario, response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	//
	// }
	//
	// @Test
	// public void testinsertOrdemServicoItens() throws Exception
	// {
	//
	// OrdemServicoItens funcionario = new OrdemServicoItens();
	// funcionario = insertOrdemServicoItens(PersistanceActionEnum.INSERT);
	//
	// InternalResultsResponse<OrdemServicoItens> response = new InternalResultsResponse<OrdemServicoItens>();
	//
	// Integer a = getOrdemServicoItensDAC().insertOrdemServicoItens(funcionario, "INSERT", response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	//
	//
	// OrdemServicoItens funcionario = new OrdemServicoItens();
	// funcionario = insertOrdemServicoItens(PersistanceActionEnum.INSERT);
	// InternalResultsResponse<OrdemServicoItens> response = new InternalResultsResponse<OrdemServicoItens>();
	//
	// Integer a = getOrdemServicoItensDAC().insertOrdemServicoItens(funcionario, response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// // FetchByIdRequest request = new FetchByIdRequest();
	// // request.setFetchId(response.getFirstResult().getId());
	// InternalResultsResponse<OrdemServicoItens> responseA =
	// getOrdemServicoItensDAC().fetchOrdemServicoItensById(response.getFirstResult().getId());
	// assertTrue(responseA.getResultsList().size() == 1);
	// assertEquals(responseA.getStatus(), Status.OperationSuccess);
	//
	//
	// }
	//
	// @Test
	// public void testdeleteOrdemServicoItens() throws Exception
	// {
	//
	// OrdemServicoItens funcionario = new OrdemServicoItens();
	// funcionario = insertOrdemServicoItens(PersistanceActionEnum.INSERT);
	// InternalResultsResponse<OrdemServicoItens> response = new InternalResultsResponse<OrdemServicoItens>();
	// Integer a = getOrdemServicoItensDAC().insertOrdemServicoItens(funcionario,response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// funcionario = response.getFirstResult();
	// response = new InternalResultsResponse<OrdemServicoItens>();
	// funcionario.setModelAction(PersistanceActionEnum.DELETE);
	// Integer b = getOrdemServicoItensDAC().deleteOrdemServicoItens(funcionario,response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// //FetchByIdRequest request = new FetchByIdRequest();
	// // request.setFetchId(response.getFirstResult().getId());
	// InternalResultsResponse<Classicacao> responseA =
	// getOrdemServicoItensDAC().fetchOrdemServicoItensById(response.getFirstResult().getId());
	// assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);
	//
	// }
	//
	// @Test
	// public void testfetchOrdemServicoItensById() throws Exception
	// {
	// // check for valid and precount
	// FetchByIdRequest request = new FetchByIdRequest();
	// request.setFetchId(3);
	// InternalResultsResponse<OrdemServicoItens> response =
	// getOrdemServicoItensDAC().fetchOrdemServicoItensById(request);
	// assertTrue(response.getResultsSetInfo().getPageSize() == 1);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// }
	//
	// @Test
	// public void testfetchOrdemServicoItensById2() throws Exception
	// {
	// // check for valid and precount
	// FetchByIdRequest request = new FetchByIdRequest();
	// request.setFetchId(3);
	// InternalResultsResponse<OrdemServicoItens> response = getOrdemServicoItensDAC().fetchOrdemServicoItensById(1);
	// assertTrue(response.getResultsSetInfo().getPageSize() == 1);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// }
	//
	// @Test
	// public void testfetchOrdemServicoItensByRequest() throws Exception
	// {
	// // check for valid and precount
	// OrdemServicoItensInquiryRequest request = new OrdemServicoItensInquiryRequest();
	// request.setPreQueryCount(true);
	// request.setStartPage(0);
	// request.setPageSize(4);
	// InternalResultsResponse<OrdemServicoItens> response =
	// getOrdemServicoItensDAC().fetchOrdemServicoItensByRequest(request);
	// assertTrue(response.getResultsSetInfo().getPageSize() == 4);
	// assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	// }
	//
	// public OrdemServicoItens insertOrdemServicoItens(PersistanceActionEnum action)
	// {
	// OrdemServicoItens exame = new OrdemServicoItens();
	// Date a = new Date();
	// exame.setId(1);
	// exame.setModelAction(action);
	// // exame.setNome("Nome");
	// // exame.setDataOrdemServicoItens((int)a.getTime());
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
