package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

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

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.entidade.Usuario;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IUsuarioDAC;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class UsuarioDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(UsuarioDACTest.class);
	private IUsuarioDAC usuarioDAC; // injected by Spring through setter @resource

	// below

	public IUsuarioDAC getUsuarioDAC()
	{
		return usuarioDAC;
	}

	@Resource
	public void setUsuarioDAC(IUsuarioDAC usuarioDAC)
	{
		this.usuarioDAC = usuarioDAC;
	}

	@Test
	public void testupdateUsuario() throws Exception
	{

		Usuario funcionario = new Usuario();
		funcionario = insertUsuario(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Usuario> response = new InternalResultsResponse<Usuario>();
		Integer a = getUsuarioDAC().insertUsuario(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<Usuario>();

		a = getUsuarioDAC().updateUsuario(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertUsuario() throws Exception
	{

		Usuario funcionario = new Usuario();
		funcionario = insertUsuario(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Usuario> response = new InternalResultsResponse<Usuario>();

		Integer a = getUsuarioDAC().insertUsuario(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new Usuario();
		funcionario = insertUsuario(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<Usuario>();

		a = getUsuarioDAC().insertUsuario(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Usuario> responseA =
				getUsuarioDAC().fetchUsuarioById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteUsuario() throws Exception
	{

		Usuario funcionario = new Usuario();
		funcionario = insertUsuario(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Usuario> response = new InternalResultsResponse<Usuario>();
		Integer a = getUsuarioDAC().insertUsuario(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Usuario>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getUsuarioDAC().deleteUsuario(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Usuario> responseA =
				getUsuarioDAC().fetchUsuarioById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchUsuarioById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Usuario> response = getUsuarioDAC().fetchUsuarioById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchUsuarioById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Usuario> response = getUsuarioDAC().fetchUsuarioById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchUsuarioByRequest() throws Exception
	{
		// check for valid and precount
		UsuarioInquiryRequest request = new UsuarioInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Usuario> response = getUsuarioDAC().fetchUsuarioByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Usuario insertUsuario(PersistanceActionEnum action)
	{
		Usuario exame = new Usuario();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataUsuario((int)a.getTime());
		// exame.setMedicoResponsavel("Resposnsavel");
		// exame.setLaboratorio("Laboratorio");

		return exame;
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertBanco.sql", false);
	}
}
