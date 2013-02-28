object Form118: TForm118
  Left = 265
  Top = 242
  Width = 345
  Height = 274
  Caption = 'Form118'
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
    Width = 337
    Height = 240
    Align = alClient
    TabOrder = 0
    object Button1: TButton
      Left = 240
      Top = 184
      Width = 75
      Height = 25
      Caption = 'Button1'
      TabOrder = 0
    end
    object Button2: TButton
      Left = 240
      Top = 152
      Width = 75
      Height = 25
      Caption = 'Button2'
      TabOrder = 1
      OnClick = Button2Click
    end
  end
  object Query1: TQuery
    DatabaseName = 'laluna1'
    Left = 112
    Top = 24
  end
  object Query2: TQuery
    DatabaseName = 'laluna1'
    Left = 152
    Top = 24
  end
end
