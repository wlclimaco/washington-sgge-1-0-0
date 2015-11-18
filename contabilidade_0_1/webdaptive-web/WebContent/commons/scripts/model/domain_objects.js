
	//County Object
	qat.model.county = function(_countyId, _countyDesc)
	{
		this.modelAction = null;
		this.id = _countyId;
		this.description = _countyDesc;
	};

	//Procedure Object
	qat.model.procedure = function(_version, _procId, _procCode, _procDesc, _procPrice)
	{
		this.modelAction = null;
		this.version = _version;
		this.id = _procId;
		this.code = _procCode;
		this.description = _procDesc;
		this.price = _procPrice;
	};

	qat.model.produto = function(_Id, _supermercadoid, _codBarra, _marca,_menu, _submenu,_trimenu, _unimed,_nome, _descricao,_foto,_precos, _imagens,_tabela)
	{
	    var userContext = new qat.base.model.userContext();
		this.id = parseInt(_Id);
		this.codBarra = _codBarra;
		this.marca = {id:parseInt(_marca),userId:'rod'};
		this.menu = {id:parseInt(_menu),userId:'rod'};
		this.embalagem = {id:parseInt(_unimed),userId:'rod'};
		this.nome = _nome;
		this.descricao = _descricao;
		this.foto = _foto;
		this.precos = _precos;
		this.imagens = _imagens;
		this.tabela  = _tabela
		this.userId = userContext.userId;
	//	this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};

	qat.model.imagem = function(_Id, _local, _nome, _fotoId,_tableEnum)
	{
	    var userContext = new qat.base.model.userContext();
		this.id = _Id;
		this.local = _local;
		this.nome = _nome;
		this.fotoId = _fotoId;
		this.tableEnum = _tableEnum;
		this.userId = userContext.userId;
		this.id = userContext.id;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};

	qat.model.preco = function(_precoid, _idProduto, _supermercadoid, _type,_preco, _promocao,_precopromo, _dateIni, _dateFim)
	{
	    var userContext = new qat.base.model.userContext();
		this.precoid = _precoid;
		this.idProduto = _idProduto;
		this.dataCreate = null;
		this.supermercadoid = new qat.model.supermercado(parseInt(_supermercadoid),null, null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
		this.preco = parseFloat(_preco);
		this.precopromo =  parseFloat(_precopromo);
		if(_promocao == "true")
			this.promocao = 1
		else
			this.promocao = 2;

		this.userId = userContext.userId;
	//	this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};

	qat.model.cadastro = function(_Id, _type, _nome, _descricao)
	{
	    var userContext = new qat.base.model.userContext();
		this.id = _Id;
		this.type = _type;
		this.nome = _nome;
		if((_descricao == null)||(_descricao == ""))
		this.descricao =_nome
		else
		this.descricao = _descricao;
		this.userId = userContext.userId;
		this.userRole = userContext.userRole;
	};

	qat.model.cliente = function(_Id,_type, _nome,_sobrenome,_usuario,_senha,_email,_enderecoid,_eid,_endereco,_logradouro,_bairro,_estado,_cidade,_numero,_cep,_nome,_complemento,_documenroid,_did,_rgInc,_cpfCnpj,_razao,_dateNascimento)
	{
	    var userContext = new qat.base.model.userContext();
		this.clienteid = _Id;
		this.type = 1;
		this.nome = _nome;
		this.sobrenome = _sobrenome;
		this.usuario = _usuario;
		this.senha = _senha;
		this.email = _email;
		this.enderecos  = [new qat.model.endereco (_enderecoid,_eid, _logradouro,_bairro,_estado,_cidade,_numero,_cep,_nome,_complemento,1)];
		this.documentos = [new qat.model.documento(_documenroid,_did, _rgInc,_cpfCnpj,_razao,1,_dateNascimento)];
		this.userId = userContext.userId;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};

	qat.model.documento = function(_documentoid,_id, _rgInc,_cpfCnpj,_razao,_tabela,_dateNascimento)
	{
	    var userContext = new qat.base.model.userContext();
		this.documentoid = _documentoid ;
		this.id = _id ;
		this.rgInc = _rgInc ;
		this.cpfCnpj = _cpfCnpj ;
		this.razao = _razao ;
		this.tabela = _tabela ;
		a = new Date(_dateNascimento);
		this.dateNascimento = a ;
		this.userId = userContext.userId;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};


	qat.model.endereco = function(_enderecoid,_id, _logradouro,_bairro,_estado,_cidade,_numero,_cep,_nome,_complemento,_tabela)
	{
	    var userContext = new qat.base.model.userContext();
		this.enderecoid = _enderecoid;
		this.id = _id;
		this.logradouro = _logradouro;
		this.bairro = _bairro;
		this.estado = _estado;
		this.cidade = _cidade;
		this.numero = _numero;
		this.cep = _cep;
		this.nome = _nome;
		this.complemento = _complemento;
		this.tabela = _tabela;
		this.userId = userContext.userId;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};

	qat.model.supermercado = function(_supermercadoid,_usuario, _email,_site,_usuario,_senha,_enderecoid,_eid,_logradouro,_bairro,_estado,_cidade,_numero,_cep,_nome,_complemento,_documenroid,_did,_rgInc,_cpfCnpj,_razao,_dateNascimento)
	{

	    var userContext = new qat.base.model.userContext();
		this.superId = _supermercadoid;
		this.usuario = _usuario;
		this.email = _email;
		this.site = _site;
		this.senha = _senha;
		this.enderecos = [new qat.model.endereco (_enderecoid,_eid, _logradouro,_bairro,_estado,_cidade,_numero,_cep,_nome,_complemento,1)];
		this.documentos = [new qat.model.documento(_documenroid,_did, _rgInc,_cpfCnpj,_razao,1,_dateNascimento)];
		this.userId = userContext.userId;
		//this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};

	qat.model.unimed = function(_Id,_nome, _sigla)
	{
	    var userContext = new qat.base.model.userContext();
		this.id = _Id;
		this.nome   = _nome;
		this.sigla  = _sigla;
		this.userId = userContext.userId;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};

	qat.model.embalagem = function(_Id,_nome, _qnt,_IdUni,_nomeU, _sigla)
	{
	    var userContext = new qat.base.model.userContext();
		this.id = _Id;
		this.nome = _nome;
		this.qnt = _qnt;
		this.unimedid = new qat.model.unimed(_IdUni,_nome, _sigla,7);
		this.userId = userContext.userId;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};


	//==========================novo

/*
 qat.model.QATModel = function(oParam) {

	var date = new Date();
	if (oParam) {
		this.createUser		= pgsi.settings.userContext.userId;
		this.modifyUser		= pgsi.settings.userContext.userId;
		this.createDateUTC	= date.getTime();
		this.modifyDateUTC  = date.getTime();
	}
};
qat.model.ModelCosmeDamiao = function(oParam){

	qat.model.QATModel.call(this, oParam);

	if (oParam) {
		this.parentId    = oParam.parentId;
		this.type        = oParam.type;
		this.acaoType    = oParam.acaoType;
		this.tabelaEnum  = oParam.tabelaEnum;
		this.statusList  = [ oParam.statusList ];
	}
};
qat.model.ModelCosmeDamiao.prototype = new qat.model.QATModel();

qat.model.qat.model.Cidade = function(oParam) {

	//qat.model.ModelCosmeDamiao.call(this, oParam);

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
//qat.model.Cidade.prototype = new qat.model.ModelCosmeDamiao();

/*
 qat.model.Business = function(oParam) {
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

 qat.model.Country = function(oParam) {
	if (oParam)
	{
		this.code = oParam.code;
		this.description = oParam.description;
		this.phoneCode = oParam.phoneCode;
	}
};

 qat.model.StateProvinceRegion = function(oParam) {
	if (oParam)
	{
		this.id = oParam.id;
		this.code = oParam.code;
		this.description = oParam.description;
	}
};

 qat.model.Contact = function(oParam) {
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

 qat.model.Phone = function(oParam) {
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


 qat.model.Address = function(oParam) {
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

 qat.model.Location = function(oParam) {
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

 qat.model.Organization = function(oParam) {
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

 qat.model.Person = function(oParam) {
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

 qat.model.Liaison = function(oParam) {
	Person.call(this, oParam);
	if (oParam) {
		this.liaisonType 		  = oParam.liaisonType;
		this.title 		          = oParam.title;
		this.locationId           = oParam.locationId;
		this.socialSecurityNumber = oParam.socialSecurityNumber;

	}
};
Liaison.prototype = new Person();


 qat.model.Note = function(oParam) {
	if (oParam) {
		this.id 			= oParam.id;
		this.noteText 		= oParam.noteText;
		this.sequenceNumber = oParam.sequenceNumber;
		this.parentKey 		= oParam.parentKey;
		this.parentKeyValue  = oParam.parentKeyType;
		this.modelAction 	= oParam.modelAction;
	}
};
 qat.model.Document = function(oParam){

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
 qat.model.Risk = function(oParam){
	if (oParam) {
		this.parentKey      = oParam.parentKey;
		this.parentKeyType  = oParam.parentKeyType;
		this.riskLevelValue = oParam.riskLevelValue;
		this.riskLevelNote  = oParam.riskLevelNote;
		this.modelAction    = oParam.modelAction;
		this.version        = oParam.version;
	}
};

 qat.model.TransferParticipant = function(oParam){

	Person.call(this, oParam);

	if (oParam) {
		this.participantId       = oParam.participantId;
		this.documentList        = [ oParam.documentList ];
		this.transferSettingList = [ oParam.transferSettingList ];
	}
};

TransferParticipant.prototype = new Person();

 qat.model.Member = function(oParam){

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


 qat.model.Recipient = function(oParam){

	Member.call(this, oParam);

};
Recipient.prototype = new Member();

 qat.model.TransferSetting = function(oParam){

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

 qat.model.EmploymentInfo = function(oParam){

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


 qat.model.PlanCategory = function(oParam){
	if (oParam) {
		this.callCreditAllowance = oParam.callCreditAllowance;
		this.feeTierList		 = oParam.feeTierList;
		this.name        		 = oParam.name;
	}
};
 qat.model.FeeTier = function(oParam){
	if (oParam) {
		this.feeValue;
		this.id;
		this.maximumValue;
		this.minimumValue;
		this.sequenceNumber;
	}
};

 qat.model.MoneyTransferBatchStatus = function(oParam) {
	if (oParam) {
		this.id = oParam.id;
		this.moneyTransferBatchId = oParam.moneyTransferBatchId;
		this.actionDueDate = oParam.actionDueDate;
		this.status = oParam.status;
		this.modelAction = oParam.modelAction;
	}
};

 qat.model.MoneyTransferStatus = function(oParam) {
	if (oParam) {
		this.id = oParam.id;
		this.moneyTransferId = oParam.moneyTransferId;
		this.status = oParam.status;
		this.response = oParam.response;
		this.moneyTransferTransaction = oParam.moneyTransferTransaction;
		this.modelAction = oParam.modelAction;
	}
};

 qat.model.PersonName = function(oParam) {
	if (oParam) {
		this.id = oParam.id;
		this.personId = oParam.personId;
		this.otherName = oParam.otherName;
		this.modelAction = oParam.modelAction;
	}
};

 qat.model.MoneyTransferBatch = function(oParam) {
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

 qat.model.MoneyTransfer = function(oParam) {
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

 qat.model.PersonSecurityAnswer = function(oParam) {
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

 qat.model.SecurityQuestion = function(oParam) {
	if (oParam) {
		this.id 			     = oParam.id;
		this.securityQuestionKey = oParam.securityQuestionKey;
		this.version 			 = oParam.version;
		this.modelAction 	  = oParam.modelAction;
	}
};

 qat.model.FrequencyBasedEventCalendar = function(oParam) {
	if (oParam) {
		this.id 	               = oParam.id;
		this.eventDate  		   = oParam.eventDate;
		this.frequencyBasedEventId = oParam.frequencyBasedEventId;
	}
};

 qat.model.SuspiciousActivity = function(oParam) {
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

 qat.model.ModelCosmeDamiao = function(oParam){

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

 qat.model.Regime = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id 			= oParam.id;
		this.nome 		    = oParam.nome;
		this.descricao      = oParam.descricao;
	}
};
Regime.prototype = new ModelCosmeDamiao();


 qat.model.qat.model.Cidade = function(oParam) {

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
qat.model.Cidade.prototype = new ModelCosmeDamiao();

 qat.model.Endereco = function(oParam) {

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

 qat.model.Telefone = function(oParam) {

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

 qat.model.Email = function(oParam) {

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


 qat.model.Cnae = function(oParam) {

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


 qat.model.CnaeRel = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.idCnae			= oParam.idCnae;
		this.modelAction 	= oParam.modelAction;

	}
};
CnaeRel.prototype = new ModelCosmeDamiao();

 qat.model.Pessoa = function(oParam) {

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


 qat.model.Socio = function(oParam) {

	Pessoa.call(this, oParam);

	if (oParam) {
		this.cota			= oParam.cota;
		this.cota			= oParam.cota;
		this.porcentagem	= oParam.porcentagem;
		this.modelAction 	= oParam.modelAction;

	}
};
Socio.prototype = new Pessoa();

 qat.model.Funcionario = function(oParam) {

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

 qat.model.Plano = function(oParam) {

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

 qat.model.Documento = function(oParam) {

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



 qat.model.Produto = function(oParam) {

	ModelCosmeDamiao.call(this, oParam);

	if (oParam) {
		this.id				= oParam.id;
		this.produto		= oParam.produto;
		this.cdBarra		= oParam.cdBarra;
		this.modelAction 	= oParam.modelAction;

	}
};
Produto.prototype = new ModelCosmeDamiao();

 qat.model.Entidade = function(oParam) {

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

 qat.model.EventoPessoa = function(oParam) {

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

 qat.model.Eventos = function(oParam) {

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


 qat.model.Empresa = function(oParam) {

	Entidade.call(this, oParam);

	if (oParam) {
		this.socios			= oParam.socios;
	}
};
Empresa.prototype = new Entidade();



 qat.model.Filial = function(oParam) {

	Entidade.call(this, oParam);

};
Filial.prototype = new Entidade();

 qat.model.Deposito = function(oParam) {

	Entidade.call(this, oParam);

};
Deposito.prototype = new Entidade();
*/

