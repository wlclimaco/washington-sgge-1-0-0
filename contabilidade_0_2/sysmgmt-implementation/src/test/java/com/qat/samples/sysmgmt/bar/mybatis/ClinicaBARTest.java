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
import com.qat.samples.sysmgmt.bar.Clinica.IClinicaBAR;
import com.qat.samples.sysmgmt.clinica.model.Consulta;
import com.qat.samples.sysmgmt.clinica.model.Exame;
import com.qat.samples.sysmgmt.clinica.model.request.ConsultaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ExameInquiryRequest;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ClinicaBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(ClinicaBARTest.class);
private IClinicaBAR clinicaBAR; // injected by Spring through @Resource

@Resource
public void setClinicaBAR(IClinicaBAR clinicaBAR)
{
	this.clinicaBAR = clinicaBAR;
}

public IClinicaBAR getClinicaBAR()
{
	return clinicaBAR;
}


//===================================### CONSULTA ####======================================


@Test
	public void testDeleteConsulta()
	{
		Consulta consulta = insertConsulta(1004, TabelaEnum.CONSULTA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		Consulta consultaResponse = getClinicaBAR().fetchConsultaById(request);
		Assert.assertEquals(consultaResponse, null);
		getClinicaBAR().insertConsulta(consulta);
		consultaResponse = getClinicaBAR().fetchConsultaById(request);
		Assert.assertEquals(consulta.getId(), consultaResponse.getId());
		getClinicaBAR().deleteConsultaById(consulta);
		consultaResponse = getClinicaBAR().fetchConsultaById(request);
		Assert.assertEquals(consultaResponse, null);
	}

	@Test
	public void testFetchAllConsultas()
	{
	Consulta consulta = new Consulta();
		List<Consulta> response = getClinicaBAR().fetchAllConsultas(consulta).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConsultas()
	{
		getClinicaBAR().deleteAllConsultas();
	Consulta consulta = new Consulta();
		List<Consulta> response = getClinicaBAR().fetchAllConsultas(new Consulta()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConsulta()
	{
		Consulta consulta = insertConsulta(1000, TabelaEnum.CONSULTA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		Consulta consultaResponse = getClinicaBAR().fetchConsultaById(request);
		Assert.assertEquals(consultaResponse.getValor().toString(), "10.0");
		getClinicaBAR().updateConsulta(consulta);
		consultaResponse = getClinicaBAR().fetchConsultaById(request);
		Assert.assertEquals(consultaResponse.getValor().toString(), "11.0");
	}

	@Test
	public void testFetchConsultasByRequest() throws Exception
	{
		// check for valid and precount
		ConsultaInquiryRequest request = new ConsultaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Consulta> response = getClinicaBAR().fetchConsultasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getClinicaBAR().fetchConsultasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ConsultaInquiryRequest request2 = new ConsultaInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Consulta> response2 = getClinicaBAR().fetchConsultasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getClinicaBAR().deleteAllConsultas();
		ConsultaInquiryRequest request3 = new ConsultaInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Consulta> response3 = getClinicaBAR().fetchConsultasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### EXAME ####======================================


@Test
	public void testDeleteExame()
	{
		Exame exame = insertExame(1004, TabelaEnum.EXAME, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		Exame exameResponse = getClinicaBAR().fetchExameById(request);
		Assert.assertEquals(exameResponse, null);
		getClinicaBAR().insertExame(exame);
		exameResponse = getClinicaBAR().fetchExameById(request);
		Assert.assertEquals(exame.getId().toString(), exameResponse.getId().toString());
		getClinicaBAR().deleteExameById(exame);
		exameResponse = getClinicaBAR().fetchExameById(request);
		Assert.assertEquals(exameResponse, null);
	}

	@Test
	public void testFetchAllExames()
	{
	Exame exame = new Exame();
		List<Exame> response = getClinicaBAR().fetchAllExames(exame).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllExames()
	{
		getClinicaBAR().deleteAllExames();
	Exame exame = new Exame();
		List<Exame> response = getClinicaBAR().fetchAllExames(new Exame()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateExame()
	{
		Exame exame = insertExame(1000, TabelaEnum.EXAME, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		Exame exameResponse = getClinicaBAR().fetchExameById(request);
		Assert.assertEquals(exameResponse.getLaboratorio(), null);
		getClinicaBAR().updateExame(exame);
		exameResponse = getClinicaBAR().fetchExameById(request);
		Assert.assertEquals(exameResponse.getLaboratorio(), null);
	}

	@Test
	public void testFetchExamesByRequest() throws Exception
	{
		// check for valid and precount
		ExameInquiryRequest request = new ExameInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Exame> response = getClinicaBAR().fetchExamesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getClinicaBAR().fetchExamesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ExameInquiryRequest request2 = new ExameInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Exame> response2 = getClinicaBAR().fetchExamesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getClinicaBAR().deleteAllExames();
		ExameInquiryRequest request3 = new ExameInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Exame> response3 = getClinicaBAR().fetchExamesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertConsulta.sql", false);
		executeSqlScript("conf/insertExame.sql", false);
	}


	public Consulta insertConsulta(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Consulta consulta = new Consulta();
			Date a = new Date();
			consulta.setId(id);
			consulta.setDataConsulta(a.getTime());
			consulta.setValor(new Double(11.00));
			consulta.setDataMarcacao(a.getTime());

//			consulta.setMedico(10000);
//			consulta.setPaciente(10000);
//			consulta.setPlanoSaude(10000);
//			consulta.setExameList(10000);
//			consulta.getundefined().add(insertundefined(id,TabelaEnum.CONSULTA,action));
			consulta.setParentId(id);
			consulta.setEmprId(1);
			consulta.setModifyDateUTC(a.getTime());
			consulta.setCreateDateUTC(a.getTime());
			consulta.setCreateUser("system");
			consulta.setModifyUser("system");
			consulta.setProcessId(1);
			consulta.setModelAction(action);

			return consulta;
		}


	public Exame insertExame(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Exame exame = new Exame();
			Date a = new Date();
			exame.setId(id);
			exame.setParentId(id);
			exame.setEmprId(1);
			exame.setModifyDateUTC(a.getTime());
			exame.setCreateDateUTC(a.getTime());
			exame.setCreateUser("system");
			exame.setModifyUser("system");
			exame.setProcessId(1);
			exame.setModelAction(action);

			return exame;
		}


}
