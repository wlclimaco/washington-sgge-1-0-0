/** create by system gera-java version 1.0.0 01/05/2016 16:56 : 54*/
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
import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaInquiryRequest;
import com.qat.samples.sysmgmt.bar.Empresa.IEmpresaBAR;
import com.qat.samples.sysmgmt.clinica.model.Clinica;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/empresabartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class EmpresaBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(EmpresaBARTest.class);
private IEmpresaBAR empresaBAR; // injected by Spring through @Resource

@Resource
public void setEmpresaBAR(IEmpresaBAR empresaBAR)
{
	this.empresaBAR = empresaBAR;
}

public IEmpresaBAR getEmpresaBAR()
{
	return empresaBAR;
}


//===================================### EMPRESA ####======================================


@Test
	public void testDeleteEmpresa()
	{
		Empresa empresa = new Empresa(4, "Empresa_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Empresa empresaResponse = getEmpresaBAR().fetchEmpresaById(request);
		Assert.assertEquals(empresaResponse, null);
		getEmpresaBAR().insertEmpresa(empresa);
		empresaResponse = getEmpresaBAR().fetchEmpresaById(request);
		Assert.assertEquals(empresa.getId(), empresaResponse.getId());
		getEmpresaBAR().deleteEmpresaById(empresa);
		empresaResponse = getEmpresaBAR().fetchEmpresaById(request);
		Assert.assertEquals(empresaResponse, null);
	}

	@Test
	public void testFetchAllEmpresas()
	{
	Empresa empresa = new Empresa();
		List<Empresa> response = getEmpresaBAR().fetchAllEmpresas(empresa).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllEmpresas()
	{
		getEmpresaBAR().deleteAllEmpresas();
	Empresa empresa = new Empresa();
		List<Empresa> response = getEmpresaBAR().fetchAllEmpresas(new Empresa()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateEmpresa()
	{
		Empresa empresa = new Empresa(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Empresa empresaResponse = getEmpresaBAR().fetchEmpresaById(request);
		Assert.assertEquals(empresaResponse.getNome(), "nome_1");
		getEmpresaBAR().updateEmpresa(empresa);
		empresaResponse = getEmpresaBAR().fetchEmpresaById(request);
		Assert.assertEquals(empresaResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchEmpresasByRequest() throws Exception
	{
		// check for valid and precount
		EmpresaInquiryRequest request = new EmpresaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Empresa> response = getEmpresaBAR().fetchEmpresasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getEmpresaBAR().fetchEmpresasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		EmpresaInquiryRequest request2 = new EmpresaInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Empresa> response2 = getEmpresaBAR().fetchEmpresasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getEmpresaBAR().deleteAllEmpresas();
		EmpresaInquiryRequest request3 = new EmpresaInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Empresa> response3 = getEmpresaBAR().fetchEmpresasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### FILIAL ####======================================


@Test
	public void testDeleteFilial()
	{
		Filial filial = new Filial(4, "Filial_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Filial filialResponse = getEmpresaBAR().fetchFilialById(request);
		Assert.assertEquals(filialResponse, null);
		getEmpresaBAR().insertFilial(filial);
		filialResponse = getEmpresaBAR().fetchFilialById(request);
		Assert.assertEquals(filial.getId(), filialResponse.getId());
		getEmpresaBAR().deleteFilialById(filial);
		filialResponse = getEmpresaBAR().fetchFilialById(request);
		Assert.assertEquals(filialResponse, null);
	}

	@Test
	public void testFetchAllFilials()
	{
	Filial filial = new Filial();
		List<Filial> response = getEmpresaBAR().fetchAllFilials(filial).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllFilials()
	{
		getEmpresaBAR().deleteAllFilials();
	Filial filial = new Filial();
		List<Filial> response = getEmpresaBAR().fetchAllFilials(new Filial()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateFilial()
	{
		Filial filial = new Filial(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Filial filialResponse = getEmpresaBAR().fetchFilialById(request);
		Assert.assertEquals(filialResponse.getNome(), "NATIVE INSERT");
		getEmpresaBAR().updateFilial(filial);
		filialResponse = getEmpresaBAR().fetchFilialById(request);
		Assert.assertEquals(filialResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchFilialsByRequest() throws Exception
	{
		// check for valid and precount
		FilialInquiryRequest request = new FilialInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Filial> response = getEmpresaBAR().fetchFilialsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getEmpresaBAR().fetchFilialsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		FilialInquiryRequest request2 = new FilialInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Filial> response2 = getEmpresaBAR().fetchFilialsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getEmpresaBAR().deleteAllFilials();
		FilialInquiryRequest request3 = new FilialInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Filial> response3 = getEmpresaBAR().fetchFilialsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### DEPOSITO ####======================================


@Test
	public void testDeleteDeposito()
	{
		Deposito deposito = new Deposito(4, "Deposito_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Deposito depositoResponse = getEmpresaBAR().fetchDepositoById(request);
		Assert.assertEquals(depositoResponse, null);
		getEmpresaBAR().insertDeposito(deposito);
		depositoResponse = getEmpresaBAR().fetchDepositoById(request);
		Assert.assertEquals(deposito.getId(), depositoResponse.getId());
		getEmpresaBAR().deleteDepositoById(deposito);
		depositoResponse = getEmpresaBAR().fetchDepositoById(request);
		Assert.assertEquals(depositoResponse, null);
	}

	@Test
	public void testFetchAllDepositos()
	{
	Deposito deposito = new Deposito();
		List<Deposito> response = getEmpresaBAR().fetchAllDepositos(deposito).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllDepositos()
	{
		getEmpresaBAR().deleteAllDepositos();
	Deposito deposito = new Deposito();
		List<Deposito> response = getEmpresaBAR().fetchAllDepositos(new Deposito()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateDeposito()
	{
		Deposito deposito = new Deposito(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Deposito depositoResponse = getEmpresaBAR().fetchDepositoById(request);
		Assert.assertEquals(depositoResponse.getNome(), "NATIVE INSERT");
		getEmpresaBAR().updateDeposito(deposito);
		depositoResponse = getEmpresaBAR().fetchDepositoById(request);
		Assert.assertEquals(depositoResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchDepositosByRequest() throws Exception
	{
		// check for valid and precount
		DepositoInquiryRequest request = new DepositoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Deposito> response = getEmpresaBAR().fetchDepositosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getEmpresaBAR().fetchDepositosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		DepositoInquiryRequest request2 = new DepositoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Deposito> response2 = getEmpresaBAR().fetchDepositosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getEmpresaBAR().deleteAllDepositos();
		DepositoInquiryRequest request3 = new DepositoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Deposito> response3 = getEmpresaBAR().fetchDepositosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### USUARIO ####======================================


@Test
	public void testDeleteUsuario()
	{
		Usuario usuario = new Usuario(4, "Usuario_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Usuario usuarioResponse = getEmpresaBAR().fetchUsuarioById(request);
		Assert.assertEquals(usuarioResponse, null);
		getEmpresaBAR().insertUsuario(usuario);
		usuarioResponse = getEmpresaBAR().fetchUsuarioById(request);
		Assert.assertEquals(usuario.getId(), usuarioResponse.getId());
		getEmpresaBAR().deleteUsuarioById(usuario);
		usuarioResponse = getEmpresaBAR().fetchUsuarioById(request);
		Assert.assertEquals(usuarioResponse, null);
	}

	@Test
	public void testFetchAllUsuarios()
	{
	Usuario usuario = new Usuario();
		List<Usuario> response = getEmpresaBAR().fetchAllUsuarios(usuario).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllUsuarios()
	{
		getEmpresaBAR().deleteAllUsuarios();
		Usuario usuario = new Usuario();
		List<Usuario> response = getEmpresaBAR().fetchAllUsuarios(new Usuario()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateUsuario()
	{
		Usuario usuario = new Usuario(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Usuario usuarioResponse = getEmpresaBAR().fetchUsuarioById(request);
		Assert.assertEquals(usuarioResponse.getLogin(), "NATIVE INSERT");
		getEmpresaBAR().updateUsuario(usuario);
		usuarioResponse = getEmpresaBAR().fetchUsuarioById(request);
		Assert.assertEquals(usuarioResponse.getLogin(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchUsuariosByRequest() throws Exception
	{
		// check for valid and precount
		UsuarioInquiryRequest request = new UsuarioInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Usuario> response = getEmpresaBAR().fetchUsuariosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getEmpresaBAR().fetchUsuariosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		UsuarioInquiryRequest request2 = new UsuarioInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Usuario> response2 = getEmpresaBAR().fetchUsuariosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getEmpresaBAR().deleteAllUsuarios();
		UsuarioInquiryRequest request3 = new UsuarioInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Usuario> response3 = getEmpresaBAR().fetchUsuariosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}


	//===================================### CONDOMINIO ####======================================


	@Test
		public void testDeleteCondominio()
		{
			Condominio condominio = new Condominio(4, "Condominio_999");
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(4);
			Condominio condominioResponse = getEmpresaBAR().fetchCondominioById(request);
			Assert.assertEquals(condominioResponse, null);
			getEmpresaBAR().insertCondominio(condominio);
			condominioResponse = getEmpresaBAR().fetchCondominioById(request);
			Assert.assertEquals(condominio.getId(), condominioResponse.getId());
			getEmpresaBAR().deleteCondominioById(condominio);
			condominioResponse = getEmpresaBAR().fetchCondominioById(request);
			Assert.assertEquals(condominioResponse, null);
		}

		@Test
		public void testFetchAllCondominios()
		{
		Condominio condominio = new Condominio();
			List<Condominio> response = getEmpresaBAR().fetchAllCondominios(condominio).getResultsList();
			Assert.assertNotNull(response);
		}

		@Test
		public void testDeleteAllCondominios()
		{
			getEmpresaBAR().deleteAllCondominios();
		Condominio condominio = new Condominio();
			List<Condominio> response = getEmpresaBAR().fetchAllCondominios(new Condominio()).getResultsList();
			Assert.assertEquals(response.size(), 0);
		}

		@Test
		public void testUpdateCondominio()
		{
			Condominio condominio = new Condominio(1, "NATIVE INSERT UPDATE");
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(1);
			Condominio condominioResponse = getEmpresaBAR().fetchCondominioById(request);
			Assert.assertEquals(condominioResponse.getNome(), "NATIVE INSERT");
			getEmpresaBAR().updateCondominio(condominio);
			condominioResponse = getEmpresaBAR().fetchCondominioById(request);
			Assert.assertEquals(condominioResponse.getNome(), "NATIVE INSERT UPDATE");
		}

		@Test
		public void testFetchCondominiosByRequest() throws Exception
		{
			// check for valid and precount
			CondominioInquiryRequest request = new CondominioInquiryRequest();
			request.setPreQueryCount(true);
			request.setStartPage(0);
			request.setPageSize(3);
			InternalResultsResponse<Condominio> response = getEmpresaBAR().fetchCondominiosByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
			// check for valid and precount and start 2nd page
			request.setPreQueryCount(true);
			request.setStartPage(1);
			request.setPageSize(3);
			response = getEmpresaBAR().fetchCondominiosByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

			// check for valid and no precount
			CondominioInquiryRequest request2 = new CondominioInquiryRequest();
			request2.setPreQueryCount(false);
			InternalResultsResponse<Condominio> response2 = getEmpresaBAR().fetchCondominiosByRequest(request2);
			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
			// this is because we did not choose to precount
			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

			// check for zero rows
			getEmpresaBAR().deleteAllCondominios();
			CondominioInquiryRequest request3 = new CondominioInquiryRequest();
			request3.setPreQueryCount(true);
			InternalResultsResponse<Condominio> response3 = getEmpresaBAR().fetchCondominiosByRequest(request3);
			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

		}

	//===================================### CLINICA ####======================================


	@Test
		public void testDeleteClinica()
		{
			Clinica clinica = new Clinica(4, "Clinica_999");
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(4);
			Clinica clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
			Assert.assertEquals(clinicaResponse, null);
			getEmpresaBAR().insertClinica(clinica);
			clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
			Assert.assertEquals(clinica.getId(), clinicaResponse.getId());
			getEmpresaBAR().deleteClinicaById(clinica);
			clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
			Assert.assertEquals(clinicaResponse, null);
		}

		@Test
		public void testFetchAllClinicas()
		{
		Clinica clinica = new Clinica();
			List<Clinica> response = getEmpresaBAR().fetchAllClinicas(clinica).getResultsList();
			Assert.assertNotNull(response);
		}

		@Test
		public void testDeleteAllClinicas()
		{
			getEmpresaBAR().deleteAllClinicas();
		Clinica clinica = new Clinica();
			List<Clinica> response = getEmpresaBAR().fetchAllClinicas(new Clinica()).getResultsList();
			Assert.assertEquals(response.size(), 0);
		}

		@Test
		public void testUpdateClinica()
		{
			Clinica clinica = new Clinica(1, "NATIVE INSERT UPDATE");
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(1);
			Clinica clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
			Assert.assertEquals(clinicaResponse.getNome(), "NATIVE INSERT");
			getEmpresaBAR().updateClinica(clinica);
			clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
			Assert.assertEquals(clinicaResponse.getNome(), "NATIVE INSERT UPDATE");
		}

		@Test
		public void testFetchClinicasByRequest() throws Exception
		{
			// check for valid and precount
			ClinicaInquiryRequest request = new ClinicaInquiryRequest();
			request.setPreQueryCount(true);
			request.setStartPage(0);
			request.setPageSize(3);
			InternalResultsResponse<Clinica> response = getEmpresaBAR().fetchClinicasByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
			// check for valid and precount and start 2nd page
			request.setPreQueryCount(true);
			request.setStartPage(1);
			request.setPageSize(3);
			response = getEmpresaBAR().fetchClinicasByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

			// check for valid and no precount
			ClinicaInquiryRequest request2 = new ClinicaInquiryRequest();
			request2.setPreQueryCount(false);
			InternalResultsResponse<Clinica> response2 = getEmpresaBAR().fetchClinicasByRequest(request2);
			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
			// this is because we did not choose to precount
			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

			// check for zero rows
			getEmpresaBAR().deleteAllClinicas();
			ClinicaInquiryRequest request3 = new ClinicaInquiryRequest();
			request3.setPreQueryCount(true);
			InternalResultsResponse<Clinica> response3 = getEmpresaBAR().fetchClinicasByRequest(request3);
			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

		}

	//===================================### ADVOCACIA ####======================================


	@Test
		public void testDeleteAdvocacia()
		{
			Advocacia advocacia = new Advocacia(4, "Advocacia_999");
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(4);
			Advocacia advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
			Assert.assertEquals(advocaciaResponse, null);
			getEmpresaBAR().insertAdvocacia(advocacia);
			advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
			Assert.assertEquals(advocacia.getId(), advocaciaResponse.getId());
			getEmpresaBAR().deleteAdvocaciaById(advocacia);
			advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
			Assert.assertEquals(advocaciaResponse, null);
		}

		@Test
		public void testFetchAllAdvocacias()
		{
		Advocacia advocacia = new Advocacia();
			List<Advocacia> response = getEmpresaBAR().fetchAllAdvocacias(advocacia).getResultsList();
			Assert.assertNotNull(response);
		}

		@Test
		public void testDeleteAllAdvocacias()
		{
			getEmpresaBAR().deleteAllAdvocacias();
		Advocacia advocacia = new Advocacia();
			List<Advocacia> response = getEmpresaBAR().fetchAllAdvocacias(new Advocacia()).getResultsList();
			Assert.assertEquals(response.size(), 0);
		}

		@Test
		public void testUpdateAdvocacia()
		{
			Advocacia advocacia = new Advocacia(1, "NATIVE INSERT UPDATE");
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(1);
			Advocacia advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
			Assert.assertEquals(advocaciaResponse.getNome(), "NATIVE INSERT");
			getEmpresaBAR().updateAdvocacia(advocacia);
			advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
			Assert.assertEquals(advocaciaResponse.getNome(), "NATIVE INSERT UPDATE");
		}

		@Test
		public void testFetchAdvocaciasByRequest() throws Exception
		{
			// check for valid and precount
			AdvocaciaInquiryRequest request = new AdvocaciaInquiryRequest();
			request.setPreQueryCount(true);
			request.setStartPage(0);
			request.setPageSize(3);
			InternalResultsResponse<Advocacia> response = getEmpresaBAR().fetchAdvocaciasByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
			// check for valid and precount and start 2nd page
			request.setPreQueryCount(true);
			request.setStartPage(1);
			request.setPageSize(3);
			response = getEmpresaBAR().fetchAdvocaciasByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

			// check for valid and no precount
			AdvocaciaInquiryRequest request2 = new AdvocaciaInquiryRequest();
			request2.setPreQueryCount(false);
			InternalResultsResponse<Advocacia> response2 = getEmpresaBAR().fetchAdvocaciasByRequest(request2);
			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
			// this is because we did not choose to precount
			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

			// check for zero rows
			getEmpresaBAR().deleteAllAdvocacias();
			AdvocaciaInquiryRequest request3 = new AdvocaciaInquiryRequest();
			request3.setPreQueryCount(true);
			InternalResultsResponse<Advocacia> response3 = getEmpresaBAR().fetchAdvocaciasByRequest(request3);
			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

		}

		@Before
		public void setup()
		{
			executeSqlScript("conf/insertEmpresa.sql", false);
			executeSqlScript("conf/insertFilial.sql", false);
			executeSqlScript("conf/insertDeposito.sql", false);
			executeSqlScript("conf/insertUsuario.sql", false);
			executeSqlScript("conf/insertCondominio.sql", false);
			executeSqlScript("conf/insertClinica.sql", false);
			executeSqlScript("conf/insertAdvocacia.sql", false);
		}


}
