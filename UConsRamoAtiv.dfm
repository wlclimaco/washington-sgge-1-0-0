object FRMCONSRAMO: TFRMCONSRAMO
  Left = 414
  Top = 291
  Width = 413
  Height = 243
  Caption = 'FRMCONSRAMO'
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
    Width = 405
    Height = 209
    Align = alClient
    TabOrder = 0
    object DBGRID1: TDBGrid
      Left = 1
      Top = 1
      Width = 403
      Height = 207
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
          FieldName = 'RAMO'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'DESCRICAO'
          Width = 239
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'SIGLA'
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
      'select *FROM RAMO_ATIVIDADE')
    Left = 125
    Top = 23
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 76
    Top = 22
  end
end
