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
import com.qat.samples.sysmgmt.bar.Dicionario.IDicionarioBAR;
import com.qat.samples.sysmgmt.dicionario.Classes;
import com.qat.samples.sysmgmt.dicionario.Field;
import com.qat.samples.sysmgmt.dicionario.Interface;
import com.qat.samples.sysmgmt.dicionario.request.ClassesInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/dicionariobartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class DicionarioBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(DicionarioBARTest.class);
private IDicionarioBAR dicionarioBAR; // injected by Spring through @Resource

@Resource
public void setDicionarioBAR(IDicionarioBAR dicionarioBAR)
{
	this.dicionarioBAR = dicionarioBAR;
}

public IDicionarioBAR getDicionarioBAR()
{
	return dicionarioBAR;
}


//===================================### CLASSES ####======================================


@Test
	public void testDeleteClasses()
	{
		Classes classes = new Classes(4, "Classes_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Classes classesResponse = getDicionarioBAR().fetchClassesById(request);
		Assert.assertEquals(classesResponse, null);
		getDicionarioBAR().insertClasses(classes);
		classesResponse = getDicionarioBAR().fetchClassesById(request);
		Assert.assertEquals(classes.getId(), classesResponse.getId());
		getDicionarioBAR().deleteClassesById(classes);
		classesResponse = getDicionarioBAR().fetchClassesById(request);
		Assert.assertEquals(classesResponse, null);
	}

	@Test
	public void testFetchAllClassess()
	{
	Classes classes = new Classes();
		List<Classes> response = getDicionarioBAR().fetchAllClassess(classes).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllClassess()
	{
		getDicionarioBAR().deleteAllClassess();
	Classes classes = new Classes();
		List<Classes> response = getDicionarioBAR().fetchAllClassess(classes).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateClasses()
	{
		Classes classes = new Classes(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Classes classesResponse = getDicionarioBAR().fetchClassesById(request);
		Assert.assertEquals(classesResponse.getNome(), "nome_1");
		getDicionarioBAR().updateClasses(classes);
		classesResponse = getDicionarioBAR().fetchClassesById(request);
		Assert.assertEquals(classesResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchClassessByRequest() throws Exception
	{
		// check for valid and precount
		ClassesInquiryRequest request = new ClassesInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Classes> response = getDicionarioBAR().fetchClassessByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getDicionarioBAR().fetchClassessByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ClassesInquiryRequest request2 = new ClassesInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Classes> response2 = getDicionarioBAR().fetchClassessByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getDicionarioBAR().deleteAllClassess();
		ClassesInquiryRequest request3 = new ClassesInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Classes> response3 = getDicionarioBAR().fetchClassessByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}



//===================================### INTERFACE ####======================================


@Test
	public void testDeleteInterface()
	{
		Interface interfaces = new Interface(4, "Interface_999","Local");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Interface interfaceResponse = getDicionarioBAR().fetchInterfaceById(request);
		Assert.assertEquals(interfaceResponse, null);
		getDicionarioBAR().insertInterface(interfaces);
		interfaceResponse = getDicionarioBAR().fetchInterfaceById(request);
		Assert.assertEquals(interfaces.getId(), interfaceResponse.getId());
		getDicionarioBAR().deleteInterfaceById(interfaces);
		interfaceResponse = getDicionarioBAR().fetchInterfaceById(request);
		Assert.assertEquals(interfaceResponse, null);
	}

	@Test
	public void testFetchAllInterfaces()
	{
	Interface interfaces = new Interface();
		List<Interface> response = getDicionarioBAR().fetchAllInterfaces(interfaces).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllInterfaces()
	{
		getDicionarioBAR().deleteAllInterfaces();
	Interface interfaces = new Interface();
		List<Interface> response = getDicionarioBAR().fetchAllInterfaces(interfaces).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateInterface()
	{
		Interface interfaces = new Interface(1, "NATIVE INSERT UPDATE","local");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Interface interfaceResponse = getDicionarioBAR().fetchInterfaceById(request);
		Assert.assertEquals(interfaceResponse.getNome(), "nome_1");
		getDicionarioBAR().updateInterface(interfaces);
		interfaceResponse = getDicionarioBAR().fetchInterfaceById(request);
		Assert.assertEquals(interfaceResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchInterfacesByRequest() throws Exception
	{
		// check for valid and precount
		InterfaceInquiryRequest request = new InterfaceInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Interface> response = getDicionarioBAR().fetchInterfacesByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getDicionarioBAR().fetchInterfacesByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		InterfaceInquiryRequest request2 = new InterfaceInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Interface> response2 = getDicionarioBAR().fetchInterfacesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getDicionarioBAR().deleteAllInterfaces();
		InterfaceInquiryRequest request3 = new InterfaceInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Interface> response3 = getDicionarioBAR().fetchInterfacesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}


//===================================### FIELD ####======================================


@Test
	public void testDeleteField()
	{
		Field field = new Field(4, "tipo", 10, true, true, true, true, true, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Field fieldResponse = getDicionarioBAR().fetchFieldById(request);
		Assert.assertEquals(fieldResponse, null);
		getDicionarioBAR().insertField(field);
		fieldResponse = getDicionarioBAR().fetchFieldById(request);
		Assert.assertEquals(field.getId(), fieldResponse.getId());
		getDicionarioBAR().deleteFieldById(field);
		fieldResponse = getDicionarioBAR().fetchFieldById(request);
		Assert.assertEquals(fieldResponse, null);
	}

	@Test
	public void testFetchAllFields()
	{
	Field field = new Field();
		List<Field> response = getDicionarioBAR().fetchAllFields(field).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllFields()
	{
		getDicionarioBAR().deleteAllFields();
	Field field = new Field();
		List<Field> response = getDicionarioBAR().fetchAllFields(field).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateField()
	{
		Field field = new Field(1, "tipo_2", 10, true, true, true, true, true, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Field fieldResponse = getDicionarioBAR().fetchFieldById(request);
		Assert.assertEquals(fieldResponse.getTipo(), "tipo_1");
		getDicionarioBAR().updateField(field);
		fieldResponse = getDicionarioBAR().fetchFieldById(request);
		Assert.assertEquals(fieldResponse.getTipo(), "tipo_2");
	}

	@Test
	public void testFetchFieldsByRequest() throws Exception
	{
		// check for valid and precount
		FieldInquiryRequest request = new FieldInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Field> response = getDicionarioBAR().fetchFieldsByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getDicionarioBAR().fetchFieldsByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		FieldInquiryRequest request2 = new FieldInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Field> response2 = getDicionarioBAR().fetchFieldsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getDicionarioBAR().deleteAllFields();
		FieldInquiryRequest request3 = new FieldInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Field> response3 = getDicionarioBAR().fetchFieldsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertClasses.sql", false);
		executeSqlScript("conf/insertInterface.sql", false);
		executeSqlScript("conf/insertField.sql", false);
	}

}
