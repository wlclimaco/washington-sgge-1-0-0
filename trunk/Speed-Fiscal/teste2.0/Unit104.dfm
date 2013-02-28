object Form104: TForm104
  Left = 261
  Top = 241
  Width = 691
  Height = 138
  Caption = 'Form104'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Button1: TButton
    Left = 192
    Top = 16
    Width = 75
    Height = 25
    Caption = 'Button1'
    TabOrder = 0
    OnClick = Button1Click
  end
  object MaskEdit1: TMaskEdit
    Left = 48
    Top = 16
    Width = 65
    Height = 21
    EditMask = '!99/99/0000;1;_'
    MaxLength = 10
    TabOrder = 1
    Text = '  /  /    '
  end
  object ProgressBar1: TProgressBar
    Left = 0
    Top = 80
    Width = 681
    Height = 17
    TabOrder = 2
  end
  object MaskEdit2: TMaskEdit
    Left = 120
    Top = 16
    Width = 65
    Height = 21
    EditMask = '!99/99/0000;1;_'
    MaxLength = 10
    TabOrder = 3
    Text = '  /  /    '
  end
  object Query1: TQuery
    DatabaseName = 'laluna1'
    Left = 616
    Top = 16
  end
  object Table1: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'inventario.DB'
    Left = 584
    Top = 16
    object Table1Codpro: TFloatField
      FieldName = 'Codpro'
    end
    object Table1Ref: TStringField
      FieldName = 'Ref'
    end
    object Table1Qtentrada: TFloatField
      FieldName = 'Qtentrada'
    end
    object Table1Data: TDateField
      FieldName = 'Data'
    end
    object Table1Qtsaida: TFloatField
      FieldName = 'Qtsaida'
    end
    object Table1Estoque: TFloatField
      FieldName = 'Estoque'
    end
    object Table1Estoquemesant: TFloatField
      FieldName = 'Estoquemesant'
    end
  end
end
