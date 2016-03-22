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
public class AtualizaEstoqueDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	// private static final Logger LOG = LoggerFactory.getLogger(AtualizaEstoqueDACTest.class);
	// private IAtualizaEstoqueDAC enderecoDAC; // injected by Spring through setter @resource
	//
	// // below
	//
	// public IAtualizaEstoqueDAC getAtualizaEstoqueDAC()
	// {
	// return enderecoDAC;
	// }
	//
	// @Resource
	// public void setAtualizaEstoqueDAC(IAtualizaEstoqueDAC enderecoDAC)
	// {
	// this.enderecoDAC = enderecoDAC;
	// }
	//
	// @Test
	// public void testupdateAtualizaEstoque() throws Exception
	// {
	//
	// AtualizaEstoque funcionario = new AtualizaEstoque();
	// funcionario = insertAtualizaEstoque(PersistanceActionEnum.UPDATE);
	//
	// InternalResultsResponse<AtualizaEstoque> funcionarioResponse =
	// getAtualizaEstoqueDAC().updateAtualizaEstoque(funcionario);
	// assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
	//
	// }
	//
	// @Test
	// public void testinsertAtualizaEstoque() throws Exception
	// {
	//
	// AtualizaEstoque funcionario = new AtualizaEstoque();
	// funcionario = insertAtualizaEstoque(PersistanceActionEnum.INSERT);
	//
	// InternalResultsResponse<AtualizaEstoque> funcionarioResponse =
	// getAtualizaEstoqueDAC().insertAtualizaEstoque(funcionario);
	// assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
	// FetchByIdRequest request = new FetchByIdRequest();
	// request.setFetchId(22);
	// InternalResultsResponse<AtualizaEstoque> responseA = getAtualizaEstoqueDAC().fetchAtualizaEstoqueById(request);
	// assertTrue(responseA.getResultsList().size() == 1);
	// assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == StatusEnum.ANALIZANDO);
	//
	// }
	//
	// @Test
	// public void testdeleteAtualizaEstoque() throws Exception
	// {
	//
	// AtualizaEstoque funcionario = new AtualizaEstoque();
	// funcionario.setId(1);
	// funcionario = insertAtualizaEstoque(PersistanceActionEnum.DELETE);
	// InternalResponse funcionarioResponse = getAtualizaEstoqueDAC().deleteAtualizaEstoque(funcionario);
	// assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
	// }
	//
	// @Test
	// public void testfetchAtualizaEstoqueById() throws Exception
	// {
	// // check for valid and precount
	// FetchByIdRequest request = new FetchByIdRequest();
	// request.setFetchId(3);
	// InternalResultsResponse<AtualizaEstoque> response = getAtualizaEstoqueDAC().fetchAtualizaEstoqueById(request);
	// assertTrue(response.getResultsSetInfo().getPageSize() == 1);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// }
	//
	// @Test
	// public void testfetchAtualizaEstoqueByRequest() throws Exception
	// {
	// // check for valid and precount
	// AtualizaEstoqueInquiryRequest request = new AtualizaEstoqueInquiryRequest();
	// request.setPreQueryCount(true);
	// request.setStartPage(0);
	// request.setPageSize(4);
	// InternalResultsResponse<AtualizaEstoque> response =
	// getAtualizaEstoqueDAC().fetchAtualizaEstoqueByRequest(request);
	// assertTrue(response.getResultsSetInfo().getPageSize() == 4);
	// assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	// }
	//
	// @Before
	// public void setup()
	// {
	// executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertAtualizaEstoque.sql", false);
	// }
}
