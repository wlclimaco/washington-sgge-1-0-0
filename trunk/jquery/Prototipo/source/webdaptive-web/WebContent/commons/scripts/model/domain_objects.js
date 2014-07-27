
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

	qat.model.cidade = function(_Id,_cidade, _estado)
	{
	    var userContext = new qat.base.model.userContext();
		this.cidadeId = _Id;
		this.cidade = _cidade;
		this.estado = _estado;
		this.userId = userContext.userId;
		this.id = userContext.id;
		this.tenant = userContext.tenant;
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


