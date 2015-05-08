/**
 * @author Flavio Tosta
 * @fileoverview Request Model Objects
 */


/** ##########  Request ##############*/
var Request = function(oParam)
{
	this.userContext = pgsi.settings.userContext;
}

var InquiryRequest = function(oParam)
{
	Request.call(this, oParam);

	if (oParam)
	{
		this.startRow 		  = oParam.startRow;
		this.endRow 		  = oParam.endRow;
		this.pageSize 		  = oParam.pageSize;
		this.startPage 		  = oParam.startPage;
		this.sortExpressions  = oParam.sortExpressions;
		this.preQueryCount    = oParam.preQueryCount;
		this.maxPreQueryCount = oParam.maxPreQueryCount;
	}
};

InquiryRequest.prototype = new Request();

var PagedInquiryRequest = function(oParam)
{
	InquiryRequest.call(this, oParam);

	if (oParam)
	{
		this.parentId	 = oParam.parentId;
	}
};

/** ##########  CustomizationRequest ##############*/
var CustomizationRequest = function (oParam)
{
	InquiryRequest.call(this, oParam);

	if (oParam)
	{
		this.customizationCriteria = oParam.customizationCriteria;
	}
};
CustomizationRequest.prototype = new InquiryRequest();


PagedInquiryRequest.prototype = new InquiryRequest();

var RecipientInquiryRequest = function(oParam) {
	PagedInquiryRequest.call(this, oParam);

	if (oParam) {
		this.criteria = oParam.criteria;
	}
};

RecipientInquiryRequest.prototype = new PagedInquiryRequest();

var MemberInquiryRequest = function(oParam){
	PagedInquiryRequest.call(this, oParam);

	if (oParam) {
		this.criteria = oParam.criteria;
	}
};

MemberInquiryRequest.prototype = new PagedInquiryRequest();

var MaintenanceRequest = function(oParam)
{
	Request.call(this, oParam);
};

MaintenanceRequest.prototype = new Request();

var LocationMaintenanceRequest = function(oParam)
{
	MaintenanceRequest.call(this, oParam);
	if (oParam)
	{
		this.location = oParam.location;
	}
};

LocationMaintenanceRequest.prototype = new MaintenanceRequest();

var OrganizationMaintenanceRequest = function(oParam)
{
	MaintenanceRequest.call(this, oParam);
	if (oParam)
	{
		this.organization = oParam.organization;
	}
}

OrganizationMaintenanceRequest.prototype = new MaintenanceRequest();


var NoteMaintenanceRequest = function(oParam)
{
	MaintenanceRequest.call(this, oParam);
	if (oParam)
	{
		this.note = oParam.note;
	}
};

NoteMaintenanceRequest.prototype = new MaintenanceRequest();

var DocumentMaintenanceRequest = function(oParam)
{
	MaintenanceRequest.call(this, oParam);
	if (oParam)
	{
		this.document = oParam.document;
	}
};

DocumentMaintenanceRequest.prototype = new MaintenanceRequest();

var RiskMaintenanceRequest = function(oParam)
{
	MaintenanceRequest.call(this, oParam);
	if (oParam)
	{
		this.risk = oParam.risk;
	}
};

RiskMaintenanceRequest.prototype = new MaintenanceRequest();

var LiaisonRequest = function(oParam)
{
	Request.call(this, oParam);

	if (oParam)
	{
		this.liaison	 = oParam.liaison;
	}
};
InquiryRequest.prototype = new Request();

var MemberMaintenanceRequest = function(oParam)
{
	MaintenanceRequest.call(this, oParam);

	if (oParam)
	{
		this.member	 = oParam.member;
	}
};
MemberMaintenanceRequest.prototype = new MaintenanceRequest();

var RecipientMaintenanceRequest = function(oParam)
{
	MaintenanceRequest.call(this, oParam);
	if (oParam)
	{
		this.recipient	 = oParam.recipient;
	}
};
RecipientMaintenanceRequest.prototype = new MaintenanceRequest();

var MoneyTransferBatchInquiryRequest = function(oParam){

	InquiryRequest.call(this, oParam);

	if (oParam) {
		this.criteria = oParam.criteria;
	}
};

