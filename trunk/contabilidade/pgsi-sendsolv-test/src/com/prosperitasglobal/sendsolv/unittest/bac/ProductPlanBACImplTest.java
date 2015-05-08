package com.prosperitasglobal.sendsolv.unittest.bac;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.IProductPlanBAC;
import com.prosperitasglobal.sendsolv.dac.IProductPlanDAC;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TemplateProductPlanInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * A JUNIT for testing the implementation of the {@link IProductPlanBAC} interface.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bac/conf/productplanbacimpltest.xml"})
public class ProductPlanBACImplTest extends AbstractJUnit4SpringContextTests
{
	/** The implementation of the {@link IProductPlanBAC}. */
	private IProductPlanBAC productPlanBAC;

	/** The implementation of the {@link IProductPlanDAC}. */
	private IProductPlanDAC productPlanDAC;

	/** The Constant Integer for a numeric 1. */
	private static final Integer INT_ONE = 1;

	/**
	 * Get the implementation of the {@link IProductPlanBAC} interface. Injected by Spring.
	 *
	 * @return The implementation of the {@link IProductPlanBAC} interface.
	 */
	public IProductPlanBAC getProductPlanBAC()
	{
		return productPlanBAC;
	}

	/**
	 * Get the implementation of the {@link IProductPlanDAC} interface. Injected by Spring.
	 *
	 * @return The implementation of the {@link IProductPlanDAC} interface.
	 */
	public IProductPlanDAC getProductPlanDAC()
	{
		return productPlanDAC;
	}

	/**
	 * Set the implementation of the {@link IProductPlanBAC} interface. Injected by Spring.
	 *
	 * @param productPlanBAC The implementation of the {@link IProductPlanBAC} interface to set.
	 */
	@Resource
	public void setProductPlanBAC(IProductPlanBAC productPlanBAC)
	{
		this.productPlanBAC = productPlanBAC;
	}

