object Form11: TForm11
  Left = 279
  Top = 226
  Width = 462
  Height = 301
  Caption = 'Form11'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 454
    Height = 267
    Align = alClient
    TabOrder = 0
    object dbgrid1: TDBGrid
      Left = 1
      Top = 1
      Width = 452
      Height = 265
      Align = alClient
      DataSource = DataSource1
      TabOrder = 0
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'MS Sans Serif'
      TitleFont.Style = []
      OnDrawColumnCell = dbgrid1DrawColumnCell
      OnDblClick = dbgrid1DblClick
      Columns = <
        item
          Expanded = False
          FieldName = 'COD_EMPRESA'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'NOME'
          Width = 171
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'CNPJ'
          Width = 155
          Visible = True
        end>
    end
  end
  object IBQuery1: TIBQuery
    Database = FRMCADPROD.IBDatabase1
    Transaction = FRMCADPROD.IBTransaction1
    Active = True
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from empresa')
    Left = 107
    Top = 43
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 120
    Top = 104
  end
end