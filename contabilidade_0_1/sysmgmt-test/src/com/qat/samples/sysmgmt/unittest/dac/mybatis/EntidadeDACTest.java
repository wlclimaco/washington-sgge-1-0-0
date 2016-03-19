package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
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

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.banco.Banco;
import com.qat.samples.sysmgmt.banco.BancoPessoa;
import com.qat.samples.sysmgmt.cfop.Cfop;
import com.qat.samples.sysmgmt.cfop.CfopPessoa;
import com.qat.samples.sysmgmt.cfop.CfopTypeEnum;
import com.qat.samples.sysmgmt.cnae.Cnae;
import com.qat.samples.sysmgmt.cnae.CnaeEmpresa;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.contabilidade.Plano;
import com.qat.samples.sysmgmt.entidade.Deposito;
import com.qat.samples.sysmgmt.entidade.Empresa;
import com.qat.samples.sysmgmt.entidade.EntidadeTypeEnum;
import com.qat.samples.sysmgmt.entidade.Filial;
import com.qat.samples.sysmgmt.entidade.Usuario;
import com.qat.samples.sysmgmt.entidade.dac.IEmpresaDAC;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.estado.Estado;
import com.qat.samples.sysmgmt.fiscal.Classificacao;
import com.qat.samples.sysmgmt.fiscal.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.Configuracao;
import com.qat.samples.sysmgmt.util.Documento;
import com.qat.samples.sysmgmt.util.DocumentoTypeEnum;
import com.qat.samples.sysmgmt.util.Email;
import com.qat.samples.sysmgmt.util.EmailTypeEnum;
import com.qat.samples.sysmgmt.util.Endereco;
import com.qat.samples.sysmgmt.util.EnderecoTypeEnum;
import com.qat.samples.sysmgmt.util.Note;
import com.qat.samples.sysmgmt.util.Telefone;
import com.qat.samples.sysmgmt.util.TelefoneTypeEnum;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class EntidadeDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(EntidadeDACTest.class);
	private IEmpresaDAC entidadeDAC; // injected by Spring through setter @resource

	// below

	public IEmpresaDAC getEntidadeDAC()
	{
		return entidadeDAC;
	}

	@Resource
	public void setEntidadeDAC(IEmpresaDAC entidadeDAC)
	{
		this.entidadeDAC = entidadeDAC;
	}

	@Test
	public void testupdateEmpresa() throws Exception
	{

		Empresa funcionario = new Empresa();
		funcionario = insertEmpresa(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Empresa> funcionarioResponse = getEntidadeDAC().insertEmpresa(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
		funcionario = funcionarioResponse.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(funcionarioResponse.getFirstResult().getId());
		funcionarioResponse = getEntidadeDAC().updateEmpresa(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertEmpresa() throws Exception
	{

		Empresa funcionario = new Empresa();
		funcionario = insertEmpresa(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Empresa> funcionarioResponse = getEntidadeDAC().insertEmpresa(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(funcionarioResponse.getFirstResult().getId());
		InternalResultsResponse<Empresa> responseA = getEntidadeDAC().fetchEmpresaById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		// assertTrue(responseA.getResultsList().get(0).getRegime().getNome() == funcionario.getRegime().getNome());
		assertTrue(responseA.getResultsList().get(0).getDocumentos().size() == funcionario.getDocumentos().size());
		assertTrue(responseA.getResultsList().get(0).getEnderecos().size() == funcionario.getEnderecos().size());
		assertTrue(responseA.getResultsList().get(0).getEmails().size() == funcionario.getEmails().size());
		assertTrue(responseA.getResultsList().get(0).getTelefones().size() == funcionario.getTelefones().size());
		assertTrue(responseA.getResultsList().get(0).getCnaes().size() == funcionario.getCnaes().size());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.ANALISANDO);
		// assertTrue(responseA.getResultsList().get(0).getNotes().size() == funcionario.getNotes().size());

	}

	@Test
	public void testdeleteEmpresa() throws Exception
	{

		Empresa funcionario = new Empresa();
		funcionario = insertEmpresa(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Empresa> funcionarioResponse = getEntidadeDAC().insertEmpresa(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
		funcionario = funcionarioResponse.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getEntidadeDAC().deleteEmpresa(funcionario);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(funcionarioResponse.getFirstResult().getId());
		InternalResultsResponse<Empresa> responseA = getEntidadeDAC().fetchEmpresaById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);
	}

	@Test
	public void testfetchEmpresaById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Empresa> response = getEntidadeDAC().fetchEmpresaById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchEmpresaByRequest() throws Exception
	{
		// check for valid and precount
		EmpresaInquiryRequest request = new EmpresaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Empresa> response = getEntidadeDAC().fetchEmpresaByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testinsertFilial() throws Exception
	{

		Filial funcionario = new Filial();
		funcionario = insertFilial(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Filial> funcionarioResponse = getEntidadeDAC().insertFilial(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(funcionarioResponse.getFirstResult().getId());
		InternalResultsResponse<Filial> responseA = getEntidadeDAC().fetchFilialById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertTrue(responseA.getResultsList().get(0).getDocumentos().size() == funcionario.getDocumentos().size());
		assertTrue(responseA.getResultsList().get(0).getEnderecos().size() == funcionario.getEnderecos().size());
		assertTrue(responseA.getResultsList().get(0).getEmails().size() == funcionario.getEmails().size());
		assertTrue(responseA.getResultsList().get(0).getTelefones().size() == funcionario.getTelefones().size());
		assertTrue(responseA.getResultsList().get(0).getCnaes().size() == funcionario.getCnaes().size());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.ANALISANDO);
		assertTrue(responseA.getResultsList().get(0).getNotes().size() == funcionario.getNotes().size());

	}

	@Test
	public void testupdateFilial() throws Exception
	{

		Filial funcionario = new Filial();
		funcionario = insertFilial(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Filial> funcionarioResponse = getEntidadeDAC().insertFilial(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
		funcionario = insertFilial(PersistanceActionEnum.UPDATE);
		funcionario.setId(funcionarioResponse.getFirstResult().getId());
		funcionarioResponse = getEntidadeDAC().updateFilial(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.NoRowsUpdatedError);

	}

	@Test
	public void testdeleteFilial() throws Exception
	{

		Filial funcionario = new Filial();
		funcionario = insertFilial(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Filial> funcionarioResponse = getEntidadeDAC().insertFilial(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
		funcionario = funcionarioResponse.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getEntidadeDAC().deleteFilial(funcionario);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(funcionarioResponse.getFirstResult().getId());
		InternalResultsResponse<Filial> responseA = getEntidadeDAC().fetchFilialById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);
	}

	@Test
	public void testfetchFilialById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Filial> response = getEntidadeDAC().fetchFilialById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchFilialByRequest() throws Exception
	{
		// check for valid and precount
		FilialInquiryRequest request = new FilialInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Filial> response = getEntidadeDAC().fetchFilialByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testinsertDeposito() throws Exception
	{

		Deposito funcionario = new Deposito();
		funcionario = insertDeposito(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Deposito> funcionarioResponse = getEntidadeDAC().insertDeposito(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(funcionarioResponse.getFirstResult().getId());
		InternalResultsResponse<Deposito> responseA = getEntidadeDAC().fetchDepositoById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		// assertTrue(responseA.getResultsList().get(0).getRegime().getNome() == funcionario.getRegime().getNome());
		assertTrue(responseA.getResultsList().get(0).getDocumentos().size() == funcionario.getDocumentos().size());
		assertTrue(responseA.getResultsList().get(0).getEnderecos().size() == funcionario.getEnderecos().size());
		assertTrue(responseA.getResultsList().get(0).getEmails().size() == funcionario.getEmails().size());
		assertTrue(responseA.getResultsList().get(0).getTelefones().size() == funcionario.getTelefones().size());
		assertTrue(responseA.getResultsList().get(0).getCnaes().size() == funcionario.getCnaes().size());
		// assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == StatusEnum.ANALIZANDO);
		assertTrue(responseA.getResultsList().get(0).getNotes().size() == funcionario.getNotes().size());

	}

	@Test
	public void testupdateDeposito() throws Exception
	{

		Deposito funcionario = new Deposito();
		funcionario = insertDeposito(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Deposito> funcionarioResponse = getEntidadeDAC().insertDeposito(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
		funcionario = insertDeposito(PersistanceActionEnum.UPDATE);
		funcionario.setId(funcionarioResponse.getFirstResult().getId());
		funcionarioResponse = getEntidadeDAC().updateDeposito(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.NoRowsUpdatedError);
	}

	@Test
	public void testdeleteDeposito() throws Exception
	{

		Deposito funcionario = new Deposito();
		funcionario = insertDeposito(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Deposito> funcionarioResponse = getEntidadeDAC().insertDeposito(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
		funcionario = funcionarioResponse.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse response = getEntidadeDAC().deleteDeposito(funcionario);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(funcionarioResponse.getFirstResult().getId());
		InternalResultsResponse<Deposito> responseA = getEntidadeDAC().fetchDepositoById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);
	}

	@Test
	public void testfetchDepositoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Deposito> response = getEntidadeDAC().fetchDepositoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchDepositoByRequest() throws Exception
	{
		// check for valid and precount
		DepositoInquiryRequest request = new DepositoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Deposito> response = getEntidadeDAC().fetchDepositoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testfetchCnaeByRequest() throws Exception
	{
		// check for valid and precount
		CnaeInquiryRequest request = new CnaeInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Cnae> response = getEntidadeDAC().fetchCnaeByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testfetchRegimeByRequest() throws Exception
	{
		// check for valid and precount
		RegimeInquiryRequest request = new RegimeInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Regime> response = getEntidadeDAC().fetchRegimeByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testfetchCidadeByRequest() throws Exception
	{
		// check for valid and precount
		CidadeInquiryRequest request = new CidadeInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Cidade> response = getEntidadeDAC().fetchCidadeByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testinsertCidade() throws Exception
	{

		Cidade funcionario = new Cidade();
		funcionario = insertCidade(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Cidade> funcionarioResponse = getEntidadeDAC().insertCidade(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testupdateCidade() throws Exception
	{

		Cidade funcionario = new Cidade();
		funcionario = insertCidade(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Cidade> funcionarioResponse = getEntidadeDAC().insertCidade(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteCidade() throws Exception
	{

		Cidade funcionario = new Cidade();
		funcionario.setId(1);
		funcionario = insertCidade(PersistanceActionEnum.DELETE);
		InternalResponse funcionarioResponse = getEntidadeDAC().deleteCidade(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchPlanoByRequest() throws Exception
	{
		// check for valid and precount
		PlanoInquiryRequest request = new PlanoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Plano> response = getEntidadeDAC().fetchPlanoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testfetchClassificacaoByRequest() throws Exception
	{
		// check for valid and precount
		ClassificacaoInquiryRequest request = new ClassificacaoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Classificacao> response = getEntidadeDAC().fetchClassificacaoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertEntidade.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertBanco.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertBeneficios.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertCondPag.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertHorario.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertEspecialidade.sql", false);
	}

	public List<Endereco> enderecoList(PersistanceActionEnum action)
	{
		List<Endereco> enderecoList = new ArrayList<Endereco>();
		Endereco endereco =
				new Endereco(0, "logradouro", new Cidade(), new Estado(), "bairro", "numero", "cep", "complemento",
						EnderecoTypeEnum.ENTREGA, action);
		endereco.setProcessId(1);
		enderecoList.add(endereco);

		endereco =
				new Endereco(0, "logradouro", new Cidade(), new Estado(), "bairro", "numero", "cep", "complemento",
						EnderecoTypeEnum.COBRANCA, action);
		endereco.setProcessId(1);
		enderecoList.add(endereco);

		endereco =
				new Endereco(0, "logradouro", new Cidade(), new Estado(), "bairro", "numero", "cep", "complemento",
						EnderecoTypeEnum.PRINCIPAL, action);
		endereco.setProcessId(1);
		enderecoList.add(endereco);
		return enderecoList;

	}

	public List<Documento> documentoList(PersistanceActionEnum action)
	{
		List<Documento> documentoList = new ArrayList<Documento>();
		Documento documento = new Documento(0, DocumentoTypeEnum.CNPJ, "numero", (long)123456789, new Estado(), action);
		documento.setProcessId(1);
		documentoList.add(documento);

		documento = new Documento(0, DocumentoTypeEnum.IE, "numero", (long)123456789, new Estado(), action);
		documento.setProcessId(1);
		documentoList.add(documento);

		documento = new Documento(0, DocumentoTypeEnum.IM, "numero", (long)123456789, new Estado(), action);
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

	}

	public List<Email> emailList(PersistanceActionEnum action)
	{
		List<Email> documentoList = new ArrayList<Email>();
		Email documento = new Email(0, "email", EmailTypeEnum.COMPRAS, action);
		documento.setProcessId(1);
		documentoList.add(documento);
		documento = new Email(0, "email", EmailTypeEnum.NFE, action);
		documento.setProcessId(1);
		documentoList.add(documento);
		documento = new Email(0, "email", EmailTypeEnum.VENDAS, action);
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

	}

	public List<Telefone> telefoneList(PersistanceActionEnum action)
	{
		List<Telefone> documentoList = new ArrayList<Telefone>();
		Telefone documento = new Telefone(0, "ddd", "numero", TelefoneTypeEnum.DIRETOR, action);
		documento.setProcessId(1);
		documentoList.add(documento);

		documento = new Telefone(0, "ddd", "numero", TelefoneTypeEnum.COMPRAS, action);
		documento.setProcessId(1);
		documentoList.add(documento);

		documento = new Telefone(0, "ddd", "numero", TelefoneTypeEnum.REPRESENTANTE, action);
		documento.setProcessId(1);
		documentoList.add(documento);

		return documentoList;

	}

	public List<BancoPessoa> bancoList(PersistanceActionEnum action)
	{
		List<BancoPessoa> documentoList = new ArrayList<BancoPessoa>();
		BancoPessoa documento = new BancoPessoa();
		documento.setModelAction(action);
		documento.setBancoId(new Banco(0, "ITAU", action));
		documento.setProcessId(1);
		documento.setSaldo(new Double(1.99));
		documentoList.add(documento);
		return documentoList;

	}

	public List<CfopPessoa> cfopList(PersistanceActionEnum action)
	{
		List<CfopPessoa> documentoList = new ArrayList<CfopPessoa>();
		CfopPessoa documento = new CfopPessoa();
		documento.setModelAction(action);
		documento.setProcessId(1);
		documento.setIdCfop(new Cfop(0, "cfop", "natureza", "simplificado", CfopTypeEnum.ENTRADA, 0.39, 0.15, 0.54,
				0.9, 0.1, "observacao", action));
		documento.setParentId(1);

		documentoList.add(documento);
		return documentoList;

	}

	public List<Note> noteList(PersistanceActionEnum action)
	{
		List<Note> documentoList = new ArrayList<Note>();
		Note documento = new Note();
		documento.setModelAction(action);
		documento.setId(1);
		documento.setEmprId(1);
		documento.setNoteText("Test NOte");
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

	}

	public List<CnaeEmpresa> cnaeList(PersistanceActionEnum action)
	{
		List<CnaeEmpresa> cnaeList = new ArrayList<CnaeEmpresa>();
		cnaeList.add(new CnaeEmpresa(1, 1, action, new Cnae(0, "CNAE", "DESCRICAO", "ABREV", action)));
		cnaeList.add(new CnaeEmpresa(1, 1, action, new Cnae(0, "CNAE", "DESCRICAO", "ABREV", action)));
		cnaeList.add(new CnaeEmpresa(1, 1, action, new Cnae(0, "CNAE", "DESCRICAO", "ABREV", action)));
		cnaeList.add(new CnaeEmpresa(1, 1, action, new Cnae(0, "CNAE", "DESCRICAO", "ABREV", action)));
		cnaeList.add(new CnaeEmpresa(1, 1, action, new Cnae(0, "CNAE", "DESCRICAO", "ABREV", action)));
		return cnaeList;

	}

	public Regime insertRegime(PersistanceActionEnum action)
	{

		Regime regime = new Regime();
		regime.setId(1);
		regime.setNome("Teste");
		regime.setDescricao("Teste");
		regime.setModelAction(action);
		return regime;
	}

	public List<Usuario> insertUsuario(PersistanceActionEnum action)
	{
		List<Usuario> list = new ArrayList<Usuario>();
		Date a = new Date();
		for (Integer i = 0; i < 10; i++)
		{
			Usuario usuario = new Usuario();
			usuario.setId(1);
			usuario.setLogin("LOGIN");
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

	public Empresa insertEmpresa(PersistanceActionEnum action)
	{
		Empresa funcionario = new Empresa();
		Date a = new Date();

		funcionario.setId(1);
		funcionario.setNome("NOME");
		funcionario.setRegime(insertRegime(action));
		funcionario.setEntidadeId(1);
		funcionario.setEntidadeEnum(EntidadeTypeEnum.EMPRESA);
		funcionario.setConfiguracao(new Configuracao());
		funcionario.setCnaes(cnaeList(action));
		funcionario.setUsuarioList(insertUsuario(action));
		funcionario.setProcessId(1);
		funcionario.setDocumentos(documentoList(action));
		funcionario.setTelefones(telefoneList(action));
		funcionario.setEmails(emailList(action));
		funcionario.setEnderecos(enderecoList(action));
		funcionario.setBancos(bancoList(action));
		funcionario.setNotes(noteList(action));
		funcionario.setModelAction(action);

		return funcionario;
	}

	public Filial insertFilial(PersistanceActionEnum action)
	{
		Filial funcionario = new Filial();
		Date a = new Date();

		funcionario.setId(1);
		funcionario.setNome("NOME");
		funcionario.setRegime(insertRegime(action));
		funcionario.setEntidadeId(1);
		funcionario.setEntidadeEnum(EntidadeTypeEnum.FILIAL);
		funcionario.setConfiguracao(new Configuracao());
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

	public Deposito insertDeposito(PersistanceActionEnum action)
	{
		Deposito funcionario = new Deposito();
		Date a = new Date();

		funcionario.setId(1);
		funcionario.setNome("NOME");
		funcionario.setRegime(insertRegime(action));
		funcionario.setEntidadeId(1);
		funcionario.setEntidadeEnum(EntidadeTypeEnum.DEPOSITO);
		funcionario.setConfiguracao(new Configuracao());
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

	public Cidade insertCidade(PersistanceActionEnum action)
	{
		Cidade cidade = new Cidade();
		Date a = new Date();

		cidade.setId(1);
		cidade.setCodigo("CODIGO");
		cidade.setNome("NOME");
		cidade.setCdIBGE("CDIBGE");
		cidade.setEstado(new Estado(1));
		cidade.setCep("CEP");
		cidade.setMunicipio("MUNICIO");
		cidade.setNotes(noteList(action));

		return cidade;
	}
}
