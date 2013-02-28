object CONSBOLETAS: TCONSBOLETAS
  Left = 226
  Top = 493
  Width = 696
  Height = 428
  Caption = 'CONSBOLETAS'
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
    Top = 64
    Width = 688
    Height = 289
    Align = alCustom
    DataSource = DataModule2.dsBOLETAS
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
    Height = 65
    TabOrder = 1
    object Label1: TLabel
      Left = 272
      Top = 16
      Width = 156
      Height = 37
      Caption = 'BOLETAS'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -32
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
  end
  object Panel2: TPanel
    Left = 0
    Top = 353
    Width = 688
    Height = 41
    Align = alBottom
    TabOrder = 2
    object SpeedButton2: TSpeedButton
      Left = 488
      Top = 8
      Width = 81
      Height = 22
      Caption = '&RELATORIO'
      OnClick = SpeedButton2Click
    end
    object SpeedButton3: TSpeedButton
      Left = 584
      Top = 8
      Width = 81
      Height = 22
      Caption = '&SAIR'
      OnClick = SpeedButton3Click
    end
  end
  object Query1: TQuery
    Active = True
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select *from boleta')
    Left = 448
    Top = 16
  end
  object DataSource1: TDataSource
    DataSet = Query1
    Left = 488
    Top = 16
  end
end
