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
import com.qat.samples.sysmgmt.bar.Dp.IDpBAR;
import com.qat.samples.sysmgmt.beneficios.model.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.Eventos;
import com.qat.samples.sysmgmt.dp.model.HorarioFunc;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class DpBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(DpBARTest.class);
private IDpBAR dpBAR; // injected by Spring through @Resource

@Resource
public void setDpBAR(IDpBAR dpBAR)
{
	this.dpBAR = dpBAR;
}

public IDpBAR getDpBAR()
{
	return dpBAR;
}


//===================================### EVENTOS ####======================================


@Test
	public void testDeleteEventos()
	{
		Eventos eventos = insertEventos(1004, TabelaEnum.EVENTOS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		Eventos eventosResponse = getDpBAR().fetchEventosById(request);
		Assert.assertEquals(eventosResponse, null);
		getDpBAR().insertEventos(eventos);
		eventosResponse = getDpBAR().fetchEventosById(request);
		Assert.assertEquals(eventos.getId(), eventosResponse.getId());
		getDpBAR().deleteEventosById(eventos);
		eventosResponse = getDpBAR().fetchEventosById(request);
		Assert.assertEquals(eventosResponse, null);
	}

	@Test
	public void testFetchAllEventoss()
	{
	Eventos eventos = new Eventos();
		List<Eventos> response = getDpBAR().fetchAllEventos(eventos).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllEventoss()
	{
		getDpBAR().deleteAllEventos();
		Eventos eventos = new Eventos();
		List<Eventos> response = getDpBAR().fetchAllEventos(new Eventos()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateEventos()
	{
		Eventos eventos = insertEventos(1000, TabelaEnum.EVENTOS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		Eventos eventosResponse = getDpBAR().fetchEventosById(request);
		Assert.assertEquals(eventosResponse.getDescricao(), "descricao_0");
		getDpBAR().updateEventos(eventos);
		eventosResponse = getDpBAR().fetchEventosById(request);
		Assert.assertEquals(eventosResponse.getDescricao(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchEventossByRequest() throws Exception
	{
		// check for valid and precount
		EventoInquiryRequest request = new EventoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Eventos> response = getDpBAR().fetchEventosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getDpBAR().fetchEventosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		EventoInquiryRequest request2 = new EventoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Eventos> response2 = getDpBAR().fetchEventosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getDpBAR().deleteAllEventos();
		EventoInquiryRequest request3 = new EventoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Eventos> response3 = getDpBAR().fetchEventosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### BENEFICIOS ####======================================


@Test
	public void testDeleteBeneficios()
	{
		Beneficios beneficios = insertBeneficios(4, TabelaEnum.BENEFICIOS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Beneficios beneficiosResponse = getDpBAR().fetchBeneficiosById(request);
		Assert.assertEquals(beneficiosResponse, null);
		getDpBAR().insertBeneficios(beneficios);
		beneficiosResponse = getDpBAR().fetchBeneficiosById(request);
		Assert.assertEquals(beneficios.getId(), beneficiosResponse.getId());
		getDpBAR().deleteBeneficiosById(beneficios);
		beneficiosResponse = getDpBAR().fetchBeneficiosById(request);
		Assert.assertEquals(beneficiosResponse, null);
	}

	@Test
	public void testFetchAllBeneficioss()
	{
	Beneficios beneficios = new Beneficios();
		List<Beneficios> response = getDpBAR().fetchAllBeneficioss(beneficios).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllBeneficioss()
	{
		getDpBAR().deleteAllBeneficioss();
	Beneficios beneficios = new Beneficios();
		List<Beneficios> response = getDpBAR().fetchAllBeneficioss(new Beneficios()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateBeneficios()
	{
		Beneficios beneficios = insertBeneficios(1000, TabelaEnum.BENEFICIOS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		Beneficios beneficiosResponse = getDpBAR().fetchBeneficiosById(request);
		Assert.assertEquals(beneficiosResponse.getNome(), null);
		getDpBAR().updateBeneficios(beneficios);
		beneficiosResponse = getDpBAR().fetchBeneficiosById(request);
		Assert.assertEquals(beneficiosResponse.getNome(), null);
	}

	@Test
	public void testFetchBeneficiossByRequest() throws Exception
	{
		// check for valid and precount
		BeneficiosInquiryRequest request = new BeneficiosInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Beneficios> response = getDpBAR().fetchBeneficiossByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getDpBAR().fetchBeneficiossByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		BeneficiosInquiryRequest request2 = new BeneficiosInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Beneficios> response2 = getDpBAR().fetchBeneficiossByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getDpBAR().deleteAllBeneficioss();
		BeneficiosInquiryRequest request3 = new BeneficiosInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Beneficios> response3 = getDpBAR().fetchBeneficiossByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### HORAFUNC ####======================================


//@Test
//	public void testDeleteHorafunc()
//	{
//		HorarioFunc horafunc = insertHoraFunc(4, TabelaEnum.HORAFUNC, PersistenceActionEnum.INSERT);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(4);
//		HorarioFunc horafuncResponse = getDpBAR().fetchHorafuncById(request);
//		Assert.assertEquals(horafuncResponse, null);
//		getDpBAR().insertHorafunc(horafunc);
//		horafuncResponse = getDpBAR().fetchHorafuncById(request);
//		Assert.assertEquals(horafunc.getId(), horafuncResponse.getId());
//		getDpBAR().deleteHorafuncById(horafunc);
//		horafuncResponse = getDpBAR().fetchHorafuncById(request);
//		Assert.assertEquals(horafuncResponse, null);
//	}
//
//	@Test
//	public void testFetchAllHorafuncs()
//	{
//	HorarioFunc horafunc = new HorarioFunc();
//		List<HorarioFunc> response = getDpBAR().fetchAllHorafuncs(horafunc).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllHorafuncs()
//	{
//		getDpBAR().deleteAllHorafuncs();
//	HorarioFunc horafunc = new HorarioFunc();
//		List<HorarioFunc> response = getDpBAR().fetchAllHorafuncs(new HorarioFunc()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateHorafunc()
//	{
//		HorarioFunc horafunc = insertHoraFunc(1, TabelaEnum.HORAFUNC, PersistenceActionEnum.UPDATE);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1);
//		HorarioFunc horafuncResponse = getDpBAR().fetchHorafuncById(request);
//		Assert.assertEquals(horafuncResponse.getTipo(), "NATIVE INSERT");
//		getDpBAR().updateHorafunc(horafunc);
//		horafuncResponse = getDpBAR().fetchHorafuncById(request);
//		Assert.assertEquals(horafuncResponse.getTipo(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchHorafuncsByRequest() throws Exception
//	{
//		// check for valid and precount
//		HoraFuncInquiryRequest request = new HoraFuncInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<HorarioFunc> response = getDpBAR().fetchHorafuncsByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getDpBAR().fetchHorafuncsByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		HoraFuncInquiryRequest request2 = new HoraFuncInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<HorarioFunc> response2 = getDpBAR().fetchHorafuncsByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getDpBAR().deleteAllHorafuncs();
//		HoraFuncInquiryRequest request3 = new HoraFuncInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<HorarioFunc> response3 = getDpBAR().fetchHorafuncsByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertEventos.sql", false);
		executeSqlScript("conf/insertBeneficios.sql", false);
		executeSqlScript("conf/insertHorafunc.sql", false);
	}


	public Eventos insertEventos(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Eventos eventos = new Eventos();
			Date a = new Date();
			eventos.setId(id);
			eventos.setDescricao("NATIVE INSERT UPDATE");

			eventos.setParentId(id);
			eventos.setEmprId(1);
			eventos.setModifyDateUTC(a.getTime());
			eventos.setCreateDateUTC(a.getTime());
			eventos.setCreateUser("system");
			eventos.setModifyUser("system");
			eventos.setProcessId(1);
			eventos.setModelAction(action);

			return eventos;
		}


	public Beneficios insertBeneficios(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Beneficios beneficios = new Beneficios();
			Date a = new Date();
			beneficios.setId(id);
			beneficios.setParentId(id);
			beneficios.setEmprId(1);
			beneficios.setModifyDateUTC(a.getTime());
			beneficios.setCreateDateUTC(a.getTime());
			beneficios.setCreateUser("system");
			beneficios.setModifyUser("system");
			beneficios.setProcessId(1);
			beneficios.setModelAction(action);

			return beneficios;
		}


	public HorarioFunc insertHoraFunc(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			HorarioFunc horafunc = new HorarioFunc();
			Date a = new Date();
			horafunc.setId(id);
			horafunc.setParentId(id);
			horafunc.setEmprId(1);
			horafunc.setModifyDateUTC(a.getTime());
			horafunc.setCreateDateUTC(a.getTime());
			horafunc.setCreateUser("system");
			horafunc.setModifyUser("system");
			horafunc.setProcessId(1);
			horafunc.setModelAction(action);

			return horafunc;
		}


}
