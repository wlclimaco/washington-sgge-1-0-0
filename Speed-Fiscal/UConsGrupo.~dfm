object FrmConsGrupo: TFrmConsGrupo
  Left = 307
  Top = 267
  Width = 463
  Height = 246
  Caption = 'FrmConsGrupo'
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
    Width = 455
    Height = 212
    Align = alClient
    TabOrder = 0
    object dbgrid1: TDBGrid
      Left = 1
      Top = 1
      Width = 453
      Height = 210
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
          FieldName = 'GRUPO'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'DSGRUPO'
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
      'select *from GRUPO')
    Left = 91
    Top = 131
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 136
    Top = 128
  end
end
