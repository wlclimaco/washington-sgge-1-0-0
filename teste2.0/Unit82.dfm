object Form82: TForm82
  Left = 229
  Top = 162
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
    Left = 248
    Top = 392
    Width = 32
    Height = 13
    Caption = 'Label1'
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
  object MaskEdit1: TMaskEdit
    Left = 448
    Top = 168
    Width = 73
    Height = 21
    EditMask = '!99/99/0000;1;_'
    MaxLength = 10
    TabOrder = 3
    Text = '  /  /    '
  end
  object MaskEdit2: TMaskEdit
    Left = 528
    Top = 168
    Width = 73
    Height = 21
    EditMask = '!99/99/0000;1;_'
    MaxLength = 10
    TabOrder = 4
    Text = '  /  /    '
  end
  object BitBtn1: TBitBtn
    Left = 608
    Top = 168
    Width = 49
    Height = 25
    Caption = 'BitBtn1'
    TabOrder = 5
    OnClick = BitBtn1Click
  end
  object OpenDialog1: TOpenDialog
    Left = 8
    Top = 8
  end
  object Database1: TDatabase
    AliasName = 'laluna'
    Connected = True
    DatabaseName = 'laluna2'
    LoginPrompt = False
    SessionName = 'Default'
    Left = 192
    Top = 64
  end
  object Table1: TTable
    AutoRefresh = True
    DatabaseName = 'laluna2'
    Left = 232
    Top = 64
    object Table1Car: TStringField
      FieldName = 'Car'
      Size = 1
    end
    object Table1Dtmovimento: TDateField
      FieldName = 'Dtmovimento'
    end
    object Table1Noserie: TStringField
      FieldName = 'Noserie'
    end
    object Table1Cdproduto: TFloatField
      FieldName = 'Cdproduto'
    end
    object Table1Quantidade: TFloatField
      FieldName = 'Quantidade'
    end
    object Table1Vltotal: TCurrencyField
      FieldName = 'Vltotal'
    end
    object Table1Baseicms: TCurrencyField
      FieldName = 'Baseicms'
    end
    object Table1Aliquota: TFloatField
      FieldName = 'Aliquota'
    end
    object Table1Vltotalicms: TCurrencyField
      FieldName = 'Vltotalicms'
    end
  end
  object Table2: TTable
    Active = True
    AutoRefresh = True
    DatabaseName = 'laluna2'
    TableName = 'redleitura.db'
    Left = 272
    Top = 64
    object Table2Tipo: TStringField
      FieldName = 'Tipo'
      Size = 2
    end
    object Table2Car: TStringField
      FieldName = 'Car'
      Size = 1
    end
    object Table2Dtmovimento: TDateField
      FieldName = 'Dtmovimento'
    end
    object Table2Noserie: TStringField
      FieldName = 'Noserie'
    end
    object Table2Noecf: TFloatField
      FieldName = 'Noecf'
    end
    object Table2Modecf: TStringField
      FieldName = 'Modecf'
      Size = 3
    end
    object Table2Cooinicial: TFloatField
      FieldName = 'Cooinicial'
    end
    object Table2Coofinal: TFloatField
      FieldName = 'Coofinal'
    end
    object Table2Crz: TFloatField
      FieldName = 'Crz'
    end
    object Table2Cro: TFloatField
      FieldName = 'Cro'
    end
    object Table2Vendabruta: TFloatField
      FieldName = 'Vendabruta'
    end
    object Table2Totalgeral: TFloatField
      FieldName = 'Totalgeral'
    end
  end
  object Table3: TTable
    Active = True
    DatabaseName = 'laluna'
    TableName = 'nfsaidas2.db'
    Left = 320
    Top = 64
    object Table3Tipo: TStringField
      FieldName = 'Tipo'
      Size = 2
    end
    object Table3Car: TStringField
      FieldName = 'Car'
      Size = 1
    end
    object Table3NOSERIE: TStringField
      FieldName = 'NOSERIE'
    end
    object Table3Modelo: TStringField
      FieldName = 'Modelo'
      Size = 3
    end
    object Table3Coo: TFloatField
      FieldName = 'Coo'
    end
    object Table3Noitem: TFloatField
      FieldName = 'Noitem'
    end
    object Table3Quantidade: TFloatField
      FieldName = 'Quantidade'
    end
    object Table3Vlproduto: TFloatField
      FieldName = 'Vlproduto'
    end
    object Table3BaseICMS: TFloatField
      FieldName = 'BaseICMS'
    end
    object Table3Aliquota: TFloatField
      FieldName = 'Aliquota'
    end
    object Table3Vlicms: TFloatField
      FieldName = 'Vlicms'
    end
    object Table3Id: TAutoIncField
      FieldName = 'Id'
      ReadOnly = True
    end
    object Table3Cdproduto: TStringField
      FieldName = 'Cdproduto'
    end
    object Table3Status: TStringField
      FieldName = 'Status'
      Size = 1
    end
    object Table3DTMOVIMENTO: TDateField
      FieldName = 'DTMOVIMENTO'
    end
    object Table3Codpro: TFloatField
      FieldName = 'Codpro'
    end
  end
  object Table4: TTable
    Active = True
    DatabaseName = 'laluna2'
    TableName = 'prodsinte.db'
    Left = 368
    Top = 64
    object Table4Id: TAutoIncField
      FieldName = 'Id'
      ReadOnly = True
    end
    object Table4Produto: TStringField
      FieldName = 'Produto'
    end
    object Table4Codsin: TStringField
      FieldName = 'Codsin'
    end
    object Table4Codpro: TFloatField
      FieldName = 'Codpro'
    end
  end
  object Query1: TQuery
    DatabaseName = 'laluna2'
    Left = 408
    Top = 64
  end
  object Query2: TQuery
    DatabaseName = 'laluna2'
    Left = 448
    Top = 64
  end
  object Query3: TQuery
    DatabaseName = 'laluna2'
    Left = 496
    Top = 64
  end
  object Query4: TQuery
    CachedUpdates = True
    DatabaseName = 'laluna2'
    Left = 536
    Top = 64
  end
  object Table5: TTable
    Active = True
    DatabaseName = 'laluna2'
    TableName = 'R60R.db'
    Left = 232
    Top = 104
    object Table5Id: TAutoIncField
      FieldName = 'Id'
      ReadOnly = True
    end
    object Table5Mesano: TStringField
      FieldName = 'Mesano'
      Size = 6
    end
    object Table5Cdproduto: TStringField
      FieldName = 'Cdproduto'
    end
    object Table5Quantidade: TFloatField
      FieldName = 'Quantidade'
    end
    object Table5Valor: TFloatField
      FieldName = 'Valor'
    end
    object Table5Baseicms: TFloatField
      FieldName = 'Baseicms'
    end
    object Table5Aliquota: TFloatField
      FieldName = 'Aliquota'
    end
  end
  object Table6: TTable
    Active = True
    DatabaseName = 'laluna2'
    TableName = 'r60A.db'
    Left = 272
    Top = 104
    object Table6DTEMISSAO: TDateField
      FieldName = 'DTEMISSAO'
    end
    object Table6NOSERIE: TStringField
      FieldName = 'NOSERIE'
    end
    object Table6Valor: TFloatField
      FieldName = 'Valor'
    end
    object Table6Aliguota: TStringField
      FieldName = 'Aliguota'
      Size = 4
    end
  end
end