	/**
	 * Set the implementation of the {@link IProductPlanDAC} interface. Injected by Spring.
	 *
	 * @param productPlanDAC The implementation of the {@link IProductPlanDAC} interface to set.
	 */
	@Resource
	public void setProductPlanDAC(IProductPlanDAC productPlanDAC)
	{
		this.productPlanDAC = productPlanDAC;
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getProductPlanDAC());
	}

	/**
	 * Test successful delete of {@link BusinessProductPlan}.
	 */
	@Test
	public void testDeleteBusinessProductPlan()
	{
		BusinessProductPlan businessProductPlan =
				CommonTestRoutines.createDummyBusinessProductPlan(CommonTestRoutines.createDummyProduct(),
						CommonTestRoutines.createDummyPayer());

		Mockito.when(getProductPlanDAC().deleteBusinessProductPlan(businessProductPlan)).thenReturn(
				new InternalResponse());

		InternalResponse response = getProductPlanBAC().deleteBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanDAC()).deleteBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Test successful delete of {@link TemplateProductPlan}.
	 */
	@Test
	public void testDeleteTemplateProductPlan()
	{
		TemplateProductPlan templateProductPlan =
				CommonTestRoutines.createDummyTemplateProductPlan(CommonTestRoutines.createDummyProduct(),
						CommonTestRoutines.createDummyPayer());

		Mockito.when(getProductPlanDAC().deleteTemplateProductPlan(templateProductPlan)).thenReturn(
				new InternalResponse());

		InternalResponse response = getProductPlanBAC().deleteTemplateProductPlan(templateProductPlan);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanDAC()).deleteTemplateProductPlan(templateProductPlan);
	}

	/**
	 * Test successful fetch of a {@link BusinessProductPlan} by id.
	 */
	@Test
	public void testFetchBusinessProductPlanById()
	{
		FetchByIdRequest request = new FetchByIdRequest(INT_ONE);
		Integer id = request.getId();

		Mockito.when(getProductPlanDAC().fetchBusinessProductPlanById(id)).thenReturn(
				new InternalResultsResponse<BusinessProductPlan>(Arrays.asList(new BusinessProductPlan())));

		InternalResultsResponse<BusinessProductPlan> response =
				getProductPlanBAC().fetchBusinessProductPlanById(request);
		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getProductPlanDAC()).fetchBusinessProductPlanById(id);
	}

	/**
	 * Test successful fetch of a {@link BusinessProductPlan} by request.
	 */
	@Test
	public void testFetchBusinessProductByRequest()
	{
		BusinessProductPlanInquiryRequest request = new BusinessProductPlanInquiryRequest();

		Mockito.when(getProductPlanDAC().fetchBusinessProductPlanByRequest(request)).thenReturn(
				new InternalResultsResponse<BusinessProductPlan>(
						Arrays.asList(new BusinessProductPlan(), new BusinessProductPlan(), new BusinessProductPlan(),
								new BusinessProductPlan())));

		InternalResultsResponse<BusinessProductPlan> results =
				getProductPlanBAC().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(results);

		Mockito.verify(getProductPlanDAC()).fetchBusinessProductPlanByRequest(request);
	}

	/**
	 * Test successful fetch of a {@link TemplateProductPlan} by id.
	 */
	@Test
	public void testFetchTemplateProductPlanById()
	{
		FetchByIdRequest request = new FetchByIdRequest(INT_ONE);
		Integer id = request.getId();

		Mockito.when(getProductPlanDAC().fetchTemplateProductPlanById(id)).thenReturn(
				new InternalResultsResponse<TemplateProductPlan>(Arrays.asList(new TemplateProductPlan())));

		InternalResultsResponse<TemplateProductPlan> response =
				getProductPlanBAC().fetchTemplateProductPlanById(request);
		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getProductPlanDAC()).fetchTemplateProductPlanById(id);
	}

	/**
	 * Test successful fetch of a {@link TemplateProductPlan} by request.
	 */
	@Test
	public void testFetchTemplateProductByRequest()
	{
		TemplateProductPlanInquiryRequest request = new TemplateProductPlanInquiryRequest();

		Mockito.when(getProductPlanDAC().fetchTemplateProductPlanByRequest(request)).thenReturn(
				new InternalResultsResponse<TemplateProductPlan>(
						Arrays.asList(new TemplateProductPlan(), new TemplateProductPlan(), new TemplateProductPlan(),
								new TemplateProductPlan())));

		InternalResultsResponse<TemplateProductPlan> results =
				getProductPlanBAC().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResultResponse(results);

		Mockito.verify(getProductPlanDAC()).fetchTemplateProductPlanByRequest(request);
	}

	/**
	 * Test successful insert of {@link BusinessProductPlan}.
	 */
	@Test
	public void testInsertBusinessProductPlan()
	{
		BusinessProductPlan businessProductPlan =
				CommonTestRoutines.createDummyBusinessProductPlan(CommonTestRoutines.createDummyProduct(),
						CommonTestRoutines.createDummyPayer());

		Mockito.when(getProductPlanDAC().insertBusinessProductPlan(businessProductPlan)).thenReturn(
				new InternalResultsResponse<BusinessProductPlan>(Arrays.asList(new BusinessProductPlan())));

		InternalResponse response = getProductPlanBAC().insertBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanDAC()).insertBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Test successful insert of {@link TemplateProductPlan}.
	 */
	@Test
	public void testInsertTemplateProductPlan()
	{
		TemplateProductPlan templateProductPlan =
				CommonTestRoutines.createDummyTemplateProductPlan(CommonTestRoutines.createDummyProduct(),
						CommonTestRoutines.createDummyPayer());

		Mockito.when(getProductPlanDAC().insertTemplateProductPlan(templateProductPlan)).thenReturn(
				new InternalResultsResponse<TemplateProductPlan>(Arrays.asList(new TemplateProductPlan())));

		InternalResponse response = getProductPlanBAC().insertTemplateProductPlan(templateProductPlan);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanDAC()).insertTemplateProductPlan(templateProductPlan);
	}

	/**
	 * Test successful update of {@link BusinessProductPlan}.
	 */
	@Test
	public void testUpdateBusinessProductPlan()
	{
		BusinessProductPlan businessProductPlan =
				CommonTestRoutines.createDummyBusinessProductPlan(CommonTestRoutines.createDummyProduct(),
						CommonTestRoutines.createDummyPayer());

		Mockito.when(getProductPlanDAC().updateBusinessProductPlan(businessProductPlan)).thenReturn(
				new InternalResultsResponse<BusinessProductPlan>(Arrays.asList(new BusinessProductPlan())));

		InternalResponse response = getProductPlanBAC().updateBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanDAC()).updateBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Test successful update of {@link TemplateProductPlan}.
	 */
	@Test
	public void testUpdateTemplateProductPlan()
	{
		TemplateProductPlan templateProductPlan =
				CommonTestRoutines.createDummyTemplateProductPlan(CommonTestRoutines.createDummyProduct(),
						CommonTestRoutines.createDummyPayer());

		Mockito.when(getProductPlanDAC().updateTemplateProductPlan(templateProductPlan)).thenReturn(
				new InternalResultsResponse<TemplateProductPlan>(Arrays.asList(new TemplateProductPlan())));

		InternalResponse response = getProductPlanBAC().updateTemplateProductPlan(templateProductPlan);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanDAC()).updateTemplateProductPlan(templateProductPlan);
	}
}
