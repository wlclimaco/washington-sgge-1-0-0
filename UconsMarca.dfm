object FConsMarca: TFConsMarca
  Left = 232
  Top = 207
  Width = 619
  Height = 244
  Caption = 'FConsMarca'
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
    Width = 611
    Height = 210
    Align = alClient
    TabOrder = 0
    object DBGrid1: TDBGrid
      Left = 1
      Top = 1
      Width = 609
      Height = 208
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
          FieldName = 'COD_MARCA'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'MARCA'
          Width = 146
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'NOME'
          Visible = True
        end>
    end
  end
  object IBQuery1: TIBQuery
    Database = FRMCADPROD.IBDatabase1
    Transaction = FRMCADPROD.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'SELECT *FROM CLIENTES_FORNECEDOR C , MARCA M '
      'WHERE M.COD_FORNECEDOR = C.COD_PART'
      ' ')
    Left = 107
    Top = 21
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 110
    Top = 51
  end
end
