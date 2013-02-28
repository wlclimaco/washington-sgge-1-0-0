object CONSBANCOS: TCONSBANCOS
  Left = 259
  Top = 142
  Width = 690
  Height = 414
  Caption = 'CONSBANCOS'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  OnActivate = FormActivate
  PixelsPerInch = 96
  TextHeight = 13
  object DBGrid1: TDBGrid
    Left = 0
    Top = 48
    Width = 682
    Height = 289
    DataSource = DataSource1
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = 'MS Sans Serif'
    TitleFont.Style = []
  end
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 681
    Height = 49
    TabOrder = 1
    object Label1: TLabel
      Left = 289
      Top = 9
      Width = 87
      Height = 29
      Align = alCustom
      Caption = 'Bancos'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -24
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
  end
  object Panel2: TPanel
    Left = 0
    Top = 339
    Width = 682
    Height = 41
    Align = alBottom
    TabOrder = 2
    object SpeedButton2: TSpeedButton
      Left = 576
      Top = 8
      Width = 81
      Height = 22
      Caption = '&SAIR'
      OnClick = SpeedButton2Click
    end
    object SpeedButton3: TSpeedButton
      Left = 480
      Top = 8
      Width = 81
      Height = 22
      Caption = '&RELATORIO'
      OnClick = SpeedButton3Click
    end
  end
  object Query1: TQuery
    Active = True
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select *from produtos')
    Left = 408
    Top = 16
  end
  object DataSource1: TDataSource
    DataSet = Query1
    Left = 440
    Top = 16
  end
end
