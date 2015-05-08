package com.prosperitasglobal.sendsolv.sdn.controller;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.ILiaisonBAI;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI;
import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.bai.IRecipientBAI;
import com.prosperitasglobal.sendsolv.common.controller.BaseController;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;
import com.prosperitasglobal.sendsolv.model.criteria.MoneyTransferBatchCriteria;
import com.prosperitasglobal.sendsolv.model.criteria.MoneyTransferCriteria;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LiaisonResponse;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferBatchResponse;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferResponse;
import com.prosperitasglobal.sendsolv.model.response.OrganizationResponse;
import com.prosperitasglobal.sendsolv.model.response.RecipientResponse;
import com.prosperitasglobal.sendsolv.sdn.bai.ISdnCheckerBAI;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchRecord;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.generated.SdnEntry;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnHistoryResponse;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnStatusHistoryResponse;
import com.qat.framework.exception.QATRuntimeException;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class SdnBaseController.
 */
public class SdnBaseController extends BaseController
{

	private static final String MONEY_TRANSFER = "moneyTransfer";

	/** The Constant SDN_MATCHES. */
	private static final String SDN_MATCHES = "matches";

	/** The Constant TYPE. */
	private static final String TYPE = "type";

	/** The Constant RECIPIENT. */
	private static final String RECIPIENT = "recipient";

	/** The Constant MEMBER. */
	private static final String MEMBER = "member";

	/** The Constant LIAISON. */
	private static final Object LIAISON = "liason";

	/** The Constant LOCATION. */
	private static final Object LOCATION = "location";

	/** The Constant ORGANIZATION. */
	private static final Object ORGANIZATION = "organization";

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SdnBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "SdnBaseController";

	/** The Constant SDN_HISTORY. */
	private static final String SDN_HISTORY = "sdn_history";

	/** The Constant SDN_ENTRY. */
	private static final String SDN_ENTRY = "sdn_entry";

	/** The money transfer bai. */
	private IMoneyTransferBAI moneyTransferBAI;

	/** The Organization BAI. */
	private ISdnCheckerBAI sdnBAI;

	/** The member bai. */
	private IMemberBAI memberBAI;

	/** The recipient bai. */
	private IRecipientBAI recipientBAI;

	/** The organization bai. */
	private IOrganizationBAI organizationBAI;

	/** The location bai. */
	private ILocationBAI locationBAI;

	/** The contact bai. */
	private ILiaisonBAI contactBAI;

	private ISdnCheckerBAI sdnCheckerBAI;

	/**
	 * Sdn mav.
	 *
	 * @param id the entity id (person or business id)
	 * @param returnViewName the return view name
	 * @param type the entity type (member, recipient, liaison, organization or location)
	 * @param isSelect the is select
	 * @param request the request
	 * @return the MAV
	 */
	protected ModelAndView sdnMAV(Integer id, String returnViewName, String type, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);
		try
		{

			if (!ValidationUtil.isNullOrZero(id))
			{
				Object entity = null;
				Object batchOrTransaction = null;
				SdnStatusHistoryResponse sdnHist = null;
				SdnStatusHistoryRequest sdnHistoryRequest = new SdnStatusHistoryRequest();
				sdnHistoryRequest.setSdnStatusHistory(new SdnStatusHistory());
				sdnHistoryRequest.getSdnStatusHistory().setParentKey(id);

				if ((LOCATION.equals(type)) || (ORGANIZATION.equals(type)))
				{
					MoneyTransferBatchCriteria moneyTransferBatchCriteria = new MoneyTransferBatchCriteria();
					moneyTransferBatchCriteria.setStatusList(new ArrayList<MoneyTransferBatchStatusEnum>());
					moneyTransferBatchCriteria.getStatusList().add(MoneyTransferBatchStatusEnum.ON_HOLD);

					if (LOCATION.equals(type))
					{
						entity = fetchLocationById(new FetchByIdRequest(id));
						sdnHistoryRequest.setMatchType(SdnMatchTypeEnum.ENTITY);
						moneyTransferBatchCriteria.setLocationId(id);

					}
					else if (ORGANIZATION.equals(type))
					{
						entity = fetchOrganizationById(new FetchByIdRequest(id));
						sdnHistoryRequest.setMatchType(SdnMatchTypeEnum.ENTITY);
						moneyTransferBatchCriteria.setOrganizationId(id);
					}

					batchOrTransaction =
							fetchBatchesByRequest(new MoneyTransferBatchInquiryRequest(moneyTransferBatchCriteria));
				}
				else
				{

					MoneyTransferCriteria moneyTransferCriteria = new MoneyTransferCriteria();
					moneyTransferCriteria.setStatusList(new ArrayList<MoneyTransferStatusEnum>());
					moneyTransferCriteria.getStatusList().add(MoneyTransferStatusEnum.ON_HOLD);

					if (MEMBER.equals(type))
					{
						sdnHistoryRequest.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);
						entity = fetchMemberById(new FetchByIdRequest(id));
						moneyTransferCriteria.setMember(new Member(id));
					}
					else if (RECIPIENT.equals(type))
					{
						entity = fetchRecipientById(new FetchByIdRequest(id));
						sdnHistoryRequest.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);
						moneyTransferCriteria.setRecipientId(id);
					}
					else if (LIAISON.equals(type))
					{
						entity = fetchLiaisonById(new FetchByIdRequest(id));
						sdnHistoryRequest.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);
					}

