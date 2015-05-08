package com.prosperitasglobal.sendsolv.unittest.bac;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.IProductPlanTemplateGroupBAC;
import com.prosperitasglobal.sendsolv.dac.IProductPlanDAC;
import com.prosperitasglobal.sendsolv.dac.IProductPlanTemplateGroupDAC;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroupLocation;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * A JUNIT for testing the implementation of the {@link IProductPlanTemplateGroupBAC} interface.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bac/conf/productplantemplategroupbacimpltest.xml"})
public class ProductPlanTemplateGroupBACImplTest extends AbstractJUnit4SpringContextTests
{
	/** The Constant Integer for a numeric 1. */
	private static final Integer INT_ONE = 1;

	/** The Constant Integer for a numeric 3. */
	private static final Integer INT_THREE = 3;

	/** The implementation of the {@link IProductPlanDAC}. */
	private IProductPlanDAC productPlanDAC;

	/** The implementation of the {@link IProductPlanTemplateGroupBAC}. */
	private IProductPlanTemplateGroupBAC productPlanTemplateGroupBAC;

	/** The implementation of the {@link IProductPlanTemplateGroupDAC}. */
	private IProductPlanTemplateGroupDAC productPlanTemplateGroupDAC;

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
	 * Get the implementation of the {@link IProductPlanTemplateGroupBAC} interface. Injected by Spring.
	 *
	 * @return The implementation of the {@link IProductPlanTemplateGroupBAC} interface.
	 */
	public IProductPlanTemplateGroupBAC getProductPlanTemplateGroupBAC()
	{
		return productPlanTemplateGroupBAC;
	}

	/**
	 * Get the implementation of the {@link IProductPlanTemplateGroupDAC} interface. Injected by Spring.
	 *
	 * @return The implementation of the {@link IProductPlanTemplateGroupDAC} interface.
	 */
	public IProductPlanTemplateGroupDAC getProductPlanTemplateGroupDAC()
	{
		return productPlanTemplateGroupDAC;
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
	 * Set the implementation of the {@link IProductPlanTemplateGroupBAC} interface. Injected by Spring.
	 *
	 * @param productPlanTemplateGroupBAC The implementation of the {@link IProductPlanTemplateGroupBAC} interface to
	 *            set.
	 */
	@Resource
	public void setProductPlanTemplateGroupBAC(IProductPlanTemplateGroupBAC productPlanTemplateGroupBAC)
	{
		this.productPlanTemplateGroupBAC = productPlanTemplateGroupBAC;
	}

	/**
	 * Set the implementation of the {@link IProductPlanTemplateGroupDAC} interface. Injected by Spring.
	 *
	 * @param productPlanTemplateGroupDAC The implementation of the {@link IProductPlanTemplateGroupDAC} interface to
	 *            set.
	 */
	@Resource
	public void setProductPlanTemplateGroupDAC(IProductPlanTemplateGroupDAC productPlanTemplateGroupDAC)
	{
		this.productPlanTemplateGroupDAC = productPlanTemplateGroupDAC;
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getProductPlanTemplateGroupDAC());
	}

	/**
	 * Test successful create of {@link BusinessProductPlan}'s.
	 */
	@Test
	public void testCreateBusinessProductPlans()
	{
		ProductPlanTemplateGroupCreateRequest request = new ProductPlanTemplateGroupCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);

		ProductPlanTemplateGroupLocation productPlanTemplateGroupLocation = new ProductPlanTemplateGroupLocation();
		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(CommonTestRoutines.createDummyProduct(),
						CommonTestRoutines.createDummyPayer());
		productPlanTemplateGroupLocation.setProductPlanTemplateGroup(productPlanTemplateGroup);
		productPlanTemplateGroupLocation.setLocationId(1);
		request.setProductPlanTemplateGroupLocation(productPlanTemplateGroupLocation);

		Mockito.when(getProductPlanDAC().insertBusinessProductPlan((BusinessProductPlan)Matchers.any())).thenReturn(
				new InternalResultsResponse<BusinessProductPlan>(Arrays.asList(new BusinessProductPlan())));

