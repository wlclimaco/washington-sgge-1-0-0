object FrmConsRep: TFrmConsRep
  Left = 224
  Top = 164
  Width = 672
  Height = 252
  Caption = 'FrmConsRep'
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
    Width = 664
    Height = 218
    Align = alClient
    TabOrder = 0
    object dbgrid1: TDBGrid
      Left = 1
      Top = 1
      Width = 662
      Height = 216
      Align = alClient
      DataSource = DataSource1
      TabOrder = 0
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'MS Sans Serif'
      TitleFont.Style = []
      OnDblClick = dbgrid1DblClick
      Columns = <
        item
          Expanded = False
          FieldName = 'COD_PART'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'CNPJ'
          Width = 180
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'NOME'
          Width = 226
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'FANTASIA'
          Width = 363
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
      'select * from CLIENTES_FORNECEDOR where REPRESENTANTE = '#39'S'#39)
    Left = 107
    Top = 43
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 120
    Top = 104
  end
end
