object Form106: TForm106
  Left = 286
  Top = 247
  Width = 195
  Height = 222
  Caption = 'Form106'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 9
    Top = 29
    Width = 59
    Height = 13
    Caption = 'Data Inicial :'
  end
  object Label2: TLabel
    Left = 9
    Top = 77
    Width = 54
    Height = 13
    Caption = 'Data Final :'
  end
  object SpeedButton1: TSpeedButton
    Left = 104
    Top = 112
    Width = 49
    Height = 22
    OnClick = SpeedButton1Click
  end
  object MaskEdit1: TMaskEdit
    Left = 80
    Top = 24
    Width = 73
    Height = 21
    EditMask = '!99/99/0000;1;_'
    MaxLength = 10
    TabOrder = 0
    Text = '  /  /    '
  end
  object MaskEdit2: TMaskEdit
    Left = 80
    Top = 72
    Width = 73
    Height = 21
    EditMask = '!99/99/0000;1;_'
    MaxLength = 10
    TabOrder = 1
    Text = '  /  /    '
  end
  object Query1: TQuery
    Active = True
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select *from movimentodiario')
    Left = 40
    Top = 112
  end
  object Query2: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select * from  vendasvendedora ')
    Left = 8
    Top = 112
  end
  object DataSource1: TDataSource
    DataSet = Query2
    Left = 8
    Top = 144
  end
  object DataSource2: TDataSource
    DataSet = Query1
    Left = 40
    Top = 144
  end
end
