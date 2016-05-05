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

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Endereco.IEnderecoBAR;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.EnderecoTypeEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class EnderecoBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(EnderecoBARTest.class);
private IEnderecoBAR enderecoBAR; // injected by Spring through @Resource

@Resource
public void setEnderecoBAR(IEnderecoBAR enderecoBAR)
{
	this.enderecoBAR = enderecoBAR;
}

public IEnderecoBAR getEnderecoBAR()
{
	return enderecoBAR;
}


//===================================### ENDERECO ####======================================


@Test
	public void testDeleteEndereco()
	{
		Endereco endereco = new Endereco(4, "logradouro", new Cidade(1),new Estado(1), "bairro", "numero", "cep", "complemento", EnderecoTypeEnum.ENTREGA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Endereco enderecoResponse = getEnderecoBAR().fetchEnderecoById(request);
		Assert.assertEquals(enderecoResponse, null);
		getEnderecoBAR().insertEndereco(endereco);
		enderecoResponse = getEnderecoBAR().fetchEnderecoById(request);
		Assert.assertEquals(endereco.getId(), enderecoResponse.getId());
		getEnderecoBAR().deleteEnderecoById(endereco);
		enderecoResponse = getEnderecoBAR().fetchEnderecoById(request);
		Assert.assertEquals(enderecoResponse, null);
	}

	@Test
	public void testFetchAllEnderecos()
	{
	Endereco endereco = new Endereco();
		List<Endereco> response = getEnderecoBAR().fetchAllEnderecos(endereco).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllEnderecos()
	{
		getEnderecoBAR().deleteAllEnderecos();
	Endereco endereco = new Endereco();
		List<Endereco> response = getEnderecoBAR().fetchAllEnderecos(new Endereco(1, "logradouro", new Cidade(1),new Estado(1), "bairro", "numero", "cep", "complemento", EnderecoTypeEnum.ENTREGA, PersistenceActionEnum.INSERT)).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateEndereco()
	{
		Endereco endereco = new Endereco(1, "logradouro", new Cidade(1),new Estado(1), "bairro_6", "numero", "cep", "complemento", EnderecoTypeEnum.ENTREGA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Endereco enderecoResponse = getEnderecoBAR().fetchEnderecoById(request);
		Assert.assertEquals(enderecoResponse.getBairro(), "bairro_5");
		getEnderecoBAR().updateEndereco(endereco);
		enderecoResponse = getEnderecoBAR().fetchEnderecoById(request);
		Assert.assertEquals(enderecoResponse.getBairro(), "bairro_6");
	}

	@Test
	public void testFetchEnderecosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Endereco> response = getEnderecoBAR().fetchEnderecosByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getEnderecoBAR().fetchEnderecosByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Endereco> response2 = getEnderecoBAR().fetchEnderecosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getEnderecoBAR().deleteAllEnderecos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Endereco> response3 = getEnderecoBAR().fetchEnderecosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertEndereco.sql", false);
	}

}
