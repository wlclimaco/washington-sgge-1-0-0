object CONSCAP: TCONSCAP
  Left = 200
  Top = 141
  Width = 831
  Height = 480
  Caption = 'CONSCAP'
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
    Top = 112
    Width = 817
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
    Width = 823
    Height = 105
    Align = alCustom
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
      Left = 376
      Top = 8
      Width = 41
      Height = 22
      OnClick = SpeedButton1Click
    end
    object Edit1: TEdit
      Left = 88
      Top = 8
      Width = 273
      Height = 21
      TabOrder = 0
    end
    object GroupBox1: TGroupBox
      Left = 24
      Top = 40
      Width = 761
      Height = 57
      Caption = 'TIPO'
      TabOrder = 1
      object RadioButton1: TRadioButton
        Left = 16
        Top = 24
        Width = 153
        Height = 17
        Caption = 'CODPLC'
        TabOrder = 0
      end
      object RadioButton2: TRadioButton
        Left = 136
        Top = 24
        Width = 113
        Height = 17
        Caption = 'DESCRI'#199#195'O'
        TabOrder = 1
      end
      object RadioButton3: TRadioButton
        Left = 312
        Top = 24
        Width = 113
        Height = 17
        Caption = 'CODCLI'
        TabOrder = 2
      end
      object RadioButton4: TRadioButton
        Left = 472
        Top = 24
        Width = 153
        Height = 17
        Caption = 'RAZ'#195'O SOCIAL'
        TabOrder = 3
      end
      object RadioButton5: TRadioButton
        Left = 584
        Top = 24
        Width = 153
        Height = 17
        Caption = 'DOC NUMERO'
        TabOrder = 4
      end
    end
  end
  object Panel2: TPanel
    Left = 0
    Top = 405
    Width = 823
    Height = 41
    Align = alBottom
    TabOrder = 2
    object SpeedButton2: TSpeedButton
      Left = 600
      Top = 8
      Width = 89
      Height = 22
      Caption = '&RELATORIO'
      OnClick = SpeedButton2Click
    end
    object SpeedButton3: TSpeedButton
      Left = 704
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
      'select *from cap')
    Left = 464
    Top = 8
  end
  object DataSource1: TDataSource
    DataSet = Query1
    Left = 464
    Top = 40
  end
end
