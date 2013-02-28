inherited FrmExcluirtrasnf: TFrmExcluirtrasnf
  Left = 198
  Top = 157
  Width = 907
  Height = 609
  Caption = 'FrmExcluirtrasnf'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 760
    Height = 510
    object DBGrid1: TDBGrid
      Left = 56
      Top = 0
      Width = 753
      Height = 465
      DataSource = DataSource1
      TabOrder = 0
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'MS Sans Serif'
      TitleFont.Style = []
      Columns = <
        item
          Expanded = False
          FieldName = 'CODTRA'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'CODPRO'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'PRODUTO'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'DATA'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'ESTQ3'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'REF'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'SAID1'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'SAID2'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'SAID3'
          Visible = True
        end>
    end
  end
  inherited Panel3: TPanel
    Width = 899
  end
  inherited Panel2: TPanel
    Left = 760
    Width = 139
    Height = 510
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataSource1
    end
    inherited DBButton2: TDBButton
      DataSource = DataSource1
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataSource1
    end
    inherited DBButton4: TDBButton
      Enabled = True
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
  object UpdateSQL1: TUpdateSQL
    ModifySQL.Strings = (
      'update "TRANSFER.DBF"'
      'set'
      '  CODTRA = :CODTRA,'
      '  PRODUTO = :PRODUTO,'
      '  REF = :REF,'
      '  CODPRO = :CODPRO,'
      '  LOJA1 = :LOJA1,'
      '  ENTR1 = :ENTR1,'
      '  SAID1 = :SAID1,'
      '  ESTQ1 = :ESTQ1,'
      '  LOJA2 = :LOJA2,'
      '  ENTR2 = :ENTR2,'
      '  SAID2 = :SAID2,'
      '  ESTQ2 = :ESTQ2,'
      '  LOJA3 = :LOJA3,'
      '  ENTR3 = :ENTR3,'
      '  SAID3 = :SAID3,'
      '  ESTQ3 = :ESTQ3,'
      '  DATA = :DATA'
      'where'
      '  CODTRA = :OLD_CODTRA and'
      '  PRODUTO = :OLD_PRODUTO and'
      '  REF = :OLD_REF and'
      '  CODPRO = :OLD_CODPRO and'
      '  LOJA1 = :OLD_LOJA1 and'
      '  ENTR1 = :OLD_ENTR1 and'
      '  SAID1 = :OLD_SAID1 and'
      '  ESTQ1 = :OLD_ESTQ1 and'
      '  LOJA2 = :OLD_LOJA2 and'
      '  ENTR2 = :OLD_ENTR2 and'
      '  SAID2 = :OLD_SAID2 and'
      '  ESTQ2 = :OLD_ESTQ2 and'
      '  LOJA3 = :OLD_LOJA3 and'
      '  ENTR3 = :OLD_ENTR3 and'
      '  SAID3 = :OLD_SAID3 and'
      '  ESTQ3 = :OLD_ESTQ3 and'
      '  DATA = :OLD_DATA')
    InsertSQL.Strings = (
      'insert into "TRANSFER.DBF"'
      
        '  (CODTRA, PRODUTO, REF, CODPRO, LOJA1, ENTR1, SAID1, ESTQ1, LOJ' +
        'A2, '
      'ENTR2, '
      '   SAID2, ESTQ2, LOJA3, ENTR3, SAID3, ESTQ3, DATA)'
      'values'
      
        '  (:CODTRA, :PRODUTO, :REF, :CODPRO, :LOJA1, :ENTR1, :SAID1, :ES' +
        'TQ1, '
      ':LOJA2, '
      
        '   :ENTR2, :SAID2, :ESTQ2, :LOJA3, :ENTR3, :SAID3, :ESTQ3, :DATA' +
        ')')
    DeleteSQL.Strings = (
      'delete from "TRANSFER.DBF"'
      'where'
      '  CODTRA = :OLD_CODTRA and'
      '  PRODUTO = :OLD_PRODUTO and'
      '  REF = :OLD_REF and'
      '  CODPRO = :OLD_CODPRO and'
      '  LOJA1 = :OLD_LOJA1 and'
      '  ENTR1 = :OLD_ENTR1 and'
      '  SAID1 = :OLD_SAID1 and'
      '  ESTQ1 = :OLD_ESTQ1 and'
      '  LOJA2 = :OLD_LOJA2 and'
      '  ENTR2 = :OLD_ENTR2 and'
      '  SAID2 = :OLD_SAID2 and'
      '  ESTQ2 = :OLD_ESTQ2 and'
      '  LOJA3 = :OLD_LOJA3 and'
      '  ENTR3 = :OLD_ENTR3 and'
      '  SAID3 = :OLD_SAID3 and'
      '  ESTQ3 = :OLD_ESTQ3 and'
      '  DATA = :OLD_DATA')
    Left = 600
    Top = 16
  end
  object Table1: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'TRANSFER.DBF'
    Left = 568
    Top = 16
  end
  object DataSource1: TDataSource
    DataSet = Query1
    Left = 536
    Top = 16
  end
  object Query1: TQuery
    Active = True
    CachedUpdates = True
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select *from transfer'
      'order by codtra,codpro,data')
    UpdateMode = upWhereKeyOnly
    UpdateObject = UpdateSQL1
    Left = 640
    Top = 16
  end
end
