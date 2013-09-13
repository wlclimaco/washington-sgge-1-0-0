package com.sensus.dm.common.device.bcl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.base.model.DeviceTypeModelPermissions;
import com.sensus.dm.common.base.model.DeviceTypePermissions;
import com.sensus.dm.common.base.model.ServicesPermissions;
import com.sensus.dm.common.base.model.ServicesTypePermissions;
import com.sensus.dm.common.device.dac.IDeviceDAC;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.AlarmsTypesCount;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceTypeCount;
import com.sensus.dm.common.device.model.GeocodeDeviceInfo;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class ElectricMeterBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/common/device/devicebclimpltest.xml"})
public class DeviceBCLImplTest extends AbstractTestBaseBusiness
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant NULL_VALUE_STRING. */
	private static final String NULL_VALUE_STRING = "NULL";

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The device bcl. */
	private IDeviceBCL deviceBCL; // injected by Spring through setter

	/**
	 * Gets the device bcl.
	 * 
	 * @return the device bcl
	 */
	public IDeviceBCL getDeviceBCL()
	{
		return deviceBCL;
	}

	/**
	 * Sets the device bcl.
	 * 
	 * @param deviceBCL the new device bcl
	 */
	@Resource(name = "deviceBCLTarget")
	public void setDeviceBCL(IDeviceBCL deviceBCL)
	{
		this.deviceBCL = deviceBCL;
	}

	/**
	 * Sets the device area.
	 */
	public void setDeviceArea()
	{
		setArea(getDeviceBCL(), EPMAreaEnum.DEVICE);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setDeviceArea();
	}

	/**
	 * Removes the device area.
	 */
	@After
	public void resetMocksToDeviceArea()
	{
		resetMocksData(getDeviceBCL());
		setDeviceArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch all devices type count.
	 */
	@Test
	public void testFetchAllDevicesTypeCount()
	{
		// Success situation
		DeviceRequest deviceRequest = TestBaseUtil.createDeviceRequest();
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		InternalResultsResponse<DeviceTypeCount> response = getDeviceBCL().fetchAllDevicesTypeCount(deviceRequest);
		TestBaseUtil.assertResultResponse(response);

		// Error situation
		setSituation(getDeviceBCL(), SituationsEnum.ERROR, IDeviceDAC.class);
		response = getDeviceBCL().fetchAllDevicesTypeCount(deviceRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch all by premise id.
	 */
	@Test
	public void testFetchAllByPremiseId()
	{
		// Success situation

		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE));

		InternalResponse response = getDeviceBCL().fetchAllByPremiseId(request);

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch device by id import.
	 */
	@Test
	public void testFetchDeviceByIdImport()
	{
		// Success situation
		DeviceRequest request = TestBaseUtil.createDeviceRequest();
		request.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER));
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		InternalResultsResponse<Device> response = getDeviceBCL().fetchDeviceByIdImport(request);

		TestBaseUtil.assertResultResponse(response);

		// check specifically for the values in the DB here...
		for (Device device : response.getResultsList())
		{
			assertNotNull(device.getRadio().getFlexNetId());
		}
	}

	/**
	 * Test fetch alarm history.
	 */
	@Test
	public void testFetchAlarmHistory()
	{
		// Success situation
		DeviceRequest deviceRequest = TestBaseUtil.createDeviceRequest();

		InternalResultsResponse<Alarm> response = getDeviceBCL().fetchAlarmHistory(deviceRequest);
		TestBaseUtil.assertResultResponse(response);

		// Error situation
		setSituation(getDeviceBCL(), SituationsEnum.ERROR, IDeviceDAC.class);
		response = getDeviceBCL().fetchAlarmHistory(deviceRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch devices bounds to map.
	 */
	@Test
	public void testFetchDevicesBoundsToMap()
	{
		// Success situation
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequest();

		InternalResultsResponse<GeocodeDeviceInfo> response = getDeviceBCL().fetchDevicesBoundsToMap(request);
		TestBaseUtil.assertResultResponse(response);

		// Error situation
		setSituation(getDeviceBCL(), SituationsEnum.ERROR, IDeviceDAC.class);
		response = getDeviceBCL().fetchDevicesBoundsToMap(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch devices to map.
	 */
	@Test
	public void testFetchDevicesToMap()
	{
		// Success situation
		InquiryDeviceRequest request = TestBaseUtil.createInquiryDeviceRequest();

		InternalResultsResponse<GeocodeDeviceInfo> response = getDeviceBCL().fetchDevicesToMap(request);
		TestBaseUtil.assertResultResponse(response);

		// Error situation
		setSituation(getDeviceBCL(), SituationsEnum.ERROR, IDeviceDAC.class);
		response = getDeviceBCL().fetchDevicesToMap(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch services by device type.
	 */
	@Test
	public void testFetchServicesByDeviceType()
	{
		// Success situation
		DeviceRequest request = new DeviceRequest();
		request.addGrantedAuthority("ROLE_EPM_ADMIN");
		request.addGrantedAuthority("ROLE_EPM_SYSTEM_OPERATOR");
		request.addGrantedAuthority("ROLE_EPM_CUSTOMER_SUPPORT");
		request.addGrantedAuthority("ROLE_EPM_BILLING_MANAGER");

		InternalResultsResponse<ServicesPermissions> response =
				new InternalResultsResponse<ServicesPermissions>();
		response = getDeviceBCL().fetchServicesByDeviceType(request);

		assertNotNull(response);
		assertFalse(response.isInError());
		assertTrue(response.getMessageInfoList().isEmpty());
		assertTrue(response.getResultsList().isEmpty());

		request.addGrantedAuthority("ROLE_EPM_SERVICE_ELECTRIC");
		request.addGrantedAuthority("ROLE_EPM_SERVICE_WATER");
		request.addGrantedAuthority("ROLE_EPM_SERVICE_GAS");
		response = getDeviceBCL().fetchServicesByDeviceType(request);

		assertNotNull(response);
		assertFalse(response.isInError());
		assertTrue(response.getMessageInfoList().isEmpty());
		assertFalse(response.getResultsList().isEmpty());

		for (ServicesTypePermissions setp : response.getFirstResult().getServicesTypePermissions())
		{
			System.out.println("Service Type **" + setp.getServiceTypeEnum() + "**");
			for (DeviceTypePermissions dtp : setp.getDeviceTypePermissions())
			{
				System.out.println(SPACE + "Device Type: *" + dtp.getDeviceType().name() + ASTERISK_VALUE);

				System.out.println(SPACE + SPACE + "*Default Action*");
				for (ActionType ap : dtp.getDefaultActions().getActions())
				{
					printAction(ap);
				}

				for (DeviceTypeModelPermissions dtmp : dtp.getDeviceTypeModels())
				{
					System.out.println(SPACE + SPACE + ASTERISK_VALUE + dtmp.getName() + ASTERISK_VALUE);

					System.out.println(SPACE + SPACE + SPACE + "**Actions**");
					for (ActionType ap : dtmp.getActions())
					{
						printAction(ap);
					}

					System.out.println(SPACE + SPACE + SPACE + "**UI Modules**");
					if (ValidationUtil.isNull(dtmp.getUiModules()))
					{
						System.out.println(SPACE + SPACE + SPACE + SPACE + NULL_VALUE_STRING);
					}
					else
					{
						System.out.println(SPACE + SPACE + SPACE + SPACE + "*Contents*");
						if (!ValidationUtil.isNull(dtmp.getUiModules().getContents()))
						{
							for (String content : dtmp.getUiModules().getContents())
							{
								System.out.println(SPACE + SPACE + SPACE + SPACE + SPACE
										+ content);
							}
						}

						System.out.println(SPACE + SPACE + SPACE + SPACE + "*Summary Data*");
						if (!ValidationUtil.isNull(dtmp.getUiModules().getSummaryDatas()))
						{
							for (String summaryData : dtmp.getUiModules().getSummaryDatas())
							{
								System.out.println(SPACE + SPACE + SPACE + SPACE + SPACE
										+ summaryData);
							}
						}

						System.out.println(SPACE + SPACE + SPACE + SPACE + "*Tabs*");
						if (!ValidationUtil.isNull(dtmp.getUiModules().getTabs()))
						{
							for (String tab : dtmp.getUiModules().getTabs())
							{
								System.out.println(SPACE + SPACE + SPACE + SPACE + SPACE
										+ tab);
							}
						}
					}
				}

				System.out.println(SPACE + SPACE + "*Alarms*");
				if (!ValidationUtil.isNull(dtp.getAlarms()))
				{
					for (AlarmEnum alarmEnum : dtp.getAlarms())
					{
						System.out.println(SPACE + SPACE + ASTERISK_VALUE + alarmEnum.getValue()
								+ ASTERISK_VALUE);
					}
				}
			}

			System.out.println(SPACE + "Dashboard Module");
			for (String dashboardUiModules : setp.getDashboardUiModules())
			{
				System.out.println(SPACE + SPACE + dashboardUiModules);
			}

			System.out.println(SPACE + "Schedule Actions");
			for (ActionType ap : setp.getScheduleActions())
			{
				printAction(ap);
			}

			System.out.println(SPACE + "List Actions");
			for (ActionType ap : setp.getListActions())
			{
				printAction(ap);
			}

			System.out.println(SPACE + "Event History Filter Actions");
			for (ActionType ap : setp.getEventHistoryFilterActions())
			{
				printAction(ap);
			}
		}
	}

	/**
	 * Test fetch all device type descriptions.
	 */
	@Test
	public void testFetchAllDeviceTypeDescriptions()
	{
		// Success situation

		InternalResultsResponse<DeviceModel> response =
				getDeviceBCL().fetchAllDeviceTypeDescriptions(new DeviceRequest());

		TestBaseUtil.assertResultResponse(response);

		for (DeviceModel dt : response.getResultsList())
		{
			assertNotNull(dt.getDescription());
		}
	}

	/**
	 * Test fetch quarantine count.
	 */
	@Test
	public void testFetchQuarantineCount()
	{
		// Success situation

		InternalResultsResponse<Integer> response =
				getDeviceBCL().fetchQuarantineCount(new DeviceRequest());

		TestBaseUtil.assertResultResponse(response);
	}
	
	/**
	 * Test fetch alarms types count.
	 */
	@Test
	public void testFetchAlarmsTypesCount()
	{
		// Success situation
		DeviceRequest deviceRequest = TestBaseUtil.createDeviceRequest();
		deviceRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		InternalResultsResponse<AlarmsTypesCount> response = getDeviceBCL().fetchAlarmsTypesCount(deviceRequest);
		TestBaseUtil.assertResultResponse(response);

		// Error situation
		setSituation(getDeviceBCL(), SituationsEnum.ERROR, IDeviceDAC.class);
		response = getDeviceBCL().fetchAlarmsTypesCount(deviceRequest);
		assertMessages(response, ERROR_CODE);
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

	/**
	 * Prints the action.
	 * 
	 * @param ap the ap
	 */
	private void printAction(ActionType ap)
	{
		System.out.println(SPACE + SPACE + SPACE + SPACE + STR_PARENTHESIS_OPEN
				+ ap.getActionTypeEnum().getActionType() + STR_PARENTHESIS_CLOSE
				+ ap.getActionTypeEnum().getActionTypeName());
		System.out.println(SPACE + SPACE + SPACE + SPACE + SPACE
				+ SPACE + STR_PARENTHESIS_OPEN
				+ ap.getActionTypeEnum().getActionCategoryEnum().getActionTypeCategory()
				+ STR_PARENTHESIS_CLOSE
				+ ap.getActionTypeEnum().getActionCategoryEnum().getActionTypeCategoryName());
	}

}
