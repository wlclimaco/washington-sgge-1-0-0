/**
 * @author Flavio Tosta
 * @fileoverview Domain Model Objects
 *
 */

 var QATModel = function(oParam) {

	var date = new Date();
	if (oParam) {
		this.createUser		= pgsi.settings.userContext.userId;
		this.modifyUser		= pgsi.settings.userContext.userId;
		this.createDateUTC	= date.getTime();
		this.modifyDateUTC  = date.getTime();
	}
};


var Business = function(oParam) {
	if (oParam)
	{
		this.id 											= oParam.id;
		this.name 											= oParam.name;
		this.businessType   								= oParam.businessType;
		this.employerIdentificationNumber 					= oParam.employerIdentificationNumber;
		this.standardIndustrialClassificationCode			= oParam.standardIndustrialClassificationCode;
		this.northAmericanIndustryClassificationSystemCode	= oParam.northAmericanIndustryClassificationSystemCode;
		this.country										= oParam.country;
		this.numberOfEmployees								= oParam.numberOfEmployees;
		this.numberOfMigrantWorkers							= oParam.numberOfMigrantWorkers;
		this.status 	 									= oParam.status;
		this.SDNstatus	 									= oParam.SDNstatus;
		this.noteList 										= oParam.noteList;
		this.contacList 									= oParam.contactList;
		this.version                                        = oParam.version;
	}
};

var Country = function(oParam) {
	if (oParam)
	{
		this.code = oParam.code;
		this.description = oParam.description;
		this.phoneCode = oParam.phoneCode;
	}
};

var StateProvinceRegion = function(oParam) {
	if (oParam)
	{
		this.id = oParam.id;
		this.code = oParam.code;
		this.description = oParam.description;
	}
};

var Contact = function(oParam) {
	if (oParam)
	{
		this.modelAction 		 = oParam.modelAction;
		this.parentKey    		 = oParam.parentKey;
		this.contactType  		 = oParam.contactType;
		this.priority	  		 = oParam.priority
		this.verified 	   		 = oParam.verified;
		this.effectiveStartDate  = oParam.effectiveStartDate;
		this.effectiveEndDate    = oParam.effectiveEndDate;
		this.version             = oParam.version;
	}
};

var Phone = function(oParam) {
	Contact.call(this, oParam);
	if (oParam)
	{
		this.country   = oParam.country;
		this.areaCode  = oParam.areaCode;
		this.number    = oParam.number;
		this.extension = oParam.extension;
		this.version   = oParam.version;
	}
};

Phone.prototype = new Contact();


var Address = function(oParam) {
	Contact.call(this, oParam);
	if (oParam)
	{
		this.addressLine1 = oParam.addressLine1;
		this.addressLine2 = oParam.addressLine2;
		this.addressLine3 = oParam.addressLine3;
		this.addressLine4 = oParam.addressLine4;
		this.cityName     = oParam.cityName;
		this.stateProvinceRegion = oParam.stateProvinceRegion;
		this.country      = oParam.country;
		this.postalCode   = oParam.postal;
		this.version      = oParam.version;
	}
};

Address.prototype = new Contact();

var Location = function(oParam) {
	Business.call(this, oParam);

	if (oParam)
	{
		this.fundsTransferDayLimit = oParam.fundsTransferDayLimit;
		this.batchApprovalDayLimit = oParam.batchApprovalDayLimit;
		this.parentOrganizationId = oParam.parentOrganizationId;
		this.parentOrganizationName = oParam.parentOrganizationName;
	}
};

Location.prototype = new Business();

var Organization = function(oParam) {
	Business.call(this, oParam);

	if (oParam)
	{
		this.id 					=  oParam.id;
		this.numberOfLocations 		=  oParam.numberOfLocations;
		this.isPayrollCentralized   =  oParam.isPayrollCentralized;
		this.dbaName 				=  oParam.dbaName;
		this.documentList 			=  oParam.documentList;
		this.locationList 			=  oParam.locationList;
	}
};

Organization.prototype = new Business();

