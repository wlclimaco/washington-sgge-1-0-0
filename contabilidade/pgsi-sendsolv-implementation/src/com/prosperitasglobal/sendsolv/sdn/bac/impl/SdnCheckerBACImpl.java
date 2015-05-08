package com.prosperitasglobal.sendsolv.sdn.bac.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.CountryUsage;
import com.prosperitasglobal.sendsolv.model.CountryUsageEnum;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Person;
import com.prosperitasglobal.sendsolv.model.PersonName;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.model.ExtendedSdnEntry;
import com.prosperitasglobal.sendsolv.sdn.model.Name;
import com.prosperitasglobal.sendsolv.sdn.model.SdnFieldEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnHistory;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatch;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchField;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchRecord;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.generated.Address;
import com.prosperitasglobal.sendsolv.sdn.model.generated.Aka;
import com.prosperitasglobal.sendsolv.sdn.model.generated.Citizenship;
import com.prosperitasglobal.sendsolv.sdn.model.generated.DateOfBirthItem;
import com.prosperitasglobal.sendsolv.sdn.model.generated.Id;
import com.prosperitasglobal.sendsolv.sdn.model.generated.Nationality;
import com.prosperitasglobal.sendsolv.sdn.model.generated.PlaceOfBirthItem;
import com.prosperitasglobal.sendsolv.sdn.model.generated.SdnEntry;
import com.prosperitasglobal.sendsolv.sdn.model.generated.SdnList;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.request.Request;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class SdnCheckerBACImpl.
 */
public class SdnCheckerBACImpl implements ISdnCheckerBAC
{
	/** The Constant SPACES. */
	private static final String SPACES = " ";

	/** The Constant SDN_FILE_NAME. */
	private static final String SDN_FILE_NAME = "sdn";

	/** The Constant SDN_FILE_EXTENSION. */
	private static final String SDN_FILE_EXTENSION = ".xml";

	// This holds the current Sdn list being used.
	/** The current sdn entry list. */
	private List<SdnEntry> currentSdnEntryList;

	// A list only with type = entity
	/** The entity sdn entry list. */
	private List<SdnEntry> entitySdnEntryList;

	// A list only with type = individual
	/** The individual sdn entry list. */
	private List<SdnEntry> individualSdnEntryList;

	// The Sdn List
	/** The sdn list. */
	private SdnList sdnList;

	// Sdn file publish date
	/** The publish date. */
	private String publishDate;

	// Sdn file record count
	/** The record count. */
	private Integer recordCount;

	// Fields injected by Spring follow

	// JAXB objects to manipulate Xml into Java and vice-versa
	/** The unmarshaller. */
	private Unmarshaller unmarshaller;

	/** The marshaller. */
	private Marshaller marshaller;

	// Local file path for Sdn file.
	/** The file path. */
	private String filePath;

	// Url for Sdn file on the web.
	/** The sdn file url. */
	private String sdnFileUrl;

