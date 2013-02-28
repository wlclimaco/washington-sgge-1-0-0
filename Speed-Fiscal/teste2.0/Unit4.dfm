object CONSBAIXAS: TCONSBAIXAS
  Left = 212
  Top = 154
  Width = 691
  Height = 520
  Caption = 'CONSBAIXAS'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object DBGrid1: TDBGrid
    Left = 0
    Top = 112
    Width = 681
    Height = 329
    DataSource = DataSource1
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = 'MS Sans Serif'
    TitleFont.Style = []
  end
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 681
    Height = 113
    TabOrder = 1
    object Label1: TLabel
      Left = 24
      Top = 16
      Width = 56
      Height = 16
      Caption = 'BUSCAR'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'MS Sans Serif'
      Font.Style = []
      ParentFont = False
    end
    object SpeedButton1: TSpeedButton
      Left = 376
      Top = 8
      Width = 41
      Height = 22
      OnClick = SpeedButton1Click
    end
    object Edit1: TEdit
      Left = 88
      Top = 8
      Width = 273
      Height = 21
      TabOrder = 0
      Text = 'Edit1'
    end
    object GroupBox1: TGroupBox
      Left = 24
      Top = 48
      Width = 649
      Height = 57
      Caption = 'TIPO'
      TabOrder = 1
      object RadioButton1: TRadioButton
        Left = 16
        Top = 24
        Width = 153
        Height = 17
        Caption = 'DESCRI'#199#195'O PRODUTO'
        TabOrder = 0
      end
      object RadioButton2: TRadioButton
        Left = 200
        Top = 24
        Width = 113
        Height = 17
        Caption = 'DATA BAIXA'
        TabOrder = 1
      end
      object RadioButton3: TRadioButton
        Left = 336
        Top = 24
        Width = 113
        Height = 17
        Caption = 'CODIBX'
        TabOrder = 2
      end
    end
  end
  object Panel2: TPanel
    Left = 0
    Top = 445
    Width = 683
    Height = 41
    Align = alBottom
    TabOrder = 2
    object SpeedButton2: TSpeedButton
      Left = 488
      Top = 8
      Width = 81
      Height = 22
    end
    object SpeedButton3: TSpeedButton
      Left = 584
      Top = 8
      Width = 81
      Height = 22
      OnClick = SpeedButton3Click
    end
  end
  object Query1: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'SELECT *FROM BAIXAS')
    Left = 456
    Top = 8
    object Query1CODIBX: TFloatField
      FieldName = 'CODIBX'
      Origin = 'LALUNA1."BAIXAS.DBF".CODIBX'
    end
    object Query1PRODUTO: TStringField
      FieldName = 'PRODUTO'
      Origin = 'LALUNA1."BAIXAS.DBF".PRODUTO'
      Size = 15
    end
    object Query1REF: TStringField
      FieldName = 'REF'
      Origin = 'LALUNA1."BAIXAS.DBF".REF'
      Size = 15
    end
    object Query1CLASS: TStringField
      FieldName = 'CLASS'
      Origin = 'LALUNA1."BAIXAS.DBF".CLASS'
      Size = 10
    end
    object Query1LOCAL: TStringField
      FieldName = 'LOCAL'
      Origin = 'LALUNA1."BAIXAS.DBF".LOCAL'
      Size = 5
    end
    object Query1DATA: TDateField
      FieldName = 'DATA'
      Origin = 'LALUNA1."BAIXAS.DBF".DATA'
    end
    object Query1LOJ1: TSmallintField
      FieldName = 'LOJ1'
      Origin = 'LALUNA1."BAIXAS.DBF".LOJ1'
    end
    object Query1ESTQ1: TFloatField
      FieldName = 'ESTQ1'
      Origin = 'LALUNA1."BAIXAS.DBF".ESTQ1'
    end
    object Query1LOJ2: TSmallintField
      FieldName = 'LOJ2'
      Origin = 'LALUNA1."BAIXAS.DBF".LOJ2'
    end
    object Query1ESTQ2: TFloatField
      FieldName = 'ESTQ2'
      Origin = 'LALUNA1."BAIXAS.DBF".ESTQ2'
    end
    object Query1LOJ3: TSmallintField
      FieldName = 'LOJ3'
      Origin = 'LALUNA1."BAIXAS.DBF".LOJ3'
    end
    object Query1ESTQ3: TFloatField
      FieldName = 'ESTQ3'
      Origin = 'LALUNA1."BAIXAS.DBF".ESTQ3'
    end
    object Query1UNIT: TFloatField
      FieldName = 'UNIT'
      Origin = 'LALUNA1."BAIXAS.DBF".UNIT'
    end
    object Query1TOTAL: TFloatField
      FieldName = 'TOTAL'
      Origin = 'LALUNA1."BAIXAS.DBF".TOTAL'
    end
  end
  object DataSource1: TDataSource
    DataSet = DataModule2.QYBAIXAS
    Left = 456
    Top = 40
  end
end