var Person = function(oParam) {
	if (oParam) {
		this.id                = oParam.id;
		this.prefix            = oParam.prefix
		this.firstName         = oParam.firstName;
		this.middleName        = oParam.middleName;
		this.lastName          = oParam.lastName;
		this.genderValue       = oParam.gender;
		this.suffix            = oParam.suffix;
		this.dateOfBirth   	   = oParam.dateOfBirth;
		this.PEPStatusValue    = oParam.PEPStatus;
		this.personTypeValue   = oParam.personType;
		this.personStatusValue = oParam.personStatus;
		this.contactList       = oParam.contactList;
		this.nameList          = oParam.nameList;
		this.noteList          = oParam.noteList;
		this.risk              = oParam.risk;
	}
};

var Liaison = function(oParam) {
	Person.call(this, oParam);
	if (oParam) {
		this.liaisonType 		  = oParam.liaisonType;
		this.title 		          = oParam.title;
		this.locationId           = oParam.locationId;
		this.socialSecurityNumber = oParam.socialSecurityNumber;

	}
};
Liaison.prototype = new Person();


var Note = function(oParam) {
	if (oParam) {
		this.id 			= oParam.id;
		this.noteText 		= oParam.noteText;
		this.sequenceNumber = oParam.sequenceNumber;
		this.parentKey 		= oParam.parentKey;
		this.parentKeyValue  = oParam.parentKeyType;
		this.modelAction 	= oParam.modelAction;
	}
};
var Document = function(oParam){

	if (oParam) {
		this.id				   = oParam.id;
		this.parentKey		   = oParam.parentKey;
		this.parentKeyValue	   = oParam.parentKeyValue;
		this.documentType	   = oParam.documentType;
		this.keywordText	   = oParam.keywordText;
		this.isActionRequired  = oParam.isActionRequired;
		this.noteText		   = oParam.noteText;
		this.filingStatusValue = oParam.filingStatusValue;
		this.modelAction       = oParam.modelAction;
		this.issueCountry      = oParam.issueCountry;
		this.issueStateProvinceRegion = oParam.issueStateProvinceRegion;
		this.expirationDate    = oParam.expirationDate;
		this.value             = oParam.expirationDate;
	}
};
var Risk = function(oParam){
	if (oParam) {
		this.parentKey      = oParam.parentKey;
		this.parentKeyType  = oParam.parentKeyType;
		this.riskLevelValue = oParam.riskLevelValue;
		this.riskLevelNote  = oParam.riskLevelNote;
		this.modelAction    = oParam.modelAction;
		this.version        = oParam.version;
	}
};

var TransferParticipant = function(oParam){

	Person.call(this, oParam);

	if (oParam) {
		this.participantId       = oParam.participantId;
		this.documentList        = [ oParam.documentList ];
		this.transferSettingList = [ oParam.transferSettingList ];
	}
};

TransferParticipant.prototype = new Person();

var Member = function(oParam){

	TransferParticipant.call(this, oParam);

	if (oParam) {

		this.pinNumber          = oParam.pinNumber;
		this.employmentInfoList = [ oParam.employmentInfoList ];
		this.countryUsageList   = [ oParam.countryUsageList ];
		this.preferredLanguage  = oParam.preferredLanguage;
		this.bestTimeToCall     = oParam.bestTimeToCall;
		this.securityAnswerList = oParam.securityAnswerList;
	}
};

Member.prototype = new TransferParticipant();


var Recipient = function(oParam){

	Member.call(this, oParam);

};
Recipient.prototype = new Member();

var TransferSetting = function(oParam){

	if (oParam) {
		this.account				  = oParam.account;
		this.customFeeList			  = oParam.customFeeList;
		this.cyclesToSkip			  = oParam.cyclesToSkip;
		this.effectiveEndDate		  = oParam.effectiveEndDate;
		this.effectiveStartDate		  = oParam.effectiveStartDate;
		this.employmentInfo			  = oParam.employmentInfo;
		this.id						  = oParam.id;
		this.memberId				  = oParam.memberId;
		this.numberOfCyclesSkipped	  = oParam.numberOfCyclesSkipped;
		this.planCategory			  = oParam.planCategory;
		this.productPlanApplicability = oParam.productPlanApplicability;
		this.recipientId			  = oParam.recipientId;
		this.status					  = oParam.status;
		this.transferAmount           = oParam.transferAmount;
		this.transferType             = oParam.transferType;
	}
};

