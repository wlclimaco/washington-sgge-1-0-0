package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.samples.sysmgmt.site.dac.ISiteDAC;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class SiteDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(SiteDACTest.class);
	private ISiteDAC siteDAC; // injected by Spring through setter @resource

	// below

	public ISiteDAC getSiteDAC()
	{
		return siteDAC;
	}

	@Resource
	public void setSiteDAC(ISiteDAC siteDAC)
	{
		this.siteDAC = siteDAC;
	}

	// @Test
	// public void testupdateSite() throws Exception
	// {
	//
	// Site funcionario = new Site();
	// funcionario = insertSite(PersistanceActionEnum.INSERT);
	// InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
	// Integer a = getSiteDAC().insertSite(funcionario,"", response);
	//
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// funcionario = response.getFirstResult();
	// funcionario.setModelAction(PersistanceActionEnum.UPDATE);
	// funcionario.setId(response.getFirstResult().getId());
	// response = new InternalResultsResponse<Site>();
	//
	// a = getSiteDAC().updateSite(funcionario, response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	//
	// }
	//
	// @Test
	// public void testinsertSite() throws Exception
	// {
	//
	// Site funcionario = new Site();
	// funcionario = insertSite(PersistanceActionEnum.INSERT);
	//
	// InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
	//
	// Integer a = getSiteDAC().insertSite(funcionario, "INSERT", response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	//
	//
	// Site funcionario = new Site();
	// funcionario = insertSite(PersistanceActionEnum.INSERT);
	// InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
	//
	// Integer a = getSiteDAC().insertSite(funcionario, response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// // FetchByIdRequest request = new FetchByIdRequest();
	// // request.setFetchId(response.getFirstResult().getId());
	// InternalResultsResponse<Site> responseA = getSiteDAC().fetchSiteById(response.getFirstResult().getId());
	// assertTrue(responseA.getResultsList().size() == 1);
	// assertEquals(responseA.getStatus(), Status.OperationSuccess);
	//
	//
	// }
	//
	// @Test
	// public void testdeleteSite() throws Exception
	// {
	//
	// Site funcionario = new Site();
	// funcionario = insertSite(PersistanceActionEnum.INSERT);
	// InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
	// Integer a = getSiteDAC().insertSite(funcionario,response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// funcionario = response.getFirstResult();
	// response = new InternalResultsResponse<Site>();
	// funcionario.setModelAction(PersistanceActionEnum.DELETE);
	// Integer b = getSiteDAC().deleteSite(funcionario,response);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// //FetchByIdRequest request = new FetchByIdRequest();
	// // request.setFetchId(response.getFirstResult().getId());
	// InternalResultsResponse<Classicacao> responseA = getSiteDAC().fetchSiteById(response.getFirstResult().getId());
	// assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);
	//
	// }
	//
	// @Test
	// public void testfetchSiteById() throws Exception
	// {
	// // check for valid and precount
	// FetchByIdRequest request = new FetchByIdRequest();
	// request.setFetchId(3);
	// InternalResultsResponse<Site> response = getSiteDAC().fetchSiteById(request);
	// assertTrue(response.getResultsSetInfo().getPageSize() == 1);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// }
	//
	// @Test
	// public void testfetchSiteById2() throws Exception
	// {
	// // check for valid and precount
	// FetchByIdRequest request = new FetchByIdRequest();
	// request.setFetchId(3);
	// InternalResultsResponse<Site> response = getSiteDAC().fetchSiteById(1);
	// assertTrue(response.getResultsSetInfo().getPageSize() == 1);
	// assertEquals(response.getStatus(), Status.OperationSuccess);
	// }
	//
	// @Test
	// public void testfetchSiteByRequest() throws Exception
	// {
	// // check for valid and precount
	// SiteInquiryRequest request = new SiteInquiryRequest();
	// request.setPreQueryCount(true);
	// request.setStartPage(0);
	// request.setPageSize(4);
	// InternalResultsResponse<Site> response = getSiteDAC().fetchSiteByRequest(request);
	// assertTrue(response.getResultsSetInfo().getPageSize() == 4);
	// assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	// }
	//
	// public Site insertSite(PersistanceActionEnum action)
	// {
	// Site exame = new Site();
	// Date a = new Date();
	// exame.setId(1);
	// exame.setModelAction(action);
	// // exame.setNome("Nome");
	// // exame.setDataSite((int)a.getTime());
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
