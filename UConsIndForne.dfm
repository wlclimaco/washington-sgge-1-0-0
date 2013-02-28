object Form17: TForm17
  Left = 393
  Top = 262
  Width = 394
  Height = 227
  Caption = 'Form17'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 386
    Height = 193
    Align = alClient
    TabOrder = 0
    object DBGRID1: TDBGrid
      Left = 1
      Top = 1
      Width = 384
      Height = 191
      Align = alClient
      DataSource = DataSource1
      TabOrder = 0
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'MS Sans Serif'
      TitleFont.Style = []
      OnDrawColumnCell = DBGRID1DrawColumnCell
      OnDblClick = DBGRID1DblClick
      Columns = <
        item
          Expanded = False
          FieldName = 'CODIGO'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'VALOR'
          Width = 281
          Visible = True
        end>
    end
  end
  object IBQuery1: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    Active = True
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from IND_FORNECEDOR')
    Left = 125
    Top = 23
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 76
    Top = 22
  end
end
