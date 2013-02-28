object ConsTransacao: TConsTransacao
  Left = 427
  Top = 286
  Width = 515
  Height = 248
  Caption = 'ConsTransacao'
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
    Width = 507
    Height = 214
    Align = alClient
    TabOrder = 0
    object DBGrid1: TDBGrid
      Left = 1
      Top = 1
      Width = 505
      Height = 212
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
    end
  end
  object IBQuery1: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    Active = True
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from TRANSACAO')
    Left = 171
    Top = 141
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 136
    Top = 141
  end
end
