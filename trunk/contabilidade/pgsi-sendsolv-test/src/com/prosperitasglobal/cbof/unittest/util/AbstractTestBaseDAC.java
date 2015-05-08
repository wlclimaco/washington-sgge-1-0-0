package com.prosperitasglobal.cbof.unittest.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.prosperitasglobal.cbof.dac.ICodeValueDAC;
import com.prosperitasglobal.cbof.dac.IContactDAC;
import com.prosperitasglobal.cbof.dac.ICountryDAC;
import com.prosperitasglobal.cbof.dac.IIndustryClassificationDAC;
import com.prosperitasglobal.cbof.dac.ILanguageDAC;
import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.dac.IRangeDAC;
import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.CodeValueEnum;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.Email;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.callingcard.dac.ICallingCardDAC;
import com.prosperitasglobal.sendsolv.dac.ICurrencyPurchaseDAC;
import com.prosperitasglobal.sendsolv.dac.IDailyCurrencyRateDAC;
import com.prosperitasglobal.sendsolv.dac.IDocumentTypeDAC;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IMoneyTransferBatchDAC;
import com.prosperitasglobal.sendsolv.dac.IMoneyTransferDAC;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.dac.IPayerDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.dac.IProductPlanDAC;
import com.prosperitasglobal.sendsolv.dac.IProductPlanTemplateGroupDAC;
import com.prosperitasglobal.sendsolv.dac.ISecurityQuestionDAC;
import com.prosperitasglobal.sendsolv.dac.ISuspiciousActivityDAC;
import com.prosperitasglobal.sendsolv.integration.twilio.dac.IIvrDAC;
import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRate;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.Person;
import com.prosperitasglobal.sendsolv.model.PersonName;
import com.prosperitasglobal.sendsolv.model.PersonSecurityAnswer;
import com.prosperitasglobal.sendsolv.model.Product;
import com.prosperitasglobal.sendsolv.model.ProductPlan;
import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.SecurityQuestion;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.criteria.PayerCriteria;
import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SarMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferProcessingResponse;
import com.prosperitasglobal.sendsolv.sdn.dac.ISdnCheckerDAC;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.Message;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class AbstractTestBaseDAC.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/unittest-datasource-txn-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/sendsolv-dac-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/callingcard/unittest/dac/mybatis/conf/calling-card-dac-context.xml",
		"classpath:com/prosperitasglobal/cbof/unittest/dac/mybatis/conf/cbof-dac-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/sdn/unittest/dac/mybatis/conf/sendsolv-sdn-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("sqlserver")
public abstract class AbstractTestBaseDAC extends AbstractTransactionalJUnit4SpringContextTests
{

	/** The Constant USER_NAME. */
	private static final String USER_NAME = "PGSI";

	/** The Constant MAX_NUMBER. */
	private static final int MAX_NUMBER = 99999;

	/** The Constant ADDRESSES_NEED_TO_MATCH. */
	private static final String ADDRESSES_NEED_TO_MATCH = "Addresses need to match";

	/** The Constant PHONES_NEED_TO_MATCH. */
	private static final String PHONES_NEED_TO_MATCH = "Phones need to match";

	/** The code value dac. */
	private ICodeValueDAC codeValueDAC;

	/** The organization dac. */
	private IOrganizationDAC organizationDAC;

	/** The payer dac. */
	private IPayerDAC payerDAC;

	/** The CurrencyPurchase dac. */
	private ICurrencyPurchaseDAC currencyPurchaseDAC;

	/** The DailyCurrencyRate dac. */
	private IDailyCurrencyRateDAC dailyCurrencyRateDAC;

	/** The person dac. */
	private IPersonDAC personDAC;

	/** The product plan dac. */
	private IProductPlanDAC productPlanDAC;

	/** The product plan template group dac. */
	private IProductPlanTemplateGroupDAC productPlanTemplateGroupDAC;

	/** The contact dac. */
	private IContactDAC contactDAC;

	/** The note dac. */
	private INoteDAC noteDAC;

	/** The location dac. */
	private ILocationDAC locationDAC;

	/** The contry dac. */
	private ICountryDAC contryDAC;

	/** The document type dac. */
	private IDocumentTypeDAC documentTypeDAC;

	/** The industry classification dac. */
	private IIndustryClassificationDAC industryClassificationDAC;

	/** The range dac. */
	private IRangeDAC rangeDAC;

	/** The country dac. */
	private ICountryDAC countryDAC;

	/** The language dac. */
	private ILanguageDAC languageDAC;

	/** The money transfer dac. */
	private IMoneyTransferDAC moneyTransferDAC;

	/** The money transfer batch dac. */
	private IMoneyTransferBatchDAC moneyTransferBatchDAC;

	/** The sdn checker dac. */
	private ISdnCheckerDAC sdnCheckerDACImpl;

	/** The calling card dac. */
	private ICallingCardDAC callingCardDACImpl;

	/** The security question dac. */
	private ISecurityQuestionDAC securityQuestionDAC;

	/** The ivr dac impl. */
	private IIvrDAC ivrDACImpl;

	/** The suspicious activity dac. */
	private ISuspiciousActivityDAC suspiciousActivityDAC;

	/**
	 * @return the suspiciousActivityDAC
	 */
	public ISuspiciousActivityDAC getSuspiciousActivityDAC()
	{
		return suspiciousActivityDAC;
	}

	/**
	 * @param suspiciousActivityDAC the suspiciousActivityDAC to set
	 */
	@Resource
	public void setSuspiciousActivityDAC(ISuspiciousActivityDAC suspiciousActivityDAC)
	{
		this.suspiciousActivityDAC = suspiciousActivityDAC;
	}

	public IIvrDAC getIvrDACImpl()
	{
		return ivrDACImpl;
	}

	@Resource
	public void setIvrDACImpl(IIvrDAC ivrDACImpl)
	{
		this.ivrDACImpl = ivrDACImpl;
	}

	/**
	 * Gets the security question dac.
	 *
	 * @return the securityQuestionDAC
	 */
	public ISecurityQuestionDAC getSecurityQuestionDAC()
	{
		return securityQuestionDAC;
	}

	/**
	 * Sets the security question dac.
	 *
	 * @param securityQuestionDAC the securityQuestionDAC to set
	 */
	@Resource
	public void setSecurityQuestionDAC(ISecurityQuestionDAC securityQuestionDAC)
	{
		this.securityQuestionDAC = securityQuestionDAC;
	}

