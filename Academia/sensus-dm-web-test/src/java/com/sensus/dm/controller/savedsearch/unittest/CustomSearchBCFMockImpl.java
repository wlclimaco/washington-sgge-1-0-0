package com.sensus.dm.controller.savedsearch.unittest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.model.response.ResultsSetInfo;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.bcf.ICustomSearchBCF;
import com.sensus.dm.common.device.model.Column;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.DeviceFilterEnum;
import com.sensus.dm.common.device.model.SearchParameter;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.response.ColumnFilterResponse;
import com.sensus.dm.common.device.model.response.CustomSearchResponse;
import com.sensus.dm.common.device.model.response.InquiryCustomSearchResponse;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.ElectricMeterSearch;

/**
 * The Class CustomSearchBCFMockImpl.
 */
public class CustomSearchBCFMockImpl extends BaseMockImpl implements ICustomSearchBCF
{
	/** The Constant CUSTOM_SEARCH. */
	public static final String CUSTOM_SEARCH = "Search %d";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.ICustomSearchBCF#insertCustomSearch(com.sensus.dm.elec.device.model.request.
	 * CustomSearchRequest
	 * )
	 */
	@Override
	public CustomSearchResponse insertCustomSearch(CustomSearchRequest request)
	{
		CustomSearchResponse customSearchResponse = new CustomSearchResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			// Invalid inputs cover Failure scenario
			if (ValidationUtil.isNull(request.getCustomSearch())
					|| ValidationUtil.isNull(request.getCustomSearch().getName()))
			{
				customSearchResponse.setOperationSuccess(false);
				customSearchResponse.addMessage(new Message(TestMessageEnum.MESSAGE_ERROR.getValue(),
						MessageSeverity.Error, MessageLevel.FieldValidation));
				return customSearchResponse;
			}

			customSearchResponse.addMessage(new Message(TestMessageEnum.MESSAGE_INFO.getValue(), MessageSeverity.Info,
					MessageLevel.Other));
			customSearchResponse.setCustomSearches(new ArrayList<CustomSearch>());
			customSearchResponse.getCustomSearches().add(request.getCustomSearch());
			customSearchResponse.getCustomSearches().get(0).setId(1);
			customSearchResponse.getCustomSearches().get(0).setModelAction(PersistanceActionEnum.INSERT);
			return customSearchResponse;
		}

		return (CustomSearchResponse)testOtherDefaultModes(customSearchResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.ICustomSearchBCF#deleteCustomSearch(com.sensus.dm.elec.device.model.request.
	 * CustomSearchRequest
	 * )
	 */
	@Override
	public CustomSearchResponse deleteCustomSearch(CustomSearchRequest request)
	{
		CustomSearchResponse customSearchResponse = new CustomSearchResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (ValidationUtil.isNull(request.getCustomSearch())
					|| ValidationUtil.isNull(request.getCustomSearch().getId()))
			{
				customSearchResponse.setOperationSuccess(false);
				customSearchResponse.addMessage(new Message(TestMessageEnum.MESSAGE_ERROR.getValue(),
						MessageSeverity.Error, MessageLevel.FieldValidation));
				return customSearchResponse;
			}

			customSearchResponse.addMessage(new Message(TestMessageEnum.MESSAGE_INFO.getValue(), MessageSeverity.Info,
					MessageLevel.Other));
			return customSearchResponse;
		}
		return (CustomSearchResponse)testOtherDefaultModes(customSearchResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.ICustomSearchBCF#fetchAllCustomSearch(com.sensus.dm.elec.device.model.request.
	 * InquiryCustomSearchRequest)
	 */
	@Override
	public InquiryCustomSearchResponse fetchAllCustomSearch(InquiryCustomSearchRequest inquiryCustomSearchRequest)
	{
		InquiryCustomSearchResponse inquiryCustomSearchResponse = new InquiryCustomSearchResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			inquiryCustomSearchResponse.setCustomSearches(new ArrayList<CustomSearch>());

			CustomSearch customSearch;
			List<SearchParameter> searchParameters;
			SearchParameter searchParameter;
			ResultsSetInfo resultsSetInfo;

			List<Column> listColumns = new ArrayList<Column>();

			List<DeviceFilterEnum> listFilters = new ArrayList<DeviceFilterEnum>();

			listFilters.add(DeviceFilterEnum.ADDRESS);

			listFilters.add(DeviceFilterEnum.valueOf("GROUP"));

			Column column = new Column();
			column.setColumnEnum(DeviceColumnEnum.FLEXNET_ID);
			column.setFieldName("FLEXNET_ID");
			listColumns.add(column);

			column = new Column();
			column.setColumnEnum(DeviceColumnEnum.INSTALL_DATE);
			column.setFieldName("DATE_ADDED");
			listColumns.add(column);

			column = new Column();
			column.setColumnEnum(DeviceColumnEnum.ENCRYPTION_STATUS);
			column.setFieldName("LOCKED");
			listColumns.add(column);

			for (int i = inquiryCustomSearchRequest.getStartRow(); i < (inquiryCustomSearchRequest.getStartRow() + inquiryCustomSearchRequest
					.getPageSize()); i++)
			{

				Location location = new Location();
				location.setCity("Omaha");
				location.setAddress("Flowers Street");
				location.setZip("00000-000");

				ElectricMeterConfiguration meterConfiguration = new ElectricMeterConfiguration();
				meterConfiguration.setFirmwareMeter("1");

				Radio radio = new Radio();
				radio.setLocation(location);

				ElectricMeter electricMeter = new ElectricMeter();
				electricMeter.setRadio(radio);
				electricMeter.setConfiguration(meterConfiguration);

				// Custom Search Definition
				customSearch = new CustomSearch();
				customSearch.setId(i + 1);
				customSearch.setName(String.format(CUSTOM_SEARCH, i));
				customSearch.setDescription("Description " + i);
				customSearch.setCreateDate(new Date());
				customSearch.setListColumn(listColumns);
				customSearch.setListFilters(listFilters);
				ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch();
				electricMeterSearch.setElectricMeter(electricMeter);
				customSearch.setElectricMeterSearch(electricMeterSearch);

				// Custom Search Parameters Definition
				searchParameters = new ArrayList<SearchParameter>();

				searchParameter = new SearchParameter();
				searchParameter.setFilterEnum(DeviceFilterEnum.INSTALL_DATE_START);

				Calendar initDate = Calendar.getInstance();
				initDate.set(Calendar.HOUR_OF_DAY, 0);
				initDate.set(Calendar.MINUTE, 0);
				initDate.set(Calendar.SECOND, 0);

				Calendar endDate = Calendar.getInstance();
				endDate.set(Calendar.HOUR_OF_DAY, 23);
				endDate.set(Calendar.MINUTE, 59);
				endDate.set(Calendar.SECOND, 59);

				searchParameter.setName(new Date().toString());
				searchParameter.setValue(new Date().toString());
				searchParameters.add(searchParameter);

				searchParameter = new SearchParameter();
				searchParameter.setFilterEnum(DeviceFilterEnum.INSTALL_DATE_END);
				searchParameter.setName(new Date().toString());
				searchParameter.setValue(endDate.toString());
				searchParameters.add(searchParameter);

				searchParameter = new SearchParameter();
				searchParameter.setFilterEnum(DeviceFilterEnum.DATE_FORMAT);
				searchParameter.setValue("mm/dd/yyyy");
				searchParameters.add(searchParameter);

				customSearch.setSearchParameters(searchParameters);
				// Custom Search Response
				resultsSetInfo = new ResultsSetInfo();
				resultsSetInfo.setTotalRowsAvailable(inquiryCustomSearchRequest.getPageSize());
				inquiryCustomSearchResponse.getCustomSearches().add(customSearch);
				inquiryCustomSearchResponse.setResultsSetInfo(resultsSetInfo);
			}
			inquiryCustomSearchResponse.setListColumn(listColumns);
			inquiryCustomSearchResponse.setListFilters(listFilters);
			inquiryCustomSearchResponse.addMessage(new Message(TestMessageEnum.MESSAGE_INFO.getValue(),
					MessageSeverity.Info, MessageLevel.Other));

			return inquiryCustomSearchResponse;
		}

		return (InquiryCustomSearchResponse)testOtherDefaultModes(inquiryCustomSearchResponse);
	}

	@Override
	public ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		ColumnFilterResponse columnFilterResponse = new ColumnFilterResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			List<Column> columns = new ArrayList<Column>();
			columns.add(new Column(DeviceColumnEnum.DEVICE_TYPE));
			columns.add(new Column(DeviceColumnEnum.DESCRIPTION));
			columns.add(new Column(DeviceColumnEnum.DEVICE_ID));
			columns.add(new Column(DeviceColumnEnum.NETWORK_ADDRESS));
			columns.add(new Column(DeviceColumnEnum.INSTALL_DATE));
			columns.add(new Column(DeviceColumnEnum.MAP_IT));
			columns.add(new Column(DeviceColumnEnum.ENCRYPTION_STATUS));
			columns.add(new Column(DeviceColumnEnum.LIFECYCLE_STATE));

			columnFilterResponse.setListColumn(columns);

			List<Column> allColumns = new ArrayList<Column>();
			allColumns.add(new Column(DeviceColumnEnum.ADDRESS));
			allColumns.add(new Column(DeviceColumnEnum.ZIP_CODE));
			allColumns.add(new Column(DeviceColumnEnum.CONNECTION_STATUS));
			allColumns.add(new Column(DeviceColumnEnum.REMOTE_DISCONNECT));
			allColumns.add(new Column(DeviceColumnEnum.LIFECYCLE_STATE));
			allColumns.add(new Column(DeviceColumnEnum.FLEXNET_ID));
			allColumns.add(new Column(DeviceColumnEnum.DESCRIPTION));
			allColumns.add(new Column(DeviceColumnEnum.DEVICE_ID));
			allColumns.add(new Column(DeviceColumnEnum.INSTALL_DATE));
			allColumns.add(new Column(DeviceColumnEnum.ENCRYPTION_STATUS));
			allColumns.add(new Column(DeviceColumnEnum.ENCRYPTION_SUPPORT));
			allColumns.add(new Column(DeviceColumnEnum.LATITUDE));
			allColumns.add(new Column(DeviceColumnEnum.LONGITUDE));
			allColumns.add(new Column(DeviceColumnEnum.DEVICE_TYPE));
			allColumns.add(new Column(DeviceColumnEnum.DESCRIPTION));

			columnFilterResponse.setAdditionalColumns(allColumns);

			List<DeviceFilterEnum> filters = new ArrayList<DeviceFilterEnum>();
			filters.add(DeviceFilterEnum.ADDRESS);
			filters.add(DeviceFilterEnum.TAG);
			filters.add(DeviceFilterEnum.GROUP);

			List<DeviceFilterEnum> allFilters = new ArrayList<DeviceFilterEnum>();
			allFilters.add(DeviceFilterEnum.GROUP);
			allFilters.add(DeviceFilterEnum.LIFECYCLE_STATE);
			allFilters.add(DeviceFilterEnum.DEVICE_TYPE);
			allFilters.add(DeviceFilterEnum.INSTALL_DATE_END);
			allFilters.add(DeviceFilterEnum.ADDRESS);
			allFilters.add(DeviceFilterEnum.METER_FIRMWARE);
			allFilters.add(DeviceFilterEnum.TAG);
			allFilters.add(DeviceFilterEnum.ENCRYPTION_STATUS);
			allFilters.add(DeviceFilterEnum.REMOTE_DISCONNECT);
			allFilters.add(DeviceFilterEnum.CONNECTION_STATUS);

			columnFilterResponse.setFilters(filters);
			columnFilterResponse.setAdditionalFilters(allFilters);

			return columnFilterResponse;
		}
		return (ColumnFilterResponse)testOtherDefaultModes(columnFilterResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcf.ICustomSearchBCF#updateColumnFilters(com.sensus.dm.elec.device.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public ColumnFilterResponse updateColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		ColumnFilterResponse columnFilterResponse = new ColumnFilterResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			// Invalid inputs cover Failure scenario
			if ((ValidationUtil.isNullOrEmpty(columnFilterRequest.getFilters())
			&& ValidationUtil.isNullOrEmpty(columnFilterRequest.getListColumn())))
			{
				columnFilterResponse.setOperationSuccess(false);
				columnFilterResponse.addMessage(new Message(TestMessageEnum.MESSAGE_ERROR.getValue(),
						MessageSeverity.Error, MessageLevel.FieldValidation));
				return columnFilterResponse;
			}

			columnFilterResponse.addMessage(new Message(TestMessageEnum.MESSAGE_INFO.getValue(), MessageSeverity.Info,
					MessageLevel.Other));
			return columnFilterResponse;
		}

		return (ColumnFilterResponse)testOtherDefaultModes(columnFilterResponse);
	}

}