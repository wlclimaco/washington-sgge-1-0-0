object Form12: TForm12
  Left = 277
  Top = 186
  Width = 444
  Height = 386
  Caption = 'Form12'
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
    Width = 436
    Height = 352
    Align = alClient
    TabOrder = 0
    object DBGrid1: TDBGrid
      Left = 1
      Top = 1
      Width = 434
      Height = 350
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
          FieldName = 'COD_EMPRESA'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'CDFILIAL'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'NOME'
          Width = 194
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
      'select *from filial')
    Left = 107
    Top = 21
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 110
    Top = 51
  end
end
