object Form76: TForm76
  Left = 213
  Top = 214
  Width = 696
  Height = 480
  Caption = 'Form76'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object DBText1: TDBText
    Left = 88
    Top = 16
    Width = 313
    Height = 17
  end
  object Button1: TButton
    Left = 88
    Top = 48
    Width = 75
    Height = 25
    Caption = 'Button1'
    TabOrder = 0
  end
  object Edit1: TEdit
    Left = 8
    Top = 16
    Width = 65
    Height = 21
    TabOrder = 1
    OnExit = Edit1Exit
  end
  object Edit2: TEdit
    Left = 8
    Top = 48
    Width = 65
    Height = 21
    TabOrder = 2
  end
  object DataSource1: TDataSource
    DataSet = DataModule2.Query9
    Left = 496
    Top = 16
  end
  object Query1: TQuery
    DatabaseName = 'laluna1'
    Left = 456
    Top = 16
  end
end
