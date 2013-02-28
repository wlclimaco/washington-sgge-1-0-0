object Form80: TForm80
  Left = 192
  Top = 114
  Width = 818
  Height = 544
  Caption = 'Form80'
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
  object Panel1: TPanel
    Left = 0
    Top = 57
    Width = 810
    Height = 402
    Align = alClient
    TabOrder = 0
    object DBGrid1: TDBGrid
      Left = 0
      Top = 0
      Width = 809
      Height = 401
      Align = alCustom
      DataSource = DataModule2.dsQrytitulospagar
      TabOrder = 0
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'MS Sans Serif'
      TitleFont.Style = []
      OnDblClick = DBGrid1DblClick
    end
  end
  object Panel2: TPanel
    Left = 0
    Top = 459
    Width = 810
    Height = 51
    Align = alBottom
    Color = clMedGray
    TabOrder = 1
  end
  object Panel3: TPanel
    Left = 0
    Top = 0
    Width = 810
    Height = 57
    Align = alTop
    Color = clSilver
    TabOrder = 2
    object Label1: TLabel
      Left = 232
      Top = 8
      Width = 366
      Height = 37
      Caption = 'Baixas de titulos pagar'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -32
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold, fsItalic]
      ParentFont = False
    end
    object SpeedButton1: TSpeedButton
      Left = 112
      Top = 16
      Width = 65
      Height = 22
      OnClick = SpeedButton1Click
    end
  end
end