	/**
	 * Get the calling card DAC.
	 *
	 * @return The calling card DAC.
	 */
	public ICallingCardDAC getCallingCardDACImpl()
	{
		return callingCardDACImpl;
	}

	/**
	 * Set the calling card DAC.
	 *
	 * @param callingCardDACImpl The calling card DAC to set.
	 */
	@Resource
	public void setCallingCardDACImpl(ICallingCardDAC callingCardDACImpl)
	{
		this.callingCardDACImpl = callingCardDACImpl;
	}

	/**
	 * Gets the code value dac.
	 *
	 * @return the code value dac
	 */
	public ICodeValueDAC getCodeValueDAC()
	{
		return codeValueDAC;
	}

	/**
	 * Sets the code value dac.
	 *
	 * @param codeValueDAC the code value dac
	 */
	@Resource
	public void setCodeValueDAC(ICodeValueDAC codeValueDAC)
	{
		this.codeValueDAC = codeValueDAC;
	}

	/**
	 * Gets the organization dac.
	 *
	 * @return the organization dac
	 */
	public IOrganizationDAC getOrganizationDAC()
	{
		return organizationDAC;
	}

	/**
	 * Sets the organization dac.
	 *
	 * @param organizationDAC the organization dac
	 */
	@Resource
	public void setOrganizationDAC(IOrganizationDAC organizationDAC)
	{
		this.organizationDAC = organizationDAC;
	}

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
	@Resource
	public void setContactDAC(IContactDAC contactDAC)
	{
		this.contactDAC = contactDAC;
	}

	/**
	 * Gets the person dac.
	 *
	 * @return the person dac
	 */
	public IPersonDAC getPersonDAC()
	{
		return personDAC;
	}

	/**
	 * Gets the payer dac.
	 *
	 * @return the payer dac.
	 */
	public IPayerDAC getPayerDAC()
	{
		return payerDAC;
	}

	/**
	 * Gets the CurrencyPurchase dac.
	 *
	 * @return the CurrencyPurchase dac.
	 */
	public ICurrencyPurchaseDAC getCurrencyPurchaseDAC()
	{
		return currencyPurchaseDAC;
	}

	/**
	 * Gets the DailyCurrencyRate dac.
	 *
	 * @return the DailyCurrencyRate dac.
	 */
	public IDailyCurrencyRateDAC getDailyCurrencyRateDAC()
	{
		return dailyCurrencyRateDAC;
	}

