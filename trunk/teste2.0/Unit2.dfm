object DataModule2: TDataModule2
  OldCreateOrder = False
  Left = 70
  Top = 155
  Height = 671
  Width = 908
  object Database1: TDatabase
    AliasName = 'laluna'
    Connected = True
    DatabaseName = 'laluna1'
    LoginPrompt = False
    SessionName = 'Default'
    Left = 40
    Top = 16
  end
  object tblprodutos: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'PRODUTOS.DBF'
    Left = 408
    Top = 16
    object tblprodutosCODPRO: TFloatField
      DisplayWidth = 12
      FieldName = 'CODPRO'
    end
    object tblprodutosPRODUTO: TStringField
      DisplayWidth = 32
      FieldName = 'PRODUTO'
      Size = 15
    end
    object tblprodutosREF: TStringField
      DisplayWidth = 18
      FieldName = 'REF'
      Size = 15
    end
    object tblprodutosCLASS: TStringField
      DisplayWidth = 12
      FieldName = 'CLASS'
      Size = 10
    end
    object tblprodutosUNIDADE: TStringField
      DisplayWidth = 10
      FieldName = 'UNIDADE'
      Size = 3
    end
    object tblprodutosLOCAL: TStringField
      DisplayWidth = 12
      FieldName = 'LOCAL'
      Size = 5
    end
    object tblprodutosCODIND: TFloatField
      DisplayWidth = 12
      FieldName = 'CODIND'
    end
    object tblprodutosINDICE: TStringField
      DisplayWidth = 7
      FieldName = 'INDICE'
      Size = 6
    end
    object tblprodutosVALOR: TFloatField
      DisplayWidth = 12
      FieldName = 'VALOR'
    end
    object tblprodutosCODCLI: TFloatField
      DisplayWidth = 12
      FieldName = 'CODCLI'
    end
    object tblprodutosRAZAO: TStringField
      DisplayWidth = 48
      FieldName = 'RAZAO'
      Size = 40
    end
    object tblprodutosAQUIS: TFloatField
      DisplayWidth = 12
      FieldName = 'AQUIS'
    end
    object tblprodutosICMS: TFloatField
      DisplayWidth = 12
      FieldName = 'ICMS'
    end
    object tblprodutosIPI: TFloatField
      DisplayWidth = 12
      FieldName = 'IPI'
    end
    object tblprodutosFINAN: TFloatField
      DisplayWidth = 12
      FieldName = 'FINAN'
    end
    object tblprodutosFRETE: TFloatField
      DisplayWidth = 12
      FieldName = 'FRETE'
    end
    object tblprodutosCUSTO: TFloatField
      DisplayWidth = 12
      FieldName = 'CUSTO'
    end
    object tblprodutosPRAZO: TSmallintField
      DisplayWidth = 12
      FieldName = 'PRAZO'
    end
    object tblprodutosPRECOVENDA: TFloatField
      FieldName = 'PRECOVENDA'
    end
    object tblprodutosSTATUS: TStringField
      FieldName = 'STATUS'
      Size = 1
    end
    object tblprodutosESTOQUE: TFloatField
      FieldName = 'ESTOQUE'
    end
    object tblprodutosESTOQUEANT: TFloatField
      FieldName = 'ESTOQUEANT'
    end
  end
  object tblClientes: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'CLIENTE.DBF'
    Left = 264
    Top = 16
    object tblClientesCODCLI: TFloatField
      DisplayWidth = 12
      FieldName = 'CODCLI'
    end
    object tblClientesSITUACAO: TStringField
      DisplayWidth = 12
      FieldName = 'SITUACAO'
      Size = 10
    end
    object tblClientesSTATUS: TStringField
      DisplayWidth = 18
      FieldName = 'STATUS'
      Size = 10
    end
    object tblClientesRAZAO: TStringField
      DisplayWidth = 48
      FieldName = 'RAZAO'
      Size = 40
    end
    object tblClientesCOM: TStringField
      DisplayWidth = 5
      FieldName = 'COM'
      Size = 3
    end
    object tblClientesSIGLA: TStringField
      DisplayWidth = 7
      FieldName = 'SIGLA'
      Size = 6
    end
    object tblClientesFANTASIA: TStringField
      DisplayWidth = 48
      FieldName = 'FANTASIA'
      Size = 40
    end
    object tblClientesCGC: TStringField
      DisplayWidth = 17
      FieldName = 'CGC'
      Size = 14
    end
    object tblClientesINCR: TStringField
      DisplayWidth = 17
      FieldName = 'INCR'
      Size = 14
    end
    object tblClientesENDERECO: TStringField
      DisplayWidth = 48
      FieldName = 'ENDERECO'
      Size = 40
    end
    object tblClientesBAIRRO: TStringField
      DisplayWidth = 18
      FieldName = 'BAIRRO'
      Size = 15
    end
    object tblClientesMUNICIPIO: TStringField
      DisplayWidth = 24
      FieldName = 'MUNICIPIO'
    end
    object tblClientesUF: TStringField
      DisplayWidth = 3
      FieldName = 'UF'
      Size = 2
    end
    object tblClientesCEP: TStringField
      DisplayWidth = 10
      FieldName = 'CEP'
      Size = 8
    end
    object tblClientesFONE: TStringField
      DisplayWidth = 16
      FieldName = 'FONE'
      Size = 13
    end
    object tblClientesFONEFAX: TStringField
      DisplayWidth = 16
      FieldName = 'FONEFAX'
      Size = 13
    end
    object tblClientesFONECEL: TStringField
      DisplayWidth = 16
      FieldName = 'FONECEL'
      Size = 13
    end
    object tblClientesCONTATO: TStringField
      DisplayWidth = 27
      FieldName = 'CONTATO'
      Size = 22
    end
    object tblClientesEMAIL: TStringField
      DisplayWidth = 36
      FieldName = 'EMAIL'
      Size = 30
    end
    object tblClientesSITE: TStringField
      DisplayWidth = 48
      FieldName = 'SITE'
      Size = 40
    end
    object tblClientesOBS: TMemoField
      DisplayWidth = 12
      FieldName = 'OBS'
      BlobType = ftMemo
      Size = 1
    end
  end
  object tblEMPREGAD: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'EMPREGAD.DBF'
    Left = 336
    Top = 16
    object tblEMPREGADCODEMP: TFloatField
      DisplayWidth = 12
      FieldName = 'CODEMP'
    end
    object tblEMPREGADNOME: TStringField
      DisplayWidth = 24
      FieldName = 'NOME'
    end
    object tblEMPREGADCARGO: TStringField
      DisplayWidth = 18
      FieldName = 'CARGO'
      Size = 15
    end
    object tblEMPREGADSIGLA: TStringField
      DisplayWidth = 11
      FieldName = 'SIGLA'
      Size = 5
    end
    object tblEMPREGADCOMISSAO: TFloatField
      DisplayWidth = 12
      FieldName = 'COMISSAO'
    end
  end
  object tblENDERECO: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'ENDERECO.DBF'
    Left = 112
    Top = 16
    object tblENDERECOCODCLI: TFloatField
      FieldName = 'CODCLI'
    end
    object tblENDERECOCODIGO: TFloatField
      FieldName = 'CODIGO'
    end
    object tblENDERECOSITUACAO: TStringField
      FieldName = 'SITUACAO'
      Size = 10
    end
    object tblENDERECORAZAO: TStringField
      FieldName = 'RAZAO'
      Size = 40
    end
    object tblENDERECOENDERECO: TStringField
      FieldName = 'ENDERECO'
      Size = 40
    end
    object tblENDERECOBAIRRO: TStringField
      FieldName = 'BAIRRO'
      Size = 15
    end
    object tblENDERECOMUNICIPIO: TStringField
      FieldName = 'MUNICIPIO'
    end
    object tblENDERECOUF: TStringField
      FieldName = 'UF'
      Size = 2
    end
    object tblENDERECOCEP: TStringField
      FieldName = 'CEP'
      Size = 8
    end
    object tblENDERECOFONE: TStringField
      FieldName = 'FONE'
      Size = 13
    end
    object tblENDERECOFONEFAX: TStringField
      FieldName = 'FONEFAX'
      Size = 13
    end
    object tblENDERECOFLAG_EXCL: TStringField
      FieldName = 'FLAG_EXCL'
      Size = 1
    end
  end
  object tblLOJAS: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'LOJAS.DBF'
    Left = 192
    Top = 16
    object tblLOJASCODLOJ: TFloatField
      FieldName = 'CODLOJ'
    end
    object tblLOJASLOJA: TStringField
      FieldName = 'LOJA'
      Size = 15
    end
    object tblLOJASSIGLA: TStringField
      FieldName = 'SIGLA'
      Size = 6
    end
  end
  object dsCLIENTES: TDataSource
    DataSet = tblClientes
    Left = 264
    Top = 72
  end
  object dsEMPREGAD: TDataSource
    DataSet = tblEMPREGAD
    Left = 336
    Top = 72
  end
  object dsENDERECO: TDataSource
    DataSet = tblENDERECO
    Left = 112
    Top = 72
  end
  object dsLOJAS: TDataSource
    DataSet = tblLOJAS
    Left = 192
    Top = 72
  end
  object dsPRODUTOS: TDataSource
    DataSet = tblprodutos
    Left = 408
    Top = 72
  end
  object QryProdutos: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'SELECT *FROM PRODUTOS')
    Left = 104
    Top = 160
  end
  object qryprodutos1: TQuery
    DatabaseName = 'laluna1'
    Left = 168
    Top = 160
  end
  object qrypreco: TQuery
    Left = 224
    Top = 160
  end
  object qryclientes: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'SELECT *FROM CLIENTE')
    Left = 272
    Top = 160
  end
  object qryvendas: TQuery
    Left = 328
    Top = 160
  end
  object qryenderecos: TQuery
    Left = 392
    Top = 160
  end
  object Query6: TQuery
    Left = 448
    Top = 232
  end
  object Query7: TQuery
    Left = 520
    Top = 232
  end
  object DsQryProd: TDataSource
    DataSet = QryProdutos
    Left = 96
    Top = 296
  end
  object Query1: TQuery
    Left = 168
    Top = 232
  end
  object tblnfentradas: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'nfentradas.DB'
    Left = 552
    Top = 16
    object tblnfentradasDcnumero: TFloatField
      FieldName = 'Dcnumero'
    end
    object tblnfentradasDcserie: TStringField
      FieldName = 'Dcserie'
      Size = 3
    end
    object tblnfentradasDcordem: TStringField
      FieldName = 'Dcordem'
      Size = 2
    end
    object tblnfentradasDctipo: TStringField
      FieldName = 'Dctipo'
      Size = 6
    end
    object tblnfentradasVlnota: TCurrencyField
      FieldName = 'Vlnota'
    end
    object tblnfentradasVlicms: TCurrencyField
      FieldName = 'Vlicms'
    end
    object tblnfentradasVlipi: TCurrencyField
      FieldName = 'Vlipi'
    end
    object tblnfentradasVlfrete: TCurrencyField
      FieldName = 'Vlfrete'
    end
    object tblnfentradasCdfornecedor: TFloatField
      FieldName = 'Cdfornecedor'
    end
    object tblnfentradasCfop: TFloatField
      FieldName = 'Cfop'
    end
    object tblnfentradasDtentrada: TDateField
      FieldName = 'Dtentrada'
    end
    object tblnfentradasDtemissao: TDateField
      FieldName = 'Dtemissao'
    end
    object tblnfentradasTransportado: TFloatField
      FieldName = 'Transportado'
    end
    object tblnfentradasCdpedido: TFloatField
      FieldName = 'Cdpedido'
    end
    object tblnfentradasNatureza: TFloatField
      FieldName = 'Natureza'
    end
    object tblnfentradasIdnfentradas: TIntegerField
      FieldName = 'Idnfentradas'
    end
    object tblnfentradasVlst: TCurrencyField
      FieldName = 'Vlst'
    end
    object tblnfentradasTpsituacao: TStringField
      FieldName = 'Tpsituacao'
      Size = 1
    end
  end
  object Dsnfentradas: TDataSource
    DataSet = tblnfentradas
    Left = 560
    Top = 72
  end
  object Tbtitulospagar: TTable
    DatabaseName = 'laluna1'
    TableName = 'titulospagar2.DB'
    Left = 616
    Top = 16
    object TbtitulospagarDcnumero: TFloatField
      FieldName = 'Dcnumero'
    end
    object TbtitulospagarDcserie: TStringField
      FieldName = 'Dcserie'
      Size = 3
    end
    object TbtitulospagarDcordem: TStringField
      FieldName = 'Dcordem'
      Size = 2
    end
    object TbtitulospagarDctipo: TStringField
      FieldName = 'Dctipo'
      Size = 6
    end
    object TbtitulospagarParcela: TFloatField
      FieldName = 'Parcela'
    end
    object TbtitulospagarStatus: TStringField
      FieldName = 'Status'
      Size = 1
    end
    object TbtitulospagarTpsituacao: TStringField
      FieldName = 'Tpsituacao'
      Size = 1
    end
    object TbtitulospagarVlparcela: TFloatField
      FieldName = 'Vlparcela'
    end
    object TbtitulospagarFornecedor: TFloatField
      FieldName = 'Fornecedor'
    end
    object TbtitulospagarObs: TStringField
      FieldName = 'Obs'
      Size = 255
    end
    object TbtitulospagarDATAPAGAMENTO: TDateField
      FieldName = 'DATAPAGAMENTO'
    end
    object TbtitulospagarDtlancamento: TDateField
      FieldName = 'Dtlancamento'
    end
  end
  object dstitulospagar: TDataSource
    DataSet = Tbtitulospagar
    Left = 624
    Top = 72
  end
  object qryMovEstoque: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select *from produtos')
    Left = 32
    Top = 80
  end
  object qrymovestoque2: TQuery
    DatabaseName = 'laluna'
    Left = 32
    Top = 160
  end
  object QYBAIXAS: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select *from baixas')
    Left = 96
    Top = 232
  end
  object Query2: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'SELECT *FROM CLIENTE')
    Left = 224
    Top = 232
  end
  object DsQryCliente: TDataSource
    DataSet = qryclientes
    Left = 168
    Top = 296
  end
  object DataSource2: TDataSource
    Left = 224
    Top = 296
  end
  object Query3: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select *from produtos')
    Left = 296
    Top = 232
  end
  object Query4: TQuery
    DatabaseName = 'laluna1'
    Left = 352
    Top = 232
  end
  object Query5: TQuery
    CachedUpdates = True
    DatabaseName = 'laluna1'
    Left = 400
    Top = 232
  end
  object Query8: TQuery
    CachedUpdates = True
    DatabaseName = 'laluna1'
    Left = 576
    Top = 232
  end
  object tblnatureza: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'natureza.db'
    Left = 688
    Top = 16
    object tblnaturezaNatureza: TFloatField
      FieldName = 'Natureza'
    end
    object tblnaturezaDescricao: TStringField
      FieldName = 'Descricao'
      Size = 100
    end
  end
  object dsNatureza: TDataSource
    DataSet = tblnatureza
    Left = 688
    Top = 64
  end
  object Query9: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select *from produtos p ,transfer t , precos e where '
      '   p.codpro = t.codpro and'
      '   p.codpro = e.codpro and'
      '   t.codpro = e.codpro  ')
    Left = 624
    Top = 232
  end
  object tbletiques: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'etiquetas.DB'
    Left = 480
    Top = 8
    object tbletiquesCodpro: TFloatField
      FieldName = 'Codpro'
    end
    object tbletiquesProduto: TStringField
      FieldName = 'Produto'
    end
    object tbletiquesPreco: TCurrencyField
      FieldName = 'Preco'
    end
    object tbletiquesQuantidade: TFloatField
      FieldName = 'Quantidade'
    end
    object tbletiquesImprime: TStringField
      FieldName = 'Imprime'
      Size = 1
    end
    object tbletiquesCodcli: TStringField
      FieldName = 'Codcli'
    end
  end
  object dsetiquetas: TDataSource
    DataSet = tbletiques
    Left = 480
    Top = 72
  end
  object qrytitulospagar: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select *from titulospagar2 WHERE TPSITUACAO = '#39'A'#39)
    Left = 24
    Top = 232
  end
  object dsQrytitulospagar: TDataSource
    DataSet = qrytitulospagar
    Left = 16
    Top = 296
  end
  object dsQUERY9: TDataSource
    DataSet = Query9
    Left = 296
    Top = 296
  end
  object IBDatabase1: TIBDatabase
    Connected = True
    DatabaseName = 'c:/nova.gdb'
    Params.Strings = (
      'user_name=sysdba'
      'password=masterkey'
      'lc_ctype=ASCII')
    LoginPrompt = False
    DefaultTransaction = IBTransaction1
    IdleTimer = 0
    SQLDialect = 3
    TraceFlags = []
    Left = 480
    Top = 160
  end
  object IBTransaction1: TIBTransaction
    Active = True
    DefaultDatabase = IBDatabase1
    AutoStopAction = saNone
    Left = 520
    Top = 160
  end
end
