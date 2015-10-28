package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.documento.model.Documento;
import com.qat.samples.sysmgmt.endereco.model.Endereco;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.supermercado.dac.ISupermercadoDAC;
import com.qat.samples.sysmgmt.supermercado.model.Supermercado;
import com.qat.samples.sysmgmt.util.AcaoTypeEnum;
import com.qat.samples.sysmgmt.util.ControleAcess;
import com.qat.samples.sysmgmt.util.TableTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class SupermercadoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(SupermercadoDACTest.class);
	private ISupermercadoDAC supermercadoDAC; // injected by Spring through setter below

	public ISupermercadoDAC getSupermercadoDAC()
	{
		return supermercadoDAC;
	}

	@Resource
	public void setSupermercadoDAC(ISupermercadoDAC newValue)
	{
		supermercadoDAC = newValue;
	}

	@Test
	public void testInsertSupermercado() throws Exception
	{
		getSupermercadoDAC().deleteAllSupermercados();
		Supermercado supermercado = createSupermercado();
		getSupermercadoDAC().insertSupermercado(supermercado);
		FetchByIdRequest request = createFetchByIdRequest(supermercado.getId());
		Supermercado response = getSupermercadoDAC().fetchSupermercadoById(request);
		assertEquals(supermercado.getId(), response.getId());
		assertEquals(supermercado.getUsuario(), response.getUsuario());
		assertEquals(supermercado.getSenha(), response.getSenha());
	}

	@Test
	public void testUpdateSupermercado() throws Exception
	{
		getSupermercadoDAC().deleteAllSupermercados();
		Supermercado supermercado = createSupermercado();
		getSupermercadoDAC().insertSupermercado(supermercado);
		getSupermercadoDAC().updateSupermercado(supermercado);
		FetchByIdRequest request = createFetchByIdRequest(supermercado.getId());

	}

	@Test
	public void testDeleteAll() throws Exception
	{
		getSupermercadoDAC().deleteAllSupermercados();
		assertTrue(getSupermercadoDAC().fetchAllSupermercados().isEmpty());
	}

	@Test
	public void testFetchSupermercadosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Supermercado> response = getSupermercadoDAC().fetchSupermercadosByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check next page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(4);
		response = getSupermercadoDAC().fetchSupermercadosByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Supermercado> response2 = getSupermercadoDAC().fetchSupermercadosByRequest(request2);
		assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getSupermercadoDAC().deleteAllSupermercados();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Supermercado> response3 = getSupermercadoDAC().fetchSupermercadosByRequest(request3);
		assertTrue(response3.getStatus() == Status.NoRowsFoundError);

	}

	@Test
	public void testDeleteSupermercado() throws Exception
	{
		Supermercado supermercado = createSupermercado();
		getSupermercadoDAC().insertSupermercado(supermercado);
		FetchByIdRequest request = createFetchByIdRequest(supermercado.getId());
		assertNotNull(getSupermercadoDAC().fetchSupermercadoById(request));
		getSupermercadoDAC().deleteSupermercado(supermercado);
		assertNull(getSupermercadoDAC().fetchSupermercadoById(request));
	}

	private Supermercado createSupermercado()
	{

		List<Endereco> listendereco = new ArrayList<Endereco>();
		Endereco endereco = new Endereco();
		endereco.setBairro("bairro");
		endereco.setCep("cep");
		endereco.setCidade("cidade");
		endereco.setComplemento("complemento");
		endereco.setEnderecoid(1);
		endereco.setEstado("estado");
		endereco.setNome("estado");
		endereco.setId(1);
		endereco.setEnderecoid(1);
		endereco.setLogradouro("logradouro");
		endereco.setNumero("10");
		endereco.setTabela(TableTypeEnum.SUPERMERCADO);
		listendereco.add(endereco);

		List<Documento> listdocumento = new ArrayList<Documento>();
		Documento documento = new Documento();
		documento.setCpfCnpj("cpfCnpj");
		documento.setDateNascimento(new Date(0));
		documento.setDocumenroid(1);
		documento.setId(1);
		documento.setNome("nome");
		documento.setRazao("razao");
		documento.setRgInc("rgInc");
		documento.setTabela(TableTypeEnum.SUPERMERCADO);
		listdocumento.add(documento);

		List<ControleAcess> listcontrole = new ArrayList<ControleAcess>();
		ControleAcess controle = new ControleAcess();
		controle.setAcao(AcaoTypeEnum.INSERT);
		controle.setControleid(1);
		controle.setData(new Date(0));
		controle.setLocal("local");
		controle.setTableEnum(TableTypeEnum.SUPERMERCADO);
		controle.setTenantId(1);
		controle.setUser("koala");
		controle.setUserId(1);

		Supermercado supermercado = new Supermercado();
		supermercado.setAcessos(listcontrole);
		supermercado.setDocumentos(listdocumento);
		supermercado.setEmail("email");
		supermercado.setEnderecos(listendereco);
		supermercado.setSite("site");
		supermercado.setUsuario("usuario");
		supermercado.setSenha("senha");
		supermercado.setId(1);
		return supermercado;
	}

	private FetchByIdRequest createFetchByIdRequest(Integer value)
	{
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(value);
		return request;
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertSupermercado.sql", false);
	}

}
