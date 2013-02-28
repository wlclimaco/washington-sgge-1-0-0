inherited FRMBAIXAS: TFRMBAIXAS
  Left = 365
  Top = 227
  Width = 499
  Height = 521
  Caption = 'FRMBAIXAS'
  OldCreateOrder = True
  OnActivate = FormActivate
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 491
    Height = 379
    object Label1: TLabel
      Left = 24
      Top = 0
      Width = 40
      Height = 13
      Caption = 'CODIBX'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 24
      Top = 40
      Width = 54
      Height = 13
      Caption = 'PRODUTO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 24
      Top = 80
      Width = 21
      Height = 13
      Caption = 'REF'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 24
      Top = 120
      Width = 34
      Height = 13
      Caption = 'CLASS'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 24
      Top = 160
      Width = 34
      Height = 13
      Caption = 'LOCAL'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 24
      Top = 200
      Width = 29
      Height = 13
      Caption = 'DATA'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 24
      Top = 240
      Width = 25
      Height = 13
      Caption = 'LOJ1'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 24
      Top = 280
      Width = 35
      Height = 13
      Caption = 'ESTQ1'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 24
      Top = 320
      Width = 25
      Height = 13
      Caption = 'LOJ2'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 256
      Top = 16
      Width = 35
      Height = 13
      Caption = 'ESTQ2'
      FocusControl = DBEdit10
    end
    object Label11: TLabel
      Left = 256
      Top = 56
      Width = 25
      Height = 13
      Caption = 'LOJ3'
      FocusControl = DBEdit11
    end
    object Label12: TLabel
      Left = 256
      Top = 96
      Width = 35
      Height = 13
      Caption = 'ESTQ3'
      FocusControl = DBEdit12
    end
    object Label13: TLabel
      Left = 256
      Top = 136
      Width = 26
      Height = 13
      Caption = 'UNIT'
      FocusControl = DBEdit13
    end
    object Label14: TLabel
      Left = 256
      Top = 176
      Width = 35
      Height = 13
      Caption = 'TOTAL'
      FocusControl = DBEdit14
    end
    object DBEdit1: TDBEdit
      Left = 24
      Top = 16
      Width = 134
      Height = 21
      DataField = 'CODIBX'
      DataSource = DataSource1
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 24
      Top = 56
      Width = 199
      Height = 21
      DataField = 'PRODUTO'
      DataSource = DataSource1
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 24
      Top = 96
      Width = 199
      Height = 21
      DataField = 'REF'
      DataSource = DataSource1
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 24
      Top = 136
      Width = 134
      Height = 21
      DataField = 'CLASS'
      DataSource = DataSource1
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 24
      Top = 176
      Width = 69
      Height = 21
      DataField = 'LOCAL'
      DataSource = DataSource1
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 24
      Top = 216
      Width = 134
      Height = 21
      DataField = 'DATA'
      DataSource = DataSource1
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 24
      Top = 256
      Width = 134
      Height = 21
      DataField = 'LOJ1'
      DataSource = DataSource1
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 24
      Top = 296
      Width = 134
      Height = 21
      DataField = 'ESTQ1'
      DataSource = DataSource1
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 24
      Top = 336
      Width = 134
      Height = 21
      DataField = 'LOJ2'
      DataSource = DataSource1
      TabOrder = 8
    end
    object DBEdit10: TDBEdit
      Left = 256
      Top = 32
      Width = 134
      Height = 21
      DataField = 'ESTQ2'
      DataSource = DataSource1
      TabOrder = 9
    end
    object DBEdit11: TDBEdit
      Left = 256
      Top = 72
      Width = 134
      Height = 21
      DataField = 'LOJ3'
      DataSource = DataSource1
      TabOrder = 10
    end
    object DBEdit12: TDBEdit
      Left = 256
      Top = 112
      Width = 134
      Height = 21
      DataField = 'ESTQ3'
      DataSource = DataSource1
      TabOrder = 11
    end
    object DBEdit13: TDBEdit
      Left = 256
      Top = 152
      Width = 134
      Height = 21
      DataField = 'UNIT'
      DataSource = DataSource1
      TabOrder = 12
    end
    object DBEdit14: TDBEdit
      Left = 256
      Top = 192
      Width = 134
      Height = 21
      DataField = 'TOTAL'
      DataSource = DataSource1
      TabOrder = 13
    end
  end
  inherited Panel2: TPanel
    Top = 436
    Width = 491
    inherited DBButton4: TDBButton
      Left = 408
      DataSource = DataSource1
    end
    inherited DBButton5: TDBButton
      Left = 328
      Enabled = True
      DataSource = DataSource1
    end
  end
  inherited Panel3: TPanel
    Width = 491
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataSource1
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataSource1
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataSource1
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataSource1
      Hints.Strings = ()
    end
  end
  object Table1: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'BAIXAS.DBF'
    UpdateObject = UpdateSQL1
    Left = 200
    Top = 217
    object Table1CODIBX: TFloatField
      FieldName = 'CODIBX'
    end
    object Table1PRODUTO: TStringField
      FieldName = 'PRODUTO'
      Size = 15
    end
    object Table1REF: TStringField
      FieldName = 'REF'
      Size = 15
    end
    object Table1CLASS: TStringField
      FieldName = 'CLASS'
      Size = 10
    end
    object Table1LOCAL: TStringField
      FieldName = 'LOCAL'
      Size = 5
    end
    object Table1DATA: TDateField
      FieldName = 'DATA'
    end
    object Table1LOJ1: TSmallintField
      FieldName = 'LOJ1'
    end
    object Table1ESTQ1: TFloatField
      FieldName = 'ESTQ1'
    end
    object Table1LOJ2: TSmallintField
      FieldName = 'LOJ2'
    end
    object Table1ESTQ2: TFloatField
      FieldName = 'ESTQ2'
    end
    object Table1LOJ3: TSmallintField
      FieldName = 'LOJ3'
    end
    object Table1ESTQ3: TFloatField
      FieldName = 'ESTQ3'
    end
    object Table1UNIT: TFloatField
      FieldName = 'UNIT'
    end
    object Table1TOTAL: TFloatField
      FieldName = 'TOTAL'
    end
  end
  object UpdateSQL1: TUpdateSQL
    ModifySQL.Strings = (
      'update "BAIXAS.DBF"'
      'set'
      '  PRODUTO = :PRODUTO,'
      '  REF = :REF,'
      '  CLASS = :CLASS,'
      '  LOCAL = :LOCAL,'
      '  DATA = :DATA,'
      '  LOJ1 = :LOJ1,'
      '  ESTQ1 = :ESTQ1,'
      '  LOJ2 = :LOJ2,'
      '  ESTQ2 = :ESTQ2,'
      '  LOJ3 = :LOJ3,'
      '  ESTQ3 = :ESTQ3,'
      '  UNIT = :UNIT,'
      '  TOTAL = :TOTAL'
      'where'
      '  CODIBX = :OLD_CODIBX')
    InsertSQL.Strings = (
      'insert into "BAIXAS.DBF"'
      
        '  (PRODUTO, REF, CLASS, LOCAL, DATA, LOJ1, ESTQ1, LOJ2, ESTQ2, L' +
        'OJ3, '
      'ESTQ3, '
      '   UNIT, TOTAL)'
      'values'
      
        '  (:PRODUTO, :REF, :CLASS, :LOCAL, :DATA, :LOJ1, :ESTQ1, :LOJ2, ' +
        ':ESTQ2, '
      '   :LOJ3, :ESTQ3, :UNIT, :TOTAL)')
    DeleteSQL.Strings = (
      'delete from "BAIXAS.DBF"'
      'where'
      '  CODIBX = :OLD_CODIBX')
    Left = 200
    Top = 257
  end
  object DataSource1: TDataSource
    DataSet = Table1
    Left = 200
    Top = 297
  end
end
