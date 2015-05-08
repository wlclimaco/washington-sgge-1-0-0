package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.dac.IContactDAC;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.RiskDACD;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEvent;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class LocationDACImpl.
 */
public class LocationDACImpl extends SqlSessionDaoSupport implements ILocationDAC
{
	/** The Constant LOCATION_NAMESPACE. */
	private static final String LOCATION_NAMESPACE = "LocationMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant LOCATION_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String LOCATION_STMT_FETCH_ALL_BY_REQUEST = LOCATION_NAMESPACE
			+ "fetchAllLocationsByRequest";

	/** The Constant LOCATION_STMT_FETCH_COUNT. */
	private static final String LOCATION_STMT_FETCH_COUNT = LOCATION_NAMESPACE + "fetchLocationRowCount";

	/** The Constant LOCATION_STMT_FETCH_BY_ID. */
	private static final String LOCATION_STMT_FETCH_BY_ID = LOCATION_NAMESPACE + "fetchLocationById";

	/** The Constant LOCATION_STMT_INSERT. */
	private static final String LOCATION_STMT_INSERT = LOCATION_NAMESPACE + "insertLocation";

	/** The Constant LOCATION_STMT_ASSOC_LOC_TO_CONTACT. */
	private static final String LOCATION_STMT_ASSOC_LOC_TO_CONTACT = LOCATION_NAMESPACE
			+ "associateLocationWithContact";

	/** The Constant LOCATION_STMT_UPDATE. */
	private static final String LOCATION_STMT_UPDATE = LOCATION_NAMESPACE + "updateLocation";

	/** The Constant LOCATION_STMT_DELETE. */
	private static final String LOCATION_STMT_DELETE = LOCATION_NAMESPACE + "deleteLocationById";

	/** The Constant STMT_VERSION. */
	private static final String LOCATION_STMT_VERSION = LOCATION_NAMESPACE + "fetchVersionNumber";

	/** The Constant LOCATION_STMT_UPDATE_LOCATION_STATUS. */
	private static final String LOCATION_STMT_UPDATE_LOCATION_STATUS = CBOF_NAMESPACE + "updateBusinessStatus";

	/** The Constant FREQUENCY_BASED_EVENT_NAMESPACE. */
	private static final String FREQUENCY_BASED_EVENT_NAMESPACE = "FrequencyEventMap.";

	/** The Constant LOCATION_STMT_FETCH_BY_ID. */
	private static final String FREQUENCY_BASED_EVENT_STMT_FETCH_BY_ID = FREQUENCY_BASED_EVENT_NAMESPACE
			+ "fetchFrequencyById";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LocationDACImpl.class);

	/** The contact dac. */
	private IContactDAC contactDAC;

	/**
	 * Gets the contact dac.
	 *
	 * @return the contact dac
	 */
	public IContactDAC getContactDAC()
	{
		return contactDAC;
	}

	/**
	 * Sets the contact dac.
	 *
	 * @param contactDAC the contact dac
	 */
	public void setContactDAC(IContactDAC contactDAC)
	{
		this.contactDAC = contactDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ILocationDAC#insertLocation(com.prosperitasglobal.sendsolv.model
	 * .Location)
	 */
	@Override
	public InternalResultsResponse<Location> insertLocation(Location location)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), LOCATION_STMT_INSERT, location, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainLocationAssociations(location, response);

		// Finally, if something was inserted then add the Location to the result.
		if (insertCount > 0)
		{
			response.addResult(location);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ILocationDAC#updateLocation(com.prosperitasglobal.sendsolv.model.Location)
	 */
	@Override
	public InternalResultsResponse<Location> updateLocation(Location location)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(location.getModelAction())
				&& (location.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdateOL(getSqlSession(), LOCATION_STMT_UPDATE, location,
							LOCATION_STMT_VERSION, response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainLocationAssociations(location, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(location);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ILocationDAC#deleteLocation(com.prosperitasglobal.sendsolv.model.Location)
	 */
	@Override
	public InternalResponse deleteLocation(Location location)
	{
		InternalResponse response = new InternalResponse();

		QATMyBatisDacHelper.doRemove(getSqlSession(), LOCATION_STMT_DELETE, location, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ILocationDAC#fetchAllLocations()
	 */
	@Override
	public InternalResultsResponse<Location> fetchAllLocations()
	{
		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), LOCATION_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ILocationDAC#fetchFrequencyBasedEventById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<FrequencyBasedEvent> fetchFrequencyBasedEventById(Integer id)
	{
		InternalResultsResponse<FrequencyBasedEvent> response = new InternalResultsResponse<FrequencyBasedEvent>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), FREQUENCY_BASED_EVENT_STMT_FETCH_BY_ID, id, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ILocationDAC#fetchLocationById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Location> fetchLocationById(FetchByIdRequest request)
	{
		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), LOCATION_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ILocationDAC#fetchLocationByRequest(com.prosperitasglobal.sendsolv.model
	 * .request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Location> fetchLocationByRequest(PagedInquiryRequest request)
	{

		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, LOCATION_STMT_FETCH_COUNT,
				LOCATION_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain location associations.
	 *
	 * @param location the location
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainLocationAssociations(Location location, InternalResultsResponse<Location> response)
	{
		Integer count = 0;
		// First Maintain Contacts
		if (ValidationUtil.isNullOrEmpty(location.getContactList()))
		{
			return count;
		}
		// For Each Contact...
		for (Contact contact : location.getContactList())
		{
			// Make sure we set the parent key
			contact.setParentKey(location.getId());

			if (ValidationUtil.isNull(contact.getModelAction()))
			{
				continue;
			}

			switch (contact.getModelAction())
			{
				case INSERT:
					count =
					getContactDAC().insertContact(contact, LOCATION_STMT_ASSOC_LOC_TO_CONTACT,
							response);
					break;
				case UPDATE:
					count = getContactDAC().updateContact(contact, response);
					break;
				case DELETE:
					count = getContactDAC().deleteBusinessContact(contact, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Location missing!");
					}
					break;
			}
		}

		return count;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ILocationDAC#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Risk> updateRisk(RiskMaintenanceRequest request)
	{
		return RiskDACD.updateRisk(getSqlSession(), request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ILocationDAC#applyStatus(com.prosperitasglobal.sendsolv.model.Location)
	 */
	@Override
	public InternalResponse applyLocationStatus(Location location)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), LOCATION_STMT_UPDATE_LOCATION_STATUS, location,
				response);

		return response;
	}
}
