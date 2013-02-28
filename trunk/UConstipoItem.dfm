object Form13: TForm13
  Left = 301
  Top = 262
  Width = 244
  Height = 300
  Caption = 'Form13'
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
    Width = 236
    Height = 266
    Align = alClient
    TabOrder = 0
    object DBGrid1: TDBGrid
      Left = 1
      Top = 1
      Width = 234
      Height = 264
      Align = alClient
      DataSource = DataSource1
      TabOrder = 0
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'MS Sans Serif'
      TitleFont.Style = []
      OnDrawColumnCell = DBGrid1DrawColumnCell
      OnDblClick = DBGrid1DblClick
      Columns = <
        item
          Expanded = False
          FieldName = 'COD_TIPO_ITEM'
          Width = 23
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'DESCRICAO'
          Width = 174
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
      'select * from TIPO_ITEM')
    Left = 105
    Top = 20
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 110
    Top = 51
  end
end