var EmploymentInfo = function(oParam){

	if (oParam) {
		this.currentPay                = oParam.currentPay;
		this.employeeId				   = oParam.employeeId;
		this.employmentInfoStatusValue = oParam.employmentInfoStatusValue;
		this.hireDate                  = oParam.hireDate;
		this.id                        = oParam.id;
		this.jobTitle 				   = oParam.jobTitle;
		this.locationId                = oParam.locationId;
		this.locationName              = oParam.locationName;
		this.memberId				   = oParam.memberId;
		this.payPerPeriod              = oParam.payPerPeriod;
	}
};


var PlanCategory = function(oParam){
	if (oParam) {
		this.callCreditAllowance = oParam.callCreditAllowance;
		this.feeTierList		 = oParam.feeTierList;
		this.name        		 = oParam.name;
	}
};
var FeeTier = function(oParam){
	if (oParam) {
		this.feeValue;
		this.id;
		this.maximumValue;
		this.minimumValue;
		this.sequenceNumber;
	}
};

var MoneyTransferBatchStatus = function(oParam) {
	if (oParam) {
		this.id = oParam.id;
		this.moneyTransferBatchId = oParam.moneyTransferBatchId;
		this.actionDueDate = oParam.actionDueDate;
		this.status = oParam.status;
		this.modelAction = oParam.modelAction;
	}
};

var MoneyTransferStatus = function(oParam) {
	if (oParam) {
		this.id = oParam.id;
		this.moneyTransferId = oParam.moneyTransferId;
		this.status = oParam.status;
		this.response = oParam.response;
		this.moneyTransferTransaction = oParam.moneyTransferTransaction;
		this.modelAction = oParam.modelAction;
	}
};

var PersonName = function(oParam) {
	if (oParam) {
		this.id = oParam.id;
		this.personId = oParam.personId;
		this.otherName = oParam.otherName;
		this.modelAction = oParam.modelAction;
	}
};

var MoneyTransferBatch = function(oParam) {
	if (oParam) {
		this.id = oParam.id;
		this.key = oParam.key;
		this.location = oParam.location;
		this.moneyTransferList = oParam.moneyTransferList;
		this.noteList = oParam.noteList;
		this.originAmount = oParam.originAmount;
		this.statusList = oParam.statusList;
		this.transferDate = oParam.transferDate;
		this.payrollReceivedDate = oParam.payrollReceivedDate;
		this.payPreparationDate = oParam.payPreparationDate;
		this.releaseUser = oParam.releaseUser;
	}
};

var MoneyTransfer = function(oParam) {
	if (oParam) {
		this.id = oParam.id;
		this.key = oParam.key;
		this.senderAccount = oParam.senderAccount;
		this.recipientAccount = oParam.recipientAccount;
		this.destinationAmount = oParam.destinationAmount;
		this.discountAmount = oParam.discountAmount;
		this.originalFlatFlee = oParam.originalFlatFlee;
		this.originAutomatedClearingHouseFee = oParam.originAutomatedClearingHouseFee;
		this.originCallCreditAmount = oParam.originCallCreditAmount;
		this.foreignExchangeRate = oParam.foreignExchangeRate;
		this.foreignExchangeRateCustomer = oParam.foreignExchangeRateCustomer;
		this.moneyTransferBatchId = oParam.moneyTransferBatchId;
		this.moneyTransferDetail = oParam.moneyTransferDetail;
		this.noteList = oParam.noteList;
		this.originAmount = oParam.originAmount;
		this.paymentType = oParam.paymentType;
		this.spreadPercentage = oParam.spreadPercentage;
		this.statusList = oParam.statusList;
		this.transferSetting = oParam.transferSetting;
		this.confirmationNumber = oParam.confirmationNumber;
		this.fundingDate = oParam.fundingDate;
		this.releaseUser = oParam.releaseUser;
	}
};

var PersonSecurityAnswer = function(oParam) {
	if (oParam) {
		this.id 			  = oParam.id;
		this.answerText		  = oParam.answerText;
		this.securityQuestion = oParam.securityQuestion;
		this.parentKey 		  = oParam.parentKey;
		this.modelAction 	  = oParam.modelAction;
		this.version 		  = oParam.version;
		this.securityQuestion = oParam.securityQuestion;
	}
};

