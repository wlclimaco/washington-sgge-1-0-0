object consMERCAD: TconsMERCAD
  Left = 192
  Top = 114
  Width = 622
  Height = 480
  Caption = 'consMERCAD'
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
    Top = 113
    Width = 614
    Height = 292
    Align = alClient
    DataSource = DataModule2.dsMERCAD
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
    Width = 614
    Height = 113
    Align = alTop
    TabOrder = 1
    object Label1: TLabel
      Left = 24
      Top = 16
      Width = 56
      Height = 16
      Caption = 'BUSCAR'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'MS Sans Serif'
      Font.Style = []
      ParentFont = False
    end
    object SpeedButton1: TSpeedButton
      Left = 372
      Top = 12
      Width = 41
      Height = 23
    end
    object Edit1: TEdit
      Left = 88
      Top = 12
      Width = 273
      Height = 21
      TabOrder = 0
    end
    object GroupBox1: TGroupBox
      Left = 1
      Top = 56
      Width = 612
      Height = 56
      Align = alBottom
      Caption = 'TIPO'
      TabOrder = 1
      object RadioButton1: TRadioButton
        Left = 16
        Top = 24
        Width = 105
        Height = 17
        Caption = 'CODIGO'
        TabOrder = 0
      end
      object RadioButton2: TRadioButton
        Left = 136
        Top = 24
        Width = 113
        Height = 17
        Caption = 'CODMAT'
        TabOrder = 1
      end
      object RadioButton3: TRadioButton
        Left = 312
        Top = 24
        Width = 113
        Height = 17
        Caption = 'PRODUTO'
        TabOrder = 2
      end
    end
  end
  object Panel2: TPanel
    Left = 0
    Top = 405
    Width = 614
    Height = 41
    Align = alBottom
    TabOrder = 2
    object SpeedButton2: TSpeedButton
      Left = 424
      Top = 8
      Width = 81
      Height = 22
    end
    object SpeedButton3: TSpeedButton
      Left = 520
      Top = 8
      Width = 81
      Height = 22
    end
  end
end
