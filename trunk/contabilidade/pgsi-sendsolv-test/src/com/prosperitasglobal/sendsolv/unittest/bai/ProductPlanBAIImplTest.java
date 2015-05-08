package com.prosperitasglobal.sendsolv.unittest.bai;

import javax.annotation.Resource;

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
import com.prosperitasglobal.sendsolv.bac.IProductPlanBAC;
import com.prosperitasglobal.sendsolv.bai.IProductPlanBAI;
import com.prosperitasglobal.sendsolv.bai.impl.ProductPlanBAIImpl;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.Product;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.TemplateProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TemplateProductPlanMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.BusinessProductPlanResponse;
import com.prosperitasglobal.sendsolv.model.response.TemplateProductPlanResponse;
import com.prosperitasglobal.sendsolv.validation.BusinessProductPlanInquiryRequestValidator;
import com.prosperitasglobal.sendsolv.validation.BusinessProductPlanValidator;
import com.prosperitasglobal.sendsolv.validation.PagedInquiryRequestValidator;
import com.prosperitasglobal.sendsolv.validation.TemplateProductPlanInquiryRequestValidator;
import com.prosperitasglobal.sendsolv.validation.TemplateProductPlanValidator;
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
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/productplanbaiimpltest.xml"})
public class ProductPlanBAIImplTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(ProductPlanBAIImplTest.class);

	/** The constant for a name sort. */
	private static final String SORT_NAME = "name";

	/** The implementation of the {@link IProductPlanBAC}. */
	private IProductPlanBAC productPlanBAC;

	/** The implementation of the {@link IProductPlanBAI}. */
	private IProductPlanBAI productPlanBAI;

	/**
	 * Creates the fetch response for a {@link BusinessProductPlan}.
	 *
	 * @param businessProductPlan The business product plan.
	 * @return the internal results response of {@link BusinessProductPlan}.
	 */
	private InternalResultsResponse<BusinessProductPlan> createFetchResponse(BusinessProductPlan businessProductPlan)
	{
		InternalResultsResponse<BusinessProductPlan> response = new InternalResultsResponse<BusinessProductPlan>();
		response.addResult(businessProductPlan);
		return response;
	}

	/**
	 * Creates the fetch response for a {@link TemplateProductPlan}.
	 *
	 * @param templateProductPlan The template product plan.
	 * @return the internal results response of {@link TemplateProductPlan}.
	 */
	private InternalResultsResponse<TemplateProductPlan> createFetchResponse(TemplateProductPlan templateProductPlan)
	{
		InternalResultsResponse<TemplateProductPlan> response = new InternalResultsResponse<TemplateProductPlan>();
		response.addResult(templateProductPlan);
		return response;
	}

	/**
	 * Gets the implementation of the {@link IProductPlanBAC}.
	 *
	 * @return The implementation of the {@link IProductPlanBAC}.
	 */
	public IProductPlanBAC getProductPlanBAC()
	{
		return productPlanBAC;
	}

	/**
	 * Gets the implementation of the {@link IProductPlanBAI}.
	 *
	 * @return The implementation of the {@link IProductPlanBAI}.
	 */
	public IProductPlanBAI getProductPlanBAI()
	{
		return productPlanBAI;
	}

	/**
	 * Sets the implementation of the {@link IProductPlanBAC}.
	 *
	 * @param productPlanBAC The implementation of the {@link IProductPlanBAC} to set.
	 */
	@Resource
	public void setProductPlanBAC(IProductPlanBAC productPlanBAC)
	{
		this.productPlanBAC = productPlanBAC;
	}

	/**
	 * Sets the implementation of the {@link IProductPlanBAI}.
	 *
	 * @param productPlanBAI The implementation of the {@link IProductPlanBAI} to set.
	 */
	@Resource
	public void setProductPlanBAI(IProductPlanBAI productPlanBAI)
	{
		this.productPlanBAI = productPlanBAI;
	}

	/**
	 * Execute before executing each @Test.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getProductPlanBAC());
	}

	/**
	 * Test successful delete of a {@link BusinessProductPlan}.
	 */
	@Test
	public void testDeleteBusinessProductPlan()
	{
		BusinessProductPlanMaintenanceRequest request = new BusinessProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		BusinessProductPlan businessProductPlan =
				CommonTestRoutines.createDummyBusinessProductPlan(new Product(), new Payer());
		businessProductPlan.setModelAction(PersistanceActionEnum.DELETE);
		request.setBusinessProductPlan(businessProductPlan);

		Mockito.when(getProductPlanBAC().deleteBusinessProductPlan(businessProductPlan)).thenReturn(
				new InternalResultsResponse<BusinessProductPlan>());

		MaintenanceResponse response = getProductPlanBAI().deleteBusinessProductPlan(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanBAC()).deleteBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Test delete of a {@link BusinessProductPlan} that takes an exception.
	 */
	@Test
	public void testDeleteBusinessProductPlanException()
	{
		BusinessProductPlanMaintenanceRequest request = new BusinessProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		BusinessProductPlan businessProductPlan =
				CommonTestRoutines.createDummyBusinessProductPlan(new Product(), new Payer());
		businessProductPlan.setModelAction(PersistanceActionEnum.DELETE);
		request.setBusinessProductPlan(businessProductPlan);

		Mockito.when(getProductPlanBAC().deleteBusinessProductPlan(businessProductPlan)).thenThrow(
				new RuntimeException());

		MaintenanceResponse response = getProductPlanBAI().deleteBusinessProductPlan(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanBAC()).deleteBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Test successful delete of a {@link TemplateProductPlan}.
	 */
	@Test
	public void testDeleteTemplateProductPlan()
	{
		TemplateProductPlanMaintenanceRequest request = new TemplateProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		TemplateProductPlan templateProductPlan =
				CommonTestRoutines.createDummyTemplateProductPlan(new Product(), new Payer());
		templateProductPlan.setModelAction(PersistanceActionEnum.DELETE);
		request.setTemplateProductPlan(templateProductPlan);

		Mockito.when(getProductPlanBAC().deleteTemplateProductPlan(templateProductPlan)).thenReturn(
				new InternalResultsResponse<TemplateProductPlan>());

		MaintenanceResponse response = getProductPlanBAI().deleteTemplateProductPlan(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanBAC()).deleteTemplateProductPlan(templateProductPlan);
	}

	/**
	 * Test delete of a {@link TemplateProductPlan} that takes an exception.
	 */
	@Test
	public void testDeleteTemplateProductPlanException()
	{
		TemplateProductPlanMaintenanceRequest request = new TemplateProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		TemplateProductPlan templateProductPlan =
				CommonTestRoutines.createDummyTemplateProductPlan(new Product(), new Payer());
		templateProductPlan.setModelAction(PersistanceActionEnum.DELETE);
		request.setTemplateProductPlan(templateProductPlan);

		Mockito.when(getProductPlanBAC().deleteTemplateProductPlan(templateProductPlan)).thenThrow(
				new RuntimeException());

		MaintenanceResponse response = getProductPlanBAI().deleteTemplateProductPlan(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanBAC()).deleteTemplateProductPlan(templateProductPlan);
	}

	/**
	 * Test successful fetch of a {@link BusinessProductPlan} by id.
	 */
	@Test
	public void testFetchBusinessProductPlanById()
	{
		BusinessProductPlan businessProductPlan =
				CommonTestRoutines.createDummyBusinessProductPlan(new Product(), new Payer());
		FetchByIdRequest request = new FetchByIdRequest(businessProductPlan.getId());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getProductPlanBAC().fetchBusinessProductPlanById(request)).thenReturn(
				createFetchResponse(businessProductPlan));

		BusinessProductPlanResponse response = getProductPlanBAI().fetchBusinessProductPlanById(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanBAC()).fetchBusinessProductPlanById(request);
	}

	/**
	 * Test fetch of a {@link BusinessProductPlan} by id that takes an exception.
	 */
	@Test
	public void testFetchBusinessProductPlanByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(1);

		Mockito.when(getProductPlanBAC().fetchBusinessProductPlanById(request)).thenThrow(new RuntimeException());

		BusinessProductPlanResponse response = getProductPlanBAI().fetchBusinessProductPlanById(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanBAC()).fetchBusinessProductPlanById(request);
	}

	/**
	 * Test successful fetch of a {@link BusinessProductPlan} by id.
	 */
	@Test
	public void testFetchBusinessProductPlanByIdMissingId()
	{
		BusinessProductPlan businessProductPlan =
				CommonTestRoutines.createDummyBusinessProductPlan(new Product(), new Payer());
		FetchByIdRequest request = new FetchByIdRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getProductPlanBAC().fetchBusinessProductPlanById(request)).thenReturn(
				createFetchResponse(businessProductPlan));

		BusinessProductPlanResponse response = getProductPlanBAI().fetchBusinessProductPlanById(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.BUSINESS_PRODUCT_PLAN_ID_REQUIRED);

		Mockito.verify(getProductPlanBAC(), Mockito.never()).fetchBusinessProductPlanById(request);
	}

	/**
	 * Test successful fetch of a {@link BusinessProductPlan} by request.
	 */
	@Test
	public void testFetchBusinessProductPlanByRequest()
	{
		BusinessProductPlanInquiryRequest request = new BusinessProductPlanInquiryRequest();
		request.addSortExpressions(new SortExpression(SORT_NAME, Direction.Ascending));

		Mockito.when(
				getProductPlanBAC().fetchBusinessProductPlanByRequest(
						Matchers.any(BusinessProductPlanInquiryRequest.class))).thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyBusinessProductPlan(new Product(), new Payer())));

		BusinessProductPlanResponse response = getProductPlanBAI().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanBAC()).fetchBusinessProductPlanByRequest(request);
	}

	/**
	 * Test fetch of a {@link BusinessProductPlan} by request that takes an exception.
	 */
	@Test
	public void testFetchBusinessProductPlanByRequestException()
	{
		BusinessProductPlanInquiryRequest request = new BusinessProductPlanInquiryRequest();

		Mockito.when(getProductPlanBAC().fetchBusinessProductPlanByRequest(request)).thenThrow(new RuntimeException());

		BusinessProductPlanResponse response = getProductPlanBAI().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanBAC()).fetchBusinessProductPlanByRequest(request);
	}

	/**
	 * Test fetch of a {@link BusinessProductPlan} by request that takes an error on sort fields.
	 */
	@Test
	public void testFetchBusinessProductPlanByRequestInvalidSortColumn()
	{
		BusinessProductPlanInquiryRequest request = new BusinessProductPlanInquiryRequest();
		request.addSortExpressions(new SortExpression("namebad", Direction.Ascending));

		Mockito.when(
				getProductPlanBAC().fetchBusinessProductPlanByRequest(
						Matchers.any(BusinessProductPlanInquiryRequest.class))).thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyBusinessProductPlan(new Product(), new Payer())));

		BusinessProductPlanResponse response = getProductPlanBAI().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, PagedInquiryRequestValidator.INVALID_SORT_FIELD);

		Mockito.verify(getProductPlanBAC(), Mockito.never()).fetchBusinessProductPlanByRequest(request);
	}

	/**
	 * Test fetch of a {@link BusinessProductPlan} by request where request is null.
	 */
	@Test
	public void testFetchBusinessProductPlanByRequestNull()
	{
		Mockito.when(
				getProductPlanBAC().fetchBusinessProductPlanByRequest(
						Matchers.any(BusinessProductPlanInquiryRequest.class))).thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyBusinessProductPlan(new Product(), new Payer())));

		BusinessProductPlanInquiryRequest request = null;
		BusinessProductPlanResponse response = getProductPlanBAI().fetchBusinessProductPlanByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG,
				BusinessProductPlanInquiryRequestValidator.BUSINESS_PRODUCT_PLAN_INQUIRY_REQUEST_REQUIRED);

		Mockito.verify(getProductPlanBAC(), Mockito.never()).fetchBusinessProductPlanByRequest(request);
	}

	/**
	 * Test successful fetch of a {@link TemplateProductPlan} by id.
	 */
	@Test
	public void testFetchTemplateProductPlanById()
	{
		TemplateProductPlan templateProductPlan =
				CommonTestRoutines.createDummyTemplateProductPlan(new Product(), new Payer());
		FetchByIdRequest request = new FetchByIdRequest(templateProductPlan.getId());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getProductPlanBAC().fetchTemplateProductPlanById(request)).thenReturn(
				createFetchResponse(templateProductPlan));

		TemplateProductPlanResponse response = getProductPlanBAI().fetchTemplateProductPlanById(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanBAC()).fetchTemplateProductPlanById(request);
	}

	/**
	 * Test fetch of a {@link TemplateProductPlan} by id that takes an exception.
	 */
	@Test
	public void testFetchTemplateProductPlanByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(1);

		Mockito.when(getProductPlanBAC().fetchTemplateProductPlanById(request)).thenThrow(new RuntimeException());

		TemplateProductPlanResponse response = getProductPlanBAI().fetchTemplateProductPlanById(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanBAC()).fetchTemplateProductPlanById(request);
	}

	/**
	 * Test successful fetch of a {@link TemplateProductPlan} by id.
	 */
	@Test
	public void testFetchTemplateProductPlanByIdMissingId()
	{
		TemplateProductPlan templateProductPlan =
				CommonTestRoutines.createDummyTemplateProductPlan(new Product(), new Payer());
		FetchByIdRequest request = new FetchByIdRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getProductPlanBAC().fetchTemplateProductPlanById(request)).thenReturn(
				createFetchResponse(templateProductPlan));

		TemplateProductPlanResponse response = getProductPlanBAI().fetchTemplateProductPlanById(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.TEMPLATE_PRODUCT_PLAN_ID_REQUIRED);

		Mockito.verify(getProductPlanBAC(), Mockito.never()).fetchTemplateProductPlanById(request);
	}

	/**
	 * Test successful fetch of a {@link TemplateProductPlan} by request.
	 */
	@Test
	public void testFetchTemplateProductPlanByRequest()
	{
		TemplateProductPlanInquiryRequest request = new TemplateProductPlanInquiryRequest();
		request.addSortExpressions(new SortExpression(SORT_NAME, Direction.Ascending));

		Mockito.when(
				getProductPlanBAC().fetchTemplateProductPlanByRequest(
						Matchers.any(TemplateProductPlanInquiryRequest.class))).thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyTemplateProductPlan(new Product(), new Payer())));

		TemplateProductPlanResponse response = getProductPlanBAI().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanBAC()).fetchTemplateProductPlanByRequest(request);
	}

	/**
	 * Test fetch of a {@link TemplateProductPlan} by request that takes an exception.
	 */
	@Test
	public void testFetchTemplateProductPlanByRequestException()
	{
		TemplateProductPlanInquiryRequest request = new TemplateProductPlanInquiryRequest();

		Mockito.when(getProductPlanBAC().fetchTemplateProductPlanByRequest(request)).thenThrow(new RuntimeException());

		TemplateProductPlanResponse response = getProductPlanBAI().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanBAC()).fetchTemplateProductPlanByRequest(request);
	}

	/**
	 * Test fetch of a {@link TemplateProductPlan} by request that takes an error on sort fields.
	 */
	@Test
	public void testFetchTemplateProductPlanByRequestInvalidSortColumn()
	{
		TemplateProductPlanInquiryRequest request = new TemplateProductPlanInquiryRequest();
		request.addSortExpressions(new SortExpression("badname", Direction.Ascending));

		Mockito.when(
				getProductPlanBAC().fetchTemplateProductPlanByRequest(
						Matchers.any(TemplateProductPlanInquiryRequest.class))).thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyTemplateProductPlan(new Product(), new Payer())));

		TemplateProductPlanResponse response = getProductPlanBAI().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, PagedInquiryRequestValidator.INVALID_SORT_FIELD);

		Mockito.verify(getProductPlanBAC(), Mockito.never()).fetchTemplateProductPlanByRequest(request);
	}

	/**
	 * Test fetch of a {@link TemplateProductPlan} by request where request is null.
	 */
	@Test
	public void testFetchTemplateProductPlanByRequestNull()
	{
		Mockito.when(
				getProductPlanBAC().fetchTemplateProductPlanByRequest(
						Matchers.any(TemplateProductPlanInquiryRequest.class))).thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyTemplateProductPlan(new Product(), new Payer())));

		TemplateProductPlanInquiryRequest request = null;
		TemplateProductPlanResponse response = getProductPlanBAI().fetchTemplateProductPlanByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG,
				TemplateProductPlanInquiryRequestValidator.TEMPLATE_PRODUCT_PLAN_INQUIRY_REQUEST_REQUIRED);

		Mockito.verify(getProductPlanBAC(), Mockito.never()).fetchTemplateProductPlanByRequest(request);
	}

	/**
	 * Test successful insert of a {@link BusinessProductPlan}.
	 */
	@Test
	public void testInsertBusinessProductPlan()
	{
		BusinessProductPlanMaintenanceRequest request = new BusinessProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		BusinessProductPlan businessProductPlan =
				CommonTestRoutines.createDummyBusinessProductPlan(new Product(), new Payer());
		businessProductPlan.setModelAction(PersistanceActionEnum.INSERT);
		request.setBusinessProductPlan(businessProductPlan);

		Mockito.when(getProductPlanBAC().insertBusinessProductPlan(businessProductPlan)).thenReturn(
				new InternalResultsResponse<BusinessProductPlan>());

		MaintenanceResponse response = getProductPlanBAI().insertBusinessProductPlan(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanBAC()).insertBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Test insert of a {@link BusinessProductPlan} that takes an exception.
	 */
	@Test
	public void testInsertBusinessProductPlanException()
	{
		BusinessProductPlanMaintenanceRequest request = new BusinessProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		BusinessProductPlan businessProductPlan =
				CommonTestRoutines.createDummyBusinessProductPlan(new Product(), new Payer());
		businessProductPlan.setModelAction(PersistanceActionEnum.INSERT);
		request.setBusinessProductPlan(businessProductPlan);

		Mockito.when(getProductPlanBAC().insertBusinessProductPlan(businessProductPlan)).thenThrow(
				new RuntimeException());

		MaintenanceResponse response = getProductPlanBAI().insertBusinessProductPlan(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanBAC()).insertBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Test insert of a {@link BusinessProductPlan} that takes an error.
	 */
	@Test
	public void testInsertBusinessProductPlanWithError()
	{
		BusinessProductPlanMaintenanceRequest request = new BusinessProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		BusinessProductPlan businessProductPlan = null;
		request.setBusinessProductPlan(businessProductPlan);

		Mockito.when(getProductPlanBAC().insertBusinessProductPlan(businessProductPlan)).thenReturn(
				new InternalResultsResponse<BusinessProductPlan>());

		MaintenanceResponse response = getProductPlanBAI().insertBusinessProductPlan(request);

		CommonTestRoutines.assertMessages(response, LOG, BusinessProductPlanValidator.BUSINESS_PRODUCT_PLAN_REQUIRED);

		Mockito.verify(getProductPlanBAC(), Mockito.never()).insertBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Test successful insert of a {@link TemplateProductPlan}.
	 */
	@Test
	public void testInsertTemplateProductPlan()
	{
		TemplateProductPlanMaintenanceRequest request = new TemplateProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		TemplateProductPlan templateProductPlan =
				CommonTestRoutines.createDummyTemplateProductPlan(new Product(), new Payer());
		templateProductPlan.setModelAction(PersistanceActionEnum.INSERT);
		request.setTemplateProductPlan(templateProductPlan);

		Mockito.when(getProductPlanBAC().insertTemplateProductPlan(templateProductPlan)).thenReturn(
				new InternalResultsResponse<TemplateProductPlan>());

		MaintenanceResponse response = getProductPlanBAI().insertTemplateProductPlan(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanBAC()).insertTemplateProductPlan(templateProductPlan);
	}

	/**
	 * Test insert of a {@link TemplateProductPlan} that takes an exception.
	 */
	@Test
	public void testInsertTemplateProductPlanException()
	{
		TemplateProductPlanMaintenanceRequest request = new TemplateProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		TemplateProductPlan templateProductPlan =
				CommonTestRoutines.createDummyTemplateProductPlan(new Product(), new Payer());
		templateProductPlan.setModelAction(PersistanceActionEnum.INSERT);
		request.setTemplateProductPlan(templateProductPlan);

		Mockito.when(getProductPlanBAC().insertTemplateProductPlan(templateProductPlan)).thenThrow(
				new RuntimeException());

		MaintenanceResponse response = getProductPlanBAI().insertTemplateProductPlan(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanBAC()).insertTemplateProductPlan(templateProductPlan);
	}

	/**
	 * Test insert of a {@link TemplateProductPlan} that takes an error.
	 */
	@Test
	public void testInsertTemplateProductPlanWithError()
	{
		TemplateProductPlanMaintenanceRequest request = new TemplateProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		TemplateProductPlan templateProductPlan = null;
		request.setTemplateProductPlan(templateProductPlan);

		Mockito.when(getProductPlanBAC().insertTemplateProductPlan(templateProductPlan)).thenReturn(
				new InternalResultsResponse<TemplateProductPlan>());

		MaintenanceResponse response = getProductPlanBAI().insertTemplateProductPlan(request);

		CommonTestRoutines.assertMessages(response, LOG, TemplateProductPlanValidator.TEMPLATE_PRODUCT_PLAN_REQUIRED);

		Mockito.verify(getProductPlanBAC(), Mockito.never()).insertTemplateProductPlan(templateProductPlan);
	}

	/**
	 * Test successful update of a {@link BusinessProductPlan}.
	 */
	@Test
	public void testUpdateBusinessProductPlan()
	{
		BusinessProductPlanMaintenanceRequest request = new BusinessProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		BusinessProductPlan businessProductPlan =
				CommonTestRoutines.createDummyBusinessProductPlan(new Product(), new Payer());
		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		request.setBusinessProductPlan(businessProductPlan);

		Mockito.when(getProductPlanBAC().updateBusinessProductPlan(businessProductPlan)).thenReturn(
				new InternalResultsResponse<BusinessProductPlan>());

		MaintenanceResponse response = getProductPlanBAI().updateBusinessProductPlan(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanBAC()).updateBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Test update of a {@link BusinessProductPlan} with an Optimistic Locking error.
	 */
	@Test
	public void testUpdateBusinessProductPlanOptimisticLocking()
	{
		BusinessProductPlanMaintenanceRequest request = new BusinessProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		BusinessProductPlan businessProductPlan =
				CommonTestRoutines.createDummyBusinessProductPlan(new Product(), new Payer());
		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		request.setBusinessProductPlan(businessProductPlan);

		InternalResultsResponse<BusinessProductPlan> internalResponse =
				new InternalResultsResponse<BusinessProductPlan>();
		internalResponse.setStatus(Status.OptimisticLockingError);
		Mockito.when(getProductPlanBAC().updateBusinessProductPlan(businessProductPlan)).thenReturn(internalResponse);

		MaintenanceResponse response = getProductPlanBAI().updateBusinessProductPlan(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.OL_ERROR);

		Mockito.verify(getProductPlanBAC()).updateBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Test update of a {@link BusinessProductPlan} that takes an exception.
	 */
	@Test
	public void testUpdateBusinessProductPlanException()
	{
		BusinessProductPlanMaintenanceRequest request = new BusinessProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		BusinessProductPlan businessProductPlan =
				CommonTestRoutines.createDummyBusinessProductPlan(new Product(), new Payer());
		businessProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		request.setBusinessProductPlan(businessProductPlan);

		Mockito.when(getProductPlanBAC().updateBusinessProductPlan(businessProductPlan)).thenThrow(
				new RuntimeException());

		MaintenanceResponse response = getProductPlanBAI().updateBusinessProductPlan(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanBAC()).updateBusinessProductPlan(businessProductPlan);
	}

	/**
	 * Test successful update of a {@link TemplateProductPlan}.
	 */
	@Test
	public void testUpdateTemplateProductPlan()
	{
		TemplateProductPlanMaintenanceRequest request = new TemplateProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		TemplateProductPlan templateProductPlan =
				CommonTestRoutines.createDummyTemplateProductPlan(new Product(), new Payer());
		templateProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		request.setTemplateProductPlan(templateProductPlan);

		Mockito.when(getProductPlanBAC().updateTemplateProductPlan(templateProductPlan)).thenReturn(
				new InternalResultsResponse<TemplateProductPlan>());

		MaintenanceResponse response = getProductPlanBAI().updateTemplateProductPlan(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getProductPlanBAC()).updateTemplateProductPlan(templateProductPlan);
	}

	/**
	 * Test update of a {@link TemplateProductPlan} that takes an exception.
	 */
	@Test
	public void testUpdateTemplateProductPlanException()
	{
		TemplateProductPlanMaintenanceRequest request = new TemplateProductPlanMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		TemplateProductPlan templateProductPlan =
				CommonTestRoutines.createDummyTemplateProductPlan(new Product(), new Payer());
		templateProductPlan.setModelAction(PersistanceActionEnum.UPDATE);
		request.setTemplateProductPlan(templateProductPlan);

		Mockito.when(getProductPlanBAC().updateTemplateProductPlan(templateProductPlan)).thenThrow(
				new RuntimeException());

		MaintenanceResponse response = getProductPlanBAI().updateTemplateProductPlan(request);
		CommonTestRoutines.assertMessages(response, LOG, ProductPlanBAIImpl.DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getProductPlanBAC()).updateTemplateProductPlan(templateProductPlan);
	}
}