var SecurityQuestion = function(oParam) {
	if (oParam) {
		this.id 			     = oParam.id;
		this.securityQuestionKey = oParam.securityQuestionKey;
		this.version 			 = oParam.version;
		this.modelAction 	  = oParam.modelAction;
	}
};

var FrequencyBasedEventCalendar = function(oParam) {
	if (oParam) {
		this.id 	               = oParam.id;
		this.eventDate  		   = oParam.eventDate;
		this.frequencyBasedEventId = oParam.frequencyBasedEventId;
	}
};

var SuspiciousActivity = function(oParam) {
	if (oParam) {
		this.id							= oParam.id;
		this.businessKey				= oParam.businessKey;
		this.type						= oParam.type;
		this.typeValue					= oParam.typeValue;
		this.summary					= oParam.summary;
		this.detail						= oParam.detail;
		this.activityStartDateTimeUTC	= oParam.activityStartDateTimeUTC;
		this.activityStopDateTimeUTC	= oParam.activityStopDateTimeUTC;
		this.status						= oParam.status;
		this.statusDateTime				= oParam.statusDateTime;
		this.statusReason				= oParam.statusReason;
		this.amountAssociated			= oParam.amountAssociated;
		this.personList					= oParam.personList;
		this.businessList				= oParam.businessList;
		this.modelAction 	            = oParam.modelAction;


	}
};

var ModelCosmeDamiao = function(oParam){

	QATModel.call(this, oParam);

	if (oParam) {
		this.parentId    = oParam.parentId;
		this.type        = oParam.type;
		this.acaoType    = oParam.acaoType;
		this.tabelaEnum  = oParam.tabelaEnum;
		this.statusList  = [ oParam.statusList ];
	}
};
ModelCosmeDamiao.prototype = new QATModel();

TransferParticipant.prototype = new Person();

var Regime = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id 			= oParam.id;
		this.nome 		    = oParam.nome;
		this.descricao      = oParam.descricao;
	}
};
Regime.prototype = new ModelCosmeDamiao();


var Cidade = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id 			= oParam.id;
		this.codigo 		= oParam.codigo;
		this.nome 			= oParam.nome;
		this.cdIBGE 		= oParam.cdIBGE;
		this.estado 		= oParam.estado;
		this.cep 			= oParam.cep;
		this.municipio 		= oParam.municipio;
		this.modelAction 	= oParam.modelAction;
	}
};
Cidade.prototype = new ModelCosmeDamiao();

var Endereco = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.logradouro		= oParam.logradouro;
		this.cidade			= oParam.cidade;
		this.estado			= oParam.estado;
		this.bairro			= oParam.bairro;
		this.numero			= oParam.numero;
		this.cep			= oParam.cep;
		this.enderecoTypeEnumValue = oParam.enderecoTypeEnumValue;
		this.modelAction 	= oParam.modelAction;

	}
};
Endereco.prototype = new ModelCosmeDamiao();

var Telefone = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.ddd			= oParam.ddd;
		this.numero			= oParam.numero;
		this.telefoneTypeEnumValue	= oParam.telefoneTypeEnumValue;
		this.modelAction 	= oParam.modelAction;

	}
};
Telefone.prototype = new ModelCosmeDamiao();

var Email = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id					= oParam.id;
		this.email				= oParam.email;
		this.emailType			= oParam.emailType;
		this.emailTypeEnumValue = oParam.emailTypeEnumValue;
		this.description		= oParam.description;
		this.modelAction 		= oParam.modelAction;

	}
};
Email.prototype = new ModelCosmeDamiao();


var Cnae = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.codigo			= oParam.codigo;
		this.cnae			= oParam.cnae;
		this.descricao		= oParam.descricao;
		this.abreviado		= oParam.abreviado;
		this.modelAction 	= oParam.modelAction;

	}
};
Cnae.prototype = new ModelCosmeDamiao();


var CnaeRel = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.idCnae			= oParam.idCnae;
		this.modelAction 	= oParam.modelAction;

	}
};
CnaeRel.prototype = new ModelCosmeDamiao();

