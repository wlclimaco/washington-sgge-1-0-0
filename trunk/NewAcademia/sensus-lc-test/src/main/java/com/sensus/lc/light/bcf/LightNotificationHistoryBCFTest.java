package com.sensus.lc.light.bcf;

import static com.sensus.lc.base.TestBaseUtil.NUMBER_RANGE;
import static com.sensus.lc.base.TestBaseUtil.RANDOM;
import static org.junit.Assert.assertFalse;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.bcl.ILightNotificationHistoryBCL;
import com.sensus.lc.light.model.criteria.NotificationHistoryCriteria;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.light.model.response.LightHistoryResponse;
import com.sensus.lc.light.model.response.NotificationHistoryResponse;

/**
 * The Class LightNotificationHistoryBCFTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/mlc/light/lightnotificationhistorybcfimpletest.xml"})
public class LightNotificationHistoryBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant FIELD_NOTIFICATION_HISTORY_ID. */
	private static final String FIELD_NOTIFICATION_HISTORY_ID = "notification_history_id";

	/** The Constant ARBITRARY_NUMBER_25. */
	private static final int ARBITRARY_NUMBER_25 = 25;

	/** The notification history bcf. */
	private ILightNotificationHistoryBCF notificationHistoryBCF;

	/** The Constant DEFAULT_LIGHT_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LIGHT_BCF_EXCEPTION_MSG = "sensus.mlc.lightbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED = "sensus.mlc.page.size.required";

	/** The Constant DEFAULT_EXCEPTION. */
	private static final String DEFAULT_EXCEPTION = "sensus.mlc.lightbcfimpl.defaultexception";

	/**
	 * Gets the notification history bcf.
	 * 
	 * @return the notificationHistoryBCF
	 */
	public ILightNotificationHistoryBCF getNotificationHistoryBCF()
	{
		return notificationHistoryBCF;
	}

	/**
	 * Sets the notification history bcf.
	 * 
	 * @param notificationHistoryBCF the notificationHistoryBCF to set
	 */
	@Resource
	public void setNotificationHistoryBCF(ILightNotificationHistoryBCF notificationHistoryBCF)
	{
		this.notificationHistoryBCF = notificationHistoryBCF;
	}

	@Test
	public void testFetchLightNotificationHistoryByRequest()
	{
		// Success Situation
		NotificationHistoryRequest request = TestBaseUtil.createNotificationHistoryRequest();
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		NotificationHistoryResponse response =
				getNotificationHistoryBCF().fetchLightNotificationHistoryByRequest(request);
		Assert.assertNotNull(response);

		// Error situation without notification history criteria
		request = TestBaseUtil.createNotificationHistoryRequest();

		response = getNotificationHistoryBCF().fetchLightNotificationHistoryByRequest(request);
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);

		// Validation/Error situation - without the user context
		request = new NotificationHistoryRequest();
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistoryByRequest(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation/Error situation - without the user ID
		request = new NotificationHistoryRequest();
		request.setUserContext(new UserContext());
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistoryByRequest(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation/Error situation - without the tenant
		request = new NotificationHistoryRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistoryByRequest(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation/Error situation - Sort expression is required
		request = new NotificationHistoryRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setPageSize(ARBITRARY_NUMBER_25);
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistoryByRequest(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation/Error situation - Pagination size is required
		request = new NotificationHistoryRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(FIELD_NOTIFICATION_HISTORY_ID, false));
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistoryByRequest(request);
		assertMessages(response, SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED);

		// Error situation
		setSituation(getNotificationHistoryBCF(), SituationsEnum.ERROR, ILightNotificationHistoryBCL.class);
		request = TestBaseUtil.createNotificationHistoryRequest();
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistoryByRequest(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		setSituation(getNotificationHistoryBCF(), SituationsEnum.EXCEPTION, ILightNotificationHistoryBCL.class);
		request = TestBaseUtil.createNotificationHistoryRequest();
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistoryByRequest(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	@Test
	public void testFetchNotificationHistoryById()
	{
		// Success Situation
		NotificationHistoryRequest request = TestBaseUtil.createNotificationHistoryRequest();
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		NotificationHistoryResponse response =
				getNotificationHistoryBCF().fetchNotificationHistoryById(request);
		Assert.assertNotNull(response);

		// Error situation without notification history criteria
		request = TestBaseUtil.createNotificationHistoryRequest();

		response = getNotificationHistoryBCF().fetchNotificationHistoryById(request);
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);

		// Validation/Error situation - without the user context
		request = new NotificationHistoryRequest();
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchNotificationHistoryById(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation/Error situation - without the user ID
		request = new NotificationHistoryRequest();
		request.setUserContext(new UserContext());
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchNotificationHistoryById(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation/Error situation - without the tenant
		request = new NotificationHistoryRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchNotificationHistoryById(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Error situation
		setSituation(getNotificationHistoryBCF(), SituationsEnum.ERROR, ILightNotificationHistoryBCL.class);
		request = TestBaseUtil.createNotificationHistoryRequest();
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchNotificationHistoryById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		setSituation(getNotificationHistoryBCF(), SituationsEnum.EXCEPTION, ILightNotificationHistoryBCL.class);
		request = TestBaseUtil.createNotificationHistoryRequest();
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchNotificationHistoryById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	@Test
	public void testFetchLightNotificationHistory()
	{
		// Success Situation
		NotificationHistoryRequest request = TestBaseUtil.createNotificationHistoryRequest();
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		LightHistoryResponse response =
				getNotificationHistoryBCF().fetchLightNotificationHistory(request);
		Assert.assertNotNull(response);

		// Error situation without notification history criteria
		request = TestBaseUtil.createNotificationHistoryRequest();

		response = getNotificationHistoryBCF().fetchLightNotificationHistory(request);
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);

		// Validation/Error situation - without the user context
		request = new NotificationHistoryRequest();
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistory(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation/Error situation - without the user ID
		request = new NotificationHistoryRequest();
		request.setUserContext(new UserContext());
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistory(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation/Error situation - without the tenant
		request = new NotificationHistoryRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistory(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation/Error situation - Sort expression is required
		request = new NotificationHistoryRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setPageSize(ARBITRARY_NUMBER_25);
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistory(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation/Error situation - Pagination size is required
		request = new NotificationHistoryRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(FIELD_NOTIFICATION_HISTORY_ID, false));
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistory(request);
		assertMessages(response, SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED);

		// Error situation
		setSituation(getNotificationHistoryBCF(), SituationsEnum.ERROR, ILightNotificationHistoryBCL.class);
		request = TestBaseUtil.createNotificationHistoryRequest();
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistory(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		setSituation(getNotificationHistoryBCF(), SituationsEnum.EXCEPTION, ILightNotificationHistoryBCL.class);
		request = TestBaseUtil.createNotificationHistoryRequest();
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());

		response = getNotificationHistoryBCF().fetchLightNotificationHistory(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

}
