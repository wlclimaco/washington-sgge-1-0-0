/** create by system gera-java version 1.0.0 30/04/2016 19:27 : 22*/
package com.qat.samples.sysmgmt.bar.mybatis;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Fiscal.IFiscalBAR;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.cnae.model.CnaeEmpresa;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class FiscalBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(FiscalBARTest.class);
private IFiscalBAR fiscalBAR; // injected by Spring through @Resource

@Resource
public void setFiscalBAR(IFiscalBAR fiscalBAR)
{
	this.fiscalBAR = fiscalBAR;
}

public IFiscalBAR getFiscalBAR()
{
	return fiscalBAR;
}


//===================================### REGIME ####======================================


@Test
	public void testDeleteRegime()
	{
		Regime regime = new Regime(4, "Regime_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Regime regimeResponse = getFiscalBAR().fetchRegimeById(request);
		Assert.assertEquals(regimeResponse, null);
		getFiscalBAR().insertRegime(regime);
		regimeResponse = getFiscalBAR().fetchRegimeById(request);
		Assert.assertEquals(regime.getId(), regimeResponse.getId());
		getFiscalBAR().deleteRegimeById(regime);
		regimeResponse = getFiscalBAR().fetchRegimeById(request);
		Assert.assertEquals(regimeResponse, null);
	}

	@Test
	public void testFetchAllRegimes()
	{
	Regime regime = new Regime();
		List<Regime> response = getFiscalBAR().fetchAllRegimes(regime).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllRegimes()
	{
		getFiscalBAR().deleteAllRegimes();
	Regime regime = new Regime();
		List<Regime> response = getFiscalBAR().fetchAllRegimes(new Regime()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

//	@Test
//	public void testUpdateRegime()
//	{
//		Regime regime = new Regime(1, "NATIVE INSERT UPDATE");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1);
//		Regime regimeResponse = getFiscalBAR().fetchRegimeById(request);
//		Assert.assertEquals(regimeResponse.getNome(), "nome_1");
//		getFiscalBAR().updateRegime(regime);
//		regimeResponse = getFiscalBAR().fetchRegimeById(request);
//		Assert.assertEquals(regimeResponse.getNome(), "NATIVE INSERT UPDATE");
//	}

	@Test
	public void testFetchRegimesByRequest() throws Exception
	{
		// check for valid and precount
		RegimeInquiryRequest request = new RegimeInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Regime> response = getFiscalBAR().fetchRegimesByRequest(request);
	//	Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFiscalBAR().fetchRegimesByRequest(request);
	//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		RegimeInquiryRequest request2 = new RegimeInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Regime> response2 = getFiscalBAR().fetchRegimesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFiscalBAR().deleteAllRegimes();
		RegimeInquiryRequest request3 = new RegimeInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Regime> response3 = getFiscalBAR().fetchRegimesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CFOP ####======================================


@Test
	public void testDeleteCfop()
	{
		Cfop cfop = new Cfop(4, "Cfop_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Cfop cfopResponse = getFiscalBAR().fetchCfopById(request);
		Assert.assertEquals(cfopResponse, null);
		getFiscalBAR().insertCfop(cfop);
		cfopResponse = getFiscalBAR().fetchCfopById(request);
		Assert.assertEquals(cfop.getId(), cfopResponse.getId());
		getFiscalBAR().deleteCfopById(cfop);
		cfopResponse = getFiscalBAR().fetchCfopById(request);
		Assert.assertEquals(cfopResponse, null);
	}

	@Test
	public void testFetchAllCfops()
	{
	Cfop cfop = new Cfop();
		List<Cfop> response = getFiscalBAR().fetchAllCfops(cfop).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllCfops()
	{
		getFiscalBAR().deleteAllCfops();
	Cfop cfop = new Cfop();
		List<Cfop> response = getFiscalBAR().fetchAllCfops(new Cfop()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCfop()
	{
		Cfop cfop = new Cfop(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Cfop cfopResponse = getFiscalBAR().fetchCfopById(request);
		Assert.assertEquals(cfopResponse.getCfop(), "cfop_1");
		getFiscalBAR().updateCfop(cfop);
		cfopResponse = getFiscalBAR().fetchCfopById(request);
		Assert.assertEquals(cfopResponse.getCfop(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchCfopsByRequest() throws Exception
	{
		// check for valid and precount
		CfopInquiryRequest request = new CfopInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Cfop> response = getFiscalBAR().fetchCfopsByRequest(request);
	//	Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFiscalBAR().fetchCfopsByRequest(request);
	//	Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		CfopInquiryRequest request2 = new CfopInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Cfop> response2 = getFiscalBAR().fetchCfopsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFiscalBAR().deleteAllCfops();
		CfopInquiryRequest request3 = new CfopInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Cfop> response3 = getFiscalBAR().fetchCfopsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CNAE ####======================================


@Test
	public void testDeleteCnae()
	{
		Cnae cnae = new Cnae(4, "Cnae_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Cnae cnaeResponse = getFiscalBAR().fetchCnaeById(request);
		Assert.assertEquals(cnaeResponse, null);
		getFiscalBAR().insertCnae(cnae);
		cnaeResponse = getFiscalBAR().fetchCnaeById(request);
		Assert.assertEquals(cnae.getId(), cnaeResponse.getId());
		getFiscalBAR().deleteCnaeById(cnae);
		cnaeResponse = getFiscalBAR().fetchCnaeById(request);
		Assert.assertEquals(cnaeResponse, null);
	}

	@Test
	public void testFetchAllCnaes()
	{
	Cnae cnae = new Cnae();
		List<Cnae> response = getFiscalBAR().fetchAllCnaes(cnae).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllCnaes()
	{
		getFiscalBAR().deleteAllCnaes();
	Cnae cnae = new Cnae();
		List<Cnae> response = getFiscalBAR().fetchAllCnaes(new Cnae()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCnae()
	{
		Cnae cnae = new Cnae(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Cnae cnaeResponse = getFiscalBAR().fetchCnaeById(request);
		Assert.assertEquals(cnaeResponse.getCnae(), "cnae_2");
		getFiscalBAR().updateCnae(cnae);
		cnaeResponse = getFiscalBAR().fetchCnaeById(request);
		Assert.assertEquals(cnaeResponse.getCnae(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchCnaesByRequest() throws Exception
	{
		// check for valid and precount
		CnaeInquiryRequest request = new CnaeInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Cnae> response = getFiscalBAR().fetchCnaesByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFiscalBAR().fetchCnaesByRequest(request);
	//	Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		CnaeInquiryRequest request2 = new CnaeInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Cnae> response2 = getFiscalBAR().fetchCnaesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFiscalBAR().deleteAllCnaes();
		CnaeInquiryRequest request3 = new CnaeInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Cnae> response3 = getFiscalBAR().fetchCnaesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CNAEEMPRESA ####======================================


@Test
	public void testDeleteCnaeEmpresa()
	{
		CnaeEmpresa cnaeempresa = new CnaeEmpresa(4);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		CnaeEmpresa cnaeempresaResponse = getFiscalBAR().fetchCnaeEmpresaById(request);
		Assert.assertEquals(cnaeempresaResponse, null);
		getFiscalBAR().insertCnaeEmpresa(cnaeempresa);
		cnaeempresaResponse = getFiscalBAR().fetchCnaeEmpresaById(request);
		Assert.assertEquals(cnaeempresa.getId(), cnaeempresaResponse.getId());
		getFiscalBAR().deleteCnaeEmpresaById(cnaeempresa);
		cnaeempresaResponse = getFiscalBAR().fetchCnaeEmpresaById(request);
		Assert.assertEquals(cnaeempresaResponse, null);
	}

	@Test
	public void testFetchAllCnaeEmpresas()
	{
	CnaeEmpresa cnaeempresa = new CnaeEmpresa();
		List<CnaeEmpresa> response = getFiscalBAR().fetchAllCnaeEmpresas(cnaeempresa).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllCnaeEmpresas()
	{
		getFiscalBAR().deleteAllCnaeEmpresas();
	CnaeEmpresa cnaeempresa = new CnaeEmpresa();
		List<CnaeEmpresa> response = getFiscalBAR().fetchAllCnaeEmpresas(new CnaeEmpresa()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCnaeEmpresa()
	{
		CnaeEmpresa cnaeempresa = new CnaeEmpresa(1);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		CnaeEmpresa cnaeempresaResponse = getFiscalBAR().fetchCnaeEmpresaById(request);
//		getFiscalBAR().updateCnaeEmpresa(cnaeempresa);
//		cnaeempresaResponse = getFiscalBAR().fetchCnaeEmpresaById(request);
	}

	@Test
	public void testFetchCnaeEmpresasByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<CnaeEmpresa> response = getFiscalBAR().fetchCnaeEmpresasByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFiscalBAR().fetchCnaeEmpresasByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<CnaeEmpresa> response2 = getFiscalBAR().fetchCnaeEmpresasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFiscalBAR().deleteAllCnaeEmpresas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<CnaeEmpresa> response3 = getFiscalBAR().fetchCnaeEmpresasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertRegime.sql", false);
		executeSqlScript("conf/insertCfop.sql", false);
		executeSqlScript("conf/insertCnae.sql", false);
		executeSqlScript("conf/insertCnaeEmpresa.sql", false);
	}

}