var Pessoa = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.nome			= oParam.nome;
		this.nomePai		= oParam.nomePai;
		this.nomeMae		= oParam.nomeMae;
		this.nomeConjugue	= oParam.nomeConjugue;
		this.estadoCivil	= oParam.estadoCivil;
		this.datanasc		= oParam.datanasc;
		this.foto		    = oParam.foto;
		this.pessoaTypeEnumValue 	= oParam.pessoaTypeEnumValue;
		this.sexo		    = oParam.sexo;
		this.enderecos		= oParam.enderecos;
		this.documentos		= oParam.documentos;
		this.emails		    = oParam.emails;
		this.Telefones		= oParam.Telefones;
		this.bancos		    = oParam.bancos;
		this.formaPagamentoList		= oParam.formaPagamentoList;
		this.condPagList		    = oParam.condPagList;
		this.contatoList		    = oParam.contatoList;
		this.modelAction			= oParam.modelAction;

	}
};
Pessoa.prototype = new ModelCosmeDamiao();


var Socio = function(oParam) {

	Pessoa.call(this, oParam);

	if (oParam) {
		this.cota			= oParam.cota;
		this.cota			= oParam.cota;
		this.porcentagem	= oParam.porcentagem;
		this.modelAction 	= oParam.modelAction;

	}
};
Socio.prototype = new Pessoa();

var Funcionario = function(oParam) {

	Pessoa.call(this, oParam);

	if (oParam) {
		this.matricula   = oParam.matricula;
		this.dataAdm     = oParam.dataAdm;
		this.salarios    = oParam.salarios;
		this.horarios    = oParam.horarios;
		this.beneficios  = oParam.beneficios;
		this.eventosList = oParam.eventosList;
		this.modelAction = oParam.modelAction;

	}
};
Funcionario.prototype = new Pessoa();

var Plano = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.dataInicio		= oParam.dataInicio;
		this.dataFinal		= oParam.dataFinal;
		this.numeroContrato	= oParam.numeroContrato;
		this.produto		= oParam.produto;
		this.modelAction 	= oParam.modelAction;

	}
};
Plano.prototype = new ModelCosmeDamiao();

var Documento = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.documentoType  = oParam.documentoType;
		this.numero         = oParam.numero;
		this.data           = oParam.data;
		this.estado         = oParam.estado;
	}
};
Documento.prototype = new ModelCosmeDamiao();



var Produto = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.produto		= oParam.produto;
		this.cdBarra		= oParam.cdBarra;
		this.modelAction 	= oParam.modelAction;

	}
};
Produto.prototype = new ModelCosmeDamiao();

var Entidade = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.entidadeId		= oParam.entidadeId;
		this.nome			= oParam.nome;
		this.regime			= oParam.regime;
		this.enderecos		= oParam.enderecos;
		this.documentos		= oParam.documentos;
		this.emails			= oParam.emails;
		this.Telefones		= oParam.Telefones;
		this.cnaes			= oParam.cnaes;
		this.modelAction 	= oParam.modelAction;

	}
};
Entidade.prototype = new ModelCosmeDamiao();

var EventoPessoa = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.data		   	= oParam.data;
		this.idEvent       	= oParam.idEvent;
		this.idFunc 		= oParam.idFunc;
		this.modelAction 	= oParam.modelAction;
	}
};
EventoPessoa.prototype = new ModelCosmeDamiao();

var Eventos = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.dataList		= oParam.dataList;
		this.nome		    = oParam.nome;
		this.descricao		= oParam.descricao;
		this.codigo			= oParam.codigo;
		this.Tipo			= oParam.Tipo;
		this.valor			= oParam.valor;
		this.porcentagem	= oParam.porcentagem;
		this.isMensal		= oParam.isMensal;
		this.isSistema		= oParam.isSistema;
		this.noteText		= oParam.noteText;
		this.modelAction 	= oParam.modelAction;
	}
};
Eventos.prototype = new ModelCosmeDamiao();


var Empresa = function(oParam) {

	Entidade.call(this, oParam);

	if (oParam) {
		this.socios			= oParam.socios;
	}
};
Empresa.prototype = new Entidade();



var Filial = function(oParam) {

	Entidade.call(this, oParam);

};
Filial.prototype = new Entidade();

var Deposito = function(oParam) {

	Entidade.call(this, oParam);

};
Deposito.prototype = new Entidade();