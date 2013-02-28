inherited FRMPRODUTOS: TFRMPRODUTOS
  Left = 232
  Top = 159
  Width = 700
  Height = 631
  Caption = 'FRMPRODUTOS'
  OldCreateOrder = True
  OnActivate = FormActivate
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 556
    Height = 532
    object Label1: TLabel
      Left = 16
      Top = 0
      Width = 46
      Height = 13
      Caption = 'CODPRO'
    end
    object Label2: TLabel
      Left = 16
      Top = 40
      Width = 54
      Height = 13
      Caption = 'PRODUTO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 16
      Top = 80
      Width = 21
      Height = 13
      Caption = 'REF'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 16
      Top = 120
      Width = 34
      Height = 13
      Caption = 'CLASS'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 16
      Top = 160
      Width = 49
      Height = 13
      Caption = 'UNIDADE'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 16
      Top = 200
      Width = 34
      Height = 13
      Caption = 'LOCAL'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 16
      Top = 240
      Width = 42
      Height = 13
      Caption = 'CODIND'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 16
      Top = 280
      Width = 36
      Height = 13
      Caption = 'INDICE'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 16
      Top = 320
      Width = 36
      Height = 13
      Caption = 'VALOR'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 16
      Top = 360
      Width = 39
      Height = 13
      Caption = 'CODCLI'
      FocusControl = DBEdit10
    end
    object Label11: TLabel
      Left = 16
      Top = 400
      Width = 37
      Height = 13
      Caption = 'RAZAO'
      FocusControl = DBEdit11
    end
    object Label12: TLabel
      Left = 16
      Top = 440
      Width = 33
      Height = 13
      Caption = 'AQUIS'
      FocusControl = DBEdit12
    end
    object Label13: TLabel
      Left = 16
      Top = 480
      Width = 26
      Height = 13
      Caption = 'ICMS'
      FocusControl = DBEdit13
    end
    object Label14: TLabel
      Left = 392
      Top = 0
      Width = 13
      Height = 13
      Caption = 'IPI'
      FocusControl = DBEdit14
    end
    object Label15: TLabel
      Left = 392
      Top = 48
      Width = 32
      Height = 13
      Caption = 'FINAN'
      FocusControl = DBEdit15
    end
    object Label16: TLabel
      Left = 392
      Top = 88
      Width = 35
      Height = 13
      Caption = 'FRETE'
      FocusControl = DBEdit16
    end
    object Label17: TLabel
      Left = 392
      Top = 128
      Width = 37
      Height = 13
      Caption = 'CUSTO'
      FocusControl = DBEdit17
    end
    object Label18: TLabel
      Left = 392
      Top = 168
      Width = 37
      Height = 13
      Caption = 'PRAZO'
      FocusControl = DBEdit18
    end
    object Label19: TLabel
      Left = 392
      Top = 216
      Width = 74
      Height = 13
      Caption = 'PRECOVENDA'
      FocusControl = DBEdit19
    end
    object Label20: TLabel
      Left = 392
      Top = 256
      Width = 43
      Height = 13
      Caption = 'STATUS'
      FocusControl = DBEdit20
    end
    object Label21: TLabel
      Left = 392
      Top = 296
      Width = 52
      Height = 13
      Caption = 'ESTOQUE'
      FocusControl = DBEdit21
    end
    object Label22: TLabel
      Left = 392
      Top = 336
      Width = 74
      Height = 13
      Caption = 'ESTOQUEANT'
      FocusControl = DBEdit22
    end
    object DBEdit2: TDBEdit
      Left = 16
      Top = 56
      Width = 199
      Height = 21
      DataField = 'PRODUTO'
      DataSource = DataSource1
      TabOrder = 0
    end
    object DBEdit3: TDBEdit
      Left = 16
      Top = 96
      Width = 199
      Height = 21
      DataField = 'REF'
      DataSource = DataSource1
      TabOrder = 1
    end
    object DBEdit4: TDBEdit
      Left = 16
      Top = 136
      Width = 134
      Height = 21
      DataField = 'CLASS'
      DataSource = DataSource1
      TabOrder = 2
    end
    object DBEdit5: TDBEdit
      Left = 16
      Top = 176
      Width = 43
      Height = 21
      DataField = 'UNIDADE'
      DataSource = DataSource1
      TabOrder = 3
    end
    object DBEdit6: TDBEdit
      Left = 16
      Top = 216
      Width = 69
      Height = 21
      DataField = 'LOCAL'
      DataSource = DataSource1
      TabOrder = 4
    end
    object DBEdit7: TDBEdit
      Left = 16
      Top = 256
      Width = 134
      Height = 21
      DataField = 'CODIND'
      DataSource = DataSource1
      TabOrder = 5
    end
    object DBEdit8: TDBEdit
      Left = 16
      Top = 296
      Width = 82
      Height = 21
      DataField = 'INDICE'
      DataSource = DataSource1
      TabOrder = 6
    end
    object DBEdit9: TDBEdit
      Left = 16
      Top = 336
      Width = 134
      Height = 21
      DataField = 'VALOR'
      DataSource = DataSource1
      TabOrder = 7
    end
    object DBEdit10: TDBEdit
      Left = 16
      Top = 376
      Width = 134
      Height = 21
      DataField = 'CODCLI'
      DataSource = DataSource1
      TabOrder = 8
    end
    object DBEdit11: TDBEdit
      Left = 16
      Top = 416
      Width = 524
      Height = 21
      DataField = 'RAZAO'
      DataSource = DataSource1
      TabOrder = 9
    end
    object DBEdit12: TDBEdit
      Left = 16
      Top = 456
      Width = 134
      Height = 21
      DataField = 'AQUIS'
      DataSource = DataSource1
      TabOrder = 10
    end
    object DBEdit13: TDBEdit
      Left = 16
      Top = 496
      Width = 134
      Height = 21
      DataField = 'ICMS'
      DataSource = DataSource1
      TabOrder = 11
    end
    object DBEdit14: TDBEdit
      Left = 392
      Top = 16
      Width = 134
      Height = 21
      DataField = 'IPI'
      DataSource = DataSource1
      TabOrder = 12
    end
    object DBEdit15: TDBEdit
      Left = 392
      Top = 64
      Width = 134
      Height = 21
      DataField = 'FINAN'
      DataSource = DataSource1
      TabOrder = 13
    end
    object DBEdit16: TDBEdit
      Left = 392
      Top = 104
      Width = 134
      Height = 21
      DataField = 'FRETE'
      DataSource = DataSource1
      TabOrder = 14
    end
    object DBEdit17: TDBEdit
      Left = 392
      Top = 144
      Width = 134
      Height = 21
      DataField = 'CUSTO'
      DataSource = DataSource1
      TabOrder = 15
    end
    object DBEdit18: TDBEdit
      Left = 392
      Top = 184
      Width = 134
      Height = 21
      DataField = 'PRAZO'
      DataSource = DataSource1
      TabOrder = 16
    end
    object DBEdit19: TDBEdit
      Left = 392
      Top = 232
      Width = 134
      Height = 21
      DataField = 'PRECOVENDA'
      DataSource = DataSource1
      TabOrder = 17
    end
    object DBEdit20: TDBEdit
      Left = 392
      Top = 272
      Width = 17
      Height = 21
      DataField = 'STATUS'
      DataSource = DataSource1
      TabOrder = 18
    end
    object DBEdit21: TDBEdit
      Left = 392
      Top = 312
      Width = 134
      Height = 21
      DataField = 'ESTOQUE'
      DataSource = DataSource1
      TabOrder = 19
    end
    object DBEdit22: TDBEdit
      Left = 392
      Top = 352
      Width = 134
      Height = 21
      DataField = 'ESTOQUEANT'
      DataSource = DataSource1
      TabOrder = 20
    end
    object Edit1: TEdit
      Left = 16
      Top = 16
      Width = 121
      Height = 21
      TabOrder = 21
      Text = 'Edit1'
      OnExit = Edit1Exit
    end
  end
  inherited Panel3: TPanel
    Width = 692
    object Label23: TLabel
      Left = 32
      Top = 26
      Width = 172
      Height = 22
      Caption = 'Cadastro Produtos'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -19
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
  end
  inherited Panel2: TPanel
    Left = 556
    Width = 136
    Height = 532
    inherited DBButton1: TDBButton
      DataSource = DataSource1
    end
    inherited DBButton2: TDBButton
      DataSource = DataSource1
    end
    inherited DBButton3: TDBButton
      DataSource = DataSource1
    end
    inherited DBButton4: TDBButton
      DataSource = DataSource1
    end
    inherited DBButton5: TDBButton
      DataSource = DataSource1
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataSource1
      Hints.Strings = ()
    end
  end
  object qry1: TQuery
    CachedUpdates = True
    DatabaseName = 'laluna1'
    SQL.Strings = (
      '')
    UpdateObject = UpdateSQL1
    Left = 304
    Top = 72
    object qry1CODPRO: TFloatField
      FieldName = 'CODPRO'
      Origin = 'LALUNA1."produtos.DBF".CODPRO'
    end
    object qry1PRODUTO: TStringField
      FieldName = 'PRODUTO'
      Origin = 'LALUNA1."produtos.DBF".PRODUTO'
      Size = 15
    end
    object qry1REF: TStringField
      FieldName = 'REF'
      Origin = 'LALUNA1."produtos.DBF".REF'
      Size = 15
    end
    object qry1CLASS: TStringField
      FieldName = 'CLASS'
      Origin = 'LALUNA1."produtos.DBF".CLASS'
      Size = 10
    end
    object qry1UNIDADE: TStringField
      FieldName = 'UNIDADE'
      Origin = 'LALUNA1."produtos.DBF".UNIDADE'
      Size = 3
    end
    object qry1LOCAL: TStringField
      FieldName = 'LOCAL'
      Origin = 'LALUNA1."produtos.DBF".LOCAL'
      Size = 5
    end
    object qry1CODIND: TFloatField
      FieldName = 'CODIND'
      Origin = 'LALUNA1."produtos.DBF".CODIND'
    end
    object qry1INDICE: TStringField
      FieldName = 'INDICE'
      Origin = 'LALUNA1."produtos.DBF".INDICE'
      Size = 6
    end
    object qry1VALOR: TFloatField
      FieldName = 'VALOR'
      Origin = 'LALUNA1."produtos.DBF".VALOR'
    end
    object qry1CODCLI: TFloatField
      FieldName = 'CODCLI'
      Origin = 'LALUNA1."produtos.DBF".CODCLI'
    end
    object qry1RAZAO: TStringField
      FieldName = 'RAZAO'
      Origin = 'LALUNA1."produtos.DBF".RAZAO'
      Size = 40
    end
    object qry1AQUIS: TFloatField
      FieldName = 'AQUIS'
      Origin = 'LALUNA1."produtos.DBF".AQUIS'
    end
    object qry1ICMS: TFloatField
      FieldName = 'ICMS'
      Origin = 'LALUNA1."produtos.DBF".ICMS'
    end
    object qry1IPI: TFloatField
      FieldName = 'IPI'
      Origin = 'LALUNA1."produtos.DBF".IPI'
    end
    object qry1FINAN: TFloatField
      FieldName = 'FINAN'
      Origin = 'LALUNA1."produtos.DBF".FINAN'
    end
    object qry1FRETE: TFloatField
      FieldName = 'FRETE'
      Origin = 'LALUNA1."produtos.DBF".FRETE'
    end
    object qry1CUSTO: TFloatField
      FieldName = 'CUSTO'
      Origin = 'LALUNA1."produtos.DBF".CUSTO'
    end
    object qry1PRAZO: TSmallintField
      FieldName = 'PRAZO'
      Origin = 'LALUNA1."produtos.DBF".PRAZO'
    end
    object qry1PRECOVENDA: TFloatField
      FieldName = 'PRECOVENDA'
      Origin = 'LALUNA1."produtos.DBF".PRECOVENDA'
    end
    object qry1STATUS: TStringField
      FieldName = 'STATUS'
      Origin = 'LALUNA1."produtos.DBF".STATUS'
      Size = 1
    end
    object qry1ESTOQUE: TFloatField
      FieldName = 'ESTOQUE'
      Origin = 'LALUNA1."produtos.DBF".ESTOQUE'
    end
    object qry1ESTOQUEANT: TFloatField
      FieldName = 'ESTOQUEANT'
      Origin = 'LALUNA1."produtos.DBF".ESTOQUEANT'
    end
  end
  object DataSource1: TDataSource
    DataSet = qry1
    Left = 496
    Top = 73
  end
  object UpdateSQL1: TUpdateSQL
    ModifySQL.Strings = (
      'update produtos'
      'set'
      '  PRODUTO = :PRODUTO,'
      '  REF = :REF,'
      '  CLASS = :CLASS,'
      '  UNIDADE = :UNIDADE,'
      '  LOCAL = :LOCAL,'
      '  CODIND = :CODIND,'
      '  INDICE = :INDICE,'
      '  VALOR = :VALOR,'
      '  CODCLI = :CODCLI,'
      '  RAZAO = :RAZAO,'
      '  AQUIS = :AQUIS,'
      '  ICMS = :ICMS,'
      '  IPI = :IPI,'
      '  FINAN = :FINAN,'
      '  FRETE = :FRETE,'
      '  CUSTO = :CUSTO,'
      '  PRAZO = :PRAZO,'
      '  PRECOVENDA = :PRECOVENDA,'
      '  STATUS = :STATUS,'
      '  ESTOQUE = :ESTOQUE,'
      '  ESTOQUEANT = :ESTOQUEANT'
      'where'
      '  CODPRO = :OLD_CODPRO')
    InsertSQL.Strings = (
      'insert into produtos'
      
        '  (PRODUTO, REF, CLASS, UNIDADE, LOCAL, CODIND, INDICE, VALOR, C' +
        'ODCLI, '
      
        '   RAZAO, AQUIS, ICMS, IPI, FINAN, FRETE, CUSTO, PRAZO, PRECOVEN' +
        'DA, '
      'STATUS, '
      '   ESTOQUE, ESTOQUEANT)'
      'values'
      
        '  (:PRODUTO, :REF, :CLASS, :UNIDADE, :LOCAL, :CODIND, :INDICE, :' +
        'VALOR, '
      
        '   :CODCLI, :RAZAO, :AQUIS, :ICMS, :IPI, :FINAN, :FRETE, :CUSTO,' +
        ' :PRAZO, '
      '   :PRECOVENDA, :STATUS, :ESTOQUE, :ESTOQUEANT)')
    DeleteSQL.Strings = (
      'delete from produtos'
      'where'
      '  CODPRO = :OLD_CODPRO')
    Left = 344
    Top = 73
  end
end