		InternalResultsResponse<BusinessProductPlan> response =
				getProductPlanTemplateGroupBAC().createBusinessProductPlans(request);
		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getProductPlanDAC(), Mockito.times(INT_THREE)).insertBusinessProductPlan(
				(BusinessProductPlan)Matchers.any());
	}

	/**
	 * Test successful delete of {@link ProductPlanTemplateGroup}.
	 */
	@Test
	public void testDeleteProductPlanTemplateGroup()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(CommonTestRoutines.createDummyProduct(),
						CommonTestRoutines.createDummyPayer());

		Mockito.when(getProductPlanTemplateGroupDAC().deleteProductPlanTemplateGroup(productPlanTemplateGroup))
				.thenReturn(new InternalResponse());

		InternalResponse response =
				getProductPlanTemplateGroupBAC().deleteProductPlanTemplateGroup(productPlanTemplateGroup);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanTemplateGroupDAC()).deleteProductPlanTemplateGroup(productPlanTemplateGroup);
	}

	/**
	 * Test successful fetch of a {@link ProductPlanTemplateGroup} by id.
	 */
	@Test
	public void testFetchProductPlanTemplateGroupById()
	{
		FetchByIdRequest request = new FetchByIdRequest(INT_ONE);
		Integer id = request.getId();

		Mockito.when(getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupById(id)).thenReturn(
				new InternalResultsResponse<ProductPlanTemplateGroup>(Arrays.asList(new ProductPlanTemplateGroup())));

		InternalResultsResponse<ProductPlanTemplateGroup> response =
				getProductPlanTemplateGroupBAC().fetchProductPlanTemplateGroupById(request);
		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getProductPlanTemplateGroupDAC()).fetchProductPlanTemplateGroupById(id);
	}

	/**
	 * Test successful fetch of a {@link ProductPlanTemplateGroup} by request.
	 */
	@Test
	public void testFetchTemplateProductByRequest()
	{
		ProductPlanTemplateGroupInquiryRequest request = new ProductPlanTemplateGroupInquiryRequest();

		Mockito.when(getProductPlanTemplateGroupDAC().fetchProductPlanTemplateGroupByRequest(request)).thenReturn(
				new InternalResultsResponse<ProductPlanTemplateGroup>(
						Arrays.asList(new ProductPlanTemplateGroup(), new ProductPlanTemplateGroup(),
								new ProductPlanTemplateGroup(), new ProductPlanTemplateGroup())));

		InternalResultsResponse<ProductPlanTemplateGroup> results =
				getProductPlanTemplateGroupBAC().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertResultResponse(results);

		Mockito.verify(getProductPlanTemplateGroupDAC()).fetchProductPlanTemplateGroupByRequest(request);
	}

	/**
	 * Test successful insert of {@link ProductPlanTemplateGroup}.
	 */
	@Test
	public void testInsertProductPlanTemplateGroup()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(CommonTestRoutines.createDummyProduct(),
						CommonTestRoutines.createDummyPayer());

		Mockito.when(getProductPlanTemplateGroupDAC().insertProductPlanTemplateGroup(productPlanTemplateGroup))
				.thenReturn(
						new InternalResultsResponse<ProductPlanTemplateGroup>(Arrays
								.asList(new ProductPlanTemplateGroup())));

		InternalResponse response =
				getProductPlanTemplateGroupBAC().insertProductPlanTemplateGroup(productPlanTemplateGroup);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanTemplateGroupDAC()).insertProductPlanTemplateGroup(productPlanTemplateGroup);
	}

	/**
	 * Test successful update of {@link ProductPlanTemplateGroup}.
	 */
	@Test
	public void testUpdateProductPlanTemplateGroup()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(CommonTestRoutines.createDummyProduct(),
						CommonTestRoutines.createDummyPayer());

		Mockito.when(getProductPlanTemplateGroupDAC().updateProductPlanTemplateGroup(productPlanTemplateGroup))
				.thenReturn(
						new InternalResultsResponse<ProductPlanTemplateGroup>(Arrays
								.asList(new ProductPlanTemplateGroup())));

		InternalResponse response =
				getProductPlanTemplateGroupBAC().updateProductPlanTemplateGroup(productPlanTemplateGroup);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanTemplateGroupDAC()).updateProductPlanTemplateGroup(productPlanTemplateGroup);
	}
}