MoneyTransferBatchInquiryRequest.prototype = new PagedInquiryRequest();

var MoneyTransferBatchStatusMaintenanceRequest = function(oParam) {

	MaintenanceRequest.call(this, oParam);

	if (oParam) {
		this.moneyTransferBatchStatusList = oParam.moneyTransferBatchStatusList;
		this.note = oParam.note;
	}

};

MoneyTransferBatchStatusMaintenanceRequest.prototype = new MaintenanceRequest();

var MoneyTransferInquiryRequest = function(oParam){

	InquiryRequest.call(this, oParam);

	if (oParam) {
		this.criteria = oParam.criteria;
	}
};

MoneyTransferInquiryRequest.prototype = new PagedInquiryRequest();


var MoneyTransferMaintenanceRequest = function(oParam) {

	MaintenanceRequest.call(this, oParam);

	if (oParam) {
		this.moneyTransferList = oParam.moneyTransferList;
	}
};

MoneyTransferMaintenanceRequest.prototype = new MaintenanceRequest();

var MoneyTransferStatusMaintenanceRequest = function(oParam) {

	MaintenanceRequest.call(this, oParam);

	if (oParam) {
		this.moneyTransferStatusList = oParam.moneyTransferStatusList;
		this.note					 = oParam.note;
	}
};

MoneyTransferStatusMaintenanceRequest.prototype = new MaintenanceRequest();


var SdnStatusHistoryRequest = function(oParam) {

	Request.call(this, oParam);

	if (oParam) {
		this.matchType         = oParam.matchType;
		this.sdnStatusHistory  = oParam.sdnStatusHistory;
	}
};

SdnStatusHistoryRequest.prototype = new Request();

var MoneyTransferBatchMaintenanceRequest = function(oParam) {

	MaintenanceRequest.call(this, oParam);

	if (oParam) {
		this.moneyTransferBatch = oParam.moneyTransferBatch
	}

};

MoneyTransferBatchMaintenanceRequest.prototype = new MaintenanceRequest();

var SdnStatusHistoryInquiryRequest = function(oParam) {

	InquiryRequest.call(this, oParam);

	if (oParam) {
		this.criteria = oParam.criteria
	}

};

SdnStatusHistoryInquiryRequest.prototype = new InquiryRequest();

var PayerInquiryRequest = function(oParam) {
	PagedInquiryRequest.call(this, oParam);

	if (oParam) {
		this.criteria = oParam.criteria;
	}
};

PayerInquiryRequest.prototype = new PagedInquiryRequest();


var MoneyTransferBatchCreateRequest = function(oParam)
{
	Request.call(this, oParam);

	if (oParam)
	{
		this.location = oParam.location;
		this.paymentPreparationDate = oParam.paymentPreparationDate;
		this.payDate = oParam.payDate;
	}
};

MoneyTransferBatchCreateRequest.prototype = new Request();


var MoneyTransferCreateRequest = function(oParam){
	Request.call(this, oParam);

	if (oParam) {
		this.moneyTransferBatch = oParam.moneyTransferBatch;
		this.transferSetting = oParam.transferSetting;
	}
};

MoneyTransferCreateRequest.prototype = new Request();

var SarMaintenanceRequest = function(oParam) {

	MaintenanceRequest.call(this, oParam);

	if (oParam) {
		this.suspiciousActivity = oParam.suspiciousActivity;
	}
};

SarMaintenanceRequest.prototype = new MaintenanceRequest();

var SarInquiryRequest  = function(oParam){

	Request.call(this, oParam);

	if (oParam) {
		this.criteria = oParam.criteria;
	}
};

SarInquiryRequest.prototype = new Request();


var PayerCountryInquiryRequest  = function(oParam){

	Request.call(this, oParam);

	if (oParam) {
		this.criteria = oParam.criteria;
	}
};

PayerCountryInquiryRequest.prototype = new Request();



var PayerStateProvinceRegionInquiryRequest  = function(oParam){

	Request.call(this, oParam);

	if (oParam) {
		this.criteria = oParam.criteria;
	}
};

PayerStateProvinceRegionInquiryRequest.prototype = new Request();