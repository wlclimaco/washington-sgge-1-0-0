/** create by system gera-java version 1.0.0 01/05/2016 16:56 : 54*/
package com.qat.samples.sysmgmt.bar.mybatis;


import java.util.ArrayList;
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
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.BancoPessoa;
import com.qat.samples.sysmgmt.bar.Empresa.IEmpresaBAR;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.CfopParentId;
import com.qat.samples.sysmgmt.cfop.model.CfopTypeEnum;
import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.cnae.model.CnaeEmpresa;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.entidade.model.Boleto;
import com.qat.samples.sysmgmt.entidade.model.ConfigAlertas;
import com.qat.samples.sysmgmt.entidade.model.ConfigCarne;
import com.qat.samples.sysmgmt.entidade.model.ConfigEntrada;
import com.qat.samples.sysmgmt.entidade.model.ConfigFiscal;
import com.qat.samples.sysmgmt.entidade.model.ConfigGeral;
import com.qat.samples.sysmgmt.entidade.model.ConfigProduto;
import com.qat.samples.sysmgmt.entidade.model.ConfigSMTP;
import com.qat.samples.sysmgmt.entidade.model.ConfigVendas;
import com.qat.samples.sysmgmt.entidade.model.Configuracao;
import com.qat.samples.sysmgmt.entidade.model.ConfiguracaoNFe;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.EntidadeTypeEnum;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.site.model.ServicoAndPlano;
import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.Documento;
import com.qat.samples.sysmgmt.util.model.DocumentoTypeEnum;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.EmailTypeEnum;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.EnderecoTypeEnum;
import com.qat.samples.sysmgmt.util.model.Note;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.Telefone;
import com.qat.samples.sysmgmt.util.model.TelefoneTypeEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

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
		Empresa empresa = new Empresa();
		empresa = 		insertEmpresa(19,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(19);

		Empresa empresaResponse = getEmpresaBAR().fetchEmpresaById(request);
		Assert.assertEquals(empresaResponse, null);
		getEmpresaBAR().insertEmpresa(insertEmpresa(19,PersistenceActionEnum.INSERT));
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
//
//
//@Test
//	public void testDeleteFilial()
//	{
//		Filial filial = new Filial(19, "Filial_999");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(19);
//		Filial filialResponse = getEmpresaBAR().fetchFilialById(request);
//		Assert.assertEquals(filialResponse, null);
//		getEmpresaBAR().insertFilial(filial);
//		filialResponse = getEmpresaBAR().fetchFilialById(request);
//		Assert.assertEquals(filial.getId(), filialResponse.getId());
//		getEmpresaBAR().deleteFilialById(filial);
//		filialResponse = getEmpresaBAR().fetchFilialById(request);
//		Assert.assertEquals(filialResponse, null);
//	}
//
//	@Test
//	public void testFetchAllFilials()
//	{
//	Filial filial = new Filial();
//		List<Filial> response = getEmpresaBAR().fetchAllFilials(filial).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllFilials()
//	{
//		getEmpresaBAR().deleteAllFilials();
//	Filial filial = new Filial();
//		List<Filial> response = getEmpresaBAR().fetchAllFilials(new Filial()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateFilial()
//	{
//		Filial filial = new Filial(6, "NATIVE INSERT UPDATE");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(6);
//		Filial filialResponse = getEmpresaBAR().fetchFilialById(request);
//		Assert.assertEquals(filialResponse.getNome(), "nome_1");
//		getEmpresaBAR().updateFilial(filial);
//		filialResponse = getEmpresaBAR().fetchFilialById(request);
//		Assert.assertEquals(filialResponse.getNome(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchFilialsByRequest() throws Exception
//	{
//		// check for valid and precount
//		FilialInquiryRequest request = new FilialInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<Filial> response = getEmpresaBAR().fetchFilialsByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getEmpresaBAR().fetchFilialsByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		FilialInquiryRequest request2 = new FilialInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Filial> response2 = getEmpresaBAR().fetchFilialsByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getEmpresaBAR().deleteAllFilials();
//		FilialInquiryRequest request3 = new FilialInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Filial> response3 = getEmpresaBAR().fetchFilialsByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
////===================================### DEPOSITO ####======================================
//
//
//@Test
//	public void testDeleteDeposito()
//	{
//		Deposito deposito = new Deposito(19, "Deposito_999");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(19);
//		Deposito depositoResponse = getEmpresaBAR().fetchDepositoById(request);
//		Assert.assertEquals(depositoResponse, null);
//		getEmpresaBAR().insertDeposito(deposito);
//		depositoResponse = getEmpresaBAR().fetchDepositoById(request);
//		Assert.assertEquals(deposito.getId(), depositoResponse.getId());
//		getEmpresaBAR().deleteDepositoById(deposito);
//		depositoResponse = getEmpresaBAR().fetchDepositoById(request);
//		Assert.assertEquals(depositoResponse, null);
//	}
//
//	@Test
//	public void testFetchAllDepositos()
//	{
//	Deposito deposito = new Deposito();
//		List<Deposito> response = getEmpresaBAR().fetchAllDepositos(deposito).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllDepositos()
//	{
//		getEmpresaBAR().deleteAllDepositos();
//	Deposito deposito = new Deposito();
//		List<Deposito> response = getEmpresaBAR().fetchAllDepositos(new Deposito()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateDeposito()
//	{
//		Deposito deposito = new Deposito(7, "NATIVE INSERT UPDATE");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(7);
//		Deposito depositoResponse = getEmpresaBAR().fetchDepositoById(request);
//		Assert.assertEquals(depositoResponse.getNome(), "nome_1");
//		getEmpresaBAR().updateDeposito(deposito);
//		depositoResponse = getEmpresaBAR().fetchDepositoById(request);
//		Assert.assertEquals(depositoResponse.getNome(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchDepositosByRequest() throws Exception
//	{
//		// check for valid and precount
//		DepositoInquiryRequest request = new DepositoInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<Deposito> response = getEmpresaBAR().fetchDepositosByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getEmpresaBAR().fetchDepositosByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		DepositoInquiryRequest request2 = new DepositoInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Deposito> response2 = getEmpresaBAR().fetchDepositosByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getEmpresaBAR().deleteAllDepositos();
//		DepositoInquiryRequest request3 = new DepositoInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Deposito> response3 = getEmpresaBAR().fetchDepositosByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
////===================================### USUARIO ####======================================
//
//
//@Test
//	public void testDeleteUsuario()
//	{
//		Usuario usuario = new Usuario(4, "Usuario_999");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(4);
//		Usuario usuarioResponse = getEmpresaBAR().fetchUsuarioById(request);
//		Assert.assertEquals(usuarioResponse, null);
//		getEmpresaBAR().insertUsuario(usuario);
//		usuarioResponse = getEmpresaBAR().fetchUsuarioById(request);
//		Assert.assertEquals(usuario.getId(), usuarioResponse.getId());
//		getEmpresaBAR().deleteUsuarioById(usuario);
//		usuarioResponse = getEmpresaBAR().fetchUsuarioById(request);
//		Assert.assertEquals(usuarioResponse, null);
//	}
//
//	@Test
//	public void testFetchAllUsuarios()
//	{
//	Usuario usuario = new Usuario();
//		List<Usuario> response = getEmpresaBAR().fetchAllUsuarios(usuario).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllUsuarios()
//	{
//		getEmpresaBAR().deleteAllUsuarios();
//		Usuario usuario = new Usuario();
//		List<Usuario> response = getEmpresaBAR().fetchAllUsuarios(new Usuario()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateUsuario()
//	{
//		Usuario usuario = new Usuario(1, "NATIVE INSERT UPDATE");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1);
//		Usuario usuarioResponse = getEmpresaBAR().fetchUsuarioById(request);
//		Assert.assertEquals(usuarioResponse.getRole(), "role_6");
//		getEmpresaBAR().updateUsuario(usuario);
//		usuarioResponse = getEmpresaBAR().fetchUsuarioById(request);
//		Assert.assertEquals(usuarioResponse.getRole(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchUsuariosByRequest() throws Exception
//	{
//		// check for valid and precount
//		UsuarioInquiryRequest request = new UsuarioInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<Usuario> response = getEmpresaBAR().fetchUsuariosByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getEmpresaBAR().fetchUsuariosByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		UsuarioInquiryRequest request2 = new UsuarioInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Usuario> response2 = getEmpresaBAR().fetchUsuariosByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getEmpresaBAR().deleteAllUsuarios();
//		UsuarioInquiryRequest request3 = new UsuarioInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Usuario> response3 = getEmpresaBAR().fetchUsuariosByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
//
//	//===================================### CONDOMINIO ####======================================
//
//
//	@Test
//		public void testDeleteCondominio()
//		{
//			Condominio condominio = new Condominio(19, "Condominio_999");
//			FetchByIdRequest request = new FetchByIdRequest();
//			request.setFetchId(19);
//			Condominio condominioResponse = getEmpresaBAR().fetchCondominioById(request);
//			Assert.assertEquals(condominioResponse, null);
//			getEmpresaBAR().insertCondominio(condominio);
//			condominioResponse = getEmpresaBAR().fetchCondominioById(request);
//			Assert.assertEquals(condominio.getId(), condominioResponse.getId());
//			getEmpresaBAR().deleteCondominioById(condominio);
//			condominioResponse = getEmpresaBAR().fetchCondominioById(request);
//			Assert.assertEquals(condominioResponse, null);
//		}
//
//		@Test
//		public void testFetchAllCondominios()
//		{
//		Condominio condominio = new Condominio();
//			List<Condominio> response = getEmpresaBAR().fetchAllCondominios(condominio).getResultsList();
//			Assert.assertNotNull(response);
//		}
//
//		@Test
//		public void testDeleteAllCondominios()
//		{
//			getEmpresaBAR().deleteAllCondominios();
//		Condominio condominio = new Condominio();
//			List<Condominio> response = getEmpresaBAR().fetchAllCondominios(new Condominio()).getResultsList();
//			Assert.assertEquals(response.size(), 0);
//		}
//
//		@Test
//		public void testUpdateCondominio()
//		{
//			Condominio condominio = new Condominio(18, "NATIVE INSERT UPDATE");
//			FetchByIdRequest request = new FetchByIdRequest();
//			request.setFetchId(18);
//			Condominio condominioResponse = getEmpresaBAR().fetchCondominioById(request);
//			Assert.assertEquals(condominioResponse.getNome(), "nome_1");
//			getEmpresaBAR().updateCondominio(condominio);
//			condominioResponse = getEmpresaBAR().fetchCondominioById(request);
//			Assert.assertEquals(condominioResponse.getNome(), "NATIVE INSERT UPDATE");
//		}
//
//		@Test
//		public void testFetchCondominiosByRequest() throws Exception
//		{
//			// check for valid and precount
//			CondominioInquiryRequest request = new CondominioInquiryRequest();
//			request.setPreQueryCount(true);
//			request.setStartPage(0);
//			request.setPageSize(3);
//			InternalResultsResponse<Condominio> response = getEmpresaBAR().fetchCondominiosByRequest(request);
//			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//			// check for valid and precount and start 2nd page
//			request.setPreQueryCount(true);
//			request.setStartPage(1);
//			request.setPageSize(3);
//			response = getEmpresaBAR().fetchCondominiosByRequest(request);
//			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//			// check for valid and no precount
//			CondominioInquiryRequest request2 = new CondominioInquiryRequest();
//			request2.setPreQueryCount(false);
//			InternalResultsResponse<Condominio> response2 = getEmpresaBAR().fetchCondominiosByRequest(request2);
//			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//			// this is because we did not choose to precount
//			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//			// check for zero rows
//			getEmpresaBAR().deleteAllCondominios();
//			CondominioInquiryRequest request3 = new CondominioInquiryRequest();
//			request3.setPreQueryCount(true);
//			InternalResultsResponse<Condominio> response3 = getEmpresaBAR().fetchCondominiosByRequest(request3);
//			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//		}
//
//	//===================================### CLINICA ####======================================
//
//
//	@Test
//		public void testDeleteClinica()
//		{
//			Clinica clinica = new Clinica(19, "Clinica_999");
//			FetchByIdRequest request = new FetchByIdRequest();
//			request.setFetchId(19);
//			Clinica clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
//			Assert.assertEquals(clinicaResponse, null);
//			getEmpresaBAR().insertClinica(clinica);
//			clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
//			Assert.assertEquals(clinica.getId(), clinicaResponse.getId());
//			getEmpresaBAR().deleteClinicaById(clinica);
//			clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
//			Assert.assertEquals(clinicaResponse, null);
//		}
//
//		@Test
//		public void testFetchAllClinicas()
//		{
//		Clinica clinica = new Clinica();
//			List<Clinica> response = getEmpresaBAR().fetchAllClinicas(clinica).getResultsList();
//			Assert.assertNotNull(response);
//		}
//
//		@Test
//		public void testDeleteAllClinicas()
//		{
//			getEmpresaBAR().deleteAllClinicas();
//		Clinica clinica = new Clinica();
//			List<Clinica> response = getEmpresaBAR().fetchAllClinicas(new Clinica()).getResultsList();
//			Assert.assertEquals(response.size(), 0);
//		}
//
//		@Test
//		public void testUpdateClinica()
//		{
//			Clinica clinica = new Clinica(13, "NATIVE INSERT UPDATE");
//			FetchByIdRequest request = new FetchByIdRequest();
//			request.setFetchId(13);
//			Clinica clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
//			Assert.assertEquals(clinicaResponse.getNome(), "nome_1");
//			getEmpresaBAR().updateClinica(clinica);
//			clinicaResponse = getEmpresaBAR().fetchClinicaById(request);
//			Assert.assertEquals(clinicaResponse.getNome(), "NATIVE INSERT UPDATE");
//		}
//
//		@Test
//		public void testFetchClinicasByRequest() throws Exception
//		{
//			// check for valid and precount
//			ClinicaInquiryRequest request = new ClinicaInquiryRequest();
//			request.setPreQueryCount(true);
//			request.setStartPage(0);
//			request.setPageSize(3);
//			InternalResultsResponse<Clinica> response = getEmpresaBAR().fetchClinicasByRequest(request);
//			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//			// check for valid and precount and start 2nd page
//			request.setPreQueryCount(true);
//			request.setStartPage(1);
//			request.setPageSize(3);
//			response = getEmpresaBAR().fetchClinicasByRequest(request);
//			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//			// check for valid and no precount
//			ClinicaInquiryRequest request2 = new ClinicaInquiryRequest();
//			request2.setPreQueryCount(false);
//			InternalResultsResponse<Clinica> response2 = getEmpresaBAR().fetchClinicasByRequest(request2);
//			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//			// this is because we did not choose to precount
//			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//			// check for zero rows
//			getEmpresaBAR().deleteAllClinicas();
//			ClinicaInquiryRequest request3 = new ClinicaInquiryRequest();
//			request3.setPreQueryCount(true);
//			InternalResultsResponse<Clinica> response3 = getEmpresaBAR().fetchClinicasByRequest(request3);
//			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//		}
//
//	//===================================### ADVOCACIA ####======================================
//
//
//	@Test
//		public void testDeleteAdvocacia()
//		{
//			Advocacia advocacia = new Advocacia(19, "Advocacia_999");
//			FetchByIdRequest request = new FetchByIdRequest();
//			request.setFetchId(19);
//			Advocacia advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
//			Assert.assertEquals(advocaciaResponse, null);
//			getEmpresaBAR().insertAdvocacia(advocacia);
//			advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
//			Assert.assertEquals(advocacia.getId(), advocaciaResponse.getId());
//			getEmpresaBAR().deleteAdvocaciaById(advocacia);
//			advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
//			Assert.assertEquals(advocaciaResponse, null);
//		}
//
//		@Test
//		public void testFetchAllAdvocacias()
//		{
//		Advocacia advocacia = new Advocacia();
//			List<Advocacia> response = getEmpresaBAR().fetchAllAdvocacias(advocacia).getResultsList();
//			Assert.assertNotNull(response);
//		}
//
//		@Test
//		public void testDeleteAllAdvocacias()
//		{
//			getEmpresaBAR().deleteAllAdvocacias();
//			Advocacia advocacia = new Advocacia();
//			List<Advocacia> response = getEmpresaBAR().fetchAllAdvocacias(new Advocacia()).getResultsList();
//			Assert.assertEquals(response.size(), 3);
//		}
//
//		@Test
//		public void testUpdateAdvocacia()
//		{
//			Advocacia advocacia = new Advocacia(10, "NATIVE INSERT UPDATE");
//			FetchByIdRequest request = new FetchByIdRequest();
//			request.setFetchId(10);
//			Advocacia advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
//			Assert.assertEquals(advocaciaResponse.getNome(), "nome_1");
//			getEmpresaBAR().updateAdvocacia(advocacia);
//			advocaciaResponse = getEmpresaBAR().fetchAdvocaciaById(request);
//			Assert.assertEquals(advocaciaResponse.getNome(), "NATIVE INSERT UPDATE");
//		}
//
//		@Test
//		public void testFetchAdvocaciasByRequest() throws Exception
//		{
//			// check for valid and precount
//			AdvocaciaInquiryRequest request = new AdvocaciaInquiryRequest();
//			request.setPreQueryCount(true);
//			request.setStartPage(0);
//			request.setPageSize(3);
//			InternalResultsResponse<Advocacia> response = getEmpresaBAR().fetchAdvocaciasByRequest(request);
//			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//			// check for valid and precount and start 2nd page
//			request.setPreQueryCount(true);
//			request.setStartPage(1);
//			request.setPageSize(3);
//			response = getEmpresaBAR().fetchAdvocaciasByRequest(request);
//			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//			// check for valid and no precount
//			AdvocaciaInquiryRequest request2 = new AdvocaciaInquiryRequest();
//			request2.setPreQueryCount(false);
//			InternalResultsResponse<Advocacia> response2 = getEmpresaBAR().fetchAdvocaciasByRequest(request2);
//			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//			// this is because we did not choose to precount
//			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//			// check for zero rows
//			getEmpresaBAR().deleteAllAdvocacias();
//			AdvocaciaInquiryRequest request3 = new AdvocaciaInquiryRequest();
//			request3.setPreQueryCount(true);
//			InternalResultsResponse<Advocacia> response3 = getEmpresaBAR().fetchAdvocaciasByRequest(request3);
//			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//		}

		@Before
		public void setup()
		{
			executeSqlScript("conf/insertEmpresa.sql", false);
//			executeSqlScript("conf/insertFilial.sql", false);
//			executeSqlScript("conf/insertDeposito.sql", false);
//			executeSqlScript("conf/insertUsuario.sql", false);
//			executeSqlScript("conf/insertCondominio.sql", false);
//			executeSqlScript("conf/insertClinica.sql", false);
//			executeSqlScript("conf/insertAdvocacia.sql", false);
		}
		public List<Endereco> enderecoList(PersistenceActionEnum action)
		{
			List<Endereco> enderecoList = new ArrayList<Endereco>();
			Endereco endereco =
					new Endereco(null, "logradouro", new Cidade(), new Estado(), "bairro", "numero", "cep", "complemento",
							EnderecoTypeEnum.ENTREGA, action);
			endereco.setProcessId(1);
			enderecoList.add(endereco);

			endereco =
					new Endereco(null, "logradouro", new Cidade(), new Estado(), "bairro", "numero", "cep", "complemento",
							EnderecoTypeEnum.COBRANCA, action);
			endereco.setProcessId(1);
			enderecoList.add(endereco);

			endereco =
					new Endereco(null, "logradouro", new Cidade(), new Estado(), "bairro", "numero", "cep", "complemento",
							EnderecoTypeEnum.PRINCIPAL, action);
			endereco.setProcessId(1);
			enderecoList.add(endereco);
			return enderecoList;

		}

		public List<Documento> documentoList(PersistenceActionEnum action)
		{
			List<Documento> documentoList = new ArrayList<Documento>();
			Documento documento = new Documento(null, DocumentoTypeEnum.CNPJ, "numero", (long)123456789, new Estado(), action);
			documento.setProcessId(1);
			documentoList.add(documento);

			documento = new Documento(null, DocumentoTypeEnum.IE, "numero", (long)123456789, new Estado(), action);
			documento.setProcessId(1);
			documentoList.add(documento);

			documento = new Documento(null, DocumentoTypeEnum.IM, "numero", (long)123456789, new Estado(), action);
			documento.setProcessId(1);
			documentoList.add(documento);
			return documentoList;

		}

		public List<Email> emailList(PersistenceActionEnum action)
		{
			List<Email> documentoList = new ArrayList<Email>();
			Email documento = new Email(null, "email", EmailTypeEnum.COMPRAS, action);
			documento.setProcessId(1);
			documentoList.add(documento);
			documento = new Email(null, "email", EmailTypeEnum.NFE, action);
			documento.setProcessId(1);
			documentoList.add(documento);
			documento = new Email(null, "email", EmailTypeEnum.VENDAS, action);
			documento.setProcessId(1);
			documentoList.add(documento);
			return documentoList;

		}

		public List<Telefone> telefoneList(PersistenceActionEnum action)
		{
			List<Telefone> documentoList = new ArrayList<Telefone>();
			Telefone documento = new Telefone(null, "ddd", "numero", TelefoneTypeEnum.DIRETOR, action);
			documento.setProcessId(1);
			documentoList.add(documento);

			documento = new Telefone(null, "ddd", "numero", TelefoneTypeEnum.COMPRAS, action);
			documento.setProcessId(1);
			documentoList.add(documento);

			documento = new Telefone(null, "ddd", "numero", TelefoneTypeEnum.REPRESENTANTE, action);
			documento.setProcessId(1);
			documentoList.add(documento);

			return documentoList;

		}

		public List<BancoPessoa> bancoList(PersistenceActionEnum action)
		{
			List<BancoPessoa> documentoList = new ArrayList<BancoPessoa>();
			BancoPessoa documento = new BancoPessoa();
			documento.setModelAction(action);
			documento.setBancoId(new Banco(null, "ITAU", action));
			documento.setProcessId(1);
			documento.setSaldo(new Double(1.99));
			documentoList.add(documento);
			return documentoList;

		}

		public List<CfopParentId> cfopList(PersistenceActionEnum action)
		{
			List<CfopParentId> documentoList = new ArrayList<CfopParentId>();
			CfopParentId documento = new CfopParentId();
			documento.setModelAction(action);
			documento.setProcessId(1);
			documento.setCfop(new Cfop(null, "cfop", "natureza", "simplificado", CfopTypeEnum.ENTRADA, 0.39, 0.15, 0.54,
					0.9, 0.1, "observacao", action));
			documento.setParentId(1);

			documentoList.add(documento);
			return documentoList;

		}

		public List<Note> noteList(PersistenceActionEnum action)
		{
			List<Note> documentoList = new ArrayList<Note>();
			Note documento = new Note();
			documento.setModelAction(action);
			documento.setId(null);
			documento.setEmprId(1);
			documento.setNoteText("Test NOte");
			documento.setProcessId(1);
			documentoList.add(documento);
			return documentoList;

		}

		public List<CnaeEmpresa> cnaeList(PersistenceActionEnum action)
		{
			List<CnaeEmpresa> cnaeList = new ArrayList<CnaeEmpresa>();
			cnaeList.add(new CnaeEmpresa(null, 1, action, new Cnae(null, "CNAE", "DESCRICAO", "ABREV", action)));
			cnaeList.add(new CnaeEmpresa(null, 1, action, new Cnae(null, "CNAE", "DESCRICAO", "ABREV", action)));
			cnaeList.add(new CnaeEmpresa(null, 1, action, new Cnae(null, "CNAE", "DESCRICAO", "ABREV", action)));
			cnaeList.add(new CnaeEmpresa(null, 1, action, new Cnae(null, "CNAE", "DESCRICAO", "ABREV", action)));
			cnaeList.add(new CnaeEmpresa(null, 1, action, new Cnae(null, "CNAE", "DESCRICAO", "ABREV", action)));
			return cnaeList;

		}

		public Regime insertRegime(PersistenceActionEnum action)
		{

			Regime regime = new Regime();
			regime.setId(null);
			regime.setNome("Teste");
			regime.setDescricao("Teste");
			regime.setModelAction(action);
			return regime;
		}

		public List<Usuario> insertUsuario(PersistenceActionEnum action)
		{
			List<Usuario> list = new ArrayList<Usuario>();
			Date a = new Date();
			for (Integer i = 0; i < 10; i++)
			{
				Usuario usuario = new Usuario();
				usuario.setId(null);
				usuario.setEmail("LOGIN");
				usuario.setSenha("SENHA");
				usuario.setPergunta("PERGUNTA");
				usuario.setRole("ROLE");
				usuario.setLanguage("LAN");
				usuario.setUltAcesso(a.getTime());
				usuario.setEmails(emailList(action));
				usuario.setModelAction(action);
				list.add(usuario);
			}
			return list;

		}

		public Empresa insertEmpresa(Integer id,PersistenceActionEnum action)
		{
			Empresa funcionario = new Empresa();
			Date a = new Date();

			funcionario.setId(id);
			funcionario.setNome("NOME");
			funcionario.setRegime(insertRegime(action));
			funcionario.setEntidadeId(1);
			funcionario.setEntidadeEnum(EntidadeTypeEnum.EMPRESA);
			funcionario.setConfiguracao(insertConfiguracao(id,TabelaEnum.EMPRESA,action));
			funcionario.setCnaes(cnaeList(action));
			funcionario.setUsuarios(insertUsuario(action));
			funcionario.setProcessId(1);
			funcionario.setDocumentos(documentoList(action));
			funcionario.setTelefones(telefoneList(action));
			funcionario.setEmails(emailList(action));
			funcionario.setEnderecos(enderecoList(action));
			funcionario.setBancos(bancoList(action));
			funcionario.setSocios(new ArrayList<Socio>());
			funcionario.setPlanosServicos(new ArrayList<ServicoAndPlano>());
			funcionario.getPlanosServicos().add(insertPlano(id,TabelaEnum.EMPRESA,action));
			funcionario.getSocios().add(insertSocios(19,action));

			funcionario.setNotes(noteList(action));
			funcionario.setModelAction(action);

			return funcionario;
		}

		public Filial insertFilial(Integer id,PersistenceActionEnum action)
		{
			Filial funcionario = new Filial();
			Date a = new Date();

			funcionario.setId(null);
			funcionario.setNome("NOME");
			funcionario.setRegime(insertRegime(action));
			funcionario.setEntidadeId(1);
			funcionario.setEntidadeEnum(EntidadeTypeEnum.FILIAL);
			funcionario.setConfiguracao(insertConfiguracao(id,TabelaEnum.EMPRESA,action));
			funcionario.setCnaes(cnaeList(action));
			funcionario.setProcessId(1);
			funcionario.setDocumentos(documentoList(action));
			funcionario.setTelefones(telefoneList(action));
			funcionario.setEmails(emailList(action));
			funcionario.setEnderecos(enderecoList(action));
			funcionario.setNotes(noteList(action));
			funcionario.setModelAction(action);

			return funcionario;
		}

		public Deposito insertDeposito(Integer id,PersistenceActionEnum action)
		{
			Deposito funcionario = new Deposito();
			Date a = new Date();

			funcionario.setId(null);
			funcionario.setNome("NOME");
			funcionario.setRegime(insertRegime(action));
			funcionario.setEntidadeId(1);
			funcionario.setEntidadeEnum(EntidadeTypeEnum.DEPOSITO);
			funcionario.setConfiguracao(insertConfiguracao(id,TabelaEnum.EMPRESA,action));
			funcionario.setCnaes(cnaeList(action));
			funcionario.setProcessId(1);
			funcionario.setDocumentos(documentoList(action));
			funcionario.setTelefones(telefoneList(action));
			funcionario.setEmails(emailList(action));
			funcionario.setEnderecos(enderecoList(action));
			funcionario.setNotes(noteList(action));
			funcionario.setModelAction(action);

			return funcionario;
		}

		public Cidade insertCidade(PersistenceActionEnum action)
		{
			Cidade cidade = new Cidade();
			Date a = new Date();

			cidade.setId(1);
			cidade.setCodigo("CODIGO");
			cidade.setNome("NOME");
			cidade.setCdIBGE("CDIBGE");
			cidade.setEstado(new Estado(1));
			cidade.setCep("CEP");
			cidade.setNotes(noteList(action));

			return cidade;
		}

		public Socio insertSocios(Integer id,PersistenceActionEnum action)
		{
			Socio cidade = new Socio();
			Date a = new Date();

			cidade.setId(id);
			cidade.setDocumentos(documentoList(action));
			cidade.setNome("NOME");
			cidade.setPorcentagem("100%");
			cidade.setCota("10");
			cidade.setParentId(id);
			cidade.setEmprId(1);
			cidade.setModifyDateUTC(a.getTime());
			cidade.setCreateDateUTC(a.getTime());
			cidade.setCreateUser("system");
			cidade.setModifyUser("system");
			cidade.setProcessId(1);
			cidade.setModelAction(action);


			return cidade;
		}

		public ServicoAndPlano insertPlano(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ServicoAndPlano plano = new ServicoAndPlano();
			Date a = new Date();
			plano.setId(id);
			plano.setDataInicio(a.getTime());
			plano.setValor(new Double(1.99));
			plano.setServicoPlanoEnumValue(1);
			plano.setServicoList(new Servico(1,"tanga"));
			plano.setPlanoList(new Plano(2,"Tanga"));
			plano.setParentId(id);
			plano.setEmprId(1);
			plano.setModifyDateUTC(a.getTime());
			plano.setCreateDateUTC(a.getTime());
			plano.setCreateUser("system");
			plano.setModifyUser("system");
			plano.setProcessId(1);
			plano.setModelAction(action);

			return plano;
		}
		
		public Configuracao insertConfiguracao(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Configuracao configuracao = new Configuracao();
			Date a = new Date();
			configuracao.setId(100);
			configuracao.setConfGeral(insertConfigGeral(id,TabelaEnum.BOLETO,action));
			configuracao.setConfNFe(insertConfiguracaoNFe(id,TabelaEnum.BOLETO,action));
			configuracao.setConfFiscal(insertConfigFiscal(id,TabelaEnum.BOLETO,action));
			configuracao.setConfProd(insertConfigProduto(id,TabelaEnum.BOLETO,action));
			configuracao.setConfVendas(insertConfigVendas(id,TabelaEnum.BOLETO,action));
			configuracao.setConfCMTP(insertConfigSMTP(id,TabelaEnum.BOLETO,action));
			configuracao.setConfEntrada(insertConfigEntrada(id,TabelaEnum.BOLETO,action));
			configuracao.setConfCarne(insertConfigCarne(id,TabelaEnum.BOLETO,action));
			configuracao.setBoletoList(new ArrayList<Boleto>());
			configuracao.getBoletoList().add(insertBoleto(id,TabelaEnum.BOLETO,action));
			configuracao.setParentId(id);
			configuracao.setEmprId(1);
			configuracao.setModifyDateUTC(a.getTime());
			configuracao.setCreateDateUTC(a.getTime());
			configuracao.setCreateUser("system");
			configuracao.setModifyUser("system");
			configuracao.setProcessId(1);
			configuracao.setModelAction(action);
	
			return configuracao;
		}

	
	public Boleto insertBoleto(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Boleto boleto = new Boleto();
			Date a = new Date();
			boleto.setId(100);
			boleto.setAtivarBolOnLine(Boolean.FALSE);
			boleto.setTipoBoleto(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			boleto.setAgencia(100);
			boleto.setCedente(100);
			boleto.setJuros(new Double(1.99));
			boleto.setTipoCalcMora(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			boleto.setMora(new Double(1.99));
			boleto.setInstrucoes("NATIVE INSERT UPDATE");
			boleto.setDemonstrativo("NATIVE INSERT UPDATE");
			boleto.setImpJuros(Boolean.FALSE);
			boleto.setParentId(id);
			boleto.setEmprId(1);
			boleto.setModifyDateUTC(a.getTime());
			boleto.setCreateDateUTC(a.getTime());
			boleto.setCreateUser("system");
			boleto.setModifyUser("system");
			boleto.setProcessId(1);
			boleto.setModelAction(action);
	
			return boleto;
		}

	
	public ConfigCarne insertConfigCarne(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigCarne ConfigCarne = new ConfigCarne();
			Date a = new Date();
			ConfigCarne.setId(100);
			ConfigCarne.setCarneBotelo(Boolean.FALSE);
			ConfigCarne.setCarneNormal(Boolean.FALSE);
			ConfigCarne.setParentId(id);
			ConfigCarne.setEmprId(1);
			ConfigCarne.setModifyDateUTC(a.getTime());
			ConfigCarne.setCreateDateUTC(a.getTime());
			ConfigCarne.setCreateUser("system");
			ConfigCarne.setModifyUser("system");
			ConfigCarne.setProcessId(1);
			ConfigCarne.setModelAction(action);
	
			return ConfigCarne;
		}

	
	public ConfigEntrada insertConfigEntrada(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigEntrada ConfigEntrada = new ConfigEntrada();
			Date a = new Date();
			ConfigEntrada.setId(100);
			ConfigEntrada.setValorTotalFixo(Boolean.FALSE);
			ConfigEntrada.setManterPrecoVendaProd(Boolean.FALSE);
			ConfigEntrada.setParentId(id);
			ConfigEntrada.setEmprId(1);
			ConfigEntrada.setModifyDateUTC(a.getTime());
			ConfigEntrada.setCreateDateUTC(a.getTime());
			ConfigEntrada.setCreateUser("system");
			ConfigEntrada.setModifyUser("system");
			ConfigEntrada.setProcessId(1);
			ConfigEntrada.setModelAction(action);
	
			return ConfigEntrada;
		}

	
	public ConfigFiscal insertConfigFiscal(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigFiscal ConfigFiscal = new ConfigFiscal();
			Date a = new Date();
			ConfigFiscal.setId(100);
			ConfigFiscal.setPrincAtividade(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigFiscal.setRegime(new Regime());
			ConfigFiscal.setAliqSimples(new Double(1.99));
			ConfigFiscal.setParentId(id);
			ConfigFiscal.setEmprId(1);
			ConfigFiscal.setModifyDateUTC(a.getTime());
			ConfigFiscal.setCreateDateUTC(a.getTime());
			ConfigFiscal.setCreateUser("system");
			ConfigFiscal.setModifyUser("system");
			ConfigFiscal.setProcessId(1);
			ConfigFiscal.setModelAction(action);
	
			return ConfigFiscal;
		}

	
	public ConfigAlertas insertConfigAlertas(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigAlertas ConfigAlertas = new ConfigAlertas();
			Date a = new Date();
			ConfigAlertas.setId(100);
			ConfigAlertas.setEstoqMin(Boolean.FALSE);
			ConfigAlertas.setEstoqMax(Boolean.FALSE);
			ConfigAlertas.setErroNFe(Boolean.FALSE);
			ConfigAlertas.setPdCompra(Boolean.FALSE);
			ConfigAlertas.setParentId(id);
			ConfigAlertas.setEmprId(1);
			ConfigAlertas.setModifyDateUTC(a.getTime());
			ConfigAlertas.setCreateDateUTC(a.getTime());
			ConfigAlertas.setCreateUser("system");
			ConfigAlertas.setModifyUser("system");
			ConfigAlertas.setProcessId(1);
			ConfigAlertas.setModelAction(action);
	
			return ConfigAlertas;
		}

	
	public ConfigGeral insertConfigGeral(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigGeral ConfigGeral = new ConfigGeral();
			Date a = new Date();
			ConfigGeral.setId(100);
			ConfigGeral.setFusoHorario(100);
			ConfigGeral.setCasasDecimais(100);
			ConfigGeral.setDiasCartaCobr(100);
			ConfigGeral.setInfPosicionarMouse(Boolean.FALSE);
			ConfigGeral.setCnpjCPFUnico(Boolean.FALSE);
			ConfigGeral.setImpCodPersonalizado(Boolean.FALSE);
			ConfigGeral.setLogListRelImp(Boolean.FALSE);
			ConfigGeral.setObsProdFinProd(Boolean.FALSE);
			ConfigGeral.setParentId(id);
			ConfigGeral.setEmprId(1);
			ConfigGeral.setModifyDateUTC(a.getTime());
			ConfigGeral.setCreateDateUTC(a.getTime());
			ConfigGeral.setCreateUser("system");
			ConfigGeral.setModifyUser("system");
			ConfigGeral.setProcessId(1);
			ConfigGeral.setModelAction(action);
	
			return ConfigGeral;
		}

	
	public ConfigProduto insertConfigProduto(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigProduto ConfigProduto = new ConfigProduto();
			Date a = new Date();
			ConfigProduto.setId(100);
			ConfigProduto.setCfop(new Cfop());
			ConfigProduto.setIcmsSitTrib(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIcmsOrigem(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIcmsModalidadeBC(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIcmsRedBaseCalc(new Double(1.99));
			ConfigProduto.setIcmsAliq(new Double(1.99));
			ConfigProduto.setIcmsMotDesoneracao(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIcmsModBCST(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIcmsMargValAdic(new Double(1.99));
			ConfigProduto.setIcmsRedBaseCalcST(new Double(1.99));
			ConfigProduto.setIcmsPrecoUnitPautaST(new Double(1.99));
			ConfigProduto.setIcmsAliqST(new Double(1.99));
			ConfigProduto.setIpiSitTrib(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIpiClasCigarroBebida(new Double(1.99));
			ConfigProduto.setIpiCNPJProd("NATIVE INSERT UPDATE");
			ConfigProduto.setIpiCodSeloCont("NATIVE INSERT UPDATE");
			ConfigProduto.setIpiQtdSelo(new Double(1.99));
			ConfigProduto.setIpiCodEnquad(100);
			ConfigProduto.setIpiTipCalc(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIpiAliq(new Double(1.99));
			ConfigProduto.setPisSitTrib(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setPisAliq(new Double(1.99));
			ConfigProduto.setPisValUnidtrib(new Double(1.99));
			ConfigProduto.setPistipoCalcSubstTrib(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setPisAliqST(new Double(1.99));
			ConfigProduto.setPisValorAliqST(new Double(1.99));
			ConfigProduto.setCofinsSubstTrib(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setCofinsAliq(new Double(1.99));
			ConfigProduto.setCofinsValorAliq(new Double(1.99));
			ConfigProduto.setCofinsTipoCalcSubstTrib(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setCofinsAliqST(new Double(1.99));
			ConfigProduto.setCofinsValorAliqST(new Double(1.99));
			ConfigProduto.setParentId(id);
			ConfigProduto.setEmprId(1);
			ConfigProduto.setModifyDateUTC(a.getTime());
			ConfigProduto.setCreateDateUTC(a.getTime());
			ConfigProduto.setCreateUser("system");
			ConfigProduto.setModifyUser("system");
			ConfigProduto.setProcessId(1);
			ConfigProduto.setModelAction(action);
	
			return ConfigProduto;
		}

	
	public ConfigSMTP insertConfigSMTP(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigSMTP ConfigSMTP = new ConfigSMTP();
			Date a = new Date();
			ConfigSMTP.setId(100);
			ConfigSMTP.setServSMTP("NATIVE INSERT UPDATE");
			ConfigSMTP.setPorta("NATIVE INSERT UPDATE");
			ConfigSMTP.setEndEmail("NATIVE INSERT UPDATE");
			ConfigSMTP.setUsuario("NATIVE INSERT UPDATE");
			ConfigSMTP.setSenha("NATIVE INSERT UPDATE");
			ConfigSMTP.setSeguranca(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigSMTP.setParentId(id);
			ConfigSMTP.setEmprId(1);
			ConfigSMTP.setModifyDateUTC(a.getTime());
			ConfigSMTP.setCreateDateUTC(a.getTime());
			ConfigSMTP.setCreateUser("system");
			ConfigSMTP.setModifyUser("system");
			ConfigSMTP.setProcessId(1);
			ConfigSMTP.setModelAction(action);
	
			return ConfigSMTP;
		}

	
	public ConfiguracaoNFe insertConfiguracaoNFe(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfiguracaoNFe ConfiguracaoNFe = new ConfiguracaoNFe();
			Date a = new Date();
			ConfiguracaoNFe.setId(100);
			ConfiguracaoNFe.setPresCompr(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfiguracaoNFe.setDestConsFinal(Boolean.FALSE);
			ConfiguracaoNFe.setPreencherDataHora(Boolean.FALSE);
			ConfiguracaoNFe.setIcmsPadrao(new Double(1.99));
			ConfiguracaoNFe.setIpiPadrao(new Double(1.99));
			ConfiguracaoNFe.setPisPadrao(new Double(1.99));
			ConfiguracaoNFe.setCofinsPadrao(new Double(1.99));
			ConfiguracaoNFe.setAmbienteEnvio(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfiguracaoNFe.setServMsmNota(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfiguracaoNFe.setSerieEnvio("NATIVE INSERT UPDATE");
			ConfiguracaoNFe.setAnexarXmlEmail(Boolean.FALSE);
			ConfiguracaoNFe.setIdCSC("NATIVE INSERT UPDATE");
			ConfiguracaoNFe.setcSC("NATIVE INSERT UPDATE");
			ConfiguracaoNFe.setInformacaoAdd("NATIVE INSERT UPDATE");
			ConfiguracaoNFe.setCertificado("NATIVE INSERT UPDATE");
			ConfiguracaoNFe.setSenha("NATIVE INSERT UPDATE");
			ConfiguracaoNFe.setSalvarSenha(Boolean.FALSE);
			ConfiguracaoNFe.setCfopPadrao(new Cfop());
			ConfiguracaoNFe.setConfSMTP(new ConfigSMTP());
			ConfiguracaoNFe.setParentId(id);
			ConfiguracaoNFe.setEmprId(1);
			ConfiguracaoNFe.setModifyDateUTC(a.getTime());
			ConfiguracaoNFe.setCreateDateUTC(a.getTime());
			ConfiguracaoNFe.setCreateUser("system");
			ConfiguracaoNFe.setModifyUser("system");
			ConfiguracaoNFe.setProcessId(1);
			ConfiguracaoNFe.setModelAction(action);
	
			return ConfiguracaoNFe;
		}

	
	public ConfigVendas insertConfigVendas(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigVendas ConfigVendas = new ConfigVendas();
			Date a = new Date();
			ConfigVendas.setId(100);
			ConfigVendas.setDescontoMaxVenda(new Double(1.99));
			ConfigVendas.setObservacao("observação");
			ConfigVendas.setImprSegVia(Boolean.FALSE);
			ConfigVendas.setImprAssinatura(Boolean.FALSE);
			ConfigVendas.setImprResumoFinanc(Boolean.FALSE);
			ConfigVendas.setAtuaPrecoClonar(Boolean.FALSE);
			ConfigVendas.setImprColUnidade(Boolean.FALSE);
			ConfigVendas.setBloquearvendProdSemEstoq(Boolean.FALSE);
			ConfigVendas.setAddDespCalcImposto(Boolean.FALSE);
			ConfigVendas.setRetSubstTribICMS(Boolean.FALSE);
			ConfigVendas.setParentId(id);
			ConfigVendas.setEmprId(1);
			ConfigVendas.setModifyDateUTC(a.getTime());
			ConfigVendas.setCreateDateUTC(a.getTime());
			ConfigVendas.setCreateUser("system");
			ConfigVendas.setModifyUser("system");
			ConfigVendas.setProcessId(1);
			ConfigVendas.setModelAction(action);
	
			return ConfigVendas;
		}
	
	public DoisValores insertDoisValor(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		DoisValores ConfigVendas = new DoisValores();
		Date a = new Date();
		ConfigVendas.setId(100);
		ConfigVendas.setNome("teste");
		ConfigVendas.setDescricao("Description");
		ConfigVendas.setParentId(id);
		ConfigVendas.setEmprId(1);
		ConfigVendas.setModifyDateUTC(a.getTime());
		ConfigVendas.setCreateDateUTC(a.getTime());
		ConfigVendas.setCreateUser("system");
		ConfigVendas.setModifyUser("system");
		ConfigVendas.setProcessId(1);
		ConfigVendas.setModelAction(action);

		return ConfigVendas;
	}

}