	// Matches above this threshold will be considered real hits.
	/** The similarity threshold. */
	private double similarityThreshold;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SdnCheckerBACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC#checkForNewSdnFile(com.qat.framework.model.request.Request)
	 */
	@Override
	public synchronized InternalResponse checkForNewSdnFile(Request request)
	{
		InternalResponse internalResponse = new InternalResponse();

		try
		{
			// Create a temporary place to put the downloaded file
			File newFile = new File(FileUtils.getTempDirectoryPath() + SDN_FILE_NAME + SDN_FILE_EXTENSION);

			// Create handle for current file
			File currentFile = new File(getFilePath() + SDN_FILE_NAME + SDN_FILE_EXTENSION);

			// If file exists but it is not loaded, load it
			if (currentFile.exists() && ValidationUtil.isNull(getSdnList()))
			{
				InputStream stream = new FileInputStream(currentFile);
				SdnList originalSdnList = (SdnList)getUnmarshaller().unmarshal(stream);

				setSdnList(originalSdnList);
				setPublishDate(originalSdnList.getPublshInformation().getPublishDate());
				setRecordCount(originalSdnList.getPublshInformation().getRecordCount());
				loadSdnLists();
			}

			// Create a URL pointing to the location of the new file on the Web
			URL sdnUrl = new URL(getSdnFileUrl());

			// Download new file from web and save on temp location
			FileUtils.copyURLToFile(sdnUrl, newFile);

			// Create a new sdnList Object from the new file
			InputStream stream = new FileInputStream(newFile);
			SdnList newSdnList = (SdnList)getUnmarshaller().unmarshal(stream);

			// Extract publishDate and recordCount from new file
			String newPublishDate = newSdnList.getPublshInformation().getPublishDate();
			Integer newRecordCount = newSdnList.getPublshInformation().getRecordCount();

			// Compare with current file to see if it is the same
			if (filesAreTheSame(newPublishDate, newRecordCount, newFile.length(), currentFile))
			{
				// No change in SDN file
				internalResponse.setStatus(Status.NoRowsUpdatedError);
			}
			else
			{
				// New file detected, update sdnList
				setSdnList(newSdnList);
				setPublishDate(newPublishDate);
				setRecordCount(newRecordCount);
				loadSdnLists();

				File folder = new File(getFilePath());

				if (!folder.exists())
				{
					if (!folder.mkdir())
					{
						internalResponse.setStatus(Status.ExternalError);
					}
				}

				if (!internalResponse.isInError())
				{
					// Copy old file to a new file that contains date/time
					if (currentFile.exists())
					{
						// Create an updated name for the old file, now containing date/time
						String updatedName = getFilePath() + SDN_FILE_NAME
								+ new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date())
								+ SDN_FILE_EXTENSION;

						File retiredFile = new File(updatedName);

						FileUtils.moveFile(currentFile, retiredFile);
					}

					// Copy new file from temp folder to current one
					FileUtils.moveFile(newFile, currentFile);

					internalResponse.setStatus(Status.OperationSuccess);
				}
			}
		}
		catch (IOException | JAXBException e)
		{
			internalResponse.setStatus(Status.ExceptionError);
			internalResponse.addMessage(e.toString(), MessageSeverity.Fatal, MessageLevel.Other);
			LOG.debug(e.toString());
		}

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC#checkSdn(com.prosperitasglobal.sendsolv.sdn.model.request
	 * .SdnCheckerRequest)
	 */
	@Override
	public InternalResultsResponse<SdnStatusHistory> checkSdn(SdnCheckerRequest request)
	{
		return checkSdn(request.getSdnMatch());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC#checkSdn(com.prosperitasglobal.sendsolv.sdn.model.request
	 * .SdnCheckerRequest)
	 */
	@Override
	public InternalResultsResponse<SdnStatusHistory> checkMemberSdn(SdnCheckerRequest<Member> request)
	{
		return checkSdn(prepareSdnMatch(request.getPersonOrBusiness()));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC#checkRecipientSdn(com.prosperitasglobal.sendsolv.sdn.model
	 * .request.SdnCheckerRequest)
	 */
	@Override
	public InternalResultsResponse<SdnStatusHistory> checkRecipientSdn(SdnCheckerRequest<Recipient> request)
	{
		return checkSdn(preparePersonSdnMatch(request.getPersonOrBusiness()));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC#checkLiaisonSdn(com.prosperitasglobal.sendsolv.sdn.model
	 * .request.SdnCheckerRequest)
	 */
	@Override
	public InternalResultsResponse<SdnStatusHistory> checkLiaisonSdn(SdnCheckerRequest<Liaison> request)
	{
		return checkSdn(preparePersonSdnMatch(request.getPersonOrBusiness()));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC#checkBusinessSdn(com.prosperitasglobal.sendsolv.sdn.model
	 * .request.SdnCheckerRequest)
	 */
	@Override
	public InternalResultsResponse<SdnStatusHistory> checkBusinessSdn(SdnCheckerRequest<Business> request)
	{
		return checkSdn(prepareSdnMatch(request.getPersonOrBusiness()));
	}

	/**
	 * Creates a {@link SdnMatch} from a {@link SdnCheckerRequest<Liaison>} or {@link SdnCheckerRequest<Recipient>} for
	 * internal usage.
	 *
	 * @param person the person
	 * @return the sdn match
	 */
	private SdnMatch preparePersonSdnMatch(Person person)
	{
		SdnMatch sdnMatch = new SdnMatch();

		sdnMatch.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);
		sdnMatch.setParentKey(person.getId());
		sdnMatch.setFirstName(person.getFirstName());
		sdnMatch.setLastName(person.getLastName());
		sdnMatch.setMiddleName(person.getMiddleName());
		sdnMatch.setMothersMaidenName(person.getMothersMaidenName());
		if (!ValidationUtil.isNull(person.getDateOfBirth()))
		{
			sdnMatch.setYearOfBirth(PGSiDateUtil.extractYearFromUTCDate(person.getDateOfBirth()));
		}

		sdnMatch.setOtherNamesList(new ArrayList<String>());
		for (PersonName personName : person.getNameList())
		{
			sdnMatch.getOtherNamesList().add(personName.getOtherName());
		}

		for (Contact contact : person.getContactList())
		{
			switch (contact.getContactType())
			{
				case POSTAL_WORK:
					sdnMatch.setCityName(((com.prosperitasglobal.cbof.model.Address)contact).getCityName());
					sdnMatch.setAddressCountryName(((com.prosperitasglobal.cbof.model.Address)contact).getCountry()
							.getDescription());
					break;
				default:
					break;
			}
		}

		sdnMatch.setDocumentIdList(new ArrayList<String>());
		for (Document document : person.getDocumentList())
		{
			sdnMatch.getDocumentIdList().add(document.getValue());
		}

		// If person has SSN, add to document list
		if (!ValidationUtil.isNull(person.getSocialSecurityNumber()))
		{
			sdnMatch.getDocumentIdList().add(person.getSocialSecurityNumber());
		}

		return sdnMatch;
	}

	/**
	 * Creates a {@link SdnMatch} from a {@link SdnCheckerRequest<Member>} for internal usage.
	 *
	 * @param member the member
	 * @return the sdn match
	 */
	private SdnMatch prepareSdnMatch(Member member)
	{
		SdnMatch sdnMatch = preparePersonSdnMatch(member);

		for (CountryUsage countryUsage : member.getCountryUsageList())
		{
			if (CountryUsageEnum.CITIZENSHIP.equals(countryUsage.getUsage())
					|| CountryUsageEnum.BIRTH.equals(countryUsage.getUsage()))
			{
				sdnMatch.setCitizenshipCountryName(countryUsage.getCountry().getDescription());
			}
		}

		return sdnMatch;
	}

	/**
	 * Creates a {@link SdnMatch} from a {@link SdnCheckerRequest<Business>} for internal usage.
	 *
	 * @param business the business
	 * @return the sdn match
	 */
	private SdnMatch prepareSdnMatch(Business business)
	{
		SdnMatch sdnMatch = new SdnMatch();
		sdnMatch.setMatchType(SdnMatchTypeEnum.ENTITY);
		sdnMatch.setParentKey(business.getId());
		sdnMatch.setLastName(business.getName());

		for (Contact contact : business.getContactList())
		{
			switch (contact.getContactType())
			{
				case POSTAL_WORK:
					sdnMatch.setCityName(((com.prosperitasglobal.cbof.model.Address)contact).getCityName());
					sdnMatch.setAddressCountryName(((com.prosperitasglobal.cbof.model.Address)contact).getCountry()
							.getDescription());
					break;
				default:
					break;
			}
		}

		return sdnMatch;
	}

	/**
	 * Checks sdn, given a {@link SdnMatch} object as input
	 *
	 * @param sdnMatch the request
	 * @return the internal results response
	 */
	private synchronized InternalResultsResponse<SdnStatusHistory> checkSdn(SdnMatch sdnMatch)
	{
		int countBefore = 0;

		InternalResultsResponse<SdnStatusHistory> response = new InternalResultsResponse<SdnStatusHistory>();

		// if SDN lists are empty, try to load them
		if (ValidationUtil.isNull(getSdnList()))
		{
			InternalResponse internalResponse = checkForNewSdnFile(new Request());

			if (!Status.NoRowsUpdatedError.equals(internalResponse.getStatus())
					&& !Status.OperationSuccess.equals(internalResponse.getStatus()))
			{
				response.merge(internalResponse);
			}
		}

		if (!response.isInError())
		{
			// Select which list to use based on type passed on request
			switch (sdnMatch.getMatchType())
			{
				case INDIVIDUAL:
					setCurrentSdnEntryList(getIndividualSdnEntryList());
					break;
				case ENTITY:
					setCurrentSdnEntryList(getEntitySdnEntryList());
					break;
				default:
					break;
			}

			// This is the final list
			List<ExtendedSdnEntry> finalResult = new ArrayList<ExtendedSdnEntry>();

			// Validate first against first and last names and AKAs
			List<ExtendedSdnEntry> result = validateAgainstNamesAndIds(sdnMatch);

			if (result.size() > 0)
			{
				// for each record, validate first against City,Country or DOB
				for (ExtendedSdnEntry extendedSdnEntry : result)
				{
					countBefore = finalResult.size();

					// verify if any match was based on ID. If it was, no need to check the rest.
					for (SdnMatchField sdnMatchField : extendedSdnEntry.getSdnMatchRecord().getSdnMatchFieldList())
					{
						if (SdnFieldEnum.ID.equals(sdnMatchField.getSdnField()))
						{
							finalResult.add(extendedSdnEntry);
							break;
						}
					}

					// If no match was based on ID, verify City, Country, DOB
					if (finalResult.size() == countBefore)
					{
						if (cityOrCountryExists(extendedSdnEntry.getSdnEntry())
								|| dobExists(extendedSdnEntry.getSdnEntry()))
						{

							countBefore = extendedSdnEntry.getSdnMatchRecord().getSdnMatchFieldList().size();

							validateAgainstOtherFields(sdnMatch, extendedSdnEntry);

							if (extendedSdnEntry.getSdnMatchRecord().getSdnMatchFieldList().size() > countBefore)
							{
								finalResult.add(extendedSdnEntry);
							}
						}
						else
						{
							// If no City/Country/DOB/Id add to final list based on the match in name
							finalResult.add(extendedSdnEntry);
						}
					}
				}
			}

			// Transform the List into a SdnStatusHistory object
			SdnStatusHistory sdnStatusHistory = transformEntryToObject(finalResult);
			response.addResult(sdnStatusHistory);
		}

		saveSdnFieldsToDatabase(response, sdnMatch);

		return response;
	}

	/**
	 * Validate against City, Country, Year of Birth and Ids.
	 *
	 * @param sdnMatch the request
	 * @param extendedSdnEntry the extended sdn entry
	 */
	private void validateAgainstOtherFields(SdnMatch sdnMatch, ExtendedSdnEntry extendedSdnEntry)
	{
		if (cityOrCountryExists(extendedSdnEntry.getSdnEntry()))
		{

			// City
			if (!ValidationUtil.isNullOrEmpty(sdnMatch.getCityName()))
			{
				validateCity(extendedSdnEntry, sdnMatch.getCityName());
			}

			// Address Country
			if (!ValidationUtil.isNullOrEmpty(sdnMatch.getAddressCountryName()))
			{
				validateAddressCountry(extendedSdnEntry, sdnMatch.getAddressCountryName());
			}

			// Citizenship Country
			if (!ValidationUtil.isNullOrEmpty(sdnMatch.getCitizenshipCountryName()))
			{
				validateCitizenshipCountry(extendedSdnEntry, sdnMatch.getCitizenshipCountryName());
			}
		}

		// Year of Birth
		if (dobExists(extendedSdnEntry.getSdnEntry()))
		{
			if (!ValidationUtil.isNullOrEmpty(sdnMatch.getYearOfBirth()))
			{
				validateDob(extendedSdnEntry, sdnMatch.getYearOfBirth());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC#fetchCurrentSdnStatusHistory(com.prosperitasglobal.sendsolv
	 * .sdn.model.request.SdnCheckerRequest)
	 */
	@Override
	public InternalResultsResponse<SdnStatusHistory> fetchCurrentSdnStatusHistory(SdnStatusHistoryRequest request)
	{
		// Implemented in SdnCheckerBACPersistanceImpl
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC#fetchFullSdnStatusHistory(com.prosperitasglobal.sendsolv
	 * .sdn.model.request.SdnCheckerRequest)
	 */
	@Override
	public InternalResultsResponse<SdnStatusHistory> fetchFullSdnStatusHistory(SdnStatusHistoryRequest request)
	{
		// Implemented in SdnCheckerBACPersistanceImpl
		return null;
	}

	@Override
	public InternalResponse updateSdnStatusHistory(SdnStatusHistoryRequest request)
	{
		// Implemented in SdnCheckerBACPersistanceImpl
		return null;
	}

	@Override
	public InternalResultsResponse<SdnHistory> fetchSdnStatusHistoryByRequest(
			SdnStatusHistoryInquiryRequest request)
	{
		// Implemented in SdnCheckerBACPersistanceImpl
		return null;
	}

	/**
	 * Save sdn fields to database.
	 *
	 * @param response the response
	 * @param request the request
	 */
	protected void saveSdnFieldsToDatabase(
			InternalResultsResponse<SdnStatusHistory> response, SdnMatch request)
	{
		// Implemented in SdnCheckerBACPersistanceImpl
	}

	/**
	 * Determine if files are the same based on publish date, record count and size.
	 *
	 * @param publishDate the publish date
	 * @param recordCount the record count
	 * @param newSize the new size
	 * @param originalFile the original file
	 * @return true, if files are considered the same
	 */
	private boolean filesAreTheSame(String publishDate, Integer recordCount, long newSize, File originalFile)
	{
		// Compare publishDate, recordCount and file size
		if (originalFile.exists())
		{
			if (publishDate.equalsIgnoreCase(getPublishDate()) && (recordCount.compareTo(getRecordCount()) == 0))
			{
				if (originalFile.length() == newSize)
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * This will produce a list of {@link ExtendedSdnEntry} that have matches based on
	 * 1 -any combination of names, both on the request and on the multiple {@link SdnEntry}. That includes {@link Aka}
	 * AND/OR
	 * 2 -any ID number
	 *
	 * @param sdnMatch the request
	 * @return the match list
	 */
	private List<ExtendedSdnEntry> validateAgainstNamesAndIds(SdnMatch sdnMatch)
	{

		List<ExtendedSdnEntry> filteredResult = new ArrayList<ExtendedSdnEntry>();

		// Generate unique Name list containing combination of first/middle/last names passed in request
		Set<Name> requestNameList = generateNameListFromRequest(sdnMatch);

		// Cycle through all SDN entries in the current list
		for (SdnEntry sdnEntry : getCurrentSdnEntryList())
		{
			// Generate unique Name list containing combination of first/last names in current SdnEntry
			Set<Name> sdnNameList = generateNameListFromEntry(sdnEntry);

			if (!ValidationUtil.isNull(requestNameList) && (requestNameList.size() > 0))
			{
				// Verify names
				ExtendedSdnEntry extendedSdnEntry = matchAgainstNames(sdnEntry, requestNameList, sdnNameList);

				// Verify IDs
				extendedSdnEntry = matchAgainstIds(sdnEntry, extendedSdnEntry, sdnMatch);

				// If any result exists, add to the filteredResult list
				if (!ValidationUtil.isNull(extendedSdnEntry))
				{
					if (!ValidationUtil.isNullOrEmpty(extendedSdnEntry.getSdnMatchRecord().getSdnMatchFieldList()))
					{
						extendedSdnEntry.getSdnMatchRecord().setXmlRecord(
								convertSdnEntryToXml(extendedSdnEntry.getSdnEntry()));
						filteredResult.add(extendedSdnEntry);
					}
				}
			}
		}

		return filteredResult;
	}

	private ExtendedSdnEntry matchAgainstIds(SdnEntry sdnEntry, ExtendedSdnEntry extendedSdnEntry, SdnMatch sdnMatch)
	{
		// Ids
		if (idExists(sdnEntry))
		{
			// If yes validate
			if (!ValidationUtil.isNullOrEmpty(sdnMatch.getDocumentIdList()))
			{
				for (String docId : sdnMatch.getDocumentIdList())
				{
					// If match add to final list
					extendedSdnEntry = validateId(sdnEntry, extendedSdnEntry, docId);
				}
			}
		}

		return extendedSdnEntry;
	}

	private ExtendedSdnEntry matchAgainstNames(SdnEntry sdnEntry, Set<Name> requestNameList, Set<Name> sdnNameList)
	{
		SdnMatchField matchField = null;
		ExtendedSdnEntry extendedSdnEntry = null;

		// Cycle through all names generated from request
		for (Name requestName : requestNameList)
		{
			if (ValidationUtil.isNull(requestName.getFirstName())
					&& ValidationUtil.isNull(requestName.getLastName()))
			{
				LOG.debug("oops, both names null");
				continue;
			}
			else
			{
				// Cycle through all names generated from SdnEntry
				for (Name sdnName : sdnNameList)
				{
					// Do matches based on presence of first/last name
					if (bothNamesPresent(requestName, sdnName))
					{
						matchField =
								isMatchBothNames(new Name(sdnName.getFirstName().toUpperCase(), sdnName
										.getLastName().toUpperCase()),
										new Name(requestName.getFirstName().toUpperCase(), requestName
												.getLastName().toUpperCase()),
												SdnFieldEnum.FIRST_LAST_NAME);
					}
					else
					{
						// Only first names
						if (!ValidationUtil.isNull(requestName.getFirstName())
								&& !ValidationUtil.isNull(sdnName.getFirstName()))
						{

							matchField =
									isMatch(sdnName.getFirstName().toUpperCase(), requestName.getFirstName()
											.toUpperCase(), SdnFieldEnum.FIRST_NAME);
						}
						else
						{
							// Only last names
							if (!ValidationUtil.isNull(requestName.getLastName())
									&& !ValidationUtil.isNull(sdnName.getLastName()))
							{
								matchField =
										isMatch(sdnName.getLastName().toUpperCase(), requestName.getLastName()
												.toUpperCase(), SdnFieldEnum.LAST_NAME);
							}
						}
					}

					// If any match was found, add the match to the extendedSdnEntry.
					// Initialize extendedSdnEntry if still null.
					if (matchField != null)
					{
						if (ValidationUtil.isNull(extendedSdnEntry))
						{
							extendedSdnEntry = new ExtendedSdnEntry(sdnEntry);

							SdnMatchRecord sdnMatchRecord = new SdnMatchRecord();
							sdnMatchRecord.setSdnMatchFieldList(new ArrayList<SdnMatchField>());
							extendedSdnEntry.setSdnMatchRecord(sdnMatchRecord);
						}

						matchField.setSdnUid(sdnEntry.getUid());
						extendedSdnEntry.getSdnMatchRecord().getSdnMatchFieldList().add(matchField);
					}
				}
			}
		}

		return extendedSdnEntry;
	}

	/**
	 * Creates two lists of {@link SdnEntry}, one for Individuals and another for Entities.
	 * Depending on the type of match requested, one type of list will be used.
	 *
	 * @return the internal response
	 */
	public void loadSdnLists()
	{
		// If list already exists, clear it. Otherwise, create it.
		if (ValidationUtil.isNullOrEmpty(getIndividualSdnEntryList()))
		{
			setIndividualSdnEntryList(new ArrayList<SdnEntry>());
		}
		else
		{
			getIndividualSdnEntryList().clear();
		}

		// If list already exists, clear it. Otherwise, create it.
		if (ValidationUtil.isNullOrEmpty(getEntitySdnEntryList()))
		{
			setEntitySdnEntryList(new ArrayList<SdnEntry>());
		}
		else
		{
			getEntitySdnEntryList().clear();
		}

		// Populate two lists, one with Entities and other with Individuals
		for (SdnEntry sdnEntry : getSdnList().getSdnEntry())
		{
			if ("Individual".equals(sdnEntry.getSdnType()))
			{
				getIndividualSdnEntryList().add(sdnEntry);
			}
			if ("Entity".equals(sdnEntry.getSdnType()))
			{
				getEntitySdnEntryList().add(sdnEntry);
			}
		}
	}

	/**
	 * Generates a list of names with all possible combinations of first/middle/last names, passed as parameters via the
	 * {@link SdnMatchRequest}.
	 *
	 * @param sdnMatch the request
	 * @return the list<Name>
	 */
	public Set<Name> generateNameListFromRequest(SdnMatch sdnMatch)
	{
		// This is a list of String containing all possible names that need to be combined
		HashSet<String> names = new HashSet<String>();

		// Add first name if exists
		if (!ValidationUtil.isNullOrEmpty(sdnMatch.getFirstName()))
		{
			breakNamesBasedOnRegex(sdnMatch.getFirstName(), names, SPACES);
		}

		// Add last name if exists
		if (!ValidationUtil.isNullOrEmpty(sdnMatch.getLastName()))
		{
			breakNamesBasedOnRegex(sdnMatch.getLastName(), names, SPACES);
		}

		// Add mother's maiden name if exists
		if (!ValidationUtil.isNullOrEmpty(sdnMatch.getMothersMaidenName()))
		{
			breakNamesBasedOnRegex(sdnMatch.getMothersMaidenName(), names, SPACES);
		}

		// Break middle names based on spaces and add
		if (!ValidationUtil.isNullOrEmpty(sdnMatch.getMiddleName()))
		{
			breakNamesBasedOnRegex(sdnMatch.getMiddleName(), names, SPACES);
		}

		// Add any other names if they exist
		if (!ValidationUtil.isNullOrEmpty(sdnMatch.getOtherNamesList()))
		{
			for (String otherName : sdnMatch.getOtherNamesList())
			{
				names.add(otherName);
			}
		}

		// Now that I have all names, combine them
		Set<Name> nameList = combineNames(names);

		return nameList;
	}

	/**
	 * Given an {@link SdnEntry} object, it generates a list of {@Name} that combines all possible first/last
	 * fields, including Aka.
	 *
	 * @param sdnEntry the sdn entry
	 * @return the sets the
	 */
	public Set<Name> generateNameListFromEntry(SdnEntry sdnEntry)
	{
		// This is a list of String containing all possible names that need to be combined
		HashSet<String> names = new HashSet<String>();

		// Add first name if exists
		if (!ValidationUtil.isNullOrEmpty(sdnEntry.getFirstName()))
		{
			breakNamesBasedOnRegex(sdnEntry.getFirstName(), names, SPACES);
		}

		// Add last name if exists
		if (!ValidationUtil.isNullOrEmpty(sdnEntry.getLastName()))
		{
			breakNamesBasedOnRegex(sdnEntry.getLastName(), names, SPACES);
		}

		if (!ValidationUtil.isNull(sdnEntry.getAkaList())
				&& !ValidationUtil.isNullOrEmpty(sdnEntry.getAkaList().getAka()))
		{
			for (Aka akaName : sdnEntry.getAkaList().getAka())
			{
				if (!ValidationUtil.isNullOrEmpty(akaName.getFirstName()))
				{
					breakNamesBasedOnRegex(akaName.getFirstName(), names, SPACES);
				}

				// Add last name if exists
				if (!ValidationUtil.isNullOrEmpty(akaName.getLastName()))
				{
					breakNamesBasedOnRegex(akaName.getLastName(), names, SPACES);
				}
			}
		}

		// Now that I have all names, combine them
		Set<Name> nameList = combineNames(names);

		return nameList;
	}

	private void breakNamesBasedOnRegex(String compoundName, HashSet<String> names, String regEx)
	{
		String[] middleNames = compoundName.split(regEx);

		for (String name : middleNames)
		{
			names.add(name);
		}
	}

	/**
	 *
	 * Generates a list of names with all possible combinations of names (String) in the list passed in.
	 * This implements the formula P(n,r) = n! / (n-r)!, where n is the number of choices
	 * and n the number of combinations.
	 * In this case we are using r = 2 because we want combinations of first and second names
	 *
	 * @param nameList the name list
	 * @return the set< name>
	 */
	public Set<Name> combineNames(HashSet<String> nameList)
	{
		Set<Name> resultNameList = new HashSet<Name>();

		if (!ValidationUtil.isNull(nameList) && (nameList.size() > 0))
		{
			HashSet<String> otherNameList = (HashSet<String>)nameList.clone();

			for (Iterator<String> it = nameList.iterator(); it.hasNext();)
			{
				String name1 = it.next();

				if (nameList.size() == 1)
				{
					// If only one name is present, use it as last name. This is important because the Entity type
					// (business) in the SDN list uses last name only.
					resultNameList.add(new Name(null, name1));
				}
				else
				{
					for (Iterator<String> it2 = otherNameList.iterator(); it2.hasNext();)
					{
						String name2 = it2.next();

						if (!name1.equalsIgnoreCase(name2))
						{
							resultNameList.add(new Name(name1, name2));
						}
					}
				}
			}
		}

		return resultNameList;
	}

	/**
	 * Verify if both names are present.
	 *
	 * @param name1 the name
	 * @param name2 the name2
	 * @return true, if successful
	 */
	private boolean bothNamesPresent(Name name1, Name name2)
	{
		if (ValidationUtil.isNull(name1.getFirstName()) || ValidationUtil.isNull(name1.getLastName()))
		{
			return false;
		}

		if (ValidationUtil.isNull(name2.getFirstName()) || ValidationUtil.isNull(name2.getLastName()))
		{
			return false;
		}

		return true;
	}

	/**
	 * Converts {@link SdnEntry} to XML.
	 *
	 * @param sdnEntry the {@link SdnEntry}
	 * @return the XML representation as a String
	 */
	private String convertSdnEntryToXml(SdnEntry sdnEntry)
	{
		String result = new String();

		StringWriter sw = new StringWriter();

		try
		{
			getMarshaller().setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			JAXBElement<SdnEntry> je =
					new JAXBElement<SdnEntry>(new QName("http://www.pgsi.com/sdn", "SdnEntry"), SdnEntry.class,
							null, sdnEntry);

			getMarshaller().marshal(je, sw);

			result = sw.toString();
		}
		catch (PropertyException e)
		{
			result = e.toString();
			e.printStackTrace();
		}
		catch (JAXBException e)
		{
			result = e.toString();
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Given two strings, check if they match according to algorithm. If yes, returns an instance of
	 * {@link SdnMatchField}
	 *
	 * @param fromSdn the from sdn
	 * @param fromSystem the name2
	 * @param field the field
	 * @return the sdn match field
	 */
	public SdnMatchField isMatch(String fromSdn, String fromSystem, SdnFieldEnum field)
	{
		double proximity = JaroWinkler.JARO_WINKLER_DISTANCE.proximity(fromSdn.toUpperCase(), fromSystem.toUpperCase());

		if (proximity > getSimilarityThreshold())
		{
			return new SdnMatchField(field, proximity, fromSdn, fromSystem);
		}

		return null;
	}

	/**
	 * Given two pairs of Names, verify if they match. It is a match ONLY IF all three checks are greater than
	 * threshold:
	 * 1- First name
	 * 2- Last name
	 * 3- Both names combined (first + last)
	 * If a match is found, the routine generates a {@link SdnMatchField} as a result
	 *
	 * @param fromSdn the from sdn
	 * @param fromSystem the name2
	 * @param field the field
	 * @return the sdn match field
	 */
	public SdnMatchField isMatchBothNames(Name nameSdn, Name namePgsi, SdnFieldEnum field)
	{
		double proximity =
				JaroWinkler.JARO_WINKLER_DISTANCE.proximity(nameSdn.getFirstName().toUpperCase(), namePgsi
						.getFirstName().toUpperCase());

		if (proximity > getSimilarityThreshold())
		{
			proximity =
					JaroWinkler.JARO_WINKLER_DISTANCE.proximity(nameSdn.getLastName().toUpperCase(), namePgsi
							.getLastName().toUpperCase());

			if (proximity > getSimilarityThreshold())
			{
				String fullNamePGSi = nameSdn.getFirstName().toUpperCase() + " "
						+ nameSdn.getLastName().toUpperCase();

				String fullNameSdn = namePgsi.getFirstName().toUpperCase() + " "
						+ namePgsi.getLastName().toUpperCase();

				proximity = JaroWinkler.JARO_WINKLER_DISTANCE.proximity(fullNamePGSi, fullNameSdn);

				if (proximity > getSimilarityThreshold())
				{
					return new SdnMatchField(field, proximity, fullNamePGSi, fullNameSdn);
				}
			}

		}

		return null;
	}

	/**
	 * Verify if an {@link SdnEntry} has city or country.
	 *
	 * @param sdnEntry the sdn entry
	 * @return the boolean
	 */
	private Boolean cityOrCountryExists(SdnEntry sdnEntry)
	{
		Boolean cityExists = false;
		Boolean countryExists = false;

		// Address
		if (!ValidationUtil.isNull(sdnEntry.getAddressList())
				&& !ValidationUtil.isNullOrEmpty(sdnEntry.getAddressList().getAddress()))
		{
			for (Address address : sdnEntry.getAddressList().getAddress())
			{
				if (!ValidationUtil.isNullOrEmpty(address.getCity()))
				{
					cityExists = true;
				}

				if (!ValidationUtil.isNullOrEmpty(address.getCountry()))
				{
					countryExists = true;
				}
			}
		}

		// Nationality
		if (!ValidationUtil.isNull(sdnEntry.getNationalityList())
				&& !ValidationUtil.isNullOrEmpty(sdnEntry.getNationalityList().getNationality()))
		{
			for (Nationality nationality : sdnEntry.getNationalityList().getNationality())
			{
				if (!ValidationUtil.isNullOrEmpty(nationality.getCountry()))
				{
					countryExists = true;
				}
			}
		}

		// Citizenship
		if (!ValidationUtil.isNull(sdnEntry.getCitizenshipList())
				&& !ValidationUtil.isNullOrEmpty(sdnEntry.getCitizenshipList().getCitizenship()))
		{
			for (Citizenship citizenship : sdnEntry.getCitizenshipList().getCitizenship())
			{
				if (!ValidationUtil.isNullOrEmpty(citizenship.getCountry()))
				{
					countryExists = true;
				}
			}
		}

		if (!ValidationUtil.isNull(sdnEntry.getPlaceOfBirthList())
				&& !ValidationUtil.isNullOrEmpty(sdnEntry.getPlaceOfBirthList()
						.getPlaceOfBirthItem()))
		{
			countryExists = true;
		}

		return cityExists || countryExists;
	}

	/**
	 * Verify if an {@link SdnEntry} has DOB.
	 *
	 * @param sdnEntry the line
	 * @return the boolean
	 */
	private Boolean dobExists(SdnEntry sdnEntry)
	{
		if (ValidationUtil.isNull(sdnEntry.getDateOfBirthList())
				|| ValidationUtil.isNullOrEmpty(sdnEntry.getDateOfBirthList().getDateOfBirthItem()))
		{
			return false;
		}

		return true;
	}

	/**
	 * Verify if an {@link SdnEntry} has Ids associated.
	 *
	 * @param sdnEntry the sdn entry
	 * @return true, if id exists
	 */
	private Boolean idExists(SdnEntry sdnEntry)
	{
		if (ValidationUtil.isNull(sdnEntry.getIdList())
				|| ValidationUtil.isNullOrEmpty(sdnEntry.getIdList().getId()))
		{
			return false;
		}

		return true;
	}

	/**
	 * Validates a city passed in against an {@link SdnEntry} that contains city.
	 *
	 * @param extendedSdnEntry the extended sdn entry
	 * @param cityName the city
	 */
	private void validateCity(ExtendedSdnEntry extendedSdnEntry, String cityName)
	{
		if (!ValidationUtil.isNull(extendedSdnEntry.getSdnEntry().getAddressList())
				&& !ValidationUtil.isNullOrEmpty(extendedSdnEntry.getSdnEntry().getAddressList().getAddress()))
		{
			for (Address address : extendedSdnEntry.getSdnEntry().getAddressList().getAddress())
			{
				if (!ValidationUtil.isNullOrEmpty(address.getCity()))
				{
					SdnMatchField matchField = isMatch(address.getCity().toUpperCase(), cityName, SdnFieldEnum.CITY);

					if (matchField != null)
					{
						matchField.setSdnUid(address.getUid());
						extendedSdnEntry.getSdnMatchRecord().getSdnMatchFieldList().add(matchField);
					}
				}
			}
		}
	}

	/**
	 * Validates a country name passed in against an {@link SdnEntry} that contains country.
	 *
	 * @param extendedSdnEntry the extended sdn entry
	 * @param countryName the country
	 */
	private void validateAddressCountry(ExtendedSdnEntry extendedSdnEntry, String countryName)
	{
		// Address
		if (!ValidationUtil.isNull(extendedSdnEntry.getSdnEntry().getAddressList())
				&& !ValidationUtil.isNullOrEmpty(extendedSdnEntry.getSdnEntry().getAddressList().getAddress()))
		{
			for (Address address : extendedSdnEntry.getSdnEntry().getAddressList().getAddress())
			{
				if (!ValidationUtil.isNullOrEmpty(address.getCountry()))
				{
					verifyCountryMatch(extendedSdnEntry, address.getCountry(), countryName,
							SdnFieldEnum.ADDRESS_COUNTRY,
							address.getUid());
				}
			}
		}

		// Nationality
		if (!ValidationUtil.isNull(extendedSdnEntry.getSdnEntry().getNationalityList())
				&& !ValidationUtil.isNullOrEmpty(extendedSdnEntry.getSdnEntry().getNationalityList().getNationality()))
		{
			for (Nationality nationality : extendedSdnEntry.getSdnEntry().getNationalityList().getNationality())
			{
				if (!ValidationUtil.isNullOrEmpty(nationality.getCountry()))
				{
					verifyCountryMatch(extendedSdnEntry, nationality.getCountry(), countryName,
							SdnFieldEnum.NATIONALITY_COUNTRY, nationality.getUid());
				}
			}
		}

		// Citizenship
		if (!ValidationUtil.isNull(extendedSdnEntry.getSdnEntry().getCitizenshipList())
				&& !ValidationUtil.isNullOrEmpty(extendedSdnEntry.getSdnEntry().getCitizenshipList().getCitizenship()))
		{
			for (Citizenship citizenship : extendedSdnEntry.getSdnEntry().getCitizenshipList().getCitizenship())
			{
				if (!ValidationUtil.isNullOrEmpty(citizenship.getCountry()))
				{
					verifyCountryMatch(extendedSdnEntry, citizenship.getCountry(), countryName,
							SdnFieldEnum.CITIZENSHIP_COUNTRY, citizenship.getUid());
				}
			}
		}

	}

	/**
	 * Validate citizenship country.
	 *
	 * @param extendedSdnEntry the extended sdn entry
	 * @param countryName the country
	 */
	private void validateCitizenshipCountry(ExtendedSdnEntry extendedSdnEntry, String countryName)
	{
		// Nationality
		if (!ValidationUtil.isNull(extendedSdnEntry.getSdnEntry().getNationalityList())
				&& !ValidationUtil.isNullOrEmpty(extendedSdnEntry.getSdnEntry().getNationalityList().getNationality()))
		{
			for (Nationality nationality : extendedSdnEntry.getSdnEntry().getNationalityList().getNationality())
			{
				if (!ValidationUtil.isNullOrEmpty(nationality.getCountry()))
				{
					verifyCountryMatch(extendedSdnEntry, nationality.getCountry(), countryName,
							SdnFieldEnum.NATIONALITY_COUNTRY, nationality.getUid());
				}
			}
		}

		// Citizenship
		if (!ValidationUtil.isNull(extendedSdnEntry.getSdnEntry().getCitizenshipList())
				&& !ValidationUtil.isNullOrEmpty(extendedSdnEntry.getSdnEntry().getCitizenshipList().getCitizenship()))
		{
			for (Citizenship citizenship : extendedSdnEntry.getSdnEntry().getCitizenshipList().getCitizenship())
			{
				if (!ValidationUtil.isNullOrEmpty(citizenship.getCountry()))
				{
					verifyCountryMatch(extendedSdnEntry, citizenship.getCountry(), countryName,
							SdnFieldEnum.CITIZENSHIP_COUNTRY, citizenship.getUid());
				}
			}
		}

		// Place of Birth
		if (!ValidationUtil.isNull(extendedSdnEntry.getSdnEntry().getPlaceOfBirthList())
				&& !ValidationUtil.isNullOrEmpty(extendedSdnEntry.getSdnEntry().getPlaceOfBirthList()
						.getPlaceOfBirthItem()))
		{
			for (PlaceOfBirthItem placeOfBirthItem : extendedSdnEntry.getSdnEntry().getPlaceOfBirthList()
					.getPlaceOfBirthItem())
			{
				// This is a list of String containing all possible names that need to be combined
				HashSet<String> places = new HashSet<String>();

				breakNamesBasedOnRegex(placeOfBirthItem.getPlaceOfBirth(), places, SPACES);

				for (String place : places)
				{
					verifyCountryMatch(extendedSdnEntry, place, countryName,
							SdnFieldEnum.NATIONALITY_COUNTRY, placeOfBirthItem.getUid());
				}
			}
		}
	}

	/**
	 * Verify match between two countries passed as parameter.
	 *
	 * @param extendedSdnEntry the extended sdn entry
	 * @param sdnCountryName the sdn country
	 * @param countryName the country
	 * @param sdnField the sdn field
	 * @param uid the uid
	 */
	private void verifyCountryMatch(ExtendedSdnEntry extendedSdnEntry, String sdnCountryName, String countryName,
			SdnFieldEnum sdnField, Integer uid)
	{
		SdnMatchField matchField =
				isMatch(sdnCountryName.toUpperCase(), countryName.toUpperCase(), sdnField);

		if (matchField != null)
		{
			matchField.setSdnUid(uid);
			extendedSdnEntry.getSdnMatchRecord().getSdnMatchFieldList().add(matchField);
		}
	}

	/**
	 * Verifies match between a year passed in and an {@link SdnEntry} that contains DOB.
	 *
	 * @param extendedSdnEntry the extended sdn entry
	 * @param year the year
	 * @return the boolean
	 */
	private void validateDob(ExtendedSdnEntry extendedSdnEntry, String year)
	{
		if (!ValidationUtil.isNull(extendedSdnEntry.getSdnEntry().getDateOfBirthList())
				&& !ValidationUtil.isNullOrEmpty(extendedSdnEntry.getSdnEntry().getDateOfBirthList()
						.getDateOfBirthItem()))
		{
			for (DateOfBirthItem dateOfBirthItem : extendedSdnEntry.getSdnEntry().getDateOfBirthList()
					.getDateOfBirthItem())
			{
				if (!ValidationUtil.isNullOrEmpty(dateOfBirthItem.getDateOfBirth()))
				{
					if (dateOfBirthItem.getDateOfBirth().contains(year))
					{
						SdnMatchField matchField =
								new SdnMatchField(SdnFieldEnum.YEAR_OF_BIRTH, 1.0, dateOfBirthItem.getDateOfBirth(),
										year);
						matchField.setSdnUid(dateOfBirthItem.getUid());
						extendedSdnEntry.getSdnMatchRecord().getSdnMatchFieldList().add(matchField);
					}
				}
			}
		}
	}

	/**
	 * Verifies match between an id number passed in and an {@link extendedSdnEntry} that contains IDs.
	 *
	 * @param sdnEntry the extended sdn entry
	 * @param idNumber the id number
	 */
	private ExtendedSdnEntry validateId(SdnEntry sdnEntry, ExtendedSdnEntry extendedSdnEntry, String idNumber)
	{
		if (!ValidationUtil.isNull(sdnEntry.getIdList())
				&& !ValidationUtil.isNullOrEmpty(sdnEntry.getIdList().getId()))
		{
			for (Id id : sdnEntry.getIdList().getId())
			{
				if (!ValidationUtil.isNullOrEmpty(id.getIdNumber()))
				{
					SdnMatchField matchField =
							isMatch(id.getIdNumber(), idNumber, SdnFieldEnum.ID);

					if (matchField != null)
					{
						// record the sdn uid
						matchField.setSdnUid(id.getUid());

						if (ValidationUtil.isNull(extendedSdnEntry))
						{
							extendedSdnEntry = new ExtendedSdnEntry(sdnEntry);

							SdnMatchRecord sdnMatchRecord = new SdnMatchRecord();
							sdnMatchRecord.setSdnMatchFieldList(new ArrayList<SdnMatchField>());
							extendedSdnEntry.setSdnMatchRecord(sdnMatchRecord);
						}

						extendedSdnEntry.getSdnMatchRecord().getSdnMatchFieldList().add(matchField);

					}
				}
			}
		}

		return extendedSdnEntry;
	}

	/**
	 * Creates a {@link SdnStatusHistory} Object from a List of {@link ExtendedSdnEntry} .
	 *
	 * @param theList List of {@link ExtendedSdnEntry}
	 * @return {@link SdnStatusHistory}
	 */
	private SdnStatusHistory transformEntryToObject(List<ExtendedSdnEntry> theList)
	{
		SdnStatusHistory sdnStatusHistory = new SdnStatusHistory();
		sdnStatusHistory.setSdnMatchRecordList(new ArrayList<SdnMatchRecord>());

		for (ExtendedSdnEntry extendedSdnEntry : theList)
		{
			sdnStatusHistory.getSdnMatchRecordList().add(extendedSdnEntry.getSdnMatchRecord());
		}

		return sdnStatusHistory;
	}

	/**
	 * ****************************
	 * gets and setters live here *
	 * ****************************.
	 *
	 * @return the current sdn entry list
	 */
	public List<SdnEntry> getCurrentSdnEntryList()
	{
		return currentSdnEntryList;
	}

	/**
	 * Sets the current sdn entry list.
	 *
	 * @param currentSdnEntryList the new current sdn entry list
	 */
	public void setCurrentSdnEntryList(List<SdnEntry> currentSdnEntryList)
	{
		this.currentSdnEntryList = currentSdnEntryList;
	}

	/**
	 * Gets the similarity threshold.
	 *
	 * @return the similarity threshold
	 */
	public double getSimilarityThreshold()
	{
		return similarityThreshold;
	}

	/**
	 * Sets the similarity threshold.
	 *
	 * @param similarityThreshold the new similarity threshold
	 */
	public void setSimilarityThreshold(double similarityThreshold)
	{
		this.similarityThreshold = similarityThreshold;
	}

	/**
	 * Gets the unmarshaller.
	 *
	 * @return the unmarshaller
	 */
	public Unmarshaller getUnmarshaller()
	{
		return unmarshaller;
	}

	/**
	 * Sets the unmarshaller.
	 *
	 * @param unmarshaller the new unmarshaller
	 */
	public void setUnmarshaller(Unmarshaller unmarshaller)
	{
		this.unmarshaller = unmarshaller;
	}

	/**
	 * Gets the marshaller.
	 *
	 * @return the marshaller
	 */
	public Marshaller getMarshaller()
	{
		return marshaller;
	}

	/**
	 * Sets the marshaller.
	 *
	 * @param marshaller the new marshaller
	 */
	public void setMarshaller(Marshaller marshaller)
	{
		this.marshaller = marshaller;
	}

	/**
	 * Gets the publish date.
	 *
	 * @return the publish date
	 */
	public String getPublishDate()
	{
		return publishDate;
	}

	/**
	 * Sets the publish date.
	 *
	 * @param publishDate the new publish date
	 */
	private void setPublishDate(String publishDate)
	{
		this.publishDate = publishDate;
	}

	/**
	 * Gets the record count.
	 *
	 * @return the record count
	 */
	public Integer getRecordCount()
	{
		return recordCount;
	}

	/**
	 * Sets the record count.
	 *
	 * @param recordCount the new record count
	 */
	private void setRecordCount(Integer recordCount)
	{
		this.recordCount = recordCount;
	}

	/**
	 * Gets the sdn list.
	 *
	 * @return the sdn list
	 */
	public SdnList getSdnList()
	{
		return sdnList;
	}

	/**
	 * Sets the sdn list.
	 *
	 * @param sdnList the new sdn list
	 */
	private void setSdnList(SdnList sdnList)
	{
		this.sdnList = sdnList;
	}

	/**
	 * Gets the file path.
	 *
	 * @return the file path
	 */
	public String getFilePath()
	{
		return filePath;
	}

	/**
	 * Sets the file path.
	 *
	 * @param filePath the new file path
	 */
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	/**
	 * Gets the sdn file url.
	 *
	 * @return the sdn file url
	 */
	public String getSdnFileUrl()
	{
		return sdnFileUrl;
	}

	/**
	 * Sets the sdn file url.
	 *
	 * @param sdnFileUrl the new sdn file url
	 */
	public void setSdnFileUrl(String sdnFileUrl)
	{
		this.sdnFileUrl = sdnFileUrl;
	}

	/**
	 * Gets the entity sdn entry list.
	 *
	 * @return the entity sdn entry list
	 */
	public List<SdnEntry> getEntitySdnEntryList()
	{
		return entitySdnEntryList;
	}

	/**
	 * Sets the entity sdn entry list.
	 *
	 * @param entitySdnEntryList the new entity sdn entry list
	 */
	public void setEntitySdnEntryList(List<SdnEntry> entitySdnEntryList)
	{
		this.entitySdnEntryList = entitySdnEntryList;
	}

	/**
	 * Gets the individual sdn entry list.
	 *
	 * @return the individual sdn entry list
	 */
	public List<SdnEntry> getIndividualSdnEntryList()
	{
		return individualSdnEntryList;
	}

	/**
	 * Sets the individual sdn entry list.
	 *
	 * @param individualSdnEntryList the new individual sdn entry list
	 */
	public void setIndividualSdnEntryList(List<SdnEntry> individualSdnEntryList)
	{
		this.individualSdnEntryList = individualSdnEntryList;
	}
}
