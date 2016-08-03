/** create by system gera-java version 1.0.0 03/08/2016 11:30 : 58*/
package com.qat.samples.sysmgmt.bar.mybatis;


import java.util.Date;
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

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Condominio.ICondominioBAR;
import com.qat.samples.sysmgmt.condominio.model.Avisos;
import com.qat.samples.sysmgmt.condominio.model.request.AvisoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class CondominioBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(CondominioBARTest.class);
private ICondominioBAR condominioBAR; // injected by Spring through @Resource

@Resource
public void setCondominioBAR(ICondominioBAR condominioBAR)
{
	this.condominioBAR = condominioBAR;
}

public ICondominioBAR getCondominioBAR()
{
	return condominioBAR;
}


//===================================### AVISOS ####======================================


@Test
	public void testDeleteAvisos()
	{
		Avisos avisos = insertAvisos(1004, TabelaEnum.AVISO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		Avisos avisosResponse = getCondominioBAR().fetchAvisosById(request);
		Assert.assertEquals(avisosResponse, null);
		getCondominioBAR().insertAvisos(avisos);
		avisosResponse = getCondominioBAR().fetchAvisosById(request);
		Assert.assertEquals(avisos.getId(), avisosResponse.getId());
		getCondominioBAR().deleteAvisosById(avisos);
		avisosResponse = getCondominioBAR().fetchAvisosById(request);
		Assert.assertEquals(avisosResponse, null);
	}

	@Test
	public void testFetchAllAvisoss()
	{
	Avisos avisos = new Avisos();
		List<Avisos> response = getCondominioBAR().fetchAllAvisos(avisos).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllAvisoss()
	{
		getCondominioBAR().deleteAllAvisoss();
	Avisos avisos = new Avisos();
		List<Avisos> response = getCondominioBAR().fetchAllAvisos(new Avisos()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateAvisos()
	{
		Avisos avisos = insertAvisos(1000, TabelaEnum.AVISO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		Avisos avisosResponse = getCondominioBAR().fetchAvisosById(request);
		Assert.assertEquals(avisosResponse.getModifyUser(), "rod");
		getCondominioBAR().updateAvisos(avisos);
		avisosResponse = getCondominioBAR().fetchAvisosById(request);
		Assert.assertEquals(avisosResponse.getModifyUser(), "system");
	}

	@Test
	public void testFetchAvisossByRequest() throws Exception
	{
		// check for valid and precount
		AvisoInquiryRequest request = new AvisoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Avisos> response = getCondominioBAR().fetchAvisosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getCondominioBAR().fetchAvisosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		AvisoInquiryRequest request2 = new AvisoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Avisos> response2 = getCondominioBAR().fetchAvisosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getCondominioBAR().deleteAllAvisoss();
		AvisoInquiryRequest request3 = new AvisoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Avisos> response3 = getCondominioBAR().fetchAvisosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertAvisos.sql", false);
	}


	public Avisos insertAvisos(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Avisos avisos = new Avisos();
			Date a = new Date();
			avisos.setId(id);
			avisos.setParentId(id);
			avisos.setEmprId(1);
			avisos.setModifyDateUTC(a.getTime());
			avisos.setCreateDateUTC(a.getTime());
			avisos.setCreateUser("system");
			avisos.setModifyUser("system");
			avisos.setProcessId(1);
			avisos.setModelAction(action);

			return avisos;
		}


}
