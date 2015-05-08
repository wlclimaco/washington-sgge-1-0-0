package com.prosperitasglobal.sendsolv.unittest.bai;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.IProductPlanTemplateGroupBAC;
import com.prosperitasglobal.sendsolv.bai.IProductPlanTemplateGroupBAI;
import com.prosperitasglobal.sendsolv.bai.impl.ProductPlanTemplateGroupBAIImpl;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.Product;
import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroupLocation;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProductPlanTemplateGroupMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.ProductPlanTemplateGroupCreateResponse;
import com.prosperitasglobal.sendsolv.model.response.ProductPlanTemplateGroupResponse;
import com.prosperitasglobal.sendsolv.validation.PagedInquiryRequestValidator;
import com.prosperitasglobal.sendsolv.validation.ProductPlanTemplateGroupCreateRequestValidator;
import com.prosperitasglobal.sendsolv.validation.ProductPlanTemplateGroupInquiryRequestValidator;
import com.prosperitasglobal.sendsolv.validation.ProductPlanTemplateGroupValidator;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.model.response.MaintenanceResponse;

/**
 * The Class MemberBAIImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/pgsi-sendsolv-validators-context-test.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/sendsolv-dac-context.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/productplantemplategroupbaiimpltest.xml"})
public class ProductPlanTemplateGroupBAIImplTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(ProductPlanTemplateGroupBAIImplTest.class);

	/** The constant for a name sort. */
	private static final String SORT_NAME = "name";

	/** The implementation of the {@link IProductPlanTemplateGroupBAC}. */
	private IProductPlanTemplateGroupBAC productPlanTemplateGroupBAC;

	/** The implementation of the {@link IProductPlanTemplateGroupBAI}. */
	private IProductPlanTemplateGroupBAI productPlanTemplateGroupBAI;

	/**
	 * Creates the fetch response for a {@link ProductPlanTemplateGroup}.
	 *
	 * @param productPlanTemplateGroup The product plan template group.
	 * @return the internal results response of {@link ProductPlanTemplateGroup}.
	 */
	private InternalResultsResponse<ProductPlanTemplateGroup> createFetchResponse(
			ProductPlanTemplateGroup productPlanTemplateGroup)
	{
		InternalResultsResponse<ProductPlanTemplateGroup> response =
				new InternalResultsResponse<ProductPlanTemplateGroup>();
		response.addResult(productPlanTemplateGroup);
		return response;
	}

	/**
	 * Gets the implementation of the {@link IProductPlanTemplateGroupBAC}.
	 *
	 * @return The implementation of the {@link IProductPlanTemplateGroupBAC}.
	 */
	public IProductPlanTemplateGroupBAC getProductPlanTemplateGroupBAC()
	{
		return productPlanTemplateGroupBAC;
	}

	/**
	 * Gets the implementation of the {@link IProductPlanTemplateGroupBAI}.
	 *
	 * @return The implementation of the {@link IProductPlanTemplateGroupBAI}.
	 */
	public IProductPlanTemplateGroupBAI getProductPlanTemplateGroupBAI()
	{
		return productPlanTemplateGroupBAI;
	}

	/**
	 * Sets the implementation of the {@link IProductPlanTemplateGroupBAC}.
	 *
	 * @param productPlanTemplateGroupBAC The implementation of the {@link IProductPlanTemplateGroupBAC} to set.
	 */
	@Resource
	public void setProductPlanTemplateGroupBAC(IProductPlanTemplateGroupBAC productPlanTemplateGroupBAC)
	{
		this.productPlanTemplateGroupBAC = productPlanTemplateGroupBAC;
	}

	/**
	 * Sets the implementation of the {@link IProductPlanTemplateGroupBAI}.
	 *
	 * @param productPlanTemplateGroupBAI The implementation of the {@link IProductPlanTemplateGroupBAI} to set.
	 */
	@Resource
	public void setProductPlanTemplateGroupBAI(IProductPlanTemplateGroupBAI productPlanTemplateGroupBAI)
	{
		this.productPlanTemplateGroupBAI = productPlanTemplateGroupBAI;
	}

	/**
	 * Execute before executing each @Test.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getProductPlanTemplateGroupBAC());
	}

	/**
	 * Test successful create of a {@link BusinessProductPlan}'s.
	 */
	@Test
	public void testCreateBusinessProductPlan()
	{
		ProductPlanTemplateGroupCreateRequest request = new ProductPlanTemplateGroupCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);

		ProductPlanTemplateGroupLocation productPlanTemplateGroupLocation = new ProductPlanTemplateGroupLocation();
		request.setProductPlanTemplateGroupLocation(productPlanTemplateGroupLocation);

		Mockito.when(getProductPlanTemplateGroupBAC().createBusinessProductPlans(request)).thenReturn(
				new InternalResultsResponse<BusinessProductPlan>(Arrays.asList(new BusinessProductPlan(),
						new BusinessProductPlan(), new BusinessProductPlan())));

		ProductPlanTemplateGroupCreateResponse response =
				getProductPlanTemplateGroupBAI().createBusinessProductPlans(request);
		CommonTestRoutines.assertResponse(response);
		Assert.assertTrue("Response contains an empty business product plan list", response
				.getBusinessProductPlanList().size() > 0);

		Mockito.verify(getProductPlanTemplateGroupBAC()).createBusinessProductPlans(request);
	}

	/**
	 * Test create of a {@link BusinessProductPlan}'s with request that is null.
	 */
	@Test
	public void testCreateBusinessProductPlanNull()
	{
		ProductPlanTemplateGroupCreateRequest request = null;

		Mockito.when(getProductPlanTemplateGroupBAC().createBusinessProductPlans(request)).thenReturn(
				new InternalResultsResponse<BusinessProductPlan>());

		ProductPlanTemplateGroupCreateResponse response =
				getProductPlanTemplateGroupBAI().createBusinessProductPlans(request);
		CommonTestRoutines.assertMessages(response, LOG,
				ProductPlanTemplateGroupCreateRequestValidator.PRODUCT_PLAN_TEMPLATE_GROUP_CREATE_REQUEST_REQUIRED);
		Assert.assertFalse("Error Response contains entries in business product plan list", response
				.getBusinessProductPlanList().size() > 0);

		Mockito.verify(getProductPlanTemplateGroupBAC(), Mockito.never()).createBusinessProductPlans(
				request);
	}

	/**
	 * Test create of a {@link BusinessProductPlan}'s that throws an exception.
	 */
	@Test
	public void testCreateBusinessProductPlanException()
	{
		ProductPlanTemplateGroupCreateRequest request = new ProductPlanTemplateGroupCreateRequest();
		CommonTestRoutines.createDummyUserContext(request);

		ProductPlanTemplateGroupLocation productPlanTemplateGroupLocation = new ProductPlanTemplateGroupLocation();
		request.setProductPlanTemplateGroupLocation(productPlanTemplateGroupLocation);

		Mockito.when(getProductPlanTemplateGroupBAC().createBusinessProductPlans(request)).thenThrow(
				new RuntimeException());

		ProductPlanTemplateGroupCreateResponse response =
				getProductPlanTemplateGroupBAI().createBusinessProductPlans(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanTemplateGroupBAIImpl.DEFAULT_EXCEPTION_MSG);
		Assert.assertFalse("Exception Response contains entries in business product plan list", response
				.getBusinessProductPlanList().size() > 0);

		Mockito.verify(getProductPlanTemplateGroupBAC()).createBusinessProductPlans(request);
	}

	/**
	 * Test successful delete of a {@link ProductPlanTemplateGroup}.
	 */
	@Test
	public void testDeleteProductPlanTemplateGroup()
	{
		ProductPlanTemplateGroupMaintenanceRequest request = new ProductPlanTemplateGroupMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(new Product(), new Payer());
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.DELETE);
		request.setProductPlanTemplateGroup(productPlanTemplateGroup);

		Mockito.when(getProductPlanTemplateGroupBAC().deleteProductPlanTemplateGroup(productPlanTemplateGroup))
				.thenReturn(
						new InternalResultsResponse<ProductPlanTemplateGroup>());

		MaintenanceResponse response = getProductPlanTemplateGroupBAI().deleteProductPlanTemplateGroup(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanTemplateGroupBAC()).deleteProductPlanTemplateGroup(productPlanTemplateGroup);
	}

	/**
	 * Test delete of a {@link ProductPlanTemplateGroup} that takes an exception.
	 */
	@Test
	public void testDeleteProductPlanTemplateGroupException()
	{
		ProductPlanTemplateGroupMaintenanceRequest request = new ProductPlanTemplateGroupMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(new Product(), new Payer());
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.DELETE);
		request.setProductPlanTemplateGroup(productPlanTemplateGroup);

		Mockito.when(getProductPlanTemplateGroupBAC().deleteProductPlanTemplateGroup(productPlanTemplateGroup))
				.thenThrow(
						new RuntimeException());

		MaintenanceResponse response = getProductPlanTemplateGroupBAI().deleteProductPlanTemplateGroup(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanTemplateGroupBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanTemplateGroupBAC()).deleteProductPlanTemplateGroup(productPlanTemplateGroup);
	}

	/**
	 * Test successful fetch of a {@link ProductPlanTemplateGroup} by id.
	 */
	@Test
	public void testFetchProductPlanTemplateGroupById()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(new Product(), new Payer());
		FetchByIdRequest request = new FetchByIdRequest(productPlanTemplateGroup.getId());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getProductPlanTemplateGroupBAC().fetchProductPlanTemplateGroupById(request)).thenReturn(
				createFetchResponse(productPlanTemplateGroup));

		ProductPlanTemplateGroupResponse response =
				getProductPlanTemplateGroupBAI().fetchProductPlanTemplateGroupById(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanTemplateGroupBAC()).fetchProductPlanTemplateGroupById(request);
	}

	/**
	 * Test fetch of a {@link ProductPlanTemplateGroup} by id that takes an exception.
	 */
	@Test
	public void testFetchProductPlanTemplateGroupByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(1);

		Mockito.when(getProductPlanTemplateGroupBAC().fetchProductPlanTemplateGroupById(request)).thenThrow(
				new RuntimeException());

		ProductPlanTemplateGroupResponse response =
				getProductPlanTemplateGroupBAI().fetchProductPlanTemplateGroupById(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanTemplateGroupBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanTemplateGroupBAC()).fetchProductPlanTemplateGroupById(request);
	}

	/**
	 * Test successful fetch of a {@link ProductPlanTemplateGroup} by id.
	 */
	@Test
	public void testFetchProductPlanTemplateGroupByIdMissingId()
	{
		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(new Product(), new Payer());
		FetchByIdRequest request = new FetchByIdRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getProductPlanTemplateGroupBAC().fetchProductPlanTemplateGroupById(request)).thenReturn(
				createFetchResponse(productPlanTemplateGroup));

		ProductPlanTemplateGroupResponse response =
				getProductPlanTemplateGroupBAI().fetchProductPlanTemplateGroupById(request);
		CommonTestRoutines.assertMessages(response, LOG,
				ProductPlanTemplateGroupBAIImpl.PRODUCT_PLAN_TEMPLATE_GROUP_ID_REQUIRED);

		Mockito.verify(getProductPlanTemplateGroupBAC(), Mockito.never()).fetchProductPlanTemplateGroupById(request);
	}

	/**
	 * Test successful fetch of a {@link ProductPlanTemplateGroup} by request.
	 */
	@Test
	public void testFetchProductPlanTemplateGroupByRequest()
	{
		ProductPlanTemplateGroupInquiryRequest request = new ProductPlanTemplateGroupInquiryRequest();
		request.addSortExpressions(new SortExpression(SORT_NAME, Direction.Ascending));

		Mockito.when(
				getProductPlanTemplateGroupBAC().fetchProductPlanTemplateGroupByRequest(
						Matchers.any(ProductPlanTemplateGroupInquiryRequest.class)))
				.thenReturn(
						createFetchResponse(CommonTestRoutines.createDummyProductPlanTemplateGroup(new Product(),
								new Payer())));

		ProductPlanTemplateGroupResponse response =
				getProductPlanTemplateGroupBAI().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanTemplateGroupBAC()).fetchProductPlanTemplateGroupByRequest(request);
	}

	/**
	 * Test fetch of a {@link ProductPlanTemplateGroup} by request that takes an exception.
	 */
	@Test
	public void testFetchProductPlanTemplateGroupByRequestException()
	{
		ProductPlanTemplateGroupInquiryRequest request = new ProductPlanTemplateGroupInquiryRequest();

		Mockito.when(getProductPlanTemplateGroupBAC().fetchProductPlanTemplateGroupByRequest(request)).thenThrow(
				new RuntimeException());

		ProductPlanTemplateGroupResponse response =
				getProductPlanTemplateGroupBAI().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanTemplateGroupBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanTemplateGroupBAC()).fetchProductPlanTemplateGroupByRequest(request);
	}

	/**
	 * Test fetch of a {@link ProductPlanTemplateGroup} by request that takes an error on sort fields.
	 */
	@Test
	public void testFetchProductPlanTemplateGroupByRequestInvalidSortColumn()
	{
		ProductPlanTemplateGroupInquiryRequest request = new ProductPlanTemplateGroupInquiryRequest();
		request.addSortExpressions(new SortExpression("badname", Direction.Ascending));

		Mockito.when(
				getProductPlanTemplateGroupBAC().fetchProductPlanTemplateGroupByRequest(
						Matchers.any(ProductPlanTemplateGroupInquiryRequest.class)))
				.thenReturn(
						createFetchResponse(CommonTestRoutines.createDummyProductPlanTemplateGroup(new Product(),
								new Payer())));

		ProductPlanTemplateGroupResponse response =
				getProductPlanTemplateGroupBAI().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, PagedInquiryRequestValidator.INVALID_SORT_FIELD);

		Mockito.verify(getProductPlanTemplateGroupBAC(), Mockito.never()).fetchProductPlanTemplateGroupByRequest(
				request);
	}

	/**
	 * Test fetch of a {@link ProductPlanTemplateGroup} by request where request is null.
	 */
	@Test
	public void testFetchProductPlanTemplateGroupByRequestNull()
	{
		Mockito.when(
				getProductPlanTemplateGroupBAC().fetchProductPlanTemplateGroupByRequest(
						Matchers.any(ProductPlanTemplateGroupInquiryRequest.class)))
				.thenReturn(
						createFetchResponse(CommonTestRoutines.createDummyProductPlanTemplateGroup(new Product(),
								new Payer())));

		ProductPlanTemplateGroupInquiryRequest request = null;
		ProductPlanTemplateGroupResponse response =
				getProductPlanTemplateGroupBAI().fetchProductPlanTemplateGroupByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG,
				ProductPlanTemplateGroupInquiryRequestValidator.PRODUCT_PLAN_TEMPLATE_GROUP_INQUIRY_REQUEST_REQUIRED);

		Mockito.verify(getProductPlanTemplateGroupBAC(), Mockito.never()).fetchProductPlanTemplateGroupByRequest(
				request);
	}

	/**
	 * Test successful insert of a {@link ProductPlanTemplateGroup}.
	 */
	@Test
	public void testInsertProductPlanTemplateGroup()
	{
		ProductPlanTemplateGroupMaintenanceRequest request = new ProductPlanTemplateGroupMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(new Product(), new Payer());
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.INSERT);
		request.setProductPlanTemplateGroup(productPlanTemplateGroup);

		Mockito.when(getProductPlanTemplateGroupBAC().insertProductPlanTemplateGroup(productPlanTemplateGroup))
				.thenReturn(
						new InternalResultsResponse<ProductPlanTemplateGroup>());

		MaintenanceResponse response = getProductPlanTemplateGroupBAI().insertProductPlanTemplateGroup(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanTemplateGroupBAC()).insertProductPlanTemplateGroup(productPlanTemplateGroup);
	}

	/**
	 * Test insert of a {@link ProductPlanTemplateGroup} that takes an exception.
	 */
	@Test
	public void testInsertProductPlanTemplateGroupException()
	{
		ProductPlanTemplateGroupMaintenanceRequest request = new ProductPlanTemplateGroupMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(new Product(), new Payer());
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.INSERT);
		request.setProductPlanTemplateGroup(productPlanTemplateGroup);

		Mockito.when(getProductPlanTemplateGroupBAC().insertProductPlanTemplateGroup(productPlanTemplateGroup))
				.thenThrow(
						new RuntimeException());

		MaintenanceResponse response = getProductPlanTemplateGroupBAI().insertProductPlanTemplateGroup(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanTemplateGroupBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanTemplateGroupBAC()).insertProductPlanTemplateGroup(productPlanTemplateGroup);
	}

	/**
	 * Test insert of a {@link ProductPlanTemplateGroup} that takes an error.
	 */
	@Test
	public void testInsertProductPlanTemplateGroupWithError()
	{
		ProductPlanTemplateGroupMaintenanceRequest request = new ProductPlanTemplateGroupMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		ProductPlanTemplateGroup productPlanTemplateGroup = null;
		request.setProductPlanTemplateGroup(productPlanTemplateGroup);

		Mockito.when(getProductPlanTemplateGroupBAC().insertProductPlanTemplateGroup(productPlanTemplateGroup))
				.thenReturn(
						new InternalResultsResponse<ProductPlanTemplateGroup>());

		MaintenanceResponse response = getProductPlanTemplateGroupBAI().insertProductPlanTemplateGroup(request);

		CommonTestRoutines.assertMessages(response, LOG,
				ProductPlanTemplateGroupValidator.PRODUCT_PLAN_TEMPLATE_GROUP_REQUIRED);

		Mockito.verify(getProductPlanTemplateGroupBAC(), Mockito.never()).insertProductPlanTemplateGroup(
				productPlanTemplateGroup);
	}

	/**
	 * Test successful update of a {@link ProductPlanTemplateGroup}.
	 */
	@Test
	public void testUpdateProductPlanTemplateGroup()
	{
		ProductPlanTemplateGroupMaintenanceRequest request = new ProductPlanTemplateGroupMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(new Product(), new Payer());
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.UPDATE);
		request.setProductPlanTemplateGroup(productPlanTemplateGroup);

		Mockito.when(getProductPlanTemplateGroupBAC().updateProductPlanTemplateGroup(productPlanTemplateGroup))
				.thenReturn(
						new InternalResultsResponse<ProductPlanTemplateGroup>());

		MaintenanceResponse response = getProductPlanTemplateGroupBAI().updateProductPlanTemplateGroup(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanTemplateGroupBAC()).updateProductPlanTemplateGroup(productPlanTemplateGroup);
	}

	/**
	 * Test update of a {@link ProductPlanTemplateGroup} that takes an exception.
	 */
	@Test
	public void testUpdateProductPlanTemplateGroupException()
	{
		ProductPlanTemplateGroupMaintenanceRequest request = new ProductPlanTemplateGroupMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(new Product(), new Payer());
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.UPDATE);
		request.setProductPlanTemplateGroup(productPlanTemplateGroup);

		Mockito.when(getProductPlanTemplateGroupBAC().updateProductPlanTemplateGroup(productPlanTemplateGroup))
				.thenThrow(
						new RuntimeException());

		MaintenanceResponse response = getProductPlanTemplateGroupBAI().updateProductPlanTemplateGroup(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanTemplateGroupBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanTemplateGroupBAC()).updateProductPlanTemplateGroup(productPlanTemplateGroup);
	}

	/**
	 * Test update of a {@link ProductPlanTemplateGroup} that takes an Optimistic Locking error.
	 */
	@Test
	public void testUpdateProductPlanTemplateGroupOptimisticLocking()
	{
		ProductPlanTemplateGroupMaintenanceRequest request = new ProductPlanTemplateGroupMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(new Product(), new Payer());
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.UPDATE);
		request.setProductPlanTemplateGroup(productPlanTemplateGroup);

		InternalResultsResponse<ProductPlanTemplateGroup> internalResponse =
				new InternalResultsResponse<ProductPlanTemplateGroup>();
		internalResponse.setStatus(Status.OptimisticLockingError);
		Mockito.when(getProductPlanTemplateGroupBAC().updateProductPlanTemplateGroup(productPlanTemplateGroup))
				.thenReturn(
						internalResponse);

		MaintenanceResponse response = getProductPlanTemplateGroupBAI().updateProductPlanTemplateGroup(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanTemplateGroupBAIImpl.OL_ERROR);

		Mockito.verify(getProductPlanTemplateGroupBAC()).updateProductPlanTemplateGroup(productPlanTemplateGroup);
	}
}
