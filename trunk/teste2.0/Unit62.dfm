object Form62: TForm62
  Left = 219
  Top = 153
  Width = 696
  Height = 480
  Caption = 'Form62'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object memo1: TMemo
    Left = 0
    Top = 0
    Width = 688
    Height = 217
    Align = alTop
    Color = clWindowText
    Font.Charset = ANSI_CHARSET
    Font.Color = clWhite
    Font.Height = -19
    Font.Name = 'Arial'
    Font.Style = [fsBold]
    Lines.Strings = (
      '')
    ParentFont = False
    TabOrder = 0
  end
  object DBGrid1: TDBGrid
    Left = 0
    Top = 261
    Width = 688
    Height = 185
    Align = alBottom
    DataSource = DataSource1
    FixedColor = clWhite
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -16
    Font.Name = 'MS Sans Serif'
    Font.Style = []
    ParentFont = False
    TabOrder = 1
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = 'MS Sans Serif'
    TitleFont.Style = []
  end
  object Button1: TButton
    Left = 560
    Top = 216
    Width = 75
    Height = 25
    Caption = 'Button1'
    TabOrder = 2
    OnClick = Button1Click
  end
  object BitBtn1: TBitBtn
    Left = 472
    Top = 216
    Width = 75
    Height = 25
    Caption = 'BitBtn1'
    TabOrder = 3
    OnClick = BitBtn1Click
  end
  object Query1: TQuery
    DatabaseName = 'laluna1'
    Left = 16
    Top = 216
  end
  object DataSource1: TDataSource
    DataSet = Query1
    Left = 56
    Top = 216
  end
end
