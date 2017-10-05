/** create by system gera-java version 1.0.0 01/05/2016 16:56 : 54*/
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
import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaInquiryRequest;
import com.qat.samples.sysmgmt.bar.Empresa.IEmpresaBAR;
import com.qat.samples.sysmgmt.clinica.model.Clinica;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.TransactionInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.Ajuda;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Field;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Menu;
import com.qat.samples.sysmgmt.entidade.model.Pagina;
import com.qat.samples.sysmgmt.entidade.model.Role;
import com.qat.samples.sysmgmt.entidade.model.Transaction;
import com.qat.samples.sysmgmt.entidade.model.UserRoles;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.Validacao;
import com.qat.samples.sysmgmt.entidade.model.criteria.FilialCriteria;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
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
		Empresa empresa = 		Objects.insertEmpresa(19000,TabelaEnum.EMPRESA,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(19000);

		Empresa empresaResponse = getEmpresaBAR().fetchEmpresaById(request);
		Assert.assertEquals(empresaResponse, null);
		getEmpresaBAR().insertEmpresa(empresa);
		empresaResponse = getEmpresaBAR().fetchEmpresaById(request);
		Assert.assertEquals(empresa.getId(), empresaResponse.getId());
		empresa = 		Objects.insertEmpresa(19000,TabelaEnum.EMPRESA,PersistenceActionEnum.DELETE);
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
		Empresa empresa = Objects.insertEmpresa(1004, TabelaEnum.EMPRESA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		Empresa empresaResponse = getEmpresaBAR().fetchEmpresaById(request);
		Assert.assertEquals(empresaResponse.getNome(), "nome_1");
		getEmpresaBAR().updateEmpresa(empresa);
		empresaResponse = getEmpresaBAR().fetchEmpresaById(request);
		Assert.assertEquals(empresaResponse.getNome(), "nome_1 - UPDATE");
	}

	@Test
	public void testFetchEmpresasByRequest() throws Exception
	{

		TransactionInquiryRequest request1 = new TransactionInquiryRequest();
		request1.setTransaction(new Transaction("tolen001Test",(new Date()).getTime(),"system"));
		request1.setPreQueryCount(true);
		request1.setStartPage(0);
		request1.setPageSize(3);

		InternalResultsResponse<Transaction> response1 = getEmpresaBAR().fetchTransactionById(request1);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response1.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response1.getResultsSetInfo().getTotalRowsAvailable() > 0);
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
		Filial filial = Objects.insertFilial(587574, TabelaEnum.FILIAL,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(587574);
		Filial filialResponse = getEmpresaBAR().fetchFilialById(request);
		Assert.assertEquals(filialResponse, null);
		getEmpresaBAR().insertFilial(filial);
		filialResponse = getEmpresaBAR().fetchFilialById(request);
		Assert.assertEquals(filial.getId(), filialResponse.getId());
		getEmpresaBAR().deleteFilialById(Objects.insertFilial(587574, TabelaEnum.FILIAL,PersistenceActionEnum.DELETE));
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
		Filial filial = Objects.insertFilial(1014, TabelaEnum.FILIAL,PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1014);
		Filial filialResponse = getEmpresaBAR().fetchFilialById(request);
		Assert.assertEquals(filialResponse.getNome(), "nome_1");
		getEmpresaBAR().updateFilial(filial);
		filialResponse = getEmpresaBAR().fetchFilialById(request);
		Assert.assertEquals(filialResponse.getNome(), "nome_1 - UPDATE");
	}

	@Test
	public void testFetchFilialsByRequest() throws Exception
	{
		// check for valid and precount
		FilialInquiryRequest request = new FilialInquiryRequest();
		FilialCriteria criteria =new FilialCriteria();
		criteria.setId(1014);
		request.setCriteria(criteria);
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
		Deposito deposito = new Deposito(19, "Deposito_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(19);
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
		Deposito deposito = new Deposito(1017, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1017);
		Deposito depositoResponse = getEmpresaBAR().fetchDepositoById(request);
		Assert.assertEquals(depositoResponse.getNome(), "nome_1");
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
		Usuario usuario = Objects.insertUsuario(4, TabelaEnum.USUARIO, PersistenceActionEnum.INSERT);
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
		Usuario usuario =  Objects.insertUsuario(10110, TabelaEnum.USUARIO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(10110);
		Usuario usuarioResponse = getEmpresaBAR().fetchUsuarioById(request);
		Assert.assertEquals(usuarioResponse.getEmail(), "username_2__4");
		getEmpresaBAR().updateUsuario(usuario);
		usuarioResponse = getEmpresaBAR().fetchUsuarioById(request);
		Assert.assertEquals(usuarioResponse.getEmail(), "econtabilsistemas@gmail.com");
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


//	//===================================### CONDOMINIO ####======================================


	@Test
		public void testDeleteCondominio()
		{
			Condominio condominio = new Condominio(19, "Condominio_999");
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(19);
			Condominio condominioResponse = getEmpresaBAR().fetchCondominioById(request);
			Assert.assertEquals(condominioResponse, null);
			getEmpresaBAR().insertCondominio(condominio);
			condominioResponse = getEmpresaBAR().fetchCondominioById(request);
//			Assert.assertEquals(condominio.getId(), condominioResponse.getId());
//			getEmpresaBAR().deleteCondominioById(condominio);
//			condominioResponse = getEmpresaBAR().fetchCondominioById(request);
//			Assert.assertEquals(condominioResponse, null);
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
			Condominio condominio = new Condominio(10116, "NATIVE INSERT UPDATE");
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(10116);
			Condominio condominioResponse = getEmpresaBAR().fetchCondominioById(request);
			Assert.assertEquals(condominioResponse.getNome(), "nome_1");
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
			Clinica clinica = new Clinica(19, "Clinica_999");
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(19);
			Clinica clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
			Assert.assertEquals(clinicaResponse, null);
			getEmpresaBAR().insertClinica(clinica);
			clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
//			Assert.assertEquals(clinica.getId(), clinicaResponse.getId());
//			getEmpresaBAR().deleteClinicaById(clinica);
//			clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
//			Assert.assertEquals(clinicaResponse, null);
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
			Clinica clinica = new Clinica(10113, "NATIVE INSERT UPDATE");
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(10113);
			Clinica clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
			Assert.assertEquals(clinicaResponse.getNome(), "nome_1");
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
			Advocacia advocacia = new Advocacia(19, "Advocacia_999");
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(19);
			Advocacia advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
			Assert.assertEquals(advocaciaResponse, null);
			getEmpresaBAR().insertAdvocacia(advocacia);
			advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
//			Assert.assertEquals(advocacia.getId(), advocaciaResponse.getId());
//			getEmpresaBAR().deleteAdvocaciaById(advocacia);
//			advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
//			Assert.assertEquals(advocaciaResponse, null);
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
			Advocacia advocacia = new Advocacia(10110, "NATIVE INSERT UPDATE");
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(10110);
			Advocacia advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
			Assert.assertEquals(advocaciaResponse.getNome(), "nome_1");
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
			executeSqlScript("conf/insertUserRoles.sql", false);
			executeSqlScript("conf/insertRoles.sql", false);
			executeSqlScript("conf/insertPagina.sql", false);
			executeSqlScript("conf/insertValidacao.sql", false);
			executeSqlScript("conf/insertField.sql", false);
			executeSqlScript("conf/insertAjuda.sql", false);
			executeSqlScript("conf/insertMenu.sql", false);
			executeSqlScript("conf/insertAdvocacia.sql", false);
			executeSqlScript("conf/insertClinica.sql", false);
			executeSqlScript("conf/insertCondominio.sql", false);
		}


	//===================================### USERROLES ####======================================


	@Test
		public void testDeleteUserRoles()
		{
			UserRoles userroles = Objects.insertUserRoles(1004, TabelaEnum.USERROLES, PersistenceActionEnum.INSERT);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(1004);
			UserRoles userrolesResponse = getEmpresaBAR().fetchUserRolesById(request);
			Assert.assertEquals(userrolesResponse, null);
			getEmpresaBAR().insertUserRoles(userroles);
			userrolesResponse = getEmpresaBAR().fetchUserRolesById(request);
			Assert.assertEquals(userroles.getUser_role_id(), userrolesResponse.getUser_role_id());
			getEmpresaBAR().deleteUserRolesById(userroles);
			userrolesResponse = getEmpresaBAR().fetchUserRolesById(request);
			Assert.assertEquals(userrolesResponse, null);
		}

		@Test
		public void testFetchAllUserRoless()
		{
		UserRoles userroles = new UserRoles();
			List<UserRoles> response = getEmpresaBAR().fetchAllUserRoless(userroles).getResultsList();
			Assert.assertNotNull(response);
		}

		@Test
		public void testDeleteAllUserRoless()
		{
			getEmpresaBAR().deleteAllUserRoless();
			UserRoles userroles = new UserRoles();
			List<UserRoles> response = getEmpresaBAR().fetchAllUserRoless(new UserRoles()).getResultsList();
			Assert.assertEquals(response.size(), 0);
		}

		@Test
		public void testUpdateUserRoles()
		{
			UserRoles userroles = Objects.insertUserRoles(1001, TabelaEnum.USERROLES, PersistenceActionEnum.UPDATE);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(1001);
			UserRoles userrolesResponse = getEmpresaBAR().fetchUserRolesById(request);
			Assert.assertEquals(userrolesResponse.getUsername(), "username_1");
			getEmpresaBAR().updateUserRoles(userroles);
			userrolesResponse = getEmpresaBAR().fetchUserRolesById(request);
			Assert.assertEquals(userrolesResponse.getUsername(), "NATIVE INSERT UPDATE");
		}

		@Test
		public void testFetchUserRolessByRequest() throws Exception
		{
			// check for valid and precount
			PagedInquiryRequest request = new PagedInquiryRequest();
			request.setPreQueryCount(true);
			request.setStartPage(0);
			request.setPageSize(3);
			InternalResultsResponse<UserRoles> response = getEmpresaBAR().fetchUserRolessByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
			// check for valid and precount and start 2nd page
			request.setPreQueryCount(true);
			request.setStartPage(1);
			request.setPageSize(3);
			response = getEmpresaBAR().fetchUserRolessByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

			// check for valid and no precount
			PagedInquiryRequest request2 = new PagedInquiryRequest();
			request2.setPreQueryCount(false);
			InternalResultsResponse<UserRoles> response2 = getEmpresaBAR().fetchUserRolessByRequest(request2);
			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
			// this is because we did not choose to precount
			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

			// check for zero rows
			getEmpresaBAR().deleteAllUserRoless();
			PagedInquiryRequest request3 = new PagedInquiryRequest();
			request3.setPreQueryCount(true);
			InternalResultsResponse<UserRoles> response3 = getEmpresaBAR().fetchUserRolessByRequest(request3);
			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

		}

	//===================================### ROLE ####======================================


	@Test
		public void testDeleteRole()
		{
			Role role = Objects.insertRole(1004, TabelaEnum.ROLE, PersistenceActionEnum.INSERT);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(1004);
			Role roleResponse = getEmpresaBAR().fetchRoleById(request);
			Assert.assertEquals(roleResponse, null);
			getEmpresaBAR().insertRole(role);
			roleResponse = getEmpresaBAR().fetchRoleById(request);
			Assert.assertEquals(role.getId(), roleResponse.getId());
			getEmpresaBAR().deleteRoleById(role);
			roleResponse = getEmpresaBAR().fetchRoleById(request);
			Assert.assertEquals(roleResponse, null);
		}

		@Test
		public void testFetchAllRoles()
		{
		Role role = new Role();
			List<Role> response = getEmpresaBAR().fetchAllRoles(role).getResultsList();
			Assert.assertNotNull(response);
		}

		@Test
		public void testDeleteAllRoles()
		{
			getEmpresaBAR().deleteAllRoles();
		Role role = new Role();
			List<Role> response = getEmpresaBAR().fetchAllRoles(new Role()).getResultsList();
			Assert.assertEquals(response.size(), 0);
		}

		@Test
		public void testUpdateRole()
		{
			Role role = Objects.insertRole(1001, TabelaEnum.ROLE, PersistenceActionEnum.UPDATE);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(1001);
			Role roleResponse = getEmpresaBAR().fetchRoleById(request);
			Assert.assertEquals(roleResponse.getRole(), "role_1");
			getEmpresaBAR().updateRole(role);
			roleResponse = getEmpresaBAR().fetchRoleById(request);
			Assert.assertEquals(roleResponse.getRole(), "NATIVE INSERT UPDATE");
		}

		@Test
		public void testFetchRolesByRequest() throws Exception
		{
			// check for valid and precount
			PagedInquiryRequest request = new PagedInquiryRequest();
			request.setPreQueryCount(true);
			request.setStartPage(0);
			request.setPageSize(3);
			InternalResultsResponse<Role> response = getEmpresaBAR().fetchRolesByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
			// check for valid and precount and start 2nd page
			request.setPreQueryCount(true);
			request.setStartPage(1);
			request.setPageSize(3);
			response = getEmpresaBAR().fetchRolesByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

			// check for valid and no precount
			PagedInquiryRequest request2 = new PagedInquiryRequest();
			request2.setPreQueryCount(false);
			InternalResultsResponse<Role> response2 = getEmpresaBAR().fetchRolesByRequest(request2);
			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
			// this is because we did not choose to precount
			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

			// check for zero rows
			getEmpresaBAR().deleteAllRoles();
			PagedInquiryRequest request3 = new PagedInquiryRequest();
			request3.setPreQueryCount(true);
			InternalResultsResponse<Role> response3 = getEmpresaBAR().fetchRolesByRequest(request3);
			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

		}

	//===================================### PAGINA ####======================================


	@Test
		public void testDeletePagina()
		{
			Pagina pagina = Objects.insertPagina(4001, TabelaEnum.PAGINA, PersistenceActionEnum.INSERT);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(4001);
			Pagina paginaResponse = getEmpresaBAR().fetchPaginaById(request);
			Assert.assertEquals(paginaResponse, null);
			getEmpresaBAR().insertPagina(pagina);
			paginaResponse = getEmpresaBAR().fetchPaginaById(request);
			Assert.assertEquals(pagina.getId(), paginaResponse.getId());
			getEmpresaBAR().deletePaginaById(pagina);
			paginaResponse = getEmpresaBAR().fetchPaginaById(request);
			Assert.assertEquals(paginaResponse, null);
		}

		@Test
		public void testFetchAllPaginas()
		{
		Pagina pagina = new Pagina();
			List<Pagina> response = getEmpresaBAR().fetchAllPaginas(pagina).getResultsList();
			Assert.assertNotNull(response);
		}

		@Test
		public void testDeleteAllPaginas()
		{
			getEmpresaBAR().deleteAllPaginas();
		Pagina pagina = new Pagina();
			List<Pagina> response = getEmpresaBAR().fetchAllPaginas(new Pagina()).getResultsList();
			Assert.assertEquals(response.size(), 0);
		}

//		@Test
//		public void testUpdatePagina()
//		{
//			Pagina pagina = Objects.insertPagina(1000, TabelaEnum.PAGINA, PersistenceActionEnum.UPDATE);
//			FetchByIdRequest request = new FetchByIdRequest();
//			request.setFetchId(1000);
//			Pagina paginaResponse = getEmpresaBAR().fetchPaginaById(request);
//			Assert.assertEquals(paginaResponse.getPagina(), "NATIVE INSERT");
//			getEmpresaBAR().updatePagina(pagina);
//			paginaResponse = getEmpresaBAR().fetchPaginaById(request);
//			Assert.assertEquals(paginaResponse.getPagina(), "NATIVE INSERT UPDATE");
//		}

		@Test
		public void testFetchPaginasByRequest() throws Exception
		{
			// check for valid and precount
			PagedInquiryRequest request = new PagedInquiryRequest();
			request.setPreQueryCount(true);
			request.setStartPage(0);
			request.setPageSize(3);
			InternalResultsResponse<Pagina> response = getEmpresaBAR().fetchPaginasByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() > 0);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() >= 0);
			// check for valid and precount and start 2nd page
			request.setPreQueryCount(true);
			request.setStartPage(1);
			request.setPageSize(3);
			response = getEmpresaBAR().fetchPaginasByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() > 0);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() >= 0);

			// check for valid and no precount
			PagedInquiryRequest request2 = new PagedInquiryRequest();
			request2.setPreQueryCount(false);
			InternalResultsResponse<Pagina> response2 = getEmpresaBAR().fetchPaginasByRequest(request2);
			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
			// this is because we did not choose to precount
			//Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() > 0);

			// check for zero rows
			getEmpresaBAR().deleteAllPaginas();
			PagedInquiryRequest request3 = new PagedInquiryRequest();
			request3.setPreQueryCount(true);
			InternalResultsResponse<Pagina> response3 = getEmpresaBAR().fetchPaginasByRequest(request3);
			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

		}

	//===================================### VALIDACAO ####======================================


	@Test
		public void testDeleteValidacao()
		{
			Validacao validacao = Objects.insertValidacao(4, TabelaEnum.VALIDACAO, PersistenceActionEnum.INSERT);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(4);
			Validacao validacaoResponse = getEmpresaBAR().fetchValidacaoById(request);
			Assert.assertEquals(validacaoResponse, null);
			getEmpresaBAR().insertValidacao(validacao);
			validacaoResponse = getEmpresaBAR().fetchValidacaoById(request);
			Assert.assertEquals(validacao.getId(), validacaoResponse.getId());
			getEmpresaBAR().deleteValidacaoById(validacao);
			validacaoResponse = getEmpresaBAR().fetchValidacaoById(request);
			Assert.assertEquals(validacaoResponse, null);
		}

		@Test
		public void testFetchAllValidacaos()
		{
		Validacao validacao = new Validacao();
			List<Validacao> response = getEmpresaBAR().fetchAllValidacaos(validacao).getResultsList();
			Assert.assertNotNull(response);
		}

		@Test
		public void testDeleteAllValidacaos()
		{
			getEmpresaBAR().deleteAllValidacaos();
		Validacao validacao = new Validacao();
			List<Validacao> response = getEmpresaBAR().fetchAllValidacaos(new Validacao()).getResultsList();
			Assert.assertEquals(response.size(), 0);
		}

		@Test
		public void testUpdateValidacao()
		{
			Validacao validacao = Objects.insertValidacao(1001, TabelaEnum.VALIDACAO, PersistenceActionEnum.UPDATE);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(1001);
			Validacao validacaoResponse = getEmpresaBAR().fetchValidacaoById(request);
			Assert.assertEquals(validacaoResponse.getError(), "error_1");
			getEmpresaBAR().updateValidacao(validacao);
			validacaoResponse = getEmpresaBAR().fetchValidacaoById(request);
			Assert.assertEquals(validacaoResponse.getError(), "NATIVE INSERT UPDATE");
		}

		@Test
		public void testFetchValidacaosByRequest() throws Exception
		{
			// check for valid and precount
			PagedInquiryRequest request = new PagedInquiryRequest();
			request.setPreQueryCount(true);
			request.setStartPage(0);
			request.setPageSize(3);
			InternalResultsResponse<Validacao> response = getEmpresaBAR().fetchValidacaosByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
			// check for valid and precount and start 2nd page
			request.setPreQueryCount(true);
			request.setStartPage(1);
			request.setPageSize(3);
			response = getEmpresaBAR().fetchValidacaosByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

			// check for valid and no precount
			PagedInquiryRequest request2 = new PagedInquiryRequest();
			request2.setPreQueryCount(false);
			InternalResultsResponse<Validacao> response2 = getEmpresaBAR().fetchValidacaosByRequest(request2);
			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
			// this is because we did not choose to precount
			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

			// check for zero rows
			getEmpresaBAR().deleteAllValidacaos();
			PagedInquiryRequest request3 = new PagedInquiryRequest();
			request3.setPreQueryCount(true);
			InternalResultsResponse<Validacao> response3 = getEmpresaBAR().fetchValidacaosByRequest(request3);
			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

		}

	//===================================### FIELD ####======================================


	@Test
		public void testDeleteField()
		{
			Field field = Objects.insertField(4, TabelaEnum.FIELD, PersistenceActionEnum.INSERT);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(4);
			Field fieldResponse = getEmpresaBAR().fetchFieldById(request);
			Assert.assertEquals(fieldResponse, null);
			getEmpresaBAR().insertField(field);
			fieldResponse = getEmpresaBAR().fetchFieldById(request);
			Assert.assertEquals(field.getId(), fieldResponse.getId());
			getEmpresaBAR().deleteFieldById(field);
			fieldResponse = getEmpresaBAR().fetchFieldById(request);
			Assert.assertEquals(fieldResponse, null);
		}

		@Test
		public void testFetchAllFields()
		{
		Field field = new Field();
			List<Field> response = getEmpresaBAR().fetchAllFields(field).getResultsList();
			Assert.assertNotNull(response);
		}

		@Test
		public void testDeleteAllFields()
		{
			getEmpresaBAR().deleteAllFields();
		Field field = new Field();
			List<Field> response = getEmpresaBAR().fetchAllFields(new Field()).getResultsList();
			Assert.assertEquals(response.size(), 0);
		}

		@Test
		public void testUpdateField()
		{
			Field field = Objects.insertField(1001, TabelaEnum.FIELD, PersistenceActionEnum.UPDATE);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(1001);
			Field fieldResponse = getEmpresaBAR().fetchFieldById(request);
			Assert.assertEquals(fieldResponse.getLabel(), "label_1");
			getEmpresaBAR().updateField(field);
			fieldResponse = getEmpresaBAR().fetchFieldById(request);
			Assert.assertEquals(fieldResponse.getLabel(), "NATIVE INSERT UPDATE");
		}

		@Test
		public void testFetchFieldsByRequest() throws Exception
		{
			// check for valid and precount
			FieldInquiryRequest request = new FieldInquiryRequest();
			request.setPreQueryCount(true);
			request.setStartPage(0);
			request.setPageSize(3);
			InternalResultsResponse<Field> response = getEmpresaBAR().fetchFieldsByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
			// check for valid and precount and start 2nd page
			request.setPreQueryCount(true);
			request.setStartPage(1);
			request.setPageSize(3);
			response = getEmpresaBAR().fetchFieldsByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

			// check for valid and no precount
			FieldInquiryRequest request2 = new FieldInquiryRequest();
			request2.setPreQueryCount(false);
			InternalResultsResponse<Field> response2 = getEmpresaBAR().fetchFieldsByRequest(request2);
			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
			// this is because we did not choose to precount
			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

			// check for zero rows
			getEmpresaBAR().deleteAllFields();
			FieldInquiryRequest request3 = new FieldInquiryRequest();
			request3.setPreQueryCount(true);
			InternalResultsResponse<Field> response3 = getEmpresaBAR().fetchFieldsByRequest(request3);
			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

		}

	//===================================### AJUDA ####======================================


	@Test
		public void testDeleteAjuda()
		{
			Ajuda ajuda = Objects.insertAjuda(4, TabelaEnum.AJUDA, PersistenceActionEnum.INSERT);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(4);
			Ajuda ajudaResponse = getEmpresaBAR().fetchAjudaById(request);
			Assert.assertEquals(ajudaResponse, null);
			getEmpresaBAR().insertAjuda(ajuda);
			ajudaResponse = getEmpresaBAR().fetchAjudaById(request);
			Assert.assertEquals(ajuda.getId(), ajudaResponse.getId());
			getEmpresaBAR().deleteAjudaById(ajuda);
			ajudaResponse = getEmpresaBAR().fetchAjudaById(request);
			Assert.assertEquals(ajudaResponse, null);
		}

		@Test
		public void testFetchAllAjudas()
		{
		Ajuda ajuda = new Ajuda();
			List<Ajuda> response = getEmpresaBAR().fetchAllAjudas(ajuda).getResultsList();
			Assert.assertNotNull(response);
		}

		@Test
		public void testDeleteAllAjudas()
		{
			getEmpresaBAR().deleteAllAjudas();
		Ajuda ajuda = new Ajuda();
			List<Ajuda> response = getEmpresaBAR().fetchAllAjudas(new Ajuda()).getResultsList();
			Assert.assertEquals(response.size(), 0);
		}

		@Test
		public void testUpdateAjuda()
		{
			Ajuda ajuda = Objects.insertAjuda(1001, TabelaEnum.AJUDA, PersistenceActionEnum.UPDATE);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(1001);
			Ajuda ajudaResponse = getEmpresaBAR().fetchAjudaById(request);
			Assert.assertEquals(ajudaResponse.getTexto(), "texto_1");
			getEmpresaBAR().updateAjuda(ajuda);
			ajudaResponse = getEmpresaBAR().fetchAjudaById(request);
			Assert.assertEquals(ajudaResponse.getTexto(), "NATIVE INSERT UPDATE");
		}

		@Test
		public void testFetchAjudasByRequest() throws Exception
		{
			// check for valid and precount
			PagedInquiryRequest request = new PagedInquiryRequest();
			request.setPreQueryCount(true);
			request.setStartPage(0);
			request.setPageSize(3);
			InternalResultsResponse<Ajuda> response = getEmpresaBAR().fetchAjudasByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
			// check for valid and precount and start 2nd page
			request.setPreQueryCount(true);
			request.setStartPage(1);
			request.setPageSize(3);
			response = getEmpresaBAR().fetchAjudasByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

			// check for valid and no precount
			PagedInquiryRequest request2 = new PagedInquiryRequest();
			request2.setPreQueryCount(false);
			InternalResultsResponse<Ajuda> response2 = getEmpresaBAR().fetchAjudasByRequest(request2);
			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
			// this is because we did not choose to precount
			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

			// check for zero rows
			getEmpresaBAR().deleteAllAjudas();
			PagedInquiryRequest request3 = new PagedInquiryRequest();
			request3.setPreQueryCount(true);
			InternalResultsResponse<Ajuda> response3 = getEmpresaBAR().fetchAjudasByRequest(request3);
			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

		}

	//===================================### MENU ####======================================


	@Test
		public void testDeleteMenu()
		{
			Menu menu = Objects.insertMenu(4, TabelaEnum.MENU, PersistenceActionEnum.INSERT);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(4);
			Menu menuResponse = getEmpresaBAR().fetchMenuById(request);
			Assert.assertEquals(menuResponse, null);
			getEmpresaBAR().insertMenu(menu);
			menuResponse = getEmpresaBAR().fetchMenuById(request);
			Assert.assertEquals(menu.getId(), menuResponse.getId());
			getEmpresaBAR().deleteMenuById(menu);
			menuResponse = getEmpresaBAR().fetchMenuById(request);
			Assert.assertEquals(menuResponse, null);
		}

		@Test
		public void testFetchAllMenus()
		{
		Menu menu = new Menu();
			List<Menu> response = getEmpresaBAR().fetchAllMenus(menu).getResultsList();
			Assert.assertNotNull(response);
		}

		@Test
		public void testDeleteAllMenus()
		{
			getEmpresaBAR().deleteAllMenus();
		Menu menu = new Menu();
			List<Menu> response = getEmpresaBAR().fetchAllMenus(new Menu()).getResultsList();
			Assert.assertEquals(response.size(), 0);
		}

		@Test
		public void testUpdateMenu()
		{
			Menu menu = Objects.insertMenu(1001, TabelaEnum.MENU, PersistenceActionEnum.UPDATE);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(1001);
			Menu menuResponse = getEmpresaBAR().fetchMenuById(request);
			Assert.assertEquals(menuResponse.getNome(), "nome_1");
			getEmpresaBAR().updateMenu(menu);
			menuResponse = getEmpresaBAR().fetchMenuById(request);
			Assert.assertEquals(menuResponse.getNome(), "NATIVE INSERT UPDATE");
		}

		@Test
		public void testFetchMenusByRequest() throws Exception
		{
			// check for valid and precount
			PagedInquiryRequest request = new PagedInquiryRequest();
			request.setPreQueryCount(true);
			request.setStartPage(0);
			request.setPageSize(3);
			InternalResultsResponse<Menu> response = getEmpresaBAR().fetchMenusByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
			// check for valid and precount and start 2nd page
			request.setPreQueryCount(true);
			request.setStartPage(1);
			request.setPageSize(3);
			response = getEmpresaBAR().fetchMenusByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

			// check for valid and no precount
			PagedInquiryRequest request2 = new PagedInquiryRequest();
			request2.setPreQueryCount(false);
			InternalResultsResponse<Menu> response2 = getEmpresaBAR().fetchMenusByRequest(request2);
			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
			// this is because we did not choose to precount
			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

			// check for zero rows
			getEmpresaBAR().deleteAllMenus();
			PagedInquiryRequest request3 = new PagedInquiryRequest();
			request3.setPreQueryCount(true);
			InternalResultsResponse<Menu> response3 = getEmpresaBAR().fetchMenusByRequest(request3);
			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

		}


}
