object Form82: TForm82
  Left = 157
  Top = 154
  Width = 694
  Height = 477
  Caption = 'Form82'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 194
    Top = 392
    Width = 32
    Height = 13
    Caption = 'Label1'
  end
  object Label2: TLabel
    Left = 288
    Top = 392
    Width = 32
    Height = 13
    Caption = 'Label2'
  end
  object Label3: TLabel
    Left = 330
    Top = 392
    Width = 32
    Height = 13
    Caption = 'Label3'
  end
  object Button2: TButton
    Left = 64
    Top = 16
    Width = 75
    Height = 25
    Caption = 'Buscar'
    TabOrder = 0
    OnClick = Button2Click
  end
  object ProgressBar1: TProgressBar
    Left = 8
    Top = 416
    Width = 673
    Height = 17
    TabOrder = 1
  end
  object Button3: TButton
    Left = 584
    Top = 360
    Width = 91
    Height = 25
    Caption = 'Baixar '
    TabOrder = 2
    OnClick = Button3Click
  end
  object BitBtn1: TBitBtn
    Left = 608
    Top = 168
    Width = 49
    Height = 25
    Caption = 'BitBtn1'
    TabOrder = 3
  end
  object Button1: TButton
    Left = 504
    Top = 232
    Width = 75
    Height = 25
    Caption = 'Button1'
    TabOrder = 4
    OnClick = Button1Click
  end
  object OpenDialog1: TOpenDialog
    Left = 8
    Top = 8
  end
  object Table1: TIBTable
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    FieldDefs = <
      item
        Name = 'CAR'
        DataType = ftString
        Size = 1
      end
      item
        Name = 'DTMOVIMENTO'
        DataType = ftDate
      end
      item
        Name = 'NOSERIE'
        DataType = ftString
        Size = 20
      end
      item
        Name = 'CDPRODUTO'
        DataType = ftString
        Size = 15
      end
      item
        Name = 'QUANTIDADE'
        DataType = ftFloat
      end
      item
        Name = 'VLTOTAL'
        DataType = ftFloat
      end
      item
        Name = 'BASEICMS'
        DataType = ftFloat
      end
      item
        Name = 'ALIQUOTA'
        DataType = ftFloat
      end
      item
        Name = 'VLTOTALICMS'
        DataType = ftFloat
      end>
    StoreDefs = True
    TableName = 'R60D'
    Left = 232
    Top = 24
    object Table1CAR: TIBStringField
      FieldName = 'CAR'
      Size = 1
    end
    object Table1DTMOVIMENTO: TDateField
      FieldName = 'DTMOVIMENTO'
    end
    object Table1NOSERIE: TIBStringField
      FieldName = 'NOSERIE'
    end
    object Table1QUANTIDADE: TFloatField
      FieldName = 'QUANTIDADE'
    end
    object Table1VLTOTAL: TFloatField
      FieldName = 'VLTOTAL'
    end
    object Table1BASEICMS: TFloatField
      FieldName = 'BASEICMS'
    end
    object Table1ALIQUOTA: TFloatField
      FieldName = 'ALIQUOTA'
    end
    object Table1VLTOTALICMS: TFloatField
      FieldName = 'VLTOTALICMS'
    end
    object Table1CDPRODUTO: TIBStringField
      FieldName = 'CDPRODUTO'
      Size = 15
    end
  end
  object Table2: TIBTable
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    FieldDefs = <
      item
        Name = 'TIPO'
        DataType = ftString
        Size = 2
      end
      item
        Name = 'CAR'
        DataType = ftString
        Size = 1
      end
      item
        Name = 'DTMOVIMENTO'
        DataType = ftDate
      end
      item
        Name = 'NOSERIE'
        DataType = ftString
        Size = 20
      end
      item
        Name = 'NOECF'
        DataType = ftInteger
      end
      item
        Name = 'MODECF'
        DataType = ftString
        Size = 3
      end
      item
        Name = 'COOINICIAL'
        DataType = ftFloat
      end
      item
        Name = 'COOFINAL'
        DataType = ftFloat
      end
      item
        Name = 'CRZ'
        DataType = ftFloat
      end
      item
        Name = 'CRO'
        DataType = ftFloat
      end
      item
        Name = 'VENDABRUTA'
        DataType = ftFloat
      end
      item
        Name = 'TOTALGERAL'
        DataType = ftFloat
      end>
    StoreDefs = True
    TableName = 'REDLEITURA'
    UpdateObject = IBUpdateSQL1
    Left = 272
    Top = 24
    object Table2TIPO: TIBStringField
      FieldName = 'TIPO'
      Size = 2
    end
    object Table2CAR: TIBStringField
      FieldName = 'CAR'
      Size = 1
    end
    object Table2NOSERIE: TIBStringField
      FieldName = 'NOSERIE'
    end
    object Table2MODECF: TIBStringField
      FieldName = 'MODECF'
      Size = 3
    end
    object Table2COOINICIAL: TFloatField
      FieldName = 'COOINICIAL'
    end
    object Table2COOFINAL: TFloatField
      FieldName = 'COOFINAL'
    end
    object Table2CRZ: TFloatField
      FieldName = 'CRZ'
    end
    object Table2CRO: TFloatField
      FieldName = 'CRO'
    end
    object Table2VENDABRUTA: TFloatField
      FieldName = 'VENDABRUTA'
    end
    object Table2TOTALGERAL: TFloatField
      FieldName = 'TOTALGERAL'
    end
    object Table2DTMOVIMENTO: TDateField
      FieldName = 'DTMOVIMENTO'
    end
    object Table2NOECF: TIntegerField
      FieldName = 'NOECF'
    end
  end
  object Table3: TIBTable
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    FieldDefs = <
      item
        Name = 'DTMOVIMENTO'
        DataType = ftDate
      end
      item
        Name = 'NOSERIE'
        DataType = ftString
        Size = 20
      end
      item
        Name = 'MODELO'
        DataType = ftString
        Size = 3
      end
      item
        Name = 'COO'
        Attributes = [faRequired]
        DataType = ftInteger
      end
      item
        Name = 'NOITEM'
        DataType = ftFloat
      end
      item
        Name = 'CDPRODUTO'
        DataType = ftString
        Size = 30
      end
      item
        Name = 'QUANTIDADE'
        DataType = ftFloat
      end
      item
        Name = 'VLNOTA'
        DataType = ftFloat
      end
      item
        Name = 'BASEICMS'
        DataType = ftFloat
      end
      item
        Name = 'ALIQUOTA'
        DataType = ftFloat
      end
      item
        Name = 'VLICMS'
        DataType = ftFloat
      end
      item
        Name = 'ID'
        DataType = ftInteger
      end
      item
        Name = 'STATUS'
        DataType = ftString
        Size = 1
      end
      item
        Name = 'CODPRO'
        DataType = ftFloat
      end
      item
        Name = 'STFISCAL'
        DataType = ftString
        Size = 1
      end>
    IndexDefs = <
      item
        Name = 'RDB$PRIMARY28'
        Fields = 'COO'
        Options = [ixPrimary, ixUnique]
      end>
    StoreDefs = True
    TableName = 'NFSAIDAS2'
    Left = 320
    Top = 24
    object Table3DTMOVIMENTO: TDateField
      FieldName = 'DTMOVIMENTO'
    end
    object Table3NOSERIE: TIBStringField
      FieldName = 'NOSERIE'
    end
    object Table3MODELO: TIBStringField
      FieldName = 'MODELO'
      Size = 3
    end
    object Table3COO: TIntegerField
      FieldName = 'COO'
    end
    object Table3NOITEM: TFloatField
      FieldName = 'NOITEM'
    end
    object Table3CDPRODUTO: TIBStringField
      FieldName = 'CDPRODUTO'
    end
    object Table3QUANTIDADE: TFloatField
      FieldName = 'QUANTIDADE'
    end
    object Table3BASEICMS: TFloatField
      FieldName = 'BASEICMS'
    end
    object Table3ALIQUOTA: TFloatField
      FieldName = 'ALIQUOTA'
    end
    object Table3VLICMS: TFloatField
      FieldName = 'VLICMS'
    end
    object Table3ID: TIntegerField
      FieldName = 'ID'
    end
    object Table3STATUS: TIBStringField
      FieldName = 'STATUS'
      Size = 1
    end
    object Table3CODPRO: TFloatField
      FieldName = 'CODPRO'
    end
    object Table3VLNOTA: TFloatField
      FieldName = 'VLNOTA'
    end
    object Table3STFISCAL: TIBStringField
      FieldName = 'STFISCAL'
      Size = 1
    end
  end
  object Table4: TIBTable
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    FieldDefs = <
      item
        Name = 'ID'
        DataType = ftInteger
      end
      item
        Name = 'PRODUTO'
        DataType = ftString
        Size = 200
      end
      item
        Name = 'CODSIN'
        DataType = ftString
        Size = 50
      end
      item
        Name = 'CODPRO'
        DataType = ftFloat
      end>
    StoreDefs = True
    TableName = 'PRODSINTE'
    Left = 368
    Top = 24
    object Table4ID: TIntegerField
      FieldName = 'ID'
    end
    object Table4PRODUTO: TIBStringField
      FieldName = 'PRODUTO'
    end
    object Table4CODSIN: TIBStringField
      FieldName = 'CODSIN'
    end
    object Table4CODPRO: TFloatField
      FieldName = 'CODPRO'
    end
  end
  object Table5: TIBTable
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    FieldDefs = <
      item
        Name = 'ID'
        DataType = ftInteger
      end
      item
        Name = 'MESANO'
        DataType = ftString
        Size = 6
      end
      item
        Name = 'CDPRODUTO'
        DataType = ftString
        Size = 20
      end
      item
        Name = 'QUANTIDADE'
        DataType = ftFloat
      end
      item
        Name = 'VALOR'
        DataType = ftFloat
      end
      item
        Name = 'BASEICMS'
        DataType = ftFloat
      end
      item
        Name = 'ALIQUOTA'
        DataType = ftFloat
      end>
    StoreDefs = True
    TableName = 'R60R'
    Left = 232
    Top = 104
    object Table5ID: TIntegerField
      FieldName = 'ID'
    end
    object Table5MESANO: TIBStringField
      FieldName = 'MESANO'
      Size = 6
    end
    object Table5CDPRODUTO: TIBStringField
      FieldName = 'CDPRODUTO'
    end
    object Table5QUANTIDADE: TFloatField
      FieldName = 'QUANTIDADE'
    end
    object Table5VALOR: TFloatField
      FieldName = 'VALOR'
    end
    object Table5BASEICMS: TFloatField
      FieldName = 'BASEICMS'
    end
    object Table5ALIQUOTA: TFloatField
      FieldName = 'ALIQUOTA'
    end
  end
  object Table6: TIBTable
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    FieldDefs = <
      item
        Name = 'DTEMISSAO'
        DataType = ftDate
      end
      item
        Name = 'NOSERIE'
        DataType = ftString
        Size = 20
      end
      item
        Name = 'ALIGUOTA'
        DataType = ftString
        Size = 4
      end
      item
        Name = 'VALOR'
        DataType = ftFloat
      end>
    StoreDefs = True
    TableName = 'R60A'
    Left = 272
    Top = 104
    object Table6DTEMISSAO: TDateField
      FieldName = 'DTEMISSAO'
    end
    object Table6NOSERIE: TIBStringField
      FieldName = 'NOSERIE'
    end
    object Table6ALIGUOTA: TIBStringField
      FieldName = 'ALIGUOTA'
      Size = 4
    end
    object Table6VALOR: TFloatField
      FieldName = 'VALOR'
    end
  end
  object Query1: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 408
    Top = 24
  end
  object Query2: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 448
    Top = 24
  end
  object Query3: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    Left = 496
    Top = 24
  end
  object Query4: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    Left = 536
    Top = 24
  end
  object IBUpdateSQL1: TIBUpdateSQL
    RefreshSQL.Strings = (
      'Select '
      '  RDB$DB_KEY as IBX_INTERNAL_DBKEY, '
      '  TIPO,'
      '  CAR,'
      '  DTMOVIMENTO,'
      '  NOSERIE,'
      '  NOECF,'
      '  MODECF,'
      '  COOINICIAL,'
      '  COOFINAL,'
      '  CRZ,'
      '  CRO,'
      '  VENDABRUTA,'
      '  TOTALGERAL'
      'from REDLEITURA '
      'where'
      '  TIPO = :TIPO and'
      '  CAR = :CAR and'
      '  DTMOVIMENTO = :DTMOVIMENTO and'
      '  NOSERIE = :NOSERIE and'
      '  NOECF = :NOECF and'
      '  MODECF = :MODECF and'
      '  COOINICIAL = :COOINICIAL and'
      '  COOFINAL = :COOFINAL and'
      '  CRZ = :CRZ and'
      '  CRO = :CRO and'
      '  VENDABRUTA = :VENDABRUTA and'
      '  TOTALGERAL = :TOTALGERAL')
    ModifySQL.Strings = (
      'update REDLEITURA'
      'set'
      '  TIPO = :TIPO,'
      '  CAR = :CAR,'
      '  DTMOVIMENTO = :DTMOVIMENTO,'
      '  NOSERIE = :NOSERIE,'
      '  NOECF = :NOECF,'
      '  MODECF = :MODECF,'
      '  COOINICIAL = :COOINICIAL,'
      '  COOFINAL = :COOFINAL,'
      '  CRZ = :CRZ,'
      '  CRO = :CRO,'
      '  VENDABRUTA = :VENDABRUTA,'
      '  TOTALGERAL = :TOTALGERAL'
      'where'
      '  TIPO = :OLD_TIPO and'
      '  CAR = :OLD_CAR and'
      '  DTMOVIMENTO = :OLD_DTMOVIMENTO and'
      '  NOSERIE = :OLD_NOSERIE and'
      '  NOECF = :OLD_NOECF and'
      '  MODECF = :OLD_MODECF and'
      '  COOINICIAL = :OLD_COOINICIAL and'
      '  COOFINAL = :OLD_COOFINAL and'
      '  CRZ = :OLD_CRZ and'
      '  CRO = :OLD_CRO and'
      '  VENDABRUTA = :OLD_VENDABRUTA and'
      '  TOTALGERAL = :OLD_TOTALGERAL')
    InsertSQL.Strings = (
      'insert into REDLEITURA'
      
        '  (TIPO, CAR, DTMOVIMENTO, NOSERIE, NOECF, MODECF, COOINICIAL, C' +
        'OOFINAL, '
      '   CRZ, CRO, VENDABRUTA, TOTALGERAL)'
      'values'
      
        '  (:TIPO, :CAR, :DTMOVIMENTO, :NOSERIE, :NOECF, :MODECF, :COOINI' +
        'CIAL, :COOFINAL, '
      '   :CRZ, :CRO, :VENDABRUTA, :TOTALGERAL)')
    DeleteSQL.Strings = (
      'delete from REDLEITURA'
      'where'
      '  TIPO = :OLD_TIPO and'
      '  CAR = :OLD_CAR and'
      '  DTMOVIMENTO = :OLD_DTMOVIMENTO and'
      '  NOSERIE = :OLD_NOSERIE and'
      '  NOECF = :OLD_NOECF and'
      '  MODECF = :OLD_MODECF and'
      '  COOINICIAL = :OLD_COOINICIAL and'
      '  COOFINAL = :OLD_COOFINAL and'
      '  CRZ = :OLD_CRZ and'
      '  CRO = :OLD_CRO and'
      '  VENDABRUTA = :OLD_VENDABRUTA and'
      '  TOTALGERAL = :OLD_TOTALGERAL')
    Left = 192
    Top = 104
  end
  object IBTable1: TIBTable
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    FieldDefs = <
      item
        Name = 'COO'
        Attributes = [faRequired]
        DataType = ftInteger
      end
      item
        Name = 'NOITEM'
        Attributes = [faRequired]
        DataType = ftInteger
      end
      item
        Name = 'QUANTIDADE'
        Attributes = [faRequired]
        DataType = ftFloat
      end
      item
        Name = 'VLPRODUTO'
        Attributes = [faRequired]
        DataType = ftFloat
      end
      item
        Name = 'BASEICMS'
        DataType = ftFloat
      end
      item
        Name = 'ALIQUOTA'
        DataType = ftFloat
      end
      item
        Name = 'VLICMS'
        DataType = ftFloat
      end
      item
        Name = 'CODPRO'
        DataType = ftInteger
      end
      item
        Name = 'CDPRODUTO'
        DataType = ftString
        Size = 50
      end
      item
        Name = 'INTEGRADO'
        DataType = ftString
        Size = 1
      end>
    IndexDefs = <
      item
        Name = 'RDB$PRIMARY45'
        Fields = 'COO;NOITEM'
        Options = [ixPrimary, ixUnique]
      end>
    StoreDefs = True
    TableName = 'NFSAIDASITENS'
    Left = 312
    Top = 104
    object IBTable1COO: TIntegerField
      FieldName = 'COO'
      Required = True
    end
    object IBTable1NOITEM: TIntegerField
      FieldName = 'NOITEM'
      Required = True
    end
    object IBTable1QUANTIDADE: TFloatField
      FieldName = 'QUANTIDADE'
      Required = True
    end
    object IBTable1VLPRODUTO: TFloatField
      FieldName = 'VLPRODUTO'
      Required = True
    end
    object IBTable1BASEICMS: TFloatField
      FieldName = 'BASEICMS'
    end
    object IBTable1ALIQUOTA: TFloatField
      FieldName = 'ALIQUOTA'
    end
    object IBTable1VLICMS: TFloatField
      FieldName = 'VLICMS'
    end
    object IBTable1CODPRO: TIntegerField
      FieldName = 'CODPRO'
    end
    object IBTable1CDPRODUTO: TIBStringField
      FieldName = 'CDPRODUTO'
      Size = 50
    end
    object IBTable1INTEGRADO: TIBStringField
      FieldName = 'INTEGRADO'
      Size = 1
    end
  end
  object IBQuery1: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    Left = 584
    Top = 24
  end
  object Query5: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    Left = 624
    Top = 24
  end
  object Query6: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    Left = 608
    Top = 80
  end
  object IBQuery2: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    SQL.Strings = (
      'select *from R60A a where a.dtemissao = :dtemissao and'
      '                          a.noserie   like :noserie and'
      '                          a.aliguota  like :aliquota')
    Left = 192
    Top = 192
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'dtemissao'
        ParamType = ptUnknown
      end
      item
        DataType = ftUnknown
        Name = 'noserie'
        ParamType = ptUnknown
      end
      item
        DataType = ftUnknown
        Name = 'aliquota'
        ParamType = ptUnknown
      end>
  end
  object IBQuery3: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    SQL.Strings = (
      'select *from R60D a where a.dtmovimento = :dtemissao and'
      '                          a.noserie   like :noserie and'
      '                          a.cdproduto  like :cdproduto')
    Left = 232
    Top = 192
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'dtemissao'
        ParamType = ptUnknown
      end
      item
        DataType = ftUnknown
        Name = 'noserie'
        ParamType = ptUnknown
      end
      item
        DataType = ftUnknown
        Name = 'cdproduto'
        ParamType = ptUnknown
      end>
  end
  object IBQuery4: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    SQL.Strings = (
      '')
    Left = 280
    Top = 192
  end
  object IBQuery5: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    SQL.Strings = (
      'select *from nfsaidasitens n where n.coo = :coo and'
      '                                   n.noitem = :noitem')
    Left = 320
    Top = 192
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'coo'
        ParamType = ptUnknown
      end
      item
        DataType = ftUnknown
        Name = 'noitem'
        ParamType = ptUnknown
      end>
  end
  object IBQuery6: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    SQL.Strings = (
      'select *from nfsaidasitens n where n.coo = :coo ')
    Left = 360
    Top = 192
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'coo'
        ParamType = ptUnknown
      end>
  end
  object IBQuery7: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    SQL.Strings = (
      
        'select p.codsin,p.produto,n.dtmovimento from nfsaidas2 n,nfsaida' +
        'sitens i,prodsinte p where'
      ' i.cdproduto = p.codsin and'
      ' i.coo = n.coo and'
      ' i.codpro = 0'
      ' and n.dtmovimento between :dtinicial and :dtfinal'
      ' group by p.codsin,p.produto,n.dtmovimento')
    Left = 192
    Top = 232
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'dtinicial'
        ParamType = ptUnknown
      end
      item
        DataType = ftUnknown
        Name = 'dtfinal'
        ParamType = ptUnknown
      end>
  end
  object DataSource1: TDataSource
    DataSet = IBQuery7
    Left = 232
    Top = 232
  end
  object QryInsert60M: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    SQL.Strings = (
      'select *from REDLEITURA ')
    Left = 88
    Top = 192
  end
  object IBUpdateSQL2: TIBUpdateSQL
    RefreshSQL.Strings = (
      'Select '
      '  TIPO,'
      '  CAR,'
      '  DTMOVIMENTO,'
      '  NOSERIE,'
      '  NOECF,'
      '  MODECF,'
      '  COOINICIAL,'
      '  COOFINAL,'
      '  CRZ,'
      '  CRO,'
      '  VENDABRUTA,'
      '  TOTALGERAL'
      'from REDLEITURA '
      'where'
      '  DTMOVIMENTO = :DTMOVIMENTO and'
      '  NOECF = :NOECF')
    ModifySQL.Strings = (
      'update REDLEITURA'
      'set'
      '  TIPO = :TIPO,'
      '  CAR = :CAR,'
      '  NOSERIE = :NOSERIE,'
      '  MODECF = :MODECF,'
      '  COOINICIAL = :COOINICIAL,'
      '  COOFINAL = :COOFINAL,'
      '  CRZ = :CRZ,'
      '  CRO = :CRO,'
      '  VENDABRUTA = :VENDABRUTA,'
      '  TOTALGERAL = :TOTALGERAL'
      'where'
      '  DTMOVIMENTO = :OLD_DTMOVIMENTO and'
      '  NOECF = :OLD_NOECF')
    InsertSQL.Strings = (
      'insert into REDLEITURA'
      '  (TIPO, CAR, NOSERIE, MODECF, COOINICIAL, COOFINAL, CRZ, CRO, '
      'VENDABRUTA, '
      '   TOTALGERAL)'
      'values'
      
        '  (:TIPO, :CAR, :NOSERIE, :MODECF, :COOINICIAL, :COOFINAL, :CRZ,' +
        ' :CRO, '
      '   :VENDABRUTA, :TOTALGERAL)')
    DeleteSQL.Strings = (
      'delete from REDLEITURA'
      'where'
      '  DTMOVIMENTO = :OLD_DTMOVIMENTO and'
      '  NOECF = :OLD_NOECF')
    Left = 88
    Top = 232
  end
  object QryReg60A: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    SQL.Strings = (
      'select *from R60A')
    UpdateObject = IBUpdateSQL3
    Left = 48
    Top = 192
  end
  object IBUpdateSQL3: TIBUpdateSQL
    RefreshSQL.Strings = (
      'Select '
      '  DTEMISSAO,'
      '  NOSERIE,'
      '  ALIGUOTA,'
      '  VALOR'
      'from R60A '
      'where'
      '  DTEMISSAO = :DTEMISSAO and'
      '  NOSERIE = :NOSERIE and'
      '  ALIGUOTA = :ALIGUOTA')
    ModifySQL.Strings = (
      'update R60A'
      'set'
      '  DTEMISSAO = :DTEMISSAO,'
      '  NOSERIE = :NOSERIE,'
      '  ALIGUOTA = :ALIGUOTA,'
      '  VALOR = :VALOR'
      'where'
      '  DTEMISSAO = :OLD_DTEMISSAO and'
      '  NOSERIE = :OLD_NOSERIE and'
      '  ALIGUOTA = :OLD_ALIGUOTA')
    InsertSQL.Strings = (
      'insert into R60A'
      '  (DTEMISSAO, NOSERIE, ALIGUOTA, VALOR)'
      'values'
      '  (:DTEMISSAO, :NOSERIE, :ALIGUOTA, :VALOR)')
    DeleteSQL.Strings = (
      'delete from R60A'
      'where'
      '  DTEMISSAO = :OLD_DTEMISSAO and'
      '  NOSERIE = :OLD_NOSERIE and'
      '  ALIGUOTA = :OLD_ALIGUOTA')
    Left = 48
    Top = 232
  end
  object query8: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    SQL.Strings = (
      '')
    Left = 280
    Top = 232
  end
  object IBQuery8: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    SQL.Strings = (
      '')
    Left = 320
    Top = 232
  end
  object IBQuery9: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    SQL.Strings = (
      '')
    Left = 360
    Top = 232
  end
end
