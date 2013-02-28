object Form72: TForm72
  Left = 192
  Top = 114
  Width = 696
  Height = 480
  Caption = 'Form72'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object DBGrid1: TDBGrid
    Left = 0
    Top = 49
    Width = 688
    Height = 356
    Align = alClient
    DataSource = DataModule2.dsNatureza
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = 'MS Sans Serif'
    TitleFont.Style = []
    OnDblClick = DBGrid1DblClick
  end
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 688
    Height = 49
    Align = alTop
    Color = clWhite
    TabOrder = 1
  end
  object Panel2: TPanel
    Left = 0
    Top = 405
    Width = 688
    Height = 41
    Align = alBottom
    Color = clWhite
    TabOrder = 2
  end
end
