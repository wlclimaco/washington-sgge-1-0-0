
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

	/** The id. */
	private Integer id;/** The supermercadoid. */
	private Supermercado supermercadoid;/** The cod barra. */
	private String codBarra;/** The code. */
	private Cadastro marca;/** The menu. */
	private Cadastro menu;/** The submenu. */
	private Cadastro submenu;	/** The trimenu. */
	private Cadastro trimenu;	/** The unimed. */
	private Cadastro unimed;	/** The description. */
	private String nome;	/** The price. */
	private String descricao;	/** The usuario. */
	private String foto;	/** The senha. */
	private List<Tabelapreco> precos;
	private List<Imagem> imagens;

	qat.model.produto = function(_Id, _supermercadoid, _codBarra, _marca,_menu, _submenu,_trimenu, _unimed,_nome, _descricao,_foto, _precos,_imagens)
	{
	    var userContext = new qat.base.model.userContext();
		this.id = _Id;
		this.supermercadoid = _supermercadoid;
		this.codBarra = _codBarra;
		this.marca = _marca;
		this.menu = _menu;
		this.submenu = _submenu;
		this.trimenu = _trimenu;
		this.unimed = _unimed;
		this.nome = _nome;
		this.descricao = _descricao;
		this.foto = _foto;
		this.precos = _precos;
		this.imagens = _imagens;
		this.userId = userContext.userId;
		this.id = userContext.id;
		this.tenant = userContext.tenant;
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

	qat.model.preco = function(_precoid, _idProduto, _supermercadoid, _type,_preco, _promocao, _dateIni, _dateFim)
	{
	    var userContext = new qat.base.model.userContext();
		this.precoid = _precoid;
		this.idProduto = _idProduto;
		this.supermercadoid = _supermercadoid;
		this.type = _type;
		this.preco = _preco;
		this.precopromo = _precopromo;
		this.promocao = _promocao;
		this.dateIni = _dateIni;
		this.dateFim = _dateFim;
		this.userId = userContext.userId;
		this.id = userContext.id;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};

	qat.model.cadastro = function(_Id, _type, _nome, _descricao,_controlAcess)
	{
	    var userContext = new qat.base.model.userContext();
		this.id = _Id;
		this.type = _type;
		this.nome = _nome;
		this.descricao = _descricao;
		this.userId = userContext.userId;
		this.id = userContext.id;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};

	qat.model.cidade = function(_Id,_cidade, _estado)
	{
	    var userContext = new qat.base.model.userContext();
		this.id = _Id;
		this.cidade = _cidade;
		this.estado = _estado;
		this.userId = userContext.userId;
		this.id = userContext.id;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};

	qat.model.cliente = function(_Id,_type, _nome,_sobrenome,_usuario,_senha,_email,_documento,_endereco)
	{
	    var userContext = new qat.base.model.userContext();
		this.id = _Id;
		this.type = _type;
		this.nome = _nome;
		this.sobrenome = _sobrenome;
		this.usuario = _usuario;
		this.senha = _senha;
		this.email = _email;
		this.documentos = _documento;
		this.enderecos = _endereco;
		this.userId = userContext.userId;
		this.id = userContext.id;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};

	qat.model.documento = function(_documentoid,_id, _rgInc,_cpfCnpj,_nome,_razao,_tabela,_dateNascimento)
	{
	    var userContext = new qat.base.model.userContext();
		this.documentoid = _documentoid;
		this.id = _id;
		this.rgInc = _rgInc;
		this.cpfCnpj = _cpfCnpj;
		this.nome = _nome;
		this.razao = _razao;
		this.tabela = _tabela;
		this.dateNascimento = _dateNascimento;
		this.userId = userContext.userId;
		this.id = userContext.id;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};

	qat.model.documento = function(_documentoid,_id, _rgInc,_cpfCnpj,_nome,_razao,_tabela,_dateNascimento)
	{
	    var userContext = new qat.base.model.userContext();
		this.documentoid = _documentoid;
		this.id = _id;
		this.rgInc = _rgInc;
		this.cpfCnpj = _cpfCnpj;
		this.nome = _nome;
		this.razao = _razao;
		this.tabela = _tabela;
		this.dateNascimento = _dateNascimento;
		this.userId = userContext.userId;
		this.id = userContext.id;
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
		this.id = userContext.id;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;
	};