	/**
	 * Sets the person dac.
	 *
	 * @param personDAC the person dac
	 */
	@Resource
	public void setPersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
	}

	/**
	 * Sets the payer dac.
	 *
	 * @param payerDAC the payer dac
	 */
	@Resource
	public void setPayerDAC(IPayerDAC payerDAC)
	{
		this.payerDAC = payerDAC;
	}

	/**
	 * Sets the CurrencyPurchase dac.
	 *
	 * @param currencyPurchaseDAC the currency purchase dac
	 * @parma currencyPurchaseDAC the currencyPurchase dac
	 */

	@Resource
	public void setCurrencyPurchaseDAC(ICurrencyPurchaseDAC currencyPurchaseDAC)
	{
		this.currencyPurchaseDAC = currencyPurchaseDAC;
	}

	/**
	 * Sets the DailyCurrencyRate dac.
	 *
	 * @param dailyCurrencyRateDAC the daily currency rate dac
	 */
	@Resource
	public void setDailyCurrencyRateDAC(IDailyCurrencyRateDAC dailyCurrencyRateDAC)
	{
		this.dailyCurrencyRateDAC = dailyCurrencyRateDAC;
	}

	/**
	 *
	 * Gets the product plan dac.
	 *
	 * @return The product plan DAC
	 */
	public IProductPlanDAC getProductPlanDAC()
	{
		return productPlanDAC;
	}

	/**
	 * Sets the product plan dac.
	 *
	 * @param productPlanDAC the product plan dac
	 */
	@Resource
	public void setProductPlanDAC(IProductPlanDAC productPlanDAC)
	{
		this.productPlanDAC = productPlanDAC;
	}

	/**
	 * Gets the product plan template group dac.
	 *
	 * @return The product plan template group DAC
	 */
	public IProductPlanTemplateGroupDAC getProductPlanTemplateGroupDAC()
	{
		return productPlanTemplateGroupDAC;
	}

	/**
	 * Sets the product plan template group dac.
	 *
	 * @param productPlanTemplateGroupDAC the product plan template group dac
	 */
	@Resource
	public void setProductPlanTemplateGroupDAC(IProductPlanTemplateGroupDAC productPlanTemplateGroupDAC)
	{
		this.productPlanTemplateGroupDAC = productPlanTemplateGroupDAC;
	}

	/**
	 * Gets the note dac.
	 *
	 * @return the note dac
	 */
	public INoteDAC getNoteDAC()
	{
		return noteDAC;
	}

	/**
	 * Sets the note dac.
	 *
	 * @param noteDAC the note dac
	 */
	@Resource
	public void setNoteDAC(INoteDAC noteDAC)
	{
		this.noteDAC = noteDAC;
	}

	/**
	 * Gets the location dac.
	 *
	 * @return the location dac
	 */
	public ILocationDAC getLocationDAC()
	{
		return locationDAC;
	}

	/**
	 * Sets the location dac.
	 *
	 * @param locationDAC the location dac
	 */
	@Resource
	public void setLocationDAC(ILocationDAC locationDAC)
	{
		this.locationDAC = locationDAC;
	}

	/**
	 * Gets the contry dac.
	 *
	 * @return the contry dac
	 */
	public ICountryDAC getContryDAC()
	{
		return contryDAC;
	}

	/**
	 * Sets the contry dac.
	 *
	 * @param contryDAC the contry dac
	 */
	@Resource
	public void setContryDAC(ICountryDAC contryDAC)
	{
		this.contryDAC = contryDAC;
	}

	/**
	 * Gets the document type dac.
	 *
	 * @return the document type dac
	 */
	public IDocumentTypeDAC getDocumentTypeDAC()
	{
		return documentTypeDAC;
	}

	/**
	 * Sets the document type dac.
	 *
	 * @param documentTypeDAC the document type dac
	 */
	@Resource
	public void setDocumentTypeDAC(IDocumentTypeDAC documentTypeDAC)
	{
		this.documentTypeDAC = documentTypeDAC;
	}

	/**
	 * Gets the industry classification dac.
	 *
	 * @return the industry classification dac
	 */
	public IIndustryClassificationDAC getIndustryClassificationDAC()
	{
		return industryClassificationDAC;
	}

	/**
	 * Sets the industry classification dac.
	 *
	 * @param industryClassificationDAC the industry classification dac
	 */
	@Resource
	public void setIndustryClassificationDAC(IIndustryClassificationDAC industryClassificationDAC)
	{
		this.industryClassificationDAC = industryClassificationDAC;
	}

	/**
	 * Gets the range dac.
	 *
	 * @return the range dac
	 */
	public IRangeDAC getRangeDAC()
	{
		return rangeDAC;
	}

	/**
	 * Sets the range dac.
	 *
	 * @param rangeDAC the range dac
	 */
	@Resource
	public void setRangeDAC(IRangeDAC rangeDAC)
	{
		this.rangeDAC = rangeDAC;
	}

	/**
	 * Gets the country dac.
	 *
	 * @return the country dac
	 */
	public ICountryDAC getCountryDAC()
	{
		return countryDAC;
	}

	/**
	 * Sets the country dac.
	 *
	 * @param countryDAC the country dac
	 */
	@Resource
	public void setCountryDAC(ICountryDAC countryDAC)
	{
		this.countryDAC = countryDAC;
	}

	/**
	 * Gets the language dac.
	 *
	 * @return the language dac
	 */
	public ILanguageDAC getLanguageDAC()
	{
		return languageDAC;
	}

	/**
	 * Sets the language dac.
	 *
	 * @param languageDAC the language dac
	 */
	@Resource
	public void setLanguageDAC(ILanguageDAC languageDAC)
	{
		this.languageDAC = languageDAC;
	}

	/**
	 * Gets the money transfer batch dac.
	 *
	 * @return the money transfer batch dac
	 */
	public IMoneyTransferBatchDAC getMoneyTransferBatchDAC()
	{
		return moneyTransferBatchDAC;
	}

	/**
	 * Sets the money transfer batch dac.
	 *
	 * @param moneyTransferBatchDAC the money transfer batch dac
	 */
	@Resource
	public void setMoneyTransferBatchDAC(IMoneyTransferBatchDAC moneyTransferBatchDAC)
	{
		this.moneyTransferBatchDAC = moneyTransferBatchDAC;
	}

	/**
	 * Gets the money transfer dac.
	 *
	 * @return the money transfer dac
	 */
	public IMoneyTransferDAC getMoneyTransferDAC()
	{
		return moneyTransferDAC;
	}

	/**
	 * Sets the money transfer dac.
	 *
	 * @param moneyTransferDAC the money transfer dac
	 */
	@Resource
	public void setMoneyTransferDAC(IMoneyTransferDAC moneyTransferDAC)
	{
		this.moneyTransferDAC = moneyTransferDAC;
	}

	/**
	 * Get the implementation of the {@link ISdnCheckerDAC}. Injected by Spring.
	 *
	 * @return The implementation.
	 */
	public ISdnCheckerDAC getSdnCheckerDACImpl()
	{
		return sdnCheckerDACImpl;
	}

	/**
	 * Set the implementation of the {@link ISdnCheckerDAC}. Injected by Spring.
	 *
	 * @param sdnCheckerDACImpl The implementation to set.
	 */
	@Resource
	public void setSdnCheckerDACImpl(ISdnCheckerDAC sdnCheckerDACImpl)
	{
		this.sdnCheckerDACImpl = sdnCheckerDACImpl;
	}

	/**
	 * Insert liaison.
	 *
	 * @return the liaison
	 */
	protected Liaison insertLiaison()
	{
		Organization organization = insertOrganization();
		return insertLiaison(organization.getId());
	}

	/**
	 * Insert liaison.
	 *
	 * @param partentId the partent id
	 * @return the liaison
	 */
	protected Liaison insertLiaison(Integer partentId)
	{
		Liaison liaison = CommonTestRoutines.createDummyLiaison(partentId);
		liaison.setPrefix(fetchSuffixPrefix(CodeValueEnum.PREFIX));
		liaison.setSuffix(fetchSuffixPrefix(CodeValueEnum.SUFFIX));
		liaison.setModelAction(PersistanceActionEnum.INSERT);
		liaison.setParticipantId(Integer.toString(partentId));
		InternalResultsResponse<Liaison> response = getPersonDAC().insertLiaison(liaison);
		CommonTestRoutines.assertResultResponse(response);

		return response.getFirstResult();
	}

	/**
	 * Insert location.
	 *
	 * @return the location.
	 */
	protected Location insertLocation()
	{
		return insertLocation(0);
	}

	/**
	 * Insert location.
	 *
	 * @param uniqueIndexForLocation Unique index that will be appended at the end of name. If value is <code>0</code>,
	 *            no value will be appended.
	 * @return the location.
	 */
	protected Location insertLocation(int uniqueIndexForLocation)
	{
		Location location = CommonTestRoutines.createDummyLocation(insertOrganization());

		if (uniqueIndexForLocation != 0)
		{
			location.setName(location.getName() + Integer.valueOf(uniqueIndexForLocation));
		}

		InternalResultsResponse<Location> response = getLocationDAC().insertLocation(location);
		CommonTestRoutines.assertResultResponse(response);
		return response.getFirstResult();
	}

	/**
	 * Insert recipient.
	 *
	 * @return the recipient
	 */
	protected Recipient insertRecipient()
	{
		return insertRecipient(0);
	}

	/**
	 * Insert recipient.
	 *
	 * @param uniqueIndexForRecipient Unique index for member.
	 * @return the recipient
	 */
	protected Recipient insertRecipient(int uniqueIndexForRecipient)
	{
		String uniqueFirstNameChars = "";
		String uniqueLastNameChars = "";

		if (uniqueIndexForRecipient > 0)
		{
			uniqueFirstNameChars = Integer.toString(uniqueIndexForRecipient);
			uniqueLastNameChars = Integer.toString(MAX_NUMBER - uniqueIndexForRecipient);
		}

		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		recipient.setFirstName(recipient.getFirstName() + uniqueFirstNameChars);
		recipient.setLastName(recipient.getLastName() + uniqueLastNameChars);
		recipient.setPrefix(fetchSuffixPrefix(CodeValueEnum.PREFIX));
		recipient.setSuffix(fetchSuffixPrefix(CodeValueEnum.SUFFIX));
		recipient.setParticipantId(Integer.toString(uniqueIndexForRecipient));
		InternalResultsResponse<Recipient> response = getPersonDAC().insertRecipient(recipient);

		CommonTestRoutines.assertResultResponse(response);

		return response.getFirstResult();
	}

	/**
	 * Insert a generic {@link TemplateProductPlan} object.
	 *
	 * @return The {@link TemplateProductPlan} object inserted.
	 */
	protected TemplateProductPlan insertTemplateProductPlan()
	{
		return insertTemplateProductPlan(0);
	}

	/**
	 * Insert a {@link TemplateProductPlan} object.
	 *
	 * @param uniqueIndexForTemplateProductPlan Unique index that will be appended at the end of name. If value is
	 *            <code>0</code>, no value will be appended.
	 * @return The {@link TemplateProductPlan} object inserted.
	 */
	protected TemplateProductPlan insertTemplateProductPlan(int uniqueIndexForTemplateProductPlan)
	{
		String uniqueNameChars = "";

		if (uniqueIndexForTemplateProductPlan > 0)
		{
			uniqueNameChars = Integer.toString(uniqueIndexForTemplateProductPlan);
		}

		Product product = insertProduct();
		Payer payer = insertPayer();
		TemplateProductPlan templateProductPlan = CommonTestRoutines.createDummyTemplateProductPlan(product, payer);
		templateProductPlan.setName(templateProductPlan.getName() + uniqueNameChars);
		templateProductPlan.setProductPlanTemplateGroupId(null);
		InternalResponse response = getProductPlanDAC().insertTemplateProductPlan(templateProductPlan);
		CommonTestRoutines.assertResponse(response);
		return templateProductPlan;
	}

	/**
	 * Insert member.
	 *
	 * @return the member
	 */
	protected Member insertMember()
	{
		return insertMember(0);
	}

	/**
	 * Insert member in the database.
	 *
	 * @param uniqueIndexForMember Unique index for member.
	 * @return the member
	 */
	protected Member insertMember(int uniqueIndexForMember)
	{
		Member member = createMember(uniqueIndexForMember);

		InternalResultsResponse<Member> response = getPersonDAC().insertMember(member);

		CommonTestRoutines.assertResultResponse(response);

		return response.getFirstResult();
	}

	/**
	 * Insert organization.
	 *
	 * @return the organization
	 */
	protected Organization insertOrganization()
	{
		InternalResultsResponse<Organization> response =
				getOrganizationDAC().insertOrganization(CommonTestRoutines.createDummyOrganization());
		CommonTestRoutines.assertResultResponse(response);
		return response.getFirstResult();
	}

	/**
	 * Insert a generic {@link ProductPlanTemplateGroup} object.
	 *
	 * @return The {@link ProductPlanTemplateGroup} object inserted.
	 */
	protected ProductPlanTemplateGroup insertProductPlanTemplateGroup()
	{
		return insertProductPlanTemplateGroup(0);
	}

	/**
	 * Insert a {@link ProductPlanTemplateGroup} object.
	 *
	 * @param uniqueIndexForProductPlanTemplateGroup Unique index that will be appended at the end of name. If value is
	 *            <code>0</code>,
	 *            no value will be appended.
	 * @return The {@link ProductPlanTemplateGroup} object inserted.
	 */
	protected ProductPlanTemplateGroup insertProductPlanTemplateGroup(int uniqueIndexForProductPlanTemplateGroup)
	{
		String uniqueNameChars = "";

		if (uniqueIndexForProductPlanTemplateGroup > 0)
		{
			uniqueNameChars = Integer.toString(uniqueIndexForProductPlanTemplateGroup);
		}

		Product product = insertProduct();
		Payer payer = insertPayer();

		ProductPlanTemplateGroup productPlanTemplateGroup =
				CommonTestRoutines.createDummyProductPlanTemplateGroup(product, payer);
		productPlanTemplateGroup.setName(productPlanTemplateGroup.getName() + uniqueNameChars);

		productPlanTemplateGroup.getTemplateProductPlanList().add(
				CommonTestRoutines.createDummyTemplateProductPlan(product, payer));
		productPlanTemplateGroup.getTemplateProductPlanList().add(
				CommonTestRoutines.createDummyTemplateProductPlan(product, payer));
		productPlanTemplateGroup.getTemplateProductPlanList().add(
				CommonTestRoutines.createDummyTemplateProductPlan(product, payer));

		InternalResponse response =
				getProductPlanTemplateGroupDAC().insertProductPlanTemplateGroup(productPlanTemplateGroup);
		CommonTestRoutines.assertResponse(response);
		return productPlanTemplateGroup;
	}

	/**
	 * Insert a {@link Payer} object.
	 *
	 * @return The {@link Payer} object inserted.
	 */
	protected Payer insertPayer()
	{
		return insertPayer(0);
	}

	/**
	 * Insert a {@link Payer} object.
	 *
	 * @param uniqueIndexForPayer Unique index that will be appended at the end of name. If value is <code>0</code>, no
	 *            value will be appended.
	 * @return The {@link Payer} object inserted.
	 */
	protected Payer insertPayer(int uniqueIndexForPayer)
	{
		String uniqueNameChars = "";
		if (uniqueIndexForPayer > 0)
		{
			uniqueNameChars = Integer.toString(uniqueIndexForPayer);
		}

		Payer payer = CommonTestRoutines.createDummyPayer();
		payer.setName(payer.getName() + uniqueNameChars);

		List<StateProvinceRegion> stateProvinceRegionList = fetchStateProvinceRegion(payer.getCountry());
		for (StateProvinceRegion stateProvinceRegion : stateProvinceRegionList)
		{
			stateProvinceRegion.setModelAction(PersistanceActionEnum.INSERT);
			stateProvinceRegion.setCreateDateUTC(payer.getCreateDateUTC());
			stateProvinceRegion.setCreateUser(payer.getCreateUser());
		}
		payer.setStateProvinceRegionList(stateProvinceRegionList);

		InternalResponse response = getPayerDAC().insertPayer(payer);
		CommonTestRoutines.assertResponse(response);
		return payer;
	}

	/**
	 * Insert currency purchase.
	 *
	 * @return the currency purchase
	 */
	protected CurrencyPurchase insertCurrencyPurchase()
	{
		Payer payer = insertPayer();

		CurrencyPurchase currencyPurchase = CommonTestRoutines.createDummyCurrencyPurchase();
		currencyPurchase.setPayerId(payer.getId());

		InternalResponse response = getCurrencyPurchaseDAC().insertCurrencyPurchase(currencyPurchase);
		CommonTestRoutines.assertResponse(response);
		return currencyPurchase;
	}

	/**
	 * Insert daily currency rate.
	 *
	 * @return the daily currency rate
	 */
	protected DailyCurrencyRate insertDailyCurrencyRate()
	{
		Payer payer = insertPayer();
		DailyCurrencyRate dailyCurrencyRate =
				CommonTestRoutines.createDummyDailyCurrencyRate(payer, CommonTestRoutines.createDummyCurrency());

		InternalResponse response = getDailyCurrencyRateDAC().insertDailyCurrencyRate(dailyCurrencyRate);
		CommonTestRoutines.assertResponse(response);
		return dailyCurrencyRate;
	}

	/**
	 * Insert a generic {@link Product} object.
	 *
	 * @return The {@link Product} object inserted.
	 */
	protected Product insertProduct()
	{
		return insertProduct(0);
	}

	/**
	 * Insert a {@link Product} object.
	 *
	 * @param uniqueIndexForProduct Unique index that will be appended at the end of name. If value is <code>0</code>,
	 *            no value will be appended.
	 * @return The {@link Product} object inserted.
	 */
	protected Product insertProduct(int uniqueIndexForProduct)
	{
		String uniqueNameChars = "";

		if (uniqueIndexForProduct > 0)
		{
			uniqueNameChars = Integer.toString(uniqueIndexForProduct);
		}

		Product product = CommonTestRoutines.createDummyProduct();
		product.setName(product.getName() + uniqueNameChars);
		InternalResponse response = getProductPlanDAC().insertProduct(product);
		CommonTestRoutines.assertResponse(response);
		return product;
	}

	/**
	 * Insert a generic {@link BusinessProductPlan} object.
	 *
	 * @return The {@link BusinessProductPlan} object inserted.
	 */
	protected BusinessProductPlan insertBusinessProductPlan()
	{
		return insertBusinessProductPlan(0);
	}

	/**
	 * Insert a {@link BusinessProductPlan} object.
	 *
	 * @param uniqueIndexForBusinessProductPlan Unique index that will be appended at the end of name. If value is
	 *            <code>0</code>, no value will be appended.
	 * @return The {@link BusinessProductPlan} object inserted.
	 */
	protected BusinessProductPlan insertBusinessProductPlan(int uniqueIndexForBusinessProductPlan)
	{
		String uniqueNameChars = "";

		if (uniqueIndexForBusinessProductPlan > 0)
		{
			uniqueNameChars = Integer.toString(uniqueIndexForBusinessProductPlan);
		}

		Product product = insertProduct();
		Payer payer = insertPayer(uniqueIndexForBusinessProductPlan);
		BusinessProductPlan businessProductPlan = CommonTestRoutines.createDummyBusinessProductPlan(product, payer);
		businessProductPlan.setLocationId(insertLocation(uniqueIndexForBusinessProductPlan).getId());
		businessProductPlan.setName(businessProductPlan.getName() + uniqueNameChars);
		InternalResponse response = getProductPlanDAC().insertBusinessProductPlan(businessProductPlan);
		CommonTestRoutines.assertResponse(response);
		return businessProductPlan;
	}

	/**
	 * Insert a {@link MoneyTransfer} object.
	 *
	 * @return The {@link MoneyTransfer} object inserted.
	 */
	protected MoneyTransfer insertMoneyTransfer()
	{
		return insertMoneyTransfer(0);
	}

	/**
	 * Insert a {@link MoneyTransfer} object.
	 *
	 * @param uniqueIndexForMoneyTransfer Unique index that will be appended at the end of names. If value is
	 *            <code>0</code>, no value will be appended.
	 * @return The {@link MoneyTransfer} object inserted.
	 */
	protected MoneyTransfer insertMoneyTransfer(int uniqueIndexForMoneyTransfer)
	{
		return insertMoneyTransfer(0, insertMoneyTransferBatch(uniqueIndexForMoneyTransfer), null);
	}

	/**
	 * Insert a {@link MoneyTransfer} object.
	 *
	 * @param uniqueIndexForMoneyTransfer Unique index that will be appended at the end of names. If value is
	 *            <code>0</code>, no value will be appended.
	 * @param member {@link Member} the {@link MoneyTransfer} will be associated to.
	 * @return The {@link MoneyTransfer} object inserted.
	 */
	protected MoneyTransfer insertMoneyTransfer(int uniqueIndexForMoneyTransfer, Member member)
	{
		return insertMoneyTransfer(0, insertMoneyTransferBatch(uniqueIndexForMoneyTransfer), member);
	}

	/**
	 * Insert a {@link MoneyTransfer} object.
	 *
	 * @param uniqueIndexForMoneyTransfer Unique index that will be appended at the end of names. If value is
	 *            <code>0</code>, no value will be appended.
	 * @param moneyTransferBatch The parent {@link MoneyTransferBatch}
	 * @param member {@link Member} the {@link MoneyTransfer} will be associated to. If null, one is added.
	 * @return The {@link MoneyTransfer} object inserted.
	 */
	protected MoneyTransfer insertMoneyTransfer(int uniqueIndexForMoneyTransfer, MoneyTransferBatch moneyTransferBatch,
			Member member)
	{
		boolean skipMemberCreation = false;

		if (member != null)
		{
			skipMemberCreation = true;
		}

		BusinessProductPlan businessProductPlan = moneyTransferBatch.getLocation().getBusinessProductPlanList().get(0);

		if (!skipMemberCreation)
		{
			member = CommonTestRoutines.createDummyMember();
		}

		Recipient recipient = null;
		if (uniqueIndexForMoneyTransfer == 0)
		{
			recipient = CommonTestRoutines.createDummyRecipient();
		}
		else
		{
			member.setLastName(member.getLastName() + Integer.toString(uniqueIndexForMoneyTransfer));
			recipient = CommonTestRoutines.createDummyRecipient();
			recipient.setLastName(recipient.getLastName() + Integer.toString(uniqueIndexForMoneyTransfer));
		}

		member.getEmploymentInfoList().get(0).setLocationId(insertLocation().getId());
		member.setPrefix(fetchSuffixPrefix(CodeValueEnum.PREFIX));
		member.setSuffix(fetchSuffixPrefix(CodeValueEnum.SUFFIX));
		member.setParticipantId(Integer.toString(uniqueIndexForMoneyTransfer));
		member.setPinNumber(Integer.toString(uniqueIndexForMoneyTransfer));

		if (!skipMemberCreation)
		{
			getPersonDAC().insertMember(member);
		}

		recipient.setPrefix(fetchSuffixPrefix(CodeValueEnum.PREFIX));
		recipient.setSuffix(fetchSuffixPrefix(CodeValueEnum.SUFFIX));
		recipient.setParticipantId(Integer.toString(uniqueIndexForMoneyTransfer));

		getPersonDAC().insertRecipient(recipient);

		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.getTransferSettingList().get(0).setPlanCategory(businessProductPlan.getPlanCategoryList().get(0));
		member.getTransferSettingList().get(0)
				.setProductPlanApplicability(businessProductPlan.getProductPlanApplicabilityList().get(0));
		member.getTransferSettingList().get(0).setEmploymentInfo(member.getEmploymentInfoList().get(0));
		member.getTransferSettingList().get(0).setMemberId(member.getId());
		member.getTransferSettingList().get(0).setRecipientId(recipient.getId());
		member.setModelAction(PersistanceActionEnum.UPDATE);

		getPersonDAC().updateMember(member);

		CurrencyPurchase currencyPurchase = CommonTestRoutines.createDummyCurrencyPurchase();
		currencyPurchase.setPayerId(member.getTransferSettingList().get(0).getProductPlanApplicability().getPayer()
				.getId());
		getCurrencyPurchaseDAC().insertCurrencyPurchase(currencyPurchase);

		MoneyTransfer moneyTransfer =
				CommonTestRoutines.createDummyMoneyTransfer(moneyTransferBatch, member.getTransferSettingList().get(0));
		moneyTransfer.setMoneyTransferBatchId(moneyTransferBatch.getId());
		moneyTransfer.setTransferSetting(member.getTransferSettingList().get(0));

		MoneyTransferStatus status = moneyTransfer.getStatusList().get(0);
		status.setResponse(new MoneyTransferProcessingResponse());
		status.getResponse().addMessage("sendsolv.base.defaultexception", MessageSeverity.Info, MessageLevel.None,
				new Object[] {1, 2});
		status.getResponse().addMessage(
				Message.internalMessage("intcode", MessageSeverity.Error, "Internal Test Message"));
		status.getResponse().setOperationSuccess(false);
		status.setMoneyTransferTransaction(CommonTestRoutines.createDummyMoneyTransferTransaction(moneyTransfer));
		InternalResponse response = getMoneyTransferDAC().insertMoneyTransfer(moneyTransfer);
		CommonTestRoutines.assertResponse(response);

		getDailyCurrencyRateDAC().insertDailyCurrencyRate(
				CommonTestRoutines.createDummyDailyCurrencyRate(moneyTransfer.getTransferSetting()
						.getProductPlanApplicability().getPayer(), moneyTransfer.getTransferSetting()
						.getProductPlanApplicability().getCurrency()));
		CommonTestRoutines.assertResponse(response);

		return moneyTransfer;
	}

	/**
	 * Insert a {@link MoneyTransfer} object.
	 *
	 * @param moneyTransferBatch The parent {@link MoneyTransferBatch}
	 * @return The {@link MoneyTransfer} object inserted.
	 */
	protected MoneyTransfer insertMoneyTransfer(MoneyTransferBatch moneyTransferBatch)
	{
		return insertMoneyTransfer(0, moneyTransferBatch, null);
	}

	/**
	 * Insert a {@link MoneyTransferBatch} object with no {@link MoneyTransfer} objects.
	 *
	 * @return The {@link MoneyTransferBatch} object inserted.
	 */
	protected MoneyTransferBatch insertMoneyTransferBatch()
	{
		return insertMoneyTransferBatch(0);
	}

	/**
	 * Insert a {@link MoneyTransferBatch} object with no {@link MoneyTransfer} objects.
	 *
	 * @param uniqueIndexForMoneyTransferBatch Unique index that will be appended at the end of names. If value is
	 *            <code>0</code>, no value will be appended.
	 * @return The {@link MoneyTransferBatch} object inserted.
	 */
	protected MoneyTransferBatch insertMoneyTransferBatch(int uniqueIndexForMoneyTransferBatch)
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan(uniqueIndexForMoneyTransferBatch);
		Location location =
				getLocationDAC().fetchLocationById(new FetchByIdRequest(businessProductPlan.getLocationId()))
						.getFirstResult();

		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		moneyTransferBatch.setLocation(location);
		moneyTransferBatch.getMoneyTransferList().remove(0);

		Member member = null;
		Recipient recipient = null;
		if (uniqueIndexForMoneyTransferBatch == 0)
		{
			member = CommonTestRoutines.createDummyMember();
			recipient = CommonTestRoutines.createDummyRecipient();
		}
		else
		{
			member = CommonTestRoutines.createDummyMember();
			member.setLastName(member.getLastName() + Integer.toString(uniqueIndexForMoneyTransferBatch));
			recipient = CommonTestRoutines.createDummyRecipient();
			recipient.setLastName(recipient.getLastName() + Integer.toString(uniqueIndexForMoneyTransferBatch));
		}

		member.getEmploymentInfoList().get(0).setLocationId(insertLocation().getId());
		member.setPrefix(fetchSuffixPrefix(CodeValueEnum.PREFIX));
		member.setSuffix(fetchSuffixPrefix(CodeValueEnum.SUFFIX));
		member.setParticipantId(Integer.toString(uniqueIndexForMoneyTransferBatch));
		member.setPinNumber(Integer.toString(uniqueIndexForMoneyTransferBatch));

		getPersonDAC().insertMember(member);

		recipient.setPrefix(fetchSuffixPrefix(CodeValueEnum.PREFIX));
		recipient.setSuffix(fetchSuffixPrefix(CodeValueEnum.SUFFIX));
		recipient.setParticipantId(Integer.toString(uniqueIndexForMoneyTransferBatch));

		getPersonDAC().insertRecipient(recipient);

		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.getTransferSettingList().get(0).setPlanCategory(businessProductPlan.getPlanCategoryList().get(0));
		member.getTransferSettingList().get(0)
				.setProductPlanApplicability(businessProductPlan.getProductPlanApplicabilityList().get(0));
		member.getTransferSettingList().get(0).setEmploymentInfo(member.getEmploymentInfoList().get(0));
		member.getTransferSettingList().get(0).setMemberId(member.getId());
		member.getTransferSettingList().get(0).setRecipientId(recipient.getId());
		member.setModelAction(PersistanceActionEnum.UPDATE);

		getPersonDAC().updateMember(member);

		MoneyTransfer moneyTransfer =
				CommonTestRoutines.createDummyMoneyTransfer(moneyTransferBatch, member.getTransferSettingList().get(0));

		moneyTransfer.setConfirmationNumber(moneyTransfer.getConfirmationNumber() + uniqueIndexForMoneyTransferBatch);
		moneyTransferBatch.getMoneyTransferList().add(moneyTransfer);

		InternalResponse response = getMoneyTransferBatchDAC().insertMoneyTransferBatch(moneyTransferBatch);
		CommonTestRoutines.assertResponse(response);
		return moneyTransferBatch;
	}

	/**
	 * Fetch suffix prefix.
	 *
	 * @param type the type
	 * @return the code value
	 */
	protected CodeValue fetchSuffixPrefix(CodeValueEnum type)
	{
		CodeValueRequest request = new CodeValueRequest();
		request.setCodeValueType(type);
		InternalResultsResponse<CodeValue> result = getCodeValueDAC().fetchAllCodeValueByType(request);

		return result.getFirstResult();
	}

	/**
	 * Fetch payer by a country.
	 *
	 * @param country The country of the payer to fetch
	 * @return The first payer containing the country.
	 */
	protected Payer fetchPayer(Country country)
	{
		PayerInquiryRequest request = new PayerInquiryRequest();
		PayerCriteria criteria = new PayerCriteria();
		criteria.setCountry(country);
		request.setCriteria(criteria);
		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		return response.getFirstResult();
	}

	/**
	 * Fetch payer by a payer name.
	 *
	 * @param payerName The name of the payer to fetch
	 * @return The first payer containing the name.
	 */
	protected Payer fetchPayer(String payerName)
	{
		PayerInquiryRequest request = new PayerInquiryRequest();
		PayerCriteria criteria = new PayerCriteria();
		criteria.setName(payerName);
		request.setCriteria(criteria);
		InternalResultsResponse<Payer> response = getPayerDAC().fetchPayerByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		return response.getFirstResult();
	}

	/**
	 * Fetch all {@link StateProvinceRegion} associated with a country.
	 *
	 * @param country The country to get its {@link StateProvinceRegion} list.
	 * @return The {@link StateProvinceRegion} list.
	 */
	protected List<StateProvinceRegion> fetchStateProvinceRegion(Country country)
	{
		InternalResultsResponse<StateProvinceRegion> response =
				getCountryDAC().fetchStateProvinceRegionByCountryCode(country.getCode());
		CommonTestRoutines.assertResultResponse(response);
		return response.getResultsList();
	}

	/**
	 * Compare contact fields.
	 *
	 * @param contactObject the contact object
	 */
	protected void compareContactFields(Contact contactObject)
	{
		InternalResultsResponse<Contact> response =
				getContactDAC().fetchContactById(contactObject.getId());

		for (Contact contactFromDb : response.getResultsList())
		{
			if (contactFromDb.getContactType().equals(contactObject.getContactType()))
			{
				switch (contactFromDb.getContactType())
				{
					case EMAIL_PERSONAL:
					case EMAIL_WORK:

						Assert.assertEquals("Emails need to match", ((Email)contactObject).getEmailAddress(),
								((Email)contactFromDb).getEmailAddress());
						break;

					case OTHER:
					case PHONE_WORK:

						Assert.assertEquals(PHONES_NEED_TO_MATCH, ((Phone)contactObject).getNumber(),
								((Phone)contactFromDb).getNumber());
						Assert.assertEquals(PHONES_NEED_TO_MATCH, ((Phone)contactObject).getCountry().getCode(),
								((Phone)contactFromDb).getCountry().getCode());
						Assert.assertEquals(PHONES_NEED_TO_MATCH, ((Phone)contactObject).getExtension(),
								((Phone)contactFromDb).getExtension());
						break;

					case POSTAL_HOME:
					case POSTAL_WORK:

						Assert.assertEquals(ADDRESSES_NEED_TO_MATCH, ((Address)contactObject).getCityName(),
								((Address)contactFromDb).getCityName());
						Assert.assertEquals(ADDRESSES_NEED_TO_MATCH, ((Address)contactObject).getAddressLine1(),
								((Address)contactFromDb).getAddressLine1());
						Assert.assertEquals(ADDRESSES_NEED_TO_MATCH, ((Address)contactObject).getAddressLine2(),
								((Address)contactFromDb).getAddressLine2());
						Assert.assertEquals(ADDRESSES_NEED_TO_MATCH, ((Address)contactObject).getAddressLine3(),
								((Address)contactFromDb).getAddressLine3());
						Assert.assertEquals(ADDRESSES_NEED_TO_MATCH, ((Address)contactObject).getAddressLine4(),
								((Address)contactFromDb).getAddressLine4());
						Assert.assertEquals(ADDRESSES_NEED_TO_MATCH, ((Address)contactObject).getCountry().getCode(),
								((Address)contactFromDb).getCountry().getCode());
						break;

					case UNKNOWN:
						break;
					default:
						break;
				}
			}
		}
	}

	/**
	 * Compare name fields.
	 *
	 * @param personNameObject the person name object
	 */
	protected void compareNameFields(PersonName personNameObject)
	{
		InternalResultsResponse<PersonName> response =
				getPersonDAC().fetchPersonNameById(personNameObject.getId());

		Assert.assertEquals("One name needs to be retrieved", 1, response.getResultsList().size());

		for (PersonName nameFromDb : response.getResultsList())
		{
			if (nameFromDb.getId().equals(personNameObject.getId()))
			{
				Assert.assertEquals("Other Name needs to match", personNameObject.getOtherName(),
						nameFromDb.getOtherName());
			}
		}
	}

	/**
	 * Compare note fields.
	 *
	 * @param noteObject the note object
	 */
	protected void compareNoteFields(Note noteObject)
	{
		InternalResultsResponse<Note> response =
				getNoteDAC().fetchNoteById(noteObject.getId());

		Assert.assertEquals("One note needs to be retrieved", response.getResultsList().size(), 1);

		for (Note noteFromDb : response.getResultsList())
		{
			if (noteFromDb.getId().equals(noteObject.getId()))
			{
				Assert.assertEquals("NoteText needs to match", noteObject.getNoteText(), noteFromDb.getNoteText());
			}
		}
	}

	/**
	 * Insert a new dummy Security Question in the database.
	 *
	 * @param question the question
	 * @return SecurityQuestion
	 */
	protected SecurityQuestion insertSecurityQuestion(String question)
	{
		SecurityQuestion securityQuestion = new SecurityQuestion();
		securityQuestion.setSecurityQuestionKey(question);
		securityQuestion.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		securityQuestion.setCreateUser(USER_NAME);

		InternalResultsResponse<SecurityQuestion> response =
				getSecurityQuestionDAC().insertSecurityQuestion(securityQuestion);
		CommonTestRoutines.assertResponse(response);

		return securityQuestion;
	}

	/**
	 * Creates the person security answer.
	 *
	 * @param answer the security answer
	 * @param securityQuestion the securityQuestion
	 * @param member the member
	 * @return the person security answer
	 */
	protected PersonSecurityAnswer createPersonSecurityAnswer(String answer, SecurityQuestion securityQuestion,
			Member member)
	{
		PersonSecurityAnswer personSecurityAnswer = new PersonSecurityAnswer();
		personSecurityAnswer.setAnswerText(answer);
		personSecurityAnswer.setParentKey(member.getId());
		personSecurityAnswer.setSecurityQuestion(securityQuestion);
		personSecurityAnswer.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		personSecurityAnswer.setCreateUser(USER_NAME);

		return personSecurityAnswer;
	}

	/**
	 * Insert person security answer.
	 *
	 * @param answer the answer
	 * @param securityQuestion the security question
	 * @param member the member
	 * @return the person security answer
	 */
	protected PersonSecurityAnswer insertPersonSecurityAnswer(String answer, SecurityQuestion securityQuestion,
			Member member)
	{
		PersonSecurityAnswer personSecurityAnswer = createPersonSecurityAnswer(answer, securityQuestion, member);

		InternalResultsResponse<PersonSecurityAnswer> response =
				getSecurityQuestionDAC().insertPersonSecurityAnswer(personSecurityAnswer);
		CommonTestRoutines.assertResponse(response);

		return personSecurityAnswer;
	}

	/**
	 * Insert suspicius activity.
	 *
	 * @param type the type
	 * @return the suspicious activity
	 */
	protected SuspiciousActivity insertSuspiciusActivity(BusinessTypeEnum type)
	{
		SarMaintenanceRequest request = new SarMaintenanceRequest();

		SuspiciousActivity suspiciousActivity =
				CommonTestRoutines.createDummySuspiciousActivity(type);

		switch (type)
		{
			case ORGANIZATION:
				List<Business> orgList = new ArrayList<Business>();
				orgList.add(insertOrganization());
				suspiciousActivity.setBusinessList(orgList);
				break;
			case LOCATION:
				List<Business> locList = new ArrayList<Business>();
				locList.add(insertLocation());
				suspiciousActivity.setBusinessList(locList);
				break;
			case MEMBER:
				List<Person> memberList = new ArrayList<Person>();
				memberList.add(insertMember());
				suspiciousActivity.setPersonList(memberList);
				break;
			case RECIPIENT:
				List<Person> recipientList = new ArrayList<Person>();
				recipientList.add(insertRecipient());
				suspiciousActivity.setPersonList(recipientList);
				break;
			case LIAISON:
				List<Person> liaisonList = new ArrayList<Person>();
				liaisonList.add(insertLiaison());
				suspiciousActivity.setPersonList(liaisonList);
				break;
			default:
				break;
		}
		request.setSuspiciousActivity(suspiciousActivity);

		InternalResponse response = getSuspiciousActivityDAC().insertSuspiciousActivity(request);
		CommonTestRoutines.assertResponse(response);

		return request.getSuspiciousActivity();
	}

	/**
	 * Create a new Member with some dummy informations.
	 *
	 * @param uniqueIndexForMember Unique index for the member.
	 * @return The member.
	 */
	protected Member createMember(int uniqueIndexForMember)
	{
		String uniqueFirstNameChars = "";
		String uniqueLastNameChars = "";

		if (uniqueIndexForMember > 0)
		{
			uniqueFirstNameChars = Integer.toString(uniqueIndexForMember);
			uniqueLastNameChars = Integer.toString(MAX_NUMBER - uniqueIndexForMember);
		}

		Location location = insertLocation();
		Member member = CommonTestRoutines.createDummyMember();
		member.setFirstName(member.getFirstName() + uniqueFirstNameChars);
		member.setLastName(member.getLastName() + uniqueLastNameChars);
		member.setMothersMaidenName(member.getMothersMaidenName() + uniqueLastNameChars);
		member.getEmploymentInfoList().get(0).setLocationId(location.getId());
		member.getEmploymentInfoList().get(0).setOrganizationId(location.getParentOrganizationId());
		member.setPrefix(fetchSuffixPrefix(CodeValueEnum.PREFIX));
		member.setSuffix(fetchSuffixPrefix(CodeValueEnum.SUFFIX));
		member.setParticipantId(Integer.toString(uniqueIndexForMember));
		member.setPinNumber(Integer.toString(uniqueIndexForMember));

		return member;
	}

	/**
	 * Adds the transfer settings to member.
	 *
	 * @param member the member
	 */
	protected void addTransferSettingsToMember(Member member)
	{
		ProductPlan productPlan = insertTemplateProductPlan();
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		transferSetting.setMemberId(member.getId());
		transferSetting.setRecipientId(insertRecipient().getId());
		transferSetting.getEmploymentInfo().setId(member.getEmploymentInfoList().get(0).getId());
		transferSetting.setPlanCategory(productPlan.getPlanCategoryList().get(0));
		transferSetting.setProductPlanApplicability(productPlan.getProductPlanApplicabilityList().get(0));
		member.setModelAction(PersistanceActionEnum.NONE);
		member.setTransferSettingList(Arrays.asList(transferSetting));

		InternalResultsResponse<Member> response = getPersonDAC().updateMember(member);
		CommonTestRoutines.assertResultResponse(response);
	}

}