					batchOrTransaction =
							fetchTransactionsByRequest(new MoneyTransferInquiryRequest(moneyTransferCriteria));
				}

				sdnHist = getSdnBAI().fetchFullSdnStatusHistory(sdnHistoryRequest);

				SdnEntry entry = null;
				SdnStatusHistory history = null;
				if (!ValidationUtil.isNullOrEmpty(sdnHist.getSdnStatusHistoryList()))
				{
					history = sdnHist.getSdnStatusHistoryList().get(sdnHist.getSdnStatusHistoryList().size() - 1);
					if (!ValidationUtil.isNullOrEmpty(history.getSdnMatchRecordList()))
					{
						entry =
								unmarshalSdnEntry(history.getSdnMatchRecordList().get(0).getXmlRecord());
					}
				}

				// ATTENTION: The UI will need to render one tab with SDN Details per match id (the tab shows the sdn
				// entry's main uid, not the match id).
				// This will provide a map of matches indexed by 'id_<match id>'
				Map<String, SdnMatchRecord> matches = new HashMap<String, SdnMatchRecord>();
				for (SdnMatchRecord match : history.getSdnMatchRecordList())
				{
					String key = "id_" + match.getId();
					matches.put(key, match);
				}

				// Map of corresponding unmarshaled SDN Records, also by 'id_<match_id>
				Map<String, SdnEntry> sdnEntries = new HashMap<String, SdnEntry>();
				for (Entry<String, SdnMatchRecord> e : matches.entrySet())
				{
					sdnEntries.put(e.getKey(), unmarshalSdnEntry(e.getValue().getXmlRecord()));
				}

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(entity));
				modelAndView.addObject(SDN_HISTORY,
						getMapper().writeValueAsString(sdnHist.getSdnStatusHistoryList()));
				modelAndView.addObject(TYPE, type);
				modelAndView.addObject(SDN_ENTRY, getMapper().writeValueAsString(sdnEntries));
				modelAndView.addObject(SDN_MATCHES, getMapper().writeValueAsString(matches));
				modelAndView.addObject(MONEY_TRANSFER, getMapper().writeValueAsString(batchOrTransaction));

				return modelAndView;
			}

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
				modelAndView.addObject(SDN_HISTORY, null);
				modelAndView.addObject(TYPE, null);

			}
		}

		return modelAndView;
	}

	/**
	 * Fetch member by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the sdn response
	 */
	public MemberResponse fetchMemberById(FetchByIdRequest fetchByIdRequest)
	{

		MemberResponse sdnResponse = new MemberResponse();
		try
		{

			sdnResponse =
					getMemberBAI().fetchMemberById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return sdnResponse;
	}

	/**
	 * Fetch member by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the sdn response
	 */
	public RecipientResponse fetchRecipientById(FetchByIdRequest fetchByIdRequest)
	{

		RecipientResponse sdnResponse = new RecipientResponse();
		try
		{
			sdnResponse =
					getRecipientBAI().fetchRecipientById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return sdnResponse;
	}

	/**
	 * Fetch member by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the sdn response
	 */
	public OrganizationResponse fetchOrganizationById(FetchByIdRequest fetchByIdRequest)
	{

		OrganizationResponse sdnResponse = new OrganizationResponse();
		try
		{

			sdnResponse =
					getOrganizationBAI().fetchOrganizationById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return sdnResponse;
	}

	/**
	 * Fetch member by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the sdn response
	 */
	public LocationResponse fetchLocationById(FetchByIdRequest fetchByIdRequest)
	{

		LocationResponse sdnResponse = new LocationResponse();
		try
		{
			sdnResponse =
					getLocationBAI().fetchLocationById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return sdnResponse;
	}

	/**
	 * Fetch member by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the sdn response
	 */
	public LiaisonResponse fetchLiaisonById(FetchByIdRequest fetchByIdRequest)
	{

		LiaisonResponse sdnResponse = new LiaisonResponse();
		try
		{
			sdnResponse =
					getContactBAI().fetchLiaisonById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return sdnResponse;
	}

	/**
	 * Unmarshal sdn entry.
	 *
	 * @param sdnEntryXml the sdn entry xml
	 * @return the sdn entry
	 */
	private SdnEntry unmarshalSdnEntry(String sdnEntryXml)
	{

		try
		{
			XMLStreamReader xsr =
					XMLInputFactory.newFactory().createXMLStreamReader(new StreamSource(new StringReader(sdnEntryXml)));
			xsr.nextTag();
			SdnEntry entry = getUnmarshaller().unmarshal(xsr, SdnEntry.class).getValue();
			return entry;

		}
		catch (Exception e)
		{
			LOG.error("Could not unmarshal " + sdnEntryXml);
			throw new QATRuntimeException(e);
		}
	}

	/**
	 * Fetch batches by request.
	 *
	 * @param request the request
	 * @return the money transfer batch response
	 */
	public MoneyTransferBatchResponse fetchBatchesByRequest(MoneyTransferBatchInquiryRequest request)
	{

		MoneyTransferBatchResponse moneyTransferBatchResponse = new MoneyTransferBatchResponse();
		try
		{

			moneyTransferBatchResponse = getMoneyTransferBAI().fetchMoneyTransferBatchByRequest(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return moneyTransferBatchResponse;
	}

	public MoneyTransferResponse fetchTransactionsByRequest(MoneyTransferInquiryRequest request)
	{

		MoneyTransferResponse moneyTransferResponse = new MoneyTransferResponse();
		try
		{

			moneyTransferResponse = getMoneyTransferBAI().fetchMoneyTransferByRequest(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return moneyTransferResponse;
	}

	public SdnStatusHistoryResponse updateSdnStatusHistory(SdnStatusHistoryRequest request)
	{

		SdnStatusHistoryResponse sdnResponse = new SdnStatusHistoryResponse();
		try
		{

			sdnResponse =
					getSdnCheckerBAI().updateSdnStatusHistory(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return sdnResponse;
	}

	public SdnHistoryResponse fetchFullSdnStatusHistory(SdnStatusHistoryInquiryRequest request)
	{

		SdnHistoryResponse sdnResponse = new SdnHistoryResponse();

		try
		{

			sdnResponse =
					getSdnCheckerBAI().fetchSdnStatusHistoryByRequest(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return sdnResponse;
	}

	/**
	 * Gets the sdn bai.
	 *
	 * @return the sdn bai
	 */
	public ISdnCheckerBAI getSdnBAI()
	{
		return sdnBAI;
	}

	/**
	 * Sets the sdn bai.
	 *
	 * @param sdnBAI the new sdn bai
	 */
	@Resource
	public void setSdnBAI(ISdnCheckerBAI sdnBAI)
	{
		this.sdnBAI = sdnBAI;
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
	@Resource
	public void setUnmarshaller(Unmarshaller unmarshaller)
	{
		this.unmarshaller = unmarshaller;
	}

	/** The unmarshaller. */
	private Unmarshaller unmarshaller;

	/**
	 * Gets the member bai.
	 *
	 * @return the member bai
	 */
	public IMemberBAI getMemberBAI()
	{
		return memberBAI;
	}

	/**
	 * Sets the member bai.
	 *
	 * @param memberBAI the new member bai
	 */
	@Resource
	public void setMemberBAI(IMemberBAI memberBAI)
	{
		this.memberBAI = memberBAI;
	}

	/**
	 * Gets the recipient bai.
	 *
	 * @return the recipient bai
	 */
	public IRecipientBAI getRecipientBAI()
	{
		return recipientBAI;
	}

	/**
	 * Sets the recipient bai.
	 *
	 * @param recipientBAI the new recipient bai
	 */
	@Resource
	public void setRecipientBAI(IRecipientBAI recipientBAI)
	{
		this.recipientBAI = recipientBAI;
	}

	/**
	 * Gets the organization bai.
	 *
	 * @return the organization bai
	 */
	public IOrganizationBAI getOrganizationBAI()
	{
		return organizationBAI;
	}

	/**
	 * Sets the organization bai.
	 *
	 * @param organizationBAI the new organization bai
	 */
	@Resource
	public void setOrganizationBAI(IOrganizationBAI organizationBAI)
	{
		this.organizationBAI = organizationBAI;
	}

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the new location bai
	 */
	@Resource
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Gets the contact bai.
	 *
	 * @return the contact bai
	 */
	public ILiaisonBAI getContactBAI()
	{
		return contactBAI;
	}

	/**
	 * Sets the contact bai.
	 *
	 * @param contactBAI the new contact bai
	 */
	@Resource
	public void setContactBAI(ILiaisonBAI contactBAI)
	{
		this.contactBAI = contactBAI;
	}

	/**
	 * Gets the money transfer bai.
	 *
	 * @return the moneyTransferBAI
	 */
	public IMoneyTransferBAI getMoneyTransferBAI()
	{
		return moneyTransferBAI;
	}

	/**
	 * Sets the money transfer bai.
	 *
	 * @param moneyTransferBAI the moneyTransferBAI to set
	 */
	@Resource
	public void setMoneyTransferBAI(IMoneyTransferBAI moneyTransferBAI)
	{
		this.moneyTransferBAI = moneyTransferBAI;
	}

	/**
	 * @return the sdnCheckerBAI
	 */
	public ISdnCheckerBAI getSdnCheckerBAI()
	{
		return sdnCheckerBAI;
	}

	/**
	 * @param sdnCheckerBAI the sdnCheckerBAI to set
	 */
	@Resource
	public void setSdnCheckerBAI(ISdnCheckerBAI sdnCheckerBAI)
	{
		this.sdnCheckerBAI = sdnCheckerBAI;
	}

}
